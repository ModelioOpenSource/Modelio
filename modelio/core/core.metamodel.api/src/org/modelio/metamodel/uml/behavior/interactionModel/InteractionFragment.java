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

package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * InteractionFragment v0.0.9054
 * 
 * 
 * InteractionFragment is an abstract notion of the most general interaction unit. An interaction fragment is a piece of an interaction. 
 * 
 * InteractionFragments sequencing information is handled by an internal feature that is not directly accessible.
 * 
 * The Joni Java API provides methods to access the ordering of InteractionFragments.
 */
@objid ("004713c8-c4bf-1fd8-97fe-001ec947cd2a")
public interface InteractionFragment extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8710a55f-c6f1-4dbd-a2e9-f9b839601166")
    public static final String MNAME = "InteractionFragment";

    /**
     * The metaclass qualified name.
     */
    @objid ("d7e67c93-d775-4cf0-a407-50c20e84e57f")
    public static final String MQNAME = "Standard.InteractionFragment";

    /**
     * Getter for attribute 'InteractionFragment.LineNumber'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("785ab63a-8119-46b2-b24d-b72996bbbab7")
    int getLineNumber();

    /**
     * Setter for attribute 'InteractionFragment.LineNumber'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("79e5f773-538e-484d-893d-c2cd71334d6f")
    void setLineNumber(int value);

    /**
     * Getter for relation 'InteractionFragment->EnclosingOperand'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2c33766d-8689-4256-9d91-02858dcdb0d4")
    InteractionOperand getEnclosingOperand();

    /**
     * Setter for relation 'InteractionFragment->EnclosingOperand'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd15672a-191d-40e7-9f90-8a3bd5c46366")
    void setEnclosingOperand(InteractionOperand value);

    /**
     * Getter for relation 'InteractionFragment->EnclosingInteraction'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5a7e55be-9866-4ccc-af0d-4180ed2c79a9")
    Interaction getEnclosingInteraction();

    /**
     * Setter for relation 'InteractionFragment->EnclosingInteraction'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("84c9c7dc-499d-4093-8343-250e418e2ed6")
    void setEnclosingInteraction(Interaction value);

    /**
     * Getter for relation 'InteractionFragment->Covered'
     * 
     * Metamodel description:
     * <i>References the Lifelines that the InteractionFragment involves.</i>
     */
    @objid ("a7f9069c-123d-4c41-b746-8553a4fc9095")
    EList<Lifeline> getCovered();

    /**
     * Filtered Getter for relation 'InteractionFragment->Covered'
     * 
     * Metamodel description:
     * <i>References the Lifelines that the InteractionFragment involves.</i>
     */
    @objid ("7a103eee-52cc-45f2-a6d5-3bba19b9e02e")
    <T extends Lifeline> List<T> getCovered(java.lang.Class<T> filterClass);

}
