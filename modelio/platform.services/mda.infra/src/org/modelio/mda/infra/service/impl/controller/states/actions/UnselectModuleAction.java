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
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.impl.controller.states.IModuleStateAction;

/**
 * Action that unselects a module.
 */
@objid ("e80917af-c365-4018-bcd4-edebda8293b6")
public class UnselectModuleAction implements IModuleStateAction {
    @objid ("6cb87ffd-a7a3-46e3-81ef-5955f45d2ae3")
    private IRTModule module;

    /**
     * @param module the module
     */
    @objid ("1c04348b-43b4-41ed-ab2f-4ee2e941b745")
    public UnselectModuleAction(IRTModule module) {
        this.module = module;
    }

    @objid ("1e3491eb-728e-44a7-baa7-456ebc23b1b1")
    @Override
    public void execute() throws ModuleException {
        this.module.getIModule().getLifeCycleHandler().unselect();
    }

}
