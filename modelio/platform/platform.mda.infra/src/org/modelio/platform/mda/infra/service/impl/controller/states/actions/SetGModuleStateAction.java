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
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.impl.controller.states.IModuleStateAction;

/**
 * Action that changes the activation state of a {@link GModule}.
 */
@objid ("4574398a-ee03-4518-8269-27e453c5e680")
public class SetGModuleStateAction implements IModuleStateAction {
    @objid ("6b36ef2e-ac4f-4c98-ab39-9b3d35bf2b6d")
    private boolean state;

    @objid ("71dfcb52-53c1-4caa-b40e-f09a25621050")
    private IRTModule module;

    /**
     * @param module a module
     * @param state the activation state
     */
    @objid ("d7051218-9269-47a0-b2a8-c74a1375101e")
    public  SetGModuleStateAction(IRTModule module, boolean state) {
        this.module = module;
        this.state = state;
        
    }

    @objid ("ce1c3e68-9f00-4b88-8009-7fdf4f287c94")
    @Override
    public void execute() throws ModuleException {
        this.module.getGModule().setActive(this.state);
    }

}
