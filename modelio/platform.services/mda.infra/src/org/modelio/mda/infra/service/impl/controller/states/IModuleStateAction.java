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

package org.modelio.mda.infra.service.impl.controller.states;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;

/**
 * Module action to execute when entering/existing a state or on a transition.
 */
@objid ("57c8991b-ffda-4b29-888e-a4db6125b265")
public interface IModuleStateAction {
    /**
     * Execute the action
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException to refuse state change
     */
    @objid ("dd139934-bc36-409d-b528-ce8c9cce52e6")
    void execute() throws ModuleException;

}
