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

package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;

/**
 * SendSignalAction v0.0.9054
 * 
 * 
 * SendSignalAction is an action that creates a signal instance from its inputs, and transmits it to the target object, where it may cause the firing of a state machine transition or the execution of an activity. The argument values are available to the execution of associated behaviors. The requestor continues execution immediately. 
 */
@objid ("003d3af6-c4bf-1fd8-97fe-001ec947cd2a")
public interface SendSignalAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("2abdd5f7-133e-4e30-bbab-7c3239908d10")
    public static final String MNAME = "SendSignalAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("33e516b2-730a-405e-842e-02f1efc8b562")
    public static final String MQNAME = "Standard.SendSignalAction";

    /**
     * Getter for relation 'SendSignalAction->Sent'
     * 
     * Metamodel description:
     * <i>The type of signal transmitted.</i>
     */
    @objid ("32d7800b-c4f3-4739-953f-b632dfba9aa0")
    Signal getSent();

    /**
     * Setter for relation 'SendSignalAction->Sent'
     * 
     * Metamodel description:
     * <i>The type of signal transmitted.</i>
     */
    @objid ("20e005e4-a642-401c-a2de-213a0509b046")
    void setSent(Signal value);

}
