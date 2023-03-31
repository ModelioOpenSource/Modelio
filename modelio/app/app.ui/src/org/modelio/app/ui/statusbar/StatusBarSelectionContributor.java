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
package org.modelio.app.ui.statusbar;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.UIThreadRunner;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Status bar contributor for current selection.
 * <ul>
 * <li>for MObject displays an icon and the element name</li>
 * <li>for anything else displays nothing</li>
 * </ul>
 */
@objid ("18f8b550-f2f7-49ca-a783-43fa90597b08")
public class StatusBarSelectionContributor implements IStatusBarContribution, IModelChangeListener {
    @objid ("3569bdfb-1d97-4990-8420-d83ed8e74ac0")
    private CLabel selectionLabel;

    /**
     * Read/write status
     */
    @objid ("f34de509-48a9-462c-b4ed-fc183c50727c")
    private CLabel labelRW;

    @objid ("4c00d3d5-4be4-4a66-a8bb-308ac5ec96d5")
    private List<Control> allLabels = null;

    @objid ("a411806c-2ec1-44a2-87e0-483f937d2f5c")
    @Inject
    IEventBroker eventBroker;

    @objid ("e17dfe17-5992-4a67-bb13-40667b54530b")
    @Optional
    @Inject
    ESelectionService selectionService;

    @objid ("16989cca-a62e-435f-8c31-3fef1a0452b7")
    private Resources resources;

    @objid ("b4be46af-621f-4903-baa9-f67a646046c5")
    @Override
    public Control createControl(Composite parent) {
        this.resources = new Resources();
        Composite container = new Composite(parent, SWT.NONE);
        
        GridLayout gridLayout = new GridLayout(3, false);
        gridLayout.horizontalSpacing = 2;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        container.setLayout(gridLayout);
        
        this.selectionLabel = new CLabel(container, SWT.NONE);
        this.selectionLabel.setText("                ");
        this.selectionLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        Label sep = new Label(container, SWT.SEPARATOR);
        sep.setLayoutData(new GridData(SWT.DEFAULT, 16));
        
        this.labelRW = new CLabel(container, SWT.NONE);
        this.labelRW.setImage(this.resources.emptyIcon);
        this.labelRW.setToolTipText(AppUi.I18N.getString("Statusbar.rwlabel.tooltip"));
        this.labelRW.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        
        this.allLabels = Arrays.asList(this.labelRW, this.selectionLabel);
        return container;
    }

    @objid ("fd65ca02-3793-4223-bf56-26079dcb0b39")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final IGProject project, IProjectService projectService) {
        project.getSession().getModelChangeSupport().addModelChangeListener(this);
    }

    @objid ("4c9ca69a-f58a-4446-9ac1-2ae1d6f270a9")
    @Inject
    @Optional
    private void onProjectClosing(@EventTopic (ModelioEventTopics.PROJECT_CLOSING) final IGProject project, IProjectService projectService) {
        // Unregister as a model change listener.
        project.getSession().getModelChangeSupport().removeModelChangeListener(this);
        updateSelectionLabel("", null);
        
    }

    @objid ("ce440632-616a-41aa-8fb8-1fcad5024c3a")
    @Inject
    @Optional
    public void onSelectionChange(@Named (IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        if (this.selectionLabel == null || this.selectionLabel.isDisposed()) {
            return;
        }
        
        if (selection == null || selection.isEmpty()) {
            clear();
        } else if (selection.size() == 1) {
            update(selection.getFirstElement());
        } else {
            clear();
            updateSelectionLabel(String.format("%d selected", selection.size()), null);
        }
        
    }

    @objid ("a75a5716-5d52-493c-912c-431eef5a6a50")
    private void updateRWLabel(MObject mObj) {
        if (mObj != null) {
            if (mObj.getStatus().isModifiable()) {
                this.labelRW.setImage(this.resources.rwIcon);
                this.labelRW.setToolTipText(AppUi.I18N.getString("Statusbar.rwlabel.readwrite.tooltip"));
            } else {
                this.labelRW.setImage(this.resources.roIcon);
                this.labelRW.setToolTipText(AppUi.I18N.getString("Statusbar.rwlabel.readonly.tooltip"));
            }
        } else {
            this.labelRW.setImage(this.resources.emptyIcon);
        }
        
    }

    @objid ("48bfddff-c580-4f71-974f-b4f56d058d9d")
    private void fireStatusBarUpdate() {
        this.eventBroker.post("org/modelio/app/ui/statusbar", this.allLabels);
    }

    @objid ("8ff98ae7-ea29-4d13-ab45-53998ebaa1dc")
    private void update(Object o) {
        if (o != null && o instanceof MObject) {
            updateSelectionLabel(((MObject) o).getName(), ElementImageService.getIcon((MObject) o));
            updateRWLabel((MObject) o);
        } else {
            clear();
        }
        
    }

    @objid ("bbfbbd6e-fb4d-41da-9db3-cea83af78fd4")
    private void clear() {
        updateSelectionLabel("", null);
        updateRWLabel(null);
        
    }

    @objid ("0101ac7c-2bfc-45f8-8ed1-56f688a69689")
    private void updateSelectionLabel(String text, Image img) {
        if (this.selectionLabel != null && !this.selectionLabel.isDisposed()) {
            this.selectionLabel.setText(text != null ? text : "");
            this.selectionLabel.setImage(img != null && !img.isDisposed() ? img : null);
            fireStatusBarUpdate();
        }
        
    }

    @objid ("0339e7e7-fa43-4b37-a50e-0137158527c2")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        UIThreadRunner.asynExec(this.selectionLabel, () -> {
            ISelection selection = (ISelection) this.selectionService.getSelection();
            update(SelectionHelper.getFirst(selection, Object.class));
        });
        
    }

    @objid ("282794b0-dd6e-459a-a738-bd3cc175db08")
    private static class Resources {
        @objid ("d84956f3-b6c9-4887-b79e-ab0a1cee5b40")
        private Image rwIcon;

        @objid ("237ae60d-5340-45dc-9d9e-ae8b93615f57")
        private Image roIcon;

        @objid ("61f28ff5-f50b-4da8-b029-90eb1e7b440d")
        private Image emptyIcon;

        @objid ("597f60e2-4a74-42bb-9138-b4d35743d8ce")
        public  Resources() {
            this.rwIcon = createImageDescriptor("icons/rw-element.png").createImage();
            this.roIcon = createImageDescriptor("icons/ro-element.png").createImage();
            this.emptyIcon = createImageDescriptor("icons/empty.png").createImage();
            
        }

        @objid ("1f0ae530-3642-4d98-b47b-0ebc7b352365")
        private void freeResources() {
            if (this.rwIcon != null) {
                this.rwIcon.dispose();
                this.rwIcon = null;
            }
            if (this.roIcon != null) {
                this.roIcon.dispose();
                this.roIcon = null;
            }
            if (this.emptyIcon != null) {
                this.emptyIcon.dispose();
                this.emptyIcon = null;
            }
            
        }

        /**
         * Create an image descriptor from a path relative to the Audit plugin.
         * @param path a relative path.
         * @return the image descriptor.
         */
        @objid ("6bbba18a-6019-484f-a4c9-03506a181aaf")
        private ImageDescriptor createImageDescriptor(final String path) {
            URL bitmapUrl = FileLocator.find(Platform.getBundle(AppUi.PLUGIN_ID), new Path(path), null);
            return ImageDescriptor.createFromURL(bitmapUrl);
        }

    }

}
