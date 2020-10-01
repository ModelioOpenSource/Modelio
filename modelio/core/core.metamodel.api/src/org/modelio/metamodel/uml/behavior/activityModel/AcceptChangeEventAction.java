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
 * AcceptChangeEventAction v0.0.9054
 * 
 * 
 * A change event occurs when the Boolean-valued expression becomes true. For example, as a result of a change in the value held in a slot corresponding to an attribute, or a change in the value referenced by a link corresponding to an association. 
 * 
 * A change event is raised implicitly and is not the result of an explicit action. 
 * 
 * Each time the value of the change expression changes from false to true, the change event action is triggered.
 */
@objid ("00249514-c4bf-1fd8-97fe-001ec947cd2a")
public interface AcceptChangeEventAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("adf7ead8-afb2-45ba-869a-98fb12af2e04")
    public static final String MNAME = "AcceptChangeEventAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("1db9b473-25a4-4681-8589-d0708e8cae0b")
    public static final String MQNAME = "Standard.AcceptChangeEventAction";

    /**
     * Getter for attribute 'AcceptChangeEventAction.ChangeExpresion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("84036d92-3561-4096-a70f-77b9776616bf")
    String getChangeExpresion();

    /**
     * Setter for attribute 'AcceptChangeEventAction.ChangeExpresion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("723e6815-1a33-4296-92cc-bb23678370cf")
    void setChangeExpresion(String value);

}
