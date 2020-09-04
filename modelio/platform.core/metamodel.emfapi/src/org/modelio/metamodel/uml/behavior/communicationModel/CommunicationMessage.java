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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * CommunicationMessage v0.0.9054
 * 
 * 
 * CommunicationMessages are used in communication diagrams. 
 * 
 * If the CommunicationMessage has no InvokedOperation, then its description is in its name. 
 * 
 * In Modelio, a Message belongs to the Communication it follows.
 * 
 * The message sequencing information is handled in communication diagrams by the 'Sequencing' attribute.
 */
@objid ("005b08f6-c4bf-1fd8-97fe-001ec947cd2a")
public interface CommunicationMessage extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("7c21e66e-03f9-4177-93e8-de595dddab96")
    public static final String MNAME = "CommunicationMessage";

    /**
     * The metaclass qualified name.
     */
    @objid ("60879b15-405e-4e7c-9ff4-8a599e95aea3")
    public static final String MQNAME = "Standard.CommunicationMessage";

    /**
     * Getter for attribute 'CommunicationMessage.Argument'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0d4560a2-bcfc-4971-80aa-5cc030cec4a2")
    String getArgument();

    /**
     * Setter for attribute 'CommunicationMessage.Argument'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0aac360d-3d70-4a09-a0c5-e9ed652090b0")
    void setArgument(String value);

    /**
     * Getter for attribute 'CommunicationMessage.Sequence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b609eb48-5068-49a0-94f7-62c005f779e8")
    String getSequence();

    /**
     * Setter for attribute 'CommunicationMessage.Sequence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2ab41695-b9c9-423c-807f-d10e76e80754")
    void setSequence(String value);

    /**
     * Getter for attribute 'CommunicationMessage.SortOfMessage'
     * 
     * Metamodel description:
     * <i>The sort of communication reflected by the CommunicationMessage. Default value is synchCall.</i>
     */
    @objid ("c3047e8d-9096-4d11-b872-76b14788de85")
    MessageSort getSortOfMessage();

    /**
     * Setter for attribute 'CommunicationMessage.SortOfMessage'
     * 
     * Metamodel description:
     * <i>The sort of communication reflected by the CommunicationMessage. Default value is synchCall.</i>
     */
    @objid ("e2b758b9-70ea-4981-b81c-61ea9a3b7b20")
    void setSortOfMessage(MessageSort value);

    /**
     * Getter for relation 'CommunicationMessage->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f0b19be5-9a88-40a0-bb77-5859db3bd034")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'CommunicationMessage->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9c5593fd-889e-4b48-85cd-3024df1d128a")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'CommunicationMessage->Channel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("95318e99-a3d5-4b28-9105-d3ffed72a753")
    CommunicationChannel getChannel();

    /**
     * Setter for relation 'CommunicationMessage->Channel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7691e9b5-4cf1-4dc9-965a-e59ef059cef3")
    void setChannel(CommunicationChannel value);

    /**
     * Getter for relation 'CommunicationMessage->InvertedChannel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7cb38a1f-9b26-41c9-8da6-beb6b05e039d")
    CommunicationChannel getInvertedChannel();

    /**
     * Setter for relation 'CommunicationMessage->InvertedChannel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("06f1402a-f750-467b-8acb-512ecd7fc6b5")
    void setInvertedChannel(CommunicationChannel value);

    /**
     * Getter for relation 'CommunicationMessage->Invoked'
     * 
     * Metamodel description:
     * <i>The Operation is invoked by the Message.</i>
     */
    @objid ("4a99a651-9892-4bb0-89cf-a867c145c454")
    Operation getInvoked();

    /**
     * Setter for relation 'CommunicationMessage->Invoked'
     * 
     * Metamodel description:
     * <i>The Operation is invoked by the Message.</i>
     */
    @objid ("f323a471-df47-42ef-9445-fd10a58404c3")
    void setInvoked(Operation value);

    /**
     * Getter for relation 'CommunicationMessage->SignalSignature'
     * 
     * Metamodel description:
     * <i>Signal that is sent by the message.</i>
     */
    @objid ("a3d5ec6e-e081-44cc-a72d-891a3c50f904")
    Signal getSignalSignature();

    /**
     * Setter for relation 'CommunicationMessage->SignalSignature'
     * 
     * Metamodel description:
     * <i>Signal that is sent by the message.</i>
     */
    @objid ("72743132-5450-4923-bf4b-b3f9a894b354")
    void setSignalSignature(Signal value);

}
