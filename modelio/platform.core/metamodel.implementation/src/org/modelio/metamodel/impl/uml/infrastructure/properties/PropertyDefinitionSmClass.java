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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
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

@objid ("06c74818-fdd4-4cb0-a2ce-55b4db78928d")
public class PropertyDefinitionSmClass extends ModelElementSmClass {
    @objid ("17915ac4-32e1-4ded-bae4-5de3eab90b03")
    private SmAttribute isEditableAtt;

    @objid ("b338d4a9-4763-4699-9ef4-4764df94ebd6")
    private SmAttribute defaultValueAtt;

    @objid ("8fecc357-7e26-49ec-8c63-7c97c27aa529")
    private SmDependency typeDep;

    @objid ("370dfb27-29a9-485d-9ec7-00e55a2092fb")
    private SmDependency ownerDep;

    @objid ("c4969c5b-02bd-4e7a-b172-bad44a467055")
    public PropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("68e877de-aa44-4d87-b9b4-1a632fc4dc06")
    @Override
    public String getName() {
        return "PropertyDefinition";
    }

    @objid ("8c48fcc1-dab4-483c-8383-baaf6d312d9c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("23bc2d6c-08a7-4951-b305-3627b7f38add")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyDefinition.class;
    }

    @objid ("8f5ec2e8-a1cb-4b67-a55e-1c1c6fbcefc0")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("2a5cfaea-3229-47de-adc9-930d10fdd8f8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b68e5b53-bb1b-42e8-bf6b-6dc03089f81e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new PropertyDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isEditableAtt = new IsEditableSmAttribute();
        this.isEditableAtt.init("IsEditable", this, Boolean.class );
        registerAttribute(this.isEditableAtt);
        
        this.defaultValueAtt = new DefaultValueSmAttribute();
        this.defaultValueAtt.init("DefaultValue", this, String.class );
        registerAttribute(this.defaultValueAtt);
        
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(PropertyType.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(PropertyTableDefinition.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("4b078536-5247-4ba2-99e2-dd36931a9fe4")
    public SmAttribute getIsEditableAtt() {
        if (this.isEditableAtt == null) {
        	this.isEditableAtt = this.getAttributeDef("IsEditable");
        }
        return this.isEditableAtt;
    }

    @objid ("0fea06bc-d9b8-4c5a-8a0f-c9b1d5ff48e1")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("00922143-724a-410d-b7b2-cda3af134362")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("6b3ade0c-dffb-4e91-a8cf-46f1f300b850")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("fc653f62-c479-4bda-9d92-7cf9f242b20d")
    private static class PropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("0a7ecb08-88db-4735-93d6-6989a27f126c")
        private PropertyDefinitionSmClass smClass;

        @objid ("ab5d56c7-8778-47ac-add4-dfa1d37be298")
        public PropertyDefinitionObjectFactory(PropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("23cf9577-f56b-42f3-bb5d-7b11e2f4ea89")
        @Override
        public ISmObjectData createData() {
            return new PropertyDefinitionData(this.smClass);
        }

        @objid ("57bd3675-43ba-4121-a53f-7e51014aaf34")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyDefinitionImpl();
        }

    }

    @objid ("287dcb68-a937-4906-bc7b-1fb9638eac0d")
    public static class IsEditableSmAttribute extends SmAttribute {
        @objid ("cb5826a4-0d34-4e79-a20e-3d1670b9563a")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mIsEditable;
        }

        @objid ("4f44b553-fa83-443e-baed-c99a3023e9f9")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mIsEditable = value;
        }

    }

    @objid ("fa5847e8-330c-4d38-9509-e348b4375dad")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("bed40e8c-2760-42b3-8b0f-441e7f6fc208")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mDefaultValue;
        }

        @objid ("4c6666e6-d6c4-4c06-915d-8bb32d6b8a27")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mDefaultValue = value;
        }

    }

    @objid ("e967da41-7484-4d42-af8c-ac909a3c5219")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("de977dd3-8c8a-4ef4-812d-4abcbf17b537")
        private SmDependency symetricDep;

        @objid ("95a47318-a114-4f9e-a8e8-0d3811cf0844")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mType;
        }

        @objid ("a635f910-a195-40f5-a41e-bc0c938284cc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mType = value;
        }

        @objid ("6947ae44-27ac-417b-b426-f0fb1cbfb4ec")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTypeSmClass)this.getTarget()).getTypedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b15389aa-b835-487f-80e0-15935b780440")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("7bb2d772-c933-467f-8b0c-e8248d216ba2")
        private SmDependency symetricDep;

        @objid ("d86e5cb6-2173-44b2-8ab6-01a8d9b5c7e7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mOwner;
        }

        @objid ("eea1b9e9-a2e9-45f6-80fb-ad003fccb84b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mOwner = value;
        }

        @objid ("db6d5164-1a94-4412-9ac7-e7446fd5dd67")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getOwnedDep();
            }
            return this.symetricDep;
        }

    }

}
