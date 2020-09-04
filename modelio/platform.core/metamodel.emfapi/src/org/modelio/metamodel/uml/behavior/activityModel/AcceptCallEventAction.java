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
import org.modelio.metamodel.uml.statik.Operation;

/**
 * AcceptCallEventAction v0.0.9054
 * 
 * 
 * AcceptCallAction is an accept event action representing the receipt of a synchronous call request. In addition to the normal operation parameters, the action produces an output that is needed later to supply the information to the ReplyAction necessary to return control to the caller.
 * 
 * This action is for synchronous calls. If it is used to handle an asynchronous call, execution of the subsequent reply action will complete immediately with no effects.
 */
@objid ("00241b8e-c4bf-1fd8-97fe-001ec947cd2a")
public interface AcceptCallEventAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("91139ed7-4dbf-41cc-8b4d-9cd8cb6936da")
    public static final String MNAME = "AcceptCallEventAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("c7c15c72-c2e0-40b2-966f-c9c077085e77")
    public static final String MQNAME = "Standard.AcceptCallEventAction";

    /**
     * Getter for relation 'AcceptCallEventAction->Called'
     * 
     * Metamodel description:
     * <i>The Operation invoked by the call event.</i>
     */
    @objid ("f0115a21-4e17-4eee-bc13-b83b7123ca2b")
    Operation getCalled();

    /**
     * Setter for relation 'AcceptCallEventAction->Called'
     * 
     * Metamodel description:
     * <i>The Operation invoked by the call event.</i>
     */
    @objid ("92798763-c583-4113-894d-db427f49131e")
    void setCalled(Operation value);

}
