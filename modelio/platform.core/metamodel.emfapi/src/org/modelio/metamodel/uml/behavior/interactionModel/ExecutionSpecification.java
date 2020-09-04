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
package org.modelio.metamodel.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;

/**
 * ExecutionSpecification v0.0.9054
 * 
 * 
 * An ExecutionSpecification is a specification of the execution of a unit of behavior or action within the Lifeline. The duration of an ExecutionSpecification is represented by two ExecutionOccurrenceSpecifications, the start ExecutionOccurrenceSpecification and the finish ExecutionOccurrenceSpecification.
 */
@objid ("0044c988-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExecutionSpecification extends InteractionFragment {
    /**
     * The metaclass simple name.
     */
    @objid ("5d97a4fe-1c4b-4908-b388-52a3f85156ff")
    public static final String MNAME = "ExecutionSpecification";

    /**
     * The metaclass qualified name.
     */
    @objid ("6b163d43-e484-4bf6-90ba-6be13327a65b")
    public static final String MQNAME = "Standard.ExecutionSpecification";

    /**
     * Getter for relation 'ExecutionSpecification->Finish'
     * 
     * Metamodel description:
     * <i>References the OccurrenceSpecification that designates the completion of the execution.</i>
     */
    @objid ("d461367e-99eb-4f6d-8cf4-69580c24c61b")
    ExecutionOccurenceSpecification getFinish();

    /**
     * Setter for relation 'ExecutionSpecification->Finish'
     * 
     * Metamodel description:
     * <i>References the OccurrenceSpecification that designates the completion of the execution.</i>
     */
    @objid ("f64439ac-5975-4780-a575-a71e7a852c53")
    void setFinish(ExecutionOccurenceSpecification value);

    /**
     * Getter for relation 'ExecutionSpecification->Start'
     * 
     * Metamodel description:
     * <i>References the OccurrenceSpecification that designates the start of the execution.</i>
     */
    @objid ("6c58ec82-beec-4c88-bbd9-b188620a511b")
    ExecutionOccurenceSpecification getStart();

    /**
     * Setter for relation 'ExecutionSpecification->Start'
     * 
     * Metamodel description:
     * <i>References the OccurrenceSpecification that designates the start of the execution.</i>
     */
    @objid ("38b7057f-a850-438f-9725-d9bb8872ebbf")
    void setStart(ExecutionOccurenceSpecification value);

}
