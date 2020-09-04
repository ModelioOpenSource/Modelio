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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteData;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
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

@objid ("760b5a4c-82aa-493b-ab51-00c95b2afa6e")
public class NoteSmClass extends ModelElementSmClass {
    @objid ("740e0a84-91e1-4841-bbd5-f7b0d81b2c72")
    private SmAttribute contentAtt;

    @objid ("c5cdd3ac-d084-4f43-9f5d-2e480a81d30b")
    private SmAttribute mimeTypeAtt;

    @objid ("2ee49132-0946-4e67-a786-7053e4f8b4af")
    private SmDependency modelDep;

    @objid ("435b515c-e993-43a6-a49d-34861985a1b0")
    private SmDependency subjectDep;

    @objid ("5c6fa326-a556-4bf9-8320-307c2451f36c")
    public NoteSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("11f3e201-b204-4961-ba64-781af7cb6195")
    @Override
    public String getName() {
        return "Note";
    }

    @objid ("88de66f6-0b1c-4729-9b78-80aad4951d4a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("64292632-a06f-49ff-b66b-7b4a0e23bcfb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Note.class;
    }

    @objid ("1ec1bec3-65d6-4b7f-bcc3-1e4bb662816f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("727adf68-2284-44c1-acdf-2a9aadb976c8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c1cefcec-a312-44b4-ab6b-8fa80a85c75d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new NoteObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.contentAtt = new ContentSmAttribute();
        this.contentAtt.init("Content", this, String.class );
        registerAttribute(this.contentAtt);
        
        this.mimeTypeAtt = new MimeTypeSmAttribute();
        this.mimeTypeAtt.init("MimeType", this, String.class );
        registerAttribute(this.mimeTypeAtt);
        
        
        // Initialize and register the SmDependency
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(NoteType.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.modelDep);
        
        this.subjectDep = new SubjectSmDependency();
        this.subjectDep.init("Subject", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 );
        registerDependency(this.subjectDep);
    }

    @objid ("580c97b4-f13c-4e76-a43a-b20eec34d2ed")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("d6985390-0b2c-42a6-a725-ab1abca6996c")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("02755d2a-727b-4289-ae85-6bc7f8efa997")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("23cd442e-ec79-49f5-8c5c-529d96da60d6")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("6e6203ed-0b4f-479e-b758-d1a4549c3e06")
    private static class NoteObjectFactory implements ISmObjectFactory {
        @objid ("7518129c-49c2-4c9c-a530-8f88fcee67a4")
        private NoteSmClass smClass;

        @objid ("41fc4469-712a-4ee5-99cd-fe6b8b67cc42")
        public NoteObjectFactory(NoteSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1ec931c4-d520-4ba4-aace-e6dc1144a8cb")
        @Override
        public ISmObjectData createData() {
            return new NoteData(this.smClass);
        }

        @objid ("03a37266-1d61-4578-a91f-fd2c55253ab8")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteImpl();
        }

    }

    @objid ("82dab33a-ddfc-46de-a05d-0dcbda2a8442")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("4826c8d4-021a-4973-9e58-4f17e3707195")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mContent;
        }

        @objid ("f4140e1a-869e-4b4f-bb28-015626ba6dc6")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mContent = value;
        }

    }

    @objid ("efe57f96-877d-444e-8187-cc06fa38762f")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("1122e0ed-d580-4d80-a4c1-5f9d1ca90070")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mMimeType;
        }

        @objid ("46daff91-34bc-4aac-b59f-1bfc75d99774")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mMimeType = value;
        }

    }

    @objid ("4704f32e-18dd-4db4-8d94-6be49bad7ab3")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("fb9c6b72-ca7e-4c93-98d6-e105ca1f3f48")
        private SmDependency symetricDep;

        @objid ("b4d11a95-9524-4f79-8a16-6c859e1ae872")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mModel;
        }

        @objid ("e99bab5d-b7c6-416d-85b9-8e822ce541da")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mModel = value;
        }

        @objid ("cd6b09a5-544a-4a2d-a5d2-73ed2a67bf17")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NoteTypeSmClass)this.getTarget()).getElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("291a2d86-31bb-44f7-bbb2-059ad8422940")
    public static class SubjectSmDependency extends SmSingleDependency {
        @objid ("ce830aac-0fa0-47d2-960c-8388f75eb844")
        private SmDependency symetricDep;

        @objid ("d22711f5-a01b-4eb2-ac84-85ca7f62a6cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mSubject;
        }

        @objid ("f15bd2e9-649e-4b0a-8a6a-30ef9208de7d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mSubject = value;
        }

        @objid ("b26310a4-e90d-4d4b-a230-d312317b9863")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDescriptorDep();
            }
            return this.symetricDep;
        }

    }

}
