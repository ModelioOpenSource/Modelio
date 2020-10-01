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

package org.modelio.app.module.catalog.catalog;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.module.catalog.catalog.update.ModuleUpdater;
import org.modelio.app.module.catalog.plugin.AppModules;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.progress.IModelioProgressService;

/**
 * Module catalog dialog.
 */
@objid ("9749de58-5677-4846-9609-115da4110982")
public class ModuleCatalogDialog extends ModelioDialog {
    @objid ("f3a7ed6b-536c-48a1-aaa4-1952106a65f6")
     ModuleCatalogPanel panel;

    @objid ("7ecefd9c-de00-4dbf-a886-80a297eebcac")
     Controller controller;

    @objid ("26c59f6e-9f3f-43b4-8f62-b457b10e6398")
     Button deleteButton;

    @objid ("a6e50e09-f595-43d6-8d3a-dc34defb66a4")
    private Button addButton;

    @objid ("1f54c94d-39f4-4a00-b1e8-d4ce6a93cf0a")
    private Button updateButton;

    @objid ("99e7d0b1-e6c3-4436-8322-9895e7173022")
    protected IModelioProgressService progressService;

    @objid ("6e9e7aab-2f96-42bd-9d8e-0cf8336e90c9")
    private Image addToCatalogImage = AppModules.getImageDescriptor("icons/addtocatalog.png").createImage();

    @objid ("21e0aa76-c8f5-4d0d-bc34-38fb218ee614")
    private Image removeFromCatalogImage = AppModules.getImageDescriptor("icons/removefromcatalog.png").createImage();

    @objid ("1d570ff9-56ff-496e-98b6-f90d01029dfc")
    private Image updateCatalogImage = AppModules.getImageDescriptor("icons/updatecatalog.png").createImage();

    /**
     * @param parentShell a SWT shell
     * @param moduleCatalog the module catalog.
     * @param progressService a progress service.
     */
    @objid ("6cfffb59-1769-4820-8416-4db028b5bdd1")
    public ModuleCatalogDialog(Shell parentShell, IModuleStore moduleCatalog, IModelioProgressService progressService) {
        super(parentShell);
        this.controller = new Controller(this);
        this.panel = new ModuleCatalogPanel(moduleCatalog);
        this.progressService = progressService;
    }

    @objid ("32b61d9a-27a5-4958-ba01-5b29a8498a38")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, Window.OK, AppModules.I18N.getString("ModuleCatalogDialog.Close"), true);
    }

    @objid ("b9235e21-2252-4c21-aeb9-e813547e1c08")
    @Override
    public void init() {
        getShell().setText(AppModules.I18N.getString("ModuleCatalogDialog.ShellTitle")); //$NON-NLS-1$ );
        setTitle(AppModules.I18N.getString("ModuleCatalogDialog.Title")); //$NON-NLS-1$
        setMessage(AppModules.I18N.getString("ModuleCatalogDialog.Message")); //$NON-NLS-1$
        
        // Position and resize dialog shell
        int width = 800;
        int height = 600;
        
        Rectangle refBounds = getShell().getParent().getBounds();
        getShell().setMinimumSize(width, height);
        getShell().layout(true);
        
        getShell().setBounds(refBounds.x + ((refBounds.width - width) / 2), refBounds.y + ((refBounds.height - height) / 2), width, height);
    }

    @objid ("789b01b9-e296-4aa2-808e-89ad5bcfa5c8")
    @Override
    public Control createContentArea(Composite parent) {
        // Top level container
        Composite top = new Composite(parent, SWT.NULL);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        top.setLayout(new FormLayout());
        
        FormData fd = null;
        
        // List of modules from catalog: use a ModuleCatalogPanel
        this.panel.createPanel(top);
        Composite panelComposite = (Composite) this.panel.getPanel();
        fd = new FormData();
        fd.top = new FormAttachment(0, 4);
        fd.left = new FormAttachment(0, 4);
        fd.bottom = new FormAttachment(80, 0);
        fd.right = new FormAttachment(100, -4);
        panelComposite.setLayoutData(fd);
        
        this.panel.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                ModuleCatalogDialog.this.controller.onModuleSelection(selection);
            }
        });
        
        Composite buttonLine = new Composite(top, SWT.NONE);
        fd = new FormData();
        fd.top = new FormAttachment(panelComposite, 10, SWT.BOTTOM);
        fd.left = new FormAttachment(0, 0);
        fd.right = new FormAttachment(100, 0);
        final FillLayout layout = new FillLayout();
        layout.spacing = 5;
        layout.marginWidth = 5;
        buttonLine.setLayout(layout);
        buttonLine.setLayoutData(fd);
        
        // Install mdac file button
        this.addButton = new Button(buttonLine, SWT.PUSH);
        this.addButton.setText(AppModules.I18N.getString("ModuleCatalogDialog.Add"));
        this.addButton.setImage(this.addToCatalogImage);
        
        this.addButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final List<File> mdacs = ModuleCatalogDialog.promptUserForFiles();
                ModuleCatalogDialog.this.controller.onAddModule(mdacs);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                //
            }
        });
        
        // Delete mdac file button
        this.deleteButton = new Button(buttonLine, SWT.PUSH);
        this.deleteButton.setText(AppModules.I18N.getString("ModuleCatalogDialog.Delete"));
        this.deleteButton.setImage(this.removeFromCatalogImage);
        
        this.deleteButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection) ModuleCatalogDialog.this.panel.getViewer().getSelection();
                ModuleCatalogDialog.this.controller.onDeleteModule(selection);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Nothing to do
            }
        });
        
        this.updateButton = new Button(buttonLine, SWT.PUSH);
        this.updateButton.setText(AppModules.I18N.getString("ModuleCatalogDialog.CheckUpdate"));
        
        this.updateButton.setImage(this.updateCatalogImage);
        this.updateButton.addSelectionListener(new SelectionListener() {
        
            @Override
            public void widgetSelected(SelectionEvent evt) {
                final IModuleStore catalog = ModuleCatalogDialog.this.panel.getInput();
                new ModuleUpdater().updateModule(catalog, getShell(), ModuleCatalogDialog.this.progressService);
        
                // Update catalog list
                ModuleCatalogDialog.this.panel.setInput(catalog);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Nothing to do
        
            }
        });
        this.controller.init();
        return top;
    }

    @objid ("efe0bcc1-1d0c-42af-8897-b08ecd8c4f68")
    protected static List<File> promptUserForFiles() {
        List<File> returnedFiles = new ArrayList<>();
        
        Shell parentShell = Display.getDefault().getActiveShell();
        FileDialog dialog = new FileDialog(parentShell, SWT.OPEN | SWT.MULTI);
        
        // Find out the module store path
        File moduleStore = new File(Platform.getInstallLocation().getURL().getFile(), "modules"); //$NON-NLS-1$
        
        // Configure the dialog
        dialog.setFilterPath(moduleStore.getAbsolutePath());
        dialog.setFilterNames(new String[] { AppModules.I18N.getString("MDAComponents") }); //$NON-NLS-1$
        dialog.setFilterExtensions(new String[] { "*.jmdac" }); //$NON-NLS-1$
        
        // Open the dialog and get the answer
        String firstFile = dialog.open();
        
        if (firstFile != null) {
            String path = (new File(firstFile)).getParent();
            for (String fname : dialog.getFileNames()) {
                try {
                    File file = new File(path, fname);
                    if (file.getName().indexOf(".jmdac") != -1) { //$NON-NLS-1$
                        // J MDA component
                        returnedFiles.add(file);
                    }
                } catch (RuntimeException e) {
                    AppModules.LOG.error(e);
                }
            }
        }
        return returnedFiles;
    }

    @objid ("72066743-b215-444c-b529-f112c87d490e")
    @Override
    protected String getHelpId() {
        return AppModules.I18N.getString("ModuleCatalogDialog.HELP_TOPIC");
    }

    @objid ("a253e519-3327-4efe-83c0-6633f9677b3f")
    @Override
    public boolean close() {
        this.addButton.setImage(null);
        this.addToCatalogImage.dispose();
        this.updateButton.setImage(null);
        this.updateCatalogImage.dispose();
        this.deleteButton.setImage(null);
        this.removeFromCatalogImage.dispose();
        return super.close();
    }

    @objid ("ae36d6dc-3d5f-485a-83ae-bc8e08b28047")
    private class Controller {
        @objid ("3d44d353-74f5-4bdd-93c4-f069900cca5a")
        private final ModuleCatalogDialog dlg;

        @objid ("e5cf273a-bd49-442d-b527-9cdcae5bc088")
        public Controller(ModuleCatalogDialog dlg) {
            this.dlg = dlg;
        }

        @objid ("91ed90fd-b1e4-40b4-b27d-e309122b2c36")
        public void init() {
            this.dlg.deleteButton.setEnabled(false);
        }

        @objid ("7081a398-bdd8-4825-809c-4716a0f2deb2")
        public void onModuleSelection(IStructuredSelection selection) {
            List<IModuleHandle> selectedModules = new ArrayList<>();
            for (Object obj : selection.toList()) {
                if (obj instanceof IModuleHandle) {
                    selectedModules.add((IModuleHandle) obj);
                }
            }
            this.dlg.deleteButton.setEnabled(!selectedModules.isEmpty());
        }

        @objid ("a7c63e2e-2d9a-42a6-9bb1-ee2e04e21f15")
        public void onAddModule(final List<File> modules) {
            final IModuleStore catalog = this.dlg.panel.getInput();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(AppModules.I18N.getString("ModuleCatalogDialog.AddModulesProgressTitle"), modules.size() * 3);
                    for (int i = 0; i < modules.size(); i++) {
                        File mdacFile = modules.get(i);
                        // Keys {0}:counter {1}:sum of modules {2}:module file
                        // name
                        monitor.subTask(AppModules.I18N.getMessage("ModuleCatalogDialog.AddModulesProgressSubTask", String.valueOf(i + 1), String.valueOf(modules.size()), mdacFile.getName()));
                        monitor.worked(2);
                        try {
                            catalog.installModuleArchive(mdacFile.toPath(), null);
                            monitor.worked(1);
                        } catch (IOException e) {
                            AppModules.LOG.error(e);
                        }
                    }
                    monitor.done();
                }
            };
            try {
                ModuleCatalogDialog.this.progressService.run(true, false, runnable);
                this.dlg.panel.setInput(catalog);
            } catch (InvocationTargetException | InterruptedException e) {
                AppModules.LOG.error(e);
            }
        }

        @objid ("e912f6d4-c4c9-47b5-8067-a8952552f828")
        public void onDeleteModule(final IStructuredSelection selection) {
            final IModuleStore catalog = this.dlg.panel.getInput();
            final boolean isShowLatestOnly = this.dlg.panel.isShowLatestOnly();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(AppModules.I18N.getString("ModuleCatalogDialog.RemoveModulesProgressTitle"), selection.size() * 3);
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
                        monitor.subTask(AppModules.I18N.getMessage("ModuleCatalogDialog.RemoveModulesProgressSubTask", String.valueOf(i + 1), String.valueOf(modules.size()), module.getName(), module.getVersion().toString()));
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
                            AppModules.LOG.error(e);
                        }
                    }
                    monitor.done();
                }
            };
            try {
                ModuleCatalogDialog.this.progressService.run(true, false, runnable);
                this.dlg.panel.setInput(catalog);
            } catch (InvocationTargetException | InterruptedException e) {
                AppModules.LOG.error(e);
            }
        }

    }

}
