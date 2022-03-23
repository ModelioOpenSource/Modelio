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

/**
 * CombinedFragment v0.0.9054
 * 
 * 
 * A combined fragment is defined by an interaction operator and corresponding interaction operands. Through the use of CombinedFragments the user will be able to describe a number of traces in a compact and concise manner. The semantics of a CombinedFragment depend on the interaction operator.
 * 
 * Modelio specific:
 * Gates on CombinedFragments are not supported. In order to model the call and return from a CombinedFragment, create messages just before or just after the CombinedFragment.
 */
@objid ("00435a1c-c4bf-1fd8-97fe-001ec947cd2a")
public interface CombinedFragment extends InteractionFragment {
    /**
     * The metaclass simple name.
     */
    @objid ("34a436f6-9f0a-452f-ab97-e11b58b53303")
    public static final String MNAME = "CombinedFragment";

    /**
     * The metaclass qualified name.
     */
    @objid ("7bbc05d2-ef29-4cdc-936d-b8d1e8de09eb")
    public static final String MQNAME = "Standard.CombinedFragment";

    /**
     * Getter for attribute 'CombinedFragment.Operator'
     * 
     * Metamodel description:
     * <i>Specifies the operation that defines the semantics of this combination of InteractionFragments. The default value is seq.</i>
     */
    @objid ("c560afac-05df-4e7e-8935-8fc6803096c8")
    InteractionOperator getOperator();

    /**
     * Setter for attribute 'CombinedFragment.Operator'
     * 
     * Metamodel description:
     * <i>Specifies the operation that defines the semantics of this combination of InteractionFragments. The default value is seq.</i>
     */
    @objid ("561e6ad8-149c-401b-b81f-4e29b65c5daa")
    void setOperator(InteractionOperator value);

    /**
     * Getter for relation 'CombinedFragment->Operand'
     * 
     * Metamodel description:
     * <i>The set of operands of the combined fragment. </i>
     */
    @objid ("75776855-f6a7-463b-a30a-a0cf2734f60c")
    EList<InteractionOperand> getOperand();

    /**
     * Filtered Getter for relation 'CombinedFragment->Operand'
     * 
     * Metamodel description:
     * <i>The set of operands of the combined fragment. </i>
     */
    @objid ("283270a9-3351-48a7-aaeb-c22966fda63d")
    <T extends InteractionOperand> List<T> getOperand(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'CombinedFragment->FragmentGate'
     * 
     * Metamodel description:
     * <i>Not supported in sequence diagrams:
     * Specifies the gates that form the interface between this CombinedFragment and its surroundings.</i>
     */
    @objid ("d3847ac0-bf74-496b-be85-112c64148fab")
    EList<Gate> getFragmentGate();

    /**
     * Filtered Getter for relation 'CombinedFragment->FragmentGate'
     * 
     * Metamodel description:
     * <i>Not supported in sequence diagrams:
     * Specifies the gates that form the interface between this CombinedFragment and its surroundings.</i>
     */
    @objid ("bfc58c78-0240-4a4b-b648-f3376e812924")
    <T extends Gate> List<T> getFragmentGate(java.lang.Class<T> filterClass);

}
