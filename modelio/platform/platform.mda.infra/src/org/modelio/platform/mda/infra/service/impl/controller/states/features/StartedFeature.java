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
package org.modelio.platform.mda.infra.service.impl.controller.states.features;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.AbstractIRTModuleListener;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.platform.mda.infra.service.IRTModuleListener;
import org.modelio.platform.mda.infra.service.ModuleRefusedActionException;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;

/**
 * When the module is started.
 */
@objid ("92002fbd-ffaa-4d3e-ba09-c6625a2068dc")
public class StartedFeature extends AbstractFeature {
    @objid ("0e864b7d-197d-40a3-a08b-401afa61253e")
    private IRTModuleListener requiredListener;

    @objid ("deb951db-b735-4962-a8ad-7278b6325923")
    private IRTModuleListener optionalListener;

    @objid ("1ae08b6d-c20c-427c-8dc4-0bde436200ec")
    IRTModuleListener restartListener;

    /**
     * @param myModule the module
     */
    @objid ("6fb51ead-d0fb-4340-a3e2-42d47d1d1c59")
    public  StartedFeature(IRTModuleAccess myModule) {
        super(myModule);
        
        // Create listeners that stop the module when a required one is stopped.
        this.requiredListener = new AbstractIRTModuleListener() {
            @Override
            public void moduleStopping(IRTModule amodule) {
                try {
                    MdaInfra.LOG.debug(" Stopping '%s' because required '%s' is stopping.", myModule.getName(), amodule.getName());
        
                    // Stop the module when an required module is stopped
                    myModule.getController().stop();
        
                    // Listen for module restart to restart itself
                    amodule.getListeners().add(StartedFeature.this.restartListener);
                } catch (ModuleException e) {
                    // Cannot stop : the module is broken
                    myModule.getController().broken(e);
                }
            }
        };
        
        this.restartListener = new AbstractIRTModuleListener() {
        
            @Override
            public void moduleStarted(IRTModule amodule) {
                // Restart the module when an required module has restarted.
                try {
                    MdaInfra.LOG.debug(" Starting '%s' because required '%s' has started.", myModule.getName(), amodule.getName());
                    myModule.getController().start();
                    amodule.getListeners().remove(this);
                } catch (ModuleException e) {
                    // myModule.getController().broken(e);
                    MdaInfra.LOG.warning("Restarting module '%s' required by '%s' module failed:",
                            myModule.getName(), amodule.getName());
                    MdaInfra.LOG.warning(e);
        
                }
            }
        };
        
        this.optionalListener = new AbstractIRTModuleListener() {
            @Override
            public void moduleStopping(IRTModule amodule) {
                // Temporarly stop the module when an optional module is stopped
                try {
                    MdaInfra.LOG.debug(" Temporarly stopping '%s' because optional '%s' is stopping.", myModule.getName(), amodule.getName());
                    myModule.getController().stop();
        
                    // stop() did remove this listener, add it again
                    amodule.getListeners().add(this);
                } catch (ModuleException e) {
                    MdaInfra.LOG.warning("Temporarly stopping '%s' as weak dependency of module '%s' failed:",
                            myModule.getName(), amodule.getName());
                    MdaInfra.LOG.warning(e);
                }
            }
        
            @Override
            public void moduleStopped(IRTModule amodule) {
                // One shot operation
                amodule.getListeners().remove(this);
        
                // Restart the module when an optional module is stopped
                try {
                    myModule.getController().start();
                } catch (ModuleException e) {
                    MdaInfra.LOG.warning("Restarting module '%s' as weak dependency of module '%s' failed:",
                            myModule.getName(), amodule.getName());
                    MdaInfra.LOG.warning(e);
                }
        
            }
        };
        
    }

    @objid ("89c4baab-297f-4142-b39e-ca9aff1d885e")
    @Override
    public void enable() throws ModuleException {
        // Start dependencies
        startDependencies();
        
        // Start the module
        try {
            boolean ok = this.module.getIModule().getLifeCycleHandler().start();
        
            if (!ok) {
                throw new ModuleRefusedActionException(MdaInfra.I18N.getMessage("StartedFeature.StartReturnedFalse", this.module.getLabel()));
            }
        } catch (RuntimeException | LinkageError e) {
            String message = MdaInfra.I18N.getMessage("ModuleStarter.startFailed",
                    this.module.getLabel(), 
                    this.module.getVersion(), 
                    e.toString());
            throw new ModuleException(message, e);
        }
        
        this.module.setState(ModuleRuntimeState.Started);
        
        this.module.getController().getModuleRegistry().addStartedModule(this.module);
        
        MdaInfra.LOG.debug("ModuleStarter.doStartModule(): %s v%s started successfully.", this.module.getName(), this.module.getVersion());
        
        // Register listeners on used modules
        registerListeners();
        
        // Fire start listener
        IRTModuleListener.Poster.moduleStarted(this.module);
        
    }

    @objid ("0af0c3ae-5590-4be0-9325-e8b8b410afaf")
    @Override
    public void disable() throws ModuleException {
        // fire stop listener
        IRTModuleListener.Poster.moduleStopping(this.module);
        
        // Stop the module itself
        this.module.getIModule().getLifeCycleHandler().stop();
        
        // Add to registry and set state
        this.module.getController().getModuleRegistry().removeStartedModule(this.module);
        
        this.module.setState(ModuleRuntimeState.Loaded);
        
        // remove listeners
        removeListeners();
        
        // fire stop listener
        IRTModuleListener.Poster.moduleStopped(this.module);
        
    }

    @objid ("1581326e-3629-41e7-8750-49f864685bf3")
    private void startDependencies() throws ModuleException {
        MdaInfra.LOG.debug("Starting '%s' module required modules", this.module.getName());
        for (IRTModule dep : this.module.getRequiredDependencies()) {
            if (dep.getState() != ModuleRuntimeState.Started) {
                try {
                    if (!dep.getGModule().isActivated()) {
                        dep.getController().activate();
                    }
                    dep.getController().start();
                } catch (ModuleException e) {
                    throw new ModuleException(MdaInfra.I18N.getMessage("StartedFeature.FailedStartingDep",
                            dep.getLabel(), dep.getVersion(), e.getLocalizedMessage()), e);
                } catch (RuntimeException | LinkageError e) {
                    throw new ModuleException(MdaInfra.I18N.getMessage("StartedFeature.FailedStartingDep",
                            dep.getLabel(), dep.getVersion(), e.toString()), e);
                }
            }
        }
        
        MdaInfra.LOG.debug("Starting '%s' module optionally used modules", this.module.getName());
        for (IRTModule dep : this.module.getOptionalDependencies()) {
            if (dep.getGModule().isActivated()) {
                try {
                    dep.getController().start();
                } catch (ModuleException e) {
                    MdaInfra.LOG.debug(MdaInfra.I18N.getMessage("StartedFeature.FailedStartingDep",
                            dep.getLabel(), dep.getVersion(), e.getLocalizedMessage()), e);
                } catch (RuntimeException | LinkageError e) {
                    MdaInfra.LOG.debug(MdaInfra.I18N.getMessage("StartedFeature.FailedStartingDep",
                            dep.getLabel(), dep.getVersion(), e.toString(), e.toString()));
                }
            }
        }
        
    }

    @objid ("cfe4e120-4d54-49fb-9698-719b2968dbe3")
    @Override
    public String toString() {
        return "Module started feature";
    }

    /**
     * Listen for modules dependencies life cycle.
     */
    @objid ("39cef90c-3d0c-41f4-ba6c-2f5005dca5b3")
    private void registerListeners() {
        for (IRTModule reqModule : this.module.getRequiredDependencies()) {
            reqModule.getListeners().add(this.requiredListener);
        }
        
        for (IRTModule reqModule : this.module.getOptionalDependencies()) {
            reqModule.getListeners().add(this.optionalListener);
        }
        
    }

    @objid ("08f2e6a3-bb57-4fb7-98d4-5de9c650a585")
    private void removeListeners() {
        for (IRTModule reqModule : this.module.getRequiredDependencies()) {
            reqModule.getListeners().remove(this.requiredListener);
            reqModule.getListeners().remove(this.restartListener);
        }
        
        for (IRTModule reqModule : this.module.getOptionalDependencies()) {
            reqModule.getListeners().remove(this.optionalListener);
        }
        
    }

    /**
     * fire listeners on dependent modules first then on the given module.
     * @param amodule the module being unloaded
     */
    @objid ("acab238a-3001-40dd-ac92-d1bee452d308")
    private void fireModuleStopping(IRTModule amodule) {
        // First recurse on dependent modules
        for (IRTModule m : amodule.getModuleOptionalUsers()) {
            fireModuleStopping(m);
        }
        for (IRTModule m : amodule.getModuleUsers()) {
            fireModuleStopping(m);
        }
        
        for (IRTModuleListener listener : amodule.getListeners()) {
            try {
                listener.moduleStopping(this.module);
            } catch (RuntimeException e) {
                MdaInfra.LOG.warning(e);
            }
        }
        
    }

}
