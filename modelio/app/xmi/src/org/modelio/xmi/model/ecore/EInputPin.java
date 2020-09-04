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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("8112630e-54e1-4398-8cf5-07fa085f036f")
public class EInputPin extends EPin {
    @objid ("a889df2c-db4b-4cfc-b4b9-401e3817479c")
    private org.eclipse.uml2.uml.InputPin ecoreElement = null;

    @objid ("addbb05c-f2c3-42f4-8f45-a410d9b65342")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInputPin();
    }

    @objid ("620c3b37-5c2b-454c-ab9f-b58c594fbfeb")
    public EInputPin(org.eclipse.uml2.uml.InputPin element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("f7d5c77f-d85e-4784-9d50-0e9490ae4ab4")
    @Override
    public void attach(Element objingElt) {
        attachToAction(objingElt);
    }

    @objid ("4fafbb05-96c2-418f-9f21-1d8b54940464")
    @Override
    public void setProperties(Element objingElt) {
        setStereotype((InputPin) objingElt);
        
        // Properties defined on ModelElement
        super.setProperties(objingElt); 
        
        // Properties defined on ObjectNode
        setControlType((InputPin) objingElt);
        setOrdering((InputPin) objingElt);
        setSelectionBehavior((InputPin) objingElt);
        setType((InputPin) objingElt);
        setState((InputPin) objingElt);
    }

    @objid ("6a742021-a125-4ebe-a93e-a13f808bab97")
    private void attachToAction(Element objingElt) {
        org.eclipse.uml2.uml.Action ecoreAction =  (org.eclipse.uml2.uml.Action) getEcoreElement().getOwner();
        
        Object objingAction = ReverseProperties.getInstance().getMappedElement(ecoreAction);
        if (objingAction instanceof ActivityAction) 
            ((InputPin) objingElt).setInputing((ActivityAction) objingAction);
        else{
            String message = "Owner of pin was " + objingAction.getClass().getSimpleName();
            ReverseProperties.getInstance().addError(message);
            objingElt.delete();
        }
    }

    @objid ("a4776688-f756-4fac-9201-e1c8cc75e2df")
    private void setControlType(InputPin pin) {
        pin.setIsControlType(this.ecoreElement.isControlType());
    }

    @objid ("a1e256ac-7d64-4c73-aa2e-5a21286132fb")
    private void setOrdering(InputPin pin) {
        switch (this.ecoreElement.getOrdering().getValue()) {
        case org.eclipse.uml2.uml.ObjectNodeOrderingKind.FIFO:
            pin.setOrdering(ObjectNodeOrderingKind.FIFO);
            break;
        case org.eclipse.uml2.uml.ObjectNodeOrderingKind.LIFO:
            pin.setOrdering(ObjectNodeOrderingKind.LIFO);
            break;
        case org.eclipse.uml2.uml.ObjectNodeOrderingKind.ORDERED:
            pin.setOrdering(ObjectNodeOrderingKind.ORDERED);
            break;
        case org.eclipse.uml2.uml.ObjectNodeOrderingKind.UNORDERED:
            pin.setOrdering(ObjectNodeOrderingKind.UNORDERED);
            break;
        default:
            pin.setOrdering(ObjectNodeOrderingKind.FIFO);
        }
    }

    @objid ("ba8d9f72-f7b8-40dd-9dea-a6e6f135f2b5")
    private void setSelectionBehavior(InputPin pin) {
        org.eclipse.uml2.uml. Behavior ecoreBehavior = this.ecoreElement.getSelection();
        if (ecoreBehavior instanceof org.eclipse.uml2.uml.OpaqueBehavior) {
            String objingBehavior = "";
            for (Object body : ((org.eclipse.uml2.uml.OpaqueBehavior) ecoreBehavior).getBodies()) {
                objingBehavior = objingBehavior.concat((String) body);
            }
            pin.setSelectionBehavior(objingBehavior);
        } else if (ecoreBehavior != null) {
            String behaviorName = ecoreBehavior.getName();
            if (behaviorName != null)
                pin.setSelectionBehavior(behaviorName);
        }
    }

    @objid ("85a00276-46d9-4c44-9b62-379d9a5e4e9e")
    private void setType(InputPin pin) {
        org.eclipse.uml2.uml.Type ecoreType = this.ecoreElement.getType();
        
        if (ecoreType != null) {      
            Object objingType = ReverseProperties.getInstance().getMappedElement(ecoreType);
            if (objingType instanceof GeneralClass)
                pin.setType((GeneralClass) objingType);       
        }
    }

    @objid ("b9ba77cb-dfd8-423e-b0a2-25260c4a0942")
    private void setState(InputPin pin) {
        for(org.eclipse.uml2.uml.State  ecoreState : this.ecoreElement.getInStates()) {
            Object objingState = ReverseProperties.getInstance().getMappedElement(ecoreState);
            if (objingState instanceof State)
                pin.setInState((State) objingState);
        }
    }

    @objid ("afd80eba-172d-4228-931a-9e2bad87f783")
    private void setStereotype(InputPin objingElt) {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        org.eclipse.uml2.uml.Element owner = this.ecoreElement.getOwner();
        
        try {
            if (owner instanceof org.eclipse.uml2.uml.TestIdentityAction){
                if (((org.eclipse.uml2.uml.TestIdentityAction) owner).getFirst().equals(this.ecoreElement)){
        
                    objingElt.getExtension().add(mmServices
                            .getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2FIRST, objingElt.getMClass()));
        
                }else  if (((org.eclipse.uml2.uml.TestIdentityAction) owner).getSecond().equals(this.ecoreElement)){
        
                    objingElt.getExtension().add(mmServices
                            .getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2SECOND, objingElt.getMClass()));
        
                }
            }else if (owner instanceof  org.eclipse.uml2.uml.CallOperationAction){
                org.eclipse.uml2.uml.InputPin input = ( (org.eclipse.uml2.uml.CallOperationAction) owner).getTarget();
                if ((input != null) && (input.equals(this.ecoreElement))){
        
                    objingElt.getExtension().add(mmServices
                            .getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2TARGET, objingElt.getMClass()));
        
                }
            }else if (owner instanceof org.eclipse.uml2.uml.WriteStructuralFeatureAction){
                org.eclipse.uml2.uml.InputPin input = ((org.eclipse.uml2.uml.WriteStructuralFeatureAction) this.ecoreElement.getOwner()).getValue();
                if ((input != null) && (input.equals(this.ecoreElement))){
        
                    objingElt.getExtension().add(mmServices
                            .getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2VALUE, objingElt.getMClass()));
        
                }
            }
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
    }

}
