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
 * CentralBufferNode v0.0.9054
 * 
 * 
 * A central buffer node accepts tokens from upstream object nodes and passes them along to downstream object nodes. 
 * 
 * Central buffer nodes act as a buffer for multiple in flows and out flows from other object nodes. 
 */
@objid ("002c1492-c4bf-1fd8-97fe-001ec947cd2a")
public interface CentralBufferNode extends ObjectNode {
    /**
     * The metaclass simple name.
     */
    @objid ("38d56aab-e2b2-49e0-aefe-1969d206c4d5")
    public static final String MNAME = "CentralBufferNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("18cb56c2-aa8b-4f4a-9338-e624db1e50a9")
    public static final String MQNAME = "Standard.CentralBufferNode";

}
