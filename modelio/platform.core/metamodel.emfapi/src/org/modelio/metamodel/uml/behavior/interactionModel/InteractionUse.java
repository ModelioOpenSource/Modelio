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
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;

/**
 * InteractionUse v0.0.9054
 * 
 * 
 * An InteractionUse refers to an Interaction. The InteractionUse is a shorthand for copying the contents of the referred Interaction where the InteractionUse is. To be accurate the copying must connect the formal gates with the actual ones.
 * 
 * It is common to want to share portions of an interaction between several other interactions. An InteractionUse allows multiple interactions to reference an interaction that represents a common portion of their specification.
 */
@objid ("00482c5e-c4bf-1fd8-97fe-001ec947cd2a")
public interface InteractionUse extends InteractionFragment {
    /**
     * The metaclass simple name.
     */
    @objid ("30f28d02-796c-49bf-932c-dad26116b753")
    public static final String MNAME = "InteractionUse";

    /**
     * The metaclass qualified name.
     */
    @objid ("5c7cc85f-f23e-4eeb-b194-18d08b93fc93")
    public static final String MQNAME = "Standard.InteractionUse";

    /**
     * Getter for attribute 'InteractionUse.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1d74788e-6ace-425d-9d6e-e0bcdbee5131")
    int getEndLineNumber();

    /**
     * Setter for attribute 'InteractionUse.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9d1d7404-d8b2-48d5-8177-d77e428ef18f")
    void setEndLineNumber(int value);

    /**
     * Getter for relation 'InteractionUse->ActualGate'
     * 
     * Metamodel description:
     * <i>The actual gates of the InteractionUse.</i>
     */
    @objid ("641fa332-1d95-4f70-9eb2-4e7fe91fe02f")
    EList<Gate> getActualGate();

    /**
     * Filtered Getter for relation 'InteractionUse->ActualGate'
     * 
     * Metamodel description:
     * <i>The actual gates of the InteractionUse.</i>
     */
    @objid ("a209aa9b-08ec-452f-9e70-f49f44868080")
    <T extends Gate> List<T> getActualGate(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InteractionUse->RefersTo'
     * 
     * Metamodel description:
     * <i>Refers to the Interaction that defines its meaning.</i>
     */
    @objid ("3a2bab6b-210c-4b99-844f-dfd2d4e2f1bc")
    Interaction getRefersTo();

    /**
     * Setter for relation 'InteractionUse->RefersTo'
     * 
     * Metamodel description:
     * <i>Refers to the Interaction that defines its meaning.</i>
     */
    @objid ("f587fce0-5198-4376-b93b-8689a4b6dc25")
    void setRefersTo(Interaction value);

}
