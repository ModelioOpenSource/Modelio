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
package org.modelio.metamodel.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnEndEvent v0.0.9054
 * 
 * 
 * the End Event indicates where a Process will end. In terms of Sequence Flow, the End Event
 * ends the flow of the Process, and thus, will not have any outgoing Sequence Flow
 */
@objid ("008a8f9a-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnEndEvent extends BpmnThrowEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("e46d6a99-d8af-40f5-a866-3aa6817dc6e0")
    public static final String MNAME = "BpmnEndEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("5fb2ab99-c42e-4ef9-9a92-a18524ff7b9c")
    public static final String MQNAME = "Standard.BpmnEndEvent";

}
