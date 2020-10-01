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

package org.modelio.platform.mda.infra.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IModuleRegistry;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Helper class to convert {@link GModule} into {@link IRTModule} or a name into a {@link GModule}, get the list of weak dependencies, etc.
 */
@objid ("2f013d2a-0233-11e2-9fca-001ec947c8cc")
public class ModuleResolutionHelper {
    /**
     * Returns whether the passed module is Version compatible with the passed reference Version.
     * <p>
     * Current strategy is to return <i>true</i>
     * if the module Version is equal or newer than the reference Version.
     * 
     * @param moduleVersion the Version to test for compatibility.
     * @param referenceVersion the reference version.
     * @return <i>true</i> if the passed module is version compatible with the reference.
     */
    @objid ("0fe5a024-67b6-4172-a32a-1cb93f3e3d9c")
    public static boolean isVersionCompatible(Version moduleVersion, Version referenceVersion) {
        return moduleVersion.isNewerThan(referenceVersion) || moduleVersion.equals(referenceVersion);
    }

    /**
     * Returns the first found GModule in the project which name match the passed name.
     * 
     * @param gProject the project to search into.
     * @param moduleName the name of the GModule to find.
     * @return the first found GModule in the project which name match the passed name or <code>null</code> if none found.
     */
    @objid ("fd69ce1d-5d5a-46c8-990a-f54f45d0dad7")
    private static GModule getGModuleByName(GProject gProject, String moduleName) {
        for (GModule module : gProject.getModules()) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        return null;
    }

    /**
     * Returns the list of GModule of the passed project that the passed GModule depends on
     * (i.e. the returned GModules are required as mandatory by the passed GModule).
     * 
     * @param gModule the GModule to look dependencies for
     * @param gProject the project to look into.
     * @return the list of GModule of the passed project that the passed GModule depends on.
     */
    @objid ("fd87f63b-bb23-480b-aadf-420aa68f28a4")
    public static List<GModule> getRequiredGModules(GModule gModule, GProject gProject) {
        return getRequiredGModules(gModule.getModuleHandle(), gProject);
    }

    /**
     * Returns the list of GModule of the passed project that the passed ModuleComponent depends on
     * (i.e. the returned GModules are required as mandatory by the passed ModuleComponent).
     * 
     * @param moduleHandle the module handle to look dependencies for
     * @param gProject the project to look into.
     * @return the list of GModule of the passed project that the passed ModuleComponent depends on.
     */
    @objid ("2c1e14ee-3d75-4ad4-8bd8-31adc2a3103e")
    private static List<GModule> getRequiredGModules(IModuleHandle moduleHandle, GProject gProject) {
        // Read info in the model and find the corresponding GModules in the project.
        List<GModule> dependsOn = new ArrayList<>();
        // Read the list from administrative informations
        for (VersionedItem<?> moduleId : moduleHandle.getDependencies()) {
            // Search the corresponding GModule
            GModule module = getGModuleByName(gProject, moduleId.getName());
            if (module != null) {
                // If Version compatible and activated, add it to the list.
                if (isVersionCompatible(module.getVersion(), moduleId.getVersion())) {
                    dependsOn.add(module);
                }
            }
        }
        return dependsOn;
    }

    /**
     * Returns the list of GModule that the passed module has weak dependencies on.
     * 
     * @param gModule the GModule to look weak dependencies for
     * @param gProject the project to look into.
     * @return the list of GModule of the passed project that the passed GModule have weak dependencies on.
     */
    @objid ("9fdad231-2763-4a01-b254-e598c065f305")
    public static List<GModule> getWeakDependenciesGModules(GModule gModule, GProject gProject) {
        return getWeakDependenciesGModules(gModule.getModuleHandle(), gProject);
    }

    /**
     * Returns the list of GModule that the passed module has weak dependencies on.
     * 
     * @param moduleHandle the module to look weak dependencies for.
     * @param gProject the project to look into.
     * @return the list of GModule of the passed project that the passed GModule have weak dependencies on.
     */
    @objid ("cb822355-ec0a-4192-9dde-6e68b15467cc")
    private static List<GModule> getWeakDependenciesGModules(IModuleHandle moduleHandle, GProject gProject) {
        // Read info in the model and find the corresponding GModules in the project.
        List<GModule> weakDependencies = new ArrayList<>();
        // Read the list from administrative informations
        for (VersionedItem<?> moduleId : moduleHandle.getWeakDependencies()) {
            // Search the corresponding GModule
            GModule module = getGModuleByName(gProject, moduleId.getName());
            if (module != null) {
                // If Version compatible and activated, add it to the list.
                if (isVersionCompatible(module.getVersion(), moduleId.getVersion())) {
                    weakDependencies.add(module);
                }
            }
        }
        return weakDependencies;
    }

    /**
     * Returns if the module defined by the passed ModuleHandle can safely be installed in the project.
     * <p>
     * Current strategy is to test if all modules required by the passed module can be found in
     * the passed project in a version compatible
     * with the requirement and that no module in the project requires a newer version of this model.
     * 
     * @param moduleHandle the module to test.
     * @param gProject the project in which the passed module would be installed
     * @throws org.modelio.api.module.lifecycle.ModuleException if a dependency is missing or the module would break another one.
     */
    @objid ("f51019ab-0358-4a08-ad63-d38979b5fc87")
    public static void checkCanInstall(IModuleHandle moduleHandle, GProject gProject) throws ModuleException {
        StringBuilder detailMsg = new StringBuilder();
        boolean hasMissingDep = false;
        boolean hasBrokenDep = false;
        
        // Check for missing or outdated dependencies
        for (VersionedItem<?> moduleId : moduleHandle.getDependencies()) {
            boolean moduleFoundInProject = false;
            for (GModule gModuleInProject : gProject.getModules()) {
                if (gModuleInProject.getName().equals(moduleId.getName())
                        && isVersionCompatible(gModuleInProject.getVersion(), moduleId.getVersion())) {
                    moduleFoundInProject = true;
                    break;
                }
            }
            if (!moduleFoundInProject) {
                if (! hasMissingDep) {
                    hasMissingDep = true;
                    detailMsg.append("\n");
                    detailMsg.append(MdaInfra.I18N.getMessage("ModuleExceptionMessage.MissingDep.title",
                            moduleHandle.getName(),
                            moduleHandle.getVersion()));
                }
                detailMsg.append("\n");
                detailMsg.append(MdaInfra.I18N.getMessage("ModuleExceptionMessage.MissingDep.line",
                        moduleId.getName(),
                        moduleId.getVersion()));
            }
        }
        
        // Check for modules requiring a newer version than moduleHandle
        for (GModule gModuleInProject : gProject.getModules()) {
            for (VersionedItem<?> requiredModuleId : gModuleInProject.getModuleHandle().getDependencies()) {
                if (requiredModuleId.getName().equals(moduleHandle.getName())
                        && !isVersionCompatible(moduleHandle.getVersion(), requiredModuleId.getVersion())) {
                    // gModuleInProject thinks moduleHandle is too old for him.
                    if (!hasBrokenDep) {
                        detailMsg.append("\n");
                        detailMsg.append(MdaInfra.I18N.getMessage("ModuleExceptionMessage.ModuleIncompatible.title",
                                moduleHandle.getName(),
                                moduleHandle.getVersion()));
        
                        hasBrokenDep = true;
                    }
        
                    detailMsg.append("\n");
                    detailMsg.append(MdaInfra.I18N.getMessage("ModuleExceptionMessage.ModuleIncompatible.line",
                            gModuleInProject.getName(),
                            gModuleInProject.getVersion(),
                            requiredModuleId.getName(),
                            requiredModuleId.getVersion()));
                }
            }
        }
        
        
        if (hasBrokenDep || hasMissingDep) {
            throw new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.CannotInstallModuleDetail",
                    moduleHandle.getName(),
                    moduleHandle.getVersion(),
                    detailMsg.toString()));
        }
    }

    /**
     * Get the modules in the project that depend directly on the given module handle.
     * 
     * @param moduleHandle a module handle
     * @param gProject the project to scan
     * @return a list containing all modules that depend on <i>moduleHandle</i>
     */
    @objid ("0f380173-9925-4d1a-8e58-2fc0f6dc1fe9")
    private static List<GModule> getDependentGModules(IModuleHandle moduleHandle, GProject gProject) {
        List<GModule> dependents = new ArrayList<>();
        
        // Go through each module in the project and look at its dependencies in case it contains the passed
        // module...
        for (GModule gModuleInProject : gProject.getModules()) {
            // Weak dependencies
            for (VersionedItem<?> moduleId : gModuleInProject.getModuleHandle().getWeakDependencies()) {
                if (moduleHandle.getName().equals(moduleId.getName())
                        && isVersionCompatible(moduleHandle.getVersion(), moduleId.getVersion())) {
                    dependents.add(gModuleInProject);
                }
            }
        }
        return dependents;
    }

    /**
     * Returns the first found GModule in the project which UUID matches the passed handle UUID.
     * <p>
     * If no such module is found returns the first one whose name matches the handle name.
     * 
     * @param gProject the project to search into.
     * @param handle the handle of the GModule to find.
     * @return the first found GModule in the project which matches the passed handle or <code>null</code> if none found.
     */
    @objid ("695d0090-f3bb-43dc-b5b1-b55e11433fcd")
    public static GModule getGModuleByHandle(GProject gProject, IModuleHandle handle) {
        GModule sameName = null;
        for (GModule module : gProject.getModules()) {
            if (module.getModuleHandle().getUid().equals(handle.getUid())) {
                return module;
            } else if (module.getName().equals(handle.getName())) {
                sameName = module;
            }
        }
        return sameName;
    }

    /**
     * Returns the list of loaded {@link IRTModule} in the project that have a dependency
     * (direct , strong ) on the passed module.
     * 
     * @param iModule the module for which dependents are searched.
     * @param moduleRegistry the modules registry.
     * @return the list of {@link IRTModule} in the project that have a dependency (direct, strong) on the passed module.
     */
    @objid ("c57e084d-82b5-4e93-82f7-5b7183725c01")
    public static List<IRTModule> getDependentRTModules(IRTModule iModule, IModuleRegistry moduleRegistry) {
        List<IRTModule> ret = new ArrayList<>();
        
        GModule gModule = iModule.getGModule();
        
        List<GModule> dependentGModules = getDependentGModules(gModule.getModuleHandle(), gModule.getProject());
        for (GModule dependentGModule : dependentGModules) {
            IRTModule dependentLoadedModule = moduleRegistry.getModule(dependentGModule);
            if (dependentLoadedModule != null) {
                ret.add(dependentLoadedModule);
            }
        }
        return ret;
    }

    /**
     * Get the modules in the project that weakly depend directly on the given module handle.
     * 
     * @param moduleHandle a module handle
     * @param gProject the project to scan
     * @return a list containing all modules that depend weakly on <i>moduleHandle</i>
     */
    @objid ("ead2fe7c-43ab-494c-ae89-458163ec87b4")
    private static List<GModule> getWeakDependentGModules(IModuleHandle moduleHandle, GProject gProject) {
        List<GModule> dependents = new ArrayList<>();
        
        // Go through each module in the project and look at its dependencies in case it contains the passed
        // module...
        for (GModule gModuleInProject : gProject.getModules()) {
            // Weak dependencies
            for (VersionedItem<?> moduleId : gModuleInProject.getModuleHandle().getWeakDependencies()) {
                if (moduleHandle.getName().equals(moduleId.getName())
                        && isVersionCompatible(moduleHandle.getVersion(), moduleId.getVersion())) {
                    dependents.add(gModuleInProject);
                }
            }
        }
        return dependents;
    }

    /**
     * Returns the list of loaded {@link IRTModule} in the project that have a dependency
     * (direct , weak) on the passed module.
     * 
     * @param iModule the module for which dependents are searched.
     * @param moduleRegistry the modules registry.
     * @return the list of {@link IRTModule} in the project that have a dependency (direct or not, strong or weak) on the passed module.
     */
    @objid ("61493e88-6e32-4cdf-9305-1b9b23588772")
    public static List<IRTModule> getWeakDependentRTModules(IRTModule iModule, IModuleRegistry moduleRegistry) {
        List<IRTModule> ret = new ArrayList<>();
        
        GModule gModule = iModule.getGModule();
        
        List<GModule> dependentGModules = getWeakDependentGModules(gModule.getModuleHandle(), gModule.getProject());
        for (GModule dependentGModule : dependentGModules) {
            IRTModule dependentLoadedModule = moduleRegistry.getModule(dependentGModule);
            if (dependentLoadedModule != null) {
                ret.add(dependentLoadedModule);
            }
        }
        return ret;
    }

}
