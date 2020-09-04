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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessData;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactSmClass;
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

@objid ("ca96dca3-70f3-4113-bfa9-e4768ccb42e1")
public class BpmnSubProcessSmClass extends BpmnActivitySmClass {
    @objid ("e4b6d371-23ab-431d-9a92-bbb221a3c9d1")
    private SmDependency artifactDep;

    @objid ("340656f7-4d99-45fd-adc3-8ccc98286fa1")
    private SmDependency flowElementDep;

    @objid ("92906aa1-3c8b-4180-9ad0-e530ca3d3fbc")
    private SmDependency laneSetDep;

    @objid ("65efb0f5-5892-48d0-96b2-fa2014b420ec")
    public BpmnSubProcessSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("85163652-317f-4eb9-a5e5-e73c7b46ace6")
    @Override
    public String getName() {
        return "BpmnSubProcess";
    }

    @objid ("53a72831-6a45-4b46-9ce4-94e14f93bdf0")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("965b0ed6-1605-4c1c-89c5-9af85827df71")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSubProcess.class;
    }

    @objid ("fd7f003c-cd1c-424a-bf45-e0751a0f0e93")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("1321d19c-d9d2-4b79-a980-a1c1bc628877")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("75bcc749-92aa-4894-b520-cb938c7e5d20")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnActivity.MQNAME);
        this.registerFactory(new BpmnSubProcessObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.artifactDep = new ArtifactSmDependency();
        this.artifactDep.init("Artifact", this, metamodel.getMClass(BpmnArtifact.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.artifactDep);
        
        this.flowElementDep = new FlowElementSmDependency();
        this.flowElementDep.init("FlowElement", this, metamodel.getMClass(BpmnFlowElement.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.flowElementDep);
        
        this.laneSetDep = new LaneSetSmDependency();
        this.laneSetDep.init("LaneSet", this, metamodel.getMClass(BpmnLaneSet.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.laneSetDep);
    }

    @objid ("ff6848b6-160c-48ff-8559-e362b40f32c1")
    public SmDependency getArtifactDep() {
        if (this.artifactDep == null) {
        	this.artifactDep = this.getDependencyDef("Artifact");
        }
        return this.artifactDep;
    }

    @objid ("bc5595b5-c7bf-4bbe-ae8f-30830cff2ca0")
    public SmDependency getFlowElementDep() {
        if (this.flowElementDep == null) {
        	this.flowElementDep = this.getDependencyDef("FlowElement");
        }
        return this.flowElementDep;
    }

    @objid ("fcfb06cf-2fd3-4b41-a7fc-aeeb524fdc34")
    public SmDependency getLaneSetDep() {
        if (this.laneSetDep == null) {
        	this.laneSetDep = this.getDependencyDef("LaneSet");
        }
        return this.laneSetDep;
    }

    @objid ("0b070c73-6f8f-4c0f-b950-8e0b8a996709")
    private static class BpmnSubProcessObjectFactory implements ISmObjectFactory {
        @objid ("919a56c7-c692-4eb7-a3dd-f6ad3129ef61")
        private BpmnSubProcessSmClass smClass;

        @objid ("fa878b39-5267-4036-99d8-9e70e0e1a774")
        public BpmnSubProcessObjectFactory(BpmnSubProcessSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("abcf3ae1-fdcc-4891-9a41-d1bbc0ce5b42")
        @Override
        public ISmObjectData createData() {
            return new BpmnSubProcessData(this.smClass);
        }

        @objid ("def2a760-ac68-4803-b894-21690fd732fa")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSubProcessImpl();
        }

    }

    @objid ("639a833a-d610-4ad7-97eb-6090e00ea15a")
    public static class ArtifactSmDependency extends SmMultipleDependency {
        @objid ("237e3412-3042-47f9-90b2-bf3b19704227")
        private SmDependency symetricDep;

        @objid ("94fc4e42-9f9a-4b1e-adb2-07bd663cacd8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnSubProcessData)data).mArtifact != null)? ((BpmnSubProcessData)data).mArtifact:SmMultipleDependency.EMPTY;
        }

        @objid ("640ccc83-b152-4523-8270-5785a8abe30f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnSubProcessData) data).mArtifact = values;
        }

        @objid ("380aaaee-42fc-451e-bcbd-bf028b0e1806")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnArtifactSmClass)this.getTarget()).getSubProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1149619f-fb1d-4b04-a3da-0a45b2392d93")
    public static class FlowElementSmDependency extends SmMultipleDependency {
        @objid ("c8543ba1-cef9-4b01-a509-9dd46ed02e60")
        private SmDependency symetricDep;

        @objid ("ba524487-10ed-48f3-8a7a-9aaaf83214df")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnSubProcessData)data).mFlowElement != null)? ((BpmnSubProcessData)data).mFlowElement:SmMultipleDependency.EMPTY;
        }

        @objid ("f6582ec5-036f-417d-ba7d-07d439176819")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnSubProcessData) data).mFlowElement = values;
        }

        @objid ("8477022a-dd11-4c86-87b8-9fdc1719dbd1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowElementSmClass)this.getTarget()).getSubProcessDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("37a8d9ac-5f4b-44f8-98d4-0250ec62975b")
    public static class LaneSetSmDependency extends SmSingleDependency {
        @objid ("6853b6db-68d9-47f4-b87d-01dd33b0f3db")
        private SmDependency symetricDep;

        @objid ("63361d1d-5b2d-4e59-a4d8-f5e5bed9f981")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSubProcessData) data).mLaneSet;
        }

        @objid ("d41858b2-debf-4d05-9db7-66cb16d0f97e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSubProcessData) data).mLaneSet = value;
        }

        @objid ("5801cbe5-180e-41ef-b9eb-089547aab845")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSetSmClass)this.getTarget()).getSubProcessDep();
            }
            return this.symetricDep;
        }

    }

}
