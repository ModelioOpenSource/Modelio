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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.api.XMIExtension;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.preferences.XmiPreferencesKeys;

/**
 * @author ebrosse
 */
@objid ("6b9ab30b-24e8-49d5-86d7-f7597019fb69")
public abstract class AbstractSwtWizardExport extends AbstractSwtWizardWindow {
    @objid ("bb0a5833-7fcf-4145-8f3f-2be03e3d2a24")
    protected OptionComposite optionComposite = null;

    @objid ("38d96d87-c247-49ac-98a9-4e3cb0c2ae69")
    public  AbstractSwtWizardExport(final Shell parent, IProgressService progressService, IProjectService projectService) {
        super(parent, progressService, projectService);
    }

    @objid ("d6fb3081-2d99-4c74-a0ff-7abb9be08d31")
    @Override
    protected void enableOrDisableCompistes(boolean isEnable) {
        if (!this.shell.isDisposed()) {
        
            if (this.fileChooserComposite != null) {
                this.fileChooserComposite.setEnabled(isEnable);
            }
        
            if (this.optionComposite != null) {
                this.optionComposite.setEnabled(isEnable);
            }
        
            this.validateComposite.getValidationButton().setEnabled(isEnable);
        
        }
        
    }

    /**
     * @param inpath : the initial path
     * @return the same path in a correct form
     */
    @objid ("14edaffa-1911-4844-b828-264f8f7d0e1d")
    private String checkAndReplaceEndPath(final String inpath) {
        if (inpath.endsWith("\\")) {
            return inpath.substring(0, inpath.lastIndexOf("\\"));
        } else if (inpath.endsWith("/")) {
            return inpath.substring(0, inpath.lastIndexOf("/"));
        }
        return inpath;
    }

    @objid ("b7708df3-595a-4ff7-955e-f52056755026")
    @Override
    protected void createContents() {
        setLabels();
        
        this.shell = new Shell(getParent(), 67696 | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.TITLE);
        this.shell.setLayout(new FormLayout());
        this.shell.setText(this.frametitle);
        
        // Header composite
        final BannerComposite headerComposite = new BannerComposite(this.shell, SWT.NONE, this.title, this.description);
        headerComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        
        // File chooser composite
        this.fileChooserComposite = new FileChooserComposite(this.shell, SWT.NONE, SWT.SAVE);
        
        // Option Composite
        setOptionComposite(this.shell, this.projectService);
        
        // Progress Bar Composite
        this.theProgressBar = new ProgressBarComposite(this.shell, SWT.NONE);
        
        // Validation Composite
        this.validateComposite = new ValidationBoutonComposite(this.shell, SWT.NONE, this.cancelButton, this.validateButton);
        
        this.validateComposite.getValidationButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                disableComposites();
        
                if (getFileChooserComposite().getCurrentFile() != null) {
                    validationAction();
                } else {
                    selectAFile();
                }
                enableComposites();
            }
        });
        
        this.validateComposite.getCancelButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setCancellation(true);
                cancelAction();
            }
        });
        
        final FormData fd_headerComposite = new FormData();
        fd_headerComposite.right = new FormAttachment(100, 0);
        fd_headerComposite.bottom = new FormAttachment(0, headerComposite.getMinHeight());
        fd_headerComposite.top = new FormAttachment(0, 0);
        fd_headerComposite.left = new FormAttachment(0, 0);
        headerComposite.setLayoutData(fd_headerComposite);
        
        final FormData fd_fileChooserComposite = new FormData();
        fd_fileChooserComposite.top = new FormAttachment(headerComposite, 5);
        fd_fileChooserComposite.right = new FormAttachment(headerComposite, 0, SWT.RIGHT);
        fd_fileChooserComposite.left = new FormAttachment(headerComposite, 0, SWT.LEFT);
        this.fileChooserComposite.setLayoutData(fd_fileChooserComposite);
        
        final FormData fd_optionComposite = new FormData();
        fd_optionComposite.top = new FormAttachment(this.fileChooserComposite, 0);
        fd_optionComposite.right = new FormAttachment(this.fileChooserComposite, -5, SWT.RIGHT);
        fd_optionComposite.left = new FormAttachment(this.fileChooserComposite, 5, SWT.LEFT);
        
        this.optionComposite.setLayoutData(fd_optionComposite);
        
        this.optionComposite.getUMLButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (AbstractSwtWizardExport.this.optionComposite.getUMLButton().getSelection()) {
                    setUMLExtension();
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFilterNames(new String[] { "UML Files", "XMI Files" });
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.uml", "*.xmi" });
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFileName(AbstractSwtWizardExport.this.selectedElt.getName() + ".uml");
                }
            }
        });
        
        this.optionComposite.getXMIButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (AbstractSwtWizardExport.this.optionComposite.getXMIButton().getSelection()) {
                    setXMIExtension();
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFilterNames(new String[] { "XMI Files", "UML Files" });
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi", "*.uml" });
                    AbstractSwtWizardExport.this.fileChooserComposite.getDialog().setFileName(AbstractSwtWizardExport.this.selectedElt.getName() + ".xmi");
                }
            }
        
        });
        
        
        this.fileChooserComposite.getSearch().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkPath(AbstractSwtWizardExport.this.fileChooserComposite.searchFile());
            }
        });
        
        this.fileChooserComposite.getTextButton().addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                checkPath(AbstractSwtWizardExport.this.fileChooserComposite.getText());
            }
        });
        
        this.fileChooserComposite.getTextButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkPath(AbstractSwtWizardExport.this.fileChooserComposite.getText());
            }
        });
        
        final FormData fd_laProgressBar = new FormData();
        fd_laProgressBar.top = new FormAttachment(this.optionComposite, 0);
        
        fd_laProgressBar.left = new FormAttachment(this.fileChooserComposite, 5, SWT.LEFT);
        fd_laProgressBar.right = new FormAttachment(this.fileChooserComposite, -5, SWT.RIGHT);
        this.theProgressBar.setLayoutData(fd_laProgressBar);
        
        final FormData fd_validateComposite = new FormData();
        fd_validateComposite.top = new FormAttachment(this.theProgressBar, 5);
        fd_validateComposite.bottom = new FormAttachment(100, -5);
        fd_validateComposite.left = new FormAttachment(this.theProgressBar, 0, SWT.LEFT);
        fd_validateComposite.right = new FormAttachment(this.theProgressBar, 0, SWT.RIGHT);
        this.validateComposite.setLayoutData(fd_validateComposite);
        
        setDefaultDialog();
        this.shell.pack();
        
        //Set minimum size
        if (this.shell.getBounds().width > this.minwidth) {
            this.shell.setMinimumSize(new Point(this.shell.getBounds().width, this.shell.getBounds().height));
        }else {
            this.shell.setMinimumSize(new Point(this.minwidth, this.shell.getBounds().height));
        }
        
        this.validateComposite.getValidationButton().setFocus();
        
    }

    /**
     * @return the optionComposite
     */
    @objid ("83cedd1c-ceb4-471f-a8f6-061d5ff6db38")
    public OptionComposite getOptionComposite() {
        return this.optionComposite;
    }

    /**
     * set default configuration of the dialog box
     */
    @objid ("55316178-7269-4557-b921-c20309e84a9d")
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

    /**
     * the action i.e. import or export
     */
    @objid ("2810704b-95e2-4824-aa83-01114b9947f5")
    @Override
    public abstract void validationAction();

    /**
     * set button labels
     */
    @objid ("87967f44-ce76-4c11-8fea-7c34a6d9d4f4")
    @Override
    public abstract void setLabels();

    /**
     * initialize file path
     */
    @objid ("7e14c469-c08d-4ae5-8e35-87132afed7bd")
    @Override
    public void setPath() {
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.setRootElement(this.selectedElt);
        
        if (this.path.equals(""))
            this.path = genProp.getProjectRoot() + java.io.File.separator + "XMI";
        
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
     * @param shell : the current shell
     */
    @objid ("6ed93d32-f160-4ff7-80ed-e71848f4476e")
    @Inject
    public void setOptionComposite(final Shell shell, IProjectService projectService) {
        this.optionComposite = new OptionComposite(shell, SWT.NONE, SWT.OPEN, projectService);
    }

    @objid ("e83a4799-78d4-4e3a-9b90-a58d50a0b7b7")
    private void checkPath(String filePath) {
        if ((filePath != null) && (!filePath.equals(""))) {
            int length = filePath.length();
            if ((length - 4) == filePath.lastIndexOf(".uml")) {
                this.optionComposite.setUMLSelected();
            } else if (filePath.lastIndexOf(".xmi") == (length - 4)) {
                this.optionComposite.setXMISelected();
            } else {
                if (this.optionComposite.getXMIButton().getSelection()) {
                    setXMIExtension();
                } else {
                    setUMLExtension();
                }
            }
        }
        
    }

    @objid ("6b229e8b-b06c-4f69-bfb0-5a292fc94192")
    private void setUMLExtension() {
        String filePath = this.fileChooserComposite.getText();
        int length = filePath.length();
        if ((length - 4) != filePath.lastIndexOf(".uml")) {
            if (filePath.lastIndexOf(".xmi") == (length - 4)) {
                filePath = filePath.substring(0, length - 4) + ".uml";
            } else {
                filePath = filePath + ".uml";
            }
            this.fileChooserComposite.setText(filePath);
        }
        
    }

    @objid ("a69560cc-5a89-4c1a-a332-f110797bae41")
    private void setXMIExtension() {
        String filePath = this.fileChooserComposite.getText();
        int length = filePath.length();
        if ((length - 4) != filePath.lastIndexOf(".xmi")) {
            if (filePath.lastIndexOf(".uml") == (length - 4)) {
                filePath = filePath.substring(0, length - 4) + ".xmi";
            } else {
                filePath = filePath + ".xmi";
            }
            this.fileChooserComposite.setText(filePath);
        }
        
    }

    @objid ("2b191f9f-21c4-44cc-9fc4-6f0a816e888f")
    private void fileCanNotBeCreated(File file) {
        MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
        messageBox.setMessage(Xmi.I18N.getMessage("fileChooser.dialog.confirm.canNotCreateFile.label", file.getName()));
        messageBox.setText(Xmi.I18N.getString("fileChooser.dialog.confirm.canNotCreateFile.title"));
        messageBox.open();
        
    }

    @objid ("0ec9e459-a492-4ea8-a221-1c6e55a442ac")
    private boolean confirmOverwrite(File file) {
        MessageBox messageBox = new MessageBox(this.shell, SWT.YES | SWT.NO);
        messageBox.setMessage(Xmi.I18N.getMessage("fileChooser.dialog.confirm.overwriteFile.label", file.getName()));
        messageBox.setText(Xmi.I18N.getString("fileChooser.dialog.confirm.overwriteFile.title"));
        return (messageBox.open() == SWT.YES);
    }

    @objid ("28e3c920-f106-4258-bc32-84234a9df6f8")
    protected boolean testExportFilePath(File theFile) {
        if (theFile.exists()) {
            return confirmOverwrite(theFile);
        }
        
        if (theFile.getParentFile().exists() || theFile.getParentFile().mkdirs()) {
            return true;
        }
        
        fileCanNotBeCreated(theFile);
        return false;
    }

}
