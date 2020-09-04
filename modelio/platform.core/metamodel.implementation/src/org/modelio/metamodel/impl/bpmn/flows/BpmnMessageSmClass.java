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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnReceiveTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSendTaskSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnMessageEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageData;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("67a60b2f-d45e-41b3-b55c-d10466cf2bbb")
public class BpmnMessageSmClass extends BpmnSharedElementSmClass {
    @objid ("efdd44c2-590c-40e0-a2cc-e26cc225408d")
    private SmDependency outputMessageDep;

    @objid ("c6da3ba0-af76-45b8-a338-178e84d7f955")
    private SmDependency itemRefDep;

    @objid ("d8c1ba34-97a5-43d7-bf64-be9097eeebc5")
    private SmDependency eventDefinitionDep;

    @objid ("9ae3b01b-81f8-40a5-a749-40f9e42dc3a1")
    private SmDependency senderDep;

    @objid ("ec5ded7e-5584-4b30-bffb-3bc0d520e237")
    private SmDependency inputMessageDep;

    @objid ("120add99-fe30-4dad-b2ec-77ba4a4468e5")
    private SmDependency receiverDep;

    @objid ("368402e1-712b-4b4c-9b36-ea09d6186e0e")
    private SmDependency messageFlowDep;

    @objid ("522f176d-35b4-42c1-8b1d-c1e5d6b9fb6f")
    private SmDependency collaborationDep;

    @objid ("50449c90-b8e4-4555-bbe5-70214e22068c")
    public BpmnMessageSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7a0c800e-6940-43a1-8626-d9583f183d2d")
    @Override
    public String getName() {
        return "BpmnMessage";
    }

    @objid ("bcdc8674-d00d-48a9-94a1-e24767827745")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7672f111-a84b-4304-91e0-624175f9c0c7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnMessage.class;
    }

    @objid ("bebe285d-e608-4f3a-a8db-105aef03ff63")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0fb34fc1-4a2c-45ad-aee4-0b242f1b6db1")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("32d2cf2f-b50e-4770-a534-545b4b018455")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSharedElement.MQNAME);
        this.registerFactory(new BpmnMessageObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.outputMessageDep = new OutputMessageSmDependency();
        this.outputMessageDep.init("OutputMessage", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, -1 );
        registerDependency(this.outputMessageDep);
        
        this.itemRefDep = new ItemRefSmDependency();
        this.itemRefDep.init("ItemRef", this, metamodel.getMClass(BpmnItemDefinition.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.itemRefDep);
        
        this.eventDefinitionDep = new EventDefinitionSmDependency();
        this.eventDefinitionDep.init("EventDefinition", this, metamodel.getMClass(BpmnMessageEventDefinition.MQNAME), 0, -1 );
        registerDependency(this.eventDefinitionDep);
        
        this.senderDep = new SenderSmDependency();
        this.senderDep.init("Sender", this, metamodel.getMClass(BpmnSendTask.MQNAME), 0, -1 );
        registerDependency(this.senderDep);
        
        this.inputMessageDep = new InputMessageSmDependency();
        this.inputMessageDep.init("InputMessage", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, -1 );
        registerDependency(this.inputMessageDep);
        
        this.receiverDep = new ReceiverSmDependency();
        this.receiverDep.init("Receiver", this, metamodel.getMClass(BpmnReceiveTask.MQNAME), 0, -1 );
        registerDependency(this.receiverDep);
        
        this.messageFlowDep = new MessageFlowSmDependency();
        this.messageFlowDep.init("MessageFlow", this, metamodel.getMClass(BpmnMessageFlow.MQNAME), 0, -1 );
        registerDependency(this.messageFlowDep);
        
        this.collaborationDep = new CollaborationSmDependency();
        this.collaborationDep.init("Collaboration", this, metamodel.getMClass(BpmnCollaboration.MQNAME), 0, 1 );
        registerDependency(this.collaborationDep);
    }

    @objid ("495c2f16-00d1-4ab7-ac32-48ae5f8f5bb8")
    public SmDependency getOutputMessageDep() {
        if (this.outputMessageDep == null) {
        	this.outputMessageDep = this.getDependencyDef("OutputMessage");
        }
        return this.outputMessageDep;
    }

    @objid ("43805f77-136c-42eb-a051-f1830cbac839")
    public SmDependency getItemRefDep() {
        if (this.itemRefDep == null) {
        	this.itemRefDep = this.getDependencyDef("ItemRef");
        }
        return this.itemRefDep;
    }

    @objid ("80c31f5b-aa1a-4814-be3a-6a3e9461d15d")
    public SmDependency getEventDefinitionDep() {
        if (this.eventDefinitionDep == null) {
        	this.eventDefinitionDep = this.getDependencyDef("EventDefinition");
        }
        return this.eventDefinitionDep;
    }

    @objid ("5d779f2d-80a0-4d22-81ee-07b80e9cc6d8")
    public SmDependency getSenderDep() {
        if (this.senderDep == null) {
        	this.senderDep = this.getDependencyDef("Sender");
        }
        return this.senderDep;
    }

    @objid ("b7eefa17-496f-4ecf-a188-18bb4cd1c8b9")
    public SmDependency getInputMessageDep() {
        if (this.inputMessageDep == null) {
        	this.inputMessageDep = this.getDependencyDef("InputMessage");
        }
        return this.inputMessageDep;
    }

    @objid ("5391c73d-bc9b-4ab3-be85-0a94b33435f2")
    public SmDependency getReceiverDep() {
        if (this.receiverDep == null) {
        	this.receiverDep = this.getDependencyDef("Receiver");
        }
        return this.receiverDep;
    }

    @objid ("ebd1f436-97d5-4d9a-9298-e2616efe14fa")
    public SmDependency getMessageFlowDep() {
        if (this.messageFlowDep == null) {
        	this.messageFlowDep = this.getDependencyDef("MessageFlow");
        }
        return this.messageFlowDep;
    }

    @objid ("1cd0806d-5174-465c-9751-2787aeeab68c")
    public SmDependency getCollaborationDep() {
        if (this.collaborationDep == null) {
        	this.collaborationDep = this.getDependencyDef("Collaboration");
        }
        return this.collaborationDep;
    }

    @objid ("d1808b14-b5a0-487f-b7fe-0e4e72101cc6")
    private static class BpmnMessageObjectFactory implements ISmObjectFactory {
        @objid ("51ac4e45-8ca9-40d8-9138-0d2c8a728b5d")
        private BpmnMessageSmClass smClass;

        @objid ("6cfe4282-af6e-4725-96a1-f6d749742541")
        public BpmnMessageObjectFactory(BpmnMessageSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d0ce3733-4177-4627-b3e1-1ffcd4f2f64f")
        @Override
        public ISmObjectData createData() {
            return new BpmnMessageData(this.smClass);
        }

        @objid ("557102ba-bf36-44c5-a83c-383dd1119908")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnMessageImpl();
        }

    }

    @objid ("f7cb3830-44bf-4bdb-9a40-9ee190eb2cf7")
    public static class OutputMessageSmDependency extends SmMultipleDependency {
        @objid ("c31b94d1-9761-40ee-bb86-a5fd9af33c86")
        private SmDependency symetricDep;

        @objid ("8dcecce1-ce58-40af-8cd2-bbadac0ec48a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mOutputMessage != null)? ((BpmnMessageData)data).mOutputMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("9b77481e-336b-4fa4-b176-9a59fe8fe969")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mOutputMessage = values;
        }

        @objid ("fd77b43e-09a4-4791-a892-08e4396575c4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getOutMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c974f496-cb5d-484c-87f8-31b67ad02d71")
    public static class ItemRefSmDependency extends SmSingleDependency {
        @objid ("a064980a-d9eb-4ccb-9f9a-157207a803c7")
        private SmDependency symetricDep;

        @objid ("59be62cc-872a-4b91-b233-f04bb4a72471")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageData) data).mItemRef;
        }

        @objid ("c0810c2e-b06c-40e0-8770-b19f26347238")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageData) data).mItemRef = value;
        }

        @objid ("259cf8f6-68fa-4a2c-8d33-b098f8385758")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemDefinitionSmClass)this.getTarget()).getTypedMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("14a3c48d-441a-4297-9d7e-6a9971010ae4")
    public static class EventDefinitionSmDependency extends SmMultipleDependency {
        @objid ("bead19c8-557a-4d09-9506-0a7bbd9638a0")
        private SmDependency symetricDep;

        @objid ("5ad1717a-369f-4c38-8147-d60f73f13db6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mEventDefinition != null)? ((BpmnMessageData)data).mEventDefinition:SmMultipleDependency.EMPTY;
        }

        @objid ("02b12ed8-00c3-48b0-b1b5-2491b3b126c5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mEventDefinition = values;
        }

        @objid ("8fbb215f-162b-4661-9dea-c7019eb5db9e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageEventDefinitionSmClass)this.getTarget()).getMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5ba42f34-fd55-45ef-b8b7-927422618153")
    public static class SenderSmDependency extends SmMultipleDependency {
        @objid ("d7261b3a-23f9-4b7b-94c6-91b7458d4ee7")
        private SmDependency symetricDep;

        @objid ("d8310ec9-d588-4ae4-a41b-e3424b11d5b9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mSender != null)? ((BpmnMessageData)data).mSender:SmMultipleDependency.EMPTY;
        }

        @objid ("1816f3ec-9752-41f0-b023-227c1b862eb1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mSender = values;
        }

        @objid ("4fc78977-0812-41f6-9948-3bbea1b29626")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSendTaskSmClass)this.getTarget()).getMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("dc181931-ce43-42e9-ac5f-bc0cc98ba435")
    public static class InputMessageSmDependency extends SmMultipleDependency {
        @objid ("5effaac9-477c-4f36-847e-4c863f963514")
        private SmDependency symetricDep;

        @objid ("670e1755-9d23-41c2-9b09-87c64b9df848")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mInputMessage != null)? ((BpmnMessageData)data).mInputMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("3b4be1c8-4125-4a40-8e84-e112a39cc166")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mInputMessage = values;
        }

        @objid ("5bcac326-5074-4e1a-82ba-b42e9bc304c2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getInMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2dfeeb74-d853-4d5f-9fb9-2caa8f417c6e")
    public static class ReceiverSmDependency extends SmMultipleDependency {
        @objid ("cabd0e7d-8f80-4575-b39b-b2eb3ca3ce37")
        private SmDependency symetricDep;

        @objid ("954d84b8-2384-4675-846b-76168601770c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mReceiver != null)? ((BpmnMessageData)data).mReceiver:SmMultipleDependency.EMPTY;
        }

        @objid ("01032765-7ee0-450d-9d11-f5805fbc43f8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mReceiver = values;
        }

        @objid ("4eb3558b-c8b3-4119-9f83-441e44a067b6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnReceiveTaskSmClass)this.getTarget()).getMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ecef9b09-01e6-41fc-9305-dc7b6ea772b8")
    public static class MessageFlowSmDependency extends SmMultipleDependency {
        @objid ("5244649b-c981-4c92-8c77-21a270195d37")
        private SmDependency symetricDep;

        @objid ("2bc97947-3f2d-42ea-a71c-a6279b6503ea")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageData)data).mMessageFlow != null)? ((BpmnMessageData)data).mMessageFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("2d97f64c-b164-4257-b34c-d3150e68b52e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageData) data).mMessageFlow = values;
        }

        @objid ("74dcaaa4-004c-4df2-86ca-d5c90e15bc47")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageFlowSmClass)this.getTarget()).getMessageRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("19a7adc8-416b-4030-80da-e7b6bbe7200b")
    public static class CollaborationSmDependency extends SmSingleDependency {
        @objid ("e522c320-e456-4159-adc8-286e1c0357dc")
        private SmDependency symetricDep;

        @objid ("b270fad1-0147-4e40-b71f-07cf97d38064")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageData) data).mCollaboration;
        }

        @objid ("195fac10-318b-4f41-b0cb-196d32b2b07a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageData) data).mCollaboration = value;
        }

        @objid ("70c0756b-e318-41f6-bc73-aca40d8cf5af")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCollaborationSmClass)this.getTarget()).getMessagesDep();
            }
            return this.symetricDep;
        }

    }

}
