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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Element;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of InteractionFragment
 * @author ebrosse
 */
@objid ("d5a7a2f5-c74b-44a4-84db-a7f177eb2774")
public class OInteractionFragment extends OModelElement {
    @objid ("efe35a35-f005-41e2-a2a0-77081d7c68fb")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    /**
     * Constructor
     * @param element : the exported Modelio InteractionFragment
     */
    @objid ("a7de0259-dac4-4fe4-b298-5191d74a3a22")
    public OInteractionFragment(final InteractionFragment element) {
        super(element);
    }

    @objid ("f5051945-ed08-47d1-983e-5ea26bff9dc6")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt.getOwner() == null){
        
            InteractionFragment objingElement = (InteractionFragment) getObjingElement();
        
            Interaction interaction = objingElement.getEnclosingInteraction();
            InteractionOperand interactionOperand = objingElement.getEnclosingOperand();   
        
            if (interactionOperand == null){
        
                int lineNumber = objingElement.getLineNumber();
                int startNumber = 0;
                int endNumber = -1;
                for (CombinedFragment comFrag : interaction.getFragment(CombinedFragment.class)){
                    for (InteractionOperand operand : comFrag.getOperand()){
                        int startFrag = operand.getLineNumber();
                        int endFrag = operand.getEndLineNumber();
                        if ((startFrag < lineNumber) 
                                && (endFrag >  lineNumber) 
                                &&  (startFrag > startNumber)
                                && ((endFrag < endNumber) || (endNumber == -1))){
                            startNumber = startFrag;
                            endNumber = endFrag;
                            interactionOperand = operand;
                        }
                    }
                }
            }
        
            if (interaction != null || interactionOperand != null) {
        
                // for ecore we add the fragment to the interaction
                if (interactionOperand != null) {
                    org.eclipse.uml2.uml.InteractionOperand ecoreInteractionOperand = (org.eclipse.uml2.uml.InteractionOperand) GenerationProperties.getInstance()
                            .getMappedElement(interactionOperand);
                    if ((ecoreInteractionOperand != null) && (ecoreElt instanceof org.eclipse.uml2.uml.InteractionFragment))
                        ecoreInteractionOperand.getFragments().add((org.eclipse.uml2.uml.InteractionFragment)ecoreElt);
        
                }else  if (interaction != null) {
                    org.eclipse.uml2.uml.Interaction ecoreInteraction = (org.eclipse.uml2.uml.Interaction) GenerationProperties.getInstance().getMappedElement(interaction);
                    if ((ecoreInteraction != null) && (ecoreElt instanceof org.eclipse.uml2.uml.InteractionFragment))
                        ecoreInteraction.getFragments().add((org.eclipse.uml2.uml.InteractionFragment)ecoreElt);
                }
            }
        }
    }

    @objid ("014adc09-1600-4176-acd6-6ea76fcdd23b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setLineNumber(ecoreElt);
        setCovered(ecoreElt);
    }

    @objid ("f1692c33-d623-4bec-8907-cbe048860035")
    private void setLineNumber(org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setLineNumber(ecoreElt, ((InteractionFragment) getObjingElement()).getLineNumber());
    }

    @objid ("10d070dc-dcde-4e9f-9e65-dc0eb22c3a92")
    private void setCovered(Element ecoreElt) {
        InteractionFragment objingElement = (InteractionFragment) getObjingElement();
        
        // We add the fragment to the lifeline
        for (Lifeline lifeline :  objingElement.getCovered()) {
            org.eclipse.uml2.uml.Lifeline ecoreLifeline = (org.eclipse.uml2.uml.Lifeline) GenerationProperties.getInstance().getMappedElement(lifeline);
            if (ecoreLifeline != null) {
                try{
                    ecoreLifeline.getCoveredBys().add((org.eclipse.uml2.uml.InteractionFragment)ecoreElt);
                }catch (ArrayStoreException e){
                    Xmi.LOG.warning(e);
                }
            }
        }
    }

}
