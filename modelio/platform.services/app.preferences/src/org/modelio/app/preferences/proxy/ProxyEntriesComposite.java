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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.internal.net.ProxyData;
import org.eclipse.core.internal.net.ProxySelector;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.internal.net.NetUIMessages;
import org.eclipse.ui.internal.net.ProxyEntriesContentProvider;
import org.eclipse.ui.internal.net.ProxyEntriesLabelProvider;
import org.eclipse.ui.internal.net.ProxyEntryDialog;

/**
 * This class is the Composite that consists of the controls for proxy entries
 * and is used by ProxyPreferencesPage.
 */
@objid ("5cd41d8a-4d9e-4d5d-87e3-0b5a43479d7a")
public class ProxyEntriesComposite extends Composite {
    @objid ("5a5e1cce-1cd9-478d-826c-9c23c7b97a86")
    protected String currentProvider;

    @objid ("3b4789bd-c79f-466f-a981-c09e376b310b")
    private Label entriesLabel;

    @objid ("8b8662b8-ed2b-404c-ae0c-2fa6caf105a3")
    private CheckboxTableViewer entriesViewer;

// private Button addButton;
    @objid ("ba0395a7-5970-459c-8eb3-e0156d999f66")
    private Button editButton;

    @objid ("dc8c45ea-a3e4-457e-96da-92116bf0f1c2")
    private Button removeButton;

    @objid ("629e510c-32f9-4d50-9be6-d819110c3d61")
    private List<ProxyData> proxyEntries = new ArrayList<>();

    @objid ("86d31e4a-cdb0-4a7c-afab-bedb61b68288")
    ProxyEntriesComposite(Composite parent, int style) {
        super(parent, style);
        createWidgets();
    }

    @objid ("cae687a8-b4e3-4deb-ac1e-783eb07da32d")
    protected void createWidgets() {
        setLayout(new GridLayout(2, false));
        
        this.entriesLabel = new Label(this, SWT.NONE);
        this.entriesLabel.setText(NetUIMessages.ProxyPreferencePage_1);
        this.entriesLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
                false, false, 2, 1));
        
        Table entriesTable = new Table(this, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK);
        entriesTable.setHeaderVisible(true);
        entriesTable.setLinesVisible(true);
        entriesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
                1, 3));
        
        this.entriesViewer = new CheckboxTableViewer(entriesTable);
        ProxyEntriesLabelProvider labelProvider = new ProxyEntriesLabelProvider();
        ProxyEntriesContentProvider contentProvider = new ProxyEntriesContentProvider();
        labelProvider.createColumns(this.entriesViewer);
        this.entriesViewer.setContentProvider(contentProvider);
        this.entriesViewer.setLabelProvider(labelProvider);
        
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnPixelData(24));
        tableLayout.addColumnData(new ColumnWeightData(20, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(20, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(20, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(20, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
        
        entriesTable.setLayout(tableLayout);
        
        // addButton = createButton(NetUIMessages.ProxyPreferencePage_9);
        this.editButton = createButton(NetUIMessages.ProxyPreferencePage_10);
        this.removeButton = createButton(NetUIMessages.ProxyPreferencePage_11);
        
        this.entriesViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent event) {
                        enableButtons();
                    }
                });
        this.entriesViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                setProvider(ProxyEntriesComposite.this.currentProvider);
            }
        });
        this.entriesViewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                editSelection();
            }
        });
        // addButton.addSelectionListener(new SelectionAdapter() {
        // public void widgetSelected(SelectionEvent e) {
        // addEntry();
        // }
        // });
        this.editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editSelection();
            }
        });
        this.removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removeSelection();
            }
        });
        
        initializeValues();
        enableButtons();
    }

    @objid ("dfd17225-2a2d-47cc-aa9b-e866fb3856ec")
    protected void enableButtons() {
        boolean enabled = getEnabled();
        if (enabled) {
            // addButton.setEnabled(true);
            this.editButton.setEnabled(isSelectionEditable());
            this.removeButton.setEnabled(isSelectionRemovable());
        } else {
            // addButton.setEnabled(false);
            this.editButton.setEnabled(false);
            this.removeButton.setEnabled(false);
        }
    }

    @objid ("27b904d4-05de-4052-bd78-023972ddee6a")
    private boolean isSelectionEditable() {
        IStructuredSelection selection = (IStructuredSelection) this.entriesViewer
                .getSelection();
        return isSelectionRemovable() && selection.size() == 1;
    }

    @objid ("4cec801c-0ac1-467f-8f8f-8f886e7d05a5")
    private boolean isSelectionRemovable() {
        IStructuredSelection selection = (IStructuredSelection) this.entriesViewer
                .getSelection();
        Iterator iterator = selection.iterator();
        boolean editable = iterator.hasNext();
        while (iterator.hasNext()) {
            String provider = ((ProxyData) iterator.next()).getSource();
            if (!ProxySelector.canSetProxyData(provider)) {
                editable = false;
            }
        }
        return editable;
    }

    @objid ("52a224f5-9171-4620-91d8-4a2dcf24c292")
    protected void addEntry() {
        Iterator<ProxyData> it = this.proxyEntries.iterator();
        ArrayList<String> added = new ArrayList<>();
        String editableProvider = getEditableProvider();
        while (it.hasNext()) {
            ProxyData data = it.next();
            if (data.getSource().equalsIgnoreCase(editableProvider)) {
                added.add(data.getType());
            }
        }
        String addedArray[] = added.toArray(new String[0]);
        ProxyData data = promptForEntry(null, addedArray,
                NetUIMessages.ProxyEntryDialog_0);
        if (data != null) {
            data.setSource(editableProvider);
            this.proxyEntries.add(0, data);
            this.entriesViewer.refresh();
        }
    }

    @objid ("a07ac32d-e7d7-4168-bda0-5a006cea82d8")
    private String getEditableProvider() {
        String providers[] = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            if (ProxySelector.canSetProxyData(providers[i])) {
                return providers[i];
            }
        }
        return null;
    }

    @objid ("7cd66de6-7716-429f-8bc1-2abed689c010")
    private ProxyData promptForEntry(ProxyData entry, String[] addedArray, String title) {
        ProxyEntryDialog dialog = new ProxyEntryDialog(getShell(), entry,
                addedArray, title);
        int result = dialog.open();
        if (result != Window.CANCEL) {
            return dialog.getValue();
        }
        return null;
    }

    @objid ("894d05de-3488-439f-be45-1da53d9549dd")
    protected void editSelection() {
        if (!isSelectionRemovable()) {
            return;
        }
        Iterator itsel = ((IStructuredSelection) this.entriesViewer.getSelection()).iterator();
        ProxyData toEdit = null;
        if (itsel.hasNext()) {
            toEdit = ((ProxyData) itsel.next());
        } else {
            return;
        }
        Iterator<ProxyData> it = this.proxyEntries.iterator();
        ArrayList<String> added = new ArrayList<>();
        String editableProvider = getEditableProvider();
        while (it.hasNext()) {
            ProxyData data = it.next();
            if (data.getSource().equalsIgnoreCase(editableProvider)) {
                if (data.getType() != toEdit.getType()) {
                    added.add(data.getType());
                }
            }
        }
        String addedArray[] = added.toArray(new String[0]);
        ProxyData data = promptForEntry(toEdit, addedArray,
                NetUIMessages.ProxyEntryDialog_1);
        if (data != null) {
            this.entriesViewer.refresh();
        }
    }

    @objid ("9eccee93-f409-408a-bc33-7ef32f5974c2")
    protected void removeSelection() {
        IStructuredSelection selection = (IStructuredSelection) this.entriesViewer
                .getSelection();
        Iterator it = selection.iterator();
        while (it.hasNext()) {
            ProxyData data = (ProxyData) it.next();
            data.setHost(""); //$NON-NLS-1$
            data.setPort(-1);
            data.setUserid(null);
            data.setPassword(null);
        }
        this.entriesViewer.refresh();
    }

    @objid ("a13feb32-7e2a-4e84-be11-58a63013c5b2")
    private Button createButton(String message) {
        Button button = new Button(this, SWT.PUSH);
        button.setText(message);
        button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
        return button;
    }

    @objid ("cd38d89f-7946-4976-88ee-29acd628e137")
    public void initializeValues() {
        String providers[] = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            this.proxyEntries.addAll(getProxyData(providers[i]));
        }
        this.entriesViewer.setInput(this.proxyEntries);
        setProvider(ProxySelector.getDefaultProvider());
    }

    @objid ("0dadf171-489c-4f00-bb98-05f18f046f68")
    public void setProvider(String item) {
        if (item == null) {
            item = this.currentProvider;
        } else {
            this.currentProvider = item;
        }
        ArrayList<ProxyData> checked = new ArrayList<>();
        Iterator<ProxyData> it = this.proxyEntries.iterator();
        while (it.hasNext()) {
            ProxyData data = it.next();
            if (data.getSource().equalsIgnoreCase(item)) {
                checked.add(data);
            }
        }
        this.entriesViewer.setCheckedElements(checked.toArray(new ProxyData[0]));
    }

    @objid ("6edaa134-615b-460f-b7d2-c5fb2c458870")
    public void performApply() {
        String provider = getEditableProvider();
        Iterator<ProxyData> it = this.proxyEntries.iterator();
        ArrayList<ProxyData> proxies = new ArrayList<>();
        while (it.hasNext()) {
            ProxyData data = it.next();
            if (data.getSource().equals(provider)) {
                proxies.add(data);
            }
        }
        ProxyData data[] = proxies.toArray(new ProxyData[0]);
        ProxySelector.setProxyData(provider, data);
    }

    @objid ("92ca10e2-ceb0-407e-9285-8eaddb244a35")
    public void refresh() {
        String provider = getEditableProvider();    
        Iterator<ProxyData> it = this.proxyEntries.iterator();
        ArrayList<ProxyData> natives = new ArrayList<>();
        while (it.hasNext()) {
            ProxyData data = it.next();
            if (!data.getSource().equals(provider)) {
                natives.add(data);
            }
        }
        this.proxyEntries.removeAll(natives);
        String[] providers = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            if (!providers[i].equals(provider)) {
                this.proxyEntries.addAll(getProxyData(providers[i]));
            }
        }
        this.entriesViewer.refresh();
        setProvider(this.currentProvider);
    }

    @objid ("55abc228-ae4b-4ffd-8b90-2affdd667f50")
    private List<ProxyData> getProxyData(String provider) {
        List<ProxyData> proxyDatas = new ArrayList<>();
        ProxyData[] entries = ProxySelector.getProxyData(provider);
        for (int j = 0; j < entries.length; j++) {
            entries[j].setSource(provider);
            proxyDatas.add(entries[j]);
        }
        return proxyDatas;
    }

}
