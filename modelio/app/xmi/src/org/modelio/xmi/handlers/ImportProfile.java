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

package org.modelio.xmi.handlers;

import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.xmi.gui.SwtWizardImportProfile;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * Handler of the  XMI "ImportProfile" command.
 * @author ebrosse
 */
@objid ("ff19aba2-a24e-4ab2-9508-aeeedfc36d4c")
public class ImportProfile {
    @objid ("3c349897-eb13-454d-9225-90cc77ff9a00")
    private ModuleComponent selectedModule = null;

    @objid ("4d2f7edf-db40-4c06-bb36-c82f551a4f33")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell, IProgressService progressService, IProjectService projectService, IMModelServices mmService, final IModelioNavigationService navigationService) {
        ReverseProperties revprop = ReverseProperties.getInstance();
        
        revprop.initialize(mmService, projectService.getSession().getMetamodel(), navigationService);
        revprop.setProfileRoot(this.selectedModule);
        revprop.setRootElement(this.selectedModule);
        revprop.setCoreSession(GProject.getProject(this.selectedModule).getSession());
        
        final SwtWizardImportProfile dialog = new SwtWizardImportProfile(activeShell, progressService, projectService);
        dialog.open();
    }

    @objid ("125143f3-0d73-4016-a5b4-20967d8f90a6")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection.size() == 1)
            if(selection.getFirstElement() instanceof ModuleComponent){
                this.selectedModule =  (ModuleComponent) selection.getFirstElement();
                final MStatus status =  this.selectedModule.getStatus();
                return (status.isModifiable());
            }
        return false;
    }

    @objid ("1dcff0dd-1e37-4488-9c5e-817165e57385")
    public static boolean isVisible(List<MObject> selectedElements) {
        return ((! selectedElements.isEmpty())
                && (selectedElements.size() == 1)
                && (selectedElements.get(0) instanceof ModuleComponent));
    }

}
