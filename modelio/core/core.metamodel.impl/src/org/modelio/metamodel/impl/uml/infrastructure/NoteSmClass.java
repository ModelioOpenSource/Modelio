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
    @objid ("2b0ab586-d3eb-48eb-96be-8d963e283e27")
    private SmAttribute contentAtt;

    @objid ("afc0b56a-bce2-4070-979d-865a209062b5")
    private SmAttribute mimeTypeAtt;

    @objid ("5c63deda-bf5d-4208-83f5-cd2699fd00d9")
    private SmDependency modelDep;

    @objid ("71fe0965-62ef-4f47-8a3b-938540e6d9ca")
    private SmDependency subjectDep;

    @objid ("182160c0-dc08-4d49-8776-887d2318c554")
    public  NoteSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3d81fe36-10c1-4571-ae21-2c74422cb03b")
    @Override
    public String getName() {
        return "Note";
        
    }

    @objid ("17147b4f-f6a2-4c7a-956e-ec45fe623325")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("38964edf-1f12-470f-8c18-65f1f73e30d8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Note.class;
        
    }

    @objid ("33914322-626b-4ef6-96bf-090d65343db6")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fb11cc47-72b9-4c69-a085-4531d85f30cd")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ce748da5-08ee-47b3-a731-074d91235d94")
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

    @objid ("f360b730-bbb7-4e7b-9e75-ae794fe05062")
    public SmAttribute getContentAtt() {
        if (this.contentAtt == null) {
        	this.contentAtt = this.getAttributeDef("Content");
        }
        return this.contentAtt;
    }

    @objid ("749e973a-2ab1-46c0-81d5-51cfa1b801f0")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("b8d31705-560e-4599-9414-cb21a31ce8c6")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("ac7297eb-a697-4a41-8bf3-9ee804a01510")
    public SmDependency getSubjectDep() {
        if (this.subjectDep == null) {
        	this.subjectDep = this.getDependencyDef("Subject");
        }
        return this.subjectDep;
    }

    @objid ("6e6203ed-0b4f-479e-b758-d1a4549c3e06")
    private static class NoteObjectFactory implements ISmObjectFactory {
        @objid ("1b3da826-11f1-44af-9a4a-35f0c0ee3510")
        private NoteSmClass smClass;

        @objid ("945031cd-3df6-456d-bd39-e9697540ee8c")
        public  NoteObjectFactory(NoteSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9537fbb4-2f89-472a-b10e-fcf54b9d98e8")
        @Override
        public ISmObjectData createData() {
            return new NoteData(this.smClass);
        }

        @objid ("2cba4833-c619-416f-88a9-dbe8de33b9ec")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteImpl();
        }

    }

    @objid ("82dab33a-ddfc-46de-a05d-0dcbda2a8442")
    public static class ContentSmAttribute extends SmAttribute {
        @objid ("6b0b68c4-2e32-4bcf-b653-4eb9fb78dbb1")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mContent;
        }

        @objid ("1ec46503-c1e3-4e32-89f9-4b0194f74b09")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mContent = value;
        }

    }

    @objid ("efe57f96-877d-444e-8187-cc06fa38762f")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("b13d58ef-fe1d-4b06-9366-fdf4c138f59a")
        public Object getValue(ISmObjectData data) {
            return ((NoteData) data).mMimeType;
        }

        @objid ("d0acd4a4-81a5-4a9c-bb4a-889bd561f70f")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteData) data).mMimeType = value;
        }

    }

    @objid ("4704f32e-18dd-4db4-8d94-6be49bad7ab3")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("3fabe734-7659-4626-aa69-602e0d5c58d1")
        private SmDependency symetricDep;

        @objid ("b4ce2453-8d32-4abd-821f-9b854c18fe6c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mModel;
        }

        @objid ("a59fe2e2-9ae7-474b-8f44-a083a33e0a9e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mModel = value;
        }

        @objid ("5def1649-a51e-4294-be67-fca00570afdf")
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
        @objid ("45a658b1-293d-4069-b021-05ecf97ab4cc")
        private SmDependency symetricDep;

        @objid ("dcf3f47d-8ee2-46d9-9a1d-9210dffc066b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteData) data).mSubject;
        }

        @objid ("8da6a2c4-cd46-47b4-86db-1772acc5cf73")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteData) data).mSubject = value;
        }

        @objid ("831b16a6-7606-4fcc-b93b-63f2e2a56033")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDescriptorDep();
            }
            return this.symetricDep;
            
        }

    }

}
