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
import org.modelio.xmi.gui.report.ReportModel;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class provides the XMI import dialog
 * @author ebrosse
 */
@objid ("ea710891-a275-4523-a1f2-402acf87333c")
public class SwtWizardImport extends AbstractSwtWizardWindow {
    @objid ("54e9a756-61db-4392-8c51-ae214789dce8")
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
               
                  try(ITransaction t = this.projectService.getSession().getTransactionSupport().createTransaction("Import") ) {
        
                    this.progressService.busyCursorWhile (new ImportThread(this.shell,
                            getTheProgressBar() ));
                    t.commit();
        
                    ReportModel reportModel = revProp.getReportModel();
                    if (!reportModel.isEmpty()){
                        reportBox(reportModel, revProp.getNavigationServices(), revProp.getLogFilePath());       
                    }else{
                        completeBox();
                    }        
                
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

    @objid ("073f156d-159c-4419-aeca-d5a56261250b")
    @Override
    public void setLabels() {
        setTitle(Xmi.I18N.getString("fileChooser.banner.import.title"));
        setDescription(Xmi.I18N.getString("fileChooser.banner.import.description"));
        setFrametitle(Xmi.I18N.getString("fileChooser.frame.import.title"));
        setCancelButton(Xmi.I18N.getString("fileChooser.buttons.import.cancel.name"));
        setValidateButton(Xmi.I18N.getString("fileChooser.buttons.import.import.name"));
    }

    @objid ("3759e190-f11c-4b8b-913a-c0f3ddeab109")
    @Override
    public void setPath() {
        try {         
            if (this.path.equals(""))
                this.path = ReverseProperties.getInstance().getXMIFolder();
        
            this.fileChooserComposite.getDialog().setFilterPath(this.path);       
            this.fileChooserComposite.getDialog().setFileName("");      
            this.fileChooserComposite.setText(this.path);
        } catch (final NoClassDefFoundError e) {       
            this.fileChooserComposite.setText(e.getMessage());
        }
    }

    @objid ("b6feb3f7-b70a-409d-be67-7ff97278009d")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] {"All Files (*.xmi; *.uml; *.xml)", "XMI Files (*.xmi)", "UML Files (*.uml)", "XML Files (*.xml)" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi; *.uml; *.xml", "*.xmi", "*.uml", "*.xml" });         
        setPath();
    }

    /**
     * @param parent : the parent shell
     * @param style : the SWT style
     */
    @objid ("62fd7184-3a2d-43de-b370-42ca75091996")
    public SwtWizardImport(final Shell parent, final int style) {
        super(parent, style);
        setSelectedType(SWT.OPEN);
        this.exportWindows = false;
    }

    /**
     * @param parent : the parent shell
     */
    @objid ("ffcab5e9-c1f3-4e9e-899f-e1bbd411d3f8")
    @Inject
    public SwtWizardImport(final Shell parent, IProgressService progressService, IProjectService projectService) {
        this(parent, SWT.NONE);
        this.progressService = progressService;
        this.projectService = projectService;
    }

    @objid ("da6e108f-373f-430c-8f6f-80d110999d55")
    @Override
    public void setOptionComposite(final Shell shell, IProjectService projectService) {
        this.optionComposite = null;
    }

}
