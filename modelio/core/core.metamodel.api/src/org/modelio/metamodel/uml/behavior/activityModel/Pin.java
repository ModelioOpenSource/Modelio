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
import org.modelio.metamodel.uml.statik.Parameter;

/**
 * Pin v0.0.9054
 * 
 * 
 * A pin represents an input to an action or an output from an action.
 */
@objid ("003b6582-c4bf-1fd8-97fe-001ec947cd2a")
public interface Pin extends ObjectNode {
    /**
     * The metaclass simple name.
     */
    @objid ("656085c1-c72c-46e3-8772-86d29060b463")
    public static final String MNAME = "Pin";

    /**
     * The metaclass qualified name.
     */
    @objid ("226af19d-a595-4e5c-8956-a506c7f9e14b")
    public static final String MQNAME = "Standard.Pin";

    /**
     * Getter for attribute 'Pin.IsControl'
     * 
     * Metamodel description:
     * <i>Indicates whether the pins provide data to the actions, or just controls when it executes it.</i>
     */
    @objid ("db0869b0-0b85-47b0-a14b-74bc1b6b7e20")
    boolean isIsControl();

    /**
     * Setter for attribute 'Pin.IsControl'
     * 
     * Metamodel description:
     * <i>Indicates whether the pins provide data to the actions, or just controls when it executes it.</i>
     */
    @objid ("bb28f25d-295c-427f-9f74-da49b2ea46a2")
    void setIsControl(boolean value);

    /**
     * Getter for attribute 'Pin.IsExpansion'
     * 
     * Metamodel description:
     * <i>Indicates whether the pin is used for expansion: enables an iteration of the action on the tokens attached to the pin.</i>
     */
    @objid ("7d775630-24fb-45e8-8456-0cb1af7d02a7")
    boolean isIsExpansion();

    /**
     * Setter for attribute 'Pin.IsExpansion'
     * 
     * Metamodel description:
     * <i>Indicates whether the pin is used for expansion: enables an iteration of the action on the tokens attached to the pin.</i>
     */
    @objid ("869f0fb5-0d9a-40dc-9cee-1753025222f9")
    void setIsExpansion(boolean value);

    /**
     * Getter for relation 'Pin->Matched'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("431b47b4-99a4-444b-93d3-ab630c017bd5")
    Parameter getMatched();

    /**
     * Setter for relation 'Pin->Matched'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("637250f9-e39a-4ebe-9a1e-4d260eee22c7")
    void setMatched(Parameter value);

}
