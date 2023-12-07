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
package org.modelio.bpmnxml.exporter.commands;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.JAXBException;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.bpmnxml.exporter.service.BPMNExportService;
import org.modelio.bpmnxml.plugin.BPMNXml;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.project.ICurrentProjectService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8edc3ebf-97b2-4b32-af09-7a94944ea277")
public class BPMExportHandler {
    @objid ("68e2b1ea-928b-4323-a8cd-4fcac3a38b40")
    private static String directory;

    @objid ("6adadc70-b6e9-4754-974b-3a64219014ce")
    @Optional
    @Inject
    private ICurrentProjectService projectService;

    @objid ("09083a51-3207-4721-8dc7-a8908466f50c")
    @Optional
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("330c23f4-9998-45be-82a8-94d06aaf566a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, @Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell) {
        ModelElement selectedOwner = SelectionHelper.getFirst(selection, ModelElement.class);
        
        
        FileDialog dialog = new FileDialog(activeShell, SWT.SAVE);
        dialog.setFilterExtensions(new String[] { "*.bpmn" });
        dialog.setFilterNames(new String[] { "*.bpmn" });
        dialog.setText(BPMNXml.I18N.getMessage("ui.command.export.description"));
        if (directory != null) {
            dialog.setFilterPath(directory);
        }
        
        dialog.setFileName(selectedOwner.getName() + ".bpmn");
        
        String path = dialog.open();
        
        if (path != null) {
            File bpmnFile = new File(path);
            directory = bpmnFile.getParent();
        
            exportBpmn(selectedOwner, bpmnFile);
        }
        
    }

    @objid ("ea6cbadc-f54a-4305-9746-b59b09e3b531")
    private void exportBpmn(ModelElement selectedOwner, File bpmnFile) {
        IDiagramService diagramService = this.eclipseContext.get(IModuleContext.class).getModelioServices().getDiagramService();
        BPMNExportService exportService = new BPMNExportService(diagramService, this.projectService.getSession());
        
        try {
            Map<String,Object> tmpConf = new HashMap<>();
            tmpConf.put(IExportConfiguration.COMPATIBILITY_MODE, true);
            exportService.exportBPMN(bpmnFile.toPath(), selectedOwner,tmpConf);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
    }

    @objid ("b13b7afe-1fec-4070-abcf-de7b80b17812")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        if (selectedElements.size() != 1) {
            return false;
        }
        MObject mObject = selectedElements.get(0);
        return (mObject instanceof BpmnProcess || mObject instanceof BpmnCollaboration);
    }

}
