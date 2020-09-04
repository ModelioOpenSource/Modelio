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

package org.modelio.app.preferences.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.internal.net.ProxySelector;
import org.eclipse.core.internal.net.StringUtil;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.internal.net.NetUIMessages;
import org.eclipse.ui.internal.net.NonProxyHostsContentProvider;
import org.eclipse.ui.internal.net.NonProxyHostsLabelProvider;
import org.eclipse.ui.internal.net.ProxyBypassData;

/**
 * This class is the Composite that consists of controls for proxy bypass hosts
 * and is used by ProxyPreferencesPage.
 */
@objid ("9d2174a6-0c20-4894-808e-8bcbbb777349")
public class NonProxyHostsComposite extends Composite {
    @objid ("98093656-88e5-4a04-849a-b39fd720a490")
    protected String currentProvider;

    @objid ("9d8f9852-2c13-491e-b2fa-a94424cbe30e")
    private Label hostsLabel;

    @objid ("e37e6dd8-a519-4d9b-8bb6-f8fc15e9ebba")
     CheckboxTableViewer hostsViewer;

    @objid ("111d3ef7-699a-433e-954d-ec75dc629736")
    private Button addButton;

    @objid ("b22f905e-4b03-460b-b400-0781e3cac434")
    private Button editButton;

    @objid ("1ab6786c-85d2-43d9-82cf-64f74ef423cc")
    private Button removeButton;

    @objid ("b68fc2ed-9dd8-4df6-b77b-ba2f5f1f128c")
    private List<ProxyBypassData> bypassHosts = new ArrayList<>();

    @objid ("7d149346-33b5-4ba9-aa08-a4d9b3355557")
    NonProxyHostsComposite(Composite parent, int style) {
        super(parent, style);
        createWidgets();
    }

    @objid ("48f2a019-df30-4484-9b3c-c4bd68c5d6cb")
    protected void createWidgets() {
        setLayout(new GridLayout(2, false));
        
        this.hostsLabel = new Label(this, SWT.NONE);
        this.hostsLabel.setText(NetUIMessages.ProxyPreferencePage_12);
        this.hostsLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false,
                2, 1));
        
        Table hostsTable = new Table(this, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK);
        hostsTable.setHeaderVisible(true);
        hostsTable.setLinesVisible(true);
        hostsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
                1, 3));
        
        this.hostsViewer = new CheckboxTableViewer(hostsTable);
        NonProxyHostsLabelProvider labelProvider = new NonProxyHostsLabelProvider();
        NonProxyHostsContentProvider contentProvider = new NonProxyHostsContentProvider();
        labelProvider.createColumns(this.hostsViewer);
        this.hostsViewer.setContentProvider(contentProvider);
        this.hostsViewer.setLabelProvider(labelProvider);
        
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnPixelData(24));
        tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
        tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
        hostsTable.setLayout(tableLayout);
        
        this.addButton = createButton(NetUIMessages.ProxyPreferencePage_15);
        this.editButton = createButton(NetUIMessages.ProxyPreferencePage_16);
        this.removeButton = createButton(NetUIMessages.ProxyPreferencePage_17);
        
        this.hostsViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent event) {
                        enableButtons();
                    }
                });
        this.hostsViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                setProvider(NonProxyHostsComposite.this.currentProvider);
            }
        });
        this.hostsViewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                editSelection();
            }
        });
        this.addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addHost();
            }
        });
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

    @objid ("13f03420-5f14-4299-9ac5-59759cca506a")
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.hostsViewer.getTable().setEnabled(enabled);
        enableButtons();
    }

    @objid ("945d5033-b3e1-48fb-b9d8-2a3d0f207382")
    protected void enableButtons() {
        boolean enabled = getEnabled();
        if (enabled) {
            boolean editable = isSelectionEditable();
            this.addButton.setEnabled(true);
            this.editButton.setEnabled(editable);
            this.removeButton.setEnabled(editable);
        } else {
            this.addButton.setEnabled(false);
            this.editButton.setEnabled(false);
            this.removeButton.setEnabled(false);
        }
    }

    @objid ("ea0af4b7-75a1-4693-8f78-f1467cb4541b")
    private boolean isSelectionEditable() {
        IStructuredSelection selection = (IStructuredSelection) this.hostsViewer
                .getSelection();
        Iterator iterator = selection.iterator();
        boolean editable = iterator.hasNext();
        while (iterator.hasNext()) {
            String provider = ((ProxyBypassData) iterator.next()).getSource();
            if (!ProxySelector.canSetBypassHosts(provider)) {
                editable = false;
            }
        }
        return editable;
    }

    @objid ("223645e4-4bb0-49c9-8292-9a2e35baf2eb")
    protected void addHost() {
        String hosts[] = promptForHost(null);
        if (hosts != null) {
            for (int i = 0; i < hosts.length; i++) {
                this.bypassHosts.add(0, new ProxyBypassData(hosts[i],
                        getEditableProvider()));
            }
            this.hostsViewer.refresh();
            setProvider(this.currentProvider);
        }
    }

    @objid ("33a357ba-f7b8-4dff-8893-104b64cae66b")
    private String getEditableProvider() {
        String providers[] = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            if (ProxySelector.canSetBypassHosts(providers[i])) {
                return providers[i];
            }
        }
        return null;
    }

    @objid ("dbcb8f74-10fa-430d-8f3f-8fb461a7948e")
    protected void removeSelection() {
        IStructuredSelection selection = (IStructuredSelection) this.hostsViewer
                .getSelection();
        Iterator it = selection.iterator();
        while (it.hasNext()) {
            ProxyBypassData data = (ProxyBypassData) it.next();
            this.bypassHosts.remove(data);
        }
        this.hostsViewer.refresh();
    }

    @objid ("53ab0ea9-9985-4d16-858d-33e4fe8a68a7")
    protected void editSelection() {
        if (!isSelectionEditable()) {
            return;
        }
        IStructuredSelection selection = (IStructuredSelection) this.hostsViewer
                .getSelection();
        String selectedHosts = getStringList(selection.iterator());
        String hosts[] = promptForHost(selectedHosts);
        if (hosts != null) {
            Object[] selectedItems = selection.toArray();
            for (int i = 0; i < selectedItems.length; i++) {
                ProxyBypassData data = (ProxyBypassData) selectedItems[i];
                data.setHost(hosts[i]);
            }
            this.hostsViewer.refresh();
        }
    }

    @objid ("4146a1ff-44fc-4d25-89e5-8adef43820ac")
    String getStringList(Iterator iterator) {
        StringBuffer buffer = new StringBuffer();
        if (iterator.hasNext()) {
            ProxyBypassData data = (ProxyBypassData) iterator.next();
            buffer.append(data.getHost());
        }
        while (iterator.hasNext()) {
            buffer.append(';');
            ProxyBypassData data = (ProxyBypassData) iterator.next();
            buffer.append(data.getHost());
        }
        return buffer.toString();
    }

    @objid ("62f16289-20e0-4b39-9869-63a05b3bda08")
    private String[] promptForHost(String selectedHosts) {
        InputDialog dialog = new InputDialog(getShell(),
                NetUIMessages.ProxyBypassDialog_0,
                NetUIMessages.ProxyBypassDialog_1, selectedHosts, null) {
            private ControlDecoration decorator;
        
            @Override
            protected Control createDialogArea(Composite parent) {
                Control createDialogArea = super.createDialogArea(parent);
                this.decorator = new ControlDecoration(getText(), SWT.TOP | SWT.LEFT);
                this.decorator.setDescriptionText(NetUIMessages.ProxyBypassDialog_2);
                this.decorator.setImage(FieldDecorationRegistry.getDefault()
                        .getFieldDecoration(
                                FieldDecorationRegistry.DEC_INFORMATION)
                        .getImage());
                return createDialogArea;
            }
        
            @Override
            public boolean close() {
                this.decorator.dispose();
                return super.close();
            }
        };
        int result = dialog.open();
        if (result != Window.CANCEL) {
            String value = dialog.getValue();
            String hosts[] = StringUtil.split(value, new String[] { ";", "|" }); //$NON-NLS-1$ //$NON-NLS-2$
            ArrayList<String> filtered = new ArrayList<>();
            for (int i = 0; i < hosts.length; i++) {
                if (hosts[i].length() != 0) {
                    filtered.add(hosts[i]);
                }
            }
            return filtered.toArray(new String[0]);
        }
        return null;
    }

    @objid ("4293920e-48ec-44f3-a77a-ca712a41a392")
    private Button createButton(String message) {
        Button button = new Button(this, SWT.PUSH);
        button.setText(message);
        button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
        return button;
    }

    @objid ("44902035-a82c-4c3f-af91-2512c9fa8338")
    public void initializeValues() {
        String providers[] = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            this.bypassHosts.addAll(getProxyBypassData(providers[i]));
        }
        this.hostsViewer.setInput(this.bypassHosts);
        setProvider(ProxySelector.getDefaultProvider());
    }

    @objid ("2333234c-bd5d-42c4-be69-fafbdad82871")
    public void setProvider(String item) {
        if (item == null) {
            item = this.currentProvider;
        } else {
            this.currentProvider = item;
        }
        ArrayList<ProxyBypassData> selected = new ArrayList<>();
        Iterator<ProxyBypassData> it = this.bypassHosts.iterator();
        while (it.hasNext()) {
            ProxyBypassData data = it.next();
            if (data.getSource().equalsIgnoreCase(item)) {
                selected.add(data);
            }
        }
        this.hostsViewer
                .setCheckedElements(selected.toArray(new ProxyBypassData[0]));
    }

    @objid ("dfa509c1-688d-45ba-8aff-60d4e3c87878")
    public void performApply() {
        String provider = getEditableProvider();
        Iterator<ProxyBypassData> it = this.bypassHosts.iterator();
        ArrayList<String> hosts = new ArrayList<>();
        while (it.hasNext()) {
            ProxyBypassData data = it.next();
            if (data.getSource().equals(provider)) {
                hosts.add(data.getHost());
            }
        }
        String data[] = hosts.toArray(new String[0]);
        ProxySelector.setBypassHosts(provider, data);
    }

    @objid ("ab8722a6-3b9f-48b3-85e4-daeaacb1490f")
    public void refresh() {
        ArrayList<ProxyBypassData> natives = new ArrayList<>();
        String provider = getEditableProvider();
        Iterator<ProxyBypassData> it = this.bypassHosts.iterator();
        while (it.hasNext()) {
            ProxyBypassData data = it.next();
            if (!data.getSource().equals(provider)) {
                natives.add(data);
            }
        }        
        this.bypassHosts.removeAll(natives);
        String providers[] = ProxySelector.getProviders();
        for (int i = 0; i < providers.length; i++) {
            if (!providers[i].equals(provider)) {
                this.bypassHosts.addAll(getProxyBypassData(providers[i]));
            }
        }
        this.hostsViewer.refresh();
        setProvider(this.currentProvider);
    }

    @objid ("fe4b117b-8809-4c54-bca9-309b629e953f")
    private List<ProxyBypassData> getProxyBypassData(String provider) {
        List<ProxyBypassData> bypassProxyData = new ArrayList<>();
        String[] hosts = ProxySelector.getBypassHosts(provider);
        for (int j = 0; hosts != null && j < hosts.length; j++) {
            ProxyBypassData data = new ProxyBypassData(hosts[j], provider);
            bypassProxyData.add(data);
        }
        return bypassProxyData;
    }

}
