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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
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
    @objid ("1de731bc-1cce-4b15-aa98-1adbe54544f6")
    private SmDependency typedTableDep;

    @objid ("dea89038-c430-48e9-937f-ae95dc1bd592")
    private SmDependency ownerReferenceDep;

    @objid ("cedc343f-369b-437b-9b16-3d84b0acc6f6")
    private SmDependency ownerStereotypeDep;

    @objid ("c812afe4-35df-4e26-a4a4-446a83544f97")
    private SmDependency ownedDep;

    @objid ("2d8231d3-7eb9-4da5-a0e9-d56e53fd630d")
    public  PropertyTableDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7ad8ebc4-7dce-42c4-9dbc-b5f7fe580cd5")
    @Override
    public String getName() {
        return "PropertyTableDefinition";
        
    }

    @objid ("a4ba3cfe-4416-4d58-b63f-603d0de16a11")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d2337cb6-2f90-44ac-85ec-5eaf5e06d5f7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTableDefinition.class;
        
    }

    @objid ("1e504a59-d701-4f51-b2cd-be975848a599")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("5572143e-628b-4ef5-8402-d66ae57b8015")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("073c8937-58b9-4131-b77e-b0f1eb548944")
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

    @objid ("673b7592-a17b-47fe-b699-878913be69d8")
    public SmDependency getTypedTableDep() {
        if (this.typedTableDep == null) {
        	this.typedTableDep = this.getDependencyDef("TypedTable");
        }
        return this.typedTableDep;
    }

    @objid ("e4279ba5-96d0-4074-96e2-e14f4de2c0d4")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("2dbc2a42-f587-4c1f-989a-68e067261c68")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("c4084434-8c08-4410-83c6-0cfdb4e2ce5e")
    public SmDependency getOwnedDep() {
        if (this.ownedDep == null) {
        	this.ownedDep = this.getDependencyDef("Owned");
        }
        return this.ownedDep;
    }

    @objid ("e65a5b09-72a0-4a38-ab92-39a3a36ac804")
    private static class PropertyTableDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("84b2a4a2-44e6-4ad5-b7c4-824c4ed05003")
        private PropertyTableDefinitionSmClass smClass;

        @objid ("c398e307-7c0d-4cf0-98a5-4d29dfd931fd")
        public  PropertyTableDefinitionObjectFactory(PropertyTableDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("65652608-5eff-4fa3-b9a8-faa94c699999")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableDefinitionData(this.smClass);
        }

        @objid ("facf6a47-5990-4d5d-97db-b7daa90e8ebe")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableDefinitionImpl();
        }

    }

    @objid ("49fb296e-a1d8-4fb5-861a-d0d16673bec4")
    public static class TypedTableSmDependency extends SmMultipleDependency {
        @objid ("84e519ac-cb02-484e-8468-519f6eb5f9ab")
        private SmDependency symetricDep;

        @objid ("2038a953-2314-4b9e-9121-42fa5aa906f1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mTypedTable != null)? ((PropertyTableDefinitionData)data).mTypedTable:SmMultipleDependency.EMPTY;
        }

        @objid ("09cd9b96-b916-4293-9d41-3be80645aea8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mTypedTable = values;
            
        }

        @objid ("2e5bded3-2053-4aee-89a5-2890f965ebf6")
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
        @objid ("443523a8-211e-43e4-bfda-391cad7e42f0")
        private SmDependency symetricDep;

        @objid ("f09d4be8-91f3-44b1-ac20-6ccf975c8b17")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerReference;
        }

        @objid ("218bf0bd-9a87-4d93-82ed-af1547629112")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerReference = value;
        }

        @objid ("362106ee-58ac-4294-97ba-1c6f8113b0cb")
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
        @objid ("477a87c2-3ded-4589-ae75-ea7c0f89877b")
        private SmDependency symetricDep;

        @objid ("ae796f65-1089-4fd6-818b-c5efda3da110")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerStereotype;
        }

        @objid ("57b3037b-fe6b-4db1-a2ce-0113f99764be")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerStereotype = value;
        }

        @objid ("890d5268-1fda-4941-bce3-b8c4a6b24944")
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
        @objid ("d1b52c08-7cb5-4378-a6d6-f5eab08b8b38")
        private SmDependency symetricDep;

        @objid ("9eb531b1-af28-4482-837e-abf815f66793")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mOwned != null)? ((PropertyTableDefinitionData)data).mOwned:SmMultipleDependency.EMPTY;
        }

        @objid ("07039273-1766-446f-b2c2-d10fd69cc35c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mOwned = values;
            
        }

        @objid ("ae210dcd-f125-4ee8-a698-dd78f0fca920")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyDefinitionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
