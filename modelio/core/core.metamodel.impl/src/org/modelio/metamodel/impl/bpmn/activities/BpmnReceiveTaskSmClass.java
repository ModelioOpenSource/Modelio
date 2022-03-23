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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
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

@objid ("93a2c0de-6f98-4374-9fa7-dc48f0ae1bd7")
public class BpmnReceiveTaskSmClass extends BpmnTaskSmClass {
    @objid ("0da3486b-bf69-4564-b5e4-46adc3c93933")
    private SmAttribute implementationAtt;

    @objid ("c4d386e1-8866-4a08-b2df-a8c90026f7a4")
    private SmAttribute instanciateAtt;

    @objid ("da10febc-f456-46f5-a89e-3eb6ed06ab0d")
    private SmDependency messageRefDep;

    @objid ("3977eaf8-45f3-4f45-8092-6c1c96048fb2")
    private SmDependency operationRefDep;

    @objid ("3a02922f-38ae-448a-9eec-e08b6d8a08d8")
    public  BpmnReceiveTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("69219549-8f4e-4293-ad25-8f436ce88f6d")
    @Override
    public String getName() {
        return "BpmnReceiveTask";
        
    }

    @objid ("10be6f13-dddc-4c33-830a-673656783386")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6e2653aa-cd40-43f2-a7b8-e47c89d1a62f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnReceiveTask.class;
        
    }

    @objid ("8bc21395-4fc6-4593-a739-9550fc4da7aa")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("2a6fe2ba-72de-4398-a3fa-48bf2dc36242")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("cc3be38a-339f-4e75-9c72-7df570ed43dc")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnReceiveTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.implementationAtt = new ImplementationSmAttribute();
        this.implementationAtt.init("Implementation", this, String.class );
        registerAttribute(this.implementationAtt);
        
        this.instanciateAtt = new InstanciateSmAttribute();
        this.instanciateAtt.init("Instanciate", this, Boolean.class );
        registerAttribute(this.instanciateAtt);
        
        
        // Initialize and register the SmDependency
        this.messageRefDep = new MessageRefSmDependency();
        this.messageRefDep.init("MessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.messageRefDep);
        
        this.operationRefDep = new OperationRefSmDependency();
        this.operationRefDep.init("OperationRef", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.operationRefDep);
        
        
    }

    @objid ("7a0b7b85-7980-480a-950a-adfedf773b4f")
    public SmAttribute getImplementationAtt() {
        if (this.implementationAtt == null) {
        	this.implementationAtt = this.getAttributeDef("Implementation");
        }
        return this.implementationAtt;
    }

    @objid ("7c3acb56-2399-4d50-907b-f1ab26f51113")
    public SmAttribute getInstanciateAtt() {
        if (this.instanciateAtt == null) {
        	this.instanciateAtt = this.getAttributeDef("Instanciate");
        }
        return this.instanciateAtt;
    }

    @objid ("79fe90b5-f82b-416c-9bb1-5df5f70f2e63")
    public SmDependency getMessageRefDep() {
        if (this.messageRefDep == null) {
        	this.messageRefDep = this.getDependencyDef("MessageRef");
        }
        return this.messageRefDep;
    }

    @objid ("c2794e39-993b-4c8d-b38e-886a0c355d1b")
    public SmDependency getOperationRefDep() {
        if (this.operationRefDep == null) {
        	this.operationRefDep = this.getDependencyDef("OperationRef");
        }
        return this.operationRefDep;
    }

    @objid ("26320e90-a096-4617-9376-402e1c6fa403")
    private static class BpmnReceiveTaskObjectFactory implements ISmObjectFactory {
        @objid ("b775d14d-b00f-4c68-8c99-68162d4a53ec")
        private BpmnReceiveTaskSmClass smClass;

        @objid ("4d19d9f8-c612-4147-8c66-7c3f975efc67")
        public  BpmnReceiveTaskObjectFactory(BpmnReceiveTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f9f85a19-ec3b-4d6c-ae02-ec67463e96d5")
        @Override
        public ISmObjectData createData() {
            return new BpmnReceiveTaskData(this.smClass);
        }

        @objid ("7a4f39d4-de44-48c8-bbb2-8e851700588a")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnReceiveTaskImpl();
        }

    }

    @objid ("09c2e8e9-e165-4fc9-aec7-4543bce875a1")
    public static class ImplementationSmAttribute extends SmAttribute {
        @objid ("2c2a40df-8511-4b22-9d9b-ac5b2a0dbcaf")
        public Object getValue(ISmObjectData data) {
            return ((BpmnReceiveTaskData) data).mImplementation;
        }

        @objid ("bb33b9de-afae-4aa8-8110-640700ea19e4")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnReceiveTaskData) data).mImplementation = value;
        }

    }

    @objid ("0e2d0131-74b8-4fd0-94af-642987e8e783")
    public static class InstanciateSmAttribute extends SmAttribute {
        @objid ("41994fd4-813e-4db8-92a9-ee19706c92fa")
        public Object getValue(ISmObjectData data) {
            return ((BpmnReceiveTaskData) data).mInstanciate;
        }

        @objid ("b1a322e0-67df-4b7a-bef9-bc175c63ec01")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnReceiveTaskData) data).mInstanciate = value;
        }

    }

    @objid ("5540b663-2994-4b10-b061-4d402925ac53")
    public static class MessageRefSmDependency extends SmSingleDependency {
        @objid ("85882ae1-13a3-4c11-b742-45dbab1bee8b")
        private SmDependency symetricDep;

        @objid ("62a1ab54-3781-4979-9d22-0fc5281e1833")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnReceiveTaskData) data).mMessageRef;
        }

        @objid ("0460c1b1-8f65-4c4e-a128-3896b2ef79cc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnReceiveTaskData) data).mMessageRef = value;
        }

        @objid ("21b5eea5-10db-49bf-b43e-b900b911ea7e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getReceiverDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("f7074a04-a605-47fb-ad01-816892b50f21")
    public static class OperationRefSmDependency extends SmSingleDependency {
        @objid ("7d7b938c-4b3f-4878-8ac0-5a2a59229f9e")
        private SmDependency symetricDep;

        @objid ("59f1176f-8ab0-4851-a3f7-6b6c276abeed")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnReceiveTaskData) data).mOperationRef;
        }

        @objid ("fa301bde-bce8-4a33-9e10-4a03b7c1dabf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnReceiveTaskData) data).mOperationRef = value;
        }

        @objid ("b3b98f98-f65f-41a8-ae3b-23bf813d6366")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getReceiverDep();
            }
            return this.symetricDep;
            
        }

    }

}
