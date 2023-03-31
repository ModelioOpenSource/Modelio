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
package org.modelio.app.project.conf.dialog.projectinfo;

import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.platform.model.ui.swt.images.FragmentImageService;
import org.modelio.platform.ui.UIColor;

/**
 * Manage the fragments summary section.
 */
@objid ("a7459d38-33f6-11e2-a514-002564c97630")
class FragmentsSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("a7459d3a-33f6-11e2-a514-002564c97630")
    private ProjectModel projectAdapter;

    @objid ("a7459d3d-33f6-11e2-a514-002564c97630")
    private TableViewer viewer;

    @objid ("a7459d3e-33f6-11e2-a514-002564c97630")
    public  FragmentsSection(IEclipseContext applicationContext) {
        super();
    }

    /**
     * Update() is called by the project infos view when the project to be displayed changes or need contents refresh
     * @param selectedProject the project selected in the workspace tree view
     */
    @objid ("a7459d41-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel selectedProject) {
        this.projectAdapter = selectedProject;
        
        if (selectedProject != null) {
            this.viewer.setInput(selectedProject.getModels());
        } else {
            this.viewer.setInput(new Object[0]);
        }
        
        for (TableColumn col : this.viewer.getTable().getColumns()) {
            col.pack();
        }
        
    }

    @objid ("a7459d45-33f6-11e2-a514-002564c97630")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConf.I18N.getString("FragmentsSection.SectionText")); //$NON-NLS-1$
        section.setDescription(AppProjectConf.I18N.getString("FragmentsSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        
        Table table = toolkit.createTable(composite, SWT.BORDER);
        table.setHeaderVisible(true);
        table.setBackground(UIColor.TEXT_READONLY_BG);
        this.viewer = new TableViewer(table);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gd);
        
        this.viewer.setContentProvider(new ArrayContentProvider());
        
        TableViewerColumn nameColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        nameColumn.getColumn().setText(AppProjectConf.I18N.getString("FragmentsSection.NameColumn")); //$NON-NLS-1$
        nameColumn.getColumn().setWidth(120);
        nameColumn.getColumn().setResizable(true);
        nameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IGModelFragment) {
                    return ((IGModelFragment) element).getId();
                }
                return ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                return FragmentImageService.getImage((IGModelFragment) element);
            }
        });
        
        TableViewerColumn statusColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        statusColumn.getColumn().setText(AppProjectConf.I18N.getString("FragmentsSection.StatusColumn")); //$NON-NLS-1$
        statusColumn.getColumn().setWidth(120);
        statusColumn.getColumn().setResizable(true);
        statusColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IGModelFragment) {
                    final IGModelFragment fragment = (IGModelFragment) element;
                    String str = AppProjectConf.I18N.getString("$FragmentsSection." + fragment.getState().getValue().name());
                    if (fragment.getState().getDownError() != null) {
                        str += ": " + fragment.getState().getDownError().getLocalizedMessage();
                    }
        
                    return str;
                }
                return ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                if (element instanceof IGModelFragment) {
                    final IGModelFragment fragment = (IGModelFragment) element;
                    return FragmentImageService.getStateImage(fragment.getState().getValue());
                }
                return null;
            }
        });
        
        TableViewerColumn uriColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        uriColumn.getColumn().setText(AppProjectConf.I18N.getString("FragmentsSection.UriColumn")); //$NON-NLS-1$
        uriColumn.getColumn().setWidth(300);
        uriColumn.getColumn().setResizable(true);
        uriColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                URI uri = null;
                if (element instanceof IGModelFragment) {
                    IGModelFragment fragment = (IGModelFragment) element;
                    if (fragment.getType() != GProjectPartType.EXMLFRAGMENT && fragment.getType() != GProjectPartType.RAMC) {
                        uri = fragment.getDescriptor().getLocation();
                    }
                }
                return uri != null ? uri.toString().replaceAll("%20", " ") : ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        
        this.viewer.setInput(null);
        
        this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                // launch a full refresh of the section
                setInput(getProjectAdapter());
            }
        });
        
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    @objid ("a7459d4d-33f6-11e2-a514-002564c97630")
    TableViewer getViewer() {
        return this.viewer;
    }

    @objid ("a7459d51-33f6-11e2-a514-002564c97630")
    ProjectModel getProjectAdapter() {
        return FragmentsSection.this.projectAdapter;
    }

}
