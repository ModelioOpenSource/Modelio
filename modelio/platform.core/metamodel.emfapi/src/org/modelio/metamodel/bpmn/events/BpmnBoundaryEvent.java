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
package org.modelio.metamodel.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;

/**
 * BpmnBoundaryEvent v0.0.9054
 * 
 * 
 * Event attached to the boundary of an activity. 
 */
@objid ("00875c94-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnBoundaryEvent extends BpmnCatchEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("d3ac556e-261c-49f0-bba4-1ac9130c9ae9")
    public static final String MNAME = "BpmnBoundaryEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("300fdcc7-8fa9-44d3-9297-8af7800a4862")
    public static final String MQNAME = "Standard.BpmnBoundaryEvent";

    /**
     * Getter for attribute 'BpmnBoundaryEvent.CancelActivity'
     * 
     * Metamodel description:
     * <i>Denotes whether the Activity should be cancelled or not, i.e., whether the boundary catch Event acts as an Error or an Escalation. If the Activity is not cancelled, multiple instances of that handler can run concurrently.
     * This attribute cannot be applied to Error Events (where it?s always true), or Compensation Events (where it doesn?t apply).</i>
     */
    @objid ("2fe95ba2-9e9f-46e0-9d86-b5a15123d12c")
    boolean isCancelActivity();

    /**
     * Setter for attribute 'BpmnBoundaryEvent.CancelActivity'
     * 
     * Metamodel description:
     * <i>Denotes whether the Activity should be cancelled or not, i.e., whether the boundary catch Event acts as an Error or an Escalation. If the Activity is not cancelled, multiple instances of that handler can run concurrently.
     * This attribute cannot be applied to Error Events (where it?s always true), or Compensation Events (where it doesn?t apply).</i>
     */
    @objid ("c711f866-fd24-4244-ba87-72bbd30a220a")
    void setCancelActivity(boolean value);

    /**
     * Getter for relation 'BpmnBoundaryEvent->AttachedToRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7fec0734-e0d6-47ec-aa60-3b97ba344c86")
    BpmnActivity getAttachedToRef();

    /**
     * Setter for relation 'BpmnBoundaryEvent->AttachedToRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("afe57390-a26b-4a18-bbbb-51cc23a7a04f")
    void setAttachedToRef(BpmnActivity value);

}
