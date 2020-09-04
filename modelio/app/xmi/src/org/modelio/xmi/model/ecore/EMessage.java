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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("b878cf00-e2ba-4505-badc-bb44f242bb5a")
public class EMessage extends ENamedElement {
    @objid ("e6fe54fc-18f2-4cf9-94c7-4c6e100977a1")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createMessage();
    }

    @objid ("b1b41ab9-dfa8-4789-bea6-4206027f5ba8")
    public EMessage(org.eclipse.uml2.uml.Message element) {
        super(element);
    }

    @objid ("7cc65500-906c-409a-b299-b13bfc7b5f00")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof Message) {
            org.eclipse.uml2.uml.Message ecoreElement = (org.eclipse.uml2.uml.Message) getEcoreElement();            
            setKindOfMessage((Message) objingElt, ecoreElement);
            setSortOfMessage((Message) objingElt, ecoreElement);
            setSignature((Message) objingElt, ecoreElement);
            if (!ReverseProperties.getInstance().isRoundtripEnabled()){
                setLineNumbers((Message) objingElt);
            }
        }
    }

    @objid ("f5ba587c-e1aa-44ca-b379-c8efe4698c6c")
    private void setSignature(Message message, org.eclipse.uml2.uml.Message ecoreMesasge) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.NamedElement signature = ecoreMesasge.getSignature();
        
        if (signature != null) {
            ModelElement objingSignature = (ModelElement) revProp
                    .getMappedElement(signature);
            if (objingSignature instanceof Signal)
                message.setSignalSignature((Signal) objingSignature);
            else if ((objingSignature instanceof Operation) &&
                    message.getSortOfMessage().equals(MessageSort.SYNCCALL)){
                message.setInvoked((Operation) objingSignature);
            }
        }
    }

    @objid ("e5e6ca84-53ee-42be-9ab3-5b53114d4608")
    private void setKindOfMessage(Message message, org.eclipse.uml2.uml.Message ecoreMessage) {
        org.eclipse.uml2.uml.MessageKind ecoreKind = ecoreMessage.getMessageKind();
        switch (ecoreKind.getValue()) {
        case org.eclipse.uml2.uml.MessageKind.COMPLETE:
            message.setKindOfMessage(MessageKind.COMPLETEKIND);
            break;
        case org.eclipse.uml2.uml.MessageKind.FOUND:
            message.setKindOfMessage(MessageKind.FOUNDKIND);
            break;
        case org.eclipse.uml2.uml.MessageKind.LOST:
            message.setKindOfMessage(MessageKind.LOSTKIND);
            break;
        default:
            message.setKindOfMessage(MessageKind.UNKNOWNKIND);
            break;
        }
        
        // The getMessageKind() method is not reliable for LOST and FOUND
        // messages ...
        if (EcoreModelNavigation.isFoundMessage(ecoreMessage)) {
            message.setKindOfMessage(MessageKind.FOUNDKIND);
        } else if (EcoreModelNavigation.isLostMessage(ecoreMessage)) {
            message.setKindOfMessage(MessageKind.LOSTKIND);
        }
    }

    @objid ("aa38b881-68a4-4600-8084-d873493fdd46")
    private void setSortOfMessage(Message message, org.eclipse.uml2.uml.Message ecoreMessage) {
        org.eclipse.uml2.uml.MessageSort ecoreSort = ecoreMessage.getMessageSort();
        switch (ecoreSort.getValue()) {
        case org.eclipse.uml2.uml.MessageSort.ASYNCH_CALL:
            message.setSortOfMessage(MessageSort.ASYNCCALL);
            break;
        case org.eclipse.uml2.uml.MessageSort.ASYNCH_SIGNAL:
            message.setSortOfMessage(MessageSort.ASYNCSIGNAL);
            break;
        case org.eclipse.uml2.uml.MessageSort.CREATE_MESSAGE:
            message.setSortOfMessage(MessageSort.CREATEMESSAGE);
            break;
        case org.eclipse.uml2.uml.MessageSort.DELETE_MESSAGE:
            message.setSortOfMessage(MessageSort.DESTROYMESSAGE);
            break;
        case org.eclipse.uml2.uml.MessageSort.REPLY:
            message.setSortOfMessage(MessageSort.RETURNMESSAGE);
            break;
        case org.eclipse.uml2.uml.MessageSort.SYNCH_CALL:
            message.setSortOfMessage(MessageSort.SYNCCALL);
            break;
        default :
            message.setSortOfMessage(MessageSort.ASYNCCALL);
            break;
        }
    }

    @objid ("bb082897-d74d-424a-aca8-cf86b50a91d8")
    private void setLineNumbers(Message objingElt) {
        objingElt.getReceiveEvent().setLineNumber(objingElt.getSendEvent().getLineNumber());
    }

}
