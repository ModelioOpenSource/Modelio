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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.gui.report.ReportModel;
import org.modelio.xmi.plugin.Xmi;

/**
 * This class provides the XMI export profile dialog
 * @author ebrosse
 */
@objid ("1079ecdd-1724-4390-a1f5-e30f8f939df9")
public class SwtWizardExportProfile extends AbstractSwtWizardExport {
    /**
     * @param parent : shell parent
     */
    @objid ("7ce76360-2db3-4122-a2d5-5836823db95e")
    @Inject
    public  SwtWizardExportProfile(final Shell parent, IProgressService progressService, IProjectService projectService) {
        super(parent, progressService, projectService);
    }

    @objid ("c32d89da-e948-4fb3-8bec-7f3769e7765a")
    @Override
    public void setLabels() {
        setTitle(Xmi.I18N.getString("fileChooser.banner.exportProfile.title"));
        setDescription(Xmi.I18N.getString("fileChooser.banner.export.description"));
        setFrametitle(Xmi.I18N.getString("fileChooser.frame.exportProfile.title"));
        setCancelButton(Xmi.I18N.getString("fileChooser.buttons.export.cancel.name"));
        setValidateButton(Xmi.I18N.getString("fileChooser.buttons.export.export.name"));
        
    }

    @objid ("05bdef0d-357c-4226-ac6e-32dd696751b0")
    @Override
    public void validationAction() {
        final File theFile = getFileChooserComposite().getCurrentFile();
        this.path = theFile.getParent();
        
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.setFilePath(theFile);
        
        if (testExportFilePath(theFile)) {
        
            genProp.setFileExtension((this.getOptionComposite()).getExtension());
            genProp.setVersionExport((this.getOptionComposite()).getFormat());
            genProp.setRoundtripEnabled(this.getOptionComposite().getCompatibilityButton().getSelection());
        
            try {
        
                this.progressService.busyCursorWhile (new ExportProfileThread(this.shell,
                        getTheProgressBar()));
        
                ReportModel reportModel = genProp.getReportModel();
                if (!reportModel.isEmpty()){
                    reportBox(reportModel, genProp.getNavigationServices(), genProp.getLogFilePath());
                }else{
                    completeBox();
                }
        
            } catch (final Exception e) {
                catchException(e);
            }
        
        }
        
    }

}
