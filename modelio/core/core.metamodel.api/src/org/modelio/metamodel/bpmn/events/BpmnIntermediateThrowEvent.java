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

package org.modelio.metamodel.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnIntermediateThrowEvent v0.0.9054
 * 
 * 
 * <p>the Intermediate Event indicates where something happens (an Event) somewhere between the start and end of a Process. It will affect the flow of the Process, but will not start or (directly) terminate the Process. Intermediate Events can be used to:</p>
 * 
 * <ul>
 * 	<li>Show where Messages are expected or sent within the Process,</li>
 * 	<li>Show delays are expected within the Process,</li>
 * 	<li>Disrupt the normal flow through exception handling, or</li>
 * 	<li>Show the extra work required for compensation.</li>
 * </ul>
 */
@objid ("008f5aa2-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnIntermediateThrowEvent extends BpmnThrowEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("a8a5e70d-2a11-469c-9cf6-f20aba6e84a1")
    public static final String MNAME = "BpmnIntermediateThrowEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("fd5ff0fe-b31c-4ba4-9db9-2f11efa064c4")
    public static final String MQNAME = "Standard.BpmnIntermediateThrowEvent";

}
