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
package org.modelio.platform.mda.infra.service.impl.controller.load;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.data.module.JaxbModelPersistence;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Command;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Commands;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Tools;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Tool;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.contributions.IDynamicModelLoader;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.controller.load.command.CommandReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.diagram.palette.DiagramToolReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.diagram.type.DiagramTypeReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.diagram.wizard.DiagramWizardReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.menu.ContextualMenuReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.parameter.ParameterReader;
import org.modelio.platform.mda.infra.service.impl.controller.load.property.PropertyPageReader;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vbasic.files.FileUtils;

/**
 * Service class that reads the module.xml file and deserialize its content (basically: commands and diagrams customization).
 * <p>
 * Since Modelio 3.4 can delegate to extension points.
 */
@objid ("1a5e07c3-018d-11e2-9fca-001ec947c8cc")
public class DynamicModelImporter {
    @objid ("6300cb30-0a58-473e-9c3b-1f9d29188135")
    private static final String EXTENSION_POINT_ID = "org.modelio.platform.mda.infra.dynamicmodel";

    @objid ("5a5479cc-c4f0-4e40-baac-c7fcb476890f")
    private final IRTModuleAccess module;

    @objid ("f13a1f3d-59b9-477b-8899-f18559c4c60d")
    private final List<IDynamicModelLoader> loaders = new ArrayList<>();

    /**
     * Load the dynamic GUI model of a module from its path.
     * <p>
     * Relies on several 'reader' classes:
     * <ul>
     * <li>{@link CommandReader} for command definitions.</li>
     * <li>{@link ContextualMenuReader} for contextual menu configuration.</li>
     * <li>{@link PropertyPageReader} for module view.</li>
     * <li>{@link DiagramToolReader} for diagram tool registering.</li>
     * <li>{@link DiagramTypeReader} for diagram definitions.</li>
     * <li>{@link DiagramWizardReader} for diagram contributions to the creation wizard.</li>
     * <li>{@link ParameterReader} for parameter edition model.</li>
     * <li>{@link MatrixTypeReader} for matrix definitions.</li>
     * <li>{@link MatrixWizardReader} for matrix contributions to the creation wizard.</li>
     * </ul>
     * </p>
     * @param dynamicModelPath the xml file to load.
     * @throws ModuleException when an error occurs during the parsing.
     */
    @objid ("1a5e07cb-018d-11e2-9fca-001ec947c8cc")
    public void loadDynamicModel(final Path dynamicModelPath) throws ModuleException {
        try {
            // Load xml File into model (JAXB), using the ModuleLoader from core.project plugin
            final Jxbv2Module jaxbModule = JaxbModelPersistence.loadJaxbModel(dynamicModelPath);
        
            // Use a CompletableFuture to avoid syncExec catching our exceptions...
            CompletableFuture.runAsync(() -> runRegisterDynamicFeatures(jaxbModule), Display.getDefault()::syncExec)
            .join();
        } catch (final IOException e) {
            final String msg = MdaInfra.I18N.getMessage("DynamicModelFeature.readFailed",
                    this.module.getName(), FileUtils.getLocalizedMessage(e));
            throw new ModuleException(msg, e);
        } catch (final CompletionException e) {
            final String msg = MdaInfra.I18N.getMessage("DynamicModelFeature.readFailed",
                    this.module.getName(), e.getLocalizedMessage());
            throw new ModuleException(msg, e);
        } catch (final RuntimeException e) {
            final String msg = MdaInfra.I18N.getMessage("DynamicModelFeature.readFailed",
                    this.module.getName(), e.toString());
        
            throw new ModuleException(msg, e);
        }
        
    }

    /**
     * @param module the module to build GUI elements for.
     */
    @objid ("9c6d98a8-9ff5-4876-8745-9b0604586e20")
    public  DynamicModelImporter(final IRTModuleAccess module) {
        this.module = module;
        
        // Load all dynamic loaders from extension registry.
        for (final IConfigurationElement configEl : new ExtensionPointContributionManager(DynamicModelImporter.EXTENSION_POINT_ID).getExtensions("loader")) {
            try {
                final IDynamicModelLoader loader = (IDynamicModelLoader) configEl.createExecutableExtension("class");
                this.loaders.add(loader);
            } catch (final CoreException e) {
                MdaInfra.LOG.warning(e);
            }
        }
        
    }

    /**
     * Load the dynamic GUI model of a module from its path.
     * <p>
     * This method must be run in the SWT thread and is meant to be passed as lambda to {@link Display#syncExec(Runnable)}.
     * @param jaxbModule the dynamic model
     * @throws CompletionException on IOException, the message is already translated.
     */
    @objid ("bd1f9154-89b6-4c86-a314-f9c52af0e04e")
    private void runRegisterDynamicFeatures(final Jxbv2Module jaxbModule) throws CompletionException {
        final Map<String, IModuleAction> commandCache = new HashMap<>();
        
        final CommandReader commandReader = new CommandReader();
        final ContextualMenuReader contextualMenuReader = new ContextualMenuReader();
        final PropertyPageReader propertyPageReader = new PropertyPageReader();
        final DiagramWizardReader diagramWizardHelper = new DiagramWizardReader();
        final DiagramToolReader diagramToolReader = new DiagramToolReader();
        final DiagramTypeReader diagramTypeReader = new DiagramTypeReader();
        final ParameterReader parameterReader = new ParameterReader();
        
        try {
            final Jxbv2Parameters parameters = jaxbModule.getParameters();
            final IRTModuleAccess lmodule = DynamicModelImporter.this.module;
            if (parameters != null) {
                parameterReader.registerParameterModel(parameters, lmodule);
            }
        
            final Jxbv2Gui gui = jaxbModule.getGui();
            if (gui == null) {
                return;
            }
        
            // Jxbv2Commands: module commands
            final Jxbv2Commands commands = gui.getCommands();
            if (commands != null) {
                for (final Jxbv2Command cmdDef : commands.getCommand()) {
                    // standard command, described by a handler class.
                    final IModuleAction action = commandReader.createCommand(cmdDef, lmodule);
                    commandCache.put(cmdDef.getId(), action);
                }
            }
        
            // Jxbv2ContextualMenu: Module contextual menu contributions
            for (final Jxbv2ContextualMenu menu : gui.getContextualMenu()) {
                contextualMenuReader.registerContextualMenu(lmodule, menu, commandCache);
            }
        
            // Jxbv2PropertyPage: module property pages
            final Jxbv2Views views = gui.getViews();
            if (views != null) {
                for (final Jxbv2PropertyPage pp : views.getPropertyPage()) {
                    propertyPageReader.registerPropertyPanel(pp, lmodule, commandCache);
                }
            }
        
            // Jxbv2Tools: module diagram tools
            // The 'class" defined for the tool can be either a keyword (one of Box, Link, MultiLink, AttachedBox) or a
            // qualified Java class name. Keyword values are used as shortcuts for default implementation of tools.
            final Jxbv2Tools tools = gui.getTools();
            if (tools != null) {
                for (final Jxbv2Tool toolDef : tools.getTool()) {
                    // Register the tool
                    diagramToolReader.registerTool(toolDef, lmodule);
                }
            }
        
            // Jxbv2Diagrams: module defined diagram types
            final Jxbv2Diagrams diagrams = gui.getDiagrams();
            if (diagrams != null) {
                final ContributorCategory wizardCategory = new ContributorCategory(lmodule.getLabel(), lmodule.getLabel(), lmodule.getModuleImage());
                for (final Jxbv2DiagramType diagramDef : diagrams.getDiagramType()) {
                    // Create and Register the diagram type
                    diagramTypeReader.registerDiagramType(diagramDef, lmodule);
        
                    // Jxbv2Wizard contribution
                    final Jxbv2Wizard wizardDef = diagramDef.getWizard();
                    if (wizardDef != null) {
                        diagramWizardHelper.registerWizard(lmodule, wizardDef, wizardCategory);
                    }
                }
            }
        
            // call dynamic loaders
            for (final IDynamicModelLoader loader : DynamicModelImporter.this.loaders) {
                loader.loadDynamicModel(lmodule, gui);
            }
        } catch (final IOException e) {
            // Wrap into CompletionException.
            throw new CompletionException(FileUtils.getLocalizedMessage(e), e);
        } catch (final ModuleException e) {
            // Wrap into CompletionException.
            throw new CompletionException(e.getLocalizedMessage(), e);
        }
        
    }

}
