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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeData;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("791cf857-e0a6-4d45-b992-f00114cf2def")
public class NoteTypeSmClass extends ModelElementSmClass {
    @objid ("73ef2ce0-21fb-46bd-ae00-f0bcc1d67521")
    private SmAttribute isHiddenAtt;

    @objid ("7a2c8200-7f08-408b-b24b-c8447e461451")
    private SmAttribute labelKeyAtt;

    @objid ("8c702ed9-db1d-4376-83ed-07d7fde16c43")
    private SmAttribute mimeTypeAtt;

    @objid ("24e4b8d7-b3bb-4044-985f-7a969403a385")
    private SmDependency elementDep;

    @objid ("c263d039-7f36-4555-981b-4fc3f70f004d")
    private SmDependency ownerStereotypeDep;

    @objid ("7085a149-38b2-4c21-b0de-4bd92bb7a2ef")
    private SmDependency ownerReferenceDep;

    @objid ("4761ec33-b5c1-482b-9b81-d29f72ad06b6")
    public NoteTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e787b1d0-ec5e-43d5-9947-70d3e375cab7")
    @Override
    public String getName() {
        return "NoteType";
    }

    @objid ("7727780c-2b1a-4c9e-910a-2a94771a6ae7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("927a76c5-11eb-47d7-993d-ed36ec97504e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NoteType.class;
    }

    @objid ("21389ea0-56dd-425b-8715-5254eed25a9e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("c0cea6a5-3e70-452a-afe9-2d5b776798f3")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("566f0510-8809-45ac-a997-f93db1e4eec5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new NoteTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isHiddenAtt = new IsHiddenSmAttribute();
        this.isHiddenAtt.init("IsHidden", this, Boolean.class );
        registerAttribute(this.isHiddenAtt);
        
        this.labelKeyAtt = new LabelKeySmAttribute();
        this.labelKeyAtt.init("LabelKey", this, String.class );
        registerAttribute(this.labelKeyAtt);
        
        this.mimeTypeAtt = new MimeTypeSmAttribute();
        this.mimeTypeAtt.init("MimeType", this, String.class );
        registerAttribute(this.mimeTypeAtt);
        
        
        // Initialize and register the SmDependency
        this.elementDep = new ElementSmDependency();
        this.elementDep.init("Element", this, metamodel.getMClass(Note.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.elementDep);
        
        this.ownerStereotypeDep = new OwnerStereotypeSmDependency();
        this.ownerStereotypeDep.init("OwnerStereotype", this, metamodel.getMClass(Stereotype.MQNAME), 0, 1 );
        registerDependency(this.ownerStereotypeDep);
        
        this.ownerReferenceDep = new OwnerReferenceSmDependency();
        this.ownerReferenceDep.init("OwnerReference", this, metamodel.getMClass(MetaclassReference.MQNAME), 0, 1 );
        registerDependency(this.ownerReferenceDep);
    }

    @objid ("d42f6e26-ef30-49cf-8717-33f113201098")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("299d8ed4-8de1-4631-aaf8-c483d0630b4e")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("bb4318ee-d0dc-4e82-bc9f-b984d650c80c")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("e2246acf-a43a-4f24-b3aa-a36b60354c66")
    public SmDependency getElementDep() {
        if (this.elementDep == null) {
        	this.elementDep = this.getDependencyDef("Element");
        }
        return this.elementDep;
    }

    @objid ("7464fe69-094e-40dc-b736-524ecefbc298")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("d59b10ce-abd8-43e3-9c30-dfe35535f002")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("1c63226f-d265-4504-8721-e7d86bf038f1")
    private static class NoteTypeObjectFactory implements ISmObjectFactory {
        @objid ("0a052c00-8c83-4b60-9f95-feaf50e32e61")
        private NoteTypeSmClass smClass;

        @objid ("55230b01-e114-4f0f-8d6f-06b42923d71a")
        public NoteTypeObjectFactory(NoteTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("adb34a42-6508-4722-b255-8e091ad2a959")
        @Override
        public ISmObjectData createData() {
            return new NoteTypeData(this.smClass);
        }

        @objid ("2795bb48-13a4-402b-bd4e-399e12dd6043")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteTypeImpl();
        }

    }

    @objid ("6b55654f-85e1-4bd1-8a09-47158337c8f4")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("b2b59256-4bb2-4531-bff4-6ff8d0ea4a4f")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mIsHidden;
        }

        @objid ("49f5aaa9-d622-4598-ae51-20ba553bf471")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mIsHidden = value;
        }

    }

    @objid ("38cc8079-8fcf-413b-ad0b-a57fde886708")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("0c9f93ee-22ce-4e67-84f6-ed5e023d03e5")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mLabelKey;
        }

        @objid ("9a21471a-c2df-466b-9cdc-7d6567392f00")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7dff0381-4d43-4649-912b-3e24ee51e21e")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("bc872578-9595-49e7-8db3-a17ad6e1a0b5")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mMimeType;
        }

        @objid ("ec8fcca4-6b31-4001-b0ab-1b26520607c0")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mMimeType = value;
        }

    }

    @objid ("7618a32e-1219-4439-b06a-663e1f7d2ed9")
    public static class ElementSmDependency extends SmMultipleDependency {
        @objid ("3c3f9f79-5407-4240-a4ff-028ff58c2b1a")
        private SmDependency symetricDep;

        @objid ("e28fb44c-260a-4cb6-9837-c3306491030c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NoteTypeData)data).mElement != null)? ((NoteTypeData)data).mElement:SmMultipleDependency.EMPTY;
        }

        @objid ("8eb846fe-b8d6-4fdc-bbf1-ec479fc8c637")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NoteTypeData) data).mElement = values;
        }

        @objid ("fea04d6a-3621-4bd5-9d61-e482e4c2f302")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NoteSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("80cb6dbf-e821-47d8-a7df-80e95f5e9ed7")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("3e2a6b80-5982-4ca5-adf2-9451e944d1f5")
        private SmDependency symetricDep;

        @objid ("c6908274-5c65-47a8-938c-70f5c862df91")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerStereotype;
        }

        @objid ("975d1328-fdd6-4664-b309-af2922cf4f45")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerStereotype = value;
        }

        @objid ("a6557fdf-52b6-424d-806e-a5c999565eff")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getDefinedNoteTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("52597b06-bec7-4976-86c1-55592600a17a")
    public static class OwnerReferenceSmDependency extends SmSingleDependency {
        @objid ("777742a1-4b8f-437f-8eda-801de54b739a")
        private SmDependency symetricDep;

        @objid ("cd47cf5f-6f3e-4fb0-ac08-2a7ef846bcfc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerReference;
        }

        @objid ("05638d97-0ca9-4b1e-8594-78dd46c9dbe8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerReference = value;
        }

        @objid ("737dea6f-9400-4823-abd5-277f99a2bd18")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedNoteTypeDep();
            }
            return this.symetricDep;
        }

    }

}
