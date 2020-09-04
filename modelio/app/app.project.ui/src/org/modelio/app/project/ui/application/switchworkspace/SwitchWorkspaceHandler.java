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

package org.modelio.app.project.ui.application.switchworkspace;

import java.io.File;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.plugin.AppProjectUi;

@objid ("00455d8a-6b88-10b3-9941-001ec947cd2a")
public class SwitchWorkspaceHandler {
    @objid ("00456320-6b88-10b3-9941-001ec947cd2a")
    @Execute
    public void execute(final IProjectService projectService, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        assert (projectService.getOpenedProject() == null);
        
        DirectoryDialog dialog = new DirectoryDialog(shell);
        dialog.setFilterPath(projectService.getWorkspace().toString());
        
        dialog.setMessage(AppProjectUi.I18N.getString("SwitchWorkspaceTitle"));
        String newWorkspacedir = dialog.open();
        
        if (newWorkspacedir != null) {
            File newWorkspace = new File(newWorkspacedir);
            if (newWorkspace.isDirectory()) {
                projectService.changeWorkspace(newWorkspace.toPath());
            }
        }
    }

    @objid ("00456172-6b88-10b3-9941-001ec947cd2a")
    @CanExecute
    public boolean canExecute(final IProjectService projectService) {
        return (projectService.getOpenedProject() == null);
    }

}
