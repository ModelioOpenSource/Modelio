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

/**
 * ValuePin v0.0.9054
 * 
 * 
 * null
 */
@objid ("003ed672-c4bf-1fd8-97fe-001ec947cd2a")
public interface ValuePin extends InputPin {
    /**
     * The metaclass simple name.
     */
    @objid ("4889404d-a892-45e1-9697-b6509e505193")
    public static final String MNAME = "ValuePin";

    /**
     * The metaclass qualified name.
     */
    @objid ("d4a47967-ae64-4fc4-9048-8bfeccbe212c")
    public static final String MQNAME = "Standard.ValuePin";

    /**
     * Getter for attribute 'ValuePin.Value'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("40a55e03-6a85-497a-b5cf-040047f41698")
    String getValue();

    /**
     * Setter for attribute 'ValuePin.Value'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("59cc87be-57ba-45aa-8ae6-bbf95bd8ff65")
    void setValue(String value);

}
