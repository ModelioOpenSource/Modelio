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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnGroupSmClass;
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

@objid ("0fb485f4-fbfb-45e4-abbf-71abd33d6804")
public class BpmnFlowElementSmClass extends BpmnBaseElementSmClass {
    @objid ("26f1d194-12f8-4357-9926-7d90641e57b0")
    private SmAttribute triggeredByEventAtt;

    @objid ("6410e1c6-6863-40ad-a0cb-547655ac0b7e")
    private SmDependency groupsDep;

    @objid ("64c2a0a1-e083-49d8-afd6-77a82ad33619")
    private SmDependency subProcessDep;

    @objid ("ca2b1660-6104-4d67-b6ff-7695a17243fd")
    private SmDependency laneDep;

    @objid ("5658fffe-4460-431e-83a8-b58ad3351795")
    private SmDependency containerDep;

    @objid ("37c9fd3b-9f19-41e1-807a-e3f1e5eadc65")
    public BpmnFlowElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0d8cfb0d-fc54-409d-937d-96fafd94211b")
    @Override
    public String getName() {
        return "BpmnFlowElement";
    }

    @objid ("7c06df87-bbed-4a7c-804b-8b72519f34b6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("35ad7b12-3c50-4a99-8970-aec82019ce27")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnFlowElement.class;
    }

    @objid ("5817696b-cbd7-4e05-b34b-a06a7b729976")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e9d78841-9837-4e7e-9715-8e5d23985c82")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("b86918be-9ec1-4cc1-94a2-ea841feecca2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnFlowElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.triggeredByEventAtt = new TriggeredByEventSmAttribute();
        this.triggeredByEventAtt.init("TriggeredByEvent", this, Boolean.class );
        registerAttribute(this.triggeredByEventAtt);
        
        
        // Initialize and register the SmDependency
        this.groupsDep = new GroupsSmDependency();
        this.groupsDep.init("Groups", this, metamodel.getMClass(BpmnGroup.MQNAME), 0, -1 );
        registerDependency(this.groupsDep);
        
        this.subProcessDep = new SubProcessSmDependency();
        this.subProcessDep.init("SubProcess", this, metamodel.getMClass(BpmnSubProcess.MQNAME), 0, 1 );
        registerDependency(this.subProcessDep);
        
        this.laneDep = new LaneSmDependency();
        this.laneDep.init("Lane", this, metamodel.getMClass(BpmnLane.MQNAME), 0, -1 );
        registerDependency(this.laneDep);
        
        this.containerDep = new ContainerSmDependency();
        this.containerDep.init("Container", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 );
        registerDependency(this.containerDep);
    }

    @objid ("c7d59838-bbcc-45c8-94a8-6820c4610cbf")
    public SmAttribute getTriggeredByEventAtt() {
        if (this.triggeredByEventAtt == null) {
        	this.triggeredByEventAtt = this.getAttributeDef("TriggeredByEvent");
        }
        return this.triggeredByEventAtt;
    }

    @objid ("d45ffdec-68ec-49e5-a735-f77cdc7b29b2")
    public SmDependency getGroupsDep() {
        if (this.groupsDep == null) {
        	this.groupsDep = this.getDependencyDef("Groups");
        }
        return this.groupsDep;
    }

    @objid ("1893d99a-e2dd-4d76-b0a2-1f124a08db2f")
    public SmDependency getSubProcessDep() {
        if (this.subProcessDep == null) {
        	this.subProcessDep = this.getDependencyDef("SubProcess");
        }
        return this.subProcessDep;
    }

    @objid ("8763c671-4cd4-4a8f-8257-77bb9114a152")
    public SmDependency getLaneDep() {
        if (this.laneDep == null) {
        	this.laneDep = this.getDependencyDef("Lane");
        }
        return this.laneDep;
    }

    @objid ("4f9f125a-599b-43c7-a1e8-e8fc4e577463")
    public SmDependency getContainerDep() {
        if (this.containerDep == null) {
        	this.containerDep = this.getDependencyDef("Container");
        }
        return this.containerDep;
    }

    @objid ("af315101-89f4-4ee2-9609-6b9f5e6e9a54")
    private static class BpmnFlowElementObjectFactory implements ISmObjectFactory {
        @objid ("a558a08d-7cd3-4939-857e-c9484c605468")
        private BpmnFlowElementSmClass smClass;

        @objid ("bf883f07-f416-4676-bf2a-ab966464bad5")
        public BpmnFlowElementObjectFactory(BpmnFlowElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1c4c02d6-0759-403c-8276-4a01f999dd67")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("83b46ac9-404e-4570-8e08-5602d34a4edd")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("3a7066d0-6810-4166-9f75-5f92d9484ea1")
    public static class TriggeredByEventSmAttribute extends SmAttribute {
        @objid ("6bf27737-0486-4f58-b18f-0894d18357b2")
        public Object getValue(ISmObjectData data) {
            return ((BpmnFlowElementData) data).mTriggeredByEvent;
        }

        @objid ("f3f38e16-0e5c-4026-a819-cfb713bacb5c")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnFlowElementData) data).mTriggeredByEvent = value;
        }

    }

    @objid ("3356a559-baec-426c-8366-a9c0ae050653")
    public static class GroupsSmDependency extends SmMultipleDependency {
        @objid ("79bf3c17-19f8-481b-81b2-d422693823e6")
        private SmDependency symetricDep;

        @objid ("29892146-8038-447e-961f-7a46c6a6311a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnFlowElementData)data).mGroups != null)? ((BpmnFlowElementData)data).mGroups:SmMultipleDependency.EMPTY;
        }

        @objid ("fbad0c74-66a3-4723-ba50-491951911c70")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnFlowElementData) data).mGroups = values;
        }

        @objid ("d1ff3d2f-757b-4e41-a51c-404c494188a4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnGroupSmClass)this.getTarget()).getCategorizedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2a991d88-6f27-46a6-8d2c-1836d487a5af")
    public static class SubProcessSmDependency extends SmSingleDependency {
        @objid ("f8728cfb-e5e3-4eac-be18-7131abcaa5ef")
        private SmDependency symetricDep;

        @objid ("c6547a21-3b01-43b4-8bd2-6f63dc106f15")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnFlowElementData) data).mSubProcess;
        }

        @objid ("c72c6255-4db5-4317-96bf-ce4a99e70bd9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnFlowElementData) data).mSubProcess = value;
        }

        @objid ("43eaae01-8369-4c89-9e22-704129f23e43")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSubProcessSmClass)this.getTarget()).getFlowElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("edd357b3-cc7b-45b7-a202-1b864a579f0b")
    public static class LaneSmDependency extends SmMultipleDependency {
        @objid ("54a8e4f3-783a-4ecc-9924-95960a3ea733")
        private SmDependency symetricDep;

        @objid ("936b30c5-0443-4e0d-a17f-be5f2f4a2847")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnFlowElementData)data).mLane != null)? ((BpmnFlowElementData)data).mLane:SmMultipleDependency.EMPTY;
        }

        @objid ("2f3a51a1-bb55-40de-b814-ea6d7e429652")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnFlowElementData) data).mLane = values;
        }

        @objid ("6c8fa6fe-469f-48f1-a204-1aec9d1b5040")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnLaneSmClass)this.getTarget()).getFlowElementRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("eb7e90e0-01b3-4b2e-bdba-73e1021ac005")
    public static class ContainerSmDependency extends SmSingleDependency {
        @objid ("50eb73fa-76ce-4897-89ba-afd4a8e26782")
        private SmDependency symetricDep;

        @objid ("d4c8c316-3f67-499c-bb4f-f02100c6f975")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnFlowElementData) data).mContainer;
        }

        @objid ("2ae6e027-f1cb-4cb1-83fc-e5569e657700")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnFlowElementData) data).mContainer = value;
        }

        @objid ("99556c2f-2d30-477b-afb0-18e9fabdeddd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getFlowElementDep();
            }
            return this.symetricDep;
        }

    }

}
