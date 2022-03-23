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
 * CallAction v0.0.9054
 */
@objid ("002a65de-c4bf-1fd8-97fe-001ec947cd2a")
public interface CallAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("6240223d-3c58-43c1-b622-34f0afc1c42e")
    public static final String MNAME = "CallAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("b258b8a1-8440-4c73-bb84-54cf40d37713")
    public static final String MQNAME = "Standard.CallAction";

    /**
     * Getter for attribute 'CallAction.IsSynchronous'
     * 
     * Metamodel description:
     * <i>If true, the call is synchronous and the caller waits for completion of the invoked behavior. If false, the call is asynchronous and the caller proceeds immediately and does not expect a return value.</i>
     */
    @objid ("f9197851-7f6f-4064-a5cc-c8f2750f05cb")
    boolean isIsSynchronous();

    /**
     * Setter for attribute 'CallAction.IsSynchronous'
     * 
     * Metamodel description:
     * <i>If true, the call is synchronous and the caller waits for completion of the invoked behavior. If false, the call is asynchronous and the caller proceeds immediately and does not expect a return value.</i>
     */
    @objid ("33e4f098-d520-4c57-b5bc-ec5f860c92ac")
    void setIsSynchronous(boolean value);

}
