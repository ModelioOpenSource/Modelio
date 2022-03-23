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
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("09f9d64a-e0ff-4b65-8370-a202156552a3")
public class OState extends OModelElement {
    @objid ("5d7af560-35fe-44cd-b59a-e04f23d4c5e7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createState();
    }

    @objid ("87c125a0-97fb-4b9b-983a-c4835a778beb")
    public  OState(State param) {
        super(param);
    }

    @objid ("c77ad0ae-6719-4247-90bd-cb82b702beb3")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(getObjingElement().getParent());
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Region){
            ( (org.eclipse.uml2.uml.Region) ecoreOwner).getSubvertices().add((org.eclipse.uml2.uml.State) ecoreElt);
        }
        
    }

    @objid ("26b33a2f-091e-46b6-bd2c-c9d604b50d0c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSubMachine((org.eclipse.uml2.uml.State) ecoreElt);
        
    }

    @objid ("ea24f106-55a1-4f68-a67b-6d5f65d53535")
    private void setSubMachine(org.eclipse.uml2.uml.State ecoreElt) {
        StateMachine objingSubMachine = getObjingElement().getSubMachine();
        if (objingSubMachine != null) {
            org.eclipse.uml2.uml.Element ecoreSubMachine = GenerationProperties.getInstance()
                    .getMappedElement(objingSubMachine);
            if (ecoreSubMachine instanceof org.eclipse.uml2.uml.StateMachine) {
                ecoreElt.setSubmachine((org.eclipse.uml2.uml.StateMachine) ecoreSubMachine);
            }
        }
        
    }

    @objid ("e2fa18c4-d677-43cd-9387-c25f0a43f2de")
    @Override
    public State getObjingElement() {
        return (State) super.getObjingElement();
    }

}
