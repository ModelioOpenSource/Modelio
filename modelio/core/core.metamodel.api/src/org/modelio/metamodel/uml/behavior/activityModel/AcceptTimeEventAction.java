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

/**
 * AcceptTimeEventAction v0.0.9054
 * 
 * 
 * An AcceptTimeEventAction waits for the occurrence of a time event meeting a specified condition. 
 * The time expression specifies a point in time. It can be absolute or can be relative to some other point in time.
 */
@objid ("00258b22-c4bf-1fd8-97fe-001ec947cd2a")
public interface AcceptTimeEventAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("5d5cc6fa-7dc1-4a78-a2c6-b2cb8d4dd677")
    public static final String MNAME = "AcceptTimeEventAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("c5d6400f-c10c-498e-9a85-801374d2c9d1")
    public static final String MQNAME = "Standard.AcceptTimeEventAction";

    /**
     * Getter for attribute 'AcceptTimeEventAction.TimeExpresion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("37e92c69-263e-4f7f-b80c-d53d0897393a")
    String getTimeExpresion();

    /**
     * Setter for attribute 'AcceptTimeEventAction.TimeExpresion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4510feeb-769d-443a-9a87-5a7acc4c7389")
    void setTimeExpresion(String value);

}
