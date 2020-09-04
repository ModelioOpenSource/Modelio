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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataInputData;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementSmClass;
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

@objid ("3fc273f8-4522-4900-b978-5df64f292140")
public class BpmnDataInputSmClass extends BpmnItemAwareElementSmClass {
    @objid ("17b712d7-8e63-4146-9815-dac874cf6e97")
    private SmAttribute isCollectionAtt;

    @objid ("08cd6a32-5271-44c4-bbef-f0b8eeed4bb0")
    private SmDependency ownerLoopCharacteristicsDep;

    @objid ("8a0e325b-8c29-499f-b148-428cfb4e4de5")
    private SmDependency ownerActivityDep;

    @objid ("d33580b9-9433-46ad-b921-28005a0a66d1")
    private SmDependency ownerThrowEventDep;

    @objid ("3a3646ee-8e75-4b94-9122-0cf5c9a27046")
    public BpmnDataInputSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("41d1fe10-8197-43c3-ad98-81ac00ed326d")
    @Override
    public String getName() {
        return "BpmnDataInput";
    }

    @objid ("2f547a00-e71f-44ec-93c3-f64a194d3665")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("be6b5b31-47a3-450b-ba28-cb8304032ee0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataInput.class;
    }

    @objid ("8a317aaa-dfab-4422-ad6f-90d804f6d98d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("594138dd-b77a-461e-ad0a-a66bbf3abd56")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4f498ded-6ab3-445c-a767-b1288df54e1b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnItemAwareElement.MQNAME);
        this.registerFactory(new BpmnDataInputObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isCollectionAtt = new IsCollectionSmAttribute();
        this.isCollectionAtt.init("IsCollection", this, Boolean.class );
        registerAttribute(this.isCollectionAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerLoopCharacteristicsDep = new OwnerLoopCharacteristicsSmDependency();
        this.ownerLoopCharacteristicsDep.init("OwnerLoopCharacteristics", this, metamodel.getMClass(BpmnMultiInstanceLoopCharacteristics.MQNAME), 0, 1 );
        registerDependency(this.ownerLoopCharacteristicsDep);
        
        this.ownerActivityDep = new OwnerActivitySmDependency();
        this.ownerActivityDep.init("OwnerActivity", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 );
        registerDependency(this.ownerActivityDep);
        
        this.ownerThrowEventDep = new OwnerThrowEventSmDependency();
        this.ownerThrowEventDep.init("OwnerThrowEvent", this, metamodel.getMClass(BpmnThrowEvent.MQNAME), 0, 1 );
        registerDependency(this.ownerThrowEventDep);
    }

    @objid ("f898b276-3717-45ea-9e90-a3029c227fa6")
    public SmAttribute getIsCollectionAtt() {
        if (this.isCollectionAtt == null) {
        	this.isCollectionAtt = this.getAttributeDef("IsCollection");
        }
        return this.isCollectionAtt;
    }

    @objid ("22b1a5e9-b65b-4312-9f3e-12811690dfa8")
    public SmDependency getOwnerLoopCharacteristicsDep() {
        if (this.ownerLoopCharacteristicsDep == null) {
        	this.ownerLoopCharacteristicsDep = this.getDependencyDef("OwnerLoopCharacteristics");
        }
        return this.ownerLoopCharacteristicsDep;
    }

    @objid ("171a2cd8-c7d7-4320-ae34-6933e91e8c35")
    public SmDependency getOwnerActivityDep() {
        if (this.ownerActivityDep == null) {
        	this.ownerActivityDep = this.getDependencyDef("OwnerActivity");
        }
        return this.ownerActivityDep;
    }

    @objid ("1571bed8-797c-480c-8991-c908215e77b2")
    public SmDependency getOwnerThrowEventDep() {
        if (this.ownerThrowEventDep == null) {
        	this.ownerThrowEventDep = this.getDependencyDef("OwnerThrowEvent");
        }
        return this.ownerThrowEventDep;
    }

    @objid ("c0e5f33c-73da-4395-802f-f6de599f924d")
    private static class BpmnDataInputObjectFactory implements ISmObjectFactory {
        @objid ("4292748b-30cd-42f7-8e7d-d83255d86b28")
        private BpmnDataInputSmClass smClass;

        @objid ("ea4afdd1-8581-4e04-a334-8a57580450f3")
        public BpmnDataInputObjectFactory(BpmnDataInputSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("73934936-8ca7-4672-928b-57fc986189c5")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataInputData(this.smClass);
        }

        @objid ("a448da43-4353-449a-88cc-3cf8be41a8a0")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataInputImpl();
        }

    }

    @objid ("0a95aa92-c9be-428d-8be5-4588c8d6ea5d")
    public static class IsCollectionSmAttribute extends SmAttribute {
        @objid ("f77ed8a6-5bdb-4e4d-8ebb-6f3f2c237b5d")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataInputData) data).mIsCollection;
        }

        @objid ("6a778086-8475-4111-904f-75cdd90934fc")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataInputData) data).mIsCollection = value;
        }

    }

    @objid ("41115a8a-c3eb-44fa-a2aa-4d25f82fa4cf")
    public static class OwnerLoopCharacteristicsSmDependency extends SmSingleDependency {
        @objid ("b227a3ab-94b4-47c8-9d14-73fa09c8dd54")
        private SmDependency symetricDep;

        @objid ("5fe72259-9c97-475e-bbc6-624307823cb1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataInputData) data).mOwnerLoopCharacteristics;
        }

        @objid ("ce356afb-b076-4015-a731-92d240d235ab")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataInputData) data).mOwnerLoopCharacteristics = value;
        }

        @objid ("c9b13238-1072-47f4-bb66-eb3ed4e19caa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMultiInstanceLoopCharacteristicsSmClass)this.getTarget()).getLoopDataInputDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("762a0eea-6c73-4b98-9290-cd8ecb53edc7")
    public static class OwnerActivitySmDependency extends SmSingleDependency {
        @objid ("f66784ab-864e-4cca-aad3-b0c72f2e644e")
        private SmDependency symetricDep;

        @objid ("8888b48c-8e40-4fac-ba68-da84a343bcf1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataInputData) data).mOwnerActivity;
        }

        @objid ("492127e5-dc5f-44e8-919f-a8eb642285e7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataInputData) data).mOwnerActivity = value;
        }

        @objid ("8021d8b3-7973-4ecd-b444-0a184fd10317")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getInputSpecificationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("29c3ac51-5f70-4d37-b6f2-0f1e06050c7e")
    public static class OwnerThrowEventSmDependency extends SmSingleDependency {
        @objid ("d940a8b9-f61c-41d5-adcb-66b55accce1c")
        private SmDependency symetricDep;

        @objid ("f8fd06d7-9e61-41d4-b59c-21378e6c8c3c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataInputData) data).mOwnerThrowEvent;
        }

        @objid ("acb71a1c-4ba9-4721-b0c4-3c354b58e638")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataInputData) data).mOwnerThrowEvent = value;
        }

        @objid ("1bf0d62a-f968-4d5b-b6e0-914a449f1978")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnThrowEventSmClass)this.getTarget()).getDataInputDep();
            }
            return this.symetricDep;
        }

    }

}
