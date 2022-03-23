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
package org.modelio.vcore.smkernel.meta.descriptor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Metamodel dependency aggregation type.
 * @author cma
 * @since 3.6
 */
@objid ("4d2bce4b-35d9-4dda-8997-e06ed253a045")
public enum MAggregation {
    /**
     * No aggregation.
     */
    @objid ("959777a3-f2ac-47be-ba80-086b2ba9df0e")
    None,
    /**
     * Shared composition.
     */
    @objid ("9c33a4ee-4984-4ada-acae-e3385705cd0a")
    SharedAggregation,
    /**
     * Composition
     */
    @objid ("a963a8ac-7ae7-4040-a51d-96dd6de3d975")
    Composition;

}
