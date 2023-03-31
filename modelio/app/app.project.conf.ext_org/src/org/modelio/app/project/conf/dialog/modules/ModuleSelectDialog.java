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
package org.modelio.app.project.conf.dialog.modules;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.module.catalog.catalog.ModuleCatalogPanel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.progress.IModelioProgressService;

/**
 * Module catalog dialog.
 */
@objid ("6ed29531-b75e-4aac-af8c-ae48b16d1a0b")
public class ModuleSelectDialog extends ModelioDialog {
    @objid ("e515deb2-2259-42d0-8b19-a457f53cf25c")
    ModuleCatalogPanel panel;

    @objid ("49d076d9-17ea-42c3-b84d-f9fe2827e9ec")
    Controller controller;

    @objid ("cde84b3d-2a6f-47ce-ae17-b96e548cfdc7")
    protected IModelioProgressService progressService;

    /**
     * @param parentShell a SWT shell
     * @param progressService a progress service.
     */
    @objid ("11c6cddf-7d46-4702-acd4-ea8995a668a2")
    public  ModuleSelectDialog(Shell parentShell, IModuleStore catalog, IGProject gProject, IModelioProgressService progressService) {
        super(parentShell);
        this.controller = new Controller(this);
        this.panel = new ModuleCatalogPanel(catalog);
        this.progressService = progressService;
        
    }

    @objid ("2b9bd32f-ba87-4d7d-973b-6bfacfee3e2b")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, Window.OK, AppProjectConfExt.I18N.getString("ModuleSelectDialog.AddToProject"), false)
                .setEnabled(false);
        createButton(parent, Window.CANCEL, AppProjectConfExt.I18N.getString("ModuleSelectDialog.Cancel"), true);
        
    }

    @objid ("5ae0e22d-5fa7-4003-bf60-739906a84146")
    @Override
    public void init() {
        getShell().setText(AppProjectConfExt.I18N.getString("ModuleSelectDialog.ShellTitle")); //$NON-NLS-1$ );
        setTitle(AppProjectConfExt.I18N.getString("ModuleSelectDialog.Title")); //$NON-NLS-1$
        setMessage(AppProjectConfExt.I18N.getString("ModuleSelectDialog.Message")); //$NON-NLS-1$
        
        // Position and resize dialog shell
        int width = 800;
        int height = 600;
        
        Rectangle refBounds = getShell().getParent().getBounds();
        getShell().setMinimumSize(width, height);
        getShell().layout(true);
        
        getShell().setBounds(refBounds.x + ((refBounds.width - width) / 2),
                refBounds.y + ((refBounds.height - height) / 2), width, height);
        
    }

    @objid ("16ef9213-c017-4e3b-a859-653f58d8e18c")
    @Override
    public Control createContentArea(Composite parent) {
        // Top level container
        Composite top = new Composite(parent, SWT.BORDER);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        top.setLayout(new FormLayout());
        
        FormData fd = null;
        
        // List of modules from catalog: use a ModuleCatalogPanel
        this.panel.createPanel(top);
        Composite panelComposite = (Composite) this.panel.getPanel();
        fd = new FormData();
        fd.top = new FormAttachment(0, 4);
        fd.left = new FormAttachment(0, 4);
        fd.bottom = new FormAttachment(100, -4);
        fd.right = new FormAttachment(100, -4);
        panelComposite.setLayoutData(fd);
        
        this.panel.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                ModuleSelectDialog.this.controller.onModuleSelection(selection);
            }
        });
        
        this.controller.init();
        return top;
    }

    @objid ("40874587-7a55-43b3-9f83-f00ed07bbd02")
    @Override
    protected String getHelpId() {
        return AppProjectConfExt.I18N.getString("ModuleSelectDialog.HELP_TOPIC");
    }

    @objid ("71018a7a-f2f0-4a27-a772-d97139b2b7bd")
    @Override
    protected void okPressed() {
        super.okPressed();
    }

    @objid ("847fe520-f14a-4479-89be-7847e57429b0")
    public List<IModuleHandle> getSelectedModules() {
        return this.controller.selectedModules;
    }

    @objid ("68445400-b881-4e02-9403-ee1c56b646d8")
    @Override
    protected void cancelPressed() {
        this.controller.selectedModules.clear();
        super.cancelPressed();
        
    }

    @objid ("eb07fb20-b60b-4b36-b8ca-53fab47a500f")
    private class Controller {
        @objid ("28ea47e9-9c3c-4f5d-9657-cbbbdcf0f800")
        private final ModuleSelectDialog dlg;

        @objid ("98157e4d-0e69-4226-aaf3-77aa30e268e9")
        private ArrayList<IModuleHandle> selectedModules = new ArrayList<>();

        @objid ("0465e24b-1ffa-4935-86cd-7938652c01a9")
        public  Controller(ModuleSelectDialog dlg) {
            this.dlg = dlg;
        }

        @objid ("eb0d15d8-3c34-4f99-8037-5c478d32cfb6")
        public void init() {
            // this.dlg.deleteButton.setEnabled(false);
        }

        @objid ("a5acd151-b460-44b9-9b5e-9b60bb1bce81")
        public void onModuleSelection(IStructuredSelection selection) {
            if (selection.isEmpty()) {
                return;
            }
            
            this.selectedModules.clear();
            for (Object obj : selection.toList()) {
                if (obj instanceof IModuleHandle) {
                    this.selectedModules.add((IModuleHandle) obj);
                }
            }
            
            this.dlg.getButton(Window.OK).setEnabled(!this.selectedModules.isEmpty());
            
        }

        @objid ("301a99d0-208e-4695-9842-e0931bda2649")
        public void onAddModule(final List<File> modules) {
            final IModuleStore catalog = this.dlg.panel.getInput();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(AppProjectConfExt.I18N.getString("ModuleSelectDialog.AddModulesProgressTitle"),
                            modules.size() * 3);
                    for (int i = 0; i < modules.size(); i++) {
                        File mdacFile = modules.get(i);
                        // Keys {0}:counter {1}:sum of modules {2}:module file
                        // name
                        monitor.subTask(AppProjectConfExt.I18N.getMessage("ModuleSelectDialog.AddModulesProgressSubTask",
                                String.valueOf(i + 1), String.valueOf(modules.size()), mdacFile.getName()));
                        monitor.worked(2);
                        try {
                            catalog.installModuleArchive(mdacFile.toPath(), null);
                            monitor.worked(1);
                        } catch (IOException e) {
                            AppProjectConfExt.LOG.error(e);
                        }
                    }
                    monitor.done();
                }
            };
            try {
                ModuleSelectDialog.this.progressService.run(true, false, runnable);
                this.dlg.panel.setInput(catalog);
            } catch (InvocationTargetException | InterruptedException e) {
                AppProjectConfExt.LOG.error(e);
            }
            
        }

        @objid ("781865dc-68c0-4b5b-8e4d-9162e054e9e4")
        public void onDeleteModule(final IStructuredSelection selection) {
            final IModuleStore catalog = this.dlg.panel.getInput();
            final boolean isShowLatestOnly = this.dlg.panel.isShowLatestOnly();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(AppProjectConfExt.I18N.getString("ModuleSelectDialog.RemoveModulesProgressTitle"),
                            selection.size() * 3);
                    List<IModuleHandle> modules = new ArrayList<>();
                    for (Object obj : selection.toList()) {
                        if (obj instanceof IModuleHandle) {
                            modules.add((IModuleHandle) obj);
            
                        }
                    }
                    for (int i = 0; i < modules.size(); i++) {
                        IModuleHandle module = modules.get(i);
                        // Keys {0}:counter {1}:sum of modules {2}:module name
                        // {3}:module version
                        monitor.subTask(AppProjectConfExt.I18N.getMessage("ModuleSelectDialog.RemoveModulesProgressSubTask", String.valueOf(i + 1), String.valueOf(modules.size()), module.getName(), module.getVersion().toString()));
                        monitor.worked(2);
                        try {
                            if (!isShowLatestOnly) {
                                catalog.removeModule(module);
                            } else {
                                for (IModuleHandle m : catalog.findModule(module.getName(), null)) {
                                    catalog.removeModule(m);
                                }
                            }
                            monitor.worked(1);
                        } catch (IOException e) {
                            AppProjectConfExt.LOG.error(e);
                        }
                    }
                    monitor.done();
                }
            };
            try {
                ModuleSelectDialog.this.progressService.run(true, false, runnable);
                this.dlg.panel.setInput(catalog);
            } catch (InvocationTargetException | InterruptedException e) {
                AppProjectConfExt.LOG.error(e);
            }
            
        }

    }

}
