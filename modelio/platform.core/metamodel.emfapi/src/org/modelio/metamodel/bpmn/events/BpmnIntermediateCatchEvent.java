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

/**
 * BpmnIntermediateCatchEvent v0.0.9054
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
 * 
 * <p>&nbsp;</p>
 */
@objid ("008ea3be-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnIntermediateCatchEvent extends BpmnCatchEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("95c36ac4-3906-42df-ad5d-9397461c7324")
    public static final String MNAME = "BpmnIntermediateCatchEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("0ef8166c-cd12-49c1-bedb-ba56e2dfd4c3")
    public static final String MQNAME = "Standard.BpmnIntermediateCatchEvent";

}
