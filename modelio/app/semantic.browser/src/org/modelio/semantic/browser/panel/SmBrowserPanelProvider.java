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

package org.modelio.semantic.browser.panel;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.gproject.GProject;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Provides reusable browser panel
 */
@objid ("90cb05c6-c555-4d4f-892d-b1bd4ca7063f")
public class SmBrowserPanelProvider implements IPanelProvider {
    @objid ("2b12e1b0-8e15-48e2-be56-1317ec9b8fed")
    private boolean synchronizedSelectionMode = false;

    @objid ("12d3da1a-f3eb-4af6-8188-1a64258c8152")
    @Inject
    private IEclipseContext eclipseContext;

    /**
     * The UI of the provided browser panel
     */
    @objid ("7b23807b-6fc2-4a2a-8205-532b2e06a3c2")
    private SmBrowserUi ui;

    /**
     * The controller of the provided browser panel
     */
    @objid ("dd7150cf-beb9-4908-91d7-4a4090ab3bc0")
    private SmBrowserController controller;

    /**
     * Create the browser panel ui using the controller
     */
    @objid ("acfd3a30-54a5-42c9-a20f-33dd3f2d1885")
    @Override
    public Object createPanel(Composite parent) {
        this.ui = this.controller.createUi(parent);
        return this.ui.getComposite();
    }

    @objid ("75ead700-e142-4969-a5a3-90b5e9d2e13d")
    @Override
    public void dispose() {
        this.ui = null;
        this.controller = null;
    }

    @objid ("479c1c1b-564e-4849-b424-8cac0bc790ef")
    @Override
    public String getHelpTopic() {
        // FIXME help topic should point to Browser help pages
        return null;
    }

    @objid ("1ccde576-1524-4593-ab19-b6af01372470")
    @Override
    public Object getInput() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return the UI top level composite
     */
    @objid ("be3eb425-d81b-49a7-9f91-35df190055e4")
    @Override
    public Object getPanel() {
        return this.ui.getComposite();
    }

    @objid ("e0678ac3-9a39-4037-862f-baf26c321ffd")
    @Override
    public boolean isRelevantFor(Object input) {
        return input instanceof GProject || input instanceof List<?>;
    }

    /**
     * input may be either a GProject of a list of root MObject
     */
    @objid ("5520b15f-48a7-48dd-be3b-43d57f2af525")
    @Override
    public void setInput(Object input) {
        if (input != null) {
            if (input instanceof GProject) {
                // Call the controller for project opening
                this.controller.onProjectOpened((GProject) input);
        
                // // Branching the expertise listener
                // // Create the expertise listener
                // IExpertiseService expertiseService = this.eclipseContext.get(IExpertiseService.class);
                // if (expertiseService != null) {
                // this.expertiseListener = new ExpertiseListener(this);
                // ContextInjectionFactory.inject(this.expertiseListener, this.eclipseContext);
                // expertiseService.registerExpertiseListener(this.expertiseListener);
                // }
            }
        
        } else {
        
            // // Remove the expertise listener
            // IExpertiseService expertiseService = this.eclipseContext.get(IExpertiseService.class);
            // if (expertiseService != null && this.expertiseListener != null) {
            // expertiseService.unregisterExpertiseListener(this.expertiseListener);
            // this.expertiseListener = null;
            // }
            //
            // Call the controller for closing project
            this.controller.onProjectClosed(null);
        }
    }

    /**
     * Build a controller with injected fields.
     * @param context
     */
    @objid ("4bf6ef2e-5b88-4d23-a1ec-fb01f6608e78")
    @PostConstruct
    void postConstruct(IEclipseContext context) {
        this.controller = new SmBrowserController();
        ContextInjectionFactory.inject(this.controller, context);
    }

    @objid ("a5ae9dcb-04b5-4558-9598-70151a581d0e")
    @Inject
    @Optional
    void onNavigateElement(@UIEventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final MObject element) {
        this.controller.select(Arrays.asList(element));
    }

    @objid ("13bae3ee-7241-47ac-ae79-213764939cb0")
    @Inject
    @Optional
    void onNavigateElement(@UIEventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final List<MObject> elements) {
        this.controller.select(elements);
    }

    /**
     * Called when application selection changes.
     * @param selection
     */
    @objid ("4b9dfd3c-46de-459d-9f31-e76c28d3b395")
    @Optional
    @Inject
    public void onSelectionChanged(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (this.synchronizedSelectionMode) {
            List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
            this.controller.select(elements);
        }
    }

    @objid ("2c021f8b-cecf-4987-861c-fc0d9fb0bd5c")
    public void setSynchronizedSelectionMode(boolean selected) {
        this.synchronizedSelectionMode = selected;
    }

    /**
     * Register a selection change listener to be called when the 'current' selection changes in the panel.
     * @param l
     */
    @objid ("f1f8cc7d-7da9-4d1b-aa43-7328357a42a5")
    public void addSelectionChangedListener(ISelectionChangedListener l) {
        this.ui.addSelectionChangedListener(l);
    }

    /**
     * Remove a previously registered selection change listener.
     * @param l
     */
    @objid ("b237b0eb-bc96-4f2e-97fc-8ea6fbad01e9")
    public void removeSelectionChangedListener(ISelectionChangedListener l) {
        this.ui.removeSelectionChangedListener(l);
    }

}
