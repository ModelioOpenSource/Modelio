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
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.SendSignalEvent;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;

@objid ("b4674bf7-91ae-46fa-a732-cf33045ec2f0")
public class OMessageEnd extends OOccurrenceSpecification {
    @objid ("a0f5e5d6-49de-4bf6-bd88-4e400967c04e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("15570f8b-3d47-4e3e-83f4-8a7c710a2e86")
    public  OMessageEnd(MessageEnd param) {
        super(param);
    }

    @objid ("9f074bd4-71e0-4ff0-b9dc-08c3bc8de654")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("58e8f1a8-ffa1-422d-b3e2-4db35d76015b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification
                || ecoreElt instanceof org.eclipse.uml2.uml.Gate) {
        
            org.eclipse.uml2.uml.MessageEnd messageEnd = (org.eclipse.uml2.uml.MessageEnd) ecoreElt;
            Message message = ((MessageEnd) getObjingElement()).getReceivedMessage();
        
            if (message != null)
                setMessageAndSignatureMessage(messageEnd, message);
        
            message = ((MessageEnd) getObjingElement()).getSentMessage();
            if (message != null)
                setMessageAndSignatureMessage(messageEnd, message);
        
        }
        
    }

    @objid ("e9b81052-4cc8-4822-af78-907d9014e36e")
    private void setMessageAndSignatureMessage(org.eclipse.uml2.uml.MessageEnd ecoreMessOccSpec, Message message) {
        // we set the message on Execution occurence because if we set on
        // org.eclipse.uml2.uml.Message we may create Execution occurence in bad order
        Operation objinOperation = message.getInvoked();
        Signal objingSignal = message.getSignalSignature();
        
        org.eclipse.uml2.uml.Message ecoreMessage = (org.eclipse.uml2.uml.Message) GenerationProperties.getInstance().getMappedElement(message);
        Element objingElement= getObjingElement();
        
        org.eclipse.uml2.uml.Event event = null;
        
        if (ecoreMessage != null) {
        
            if (objingElement.equals(message.getSendEvent())) {
        
                ecoreMessage.setSendEvent(ecoreMessOccSpec);
                ecoreMessOccSpec.setMessage(ecoreMessage);
        
            } else if (objingElement.equals(message.getReceiveEvent())){
        
                ecoreMessage.setReceiveEvent(ecoreMessOccSpec);
                ecoreMessOccSpec.setMessage(ecoreMessage);
            }
        
            if (message.getSortOfMessage().equals(org.modelio.metamodel.uml.behavior.interactionModel.MessageSort.CREATEMESSAGE)
                    && objinOperation != null
                    && ecoreMessOccSpec instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
        
                event = UMLFactory.eINSTANCE.createCreationEvent();
        
            }
        
            // We set a SentEvent  org.eclipse.uml2.uml.Operation
            if (objinOperation != null
                    && ecoreMessOccSpec instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification) {
                event = UMLFactory.eINSTANCE.createSendOperationEvent();
                ((SendOperationEvent) event).setOperation((org.eclipse.uml2.uml.Operation) GenerationProperties.getInstance().getMappedElement(objinOperation));
            }
        
            if ((objingSignal != null)
                    && (ecoreMessOccSpec instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)) {
                event = UMLFactory.eINSTANCE.createSendSignalEvent();
                ((SendSignalEvent) event).setSignal((org.eclipse.uml2.uml.Signal) GenerationProperties.getInstance().getMappedElement(objingSignal));
        
            }
        
            if (event != null) {
                attachEvent(event, ecoreMessOccSpec);
                setEvent(event,ecoreMessOccSpec);
            }
        }
        
    }

    @objid ("1aa94ba1-b2e0-424d-95f9-cd9c60d4af74")
    private void attachEvent(org.eclipse.uml2.uml.Event event, org.eclipse.uml2.uml.MessageEnd ecoreMessOccSpec) {
        org.eclipse.uml2.uml.Package thePackage = ecoreMessOccSpec.getNearestPackage();
        
        if (thePackage == null) {
        
            Interaction objingInteraction = (Interaction) AbstractObjingModelNavigation.getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Interaction.class));
        
            if (objingInteraction != null) {
        
                org.eclipse.uml2.uml.Interaction ecoreInteraction = (org.eclipse.uml2.uml.Interaction) GenerationProperties.getInstance()
                        .getMappedElement(objingInteraction);
        
                if (ecoreInteraction != null)
                    thePackage = ecoreInteraction.getNearestPackage();
        
            }
        }
        
        if (thePackage != null){
            thePackage.getPackagedElements().add(event);
        }
        
    }

    @objid ("1b4554b1-e982-497b-8510-a1dabcc45e8d")
    private boolean haveUML2Event(Message objingMessage) {
        MessageEnd end = objingMessage.getSendEvent();
        MessageEnd start = objingMessage.getReceiveEvent();
        
        for (Dependency dep : end.getDependsOnDependency()){
            if (dep.isStereotyped(IModelerModulePeerModule.MODULE_NAME, "UML2Event"))
                return true;
        }
        
        for (Dependency dep : start.getDependsOnDependency()){
            if (dep.isStereotyped(IModelerModulePeerModule.MODULE_NAME, "UML2Event"))
                return true;
        }
        return false;
    }

    @objid ("a88968ff-14dd-4041-bfab-c5caed12783a")
    private void setEvent(org.eclipse.uml2.uml.Event event, org.eclipse.uml2.uml.MessageEnd ecoreMessOccSpec) {
        if (ecoreMessOccSpec instanceof org.eclipse.uml2.uml.OccurrenceSpecification) {
            ((org.eclipse.uml2.uml.OccurrenceSpecification) ecoreMessOccSpec)
            .setEvent(event);
        }
        
    }

}
