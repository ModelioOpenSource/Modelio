/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;

/**
 * Interaction v0.0.9054
 * 
 * 
 * Interactions are units of behavior of an enclosing Classifier. Interactions focus on the passing of information with Messages between the Instances, Attributes, Parameters, ...  of the Classifier or the Operation.
 * 
 * An Interaction belongs to a NameSpace or an Operation.
 * 
 * The interaction is composed of Lifelines and InteractionFragments, some of them send or receive Messages.
 * 
 * InteractionFragments are ordered by an internal feature that is not directly accessible.
 * 
 * The Joni Java API provides methods to access the ordering of InteractionFragments.
 */
@objid ("004683fe-c4bf-1fd8-97fe-001ec947cd2a")
public interface Interaction extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("43e296a8-d1c1-4748-8bc7-e96c1f88d506")
    public static final String MNAME = "Interaction";

    /**
     * The metaclass qualified name.
     */
    @objid ("f1f0032f-f44e-4d84-b0e6-8ed93d22b11f")
    public static final String MQNAME = "Standard.Interaction";

    /**
     * Getter for relation 'Interaction->FormalGate'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("5f9e037a-948f-477a-80c3-2a92ec9fa928")
    EList<Gate> getFormalGate();

    /**
     * Filtered Getter for relation 'Interaction->FormalGate'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("730c0c6f-d58a-495e-a4a5-e10217d3d1c1")
    <T extends Gate> List<T> getFormalGate(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Interaction->Fragment'
     * 
     * Metamodel description:
     * <i>The set of fragments in the Interaction.</i>
     */
    @objid ("6306227b-7eca-46c0-bc8f-47a2d525c301")
    EList<InteractionFragment> getFragment();

    /**
     * Filtered Getter for relation 'Interaction->Fragment'
     * 
     * Metamodel description:
     * <i>The set of fragments in the Interaction.</i>
     */
    @objid ("bd5f4a2c-9a63-43be-973e-b6cddc1aafaa")
    <T extends InteractionFragment> List<T> getFragment(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Interaction->OwnedLine'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ec90d76d-fb5f-4d38-99fb-c650d22055c8")
    EList<Lifeline> getOwnedLine();

    /**
     * Filtered Getter for relation 'Interaction->OwnedLine'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("b43a08b8-aa79-4e59-bf12-0ae82822e028")
    <T extends Lifeline> List<T> getOwnedLine(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Interaction->ReferedUse'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e9f8f8b8-cea6-40a2-8528-c6d169a903c2")
    EList<InteractionUse> getReferedUse();

    /**
     * Filtered Getter for relation 'Interaction->ReferedUse'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("04cc70f8-4c3f-4218-91f2-d313e651e197")
    <T extends InteractionUse> List<T> getReferedUse(java.lang.Class<T> filterClass);

}
