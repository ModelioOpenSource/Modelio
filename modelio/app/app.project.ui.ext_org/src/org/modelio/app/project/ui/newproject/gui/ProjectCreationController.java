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
package org.modelio.app.project.ui.newproject.gui;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.project.creation.ProjectCreationDataModel;
import org.modelio.platform.project.creation.ProjectNameValidator;

/**
 * Controller of the {@link ProjectCreationDialog} dialog.
 */
@objid ("0044a9ee-cc35-1ff2-a7f4-001ec947cd2a")
public class ProjectCreationController implements Listener, ISelectionChangedListener {
    @objid ("0048e676-cc35-1ff2-a7f4-001ec947cd2a")
    private final ProjectCreationDataModel dataModel;

    @objid ("0045ae3e-cc35-1ff2-a7f4-001ec947cd2a")
    private final ProjectCreationDialog dialog;

    @objid ("00949ff8-c2c2-10b4-9941-001ec947cd2a")
    private final IModuleStore moduleCatalog;

    /**
     * Initialize the controller.
     * @param dialog The project creation dialog
     * @param dataModel The data model
     */
    @objid ("0046d53e-cc35-1ff2-a7f4-001ec947cd2a")
    public  ProjectCreationController(final ProjectCreationDialog dialog, final ProjectCreationDataModel dataModel, final IModuleStore moduleCatalog) {
        this.dataModel = dataModel;
        this.dialog = dialog;
        this.moduleCatalog = moduleCatalog;
        this.dialog.getProjectPanel().addListener(this);
        
    }

    @objid ("0046d5de-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void handleEvent(final Event event) {
        boolean isValid = validateData();
        
        this.dataModel.setProjectName(this.dialog.getProjectPanel().getProjectName());
        this.dataModel.setProjectDescription(this.dialog.getProjectPanel().getProjectDescription());
        
        this.dialog.getProjectPanel().invalidateProjectNameField(!isValid);
        this.dialog.updateButtons(isValid);
        
    }

    @objid ("00463994-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        this.dialog.getProjectPanel().refresh();
    }

    /**
     * Check the filled data validity.
     * @return true if the tab data is valid.
     */
    @objid ("00463a2a-cc35-1ff2-a7f4-001ec947cd2a")
    public boolean validateData() {
        boolean projectNameOk = validateProjectName(this.dialog.getProjectPanel().getProjectName());
        boolean descriptionOk = true; // no control on description
        return projectNameOk && descriptionOk;
    }

    @objid ("00463aca-cc35-1ff2-a7f4-001ec947cd2a")
    protected boolean validateProjectName(final String name) {
        return ProjectNameValidator.PROJECT_NAME_PATTERN.matcher(name).matches();
    }

    @objid ("00963e80-c2c2-10b4-9941-001ec947cd2a")
    public void updateDataModel() {
        this.dataModel.setProjectName(this.dialog.getProjectPanel().getProjectName());
        this.dataModel.setProjectDescription(this.dialog.getProjectPanel().getProjectDescription());
        
        if (this.dialog.getProjectPanel().isJavaChecked()) {
            try {
                IModuleHandle javaDesigner = this.moduleCatalog.findModule("JavaDesigner", null, null);
                if (javaDesigner != null) {
                    this.dataModel.getModuleHandles().add(javaDesigner);
                }
            } catch (IOException e) {
                AppProjectUiExt.LOG.debug(e);
            }
        }
        
    }

}
