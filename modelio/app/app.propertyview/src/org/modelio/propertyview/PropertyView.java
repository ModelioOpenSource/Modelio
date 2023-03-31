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
package org.modelio.propertyview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.gproject.core.IGProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIFont;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.propertyview.plugin.PropertyViewPlugin;
import org.modelio.propertyview.vtabfolder.VTabFolder;
import org.modelio.propertyview.vtabfolder.VTabItem;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

/**
 * ModelProperty plugin main model class. The PropertyView typically displays several tabs:
 * <ul>
 * <li>a 'main' tab that displays only the name and description of the selected model element
 * <li>
 * <li>a 'notes and constraints' tab of the selected model element</li>
 * <li>a 'properties' a generic UML/MDA view of the edited element where UML properties and MDA annotations are displayed in a generic way (table of properties)</li>
 * <li>an audit tab where the current audit reports for the edited element are displayed.</li>
 * <li>a symbol tab when the selected element has been selected as a symbol in a diagram, showing graphics properties</li>
 * <ul>
 * Note that these tabs are contributed to the PropertyView plugin as IPanelProvider instances. Connections to Modelio application: - This class stores a reference to the current project, and listens to open/close events. -
 */
@objid ("61966998-cc0b-4125-a1ad-dd34e53caf5c")
public class PropertyView implements IModelChangeListener, IStatusChangeListener {
    /**
     * Used to indicate that the view should ignore selection changes
     */
    @objid ("43a250ce-480b-4b39-b797-2130ff5ccd09")
    private boolean isPinned = false;

    @objid ("2d45f7b3-67ac-4957-b4da-a71b15d796f6")
    private static final String PANEL = "PANEL";

    @objid ("b42d7554-4fba-4678-bd0c-225adf272a8d")
    private IStructuredSelection currentSelection;

    @objid ("9df29847-23dc-4d83-99da-2cd127f8d766")
    @Inject
    @Optional
    private EModelService eModelService;

    @objid ("a7211daa-cc48-4fa3-b372-2b8cb0f38fc1")
    @Inject
    @Optional
    private IEclipseContext eclipseContext;

    @objid ("446e33ae-96ad-499e-b46c-0ebc1ecc8bcc")
    @Inject
    @Optional
    private IEventBroker eventBroker;

    @objid ("779cb83f-9f16-4cd5-ba71-b7ab1ea6e29e")
    @Inject
    @Optional
    EMenuService menuService;

    @objid ("9b78bf69-54b6-4f77-ad13-6b9b9c055eda")
    private MPart myPart;

    @objid ("4a7faed8-92e8-457b-b9ce-de8aadb9852d")
    private CLabel header;

    @objid ("b357052a-7cfa-47b8-b378-d01ef24904b3")
    private Composite parentComposite;

    @objid ("416c04b4-33cb-48a8-bda1-e3413d4112a9")
    private IActivationService activationService;

    @objid ("f98e1985-55cb-4e01-adb0-ba7e51de107a")
    private PropertyViewConfigurator configurator;

    @objid ("3870c553-7963-4a32-a44c-a186379073ad")
    private IMModelServices modelService;

    @objid ("5f129d95-35c7-449a-96ab-cd7b79f352d0")
    private IModelioPickingService pickingService;

    @objid ("5e81d308-a164-467a-94cf-cdb8fffd3c3a")
    IGProject project;

    @objid ("64bc76e3-41c3-4158-910e-b6b83cb143aa")
    @Inject
    private IProjectService projectService;

    @objid ("a5ab841a-6383-4a7d-83de-8111ae86335e")
    private PropertyViewContributorsManager pvm;

    @objid ("c28aa121-8acf-40cf-9c27-fff8fbb247b2")
    private VTabFolder tabFolder;

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
    @objid ("25234b53-7251-4fc7-88ea-d232f7c68d55")
    @PostConstruct
    public void createControls(final IProjectService aProjectService, @Optional final IMModelServices modelServices, @Optional final IActivationService modelioActivationService, @Optional final IModelioPickingService modelioPickingService, final Composite parent, @Optional
    @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, @Optional final EMenuService theMenuService, @Optional final MPart propertyPart) {
        this.parentComposite = parent;
        
        // The property view contributors
        this.pvm = new PropertyViewContributorsManager(this.eclipseContext);
        
        // Create the GUI
        createGui(this.parentComposite);
        
        // Sometimes, the view is instantiated only after the project is opened
        if (aProjectService != null && aProjectService.getOpenedProject() != null) {
            onProjectOpened(aProjectService.getOpenedProject(), modelServices, modelioPickingService,
                    modelioActivationService, theMenuService, propertyPart);
            if (selection != null) {
                update(selection);
            }
        }
        
    }

    /**
     * @param isShown whether hidden annotations should be displayed.
     */
    @objid ("16f6c234-c1d4-43e5-af75-b1f5fdf8bcac")
    public void setShowHiddenMdaElements(final boolean isShown) {
        // this.configurator.setShowHiddenMdaElements(isShown);
    }

    /**
     * Updates the view for the given selection. Note that this might be called before the GUI has been created !
     * @param selection an Eclipse selection
     */
    @objid ("3247ccb8-e344-439b-8324-fbb7ed478b1e")
    @Optional
    @Inject
    public void update(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (this.isPinned || this.projectService.getOpenedProject() == null) {
            return;
        }
        
        // Update the header
        if (this.header != null) {
            this.header.setText(getTitleFor(selection));
            this.header.setImage(getImageFor(selection));
        
            for (final PanelDescriptor desc : this.pvm.getPanels()) {
                final IPanelProvider panel = desc.getPanel();
                try {
                    if (panel.isRelevantFor(selection)) {
                        showTabFor(desc);
                        panel.setInput(selection);
                    } else {
                        panel.setInput(null);
                        hideTabFor(panel);
                    }
                } catch (final Throwable e) {
                    PropertyViewPlugin.LOG.error(e);
                }
            } // end for
        
            if (this.tabFolder.getSelection() == null) {
                this.tabFolder.setSelection(0);
            }
        
            this.tabFolder.redraw();
        
            this.currentSelection = selection;
        }
        
    }

    @objid ("e02232af-4be2-4539-ba76-67264f7b3caf")
    EMenuService getMenuService() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.menuService;
    }

    @objid ("aec881e8-7bf3-47a0-8ba6-42d801e99aee")
    @Inject
    @SuppressWarnings ("unused")
    @Optional
    void onPickingSessionStop(@EventTopic (ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        // Unpin the view
        setIsPinned(false);
        
        // Restore the selection
        this.tabFolder.getDisplay().asyncExec(
                () -> update(this.currentSelection));
        
    }

    @objid ("7fb8f8ff-50d0-4c1d-aad8-a503b2b74a10")
    @Inject
    @Optional
    @SuppressWarnings ("unused")
    void onPickingStart(@EventTopic (ModelioEventTopics.PICKING_START) final IPickingSession session) {
        // Temporary pin the view when picking is in progress
        setIsPinned(true);
        
    }

    /**
     * Called when a project is closed or when the SWT composite is disposed. On session close un-reference the modeling session and model services.
     */
    @objid ("0491f53a-ccde-42e4-95a3-60851039ff06")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic (ModelioEventTopics.PROJECT_CLOSED) final IGProject closedProject) {
        if (closedProject != null && closedProject == this.project) {
            final ICoreSession session = this.project.getSession();
            if (session != null) {
                // Unregister model change listeners
                session.getModelChangeSupport().removeModelChangeListener(this);
                session.getModelChangeSupport().removeStatusChangeListener(this);
            }
        }
        this.project = null;
        this.modelService = null;
        
    }

    /**
     * This method is called after a project opening. Keep a reference to the modeling session and model services.
     */
    @objid ("c0005f01-044c-43ac-ba06-74014bda209e")
    @Optional
    @Inject
    void onProjectOpened(@EventTopic (ModelioEventTopics.PROJECT_OPENED) final IGProject openedProject, @Optional final IMModelServices mmService, @Optional final IModelioPickingService modelioPickingService, @Optional final IActivationService modelioActivationService, @Optional final EMenuService theMenuService, final MPart propertyPart) {
        this.project = openedProject;
        this.modelService = mmService;
        this.pickingService = modelioPickingService;
        this.activationService = modelioActivationService;
        this.menuService = theMenuService;
        this.myPart = propertyPart;
        
        if (this.project != null) {
            this.project.getSession().getModelChangeSupport().addModelChangeListener(this);
            this.project.getSession().getModelChangeSupport().addStatusChangeListener(this);
        }
        
    }

    @objid ("32785abb-076a-46df-beae-14f9e7b04c89")
    @Focus
    void setFocus() {
        // if (this.view != null) {
        // this.view.getPanel().setFocus();
        // }
        this.tabFolder.setFocus();
        
    }

    @objid ("b4c3140d-d00a-415b-8f85-0afee14fa7e5")
    void setMenuService(final EMenuService value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.menuService = value;
        
    }

    /**
     * Create the Gui of the property view.<br/>
     * The gui is composed of a header showing the name and the metaclass of the selected elements and a vertical tab container.
     */
    @objid ("e88c2fcf-ae30-445e-8030-6de9bdd64d98")
    private void createGui(final Composite parent) {
        final Composite comp = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        comp.setLayout(layout);
        
        comp.addDisposeListener(ev -> {
            for (final PanelDescriptor desc : this.pvm.getPanels()) {
                desc.getPanel().dispose();
            }
            onProjectClosed(this.project);
        });
        
        // The header
        this.header = new CLabel(comp, SWT.NONE);
        
        final Color[] colors = new Color[] { UIColor.WHITE, null };
        final int[] percents = new int[] { 60 };
        this.header.setBackground(colors, percents, true);
        
        this.header.setFont(UIFont.LARGEB);
        this.header.setForeground(UIColor.LABEL_TIP_FG);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.header.setLayoutData(gd);
        
        // The tab folder, populated with contributors
        this.tabFolder = new VTabFolder(comp, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.tabFolder.setLayoutData(gd);
        
        for (final PanelDescriptor desc : this.pvm.getPanels()) {
            desc.getPanel().createPanel(this.tabFolder);
        }
        
    }

    @objid ("d2eddd1d-8432-4fd3-98f4-500f9cb03af6")
    private Image getImageFor(final IStructuredSelection selection) {
        final ModelElement me = SelectionHelper.getFirst(selection, ModelElement.class);
        return me != null ? MetamodelImageService.getIcon(me.getMClass()) : null;
    }

    @objid ("20a80a04-53bc-44e7-af8e-22ec86e247a0")
    private String getTitleFor(final IStructuredSelection selection) {
        final ModelElement me = SelectionHelper.getFirst(selection, ModelElement.class);
        if (me != null) {
            return me.getName() + " (" + me.getMClass().getName() + ')';
        }
        return "";
    }

    @objid ("6e810b5a-8e8b-4d82-b077-db61ecee3ea9")
    private void hideTabFor(final IPanelProvider panel) {
        // if a tab item already exists for this panel, just dispose it
        final List<VTabItem> tabItemsToDispose = new ArrayList<>();
        
        for (final VTabItem tabItem : this.tabFolder.getItems()) {
            if (Objects.equals(tabItem.getData(PropertyView.PANEL), panel)) {
                tabItemsToDispose.add(tabItem);
            }
        }
        for (final VTabItem tabItem : tabItemsToDispose) {
            if (tabItem.getImage() != null) {
                tabItem.getImage().dispose();
            }
            tabItem.dispose();
        }
        
    }

    @objid ("2175f01d-ebb8-4f31-91f3-a16bdba4a654")
    private void setIsPinned(final boolean onOff) {
        this.isPinned = onOff;
    }

    @objid ("5fca9e1e-e73a-4d97-a9ff-6391b2b61681")
    private void showTabFor(final PanelDescriptor desc) {
        // if a tab item already exists for this panel, just return
        for (final VTabItem tabItem : this.tabFolder.getItems()) {
            if (Objects.equals(tabItem.getData(PropertyView.PANEL), desc.getPanel())) {
                return;
            }
        }
        
        // Need to create a new tabItem at the proper position
        final VTabItem tabItem = new VTabItem(this.tabFolder, SWT.NULL);
        tabItem.setText(desc.getPanelLabel());
        
        final ImageDescriptor iconDescriptor = desc.getIconDescriptor();
        if (iconDescriptor != null) {
            tabItem.setImage(iconDescriptor.createImage());
        }
        
        final Control top = (Control) desc.getPanel().getPanel();
        tabItem.setControl(top);
        tabItem.setData(PropertyView.PANEL, desc.getPanel());
        
    }

    @objid ("41df04a4-4616-4713-bca8-6bdd4ea526e6")
    @Override
    public void statusChanged(final IStatusChangeEvent event) {
        this.tabFolder.getDisplay().asyncExec(() -> {
            if (!this.tabFolder.isDisposed()) {
                for (final VTabItem item : this.tabFolder.getItems()) {
                    final IPanelProvider panel = (IPanelProvider) item.getData(PropertyView.PANEL);
                    panel.setInput(this.currentSelection);
                }
            }
        });
        
    }

    @objid ("0d359da7-9b33-4272-ad10-f5577e77453a")
    @Override
    public void modelChanged(final IModelChangeEvent event) {
        this.tabFolder.getDisplay().asyncExec(() -> {
            // Simplest strategy here : setInput on element Panel
            if (!this.tabFolder.isDisposed()) {
                for (final VTabItem item : this.tabFolder.getItems()) {
                    final IPanelProvider panel = (IPanelProvider) item.getData(PropertyView.PANEL);
                    panel.setInput(this.currentSelection);
                }
            }
        });
        
    }

    @objid ("eeab94d6-04e8-44b4-af2f-a0a05dde5025")
    public IPanelProvider getSelectedPanel() {
        return (IPanelProvider) this.tabFolder.getSelection().getData(PropertyView.PANEL);
    }

    @objid ("eb783bd3-a55c-4a05-8150-e02f59543368")
    private static class PanelDescriptor {
        
        @mdl.prop
        @objid ("aa18d09a-3b3d-42ef-93eb-1b5deab59fb6")
        public final String panelLabel;

        @mdl.propgetter
        public String getPanelLabel() {
            // Automatically generated method. Please delete this comment before entering specific code.
            return this.panelLabel;
        }

        
        @mdl.prop
        @objid ("b5b08bc9-3794-4338-8ed5-2c10576da5b8")
        public final boolean primary;

        @mdl.propgetter
        public boolean isPrimary() {
            // Automatically generated method. Please delete this comment before entering specific code.
            return this.primary;
        }

        
        @mdl.prop
        @objid ("4f9ed041-ac23-4d72-a21f-a7489d595608")
        public final ImageDescriptor iconDescriptor;

        @mdl.propgetter
        public ImageDescriptor getIconDescriptor() {
            // Automatically generated method. Please delete this comment before entering specific code.
            return this.iconDescriptor;
        }

        
        @mdl.prop
        @objid ("88ffd378-a3a0-4cba-8e0d-1a774680c7f1")
        public final IPanelProvider panel;

        @mdl.propgetter
        public IPanelProvider getPanel() {
            // Automatically generated method. Please delete this comment before entering specific code.
            return this.panel;
        }

        /**
         * @param id the panel id
         * @param label the panel label
         * @param iconDescriptor the tab icon ?
         * @param isPrimary whether it is a primary panel: to be displayed first
         * @param panel the panel implementation
         */
        @objid ("e85fbe4c-fcd8-4cbe-8522-2ca29a906a2f")
        public  PanelDescriptor(final String id, final String label, final ImageDescriptor iconDescriptor, final boolean isPrimary, final IPanelProvider panel) {
            this.panelLabel = label != null ? label : "";
            this.primary = isPrimary;
            this.panel = panel;
            this.iconDescriptor = iconDescriptor;
            
        }

    }

    @objid ("c1b94bef-19df-4870-b8f8-1e1c5d6074f4")
    private static class PropertyViewConfigurator {
    }

    @objid ("e00cc1dd-e331-4012-a238-c1c460821083")
    private static class PropertyViewContributorsManager {
        @objid ("782325bf-4f41-46ba-b2da-b9f4b4f38491")
        private static final String PROPERTYVIEW_PANEL_EXTENSIONPOINT = "org.modelio.app.propertyview.panels";

        @objid ("90e5cfa8-a561-4e42-b370-5ce446c6d351")
        private ArrayList<PanelDescriptor> panels;

        @objid ("a55baa94-cdec-4a81-a542-cd22ffbea3c4")
        public  PropertyViewContributorsManager(final IEclipseContext eclipseContext) {
            initializeExtensions(eclipseContext);
        }

        @objid ("93719a52-1331-4e37-bc63-be1a9b74bbf0")
        private void initializeExtensions(final IEclipseContext eclipseContext) {
            this.panels = new ArrayList<>();
            
            // Sort contributions by id as a convention id are "org.modelio.propertyview.panels.panelXX" where XX is a number for ordering panels)
            // Contributors are invited to strictly follow the convention to benefit of ordering
            final List<IConfigurationElement> contributions = new ArrayList<>(new ExtensionPointContributionManager(PROPERTYVIEW_PANEL_EXTENSIONPOINT).getExtensions("panel"));
            contributions.sort(new Comparator<IConfigurationElement>() {
                @Override
                public int compare(final IConfigurationElement o1, final IConfigurationElement o2) {
                    return o1.getAttribute("id").compareTo(o2.getAttribute("id"));
                }
            });
            
            for (final IConfigurationElement entry : contributions) {
                try {
                    final IPanelProvider panel = (IPanelProvider) entry.createExecutableExtension("class");
                    ContextInjectionFactory.inject(panel, eclipseContext);
            
                    ImageDescriptor iconDescriptor = null;
                    final String extendingPluginId = entry.getDeclaringExtension().getContributor().getName();
                    final String iconPath = entry.getAttribute("icon");
                    if (iconPath != null) {
                        iconDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(extendingPluginId, iconPath);
                    }
            
                    this.panels.add(new PanelDescriptor(
                            entry.getAttribute("id"),
                            entry.getAttribute("label"),
                            iconDescriptor,
                            Boolean.parseBoolean(entry.getAttribute("primary")),
                            panel));
                } catch (ClassCastException | InvalidRegistryObjectException | CoreException e) {
                    PropertyViewPlugin.LOG.error(e);
                }
            }
            
        }

        @objid ("b52a04a4-275a-4c53-841b-f1b463c3f640")
        public List<PanelDescriptor> getPanels() {
            return this.panels;
        }

    }

}
