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

package org.modelio.app.project.core.services;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.module.ModuleSorter;
import org.modelio.mda.infra.service.CompatibilityHelper.CompatibilityLevel;
import org.modelio.mda.infra.service.CompatibilityHelper;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Updates all project modules to the last version found in the catalog.
 * <p>
 * Installs missing dependencies if needed.
 * 
 * @author cma
 */
@objid ("65fe3a1c-df77-4f64-9222-4e9fa028d73f")
public class ModulesUpdater {
    @objid ("b268d021-26e2-42ba-9273-130af1f43313")
    private static final String pluginId = AppProjectCore.PLUGIN_ID;

    @objid ("7cf2c371-4a20-4d55-b6de-3d047e0f26f6")
    private final GProject project;

    @objid ("5f651f9b-bd04-4dfd-84e1-c7ddc8fb3e6f")
    private final IModuleManagementService moduleSvc;

    @objid ("d2f4ad4c-77b1-4e94-bfc2-b5ab0397a544")
    private final IModuleStore modulesCatalog;

    @objid ("0f3ac3b1-d3ad-4da0-85be-18fea833c3d5")
    private final List<IStatus> results;

    /**
     * C'tor
     * 
     * @param project the project to work on.
     * @param withConfirmation whether to ask user for confirmation.
     */
    @objid ("26c9a208-ec68-472e-b5a8-667df7e45fca")
    public ModulesUpdater(IModuleManagementService moduleSvc, IModuleStore modulesCatalog, GProject project, boolean withConfirmation) {
        this.modulesCatalog = modulesCatalog;
        this.project = Objects.requireNonNull(project);
        this.moduleSvc = moduleSvc;
        this.results = new ArrayList<>();
    }

    /**
     * Update all modules to last version and add missing mandatory modules.
     * 
     * @param monitor a progress monitor
     */
    @objid ("481caa61-d023-4be9-8475-dbbbf9ba8cc9")
    public void run(IProgressMonitor monitor) {
        Collection<IModuleHandle> toInstall = new HashSet<>();
        SubProgress mon = ModelioProgressAdapter.convert(monitor, "Updating modules ...", this.project.getModules().size() * 2 + 1);
        for (GModule gModule : this.project.getModules()) {
            if (gModule.getScope() == DefinitionScope.LOCAL) {
                try {
                    IModuleHandle found = this.modulesCatalog.findModule(gModule.getName(), null, mon.newChild(1));
                    if (found != null && found.getVersion().isNewerThan(gModule.getVersion())) {
                        if (CompatibilityHelper.getCompatibilityLevel(found) == CompatibilityLevel.FULLYCOMPATIBLE) {
                            if (addNewDependencies(mon.newChild(1), found, toInstall)) {
                                toInstall.add(found);
                            }
                        }
                    }
                } catch (IOException e) {
                    this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, FileUtils.getLocalizedMessage(e), e));
                }
            }
        }
        
        // add mandatory modules too
        try {
            toInstall.addAll(getMissingMandatoryModules(mon.newChild(1)));
        } catch (IOException e) {
            this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, FileUtils.getLocalizedMessage(e), e));
        }
        
        installUpdates(toInstall, mon.newChild(10));
    }

    @objid ("dded2287-ac4f-4872-a614-9a90c0c374bb")
    public List<IStatus> getResults() {
        return this.results;
    }

    @objid ("cbff1ca2-55d2-4325-b296-450b11c7cbe8")
    private boolean addNewDependencies(SubProgress monitor, final IModuleHandle module, Collection<IModuleHandle> toInstall) {
        boolean ok = true;
        for (VersionedItem<?> dep : module.getDependencies()) {
            if (!isPresent(dep.getName())) {
                try {
                    IModuleHandle found = this.modulesCatalog.findModule(dep.getName(), null, monitor.newChild(1));
                    if (found != null) {
                        if (CompatibilityHelper.getCompatibilityLevel(found) == CompatibilityLevel.FULLYCOMPATIBLE) {
                            if (addNewDependencies(monitor, found, toInstall)) {
                                toInstall.add(found);
                            }
                        }
                    }
                } catch (IOException e) {
                    String msg = MessageFormat.format("Unable to get {0} dependency for {1} : {2}", dep.getName(), module.getName(),
                            FileUtils.getLocalizedMessage(e));
        
                    this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, msg, e));
        
                    ok = false;
                }
            }
        }
        return ok;
    }

    @objid ("fa306aff-95c2-4a4c-8a37-44cd4f136da3")
    private boolean isPresent(String moduleName) {
        for (GModule m : this.project.getModules()) {
            if (m.getName().equals(moduleName)) {
                return true;
            }
        }
        return false;
    }

    @objid ("70c79c06-f967-47c0-baa9-31125aa80427")
    private void installUpdates(Collection<IModuleHandle> toInstall, SubProgress monitor) {
        monitor.setWorkRemaining(toInstall.size());
        
        // Sort modules
        List<IModuleHandle> sorted;
        try {
            sorted = ModuleSorter.sortHandles(toInstall);
        } catch (CyclicDependencyException e) {
            this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, e.getLocalizedMessage(), e));
            sorted = new ArrayList<>(toInstall);
        }
        
        for (IModuleHandle h : sorted) {
            installUpdate(h, monitor.newChild(1));
        }
    }

    @objid ("e9bbc5f5-3cc2-41ce-b092-a38cc3ca3691")
    private void installUpdate(IModuleHandle found, SubProgress monitor) {
        try {
            monitor.subTask(MessageFormat.format("Installing {0} v{1} module ...",
                    found.getName(),
                    found.getVersion()));
        
            this.moduleSvc.installModule(monitor, this.project, found, null);
        
            String msg = MessageFormat.format("Installed {0} v{1} module.",
                    found.getName(),
                    found.getVersion());
            this.results.add(new Status(IStatus.OK, ModulesUpdater.pluginId, msg));
        
        } catch (ModuleException e) {
            String msg = MessageFormat.format("Unable to install {0} v{1} module : {2}",
                    found.getName(),
                    found.getVersion(),
                    e.getLocalizedMessage());
            this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, msg, e));
        
        }
    }

    /**
     * Install or update all mandatory modules.
     * @param monitor
     * @param monitorTicks
     */
    @objid ("6c13e3cb-5c56-4bbe-9173-d2f537fffa50")
    public void installMandatoryModules(IProgressMonitor monitor) {
        SubProgress mon = ModelioProgressAdapter.convert(monitor, "Checking mandatory modules ...", this.project.getModules().size() * 2);
        try {
            Collection<IModuleHandle> toInstall = getMissingMandatoryModules(mon.newChild(5));
        
            if (!toInstall.isEmpty()) {
                installUpdates(toInstall, mon.newChild(10));
            }
        
        } catch (IOException e) {
            this.results.add(new Status(IStatus.WARNING, ModulesUpdater.pluginId, FileUtils.getLocalizedMessage(e), e));
        }
    }

    @objid ("37d6ebe9-db51-46e0-b7a4-f5ba8400e770")
    private Collection<IModuleHandle> getMissingMandatoryModules(SubProgress mon) throws FileSystemException, IOException {
        List<IModuleHandle> toInstall = new ArrayList<>();
        
        for (IModuleHandle moduleHandle : this.modulesCatalog.findAllModules(mon)) {
            if (moduleHandle.isMandatory()) {
                IRTModule existing = this.moduleSvc.getModuleRegistry().getModule(new VersionedItem<>(moduleHandle.getName(), moduleHandle.getVersion()));
                if (existing == null || !Objects.equals(existing.getVersion(), moduleHandle.getVersion())) {
                    toInstall.add(moduleHandle);
                }
            }
        }
        return toInstall;
    }

}
