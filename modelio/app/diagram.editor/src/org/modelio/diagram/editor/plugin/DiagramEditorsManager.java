/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.rcp.inputpart.IInputPartService;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A manager for the diagram editors. This class is responsible for handling the activation request on diagram, by either opening a new diagram editor for said diagram if none already exists, or if on is found by bringing it to top so that it is
 * visible.<br>
 */
@objid ("6670d446-33f7-11e2-95fe-001ec947c8cc")
public class DiagramEditorsManager {
    @objid ("98e02547-0d22-44f8-a91b-3b5ee448a64e")
    private final Map<AbstractDiagram , MPart> editors = new HashMap<>();

    @objid ("083bd59f-afa8-4a9c-9017-d699c967c57e")
    private static DiagramEditorsManager instance;

    @objid ("93852226-7ef4-4671-8f32-119c2b9cb508")
    @Inject
     IModelioEventService eventService;

    @objid ("74f65215-eb9a-4198-9b01-ea9db7632807")
    public MPart get(AbstractDiagram abstractDiagram) {
        return this.editors.get(abstractDiagram);
    }

    @objid ("d95de5b5-afad-444f-b4a2-fc29eb498b15")
    public static synchronized DiagramEditorsManager getInstance() {
        if (instance == null) {
            instance = new DiagramEditorsManager();
        }
        return instance;
    }

    @objid ("025f7abb-22a8-4203-ab6d-f764ea1793cd")
    public void put(AbstractDiagram iDiagram, MPart editor) {
        this.editors.putIfAbsent(iDiagram, editor);
    }

    @objid ("bfd0f1c9-78cb-4a62-aa56-75873c350ba8")
    public void remove(AbstractDiagram iDiagram) {
        this.editors.remove(iDiagram);
    }

    @objid ("6670d450-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    static void execute(IEclipseContext context) {
        DiagramEditorsManager newInstance = DiagramEditorsManager.getInstance();
        ContextInjectionFactory.inject(newInstance, context);
        context.set(DiagramEditorsManager.class, newInstance);
        context.set(ToolRegistry.class, ContextInjectionFactory.make(ToolRegistry.class, context));
    }

    @objid ("6670d448-33f7-11e2-95fe-001ec947c8cc")
    @Inject
    @Optional
    void onEditElement(@EventTopic(ModelioEventTopics.EDIT_ELEMENT) MObject mObject, IDiagramConfigurerRegistry configurerRegistry, final IInputPartService inputPartService, final DiagramEditorsManager manager) {
        // FIXME this should be an @UIEventTopic, but they are not triggered with eclipse 4.3 M5...
        
        // Only handle activation requests for diagrams.
        if (!(mObject instanceof AbstractDiagram) || !mObject.isValid()) {
            return;
        }
        final AbstractDiagram diagram = (AbstractDiagram) mObject;
        
        // Get the configurers associated with the metaclass/stereotypes.
        String metaclassName = diagram.getMClass().getName();
        List<String> stereotypes = new ArrayList<>();
        for (Stereotype stereo : diagram.getExtension()) {
            stereotypes.add(stereo.getName());
        }
        List<IDiagramConfigurer> configurers = configurerRegistry.getConfigurers(metaclassName, stereotypes);
        String editorId = null;
        for (IDiagramConfigurer configurer : configurers) {
            if (configurer.getContributionURI() != null && !configurer.getContributionURI().isEmpty()) {
                editorId = configurer.getContributionURI();
            }
        }
        if (editorId == null) {
            DiagramEditor.LOG.error("No IDiagramConfigurer in the IDiagramConfigurerRegistry for '%s' diagram metaclass." , metaclassName);
            return;
        }
        
        final String finalEditorId = editorId;
        
        Display.getDefault().asyncExec(() -> {
            // Request the opening (or re-activation/bring-to-top/get-focus) of a Diagram Editor
            String inputUri = diagram.getUuid();
            MPart openedPart = inputPartService.showInputPart(finalEditorId, inputUri, PartState.ACTIVATE);
        
            if (openedPart == null) {
                DiagramEditor.LOG.error("No MPart returned to open a '%s' editor for %s", finalEditorId, diagram);
                return;
            }
            
            manager.put(diagram, openedPart);
        
            String label = diagram.getName();
            openedPart.setLabel(label);
        
            // Set icon
            // Hack: we only have an Image, not an URL. We have to get the SWT widget (in an ugly way) and set it manually.
            Object widget = openedPart.getWidget();
            try {
                Method m = widget.getClass().getMethod("getParent");
                if (m != null) {
                    Object parent = m.invoke(widget);
                    if (parent instanceof CTabFolder) {
                        CTabFolder tabFolder = (CTabFolder) parent;
                        for (CTabItem tabItem : tabFolder.getItems()) {
                            if (tabItem.getImage() == null && label.equals(tabItem.getText())) {
                                tabItem.setImage(ElementImageService.getIcon(diagram));
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // Implementation probably changed, image isn't set
                DiagramEditor.LOG.warning(e);
                manager.remove(diagram);
            }
        
        });
        return;
    }

    @objid ("86dead11-89b7-41ba-b6e9-6b7241d5b7de")
    @Inject
    @Optional
    void onProjectClosing(@UIEventTopic(ModelioEventTopics.PROJECT_CLOSING) final GProject project, final IProjectService projectService, final IInputPartService inputPartService) {
        // Save opened diagrams list
        IPreferenceStore statePrefs = projectService.getStatePreferences();
        StatePersistenceHelper.saveState(statePrefs, this);
        
        // Close all diagram editors when closing the project
        for (MPart editor : new ArrayList<>(this.editors.values())) {
            // close the editor.
            inputPartService.hideInputPart(editor);
        }
        this.editors.clear();
    }

    @objid ("6f12a531-b16a-428a-b079-a86c9bc0f687")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, IProjectService projectService, IModelioEventService eventService) {
        // Reload saved opened diagrams
        IPreferenceStore statePrefs = projectService.getStatePreferences();
        StatePersistenceHelper.restoreState(statePrefs, openedProject, eventService);
    }

    /**
     * Helper class in charge of saving and restoring the list of opened diagram in the saved state preferences
     */
    @objid ("33bac946-3890-427f-937c-a13c9659ee32")
    private static class StatePersistenceHelper {
        @objid ("d1013962-fc8f-4e5e-be33-ed24c146b4c6")
        private static final String OPENED_DIAGRAM_CONFIG_KEY = DiagramEditor.PLUGIN_ID + ".OpenedDiagrams";

        @objid ("eefa215f-5259-4c17-b0ae-35ac44c1b66a")
        public static void restoreState(IPreferenceStore savedPrefs, GProject openedProject, IModelioEventService eventService) {
            // Read opened diagram list and open the diagrams
            int i = 0;
            while (true) {
                String key = StatePersistenceHelper.OPENED_DIAGRAM_CONFIG_KEY + i;
                if (savedPrefs.contains(key)) {
                    openDiagram(openedProject, eventService, new MRef(savedPrefs.getString(key)));
                } else {
                    break;
                }
                i++;
            }
        }

        @objid ("fa19566f-4c3a-455c-947c-18ed4c85ca94")
        public static void saveState(IPreferenceStore prefs, DiagramEditorsManager manager) {
            clean(prefs);
            
            List<AbstractDiagram> diagrams = new ArrayList<>(manager.editors.keySet());
            diagrams.sort(new Comparator<AbstractDiagram>() {
                @Override
                public int compare(AbstractDiagram d1, AbstractDiagram d2) {
                    MPart p1 = manager.editors.get(d1);
                    MPart p2 = manager.editors.get(d2);
                    return Integer.compare(p1.getParent().getChildren().indexOf(p1), p2.getParent().getChildren().indexOf(p2));
                }
            });
            
            int i = 0;
            for (AbstractDiagram diagram : diagrams) {
                String key = StatePersistenceHelper.OPENED_DIAGRAM_CONFIG_KEY + i;
                prefs.setValue(key, new MRef(diagram).toString());
                i++;
            }
        }

        @objid ("bbf0855d-eef7-49f7-8ae4-aeef80fb6dc0")
        private static void openDiagram(GProject openedProject, IModelioEventService eventService, MRef mref) {
            AbstractDiagram diagram = (AbstractDiagram) openedProject.getSession().getModel().findByRef(mref);
            if (diagram != null) {
                eventService.postAsyncEvent(new IModelioService() {
                    @Override
                    public String getName() {
                        return "openEditor : AbstractDiagram";
                    }
                }, ModelioEvent.EDIT_ELEMENT, diagram);
            }
        }

        @objid ("54183ca0-23f3-40e0-baf3-1ea362757dff")
        private static void clean(IPreferenceStore prefs) {
            // Clean previous values
            int i = 0;
            while (true) {
                String key = StatePersistenceHelper.OPENED_DIAGRAM_CONFIG_KEY + i;
                if (prefs.contains(key)) {
                    prefs.setToDefault(key);
                } else {
                    break;
                }
                i++;
            }
        }

    }

}
