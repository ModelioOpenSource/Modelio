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
    @objid ("c4843990-ce9e-4279-acb5-45036ff6b527")
    private SmAttribute contentAtt;

    @objid ("8411e94e-d212-4ae4-8d32-b7dca985975e")
    private SmAttribute mimeTypeAtt;

    @objid ("f55c61c4-0879-4fca-a14a-ce15bc6c99db")
    private SmDependency modelDep;

    @objid ("24835153-2adf-454a-83cf-abbf396b1249")
    private SmDependency subjectDep;

    @objid ("19eaf28f-4116-4c54-a6ad-72b6f6547f60")
    public  NoteSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b8074f96-7ea2-4751-8409-70b5054ff342")
    @Override
    public String getName() {
        return "Note";
        
    }

    @objid ("19492eb1-8637-4c8d-9b72-17ddcb88d6ab")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ed88d9aa-bcf1-431d-ab24-3bf76b84b3f8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Note.class;
        
    }

    @objid ("a57c9f54-1640-44e9-8c66-24bca00f3b96")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ada4314a-92c7-4921-a740-e98bc22d04d3")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7b932367-a47a-46f3-bd63-45fffdc21677")
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

    @objid ("34f366ea-68ba-4671-a809-04205f895899")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("43837f61-c5b0-4e9a-a46a-c248da8559d4")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("8ebd3302-0232-4c9d-91ae-ef77967e790a")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("519f00ff-12f1-4a31-9294-73c67a7bfdcb")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("6e6203ed-0b4f-479e-b758-d1a4549c3e06")
    private static class NoteObjectFactory implements ISmObjectFactory {
        @objid ("ae9c4012-8128-4861-ad51-e67554d83ae3")
        private NoteSmClass smClass;

        @objid ("aefb53f6-715d-4179-a02a-508b9a4dacc1")
        public  NoteObjectFactory(NoteSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a4af655f-d974-4d64-90a2-d41eed3c8173")
        @Override
        public ISmObjectData createData() {
            return new NoteData(this.smClass);
        }

        @objid ("05753705-361f-42e7-b2a5-98b0cdb8e06e")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteImpl();
        }

    }

    @objid ("82dab33a-ddfc-46de-a05d-0dcbda2a8442")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("28970e4d-e47a-4f16-bff4-b24ba5404276")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mContent;
        }

        @objid ("8903fbe0-5373-4be6-9e8b-6b19b74dcb5e")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mContent = value;
        }

    }

    @objid ("efe57f96-877d-444e-8187-cc06fa38762f")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("831828b6-e2c9-4002-b7e6-25053adc4e0a")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mMimeType;
        }

        @objid ("4f24ef17-22ac-4f05-a330-ad642789c853")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mMimeType = value;
        }

    }

    @objid ("4704f32e-18dd-4db4-8d94-6be49bad7ab3")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("1515f282-a994-40d7-b111-108a4978c946")
        private SmDependency symetricDep;

        @objid ("bbca9fff-6270-49da-9180-958c42bbbae2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mModel;
        }

        @objid ("dc478f17-ed50-47d0-a4b6-478185da84db")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mModel = value;
        }

        @objid ("5a0adc66-c89a-4370-8ce2-044e1a656bfa")
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
        @objid ("ca07e95a-4f18-4842-b868-2a9172ba5847")
        private SmDependency symetricDep;

        @objid ("a317b7ee-52d9-4192-a7f1-0bcfcb97a813")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mSubject;
        }

        @objid ("e0bd5e52-f449-4e5a-9886-785078d36a71")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mSubject = value;
        }

        @objid ("494183ee-7151-4686-a029-e3f1fb3e8b6e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDescriptorDep();
            }
            return this.symetricDep;
            
        }

    }

}
