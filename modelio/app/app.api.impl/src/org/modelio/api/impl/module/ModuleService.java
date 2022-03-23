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
package org.modelio.api.impl.module;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.module.IModuleService;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.mda.infra.service.IModuleRegistry;
import org.modelio.platform.mda.infra.service.IRTModule;

/**
 * Implementation of IModuleService, using the {@link IModuleRegistry} to get started modules.
 */
@objid ("37c5b60b-dc4e-412b-abae-2341a06f97cd")
public class ModuleService implements IModuleService {
    @objid ("c95932f2-7bda-42a3-8640-abb86c3f4e1e")
    private IModuleManagementService coreModuleService;

    @objid ("94d42a01-22b9-4b8a-9398-614371e0823a")
    public  ModuleService(final IModuleManagementService coreModuleService) {
        this.coreModuleService = coreModuleService;
    }

    @objid ("dd3df7a3-8e37-4693-868f-0e5bbb73b322")
    @Override
    public Collection<IPeerModule> getAllPeerModules() {
        Collection<IPeerModule> ret = new ArrayList<>();
        for (IRTModule module : this.coreModuleService.getModuleRegistry().getStartedModules()) {
            ret.add(module.getPeerModule());
        }
        return ret;
    }

    @objid ("6bdecc4e-6c7f-48ce-83e9-e6db0e07ab2a")
    @Override
    public <T extends IPeerModule> T getPeerModule(final Class<T> peerClass) {
        for (IRTModule module : this.coreModuleService.getModuleRegistry().getStartedModules()) {
            IPeerModule peerModule = module.getPeerModule();
            if (peerClass.isAssignableFrom(peerModule.getClass())) {
                return peerClass.cast(peerModule);
            }
        }
        return null;
    }

    @objid ("5fac7393-13a8-4db2-9283-d1d4fc78a07b")
    @Override
    public IPeerModule getPeerModule(final String moduleName) {
        for (IRTModule module : this.coreModuleService.getModuleRegistry().getStartedModules()) {
            if (module.getName().equals(moduleName)) {
                return module.getPeerModule();
            }
        }
        return null;
    }

    @objid ("b282ed7c-7847-4d94-bb1e-e8e983680779")
    public void installModule(GProject gProject, Path moduleFilePath) throws ModuleException {
        this.coreModuleService.installModule(null, gProject, moduleFilePath);
    }

    @objid ("85aeb35c-8857-4a17-8cbd-91e41d909eba")
    public void removeModule(GModule gModule) throws ModuleException {
        this.coreModuleService.removeModule(gModule, false);
    }

}
