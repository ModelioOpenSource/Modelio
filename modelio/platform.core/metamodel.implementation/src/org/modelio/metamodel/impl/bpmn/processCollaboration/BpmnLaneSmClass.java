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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneData;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
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

@objid ("f00c3564-2a53-4a36-aee2-6bf19fcf1f74")
public class BpmnLaneSmClass extends BpmnBaseElementSmClass {
    @objid ("b125bca3-1f41-42ca-b325-a13abf741e18")
    private SmDependency childLaneSetDep;

    @objid ("b7218e01-79b3-4988-82d9-1ed1eb699f4a")
    private SmDependency flowElementRefDep;

    @objid ("cc8061bc-0b16-4752-941f-86d241d0282f")
    private SmDependency laneSetDep;

    @objid ("f3faa4d8-7b0e-4575-a6b7-b95fa966cc5e")
    private SmDependency bpmnPartitionElementRefDep;

    @objid ("39de647b-8a2b-4a03-8079-56115d6d5310")
    public BpmnLaneSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b8abfd04-c19a-441c-bd09-ef21dc9fba9e")
    @Override
    public String getName() {
        return "BpmnLane";
    }

    @objid ("a5a115a2-9c1c-46a6-8b46-93552f5af1b2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8547748a-3617-4f2a-8ef1-537d4d867e87")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnLane.class;
    }

    @objid ("2674b903-a14f-4276-834f-b2520d04baf0")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9d3dc0ce-0d99-4121-af1d-eb8952b59d45")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("2f73391a-ff76-4111-9af4-512513ca9db8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnLaneObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.childLaneSetDep = new ChildLaneSetSmDependency();
        this.childLaneSetDep.init("ChildLaneSet", this, metamodel.getMClass(BpmnLaneSet.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.childLaneSetDep);
        
        this.flowElementRefDep = new FlowElementRefSmDependency();
        this.flowElementRefDep.init("FlowElementRef", this, metamodel.getMClass(BpmnFlowElement.MQNAME), 0, -1 , SmDirective.SMCDPARTOF, SmDirective.SMCDTODELETE);
        registerDependency(this.flowElementRefDep);
        
        this.laneSetDep = new LaneSetSmDependency();
        this.laneSetDep.init("LaneSet", this, metamodel.getMClass(BpmnLaneSet.MQNAME), 1, 1 );
        registerDependency(this.laneSetDep);
        
        this.bpmnPartitionElementRefDep = new BpmnPartitionElementRefSmDependency();
        this.bpmnPartitionElementRefDep.init("BpmnPartitionElementRef", this, metamodel.getMClass(BpmnBaseElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.bpmnPartitionElementRefDep);
    }

    @objid ("aa1d1eeb-e5bf-4565-ac1a-ddf002e482ba")
    public SmDependency getChildLaneSetDep() {
        if (this.childLaneSetDep == null) {
        	this.childLaneSetDep = this.getDependencyDef("ChildLaneSet");
        }
        return this.childLaneSetDep;
    }

    @objid ("03eb5eec-3989-4550-877b-da2856cf0c87")
    public SmDependency getFlowElementRefDep() {
        if (this.flowElementRefDep == null) {
        	this.flowElementRefDep = this.getDependencyDef("FlowElementRef");
        }
        return this.flowElementRefDep;
    }

    @objid ("10a5ced0-11e7-4734-be51-265fb407236c")
    public SmDependency getLaneSetDep() {
        if (this.laneSetDep == null) {
        	this.laneSetDep = this.getDependencyDef("LaneSet");
        }
        return this.laneSetDep;
    }

    @objid ("ccf2a3e8-3bb4-4c13-86b7-43f74730a26c")
    public SmDependency getBpmnPartitionElementRefDep() {
        if (this.bpmnPartitionElementRefDep == null) {
        	this.bpmnPartitionElementRefDep = this.getDependencyDef("BpmnPartitionElementRef");
        }
        return this.bpmnPartitionElementRefDep;
    }

    @objid ("297e5501-8fc6-4e62-8030-36a32fa31b4d")
    private static class BpmnLaneObjectFactory implements ISmObjectFactory {
        @objid ("6b9bfa3d-62b3-418d-844e-3e3ba456df12")
        private BpmnLaneSmClass smClass;

        @objid ("1b653ec6-2ac1-4a3f-898d-02f4779d4a00")
        public BpmnLaneObjectFactory(BpmnLaneSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("bb56e431-c607-436b-8a3f-f575d2c752a0")
        @Override
        public ISmObjectData createData() {
            return new BpmnLaneData(this.smClass);
        }

        @objid ("66fafce9-53da-40e4-a33d-a38df6196a05")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnLaneImpl();
        }

    }

    @objid ("85be006f-d5d2-4217-b167-64926023caa2")
    public static class ChildLaneSetSmDependency extends SmSingleDependency {
        @objid ("9d07dc17-4d13-40af-9065-0a8632323144")
        private SmDependency symetricDep;

        @objid ("5f4c2b39-4f10-4664-b4c1-cfd0f20204da")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneData) data).mChildLaneSet;
        }

        @objid ("5b534b98-f1e0-4c71-b1a1-dcacade3a072")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneData) data).mChildLaneSet = value;
        }

        @objid ("60856f3d-fa31-4e98-8710-ee4ea3b07565")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSetSmClass)this.getTarget()).getParentLaneDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f5b6c2d5-c1aa-4b2d-97b2-14cb0a45bb79")
    public static class FlowElementRefSmDependency extends SmMultipleDependency {
        @objid ("053ebec0-bc6a-4973-b7a3-30cffac85c7f")
        private SmDependency symetricDep;

        @objid ("55124e31-7890-4210-afd2-19a74c521571")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnLaneData)data).mFlowElementRef != null)? ((BpmnLaneData)data).mFlowElementRef:SmMultipleDependency.EMPTY;
        }

        @objid ("e3a5bd64-f65f-4776-8b3d-cfd585321245")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnLaneData) data).mFlowElementRef = values;
        }

        @objid ("f145f48e-7a7c-404a-9b53-07cd9b7e84bd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowElementSmClass)this.getTarget()).getLaneDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5254483f-5c3c-40cf-96d1-dc9c7a75ff1e")
    public static class LaneSetSmDependency extends SmSingleDependency {
        @objid ("5b3a5b69-e660-40f4-8bde-f3b6a6c06383")
        private SmDependency symetricDep;

        @objid ("9b5276fb-fdac-48ca-9407-59d2e83a8059")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneData) data).mLaneSet;
        }

        @objid ("207d4e24-fb31-415f-b9ac-00210df79585")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneData) data).mLaneSet = value;
        }

        @objid ("1a0d1279-173b-48e0-b0e4-cab259538975")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSetSmClass)this.getTarget()).getLaneDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6cb42e13-e876-4d46-b81e-5ac07fb88bb2")
    public static class BpmnPartitionElementRefSmDependency extends SmSingleDependency {
        @objid ("9af19de1-d425-4006-a36b-4690a4ee3456")
        private SmDependency symetricDep;

        @objid ("c84375b3-8af5-4e96-8ee0-93fc8966ce70")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneData) data).mBpmnPartitionElementRef;
        }

        @objid ("c729bd18-f38b-4a99-97c5-9cefbd7789ea")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneData) data).mBpmnPartitionElementRef = value;
        }

        @objid ("a6f69d43-a3c6-4905-9a4d-489dc0ab10ea")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnBaseElementSmClass)this.getTarget()).getPartitionedLaneRefsDep();
            }
            return this.symetricDep;
        }

    }

}
