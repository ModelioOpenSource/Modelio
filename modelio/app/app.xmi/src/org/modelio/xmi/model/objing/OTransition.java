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
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Vertex;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class handles the export of Transition.
 * @author ebrosse
 */
@objid ("fa2e2017-b1af-480a-ac44-3299bd462bfa")
public class OTransition extends OModelElement {
    @objid ("3a55be12-61d2-4865-a454-6a9468168ca2")
    private boolean isProtocolTransition = false;

    @objid ("06b74d5b-7500-4ab1-9e01-490c12620b2a")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("27d0a3bd-6380-4d92-a933-4f6c20218205")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (!( getObjingElement() instanceof InternalTransition)){
        
            if (this.isProtocolTransition)
        
                return UMLFactory.eINSTANCE.createProtocolTransition();
        
            return UMLFactory.eINSTANCE.createTransition();
        }
        return null;
    }

    /**
     * Constructor with the exported Transition as parameter
     * 
     * @param param : the exported Transition
     */
    @objid ("067c4414-3af8-4475-aec1-23ca0b7d47fb")
    public OTransition(Transition param) {
        super(param);
        this.isProtocolTransition = AbstractObjingModelNavigation.isProtocolTransition(param);
    }

    @objid ("6da3591b-cc9f-4793-a748-8ac20c7a3670")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        StateVertex objingOwner = getObjingElement().getSource();
        org.eclipse.uml2.uml.Region ownerRegion = null;
        
        if (objingOwner != null) {
        
            Region objOwnerRegion = objingOwner.getParent();
        
            if (objOwnerRegion == null){
        
                if (objingOwner instanceof ConnectionPointReference){
                    objOwnerRegion = ((ConnectionPointReference) objingOwner).getOwnerState().getParent();
        
                }else if (objingOwner instanceof EntryPointPseudoState){
                    EntryPointPseudoState entry = (EntryPointPseudoState) objingOwner;
                    StateMachine sm = entry.getEntryOfMachine();
        
                    if (sm != null)
                        objOwnerRegion= sm.getTop();
                    else{
                        objOwnerRegion= entry.getEntryOf().getParent();
                    }
        
                } else if (objingOwner instanceof ExitPointPseudoState){
                    ExitPointPseudoState exit = (ExitPointPseudoState) objingOwner;
                    StateMachine sm = exit.getExitOfMachine();
        
                    if (sm != null)
                        objOwnerRegion= sm.getTop();
                    else{
                        objOwnerRegion= exit.getExitOf().getParent();
                    }
        
                }
        
            }
        
            if (objOwnerRegion != null)
                ownerRegion=  (org.eclipse.uml2.uml.Region) this.genProp.getMappedElement(objOwnerRegion);
        
            if (ownerRegion != null){
                ownerRegion.getTransitions().add( (org.eclipse.uml2.uml.Transition)ecoreElt);
            }
        
        }else{
            ecoreElt.destroy();
        }
    }

    @objid ("718d084e-b2d5-47f7-8bb4-08023eed9c8a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (getObjingElement() instanceof InternalTransition){
            setOwner( (org.eclipse.uml2.uml.Transition) ecoreElt);
        
        }else{
            setSource( (org.eclipse.uml2.uml.Transition) ecoreElt);
            setTarget( (org.eclipse.uml2.uml.Transition) ecoreElt);
        
        }
        
        setEffect( (org.eclipse.uml2.uml.Transition)ecoreElt);
        setTrigger( (org.eclipse.uml2.uml.Transition) ecoreElt);
        setGuard( (org.eclipse.uml2.uml.Transition) ecoreElt);
        setPostCondition( (org.eclipse.uml2.uml.Transition) ecoreElt);
        setEffects( (org.eclipse.uml2.uml.Transition)ecoreElt);
        setProcessed( (org.eclipse.uml2.uml.Transition) ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.ProtocolTransition){
            setReferred( (org.eclipse.uml2.uml.Transition) ecoreElt);
        }
        
        if (this.genProp.isRoundtripEnabled()){
            setReceivedEvent(ecoreElt);
            setSentEvents(ecoreElt);
        
        }
    }

    @objid ("3c29161c-455f-43e3-826e-fa865b11cfa0")
    private void setGuard(org.eclipse.uml2.uml.Transition ecoreElt) {
        String objGuard = getObjingElement().getGuard();
        if ((objGuard != null) && (!objGuard.equals("")) && (ecoreElt.getGuard() == null)){
            org.eclipse.uml2.uml.Constraint guard = UMLFactory.eINSTANCE.createConstraint();
            guard.setName(objGuard);
            ecoreElt.setGuard(guard);
            guard.getConstrainedElements().add(ecoreElt);
            org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE.createLiteralString();
            valueSpecification.setValue(objGuard);
            guard.setSpecification(valueSpecification);
        
            if (this.isProtocolTransition){
                ((org.eclipse.uml2.uml.ProtocolTransition) ecoreElt).setPreCondition(guard);
            }
        }
    }

    @objid ("bb5eb835-93ff-4b0c-bf86-54250869024e")
    private void setEffect(org.eclipse.uml2.uml.Transition ecoreElt) {
        String effect = getObjingElement().getEffect();
        if ((effect != null) && (!effect.equals(""))){
            // String Case
            org.eclipse.uml2.uml.OpaqueBehavior opaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
            opaqueBehavior.getBodies().add(effect);
            ecoreElt.setEffect(opaqueBehavior);
        }else{
        
            //Behavior Case
        
            Behavior behavior = getObjingElement().getBehaviorEffect();
            if (behavior != null){
                org.eclipse.uml2.uml.Element ecoreBehavior = this.genProp.getMappedElement(behavior);
        
                if ((ecoreBehavior != null) && (ecoreBehavior instanceof org.eclipse.uml2.uml. Behavior)){
        
                    org.eclipse.uml2.uml.OpaqueBehavior opaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
                    ecoreElt.setEffect(opaqueBehavior);
                    opaqueBehavior.getRedefinedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreBehavior);
                }
        
            }
        }
    }

    @objid ("02465362-e4ea-4e56-b4aa-72e951e14f6a")
    private void setSentEvents(org.eclipse.uml2.uml.Element ecoreElt) {
        String signalString = getObjingElement().getSentEvents();
        if (signalString != null)
            ObjingEAnnotation.setSignal(ecoreElt, signalString);
    }

    @objid ("7622b4d5-6695-4451-bdf0-84b0e0c26c13")
    private void setSource(org.eclipse.uml2.uml.Transition transition) {
        StateVertex objingSource = getObjingElement().getSource();
        if (objingSource != null) {
            org.eclipse.uml2.uml.Element ecoreSource = this.genProp.getMappedElement(objingSource);
            if (ecoreSource instanceof org.eclipse.uml2.uml.Vertex) {
                transition.setSource((Vertex) ecoreSource);
            }
        
        }
    }

    @objid ("ed54ec13-8cff-491d-b1a9-5020e2c4126e")
    private void setTarget(org.eclipse.uml2.uml.Transition transition) {
        StateVertex objingTarget = getObjingElement().getTarget();
        if (objingTarget != null) {
            org.eclipse.uml2.uml.Element ecoreTarget = this.genProp.getMappedElement(objingTarget);
            if (ecoreTarget instanceof org.eclipse.uml2.uml.Vertex) {
                transition.setTarget((Vertex) ecoreTarget);
            }
        }
    }

    @objid ("9f47f02b-bae2-468b-9eba-b3b487c2e748")
    private void setEffects(org.eclipse.uml2.uml.Transition transition) {
        Signal signal = getObjingElement().getEffects();
        if (signal != null) {
            org.eclipse.uml2.uml. Behavior behavior = transition.getEffect();
            if (behavior == null){
                behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
                behavior.setName(signal.getName());
                transition.setEffect(behavior);
            }
        
            org.eclipse.uml2.uml.Signal ecoreSignal =  (org.eclipse.uml2.uml.Signal) this.genProp.getMappedElement(signal);
            if (ecoreSignal != null) {
                Region ownerRegion = getObjingElement().getTarget().getParent();
        
                StateMachine objingSM =  AbstractObjingModelNavigation
                        .getOwnerStateMachine(ownerRegion);
        
                if (objingSM != null) {
                    org.modelio.metamodel.uml.statik.Package smOwner = AbstractObjingModelNavigation
                            .getNearestPackage(objingSM);
        
                    if (smOwner != null) {
                        org.eclipse.uml2.uml.Element ecorePkg = this.genProp.getMappedElement(smOwner);
        
                        if (ecorePkg instanceof Package) {
                            org.eclipse.uml2.uml.Dependency dependency = UMLFactory.eINSTANCE.createDependency();
                            dependency.getClients().add(behavior);
                            dependency.getSuppliers().add(ecoreSignal);
                            ((Package) ecorePkg).getPackagedElements().add(dependency);
                        }
                    }
                }
            }
        }
    }

    @objid ("ed37a9d2-45e2-454e-8333-36fcec10901a")
    private void setTrigger(org.eclipse.uml2.uml.Transition transition) {
        Event event = getObjingElement().getTrigger();
        if (event != null) {
            setEventTrigger(transition, event);
        }
    }

    @objid ("72f92d0a-02bd-4554-87d7-1b55a5d6fd99")
    private void setReferred(org.eclipse.uml2.uml.Transition transition) {
        if (transition instanceof org.eclipse.uml2.uml.ProtocolTransition){
            Event event = getObjingElement().getTrigger();
            if (event != null){
                Object temp = this.genProp.getMappedElement(event);
                if (temp instanceof  org.eclipse.uml2.uml.Event){
                    org.eclipse.uml2.uml.Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
                    trigger.setEvent((org.eclipse.uml2.uml.Event)temp);
                    transition.getTriggers().add(trigger);
                }
            }
        }
    }

    @objid ("5c9e5d84-d094-4df8-bd5f-9b541139d944")
    private void setPostCondition(final org.eclipse.uml2.uml.Transition ecoreElt) {
        String objPost = getObjingElement().getPostCondition();
        if ((objPost != null) && (!objPost.equals(""))){
            if ((ecoreElt instanceof org.eclipse.uml2.uml.ProtocolTransition) && (getObjingElement().getProcessed() != null)){
                org.eclipse.uml2.uml.Constraint post = UMLFactory.eINSTANCE.createConstraint();
                post.setName(objPost);
                ((org.eclipse.uml2.uml.ProtocolTransition)ecoreElt).setPostCondition(post);
                post.getConstrainedElements().add(ecoreElt);
                org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE.createLiteralString();
                valueSpecification.setValue(objPost);
                post.setSpecification(valueSpecification);
            }else{
                if (this.genProp.isRoundtripEnabled())
                    ObjingEAnnotation.setPostCondition(ecoreElt, objPost);
            }
        }
    }

    @objid ("7d6f234e-0274-4b14-8498-087138ae333b")
    private void setReceivedEvent(final org.eclipse.uml2.uml.Element ecoreElt) {
        String receivedEvent = getObjingElement().getReceivedEvents();
        if (receivedEvent != null)
            ObjingEAnnotation.setReceivedEvent(ecoreElt, receivedEvent);
    }

    @objid ("8fc1bd32-9dc9-4e95-980f-9794400e2b5b")
    private void setEventTrigger(final org.eclipse.uml2.uml.Transition transition, final Event objingEvent) {
        Object ecoreEvent =  this.genProp.getMappedElement(objingEvent);
        
        if (ecoreEvent instanceof org.eclipse.uml2.uml.Event) {
            org.eclipse.uml2.uml.Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
            trigger.setName(objingEvent.getName());
            transition.getTriggers().add(trigger);
            trigger.setEvent((org.eclipse.uml2.uml.Event) ecoreEvent);
        }
    }

    @objid ("09fc5c0a-4e57-47a2-8b0e-37070a89a34a")
    private void setProcessed(final org.eclipse.uml2.uml.Transition transition) {
        Operation objingOperation = getObjingElement().getProcessed();
        
        if (objingOperation != null) {
        
            Object effect = this.genProp.getMappedElement(objingOperation);
            if (effect instanceof org.eclipse.uml2.uml. BehavioralFeature){
                org.eclipse.uml2.uml. Behavior behavior = transition.getEffect();
                if (behavior == null){
                    behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
                    transition.setEffect(behavior);
                }
        
                behavior.setSpecification((org.eclipse.uml2.uml.BehavioralFeature) effect);
        
            }
        }
    }

    @objid ("312e13c7-f5aa-4b9d-9c0f-ede9340acf10")
    private void setOwner(final org.eclipse.uml2.uml.Transition ecoreElt) {
        StateVertex objingSource = ((InternalTransition) getObjingElement()).getSComposed();
        
        org.eclipse.uml2.uml.Element ecoreSource = this.genProp.getMappedElement(objingSource);
        if (ecoreSource instanceof org.eclipse.uml2.uml.Vertex) {
            ecoreElt.setSource((Vertex) ecoreSource);
            ecoreElt.setTarget((Vertex) ecoreSource);
        }
    }

    @objid ("82bc8d83-b6bc-47da-bead-78e7db478a36")
    @Override
    public Transition getObjingElement() {
        return (Transition) super.getObjingElement();
    }

}
