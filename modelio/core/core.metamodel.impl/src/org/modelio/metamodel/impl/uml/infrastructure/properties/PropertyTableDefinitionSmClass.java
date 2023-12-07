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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
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
    @objid ("b1c97cf5-36f6-40f2-82f8-d582cf688a03")
    private SmDependency typedTableDep;

    @objid ("6958e2fa-e82a-455c-8919-f97bd4412663")
    private SmDependency ownerReferenceDep;

    @objid ("73d735ce-78eb-419c-af1b-c5b5dcb662c2")
    private SmDependency ownerStereotypeDep;

    @objid ("76746eb8-8bef-4dc1-a280-40fb4e10e30c")
    private SmDependency ownedDep;

    @objid ("398eda33-cf7b-4f4a-a2b5-75a0d5658852")
    public  PropertyTableDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d5a596ad-39b1-4ecc-892b-6ce1edc7dde8")
    @Override
    public String getName() {
        return "PropertyTableDefinition";
        
    }

    @objid ("5fb16dff-9c19-45fd-b68f-6a087b44f1fc")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8e8dfae0-f183-4efa-a1f1-eb588c44c922")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTableDefinition.class;
        
    }

    @objid ("b33e08b0-42de-4245-8cbf-f72ddd75530a")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("ff317d07-628f-4a59-bb5d-59828ddcda2b")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b9c08c6d-f9a4-4535-9b64-1954b58a3f5c")
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

    @objid ("a48469d2-21e2-4afd-bf1e-bbbdb0aa8380")
    public SmDependency getTypedTableDep() {
        if (this.typedTableDep == null) {
        	this.typedTableDep = this.getDependencyDef("TypedTable");
        }
        return this.typedTableDep;
    }

    @objid ("ae62d5b1-fc45-476b-8490-86bbaaaa72e3")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("ad707cd3-f82d-4bb7-838c-087a3a49ffce")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("f74db211-843e-4672-b4bd-7acec8259431")
    public SmDependency getOwnedDep() {
        if (this.ownedDep == null) {
        	this.ownedDep = this.getDependencyDef("Owned");
        }
        return this.ownedDep;
    }

    @objid ("e65a5b09-72a0-4a38-ab92-39a3a36ac804")
    private static class PropertyTableDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("94da93c1-23da-4851-a296-115cda2fda46")
        private PropertyTableDefinitionSmClass smClass;

        @objid ("721791a8-84b9-40c7-b885-eba373d4291a")
        public  PropertyTableDefinitionObjectFactory(PropertyTableDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6dd0bbb3-ccb7-4d56-b268-2ee78f1d8a13")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableDefinitionData(this.smClass);
        }

        @objid ("daf50474-0768-4bcd-b209-a2d2e3da810d")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableDefinitionImpl();
        }

    }

    @objid ("49fb296e-a1d8-4fb5-861a-d0d16673bec4")
    public static class TypedTableSmDependency extends SmMultipleDependency {
        @objid ("b2c51000-d932-4358-bfe9-0357ed9d7552")
        private SmDependency symetricDep;

        @objid ("ade59b5c-413f-4db5-b80e-2f944f049ada")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mTypedTable != null)? ((PropertyTableDefinitionData)data).mTypedTable:SmMultipleDependency.EMPTY;
        }

        @objid ("1ae88150-2d2a-4b75-98f0-f483f8d421f0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mTypedTable = values;
            
        }

        @objid ("8640702c-e654-457f-8e1b-a17bc0401dad")
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
        @objid ("e14a6306-c678-4bfd-9279-cc75ac21d69c")
        private SmDependency symetricDep;

        @objid ("527ea474-467f-442c-bed6-8a2bdb9d934d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerReference;
        }

        @objid ("fd7bea99-37b6-468a-9d6a-cc0d1ca1e3c5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerReference = value;
        }

        @objid ("b39463e4-91e4-48b8-9474-db9643d1da0b")
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
        @objid ("0ca8405d-de94-4209-8e95-5fce95d72d7a")
        private SmDependency symetricDep;

        @objid ("9377026e-446d-4c91-be12-21d1634d130a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableDefinitionData) data).mOwnerStereotype;
        }

        @objid ("fcb1582c-805d-4432-bbbb-02e033cc484e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableDefinitionData) data).mOwnerStereotype = value;
        }

        @objid ("a914a7c7-b850-4ecb-8173-56676256f1c0")
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
        @objid ("30bb4c7b-9b7c-460a-a52e-35bd957cd31b")
        private SmDependency symetricDep;

        @objid ("c2382aff-05b5-4551-931e-79ba8d1e2c9a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTableDefinitionData)data).mOwned != null)? ((PropertyTableDefinitionData)data).mOwned:SmMultipleDependency.EMPTY;
        }

        @objid ("6bed2230-fc74-4972-97f4-91c5f84d2f15")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTableDefinitionData) data).mOwned = values;
            
        }

        @objid ("0f0098fb-a5f5-4701-9497-4a6ea0695aa7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyDefinitionSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
