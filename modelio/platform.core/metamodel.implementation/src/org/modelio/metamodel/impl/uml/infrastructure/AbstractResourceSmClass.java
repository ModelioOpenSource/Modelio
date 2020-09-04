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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeSmClass;
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
    @objid ("2ae85527-d990-4f3f-8c09-1cbe5755caaf")
    private SmAttribute mimeTypeAtt;

    @objid ("75e25af5-1fc6-4e72-a943-00e61324140c")
    private SmAttribute storageInfoAtt;

    @objid ("8bb7cdf8-cb12-442d-a3a2-fedbb380bd95")
    private SmDependency typeDep;

    @objid ("0bd1cc47-a069-43cd-b310-5a4deba9f08d")
    private SmDependency subjectDep;

    @objid ("c1bf33dc-dfd7-43b9-994b-fa89b0fb4176")
    public AbstractResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5ab27034-4349-4325-a26d-c54416e33a62")
    @Override
    public String getName() {
        return "AbstractResource";
    }

    @objid ("01b64331-8f7a-4d21-a71e-650938f60386")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("5674668c-8071-4704-aff4-8fa315bf5b34")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractResource.class;
    }

    @objid ("416f2a82-a73f-4489-b6ce-45b0cd5bb520")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("fe26d0b5-5683-4e59-a963-c7c1806fad83")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("129effab-ffb8-42a4-919d-f80ccab96ba9")
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

    @objid ("b4b2fcd4-051e-4be7-bf0c-94e8b9863239")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("79379e72-92a2-49e7-ae45-ca18df0535b6")
    public SmAttribute getStorageInfoAtt() {
        if (this.storageInfoAtt == null) {
        	this.storageInfoAtt = this.getAttributeDef("StorageInfo");
        }
        return this.storageInfoAtt;
    }

    @objid ("51c2e26c-604f-4933-b818-78c83a510dbc")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("464b61ef-11a4-446e-875f-6fa2a08b87e7")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("534c467f-e591-40a6-ad60-770e701418b5")
    private static class AbstractResourceObjectFactory implements ISmObjectFactory {
        @objid ("add34a86-7b15-4d86-a185-490c3248041d")
        private AbstractResourceSmClass smClass;

        @objid ("67e3b991-4cec-43c2-9884-5228bea7d309")
        public AbstractResourceObjectFactory(AbstractResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("15ee04ab-7e88-4e44-bf4d-4e992d599e6a")
        @Override
        public ISmObjectData createData() {
            return new AbstractResourceData(this.smClass);
        }

        @objid ("a262053e-4403-49d7-8201-4f65d5a6190d")
        @Override
        public SmObjectImpl createImpl() {
            return new AbstractResourceImpl();
        }

    }

    @objid ("56f5b283-13ce-4ee2-b224-240dc6002dce")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("aceb84e3-1598-48a7-8b32-2d5c31f22572")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mMimeType;
        }

        @objid ("9bce5aaf-75a9-4e50-8448-66d86b2530b9")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mMimeType = value;
        }

    }

    @objid ("b104e3fb-7ba5-4031-873e-1e94d427afff")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("cbe90382-81b7-4c36-b3d5-e950e7e46751")
        private SmDependency symetricDep;

        @objid ("2dbabc82-7ef9-4b0f-be1f-d54cc3ba7dd9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mType;
        }

        @objid ("675510ae-8a97-4413-8798-3d772029bdb9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mType = value;
        }

        @objid ("a4b0463b-b02b-4e19-9c8b-f98ebfca1b4b")
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
        @objid ("ce88c3fb-975b-4bd0-99b1-f84ad5889331")
        private SmDependency symetricDep;

        @objid ("54ea6b93-1b94-46be-ac8b-ca3a59b9751a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mSubject;
        }

        @objid ("60322579-0141-433b-97b9-123b54f70150")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractResourceData) data).mSubject = value;
        }

        @objid ("2223297c-0fb3-4e4b-80db-741554bf8f64")
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
        @objid ("59623278-4d76-4f17-8726-79a0da18f6d1")
        public Object getValue(ISmObjectData data) {
            return ((AbstractResourceData) data).mStorageInfo;
        }

        @objid ("1af83c87-2a6e-4a71-87c5-d2f6c8d9dc6a")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractResourceData) data).mStorageInfo = value;
        }

    }

}
