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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableData;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
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

@objid ("8b24307d-4164-47ea-a31e-4840ee34aa3e")
public class PropertyTableSmClass extends ElementSmClass {
    @objid ("cd9fc93b-0c9c-4ec9-b6e9-dfa829e17172")
    private SmAttribute nameAtt;

    @objid ("e97ba089-681a-4d61-bdd6-9de872aac49f")
    private SmAttribute contentAtt;

    @objid ("66f96dbe-b735-4982-a2af-08eb48a8a55b")
    private SmDependency ownerValDefDep;

    @objid ("8404e2c6-8e6d-4421-b4b3-4529828ba02e")
    private SmDependency ownerQueryDep;

    @objid ("1d000f36-f654-4f3c-b01b-06128e094bd7")
    private SmDependency ownerDep;

    @objid ("2a819bc3-40e5-4a7d-af68-9f5548e0679b")
    public PropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("11e02813-82a4-4259-b123-eb7d44cc07c2")
    @Override
    public String getName() {
        return "PropertyTable";
    }

    @objid ("e2add9e5-72d2-46c6-9672-5dd0b27e3fd3")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("44c503bd-d332-42c9-8fdf-08bfee08ee65")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTable.class;
    }

    @objid ("544f639c-b67c-4df1-a330-261249f538cc")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3fba2706-900e-4823-9cb3-1df71afacb2f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e1943123-8f3a-4c59-b3dc-1e15672f515e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new PropertyTableObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.nameAtt = new NameSmAttribute();
        this.nameAtt.init("Name", this, String.class );
        registerAttribute(this.nameAtt);
        
        this.contentAtt = new ContentSmAttribute();
        this.contentAtt.init("Content", this, String.class );
        registerAttribute(this.contentAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerValDefDep = new OwnerValDefSmDependency();
        this.ownerValDefDep.init("OwnerValDef", this, metamodel.getMClass(MatrixValueDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerValDefDep);
        
        this.ownerQueryDep = new OwnerQuerySmDependency();
        this.ownerQueryDep.init("OwnerQuery", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerQueryDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("160e5f49-b986-45bd-b745-e1681245362e")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("f7bdf3b3-6b63-459b-bbd3-db020c031d98")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("42988a02-9b1c-415d-9d74-012da559fee2")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("434fc77d-e262-4bf4-a778-9c2618986899")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("77b13fd0-b7c0-415f-aa65-f4fb8004e07b")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("bc2be4ad-73a8-4b1b-8ded-e145610ac4ad")
    private static class PropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("3fb79ed3-079c-467b-b14f-4ce86d3b3ed9")
        private PropertyTableSmClass smClass;

        @objid ("827f4dab-2820-4278-ba5c-911c8e418db8")
        public PropertyTableObjectFactory(PropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("217f30ab-1c64-44a5-8101-4517c1ca7a48")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableData(this.smClass);
        }

        @objid ("ded8dbe6-8213-43ef-be7b-09080370d65a")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableImpl();
        }

    }

    @objid ("2dd114cf-272c-4615-baa4-3426b42bf150")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("59e58edf-4809-41c3-a2c2-1c96280ff3e2")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mName;
        }

        @objid ("fbfaaffa-1d91-4794-89d4-dcb0e32d193b")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mName = value;
        }

    }

    @objid ("d8ed4ba4-a1eb-4b34-b258-24307d1a30a0")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("0d6ed7e6-35d9-4602-b0d1-569cd855a35f")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mContent;
        }

        @objid ("f81eeb72-bdd5-4685-a5ed-7a25be5b195c")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mContent = value;
        }

    }

    @objid ("1930c8c9-31e6-4304-b47b-b6a665bf9d62")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("df2e9fbe-5473-4657-bba2-406a72a4a118")
        private SmDependency symetricDep;

        @objid ("d8a3fdd5-f396-41ee-8ac5-8f451ed4fa46")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwner;
        }

        @objid ("d43464d7-1973-4e95-ac13-b9ab8358f6f5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwner = value;
        }

        @objid ("5ceb8ea4-55dd-4013-b8f5-61459aeca497")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getPropertiesDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("3fbba496-8db7-4832-a356-73e87e8f83ed")
    public static class OwnerValDefSmDependency extends SmSingleDependency {
        @objid ("2c8c6322-3c8b-48e3-9bda-bd52399185d8")
        private SmDependency symetricDep;

        @objid ("08a422fa-ee5e-40e9-8299-9f12e7109cff")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerValDef;
        }

        @objid ("1fc4ed5f-5e85-4eb5-bd6d-0d68419900b2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerValDef = value;
        }

        @objid ("818b60a1-f7e4-44dc-a5f1-02e2ff66b516")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getParametersDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("27dcbcd9-9522-4eb4-9340-3f38bd732a28")
    public static class OwnerQuerySmDependency extends SmSingleDependency {
        @objid ("3f74f7db-1e5e-4ad4-8a21-902c8982c896")
        private SmDependency symetricDep;

        @objid ("f6622f57-e1d2-477a-a8e7-477963ed4abf")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerQuery;
        }

        @objid ("adcb254a-a566-4e4b-b28d-2d1bf29c3f4e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerQuery = value;
        }

        @objid ("81e646a5-557b-42ea-a13e-266cb796e8e7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getParametersDep();
            }
            return this.symetricDep;
        }

    }

}
