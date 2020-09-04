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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataOutputData;
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

@objid ("27dfd40d-8eb0-4c4b-9f14-bb8971a44059")
public class BpmnDataOutputSmClass extends BpmnItemAwareElementSmClass {
    @objid ("c92b6dec-1700-4b69-bc01-8fc8b9e9e411")
    private SmAttribute isCollectionAtt;

    @objid ("2c9301e8-8ca6-4813-bc6c-ef0fd3c8a2cf")
    private SmDependency ownerActivityDep;

    @objid ("dfbb45c7-6861-4b77-9c55-bdecf3dd6b0d")
    private SmDependency catchedDep;

    @objid ("798a818e-27e0-450c-8959-016054f2d940")
    private SmDependency ownerLoopCharacteristicsDep;

    @objid ("c1714eff-0945-4a0c-9069-d68136c65700")
    public BpmnDataOutputSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b78b04cd-a78a-453c-8f97-973253bec8f3")
    @Override
    public String getName() {
        return "BpmnDataOutput";
    }

    @objid ("70350d4b-44ea-4285-aa6e-175b838ff0f7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("afa6cbdc-a222-48cb-a51d-ef432bef0809")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataOutput.class;
    }

    @objid ("21b87d50-a236-4946-8679-2b9339d05ccb")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7d39d13b-21ea-477c-b714-39a069655ee4")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4e1277fb-9d3c-4682-ba23-865858fb0173")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnItemAwareElement.MQNAME);
        this.registerFactory(new BpmnDataOutputObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isCollectionAtt = new IsCollectionSmAttribute();
        this.isCollectionAtt.init("IsCollection", this, Boolean.class );
        registerAttribute(this.isCollectionAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerActivityDep = new OwnerActivitySmDependency();
        this.ownerActivityDep.init("OwnerActivity", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 );
        registerDependency(this.ownerActivityDep);
        
        this.catchedDep = new CatchedSmDependency();
        this.catchedDep.init("Catched", this, metamodel.getMClass(BpmnCatchEvent.MQNAME), 0, 1 );
        registerDependency(this.catchedDep);
        
        this.ownerLoopCharacteristicsDep = new OwnerLoopCharacteristicsSmDependency();
        this.ownerLoopCharacteristicsDep.init("OwnerLoopCharacteristics", this, metamodel.getMClass(BpmnMultiInstanceLoopCharacteristics.MQNAME), 0, 1 );
        registerDependency(this.ownerLoopCharacteristicsDep);
    }

    @objid ("89015f13-6744-4fff-96c1-8b7c19e77fdc")
    public SmAttribute getIsCollectionAtt() {
        if (this.isCollectionAtt == null) {
        	this.isCollectionAtt = this.getAttributeDef("IsCollection");
        }
        return this.isCollectionAtt;
    }

    @objid ("39d38b96-84db-46a7-a4c6-e5fb54565c1d")
    public SmDependency getOwnerActivityDep() {
        if (this.ownerActivityDep == null) {
        	this.ownerActivityDep = this.getDependencyDef("OwnerActivity");
        }
        return this.ownerActivityDep;
    }

    @objid ("58e820d6-786c-45ee-a3bd-0c4abbebf9ba")
    public SmDependency getCatchedDep() {
        if (this.catchedDep == null) {
        	this.catchedDep = this.getDependencyDef("Catched");
        }
        return this.catchedDep;
    }

    @objid ("2a1cffe6-2fde-4ead-b84e-85b37ecb77f1")
    public SmDependency getOwnerLoopCharacteristicsDep() {
        if (this.ownerLoopCharacteristicsDep == null) {
        	this.ownerLoopCharacteristicsDep = this.getDependencyDef("OwnerLoopCharacteristics");
        }
        return this.ownerLoopCharacteristicsDep;
    }

    @objid ("eb8523f4-fe3f-4fbf-9397-146e5e275157")
    private static class BpmnDataOutputObjectFactory implements ISmObjectFactory {
        @objid ("cb2b5b1f-92c2-4131-b1cd-eb9640ab1d4a")
        private BpmnDataOutputSmClass smClass;

        @objid ("9682b814-885b-4417-b162-dbe9b48b4662")
        public BpmnDataOutputObjectFactory(BpmnDataOutputSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2f05554d-ed7e-4d90-ac54-c27b62f20783")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataOutputData(this.smClass);
        }

        @objid ("a7812d87-8f91-46e3-b7ea-0e2205d90e33")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataOutputImpl();
        }

    }

    @objid ("d50c9caf-f9b1-4334-8313-e94bafdfa12e")
    public static class IsCollectionSmAttribute extends SmAttribute {
        @objid ("6e3224aa-98ca-4996-9d95-a0d571625694")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataOutputData) data).mIsCollection;
        }

        @objid ("82033c94-9e10-4b83-8664-1bebd1e0f442")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataOutputData) data).mIsCollection = value;
        }

    }

    @objid ("3dd1d0e1-2252-4453-8d9a-ba21250532ae")
    public static class OwnerActivitySmDependency extends SmSingleDependency {
        @objid ("30eead3d-8e67-4056-a44b-aa891cec8553")
        private SmDependency symetricDep;

        @objid ("bc76fde4-6189-425a-a77a-1495fb7df635")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataOutputData) data).mOwnerActivity;
        }

        @objid ("1fe65b8e-59d4-4265-8ab6-6535c3340539")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataOutputData) data).mOwnerActivity = value;
        }

        @objid ("3f0e90b4-10f7-48dd-af76-9291a91fb9bc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getOutputSpecificationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e143bacb-1319-4a7d-927c-3e1ca6cdb503")
    public static class CatchedSmDependency extends SmSingleDependency {
        @objid ("b53df156-f2c0-4ca4-9549-a998882bf7df")
        private SmDependency symetricDep;

        @objid ("b385cc05-12d2-4083-9fc3-4d8c56c11b23")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataOutputData) data).mCatched;
        }

        @objid ("8ae07d27-7b17-4932-951e-0f384235fcf5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataOutputData) data).mCatched = value;
        }

        @objid ("2b7990e4-cc64-4d59-9842-ce35e9f800cc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCatchEventSmClass)this.getTarget()).getDataOutputDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("abfff458-ba4e-4364-ace5-1b0e244f7bae")
    public static class OwnerLoopCharacteristicsSmDependency extends SmSingleDependency {
        @objid ("3c600254-87b4-4fc6-8021-e0122760b73c")
        private SmDependency symetricDep;

        @objid ("a612bf93-929e-4e89-9c89-8eaf46964e98")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataOutputData) data).mOwnerLoopCharacteristics;
        }

        @objid ("7e02a810-2320-4b1d-a032-ddf7f24d717b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataOutputData) data).mOwnerLoopCharacteristics = value;
        }

        @objid ("45ecec86-817a-4079-b860-1ec19731ae5a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMultiInstanceLoopCharacteristicsSmClass)this.getTarget()).getLoopDataOutputRefDep();
            }
            return this.symetricDep;
        }

    }

}
