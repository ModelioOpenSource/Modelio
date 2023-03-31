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
package org.modelio.app.project.ui.views.workspace.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.project.services.IProjectService;

@objid ("af15325d-eecf-4b45-a2a0-70b282acdc22")
public class RefreshWorkspaceHandler {
    @objid ("96cae632-d79e-4e96-8722-9c68008a6056")
    @Execute
    public void execute(final IProjectService projectService) {
        IGProject currentProject = projectService.getOpenedProject();
        projectService.refreshWorkspace((currentProject != null) ? currentProject.getName() : null);
        
    }

}
