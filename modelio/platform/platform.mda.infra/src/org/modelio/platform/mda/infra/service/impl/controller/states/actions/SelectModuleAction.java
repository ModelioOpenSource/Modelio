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

package org.modelio.platform.mda.infra.service.impl.controller.states.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.ModuleRefusedActionException;
import org.modelio.platform.mda.infra.service.impl.controller.states.IModuleStateAction;

/**
 * Action that call select() method on the module
 */
@objid ("81852cc1-1993-4b6a-aea4-ff60c103e989")
public class SelectModuleAction implements IModuleStateAction {
    @objid ("dca6b7a4-23f5-41f2-a8d4-3925c64f5034")
    private IRTModule module;

    /**
     * @param module the module
     */
    @objid ("8b5fdb4e-110d-4696-b680-fd5f78391578")
    public SelectModuleAction(IRTModule module) {
        this.module = module;
    }

    @objid ("b561a6c5-6faf-44b4-bb95-3afbe34e85bf")
    @Override
    public void execute() throws ModuleException {
        IModule iModule = this.module.getIModule();
        try {
            if (!iModule.getLifeCycleHandler().select()) {
                throw new ModuleRefusedActionException(MdaInfra.I18N.getMessage("SelectModuleAction.selectRefused", iModule.getName(), iModule.getVersion()));
            }
        } catch (RuntimeException | LinkageError e) {
            ModuleException e2 = new ModuleException(MdaInfra.I18N.getMessage("SelectModuleAction.selectFailed", iModule.getName(), iModule.getVersion(), e.toString()));
            e2.initCause(e);
            throw e2;
        }
    }

}
