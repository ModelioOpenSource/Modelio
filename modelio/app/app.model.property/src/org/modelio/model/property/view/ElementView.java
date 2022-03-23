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
package org.modelio.model.property.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.model.property.panel.ElementPropertyPanelProvider;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

/**
 * ModelProperty plugin main model class. This class stores a reference to the current project, and listens to open/close events.
 */
@objid ("8fb871ca-c068-11e1-8c0a-002564c97630")
@SuppressWarnings ("restriction")
public class ElementView implements IModelChangeListener, IStatusChangeListener {
    @objid ("0332c358-fe92-41f0-83ce-61881d425384")
    private static final String POPUPID = "org.modelio.property.popupmenu";

    @objid ("8fb871ce-c068-11e1-8c0a-002564c97630")
    ElementPropertyPanelProvider panel;

    @objid ("86a2b971-cf24-11e1-80a9-002564c97630")
    @Inject
    private IProjectService projectService;

    @objid ("ab38a695-d004-11e1-9020-002564c97630")
    private IMModelServices modelService;

    @objid ("06bda2d0-16d1-11e2-aa0d-002564c97630")
    private IModelioPickingService pickingService;

    @objid ("6398bde1-5bbe-44c2-aa6e-c24ad0d38bd7")
    private Composite parentComposite;

    @objid ("f60bed7a-0c12-4103-af97-3637ba7fb13f")
    @Inject
    @Optional
    EMenuService menuService;

    @objid ("0d21bd7b-54d4-4b91-b0ee-4a8104faed9d")
    private IActivationService activationService;

    @objid ("56fa8d93-8a21-4b85-8e90-40aca0231732")
    private PropertyViewConfigurator configurator;

    @objid ("843c2df0-a814-4c0a-910d-d9c05ffce86a")
    private MPart myPart;

    @objid ("3d525aef-bc46-401c-99f1-9ff55e600a7b")
    @Inject
    @Optional
    private EModelService eModelService;

    @objid ("507a4918-0dca-45d0-85cf-882bf32ae7a7")
    @Inject
    @Optional
    private IEventBroker eventBroker;

    @objid ("777e79a2-cbf8-482f-abee-50a006b620c4")
    private IStructuredSelection currentSelection;

    @objid ("15b80f6e-e47c-4fec-bc9e-05c944872e50")
    GProject project;

    /**
     * Called by the framework to create the view and initialize it.
     * @param aProjectService the project service.
     * @param modelServices the model service.
     * @param modelioActivationService the activation service
     * @param modelioPickingService the picking service.
     * @param parent the composite the view must add its content into.
     * @param selection the application selection.
     * @param theMenuService the E4 menu service
     * @param propertyPart the E4 part representing the property view
     */
    @objid ("8fb871cf-c068-11e1-8c0a-002564c97630")
    @PostConstruct
    public void createControls(IProjectService aProjectService, @Optional IMModelServices modelServices, @Optional IActivationService modelioActivationService, IModelioPickingService modelioPickingService, Composite parent, @Optional
    @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, @Optional EMenuService theMenuService, @Optional MPart propertyPart, IEclipseContext eclipseContext) {
        this.parentComposite = parent;
        
        // Sometimes, the view is instantiated only after the project is opened
        if (aProjectService != null && aProjectService.getOpenedProject() != null) {
            onProjectOpened(aProjectService.getOpenedProject(), modelServices, modelioPickingService,
                    modelioActivationService, theMenuService, propertyPart);
            if (selection != null) {
                update(selection, eclipseContext);
            }
        }
        
    }

    /**
     * Updates the view for the given selection.
     * @param selection an Eclipse selection
     */
    @objid ("8fb871d4-c068-11e1-8c0a-002564c97630")
    @Optional
    @Inject
    public void update(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IEclipseContext eclipseContext) {
        // Do not update unless 'onProjectOpened' was called
        if (this.project == null || this.projectService.getOpenedProject() == null) {
            return;
        }
        
        if (this.panel == null) {
            // Create the view content
            this.panel = new ElementPropertyPanelProvider(eclipseContext);
            this.panel.createPanel(this.parentComposite);
            this.parentComposite.layout();
        
            configurePanelByPreferences();
        } else if (this.panel.isPinned()) {
            return;
        }
        
        if (selection != null && selection.size() == 1) {
            this.panel.setInput(selection);
            this.currentSelection = selection;
            return;
        }
        this.panel.setInput(null);
        
    }

    /**
     * This method is called after a project opening. Keep a reference to the modeling session and model services.
     */
    @objid ("8fb871da-c068-11e1-8c0a-002564c97630")
    @Optional
    @Inject
    void onProjectOpened(@EventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, @Optional IMModelServices mmService, @Optional IModelioPickingService modelioPickingService, @Optional IActivationService modelioActivationService, @Optional EMenuService theMenuService, MPart propertyPart) {
        this.project = openedProject;
        this.modelService = mmService;
        this.pickingService = modelioPickingService;
        this.activationService = modelioActivationService;
        this.menuService = theMenuService;
        this.myPart = propertyPart;
        
        // Activate edition on the view
        if (this.panel != null) {
            // this.view.activateEdition(this.projectService, this.project !=
            // null ? this.project.getSession() : null,
            // this.modelService, this.pickingService, this.activationService);
        }
        
        if (this.project != null) {
            this.project.getSession().getModelChangeSupport().addModelChangeListener(this);
            this.project.getSession().getModelChangeSupport().addStatusChangeListener(this);
        }
        
    }

    /**
     * Called when a project is closing. Set the current selection to <code>null</code>.
     */
    @objid ("3e16e841-cbe8-4277-bb1a-fc8232bfc8d5")
    @Inject
    @Optional
    void onProjectClosing(@EventTopic (ModelioEventTopics.PROJECT_CLOSING) GProject closedProject) {
        if (this.panel != null) {
            this.panel.setInput(null);
        }
        
    }

    /**
     * Called when a project is closed. On session close un-reference the modeling session and model services.
     */
    @objid ("8fb871e3-c068-11e1-8c0a-002564c97630")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic (ModelioEventTopics.PROJECT_CLOSED) GProject closedProject) {
        if (closedProject == this.project) {
            removeModelListeners();
        
            this.project = null;
            this.modelService = null;
        }
        
    }

    @objid ("06be8d36-16d1-11e2-aa0d-002564c97630")
    @Focus
    void setFocus() {
        if (this.panel != null) {
            this.panel.getPanel().setFocus();
        }
        
    }

    /**
     * Called by the modelio application when a picking session starts. We 'pin' the view so that it is not responding to selection changes during the picking session.
     */
    @objid ("06be8d39-16d1-11e2-aa0d-002564c97630")
    @Inject
    @Optional
    @SuppressWarnings ("unused")
    void onPickingStart(@EventTopic (ModelioEventTopics.PICKING_START) final IPickingSession session) {
        // Temporary pin the view when picking is in progress
        this.panel.setPinned(true);
        
    }

    /**
     * Called by the modelio application when a picking session ends. We 'un-pin' the view so restore the response to selection changes.
     */
    @objid ("06beb44b-16d1-11e2-aa0d-002564c97630")
    @Inject
    @SuppressWarnings ("unused")
    @Optional
    void onPickingSessionStop(@EventTopic (ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        // Unpin the view
        this.panel.setPinned(false);
        
    }

    /**
     * @return the property panel.
     */
    @objid ("650c6290-def7-47cf-85e5-e4eb3466e275")
    public ElementPropertyPanelProvider getPanel() {
        return this.panel;
    }

    /**
     * Configure panel from project preferences.
     */
    @objid ("944a4c06-f525-470d-9c13-6dbe1b230bc2")
    public void configurePanelByPreferences() {
        if (this.configurator == null) {
            this.configurator = new PropertyViewConfigurator(this.projectService, this.myPart);
        
            // Add a property change listener for future updates
            this.projectService.getProjectPreferences(this.myPart.getElementId())
                    .addPropertyChangeListener(new IPropertyChangeListener() {
        
                        @Override
                        public void propertyChange(PropertyChangeEvent event) {
                            Display.getDefault().asyncExec(new Runnable() {
        
                                @Override
                                public void run() {
                                    configurePanelByPreferences();
                                }
                            });
                        }
                    });
        }
        if (this.panel != null) {
            this.configurator.loadConfiguration(this.panel);
            configureMenuItem();
        }
        
    }

    /**
     * @param isShown whether hidden annotations should be displayed.
     */
    @objid ("fd9b4d33-c965-4a1a-ab4b-91115deb7cea")
    public void setShowHiddenMdaElements(boolean isShown) {
        this.configurator.setShowHiddenMdaElements(isShown);
    }

    @objid ("6815fe66-6ed0-4346-a48e-02f734cf5b96")
    void configureMenuItem() {
        // MMenu hideMenu = null;
        // for (MMenu mmenu : this.myPart.getMenus()) {
        // if (VIEWMENU.equals(mmenu.getElementId())) {
        // hideMenu = mmenu;
        // break;
        // }
        // }
        // if (hideMenu != null && this.eModelService != null) {
        // // show/hide fragments
        // MDirectMenuItem fragmentsMenuItem = (MDirectMenuItem) this.eModelService.find(MENUITEM_SHOW_HIDDEN,
        // hideMenu);
        // fragmentsMenuItem.setSelected(this.configurator.areHiddenMdaElementsDisplayed());
        // }
        
    }

    @objid ("93d2c6e4-7ce6-4d3b-9bf5-f4af115ddf64")
    private EMenuService getMenuService() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.menuService;
    }

    @objid ("2a7d4c79-c514-4a90-ab94-ded4e76ddbc6")
    private void setMenuService(EMenuService value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.menuService = value;
        
    }

    @objid ("147b931d-17dd-4194-8bf0-0e686ef67f94")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        if (this.panel != null) {
            this.panel.getTreeViewer().getControl().getDisplay().syncExec(() -> {
                // Simplest strategy here : setInput on element Panel
                this.panel.setInput(this.currentSelection);
        
            });
        }
        
    }

    @objid ("6eee49d3-a7cc-4da8-93a0-96ff440f0089")
    @Override
    public void statusChanged(IStatusChangeEvent event) {
        if (this.panel != null) {
            this.panel.getTreeViewer().getControl().getDisplay().asyncExec(() -> {
                this.panel.setInput(this.currentSelection);
            });
        }
        
    }

    /**
     * E4 destructor.
     * <p>
     * Remove model listeners.
     */
    @objid ("876703c1-39bd-481c-9de3-b2ffc03c3f12")
    @PreDestroy
    void onPreDestroy() {
        removeModelListeners();
    }

    @objid ("644396cd-5702-4778-8cf3-9aca29d855ef")
    private void removeModelListeners() {
        if (this.project != null && this.project.getSession() != null) {
            this.project.getSession().getModelChangeSupport().removeModelChangeListener(this);
            this.project.getSession().getModelChangeSupport().removeStatusChangeListener(this);
        }
        
    }

    /**
     * Manage the preferences for the view. Preferences are project scoped. Right now, the unique preference currently managed is:
     * <ul>
     * <li>SHOW_HIDDEN_MDA_ELEMENTS.</li>
     * </ul>
     */
    @objid ("1ce077eb-43aa-498a-bf6c-2bf5af5b2a32")
    private static class PropertyViewConfigurator {
        @objid ("8dba6bd2-f3b1-4722-b447-99a61c8665b6")
        private static final String SHOW_HIDDEN_MDA_ELEMENTS = "ShowHiddenMdaElements";

        @objid ("416f0364-d1c5-4528-9b43-1233d5f6ba0b")
        private static final boolean SHOW_HIDDEN_MDA_ELEMENTS_DEFAULT = false;

        @objid ("baa4548a-26f0-4b3e-ac45-40ce0f595ab7")
        private IProjectService projectService;

        @objid ("18b31212-4479-4ef7-9952-9b474886766a")
        private MPart part;

        @objid ("031ee441-8341-4212-8595-3cb2baea5337")
        public void setShowHiddenMdaElements(boolean isShown) {
            final IPreferenceStore prefs = getPreferenceStore();
            if (prefs != null) {
                prefs.setValue(PropertyViewConfigurator.SHOW_HIDDEN_MDA_ELEMENTS, isShown);
            }
            
        }

        @objid ("412a7fee-8d3f-4dc3-b424-a092ae484a44")
        public void loadConfiguration(ElementPropertyPanelProvider panel) {
            final IPreferenceStore prefs = getPreferenceStore();
            if (prefs != null) {
                panel.setShowHiddenMdaElements(prefs.getBoolean(PropertyViewConfigurator.SHOW_HIDDEN_MDA_ELEMENTS));
            }
            
        }

        @objid ("f639c91d-23b7-4d68-8fc7-3e3e9241d8e6")
        public  PropertyViewConfigurator(IProjectService projectService, MPart part) {
            this.projectService = projectService;
            this.part = part;
            
            final IPreferenceStore prefs = projectService.getProjectPreferences(part.getElementId());
            if (prefs != null) {
                prefs.setDefault(PropertyViewConfigurator.SHOW_HIDDEN_MDA_ELEMENTS, PropertyViewConfigurator.SHOW_HIDDEN_MDA_ELEMENTS_DEFAULT);
            }
            
        }

        @objid ("a1da02dc-21bc-4be3-8909-51bc6b4f1b03")
        public boolean areHiddenMdaElementsDisplayed() {
            final IPreferenceStore prefs = getPreferenceStore();
            if (prefs != null) {
                return prefs.getBoolean(PropertyViewConfigurator.SHOW_HIDDEN_MDA_ELEMENTS);
            }
            return false;
        }

        @objid ("482cbe6b-f840-4c26-9d47-3fec0ab95da1")
        private IPreferenceStore getPreferenceStore() {
            if (this.projectService.getOpenedProject() != null) {
                return this.projectService.getProjectPreferences(this.part.getElementId());
            } else {
                return null;
            }
            
        }

    }

}
