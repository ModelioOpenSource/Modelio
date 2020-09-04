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

package org.modelio.mda.infra.service.impl.controller.states.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.mda.infra.service.impl.controller.load.ModuleLoader;
import org.modelio.mda.infra.service.impl.controller.states.IModuleStateAction;

/**
 * Call the static install(String projectPath, String moduleResourcesPath) method
 * on the module main class using a temporary class loader.
 */
@objid ("0dd9a6af-8b95-4430-92dc-0bf57344488b")
public class CallModuleInstallAction implements IModuleStateAction {
    @objid ("3be4be5e-9126-475c-b4cd-5fcdeab09f6a")
    private IRTModuleAccess module;

    @objid ("8da95ca2-b5d0-489b-84e7-8ab0b4fac2c2")
    @Override
    public void execute() throws ModuleException {
        new ModuleLoader(this.module).callStaticMethodInstall();
    }

    /**
     * @param module the module
     */
    @objid ("9a2e3b3d-3b4e-4d10-bea4-ee444f0eda44")
    public CallModuleInstallAction(IRTModuleAccess module) {
        this.module = module;
    }

}
