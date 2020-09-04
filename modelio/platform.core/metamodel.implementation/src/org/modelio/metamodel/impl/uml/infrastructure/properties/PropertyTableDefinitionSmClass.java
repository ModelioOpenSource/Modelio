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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.TypedPropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
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

@objid ("0d6c2aea-8dca-4863-a004-7fbfad275c72")
public class PropertyTableDefinitionSmClass extends ModelElementSmClass {
    @objid ("edd087a6-b735-40da-b538-3dde2dd6dd22")
    private SmDependency typedTableDep;

    @objid ("c8fbc600-40dc-40ef-a18c-02b0aa71f6d9")
    private SmDependency ownerReferenceDep;

    @objid ("e9f0d503-0090-4211-b405-c6be0298d022")
    private SmDependency ownerStereotypeDep;

    @objid ("e2c7b289-bcfc-429b-9584-63e1903816cc")
    private SmDependency ownedDep;

    @objid ("5e41b9ec-33b6-45d6-b75b-2a2b4da34e82")
    public PropertyTableDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("360032e6-5b4c-49e8-89cf-376c7898e5b9")
    @Override
    public String getName() {
        return "PropertyTableDefinition";
    }

    @objid ("3fd44f60-90cb-4926-981e-2686a2a8eb2a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("45c1abac-b4ea-4ff7-8199-56767813d176")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTableDefinition.class;
    }

    @objid ("0f9f13b5-559f-493f-9888-c39bee49679b")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("38987487-5e83-4035-b2a5-a4195e6d82d5")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("aae07e9e-6128-4491-9a0f-99715ec0df25")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new PropertyTableDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.typedTableDep = new TypedTableSmDependency();
        this.typedTableDep.init("TypedTable", this, metamodel.getMClass(TypedPropertyTable.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.typedTableDep);
        
        this.ownerReferenceDep = new OwnerReferenceSmDependency();
        this.ownerReferenceDep.init("OwnerReference", this, metamodel.getMClass(MetaclassReference.MQNAME), 0, 1 );
        registerDependency(this.ownerReferenceDep);
        
        this.ownerStereotypeDep = new OwnerStereotypeSmDependency();
        this.ownerStereotypeDep.init("OwnerStereotype", this, metamodel.getMClass(Stereotype.MQNAME), 0, 1 );
        registerDependency(this.ownerStereotypeDep);
        
        this.ownedDep = new OwnedSmDependency();
        this.ownedDep.init("Owned", this, metamodel.getMClass(PropertyDefinition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedDep);
    }

    @objid ("38712188-8d75-4284-9b98-1147e333c359")
    public SmDependency getTypedTableDep() {
        if (this.typedTableDep == null) {
        	this.typedTableDep = this.getDependencyDef("TypedTable");
        }
        return this.typedTableDep;
    }

    @objid ("704b6ea5-7c30-44f3-82f6-7fa7999cfcb2")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("0988add1-fcdf-4d0d-9489-e90b918aedf1")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("e13a2895-f3c0-4982-ac68-e89bd670727c")
    public SmDependency getOwnedDep() {
        if (this.ownedDep == null) {
        	this.ownedDep = this.getDependencyDef("Owned");
        }
        return this.ownedDep;
    }

    @objid ("e65a5b09-72a0-4a38-ab92-39a3a36ac804")
    private static class PropertyTableDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("e522f988-e062-4bb4-a24a-adbadb883cf8")
        private PropertyTableDefinitionSmClass smClass;

        @objid ("9d4a6133-33a9-4757-b524-bdc2c0854b5c")
        public PropertyTableDefinitionObjectFactory(PropertyTableDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("75500f56-f2ac-47cc-b641-5817d86b02c3")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableDefinitionData(this.smClass);
        }

        @objid ("837e180d-fd6d-4733-b4b7-6997604568f1")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableDefinitionImpl();
        }

    }

    @objid ("49fb296e-a1d8-4fb5-861a-d0d16673bec4")
    public static class TypedTableSmDependency extends SmMultipleDependency {
        @objid ("9eab405f-8a34-42c7-92ae-9793164183ce")
        private SmDependency symetricDep;

        @objid ("d479183f-0b52-421d-b9f3-ba858a897e99")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mTypedTable != null)? ((PropertyTableDefinitionData)data).mTypedTable:SmMultipleDependency.EMPTY;
        }

        @objid ("e079d9d6-bbc2-47b0-aa93-3495c71cace8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mTypedTable = values;
        }

        @objid ("b0ba7fed-aa23-4a10-8485-770a1ca5b77c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TypedPropertyTableSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a868f91e-a0fc-4bc2-a026-c547c09c938c")
    public static class OwnerReferenceSmDependency extends SmSingleDependency {
        @objid ("a7ceb988-7b8e-4ea6-a0e4-f367ea5f1953")
        private SmDependency symetricDep;

        @objid ("ef5f972e-4c48-4ef8-be0c-34bb5668b68a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerReference;
        }

        @objid ("87e7848d-f9c8-423b-8095-405444d4d883")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerReference = value;
        }

        @objid ("0bf7333a-31e4-4d11-82d9-bc480cdab8ca")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedTableDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5c1c341a-0e44-4124-924b-8f47c572690c")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("60e1b19a-9829-48bd-9000-8dd86ae05468")
        private SmDependency symetricDep;

        @objid ("1c27c87f-fd5f-4713-8913-803d580471f6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerStereotype;
        }

        @objid ("0f4cd9d0-2d56-4f81-a3a7-82f22e3b4fa1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerStereotype = value;
        }

        @objid ("d821d34f-7828-43e8-ae40-35d81e7b662b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getDefinedTableDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9f4659b3-b1d3-43ce-883a-5dbd98a17725")
    public static class OwnedSmDependency extends SmMultipleDependency {
        @objid ("6ad88b38-da65-497b-b813-3dcfbe1f00e3")
        private SmDependency symetricDep;

        @objid ("1f260a68-3e26-4521-9a0e-7ad95281ba89")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mOwned != null)? ((PropertyTableDefinitionData)data).mOwned:SmMultipleDependency.EMPTY;
        }

        @objid ("38a52d9b-2376-4863-a2f6-48ac14eccb7f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mOwned = values;
        }

        @objid ("32dd1d6f-27f0-4c3d-8e9f-fa944ec3809a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyDefinitionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
