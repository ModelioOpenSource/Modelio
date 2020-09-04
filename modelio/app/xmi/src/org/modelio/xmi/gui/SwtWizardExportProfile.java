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

/**
 * This class provides the XMI export profile dialog
 * @author ebrosse
 */
@objid ("1079ecdd-1724-4390-a1f5-e30f8f939df9")
public class SwtWizardExportProfile extends AbstractSwtWizardWindow {
    /**
     * @param parent : shell parent
     * @param style : SWT style
     */
    @objid ("47972550-aa92-44fa-b3db-a1006b8f5323")
    public SwtWizardExportProfile(final Shell parent, final int style) {
        super(parent, style);
        setSelectedType(SWT.NONE);
        this.exportWindows = true;
        this.shell = parent;
    }

    /**
     * @param parent : shell parent
     */
    @objid ("7ce76360-2db3-4122-a2d5-5836823db95e")
    @Inject
    public SwtWizardExportProfile(final Shell parent, IProgressService progressService, IProjectService projectService) {
        this(parent, SWT.NONE);
        this.progressService = progressService;
        this.projectService = projectService;
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
        
                if (!genProp.getReportModel().isEmpty()){
                    reportBox(genProp.getReportModel(), genProp.getNavigationServices(), genProp.getLogFilePath());    
                }else{
                    completeBox();
                }        
        
            } catch (final Exception e) {
                catchException(e);
            }
        
        }
    }

    @objid ("01ee3e34-7762-4048-8e7c-ee81bbba4ef4")
    @Override
    @Inject
    public void setOptionComposite(final Shell shell, IProjectService projectService) {
        this.optionComposite = new OptionComposite(shell, SWT.NONE, SWT.OPEN, projectService);
    }

    @objid ("89d0a786-374d-480f-8dc9-34b3eeed1dc5")
    @Override
    public void setPath() {
        if (this.path.equals(""))
            this.path = GenerationProperties.getInstance().getProjectRoot() + java.io.File.separator + "XMI";
        
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

    @objid ("437c6b41-3323-492e-801e-d3d2780ee907")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] { "XMI Files", "UML Files" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi", "*.uml" });
        setPath();
    }

}
