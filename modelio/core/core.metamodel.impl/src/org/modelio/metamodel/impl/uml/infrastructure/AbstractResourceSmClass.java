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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
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

@objid ("2ad1e566-e1c4-46c9-918b-88a4c27fbaba")
public class AbstractResourceSmClass extends ModelElementSmClass {
    @objid ("3b257398-050b-482a-b7e9-f0a1018ea63d")
    private SmAttribute mimeTypeAtt;

    @objid ("6546c6ff-de21-41c5-8f09-b0c3fa53956b")
    private SmAttribute storageInfoAtt;

    @objid ("90b92b7e-c971-4a1c-99d0-11661df06758")
    private SmDependency typeDep;

    @objid ("bb238404-be24-485d-ada5-7368632727d9")
    private SmDependency subjectDep;

    @objid ("7a075438-5b74-42e8-ae1c-2b35abbe3545")
    public  AbstractResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2c9a0753-aef6-46b7-8c1d-ca626d07399f")
    @Override
    public String getName() {
        return "AbstractResource";
        
    }

    @objid ("ff28cafa-95f4-4221-8118-3631b085c12b")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("67f32338-498a-4166-96a2-152d6cfbccf2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractResource.class;
        
    }

    @objid ("4461b460-05aa-4910-af09-d291fdb3815b")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("1509a57f-f0f8-4352-9fb7-96a6bcdb875c")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("cd7d79d0-a05b-45ef-9f6e-b2c13855aae2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new AbstractResourceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.mimeTypeAtt = new MimeTypeSmAttribute();
        this.mimeTypeAtt.init("MimeType", this, String.class );
        registerAttribute(this.mimeTypeAtt);
        
        this.storageInfoAtt = new StorageInfoSmAttribute();
        this.storageInfoAtt.init("StorageInfo", this, String.class );
        registerAttribute(this.storageInfoAtt);
        
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(ResourceType.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.subjectDep = new SubjectSmDependency();
        this.subjectDep.init("Subject", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 );
        registerDependency(this.subjectDep);
        
        
    }

    @objid ("9a9d456e-e64b-4696-a202-1748d8623018")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("76d79be1-7ec9-4d2c-93fa-0ca88bd6d350")
    public SmAttribute getStorageInfoAtt() {
        if (this.storageInfoAtt == null) {
        	this.storageInfoAtt = this.getAttributeDef("StorageInfo");
        }
        return this.storageInfoAtt;
    }

    @objid ("1a5d235b-7fb1-4286-bfe7-375bdd0d913f")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("c56eddb8-a295-420a-831c-6c684ea3d46f")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("534c467f-e591-40a6-ad60-770e701418b5")
    private static class AbstractResourceObjectFactory implements ISmObjectFactory {
        @objid ("bf37cde0-5660-4d46-bcc2-a96902f6fb7e")
        private AbstractResourceSmClass smClass;

        @objid ("d1638009-b5b4-410e-93da-edb060459610")
        public  AbstractResourceObjectFactory(AbstractResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5ac851ac-d088-4383-9ce9-4e9eaaf9d4d8")
        @Override
        public ISmObjectData createData() {
            return new AbstractResourceData(this.smClass);
        }

        @objid ("dd9838ec-936d-45b8-a9e9-ca9eb1ba5a2e")
        @Override
        public SmObjectImpl createImpl() {
            return new AbstractResourceImpl();
        }

    }

    @objid ("56f5b283-13ce-4ee2-b224-240dc6002dce")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("62799816-8d54-4e58-970b-55fae6a9d703")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mMimeType;
        }

        @objid ("f81c73dc-f3a8-481f-986c-a9c627517258")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mMimeType = value;
        }

    }

    @objid ("b104e3fb-7ba5-4031-873e-1e94d427afff")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("a17fd1b0-d61e-4e0f-962c-6117b840315d")
        private SmDependency symetricDep;

        @objid ("b0f4f2f4-3cf0-4a65-aebf-563a6bc1406c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mType;
        }

        @objid ("7a9c52ff-fce9-4e81-9290-32b688f44bbb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mType = value;
        }

        @objid ("3cf46952-01aa-49ba-85d7-fb96d76725b6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ResourceTypeSmClass)this.getTarget()).getTypedResourceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("24f11f89-2160-4c0a-95f0-7fc1cbf68185")
    public static class SubjectSmDependency extends SmSingleDependency {
        @objid ("26539eb2-2bc4-4aa0-8d1c-58f8bc07ebc8")
        private SmDependency symetricDep;

        @objid ("dea0b801-1219-4fe0-ae0e-3447b9b990cc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mSubject;
        }

        @objid ("557a043a-cfe1-4755-9af3-d587e8040488")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mSubject = value;
        }

        @objid ("af6ffb3f-9b60-438b-a6be-4d943d0d5e87")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getAttachedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ba01ab1b-5d45-401b-8f6b-abe1243f518c")
    public static class StorageInfoSmAttribute extends SmAttribute {
        @objid ("d1b7f121-05ad-478c-bdc2-9df9563d6e1f")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mStorageInfo;
        }

        @objid ("4f6cb12c-38ec-4bea-9af0-e611841d2c45")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mStorageInfo = value;
        }

    }

}
