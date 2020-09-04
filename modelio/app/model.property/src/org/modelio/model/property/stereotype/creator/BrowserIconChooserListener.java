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

package org.modelio.model.property.stereotype.creator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.ui.UIImages;

@objid ("46bfc3c4-0456-4f5d-ad2c-9e8689f6196f")
class BrowserIconChooserListener implements SelectionListener {
    @objid ("dd796780-20b5-46c1-97d8-7494f9774c97")
    private StereotypeEditionDialog dialog = null;

    @objid ("bad2cc88-adeb-4f9b-b1ae-1589140d6582")
    private StereotypeEditionDataModel dataModel = null;

    @objid ("9c627fcf-009c-4807-b94d-426c07d1ac11")
    private IProjectService projectService = null;

    @objid ("4a26e38d-a9b7-4e0c-b01e-2a88e490beb9")
    public BrowserIconChooserListener(StereotypeEditionDialog dialog, IProjectService projectService, StereotypeEditionDataModel dataModel) {
        this.dialog = dialog;
        this.dataModel = dataModel;
        this.projectService = projectService;
    }

    @objid ("430dc3cd-7d6f-4d0f-9e7f-d7990e29f34f")
    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        selectImage();
    }

    @objid ("40d63feb-a7e4-48d7-8753-52d5a157e8df")
    @Override
    public void widgetSelected(SelectionEvent event) {
        selectImage();
    }

    @objid ("66354488-52f2-43bd-b3bc-c549c1b29331")
    private void selectImage() {
        FileDialog fileDialog = new FileDialog(this.dialog.getShell(), SWT.OPEN);
        
        String[] filterNames = new String[] { ModelProperty.I18N.getString("StereotypeCreationDialog.ImageFiles") };
        String[] filterExtensions = new String[] { "*.png;*.bmp" };
        String projectPath = "";
        GProject openedProject = this.projectService.getOpenedProject();
        projectPath = openedProject.getProjectPath().toString();
        
        fileDialog.setFilterNames(filterNames);
        fileDialog.setFilterExtensions(filterExtensions);
        fileDialog.setFilterPath(projectPath);
        
        String imagePath = fileDialog.open();
        
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                this.dataModel.setIconName(imageFile.getName());
                Path target = this.dataModel.getDefaultTempIconPath();
                if (target != null) {
                    target.toFile().mkdirs();
                    try {
                        Files.copy(imageFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        setBrowserIcon(target.toFile(), imageFile.getName());
                    } catch (IOException e) {
                        ModelProperty.LOG.error(e);
                    }
                } else {
                    ModelProperty.LOG.error("No temp browser icon!");
                }
            }
        } else {
            this.dataModel.setIconName("");
        }
        
        this.dialog.refresh();
    }

    @objid ("fe37d721-6f34-4a84-a88c-2345bbe630d9")
    void setBrowserIcon(File temp, String fileName) {
        ImageDescriptor explorerIconDesc = null;
        
        try {
            explorerIconDesc = ImageDescriptor.createFromURL(temp.toURI().toURL());
        } catch (MalformedURLException e1) {
            ModelProperty.LOG.error(e1);
        }
        
        if (explorerIconDesc != null) {
            ImageData explorerIconData = explorerIconDesc.getImageData(100);
            ImageData iconPlaceholderData = UIImages.PLACEHOLDER.getImageData();
            if (explorerIconData.width <= iconPlaceholderData.width && explorerIconData.height <= iconPlaceholderData.height) {
                this.dataModel.setExplorerIcon(temp.getPath());
            } else {
                this.dataModel.setIconName("");
                MessageDialog.openError(this.dialog.getShell(),
                        ModelProperty.I18N.getString("BrowserIconChooser.ImageIsTooBig.Title"),
                        ModelProperty.I18N.getMessage("BrowserIconChooser.ImageIsTooBig.Message", fileName));
            }
        }
    }

}
