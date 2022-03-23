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
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.controller.states.IModuleFeature;

/**
 * Abstract implementation of {@link IModuleFeature}.
 */
@objid ("d01bab03-b95c-48b8-80ce-a90c6c75929a")
public abstract class AbstractFeature implements IModuleFeature {
    @objid ("4a039545-4521-4c89-83fc-7bde5c13c31c")
    protected final IRTModuleAccess module;

    /**
     * @param module the module
     */
    @objid ("2595e8ef-2e83-48b8-bc8d-b59c39db7e8e")
    public  AbstractFeature(IRTModuleAccess module) {
        this.module = module;
    }

}
