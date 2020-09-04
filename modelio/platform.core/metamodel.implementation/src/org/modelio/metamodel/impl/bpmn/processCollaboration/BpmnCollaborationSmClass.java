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
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationData;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactSmClass;
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

@objid ("35abebd8-3162-4403-be18-3ed37e7f8391")
public class BpmnCollaborationSmClass extends BehaviorSmClass {
    @objid ("b8bb9eb8-4bc4-4e5f-9244-28413d19af79")
    private SmAttribute isClosedAtt;

    @objid ("a60219a5-2e0a-4130-aacd-01a9587f61db")
    private SmDependency artifactDep;

    @objid ("f668f20d-f150-408d-9094-c9c4110abebf")
    private SmDependency messageFlowDep;

    @objid ("233f4725-121e-4bd6-a4f3-5e3ba9807319")
    private SmDependency participantsDep;

    @objid ("50f8446f-70e0-4a8a-b713-828d722127a5")
    private SmDependency messagesDep;

    @objid ("18dbb35f-ab90-4d60-9321-fedaba3bb786")
    private SmDependency definedProcessDep;

    @objid ("2b11237f-b845-4a58-9638-58b6103f5e75")
    public BpmnCollaborationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9f0aec50-4e36-4a1d-bdb2-06594b94f0a6")
    @Override
    public String getName() {
        return "BpmnCollaboration";
    }

    @objid ("8b740db6-e023-45ed-803e-c0a80e37d2a0")
    @Override
    public Version getVersion() {
        return new Version("2.2.00");
    }

    @objid ("5f547bdd-9876-4c98-a3a6-db95f45b44c5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCollaboration.class;
    }

    @objid ("0ee219e5-37be-44fa-87a6-c7ebf8464ac2")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("99b35e61-09d0-4480-8dbd-769dbfeb274d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7eddddbc-bf23-4806-a801-5a4117f1c4f0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new BpmnCollaborationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isClosedAtt = new IsClosedSmAttribute();
        this.isClosedAtt.init("IsClosed", this, Boolean.class );
        registerAttribute(this.isClosedAtt);
        
        
        // Initialize and register the SmDependency
        this.artifactDep = new ArtifactSmDependency();
        this.artifactDep.init("Artifact", this, metamodel.getMClass(BpmnArtifact.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.artifactDep);
        
        this.messageFlowDep = new MessageFlowSmDependency();
        this.messageFlowDep.init("MessageFlow", this, metamodel.getMClass(BpmnMessageFlow.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.messageFlowDep);
        
        this.participantsDep = new ParticipantsSmDependency();
        this.participantsDep.init("Participants", this, metamodel.getMClass(BpmnParticipant.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.participantsDep);
        
        this.messagesDep = new MessagesSmDependency();
        this.messagesDep.init("Messages", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.messagesDep);
        
        this.definedProcessDep = new DefinedProcessSmDependency();
        this.definedProcessDep.init("DefinedProcess", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 );
        registerDependency(this.definedProcessDep);
    }

    @objid ("d0b7a4f2-7125-4681-9db3-586d8d915d5f")
    public SmAttribute getIsClosedAtt() {
        if (this.isClosedAtt == null) {
        	this.isClosedAtt = this.getAttributeDef("IsClosed");
        }
        return this.isClosedAtt;
    }

    @objid ("9e220776-c2a7-49c3-9adf-c018676023f6")
    public SmDependency getArtifactDep() {
        if (this.artifactDep == null) {
        	this.artifactDep = this.getDependencyDef("Artifact");
        }
        return this.artifactDep;
    }

    @objid ("53769b53-b3b1-412e-970f-a2dcc1366716")
    public SmDependency getMessageFlowDep() {
        if (this.messageFlowDep == null) {
        	this.messageFlowDep = this.getDependencyDef("MessageFlow");
        }
        return this.messageFlowDep;
    }

    @objid ("2af767a7-f296-461e-8c0c-e92031f84bf1")
    public SmDependency getParticipantsDep() {
        if (this.participantsDep == null) {
        	this.participantsDep = this.getDependencyDef("Participants");
        }
        return this.participantsDep;
    }

    @objid ("2459a089-69ec-4448-b403-033315cbf910")
    public SmDependency getMessagesDep() {
        if (this.messagesDep == null) {
        	this.messagesDep = this.getDependencyDef("Messages");
        }
        return this.messagesDep;
    }

    @objid ("913f9561-c46b-4f58-bdc0-a8ff4f5288c5")
    public SmDependency getDefinedProcessDep() {
        if (this.definedProcessDep == null) {
        	this.definedProcessDep = this.getDependencyDef("DefinedProcess");
        }
        return this.definedProcessDep;
    }

    @objid ("218e65fa-7f2e-40b1-817d-38b85b6fe210")
    private static class BpmnCollaborationObjectFactory implements ISmObjectFactory {
        @objid ("215714b8-05e8-4132-9441-3298fc48cac9")
        private BpmnCollaborationSmClass smClass;

        @objid ("e877a9b8-2b25-4627-9aea-0dcc32453638")
        public BpmnCollaborationObjectFactory(BpmnCollaborationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d1663d6b-9fa3-4abf-97b8-7ac423aa8b9a")
        @Override
        public ISmObjectData createData() {
            return new BpmnCollaborationData(this.smClass);
        }

        @objid ("ffd6313c-35e3-41ae-8377-51fc0f48a7b8")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnCollaborationImpl();
        }

    }

    @objid ("1a7e9c6c-6cfe-4d80-898c-621dbea777c4")
    public static class IsClosedSmAttribute extends SmAttribute {
        @objid ("75c87ca0-c81c-4e0d-bf4e-a82f90d60089")
        public Object getValue(ISmObjectData data) {
            return ((BpmnCollaborationData) data).mIsClosed;
        }

        @objid ("939703f9-288d-42b0-bc88-3fc200ec87c9")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnCollaborationData) data).mIsClosed = value;
        }

    }

    @objid ("a3ee30a5-ee6d-40c4-a5cf-0537345a9ce3")
    public static class ArtifactSmDependency extends SmMultipleDependency {
        @objid ("e970558a-d34d-4cf8-b25a-1a43309cc8a1")
        private SmDependency symetricDep;

        @objid ("718f4359-f532-4cd8-a208-6eaae4340fb2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnCollaborationData)data).mArtifact != null)? ((BpmnCollaborationData)data).mArtifact:SmMultipleDependency.EMPTY;
        }

        @objid ("3ef0de3a-5696-4494-962c-0874eeb68ebd")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnCollaborationData) data).mArtifact = values;
        }

        @objid ("fd3f8d1d-3472-46c3-aff1-df5309e4b1ec")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnArtifactSmClass)this.getTarget()).getCollaborationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b0101681-fc58-423a-b996-bf5a613aab5f")
    public static class MessageFlowSmDependency extends SmMultipleDependency {
        @objid ("b952833f-4ef2-4590-b730-0a82b88a325d")
        private SmDependency symetricDep;

        @objid ("aedf24aa-01c3-4cb2-b0be-f71040137b3a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnCollaborationData)data).mMessageFlow != null)? ((BpmnCollaborationData)data).mMessageFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("2f4cf838-a38c-4eab-b9ac-cd824a153225")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnCollaborationData) data).mMessageFlow = values;
        }

        @objid ("3c67dcaf-ae03-44a9-b9d6-14f6390c925a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageFlowSmClass)this.getTarget()).getCollaborationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("408f34e8-65d7-4852-be8d-04ddd299da51")
    public static class ParticipantsSmDependency extends SmMultipleDependency {
        @objid ("94c240ef-8a95-4556-a72c-0f1503385d9b")
        private SmDependency symetricDep;

        @objid ("4377cc61-679c-466a-b906-6095a27de402")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnCollaborationData)data).mParticipants != null)? ((BpmnCollaborationData)data).mParticipants:SmMultipleDependency.EMPTY;
        }

        @objid ("6a85cc65-117f-404b-92c2-e4ff75cbf48c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnCollaborationData) data).mParticipants = values;
        }

        @objid ("1f9ca96e-add9-4ee8-8f56-658c91790c92")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnParticipantSmClass)this.getTarget()).getContainerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("964a747b-62e1-4d65-8a17-009d205e31a5")
    public static class MessagesSmDependency extends SmMultipleDependency {
        @objid ("b95d6cde-4288-4def-bd8b-d9281aef5aa5")
        private SmDependency symetricDep;

        @objid ("74d7495c-f448-4add-a093-68dea4f64bb5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnCollaborationData)data).mMessages != null)? ((BpmnCollaborationData)data).mMessages:SmMultipleDependency.EMPTY;
        }

        @objid ("581fec93-5b2c-4cac-a0b8-2152f2a74af4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnCollaborationData) data).mMessages = values;
        }

        @objid ("51f2e6db-0724-4c5f-8198-da2366125b85")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getCollaborationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("be6a8ba5-f788-4257-bcf8-b096437439cc")
    public static class DefinedProcessSmDependency extends SmSingleDependency {
        @objid ("e3d4ccb9-9acc-4832-bdfa-0a5097e5991b")
        private SmDependency symetricDep;

        @objid ("417e0710-5c97-46bc-8b19-16db2c94ed34")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnCollaborationData) data).mDefinedProcess;
        }

        @objid ("ee8355a6-546c-48b7-a2e0-c4642577eb0b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnCollaborationData) data).mDefinedProcess = value;
        }

        @objid ("84796483-8cbf-46d9-a416-f47485c2e9e4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getDefinitionalCollaborationDep();
            }
            return this.symetricDep;
        }

    }

}
