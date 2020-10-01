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
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Vertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("ccbf7129-c052-4005-96b4-9e3f6b7eb0c5")
public class OConnectionPointReference extends OModelElement {
    @objid ("6018a470-48d2-4f83-bde7-c41cbd8d2f6e")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("a60023e9-1611-49f6-ad7a-f9ba95b4da5a")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        State objOwner = getObjingElement().getOwnerState();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objOwner);
        if ((ecoreOwner != null) &&  (ecoreOwner instanceof org.eclipse.uml2.uml.State)){
                return ((org.eclipse.uml2.uml.State) ecoreOwner).createConnection(getObjingElement().getName());
        }
        return null;
    }

    @objid ("7b8c2712-5d37-4988-9234-998a28b3fb73")
    public OConnectionPointReference(ConnectionPointReference objingElt) {
        super(objingElt);
    }

    @objid ("043e9e97-6124-4735-8a02-14ddb9c4af49")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        
        if (objingOwner != null) {
            org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(objingOwner);
        
            if (ecoreOwner != null) {
        
                if (ecoreOwner instanceof org.eclipse.uml2.uml.State) {
                    ((org.eclipse.uml2.uml.ConnectionPointReference) ecoreElt).setState((org.eclipse.uml2.uml.State)ecoreOwner);
                } else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Region) {
                    ( (org.eclipse.uml2.uml.Region) ecoreOwner).getSubvertices().add((Vertex)ecoreElt);
                } else if (ecoreOwner instanceof org.eclipse.uml2.uml.StateMachine) {
        
                    EList<?> regions = ((org.eclipse.uml2.uml.StateMachine) ecoreOwner).getRegions();
                    if (regions.size() > 0) {
                        ( (org.eclipse.uml2.uml.Region) regions.get(0)).getSubvertices().add((Vertex)ecoreElt);
                    }
                } else {
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class ("
                            + ecoreOwner.getClass().getSimpleName()
                            + ") Not Found");
                }
            } else {
                ecoreElt.destroy();
                throw new NotFoundException(
                        "Owner Class of FinalState Not Found");
            }
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class of FinalState Not Found");
        }
    }

    @objid ("cf7cd0f2-3128-497a-9355-58a73470f5fa")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setExit((org.eclipse.uml2.uml.ConnectionPointReference) ecoreElt);
        setEntry((org.eclipse.uml2.uml.ConnectionPointReference) ecoreElt);
    }

    @objid ("4839afaa-c792-4f00-bff1-424e28839c46")
    private void setEntry(org.eclipse.uml2.uml.ConnectionPointReference ecoreElt) {
        EntryPointPseudoState objEntry = getObjingElement().getEntry();
        if (objEntry != null){
            org.eclipse.uml2.uml.Element ecoreEntry = GenerationProperties.getInstance().getMappedElement(objEntry);
            if (ecoreEntry != null){
                if ((ecoreEntry instanceof org.eclipse.uml2.uml.Pseudostate) && ( (org.eclipse.uml2.uml.Pseudostate) ecoreEntry).getKind().equals (org.eclipse.uml2.uml.PseudostateKind.ENTRY_POINT_LITERAL)){
                    (ecoreElt).getEntries().add( (org.eclipse.uml2.uml.Pseudostate) ecoreEntry);
        
                }
            }else{
                ecoreElt.destroy();
                throw new NotFoundException("Entry Point of ("
                        + ecoreElt.getName() + ") Not Found");
            }
        }
    }

    @objid ("280e826e-6fe5-47ba-8825-647684b454f7")
    private void setExit(org.eclipse.uml2.uml.ConnectionPointReference ecoreElt) {
        ExitPointPseudoState objExit = getObjingElement().getExit();
        if (objExit != null){
            org.eclipse.uml2.uml.Element ecoreExit = GenerationProperties.getInstance().getMappedElement(objExit);
            if (ecoreExit != null){
                if ((ecoreExit instanceof org.eclipse.uml2.uml.Pseudostate) && ( (org.eclipse.uml2.uml.Pseudostate) ecoreExit).getKind().equals (org.eclipse.uml2.uml.PseudostateKind.EXIT_POINT_LITERAL)){
                    (ecoreElt).getExits().add( (org.eclipse.uml2.uml.Pseudostate) ecoreExit);
        
                }
            }else{
                ecoreElt.destroy();
                throw new NotFoundException("Entry Point ("
                        + ecoreElt.getName() + ") Not Found");
            }
        }
    }

    @objid ("cbcbf84b-6cf4-4eb5-ad03-d542c6433dc2")
    @Override
    public ConnectionPointReference getObjingElement() {
        return (ConnectionPointReference) super.getObjingElement();
    }

}
