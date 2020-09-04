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
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("bd8cad09-829a-4450-a166-810d875ea6ce")
public class EOutputPin extends EPin {
    @objid ("4a082cf2-b05b-4e63-b19d-f7766ce1a27d")
    private org.eclipse.uml2.uml.OutputPin ecoreElement = null;

    @objid ("d0fda3a7-ab62-4b84-b65b-499e8e4e9089")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createOutputPin();
    }

    @objid ("262c60ac-aedf-4f7d-9ee1-7b565ea44506")
    public EOutputPin(org.eclipse.uml2.uml.OutputPin element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("e76388ab-d56c-449f-988c-28a407e27e5e")
    @Override
    public void setProperties(Element objingElt) {
        //set ownership relation
        setStereotype((OutputPin) objingElt);
        
        super.setProperties(objingElt);
        // Properties defined on ObjectNode
        setControlType((OutputPin) objingElt);
        setOrdering((OutputPin) objingElt);
        setSelectionBehavior((OutputPin) objingElt);
        setType((OutputPin) objingElt);
        setState((OutputPin) objingElt);
    }

    @objid ("d8979ab3-693b-4620-be37-bdc2303c9f99")
    private void attachToAction(Element objingElt) {
        org.eclipse.uml2.uml.Action ecoreAction =  (org.eclipse.uml2.uml.Action) getEcoreElement().getOwner();
        
        Object objingAction = ReverseProperties.getInstance().getMappedElement(ecoreAction);
        if (objingAction instanceof ActivityAction) 
            ((OutputPin) objingElt).setOutputing((ActivityAction) objingAction);
        else{
            String message = "Owner of outputpin was " + objingAction.getClass().getSimpleName();
            ReverseProperties.getInstance().addError(message);
            objingElt.delete();
        }
    }

    @objid ("1682a770-1a35-41fa-aa7d-ac27193b3143")
    private void setControlType(OutputPin pin) {
        pin.setIsControlType(this.ecoreElement.isControlType());
    }

    @objid ("dee7f947-3ad7-4466-b689-b911efdd82c2")
    private void setOrdering(OutputPin pin) {
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

    @objid ("7a18bcaf-0ee1-4c40-9b4f-a597b79170b5")
    private void setSelectionBehavior(OutputPin pin) {
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

    @objid ("70c1035c-f251-47a2-8847-f57bded0181f")
    private void setType(OutputPin pin) {
        org.eclipse.uml2.uml.Type ecoreType = this.ecoreElement.getType();
        if (ecoreType != null) {
            Object objingType = ReverseProperties.getInstance().getMappedElement(ecoreType);
            if (objingType instanceof GeneralClass)
                pin.setType((GeneralClass) objingType);
        }
    }

    @objid ("7153c12c-3e46-4c48-9bbc-0e35d3093cd9")
    private void setState(OutputPin pin) {
        EList<?> ecoreStates = this.ecoreElement.getInStates();
        if (ecoreStates != null && ecoreStates.size() > 0) {
            org.eclipse.uml2.uml.State ecoreState = (org.eclipse.uml2.uml.State) ecoreStates.get(0);
            if (ecoreState != null) {
                Object objingState = ReverseProperties.getInstance().getMappedElement(ecoreState);
                if (objingState instanceof State)
                    pin.setInState((State) objingState);
            }
        }
    }

    @objid ("51d0dd53-07d4-4ad9-9c66-c88dcd992505")
    @Override
    public void attach(Element objingElt) {
        attachToAction(objingElt);
    }

    @objid ("26406f08-f8a3-460a-a173-953fd715bd25")
    private void setStereotype(OutputPin objingElt) {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        org.eclipse.uml2.uml.Element owner = this.ecoreElement.getOwner();
        
        try {
            
            //AcceptCallAction case
            if (owner instanceof org.eclipse.uml2.uml.AcceptCallAction){
                //Get Return Information
                org.eclipse.uml2.uml.OutputPin output = ( (org.eclipse.uml2.uml.AcceptCallAction) owner).getReturnInformation();
                if ((output != null) && (output.equals(this.ecoreElement))){      
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RETURNINFORMATION, objingElt.getMClass()));       
                }
            }
            
            //AcceptEventAction case
            if (owner instanceof  org.eclipse.uml2.uml.AcceptEventAction){
                if (((org.eclipse.uml2.uml.AcceptEventAction) owner).getResults().contains(this.ecoreElement)) {
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }
            }
            
            //AddStructuralFeatureValueAction case
            if (owner instanceof org.eclipse.uml2.uml.AddStructuralFeatureValueAction){               
                org.eclipse.uml2.uml.OutputPin output = ( (org.eclipse.uml2.uml.AddStructuralFeatureValueAction) owner).getResult();
                if ((output != null) && (output.equals(this.ecoreElement))){ 
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }             
            }
            
            //ConditionalNode case
            if (owner instanceof  org.eclipse.uml2.uml.ConditionalNode){
                if (((org.eclipse.uml2.uml.ConditionalNode) owner).getResults().contains(this.ecoreElement)) {
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }
            }
            
            //LoopNode case
            if (owner instanceof  org.eclipse.uml2.uml.LoopNode){
                if (((org.eclipse.uml2.uml.LoopNode) owner).getResults().contains(this.ecoreElement)) {
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }else { 
                    org.eclipse.uml2.uml.OutputPin output = ( (org.eclipse.uml2.uml.LoopNode) owner).getDecider();
                    if ((output != null) && (output.equals(this.ecoreElement))){ 
                        objingElt.getExtension().add(mmServices
                                .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DECIDER, objingElt.getMClass()));
                    }
                }                             
            }
        
            //UnmarshallAction case
            if (owner instanceof org.eclipse.uml2.uml.UnmarshallAction){              
                if (((org.eclipse.uml2.uml.UnmarshallAction) owner).getResults().contains(this.ecoreElement)) {
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }             
            }
            
            //WriteStructuralFeatureAction case
            if (owner instanceof org.eclipse.uml2.uml.WriteStructuralFeatureAction){               
                org.eclipse.uml2.uml.OutputPin output = ( (org.eclipse.uml2.uml.WriteStructuralFeatureAction) owner).getResult();
                if ((output != null) && (output.equals(this.ecoreElement))){ 
                    objingElt.getExtension().add(mmServices
                            .getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2RESULT, objingElt.getMClass()));
                }
            }
        
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
    }

}
