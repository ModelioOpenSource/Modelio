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

package org.modelio.metamodel.uml.behavior.commonBehaviors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * OpaqueBehavior v0.0.9054
 * 
 * 
 * The semantics of the behavior are determined by the implementation.
 * 
 * MDA components have to provide notes types and tag types to define the semantics.
 * 
 * 
 */
@objid ("00419286-c4bf-1fd8-97fe-001ec947cd2a")
public interface OpaqueBehavior extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("340dc227-7cab-4f4f-b788-e7e1a8345653")
    public static final String MNAME = "OpaqueBehavior";

    /**
     * The metaclass qualified name.
     */
    @objid ("44015eb3-71c7-4204-8d36-090bc1977216")
    public static final String MQNAME = "Standard.OpaqueBehavior";

    /**
     * Getter for attribute 'OpaqueBehavior.Body'
     * 
     * Metamodel description:
     * <i>Specifies the behavior in one language.</i>
     * 
     */
    @objid ("a2cb63ec-e7a5-4df3-8486-f72abbb3bee2")
    String getBody();

    /**
     * Setter for attribute 'OpaqueBehavior.Body'
     * 
     * Metamodel description:
     * <i>Specifies the behavior in one language.</i>
     * 
     */
    @objid ("8bc41105-4a48-4b2c-80fc-130720d53ee0")
    void setBody(String value);
}

