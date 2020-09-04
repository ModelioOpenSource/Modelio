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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnSequenceFlowDataAssociationData;
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

@objid ("1be2fce9-1ef8-4ede-bafa-7464c144738d")
public class BpmnSequenceFlowDataAssociationSmClass extends BpmnBaseElementSmClass {
    @objid ("664d578d-0c5e-4aca-b2c0-97b39ff45156")
    private SmDependency connectedDep;

    @objid ("44b7553a-88db-4a05-bb2f-d7d066ef22f5")
    private SmDependency dataAssociationDep;

    @objid ("be62fd75-4e8a-47ed-b96b-4495d2585aea")
    public BpmnSequenceFlowDataAssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0db05c2a-6cba-4153-b69b-04b623f2cc50")
    @Override
    public String getName() {
        return "BpmnSequenceFlowDataAssociation";
    }

    @objid ("955aa4f2-27b5-4bec-9acf-ec8300d96a25")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("46bdad85-3cb2-4269-a1d8-b71bd3ae67bd")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSequenceFlowDataAssociation.class;
    }

    @objid ("a73a8976-d144-4957-81ea-ea2d756494b5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("df1f31c5-4d7d-4551-8c85-5b90a3a2e076")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("340a706c-b0ad-4465-9b6c-f9d297521af1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnSequenceFlowDataAssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.connectedDep = new ConnectedSmDependency();
        this.connectedDep.init("Connected", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.connectedDep);
        
        this.dataAssociationDep = new DataAssociationSmDependency();
        this.dataAssociationDep.init("DataAssociation", this, metamodel.getMClass(BpmnDataAssociation.MQNAME), 2, 2 , SmDirective.SMCDPARTOF);
        registerDependency(this.dataAssociationDep);
    }

    @objid ("f913c9ba-7af6-4f05-a067-e9f5e77cd531")
    public SmDependency getConnectedDep() {
        if (this.connectedDep == null) {
        	this.connectedDep = this.getDependencyDef("Connected");
        }
        return this.connectedDep;
    }

    @objid ("49b30ce5-82f0-46bf-aa41-79ba22a92775")
    public SmDependency getDataAssociationDep() {
        if (this.dataAssociationDep == null) {
        	this.dataAssociationDep = this.getDependencyDef("DataAssociation");
        }
        return this.dataAssociationDep;
    }

    @objid ("e669d70a-540f-43bc-b254-16ad5ee4b729")
    private static class BpmnSequenceFlowDataAssociationObjectFactory implements ISmObjectFactory {
        @objid ("7c62f01f-f9c2-464a-81a3-f73cca746efa")
        private BpmnSequenceFlowDataAssociationSmClass smClass;

        @objid ("77fffab8-4954-4a86-887d-597487fadb2b")
        public BpmnSequenceFlowDataAssociationObjectFactory(BpmnSequenceFlowDataAssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b367b340-f057-4dcf-8538-00f7ede88ff2")
        @Override
        public ISmObjectData createData() {
            return new BpmnSequenceFlowDataAssociationData(this.smClass);
        }

        @objid ("a03f4745-fb3b-46da-8b92-d352566f92de")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSequenceFlowDataAssociationImpl();
        }

    }

    @objid ("f1e4cf31-bfd3-4254-a6b9-b1ceae4d48a2")
    public static class ConnectedSmDependency extends SmSingleDependency {
        @objid ("0c3ff58d-09fa-46ea-ab0c-dcefd4beb397")
        private SmDependency symetricDep;

        @objid ("d32e8a2b-9bc3-474a-abfe-219672da5fdb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSequenceFlowDataAssociationData) data).mConnected;
        }

        @objid ("66683ee4-35e2-4c89-9dea-8080664454eb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSequenceFlowDataAssociationData) data).mConnected = value;
        }

        @objid ("bd022bbc-ec33-494d-8e8b-860d5f20f0f7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getConnectorDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a21b9dd2-e68f-4e0b-8f02-50ca0d73a3d1")
    public static class DataAssociationSmDependency extends SmMultipleDependency {
        @objid ("c9c7fb94-6bc6-4029-b04d-7ef396108681")
        private SmDependency symetricDep;

        @objid ("28ab2f48-f7ac-4d69-a379-be12928b3e54")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnSequenceFlowDataAssociationData)data).mDataAssociation != null)? ((BpmnSequenceFlowDataAssociationData)data).mDataAssociation:SmMultipleDependency.EMPTY;
        }

        @objid ("f079303a-90be-4ae7-8bc6-9f106d493338")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnSequenceFlowDataAssociationData) data).mDataAssociation = values;
        }

        @objid ("703b357c-37b3-4031-bfce-b157c6ace4cc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataAssociationSmClass)this.getTarget()).getVisualShortCutDep();
            }
            return this.symetricDep;
        }

    }

}
