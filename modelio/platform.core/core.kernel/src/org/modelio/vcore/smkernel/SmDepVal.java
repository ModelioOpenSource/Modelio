/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Represents the tuple SmDependency, MObject.
 * <p>
 * Returned by {@link SmObjectImpl#getCompositionRelation()}
 */
@objid ("007daa3c-b535-1f33-94a4-001ec947cd2a")
public class SmDepVal {
    /**
     * The dependency
     */
    @objid ("66d450f4-58ba-11e1-9a05-001ec947ccaf")
    public final SmDependency dep;

    /**
     * The dependency value
     */
    @objid ("779cf9eb-9b64-11e1-94a3-001ec947ccaf")
    public final MObject value;

    /**
     * @param dep the dependency
     * @param value the dependency value
     */
    @objid ("0082e0f6-b5bf-1f33-94a4-001ec947cd2a")
    public SmDepVal(final SmDependency dep, final MObject value) {
        this.dep = dep;
        this.value = value;
    }

}
