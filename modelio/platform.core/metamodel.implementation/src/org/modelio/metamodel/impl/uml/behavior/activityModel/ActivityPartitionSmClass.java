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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityPartitionData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.MessageFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("4a14a4a5-a6b6-4222-990c-bf3308b3811e")
public class ActivityPartitionSmClass extends ActivityGroupSmClass {
    @objid ("0dce8df2-80e1-476f-998c-39312def39f3")
    private SmAttribute isDimensionAtt;

    @objid ("54f7e6e3-afb4-408f-be13-806e91800f47")
    private SmAttribute isExternalAtt;

    @objid ("1752217e-5f9c-4e8d-9536-3b4b3acfc3bf")
    private SmDependency representedDep;

    @objid ("c6bb66f5-50f7-481f-980c-3aefeec67043")
    private SmDependency containedNodeDep;

    @objid ("cf11efbd-32dd-4c25-908a-fd072eb8c047")
    private SmDependency outgoingDep;

    @objid ("1bf06a51-0b19-433b-acab-9f82eaac41f5")
    private SmDependency superPartitionDep;

    @objid ("58a0601d-eaaf-4fb8-9ab5-a18ca0daf681")
    private SmDependency subPartitionDep;

    @objid ("5e6e4865-9a52-4f13-a623-c41f1d12aa9b")
    private SmDependency incomingDep;

    @objid ("eb355345-95dc-4839-8dc9-77e1b5ffb802")
    public ActivityPartitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("98080657-cee2-4805-bbe3-fdd59c1f5ca2")
    @Override
    public String getName() {
        return "ActivityPartition";
    }

    @objid ("4a89855b-5472-45c6-9b95-73d0f7bcd308")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3bcba5cb-7b40-422b-991e-bb436cfa0c12")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityPartition.class;
    }

    @objid ("23704778-aad8-49d1-be96-37e8937ae770")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4d9505f0-5786-45aa-9b9a-41dc4f8eee04")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("118b9a8d-5968-4eba-9f77-adf9f8ee6ebb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityGroup.MQNAME);
        this.registerFactory(new ActivityPartitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isDimensionAtt = new IsDimensionSmAttribute();
        this.isDimensionAtt.init("IsDimension", this, Boolean.class );
        registerAttribute(this.isDimensionAtt);
        
        this.isExternalAtt = new IsExternalSmAttribute();
        this.isExternalAtt.init("IsExternal", this, Boolean.class );
        registerAttribute(this.isExternalAtt);
        
        
        // Initialize and register the SmDependency
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedDep);
        
        this.containedNodeDep = new ContainedNodeSmDependency();
        this.containedNodeDep.init("ContainedNode", this, metamodel.getMClass(ActivityNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.containedNodeDep);
        
        this.outgoingDep = new OutgoingSmDependency();
        this.outgoingDep.init("Outgoing", this, metamodel.getMClass(MessageFlow.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.outgoingDep);
        
        this.superPartitionDep = new SuperPartitionSmDependency();
        this.superPartitionDep.init("SuperPartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, 1 );
        registerDependency(this.superPartitionDep);
        
        this.subPartitionDep = new SubPartitionSmDependency();
        this.subPartitionDep.init("SubPartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.subPartitionDep);
        
        this.incomingDep = new IncomingSmDependency();
        this.incomingDep.init("Incoming", this, metamodel.getMClass(MessageFlow.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.incomingDep);
    }

    @objid ("98fe2fe6-bf84-41b0-b1dc-defc09c73f64")
    public SmAttribute getIsDimensionAtt() {
        if (this.isDimensionAtt == null) {
        	this.isDimensionAtt = this.getAttributeDef("IsDimension");
        }
        return this.isDimensionAtt;
    }

    @objid ("c30c8820-820a-4e0d-99c6-54044c27a1ec")
    public SmAttribute getIsExternalAtt() {
        if (this.isExternalAtt == null) {
        	this.isExternalAtt = this.getAttributeDef("IsExternal");
        }
        return this.isExternalAtt;
    }

    @objid ("4e8f9472-76e1-47b0-9ebf-919cde7feab1")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("dd642f0a-0d55-490c-aaf2-46065cfdb9af")
    public SmDependency getContainedNodeDep() {
        if (this.containedNodeDep == null) {
        	this.containedNodeDep = this.getDependencyDef("ContainedNode");
        }
        return this.containedNodeDep;
    }

    @objid ("d26f2858-2329-4300-af1b-ee4b2d669cd6")
    public SmDependency getOutgoingDep() {
        if (this.outgoingDep == null) {
        	this.outgoingDep = this.getDependencyDef("Outgoing");
        }
        return this.outgoingDep;
    }

    @objid ("ed891eaf-f772-4dc8-b01e-320df2bf87fb")
    public SmDependency getSuperPartitionDep() {
        if (this.superPartitionDep == null) {
        	this.superPartitionDep = this.getDependencyDef("SuperPartition");
        }
        return this.superPartitionDep;
    }

    @objid ("64a69b8f-43f5-46a9-9012-56dbc41c4066")
    public SmDependency getSubPartitionDep() {
        if (this.subPartitionDep == null) {
        	this.subPartitionDep = this.getDependencyDef("SubPartition");
        }
        return this.subPartitionDep;
    }

    @objid ("861b20d3-55ff-4914-8dad-9faf66ae0be2")
    public SmDependency getIncomingDep() {
        if (this.incomingDep == null) {
        	this.incomingDep = this.getDependencyDef("Incoming");
        }
        return this.incomingDep;
    }

    @objid ("fef78210-3c28-4e6e-9c85-72d28feea250")
    private static class ActivityPartitionObjectFactory implements ISmObjectFactory {
        @objid ("b8ee4ad9-ae1e-47d5-918b-b06e3563c003")
        private ActivityPartitionSmClass smClass;

        @objid ("ea07c091-cc53-4358-bb6f-14b35f0ae265")
        public ActivityPartitionObjectFactory(ActivityPartitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6fdbabfb-cce4-4f02-a6f8-1bd9c27e8112")
        @Override
        public ISmObjectData createData() {
            return new ActivityPartitionData(this.smClass);
        }

        @objid ("af1652af-660f-44aa-82d9-6b772b23e4f2")
        @Override
        public SmObjectImpl createImpl() {
            return new ActivityPartitionImpl();
        }

    }

    @objid ("58335be6-9f7e-4fed-a885-a53d0193f45b")
    public static class IsDimensionSmAttribute extends SmAttribute {
        @objid ("acb82bd1-d3d6-4b1c-869c-619b40cebcb5")
        public Object getValue(ISmObjectData data) {
            return ((ActivityPartitionData) data).mIsDimension;
        }

        @objid ("dc9e6c63-7b12-456c-802e-d53399f8a5fa")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityPartitionData) data).mIsDimension = value;
        }

    }

    @objid ("481c0611-3e67-41fe-a303-90c0dd0349c5")
    public static class IsExternalSmAttribute extends SmAttribute {
        @objid ("d6691914-c83e-423a-90e6-82a38521b431")
        public Object getValue(ISmObjectData data) {
            return ((ActivityPartitionData) data).mIsExternal;
        }

        @objid ("21b7ac3a-8228-4d77-a717-0ac1ce99d66b")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityPartitionData) data).mIsExternal = value;
        }

    }

    @objid ("d4f65f98-e800-4796-88e9-ef88146b59fe")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("ec0374df-faf0-4ca2-88a8-fad6cc847670")
        private SmDependency symetricDep;

        @objid ("8ef925ad-0668-4b77-aa07-0b3178aae582")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityPartitionData) data).mRepresented;
        }

        @objid ("404b2f34-b60f-4baa-bc5f-41cbb0c99a4a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityPartitionData) data).mRepresented = value;
        }

        @objid ("3581520e-e120-4ae7-9c32-af1ee36d511e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getRepresentingPartitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7324f895-b7c3-4a93-9242-3ace9609561f")
    public static class ContainedNodeSmDependency extends SmMultipleDependency {
        @objid ("7a8fa63c-5e5d-44c9-be45-a0b2645415ba")
        private SmDependency symetricDep;

        @objid ("a362d6c2-71d6-4178-8eec-65592de48af6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityPartitionData)data).mContainedNode != null)? ((ActivityPartitionData)data).mContainedNode:SmMultipleDependency.EMPTY;
        }

        @objid ("920b2354-8edd-4fdd-b8b9-a0393dec1ce8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityPartitionData) data).mContainedNode = values;
        }

        @objid ("8b3c59b9-6a38-4144-90e1-59d63417f13a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getOwnerPartitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7d9166de-3743-40dc-8349-520a825d6e80")
    public static class OutgoingSmDependency extends SmMultipleDependency {
        @objid ("7bd23017-3ac6-49d9-b4e9-52b00b731b4d")
        private SmDependency symetricDep;

        @objid ("ef6d1bd5-b864-42c6-af18-e6ec0108bf3e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityPartitionData)data).mOutgoing != null)? ((ActivityPartitionData)data).mOutgoing:SmMultipleDependency.EMPTY;
        }

        @objid ("6159a9cc-ed9e-45a4-be1b-61850bba58bc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityPartitionData) data).mOutgoing = values;
        }

        @objid ("2553035a-ab6d-4cb0-97dc-a469f1747804")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageFlowSmClass)this.getTarget()).getSourcePartitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0f63c2e4-11a1-4541-8f1b-b419b9ddfbb4")
    public static class SuperPartitionSmDependency extends SmSingleDependency {
        @objid ("551c9afa-06ed-4db0-a461-e2fe30721271")
        private SmDependency symetricDep;

        @objid ("beb20ea8-ce3a-45bb-8c5e-df75aa351fec")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityPartitionData) data).mSuperPartition;
        }

        @objid ("1fe5b197-de73-47d9-a6b9-667d4e96161d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityPartitionData) data).mSuperPartition = value;
        }

        @objid ("73fd45c3-14a3-4c21-bbe9-2b5420f4db15")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getSubPartitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("970515b9-8e49-498e-9e52-c5eefb7e96bf")
    public static class SubPartitionSmDependency extends SmMultipleDependency {
        @objid ("77c3f70c-f570-46da-be1a-8745bf4eef47")
        private SmDependency symetricDep;

        @objid ("555d9c23-e4f2-4153-9b3e-d99bab24b843")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityPartitionData)data).mSubPartition != null)? ((ActivityPartitionData)data).mSubPartition:SmMultipleDependency.EMPTY;
        }

        @objid ("c6d991c2-d0a5-4bd9-8070-42c17e6e83a2")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityPartitionData) data).mSubPartition = values;
        }

        @objid ("e529e0e5-f7fa-404a-91c9-609ca7efa0fb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getSuperPartitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1c5dc013-54ad-41bf-a85c-aa94d0b4cefb")
    public static class IncomingSmDependency extends SmMultipleDependency {
        @objid ("7ea1fb4a-301d-4b3b-a67e-60ef3d28c96d")
        private SmDependency symetricDep;

        @objid ("febc6be7-bfb5-47d1-8b4a-2475ec43ee16")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityPartitionData)data).mIncoming != null)? ((ActivityPartitionData)data).mIncoming:SmMultipleDependency.EMPTY;
        }

        @objid ("5a011d79-4e02-4b32-b00f-a7de039802d6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityPartitionData) data).mIncoming = values;
        }

        @objid ("5d3cd5cb-a907-49eb-a358-47d4d8ae2d3d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageFlowSmClass)this.getTarget()).getTargetPartitionDep();
            }
            return this.symetricDep;
        }

    }

}
