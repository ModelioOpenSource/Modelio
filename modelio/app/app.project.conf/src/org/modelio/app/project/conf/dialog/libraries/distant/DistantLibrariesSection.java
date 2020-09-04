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

package org.modelio.app.project.conf.dialog.libraries.distant;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.common.ColumnHelper;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.data.project.AuthDescriptor;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;

/**
 * Manage the distant libraries section.
 * <p>
 * Call {@link DistantLibrariesSection#createControls(FormToolkit, Composite)} and {@link DistantLibrariesSection#setInput(ProjectModel)} to use it.
 * </p>
 */
@objid ("7d4e9539-3adc-11e2-916e-002564c97630")
public class DistantLibrariesSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("7d4e953b-3adc-11e2-916e-002564c97630")
    private ProjectModel projectAdapter;

    @objid ("5b56f57c-8e96-4daa-b83f-9a04521ba0bb")
    protected IEclipseContext applicationContext;

    @objid ("82728571-ce28-4a12-8cdd-773be1415689")
    private TableViewer viewer;

    @objid ("3d0a289e-c664-472f-bfab-a050ed7f030f")
    private Button addFragment;

    @objid ("1582287a-aba9-4f8f-8c92-493f51afe543")
    private Button removeFragment;

    @objid ("5c573c39-bcbd-4cd3-91c5-962ea1087dd6")
    private Button editFragment;

    @objid ("22012344-308e-497e-8c98-01e9b214c099")
    private Button refreshFragment;

    /**
     * @param applicationContext the Eclipse context
     */
    @objid ("7d4e9543-3adc-11e2-916e-002564c97630")
    public DistantLibrariesSection(IEclipseContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Update() is called by the project infos view when the project to be
     * displayed changes or need contents refresh.
     * @param selectedProject the project selected in the workspace tree view
     */
    @objid ("7d4e9546-3adc-11e2-916e-002564c97630")
    public void setInput(ProjectModel selectedProject) {
        this.projectAdapter = selectedProject;
        
        if (selectedProject != null) {
            List<IProjectFragment> sel = getSelectedFragments();
            boolean isFragmentSelected = !sel.isEmpty();
            boolean areLocal = isFragmentSelected && areAllLocalFragments(sel);
        
            this.viewer.setInput(selectedProject.getDistantLibraryFragments());
            this.addFragment.setEnabled(true && this.projectAdapter.isLocalProject());
            this.removeFragment.setEnabled(areLocal && this.projectAdapter.isLocalProject());
            this.editFragment.setEnabled(isFragmentSelected);
            this.refreshFragment.setEnabled(isFragmentSelected);
        } else {
            this.viewer.setInput(new Object[0]);
            this.addFragment.setEnabled(false);
            this.removeFragment.setEnabled(false);
            this.editFragment.setEnabled(false);
            this.refreshFragment.setEnabled(false);
        }
        
        for (TableColumn col : this.viewer.getTable().getColumns()) {
            col.pack();
        }
    }

    /**
     * @param toolkit the Eclipse form toolkit
     * @param parent the parent composite
     * @return the created Section
     */
    @objid ("7d4e954a-3adc-11e2-916e-002564c97630")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConf.I18N.getString("DistantLibrariesSection.SectionText")); //$NON-NLS-1$
        section.setDescription(AppProjectConf.I18N.getString("DistantLibrariesSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        
        Table table = toolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        this.viewer = new TableViewer(table);
        // table.setHeaderVisible(true);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gd);
        
        this.viewer.setContentProvider(new ArrayContentProvider());
        ColumnViewerToolTipSupport.enableFor(this.viewer);
        
        // Name column
        @SuppressWarnings("unused")
        TableViewerColumn nameColumn = ColumnHelper.createFragmentNameColumn(this.viewer);
        
        // Scope column
        @SuppressWarnings("unused")
        TableViewerColumn scopeColumn = ColumnHelper.createFragmentScopeColumn(this.viewer);
        
        // Metamodel version column
        @SuppressWarnings("unused")
        TableViewerColumn mmVer = ColumnHelper.createFragmentMmVersionColumn(this.viewer);
        
        // URL column
        TableViewerColumn uriColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        uriColumn.getColumn().setWidth(300);
        uriColumn.getColumn().setResizable(true);
        uriColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                URI uri = null;
                if (element instanceof IProjectFragment) {
                    uri = ((IProjectFragment) element).getUri();
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
        
        // The add button
        this.addFragment = toolkit.createButton(panel, AppProjectConf.I18N.getString("DistantLibrariesSection.AddLibrary"), SWT.PUSH); //$NON-NLS-1$
        this.addFragment.addSelectionListener(new AddFragmentButtonListener());
        
        // The refresh button
        this.refreshFragment = toolkit.createButton(panel, AppProjectConf.I18N.getString("DistantLibrariesSection.Refresh"), SWT.PUSH); //$NON-NLS-1$
        this.refreshFragment.setEnabled(false);
        this.refreshFragment.addSelectionListener(new RefreshFragmentButtonListener());
        
        // The edit button
        this.editFragment = toolkit.createButton(panel, AppProjectConf.I18N.getString("DistantLibrariesSection.Edit"), SWT.PUSH); //$NON-NLS-1$
        this.editFragment.setEnabled(false);
        this.editFragment.addSelectionListener(new EditFragmentButtonListener());
        
        // The delete button
        this.removeFragment = toolkit.createButton(panel, AppProjectConf.I18N.getString("DistantLibrariesSection.Remove"), SWT.PUSH); //$NON-NLS-1$
        this.removeFragment.setEnabled(false);
        this.removeFragment.addSelectionListener(new RemoveFragmentButtonListener());
        
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    @objid ("7d4e9552-3adc-11e2-916e-002564c97630")
    TableViewer getViewer() {
        return this.viewer;
    }

    @objid ("7d4e9556-3adc-11e2-916e-002564c97630")
    ProjectModel getProjectAdapter() {
        return DistantLibrariesSection.this.projectAdapter;
    }

    @objid ("453821a7-3df9-11e2-b413-002564c97630")
    protected void refresh() {
        setInput(this.projectAdapter);
    }

    @objid ("bbd157b1-6f11-48b7-ada2-9fb24d519fc8")
    private boolean areAllLocalFragments(List<IProjectFragment> sel) {
        for (IProjectFragment f : sel) {
            if (f.getScope() == DefinitionScope.SHARED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the fragment viewer selection as a list of fragments.
     * @return the selection as a list of fragments.
     */
    @objid ("55f63ac7-1fae-4c8d-b126-2c4ba5d1e4e3")
    @SuppressWarnings("unchecked")
    public List<IProjectFragment> getSelectedFragments() {
        IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
        return selection.toList();
    }

    @objid ("7d4e955a-3adc-11e2-916e-002564c97630")
    private class AddFragmentButtonListener extends SelectionAdapter {
        @objid ("7d4e955b-3adc-11e2-916e-002564c97630")
        @Override
        public void widgetSelected(SelectionEvent e) {
            AppProjectConf.LOG.debug("add HTML fragment"); //$NON-NLS-1$
            
            final IProjectService projectService = DistantLibrariesSection.this.applicationContext.get(IProjectService.class);
            
            Shell shell = getViewer().getControl().getShell();
            
            boolean allowProjectAuth = projectService.getOpenedProject().getType() != ProjectType.LOCAL;
            DistantLibraryDialog dlg = new DistantLibraryDialog(shell, null, allowProjectAuth, getProjectAdapter().getFragmentIdList());
            dlg.open();
            
            final FragmentDescriptor fragmentDescriptor = dlg.getFragmentDescriptor();
            if (fragmentDescriptor != null) {
            
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        try {
                            projectService.addFragment(getProjectAdapter().getOpenedProject(), fragmentDescriptor, monitor);
                        } catch (FragmentConflictException ex) {
                            throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                        }
                    }
                };
            
                try {
                    new ProgressMonitorDialog(shell).run(true, false, runnable);
                    refresh();
                } catch (InvocationTargetException ex) {
                    AppProjectConf.LOG.error(ex);
                    MessageDialog.openError(shell, AppProjectConf.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                } catch (InterruptedException ex) {
                    // nothing
                }
            
            }
        }

        @objid ("7d4e955f-3adc-11e2-916e-002564c97630")
        AddFragmentButtonListener() {
            // empty
        }

    }

    @objid ("7d4e9561-3adc-11e2-916e-002564c97630")
    private class RemoveFragmentButtonListener extends SelectionAdapter {
        @objid ("7d4e9562-3adc-11e2-916e-002564c97630")
        @Override
        public void widgetSelected(SelectionEvent e) {
            AppProjectConf.LOG.debug("remove HTML fragment"); //$NON-NLS-1$
            
            IProjectService projectService = DistantLibrariesSection.this.applicationContext.get(IProjectService.class);
            
            if (getViewer().getSelection().isEmpty()) {
                return;
            }
            
            IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
            for (Object obj : selection.toList()) {
                projectService.removeFragment(getProjectAdapter().getOpenedProject(), (IProjectFragment) obj);
            }
            refresh();
        }

        @objid ("7d4e9566-3adc-11e2-916e-002564c97630")
        RemoveFragmentButtonListener() {
            // empty
        }

    }

    @objid ("7d4e9568-3adc-11e2-916e-002564c97630")
    private class RefreshFragmentButtonListener extends SelectionAdapter {
        @objid ("7d4e9569-3adc-11e2-916e-002564c97630")
        RefreshFragmentButtonListener() {
            // empty
        }

        @objid ("7d4e956b-3adc-11e2-916e-002564c97630")
        @Override
        public void widgetSelected(SelectionEvent e) {
            AppProjectConf.LOG.info("Refresh HTTP fragment"); //$NON-NLS-1$
            
            IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
            for (Object obj : selection.toList()) {
                final IProjectFragment fragment = (IProjectFragment) obj;
            
                final Shell shell = getViewer().getControl().getShell();
            
                final IProjectService projectService = DistantLibrariesSection.this.applicationContext.get(IProjectService.class);
                final GProject openedProject = getProjectAdapter().getOpenedProject();
            
                final GAuthConf authConf = fragment.getAuthConfiguration();
                final FragmentDescriptor fragmentDescriptor = new FragmentDescriptor();
                fragmentDescriptor.setId(fragment.getId());
                fragmentDescriptor.setProperties(fragment.getProperties());
                fragmentDescriptor.setType(fragment.getType());
                fragmentDescriptor.setUri(fragment.getUri());
                fragmentDescriptor.setScope(fragment.getScope());
                fragmentDescriptor.setAuthDescriptor(new AuthDescriptor(authConf.getAuthData(), authConf.getScope()));
            
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        projectService.removeFragment(openedProject, fragment);
                        try {
                            projectService.addFragment(openedProject, fragmentDescriptor, monitor);
                        } catch (FragmentConflictException ex) {
                            throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                        }
                    }
                };
            
                try {
                    new ProgressMonitorDialog(shell).run(true, false, runnable);
                } catch (InvocationTargetException ex) {
                    AppProjectConf.LOG.error(ex);
                    MessageDialog.openError(shell, AppProjectConf.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                } catch (InterruptedException ex) {
                    // nothing
                }
            }
            refresh();
        }

    }

    @objid ("7d4e956f-3adc-11e2-916e-002564c97630")
    private class EditFragmentButtonListener extends SelectionAdapter {
        @objid ("7d4e9570-3adc-11e2-916e-002564c97630")
        EditFragmentButtonListener() {
            // empty
        }

        @objid ("7d4e9572-3adc-11e2-916e-002564c97630")
        @Override
        public void widgetSelected(SelectionEvent e) {
            AppProjectConf.LOG.info("Edit HTML fragment"); //$NON-NLS-1$
            
            for (final IProjectFragment fragment : getSelectedFragments()) {
                final Shell shell = getViewer().getControl().getShell();
            
                final IProjectService projectService = DistantLibrariesSection.this.applicationContext.get(IProjectService.class);
                final GProject openedProject = getProjectAdapter().getOpenedProject();
                boolean allowProjectAuth = openedProject.getType() != ProjectType.LOCAL;
            
                DistantLibraryDialog dlg = new DistantLibraryDialog(shell, fragment, allowProjectAuth, getProjectAdapter().getFragmentIdList());
                dlg.open();
            
                final FragmentDescriptor fragmentDescriptor = dlg.getFragmentDescriptor();
                if (fragmentDescriptor != null) {
            
                    IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            try {
                                projectService.removeFragment(openedProject, fragment);
                                projectService.addFragment(openedProject, fragmentDescriptor, monitor);
                            } catch (FragmentConflictException ex) {
                                throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                            }
                        }
                    };
            
                    try {
                        new ProgressMonitorDialog(shell).run(true, false, runnable);
                    } catch (InvocationTargetException ex) {
                        AppProjectConf.LOG.error(ex);
                        MessageDialog.openError(shell, AppProjectConf.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                    } catch (InterruptedException ex) {
                        // nothing
                    }
            
                }
            }
            refresh();
        }

    }

}
