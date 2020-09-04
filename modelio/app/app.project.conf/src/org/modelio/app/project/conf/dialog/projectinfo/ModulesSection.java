/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.common.ModuleHelper;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.module.GModule;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.ui.UIColor;

/**
 * Manage the module summary section.
 */
@objid ("a745c45a-33f6-11e2-a514-002564c97630")
class ModulesSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("a745c45c-33f6-11e2-a514-002564c97630")
    protected ProjectModel displayedProject;

    @objid ("a745c45f-33f6-11e2-a514-002564c97630")
    private TableViewer modulesTable;

    @objid ("16c4108e-59de-4178-940e-17e636a490c4")
    protected IModuleManagementService moduleService;

    @objid ("a745c460-33f6-11e2-a514-002564c97630")
    public ModulesSection(IEclipseContext applicationContext) {
        this.moduleService = applicationContext.get(IModuleManagementService.class);
    }

    @objid ("a745c463-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel projectAdapter) {
        this.displayedProject = projectAdapter;
        
        if (projectAdapter != null) {
            this.modulesTable.setInput(projectAdapter.getModules());
        } else {
            this.modulesTable.setInput(new Object[0]);
        }
        
        for (TableColumn col : this.modulesTable.getTable().getColumns()) {
            col.pack();
        }
    }

    @objid ("a745c466-33f6-11e2-a514-002564c97630")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConf.I18N.getString("ModulesSection.SectionText")); //$NON-NLS-1$
        section.setDescription(AppProjectConf.I18N.getString("InformationPage.ModulesSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        
        Table table = toolkit.createTable(composite, SWT.BORDER);
        table.setHeaderVisible(true);
        table.setBackground(UIColor.TEXT_READONLY_BG);
        this.modulesTable = new TableViewer(table);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gd);
        
        this.modulesTable.setContentProvider(new ArrayContentProvider());
        
        TableViewerColumn labelColumn = new TableViewerColumn(this.modulesTable, SWT.NONE);
        labelColumn.getColumn().setText(AppProjectConf.I18N.getString("ModulesSection.NameColumn")); //$NON-NLS-1$
        labelColumn.getColumn().setWidth(50);
        labelColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ModuleHelper.getLabel(element, ModulesSection.this.moduleService);
            }
        
            @Override
            public Image getImage(Object element) {
                return ModuleHelper.getIcon(element);
            }
        });
        
        TableViewerColumn versionColumn = new TableViewerColumn(this.modulesTable, SWT.RIGHT);
        versionColumn.getColumn().setText(AppProjectConf.I18N.getString("ModulesSection.VersionColumn")); //$NON-NLS-1$
        versionColumn.getColumn().setWidth(20);
        versionColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ModuleHelper.getVersion(element);
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        
        TableViewerColumn statusColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
        statusColumn.getColumn().setText(AppProjectConf.I18N.getString("ModulesSection.StatusColumn")); //$NON-NLS-1$
        statusColumn.getColumn().setWidth(100);
        statusColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof GModule) {
                    IModuleManagementService mService = ModulesSection.this.moduleService;
                    IRTModule iModule = mService.getIRTModule((GModule) element);
                    if (iModule != null) {
                        return AppProjectConf.I18N.getString("$ModulesSection.state." + iModule.getState().name()); //$NON-NLS-1$
                    }
                }
                return AppProjectConf.I18N.getString("ModulesSection.Broken"); //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        
        this.modulesTable.setInput(null);
        
        this.modulesTable.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setInput(ModulesSection.this.displayedProject);
            }
        });
        
        // The buttons composite
        Composite panel = toolkit.createComposite(composite, SWT.NONE);
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
        
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

}
