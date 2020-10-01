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

package org.modelio.xmi.gui;

import java.io.File;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.gui.report.ReportModel;
import org.modelio.xmi.plugin.Xmi;

/**
 * This class provides the XMI export dialog
 * @author ebrosse
 */
@objid ("f91e2337-fef4-4e6e-b382-cb9fb518f9b2")
public class SwtWizardExport extends AbstractSwtWizardExport {
    @objid ("4e2d6edc-3ccc-4cba-ad1f-15d93e376374")
    @Override
    public void setLabels() {
        setTitle(Xmi.I18N.getString("fileChooser.banner.export.title"));
        setDescription(Xmi.I18N.getString("fileChooser.banner.export.description"));
        setFrametitle(Xmi.I18N.getString("fileChooser.frame.export.title"));
        setCancelButton(Xmi.I18N.getString("fileChooser.buttons.export.cancel.name"));
        setValidateButton(Xmi.I18N.getString("fileChooser.buttons.export.export.name"));
    }

    @objid ("9029119f-2c5b-4472-b2ad-9ecfc3a56cbc")
    @Override
    public void validationAction() {
        File theFile = getFileChooserComposite().getCurrentFile();
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        genProp.setFilePath(theFile);
        this.path = theFile.getParent();
        if (testExportFilePath(theFile)) {
        
            genProp.setFileExtension((this.getOptionComposite()).getExtension());        
            genProp.setVersionExport((this.getOptionComposite()).getFormat());       
            genProp.setRoundtripEnabled(this.getOptionComposite().getCompatibilityButton().getSelection());
            
            try {
        
                this.progressService.busyCursorWhile(new ExportThread(this.shell,
                        getTheProgressBar()));
        
                ReportModel reportModel = genProp.getReportModel();
                if (!reportModel.isEmpty()){                
                    reportBox(reportModel, genProp.getNavigationServices(), genProp.getLogFilePath());
                }else{
                    completeBox();
                }        
                           
            } catch (Exception e) {
                catchException(e);
            }
        }
    }

    /**
     * @param parent : the parent shell
     */
    @objid ("b527bb98-fe64-46c8-959a-18f4cff6e4a4")
    @Inject
    public SwtWizardExport(final Shell parent, IProgressService progressService, IProjectService projectService) {
        super(parent, progressService, projectService);
    }

}
