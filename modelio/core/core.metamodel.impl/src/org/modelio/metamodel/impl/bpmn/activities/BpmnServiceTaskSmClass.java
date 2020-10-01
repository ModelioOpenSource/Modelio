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
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.impl.bpmn.activities.BpmnServiceTaskData;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationSmClass;
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

@objid ("7fd93dec-e8fc-4871-8288-ff273df77741")
public class BpmnServiceTaskSmClass extends BpmnTaskSmClass {
    @objid ("4988924c-ddbe-4bee-aec1-b3143842479c")
    private SmAttribute implementationAtt;

    @objid ("44c0a1a9-7fc6-44c9-bd5e-f00a84364458")
    private SmDependency operationRefDep;

    @objid ("b667b60a-6ae6-4d4f-9779-e651e9c3b3dd")
    public BpmnServiceTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e128a8db-c7ef-42d1-9006-c267f0658946")
    @Override
    public String getName() {
        return "BpmnServiceTask";
    }

    @objid ("328060af-aea1-4201-a102-aef29d5968a2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("633375b4-015a-4235-a559-12db8d7b223b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnServiceTask.class;
    }

    @objid ("9174099a-28a8-4e30-a750-af4c39b2ee3c")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("c2c7b9df-79f5-423d-9777-21e248491db2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7a0342d6-41a9-43e2-943b-53e45a472298")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnServiceTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.implementationAtt = new ImplementationSmAttribute();
        this.implementationAtt.init("Implementation", this, String.class );
        registerAttribute(this.implementationAtt);
        
        
        // Initialize and register the SmDependency
        this.operationRefDep = new OperationRefSmDependency();
        this.operationRefDep.init("OperationRef", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.operationRefDep);
    }

    @objid ("f51d0e7d-6789-4c99-82cb-8960cde7fd65")
    public SmAttribute getImplementationAtt() {
        if (this.implementationAtt == null) {
        	this.implementationAtt = this.getAttributeDef("Implementation");
        }
        return this.implementationAtt;
    }

    @objid ("cc5be007-d14d-453f-bbe0-89e53d3a039e")
    public SmDependency getOperationRefDep() {
        if (this.operationRefDep == null) {
        	this.operationRefDep = this.getDependencyDef("OperationRef");
        }
        return this.operationRefDep;
    }

    @objid ("9cf6e30f-ddd3-4596-9eb7-9933a574cbc6")
    private static class BpmnServiceTaskObjectFactory implements ISmObjectFactory {
        @objid ("c5bc79aa-51fb-4cb6-a52f-7b3733116728")
        private BpmnServiceTaskSmClass smClass;

        @objid ("4e4778cf-dd7f-49e5-9201-3e925826c0c7")
        public BpmnServiceTaskObjectFactory(BpmnServiceTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b0a0abac-9493-4158-bc47-e8971d971536")
        @Override
        public ISmObjectData createData() {
            return new BpmnServiceTaskData(this.smClass);
        }

        @objid ("b4eb0a45-a156-41e8-921e-fc182be42cba")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnServiceTaskImpl();
        }

    }

    @objid ("f0d6b967-ad6a-446c-8c68-f1ca5b9280aa")
    public static class ImplementationSmAttribute extends SmAttribute {
        @objid ("22811f20-e93c-4873-98a1-5bfcc8069ca6")
        public Object getValue(ISmObjectData data) {
            return ((BpmnServiceTaskData) data).mImplementation;
        }

        @objid ("9c162ad4-e159-47f5-8ef7-1ae5465eb4c2")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnServiceTaskData) data).mImplementation = value;
        }

    }

    @objid ("90ece530-bcf1-4ce8-a1b4-9079e37754df")
    public static class OperationRefSmDependency extends SmSingleDependency {
        @objid ("cbf73169-541b-4eca-9933-d27aea391b3e")
        private SmDependency symetricDep;

        @objid ("29ce8cf6-47af-47e4-860b-33a232e2d6ed")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnServiceTaskData) data).mOperationRef;
        }

        @objid ("607f6424-4be0-434d-9149-40be2fde298e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnServiceTaskData) data).mOperationRef = value;
        }

        @objid ("4283e79d-4657-4aa9-a802-60c71ab61b50")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getCallerDep();
            }
            return this.symetricDep;
        }

    }

}
