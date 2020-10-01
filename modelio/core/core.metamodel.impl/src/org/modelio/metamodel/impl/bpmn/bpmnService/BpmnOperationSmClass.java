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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnReceiveTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSendTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnServiceTaskSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnInterfaceSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationData;
import org.modelio.metamodel.impl.bpmn.events.BpmnMessageEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
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

@objid ("0fe74703-9e94-4efe-a402-d2b779a10567")
public class BpmnOperationSmClass extends BpmnBaseElementSmClass {
    @objid ("4b14b9c8-74cc-4790-a8be-2f5cda072866")
    private SmDependency senderDep;

    @objid ("fd35daeb-cc5c-4a7f-b137-81ead1539be8")
    private SmDependency inMessageRefDep;

    @objid ("6a6f3cc4-5cce-455e-b691-381a12df5fff")
    private SmDependency callerDep;

    @objid ("65d288ae-b4df-4a9c-ba59-e462a2f4093f")
    private SmDependency outMessageRefDep;

    @objid ("e30eb425-ec5c-44b6-a0af-5bc696683cc6")
    private SmDependency eventDefinitionDep;

    @objid ("c4df6efd-da95-4e7a-8ca6-bec3c46af0a8")
    private SmDependency bpmnInterfaceRefDep;

    @objid ("9f8fa2ae-60cc-44aa-8fc8-32376a82890f")
    private SmDependency receiverDep;

    @objid ("163e8115-b2b5-498c-9803-14d9338eebee")
    public BpmnOperationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e208375d-2dfe-4961-a834-3e6b051f2615")
    @Override
    public String getName() {
        return "BpmnOperation";
    }

    @objid ("ed08d754-6985-4869-9611-efa3c71051b8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("040bb910-56a0-4898-9f5c-a28d3a5eb4b8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnOperation.class;
    }

    @objid ("06291c7d-b318-4a6e-bb47-f49050a7a908")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3496fa02-6949-4907-8bf9-a73d0ec19233")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0b7024b5-6c91-439c-9589-52473405663c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnOperationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.senderDep = new SenderSmDependency();
        this.senderDep.init("Sender", this, metamodel.getMClass(BpmnSendTask.MQNAME), 0, -1 );
        registerDependency(this.senderDep);
        
        this.inMessageRefDep = new InMessageRefSmDependency();
        this.inMessageRefDep.init("InMessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.inMessageRefDep);
        
        this.callerDep = new CallerSmDependency();
        this.callerDep.init("Caller", this, metamodel.getMClass(BpmnServiceTask.MQNAME), 0, -1 );
        registerDependency(this.callerDep);
        
        this.outMessageRefDep = new OutMessageRefSmDependency();
        this.outMessageRefDep.init("OutMessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.outMessageRefDep);
        
        this.eventDefinitionDep = new EventDefinitionSmDependency();
        this.eventDefinitionDep.init("EventDefinition", this, metamodel.getMClass(BpmnMessageEventDefinition.MQNAME), 0, -1 );
        registerDependency(this.eventDefinitionDep);
        
        this.bpmnInterfaceRefDep = new BpmnInterfaceRefSmDependency();
        this.bpmnInterfaceRefDep.init("BpmnInterfaceRef", this, metamodel.getMClass(BpmnInterface.MQNAME), 1, 1 );
        registerDependency(this.bpmnInterfaceRefDep);
        
        this.receiverDep = new ReceiverSmDependency();
        this.receiverDep.init("Receiver", this, metamodel.getMClass(BpmnReceiveTask.MQNAME), 0, -1 );
        registerDependency(this.receiverDep);
    }

    @objid ("1cbec494-044c-4854-ade1-869e9996c165")
    public SmDependency getSenderDep() {
        if (this.senderDep == null) {
        	this.senderDep = this.getDependencyDef("Sender");
        }
        return this.senderDep;
    }

    @objid ("4b593489-9e6a-467e-b055-3356c774925a")
    public SmDependency getInMessageRefDep() {
        if (this.inMessageRefDep == null) {
        	this.inMessageRefDep = this.getDependencyDef("InMessageRef");
        }
        return this.inMessageRefDep;
    }

    @objid ("f7337eba-39f3-40bb-a952-c4f8b52d0204")
    public SmDependency getCallerDep() {
        if (this.callerDep == null) {
        	this.callerDep = this.getDependencyDef("Caller");
        }
        return this.callerDep;
    }

    @objid ("938447df-6369-4510-ac3f-a2c86d6a2bf8")
    public SmDependency getOutMessageRefDep() {
        if (this.outMessageRefDep == null) {
        	this.outMessageRefDep = this.getDependencyDef("OutMessageRef");
        }
        return this.outMessageRefDep;
    }

    @objid ("ee8bdee3-dd03-4df7-af61-82e537de7ca8")
    public SmDependency getEventDefinitionDep() {
        if (this.eventDefinitionDep == null) {
        	this.eventDefinitionDep = this.getDependencyDef("EventDefinition");
        }
        return this.eventDefinitionDep;
    }

    @objid ("5368138c-d127-4299-bea6-5e2ecc299338")
    public SmDependency getBpmnInterfaceRefDep() {
        if (this.bpmnInterfaceRefDep == null) {
        	this.bpmnInterfaceRefDep = this.getDependencyDef("BpmnInterfaceRef");
        }
        return this.bpmnInterfaceRefDep;
    }

    @objid ("8e624010-5ea7-49f8-b9cb-e16736f3265b")
    public SmDependency getReceiverDep() {
        if (this.receiverDep == null) {
        	this.receiverDep = this.getDependencyDef("Receiver");
        }
        return this.receiverDep;
    }

    @objid ("a3b08f42-4691-45d1-86b2-0b062dcbda6e")
    private static class BpmnOperationObjectFactory implements ISmObjectFactory {
        @objid ("b3495cea-d263-442b-ab9e-c462b27ac149")
        private BpmnOperationSmClass smClass;

        @objid ("4c83d50d-1dff-439a-b44b-7692a7907acf")
        public BpmnOperationObjectFactory(BpmnOperationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1cc77efb-b396-49ff-b2b1-2be7bc84d861")
        @Override
        public ISmObjectData createData() {
            return new BpmnOperationData(this.smClass);
        }

        @objid ("27b5555e-fa1c-4094-8dc6-041cab6448ba")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnOperationImpl();
        }

    }

    @objid ("964ed99a-b6b7-49a1-85f8-8ec358a07ba9")
    public static class SenderSmDependency extends SmMultipleDependency {
        @objid ("52bc518b-2d97-436d-a8b7-979eeba6b353")
        private SmDependency symetricDep;

        @objid ("c5ff0a37-c818-4f7b-b65b-65a452ea38d4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnOperationData)data).mSender != null)? ((BpmnOperationData)data).mSender:SmMultipleDependency.EMPTY;
        }

        @objid ("2a141566-033c-4903-98d5-066ac951f51f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnOperationData) data).mSender = values;
        }

        @objid ("357ab883-6297-4d4a-8052-532f962d6336")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSendTaskSmClass)this.getTarget()).getOperationRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2cd47679-a8a1-4641-be66-af8e7a8196c5")
    public static class InMessageRefSmDependency extends SmSingleDependency {
        @objid ("1b436d00-cee8-4d36-a983-d6c4db7486f8")
        private SmDependency symetricDep;

        @objid ("c94e9b15-c489-423f-937a-c617a714adc8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnOperationData) data).mInMessageRef;
        }

        @objid ("7c351e67-f771-466a-8def-c1517769d282")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnOperationData) data).mInMessageRef = value;
        }

        @objid ("4303a501-8738-4cb4-85b4-ffcbe95ac2ef")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getInputMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("59dafd44-783f-4ae7-a955-c3df90117ad0")
    public static class CallerSmDependency extends SmMultipleDependency {
        @objid ("a5ad59e8-8c23-4584-bf24-5ac5c2699b07")
        private SmDependency symetricDep;

        @objid ("9fccc023-eb72-4f12-89d7-c7b81ce69932")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnOperationData)data).mCaller != null)? ((BpmnOperationData)data).mCaller:SmMultipleDependency.EMPTY;
        }

        @objid ("697333f1-96fb-4fe5-a538-cb1794ca3dd4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnOperationData) data).mCaller = values;
        }

        @objid ("98bb676e-8589-4704-b3af-8ca34c7b0f21")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnServiceTaskSmClass)this.getTarget()).getOperationRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cb013d46-aef0-44b4-b15b-508ef376b9f5")
    public static class OutMessageRefSmDependency extends SmSingleDependency {
        @objid ("751c650c-3add-4817-8296-4de510473998")
        private SmDependency symetricDep;

        @objid ("e6d083eb-ef7d-4b6a-94c2-4a0c586595cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnOperationData) data).mOutMessageRef;
        }

        @objid ("cdb6ab88-783d-47a0-9990-1f46bb0d6e5f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnOperationData) data).mOutMessageRef = value;
        }

        @objid ("5130b0ac-71f7-43c5-8473-965235344da7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getOutputMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bab64fcb-afca-4b31-b245-9c946cbaf4b6")
    public static class EventDefinitionSmDependency extends SmMultipleDependency {
        @objid ("97355b5b-d2e9-4e39-be4e-80ed513211fd")
        private SmDependency symetricDep;

        @objid ("fdf51a27-0f50-41ff-9b38-4727654488c2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnOperationData)data).mEventDefinition != null)? ((BpmnOperationData)data).mEventDefinition:SmMultipleDependency.EMPTY;
        }

        @objid ("063f8567-10a1-420d-9cc0-784ce8752b61")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnOperationData) data).mEventDefinition = values;
        }

        @objid ("903f3712-ef20-4277-93da-c8ec11f907c4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageEventDefinitionSmClass)this.getTarget()).getOperationRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a07f929a-6fe6-443a-a888-a66808c08a44")
    public static class BpmnInterfaceRefSmDependency extends SmSingleDependency {
        @objid ("bf11f962-2a66-4d40-9917-538f4c7673b3")
        private SmDependency symetricDep;

        @objid ("9bee1cf5-c935-4d61-ad17-daea8a4373ff")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnOperationData) data).mBpmnInterfaceRef;
        }

        @objid ("9d5060b7-0547-4d50-9d65-02712a430ab0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnOperationData) data).mBpmnInterfaceRef = value;
        }

        @objid ("8484e99c-8a3c-4dee-a47c-5b2f753ededa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnInterfaceSmClass)this.getTarget()).getOperationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("62fd6533-3b2e-43fc-a755-23128836df25")
    public static class ReceiverSmDependency extends SmMultipleDependency {
        @objid ("70d5cc03-178e-4751-ad84-d6f3dcbff83d")
        private SmDependency symetricDep;

        @objid ("54dae12c-b774-4730-9004-8252d0d72cc1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnOperationData)data).mReceiver != null)? ((BpmnOperationData)data).mReceiver:SmMultipleDependency.EMPTY;
        }

        @objid ("11489911-14b1-4705-af7b-dccfd19c38cb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnOperationData) data).mReceiver = values;
        }

        @objid ("e948bb75-5438-4cf9-b9b4-c5a0e99683a8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnReceiveTaskSmClass)this.getTarget()).getOperationRefDep();
            }
            return this.symetricDep;
        }

    }

}
