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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
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
    @objid ("1509fb63-b1ed-442b-9f50-352652814070")
    private SmAttribute isEditableAtt;

    @objid ("b6600b67-5ed6-414d-bfdf-98f998aa3390")
    private SmAttribute defaultValueAtt;

    @objid ("d3d759ad-9b0a-406a-9e34-3151605a896b")
    private SmDependency typeDep;

    @objid ("7001bb46-e433-41d0-9c50-c1adfbbbe08d")
    private SmDependency ownerDep;

    @objid ("3746cae3-230c-4667-9a94-8ac04ba20af6")
    public  PropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a5242d46-2eef-4dad-82f4-1f018048c366")
    @Override
    public String getName() {
        return "PropertyDefinition";
        
    }

    @objid ("e655786f-e72d-45a9-98bb-fa73b10c9c89")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f0d0f895-38c1-4646-887c-4b68dac9e5c0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyDefinition.class;
        
    }

    @objid ("7b7e1ee2-5438-48d8-82d3-3e6946f853d8")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fb00aa40-bf44-4213-a5cb-483f28bd5233")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("13315451-d24b-430c-8273-0170976ad8bd")
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

    @objid ("34af415c-b874-4ccb-9df8-300bc9c0ab93")
    public SmAttribute getIsEditableAtt() {
        if (this.isEditableAtt == null) {
        	this.isEditableAtt = this.getAttributeDef("IsEditable");
        }
        return this.isEditableAtt;
    }

    @objid ("ed700bf6-3b22-400b-87c6-f51ea7645bc5")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("bb775793-e6c7-4e8e-8db8-e6de13e6f164")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("ddc1ba3f-1eb3-41dd-aa29-a2e929d82f50")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("fc653f62-c479-4bda-9d92-7cf9f242b20d")
    private static class PropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("0529aff4-e115-4eec-8e04-525d7d7176aa")
        private PropertyDefinitionSmClass smClass;

        @objid ("f64cda19-d853-4a78-9d47-705f8ee3703a")
        public  PropertyDefinitionObjectFactory(PropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("eb15e584-f022-45b3-869d-482af7741477")
        @Override
        public ISmObjectData createData() {
            return new PropertyDefinitionData(this.smClass);
        }

        @objid ("b415c5b0-225c-4ec3-a296-e72a0ecb51ed")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyDefinitionImpl();
        }

    }

    @objid ("287dcb68-a937-4906-bc7b-1fb9638eac0d")
    public static class IsEditableSmAttribute extends SmAttribute {
        @objid ("9aae3f84-7bda-44f5-99ca-7676e70e0266")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mIsEditable;
        }

        @objid ("bd7ee2de-3031-4d7c-917a-30dfa8117238")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mIsEditable = value;
        }

    }

    @objid ("fa5847e8-330c-4d38-9509-e348b4375dad")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("f1f8568a-5e4f-4574-a5db-2d9c5f27e792")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mDefaultValue;
        }

        @objid ("43b8aa5d-8aad-4d44-aae3-3b977d467a1d")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mDefaultValue = value;
        }

    }

    @objid ("e967da41-7484-4d42-af8c-ac909a3c5219")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("98692521-d571-4e51-8988-1d6a23f207b5")
        private SmDependency symetricDep;

        @objid ("5473a85a-b90b-4253-9ada-4622f5d80203")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mType;
        }

        @objid ("e2c79759-b448-481d-a615-37225acd6519")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mType = value;
        }

        @objid ("75d1b3a8-4c3b-4376-ab13-723c0cfb7700")
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
        @objid ("5a9a8368-2ffb-4d8e-9c6c-9bf6942f3f68")
        private SmDependency symetricDep;

        @objid ("c1bae623-48a0-4935-8083-3498baf26529")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mOwner;
        }

        @objid ("ae04f4c4-eb28-4178-8d99-d5b8cf92fc9f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mOwner = value;
        }

        @objid ("cd2b6a98-c0fc-46e9-ae9c-03bf5b544589")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getOwnedDep();
            }
            return this.symetricDep;
            
        }

    }

}
