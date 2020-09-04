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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlowEffectKind;

/**
 * ObjectFlow v0.0.9054
 * 
 * 
 * An object flow is an activity edge that only passes object and data tokens. 
 * Tokens offered by the source node are all offered to the target node.
 * 
 * If transformation behavior is specified, then each token offered to the edge is passed to the behavior, and the output of the behavior is given to the target node for consideration instead of the token that was input to the transformation expression.
 * 
 * If a selection expression is specified, then it is used to offer a token from the source object node to the edge, rather than using object node's ordering. It has the same semantics as selection expression on object nodes. 
 * 
 * Modelio specific : 
 * In order to make the selection and transformation sections easier to fill, these are string expressions in Modelio, instead of Behaviors in the OMG UML specification.
 */
@objid ("00388c90-c4bf-1fd8-97fe-001ec947cd2a")
public interface ObjectFlow extends ActivityEdge {
    /**
     * The metaclass simple name.
     */
    @objid ("58a42bad-d2fe-47bf-8b97-4605a81ca9aa")
    public static final String MNAME = "ObjectFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("ddc48a5f-90a6-4629-b349-6eeb304aa880")
    public static final String MQNAME = "Standard.ObjectFlow";

    /**
     * Getter for attribute 'ObjectFlow.TransformationBehavior'
     * 
     * Metamodel description:
     * <i>Expression that changes or replaces data tokens flowing along edge.</i>
     */
    @objid ("6f9fc3c9-8772-4994-9afc-0db279e5cab2")
    String getTransformationBehavior();

    /**
     * Setter for attribute 'ObjectFlow.TransformationBehavior'
     * 
     * Metamodel description:
     * <i>Expression that changes or replaces data tokens flowing along edge.</i>
     */
    @objid ("df883675-77a4-4b56-b7be-6eb878a0b2e4")
    void setTransformationBehavior(String value);

    /**
     * Getter for attribute 'ObjectFlow.SelectionBehavior'
     * 
     * Metamodel description:
     * <i>Selects tokens from a source object node.</i>
     */
    @objid ("a4c59cf4-4ed4-4e43-82b5-727c94baa4d2")
    String getSelectionBehavior();

    /**
     * Setter for attribute 'ObjectFlow.SelectionBehavior'
     * 
     * Metamodel description:
     * <i>Selects tokens from a source object node.</i>
     */
    @objid ("3c95b54f-e3e3-485b-8e55-a326e64e3aa2")
    void setSelectionBehavior(String value);

    /**
     * Getter for attribute 'ObjectFlow.IsMultiCast'
     * 
     * Metamodel description:
     * <i>Indicates whether the objects in the flow are passed by multicasting.</i>
     */
    @objid ("f3d804c7-8b96-48b1-b536-379d402e5d09")
    boolean isIsMultiCast();

    /**
     * Setter for attribute 'ObjectFlow.IsMultiCast'
     * 
     * Metamodel description:
     * <i>Indicates whether the objects in the flow are passed by multicasting.</i>
     */
    @objid ("a39ad545-4529-4faa-8ac4-eddb257d1555")
    void setIsMultiCast(boolean value);

    /**
     * Getter for attribute 'ObjectFlow.IsMultiReceive'
     * 
     * Metamodel description:
     * <i>Indicates whether the objects in the flow are gathered from respondents to multicasting.</i>
     */
    @objid ("302e0f96-8ba8-44dd-bfbe-7f6cfb81faca")
    boolean isIsMultiReceive();

    /**
     * Setter for attribute 'ObjectFlow.IsMultiReceive'
     * 
     * Metamodel description:
     * <i>Indicates whether the objects in the flow are gathered from respondents to multicasting.</i>
     */
    @objid ("4e8aa1d4-c82b-43f4-9e2d-6f853a420e50")
    void setIsMultiReceive(boolean value);

    /**
     * Getter for attribute 'ObjectFlow.Effect'
     * 
     * Metamodel description:
     * <i>Specifies the effect that the owner of the object flow has on values that it represents.</i>
     */
    @objid ("685d416f-410c-4db2-960b-1160153d9e5e")
    ObjectFlowEffectKind getEffect();

    /**
     * Setter for attribute 'ObjectFlow.Effect'
     * 
     * Metamodel description:
     * <i>Specifies the effect that the owner of the object flow has on values that it represents.</i>
     */
    @objid ("a01fc2e7-8f8f-4557-8562-0c1aefb8e392")
    void setEffect(ObjectFlowEffectKind value);

}
