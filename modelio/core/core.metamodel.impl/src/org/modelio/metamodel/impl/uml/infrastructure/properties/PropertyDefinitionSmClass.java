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
    @objid ("e0ff755d-a49a-445c-819a-72e453b5e6fa")
    private SmAttribute isEditableAtt;

    @objid ("c6fc4149-96bc-4b49-8b97-6e198999ee74")
    private SmAttribute defaultValueAtt;

    @objid ("d2354861-4dad-4d38-bf8b-7c62f3f6b348")
    private SmDependency typeDep;

    @objid ("fc2b0d58-f93f-4faf-b82e-cb3e5080409d")
    private SmDependency ownerDep;

    @objid ("7f3072ff-839d-4f61-9d46-6f842bca2b06")
    public PropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1adeb9d1-2aef-4ff9-a410-30cd11354faf")
    @Override
    public String getName() {
        return "PropertyDefinition";
    }

    @objid ("2ff0ad41-7b59-44d9-b1ae-0b23f464545b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a894e4a7-01b7-4285-905a-84c67c909038")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyDefinition.class;
    }

    @objid ("01030461-4caf-4803-a62c-fd882f278e85")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("63c60155-1592-4483-8816-7c5b2c396c84")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a1c78f0b-1c56-416d-b894-768b1706d8ed")
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

    @objid ("b80cb051-6cb5-473e-9be9-2c505504c772")
    public SmAttribute getIsEditableAtt() {
        if (this.isEditableAtt == null) {
        	this.isEditableAtt = this.getAttributeDef("IsEditable");
        }
        return this.isEditableAtt;
    }

    @objid ("ff6f7160-6e9d-4473-aa6c-e891c2cba37b")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("21f496c5-83f0-4f43-970c-2653b0b668c3")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("7c154501-f057-466b-be7c-3b31fa2ede46")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("fc653f62-c479-4bda-9d92-7cf9f242b20d")
    private static class PropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("bae286d4-9160-4b1f-baa6-c542b0601d8c")
        private PropertyDefinitionSmClass smClass;

        @objid ("0574fb63-064e-48f2-a24d-cfa412a277c8")
        public PropertyDefinitionObjectFactory(PropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("967443d2-9e6d-4eec-a6b4-833e0f354c03")
        @Override
        public ISmObjectData createData() {
            return new PropertyDefinitionData(this.smClass);
        }

        @objid ("851fa6e5-3638-4f0c-ae20-b62be96af87f")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyDefinitionImpl();
        }

    }

    @objid ("287dcb68-a937-4906-bc7b-1fb9638eac0d")
    public static class IsEditableSmAttribute extends SmAttribute {
        @objid ("3456aa09-6da7-43e7-8859-5293798270f6")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mIsEditable;
        }

        @objid ("e2b5acd2-52b2-4040-af57-726e71f2a67d")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mIsEditable = value;
        }

    }

    @objid ("fa5847e8-330c-4d38-9509-e348b4375dad")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("aa77a5ec-2ffc-4396-9d90-baa57a33a9de")
        public Object getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mDefaultValue;
        }

        @objid ("056ba00a-4d0c-4df4-8b4f-37c71ad4bfc6")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyDefinitionData) data).mDefaultValue = value;
        }

    }

    @objid ("e967da41-7484-4d42-af8c-ac909a3c5219")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("0bb4971e-c12c-4437-bfff-06081b2a4690")
        private SmDependency symetricDep;

        @objid ("984f117a-2792-41db-9ca6-a9ea0a0f041f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mType;
        }

        @objid ("2cf6e53e-921a-42c8-a335-2e5710105a2b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mType = value;
        }

        @objid ("dc38bcc7-cf46-4133-a33d-3b4f41bc2e83")
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
        @objid ("51e9f86c-c17e-4505-b574-db982c99338e")
        private SmDependency symetricDep;

        @objid ("d8c09f0b-8b77-4e14-8386-f35aab0b3a80")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyDefinitionData) data).mOwner;
        }

        @objid ("6d5288cd-7f09-4c57-b5c4-02e40f7b52d3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyDefinitionData) data).mOwner = value;
        }

        @objid ("fe541698-d40f-49b2-b9b8-3f694370f8f9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getOwnedDep();
            }
            return this.symetricDep;
        }

    }

}
