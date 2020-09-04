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

package org.modelio.bpmnxml.importer.commands;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.app.core.project.ICurrentProjectService;
import org.modelio.bpmnxml.importer.service.BPMNImportService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("596c1e64-40d0-43ce-b1ca-e279934e700c")
public class BPMImportHandler {
    @objid ("74d9a3d7-0e7b-4462-aa9c-59e999e6539c")
    private static String directory;

    @objid ("f0ec3e85-d35e-4336-b463-4f3fb5ae9a06")
    @Optional
    @Inject
    private ICurrentProjectService projectService;

    @objid ("474f9500-5b48-46b7-a281-f2721e9c0c71")
    @Optional
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("52740ba0-d666-4618-b632-c3967193aecf")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, @Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell) {
        Object context = SelectionHelper.getFirst(selection, IProjectFragment.class);
        if(context == null){    
            context = SelectionHelper.getFirst(selection, Element.class);
        }
        
        BPMImportModel model = new BPMImportModel();
            
        if(context instanceof BpmnProcess || context instanceof BpmnCollaboration){
            model.setUpdate(true);
        }
            
        BPMImportDialog dialog = new BPMImportDialog(activeShell, model);
        if(dialog.open() == 0){               
            File bpmnFile =    new File(model.getFilePath()); 
            importBpmn(bpmnFile.toPath(), context,model.isKeeyId());       
        }
    }

    @objid ("164220ba-9edc-4f97-8eb6-365dbdbfac1e")
    private void importBpmn(Path filePath, Object context, boolean keepId) {
        try {
            IDiagramService diagramService = this.eclipseContext.get(IModuleContext.class).getModelioServices().getDiagramService();
            BPMNImportService importService = new BPMNImportService(this.projectService.getSession(), diagramService);
        
            if (context instanceof IProjectFragment) {
                importService.importBPMN(filePath, (IProjectFragment) context, keepId);
            }if (context instanceof Package) {
                importService.importBPMN(filePath, (Package) context, keepId);
            } else if (context instanceof BpmnCollaboration) {
                importService.updateBPMN(filePath, (BpmnCollaboration) context, keepId);
            }else if (context instanceof BpmnProcess) {
                importService.updateBPMN(filePath, (BpmnProcess) context, keepId);
            }
            
               
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @objid ("57edff91-22d8-4610-8740-58d188cf143f")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        final List<IProjectFragment> selectedFragments = SelectionHelper.toList(selection, IProjectFragment.class);
        if (selectedElements.size() != 1 && selectedFragments.size() != 1) {
            return false;
        }
        if (selectedElements.size() == 1) {
            MObject mObject = selectedElements.get(0);
            return (mObject instanceof Package )||(mObject instanceof BpmnProcess)||(mObject instanceof BpmnCollaboration);
        } else if (selectedFragments.size() == 1) {
            return true;
        }
        return false;
    }

}
