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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;

/**
 * Usage v0.0.9054
 * 
 * 
 * A Usage is a relationship in which one element requires another element (or set of elements) for its full implementation or operation. 
 * 
 * In the metamodel, a Usage is a Dependency in which the client requires the presence of the supplier.
 * 
 * 
 * 
 */
@objid ("0090bf1e-c4be-1fd8-97fe-001ec947cd2a")
public interface Usage extends Dependency {
    /**
     * The metaclass simple name.
     */
    @objid ("0330471d-90c5-4a9b-86a2-625a0b5d9137")
    public static final String MNAME = "Usage";

    /**
     * The metaclass qualified name.
     */
    @objid ("0a023f8c-e40b-42c3-8c5e-72005cc0a96f")
    public static final String MQNAME = "Standard.Usage";
}

