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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * DataStoreNode v0.0.9054
 * 
 * 
 * A data store keeps all tokens that enter it, copying them when they are chosen to move downstream. Incoming tokens containing a particular object replace any tokens in the object node containing that object.
 */
@objid ("002f1fe8-c4bf-1fd8-97fe-001ec947cd2a")
public interface DataStoreNode extends CentralBufferNode {
    /**
     * The metaclass simple name.
     */
    @objid ("711675df-7713-41e3-a5c1-c24fac7c0554")
    public static final String MNAME = "DataStoreNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("0ed4c131-7e3a-482c-9988-6643bf505b3f")
    public static final String MQNAME = "Standard.DataStoreNode";

}
