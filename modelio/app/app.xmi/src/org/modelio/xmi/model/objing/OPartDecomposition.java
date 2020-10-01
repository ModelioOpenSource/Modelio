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
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("784e5006-381a-4264-952d-92d22e5189a5")
public class OPartDecomposition extends OInteractionUse {
    @objid ("e7c8fa06-c6d2-4286-b748-81eb2a5cfe08")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPartDecomposition();
    }

    @objid ("f68f6d80-9874-4b23-a95b-4806e6aecca6")
    public OPartDecomposition(PartDecomposition param) {
        super(param);
    }

    @objid ("31a59718-9b51-4f8f-8211-f507f1bd2b64")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Lifeline objingLifeLine = ((PartDecomposition) getObjingElement())
                .getDecomposed();
        Interaction objInteraction = objingLifeLine.getOwner();
        
        GenerationProperties genProp = GenerationProperties.getInstance();
        org.eclipse.uml2.uml.Interaction ecoreInteraction = (org.eclipse.uml2.uml.Interaction) genProp
                .getMappedElement(objInteraction);
        
        if (ecoreInteraction != null) {
            ecoreInteraction.getFragments().add((org.eclipse.uml2.uml.PartDecomposition)ecoreElt);
        }
    }

    @objid ("387ede1d-4d02-4028-a6b4-60dafe9bb2c3")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setDecomposedAs((org.eclipse.uml2.uml.PartDecomposition) ecoreElt);
        setCovered((org.eclipse.uml2.uml.PartDecomposition) ecoreElt);
    }

    @objid ("c24a61ac-c7e1-4beb-94e9-17b4298ccbf3")
    private void setCovered(org.eclipse.uml2.uml.PartDecomposition ecorePartDec) {
        Lifeline objingLifeLine = ((PartDecomposition) getObjingElement())
                .getDecomposed();
        GenerationProperties genProp = GenerationProperties.getInstance();
        org.eclipse.uml2.uml.Lifeline ecoreLifeline = (org.eclipse.uml2.uml.Lifeline) genProp
                .getMappedElement(objingLifeLine);
        if (ecoreLifeline != null) {
            ecorePartDec.getCovereds().add(ecoreLifeline);
        }
    }

    @objid ("43a419a5-71db-4c36-b932-956e793a9163")
    private void setDecomposedAs(org.eclipse.uml2.uml.PartDecomposition ecorePartDec) {
        Lifeline objingLifeLine = ((PartDecomposition) getObjingElement())
                .getDecomposed();
        GenerationProperties genProp = GenerationProperties.getInstance();
        org.eclipse.uml2.uml.Lifeline ecoreLifeline = (org.eclipse.uml2.uml.Lifeline) genProp
                .getMappedElement(objingLifeLine);
        if (ecoreLifeline != null) {
            ecoreLifeline.setDecomposedAs(ecorePartDec);
        }
    }

}
