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
package org.modelio.app.module.catalog.catalog.update;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.module.catalog.plugin.AppModules;
import org.modelio.app.update.checker.dialog.UpdatePanelDataModel;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.platform.update.repo.UpdateChecker;
import org.modelio.platform.update.repo.UpdateDescriptor;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;

@objid ("5b118fb5-aa94-438c-ac87-c91fdcffe35d")
public class ModuleUpdater {
    @objid ("17d0bb77-9f68-4088-992d-1ef90f943155")
    protected boolean validUpdateSite;

    @objid ("ec8eb56f-95fb-4348-a95c-2ba090b06a35")
    protected List<UpdateDescriptor> modulesToUpdate = new ArrayList<>();

    @objid ("95eec14f-6c2f-4e51-ade5-720c1da22bfa")
    public void updateModule(IModuleStore catalog, Shell parentShell, IModelioProgressService progressService) {
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
        
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                List<IModuleHandle> modules;
                try {
                    SubProgress mon = ModelioProgressAdapter.convert(monitor, AppModules.I18N.getString("ModuleCatalogDialog.UpdateProgressTitle"), 5);
                    modules = catalog.findAllModules(mon.newChild(4));
        
                    // The initial referenceModules may include several versions of a given
                    // one...there is some clean up needed
                    final Map<String, Version> refModules = new HashMap<>();
                    for (final IModuleHandle refModule : modules) {
                        final String key = refModule.getName();
                        if (!refModules.containsKey(key) || (refModules.containsKey(key) && refModules.get(key).isOlderThan(refModule.getVersion()))) {
                            refModules.put(refModule.getName(), refModule.getVersion());
                        }
                    }
        
                    UpdateChecker checker = new UpdateChecker();
                    ModuleUpdater.this.modulesToUpdate = checker.getModuleUpdates(refModules, false /* strict */);
                    ModuleUpdater.this.validUpdateSite = true;
                    monitor.worked(1);
                    monitor.done();
                } catch (IOException e) {
                    ModuleUpdater.this.validUpdateSite = false;
                    throw new InvocationTargetException(e, FileUtils.getLocalizedMessage(e));
                }
            }
        };
        
        try {
            progressService.run(true, false, runnable);
        
            if (this.validUpdateSite) {
                // New modules found: open the update dialog
                if (this.modulesToUpdate != null && !this.modulesToUpdate.isEmpty()) {
                    UpdatePanelDataModel dataModel = new UpdatePanelDataModel(this.modulesToUpdate);
        
                    ModuleUpdateDialog dialog = new ModuleUpdateDialog(parentShell, dataModel);
                    int ret = dialog.open();
                    if (ret == Window.OK) {
                        executeUpdate(dataModel.getSelectedUpdates(), catalog, progressService);
                    }
                } else {
                    MessageDialog.openInformation(parentShell, AppModules.I18N.getString("ModuleUpdateDialog.Title"), AppModules.I18N.getString("ModuleUpdateDialog.NoUpdate"));
                }
            }
        } catch (InvocationTargetException e) {
            AppModules.LOG.error(e);
            MessageDialog.openError(parentShell, AppModules.I18N.getString("ModuleUpdateDialog.Title"), e.getLocalizedMessage());
        } catch (InterruptedException e) {
            AppModules.LOG.info(e);
        }
        
    }

    @objid ("7d579c29-ee17-4910-8853-638fc19af8ad")
    private void executeUpdate(Collection<UpdateDescriptor> selectedModules, IModuleStore catalog, IModelioProgressService progressService) {
        final IRunnableWithProgress runnable = new IRunnableWithProgress() {
        
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        
                final int modulesToUpdateSum = selectedModules.size();
                monitor.beginTask(AppModules.I18N.getString("ModuleUpdateDialog.UpdateProgressTitle"), modulesToUpdateSum * 5);
                int i = 0;
                for (UpdateDescriptor desc : selectedModules) {
                    if (monitor.isCanceled()) {
                        break; // if monitor is canceled
                    }
        
                    // Keys {0}:counter {1}:sum of modules {2}:module file name
                    monitor.subTask(AppModules.I18N.getMessage("ModuleUpdateDialog.UpdateModulesProgressSubTask", String.valueOf(i + 1), String.valueOf(modulesToUpdateSum), desc.getLabel()));
                    monitor.worked(1);
        
                    try (UriPathAccess pathAccess = new UriPathAccess(URIUtil.fromString(desc.getDownloadLink()), null)) {
                        final Path path = pathAccess.getPath();
                        monitor.worked(2);
                        // install in catalog
                        catalog.installModuleArchive(path, null);
                        monitor.worked(1);
                        pathAccess.close();
                    } catch (IOException | URISyntaxException e) {
                        AppModules.LOG.error("Unable to install module " + desc.getDownloadLink());
                        AppModules.LOG.debug(e);
                    }
                    monitor.worked(1);
        
                    i++;
                }
                monitor.done();
            }
        };
        try {
            progressService.run(true, true, runnable);
        } catch (InvocationTargetException | InterruptedException e) {
            AppModules.LOG.debug(e);
        }
        
    }

}
