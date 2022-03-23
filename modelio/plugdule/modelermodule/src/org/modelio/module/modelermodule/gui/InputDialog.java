/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.module.modelermodule.i18n.I18nMessageService;

/**
 * Basic swt dialog asking for a text value.
 */
@objid ("9146683a-3de5-4733-8a86-f237e65632c1")
public class InputDialog {
    @objid ("2eb970b8-a513-494e-aec0-0fe2f652b5ed")
    protected String name;

    @objid ("42db9d57-161a-4113-83cd-c0c8baee383e")
    protected Text text;

    @objid ("bd4cd8e1-f737-4ebf-b03c-4aea857f1692")
    protected Shell shell;

    /**
     * Private constructor, initializing the dialog's content.
     * @param parent the parent to attach the dialog to.
     * @param label the label to display in the dialog.
     * @param title the title for the dialog.
     * @param value the default value for the asked text.
     */
    @objid ("5a36d3aa-d711-42d6-a861-f874dfca685f")
    private  InputDialog(final Shell parent, final String label, final String title, final String value) {
        this.name = "";
        
        createContents(parent, label, title, value);
        
    }

    /**
     * Create contents of the window
     */
    @objid ("c8811028-b3ff-4c68-b3be-14510bd49936")
    private void createContents(final Shell parent, final String label, final String title, final String value) {
        this.shell = new Shell(parent, SWT.DIALOG_TRIM);
        
        this.shell.setLayout(new FormLayout());
        this.shell.setText(title);
        
        final Label nameLabel = new Label(this.shell, SWT.NONE);
        nameLabel.setAlignment(SWT.CENTER);
        final FormData fd_nameLabel = new FormData();
        fd_nameLabel.right = new FormAttachment(100, -5);
        fd_nameLabel.left = new FormAttachment(0, 5);
        fd_nameLabel.top = new FormAttachment(0, 5);
        fd_nameLabel.bottom = new FormAttachment(0, 25);
        nameLabel.setLayoutData(fd_nameLabel);
        nameLabel.setText(label);
        
        Composite buttons = createButtons();
        
        this.text = new Text(this.shell, SWT.BORDER);
        final FormData fd_text = new FormData();
        fd_text.bottom = new FormAttachment(buttons, 0);
        fd_text.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
        fd_text.top = new FormAttachment(nameLabel, 0, SWT.BOTTOM);
        fd_text.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
        this.text.setText(value);
        this.text.setLayoutData(fd_text);
        
        this.shell.pack();
        this.shell.setSize(this.shell.getSize().x + 20, this.shell.getSize().y);
        
    }

    @objid ("f66fe310-2259-4e4a-a7a3-ebdb877f4895")
    private Composite createButtons() {
        final Composite composite = new Composite(this.shell, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        final FormData fd_composite_1 = new FormData();
        fd_composite_1.left = new FormAttachment(0, 5);
        fd_composite_1.bottom = new FormAttachment(100, 0);
        fd_composite_1.right = new FormAttachment(100, -5);
        composite.setLayoutData(fd_composite_1);
        
        final Composite composite_2 = new Composite(composite, SWT.NONE);
        composite_2.setLayout(new FillLayout());
        
        final Button okButton = new Button(composite_2, SWT.NONE);
        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    InputDialog.this.name = InputDialog.this.text.getText();
                } catch (Exception e) {
                    InputDialog.this.name = "";
                }
                dispose();
            }
        });
        okButton.setText(I18nMessageService.getString("module.gui.ok"));
        
        final Button cancelButton;
        cancelButton = new Button(composite_2, SWT.NONE);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                InputDialog.this.name = null;
                dispose();
            }
        });
        cancelButton.setText(I18nMessageService.getString("module.gui.cancel"));
        return composite;
    }

    /**
     * Opens a dialog and returns the chosen text.
     * @param parent the parent to attach the dialog to.
     * @param label the label to display in the dialog.
     * @param title the title for the dialog.
     * @param value the default value for the asked text.
     * @return the text inputed into the dialog.
     */
    @objid ("0f3dd658-3d3d-4b56-add5-9bfef3b33d2f")
    public static String showInputDialog(final Shell parent, final String label, final String title, final String value) {
        InputDialog diag = new InputDialog(parent, label, title, value);
        return diag.choose();
    }

    @objid ("d0020e3e-5370-4f0c-9642-d78ca40db336")
    private String choose() {
        ShellHelper.centerShell(this.shell);
        
        this.shell.open();
        this.shell.layout();
        
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return this.name;
    }

    @objid ("57550b8d-cf97-4592-b6b2-02ee46553183")
    protected void dispose() {
        this.shell.dispose();
    }

}
