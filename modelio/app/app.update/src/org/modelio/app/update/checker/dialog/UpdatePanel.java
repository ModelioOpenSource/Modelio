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

package org.modelio.app.update.checker.dialog;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.modelio.app.update.plugin.AppUpdate;
import org.modelio.app.update.repo.UpdateDescriptor;
import org.modelio.ui.UIImages;
import org.modelio.ui.panel.IPanelListener;
import org.modelio.ui.panel.IPanelProvider;

@objid ("8702429c-9706-4be4-8ffb-0141ec17dc94")
public class UpdatePanel implements IPanelProvider {
    @objid ("bc33f331-ac8f-44fd-acb4-b752220da21e")
    private final PanelController controller;

    @objid ("24c63740-28a4-4d61-83f2-ee52353c2f54")
    public UpdatePanel() {
        this.controller = new PanelController();
    }

    @objid ("f8987bb5-d5b3-4dd7-99f9-518c942308ec")
    @Override
    public boolean isRelevantFor(final Object obj) {
        return obj instanceof UpdatePanelDataModel;
    }

    @objid ("2ad7de31-61a3-4b28-92d5-989bf31b60dd")
    @Override
    public Control createPanel(final Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("d549873a-74cd-48d5-b8c7-e3d30cd27156")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("0fec92cb-7d2f-49aa-b63a-b18627e8c956")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("1239a498-3705-4390-b41e-bd92db255181")
    @Override
    public UpdatePanelDataModel getInput() {
        return this.controller.getData();
    }

    @objid ("e4fd9aab-327d-4a2e-a38c-d5c9347ba84d")
    @Override
    public void setInput(final Object input) {
        if (input instanceof UpdatePanelDataModel) {
            this.controller.setData((UpdatePanelDataModel) input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("0b16c9eb-6b42-4888-9a47-06eb1217fe03")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("5212d912-1427-4a82-9cb3-466177650d20")
    private static class PanelController {
        @objid ("a955f4c5-45b8-4d03-be5f-49577d396696")
        private PanelUI ui;

        @objid ("8917acab-923e-4b50-8886-25ba5d3b1b32")
        private final List<IPanelListener> listeners = new ArrayList<>();

        @objid ("3dfd8385-68f8-4844-8f60-af782f2c6962")
        private UpdatePanelDataModel data;

        @objid ("7eb2f1f0-a737-4fa2-bc18-5af50e4a037e")
        public UpdatePanelDataModel getData() {
            return this.data;
        }

        @objid ("04a10e2d-23e1-4b68-8ef4-3817fbcd1e1d")
        public void setData(final UpdatePanelDataModel data) {
            this.data = data;
            if (this.ui != null) {
                this.ui.update(this.data);
            }
        }

        @objid ("95251a96-8120-4acf-a72f-9b01e872274b")
        public Control createUi(final Composite parent) {
            this.ui = new PanelUI(this);
            final Control control = this.ui.createUI(parent);
            this.ui.update(this.data);
            return control;
        }

        @objid ("49af2eec-8929-4427-ac47-d549382add2d")
        public Control getUi() {
            return this.ui.composite;
        }

        @objid ("fe6d0dcd-93ae-435a-abf0-5f16220e5428")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
        }

        @objid ("1f8483a5-565e-4785-ad80-c521865f320d")
        public void onItemSelected(final UpdateDescriptor element, final boolean value) {
            if (value) {
                this.data.selectUpdate(element);
            } else {
                this.data.unSelectUpdate(element);
            }
            this.ui.update(this.data);
        }

        @objid ("9e89ac36-57ad-4c76-a8d1-b3423dd4443b")
        public void onSelectAll() {
            for (final UpdateDescriptor descriptor : this.data.getAvailableUpdates()) {
                this.data.selectUpdate(descriptor);
            }
            this.ui.update(this.data);
        }

        @objid ("8df13a9e-aa90-4b92-944c-229d9d8de0d2")
        public void onUnselectAll() {
            for (final UpdateDescriptor descriptor : this.data.getAvailableUpdates()) {
                this.data.unSelectUpdate(descriptor);
            }
            this.ui.update(this.data);
        }

    }

    @objid ("0ea997f2-1a96-46a1-8b27-ed40fe26a076")
    private static class PanelUI {
        @objid ("027e21c1-a43f-41da-9c5b-3fea52324896")
        private Composite composite = null;

        @objid ("20bb3c6e-9b01-4f10-942e-aa33ac5685f8")
        private final PanelController controller;

        @objid ("569f19e3-1625-4233-806e-28f4309d0076")
        protected Browser browser;

        @objid ("4d4ccf2b-c296-437a-a15a-055b7565bc0d")
        private TableViewer tableViewer;

        @objid ("2f39d878-54d9-49fb-aba6-389824a1d4f4")
        protected static final Image RAMC = AppUpdate.getImageDescriptor("icons/ramc.png").createImage();

        @objid ("659dc368-183b-4303-925f-6dc01d1ee801")
        protected static final Image MODULE = AppUpdate.getImageDescriptor("icons/module.png").createImage();

        @objid ("d35ad481-993a-4961-9041-622b3cbf6b01")
        public PanelUI(final PanelController controller) {
            this.controller = controller;
        }

        @objid ("5d93dfa9-8a5d-47f8-adce-347b2d593007")
        public Control createUI(final Composite parent) {
            this.composite = new Composite(parent, SWT.NONE);
            this.composite.setLayout(new GridLayout(2, false));
            
            final Composite leftComposite = new Composite(this.composite, SWT.NONE);
            leftComposite.setLayout(new GridLayout(1, false));
            final GridData layoutData = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
            leftComposite.setLayoutData(layoutData);
            
            // Define the TableViewer
            this.tableViewer = new TableViewer(leftComposite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
            final Table table = this.tableViewer.getTable();
            table.setHeaderVisible(true);
            table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            
            // Create the columns
            createColumns(this.tableViewer);
            
            // Set the ContentProvider
            this.tableViewer.setContentProvider(ArrayContentProvider.getInstance());
            
            // Sort table
            this.tableViewer.setComparator(new ViewerComparator());
            
            this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            
                @Override
                public void selectionChanged(final SelectionChangedEvent event) {
                    final ISelection selection = event.getSelection();
                    if (selection instanceof IStructuredSelection) {
                        final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                        if (structuredSelection.size() == 1) {
                            final Object obj = structuredSelection.getFirstElement();
                            if (obj instanceof UpdateDescriptor) {
                                final String url = ((UpdateDescriptor) obj).getDocumentationLink();
                                if (url != null && !url.isEmpty()) {
                                    PanelUI.this.browser.setUrl(url);
                                }
            
                            }
                        }
                    }
                }
            });
            
            final Composite buttonComposite = new Composite(leftComposite, SWT.NONE);
            buttonComposite.setLayoutData(new GridData(SWT.END, SWT.END, false, false));
            buttonComposite.setLayout(new GridLayout(2, false));
            
            // Select all button
            final Button selectAllButton = new Button(buttonComposite, SWT.PUSH);
            selectAllButton.setText(AppUpdate.I18N.getString("UpdateBrowserDialog.SelectAll"));
            selectAllButton.addSelectionListener(new SelectionListener() {
            
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    PanelUI.this.controller.onSelectAll();
                }
            
                @Override
                public void widgetDefaultSelected(final SelectionEvent e) {
                    // Empty
                }
            });
            selectAllButton.setLayoutData(new GridData());
            
            // Unselect all button
            final Button unselectAllButton = new Button(buttonComposite, SWT.PUSH);
            unselectAllButton.setText(AppUpdate.I18N.getString("UpdateBrowserDialog.UnselectAll"));
            unselectAllButton.addSelectionListener(new SelectionListener() {
            
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    PanelUI.this.controller.onUnselectAll();
                }
            
                @Override
                public void widgetDefaultSelected(final SelectionEvent e) {
                    // Empty
                }
            });
            unselectAllButton.setLayoutData(new GridData());
            
            this.browser = new Browser(this.composite, SWT.NONE);
            this.browser.setText("");
            
            final GridData browserLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
            browserLayoutData.widthHint = 300;
            this.browser.setLayoutData(browserLayoutData);
            return this.composite;
        }

        @objid ("62d741c0-d6b5-490c-ad81-08ce978d89f9")
        private void createColumns(final TableViewer viewer) {
            final TableViewerColumn nameCol = new TableViewerColumn(viewer, SWT.NONE);
            nameCol.getColumn().setText(AppUpdate.I18N.getString("UpdateBrowserDialog.column.Name"));
            nameCol.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(final Object element) {
                    final UpdateDescriptor descriptor = (UpdateDescriptor) element;
                    return descriptor.getLabel();
                }
            
                @Override
                public Image getImage(final Object element) {
                    final UpdateDescriptor descriptor = (UpdateDescriptor) element;
                    if (descriptor.getDownloadLink().endsWith(".ramc")) {
                        return RAMC;
                    } else if (descriptor.getDownloadLink().endsWith(".jmdac")) {
                        return MODULE;
                    } else {
                        return null;
                    }
                }
            });
            
            final TableViewerColumn oldVersionCol = new TableViewerColumn(viewer, SWT.NONE);
            oldVersionCol.getColumn().setText(AppUpdate.I18N.getString("UpdateBrowserDialog.column.CurrentVersion"));
            oldVersionCol.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(final Object element) {
                    final UpdateDescriptor mud = (UpdateDescriptor) element;
                    if (mud.getCurrentVersion().equals("")) {
                        return AppUpdate.I18N.getString("UpdateBrowserDialog.NotInstalled");
                    }
                    return mud.getCurrentVersion();
                }
            });
            
            final TableViewerColumn newVersionCol = new TableViewerColumn(viewer, SWT.NONE);
            newVersionCol.getColumn().setText(AppUpdate.I18N.getString("UpdateBrowserDialog.column.NewVersion"));
            newVersionCol.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(final Object element) {
                    final UpdateDescriptor mud = (UpdateDescriptor) element;
                    return mud.getNewVersion();
                }
            });
            
            final TableViewerColumn updateCol = new TableViewerColumn(viewer, SWT.NONE);
            updateCol.getColumn().setText(AppUpdate.I18N.getString("UpdateBrowserDialog.column.SelectInstall"));
            updateCol.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(final Object element) {
                    return "";
                }
            
                @Override
                public Image getImage(final Object element) {
                    if (PanelUI.this.controller.data.isSelected((UpdateDescriptor) element)) {
                        return UIImages.CHECKED;
                    }
                    return UIImages.UNCHECKED;
                }
            });
            updateCol.setEditingSupport(new EditingSupport(viewer) {
                @Override
                protected Object getValue(final Object element) {
                    return PanelUI.this.controller.data.isSelected((UpdateDescriptor) element);
                }
            
                @Override
                protected void setValue(final Object element, final Object value) {
                    PanelUI.this.controller.onItemSelected((UpdateDescriptor) element, (boolean) value);
                }
            
                @Override
                protected CellEditor getCellEditor(final Object element) {
                    return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
                }
            
                @Override
                protected boolean canEdit(final Object element) {
                    return true;
                }
            });
        }

        @objid ("f8110f0d-9eba-4775-b217-9bc83bd3fed4")
        public void update(final UpdatePanelDataModel data) {
            if (data != null) {
                final boolean noInput = this.tableViewer.getInput() == null;
                this.tableViewer.setInput(data.getAvailableUpdates());
            
                if (noInput) {
                    final Table table = this.tableViewer.getTable();
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        final TableColumn col = table.getColumn(i);
                        col.pack();
            
                        // The column with an icon isn't resized well after pack, we need to
                        // add the image width
                        if (i == 4) {
                            final int width = col.getWidth();
                            col.setWidth(width + 50);
                        }
                    }
                }
            
                if (this.tableViewer.getSelection().isEmpty()) {
                    this.tableViewer.setSelection(new StructuredSelection(this.tableViewer.getElementAt(0)));
                }
            }
        }

        @objid ("ff412ffb-dc46-40c6-9f41-1ee75c7bc8c2")
        public void dispose() {
            this.composite.dispose();
        }

    }

}
