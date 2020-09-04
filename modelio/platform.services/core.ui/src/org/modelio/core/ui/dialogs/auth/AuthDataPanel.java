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

package org.modelio.core.ui.dialogs.auth;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.modelio.core.ui.dialogs.auth.ui.IAuthDataUi;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Authentication data panel provider.
 * <ul>
 * <li> {@link #createPanel(Composite)} returns itself.<br>
 * Assumes the parent composite uses a {@link GridLayout} layout.
 * <li> Uses a {@link IAuthData} as input.
 * </ul>
 */
@objid ("e1e54a1c-e853-4a20-a485-5072b8d6065c")
public class AuthDataPanel implements IPanelProvider {
    /**
     * A map that associates Scheme and their UIs
     */
    @objid ("04b5e34c-5bba-444f-80bf-e2d559b4423c")
    private final Map<Class<? extends IAuthData>, IAuthDataUi> stackedPanels = new HashMap<>();

    /**
     * The currently displayed ui
     */
    @objid ("20a6f1dd-0af9-4994-8de9-8b7cfc62e6b1")
    private IAuthDataUi currentUi;

    @objid ("325f6556-8057-482e-b450-0469a5e19d4e")
    private AuthDataPanelController controller;

    @objid ("88d90bbd-9fab-490a-81b9-ca201acaf508")
    private IAuthData authData;

    /**
     * Top control of the panel
     */
    @objid ("52c0de6a-f132-4bb4-8561-da65f6771c24")
    private Composite top;

    /**
     * The combo viewer to select a scheme
     */
    @objid ("7800cd64-064f-4bcd-bdb8-a75c077c2332")
    private ComboViewer schemeSelector;

    /**
     * The composite stacking the different scheme UIs
     */
    @objid ("94173805-c060-4191-a0cd-87ef6fa235e4")
    private Composite stack;

    /**
     * The stack layout for the composite stacking the different scheme UIs
     */
    @objid ("29511727-ad9a-464d-9278-31c848b5819f")
    private StackLayout stackLayout;

    @objid ("1a4ea7b4-e1c5-4104-88b0-73eeae7ca077")
    private Composite dataPanel;

    @objid ("5f6e529a-4eb1-4ad7-9d50-eba9d59d4c92")
    @Override
    public AuthDataPanel createPanel(Composite parent) {
        // top level container
        this.top = new Composite(parent, SWT.NONE);
        this.top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.top.setLayout(new FormLayout());
        
        // Label
        Label label = new Label(this.top, SWT.NONE);
        label.setText(CoreUi.I18N.getString("AuthDataPanel.scheme.label"));
        FormData fd = null;
        fd = new FormData();
        fd.top = new FormAttachment(0, 8);
        fd.left = new FormAttachment(0, 8);
        label.setLayoutData(fd);
        
        // List of available schemes
        Combo combo = new Combo(this.top, SWT.READ_ONLY);
        this.schemeSelector = new ComboViewer(combo);
        
        //Control combo = this.schemeSelector.getControl();
        fd = new FormData();
        fd.top = new FormAttachment(label, 0, SWT.CENTER);
        fd.left = new FormAttachment(label, 8);
        fd.right = new FormAttachment(100, -8);
        combo.setLayoutData(fd);
        
        this.schemeSelector.setContentProvider(new ArrayContentProvider());
        this.schemeSelector.setInput(AuthDataUiFactory.getAllSchemes().toArray());
        this.schemeSelector.addSelectionChangedListener(new ISelectionChangedListener() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                AuthDataPanel.this.controller.onSchemeChanged(event.getSelection());
            }
        });
        this.schemeSelector.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return CoreUi.I18N.getString("$AuthDataPanel.scheme." + ((Class<?>)element).getSimpleName());
            }
        });
        
        // Variable stack
        this.dataPanel = createDataPanel(this.top);
        fd = new FormData();
        fd.top = new FormAttachment(combo, 4);
        fd.left = new FormAttachment(0, 0);
        fd.bottom = new FormAttachment(100, 0);
        fd.right = new FormAttachment(100, 0);
        this.dataPanel.setLayoutData(fd);
        
        this.controller = new AuthDataPanelController(this);
        return this;
    }

    @objid ("c5a62efb-1c38-43e3-9d0f-9b70c6c1f802")
    @Override
    public Composite getPanel() {
        return this.top;
    }

    @objid ("058c380b-9012-4860-a295-4a745539364f")
    @Override
    public IAuthData getInput() {
        if (this.authData != null)
            this.currentUi.collect(this.authData);
        return this.authData;
    }

    /**
     * Set the input. Uses a {@link IAuthData}.
     */
    @objid ("39e82d37-3b9b-414d-b48f-0e10e2746e67")
    @Override
    public void setInput(Object input) {
        this.authData = (IAuthData) input;
        if (this.authData != null)
            this.schemeSelector.setSelection(new StructuredSelection(this.authData.getClass()));
    }

    @objid ("3d941b3a-7bc8-45db-9eba-a44a48a7eb9f")
    protected void selectScheme(Class<? extends IAuthData> scheme) {
        IAuthDataUi ui = this.stackedPanels.get(scheme);
        
        if (ui != null) {
            this.stackLayout.topControl = ui.getTopComposite();
            this.stack.layout();
        
            // reuse and display as much data as possible
            if (this.authData != null)
                ui.show(this.authData);
        
            // instantiate data
            try {
                this.authData = scheme.newInstance();
                ui.collect(this.authData);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            this.currentUi = ui;
        }
    }

    @objid ("bae44df8-81ad-4ba6-a04c-1c53bb339117")
    private Composite createDataPanel(final Composite parent) {
        GridData gd;
        final Composite data = new Composite(parent, SWT.NONE);
        data.setLayout(new GridLayout(2, false));
        
        // fragment type-specific panel
        this.stack = new Composite(data, SWT.NONE);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        this.stack.setLayoutData(gd);
        
        this.stackLayout = new StackLayout();
        this.stack.setLayout(this.stackLayout);
        
        for (Class<? extends IAuthData> type : AuthDataUiFactory.getAllSchemes()) {
            IAuthDataUi panel = AuthDataUiFactory.createPanel(this.stack, type);
            if (panel != null) {
                this.stackedPanels.put(type, panel);
            }
        }
        return data;
    }

    @objid ("9a2e970a-a2f4-40e5-bb0e-669381a2b030")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("e8a72530-d6f3-4909-8426-90d87b4a814d")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("92f136eb-9b07-40e4-a340-204f74ea7533")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("8184be64-0f5f-4f4e-bf16-4d42b44c2539")
    private static class AuthDataPanelController {
        @objid ("17372e75-8d0a-4960-989f-202dc0562abd")
        private AuthDataPanel panel;

        @objid ("9027602d-8e71-4383-b347-98e540074f35")
        public AuthDataPanelController(AuthDataPanel authDataPanel) {
            this.panel = authDataPanel;
        }

        /**
         * Called when the user selects another scheme
         * 
         * @param selection selected scheme
         */
        @objid ("f33eb41e-6157-423d-9ec6-e5f38e9abb96")
        public void onSchemeChanged(ISelection selection) {
            if (selection instanceof IStructuredSelection) {
                @SuppressWarnings("unchecked")
                final Class<? extends IAuthData> scheme = (Class<? extends IAuthData>) ((IStructuredSelection) selection)
                        .getFirstElement();
                this.panel.selectScheme(scheme);
            }
        }

    }

}
