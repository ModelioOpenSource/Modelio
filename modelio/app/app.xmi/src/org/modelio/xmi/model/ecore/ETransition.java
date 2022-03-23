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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("2a96027b-2671-4eef-91d7-706bec1323c4")
public class ETransition extends ENamedElement {
    @objid ("1d964b0a-5f4f-4fd5-af5e-128bd7b5dc19")
    private boolean isInternalTransition = false;

    @objid ("4b9505f8-ed74-4ab9-b5c0-c66743db05ab")
    private org.eclipse.uml2.uml.Transition ecoreElement = null;

    @objid ("6159b52e-fe85-4ec8-948b-1819aff8dd04")
    @Override
    public Element createObjingElt() {
        IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        if (this.isInternalTransition)
            return factory.createInternalTransition();
        return factory.createTransition();
    }

    @objid ("4dd69ba2-0f10-44f4-8e4a-bf31c0b67df2")
    public  ETransition(org.eclipse.uml2.uml.Transition element) {
        super(element);
        this.ecoreElement = element;
        this.isInternalTransition = EcoreModelNavigation.isInternalTransition(element);
        
    }

    @objid ("260e89b6-971a-4788-bc50-9bf69c533fa4")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if (!this.isInternalTransition)
            setTarget((Transition) objingElt);
        
        setSource((Transition) objingElt);
        
        setEffects((Transition) objingElt);
        setTrigger((Transition) objingElt);
        setPost((Transition) objingElt);
        setGuard((Transition) objingElt);
        
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setReceivedEvent((Transition) objingElt);
            setSentEvents((Transition) objingElt);
            setEffect((Transition) objingElt);        
        }
        
    }

    @objid ("d181841b-524b-4eba-97ce-100ef751f7ba")
    private void setEffect(Transition objingElt) {
        String effect = ObjingEAnnotation.getEffect(this.ecoreElement);
        if (effect != null)
            objingElt.setEffect(effect);
        
    }

    @objid ("6d08a826-bb72-4ada-80ca-94d003589b2d")
    private void setSentEvents(Transition objingElt) {
        String signalString = ObjingEAnnotation.getSignal(this.ecoreElement);
        if (signalString != null)
            objingElt.setSentEvents(signalString);
        
    }

    @objid ("9bc783a3-94a8-49b5-8e07-b50ce0d96042")
    private void setReceivedEvent(Transition objingElt) {
        String receivedEvent = ObjingEAnnotation.getReceivedEvent(this.ecoreElement);
        if (receivedEvent != null){
            objingElt.setReceivedEvents(receivedEvent);
        }
        
    }

    @objid ("56ed689a-827e-4cf0-bd77-9888cd4eaae9")
    private void setGuard(Transition objingElt) {
        org.eclipse.uml2.uml.Constraint guardConstraint = this.ecoreElement.getGuard();
        
        if (guardConstraint != null){
        
            String guard = "";
            org.eclipse.uml2.uml.ValueSpecification value = guardConstraint.getSpecification();
        
            if (value != null){                
                guard = EcoreModelNavigation.getValue(value);
            }
        
            if (guard.equals(""))
                guard = guardConstraint.getName();
        
            objingElt.setGuard(guard);
        }
        
    }

    @objid ("6aa87f31-5aeb-414f-be44-fb28413ac836")
    private void setPost(Transition objingElt) {
        if (this.ecoreElement instanceof org.eclipse.uml2.uml.ProtocolTransition){
            org.eclipse.uml2.uml.Constraint postConstraint = ((org.eclipse.uml2.uml.ProtocolTransition) this.ecoreElement).getPostCondition();
            if (postConstraint != null){
                org.eclipse.uml2.uml.ValueSpecification value = postConstraint.getSpecification();
                if (value != null){
                    objingElt.setPostCondition(EcoreModelNavigation.getValue(value));
                }else 
                    objingElt.setPostCondition(postConstraint.getName());
            }
        }else if (ReverseProperties.getInstance().isRoundtripEnabled()){
            objingElt.setPostCondition(ObjingEAnnotation.getPostCondition(this.ecoreElement));
        }
        
    }

    @objid ("6946ba99-5c76-4602-8879-f99bec8dbb1d")
    private void setSource(Transition transition) {
        org.eclipse.uml2.uml.Vertex ecoreSource = this.ecoreElement.getSource();
        if (ecoreSource != null) {
            Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
        
            if (objingSource instanceof StateVertex) {
                if (this.isInternalTransition && objingSource instanceof State)
                    ((InternalTransition) transition)
                    .setSComposed((State) objingSource);
                else
                    transition.setSource((StateVertex) objingSource);
            }
        }
        
    }

    @objid ("6a9063ea-7e2f-4fbf-9198-76746c639e2b")
    private void setTarget(Transition transition) {
        org.eclipse.uml2.uml.Vertex ecoreTarget = this.ecoreElement.getTarget();
        if (ecoreTarget != null) {
            Object objingTarget = ReverseProperties.getInstance().getMappedElement(ecoreTarget);
        
            if (objingTarget instanceof StateVertex)
                transition.setTarget((StateVertex) objingTarget);
        }
        
    }

    @objid ("0c46887c-e3bb-4a87-a1fc-f518ff196d2c")
    private void setEffects(Transition transition) {
        org.eclipse.uml2.uml. Behavior behavior = this.ecoreElement.getEffect();
        
        if ((behavior != null) && (behavior instanceof org.eclipse.uml2.uml.OpaqueBehavior)) {
            for (Object clientDependency : behavior.getClientDependencies()) {
                for (Object ecoreSupplier : ((org.eclipse.uml2.uml.Dependency) clientDependency)
                        .getSuppliers()) {
                    if (ecoreSupplier instanceof  org.eclipse.uml2.uml.Signal) {
                        Object objingSupplier = ReverseProperties.getInstance()
                                .getMappedElement( (org.eclipse.uml2.uml.Signal) ecoreSupplier);
                        if (objingSupplier instanceof Signal) {
                            transition.setEffects((Signal) objingSupplier);
                            return;
                        }
                    }
                }
            }
        }
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            String signal = ObjingEAnnotation.getSignal(this.ecoreElement);
            if (signal != null){
                transition.setEffect(signal);
            }
        }
        
    }

    @objid ("43d0212d-8fb7-4918-b1cc-13d365fbbe0c")
    private void setTrigger(Transition transition) {
        EList<?> ecoreTriggerList = this.ecoreElement.getTriggers();
        if (ecoreTriggerList.size() > 0) {
            org.eclipse.uml2.uml.Trigger ecoreTrigger =  (org.eclipse.uml2.uml.Trigger) ecoreTriggerList.get(0);
            org.eclipse.uml2.uml.Event ecoreEvent = ecoreTrigger.getEvent();
            if (ecoreEvent != null){    
                if (ecoreEvent instanceof  org.eclipse.uml2.uml.ChangeEvent) {
                    setReceivedEvent(transition,  (org.eclipse.uml2.uml.ChangeEvent) ecoreEvent);
                } else {
                    setEvent(transition,  ecoreEvent);
                }
            }
        }
        
    }

    @objid ("4bd00279-bfdf-41e6-bdb7-0f6a7d3b0b19")
    private void setReceivedEvent(Transition transition, org.eclipse.uml2.uml.ChangeEvent event) {
        Event objEvent = (Event) ReverseProperties.getInstance().getMappedElement(event);
        transition.setTrigger(objEvent);
        
    }

    @objid ("46c25802-58d0-440d-a193-6395ebf61f24")
    private void setEvent(final Transition transition, final org.eclipse.uml2.uml.Event ecoreEvent) {
        Event objEvent = (Event) ReverseProperties.getInstance().getMappedElement(ecoreEvent);
        transition.setTrigger(objEvent);
        
    }

}
