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

package org.modelio.xmi.handlers;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.gui.SwtWizardExport;

/**
 * Handler of the XMI export
 * @author ebrosse
 */
@objid ("ce38f353-e8f9-44dc-b2f1-8b8359a4ec87")
public class ExportXMI {
    @objid ("6dc138e9-b109-4bf9-bb79-a5dcb838f8bf")
    private List<ModelElement> selectedPackage = null;

    @objid ("bdce6cd6-25d3-47aa-a390-532adf3b5f53")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell, IProgressService progressService, IProjectService projectService, IMModelServices modelServices, final IModelioNavigationService navigationService) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.initialize(modelServices, projectService.getSession().getMetamodel(), navigationService);
        genProp.setRootElements(this.selectedPackage);
        
        
        final SwtWizardExport dialog = new SwtWizardExport(activeShell, progressService, projectService);
        dialog.setSelectedElt(this.selectedPackage.get(0));
        dialog.open();
    }

    @objid ("83392784-7629-44b9-a089-ee91420fef33")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        Iterator<?> itr = selection.iterator();
        this.selectedPackage = new ArrayList<>();
        while(itr.hasNext()) {
            Object element = itr.next();
            if ((element instanceof Package)
                    && (!(element instanceof Profile))){
                this.selectedPackage.add((Package) element);
        
            }
        }
        return (!(this.selectedPackage.isEmpty()));
    }

    @objid ("0aeb5e59-fc8f-4655-9622-774aa665326c")
    public static boolean isVisible(List<MObject> selectedElements) {
        if (! selectedElements.isEmpty()){
            for (MObject selectedElement : selectedElements ){
                if ((selectedElement instanceof Profile)
                        && (!(selectedElement instanceof Package)))
                    return false;
            }
            return true;
        }
        return false;
    }

}
