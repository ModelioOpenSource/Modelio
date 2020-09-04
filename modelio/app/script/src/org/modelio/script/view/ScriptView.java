/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.script.view;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.Modelio;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.script.IInputView;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.engine.core.engine.ScriptRunnerFactory;
import org.modelio.script.options.ScriptOptions;
import org.modelio.script.plugin.Script;
import org.modelio.ui.UIColor;

/**
 * Script view.
 * <p>
 * The script view contains 2 zones:
 * <ul>
 * <li>An output zone that displays messages and script output.</li>
 * <li>An input zone where the user enters the script code.</li>
 * </ul>
 */
@objid ("007ee0f0-663d-105c-84ef-001ec947cd2a")
public class ScriptView {
    @objid ("0018836e-207a-106b-bf4f-001ec947cd2a")
    public static final String PARTID = "org.modelio.script.part";

    @objid ("007f0fa8-663d-105c-84ef-001ec947cd2a")
    private InputView inputView;

    @objid ("007f15d4-663d-105c-84ef-001ec947cd2a")
    private OutputView outputView;

    @objid ("007f1bd8-663d-105c-84ef-001ec947cd2a")
    private PrintWriter errorWriter;

    @objid ("007f2222-663d-105c-84ef-001ec947cd2a")
    private PrintWriter outputWriter;

    @objid ("007f2966-663d-105c-84ef-001ec947cd2a")
    private PrintWriter commandWriter;

    @objid ("00725a6a-7102-1064-a2b8-001ec947cd2a")
    private ScriptViewSelectionGetter selectionGetter;

    @objid ("000cca56-cbce-1065-a2b8-001ec947cd2a")
    private IScriptRunner jythonRunner;

    @objid ("005311dc-f1bd-106a-bf4f-001ec947cd2a")
    private SashForm shform;

    @objid ("00531ac4-f1bd-106a-bf4f-001ec947cd2a")
    private Font font;

    @objid ("000a5c26-2079-106b-bf4f-001ec947cd2a")
    @Inject
    private static EPartService partService;

    @objid ("007b1e0c-52bf-106c-80fa-001ec947cd2a")
    @Inject
    @Optional
    private IModuleService moduleService;

    @objid ("a5e9c72b-5637-43e9-bb05-6ba993ed2567")
    private ScriptOptions options = new ScriptOptions(null);

    @objid ("b0cc34a0-c038-4173-83cd-cbd918e7c321")
    @Inject
    private IProjectService projectService;

    @objid ("007f49be-663d-105c-84ef-001ec947cd2a")
    @PostConstruct
    public void createPartControl(Composite parent, MApplication application) {
        Display display = parent.getDisplay();
        
        this.shform = new SashForm(parent, SWT.VERTICAL | SWT.NO_REDRAW_RESIZE);
        this.font = new Font(display, "Courier New", 10, SWT.NORMAL);
        
        this.outputView = new OutputView(this.shform, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        this.outputView.setFont(this.font);
        this.outputView.setBackground(UIColor.POSTIT_YELLOW);
        
        this.inputView = new InputView(this.shform, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, this);
        this.inputView.getControl().setFont(this.font);
        this.inputView.getControl().setBackground(UIColor.WHITE);
        
        this.shform.setWeights(new int[] { 70, 30 });
        
        this.outputWriter = new PrintWriter(new BufferedWriter(new OutputWriter(this.outputView,
                new TextStyle(null, display.getSystemColor(SWT.COLOR_DARK_GREEN), null))));
        this.errorWriter = new PrintWriter(new BufferedWriter(new OutputWriter(this.outputView,
                new TextStyle(null, display.getSystemColor(SWT.COLOR_RED), null), null /* onFlush */)));
        this.commandWriter = new PrintWriter(new BufferedWriter(
                new OutputWriter(this.outputView, new TextStyle(null, display.getSystemColor(SWT.COLOR_BLACK), null))));
        
        // Selection manager
        this.selectionGetter = ContextInjectionFactory.make(ScriptViewSelectionGetter.class, application.getContext());
        
        //
        this.jythonRunner = ScriptRunnerFactory.getInstance().getTransactionalScriptRunner("jython");
        assert (this.jythonRunner != null);
        this.jythonRunner.setCommandStream(this.commandWriter);
        this.jythonRunner.setErrorStream(this.errorWriter);
        this.jythonRunner.setOutputStream(this.outputWriter);
        
        // It may happen that the view is created while a project is already
        // opened
        // In such situation we cannot rely on Modelio events for setting up the
        // jython runner variables
        if (this.projectService != null && this.projectService.getOpenedProject() != null) {
            bindJythonRunner(this.projectService.getOpenedProject());
        }
        
        // Initialize Options
        configureOptions(getProjectPreferences());
        
        // Print help message
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                ScriptView.this.commandWriter.println(Script.I18N.getMessage("ConsoleHelp"));
                ScriptView.this.commandWriter.flush();
            }
        
        });
    }

    @objid ("007f795c-663d-105c-84ef-001ec947cd2a")
    public void dispose() {
        this.font.dispose();
        // super.dispose();
        // this.selectionGetter ???
        this.font = null;
        this.inputView = null;
        this.outputView = null;
        this.commandWriter = null;
        this.errorWriter = null;
        this.outputWriter = null;
        this.shform = null;
    }

    @objid ("007f8f8c-663d-105c-84ef-001ec947cd2a")
    public String getInputViewContent(boolean debugMode) {
        return this.inputView.popInput(debugMode);
    }

    @objid ("007fb412-663d-105c-84ef-001ec947cd2a")
    public PrintWriter getOutputWriter() {
        return this.outputWriter;
    }

    @objid ("007fd712-663d-105c-84ef-001ec947cd2a")
    public PrintWriter getErrorWriter() {
        return this.errorWriter;
    }

    /**
     * @return the commandWriter
     */
    @objid ("007ffa08-663d-105c-84ef-001ec947cd2a")
    public PrintWriter getCommandWriter() {
        return this.commandWriter;
    }

    @objid ("00802af0-663d-105c-84ef-001ec947cd2a")
    public IInputView getInputView() {
        return this.inputView;
    }

    /**
     * Get the script view.
     * 
     * @return The script view
     */
    @objid ("0080721c-663d-105c-84ef-001ec947cd2a")
    public static ScriptView getScriptView(boolean bringToFront) {
        if (ScriptView.partService != null) {
            MPart part = ScriptView.partService.findPart(ScriptView.PARTID);
            if (part != null) {
                if (bringToFront) {
                    ScriptView.partService.bringToTop(part);
                }
                return (ScriptView) part.getObject();
            }
        }
        return null;
    }

    /**
     * Clear the output field.
     */
    @objid ("0080b218-663d-105c-84ef-001ec947cd2a")
    public void clearOutputView() {
        this.outputView.setText("");
    }

    @objid ("00716b3c-572b-1064-a2b8-001ec947cd2a")
    @Inject
    @Optional
    void onProjectOpened(@EventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject openedProject) {
        bindJythonRunner(openedProject);
        configureOptions(getProjectPreferences());
    }

    @objid ("0071aae8-572b-1064-a2b8-001ec947cd2a")
    @Optional
    @SuppressWarnings("unused")
    @Inject
    void onProjectClose(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) GProject project) {
        unbindJythonRunner();
        configureOptions(null);
    }

    @objid ("0039caba-d886-1065-a2b8-001ec947cd2a")
    public IScriptRunner getScriptRunner() {
        return this.jythonRunner;
    }

    @objid ("0039e838-d886-1065-a2b8-001ec947cd2a")
    public ScriptViewSelectionGetter getSelectionGetter() {
        return this.selectionGetter;
    }

    /**
     * Binding the jython runner to the current project consists in: - binding
     * the python session variables - setting a classloader that is aware of the
     * currently started project modules classes
     * @param project
     */
    @objid ("007c43ae-52bf-106c-80fa-001ec947cd2a")
    private void bindJythonRunner(GProject project) {
        // @SuppressWarnings("resource")
        // TO REMOve ClassLoader classLoader = new
        // ScriptClassLoader(this.moduleService);
        // this.jythonRunner.setClassLoader(classLoader);
        this.jythonRunner.clearClassloader();
        this.jythonRunner.addClassLoader(this.jythonRunner.getEngine().getClass().getClassLoader());
        
        for (IRTModule module : this.moduleService.getStartedModules()) {
            this.jythonRunner.addClassLoader(module.getClass().getClassLoader());
        }
        
        this.jythonRunner.bind("coreSession", (project != null) ? project.getSession() : null);
        this.jythonRunner.bind("modelingSession", Modelio.getInstance().getModelingSession());
        this.jythonRunner.bind("session", Modelio.getInstance().getModelingSession());
    }

    @objid ("007c5f1a-52bf-106c-80fa-001ec947cd2a")
    private void unbindJythonRunner() {
        if (this.jythonRunner != null) {
            this.jythonRunner.clearClassloader();
            this.jythonRunner.bind("coreSession", null);
            this.jythonRunner.bind("modelingSession", null);
        }
    }

    @objid ("007d2d1e-52bf-106c-80fa-001ec947cd2a")
    @Focus
    public void setFocus() {
        if (this.shform != null) {
            this.shform.setFocus();
        }
    }

    @objid ("88d0858e-e4aa-4d65-b420-4b119a9c9e0a")
    private void configureOptions(IPreferenceStore projectPreferences) {
        // Link options with the current preference store.
        this.options = new ScriptOptions(projectPreferences);
    }

    @objid ("5ea6bce8-51b0-497a-bd60-814d81d77fa1")
    protected IPreferenceStore getProjectPreferences() {
        if (this.projectService != null && this.projectService.getOpenedProject() != null) {
            return this.projectService.getProjectPreferences(Script.PLUGIN_ID);
        }
        return null;
    }

    @objid ("06de48cf-832d-4c13-ab46-69e8481a762f")
    public ScriptOptions getOptions() {
        return this.options;
    }

}
