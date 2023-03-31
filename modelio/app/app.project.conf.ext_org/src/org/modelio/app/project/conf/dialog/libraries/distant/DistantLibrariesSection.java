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
package org.modelio.app.project.conf.dialog.libraries.distant;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
import org.modelio.api.ui.swt.SelectionHelper;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.common.ColumnHelper;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.progress.NullProgress;
import org.modelio.vbasic.progress.SubProgress;

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
    public  DistantLibrariesSection(final IEclipseContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Update() is called by the project infos view when the project to be displayed changes or need contents refresh.
     * @param selectedProject the project selected in the workspace tree view
     */
    @objid ("7d4e9546-3adc-11e2-916e-002564c97630")
    public void setInput(final ProjectModel selectedProject) {
        this.projectAdapter = selectedProject;
        
        if (selectedProject != null) {
            final List<IGModelFragment> sel = getSelectedFragments();
            final boolean isFragmentSelected = !sel.isEmpty();
            final boolean areLocal = isFragmentSelected && areAllLocalFragments(sel);
        
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
        
        for (final TableColumn col : this.viewer.getTable().getColumns()) {
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
        final Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConfExt.I18N.getString("DistantLibrariesSection.SectionText")); //$NON-NLS-1$
        section.setDescription(AppProjectConfExt.I18N.getString("DistantLibrariesSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        final Composite composite = toolkit.createComposite(section, SWT.WRAP);
        final GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        
        final Table table = toolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        this.viewer = new TableViewer(table);
        // table.setHeaderVisible(true);
        
        final GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gd);
        
        this.viewer.setContentProvider(new ArrayContentProvider());
        ColumnViewerToolTipSupport.enableFor(this.viewer);
        
        // Name column
        @SuppressWarnings ("unused")
        final TableViewerColumn nameColumn = ColumnHelper.createFragmentNameColumn(this.viewer);
        
        // Scope column
        @SuppressWarnings ("unused")
        final TableViewerColumn scopeColumn = ColumnHelper.createFragmentScopeColumn(this.viewer);
        
        // Metamodel version column
        @SuppressWarnings ("unused")
        final TableViewerColumn mmVer = ColumnHelper.createFragmentMmVersionColumn(this.viewer);
        
        // URL column
        final TableViewerColumn uriColumn = new TableViewerColumn(this.viewer, SWT.NONE);
        uriColumn.getColumn().setWidth(300);
        uriColumn.getColumn().setResizable(true);
        uriColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                URI uri = null;
                if (element instanceof IGModelFragment) {
                    uri = ((IGModelFragment) element).getDescriptor().getLocation();
                }
                return uri != null ? uri.toString().replaceAll("%20", " ") : ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(final Object element) {
                return null;
            }
        });
        
        this.viewer.setInput(null);
        
        this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                // launch a full refresh of the section
                setInput(getProjectAdapter());
            }
        });
        
        // The buttons composite
        final Composite panel = toolkit.createComposite(composite, SWT.NONE);
        final GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
        panel.setLayoutData(gd2);
        
        final RowLayout rowLayout = new RowLayout();
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
        this.addFragment = toolkit.createButton(panel, AppProjectConfExt.I18N.getString("DistantLibrariesSection.AddLibrary"), SWT.PUSH); //$NON-NLS-1$
        this.addFragment.addSelectionListener(new AddFragmentButtonListener());
        
        // The refresh button
        this.refreshFragment = toolkit.createButton(panel, AppProjectConfExt.I18N.getString("DistantLibrariesSection.Refresh"), SWT.PUSH); //$NON-NLS-1$
        this.refreshFragment.setEnabled(false);
        this.refreshFragment.addSelectionListener(new RefreshFragmentButtonListener());
        
        // The edit button
        this.editFragment = toolkit.createButton(panel, AppProjectConfExt.I18N.getString("DistantLibrariesSection.Edit"), SWT.PUSH); //$NON-NLS-1$
        this.editFragment.setEnabled(false);
        this.editFragment.addSelectionListener(new EditFragmentButtonListener());
        
        // The delete button
        this.removeFragment = toolkit.createButton(panel, AppProjectConfExt.I18N.getString("DistantLibrariesSection.Remove"), SWT.PUSH); //$NON-NLS-1$
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
    private boolean areAllLocalFragments(final List<IGModelFragment> sel) {
        for (final IGModelFragment f : sel) {
            if (f.getDefinitionScope() == DefinitionScope.SHARED) {
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
    public List<IGModelFragment> getSelectedFragments() {
        final IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
        return SelectionHelper.toList(selection, IGModelFragment.class);
    }

    @objid ("7d4e955a-3adc-11e2-916e-002564c97630")
    private class AddFragmentButtonListener extends SelectionAdapter {
        @objid ("7d4e955b-3adc-11e2-916e-002564c97630")
        @Override
        public void widgetSelected(final SelectionEvent e) {
            final IProjectService projectService = DistantLibrariesSection.this.applicationContext.get(IProjectService.class);
            
            final Shell shell = getViewer().getControl().getShell();
            
            final boolean allowProjectAuth = projectService.getOpenedProject().getType() != ProjectType.LOCAL;
            final DistantLibraryDialog dlg = new DistantLibraryDialog(shell, null, allowProjectAuth, getProjectAdapter().getFragmentIdList());
            dlg.open();
            
            final GProjectPartDescriptor fragmentDescriptor = dlg.getFragmentDescriptor();
            if (fragmentDescriptor != null) {
            
                final IRunnableWithProgress runnable = monitor -> {
                    try {
                        IGProject openedProject = getProjectAdapter().getOpenedProject();
                        SubProgress mon = ModelioProgressAdapter.convert(monitor, 100);
            
                        // Instantiate the part
                        final IGPart newFragment = GPartFactory.getInstance().instantiate(fragmentDescriptor);
            
                        // Add new fragment to project, permanent mount
                        openedProject.addGPart(mon, newFragment, true);
                    } catch (final GPartException ex) {
                        throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                    }
                };
            
                try {
                    new ProgressMonitorDialog(shell).run(true, false, runnable);
                    refresh();
                } catch (final InvocationTargetException ex) {
                    AppProjectConfExt.LOG.error(ex);
                    MessageDialog.openError(shell, AppProjectConfExt.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                } catch (@SuppressWarnings ("unused") final InterruptedException ex) {
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
        public void widgetSelected(final SelectionEvent e) {
            if (getViewer().getSelection().isEmpty()) {
                return;
            }
            
            IGProject openedProject = getProjectAdapter().getOpenedProject();
            IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
            try {
                for (final IGModelFragment gPart : SelectionHelper.toList(selection, IGModelFragment.class)) {
                    openedProject.removeGPart(new NullProgress(), gPart);
                }
            } catch (GPartException ex) {
                AppProjectConfExt.LOG.error(ex);
                Shell shell = getViewer().getControl().getShell();
                MessageDialog.openError(shell, AppProjectConfExt.I18N.getString("Error"), ex.getLocalizedMessage());
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
        public void widgetSelected(final SelectionEvent e) {
            final IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
            for (final Object obj : selection.toList()) {
                final IGModelFragment fragment = (IGModelFragment) obj;
            
                final Shell shell = getViewer().getControl().getShell();
            
                final IGProject openedProject = getProjectAdapter().getOpenedProject();
            
                final AuthDescriptor authConf = fragment.getAuth();
                final GProjectPartDescriptor fragmentDescriptor = new GProjectPartDescriptor(fragment.getType(), fragment.getId(), null, fragment.getDefinitionScope());
                fragmentDescriptor.setProperties(fragment.getProperties());
                fragmentDescriptor.setLocation(fragment.getDescriptor().getLocation());
                fragmentDescriptor.setAuth(new AuthDescriptor(authConf));
            
                final IRunnableWithProgress runnable = monitor -> {
                    try {
                        SubProgress mon = ModelioProgressAdapter.convert(monitor, 2);
                        openedProject.removeGPart(mon.newChild(1), fragment);
            
                        // Instantiate the part
                        final IGPart newFragment = GPartFactory.getInstance().instantiate(fragmentDescriptor);
            
                        // Add new fragment to project, permanent mount
                        openedProject.addGPart(mon.newChild(1), newFragment, true);
                    } catch (final GPartException ex) {
                        throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                    }
                };
            
                try {
                    new ProgressMonitorDialog(shell).run(true, false, runnable);
                } catch (final InvocationTargetException ex) {
                    AppProjectConfExt.LOG.error(ex);
                    MessageDialog.openError(shell, AppProjectConfExt.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                } catch (@SuppressWarnings ("unused") final InterruptedException ex) {
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
        public void widgetSelected(final SelectionEvent e) {
            for (final IGModelFragment fragment : getSelectedFragments()) {
                final Shell shell = getViewer().getControl().getShell();
            
                final IGProject openedProject = getProjectAdapter().getOpenedProject();
                final boolean allowProjectAuth = openedProject.getType() != ProjectType.LOCAL;
            
                final DistantLibraryDialog dlg = new DistantLibraryDialog(shell, fragment, allowProjectAuth, getProjectAdapter().getFragmentIdList());
                dlg.open();
            
                final GProjectPartDescriptor fragmentDescriptor = dlg.getFragmentDescriptor();
                if (fragmentDescriptor != null) {
            
                    final IRunnableWithProgress runnable = monitor -> {
                        try {
                            SubProgress mon = ModelioProgressAdapter.convert(monitor, 2);
                            openedProject.removeGPart(mon.newChild(1), fragment);
            
                            // Instantiate the part
                            final IGPart newFragment = GPartFactory.getInstance().instantiate(fragmentDescriptor);
            
                            // Add new fragment to project, permanent mount
                            openedProject.addGPart(mon.newChild(1), newFragment, true);
                        } catch (final GPartException ex) {
                            throw new InvocationTargetException(ex, ex.getLocalizedMessage());
                        }
                    };
            
                    try {
                        new ProgressMonitorDialog(shell).run(true, false, runnable);
                    } catch (final InvocationTargetException ex) {
                        AppProjectConfExt.LOG.error(ex);
                        MessageDialog.openError(shell, AppProjectConfExt.I18N.getString("Error"), ex.getCause().getLocalizedMessage()); //$NON-NLS-1$
                    } catch (@SuppressWarnings ("unused") final InterruptedException ex) {
                        // nothing
                    }
            
                }
            }
            refresh();
            
        }

    }

}
