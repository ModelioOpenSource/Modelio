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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("e21296fe-66a5-4d2d-8303-57b04a5dde18")
public class EPseudostate extends ENamedElement {
    @objid ("5672196a-010d-49fe-9045-45b53b26e68e")
    private org.eclipse.uml2.uml.Pseudostate ecoreElement;

    @objid ("0ddad894-955d-4e70-b46d-caa86892964a")
    @Override
    public Element createObjingElt() {
        AbstractPseudoState state = null;
        IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        
        switch (this.ecoreElement.getKind().getValue()) {
        case org.eclipse.uml2.uml.PseudostateKind.INITIAL:
            state = factory.createInitialPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.DEEP_HISTORY:
            state = factory.createDeepHistoryPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.SHALLOW_HISTORY:
            state = factory.createShallowHistoryPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.JOIN:
            state = factory.createJoinPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.FORK:
            state = factory.createForkPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.JUNCTION:
            state = factory.createJunctionPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.CHOICE:
            state = factory.createChoicePseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.ENTRY_POINT:
            state = factory.createEntryPointPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.EXIT_POINT:
            state = factory.createExitPointPseudoState();
        
            break;
        case org.eclipse.uml2.uml.PseudostateKind.TERMINATE:
            state = factory.createTerminatePseudoState();
        
            break;
            default:
        }
        return state;
    }

    @objid ("b01024f9-4341-4a01-b6f8-b153b19f00fe")
    public EPseudostate(org.eclipse.uml2.uml.Pseudostate element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("5c3c7636-55e3-46c6-b0d0-fa2ff94da1ea")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Region ecoreContainer = this.ecoreElement.getContainer();
        
        if (ecoreContainer != null) {
            Object objingContainer = ReverseProperties.getInstance().getMappedElement(ecoreContainer);
        
            if ((objingContainer instanceof Region) && (objingElt instanceof StateVertex))
                ((Region) objingContainer).getSub().add((StateVertex) objingElt);
        
        }else{
            org.eclipse.uml2.uml.StateMachine ecoreStateMachine = this.ecoreElement.getStateMachine();
            if (ecoreStateMachine != null){
                Object objingStateMachine = ReverseProperties.getInstance().getMappedElement(ecoreStateMachine);
        
                if ((objingStateMachine instanceof StateMachine) && (objingElt instanceof StateVertex)) {
        
                    ((StateMachine) objingStateMachine).getTop().getSub().add((StateVertex) objingElt);
        
                }
            }else{
                org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
                if (ecoreOwner instanceof org.eclipse.uml2.uml.State){
                    State objOwner = (State) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
                    if (objingElt instanceof EntryPointPseudoState)
                        objOwner.getEntryPoint().add((EntryPointPseudoState)objingElt);
                    else if (objingElt instanceof ExitPointPseudoState)
                        objOwner.getExitPoint().add((ExitPointPseudoState)objingElt);
        
                }
            }
        
        }
    }

}
