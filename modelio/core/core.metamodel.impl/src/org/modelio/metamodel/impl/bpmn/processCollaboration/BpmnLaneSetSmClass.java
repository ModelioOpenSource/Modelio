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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetData;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
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

@objid ("bb3e2389-913f-45ac-a54e-c1807ef28bc2")
public class BpmnLaneSetSmClass extends BpmnBaseElementSmClass {
    @objid ("11af2b5b-f090-4cf0-8842-0a83106edbc1")
    private SmDependency laneDep;

    @objid ("7f015ab2-3e0c-432b-b290-b9bee5b83961")
    private SmDependency processDep;

    @objid ("259d7d1e-3cf2-4dd8-b6df-91100e5f0e6c")
    private SmDependency parentLaneDep;

    @objid ("81cb275d-919f-4892-8aab-785b28f3e3a7")
    private SmDependency subProcessDep;

    @objid ("794005ce-e8c3-4969-8b12-ac82ca35ab4f")
    public BpmnLaneSetSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6e9b217f-1bca-4492-9bff-d47133b8e511")
    @Override
    public String getName() {
        return "BpmnLaneSet";
    }

    @objid ("d6e393ad-ccbc-45d0-9012-cb7f22e29cc6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3cb97843-e2e7-4644-ad3c-4e49cf8a3f7a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnLaneSet.class;
    }

    @objid ("0dfe1313-e3a1-4bd1-b7b7-d98a94610345")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e44aa900-c9eb-4ae6-9b7e-af405c65391c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e4714604-eb03-4db8-85f7-d7617172f357")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnLaneSetObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.laneDep = new LaneSmDependency();
        this.laneDep.init("Lane", this, metamodel.getMClass(BpmnLane.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.laneDep);
        
        this.processDep = new ProcessSmDependency();
        this.processDep.init("Process", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 );
        registerDependency(this.processDep);
        
        this.parentLaneDep = new ParentLaneSmDependency();
        this.parentLaneDep.init("ParentLane", this, metamodel.getMClass(BpmnLane.MQNAME), 0, 1 );
        registerDependency(this.parentLaneDep);
        
        this.subProcessDep = new SubProcessSmDependency();
        this.subProcessDep.init("SubProcess", this, metamodel.getMClass(BpmnSubProcess.MQNAME), 0, 1 );
        registerDependency(this.subProcessDep);
    }

    @objid ("bddfc055-11d1-45b9-878c-521a44498ecb")
    public SmDependency getLaneDep() {
        if (this.laneDep == null) {
        	this.laneDep = this.getDependencyDef("Lane");
        }
        return this.laneDep;
    }

    @objid ("d7f65dc8-43fa-4ad5-8051-f018c737487a")
    public SmDependency getProcessDep() {
        if (this.processDep == null) {
        	this.processDep = this.getDependencyDef("Process");
        }
        return this.processDep;
    }

    @objid ("caca4d42-fbfc-49c1-ad57-9f6330f9b51c")
    public SmDependency getParentLaneDep() {
        if (this.parentLaneDep == null) {
        	this.parentLaneDep = this.getDependencyDef("ParentLane");
        }
        return this.parentLaneDep;
    }

    @objid ("66469439-7ae3-4b3e-9415-1d528373619d")
    public SmDependency getSubProcessDep() {
        if (this.subProcessDep == null) {
        	this.subProcessDep = this.getDependencyDef("SubProcess");
        }
        return this.subProcessDep;
    }

    @objid ("ebd59f07-a1aa-4901-80bc-271cfc95a012")
    private static class BpmnLaneSetObjectFactory implements ISmObjectFactory {
        @objid ("b680ea4a-e20c-43d5-b599-915b7fea29df")
        private BpmnLaneSetSmClass smClass;

        @objid ("798c2a8c-58b4-4c6c-a605-4cfc8845453e")
        public BpmnLaneSetObjectFactory(BpmnLaneSetSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a6b71eac-33da-4f97-b299-7c11b35b0437")
        @Override
        public ISmObjectData createData() {
            return new BpmnLaneSetData(this.smClass);
        }

        @objid ("b48e21d4-b0d9-4d8c-8052-494e7e15627f")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnLaneSetImpl();
        }

    }

    @objid ("3638b885-f77c-414d-b8d8-d114ef53ac15")
    public static class LaneSmDependency extends SmMultipleDependency {
        @objid ("038c4b01-517c-4587-bbde-c69cb3ed802e")
        private SmDependency symetricDep;

        @objid ("e688a0f5-c45d-48b1-8bef-c6d057c80fed")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnLaneSetData)data).mLane != null)? ((BpmnLaneSetData)data).mLane:SmMultipleDependency.EMPTY;
        }

        @objid ("beeedb10-844a-4047-a3be-999c94e9009c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnLaneSetData) data).mLane = values;
        }

        @objid ("4873e0a1-12bb-461e-864b-c1df6ad6582a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSmClass)this.getTarget()).getLaneSetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1a185663-686d-41ee-bdae-c49e8cad3dc9")
    public static class ProcessSmDependency extends SmSingleDependency {
        @objid ("18c634a9-2c4d-40ff-bcdc-d8b46a01d025")
        private SmDependency symetricDep;

        @objid ("b01cab53-0520-47f4-834f-4037fa0bb87f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneSetData) data).mProcess;
        }

        @objid ("0b603f96-6121-4a59-8797-9eae9905b8db")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneSetData) data).mProcess = value;
        }

        @objid ("82e9b373-3c96-4181-8136-0d8088588b90")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getLaneSetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("33acc953-cd9e-435d-8c29-5ece2ff5acd4")
    public static class ParentLaneSmDependency extends SmSingleDependency {
        @objid ("96b64b76-5454-4224-b0fb-9622c1253bfc")
        private SmDependency symetricDep;

        @objid ("30bbe4bb-f703-4b2d-8cf3-ff872558b9fb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneSetData) data).mParentLane;
        }

        @objid ("c2feccd5-39d8-4547-9b2b-732d84dd61dd")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneSetData) data).mParentLane = value;
        }

        @objid ("83e7e078-a7fb-4bea-b1bd-93d63aced0a0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSmClass)this.getTarget()).getChildLaneSetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cc41134f-5015-4fa6-aafd-66699b6648e8")
    public static class SubProcessSmDependency extends SmSingleDependency {
        @objid ("08354204-7877-4b02-b279-ba6ba306867c")
        private SmDependency symetricDep;

        @objid ("ad89a492-526a-4278-a8d1-5366a0e8c52b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLaneSetData) data).mSubProcess;
        }

        @objid ("3998e9ac-2116-4ff4-a6bd-0b64b733e609")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLaneSetData) data).mSubProcess = value;
        }

        @objid ("08a6fc28-2c1e-42ae-99f6-6f6ce2a51db1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSubProcessSmClass)this.getTarget()).getLaneSetDep();
            }
            return this.symetricDep;
        }

    }

}
