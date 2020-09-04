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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnLoopCharacteristicsData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("40b17b88-05ab-4e0c-8b1f-d64f7c864a82")
public class BpmnLoopCharacteristicsSmClass extends BpmnBaseElementSmClass {
    @objid ("a4e17628-ada5-4f50-ba83-30f31096a5c7")
    private SmDependency ownerActivityDep;

    @objid ("a60ee420-9619-4f8b-80e5-e49a0b5ef329")
    public BpmnLoopCharacteristicsSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f616e2c7-6caf-4e8c-a44c-ac9ae135d440")
    @Override
    public String getName() {
        return "BpmnLoopCharacteristics";
    }

    @objid ("3f02d20e-73af-4b9f-873d-58996bf3f6dd")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e8fd6fa3-0f85-4925-92f5-7af1263e7d7c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnLoopCharacteristics.class;
    }

    @objid ("7fc2f4de-f814-435c-9b8c-2d50ae7659cb")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9e0938fc-e209-4df3-9f33-1c5f55d22de1")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("2c884288-e0a0-422a-b396-254060ddb40a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnLoopCharacteristicsObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerActivityDep = new OwnerActivitySmDependency();
        this.ownerActivityDep.init("OwnerActivity", this, metamodel.getMClass(BpmnActivity.MQNAME), 1, 1 );
        registerDependency(this.ownerActivityDep);
    }

    @objid ("1202964a-e5ba-4814-abfe-052314a40d91")
    public SmDependency getOwnerActivityDep() {
        if (this.ownerActivityDep == null) {
        	this.ownerActivityDep = this.getDependencyDef("OwnerActivity");
        }
        return this.ownerActivityDep;
    }

    @objid ("095270c5-f363-405c-9b8d-39cd01e6ee31")
    private static class BpmnLoopCharacteristicsObjectFactory implements ISmObjectFactory {
        @objid ("32c76a71-d95b-40fa-a712-774ffcd2c9ee")
        private BpmnLoopCharacteristicsSmClass smClass;

        @objid ("b4a5b373-1b72-400b-90c9-175bd6eceeeb")
        public BpmnLoopCharacteristicsObjectFactory(BpmnLoopCharacteristicsSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c23597b0-29c2-4db6-a8db-b2cd23142d09")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("27464765-980e-483c-8810-0088679a7713")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("14655c42-9616-4ff1-9803-46c14ea006ca")
    public static class OwnerActivitySmDependency extends SmSingleDependency {
        @objid ("c5d85d0a-5ae6-4b9f-bf70-06dbe0770294")
        private SmDependency symetricDep;

        @objid ("8587c2ff-3e11-4d45-a34d-2c676c422b1c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnLoopCharacteristicsData) data).mOwnerActivity;
        }

        @objid ("3e41c9f5-9c12-4059-8a96-7bc2a4efe740")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnLoopCharacteristicsData) data).mOwnerActivity = value;
        }

        @objid ("b6748292-f4bf-41b9-93f2-8c7b0977b477")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getLoopCharacteristicsDep();
            }
            return this.symetricDep;
        }

    }

}
