/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.linkeditor.ext.view;

import java.beans.PropertyChangeListener;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBarElement;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.linkeditor.ext.LinkEditorConfigurationParameters;
import org.modelio.linkeditor.handlers.leftdepth.LeftDepthSpinner;
import org.modelio.linkeditor.handlers.rightdepth.RightDepthSpinner;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.panel.ILinkEditorConfigurator;
import org.modelio.linkeditor.panel.LinkEditorPanelProvider;
import org.modelio.linkeditor.view.ILinkEditorView;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Main view class for the LinkViewer.
 */
@objid ("1ba44c6c-5e33-11e2-b81d-002564c97630")
public class LinkEditorView implements ILinkEditorView {
    /**
     * ID of this view, as defined in the plugin.xml file.
     */
    @objid ("1ba44c72-5e33-11e2-b81d-002564c97630")
    public static final String VIEW_ID = "org.modelio.linkeditor.view.LinkEditorViewID";

    @objid ("be7309e5-de70-43b4-9def-1e9a70274c7f")
    private LinkEditorPanelProvider linkEditorPanel;

    @objid ("d4aed2a9-5efd-11e2-a8be-00137282c51b")
    @Inject
    @Optional
    private IMModelServices modelServices;

    @objid ("6ef267ce-960b-4a58-ad8a-71e047c73176")
    @Inject
    private IModelioNavigationService navigationService;

    @objid ("a0ea53bb-65b2-4201-9fbb-f06e5ac1b79a")
    private PropertyChangeListener configChangeListener;

    @objid ("dbb8995c-a0ba-4cd5-8218-007ccc71ec06")
    private MToolBar toolbar;

    @objid ("036ceb3b-a458-4416-a9d2-059c0ff1d357")
    @Override
    public ILinkEditor getLinkEditor() {
        return this.linkEditorPanel;
    }

    /**
     * Forces the view to refresh completely from last received selection.
     */
    @objid ("1ba90f6c-5e33-11e2-b81d-002564c97630")
    @Override
    public void refreshFromCurrentSelection() {
        if (this.linkEditorPanel != null && this.linkEditorPanel.getPanel() != null && !this.linkEditorPanel.getPanel().isDisposed()) {
            this.linkEditorPanel.modelChanged(null);
        }
    }

    /**
     * This method changes the contents of the view. It is usually called when the selected element changes in the application.
     * However, calling it directly passing a ModelElement is perfectly valid.
     * Passing a <code>null</code> element 'disables' the view that becomes inactive.
     * <p>
     * 
     * @param element the new input element for the view.
     */
    @objid ("1ba6add5-5e33-11e2-b81d-002564c97630")
    private void setInput(final MObject element) {
        this.linkEditorPanel.setInput(element);
    }

    @objid ("1ba6adf3-5e33-11e2-b81d-002564c97630")
    @PreDestroy
    void dispose(IProjectService projectService) {
        this.linkEditorPanel.getConfigurator().removePropertyChangeListener(this.configChangeListener);
        this.configChangeListener = null;
        
        if (projectService.getOpenedProject() != null) {
            projectService.getOpenedProject().getSession().getModelChangeSupport().removeModelChangeListener(this.linkEditorPanel);
        }
        this.linkEditorPanel.dispose();
        this.linkEditorPanel = null;
    }

    @objid ("1ba90f74-5e33-11e2-b81d-002564c97630")
    @Inject
    @Optional
    void onProjectClosing(@EventTopic(ModelioEventTopics.PROJECT_CLOSING) final GProject project, ESelectionService selectionService) {
        // Make the view listening to model changes, the panel itself can be used as a IModelChnageListener
        // Unregister as a model change listener.
        if (project != null) {
            project.getSession().getModelChangeSupport().removeModelChangeListener(this.linkEditorPanel);
        }
        
        // Empty the link editor
        this.linkEditorPanel.setEditMode(false);
        selectionService.setSelection(StructuredSelection.EMPTY);
        setInput(null);
    }

    @objid ("1249df2f-9ab2-4dfd-baa3-0b4bc8152bff")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject project) {
        // Make the view listening to model changes, the panel itself can be used as a IodelChnageListener
        project.getSession().getModelChangeSupport().addModelChangeListener(this.linkEditorPanel);
        refreshFromCurrentSelection();
    }

    /**
     * Eclipse 4 constructor.
     * 
     * @param part the Eclipse 4 part
     * @param composite the parent SWT composite
     * @param theProjectService the Modelio project services
     * @param selection the current active selection if any
     */
    @objid ("d4aed2ab-5efd-11e2-a8be-00137282c51b")
    @PostConstruct
    void postConstruct(MPart part, Composite composite, IEclipseContext ctx, IProjectService theProjectService, @Optional @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        // get the view toolbar and view menu for future updates
        this.toolbar = part.getToolbar();
        
        // Create the GUI controls
        this.linkEditorPanel = ContextInjectionFactory.make(LinkEditorPanelProvider.class, ctx);
        this.linkEditorPanel.createPanel(composite);
        
        // Register as a configuration change listener in order to be able to refresh the configuration buttons when a non-ui action changes the configuration
        this.configChangeListener = evt -> LinkEditorView.this.onConfigurationChange((ILinkEditorConfigurator) evt.getSource());
        
        this.linkEditorPanel.getConfigurator().apply(LinkEditorConfigurationParameters.getInstance());
        this.linkEditorPanel.getConfigurator().addPropertyChangeListener(this.configChangeListener);
        
        // Sometimes, the view is instantiated only after the project is opened
        // project preferences maybe null if there is no opened project
        GProject project = theProjectService.getOpenedProject();
        if (project != null) {
            onProjectOpened(project);
        }
    }

    @objid ("1ba6addf-5e33-11e2-b81d-002564c97630")
    @Focus
    void setFocus() {
        if (this.linkEditorPanel != null && this.linkEditorPanel.getPanel() != null) {
            this.linkEditorPanel.getPanel().setFocus();
        }
    }

    @objid ("64ebcb9e-d8c7-4100-ba50-96cfd9f29eca")
    public void onConfigurationChange(ILinkEditorConfigurator config) {
        // Update the view toolbar configuration controls to reflect the current configuration values
        for (MToolBarElement element : this.toolbar.getChildren()) {
            // Left spinner
            if (element.getElementId().equals("org.modelio.linkeditor.toolcontrol.LeftDepthSpinner")) {
                LeftDepthSpinner leftSpinner = (LeftDepthSpinner) ((MToolControl) element).getObject();
                if (leftSpinner != null && leftSpinner.getValue() != config.getLeftDepth()) {
                    leftSpinner.setSpinnerValue(config.getLeftDepth());
                }
            }
            // Right spinner
            if (element.getElementId().equals("org.modelio.linkeditor.toolcontrol.RightDepthSpinner")) {
                RightDepthSpinner rightSpinner = (RightDepthSpinner) ((MToolControl) element).getObject();
                if (rightSpinner != null && rightSpinner.getValue() != config.getRightDepth()) {
                    rightSpinner.setSpinnerValue(config.getRightDepth());
                }
            }
        
            // Pin
            if (element.getElementId().equals("org.modelio.linkeditor.handledtoolitem.PinEditor")) {
                // FIXME configurator is not able to return the pinned state of the editor
                // ((MHandledToolItem) toolbarElements.get("org.modelio.linkeditor.handledtoolitem.PinEditor")).setSelected(config.isPinned());
            }
        
        }
    }

}
