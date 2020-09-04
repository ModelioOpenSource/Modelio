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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class handles the import of Ecore org.eclipse.uml2.uml.Interaction Fragment
 * @author ebrosse
 */
@objid ("9423034a-3b98-4b20-b9cf-a62e2a6b5ed8")
public class EInteractionFragment extends ENamedElement {
    @objid ("78cb388f-3074-439a-81e0-9bb3a8f9f97f")
    private org.eclipse.uml2.uml.InteractionFragment ecoreElement = null;

    @objid ("a0b93a9c-0215-4dc2-b748-0133eaa06994")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("45f99f0b-df85-4837-8378-96dc157638a5")
    public EInteractionFragment(org.eclipse.uml2.uml.InteractionFragment element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("7fa73463-24c0-4535-ae51-e30bf4caac8e")
    @Override
    public void attach(Element objingElt) {
        InteractionFragment interFrag = (InteractionFragment) objingElt;
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        if (ecoreOwner != null){
        
            Object owner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if (owner instanceof Interaction){
                ((Interaction) owner).getFragment().add(interFrag);
            }else if (owner instanceof InteractionOperand){
                ((InteractionOperand) owner).getFragment().add(interFrag);            
            }else if ((owner instanceof CombinedFragment)
                    && (interFrag instanceof InteractionOperand)){
                ((CombinedFragment) owner).getOperand().add((InteractionOperand) interFrag);
            }
        }
    }

    @objid ("541b5212-a3e3-4607-8814-5f78091daafa")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setCovered(objingElt);
        //        if (ReverseProperties.getInstance().isRoundtripEnabled()){
        //            setLineNumbers(objingElt);
        //        }
    }

    @objid ("fb81bf2f-6bd2-4c39-89cb-9fc0b383b2c4")
    private void setCovered(Element objingElt) {
        if (objingElt instanceof InteractionFragment){
            ReverseProperties revProp = ReverseProperties.getInstance();
            for (org.eclipse.uml2.uml.Lifeline covered : this.ecoreElement.getCovereds()){
                Object objLifeline = revProp.getMappedElement(covered);
                if (objLifeline instanceof Lifeline){
                    ((Lifeline) objLifeline).getCoveredBy().add((InteractionFragment) objingElt);
                }
            }
        }
    }


//    @objid ("34cbfcbb-8fe7-4a95-9dcb-0dfeea02cfc5")
//    private void setLineNumbers(Element objingElt) {
//        ((InteractionFragment) objingElt).setLineNumber(ObjingEAnnotation.getLineNumber(getEcoreElement()) );
//
//        //InteractionUse Case
//        if (objingElt instanceof InteractionUse){
//            ((InteractionUse) objingElt).setEndLineNumber(ObjingEAnnotation.getEndLineNumber(getEcoreElement()) );
//        }
//
//        //InteractionOperand Case
//        if  (objingElt instanceof InteractionOperand){
//            ((InteractionUse) objingElt).setEndLineNumber(ObjingEAnnotation.getEndLineNumber(getEcoreElement()) );
//        }
//
//        //StateInvariant Case
//        if  (objingElt instanceof StateInvariant){
//            ((StateInvariant) objingElt).setEndLineNumber(ObjingEAnnotation.getEndLineNumber(getEcoreElement()) );
//        }
//    }
}
