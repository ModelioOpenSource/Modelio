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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * ObjectNode v0.0.9054
 * 
 * 
 * An object node is an activity node that indicates an instance of a particular classifier, possibly in a particular state, may be available at a particular point in the activity. Object nodes can be used in a variety of ways, depending on where objects are flowing from and to, as described in the semantics section.
 * 
 * The ordering of an object node specifies the order in which tokens in the node are offered to the outgoing edges. This can be set to require that tokens do not overtake each other as they pass through the node (FIFO), or that they do (LIFO or modeler-defined ordering). Modeler-defined ordering is indicated by an ordering value of ordered, and a selection expression that determines what token to offer to the edges. 
 * 
 * Modelio extension:
 * An object node can represent an attribute, a parameter, an association role or an instance.
 */
@objid ("003940c2-c4bf-1fd8-97fe-001ec947cd2a")
public interface ObjectNode extends ActivityNode {
    /**
     * The metaclass simple name.
     */
    @objid ("a87ec89d-f984-4d08-9004-490b46dfac52")
    public static final String MNAME = "ObjectNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("0b05fbfe-e68c-4db9-9b89-558306fa3e10")
    public static final String MQNAME = "Standard.ObjectNode";

    /**
     * Getter for attribute 'ObjectNode.IsControlType'
     * 
     * Metamodel description:
     * <i>Indicates whether the type of the object node is to be treated as control.</i>
     */
    @objid ("81fe4a86-f4d1-4283-bc97-9b9f6fd567aa")
    boolean isIsControlType();

    /**
     * Setter for attribute 'ObjectNode.IsControlType'
     * 
     * Metamodel description:
     * <i>Indicates whether the type of the object node is to be treated as control.</i>
     */
    @objid ("2a4bfb19-1c1d-4cdf-8a25-4bb35ad64705")
    void setIsControlType(boolean value);

    /**
     * Getter for attribute 'ObjectNode.Ordering'
     * 
     * Metamodel description:
     * <i>Indicates if and how the tokens in the object node are ordered for selection to traverse edges outgoing from the object node.</i>
     */
    @objid ("5289a3d2-ad5d-43b9-bbeb-7b590f1f6779")
    ObjectNodeOrderingKind getOrdering();

    /**
     * Setter for attribute 'ObjectNode.Ordering'
     * 
     * Metamodel description:
     * <i>Indicates if and how the tokens in the object node are ordered for selection to traverse edges outgoing from the object node.</i>
     */
    @objid ("9d8126bd-9dda-4b84-8646-1293bd59f103")
    void setOrdering(ObjectNodeOrderingKind value);

    /**
     * Getter for attribute 'ObjectNode.SelectionBehavior'
     * 
     * Metamodel description:
     * <i>Selects tokens for outgoing edges.</i>
     */
    @objid ("91a4a899-ac9a-493a-9426-7e3cbf2db0ce")
    String getSelectionBehavior();

    /**
     * Setter for attribute 'ObjectNode.SelectionBehavior'
     * 
     * Metamodel description:
     * <i>Selects tokens for outgoing edges.</i>
     */
    @objid ("07cb7104-c275-4650-a074-1b7397c76011")
    void setSelectionBehavior(String value);

    /**
     * Getter for attribute 'ObjectNode.UpperBound'
     * 
     * Metamodel description:
     * <i>The maximum number of tokens allowed in the node. Objects cannot flow into the node if the upper bound is reached.</i>
     */
    @objid ("f9dd7386-eabb-4273-9156-302b69035236")
    String getUpperBound();

    /**
     * Setter for attribute 'ObjectNode.UpperBound'
     * 
     * Metamodel description:
     * <i>The maximum number of tokens allowed in the node. Objects cannot flow into the node if the upper bound is reached.</i>
     */
    @objid ("1bbc00db-7042-43a0-9a3b-0e5c9cd9fa7d")
    void setUpperBound(String value);

    /**
     * Getter for relation 'ObjectNode->Represented'
     * 
     * Metamodel description:
     * <i>Instance that is represented by the ObjectNode.</i>
     */
    @objid ("2ae08ec2-678f-447f-8f04-c046658e043a")
    Instance getRepresented();

    /**
     * Setter for relation 'ObjectNode->Represented'
     * 
     * Metamodel description:
     * <i>Instance that is represented by the ObjectNode.</i>
     */
    @objid ("22aed1c7-49a6-42f0-80e4-9998eb409913")
    void setRepresented(Instance value);

    /**
     * Getter for relation 'ObjectNode->RepresentedRealParameter'
     * 
     * Metamodel description:
     * <i>The represented parameter. For a Pin on a CallOperationAction or a CallBehaviorAction, links to the matched parameter of the called operation or behavior.</i>
     */
    @objid ("f6a1c9f8-1798-4733-96df-14755bb3bed4")
    BehaviorParameter getRepresentedRealParameter();

    /**
     * Setter for relation 'ObjectNode->RepresentedRealParameter'
     * 
     * Metamodel description:
     * <i>The represented parameter. For a Pin on a CallOperationAction or a CallBehaviorAction, links to the matched parameter of the called operation or behavior.</i>
     */
    @objid ("fe1f7503-142e-48b3-9747-2bf80ab8a514")
    void setRepresentedRealParameter(BehaviorParameter value);

    /**
     * Getter for relation 'ObjectNode->Type'
     * 
     * Metamodel description:
     * <i>Type of the object node.</i>
     */
    @objid ("d154c61d-8ba1-45ea-9a0b-243506de23e3")
    GeneralClass getType();

    /**
     * Setter for relation 'ObjectNode->Type'
     * 
     * Metamodel description:
     * <i>Type of the object node.</i>
     */
    @objid ("b779334a-0c45-4f72-b0cf-2200b7d264d2")
    void setType(GeneralClass value);

    /**
     * Getter for relation 'ObjectNode->RepresentedRole'
     * 
     * Metamodel description:
     * <i>Shortcut to directly represent an association end by the node. This replaces the normal way: representing an instance bound to the associationEnd.</i>
     */
    @objid ("64ac734f-bf5c-455a-8d3f-e0794921a6d1")
    AssociationEnd getRepresentedRole();

    /**
     * Setter for relation 'ObjectNode->RepresentedRole'
     * 
     * Metamodel description:
     * <i>Shortcut to directly represent an association end by the node. This replaces the normal way: representing an instance bound to the associationEnd.</i>
     */
    @objid ("7630abcc-8457-492e-8c6d-629ef1d17530")
    void setRepresentedRole(AssociationEnd value);

    /**
     * Getter for relation 'ObjectNode->RepresentedAttribute'
     * 
     * Metamodel description:
     * <i>Represented attribute. Shortcut to directly represent an attribute, instead of representing an instance bound to the attribute.</i>
     */
    @objid ("3f9a9955-32d0-44d7-b757-7bf0d2f72523")
    Attribute getRepresentedAttribute();

    /**
     * Setter for relation 'ObjectNode->RepresentedAttribute'
     * 
     * Metamodel description:
     * <i>Represented attribute. Shortcut to directly represent an attribute, instead of representing an instance bound to the attribute.</i>
     */
    @objid ("53e9efa2-a890-482c-bf37-786a2662dd4f")
    void setRepresentedAttribute(Attribute value);

    /**
     * Getter for relation 'ObjectNode->InState'
     * 
     * Metamodel description:
     * <i>The required state of the object available at this point in the activity. This state must belong to the type of the objectNode, or of the represented element. </i>
     */
    @objid ("754338f1-5283-4643-960a-aca891cad987")
    State getInState();

    /**
     * Setter for relation 'ObjectNode->InState'
     * 
     * Metamodel description:
     * <i>The required state of the object available at this point in the activity. This state must belong to the type of the objectNode, or of the represented element. </i>
     */
    @objid ("ccca42b5-eef3-4f03-bf68-d4a5512924dd")
    void setInState(State value);

}
