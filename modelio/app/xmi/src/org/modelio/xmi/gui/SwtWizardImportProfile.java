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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class provides the XMI import profile dialog
 * @author ebrosse
 */
@objid ("30022f89-f8c6-4cf6-828b-bc9524177272")
public class SwtWizardImportProfile extends AbstractSwtWizardWindow {
    /**
     * @param parent : the parent shell
     * @param style : the SWT style
     */
    @objid ("1e73d71c-0264-4b3b-855b-e58b49dd21d4")
    public SwtWizardImportProfile(final Shell parent, final int style) {
        super(parent, style);
        setSelectedType(SWT.OPEN);
        this.exportWindows = false;
    }

    /**
     * @param parent : the parent shell
     */
    @objid ("0004a637-cf94-4ba0-a895-ae9f0e082d99")
    @Inject
    public SwtWizardImportProfile(final Shell parent, IProgressService progressService, IProjectService projectService) {
        this(parent, SWT.NONE);
        this.progressService = progressService;
        this.projectService = projectService;
    }

    @objid ("68407f30-ae58-4821-b8b8-1afc183a6504")
    @Override
    public void validationAction() {
        final File theFile = getFileChooserComposite().getCurrentFile();
        ReverseProperties revProp = ReverseProperties.getInstance();
        revProp.setFilePath(theFile);
        this.path = theFile.getParent();
        
        if (theFile.exists() && theFile.isFile()) {
            String extension = theFile.getName();
            extension = extension.substring(extension.lastIndexOf("."));
        
            if (extension.equals(".uml") 
                    || extension.equals(".xmi") 
                    || extension.equals(".xml")) {
        
                try(ITransaction t = this.projectService.getSession().getTransactionSupport().createTransaction("Import Profile") ) {
        
                    this.progressService.busyCursorWhile (new ImportProfileThread(this.shell,
                            getTheProgressBar()));
        
                    if (!ReverseProperties.getInstance().getReportModel().isEmpty()){
                        reportBox(revProp.getReportModel(), revProp.getNavigationServices(), revProp.getLogFilePath());   
                    }else{
                        completeBox();
                    }        
                    t.commit();
        
                } catch (final Exception e) {
                    catchException(e);
                }
            } else {
                wrongFileExtension();
                enableComposites();
            }
        
        } else {
            fileDontExist();
            enableComposites();
        }
    }

    @objid ("3acd6769-860f-4ced-8359-1d0de991e373")
    @Override
    public void setLabels() {
        setTitle(Xmi.I18N.getString("fileChooser.banner.importProfile.title"));
        setDescription(Xmi.I18N.getString("fileChooser.banner.import.description"));
        setFrametitle(Xmi.I18N.getString("fileChooser.frame.importProfile.title"));
        setCancelButton(Xmi.I18N.getString("fileChooser.buttons.import.cancel.name"));
        setValidateButton(Xmi.I18N.getString("fileChooser.buttons.import.import.name"));
    }

    @objid ("416dce2a-b299-4d6b-a30c-2f679e8dd313")
    @Override
    public void setOptionComposite(final Shell shell, IProjectService projectService) {
        this.optionComposite = null;
    }

    @objid ("bee3722e-1342-4601-aa92-92db403bafb2")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] {"All Files (*.xmi; *.uml; *.xml)", "XMI Files (*.xmi)", "UML Files (*.uml)", "XML Files (*.xml)" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi; *.uml; *.xml", "*.xmi", "*.uml", "*.xml" }); 
        
        setPath();
    }

    @objid ("bf943d1a-4da7-4e00-985c-5abbcebceda4")
    @Override
    public void setPath() {
        if (this.path.equals(""))
            this.path = ReverseProperties.getInstance().getXMIFolder();
        
        this.fileChooserComposite.getDialog().setFilterPath(this.path);
        this.fileChooserComposite.getDialog().setFileName("");
        this.fileChooserComposite.setText(this.path);
    }

}
