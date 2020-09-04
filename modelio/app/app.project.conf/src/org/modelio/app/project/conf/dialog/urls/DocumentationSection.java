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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;

@objid ("2b6b78cd-b268-4aab-9639-e135981aa7f2")
public class DocumentationSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("49920fa6-0e5b-442a-bb1a-3f6e2147149d")
    protected ProjectModel projectAdapter;

    @objid ("6c74f6d5-8f4b-4714-ab5f-d1f4002d7331")
    private final ReferencedUrlsPage referencedUrlPage;

    @objid ("f015f00d-5731-4297-b7a4-979dae6e50d8")
    private TableViewer viewer;

    @objid ("b2633072-9cb0-40e8-9a34-898b9a2305ac")
    private Button addBtn;

    @objid ("0a344b13-18d8-4dcb-bdcd-18592a822be0")
    private Button editBtn;

    @objid ("20e4e13b-7b3f-4a7e-9a3f-7d312113c7e1")
    private Button removeBtn;

    @objid ("85072c3c-b724-4904-85bc-fba209aacc4b")
    public DocumentationSection(ReferencedUrlsPage referencedUrlPage) {
        this.referencedUrlPage = referencedUrlPage;
    }

    /**
     * Update() is called by the referenced url view when the project to be displayed changes or need contents refresh
     * 
     * @param selectedProject the project selected in the workspace tree view
     */
    @objid ("d4464549-baf6-4f7e-8bce-318139ba1226")
    public void setInput(ProjectModel selectedProject) {
        this.projectAdapter = selectedProject;
        
        if (selectedProject != null) {
            List<UrlEntry> urls = selectedProject.getUrls();
            this.viewer.setInput(urls);
            Boolean isUrlSelected = false;
            // Default: select the first available url
            if (urls.size() > 0) {
                selectUrl(urls.get(0));
                isUrlSelected = true;
            }
            this.addBtn.setEnabled(this.projectAdapter.isLocalProject());
            this.editBtn.setEnabled(isUrlSelected);
            this.removeBtn.setEnabled(isUrlSelected && this.projectAdapter.isLocalProject());
        } else {
            this.viewer.setInput(new Object[0]);
            this.addBtn.setEnabled(false);
            this.editBtn.setEnabled(false);
            this.removeBtn.setEnabled(false);
        }
    }

    @objid ("88cc4543-d4ce-403d-86bf-078d35fc81bf")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
                | Section.DESCRIPTION);
        section.setText(AppProjectConf.I18N.getString("DocumentationSection.SectionText"));
        section.setDescription(AppProjectConf.I18N.getString("DocumentationSection.SectionDescription"));
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 2;
        layout.marginHeight = 2;
        composite.setLayout(layout);
        
        Table table = toolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
        this.viewer = new TableViewer(table);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gd);
        
        this.viewer.setContentProvider(new ArrayContentProvider());
        
        // first column the name
        TableViewerColumn nameColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        nameColumn.getColumn().setWidth(100);
        nameColumn.setLabelProvider(new ColumnLabelProvider() {
        
            @Override
            public String getText(Object element) {
                return UrlLabelProvider.getName(element);
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        nameColumn.setEditingSupport(new NameEditingSupport(this.viewer));
        
        // second column the url
        TableViewerColumn urlColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        urlColumn.getColumn().setWidth(100);
        urlColumn.setLabelProvider(new ColumnLabelProvider() {
        
            @Override
            public String getText(Object element) {
                return UrlLabelProvider.getUrl(element);
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        urlColumn.setEditingSupport(new UrlEditingSupport(this.viewer));
        
        this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                updateButtons();
            }
        });
        
        // The buttons composite
        Composite panel = new Composite(composite, SWT.NONE);
        GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
        panel.setLayoutData(gd2);
        
        RowLayout rowLayout = new RowLayout();
        rowLayout.wrap = false;
        rowLayout.pack = false;
        rowLayout.justify = false;
        rowLayout.type = SWT.VERTICAL;
        rowLayout.marginLeft = 2;
        rowLayout.marginTop = 2;
        rowLayout.marginRight = 2;
        rowLayout.marginBottom = 2;
        rowLayout.spacing = 1;
        
        panel.setLayout(rowLayout);
        
        // The add button
        this.addBtn = new Button(panel, SWT.PUSH);
        this.addBtn.setText(AppProjectConf.I18N.getString("DocumentationSection.AddUrl"));
        this.addBtn.addSelectionListener(new SelectionListener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppProjectConf.LOG.debug("add Url");
                UrlConfiguratorDialog dlg = new UrlConfiguratorDialog(null, null, getProjectAdapter().isLocalProject());
                if (dlg.open() == IDialogConstants.OK_ID) {
                    UrlEntry newEntry = dlg.getEntry();
                    if (newEntry != null) {
                        getProjectAdapter().addUrl(newEntry);
                        selectUrl(newEntry);
                    }
                }
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // do nothing
            }
        });
        
        // The edit button
        this.editBtn = new Button(panel, SWT.PUSH);
        this.editBtn.setText(AppProjectConf.I18N.getString("DocumentationSection.Edit"));
        this.editBtn.addSelectionListener(new SelectionListener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppProjectConf.LOG.debug("edit Url");
                // assert (DocumentationSection.this.displayedProject.isOpenedProject());
                ISelection selection = DocumentationSection.this.viewer.getSelection();
                AppProjectConf.LOG.debug("del %s", selection);
                UrlEntry selectedUrlEntry = null;
                if (selection instanceof IStructuredSelection) {
                    for (Object selected : ((IStructuredSelection) selection).toList()) {
                        selectedUrlEntry = (UrlEntry) selected;
                    }
                }
                UrlConfiguratorDialog dlg = new UrlConfiguratorDialog(null, selectedUrlEntry, getProjectAdapter().isLocalProject());
                if (dlg.open() == IDialogConstants.OK_ID) {
                    selectUrl(selectedUrlEntry);
                    UrlEntry editEntry = dlg.getEntry();
                    if (editEntry != null) {
                        getProjectAdapter().removeUrl(editEntry);
                        getProjectAdapter().addUrl(editEntry);
                        DocumentationSection.this.referencedUrlPage.browserSection.setInput(editEntry);
                    }
                }
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // do nothing
            }
        });
        
        // The delete button
        this.removeBtn = new Button(panel, SWT.PUSH);
        this.removeBtn.setText(AppProjectConf.I18N.getString("DocumentationSection.Delete"));
        this.removeBtn.addSelectionListener(new SelectionListener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppProjectConf.LOG.debug("remove Url");
                ISelection selection = DocumentationSection.this.viewer.getSelection();
                AppProjectConf.LOG.debug("del %s", selection);
                if (selection instanceof IStructuredSelection) {
                    for (Object selected : ((IStructuredSelection) selection).toList()) {
                        getProjectAdapter().removeUrl((UrlEntry) selected);
                        DocumentationSection.this.referencedUrlPage.browserSection.setInput(null);
                    }
                }
                selectUrl(null);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // do nothing
            }
        });
        
        // Do it at last
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    @objid ("b5c1bd12-ef7e-4e80-9e5b-71a05cec040c")
    protected void updateButtons() {
        boolean hasSelectedUrl = !this.viewer.getSelection().isEmpty();
        
        // Button addUrl is always enabled
        this.editBtn.setEnabled(hasSelectedUrl);
        this.removeBtn.setEnabled(hasSelectedUrl && getProjectAdapter().isLocalProject());
    }

    @objid ("8d5296ea-4a5a-4acf-b667-9db64bf42f38")
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        this.viewer.addSelectionChangedListener(listener);
    }

    /**
     * Internally called when url list has changed and view needs refresh
     * @param selectedUrlEntry
     */
    @objid ("186f4f3d-207c-40ea-baf3-eb495e1b88c4")
    private void selectUrl(UrlEntry selectedUrlEntry) {
        this.viewer.refresh();
        
        // Pack all columns, to avoid them being too small
        for (TableColumn col : this.viewer.getTable().getColumns()) {
            col.pack();
        }
        
        if (selectedUrlEntry != null) {
            this.viewer.setSelection(new StructuredSelection(selectedUrlEntry));
        } else {
            this.viewer.setSelection(new StructuredSelection());
        }
    }

    @objid ("efc86e70-3ad0-4478-a11a-5d8615ee6026")
    ProjectModel getProjectAdapter() {
        return DocumentationSection.this.projectAdapter;
    }

    @objid ("1021f49d-5916-4118-b29d-3bcb3774b077")
    public class NameEditingSupport extends EditingSupport {
        @objid ("7239a964-c8f1-4076-91fb-222196649aab")
        private final TableViewer tViewer;

        @objid ("9160a4cc-31df-4fb5-a9fa-ecf888762ef2")
        public NameEditingSupport(TableViewer viewer) {
            super(viewer);
            this.tViewer = viewer;
        }

        @objid ("6646cecd-06b5-4c88-b5b9-50860b388e7f")
        @Override
        protected CellEditor getCellEditor(Object element) {
            return new TextCellEditor(this.tViewer.getTable());
        }

        @objid ("e4596c1b-89f9-458e-99c1-ec0dedf28c68")
        @Override
        protected boolean canEdit(Object element) {
            return false; // FIXME should true
        }

        @objid ("bec93c0d-0202-4b71-8baa-db2087df92d0")
        @Override
        protected Object getValue(Object element) {
            UrlEntry entry = (UrlEntry) element;
            return (entry.name != null) ? entry.name : "";
        }

        @objid ("3b6a7f09-749e-46c2-b354-ebf08f1ee2d7")
        @Override
        protected void setValue(Object element, Object value) {
            ((UrlEntry) element).name = (String.valueOf(value));
            this.tViewer.refresh();
        }

    }

    @objid ("6cfeb0da-0645-4616-89c4-8f93d6cbe416")
    public class UrlEditingSupport extends EditingSupport {
        @objid ("7351b0e7-4a24-40d2-89dd-fcd769fa59e7")
        private final TableViewer tViewer;

        @objid ("93377337-3a67-4867-91ec-1cf50811118a")
        public UrlEditingSupport(TableViewer viewer) {
            super(viewer);
            this.tViewer = viewer;
        }

        @objid ("3048d4e4-ba39-4c1c-9caa-7fad3032e432")
        @Override
        protected CellEditor getCellEditor(Object element) {
            return new TextCellEditor(this.tViewer.getTable());
        }

        @objid ("e7e67834-f4c2-4459-9c93-13480a2023b3")
        @Override
        protected boolean canEdit(Object element) {
            return false; // FIXME should true
        }

        @objid ("e6f8df10-19d8-4e71-8242-a97fab73f69a")
        @Override
        protected Object getValue(Object element) {
            UrlEntry entry = (UrlEntry) element;
            return (entry.url != null) ? entry.url : "";
        }

        @objid ("967f58a1-ab0c-4e24-8cb6-14f74b4e5462")
        @Override
        protected void setValue(Object element, Object value) {
            ((UrlEntry) element).url = (String.valueOf(value));
            this.tViewer.refresh();
        }

    }

    @objid ("2dfb55ba-6d05-4d83-9be3-3959a7e4df7c")
    public static class UrlLabelProvider {
        @objid ("24cd7884-c70a-4052-9ac4-f44f641d1f75")
        public static String getName(Object element) {
            if (element instanceof UrlEntry) {
                UrlEntry entry = (UrlEntry) element;
                return (entry.name != null) ? entry.name : "<enter a name>";
            } else {
                return element.toString();
            }
        }

        @objid ("b2b7c5bb-2a44-4d25-8651-3b3927db78cc")
        public static String getUrl(Object element) {
            if (element instanceof UrlEntry) {
                UrlEntry entry = (UrlEntry) element;
                return (entry.url != null) ? entry.url : "<enter an url>";
            } else {
                return element.toString();
            }
        }

    }

}
