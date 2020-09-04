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

package org.modelio.xmi.gui;

import java.io.File;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.xmi.api.XMIExtension;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.preferences.XmiPreferencesKeys;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class provides the XMI export dialog
 * @author ebrosse
 */
@objid ("f91e2337-fef4-4e6e-b382-cb9fb518f9b2")
public class SwtWizardExport extends AbstractSwtWizardWindow {
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
        
                if (!genProp.getReportModel().isEmpty()){                
                    reportBox(genProp.getReportModel(), genProp.getNavigationServices(), genProp.getLogFilePath());
                }else{
                    completeBox();
                }        
                           
            } catch (Exception e) {
                catchException(e);
            }
        }
    }

    @objid ("fdbbd9fa-c792-4c27-b1e3-f95ee6a8cd9c")
    @Override
    public void setPath() {
        ReverseProperties revProp = ReverseProperties.getInstance();
        revProp.setRootElement(this.selectedElt);
        
        if (this.path.equals(""))
            this.path = revProp.getProjectRoot() + java.io.File.separator + "XMI";
             
        IPreferenceStore prefs = this.projectService.getProjectPreferences(Xmi.PLUGIN_ID);
        String extension = prefs.getString(XmiPreferencesKeys.XMIEXTENSION_PREFKEY);
        
        if (extension.equals(XMIExtension.UML.toString())){
            extension = ".uml";
        }else{
            extension = ".xmi";
        }
        
        this.fileChooserComposite.getDialog().setFilterPath(this.path);
        this.fileChooserComposite.getDialog().setFileName(this.selectedElt.getName() + extension);
        this.path = checkAndReplaceEndPath(this.path);
        this.fileChooserComposite.setText(this.path + java.io.File.separator + this.selectedElt.getName() + extension);
    }

    /**
     * @param parent : the parent shell
     */
    @objid ("b527bb98-fe64-46c8-959a-18f4cff6e4a4")
    @Inject
    public SwtWizardExport(final Shell parent, IProgressService progressService, IProjectService projectService) {
        super(parent, SWT.NONE);
        setSelectedType(SWT.SAVE);
        this.shell = parent;
        this.exportWindows = true;
        this.progressService = progressService;
        this.projectService = projectService;
    }

    @objid ("063e9bd5-0823-4284-be39-aca4a7fb2a8c")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "XMI Files", "UML Files" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi", "*.uml" });
        
        IPreferenceStore prefs = this.projectService.getProjectPreferences(Xmi.PLUGIN_ID);
        String extension = prefs.getString(XmiPreferencesKeys.XMIEXTENSION_PREFKEY);
        
        if (extension.equals(XMIExtension.UML.toString()))
            this.fileChooserComposite.getDialog().setFilterIndex(1);
                  
        setPath();
    }

    @objid ("0e8a95ca-668d-48d4-9398-7073f31daa4b")
    @Override
    public void setOptionComposite(Shell shell, IProjectService projectService) {
        this.optionComposite = new OptionComposite(shell, SWT.NONE,  SWT.OPEN, projectService);
    }

}
