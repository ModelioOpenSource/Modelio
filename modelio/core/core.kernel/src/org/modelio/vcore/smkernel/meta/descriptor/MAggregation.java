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
    None,
    /**
     * Shared composition.
     */
    SharedAggregation,
    /**
     * Composition
     */
    Composition;
}
