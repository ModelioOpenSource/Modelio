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

package org.modelio.diagram.browser.view;

import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBarElement;
import org.eclipse.e4.ui.model.application.ui.menu.impl.ItemImpl;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.core.picking.IPickingSession;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.browser.model.IBrowserModel;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Main class of the diagram browser.
 */
@objid ("000c3122-0d4f-10c6-842f-001ec947cd2a")
public class DiagramBrowserView {
    @objid ("505388d6-7b87-4218-af68-48c2069d3c9e")
    private static final String POPUPID = "org.modelio.diagram.browser.popupmenu";

    @objid ("37ccb9f0-b0d5-4063-a119-c9abd0508cbf")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("000c9752-0d4f-10c6-842f-001ec947cd2a")
    protected GProject project;

    @objid ("000caef4-0d4f-10c6-842f-001ec947cd2a")
    protected DiagramBrowserModelChangeListener modelChangeListener;

    @objid ("000cc664-0d4f-10c6-842f-001ec947cd2a")
    protected DiagramBrowserPickingManager pickingManager;

    @objid ("85b99c0e-54b9-11e2-85c1-002564c97630")
    protected DiagramBrowserPanelProvider diagramBrowserPanelProvider;

    @objid ("750d7fa0-f000-4bcb-b713-64da18a0840c")
    @Inject
    @Optional
    protected IProjectService projectService;

    @objid ("89a98bfd-5c8e-45a2-924d-2dd574ae5d34")
    protected Composite parent;

    @objid ("3e3474be-6fec-4cb6-97ec-3bb21d908170")
    @Inject
    @Optional
    protected IActivationService activationService;

    @objid ("331faaf6-b80f-48c9-b190-5efcd7500c19")
    @Inject
     static EContextService contextService;

    @objid ("000ccc86-0d4f-10c6-842f-001ec947cd2a")
    @PostConstruct
    public void createPartControl(Composite aParent, @Optional IProjectService aProjectService, MPart part, EMenuService menuService, IModelioNavigationService navigationService) {
        // Connect to session if one is open
        this.parent = aParent;
        this.projectService = aProjectService;
        if ((this.projectService != null) && (this.projectService.getOpenedProject() != null)) {
            onProjectOpened(this.projectService.getOpenedProject(), part, menuService, navigationService);
        }
    }

    /**
     * Get the modeling session.
     * 
     * @return the modeling session.
     */
    @objid ("000d345a-0d4f-10c6-842f-001ec947cd2a")
    public ICoreSession getSession() {
        return this.project.getSession();
    }

    /**
     * Select and reveal the given model element in the browser.
     * 
     * @param element the element to select.
     */
    @objid ("000d76e0-0d4f-10c6-842f-001ec947cd2a")
    public void selectElement(Element element) {
        this.diagramBrowserPanelProvider.getPanel().setSelection(new StructuredSelection(element), true);
    }

    /**
     * This method connects the view to the current modeling session ie set the tree input, branch several listeners like model change or navigate events and so on. It is guaranteed when this method is called that the view GUI and the modeling session are
     * available.
     */
    @objid ("0011a2ce-43b1-10c7-842f-001ec947cd2a")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, final MPart part, final EMenuService menuService, final IModelioNavigationService navigationService) {
        this.project = openedProject;
        
        if (DiagramBrowserView.this.diagramBrowserPanelProvider == null) {
            // diagramBrowserPanelProvider may be null if we click diagram
            // browser view before opening a project
            initDiagramBrowserPanelProvider(menuService, navigationService);
        }
        // Install the selected model
        DiagramBrowserView.this.diagramBrowserPanelProvider.switchBrowserModel(getSelectedContentModel(part));
        DiagramBrowserView.this.diagramBrowserPanelProvider.setInput(openedProject);
        
        // Branch a model change listener:
        DiagramBrowserView.this.modelChangeListener = new DiagramBrowserModelChangeListener(
                DiagramBrowserView.this.diagramBrowserPanelProvider);
        if (openedProject != null) {
            openedProject.getSession().getModelChangeSupport().addModelChangeListener(DiagramBrowserView.this.modelChangeListener);
            openedProject.getSession().getModelChangeSupport().addStatusChangeListener(DiagramBrowserView.this.modelChangeListener);
        }
        
        DiagramBrowserView.this.parent.layout();
    }

    @objid ("0012503e-43b1-10c7-842f-001ec947cd2a")
    @Focus
    public void setFocus() {
        if (this.diagramBrowserPanelProvider != null) {
            this.diagramBrowserPanelProvider.getPanel().getTree().setFocus();
        }
    }

    /**
     * Select the given elements in the view
     */
    @objid ("28686740-4ab5-11e2-a4d3-002564c97630")
    public void selectElement(List<Element> elements) {
        this.diagramBrowserPanelProvider.getPanel().setSelection(new StructuredSelection(elements), true);
    }

    @objid ("2869ede2-4ab5-11e2-a4d3-002564c97630")
    @Inject
    @Optional
    void onNavigateElement(@UIEventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final List<MObject> elements) {
        DiagramBrowserView.this.diagramBrowserPanelProvider.getPanel().setSelection(new StructuredSelection(elements));
    }

    @objid ("2869edea-4ab5-11e2-a4d3-002564c97630")
    @Inject
    @Optional
    void onPickingStart(@UIEventTopic (ModelioEventTopics.PICKING_START) final IPickingSession session) {
        if (DiagramBrowserView.this.diagramBrowserPanelProvider != null) {
            DiagramBrowserView.this.pickingManager = new DiagramBrowserPickingManager(
                    DiagramBrowserView.this.diagramBrowserPanelProvider.getPanel(), session);
            DiagramBrowserView.this.pickingManager.beginPicking();
        }
    }

    @objid ("2869edf0-4ab5-11e2-a4d3-002564c97630")
    @Inject
    @Optional
    @SuppressWarnings ("unused")
    void onPickingStop(@UIEventTopic (ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        if (DiagramBrowserView.this.pickingManager != null) {
            DiagramBrowserView.this.pickingManager.endPicking();
            DiagramBrowserView.this.pickingManager = null;
        }
    }

    @objid ("2869edf6-4ab5-11e2-a4d3-002564c97630")
    public DiagramBrowserPanelProvider getComposite() {
        return this.diagramBrowserPanelProvider;
    }

    /**
     * Selects and edits the given element in the tree if possible.
     * 
     * @param elementToEdit the element to select and edit.
     */
    @objid ("cd493808-54c7-11e2-ae63-002564c97630")
    public void edit(final Element elementToEdit) {
        Display display = this.diagramBrowserPanelProvider.getPanel().getControl().getDisplay();
        display.asyncExec(() -> {
            DiagramBrowserView.this.diagramBrowserPanelProvider.getPanel().expandToLevel(elementToEdit, 0);
            DiagramBrowserView.this.diagramBrowserPanelProvider.getPanel().editElement(elementToEdit, 0);
        });
    }

    @objid ("cd49380d-54c7-11e2-ae63-002564c97630")
    public void collapseAll() {
        if (this.diagramBrowserPanelProvider != null) {
            this.diagramBrowserPanelProvider.getPanel().collapseAll();
        }
    }

    @objid ("fc05adf8-2a43-402f-8e86-0018f85d2c90")
    protected void initDiagramBrowserPanelProvider(EMenuService menuService, IModelioNavigationService navigationService) {
        this.diagramBrowserPanelProvider = new DiagramBrowserPanelProvider(this.projectService.getOpenedProject(),
                navigationService);
        this.diagramBrowserPanelProvider.createPanel(this.parent);
        
        // Add the selection provider
        this.diagramBrowserPanelProvider.getPanel().addSelectionChangedListener(event -> {
            if (DiagramBrowserView.this.selectionService != null) {
                DiagramBrowserView.this.selectionService.setSelection(event.getSelection());
            }
        });
        
        // Add the double click listener
        this.diagramBrowserPanelProvider.getPanel().addDoubleClickListener(
                event -> {
                    final ISelection selection = event.getSelection();
                    if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
                        final Object selectedObject = ((IStructuredSelection) selection).getFirstElement();
                        if (selectedObject instanceof MObject) {
                            if (((MObject) selectedObject).isValid()) {
                                DiagramBrowserView.this.activationService.activateMObject((MObject) selectedObject);
                            }
                        } else if (selectedObject instanceof DiagramRef) {
                            DiagramBrowserView.this.activationService.activateMObject(((DiagramRef) selectedObject)
                                    .getReferencedDiagram());
                        } else if (selectedObject instanceof IAdaptable) {
                            final MObject adapter = ((IAdaptable) selectedObject).getAdapter(MObject.class);
                            if (adapter != null) {
                                DiagramBrowserView.this.activationService.activateMObject(adapter);
                            }
                        }
                    }
        
                });
        
        // Add the contextual menu
        menuService.registerContextMenu(this.diagramBrowserPanelProvider.getPanel().getTree(), DiagramBrowserView.POPUPID);
    }

    /**
     * Get selected content model in the toolbar
     * 
     * @param part @return
     */
    @objid ("756fe621-4b86-442a-9f1d-5530556c46a7")
    protected String getSelectedContentModel(MPart part) {
        MToolBar toolbar = part.getToolbar();
        for (MToolBarElement element : toolbar.getChildren()) {
            if (element instanceof ItemImpl) {
                ItemImpl toolItem = (ItemImpl) element;
                if (toolItem.isSelected()) {
                    if (toolItem.getElementId().equals("org.modelio.diagram.browser.toolbar.tool.byset")) {
                        return IBrowserModel.USER_MODEL;
                    } else if (toolItem.getElementId().equals("org.modelio.diagram.browser.toolbar.tool.flat")) {
                        return IBrowserModel.FLAT_MODEL;
                    } else if (toolItem.getElementId().equals("org.modelio.diagram.browser.toolbar.tool.bytype")) {
                        return IBrowserModel.BY_TYPE_MODEL;
                    } else if (toolItem.getElementId().equals("org.modelio.diagram.browser.toolbar.tool.bycontext")) {
                        return IBrowserModel.CONTEXT_MODEL;
                    } else if (toolItem.getElementId().equals("org.modelio.diagram.browser.toolbar.tool.related")) {
                        return IBrowserModel.RELATED_MODEL;
                    }
                }
            }
        }
        return IBrowserModel.FLAT_MODEL;
    }

    /**
     * This listener is activated when the selection changes in the workbench.<br>
     * 
     * @param selection the current modelio selection.
     */
    @objid ("ac1b12ab-a34a-4c6a-8791-66e0a2555261")
    @Inject
    @Optional
    void onSelectionChange(@Named (IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection, @Named (IServiceConstants.ACTIVE_PART) MPart part) {
        // Ignore 'too early' events
        if (getComposite() == null) {
            return;
        }
        
        // Discard selection from myself, process only selections from other
        // views
        if (part != null && !Objects.equals(part.getObject(), this)) {
            ModelElement e = SelectionHelper.getFirst(selection, ModelElement.class);
            if (e != null) {
                getComposite().setInput(e);
            }
        }
    }

    /**
     * Called when the modeling session is closed. This method deactivates the DiagramBrowser ie making it completely passive (no more listeners to anything) It does not 'close' the view in the GUI. This method disconnects the view from the session ie it
     * carefully undoes what the onProjectOpened() did previously. It is not guaranteed when this method is called that the view GUI has not been already disposed.
     */
    @objid ("a3c50a24-5d97-42e9-86f5-e320681d3bfd")
    @Inject
    @Optional
    void onProjectClosing(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final GProject project, IProjectService projectService) {
        // remove model change listener if session still alive
        if ((project != null) && project.isOpen()) {
            project.getSession().getModelChangeSupport().removeModelChangeListener(this.modelChangeListener);
            project.getSession().getModelChangeSupport().removeStatusChangeListener(this.modelChangeListener);
        }
        this.modelChangeListener = null;
        
        this.project = null;
        
        DiagramBrowserView.this.diagramBrowserPanelProvider.setInput(null);
        DiagramBrowserView.this.diagramBrowserPanelProvider.getPanel().getTree().dispose();
        DiagramBrowserView.this.diagramBrowserPanelProvider = null;
    }

}
