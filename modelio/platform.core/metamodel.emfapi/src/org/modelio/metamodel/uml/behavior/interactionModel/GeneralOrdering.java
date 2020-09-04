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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * GeneralOrdering v0.0.9054
 * 
 * 
 * <p>This mechanism provides the ability to define partial orders of OccurrenceSpecifications that may otherwise not have a specified order.</p><p>A GeneralOrdering may appear anywhere in an Interaction, but only between OccurrenceSpecifications.</p>
 */
@objid ("0045de18-c4bf-1fd8-97fe-001ec947cd2a")
public interface GeneralOrdering extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("fa5a9cee-c0ef-4c02-bb88-eb54ab800e9c")
    public static final String MNAME = "GeneralOrdering";

    /**
     * The metaclass qualified name.
     */
    @objid ("d8fcf98b-0255-4082-b9c8-009fe5e6c824")
    public static final String MQNAME = "Standard.GeneralOrdering";

    /**
     * Getter for relation 'GeneralOrdering->Before'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ca176abe-2e93-481b-a767-e4105b7de98a")
    OccurrenceSpecification getBefore();

    /**
     * Setter for relation 'GeneralOrdering->Before'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1c6aaec8-abe8-4837-96f9-a549ad051c03")
    void setBefore(OccurrenceSpecification value);

    /**
     * Getter for relation 'GeneralOrdering->After'
     * 
     * Metamodel description:
     * <i>The OccurrenceSpecification referred comes after the OccurrenceSpecification referred by before.</i>
     */
    @objid ("df4340e9-880f-422a-8ba7-a04288ff3d24")
    OccurrenceSpecification getAfter();

    /**
     * Setter for relation 'GeneralOrdering->After'
     * 
     * Metamodel description:
     * <i>The OccurrenceSpecification referred comes after the OccurrenceSpecification referred by before.</i>
     */
    @objid ("18a66cbe-683a-42d7-9237-ccbecb212de9")
    void setAfter(OccurrenceSpecification value);

}
