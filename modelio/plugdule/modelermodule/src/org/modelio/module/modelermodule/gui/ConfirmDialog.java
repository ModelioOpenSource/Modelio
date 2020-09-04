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
import org.modelio.module.modelermodule.i18n.I18nMessageService;

/**
 * Basic swt dialog asking to choose a confirmation value.
 * @see ConfirmDialog.Values
 */
@objid ("fccbb842-64e7-49fd-9bdc-fa7e76f25904")
public class ConfirmDialog {
    @objid ("09333bfb-5898-4650-9705-a0c8eaa2465c")
    protected Values res;

    @objid ("1c378297-cad3-4584-b485-518761ac24e5")
    protected Shell shell;

    @objid ("12474440-0321-48ff-a1c6-bb84af340409")
    private ConfirmDialog(final Shell parent, final String label, final String title) {
        this.res = Values.CANCEL_OPTION;
        
        createContents(parent, label, title);
    }

    /**
     * Create contents of the window
     */
    @objid ("b43dfae2-cbc4-4822-9019-e76e27e0e39e")
    private void createContents(final Shell parent, final String label, final String title) {
        this.shell = new Shell(parent, SWT.DIALOG_TRIM);
        
        this.shell.setLayout(new FormLayout());
        this.shell.setText(title);
        
        Composite composite = createButtons();
        
        final Label lab = new Label(this.shell, SWT.NONE);
        lab.setAlignment(SWT.CENTER);
        final FormData fd_label = new FormData();
        fd_label.bottom = new FormAttachment(composite, -10, SWT.TOP);
        fd_label.top = new FormAttachment(0, 10);
        fd_label.right = new FormAttachment(100, -5);
        fd_label.left = new FormAttachment(0, 5);
        lab.setLayoutData(fd_label);
        lab.setText(label);
        
        this.shell.pack();
        this.shell.setSize(this.shell.getSize().x + 30, this.shell.getSize().y);
    }

    @objid ("75426ee1-4c5c-4101-a58f-58dbf75d6249")
    private Composite createButtons() {
        final Composite composite = new Composite(this.shell, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        final FormData fd_composite_1 = new FormData();
        fd_composite_1.left = new FormAttachment(0, 5);
        fd_composite_1.bottom = new FormAttachment(100, -5);
        fd_composite_1.right = new FormAttachment(100, -5);
        composite.setLayoutData(fd_composite_1);
        
        final Composite composite_2 = new Composite(composite, SWT.NONE);
        composite_2.setLayout(new FillLayout());
        
        final Button yesButton = new Button(composite_2, SWT.NONE);
        yesButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                ConfirmDialog.this.res = Values.YES_OPTION;
                dispose();
            }
        });
        yesButton.setText(I18nMessageService.getString("module.gui.yes"));
        
        final Button noButton = new Button(composite_2, SWT.NONE);
        noButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                ConfirmDialog.this.res = Values.NO_OPTION;
                dispose();
            }
        });
        noButton.setText(I18nMessageService.getString("module.gui.no"));
        
        final Button cancelButton = new Button(composite_2, SWT.NONE);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                ConfirmDialog.this.res = Values.CANCEL_OPTION;
                dispose();
            }
        });
        cancelButton.setText(I18nMessageService.getString("module.gui.ignore"));
        return composite;
    }

    @objid ("2134f690-829e-4b1e-a251-04e4f63b7295")
    private Values choose() {
        ShellHelper.centerShell(this.shell);
        
        this.shell.open();
        this.shell.layout();
        
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return this.res;
    }

    /**
     * Opens a dialog and returns the chosen value.
     * 
     * @param parent the parent to attach the dialog to.
     * @param label the label to display in the dialog.
     * @param title the title for the dialog.
     * @return the value selected into the dialog.
     */
    @objid ("3c228347-52de-427a-9a35-a02058abcfeb")
    public static Values showConfirmDialog(final Shell parent, final String label, final String title) {
        ConfirmDialog diag = new ConfirmDialog(parent, label, title);
        return diag.choose();
    }

    @objid ("a743d91c-71f8-414a-914f-fa3cbe429bb5")
    protected void dispose() {
        this.shell.dispose();
    }

    /**
     * Confirm dialog's results.
     */
    @objid ("ab13ee6f-0d9e-4cc6-b8c5-91b9c8d44b44")
    public enum Values {
        /**
         * Yes choice.
         */
        YES_OPTION,
        /**
         * No choice.
         */
        NO_OPTION,
        /**
         * Cancel choice.
         */
        CANCEL_OPTION;
    }

}
