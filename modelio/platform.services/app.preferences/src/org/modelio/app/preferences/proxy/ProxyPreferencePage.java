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

package org.modelio.app.preferences.proxy;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.internal.net.ProxySelector;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.internal.net.NetUIMessages;

@objid ("dd2265ce-c382-4fd0-be46-91b694651cf4")
public class ProxyPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
    @objid ("154e84d0-cc49-4eae-89cf-fa30e5ba6053")
    private Label providerLabel;

    @objid ("8f534ed8-44ea-434a-9d1f-44fa6b43eb38")
    protected Combo providerCombo;

    @objid ("f6de1bf3-58c5-4ac4-8505-7bf1e4a8a5f2")
    private ProxyEntriesComposite proxyEntriesComposite;

    @objid ("02c1d1f5-87fd-4533-abba-6fad452e7647")
    private NonProxyHostsComposite nonProxyHostsComposite;

    @objid ("ffc50ae8-5127-43e6-b47b-4e94e35ab739")
    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        
        createProviderComposite(composite);
        createProxyEntriesComposite(composite);
        createNonProxiedHostsComposite(composite);
        
        applyDialogFont(composite);
        initializeValues();
        return composite;
    }

    @objid ("839efbe9-c180-49fa-bf0e-63ddeaa0177b")
    private void createProviderComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        this.providerLabel = new Label(composite, SWT.NONE);
        this.providerLabel.setText(NetUIMessages.ProxyPreferencePage_0);
        this.providerCombo = new Combo(composite, SWT.READ_ONLY | SWT.DROP_DOWN);
        this.providerCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setProvider(ProxySelector.unlocalizeProvider(ProxyPreferencePage.this.providerCombo.getText()));
            }
        });
    }

    @objid ("5709218f-98af-4c81-962f-45fddfad5274")
    private void createProxyEntriesComposite(Composite parent) {
        this.proxyEntriesComposite = new ProxyEntriesComposite(parent, SWT.NONE);
        this.proxyEntriesComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
                true, true));
    }

    @objid ("f27b6e4e-2235-4d9d-ba8b-991c06e7ab25")
    private void createNonProxiedHostsComposite(Composite parent) {
        this.nonProxyHostsComposite = new NonProxyHostsComposite(parent, SWT.NONE);
        this.nonProxyHostsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
                true, true));
    }

    @objid ("f3865ce2-5669-4fea-bf2a-03a17321c7f1")
    @Override
    public void init(IWorkbench workbench) {
        // Nothing to do
    }

    @objid ("6bd5dcf4-6336-4611-850b-0428f3e822d3")
    @Override
    protected void performApply() {
        if (getControl() != null) {
            refresh();
            int sel = this.providerCombo.getSelectionIndex();
            this.proxyEntriesComposite.performApply();
            this.nonProxyHostsComposite.performApply();
            ProxySelector.setActiveProvider(ProxySelector
                    .unlocalizeProvider(this.providerCombo.getItem(sel)));
        }
    }

    @objid ("09f65310-5fd6-4555-8079-d1b33652c996")
    @Override
    protected void performDefaults() {
        int index = 1;
        if (this.providerCombo.getItemCount() == 3) {
            index = 2;
        }
        this.providerCombo.select(index);
        setProvider(ProxySelector.unlocalizeProvider(this.providerCombo.getItem(index)));
    }

    @objid ("11a6fe2d-dce4-410b-8001-c225dfd98139")
    @Override
    public boolean performOk() {
        performApply();
        return super.performOk();
    }

    @objid ("d7637fcc-968e-48b8-9aca-fcc7aba98386")
    private void initializeValues() {
        String[] providers = ProxySelector.getProviders();
        String[] localizedProviders = new String[providers.length];
        for (int i = 0; i < localizedProviders.length; i++) {
            localizedProviders[i] = ProxySelector.localizeProvider(providers[i]);
        }
        this.providerCombo.setItems(localizedProviders);
        this.providerCombo.select(this.providerCombo.indexOf(ProxySelector
                .localizeProvider(ProxySelector.getDefaultProvider())));
    }

    @objid ("ac4a419f-75de-449e-937d-2b0651c1270d")
    protected void setProvider(String name) {
        this.proxyEntriesComposite.setProvider(name);
        this.nonProxyHostsComposite.setProvider(name);
        refresh();
    }

    @objid ("bde6ad9a-c778-443c-8b76-133387101c2c")
    private void refresh() {
        this.proxyEntriesComposite.refresh();
        this.nonProxyHostsComposite.refresh();
    }

}
