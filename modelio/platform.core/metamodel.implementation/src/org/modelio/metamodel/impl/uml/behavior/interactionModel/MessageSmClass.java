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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageData;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageEndSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("8c7c5c80-1e3c-40f4-96bd-887108a36523")
public class MessageSmClass extends UmlModelElementSmClass {
    @objid ("42f70467-74de-4a3e-b809-b1cb6926ae0a")
    private SmAttribute argumentAtt;

    @objid ("ad57d2eb-ca10-43c3-82ad-28b20fe0dc40")
    private SmAttribute kindOfMessageAtt;

    @objid ("0da74f84-7b53-48ed-b4dc-d8d4abd97872")
    private SmAttribute sortOfMessageAtt;

    @objid ("54faebf5-f22b-4618-8aab-7ae2628840ec")
    private SmAttribute sequenceAtt;

    @objid ("9fbc6372-38f5-4dd6-9b9c-00eda582e91f")
    private SmDependency signalSignatureDep;

    @objid ("0bc8003d-3646-4541-8399-251b13533e65")
    private SmDependency receiveEventDep;

    @objid ("bdffc46f-b358-4065-8884-bd6bf21c3033")
    private SmDependency sendEventDep;

    @objid ("b263dbda-9f27-4e40-97e0-4d4140dfe226")
    private SmDependency invokedDep;

    @objid ("4f9bde87-4ca6-40cb-9a38-bb731ff96205")
    private SmDependency realizedInformationFlowDep;

    @objid ("a472c443-d965-457a-8ade-9f6720539706")
    public MessageSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cc2628b1-2db1-4e4d-93e9-ef8fbf860e77")
    @Override
    public String getName() {
        return "Message";
    }

    @objid ("3a07ab3d-4071-447a-bd8a-5448887eecdd")
    @Override
    public Version getVersion() {
        return new Version("2.2.01");
    }

    @objid ("88e97e04-21bb-4641-a82e-3533a9589689")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Message.class;
    }

    @objid ("150a7964-7151-4255-aaa5-b04cb8acbc0e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("125122a5-2aac-49aa-b31b-880052a5e951")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("d8a81dcc-9c27-45e3-9f25-9ed96ad59236")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new MessageObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.argumentAtt = new ArgumentSmAttribute();
        this.argumentAtt.init("Argument", this, String.class );
        registerAttribute(this.argumentAtt);
        
        this.kindOfMessageAtt = new KindOfMessageSmAttribute();
        this.kindOfMessageAtt.init("KindOfMessage", this, MessageKind.class );
        registerAttribute(this.kindOfMessageAtt);
        
        this.sortOfMessageAtt = new SortOfMessageSmAttribute();
        this.sortOfMessageAtt.init("SortOfMessage", this, MessageSort.class );
        registerAttribute(this.sortOfMessageAtt);
        
        this.sequenceAtt = new SequenceSmAttribute();
        this.sequenceAtt.init("Sequence", this, String.class );
        registerAttribute(this.sequenceAtt);
        
        
        // Initialize and register the SmDependency
        this.signalSignatureDep = new SignalSignatureSmDependency();
        this.signalSignatureDep.init("SignalSignature", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.signalSignatureDep);
        
        this.receiveEventDep = new ReceiveEventSmDependency();
        this.receiveEventDep.init("ReceiveEvent", this, metamodel.getMClass(MessageEnd.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.receiveEventDep);
        
        this.sendEventDep = new SendEventSmDependency();
        this.sendEventDep.init("SendEvent", this, metamodel.getMClass(MessageEnd.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.sendEventDep);
        
        this.invokedDep = new InvokedSmDependency();
        this.invokedDep.init("Invoked", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.invokedDep);
        
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
    }

    @objid ("42d107ad-b482-4de8-a6d5-9383d5b47fb2")
    public SmAttribute getArgumentAtt() {
        if (this.argumentAtt == null) {
        	this.argumentAtt = this.getAttributeDef("Argument");
        }
        return this.argumentAtt;
    }

    @objid ("469329c0-6b1c-472d-bead-67df1b2b8357")
    public SmAttribute getKindOfMessageAtt() {
        if (this.kindOfMessageAtt == null) {
        	this.kindOfMessageAtt = this.getAttributeDef("KindOfMessage");
        }
        return this.kindOfMessageAtt;
    }

    @objid ("14b16e4e-39ef-43af-a99c-dcd337f0f6ae")
    public SmAttribute getSortOfMessageAtt() {
        if (this.sortOfMessageAtt == null) {
        	this.sortOfMessageAtt = this.getAttributeDef("SortOfMessage");
        }
        return this.sortOfMessageAtt;
    }

    @objid ("cb58b136-9c9e-4cb4-8a59-96db7b18218c")
    public SmAttribute getSequenceAtt() {
        if (this.sequenceAtt == null) {
        	this.sequenceAtt = this.getAttributeDef("Sequence");
        }
        return this.sequenceAtt;
    }

    @objid ("378ebc8b-4f39-4566-89cc-26b69a88e74c")
    public SmDependency getSignalSignatureDep() {
        if (this.signalSignatureDep == null) {
        	this.signalSignatureDep = this.getDependencyDef("SignalSignature");
        }
        return this.signalSignatureDep;
    }

    @objid ("15e89cb8-ef61-47d2-9715-0fa1cf05b0cc")
    public SmDependency getReceiveEventDep() {
        if (this.receiveEventDep == null) {
        	this.receiveEventDep = this.getDependencyDef("ReceiveEvent");
        }
        return this.receiveEventDep;
    }

    @objid ("d09dc762-a0b5-4c4d-94d5-9134498ab2d9")
    public SmDependency getSendEventDep() {
        if (this.sendEventDep == null) {
        	this.sendEventDep = this.getDependencyDef("SendEvent");
        }
        return this.sendEventDep;
    }

    @objid ("acb29851-8fca-46fd-98e1-a85ada0d3ea1")
    public SmDependency getInvokedDep() {
        if (this.invokedDep == null) {
        	this.invokedDep = this.getDependencyDef("Invoked");
        }
        return this.invokedDep;
    }

    @objid ("0fd91135-e51e-482c-a864-2fd15d4fba2a")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("91f2ff9f-3d0b-4a53-b107-bdf128bb0132")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("3696f803-b9db-470b-9da9-0a35b59ad657")
    private static class MessageObjectFactory implements ISmObjectFactory {
        @objid ("be61eb22-3504-493d-8253-749ada628b84")
        private MessageSmClass smClass;

        @objid ("d0ee593f-0bc6-49c8-98ed-0a2b53aad389")
        public MessageObjectFactory(MessageSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ed1f9357-e16b-4ea1-83e8-60e7eea0fcc9")
        @Override
        public ISmObjectData createData() {
            return new MessageData(this.smClass);
        }

        @objid ("7ff519f5-67ad-4a57-b7cb-371e9d93c885")
        @Override
        public SmObjectImpl createImpl() {
            return new MessageImpl();
        }

    }

    @objid ("5052c024-ba43-4fd0-b16e-99ebc8f7dbde")
    public static class ArgumentSmAttribute extends SmAttribute {
        @objid ("0b92f98e-e688-4181-8c09-af16f8dfa997")
        public Object getValue(ISmObjectData data) {
            return ((MessageData) data).mArgument;
        }

        @objid ("58a80730-27fd-4c0b-872d-a5ee9c8fd103")
        public void setValue(ISmObjectData data, Object value) {
            ((MessageData) data).mArgument = value;
        }

    }

    @objid ("046163f3-b249-4bab-b3aa-7c4bc68123fa")
    public static class KindOfMessageSmAttribute extends SmAttribute {
        @objid ("bc5f53ab-8128-4f0f-8810-c99d7569f584")
        public Object getValue(ISmObjectData data) {
            return ((MessageData) data).mKindOfMessage;
        }

        @objid ("13e293ea-3b62-4819-8b46-efd525a921ad")
        public void setValue(ISmObjectData data, Object value) {
            ((MessageData) data).mKindOfMessage = value;
        }

    }

    @objid ("245b5f09-ec95-44d3-b00f-656fd8bf4a67")
    public static class SortOfMessageSmAttribute extends SmAttribute {
        @objid ("7f227c37-136d-4b30-a753-723cdd52dc77")
        public Object getValue(ISmObjectData data) {
            return ((MessageData) data).mSortOfMessage;
        }

        @objid ("93c383c5-1690-47d4-a76f-56dc21c0c0a6")
        public void setValue(ISmObjectData data, Object value) {
            ((MessageData) data).mSortOfMessage = value;
        }

    }

    @objid ("bdd47aa4-244f-43ab-b009-f3ee7ca050c1")
    public static class SignalSignatureSmDependency extends SmSingleDependency {
        @objid ("b31998f9-9c33-44ae-b440-311cccdcfeaf")
        private SmDependency symetricDep;

        @objid ("2bea39c8-ee09-4c9b-bcd7-e0a22a8c5004")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageData) data).mSignalSignature;
        }

        @objid ("4af69c4c-c000-4307-876f-00ae636e07ca")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageData) data).mSignalSignature = value;
        }

        @objid ("d0828c85-43d0-4bf1-8945-cd0cab7a8ee7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getUsageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("05bcd8f7-d8e7-433e-ae8e-a2f2877378f3")
    public static class ReceiveEventSmDependency extends SmSingleDependency {
        @objid ("e856179d-3292-4dbf-bed8-9c0bfdc2f541")
        private SmDependency symetricDep;

        @objid ("3b2f5719-4699-4c9c-b7cf-5a1e3fa93a23")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageData) data).mReceiveEvent;
        }

        @objid ("b78f3367-66d2-4209-9e80-ba49221b6a2c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageData) data).mReceiveEvent = value;
        }

        @objid ("8321fcd3-6e80-4c63-88e6-fd94f6ef2df7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageEndSmClass)this.getTarget()).getReceivedMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ee32163c-de34-40a9-9455-43ed42421db9")
    public static class SendEventSmDependency extends SmSingleDependency {
        @objid ("e72f0f7f-9885-4f1f-a32c-4b52d862aff1")
        private SmDependency symetricDep;

        @objid ("456aa2d7-d354-44e3-930c-df655f2f2884")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageData) data).mSendEvent;
        }

        @objid ("76d65ce4-efa2-4876-b57b-a92168206f61")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageData) data).mSendEvent = value;
        }

        @objid ("8f995105-7f7d-4b69-93a5-da2b9b5b3bf1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageEndSmClass)this.getTarget()).getSentMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8e2e3b7c-0ae1-47ac-afde-a2f5e1e026b3")
    public static class InvokedSmDependency extends SmSingleDependency {
        @objid ("fff4e0a9-b831-4630-bc01-2d1ddf5858e2")
        private SmDependency symetricDep;

        @objid ("33f6b7aa-8759-4917-8d19-a2a0396eafbd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageData) data).mInvoked;
        }

        @objid ("5e9fb30b-943c-44c3-b34f-a046f18fde26")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageData) data).mInvoked = value;
        }

        @objid ("6852dc30-3ece-4e1c-905a-78118449a9d1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getUsageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("65ddd40a-0925-48ca-a91b-a1533d8da046")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("4e7b770c-def1-41e6-a46e-09ca5609f338")
        private SmDependency symetricDep;

        @objid ("cd7908b6-b632-47f6-ba81-d30e31420eae")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MessageData)data).mRealizedInformationFlow != null)? ((MessageData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("b2df2dde-68c0-4c6b-8c6b-2143c4c320b4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MessageData) data).mRealizedInformationFlow = values;
        }

        @objid ("00e2be58-d588-4115-a651-4b856ea28bcc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bf6df04d-0fcd-408f-ad45-f3813a321e62")
    public static class SequenceSmAttribute extends SmAttribute {
        @objid ("c9249527-7ac2-434d-81e3-a5117f8ec60a")
        public Object getValue(ISmObjectData data) {
            return ((MessageData) data).mSequence;
        }

        @objid ("3f79f730-ec44-4a67-be19-7df291afba05")
        public void setValue(ISmObjectData data, Object value) {
            ((MessageData) data).mSequence = value;
        }

    }

}
