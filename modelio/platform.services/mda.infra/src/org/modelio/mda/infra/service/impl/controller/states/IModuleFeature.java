/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.mda.infra.service.impl.controller.states;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;

/**
 * A module feature/part to be activated or disabled depending on the current state.
 */
@objid ("8ddfff03-17b6-45bd-8294-634a6891bb14")
public interface IModuleFeature {
    /**
     * Returns normally if the state can be changed,
     * throws ModuleException if state change must be aborted.
     * @throws org.modelio.api.module.lifecycle.ModuleException on error
     */
    @objid ("5d10a286-c53f-49bd-bb7e-e67e397d822a")
    void enable() throws ModuleException;

    /**
     * @throws org.modelio.api.module.lifecycle.ModuleException error to report to the user without blocking state exit
     */
    @objid ("3c84d350-a238-4535-a3ca-46fa46a2d2ab")
    void disable() throws ModuleException;

}
