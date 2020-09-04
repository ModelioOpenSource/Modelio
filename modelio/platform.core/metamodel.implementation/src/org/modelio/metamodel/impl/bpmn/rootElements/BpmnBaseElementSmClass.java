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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1c593681-5de5-4212-904d-ddce18beddaf")
public class BpmnBaseElementSmClass extends ModelElementSmClass {
    @objid ("f0fb7ad3-a560-42ed-a534-37b07f7043c5")
    private SmDependency outgoingAssocDep;

    @objid ("72bae4de-2744-435d-bbbb-45552db7c9d4")
    private SmDependency incomingAssocDep;

    @objid ("109eb0c2-b8d2-4bb9-8a3d-3244a01ecfc3")
    private SmDependency incomingFlowDep;

    @objid ("448677bb-2554-4c8e-aa6b-4b124f364593")
    private SmDependency outgoingFlowDep;

    @objid ("f38c2f5c-62aa-4bdc-928a-277537b2ff70")
    private SmDependency partitionedLaneRefsDep;

    @objid ("c86b7bb3-a759-4856-a24c-529a69a9c60b")
    public BpmnBaseElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("acd51a84-9e93-484b-8ac9-dfd5903576b8")
    @Override
    public String getName() {
        return "BpmnBaseElement";
    }

    @objid ("87f8e346-461e-40d3-9019-a690f93af986")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8c69f4a9-bc70-4d26-a516-9db2fad34f86")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnBaseElement.class;
    }

    @objid ("223c87c6-a280-4d17-bda1-b0c4b927d42f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8931a60e-8d06-440e-bd0e-e13eab903ed2")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("03d78da4-5aac-45c6-bd47-47029038ad7e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new BpmnBaseElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.outgoingAssocDep = new OutgoingAssocSmDependency();
        this.outgoingAssocDep.init("OutgoingAssoc", this, metamodel.getMClass(BpmnAssociation.MQNAME), 0, -1 );
        registerDependency(this.outgoingAssocDep);
        
        this.incomingAssocDep = new IncomingAssocSmDependency();
        this.incomingAssocDep.init("IncomingAssoc", this, metamodel.getMClass(BpmnAssociation.MQNAME), 0, -1 );
        registerDependency(this.incomingAssocDep);
        
        this.incomingFlowDep = new IncomingFlowSmDependency();
        this.incomingFlowDep.init("IncomingFlow", this, metamodel.getMClass(BpmnMessageFlow.MQNAME), 0, -1 );
        registerDependency(this.incomingFlowDep);
        
        this.outgoingFlowDep = new OutgoingFlowSmDependency();
        this.outgoingFlowDep.init("OutgoingFlow", this, metamodel.getMClass(BpmnMessageFlow.MQNAME), 0, -1 );
        registerDependency(this.outgoingFlowDep);
        
        this.partitionedLaneRefsDep = new PartitionedLaneRefsSmDependency();
        this.partitionedLaneRefsDep.init("PartitionedLaneRefs", this, metamodel.getMClass(BpmnLane.MQNAME), 0, -1 );
        registerDependency(this.partitionedLaneRefsDep);
    }

    @objid ("a3b4ff95-12ba-41c2-aa44-f94730922bf5")
    public SmDependency getOutgoingAssocDep() {
        if (this.outgoingAssocDep == null) {
        	this.outgoingAssocDep = this.getDependencyDef("OutgoingAssoc");
        }
        return this.outgoingAssocDep;
    }

    @objid ("aab70abc-913f-4cb5-9eea-a6f3888cb35a")
    public SmDependency getIncomingAssocDep() {
        if (this.incomingAssocDep == null) {
        	this.incomingAssocDep = this.getDependencyDef("IncomingAssoc");
        }
        return this.incomingAssocDep;
    }

    @objid ("2e8544cd-9c77-4ec2-9cdc-19bf4b608f22")
    public SmDependency getIncomingFlowDep() {
        if (this.incomingFlowDep == null) {
        	this.incomingFlowDep = this.getDependencyDef("IncomingFlow");
        }
        return this.incomingFlowDep;
    }

    @objid ("424af5b5-e680-4ad1-b25e-b8cac37bb9f8")
    public SmDependency getOutgoingFlowDep() {
        if (this.outgoingFlowDep == null) {
        	this.outgoingFlowDep = this.getDependencyDef("OutgoingFlow");
        }
        return this.outgoingFlowDep;
    }

    @objid ("7956c504-e82a-401d-a01b-a5c15e20f8be")
    public SmDependency getPartitionedLaneRefsDep() {
        if (this.partitionedLaneRefsDep == null) {
        	this.partitionedLaneRefsDep = this.getDependencyDef("PartitionedLaneRefs");
        }
        return this.partitionedLaneRefsDep;
    }

    @objid ("edb4462d-ec32-4450-a18e-768ff71e38e5")
    private static class BpmnBaseElementObjectFactory implements ISmObjectFactory {
        @objid ("6a1df84e-26a7-4240-a7f1-6fda82572095")
        private BpmnBaseElementSmClass smClass;

        @objid ("c2bb5aa5-090f-4868-b82b-54be72d48361")
        public BpmnBaseElementObjectFactory(BpmnBaseElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e7584eed-6518-4f0f-adfd-2c3e7741a5b7")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("851b7150-6725-4711-adce-ff14302415f5")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("f3906013-4c31-48e7-9e19-6540108e8126")
    public static class OutgoingAssocSmDependency extends SmMultipleDependency {
        @objid ("a11c208c-dc6e-4d33-a43c-01eb93ce3f21")
        private SmDependency symetricDep;

        @objid ("89e4b224-4ca1-4b83-a55c-cc5286496a9f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnBaseElementData)data).mOutgoingAssoc != null)? ((BpmnBaseElementData)data).mOutgoingAssoc:SmMultipleDependency.EMPTY;
        }

        @objid ("ee3237ea-0164-45d5-a24d-e0a06da3bacb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnBaseElementData) data).mOutgoingAssoc = values;
        }

        @objid ("b0fe7d92-eaf7-476d-905e-03e5a3f6d6b2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnAssociationSmClass)this.getTarget()).getSourceRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("60f47c8d-0013-4449-8901-361802718c09")
    public static class IncomingAssocSmDependency extends SmMultipleDependency {
        @objid ("94cda712-75c3-44d2-8702-1085d083dd2e")
        private SmDependency symetricDep;

        @objid ("f7499094-5bd8-40e6-bfdb-f74b9236fb2c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnBaseElementData)data).mIncomingAssoc != null)? ((BpmnBaseElementData)data).mIncomingAssoc:SmMultipleDependency.EMPTY;
        }

        @objid ("69fcae6b-6c94-4fdd-8d4d-8b712ab47d2c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnBaseElementData) data).mIncomingAssoc = values;
        }

        @objid ("44b0a56a-5bd3-499e-ab7d-b2d2caec7e76")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnAssociationSmClass)this.getTarget()).getTargetRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("220d558d-0e1d-462e-9034-c66fc29104b4")
    public static class IncomingFlowSmDependency extends SmMultipleDependency {
        @objid ("4affcfd6-1ce7-4c98-82b1-3cc2a39b9f10")
        private SmDependency symetricDep;

        @objid ("9b0f7fd4-d45e-43dd-8afd-fafa611e5bd7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnBaseElementData)data).mIncomingFlow != null)? ((BpmnBaseElementData)data).mIncomingFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("6fa58d0c-3a43-4ea7-9354-f5e4d8b7b287")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnBaseElementData) data).mIncomingFlow = values;
        }

        @objid ("2c6c8956-3ece-471f-a640-9088ea1ba627")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageFlowSmClass)this.getTarget()).getTargetRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bc869713-1441-485a-9cec-34cd71f54395")
    public static class OutgoingFlowSmDependency extends SmMultipleDependency {
        @objid ("bb7c6cfd-f4cc-4916-ad52-73f70ce1a3df")
        private SmDependency symetricDep;

        @objid ("d704787d-73aa-45fc-805f-f497f5814193")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnBaseElementData)data).mOutgoingFlow != null)? ((BpmnBaseElementData)data).mOutgoingFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("64a249fc-7b99-4b70-bc87-0f710dd1456a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnBaseElementData) data).mOutgoingFlow = values;
        }

        @objid ("1ae6dd6a-44ff-44ee-9215-bb50b7ef162f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageFlowSmClass)this.getTarget()).getSourceRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c7b88133-046a-4e5e-96ed-f017c1c89c03")
    public static class PartitionedLaneRefsSmDependency extends SmMultipleDependency {
        @objid ("8b352260-4a4f-44dc-970e-70a84f627caf")
        private SmDependency symetricDep;

        @objid ("c69fb231-9eb2-4489-8aed-8d2ad537012c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnBaseElementData)data).mPartitionedLaneRefs != null)? ((BpmnBaseElementData)data).mPartitionedLaneRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("3956619a-2357-4e3f-88df-b3ca89d85060")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnBaseElementData) data).mPartitionedLaneRefs = values;
        }

        @objid ("dfb820bb-ede1-4ca0-8c5c-6b70827344e1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSmClass)this.getTarget()).getBpmnPartitionElementRefDep();
            }
            return this.symetricDep;
        }

    }

}
