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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class handles the export of org.eclipse.uml2.uml.InteractionFragment
 * @author ebrosse
 */
@objid ("aaf9d8b4-c9d2-43d3-b8eb-04f4ef964ddc")
public class OInteractionOperand extends OInteractionFragment {
    @objid ("c856492d-7cde-4733-9790-ce0f24b52b5d")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInteractionOperand();
    }

    @objid ("a400db1c-46c1-4671-86fe-91959f46333f")
    public OInteractionOperand(InteractionOperand param) {
        super(param);
    }

    @objid ("59724eaa-6b56-4321-a6bf-80a970823208")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        CombinedFragment objCombin = ((InteractionOperand) getObjingElement())
                .getOwnerFragment();
        org.eclipse.uml2.uml.CombinedFragment fragment =  (org.eclipse.uml2.uml.CombinedFragment) GenerationProperties.getInstance()
                .getMappedElement(objCombin);
        
        if (fragment != null)
            fragment.getOperands().add((org.eclipse.uml2.uml.InteractionOperand)ecoreElt);
    }

    @objid ("aed0b7bb-96e2-4340-9a0a-ed93f1412a2b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setGuard((org.eclipse.uml2.uml.InteractionOperand) ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setEndLineNumber(ecoreElt);
        }
    }

    @objid ("bf9a1003-a8e9-4377-a7d7-dc09b231cdaa")
    private void setGuard(org.eclipse.uml2.uml.InteractionOperand ecoreElt) {
        String objGuard = ((InteractionOperand) getObjingElement()).getGuard();
        if (objGuard != null && objGuard.trim().length() != 0) {
            // org.eclipse.uml2.uml.Constraint creation
            org.eclipse.uml2.uml.InteractionConstraint constraint = UMLFactory.eINSTANCE
                    .createInteractionConstraint();
            // new org.eclipse.uml2.uml.LiteralString for constraint value
            org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE
                    .createLiteralString();
            valueSpecification.setValue(objGuard);
            constraint.setSpecification(valueSpecification);
            ecoreElt.setGuard(constraint);
        }
    }

    @objid ("f95a7098-1b09-42bc-9151-84f0eed26a3f")
    private void setEndLineNumber(org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setEndLineNumber(ecoreElt, ((InteractionOperand) getObjingElement()).getEndLineNumber());
    }

}
