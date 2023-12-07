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
    @objid ("749c8a58-9664-416b-b821-e2ae661d1710")
    private SmAttribute mimeTypeAtt;

    @objid ("79bbe160-57ef-4b90-94f3-bd0118ac3e11")
    private SmAttribute storageInfoAtt;

    @objid ("8b9a8c26-76ee-4160-9377-11a8c499364f")
    private SmDependency typeDep;

    @objid ("63703105-b091-4e56-a6a8-f54a3e2193df")
    private SmDependency subjectDep;

    @objid ("9de31bc4-a70c-4d0b-84aa-c2ec6c33d77b")
    public  AbstractResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a451cc7b-fc6b-46a4-9446-2387f855d319")
    @Override
    public String getName() {
        return "AbstractResource";
        
    }

    @objid ("0e7560f2-c06c-49c4-ac6a-83e4f97166f3")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("233e5dcf-81f7-45d6-b8ec-af43d67de6da")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractResource.class;
        
    }

    @objid ("b7656f4a-e8e7-4d0b-985b-1ef9172a50e5")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("e40f6249-6d81-4beb-a3f8-4d9453bb266d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("cd3a194d-95c2-44b6-b0de-f8139b851f62")
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

    @objid ("1ee7df00-c4c3-4a7f-aa89-6afb9c758f14")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("8bac259a-1835-4a4e-90af-f7c01edabe5f")
    public SmAttribute getStorageInfoAtt() {
        if (this.storageInfoAtt == null) {
        	this.storageInfoAtt = this.getAttributeDef("StorageInfo");
        }
        return this.storageInfoAtt;
    }

    @objid ("234ba906-96aa-4246-b1f2-fc3fae875e03")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("4170b672-dd81-4382-972e-e02a112ba486")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("534c467f-e591-40a6-ad60-770e701418b5")
    private static class AbstractResourceObjectFactory implements ISmObjectFactory {
        @objid ("700da456-705c-4977-b402-73cdb10a0d0b")
        private AbstractResourceSmClass smClass;

        @objid ("1eea3e27-edce-40cc-a17e-8a643a06349f")
        public  AbstractResourceObjectFactory(AbstractResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e85fe082-7031-4bb9-9999-6fda1ddaa736")
        @Override
        public ISmObjectData createData() {
            return new AbstractResourceData(this.smClass);
        }

        @objid ("2401ccbb-35a3-400c-a9ed-0e44b08cf3f7")
        @Override
        public SmObjectImpl createImpl() {
            return new AbstractResourceImpl();
        }

    }

    @objid ("56f5b283-13ce-4ee2-b224-240dc6002dce")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("19f40b7b-decf-4c49-b440-b1435802b8fb")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mMimeType;
        }

        @objid ("934b35aa-8baa-467b-9baf-9196d22ab73d")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mMimeType = value;
        }

    }

    @objid ("b104e3fb-7ba5-4031-873e-1e94d427afff")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("21c19591-2bde-458d-8aee-d34e647186fa")
        private SmDependency symetricDep;

        @objid ("35b7ae05-b38f-4c37-93fd-09694c640528")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mType;
        }

        @objid ("a98b6f22-3361-4d83-abb1-48e5667156b3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mType = value;
        }

        @objid ("18d4e251-441a-487e-91f4-7eb894bc76ac")
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
        @objid ("c6e58bb2-8e0e-4785-bc4c-e736e5bb73e9")
        private SmDependency symetricDep;

        @objid ("7ad16d3d-8e95-4d0c-bcbd-3dd7e59a1de2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mSubject;
        }

        @objid ("91152ac2-8f3f-485b-85ec-7264bd2307eb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mSubject = value;
        }

        @objid ("3839e6c9-8ca3-4c7d-a580-c040f4aac1ad")
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
        @objid ("de081175-ea00-49d0-bd82-afb563cee8ff")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mStorageInfo;
        }

        @objid ("c34f0ab2-42f0-46a1-ab79-8b5d32bb7e3f")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mStorageInfo = value;
        }

    }

}
