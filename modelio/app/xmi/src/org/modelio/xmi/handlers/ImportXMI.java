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
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.xmi.gui.SwtWizardImport;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * Handler of the XMI "Import" command.
 * @author ebrosse
 */
@objid ("7edba6b8-77da-4e0d-b1bd-206a28e2606d")
public class ImportXMI {
    @objid ("766f6931-75d7-432a-a72b-ef848428f0dc")
    private Package selectedPackage;

    @objid ("d7078e17-0b20-4a46-b248-136e0b29a551")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell, IProgressService progressService, IProjectService projectService, IMModelServices mmService, final IModelioNavigationService navigationService) {
        //initialization
        ReverseProperties revprop = ReverseProperties.getInstance();
        revprop.initialize(mmService, projectService.getSession().getMetamodel(), navigationService);
        revprop.setRootElement(this.selectedPackage);
        
        for (MObject module : GProject.getProject(this.selectedPackage).getFragment(this.selectedPackage).getRoots()){
            if ((module instanceof ModuleComponent) && (module.getName().equals("LocalModule"))){
                revprop.setProfileRoot((ModuleComponent)module);
            }
        }
        
        //SWT dialog box
        final SwtWizardImport dialog = new SwtWizardImport(activeShell, progressService, projectService);
        dialog.open();
    }

    @objid ("f2eae5f5-da79-4575-a3d5-43e5546a3ae9")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection.size() == 1)
            if ((selection.getFirstElement() instanceof Package)
                    && (!(selection.getFirstElement() instanceof Profile))){
                this.selectedPackage = (Package) selection.getFirstElement();
                final MStatus status =  this.selectedPackage.getStatus();
                return (status.isModifiable());
        
            }
        return false;
    }

    @objid ("c06bceb2-7b82-4067-927e-b563d9183c44")
    public static boolean isVisible(List<MObject> selectedElements) {
        return ((! selectedElements.isEmpty())
                && (selectedElements.size() == 1)
                && (selectedElements.get(0) instanceof Package)
                && (!(selectedElements.get(0)  instanceof Profile)));
    }

}
