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

package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
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

@objid ("497c40d4-2688-4ab1-a8a2-3842dfb95504")
public class BpmnMessageEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("92b5c47a-4678-49a1-8c58-4d5224439cc2")
    private SmDependency messageRefDep;

    @objid ("af520a26-a392-4a17-915a-352c101aca4c")
    private SmDependency operationRefDep;

    @objid ("2eaca350-f009-4694-af2a-092468f2e77b")
    public  BpmnMessageEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("afda6e01-914d-4930-bbb7-ad9abd8bbe84")
    @Override
    public String getName() {
        return "BpmnMessageEventDefinition";
        
    }

    @objid ("a69ac9d5-39d3-47f9-94b6-2b4d4e23df64")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8b73c67d-b8bb-4e6b-b0f7-b36bf299c053")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnMessageEventDefinition.class;
        
    }

    @objid ("487adef6-4068-446d-ad8e-77964fbca726")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("99f2ce73-0787-4aa8-91bb-1a54e290d4ec")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("a97038b0-d3de-4399-8c03-a2625bb8dfde")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnMessageEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.messageRefDep = new MessageRefSmDependency();
        this.messageRefDep.init("MessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.messageRefDep);
        
        this.operationRefDep = new OperationRefSmDependency();
        this.operationRefDep.init("OperationRef", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.operationRefDep);
        
        
    }

    @objid ("21728570-5f99-4000-a54a-2e544af83ff5")
    public SmDependency getMessageRefDep() {
        if (this.messageRefDep == null) {
        	this.messageRefDep = this.getDependencyDef("MessageRef");
        }
        return this.messageRefDep;
    }

    @objid ("65b6e58d-fffb-43ae-b463-ca5ca4a3b108")
    public SmDependency getOperationRefDep() {
        if (this.operationRefDep == null) {
        	this.operationRefDep = this.getDependencyDef("OperationRef");
        }
        return this.operationRefDep;
    }

    @objid ("329ea13c-8b5e-4626-90ee-09cfbf92816d")
    private static class BpmnMessageEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("e620e5ea-8a05-4b94-b816-8352e99f2ee7")
        private BpmnMessageEventDefinitionSmClass smClass;

        @objid ("6d8578a6-97f9-4a3b-8589-41467a82c4e2")
        public  BpmnMessageEventDefinitionObjectFactory(BpmnMessageEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("47521493-d48e-4389-9456-95f80ea72baa")
        @Override
        public ISmObjectData createData() {
            return new BpmnMessageEventDefinitionData(this.smClass);
        }

        @objid ("39c1e2f6-467c-4d0d-ad06-65fdd0daebf2")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnMessageEventDefinitionImpl();
        }

    }

    @objid ("119571a4-63b4-47b2-9d34-cbe4c0674da4")
    public static class MessageRefSmDependency extends SmSingleDependency {
        @objid ("bc5910a0-3e79-4e25-853f-146b7bf8feb4")
        private SmDependency symetricDep;

        @objid ("8366204f-6038-47e3-a226-1e34b657491e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageEventDefinitionData) data).mMessageRef;
        }

        @objid ("3b260f83-81f5-46ab-8811-809ee370a933")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageEventDefinitionData) data).mMessageRef = value;
        }

        @objid ("cec7d65e-83ce-4fd1-bfe8-b910a9f316a1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getEventDefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("68a88789-c095-4787-ab24-7b766b70ab31")
    public static class OperationRefSmDependency extends SmMultipleDependency {
        @objid ("47c4bf5d-fd21-4e1e-b1f5-0fb775eacc05")
        private SmDependency symetricDep;

        @objid ("c2a01133-6a7f-49e7-bf09-7c1567bc7f70")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnMessageEventDefinitionData)data).mOperationRef != null)? ((BpmnMessageEventDefinitionData)data).mOperationRef:SmMultipleDependency.EMPTY;
        }

        @objid ("ecdd5e20-7958-4615-a39d-0c1ec63b5121")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnMessageEventDefinitionData) data).mOperationRef = values;
            
        }

        @objid ("71f53031-20e1-4d84-adae-9ec50fa1852f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getEventDefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

}
