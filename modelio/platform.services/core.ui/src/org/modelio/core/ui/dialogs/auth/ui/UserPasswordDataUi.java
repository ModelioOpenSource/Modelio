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

package org.modelio.core.ui.dialogs.auth.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

@objid ("633e4230-35aa-4f02-8c14-e9939490815f")
public class UserPasswordDataUi extends AbstractDataUi {
    @objid ("3e3c7f03-4284-4eb5-8573-1cdb2f753e15")
    private Text userText;

    @objid ("447138e6-e56e-4b91-8289-8373e3b9c139")
    private Text passwordText;

    @objid ("801ebe15-4689-4bf6-a29b-01829f733e8d")
    private Label checkResultLabel;

    @objid ("8057bb40-89d3-4090-9208-6fc95278b01b")
    private Button memorizeButton;

    @objid ("76339462-38b1-483a-baa9-e34e28bc2d8a")
    public UserPasswordDataUi(Composite parent) {
        super(parent);
    }

    @objid ("980ecd95-f02f-43f4-9d09-fb4191725317")
    @Override
    protected void createContents(Composite parent) {
        final Composite data = new Composite(parent, SWT.None);
        data.setLayout(new GridLayout(2, false));
        
        // User
        Label label = new Label(data, SWT.NONE);
        label.setText(CoreUi.I18N.getString("UserPasswordDataUi.user.label")); //$NON-NLS-1$
        
        this.userText = new Text(data, SWT.BORDER);
        this.userText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        // Password
        label = new Label(data, SWT.NONE);
        label.setText(CoreUi.I18N.getString("UserPasswordDataUi.password.label")); //$NON-NLS-1$
        
        this.passwordText = new Text(data, SWT.BORDER | SWT.PASSWORD);
        this.passwordText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        this.memorizeButton = new Button(data, SWT.CHECK);
        this.memorizeButton.setText(CoreUi.I18N.getString("UserPasswordDataUi.memorize.label"));
        this.memorizeButton.setToolTipText(CoreUi.I18N.getString("UserPasswordDataUi.memorize.tooltip"));
        GridData gd = new GridData(SWT.RIGHT, SWT.BOTTOM, false, true);
        gd.horizontalSpan = 2;
        this.memorizeButton.setLayoutData(gd);
    }

    @objid ("ed81d8ab-6a7b-45bc-bbce-c3c3b144f716")
    @Override
    public void show(IAuthData authData) {
        if (authData instanceof UserPasswordAuthData) {
            UserPasswordAuthData userPassAuthData = (UserPasswordAuthData) authData;
            this.userText.setText(userPassAuthData.getUser() != null ? userPassAuthData.getUser() : "");
            this.passwordText.setText(userPassAuthData.getPassword() != null ? userPassAuthData.getPassword() : "");
            this.memorizeButton.setSelection(userPassAuthData.isStored());
        }
    }

    @objid ("bc4b5ab4-f2b5-409e-bc1a-83aae1a2506a")
    @Override
    public void collect(IAuthData authData) {
        UserPasswordAuthData userPassAuthData = (UserPasswordAuthData) authData;
        userPassAuthData.setUser(this.userText.getText());
        userPassAuthData.setPassword(this.passwordText.getText());
        userPassAuthData.setStored(this.memorizeButton.getSelection());
    }

}
