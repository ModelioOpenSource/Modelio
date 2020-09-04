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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;

/**
 * InputPin v0.0.9054
 * 
 * 
 * An input pin is a pin that holds input values to be consumed by an action.
 * 
 * An action cannot start execution if an input pin has fewer values than the lower multiplicity. The upper multiplicity determines how many values are consumed by a single execution of the action.
 */
@objid ("003503ea-c4bf-1fd8-97fe-001ec947cd2a")
public interface InputPin extends Pin {
    /**
     * The metaclass simple name.
     */
    @objid ("f615d082-15fe-47e4-9bbd-15cdd1de881e")
    public static final String MNAME = "InputPin";

    /**
     * The metaclass qualified name.
     */
    @objid ("6ce9b01e-60bd-4af8-ad4e-07423f8909e8")
    public static final String MQNAME = "Standard.InputPin";

    /**
     * Getter for attribute 'InputPin.IsSelf'
     * 
     * Metamodel description:
     * <i>Modelio extension: expresses that the pin represents the target object on which the action is executed.</i>
     */
    @objid ("0deb7104-cd11-4714-9c97-b58d88f880ac")
    boolean isIsSelf();

    /**
     * Setter for attribute 'InputPin.IsSelf'
     * 
     * Metamodel description:
     * <i>Modelio extension: expresses that the pin represents the target object on which the action is executed.</i>
     */
    @objid ("7ce4e7bd-0cf8-4fa1-ac5f-6ff4d86d0efb")
    void setIsSelf(boolean value);

    /**
     * Getter for relation 'InputPin->Handler'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e447dfab-667f-45a5-a6d0-0443b2deca2e")
    EList<ExceptionHandler> getHandler();

    /**
     * Filtered Getter for relation 'InputPin->Handler'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("21f3ff45-2fc0-4b61-b366-59dab1a05320")
    <T extends ExceptionHandler> List<T> getHandler(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InputPin->Inputing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bae910eb-643c-4670-9c79-ba0ca947f44f")
    ActivityAction getInputing();

    /**
     * Setter for relation 'InputPin->Inputing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("07c700d5-d3d5-4026-98c4-cb326fd331ca")
    void setInputing(ActivityAction value);

}
