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
    @objid ("152a42e9-2026-4a65-85d6-cff7d3842a7e")
    private SmAttribute nameAtt;

    @objid ("8a91bc7e-dd92-49fe-9782-569229270dcb")
    private SmAttribute contentAtt;

    @objid ("46f5af99-3f89-42d4-93f9-ad9298674f98")
    private SmDependency ownerValDefDep;

    @objid ("373b00b1-40a1-4dcf-a0de-1d2dd7127ea8")
    private SmDependency ownerQueryDep;

    @objid ("6f083047-22ba-4bf9-ba9d-78a37e6bd62c")
    private SmDependency ownerDep;

    @objid ("66627003-bc36-4e20-b887-2831bcba0c9f")
    public  PropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c2c530e4-cf8c-47de-9579-cfd4446d2f99")
    @Override
    public String getName() {
        return "PropertyTable";
        
    }

    @objid ("e50c4cd2-e672-4ce9-9bee-842c4febe897")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9ecafc47-ad43-45ed-a361-8bef3c99b406")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyTable.class;
        
    }

    @objid ("5f6ad5bd-dc1f-466c-a073-64014737ad45")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("279c4c7b-af7c-4685-9cd5-a5c3b9be8d56")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("e111387b-05ef-49d3-b31a-8b7d537ef873")
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

    @objid ("368f337a-da2a-4c00-9c18-e5f33aad35f6")
    public SmAttribute getNameAtt() {
        if (this.nameAtt == null) {
        	this.nameAtt = this.getAttributeDef("Name");
        }
        return this.nameAtt;
    }

    @objid ("17fd3c87-f923-4c83-b32e-2398722817cf")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("7d0a3d14-42fc-4ac4-af1c-59208f4970b4")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("979223a0-7b62-4bd0-ad00-b310ec63849b")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("3d1aa088-4834-4dd6-a34d-3763c5c93ce8")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("bc2be4ad-73a8-4b1b-8ded-e145610ac4ad")
    private static class PropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("f017ed2e-2299-497c-bce6-5b4a11ba4677")
        private PropertyTableSmClass smClass;

        @objid ("fd1056c7-e240-4efc-9f73-9de2648c8c48")
        public  PropertyTableObjectFactory(PropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9226dd79-f5d1-43de-b6c5-24e88bc1993e")
        @Override
        public ISmObjectData createData() {
            return new PropertyTableData(this.smClass);
        }

        @objid ("234d417b-2ba5-4df6-9b39-8b566c3ae4bb")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTableImpl();
        }

    }

    @objid ("2dd114cf-272c-4615-baa4-3426b42bf150")
    public static class NameSmAttribute extends SmAttribute {
        @objid ("bd65b4b3-f9f3-46db-815f-9625ee5d5719")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mName;
        }

        @objid ("b659b768-fcfa-4355-b5b7-1fbf7df0e966")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mName = value;
        }

    }

    @objid ("d8ed4ba4-a1eb-4b34-b258-24307d1a30a0")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("b89085f5-5982-421f-af9b-9a21e531451b")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mContent;
        }

        @objid ("5061e36d-aef3-4b43-9b16-daaf84b4d21a")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTableData) data).mContent = value;
        }

    }

    @objid ("1930c8c9-31e6-4304-b47b-b6a665bf9d62")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("084d20c7-ed40-4fc7-bc49-021f96a40d80")
        private SmDependency symetricDep;

        @objid ("a8ca43fc-57f9-45fd-b2e0-318ccf80f5a9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwner;
        }

        @objid ("70aa509b-ef12-49f2-9e13-50e7f3996481")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwner = value;
        }

        @objid ("497830ce-2eba-4604-9281-ee76f735ca51")
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
        @objid ("7fc7b748-ef1d-43f0-84ff-8dffc601d8e6")
        private SmDependency symetricDep;

        @objid ("986a96fb-cc16-4ce0-b61d-310b0eda4e1a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerValDef;
        }

        @objid ("556d74f7-9942-4194-9dd8-dc858bc71035")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerValDef = value;
        }

        @objid ("5049f402-4727-4ef1-81ca-5f63298730eb")
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
        @objid ("7a3351d4-e4de-47ff-ae1a-9a7a952aaf9d")
        private SmDependency symetricDep;

        @objid ("9367f076-37c9-40f3-a587-e805bf79e3b4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTableData) data).mOwnerQuery;
        }

        @objid ("a8614b11-cac8-470a-ac97-013aa3f4883b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTableData) data).mOwnerQuery = value;
        }

        @objid ("2b95eb33-7464-465b-b10e-315adbb77d1d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getParametersDep();
            }
            return this.symetricDep;
            
        }

    }

}
