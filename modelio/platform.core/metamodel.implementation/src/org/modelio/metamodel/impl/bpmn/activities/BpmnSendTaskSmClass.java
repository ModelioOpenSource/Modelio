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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSendTaskData;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskSmClass;
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

@objid ("036bd8e7-e725-4cb4-89a6-00823472c340")
public class BpmnSendTaskSmClass extends BpmnTaskSmClass {
    @objid ("6470c288-fbab-44d4-a90f-2698b87e449b")
    private SmAttribute implementationAtt;

    @objid ("152726b4-9db1-46c4-96c9-ec02c662d648")
    private SmDependency messageRefDep;

    @objid ("e4f42640-5899-4fac-a6b4-002da98ac08b")
    private SmDependency operationRefDep;

    @objid ("d13092ad-1783-4a21-a27a-12a6de379dd4")
    public BpmnSendTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1557a84e-c812-4c50-ae35-58d3d58728e4")
    @Override
    public String getName() {
        return "BpmnSendTask";
    }

    @objid ("c7035e81-7cfe-4131-9754-b5db9232cc22")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b04d3bcd-00e4-4e0f-9b7c-d8742937f19c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSendTask.class;
    }

    @objid ("c95afb7a-afea-4c37-a9c3-cd0626010e70")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("96246582-f61a-4d7f-b7d6-f04c6b24ca7c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ebab3628-1531-46b3-a231-1db270c995ec")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnSendTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.implementationAtt = new ImplementationSmAttribute();
        this.implementationAtt.init("Implementation", this, String.class );
        registerAttribute(this.implementationAtt);
        
        
        // Initialize and register the SmDependency
        this.messageRefDep = new MessageRefSmDependency();
        this.messageRefDep.init("MessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.messageRefDep);
        
        this.operationRefDep = new OperationRefSmDependency();
        this.operationRefDep.init("OperationRef", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.operationRefDep);
    }

    @objid ("e3fabc41-7754-4c8f-8af6-8d9eb4200a74")
    public SmAttribute getImplementationAtt() {
        if (this.implementationAtt == null) {
        	this.implementationAtt = this.getAttributeDef("Implementation");
        }
        return this.implementationAtt;
    }

    @objid ("12608fce-35e2-4e9d-9f07-dbf8e7878aab")
    public SmDependency getMessageRefDep() {
        if (this.messageRefDep == null) {
        	this.messageRefDep = this.getDependencyDef("MessageRef");
        }
        return this.messageRefDep;
    }

    @objid ("78f2db48-ea31-4367-a66f-2eb1e391f61c")
    public SmDependency getOperationRefDep() {
        if (this.operationRefDep == null) {
        	this.operationRefDep = this.getDependencyDef("OperationRef");
        }
        return this.operationRefDep;
    }

    @objid ("78776911-fb62-491d-ae29-9b7221eac4b4")
    private static class BpmnSendTaskObjectFactory implements ISmObjectFactory {
        @objid ("5a055ca8-67ad-4f73-aa4b-ddab9da9fb4d")
        private BpmnSendTaskSmClass smClass;

        @objid ("7f17497c-4fb1-4ea0-9afa-2cd8c5f9f457")
        public BpmnSendTaskObjectFactory(BpmnSendTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("74cc8e7d-fc25-49ee-a1ae-bb94af378ff8")
        @Override
        public ISmObjectData createData() {
            return new BpmnSendTaskData(this.smClass);
        }

        @objid ("869d2518-d97f-4415-829c-2914f2c8496a")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSendTaskImpl();
        }

    }

    @objid ("92743483-76d3-4879-a517-dd87a1d1e5e9")
    public static class ImplementationSmAttribute extends SmAttribute {
        @objid ("5ecdc201-50fa-49c8-ade9-43a4de1f5611")
        public Object getValue(ISmObjectData data) {
            return ((BpmnSendTaskData) data).mImplementation;
        }

        @objid ("e7a106c9-cd40-48b8-b4fc-991c4ffd53a8")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnSendTaskData) data).mImplementation = value;
        }

    }

    @objid ("c0dfdc44-915a-4127-b15a-c097a8e19837")
    public static class MessageRefSmDependency extends SmSingleDependency {
        @objid ("d7806022-4105-410e-9599-d08130d58ae3")
        private SmDependency symetricDep;

        @objid ("7248fcda-e4a7-4875-b57e-ebbe3c52f3bb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSendTaskData) data).mMessageRef;
        }

        @objid ("6e97b762-9238-40c5-a921-082eda040b46")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSendTaskData) data).mMessageRef = value;
        }

        @objid ("8e1c7460-6760-409d-8d57-d9ee18f50a77")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getSenderDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("63fc90d8-d1d3-4f27-b69f-2496aa90d7fb")
    public static class OperationRefSmDependency extends SmSingleDependency {
        @objid ("a1fdba86-74be-4959-8f7a-c8e8e4daeba4")
        private SmDependency symetricDep;

        @objid ("ace3d105-f0be-4ab7-af20-a689446ae0aa")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSendTaskData) data).mOperationRef;
        }

        @objid ("52022bf5-0016-48f5-9c27-941a302c439a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSendTaskData) data).mOperationRef = value;
        }

        @objid ("5d7485da-8d4f-4997-83ce-6c6dcfac24fc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getSenderDep();
            }
            return this.symetricDep;
        }

    }

}
