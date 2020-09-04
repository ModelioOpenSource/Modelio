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

package org.modelio.bpmnxml.importer.commands;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.bpmnxml.plugin.BPMNXml;
import org.modelio.ui.UIImages;

@objid ("f839b6d2-ee06-49ea-9e11-0be31b9ad1b2")
class BPMImportDialog extends ModelioDialog {
    @objid ("8e973d47-3101-4b71-86e9-27ee0734769f")
    private Composite composite = null;

    @objid ("9bb2cd8f-ff41-4177-a6f7-d0af89008aeb")
    private Controller controller;

    @objid ("680e57e4-d7fc-4171-8b33-667ff37bbc0f")
    private Text filePathText;

    @objid ("f3db26e7-468b-4c5e-b875-b77f8846d5ae")
    private Button selectFileButton = null;

    @objid ("5f27b022-d599-4b7d-87aa-20ef1b7dd179")
    private Button keepIdCheckBox;

    @objid ("88dfef39-bd4e-4724-97b9-36556f4e44d0")
    private BPMImportModel model;

    @objid ("8d497879-e889-4a53-842e-ae592e5eb021")
    private Button okButton;

    @objid ("7e9ce11a-7231-4bf3-884d-a80edd012bae")
    public BPMImportDialog(Shell parentShell, BPMImportModel model) {
        super(parentShell);
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX);
        this.controller = new Controller(this, model);
        this.model = model;
    }

    @objid ("b2a95830-940f-4bf3-92b4-59254522149b")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        this.okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        this.okButton.setEnabled(false);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("92b15b49-f7cd-4939-8029-fabef7f8fef8")
    @Override
    public Control createContentArea(Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.composite.setLayout(new GridLayout(3, false));
        
        // Action group
        Composite generateGroup = createActionGroup(this.composite);
        generateGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
        return this.composite;
    }

    @objid ("d8acc0c6-a2fa-4303-abee-71d6f3254ef1")
    @Override
    public void init() {
        Shell shell = this.getShell();
        if (this.model.isUpdate()) {
            shell.setText(BPMNXml.I18N.getMessage("ui.command.update.title"));
            setTitle(BPMNXml.I18N.getMessage("ui.command.update.title"));
            setMessage(BPMNXml.I18N.getMessage("ui.command.update.message"));
        } else {
            shell.setText(BPMNXml.I18N.getMessage("ui.command.import.title"));
            setTitle(BPMNXml.I18N.getMessage("ui.command.import.title"));
            setMessage(BPMNXml.I18N.getMessage("ui.command.import.message"));
        }
        addListeners();
    }

    @objid ("85c576b8-3e65-48b0-84d0-790b0a8dccdd")
    public void update() {
        if (this.model.getFilePath() != null) {
            this.filePathText.setText(this.model.getFilePath());
            this.okButton.setEnabled(true);
        } else {
            this.okButton.setEnabled(false);
        }
    }

    @objid ("b1211528-5292-46d9-9fb7-b8b80cdc020e")
    @Override
    protected Point getInitialSize() {
        return new Point(600, 250);
    }

    @objid ("c6ef7ce7-48b9-4f2f-af1d-2234f161309b")
    private void addListeners() {
        this.keepIdCheckBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                BPMImportDialog.this.controller.onSelectKeepId(((Button) e.getSource()).getSelection());
            }
        });
        
        this.selectFileButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                BPMImportDialog.this.controller.onSelectSelectFile();
            }
        });
    }

    @objid ("0cd2016c-b859-4f52-ab60-6b85f8666197")
    private Composite createActionGroup(Composite parent) {
        GridData gd = null;
        
        // Archive
        Label filePathLabel = new Label(parent, SWT.NONE);
        gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gd.horizontalAlignment = GridData.FILL;
        gd.minimumHeight = 200;
        filePathLabel.setLayoutData(gd);
        filePathLabel.setText(BPMNXml.I18N.getMessage("ui.command.import.selectfile.label"));
        
        this.filePathText = new Text(parent, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd.horizontalAlignment = GridData.FILL;
        this.filePathText.setLayoutData(gd);
        this.filePathText.setEditable(false);
        
        this.selectFileButton = new Button(parent, SWT.FLAT);
        this.selectFileButton.setImage(UIImages.FILECHOOSE);
        gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalAlignment = GridData.FILL;
        this.selectFileButton.setLayoutData(gd);
        this.selectFileButton.setFocus();
        
        // Build type
        this.keepIdCheckBox = new Button(parent, SWT.CHECK);
        gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalAlignment = GridData.FILL;
        gd.horizontalSpan = 2;
        this.keepIdCheckBox.setLayoutData(gd);
        this.keepIdCheckBox.setText(BPMNXml.I18N.getMessage("ui.command.import.keepid.label"));
        this.keepIdCheckBox.setToolTipText(BPMNXml.I18N.getMessage("ui.command.import.keepid.tooltip"));
        return parent;
    }

    @objid ("4910a397-d76e-4e31-ac6e-31d3864133ba")
    private static class Controller {
        @objid ("1341c4bd-d176-40b8-ba17-95d611e422eb")
        private BPMImportDialog dlg;

        @objid ("95fecfff-c0d9-4eba-9ad9-0e9ed086c5d9")
        private BPMImportModel model;

        @objid ("b02709c6-7a8a-4f6a-8065-61178edb9f80")
        public Controller(BPMImportDialog dlg, BPMImportModel model) {
            this.dlg = dlg;
            this.model = model;
        }

        @objid ("43059de8-a65b-4bd1-a2e2-959e4cc0aa4a")
        public void onSelectSelectFile() {
            FileDialog dialog = new FileDialog(new Shell(), SWT.OPEN);
            dialog.setFilterExtensions(new String[] { "*.bpmn" });
            dialog.setFilterNames(new String[] { "*.bpmn" });
            dialog.setText(BPMNXml.I18N.getMessage("ui.command.import.description"));
            
            String path = dialog.open();
            
            if (path != null) {
                File bpmnFile = new File(path);
                if (bpmnFile.exists()) {
                    this.model.setFilePath(bpmnFile.getAbsolutePath());
                }
            }
            this.dlg.update();
        }

        @objid ("5296ff2f-1cfe-44f8-aed8-29d4a4b568e8")
        public void onSelectKeepId(boolean keepIdCkeckbox) {
            this.model.setKeeyId(keepIdCkeckbox);
            this.dlg.update();
        }

    }

}
