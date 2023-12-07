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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
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
    @objid ("c53641cf-f50b-42d1-beea-d83dae6221fb")
    private SmAttribute nameAtt;

    @objid ("36c3a1c8-0218-4011-9149-9c1babb1619e")
    private SmAttribute contentAtt;

    @objid ("4787e1c7-b03d-4cb4-886d-dbcc6fa2da15")
    private SmDependency ownerValDefDep;

    @objid ("cb29b181-048e-46e8-953f-ff7f51e9f49b")
    private SmDependency ownerQueryDep;

    @objid ("8ea362ff-ba44-497d-9f5d-456143d87a07")
    private SmDependency ownerDep;

    @objid ("e332f2c5-4d1e-4d96-82bf-57622fe0944d")
    public  PropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6abaf7b9-9146-4394-a93d-947e6e1bff92")
    @Override
    public String getName() {
        return "PropertyTable";
        
    }

    @objid ("5a6d65ee-8a78-44f9-b3a7-495f9ef8226f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("407362d7-27a7-441c-b158-8629c61e1c09")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTable.class;
        
    }

    @objid ("10f38c73-b5ea-4258-b19d-1c83b632a9ed")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("c6706887-a502-4592-a92b-0525e76c759c")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("dfa8583c-b292-47ca-873f-223ebcfef371")
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

    @objid ("85375e82-bd57-4ea0-adfc-433c0a2251f3")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("db8bf7bd-c116-4e3f-8e73-b77f50111770")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("8177edbe-d6b7-4913-b3a8-147a41e9334a")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("22fa0192-f732-433e-a035-309797693b1a")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("8cc0a346-92a9-45e4-8541-5bbbe17ad884")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("bc2be4ad-73a8-4b1b-8ded-e145610ac4ad")
    private static class PropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("4c25af5a-d9c5-4140-93e4-3a8ac4e8604b")
        private PropertyTableSmClass smClass;

        @objid ("655a2f9a-df6c-4a5d-b580-37b2abbb02ee")
        public  PropertyTableObjectFactory(PropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("cc5e8da5-27b1-47ea-b6a1-6c2557825e66")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableData(this.smClass);
        }

        @objid ("ce971df2-b3b4-48cb-a6fe-4299d19cf171")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableImpl();
        }

    }

    @objid ("2dd114cf-272c-4615-baa4-3426b42bf150")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("c714a06f-26c1-477e-beb7-1cc77dedf13e")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mName;
        }

        @objid ("27f5a401-56a9-4edd-a703-48de543986fe")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mName = value;
        }

    }

    @objid ("d8ed4ba4-a1eb-4b34-b258-24307d1a30a0")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("102e8d1c-5025-48ac-9eb7-b982de4a56da")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mContent;
        }

        @objid ("0b33a095-b376-4a11-8e17-766abdb982df")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mContent = value;
        }

    }

    @objid ("1930c8c9-31e6-4304-b47b-b6a665bf9d62")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("3f052e2b-6a86-4c5e-9eb1-81738f1718a5")
        private SmDependency symetricDep;

        @objid ("c63b492f-94f7-4116-a292-b3a64a2f3e61")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwner;
        }

        @objid ("4da36a00-df1c-452b-89ac-3f90b4b9c38e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwner = value;
        }

        @objid ("e8089e9c-8f80-4015-877d-466bf1d2e69b")
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
        @objid ("2cd777ba-36fe-4b10-8ebf-bff8f52049a4")
        private SmDependency symetricDep;

        @objid ("484c407b-ba47-4e05-9007-1c8c569235da")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerValDef;
        }

        @objid ("dac95eec-0b75-474c-8217-40739c91d75f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerValDef = value;
        }

        @objid ("6696aad0-3fa0-42cd-8605-287d97f89be2")
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
        @objid ("faa31a6a-d30c-4f54-bf19-eb3fa17e2059")
        private SmDependency symetricDep;

        @objid ("e7400083-ffb1-4172-ac05-60a4ad1e71a3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerQuery;
        }

        @objid ("8603570e-b91e-4b5c-98f4-2944ac92ef98")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerQuery = value;
        }

        @objid ("9b7eb0b4-c376-4fdd-b721-b61911bd2adb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getParametersDep();
            }
            return this.symetricDep;
            
        }

    }

}
