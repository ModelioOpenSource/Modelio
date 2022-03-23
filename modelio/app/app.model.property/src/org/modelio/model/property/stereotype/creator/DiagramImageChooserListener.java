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
import org.modelio.gproject.gproject.GProject;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.UIImages;

@objid ("f19caca9-8c4d-4e40-b701-52383eab1b7e")
class DiagramImageChooserListener implements SelectionListener {
    @objid ("082941b4-163c-46bd-bf56-5cf012aacd82")
    private StereotypeEditionDialog dialog = null;

    @objid ("ce8dbd79-5d7a-476d-90cb-dbdd28fb147e")
    private StereotypeEditionDataModel dataModel = null;

    @objid ("c458b66c-f282-467b-9da6-f345a2687cc8")
    private IProjectService projectService = null;

    /**
     * @param dialog the dialog
     * @param projectService the project service
     * @param dataModel the dialog data model
     */
    @objid ("b93bbdcf-2459-48f9-9328-e5c5daaccbc9")
    public  DiagramImageChooserListener(StereotypeEditionDialog dialog, IProjectService projectService, StereotypeEditionDataModel dataModel) {
        this.dialog = dialog;
        this.dataModel = dataModel;
        this.projectService = projectService;
        
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @objid ("9c1cba91-45ef-446b-b2ce-00fe5e0e7034")
    @Override
    public void widgetDefaultSelected(SelectionEvent event) {
        selectImage(event);
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @objid ("1c7d3414-14f1-445c-a108-3acc1ed787c6")
    @Override
    public void widgetSelected(SelectionEvent e) {
        selectImage(e);
    }

    /**
     * @param event
     */
    @objid ("c448c258-c69f-489b-91f8-0562ff0b1d5b")
    private void selectImage(SelectionEvent event) {
        FileDialog fileDialog = new FileDialog(this.dialog.getShell(), SWT.OPEN);
        
        String[] filterNames = new String[] { ModelProperty.I18N.getString("StereotypeCreationDialog.ImageFiles") };
        String[] filterExtensions = new String[] { "*.png;*.bmp" };
        
        GProject openedProject = this.projectService.getOpenedProject();
        String projectPath = openedProject.getProjectFileStructure().getProjectPath().toString();
        
        fileDialog.setFilterNames(filterNames);
        fileDialog.setFilterExtensions(filterExtensions);
        fileDialog.setFilterPath(projectPath);
        
        String imagePath = fileDialog.open();
        
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                this.dataModel.setImageName(imageFile.getName());
                Path target = this.dataModel.getDefaultTempImagePath();
                if (target != null) {
                    target.toFile().mkdirs();
                    try {
                        Files.copy(imageFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        setDiagramImage(target.toFile(), imageFile.getName());
                    } catch (IOException e) {
                        ModelProperty.LOG.error(e);
                    }
                } else {
                    ModelProperty.LOG.error("No temp diagram image!");
                }
            }
        } else {
            this.dataModel.setImageName("");
        }
        
        this.dialog.refresh();
        
    }

    @objid ("97e22191-90f1-4240-80d1-3fca1dafc8a1")
    void setDiagramImage(File temp, String fileName) {
        ImageDescriptor explorerIconDesc = null;
        
        try {
            explorerIconDesc = ImageDescriptor.createFromURL(temp.toURI().toURL());
        } catch (MalformedURLException e1) {
            ModelProperty.LOG.error(e1);
        }
        
        if (explorerIconDesc != null) {
            ImageData explorerImageData = explorerIconDesc.getImageData(100);
            ImageData imagePlaceholderData = UIImages.PLACEHOLDER_48.getImageData();
            if (explorerImageData.width <= imagePlaceholderData.width && explorerImageData.height <= imagePlaceholderData.height) {
                this.dataModel.setDiagramImage(temp.getPath());
            } else {
                this.dataModel.setImageName("");
                MessageDialog.openError(this.dialog.getShell(),
                        ModelProperty.I18N.getString("DiagramImageChooser.ImageIsTooBig.Title"),
                        ModelProperty.I18N.getMessage("DiagramImageChooser.ImageIsTooBig.Message", fileName));
            }
        }
        
    }

}
