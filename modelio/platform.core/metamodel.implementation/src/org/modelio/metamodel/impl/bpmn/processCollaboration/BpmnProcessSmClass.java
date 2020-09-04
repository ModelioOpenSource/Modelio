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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcessType;
import org.modelio.metamodel.bpmn.processCollaboration.OptionalBoolean;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessData;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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

@objid ("4d8f32f6-a792-47da-86c4-b3a00a3a56ce")
public class BpmnProcessSmClass extends BehaviorSmClass {
    @objid ("865cacfa-e062-41df-96a5-8016b67946e6")
    private SmAttribute processTypeAtt;

    @objid ("1514437f-32ce-437e-a371-be4aa5f02e7e")
    private SmAttribute isClosedAtt;

    @objid ("eab3fcd3-2d67-4d28-98cc-61926a5a5e10")
    private SmAttribute isExecutableAtt;

    @objid ("c1f079b5-700c-4d07-b92c-a5aeb7615105")
    private SmDependency supportsDep;

    @objid ("6b6b8ad9-660d-42cd-96ee-0341ad0b30ff")
    private SmDependency artifactDep;

    @objid ("abcea710-4458-4639-b594-17eb350f0f0c")
    private SmDependency laneSetDep;

    @objid ("a0a8e162-2694-4fe6-9e07-75e243e73651")
    private SmDependency supportedDep;

    @objid ("454c547a-0c69-4333-98ed-16f4c393a19f")
    private SmDependency participantDep;

    @objid ("2eb04339-7aaa-4e06-94fe-ea5c368fd667")
    private SmDependency flowElementDep;

    @objid ("d1f52072-b350-4ebc-b0d0-875d547215a8")
    private SmDependency resourceDep;

    @objid ("92f58d47-5843-4600-88be-f762bae347dd")
    private SmDependency definitionalCollaborationDep;

    @objid ("dd7cbb34-5276-484b-94d7-16f3611d3a45")
    public BpmnProcessSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("440b27e0-22be-461c-be2c-b3121c5a2284")
    @Override
    public String getName() {
        return "BpmnProcess";
    }

    @objid ("22f87c79-df67-4526-a2e8-4d068aac5b56")
    @Override
    public Version getVersion() {
        return new Version("2.2.0");
    }

    @objid ("89e81ce7-5cdb-4297-bbbc-31f81d88fdab")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnProcess.class;
    }

    @objid ("de8e9b93-441c-4450-b5dd-0b3a162a3990")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("c04cb950-22bf-4974-ab88-eb5db7493303")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dff6600a-18c1-468d-8fbc-1e6bf8d7d2e6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new BpmnProcessObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.processTypeAtt = new ProcessTypeSmAttribute();
        this.processTypeAtt.init("ProcessType", this, BpmnProcessType.class );
        registerAttribute(this.processTypeAtt);
        
        this.isClosedAtt = new IsClosedSmAttribute();
        this.isClosedAtt.init("IsClosed", this, Boolean.class );
        registerAttribute(this.isClosedAtt);
        
        this.isExecutableAtt = new IsExecutableSmAttribute();
        this.isExecutableAtt.init("IsExecutable", this, OptionalBoolean.class );
        registerAttribute(this.isExecutableAtt);
        
        
        // Initialize and register the SmDependency
        this.supportsDep = new SupportsSmDependency();
        this.supportsDep.init("Supports", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.supportsDep);
        
        this.artifactDep = new ArtifactSmDependency();
        this.artifactDep.init("Artifact", this, metamodel.getMClass(BpmnArtifact.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.artifactDep);
        
        this.laneSetDep = new LaneSetSmDependency();
        this.laneSetDep.init("LaneSet", this, metamodel.getMClass(BpmnLaneSet.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.laneSetDep);
        
        this.supportedDep = new SupportedSmDependency();
        this.supportedDep.init("Supported", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, -1 );
        registerDependency(this.supportedDep);
        
        this.participantDep = new ParticipantSmDependency();
        this.participantDep.init("Participant", this, metamodel.getMClass(BpmnParticipant.MQNAME), 0, -1 );
        registerDependency(this.participantDep);
        
        this.flowElementDep = new FlowElementSmDependency();
        this.flowElementDep.init("FlowElement", this, metamodel.getMClass(BpmnFlowElement.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.flowElementDep);
        
        this.resourceDep = new ResourceSmDependency();
        this.resourceDep.init("Resource", this, metamodel.getMClass(BpmnResourceRole.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.resourceDep);
        
        this.definitionalCollaborationDep = new DefinitionalCollaborationSmDependency();
        this.definitionalCollaborationDep.init("DefinitionalCollaboration", this, metamodel.getMClass(BpmnCollaboration.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definitionalCollaborationDep);
    }

    @objid ("771fae46-acc3-4d80-87d6-bc196b8b6b9b")
    public SmAttribute getProcessTypeAtt() {
        if (this.processTypeAtt == null) {
        	this.processTypeAtt = this.getAttributeDef("ProcessType");
        }
        return this.processTypeAtt;
    }

    @objid ("c2dd3476-6142-47a3-a0c1-a2c3df30c9e5")
    public SmAttribute getIsClosedAtt() {
        if (this.isClosedAtt == null) {
        	this.isClosedAtt = this.getAttributeDef("IsClosed");
        }
        return this.isClosedAtt;
    }

    @objid ("3005e41d-58c5-4b59-b8ef-7d6ee7605473")
    public SmAttribute getIsExecutableAtt() {
        if (this.isExecutableAtt == null) {
        	this.isExecutableAtt = this.getAttributeDef("IsExecutable");
        }
        return this.isExecutableAtt;
    }

    @objid ("b8c74c5c-c9e7-45a5-a582-519d752fb392")
    public SmDependency getSupportsDep() {
        if (this.supportsDep == null) {
        	this.supportsDep = this.getDependencyDef("Supports");
        }
        return this.supportsDep;
    }

    @objid ("860f1701-775a-4bf3-a7b1-8811c3a96fa1")
    public SmDependency getArtifactDep() {
        if (this.artifactDep == null) {
        	this.artifactDep = this.getDependencyDef("Artifact");
        }
        return this.artifactDep;
    }

    @objid ("e89561f0-9c6c-4e56-8a29-427116c9ceda")
    public SmDependency getLaneSetDep() {
        if (this.laneSetDep == null) {
        	this.laneSetDep = this.getDependencyDef("LaneSet");
        }
        return this.laneSetDep;
    }

    @objid ("f1050f72-0c12-40a6-990f-350ac4442bd3")
    public SmDependency getSupportedDep() {
        if (this.supportedDep == null) {
        	this.supportedDep = this.getDependencyDef("Supported");
        }
        return this.supportedDep;
    }

    @objid ("79880e6a-b1f2-4caf-8ba8-c698c3956012")
    public SmDependency getParticipantDep() {
        if (this.participantDep == null) {
        	this.participantDep = this.getDependencyDef("Participant");
        }
        return this.participantDep;
    }

    @objid ("c49ebb7b-9eb5-427e-a1c3-90971b2e2728")
    public SmDependency getFlowElementDep() {
        if (this.flowElementDep == null) {
        	this.flowElementDep = this.getDependencyDef("FlowElement");
        }
        return this.flowElementDep;
    }

    @objid ("98472547-8790-437a-835f-c6ce034f65d9")
    public SmDependency getResourceDep() {
        if (this.resourceDep == null) {
        	this.resourceDep = this.getDependencyDef("Resource");
        }
        return this.resourceDep;
    }

    @objid ("ab6f845f-ba1f-4800-a129-5ef33a932099")
    public SmDependency getDefinitionalCollaborationDep() {
        if (this.definitionalCollaborationDep == null) {
        	this.definitionalCollaborationDep = this.getDependencyDef("DefinitionalCollaboration");
        }
        return this.definitionalCollaborationDep;
    }

    @objid ("f3f347e0-16ec-4d4c-afb7-5e2e4c48709c")
    private static class BpmnProcessObjectFactory implements ISmObjectFactory {
        @objid ("51728da4-038c-4f33-a300-0996f67d6ac1")
        private BpmnProcessSmClass smClass;

        @objid ("e7afe6d5-f48d-48ed-8ff3-a5add24716f9")
        public BpmnProcessObjectFactory(BpmnProcessSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("65a11ecb-3cc3-49e5-b212-0d47cf7d3718")
        @Override
        public ISmObjectData createData() {
            return new BpmnProcessData(this.smClass);
        }

        @objid ("d39586c1-62cf-4d44-b90a-28000c24b388")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnProcessImpl();
        }

    }

    @objid ("a57e3303-6b4b-431c-b2b8-92a64b45c0d7")
    public static class ProcessTypeSmAttribute extends SmAttribute {
        @objid ("3d48ae26-1ec1-4c00-834a-522e35c3e015")
        public Object getValue(ISmObjectData data) {
            return ((BpmnProcessData) data).mProcessType;
        }

        @objid ("0fde8a5f-c5f9-460a-b457-75b6ddef3bbd")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnProcessData) data).mProcessType = value;
        }

    }

    @objid ("143117b3-5695-45b3-8f1f-587f7d69172e")
    public static class IsClosedSmAttribute extends SmAttribute {
        @objid ("90274c41-53a1-498e-b51b-ff172ee42d4b")
        public Object getValue(ISmObjectData data) {
            return ((BpmnProcessData) data).mIsClosed;
        }

        @objid ("9383d6c8-9102-4a4d-9e98-81a1b6c3dd44")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnProcessData) data).mIsClosed = value;
        }

    }

    @objid ("91507e3b-7ca8-4fbc-b093-3e9671869398")
    public static class IsExecutableSmAttribute extends SmAttribute {
        @objid ("03f2f5e6-d278-4d27-9f9d-b7def034012d")
        public Object getValue(ISmObjectData data) {
            return ((BpmnProcessData) data).mIsExecutable;
        }

        @objid ("ff080a3c-41be-4bc6-9905-8c6683825ccd")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnProcessData) data).mIsExecutable = value;
        }

    }

    @objid ("511b7545-1288-44cf-bae6-7e4649dfe482")
    public static class SupportsSmDependency extends SmMultipleDependency {
        @objid ("f5f0c60a-b8a6-4570-8b0e-aa9e298eaeb2")
        private SmDependency symetricDep;

        @objid ("5705c8d3-8bff-4ab4-8250-0279d3e3b2c0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mSupports != null)? ((BpmnProcessData)data).mSupports:SmMultipleDependency.EMPTY;
        }

        @objid ("5e7dfdda-71d9-4ebe-8985-9cfdd813ec2e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mSupports = values;
        }

        @objid ("ac957a1d-17c9-4d2b-9b8b-54e6d7242e65")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getSupportedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a0274f02-22e6-4ab4-b904-21ec3e0ce53b")
    public static class ArtifactSmDependency extends SmMultipleDependency {
        @objid ("4b559637-345c-444c-8b91-2e1a689b283e")
        private SmDependency symetricDep;

        @objid ("f5eade74-33ed-4e1c-8334-96099c22fba0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mArtifact != null)? ((BpmnProcessData)data).mArtifact:SmMultipleDependency.EMPTY;
        }

        @objid ("500f0cdb-53bb-43cc-9bc8-9f4043a989c7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mArtifact = values;
        }

        @objid ("a1d330ea-7737-4895-82b7-21279b370b5e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnArtifactSmClass)this.getTarget()).getProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b38536a6-9bf3-4c1a-a4ae-1dac1b8c6be1")
    public static class LaneSetSmDependency extends SmSingleDependency {
        @objid ("a1253ace-884c-4ace-9a31-dd9fbcd8c7b9")
        private SmDependency symetricDep;

        @objid ("43b9a416-7805-47af-816d-a68eca0ff5e9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnProcessData) data).mLaneSet;
        }

        @objid ("a56282a9-2e55-4c0b-9e4c-c64ed5ac8818")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnProcessData) data).mLaneSet = value;
        }

        @objid ("006a909d-fe77-4a43-aebe-5068f853aaae")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSetSmClass)this.getTarget()).getProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("343d4720-fea3-4786-b409-c9a0d3b6ac74")
    public static class SupportedSmDependency extends SmMultipleDependency {
        @objid ("a3a19d4a-ec2f-45d5-aec5-b3fe89354b30")
        private SmDependency symetricDep;

        @objid ("80762670-fa70-4a57-bd47-6f22bad37ee3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mSupported != null)? ((BpmnProcessData)data).mSupported:SmMultipleDependency.EMPTY;
        }

        @objid ("6a913270-5f12-4af7-8fe8-3e5933e15e81")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mSupported = values;
        }

        @objid ("6ef53103-b790-4ee8-ac03-02076a050a6a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getSupportsDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4d9ba004-3a07-4c40-be74-246e21a33bbb")
    public static class ParticipantSmDependency extends SmMultipleDependency {
        @objid ("56581578-3b1b-4782-a605-04a4578805cc")
        private SmDependency symetricDep;

        @objid ("c6f5c9f0-2b22-4717-9d1b-64735137f80f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mParticipant != null)? ((BpmnProcessData)data).mParticipant:SmMultipleDependency.EMPTY;
        }

        @objid ("ca615437-acf6-4e64-9384-47000fbc1df6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mParticipant = values;
        }

        @objid ("cf4f92c7-6955-4980-82b9-7ca61a48d27b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnParticipantSmClass)this.getTarget()).getProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8a3d4ed6-d838-46be-978c-271a4631ec1c")
    public static class FlowElementSmDependency extends SmMultipleDependency {
        @objid ("e7012f5d-bbca-4843-9940-3e490820ab09")
        private SmDependency symetricDep;

        @objid ("4a58be7b-1cb9-495e-bc2c-d64b85fe953a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mFlowElement != null)? ((BpmnProcessData)data).mFlowElement:SmMultipleDependency.EMPTY;
        }

        @objid ("1937c940-e413-4e35-9817-a3a6b3626bb6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mFlowElement = values;
        }

        @objid ("0e540f08-a0c7-436d-aa05-8379f8fffdcf")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowElementSmClass)this.getTarget()).getContainerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("871330b9-e143-42b1-bef5-f42a7f87cb5d")
    public static class ResourceSmDependency extends SmMultipleDependency {
        @objid ("61c7d4f3-b114-4e25-a3b9-f56c9f0b12f4")
        private SmDependency symetricDep;

        @objid ("4303d96a-1fd8-4441-be96-e1f9be06e313")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnProcessData)data).mResource != null)? ((BpmnProcessData)data).mResource:SmMultipleDependency.EMPTY;
        }

        @objid ("85da08c2-3834-4699-bf6c-649049e0a0b6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnProcessData) data).mResource = values;
        }

        @objid ("4aaf86bb-34e6-44d6-af55-087244d66706")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceRoleSmClass)this.getTarget()).getProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e655a513-6f7f-41ec-9ff9-ebcab4c597bb")
    public static class DefinitionalCollaborationSmDependency extends SmSingleDependency {
        @objid ("a371828e-67d8-4a02-8b27-c0f1c41ca9e3")
        private SmDependency symetricDep;

        @objid ("191c6508-2fd8-4342-8d1a-c85f9e9de661")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnProcessData) data).mDefinitionalCollaboration;
        }

        @objid ("287e0eb0-2e99-46ca-abf0-03af3223ac51")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnProcessData) data).mDefinitionalCollaboration = value;
        }

        @objid ("8f107659-c2ef-450b-b783-e93633060e2a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCollaborationSmClass)this.getTarget()).getDefinedProcessDep();
            }
            return this.symetricDep;
        }

    }

}
