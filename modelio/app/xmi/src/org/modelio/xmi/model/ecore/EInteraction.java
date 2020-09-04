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
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class handles the import of Ecore org.eclipse.uml2.uml.Interaction
 * @author ebrosse
 */
@objid ("09047ee2-2819-42bd-bece-2a621a2b7eca")
public class EInteraction extends ENamedElement {
    @objid ("63283415-51f6-4d99-94df-6bbcfe0d1d3a")
    private org.eclipse.uml2.uml.Interaction ecoreElement = null;

    @objid ("224c762d-157b-412a-b468-bdd406ecbc7d")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInteraction();
    }

    @objid ("ddac9a35-3dac-400d-9e10-f40d5f123597")
    public EInteraction(org.eclipse.uml2.uml.Interaction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("4b249249-4322-4651-a010-b5663d4bc8c3")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        ReverseProperties revProp = ReverseProperties.getInstance();
        Object objingOwner =  revProp.getMappedElement(ecoreOwner);
        Interaction objingIImport = (Interaction) objingElt;
        
        if (objingOwner != null) {
        
            NameSpace finalOwner = null;
            if (objingOwner instanceof Transition){
                finalOwner = AbstractObjingModelNavigation.getNearestPackage((Transition)objingOwner);
            }else if (objingOwner instanceof State){
        
                finalOwner = EcoreModelNavigation.getNearestNameSpace(ecoreOwner);
        
                org.eclipse.uml2.uml. Behavior ent = ((org.eclipse.uml2.uml.State)ecoreOwner).getEntry();
                org.eclipse.uml2.uml. Behavior exit = ((org.eclipse.uml2.uml.State)ecoreOwner).getExit();
                org.eclipse.uml2.uml. Behavior doActivity = ((org.eclipse.uml2.uml.State)ecoreOwner).getDoActivity();
        
                if ((ent != null) && (ent.equals(this.ecoreElement))){
                    InternalTransition transition = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
                    transition.setSComposed((State)objingOwner);
                    transition.setBehaviorEffect(objingIImport);
                    transition.setReceivedEvents("Entry");
                }else if ((exit != null) && (exit.equals(this.ecoreElement))){    
                    InternalTransition transition = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
                    transition.setSComposed((State)objingOwner);
                    transition.setBehaviorEffect(objingIImport);
                    transition.setReceivedEvents("Exit");
                }else if ((doActivity != null) && (doActivity.equals(this.ecoreElement))){
                    InternalTransition transition = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
                    transition.setSComposed((State) objingIImport);
                    transition.setBehaviorEffect(objingIImport);
                    transition.setReceivedEvents("Do");
                }
        
            }else if (objingOwner instanceof NameSpace){
                finalOwner = (NameSpace) objingOwner;
            }
        
            if (finalOwner != null){             
                objingIImport.setOwner(finalOwner);
            }else{
                objingElt.delete();
            }
        }
    }

    @objid ("c2b58f6c-d27f-415f-96ea-1d98038e0764")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setReentrant((Interaction) objingElt);
        setDiagram((Interaction) objingElt);
    }

    @objid ("31e6fd0c-b247-446c-89ad-1ad0ae27c011")
    private void setReentrant(Interaction interaction) {
        interaction.setIsReentrant(this.ecoreElement.isReentrant());
    }

    @objid ("43f92ac8-f59d-4d24-9a03-dbd9ad4e6748")
    public void createUnconnectedGate(org.eclipse.uml2.uml.Gate ecoreGate, Interaction interaction) {
        Gate objingGate = (Gate) ReverseProperties.getInstance().getMappedElement(ecoreGate);
        
        if (objingGate == null)
            objingGate = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createGate();
        
        String name = ecoreGate.getName();
        if (EcoreModelNavigation.isNotNull(name))
            objingGate.setName(name);
        
        if (this.ecoreElement.getFormalGates().contains(ecoreGate)){
            interaction.getFormalGate().add(objingGate);
        }
    }

    @objid ("3f092a4e-4784-4d44-a45d-7c14d41f9eb6")
    private void setDiagram(Interaction objingElt) {
        SequenceDiagram diagram = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createSequenceDiagram();
        
        diagram.setOrigin(objingElt);    
        
        String name = "";
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            name = ObjingEAnnotation.getDiagramName(this.ecoreElement);
        }
        
        if (name.equals("")){
            name = this.ecoreElement.getName();
        }
        
        diagram.setName(name);
    }

}
