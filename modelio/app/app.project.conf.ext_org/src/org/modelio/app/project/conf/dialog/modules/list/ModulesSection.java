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

package org.modelio.app.project.conf.dialog.modules.list;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.common.ModuleHelper;
import org.modelio.app.project.conf.dialog.common.ScopeHelper;
import org.modelio.app.project.conf.dialog.modules.ModuleRemovalConfirmationDialog;
import org.modelio.app.project.conf.dialog.modules.ModuleSelectDialog;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.module.ModuleSorter;
import org.modelio.mda.infra.service.CompatibilityHelper;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.AccessDeniedException;

/**
 * The modules management section.
 */
@objid ("4bb31adc-d4a8-4c10-99ed-c6ad73a4324f")
public class ModulesSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("4ca5e49c-9ee8-41b1-a473-d53740852953")
    private ProjectModel projectModel;

    @objid ("fa62bb5a-08f7-424a-801f-f32f19580221")
    protected IModuleManagementService moduleService;

    @objid ("b166aa36-d7ed-4b52-a7c4-55b65d59ed62")
    protected IModelioProgressService progressService;

    @objid ("ee227aca-abb9-4ccb-8a83-d375a8f38130")
    private TableViewer modulesTable;

    @objid ("1b7ecc7d-dbf5-4db8-856a-780a8cea40a4")
    private Button addButton;

    @objid ("2bc22157-d1e2-4a26-868d-84b1bd798eef")
    protected Button removeButton;

    @objid ("d9deacb9-e126-4e4f-a807-0da80ac1543b")
    protected static final Image CHECKED = AbstractUIPlugin
            .imageDescriptorFromPlugin(AppProjectConfExt.PLUGIN_ID, "icons/checked.png").createImage();

    @objid ("2748cd30-f46a-41b1-adbc-9ad2dbd61d90")
    protected static final Image UNCHECKED = AbstractUIPlugin
            .imageDescriptorFromPlugin(AppProjectConfExt.PLUGIN_ID, "icons/unchecked.png").createImage();

    @objid ("772998f6-906e-4e99-bb9c-c75025dbbf19")
    private IModuleStore catalog;

    @objid ("0dcf50f3-1c09-49cf-91a8-4c3ae4afbd87")
    protected IProjectService projectService;

    /**
     * @param application The Eclipse context
     */
    @objid ("b77b7e36-1e37-4f10-a8c0-73134b03c249")
    public ModulesSection(IEclipseContext applicationContext) {
        this.moduleService = applicationContext.get(IModuleManagementService.class);
        this.progressService = applicationContext.get(IModelioProgressService.class);
        this.projectService = applicationContext.get(IProjectService.class);
        this.catalog = applicationContext.get(IModuleStore.class);
    }

    /**
     * @param projectModel the project model
     */
    @objid ("c893266a-36bb-4b78-9ae1-06d0c263d222")
    public void setInput(ProjectModel projectModel) {
        this.projectModel = projectModel;
        if (projectModel != null) {
            this.modulesTable.setInput(projectModel.getModules());
            this.addButton.setEnabled(projectModel.isLocalProject());
            ISelection selection = this.modulesTable.getSelection();
            this.removeButton.setEnabled(!selection.isEmpty() && !"ModelerModule".equals(SelectionHelper.getFirst(selection, GModule.class).getName()) && projectModel.isLocalProject());
        } else {
            this.modulesTable.setInput(new Object[0]);
            this.addButton.setEnabled(false);
            this.removeButton.setEnabled(false);
        }
        for (TableColumn col : this.modulesTable.getTable().getColumns()) {
            col.pack();
        }
    }

    /**
     * Build the Eclipse form Section.
     * @param toolkit the form toolkit
     * @param parent the parent composite
     * @return the built section.
     */
    @objid ("8de2fc79-56dc-4934-988b-8a565187cfa4")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        final Section section = toolkit.createSection(parent,
                ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION);
        section.setText(AppProjectConfExt.I18N.getString("ModulesSection.SectionText")); //$NON-NLS-1$
        
        section.setDescription(AppProjectConfExt.I18N.getString("ModulesSection.SectionDescription")); //$NON-NLS-1$
        section.setExpanded(true);
        
        final Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        
        final Table table = toolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
        this.modulesTable = new TableViewer(table);
        table.setHeaderVisible(true);
        
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.heightHint = 180;
        gd.minimumWidth = 300;
        table.setLayoutData(gd);
        
        this.modulesTable.setContentProvider(new ArrayContentProvider());
        
        TableViewerColumn enableColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
        enableColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.EnableColumn")); //$NON-NLS-1$
        enableColumn.getColumn().setWidth(30);
        enableColumn.setEditingSupport(new ModuleStateEditingSupport(this.modulesTable, this.moduleService));
        enableColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                if (element instanceof GModule) {
                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
                    if (iModule != null && iModule.getState() == ModuleRuntimeState.Started) {
                        return ModulesSection.CHECKED;
                    }
                }
                return ModulesSection.UNCHECKED;
            }
        });
        
        TableViewerColumn scopeColumn = new TableViewerColumn(this.modulesTable, SWT.NONE);
        scopeColumn.getColumn().setWidth(120);
        scopeColumn.getColumn().setResizable(true);
        scopeColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.ScopeColumn")); //$NON-NLS-1$
        scopeColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof GModule) {
                    return ScopeHelper.getText(((GModule) element).getScope());
                }
                return ""; //$NON-NLS-1$
            }
        });
        
        TableViewerColumn labelColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
        labelColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.NameColumn")); //$NON-NLS-1$
        labelColumn.getColumn().setWidth(100);
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
        versionColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.VersionColumn")); //$NON-NLS-1$
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
        statusColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.StatusColumn")); //$NON-NLS-1$
        statusColumn.getColumn().setWidth(100);
        statusColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof GModule) {
                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
                    if (iModule != null) {
                        String state = AppProjectConfExt.I18N
                                .getString("$ModulesSection.state." + iModule.getState().name());
                        if (iModule.getDownError() != null) {
                            state += AppProjectConfExt.I18N.getString("ModulesSection.state.problem");
                        }
                        return state;
                    }
                }
                return AppProjectConfExt.I18N.getString("ModulesSection.state.Broken"); //$NON-NLS-1$
            }
        
            @Override
            public String getToolTipText(Object element) {
                if (element instanceof GModule) {
                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
                    if (iModule != null) {
                        String state = AppProjectConfExt.I18N.getString("$ModulesSection.state." + iModule.getState().name());
                        if (iModule.getDownError() != null) {
                            return state + ": " + iModule.getDownError().getLocalizedMessage();
                        } else {
                            return state;
                        }
                    } else {
                        return AppProjectConfExt.I18N.getString("ModulesSection.state.NoRTModule"); //$NON-NLS-1$
                    }
                }
                return AppProjectConfExt.I18N.getMessage("ModulesSection.state.NoGModule", element); //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        
        TableViewerColumn licenseColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
        licenseColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.LicenseColumn")); //$NON-NLS-1$
        licenseColumn.getColumn().setWidth(200);
        licenseColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof GModule) {
                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
                    if (iModule != null) {
                        final ILicenseInfos licenseInfos = iModule.getLicenseInfos();
                        if (licenseInfos != null) {
                            Date date = licenseInfos.getDate();
                            if (date != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat(
                                        AppProjectConfExt.I18N.getString("ModulesSection.License.DateFormat"));
                                return AppProjectConfExt.I18N.getMessage("$ModulesSection.License." + licenseInfos.getStatus().name() + ".limited", sdf.format(date));
                            } else {
                                return AppProjectConfExt.I18N.getMessage("$ModulesSection.License." + licenseInfos.getStatus().name() + ".unlimited");
                            }
                        } else {
                            AppProjectConfExt.LOG.warning("'" + iModule.getName() + "' (" + iModule.getClass().getName()
                                    + ") module has no license info (state=" + iModule.getState() + ")");
                        }
                    }
                }
                return AppProjectConfExt.I18N.getString("$ModulesSection.License.UNDEFINED.unlimited"); //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                return null;
            }
        });
        
        TableViewerColumn compatibilityColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
        compatibilityColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.CompatibilityColumn")); //$NON-NLS-1$
        compatibilityColumn.getColumn().setWidth(200);
        compatibilityColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof GModule) {
                    IModuleHandle mh = ((GModule) element).getModuleHandle();
                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
                    case COMPATIBLE:
                        return AppProjectConfExt.I18N.getString("ModulesSection.Compatible");
                    case FULLYCOMPATIBLE:
                        return AppProjectConfExt.I18N.getString("ModulesSection.FullyCompatible");
                    case MODELIO_TOO_OLD:
                        return AppProjectConfExt.I18N.getString("ModulesSection.ModelioTooOld");
                    case MODULE_TOO_OLD:
                        return AppProjectConfExt.I18N.getString("ModulesSection.ModuleTooOld");
                    default:
                        break;
                    }
                }
                return "";
            }
        
            @Override
            public Color getForeground(Object element) {
                if (element instanceof GModule) {
                    IModuleHandle mh = ((GModule) element).getModuleHandle();
                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
                    case COMPATIBLE:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
                    case FULLYCOMPATIBLE:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
                    case MODELIO_TOO_OLD:
                    case MODULE_TOO_OLD:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
                    default:
                        break;
                    }
                }
                return super.getForeground(element);
            }
        });
        this.modulesTable.setInput(null);
        
        this.modulesTable.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setInput(getProjectAdapter());
            }
        });
        
        ColumnViewerToolTipSupport.enableFor(this.modulesTable);
        
        // The buttons composite
        Composite btnPanel = toolkit.createComposite(composite, SWT.NONE);
        GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
        btnPanel.setLayoutData(gd2);
        btnPanel.setLayout(new GridLayout(1, false));
        
        // The add button
        this.addButton = toolkit.createButton(btnPanel, "", SWT.PUSH);
        this.addButton.setText(AppProjectConfExt.I18N.getString("ModulesSection.AddModuleButtonLabel"));
        this.addButton.setToolTipText(AppProjectConfExt.I18N.getString("ModulesSection.AddModuleButtonToolTip"));
        this.addButton.setEnabled(false);
        this.addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        this.addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent evt) {
                addSelectedModules(promptUserModuleSeletion());
            }
        });
        
        // The delete button
        this.removeButton = toolkit.createButton(btnPanel, "", SWT.PUSH); //$NON-NLS-1$
        this.removeButton.setText(AppProjectConfExt.I18N.getString("ModulesSection.RemoveModuleButtonLabel"));
        this.removeButton.setToolTipText(AppProjectConfExt.I18N.getString("ModulesSection.RemoveModuleButtonToolTip"));
        this.removeButton.setEnabled(false);
        this.removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
        this.removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent evt) {
                AppProjectConfExt.LOG.debug("Remove a module"); //$NON-NLS-1$
        
                ModuleRemovalConfirmationDialog confirmDlg = new ModuleRemovalConfirmationDialog(
                        ModulesSection.this.removeButton.getShell());
                confirmDlg.setBlockOnOpen(true);
                if (confirmDlg.open() == 0) {
                    for (TableItem item : table.getSelection()) {
                        final GModule module = (GModule) item.getData();
                        try {
                            ModulesSection.this.moduleService.removeModule(module);
                        } catch (ModuleException e) {
                            MessageDialog.openError(null,
                                    AppProjectConfExt.I18N.getMessage("ModulesSection.UnableToRemoveModule", module.getName()),
                                    e.getLocalizedMessage() + "\n" + (e.getCause() != null ? e.getCause().getLocalizedMessage() : ""));
                            AppProjectConfExt.LOG.debug(e);
                        } catch (AccessDeniedException e) {
                            MessageDialog.openError(null, AppProjectConfExt.I18N.getMessage("ModulesSection.UnableToRemoveModule", module.getName()), e.getLocalizedMessage());
                            AppProjectConfExt.LOG.debug(e);
                        }
                    }
                }
                setInput(getProjectAdapter());
            }
        });
        
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    /**
     * Add a change listener to the modules table
     * @param listener the listener.
     */
    @objid ("da615b84-d28c-4781-812b-160d96770d41")
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        this.modulesTable.addSelectionChangedListener(listener);
    }

    @objid ("518c552c-6457-458f-ba33-baa7b6c3b047")
    ProjectModel getProjectAdapter() {
        return ModulesSection.this.projectModel;
    }

    @objid ("d84a9b96-1bd5-46ca-baec-b4c1aa6c9871")
    private void addSelectedModules(List<IModuleHandle> selectedModules) {
        // Sanity check
        if (!getProjectAdapter().isLocalProject()) {
            return;
        }
        
        // Among the selected modules keep only the more recent versions.
        final Map<String, IModuleHandle> modulesToAdd = new HashMap<>();
        for (IModuleHandle mh : selectedModules) {
            IModuleHandle moduleToCompare = modulesToAdd.get(mh.getName());
            if (moduleToCompare == null || mh.getVersion().isNewerThan(moduleToCompare.getVersion())) {
                modulesToAdd.put(mh.getName(), mh);
            }
        }
        
        addMissingDependencies(modulesToAdd);
        
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
        
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        
                if (AppProjectConfExt.LOG.isDebugEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Add module(s):\n");//$NON-NLS-1$
                    for (IModuleHandle h : selectedModules) {
                        sb.append(" - ");
                        sb.append(h.getName());
                        sb.append("  v");
                        sb.append(h.getVersion());
                        sb.append("  (");
                        sb.append(h.getDependencies().size() + h.getWeakDependencies().size());
                        sb.append("  deps)\n");
                    }
                    AppProjectConfExt.LOG.debug(sb.toString());
                }
        
                GProject openedProject = ModulesSection.this.projectService.getOpenedProject();
                AddModuleHelper.run(openedProject, ModulesSection.this.moduleService, modulesToAdd.values(), monitor, getProjectAdapter());
            }
        };
        try {
            this.progressService.run(false, false, runnable);
            setInput(getProjectAdapter());
        } catch (InvocationTargetException | InterruptedException e) {
            AppProjectConfExt.LOG.error(e);
        }
    }

    /**
     * Analyze the module list and adds all required modules to it by taking the latest version in the catalog.
     */
    @objid ("4a27fdb5-3251-4491-a726-68f26cff85f9")
    private void addMissingDependencies(Map<String, IModuleHandle> modulesToAdd) {
        // Compute all missing dependencies
        List<VersionedItem<?>> dependencies = new ArrayList<>();
        
        for (IModuleHandle module : modulesToAdd.values()) {
            for (VersionedItem<?> dep : module.getDependencies()) {
                if (!modulesToAdd.containsKey(dep.getName())) {
                    dependencies.add(dep);
                }
            }
        }
        
        if (!dependencies.isEmpty()) {
            IModuleRTCache cache = this.projectModel.getOpenedProject().getModuleCache();
            try {
                for (VersionedItem<?> moduleId : dependencies) {
                    final IModuleHandle latestModule = cache.findModule(moduleId.getName(), null, null);
                    if (latestModule != null && !latestModule.getVersion().isOlderThan(moduleId.getVersion())) {
                        modulesToAdd.put(moduleId.getName(), latestModule);
                    } else {
                        // At least one module is missing, let the deployment
                        // phase pop an error.
                        return;
                    }
                }
        
                // Make sure the module list is complete
                addMissingDependencies(modulesToAdd);
            } catch (IOException e) {
                // log & let the deployment phase pop an error.
                AppProjectConfExt.LOG.warning(e);
            }
        }
    }

    @objid ("387905c9-f7e6-4156-9f31-6ea58770247b")
    private List<IModuleHandle> promptUserModuleSeletion() {
        ModuleSelectDialog dlg = new ModuleSelectDialog(this.addButton.getShell(), this.catalog, this.projectModel.getOpenedProject(), this.progressService);
        dlg.open();
        return dlg.getSelectedModules();
    }

    @objid ("6a7f385f-fa3e-4d6c-af88-573cf0bc9c18")
    private static class AddModuleHelper {
        @objid ("1fd29654-21c1-4873-b1c2-60a226d699a3")
        public static void run(final GProject project, final IModuleManagementService moduleService, Collection<IModuleHandle> modules, IProgressMonitor monitor, ProjectModel projectAdapter) {
            // Sort the module according to their dependencies
            List<IModuleHandle> sortedModules;
            try {
                sortedModules = ModuleSorter.sortHandles(modules);
            
                if (AppProjectConfExt.LOG.isDebugEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("AddModuleHelper: Installing module(s):\n");//$NON-NLS-1$
                    for (IModuleHandle h : sortedModules) {
                        sb.append(" - ");
                        sb.append(h.getName());
                        sb.append("  v");
                        sb.append(h.getVersion());
                        sb.append("  (");
                        sb.append(h.getDependencies().size() + h.getWeakDependencies().size());
                        sb.append("  deps)\n");
                    }
                    AppProjectConfExt.LOG.debug(sb.toString());
                }
            
            } catch (CyclicDependencyException e) {
                // Error dialog
                AppProjectConfExt.LOG.debug(e);
                MessageDialog.openError(null,
                        AppProjectConfExt.I18N.getString("ModulesSection.ModuleInstallationErrorTitle"),
                        e.getLocalizedMessage());
                return;
            }
            
            int sum = sortedModules.size();
            monitor.beginTask(AppProjectConfExt.I18N.getString("ModulesSection.AddModulesProgressTitle"), sum);
            List<String> invalidIds = AddModuleHelper.getExistFragmentIdList(projectAdapter);
            for (int i = 0; i < sum; i++) {
                final IModuleHandle module = sortedModules.get(i);
                if (invalidIds.contains(module.getName())) {
                    MessageDialog.openError(null,
                            AppProjectConfExt.I18N.getString("ModulesSection.ModuleInstallationErrorTitle"),
                            AppProjectConfExt.I18N.getMessage("ModulesSection.ModuleInstallationErrorMessage.NameExistAlready", module.getName()));
                    return;
                }
            
                monitor.subTask(AppProjectConfExt.I18N.getMessage("ModulesSection.AddModulesProgressSubTask",
                        String.valueOf(i + 1), String.valueOf(sum), module.getName()));
                monitor.worked(1);
            
                try (ITransaction t = project.getSession().getTransactionSupport().createTransaction("install a module")) { //$NON-NLS-1$
                    moduleService.installModule(null, project, module, module.getArchive() != null ? module.getArchive().toUri() : null);
                    t.commit();
                } catch (ModuleException e) {
                    // Error dialog
                    AppProjectConfExt.LOG.debug(e);
                    MessageDialog.openError(null,
                            AppProjectConfExt.I18N.getString("ModulesSection.ModuleInstallationErrorTitle"),
                            e.getMessage());
                }
            }
            monitor.done();
        }

        /**
         * get existing model fragment identifiers, excepted MDA ones.
         * @param projectAdapter a project
         * @return existing model fragments identifiers
         */
        @objid ("b03e8915-397b-4e81-af86-4450dd811c66")
        private static List<String> getExistFragmentIdList(ProjectModel projectAdapter) {
            List<String> fragmentIds = new ArrayList<>();
            List<IProjectFragment> fragments = projectAdapter.getAllFragments();
            for (IProjectFragment fragment : fragments) {
                if (fragment.getType() != FragmentType.MDA) {
                    fragmentIds.add(fragment.getId());
                }
            }
            return fragmentIds;
        }

    }

}
