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
package org.modelio.platform.mda.infra.service.impl.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.platform.mda.infra.IMdaResourceProviderRegistry;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.IRTModuleController;
import org.modelio.platform.mda.infra.service.IRTModuleListener;
import org.modelio.platform.mda.infra.service.impl.IModuleRegistryAccess;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.ModuleResolutionHelper;
import org.modelio.platform.mda.infra.service.impl.RTModule;
import org.modelio.platform.mda.infra.service.impl.controller.load.ModuleLoader;
import org.modelio.platform.mda.infra.service.impl.controller.remove.ModuleRemover;
import org.modelio.platform.mda.infra.service.impl.controller.states.States;
import org.modelio.platform.mda.infra.service.impl.controller.update.ModuleUpdater;
import org.modelio.vbasic.version.Version;

/**
 * Controller for a {@link IRTModule}.
 */
@objid ("76c3a2c0-655d-4168-9eb6-1610c6b5f4ae")
public class RTModuleController implements IRTModuleController {
    @objid ("0e135976-82df-4301-b56d-3190b9af21a0")
    private final IModuleRegistryAccess moduleRegistry;

    @objid ("dc38ef62-196d-4d07-a243-0c23b8a10555")
    private final IRTModuleAccess rtModule;

    /**
     * The state machine
     */
    @objid ("a158f372-2bca-4179-b609-8a63c3c74607")
    private final States states;

    @objid ("8c6b6a94-eeef-431b-87ff-138f4d03eca9")
    private final IMdaResourceProviderRegistry mdaResourceProviderRegistry;

    /**
     * @param rtModule the controlled module
     * @param moduleRegistry the module registry
     */
    @objid ("b56c08a9-5edd-4da4-9b0a-619c183376dd")
    public  RTModuleController(IRTModuleAccess rtModule, IModuleRegistryAccess moduleRegistry, IMdaResourceProviderRegistry mdaResourceProviderRegistry) {
        this.rtModule = rtModule;
        this.moduleRegistry = moduleRegistry;
        this.mdaResourceProviderRegistry = mdaResourceProviderRegistry;
        this.states = new States(this.rtModule);
        
    }

    @objid ("dc35235c-a2bf-4cb1-bd1b-2ca318c7ae5b")
    @Override
    public void initModuleUses() {
        ArrayList<IRTModule> newRequired = new ArrayList<>();
        ArrayList<IRTModule> newOptional = new ArrayList<>();
        
        for (IRTModule m : new ArrayList<>(this.moduleRegistry.getModules())) {
            // Note: calling m.getRequiredDependencies() may result of this.resetModuleUsers() being called
        
            if (m.getMandatoryRequiredModules().contains(this.rtModule)) {
                newRequired.add(m);
            }
        
            if (m.getOptionalRequiredModules().contains(this.rtModule)) {
                newOptional.add(m);
            }
        }
        
        // Set fields last to avoid concurrent field reset by resetModuleUsers()
        this.rtModule.setModuleMandatoryUses(newRequired);
        this.rtModule.setModuleOptionalUses(newOptional);
        
    }

    /**
     * Compute required & optional dependencies from the GModule underneath.
     */
    @objid ("9d505c0d-6149-48ad-8306-74cd00600290")
    @Override
    public void initRequiredModules() {
        // Recompute required and used modules
        GModule gModule = this.rtModule.getGModule();
        List<GModule> requiredGModules = ModuleResolutionHelper.getRequiredGModules(gModule, gModule.getProject());
        List<GModule> optionalGModules = ModuleResolutionHelper.getWeakDependenciesGModules(gModule, gModule.getProject());
        
        List<IRTModule> newRequired = new ArrayList<>(requiredGModules.size());
        List<IRTModule> newOptional = new ArrayList<>(optionalGModules.size());
        
        for (GModule strongDependency : requiredGModules) {
            IRTModule rtDep = this.moduleRegistry.getModule(strongDependency);
            if (rtDep == null) {
                rtDep = new RTModule(gModule, this.moduleRegistry, this.mdaResourceProviderRegistry);
                // Add loaded module to the registry
                this.moduleRegistry.addModule(rtDep);
            }
            newRequired.add(rtDep);
            rtDep.resetModuleUsers();
        }
        
        for (GModule weakDependency : optionalGModules) {
            IRTModule rtDep = this.moduleRegistry.getModule(weakDependency);
            if (rtDep == null) {
                rtDep = new RTModule(gModule, this.moduleRegistry, this.mdaResourceProviderRegistry);
                // Add loaded module to the registry
                this.moduleRegistry.addModule(rtDep);
            }
            newOptional.add(rtDep);
            rtDep.resetModuleUsers();
        }
        
        this.rtModule.setMandatoryRequiredModules(newRequired);
        this.rtModule.setOptionalRequiredModules(newOptional);
        
    }

    @objid ("3d7091f9-dfbb-40ff-aa96-ac68d88046cb")
    @Override
    public void activate() throws ModuleException {
        MdaInfra.LOG.indent();
        
        try {
            this.states.handleMessage(States.MSGACTIVATE);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("1432006a-55c0-4133-8034-ff515c97b371")
    @Override
    public void broken(ModuleException e) {
        this.states.setBroken();
        
        this.rtModule.setIModule(new ModuleLoader(this.rtModule).createBrokenModule());
        this.rtModule.setDownError(e);
        
    }

    @objid ("0734d6ea-1ef1-455d-9ddf-c01c70616056")
    @Override
    public void deactivate() throws ModuleException {
        MdaInfra.LOG.indent();
        
        try {
            this.states.handleMessage(States.MSGDISABLE);
        
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("14198a51-f76d-43ab-b145-b4014df63454")
    @Override
    public IModuleRegistryAccess getModuleRegistry() {
        return this.moduleRegistry;
    }

    @objid ("0f4b4c7a-53c9-407e-a3cf-78c2fbe1a182")
    @Override
    public IRTModule getRTModule() {
        return this.rtModule;
    }

    @objid ("dbf40003-c67e-4ca7-a490-343a1b0cf846")
    @Override
    public void install() throws ModuleException {
        MdaInfra.LOG.indent();
        
        try {
            this.states.handleMessage(States.MSGINSTALL);
        
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("891a1a8d-f7f9-47e3-86bd-a9fd6740e330")
    @Override
    public void load() throws ModuleException {
        MdaInfra.LOG.indent();
        
        try {
            if (this.rtModule.getGModule().isActive()) {
                this.states.handleMessage(States.MSGLOADACTIVATED);
            } else {
                this.states.handleMessage(States.MSGLOADDISABLED);
            }
        
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("d8a323b7-61a5-4b9d-a07e-5ce622a8a0d6")
    @Override
    public void removeFromProject(boolean deleteAnnotations) throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            IRTModuleListener.Poster.moduleRemoving(this.rtModule);
        
            ModuleRemover.remove(this.rtModule, deleteAnnotations);
        
            // Call unselect(), and unload definitively
            this.states.handleMessage(States.MSGDELETE);
        
            IRTModuleListener.Poster.moduleRemoved(this.rtModule);
        
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("b77dab9f-6ff8-40e7-a5d7-c9f81a167c7a")
    @Override
    public void start() throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            this.states.handleMessage(States.MSGSTART);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("ba6872ac-bd82-4638-8ef2-903923b7f8ff")
    @Override
    public void stop() throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            this.states.handleMessage(States.MSGSTOP);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("1983f9d4-528c-4162-8639-4893397bd24e")
    @Override
    public void unload() throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            this.states.handleMessage(States.MSGUNLOAD);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("8461ad07-2405-4906-a825-8e8a1390a0da")
    @Override
    public void updateTo(IModuleHandle rtModuleHandle, URI moduleUri) throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            new ModuleUpdater(this.rtModule.getGModule().getProject(), this.moduleRegistry)
            .moduleUpdateInstall(this.rtModule, rtModuleHandle, moduleUri);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("76096005-e055-440e-bc81-d413fe248f48")
    @Override
    public void close() throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            this.states.handleMessage(States.MSGCLOSE);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("2c8cde5e-2bfd-469d-9f2f-b408b8671bf0")
    @Override
    public void updateFromGModule(Version oldVersion) throws ModuleException {
        MdaInfra.LOG.indent();
        try {
            new ModuleUpdater(this.rtModule.getGModule().getProject(), this.moduleRegistry)
            .moduleUpdateFromGProject(this.rtModule, oldVersion);
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("d68ada9f-4dbb-4542-b1c7-31d1db9df03f")
    @Override
    public IMdaResourceProviderRegistry getMdaResourceProviderRegistry() {
        return this.mdaResourceProviderRegistry;
    }

}
