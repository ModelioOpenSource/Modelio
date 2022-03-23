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

package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.statik.ParameterSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.Parameter;
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

@objid ("46048f4c-2fa7-4169-baf7-3fc00502e63d")
public class BehaviorParameterSmClass extends ParameterSmClass {
    @objid ("84a31c0a-4494-4a06-a734-c2533bcda427")
    private SmDependency representingObjectNodeDep;

    @objid ("15b600d5-190b-4915-9dd4-caafad2a7992")
    private SmDependency ownerDep;

    @objid ("34f862da-e4e1-430a-aedf-b73b46138d4f")
    private SmDependency mappedDep;

    @objid ("06650466-fad6-4370-8be7-49ef482bc1a8")
    public  BehaviorParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0aade626-02c1-41a0-9ee6-6863dfdecd74")
    @Override
    public String getName() {
        return "BehaviorParameter";
        
    }

    @objid ("50cd0d28-ac00-4444-9b6c-3fe1df37b20e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c5381e92-6912-48b8-97fe-40c5e63098d2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BehaviorParameter.class;
        
    }

    @objid ("742e11f9-204c-4ecd-9860-3a2276848379")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ec7b980b-bf25-494b-8541-cc94abdaaf6a")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("59c5b3a6-7053-4428-9f04-adf91520b8ea")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Parameter.MQNAME);
        this.registerFactory(new BehaviorParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.representingObjectNodeDep = new RepresentingObjectNodeSmDependency();
        this.representingObjectNodeDep.init("RepresentingObjectNode", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 );
        registerDependency(this.representingObjectNodeDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Behavior.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.mappedDep = new MappedSmDependency();
        this.mappedDep.init("Mapped", this, metamodel.getMClass(Parameter.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.mappedDep);
        
        
    }

    @objid ("ffe1fd0d-861c-48b8-b896-c2c1523c9c62")
    public SmDependency getRepresentingObjectNodeDep() {
        if (this.representingObjectNodeDep == null) {
        	this.representingObjectNodeDep = this.getDependencyDef("RepresentingObjectNode");
        }
        return this.representingObjectNodeDep;
    }

    @objid ("250b6161-0187-460c-8302-f73dd7bb2641")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("3d92137d-c934-419a-be7b-82d45adcba57")
    public SmDependency getMappedDep() {
        if (this.mappedDep == null) {
        	this.mappedDep = this.getDependencyDef("Mapped");
        }
        return this.mappedDep;
    }

    @objid ("1804ef67-b056-4d0f-96bf-2c120d61cfe4")
    private static class BehaviorParameterObjectFactory implements ISmObjectFactory {
        @objid ("d64bdc12-3913-4b4e-9876-1a23a1eda9a9")
        private BehaviorParameterSmClass smClass;

        @objid ("6c43d865-6330-4928-b233-be30a5bab4f1")
        public  BehaviorParameterObjectFactory(BehaviorParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("70f56be0-aedd-4694-8b59-87c0b421b343")
        @Override
        public ISmObjectData createData() {
            return new BehaviorParameterData(this.smClass);
        }

        @objid ("e066778f-a47d-40e0-a10f-000ad72f54b0")
        @Override
        public SmObjectImpl createImpl() {
            return new BehaviorParameterImpl();
        }

    }

    @objid ("78275c04-c102-4238-82dd-8e4e39ce2ca2")
    public static class RepresentingObjectNodeSmDependency extends SmMultipleDependency {
        @objid ("4d9818d9-8ede-4c08-a313-cc8948dc2536")
        private SmDependency symetricDep;

        @objid ("e7017092-d212-4ed5-b8ef-56782a09e899")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorParameterData)data).mRepresentingObjectNode != null)? ((BehaviorParameterData)data).mRepresentingObjectNode:SmMultipleDependency.EMPTY;
        }

        @objid ("8d4d0ac1-af8d-411b-ae34-114b3a10f39a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorParameterData) data).mRepresentingObjectNode = values;
            
        }

        @objid ("c4096a7a-2adb-4262-9a18-057c206e5968")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getRepresentedRealParameterDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1f588389-2af8-4e78-994c-5eabc0034ce4")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("39cf6284-2562-45c3-acd8-25432648ddab")
        private SmDependency symetricDep;

        @objid ("e191f62c-bc17-4ac8-9f8c-fb5766e8cfcc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BehaviorParameterData) data).mOwner;
        }

        @objid ("ca31e4db-a3a2-4ed3-9da7-d57a4eaf75ad")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BehaviorParameterData) data).mOwner = value;
        }

        @objid ("993df5e7-3478-4ff8-9e6f-248687c95da6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getParameterDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("777fed32-b770-4eb7-b412-963a8b663b2b")
    public static class MappedSmDependency extends SmSingleDependency {
        @objid ("24d9aada-9372-4ee1-aded-ef41f8fb8937")
        private SmDependency symetricDep;

        @objid ("eef6bca9-798f-4c8e-acf2-bb9c702d7322")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BehaviorParameterData) data).mMapped;
        }

        @objid ("e31881c7-fdf1-4e04-8199-b95e879c19ad")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BehaviorParameterData) data).mMapped = value;
        }

        @objid ("4fdfa3dd-f77d-45d5-bd8b-faaa025c3dc9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getBehaviorParamDep();
            }
            return this.symetricDep;
            
        }

    }

}
