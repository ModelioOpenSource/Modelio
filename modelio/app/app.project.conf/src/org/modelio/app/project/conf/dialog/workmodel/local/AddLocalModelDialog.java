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

package org.modelio.app.project.conf.dialog.workmodel.local;

import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;
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
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.ui.UIColor;
import org.modelio.ui.dialog.ModelioDialog;

/**
 * Dialog box used to instantiate a new local fragment descriptor to mount in a project.
 * <p>
 * Use {@link AddLocalModelDialog#getFragmentDescriptor()} to get the new fragment.
 * </p>
 */
@objid ("7d5f3ec9-3adc-11e2-916e-002564c97630")
public final class AddLocalModelDialog extends ModelioDialog {
    @objid ("c7bc2618-c1a0-4db2-a16f-5ad5f2bc6ad6")
     List<String> invalidIds;

    @objid ("7d5f3eca-3adc-11e2-916e-002564c97630")
    private FragmentDescriptor result;

    @objid ("7d5f3ecc-3adc-11e2-916e-002564c97630")
     Text fragmentIdText;

    @objid ("2c0bd4b1-2fe4-42fc-bef9-1ca962cb7632")
     Button addBtn;

    @objid ("60a8fa6e-0376-4781-a9df-74f155ac7a5b")
    private static final Pattern NAME_PATTERN = Pattern.compile("[\\p{L}\\p{N}\\._ ]+");

    @objid ("7d5f3ed1-3adc-11e2-916e-002564c97630")
    public AddLocalModelDialog(Shell parentShell, List<String> allFragmentsIds) {
        super(parentShell);
        this.invalidIds = allFragmentsIds;
    }

    @objid ("7d5f3ed4-3adc-11e2-916e-002564c97630")
    @Override
    public Control createContentArea(Composite parent) {
        // fragment data area
        final Composite data = new Composite(parent, 0);
        data.setLayout(new GridLayout(3, false));
        data.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // fragment name
        Label label = new Label(data, SWT.NULL);
        label.setText(AppProjectConf.I18N.getString("AddLocalModelDialog.FragmentId")); //$NON-NLS-1$
        
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
        fragmentDescription.setText(AppProjectConf.I18N.getString("AddLocalModelDialog.Description")); //$NON-NLS-1$
        fragmentDescription.setForeground(UIColor.LABEL_TIP_FG);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        fragmentDescription.setLayoutData(gd);
        return data;
    }

    @objid ("7d5f3eda-3adc-11e2-916e-002564c97630")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, Window.CANCEL, IDialogConstants.CANCEL_LABEL, true);
        this.addBtn = createButton(parent, Window.OK, AppProjectConf.I18N.getString("AddLocalModelDialog.AddFragment"), true); //$NON-NLS-1$
        this.addBtn.setEnabled(false);
    }

    @objid ("7d5f3ede-3adc-11e2-916e-002564c97630")
    @Override
    public void init() {
        getShell().setText(AppProjectConf.I18N.getString("AddLocalModelDialog.ShellTitle")); //$NON-NLS-1$
        setTitle(AppProjectConf.I18N.getString("AddLocalModelDialog.Title")); //$NON-NLS-1$
        setMessage(AppProjectConf.I18N.getString("AddLocalModelDialog.Message")); //$NON-NLS-1$
    }

    @objid ("7d5f3ee7-3adc-11e2-916e-002564c97630")
    @Override
    protected void okPressed() {
        this.result = new FragmentDescriptor();
        this.result.setId(this.fragmentIdText.getText());
        this.result.setScope(DefinitionScope.LOCAL);
        this.result.setType(FragmentType.EXML);
        
        try {
            updateFragmentModel(this.result);
        } catch (Exception e) {
            // Display and log the message, and prevent dialog from closing.
            AppProjectConf.LOG.error(e);
            setErrorMessage(e.getLocalizedMessage());
            return;
        }
        
        super.okPressed();
    }

    @objid ("7d61a029-3adc-11e2-916e-002564c97630")
    public FragmentDescriptor getFragmentDescriptor() {
        return this.result;
    }

    @objid ("102c65ce-3e33-11e2-b901-002564c97630")
    public void updateFragmentModel(FragmentDescriptor fragmentDescriptor) {
        fragmentDescriptor.setType(FragmentType.EXML);
        fragmentDescriptor.setProperties(new GProperties());
        fragmentDescriptor.setUri((URI) null);
    }

    @objid ("568cc0f3-9609-4e7b-bc0a-dd52e5a81fb2")
    protected void isFragmentIdValid() {
        String fragmentId = this.fragmentIdText.getText();
        this.addBtn.setEnabled(false);
        this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_RED));
        if (this.invalidIds.contains(fragmentId)) {
            setErrorMessage(AppProjectConf.I18N.getMessage("AddLocalModelDialog.ErrorMessage.ExistAlready", fragmentId));
        } else if (fragmentId.isEmpty()) {
            // Do nothing
        } else if (!AddLocalModelDialog.NAME_PATTERN.matcher(fragmentId).matches()) {
            setErrorMessage(AppProjectConf.I18N.getMessage("AddLocalModelDialog.ErrorMessage.InvalidCharacters", fragmentId));
        } else if (!fragmentId.isEmpty()) {
            setErrorMessage(null);
            this.addBtn.setEnabled(true);
            this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        }
    }

    @objid ("9e2fecec-22f1-4757-8d7b-988bc754e6d7")
    @Override
    protected String getHelpId() {
        return AppProjectConf.I18N.getMessage("AddLocalModelDialog.HelpId");
    }

}
