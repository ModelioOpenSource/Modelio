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

package org.modelio.app.project.conf.dialog.workmodel.local;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.ui.UIColor;
import org.modelio.ui.dialog.ModelioDialog;

/**
 * Dialog box used to rename a local fragment in a project.
 * <p>
 * Use {@link RenameLocalModelDialog#getResult()} to get the new fragment name.
 * </p>
 */
@objid ("57d57d5d-540b-4315-84f7-16a8f0dacf4f")
public final class RenameLocalModelDialog extends ModelioDialog {
    @objid ("fd012737-0b39-4053-819d-b276ff6dbba4")
     List<String> invalidIds;

    @objid ("14b634a9-879a-40cf-9433-573d8f64b849")
    private String result = null;

    @objid ("e120b171-f78d-439f-a966-1037cecc7576")
     Text fragmentIdText;

    @objid ("d50174b1-596d-4b14-8aa8-344a2650952f")
     Button addBtn;

    @objid ("5831b8b2-6fad-4001-b55e-1b1681418af6")
    public String getResult() {
        return this.result;
    }

    @objid ("fcccbcd1-9157-4e0a-af91-de936fe01c38")
    public RenameLocalModelDialog(Shell parentShell, List<String> allFragmentsIds) {
        super(parentShell);
        this.invalidIds = allFragmentsIds;
    }

    @objid ("3a743f1c-90df-49b6-92a6-04bba01d133d")
    @Override
    public Control createContentArea(Composite parent) {
        // fragment data area
        final Composite data = new Composite(parent, 0);
        data.setLayout(new GridLayout(3, false));
        data.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // fragment name
        Label label = new Label(data, SWT.NULL);
        label.setText(AppProjectConf.I18N.getString("RenameLocalModelDialog.FragmentId")); //$NON-NLS-1$
        
        this.fragmentIdText = new Text(data, SWT.BORDER | SWT.SINGLE);
        this.fragmentIdText.setText(""); //$NON-NLS-1$
        this.fragmentIdText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        this.fragmentIdText.addModifyListener(new ModifyListener() {
        
            @Override
            public void modifyText(ModifyEvent e) {
                isFragmentIdValid();
            }
        });
        
        // fragment type description message
        Label fragmentDescription = new Label(data, SWT.NONE);
        fragmentDescription.setText(AppProjectConf.I18N.getString("RenameLocalModelDialog.Description")); //$NON-NLS-1$
        fragmentDescription.setForeground(UIColor.LABEL_TIP_FG);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        fragmentDescription.setLayoutData(gd);
        return data;
    }

    @objid ("c12d7f6e-6938-4718-bbee-b579477434d1")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, Window.CANCEL, IDialogConstants.CANCEL_LABEL, true);
        this.addBtn = createButton(parent, Window.OK, AppProjectConf.I18N.getString("RenameLocalModelDialog.AddFragment"), true); //$NON-NLS-1$
        this.addBtn.setEnabled(false);
    }

    @objid ("fb6d12ce-f50c-46fd-9985-9e3d5c862893")
    @Override
    public void init() {
        getShell().setText(AppProjectConf.I18N.getString("RenameLocalModelDialog.ShellTitle")); //$NON-NLS-1$
        setTitle(AppProjectConf.I18N.getString("RenameLocalModelDialog.Title")); //$NON-NLS-1$
        setMessage(AppProjectConf.I18N.getString("RenameLocalModelDialog.Message")); //$NON-NLS-1$
    }

    @objid ("700355a0-6946-4fc6-83ca-f494e6596593")
    @Override
    protected void okPressed() {
        this.result = this.fragmentIdText.getText();
        
        super.okPressed();
    }

    @objid ("7de4da1e-e250-4098-8426-a20343a4e00b")
    protected void isFragmentIdValid() {
        String fragmentId = this.fragmentIdText.getText();
        this.addBtn.setEnabled(false);
        this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_RED));
        if (this.invalidIds.contains(fragmentId)) {
            setErrorMessage(AppProjectConf.I18N.getMessage("RenameLocalModelDialog.ErrorMessage.ExistAlready", fragmentId));
        } else if (!fragmentId.isEmpty()) {
            setErrorMessage(null);
            this.addBtn.setEnabled(true);
            this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        }
    }

    @objid ("832bb7e8-6fe5-4ebb-a58e-36a4b1a32710")
    @Override
    protected String getHelpId() {
        return AppProjectConf.I18N.getMessage("RenameLocalModelDialog.HelpId");
    }

}
