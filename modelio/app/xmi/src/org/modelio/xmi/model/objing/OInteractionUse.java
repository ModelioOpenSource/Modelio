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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("ea347b53-5311-42ab-854b-56283d4d28ca")
public class OInteractionUse extends OInteractionFragment {
    @objid ("23fe04c3-d007-4bd0-ab6b-b483ac2478b2")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInteractionUse();
    }

    @objid ("3419fc25-70a0-427b-9d0a-26dba0f7c026")
    public OInteractionUse(InteractionUse param) {
        super(param);
    }

    @objid ("fb7d4d1f-c3de-4442-a756-3708c6d0671d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("0569be83-fed5-4399-931d-161e5f42fa97")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setInteraction((org.eclipse.uml2.uml.InteractionUse) ecoreElt);
        setActualGates((org.eclipse.uml2.uml.InteractionUse) ecoreElt);
        setEndLineNumber(ecoreElt);
    }

    @objid ("b2af6892-1ffb-4c40-b084-d50a2ce5ab96")
    private void setEndLineNumber(org.eclipse.uml2.uml.Element ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            ObjingEAnnotation.setEndLineNumber(ecoreElt, ((InteractionUse) getObjingElement()).getEndLineNumber());
        }
    }

    @objid ("b4aacae1-2be0-4324-bf8d-d583546df113")
    private void setInteraction(org.eclipse.uml2.uml.InteractionUse ecoreElt) {
        InteractionUse objingInteractionUse = (InteractionUse) getObjingElement();
        Interaction objingInteractionRef = objingInteractionUse.getRefersTo();
        
        if (objingInteractionRef != null) {
        
            Object ecoreInteraction =  GenerationProperties.getInstance()
                    .getMappedElement(objingInteractionRef);
        
            if (ecoreInteraction instanceof org.eclipse.uml2.uml.Interaction)
                ecoreElt.setRefersTo((org.eclipse.uml2.uml.Interaction)ecoreInteraction);
        }
    }

    @objid ("270073b2-23cd-4eb8-8985-96dd0405027b")
    private void setActualGates(org.eclipse.uml2.uml.InteractionUse ecoreElt) {
        InteractionUse objingInteractionUse = (InteractionUse) this.getObjingElement();
        List<Gate> objiingGateListe = objingInteractionUse.getActualGate();
        
        for (Gate objingGate : objiingGateListe) {
        
            Object ecoreFormalGate =  GenerationProperties.getInstance()
                    .getMappedElement(objingGate);
        
            if (ecoreFormalGate instanceof org.eclipse.uml2.uml.Gate)
                ecoreElt.getActualGates().add((org.eclipse.uml2.uml.Gate)ecoreFormalGate);
        }
    }

}
