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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c656dbc6-a3e5-494e-a3e2-69a0638466d7")
public class TransitionSmClass extends UmlModelElementSmClass {
    @objid ("546cdc92-1e85-4ac1-b902-40ece3882426")
    private SmAttribute effectAtt;

    @objid ("61c0d989-754d-4bb7-8179-fd5fed7bb87f")
    private SmAttribute receivedEventsAtt;

    @objid ("ae3c6ef9-1bf9-4ff7-a389-e3862b9390a3")
    private SmAttribute sentEventsAtt;

    @objid ("a1104d1c-b2b0-4fb4-a69e-2c4691cfd9ca")
    private SmAttribute guardAtt;

    @objid ("967ccd3f-2a8c-40c0-9542-26466b811565")
    private SmAttribute postConditionAtt;

    @objid ("db3cdabd-2f72-4eb2-8803-7788aeb8c550")
    private SmDependency processedDep;

    @objid ("43b84ef2-d7d0-4fb0-b439-3d779d9d7583")
    private SmDependency triggerDep;

    @objid ("237f4622-9061-4f39-b880-06732944d321")
    private SmDependency behaviorEffectDep;

    @objid ("ea491e61-1dca-4a31-8d60-013636e4f9c4")
    private SmDependency targetDep;

    @objid ("4bc1d7da-9e28-432f-b28b-675cf4c458e7")
    private SmDependency sourceDep;

    @objid ("35c6ad38-1c31-4ae3-a7bb-0b8532695799")
    private SmDependency effectsDep;

    @objid ("1318d641-6a49-41a6-bd58-195e351741f9")
    public  TransitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8cb1f696-12c6-46ea-a542-0db23012716a")
    @Override
    public String getName() {
        return "Transition";
        
    }

    @objid ("64195aab-560f-4997-b852-7022056388c6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8b46fab2-2f16-40b0-8f78-716f7f024ced")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Transition.class;
        
    }

    @objid ("61448ec2-5d65-4824-aac3-10463d7ae0fd")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("85950f88-48eb-42cf-a808-12f5e2ab761e")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("20807e3c-6715-43fd-845d-716d38a1ba9d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new TransitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.effectAtt = new EffectSmAttribute();
        this.effectAtt.init("Effect", this, String.class );
        registerAttribute(this.effectAtt);
        
        this.receivedEventsAtt = new ReceivedEventsSmAttribute();
        this.receivedEventsAtt.init("ReceivedEvents", this, String.class );
        registerAttribute(this.receivedEventsAtt);
        
        this.sentEventsAtt = new SentEventsSmAttribute();
        this.sentEventsAtt.init("SentEvents", this, String.class );
        registerAttribute(this.sentEventsAtt);
        
        this.guardAtt = new GuardSmAttribute();
        this.guardAtt.init("Guard", this, String.class );
        registerAttribute(this.guardAtt);
        
        this.postConditionAtt = new PostConditionSmAttribute();
        this.postConditionAtt.init("PostCondition", this, String.class );
        registerAttribute(this.postConditionAtt);
        
        
        // Initialize and register the SmDependency
        this.processedDep = new ProcessedSmDependency();
        this.processedDep.init("Processed", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.processedDep);
        
        this.triggerDep = new TriggerSmDependency();
        this.triggerDep.init("Trigger", this, metamodel.getMClass(Event.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.triggerDep);
        
        this.behaviorEffectDep = new BehaviorEffectSmDependency();
        this.behaviorEffectDep.init("BehaviorEffect", this, metamodel.getMClass(Behavior.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.behaviorEffectDep);
        
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(StateVertex.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
        
        this.sourceDep = new SourceSmDependency();
        this.sourceDep.init("Source", this, metamodel.getMClass(StateVertex.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.sourceDep);
        
        this.effectsDep = new EffectsSmDependency();
        this.effectsDep.init("Effects", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.effectsDep);
        
        
    }

    @objid ("adee4fea-6443-406c-9f36-957d8c656363")
    public SmAttribute getEffectAtt() {
        if (this.effectAtt == null) {
        	this.effectAtt = this.getAttributeDef("Effect");
        }
        return this.effectAtt;
    }

    @objid ("96b9cdbd-c51a-4146-b193-ceefca4be235")
    public SmAttribute getReceivedEventsAtt() {
        if (this.receivedEventsAtt == null) {
        	this.receivedEventsAtt = this.getAttributeDef("ReceivedEvents");
        }
        return this.receivedEventsAtt;
    }

    @objid ("73e469a1-401d-4ba9-ba07-2905681e511e")
    public SmAttribute getSentEventsAtt() {
        if (this.sentEventsAtt == null) {
        	this.sentEventsAtt = this.getAttributeDef("SentEvents");
        }
        return this.sentEventsAtt;
    }

    @objid ("f03db814-fa99-4e6d-8cb9-596c2790a39c")
    public SmAttribute getGuardAtt() {
        if (this.guardAtt == null) {
        	this.guardAtt = this.getAttributeDef("Guard");
        }
        return this.guardAtt;
    }

    @objid ("b9350527-a2ef-48aa-9560-b337f286e155")
    public SmAttribute getPostConditionAtt() {
        if (this.postConditionAtt == null) {
        	this.postConditionAtt = this.getAttributeDef("PostCondition");
        }
        return this.postConditionAtt;
    }

    @objid ("c6dbba85-3bab-43ed-b5d9-1a4aa567b250")
    public SmDependency getProcessedDep() {
        if (this.processedDep == null) {
        	this.processedDep = this.getDependencyDef("Processed");
        }
        return this.processedDep;
    }

    @objid ("8877bbe2-3076-4018-b7d2-e72b03071be2")
    public SmDependency getTriggerDep() {
        if (this.triggerDep == null) {
        	this.triggerDep = this.getDependencyDef("Trigger");
        }
        return this.triggerDep;
    }

    @objid ("fc5917d2-d700-4c03-9651-b7ddfdcab240")
    public SmDependency getBehaviorEffectDep() {
        if (this.behaviorEffectDep == null) {
        	this.behaviorEffectDep = this.getDependencyDef("BehaviorEffect");
        }
        return this.behaviorEffectDep;
    }

    @objid ("31de37e8-7fb3-4e97-b770-4343886fbed8")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("ee979b17-5315-4928-b568-b244a486083f")
    public SmDependency getSourceDep() {
        if (this.sourceDep == null) {
        	this.sourceDep = this.getDependencyDef("Source");
        }
        return this.sourceDep;
    }

    @objid ("9ea52ecd-c72e-4ad3-a8fc-d0eafb6adedd")
    public SmDependency getEffectsDep() {
        if (this.effectsDep == null) {
        	this.effectsDep = this.getDependencyDef("Effects");
        }
        return this.effectsDep;
    }

    @objid ("f825991b-5960-4042-99ea-27845e84929e")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("dad83e8a-7d34-4663-9de2-371993220e25")
    private static class TransitionObjectFactory implements ISmObjectFactory {
        @objid ("0b3c5983-80d4-44d0-825c-f4bbe8b8f9e6")
        private TransitionSmClass smClass;

        @objid ("be1500f9-228c-43e1-a585-1560248fce49")
        public  TransitionObjectFactory(TransitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d672c55a-a85d-4fcf-9f8e-7efaec53b225")
        @Override
        public ISmObjectData createData() {
            return new TransitionData(this.smClass);
        }

        @objid ("e393ebd0-6be5-4419-b050-769ccc75ef22")
        @Override
        public SmObjectImpl createImpl() {
            return new TransitionImpl();
        }

    }

    @objid ("74d944b7-be71-4b13-89f6-4b6e8051d99c")
    public static class EffectSmAttribute extends SmAttribute {
        @objid ("4c6e91a2-e44d-4b2a-9c05-38d0020d77ab")
        public Object getValue(ISmObjectData data) {
            return ((TransitionData) data).mEffect;
        }

        @objid ("6fda9f76-5eea-4f48-a671-bc44082334f9")
        public void setValue(ISmObjectData data, Object value) {
            ((TransitionData) data).mEffect = value;
        }

    }

    @objid ("fcda15cf-e212-4cf5-a618-c7c47ef10072")
    public static class ReceivedEventsSmAttribute extends SmAttribute {
        @objid ("cb8a3f34-3bec-4de4-a89b-010a23626b16")
        public Object getValue(ISmObjectData data) {
            return ((TransitionData) data).mReceivedEvents;
        }

        @objid ("1ed044c7-bc3b-46ee-9668-cfe390a826e0")
        public void setValue(ISmObjectData data, Object value) {
            ((TransitionData) data).mReceivedEvents = value;
        }

    }

    @objid ("016eb7d6-021f-49c5-a614-90263fb4782f")
    public static class SentEventsSmAttribute extends SmAttribute {
        @objid ("80b81c33-11d0-492d-bef7-9b833dda85df")
        public Object getValue(ISmObjectData data) {
            return ((TransitionData) data).mSentEvents;
        }

        @objid ("e8a0f618-7443-454d-bfb7-454bb84c6910")
        public void setValue(ISmObjectData data, Object value) {
            ((TransitionData) data).mSentEvents = value;
        }

    }

    @objid ("0d089469-cf77-4af9-8c90-5130e3418ebe")
    public static class GuardSmAttribute extends SmAttribute {
        @objid ("26418340-c8f9-4fc0-9312-ef1827f90133")
        public Object getValue(ISmObjectData data) {
            return ((TransitionData) data).mGuard;
        }

        @objid ("ea997495-c74c-4cd0-9d50-b3c76dd81a06")
        public void setValue(ISmObjectData data, Object value) {
            ((TransitionData) data).mGuard = value;
        }

    }

    @objid ("717dbbb7-4391-436b-8f41-e20009c33821")
    public static class PostConditionSmAttribute extends SmAttribute {
        @objid ("1fb18adf-46af-4f73-bc08-b60f0804a21e")
        public Object getValue(ISmObjectData data) {
            return ((TransitionData) data).mPostCondition;
        }

        @objid ("48af3354-a08e-4c7f-8907-42d963ce85ce")
        public void setValue(ISmObjectData data, Object value) {
            ((TransitionData) data).mPostCondition = value;
        }

    }

    @objid ("45626cae-eaa7-4bcc-bbc8-2aedde223c34")
    public static class ProcessedSmDependency extends SmSingleDependency {
        @objid ("4a9973c1-475d-4127-80af-3e0963f89967")
        private SmDependency symetricDep;

        @objid ("7aee2f3f-2281-47d4-9e53-4ae1e448f599")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mProcessed;
        }

        @objid ("301615ab-ee10-4259-8b18-391aef6b8501")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mProcessed = value;
        }

        @objid ("7d0e1fff-cb49-431c-a1f7-360a012f1fa9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getInvokerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("536c33f2-aff9-4c5c-a3b4-c319cc6ac058")
    public static class TriggerSmDependency extends SmSingleDependency {
        @objid ("73d9b066-911f-4cce-adf3-c7f7da266a36")
        private SmDependency symetricDep;

        @objid ("cef206bb-7c04-4944-8529-47ce4c1ed36d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mTrigger;
        }

        @objid ("32a25fdc-870d-4348-928d-fd63ff4db5b0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mTrigger = value;
        }

        @objid ("57f4fa1f-ccf3-4716-904a-3aba943c51c4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EventSmClass)this.getTarget()).getTriggeredDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("8a8b114a-7b85-4d7c-b991-e88620616ac1")
    public static class BehaviorEffectSmDependency extends SmSingleDependency {
        @objid ("c70b9b2d-7b60-4dfc-8b4e-45cbc2bb88e3")
        private SmDependency symetricDep;

        @objid ("cfa608d3-474e-4090-b8e4-be8cf2674f9b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mBehaviorEffect;
        }

        @objid ("edf02f5b-3143-48e1-b6fd-a8df0bb08ad9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mBehaviorEffect = value;
        }

        @objid ("ff32b318-621c-4484-8f1f-1ab98ed5cd68")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getEffectOfDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3cbe8256-6d5c-494e-ab29-52426677408c")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("157f1938-e4c4-4758-a6f6-bb268c92d99b")
        private SmDependency symetricDep;

        @objid ("3bda4842-ca59-44a4-aefb-6f6e5011ca4f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mTarget;
        }

        @objid ("fefb7513-256f-4d76-91a0-24f3634e8566")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mTarget = value;
        }

        @objid ("d5ecc1fe-f5c9-48f0-83a0-c94848c94ea1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateVertexSmClass)this.getTarget()).getIncomingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("96508fd6-a30f-4c56-844c-bda949199949")
    public static class SourceSmDependency extends SmSingleDependency {
        @objid ("d29d1cae-4239-4a94-9171-00cba85702be")
        private SmDependency symetricDep;

        @objid ("0ca481b3-bf71-44b4-b9a1-ac8ecdca5d25")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mSource;
        }

        @objid ("0055c468-a8ac-4a5b-b360-1825e30adaa4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mSource = value;
        }

        @objid ("6f78e61e-261b-433b-8e3c-c654c573b8f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateVertexSmClass)this.getTarget()).getOutGoingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a5aaa978-27bc-4509-963d-a9b13ec9fbf9")
    public static class EffectsSmDependency extends SmSingleDependency {
        @objid ("8c99e0a1-a6e7-4b99-842f-3cfebdf4f66a")
        private SmDependency symetricDep;

        @objid ("621a8ff5-51ee-470f-925e-666b704ebfb9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TransitionData) data).mEffects;
        }

        @objid ("6b4d5812-69c4-4034-aed8-333b4e780495")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TransitionData) data).mEffects = value;
        }

        @objid ("ae9110ba-ff42-49d9-98a7-5d19c0e06760")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getSendsDep();
            }
            return this.symetricDep;
            
        }

    }

}
