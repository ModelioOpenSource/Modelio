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

package org.modelio.app.project.conf.dialog.urls;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.modelio.platform.ui.dialog.ModelioDialog;

@objid ("42cc222f-e787-4254-a0bb-5fbafc83450e")
public class UrlConfiguratorDialog extends ModelioDialog {
    @objid ("3b3523d5-8ebd-4067-b3fa-7afccebe7163")
    private boolean isLocalProject;

    @objid ("3538d528-2db6-416a-ba70-7691f3b587a7")
    private UrlEntry entry;

    @objid ("06c6fabd-b5e0-4a29-93de-fd57633c9a1c")
    private Text nameText;

    @objid ("30305e2e-5d9a-4d36-b473-09c73d7c71a1")
    private Button del;

    @objid ("bfb7a66b-6540-4d30-a1ec-009b9e5b59df")
    private Text urlText;

    @objid ("33548ca5-3e39-4e77-9406-7ce75ef70ee3")
     Button addBtn = null;

    @objid ("625c7be5-af92-4a08-a4a0-85bf9d67ff80")
     Button editBtn = null;

    @objid ("1b6f4b05-2a05-4a8b-93c2-1635a3df56ab")
    public UrlConfiguratorDialog(Shell shell, UrlEntry entry, boolean isLocalProject) {
        super(shell);
        this.entry = entry;
        this.isLocalProject = isLocalProject;
    }

    @objid ("d4c3aec5-1dd5-436a-a91e-cca0c3c05554")
    @Override
    public Control createContentArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridData gdmain = new GridData(SWT.FILL, SWT.TOP, true, true);
        composite.setLayoutData(gdmain);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 2;
        layout.marginHeight = 2;
        composite.setLayout(layout);
        
        // Name label and field
        Label nameLabel = new Label(composite, SWT.NULL);
        nameLabel.setText("Name:");
        GridData gd = new GridData(SWT.RIGHT, SWT.FILL, false, true);
        nameLabel.setLayoutData(gd);
        
        this.nameText = new Text(composite, SWT.NULL);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        String entryName = (this.entry == null) ? AppProjectConf.I18N.getString("UrlConfiguratorDialog.EnterName") : this.entry.name;
        this.nameText.setText(entryName);
        this.nameText.setLayoutData(gd);
        this.nameText.setEditable(this.isLocalProject);
        this.nameText.addModifyListener(new ModifyListener() {
        
            @Override
            public void modifyText(ModifyEvent e) {
                String text = ((Text) e.getSource()).getText();
                if (text.matches("[^\\=\\<\\>\"\']+")) { // Not allow: =, <, >, ", '
                    ((Text) e.getSource()).setForeground(e.display.getSystemColor(SWT.COLOR_DARK_GREEN));
                    if (UrlConfiguratorDialog.this.addBtn != null) {
                        UrlConfiguratorDialog.this.addBtn.setEnabled(true);
                    }
                    if (UrlConfiguratorDialog.this.editBtn != null) {
                        UrlConfiguratorDialog.this.editBtn.setEnabled(true);
                    }
                } else {
                    ((Text) e.getSource()).setForeground(e.display.getSystemColor(SWT.COLOR_RED));
                    if (UrlConfiguratorDialog.this.addBtn != null) {
                        UrlConfiguratorDialog.this.addBtn.setEnabled(false);
                    }
                    if (UrlConfiguratorDialog.this.editBtn != null) {
                        UrlConfiguratorDialog.this.editBtn.setEnabled(false);
                    }
                }
            }
        });
        
        // Url label and field
        Label urlLabel = new Label(composite, SWT.NULL);
        urlLabel.setText("URL:");
        gd = new GridData(SWT.RIGHT, SWT.FILL, false, true);
        nameLabel.setLayoutData(gd);
        
        this.urlText = new Text(composite, SWT.NULL);
        String entryUrl = (this.entry == null) ? AppProjectConf.I18N.getString("UrlConfiguratorDialog.EnterURL") : this.entry.url;
        this.urlText.setText(entryUrl);
        this.urlText.setEditable(this.isLocalProject);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        
        this.urlText.setLayoutData(gd);
        return composite;
    }

    @objid ("bab52943-14a3-4825-847b-dd07c5fbcafe")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
        
        if (this.entry == null) { // null when create
            this.addBtn = createButton(parent, IDialogConstants.OK_ID, AppProjectConf.I18N.getString("UrlConfiguratorDialog.AddUrl"), true); //$NON-NLS-1$
            this.addBtn.setEnabled(this.isLocalProject);
        } else {
            this.editBtn = createButton(parent, IDialogConstants.OK_ID, AppProjectConf.I18N.getString("UrlConfiguratorDialog.EditUrl"), true); //$NON-NLS-1$
            this.editBtn.setEnabled(this.isLocalProject);
        }
    }

    @objid ("529118d1-9105-4767-bb0b-8206edacbfa1")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        getShell().setText(AppProjectConf.I18N.getString("UrlConfiguratorDialog.ShellText"));
        getShell().setSize(600, 300);
        getShell().setMinimumSize(600, 300);
        if (this.entry == null) {
            setTitle(AppProjectConf.I18N.getString("UrlConfiguratorDialog.AddTitle")); //$NON-NLS-1$
            setMessage(AppProjectConf.I18N.getString("UrlConfiguratorDialog.AddMessage")); //$NON-NLS-1$
        } else {
            setTitle(AppProjectConf.I18N.getString("UrlConfiguratorDialog.EditTitle")); //$NON-NLS-1$
            setMessage(AppProjectConf.I18N.getString("UrlConfiguratorDialog.EditMessage")); //$NON-NLS-1$
        }
    }

    @objid ("79eaaa7c-5921-48b9-a6fc-a1494e0a8894")
    @Override
    protected void okPressed() {
        AppProjectConf.LOG.debug("ok");
        if (this.entry == null) { // null in create mode, not null in edit null
            this.entry = new UrlEntry();
        }
        this.entry.name = UrlConfiguratorDialog.this.nameText.getText();
        this.entry.url = UrlConfiguratorDialog.this.urlText.getText();
        close();
        super.okPressed();
    }

    @objid ("d8c766e2-3ae6-403e-a9f0-3515de9c2370")
    @Override
    protected void cancelPressed() {
        this.entry = null;
        super.cancelPressed();
    }

    @objid ("170179a7-232b-4961-9e0b-b594f0c6594d")
    public UrlEntry getEntry() {
        return this.entry;
    }

    @objid ("eb7cf5f0-dff0-4ea6-a9be-e857f7805900")
    public Text getNameText() {
        return this.nameText;
    }

    @objid ("dff246c0-63a9-4ad0-bb29-f130848e3224")
    public Text getUrlText() {
        return this.urlText;
    }

    @objid ("6deca4d4-68cf-4c79-a537-e900f76a39bf")
    @Override
    protected String getHelpId() {
        return AppProjectConf.I18N.getString("UrlConfiguratorDialog.HelpId");
    }

}
