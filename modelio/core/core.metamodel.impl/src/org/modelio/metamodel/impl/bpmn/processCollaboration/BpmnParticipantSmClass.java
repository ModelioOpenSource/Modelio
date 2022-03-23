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
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnEndPointSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnInterfaceSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
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

@objid ("5db43980-04e8-4f81-91a7-b9b3b1c4e86e")
public class BpmnParticipantSmClass extends BpmnBaseElementSmClass {
    @objid ("51bfebde-7e33-4ca8-bf62-df2517199f6e")
    private SmAttribute multiplicityMinAtt;

    @objid ("477e8dd2-d124-48be-941e-90d5264e5d13")
    private SmAttribute multiplicityMaxAtt;

    @objid ("c9039de4-8125-4012-b5f8-6c0ee4cf6401")
    private SmDependency processDep;

    @objid ("37fa353f-12ff-4582-85fd-4c5daf7445dc")
    private SmDependency containerDep;

    @objid ("2196eac1-cd8c-4e0a-8543-5d7556052e54")
    private SmDependency endPointRefsDep;

    @objid ("a22fa974-0eb5-47f7-859b-7d2ec159e60b")
    private SmDependency interfaceRefsDep;

    @objid ("95d7ab9c-c28e-4813-a356-8669edfc6ae8")
    public  BpmnParticipantSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e7d6e836-2ace-48c3-8608-68a158334ae8")
    @Override
    public String getName() {
        return "BpmnParticipant";
        
    }

    @objid ("864ec19f-6eb0-4701-b5e7-771e64c09f9c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("aea3befa-5dde-4d5b-a6e7-793c4ee5bae7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnParticipant.class;
        
    }

    @objid ("7b5e3233-e9b5-4eec-8c11-0aaa784da4a6")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("6fbc3920-9abe-4241-ba72-65847b8b48d2")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("fb7a0296-341c-4511-9fc0-ed3d09101c7b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnParticipantObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, Integer.class );
        registerAttribute(this.multiplicityMinAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, Integer.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        
        // Initialize and register the SmDependency
        this.processDep = new ProcessSmDependency();
        this.processDep.init("Process", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.processDep);
        
        this.containerDep = new ContainerSmDependency();
        this.containerDep.init("Container", this, metamodel.getMClass(BpmnCollaboration.MQNAME), 1, 1 );
        registerDependency(this.containerDep);
        
        this.endPointRefsDep = new EndPointRefsSmDependency();
        this.endPointRefsDep.init("EndPointRefs", this, metamodel.getMClass(BpmnEndPoint.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.endPointRefsDep);
        
        this.interfaceRefsDep = new InterfaceRefsSmDependency();
        this.interfaceRefsDep.init("InterfaceRefs", this, metamodel.getMClass(BpmnInterface.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.interfaceRefsDep);
        
        
    }

    @objid ("2e6e51a0-ff51-4a74-a020-a2dfc5f4af5e")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("711c4962-1381-48b4-8b3d-44e6ff2dae5d")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("5324e859-2a65-4899-987b-6d7dd1a38d9b")
    public SmDependency getProcessDep() {
        if (this.processDep == null) {
        	this.processDep = this.getDependencyDef("Process");
        }
        return this.processDep;
    }

    @objid ("c3fbc613-43a3-41a2-ac89-3ec6f6fce6cc")
    public SmDependency getContainerDep() {
        if (this.containerDep == null) {
        	this.containerDep = this.getDependencyDef("Container");
        }
        return this.containerDep;
    }

    @objid ("07ab7c0c-8e07-4cce-a569-0b42ccf264d5")
    public SmDependency getEndPointRefsDep() {
        if (this.endPointRefsDep == null) {
        	this.endPointRefsDep = this.getDependencyDef("EndPointRefs");
        }
        return this.endPointRefsDep;
    }

    @objid ("065f3fe5-f7e4-4b8c-9440-9a1807481075")
    public SmDependency getInterfaceRefsDep() {
        if (this.interfaceRefsDep == null) {
        	this.interfaceRefsDep = this.getDependencyDef("InterfaceRefs");
        }
        return this.interfaceRefsDep;
    }

    @objid ("96e52739-0fce-4616-b28f-845bdf84b0d8")
    private static class BpmnParticipantObjectFactory implements ISmObjectFactory {
        @objid ("394aedff-2efa-4f3b-8350-b2a0eb7b6d63")
        private BpmnParticipantSmClass smClass;

        @objid ("b2e35aeb-b2d1-4f32-8218-25f3f11740d9")
        public  BpmnParticipantObjectFactory(BpmnParticipantSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("885380a6-e365-42a2-a85c-1e28a300295e")
        @Override
        public ISmObjectData createData() {
            return new BpmnParticipantData(this.smClass);
        }

        @objid ("2eceaa91-efbe-4c9b-bf3d-e3ac75d614de")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnParticipantImpl();
        }

    }

    @objid ("82e677ab-8a50-43e1-99dc-c05a62ebbb95")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("4430a7de-149f-4ba8-a403-e2c90ca065fb")
        public Object getValue(ISmObjectData data) {
            return ((BpmnParticipantData) data).mMultiplicityMin;
        }

        @objid ("470de769-f1fd-4191-be8b-0f4f4eb7d3eb")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnParticipantData) data).mMultiplicityMin = value;
        }

    }

    @objid ("e5c4d404-4447-4a93-8333-bf235646006e")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("7c8b670f-f8fb-411b-a194-1e37aaa439cf")
        public Object getValue(ISmObjectData data) {
            return ((BpmnParticipantData) data).mMultiplicityMax;
        }

        @objid ("bd2e390d-ef8c-45eb-9e36-4f24a7b3c702")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnParticipantData) data).mMultiplicityMax = value;
        }

    }

    @objid ("cb487a13-2330-4bfb-a5a4-b368353d2c91")
    public static class ProcessSmDependency extends SmSingleDependency {
        @objid ("1ac255ac-db09-4b63-bee6-b53baead05dd")
        private SmDependency symetricDep;

        @objid ("0530984d-f060-41bf-b70a-5c91706bc5b2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnParticipantData) data).mProcess;
        }

        @objid ("8db9199c-2400-4202-987e-eb1aafcaf2ff")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnParticipantData) data).mProcess = value;
        }

        @objid ("5647d607-3189-40d3-a204-8c8e4215b78c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getParticipantDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("24aed84f-6a9a-48cf-a23c-4e3248756b6b")
    public static class ContainerSmDependency extends SmSingleDependency {
        @objid ("1c417ef1-9a54-435a-a68e-b514bea674b0")
        private SmDependency symetricDep;

        @objid ("d14f9421-5878-4308-8ad4-a7cbcac139f9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnParticipantData) data).mContainer;
        }

        @objid ("8939674e-d23d-4257-8766-915ff56507bb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnParticipantData) data).mContainer = value;
        }

        @objid ("1a1f27cc-9053-4e59-bb0e-19bfa904433f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCollaborationSmClass)this.getTarget()).getParticipantsDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d9d75a79-b876-41e4-83d3-ed8abe4d2c46")
    public static class EndPointRefsSmDependency extends SmMultipleDependency {
        @objid ("4691e9dd-b4ff-4ebe-bc75-ae5d53a46f9d")
        private SmDependency symetricDep;

        @objid ("681b0090-eef6-41c4-b29d-bc724eb39e4a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnParticipantData)data).mEndPointRefs != null)? ((BpmnParticipantData)data).mEndPointRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("80546395-ffbb-49fb-bcc7-7bd6d5d22292")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnParticipantData) data).mEndPointRefs = values;
            
        }

        @objid ("126161b4-a999-4a87-8aa1-36bf029dcf34")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnEndPointSmClass)this.getTarget()).getParticipantRefsDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("e034d505-0ef5-4259-8f8f-f6dbb0be0f6e")
    public static class InterfaceRefsSmDependency extends SmMultipleDependency {
        @objid ("fd8f8c69-5ce4-4339-ab27-82172f07122d")
        private SmDependency symetricDep;

        @objid ("62f70770-4a6a-4f1a-9d34-5b0a2120a158")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnParticipantData)data).mInterfaceRefs != null)? ((BpmnParticipantData)data).mInterfaceRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("ab2e78fa-5a7a-4083-a1e1-68ebeadd62f1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnParticipantData) data).mInterfaceRefs = values;
            
        }

        @objid ("8189f228-fcaf-459a-9591-bac06a9347e8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnInterfaceSmClass)this.getTarget()).getParticipantRefDep();
            }
            return this.symetricDep;
            
        }

    }

}
