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
package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;

/**
 * InteractionOperand v0.0.9054
 * 
 * 
 * An InteractionOperand is contained in a CombinedFragment and contains an ordered set of InteractionFragments. It represents one operand of the expression given by the enclosing CombinedFragment. 
 * 
 * An InteractionOperand has an optional guard expression. Only InteractionOperands with a guard that evaluates to true at this point in the interaction will be considered for the production of the traces for the enclosing CombinedFragment.
 */
@objid ("00479758-c4bf-1fd8-97fe-001ec947cd2a")
public interface InteractionOperand extends InteractionFragment {
    /**
     * The metaclass simple name.
     */
    @objid ("7a6f3289-8336-4796-a9da-81da3638defe")
    public static final String MNAME = "InteractionOperand";

    /**
     * The metaclass qualified name.
     */
    @objid ("f35dc27d-5878-4250-ad83-02fc8a11b1d6")
    public static final String MQNAME = "Standard.InteractionOperand";

    /**
     * Getter for attribute 'InteractionOperand.Guard'
     * 
     * Metamodel description:
     * <i>Constraint of the operand.</i>
     */
    @objid ("ff3cf201-da4c-4d54-85ad-a5962dc364fc")
    String getGuard();

    /**
     * Setter for attribute 'InteractionOperand.Guard'
     * 
     * Metamodel description:
     * <i>Constraint of the operand.</i>
     */
    @objid ("ec47bd7c-7001-4abe-8c87-2ee74eefb83a")
    void setGuard(String value);

    /**
     * Getter for attribute 'InteractionOperand.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2210701a-3897-4df8-bbdb-849e2e551f66")
    int getEndLineNumber();

    /**
     * Setter for attribute 'InteractionOperand.EndLineNumber'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e3d72fc6-1398-4f10-8c86-882304f6fb21")
    void setEndLineNumber(int value);

    /**
     * Getter for relation 'InteractionOperand->Fragment'
     * 
     * Metamodel description:
     * <i>The fragments of the operand.</i>
     */
    @objid ("b2abf493-ae34-4f80-9e7f-3ad4ed84c977")
    EList<InteractionFragment> getFragment();

    /**
     * Filtered Getter for relation 'InteractionOperand->Fragment'
     * 
     * Metamodel description:
     * <i>The fragments of the operand.</i>
     */
    @objid ("865e78e0-49bc-45e6-bc8a-036dcf312658")
    <T extends InteractionFragment> List<T> getFragment(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InteractionOperand->OwnerFragment'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a7d44265-3643-416b-98b0-85d159fffe61")
    CombinedFragment getOwnerFragment();

    /**
     * Setter for relation 'InteractionOperand->OwnerFragment'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("00044f20-5627-4f10-bbe0-cd3dccc9302c")
    void setOwnerFragment(CombinedFragment value);

}
