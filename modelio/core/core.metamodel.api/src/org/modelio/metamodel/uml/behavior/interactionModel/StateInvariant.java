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
package org.modelio.metamodel.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * StateInvariant v0.0.9054
 * 
 * 
 * A StateInvariant is a runtime constraint on the participants of the interaction. It may be used to specify a variety of different kinds of constraints, such as values of attributes or variables, internal or external states, and so on.
 * 
 * A StateInvariant is an InteractionFragment and is placed on a Lifeline.
 */
@objid ("004b7b34-c4bf-1fd8-97fe-001ec947cd2a")
public interface StateInvariant extends OccurrenceSpecification {
    /**
     * The metaclass simple name.
     */
    @objid ("c6cc4da1-4007-43a5-a855-28a2af5c98b4")
    public static final String MNAME = "StateInvariant";

    /**
     * The metaclass qualified name.
     */
    @objid ("ea243e7a-e2b8-4da1-8755-87f67b9f102c")
    public static final String MQNAME = "Standard.StateInvariant";

    /**
     * Getter for attribute 'StateInvariant.Body'
     * 
     * Metamodel description:
     * <i>A Constraint that should hold at runtime for this StateInvariant.</i>
     */
    @objid ("301c0286-fc9d-4366-983d-6dd5583d4e47")
    String getBody();

    /**
     * Setter for attribute 'StateInvariant.Body'
     * 
     * Metamodel description:
     * <i>A Constraint that should hold at runtime for this StateInvariant.</i>
     */
    @objid ("bc1c63bc-bfa1-46b1-bdb2-ee87ae94db4b")
    void setBody(String value);

    /**
     * Getter for attribute 'StateInvariant.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c3e0f7ce-df06-49e9-9c3d-c097af82281d")
    int getEndLineNumber();

    /**
     * Setter for attribute 'StateInvariant.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("45965828-f890-497c-acfc-62025ffd2d6a")
    void setEndLineNumber(int value);

}
