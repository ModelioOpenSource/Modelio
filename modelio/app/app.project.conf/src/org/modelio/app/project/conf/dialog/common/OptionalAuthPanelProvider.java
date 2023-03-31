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
package org.modelio.app.project.conf.dialog.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.data.project.auth.InheritedAuthData;
import org.modelio.platform.model.ui.dialogs.auth.AuthDataPanel;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

/**
 * Panel that gives the user possibility to specify or not authentication data.
 */
@objid ("a14c2936-78fe-4a7f-8fae-23e2679bfdf6")
public class OptionalAuthPanelProvider implements IPanelProvider {
    @objid ("c56bdd4c-386e-4644-8087-0f0e46599ca2")
    private boolean allowUseProjectAuth;

    @objid ("0b8ce19e-bcee-44ff-8d24-dafeb6b5b320")
    private AuthDataPanel authPanel;

    @objid ("2c703b2a-09c5-405a-943b-6a107e747188")
    private Composite top;

    @objid ("fc186fe4-07d0-4301-af24-ff2376f1feda")
    private Button useProjectAuthCheck;

    @objid ("7f5af381-e38f-48c3-a9ee-4f0072384b43")
    private IAuthData savedInput;

    /**
     * Instantiate the panel graphical elements.
     * @param parent the composite to create the new graphical elements into.
     * @return the created panel.
     */
    @objid ("663d7ffb-a108-41c9-8471-9c15df1eb20c")
    @Override
    public Object createPanel(Composite parent) {
        assert (this.top == null);
        
        this.top = new Composite(parent, SWT.NONE);
        this.top.setLayout(new GridLayout(1, false));
        
        //Label label = new Label(this.top, SWT.NONE);
        this.useProjectAuthCheck = new Button(this.top, SWT.CHECK);
        this.useProjectAuthCheck.setText(AppProjectConf.I18N.getString("OptionalAuthPanelProvider.UseProjectAuth"));
        this.useProjectAuthCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateVisibility();
            }
        });
        this.useProjectAuthCheck.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        this.authPanel.createPanel(this.top);
        this.authPanel.getPanel().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return this;
    }

    /**
     * Get the panel graphical elements, a {@link Composite}.
     * @return the created panel. <code>null</code> until IPanelProvided#create is called.
     */
    @objid ("995d638b-4fe1-45f3-bd8a-b1ed2996ce59")
    @Override
    public Composite getPanel() {
        return this.top;
    }

    /**
     * Set a new input for the panel.
     * @param input the new input for the panel.
     */
    @objid ("fddd87ee-1335-4b32-9e43-6b2716ed32cd")
    @Override
    public void setInput(Object input) {
        IAuthData data = (IAuthData) input;
        
        if (data == null) {
            data = new UserPasswordAuthData();
        } else if (InheritedAuthData.matches(data)) {
            this.useProjectAuthCheck.setSelection(true);
        } else {
            this.savedInput = data;
            this.useProjectAuthCheck.setSelection(false);
            this.authPanel.setInput(data);
        }
        updateVisibility();
        
    }

    /**
     * Get the current input of the panel.
     * @return the panel's input.
     */
    @objid ("0090f1a8-1229-4dc8-b943-e38da86b37f2")
    @Override
    public IAuthData getInput() {
        return this.authPanel.getInput();
    }

    /**
     * Initialize the panel.
     * @param allowUseProjectAuth Allow "Use project authentication"
     */
    @objid ("a5f50cbb-760e-469e-b866-f9b7c2004e32")
    public  OptionalAuthPanelProvider(boolean allowUseProjectAuth) {
        this.authPanel = new AuthDataPanel();
        this.allowUseProjectAuth = allowUseProjectAuth;
        
    }

    @objid ("5350938d-ef80-41ff-b543-520baf4be470")
    void updateVisibility() {
        Composite composite = this.authPanel.getPanel();
        setWidgetVisible(this.useProjectAuthCheck, this.allowUseProjectAuth);
        
        if (this.allowUseProjectAuth && this.useProjectAuthCheck.getSelection()) {
            setWidgetVisible(composite, false);
        } else {
            setWidgetVisible(composite, true);
            if (getInput() == null) {
                if (this.savedInput == null)
                    this.savedInput = new NoneAuthData();
                setInput(this.savedInput);
            }
        }
        this.top.getShell().layout(new Control[]{composite, this.top});
        
    }

    /**
     * Update the given fragment descriptor with the data of this panel.
     * @param fragmentDescriptor the fragment to update.
     */
    @objid ("143fc66d-8989-4f2e-9ad2-efbc6c4038f7")
    public void updateFragmentDescriptor(GProjectPartDescriptor fragmentDescriptor) {
        IAuthData newData = getInput();
        if (newData == null) {
            if (this.allowUseProjectAuth) {
                fragmentDescriptor.setAuth(new AuthDescriptor(new InheritedAuthData(), DefinitionScope.LOCAL));
            } else {
                fragmentDescriptor.setAuth(new AuthDescriptor(new NoneAuthData(), DefinitionScope.LOCAL));
            }
        } else {
            AuthDescriptor authDesc = fragmentDescriptor.getAuth();
            DefinitionScope authScope = authDesc==null ? DefinitionScope.LOCAL : authDesc.getScope();
            fragmentDescriptor.setAuth(new AuthDescriptor(newData, authScope));
        }
        
    }

    /**
     * Enable or disable edition on the panel.
     * @param b <code>true</code> to enable edition, <code>false</code> to disable it.
     */
    @objid ("cc956ea7-dd23-4cca-9afa-20540fb51698")
    public void setEnabled(boolean b) {
        this.top.setEnabled(b);
    }

    @objid ("c7ac7c99-f44f-48eb-b9c3-3bb106b465f2")
    void setWidgetVisible(Control composite, boolean visible) {
        composite.setVisible(visible);
        ((GridData)composite.getLayoutData()).exclude = !visible;
        
    }

    @objid ("13881cf4-7a13-4e92-b9fe-4156a065f393")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("da23b802-2888-4d46-81de-6577a1cd7abc")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("0d0a506c-25d4-48da-8fe3-52753a367ef2")
    @Override
    public void dispose() {
        // nothing to do
    }

}
