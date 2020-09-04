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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.io.IOException;
import java.net.URLClassLoader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.AbstractIRTModuleListener;
import org.modelio.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.IRTModuleListener;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.mda.infra.service.impl.controller.load.ModuleLoader;

/**
 * Feature to load the IModule.
 */
@objid ("63ab18c6-2992-4285-a8af-8ad141ba1206")
public class IModuleLoadedFeature extends AbstractFeature {
    @objid ("74b779c1-f748-4320-814c-27621f89f631")
    private IRTModuleListener requiredListener;

    @objid ("598dd4c3-376a-4fc6-b251-e9a9af2979b7")
    private IRTModuleListener optionalListener;

    /**
     * @param myModule the handled module
     */
    @objid ("3889a1d1-e7a1-4b26-a1d1-35d815332304")
    public IModuleLoadedFeature(final IRTModuleAccess myModule) {
        super(myModule);
        
        this.requiredListener = new AbstractIRTModuleListener() {
            @Override
            public void moduleUnloading(IRTModule requiredModule) {
                try {
                    MdaInfra.LOG.debug(" unloading '%s' because required '%s' is unloading.", myModule.getName(), requiredModule.getName());
                    myModule.getController().unload();
                } catch (ModuleException e) {
                    myModule.getController().broken(e);
                }
            }
        };
        
        // Reload this module when an optional used module is stopped
        this.optionalListener = new AbstractIRTModuleListener() {
            @Override
            public void moduleUnloading(IRTModule usedModule) {
                try {
                    ModuleRuntimeState currentState = myModule.getState();
        
                    // Unload module
                    MdaInfra.LOG.debug(" unloading '%s' because optional '%s' is unloading.", myModule.getName(), usedModule.getName());
                    myModule.getController().unload();
        
                    // Reload module & restore current state when module finish unload
                    AbstractIRTModuleListener restarter = new AbstractIRTModuleListener() {
                        @Override
                        public void moduleUnloaded(IRTModule unloadedModule) {
                            usedModule.getListeners().remove(this);
        
                            try {
                                if (currentState == ModuleRuntimeState.Started) {
        
                                    MdaInfra.LOG.debug("restarting '%s' because optional '%s' is unloaded.", myModule.getName(), unloadedModule.getName());
                                    myModule.getController().start();
        
                                } else {
                                    MdaInfra.LOG.debug("reloading '%s' because optional '%s' is unloaded.", myModule.getName(), unloadedModule.getName());
                                    myModule.getController().load();
                                }
                            } catch (ModuleException e) {
                                myModule.getController().broken(e);
                            }
                        }
                    };
        
                    usedModule.getListeners().add(restarter);
        
                } catch (ModuleException e) {
                    myModule.getController().broken(e);
                }
        
            }
        };
    }

    @objid ("fa9bb342-2bfb-4577-be76-ed7ec53e93da")
    @Override
    public void enable() throws ModuleException {
        checkDependencies();
        
        // Load module implementation
        IModule imodule = new ModuleLoader(this.module).loadModule();
        this.module.setIModule(imodule);
        
        imodule.init();
        
        this.module.setState(ModuleRuntimeState.Loaded);
        
        // Register listeners on dependencies
        registerListeners();
        
        // Restart existing modules having a weak dependency
        restartWeakDependentModules();
        
        // Fire listeners
        IRTModuleListener.Poster.moduleLoaded(this.module);
    }

    @objid ("b5c1c24f-2370-4a6d-996e-d2bd56e7c034")
    @Override
    public void disable() {
        fireModuleUnloading(this.module);
        
        try {
            this.module.getIModule().uninit();
        } catch (RuntimeException e) {
            // Log and continue
            MdaInfra.LOG.warning(e);
        }
        
        // Close and free the class loader
        ClassLoader classLoader = this.module.getClassLoader();
        if (classLoader instanceof URLClassLoader) {
            try {
                ((URLClassLoader)classLoader).close();
            } catch (IOException e) {
                MdaInfra.LOG.warning(e);
            }
            this.module.setClassLoader(null);
        }
        
        // Replace the IModule by a stub
        this.module.setIModule(new ModuleLoader(this.module).createFakeModule());
        
        // Unregister listeners on dependencies
        removeListeners();
        
        this.module.resetDependencies();
        
        // Fire listeners
        fireModuleUnloaded(this.module);
    }

    @objid ("04d29be8-1622-424e-9958-f46ebd4f9fc4")
    @Override
    public String toString() {
        return "IModule loaded feature";
    }

    @objid ("0d736b60-9bdc-4958-aca4-58ceccdf0ea6")
    private void checkDependencies() throws ModuleException {
        for (IRTModule dep : this.module.getRequiredDependencies()) {
            if (dep.getState() != ModuleRuntimeState.Loaded
                     && dep.getState() != ModuleRuntimeState.Started) {
                throw new ModuleException(MdaInfra.I18N.getMessage("IModuleLoadedFeature.missingDep",
                        dep.getLabel(), dep.getVersion()));
            }
            dep.getController().load();
        }
    }

    @objid ("2ad79fe5-2d4e-4c0c-ab89-907aa63fe000")
    private void registerListeners() {
        // register listeners
        for (IRTModule reqModule : this.module.getRequiredDependencies()) {
            reqModule.getListeners().add(this.requiredListener);
        }
        
        for (IRTModule reqModule : this.module.getOptionalDependencies()) {
            reqModule.getListeners().add(this.optionalListener);
        }
    }

    @objid ("d59afd89-3085-439c-b45a-cefcd09b5b0e")
    private void removeListeners() {
        for (IRTModule reqModule : this.module.getRequiredDependencies()) {
            reqModule.getListeners().remove(this.requiredListener);
            //reqModule.getListeners().remove(this.restartListener);
        }
        
        for (IRTModule reqModule : this.module.getOptionalDependencies()) {
            reqModule.getListeners().remove(this.optionalListener);
        }
    }

    /**
     * FIXME design should be modified to make the module itself a listener,
     * forwarding all events to its active features.
     */
    @objid ("9da701f0-7802-40eb-a0bf-405680683603")
    private void restartWeakDependentModules() {
        for (IRTModule aModule : this.module.getModuleOptionalUsers()) {
            aModule.resetDependencies();
        
            ModuleRuntimeState currentState = aModule.getState();
        
            try {
                MdaInfra.LOG.debug(" reloading '%s' v%s because optional '%s' v%s is loaded.", aModule.getName(), aModule.getVersion(), this.module.getName(), this.module.getVersion());
        
                // Unload module
                aModule.getController().unload();
        
                // Reload module & restore current state
                if (currentState == ModuleRuntimeState.Started) {
                    aModule.getController().start();
                } else {
                    aModule.getController().load();
                }
            } catch (ModuleException e) {
                aModule.getController().broken(e);
            }
        }
    }

    /**
     * fire listeners on dependent modules first then on the given module.
     * 
     * @param amodule the module being unloaded
     */
    @objid ("82dd199d-ee41-49c5-91f7-8af2ab849bb1")
    private void fireModuleUnloading(IRTModule amodule) {
        // First recurse on dependent modules
        for (IRTModule m : amodule.getModuleOptionalUsers()) {
            fireModuleUnloading(m);
        }
        for (IRTModule m : amodule.getModuleUsers()) {
            fireModuleUnloading(m);
        }
        
        for (IRTModuleListener listener : amodule.getListeners()) {
            try {
                listener.moduleUnloading(this.module);
            } catch (RuntimeException e) {
                MdaInfra.LOG.warning(e);
            }
        }
    }

    /**
     * fire listeners on dependent modules first then on the given module.
     * 
     * @param amodule the module being unloaded
     */
    @objid ("e77b9bd4-3154-416c-8f3c-bd750fbd8183")
    private void fireModuleUnloaded(IRTModule amodule) {
        // First recurse on dependent modules
        for (IRTModule m : amodule.getModuleOptionalUsers()) {
            fireModuleUnloaded(m);
        }
        for (IRTModule m : amodule.getModuleUsers()) {
            fireModuleUnloaded(m);
        }
        
        for (IRTModuleListener listener : amodule.getListeners()) {
            try {
                listener.moduleUnloaded(this.module);
            } catch (RuntimeException e) {
                MdaInfra.LOG.warning(e);
            }
        }
    }

}
