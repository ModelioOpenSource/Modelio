/* 
 * Copyright 2013-2019 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * @author ebrosse
 */
@objid ("a0779b5c-777f-4f07-a80a-b6171f1c14fd")
public abstract class AbstractSwtWizardImport extends AbstractSwtWizardWindow {
    @objid ("cd62fa67-6681-47d4-9ceb-870c4800254c")
    public AbstractSwtWizardImport(final Shell parent, IProgressService progressService, IProjectService projectService) {
        super(parent, progressService, projectService);
    }

    @objid ("1752b53f-b27d-41c7-b02e-797b60d8dec1")
    @Override
    protected void enableOrDisableCompistes(boolean isEnable) {
        if (!this.shell.isDisposed()) {
        
            if (this.fileChooserComposite != null) {
                this.fileChooserComposite.setEnabled(isEnable);
            }
            
            this.validateComposite.getValidationButton().setEnabled(isEnable);        
        }
    }

    @objid ("716fb0ec-adf1-483c-a9bc-f92d1ccfcdfe")
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
        this.fileChooserComposite = new FileChooserComposite(this.shell, SWT.NONE, SWT.OPEN);
        
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
        
        this.fileChooserComposite.getSearch().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AbstractSwtWizardImport.this.fileChooserComposite.searchFile();
            }
        });
        
        this.fileChooserComposite.getTextButton().addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                AbstractSwtWizardImport.this.fileChooserComposite.getText();
            }
        });
        
        this.fileChooserComposite.getTextButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AbstractSwtWizardImport.this.fileChooserComposite.getText();
            }
        });
        
        final FormData fd_laProgressBar = new FormData();
        fd_laProgressBar.top = new FormAttachment(this.fileChooserComposite, 0);
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
     * set default configuration of the dialog box
     */
    @objid ("aca7ba61-6543-4ef2-a481-6504feb8efb2")
    @Override
    public void setDefaultDialog() {
        this.fileChooserComposite.getDialog().setFilterNames(new String[] {"All Files (*.xmi; *.uml; *.xml)", "XMI Files (*.xmi)", "UML Files (*.uml)", "XML Files (*.xml)" });
        this.fileChooserComposite.getDialog().setFilterExtensions(new String[] { "*.xmi; *.uml; *.xml", "*.xmi", "*.uml", "*.xml" }); 
        
        setPath();
    }

    /**
     * the action i.e. import or export
     */
    @objid ("01d8dd6a-429e-4e6b-b2f9-b4251ea46390")
    @Override
    public abstract void validationAction();

    /**
     * set button labels
     */
    @objid ("8ba82004-af81-4ff0-9ff1-d5688fae3e14")
    @Override
    public abstract void setLabels();

    /**
     * initialize file path
     */
    @objid ("4c30c5dd-773b-4108-8524-4b736b54295a")
    @Override
    public void setPath() {
        if (this.path.equals(""))
            this.path = ReverseProperties.getInstance().getXMIFolder();
        
        this.fileChooserComposite.getDialog().setFilterPath(this.path);
        this.fileChooserComposite.getDialog().setFileName("");
        this.fileChooserComposite.setText(this.path);
    }

    @objid ("ccdc1702-d2df-4730-a48b-005a6643d1d2")
    protected void wrongFileExtension() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                final MessageBox messageBox = new MessageBox(AbstractSwtWizardImport.this.shell, SWT.ICON_INFORMATION);
                messageBox.setText(Xmi.I18N.getString("fileChooser.dialog.wrongEcoreFormat.title"));
                messageBox.setMessage(Xmi.I18N.getString("fileChooser.dialog.wrongEcoreFormat.description"));
                messageBox.open();
            }
        });
    }

}
