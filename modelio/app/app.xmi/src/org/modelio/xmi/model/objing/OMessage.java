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
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;

@objid ("ab225be5-a4cd-494b-be75-789b588b3018")
public class OMessage extends OModelElement {
    @objid ("f8a25c72-5584-4031-9002-620c0e07f307")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createMessage();
    }

    @objid ("2a231905-0674-42c5-982c-2488f27d910e")
    public  OMessage(Message param) {
        super(param);
    }

    @objid ("f86fc3c1-bf12-4561-a279-7cbcb0cbea51")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // we need to take the interaction on the send event
        Interaction interaction = (Interaction) AbstractObjingModelNavigation.getEnclosingElement(getObjingElement(),
                getObjingElement().getMClass().getMetamodel().getMClass(Interaction.class));
        
        
        Object ecoreInteraction =  GenerationProperties.getInstance().getMappedElement(interaction);
        
        if ((ecoreInteraction != null) && (ecoreInteraction instanceof org.eclipse.uml2.uml.Interaction))
            ((org.eclipse.uml2.uml.Message) ecoreElt).setInteraction((org.eclipse.uml2.uml.Interaction)ecoreInteraction);
        
    }

    @objid ("f4b27e6d-f577-4d41-8652-fa0b0dc38f13")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setMessageSort((org.eclipse.uml2.uml.Message) ecoreElt);
        setArgument((org.eclipse.uml2.uml.Message) ecoreElt);
        setConnector((org.eclipse.uml2.uml.Message) ecoreElt);
        
    }

    @objid ("caddd578-6782-4aa3-b8d8-a9b6307ec1c1")
    private void setMessageSort(org.eclipse.uml2.uml.Message ecoreElt) {
        switch (((Message) getObjingElement()).getSortOfMessage()) {
        case ASYNCCALL:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.ASYNCH_CALL_LITERAL);
            break;
        case CREATEMESSAGE:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.CREATE_MESSAGE_LITERAL);
            break;
        case ASYNCSIGNAL:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.ASYNCH_SIGNAL_LITERAL);
            break;
        case DESTROYMESSAGE:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.DELETE_MESSAGE_LITERAL);
            break;
        case SYNCCALL:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.SYNCH_CALL_LITERAL);
            break;
        case RETURNMESSAGE:
            ecoreElt.setMessageSort(org.eclipse.uml2.uml.MessageSort.REPLY_LITERAL);
            break;
        default:
            break;
        }
        
    }

    @objid ("9eb99c2d-a480-473b-84ab-8919f584e88f")
    private void setArgument(org.eclipse.uml2.uml.Message ecoreElt) {
        String argument = ((Message) getObjingElement()).getArgument();
        if (argument != null && argument.trim().length() > 0) {
            org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE
                    .createLiteralString();
            valueSpecification.setValue(argument);
            ecoreElt.getArguments().add(valueSpecification);
        }
        
    }

    @objid ("8758cd5f-2b99-421b-bfc3-0021c783f1d7")
    private void setConnector(org.eclipse.uml2.uml.Message ecoreElt) {
        Message objMessage = (Message) getObjingElement();
        
        MessageEnd end1 = objMessage.getReceiveEvent();
        MessageEnd end2 = objMessage.getSendEvent();
        
        if ((end1 != null )
                && (end2 != null)
                && (end1.getCovered().size() > 0)
                &&  (end2.getCovered().size() > 0)) {
        
            Instance instance1 = end1.getCovered().get(0).getRepresented();
            Instance instance2 = end2.getCovered().get(0).getRepresented();
        
            if ((instance1 != null) && (instance2 != null)){
        
                for (LinkEnd linkEnd : instance1.getOwnedEnd()){
        
                    Link link = linkEnd.getLink();
                    if (link != null){
        
                        for (LinkEnd linkend2 : link.getLinkEnd()){
        
                            if (linkend2.getOwner().equals(instance2)){
                                //found the correct instance
                                org.eclipse.uml2.uml.Element ecoreConnector = GenerationProperties.getInstance().getMappedElement(link);
                                if (ecoreConnector instanceof org.eclipse.uml2.uml.Connector){
                                    ecoreElt.setConnector((org.eclipse.uml2.uml.Connector) ecoreConnector);
                                    break;
                                }
                            }
                        }
                    }
        
                }
        
            }
        
        }
        
    }

}
