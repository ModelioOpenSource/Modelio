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

package org.modelio.mda.infra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.ModuleId;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Modules registry implementation.
 * <p>
 * Knows which modules are deployed and which of them are started.
 */
@objid ("07dbe9c3-edc0-11e1-88ee-001ec947c8cc")
class ModuleRegistry implements IModuleRegistryAccess {
    @objid ("4b445af6-9a3d-472f-9e3c-3c26e4f1e06e")
    private String projectName;

    @objid ("1e65d837-edc3-11e1-88ee-001ec947c8cc")
    private Map<GModule, IRTModule> deployedModules = new HashMap<>();

    @objid ("1e683a92-edc3-11e1-88ee-001ec947c8cc")
    private Map<GModule, IRTModule> startedModules = new HashMap<>();

    /**
     * Get the started modules.
     * 
     * @return The started modules.
     */
    @objid ("1e683a96-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public List<IRTModule> getStartedModules() {
        checkOpen();
        List<IRTModule> results = new ArrayList<>(this.startedModules.values());
        results.sort(Comparator.comparingInt(IRTModule::getPriority));
        return results;
    }

    /**
     * Get the started {@link IRTModule} corresponding to the given {@link ModuleComponent}.
     * 
     * @param model the module model.
     * @return the matching started module or <i>null</i> if no started module matches the <i>IRTModule</i>
     */
    @objid ("1e683a9e-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public IRTModule getStartedModule(GModule model) {
        checkOpen();
        return this.startedModules.get(model);
    }

    /**
     * Adds a module to the list of started modules
     * 
     * @param module the started module.
     */
    @objid ("1e683aa5-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public void addStartedModule(IRTModule module) {
        checkOpen();
        
        final GModule model = module.getGModule();
        if (model != null) {
            assert (this.deployedModules.get(model) == module) : String.format("this.deployedModules.get(%s) = %s , should be %s)", model, this.deployedModules.get(model), module);
            
            this.startedModules.put(model, module);
        }
    }

    /**
     * Remove a module from the list of started modules.
     * 
     * @param module the stopped module.
     */
    @objid ("1e6a9ceb-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public void removeStartedModule(IRTModule module) {
        checkOpen();
        
        this.startedModules.remove(module.getGModule());
    }

    /**
     * Adds a module to the list of loaded modules
     * 
     * @param module the loaded module.
     */
    @objid ("1e6a9cf0-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public void addModule(IRTModule module) {
        checkOpen();
        
        final GModule model = module.getGModule();
        if (model != null) {
            this.deployedModules.put(model, module);
        }
    }

    /**
     * Get the loaded {@link IRTModule} corresponding to the given {@link ModuleComponent}.
     * 
     * @param model the module model.
     * @return the matching loaded module or <i>null</i> if no loaded module matches the <i>IRTModule</i>
     */
    @objid ("1e6a9cf5-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public IRTModule getModule(GModule model) {
        checkOpen();
        return this.deployedModules.get(model);
    }

    @objid ("1e6a9cfc-edc3-11e1-88ee-001ec947c8cc")
    @Override
    public void removeModule(IRTModule module) {
        checkOpen();
        
        this.deployedModules.remove(module.getGModule());
    }

    @objid ("bc47fc61-f37d-11e1-9458-001ec947c8cc")
    @Override
    public IRTModule getModule(VersionedItem<?> moduleId) {
        checkOpen();
        
        for (IRTModule module : this.deployedModules.values()) {
            if (module.getName().equals(moduleId.getName())) {
                if (ModuleResolutionHelper.isVersionCompatible(module.getVersion(), moduleId.getVersion())) {
                    return module;
                }
            }
        }
        return null;
    }

    /**
     * Get the started {@link IRTModule} which name correspond to the given {@link ModuleId} name and which version is newer or equal to the given version.
     * 
     * @param moduleId the Id of the searched module.
     * @return the matching started module or <code>null</code> if no started module matches the ModuleId.
     */
    @objid ("bc47fc67-f37d-11e1-9458-001ec947c8cc")
    @Override
    public IRTModule getStartedModule(VersionedItem<?> moduleId) {
        checkOpen();
        
        for (IRTModule module : this.startedModules.values()) {
            if (module.getName().equals(moduleId.getName())) {
                if (ModuleResolutionHelper.isVersionCompatible(module.getVersion(), moduleId.getVersion())) {
                    return module;
                }
            }
        }
        return null;
    }

    /**
     * Default c'tor
     */
    @objid ("fa606801-66d1-4026-959f-360db1ac559a")
    public ModuleRegistry() {
        // Empty
    }

    @objid ("5c210534-70a7-422b-8142-59b8cf3aea10")
    @Override
    public Collection<IRTModule> getModules() {
        checkOpen();
        return this.deployedModules.values();
    }

    @objid ("3db8f327-96bd-499e-995f-72f61c33fb10")
    @Override
    public IRTModule loadRTModule(GModule gModule) {
        IRTModule rtModule = getModule(gModule);
        
        // Return the module if already loaded
        if (rtModule == null) {
            rtModule = new RTModule(gModule, this);
        
            // Add loaded module to the registry
            addModule(rtModule);
        }
        return rtModule;
    }

    @objid ("b38fca35-b681-4adf-a7a0-711bef4734fe")
    @Override
    public void dispose() {
        this.deployedModules = null;
        this.startedModules = null;
    }

    @objid ("7db7e844-5b73-47bf-b2fb-7087ab575c74")
    @Override
    public void setProjectName(String name) {
        this.projectName = name;
    }

    /**
     * Check the registry is in the right state before using it.
     * 
     * @throws java.lang.IllegalStateException if the registry is not yet open or is disposed.
     */
    @objid ("5e5db267-fef5-417b-a51e-d5325d43f3d1")
    protected void checkOpen() throws IllegalStateException {
        if (this.deployedModules == null) {
            throw new IllegalStateException(String.format("'%s' project module registry is disposed.", this.projectName));
        }
    }

}
