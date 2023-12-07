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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
    @objid ("97881996-2ebe-4ef0-bc8e-229e31f8b4a7")
    private SmAttribute isHiddenAtt;

    @objid ("4aa2826e-91e1-4e33-b0e5-843c7b768109")
    private SmAttribute labelKeyAtt;

    @objid ("8c456c1a-8b58-47db-8c3f-283a882f73f8")
    private SmAttribute mimeTypeAtt;

    @objid ("e08e0910-c98b-46aa-a893-f40662eb9f96")
    private SmDependency elementDep;

    @objid ("1dca177c-cb29-4e1e-bbd9-b3024d9b9afd")
    private SmDependency ownerStereotypeDep;

    @objid ("a16e81c1-1cae-4133-b57f-84702f6b0018")
    private SmDependency ownerReferenceDep;

    @objid ("6597bc4a-99fc-4cd5-b26a-31a74183c710")
    public  NoteTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d001a85c-2d0b-4b2c-9fad-69e4c4e8c857")
    @Override
    public String getName() {
        return "NoteType";
        
    }

    @objid ("75f586bb-7a23-49ca-9baf-94910150464e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("28129bc2-24fc-4d6e-91d7-26827a45b174")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NoteType.class;
        
    }

    @objid ("d4995a00-3914-458d-94ad-0f4f31c2e24c")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("ac02eb3e-097c-43e1-b324-676e0cc4dc30")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("9da28934-521b-4f7f-9d01-05010a3cb7da")
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

    @objid ("581af7bc-b14a-4c70-99fb-4cff6e073b2b")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("a01e1557-c1f0-4a1c-8399-1c353b1d508b")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("35d257a2-5739-405a-9a9a-e95ce1b4a33d")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("6ac15c51-dccd-4762-ae51-9586a434c71d")
    public SmDependency getElementDep() {
        if (this.elementDep == null) {
        	this.elementDep = this.getDependencyDef("Element");
        }
        return this.elementDep;
    }

    @objid ("6b506806-f7ad-443b-9443-1c690aa887cc")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("ca84a36a-33dc-4674-9a7a-de136101582e")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("1c63226f-d265-4504-8721-e7d86bf038f1")
    private static class NoteTypeObjectFactory implements ISmObjectFactory {
        @objid ("2b8f7b22-0e18-4580-b044-9a9a5682819e")
        private NoteTypeSmClass smClass;

        @objid ("85ec0b80-90ba-4ae5-8584-ea831ff1ad98")
        public  NoteTypeObjectFactory(NoteTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("de3a1ca7-3a2d-4272-89b5-94885e27b703")
        @Override
        public ISmObjectData createData() {
            return new NoteTypeData(this.smClass);
        }

        @objid ("76f6ef1f-4059-43cb-9364-9ea7d9a24770")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteTypeImpl();
        }

    }

    @objid ("6b55654f-85e1-4bd1-8a09-47158337c8f4")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("e62cd8e8-1424-432e-ad9b-6174d674f6c4")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mIsHidden;
        }

        @objid ("f1c1672b-4320-41c6-8ccd-c6beda1f73a7")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mIsHidden = value;
        }

    }

    @objid ("38cc8079-8fcf-413b-ad0b-a57fde886708")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("6355eb31-8f7c-4a45-b3e5-4ea21bc18b32")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mLabelKey;
        }

        @objid ("73c4b6ba-cca4-4b8b-9a3e-46151b2332c0")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7dff0381-4d43-4649-912b-3e24ee51e21e")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("f494869b-1a68-4be6-96a8-015982d58890")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mMimeType;
        }

        @objid ("3b1b6259-5cf5-4c16-b29d-97daf6cf8ddc")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mMimeType = value;
        }

    }

    @objid ("7618a32e-1219-4439-b06a-663e1f7d2ed9")
    public static class ElementSmDependency extends SmMultipleDependency {
        @objid ("22777293-9732-4f53-8ccc-cd23b44e272d")
        private SmDependency symetricDep;

        @objid ("db8f6d42-f1c7-415b-8e02-9e3dcb7d3b79")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NoteTypeData)data).mElement != null)? ((NoteTypeData)data).mElement:SmMultipleDependency.EMPTY;
        }

        @objid ("3b8b3344-bc74-4888-9bb5-53f314d488c6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NoteTypeData) data).mElement = values;
            
        }

        @objid ("65f76e65-74cf-4aee-a49d-68affdc50358")
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
        @objid ("8b719f88-ef55-4910-8bc4-d372cb556dee")
        private SmDependency symetricDep;

        @objid ("6ab1454c-2fcf-4905-9534-bef23943fd1b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerStereotype;
        }

        @objid ("db0705ec-e510-44e8-b680-044e80b68bc9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerStereotype = value;
        }

        @objid ("3f65f952-bb73-4008-9396-c8133a1c26e1")
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
        @objid ("69bc8148-9829-4ded-b03f-1bca8be5efb5")
        private SmDependency symetricDep;

        @objid ("c8fe023b-a331-4dce-8cdc-f394405a3b6a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerReference;
        }

        @objid ("893e85ca-db41-4ec8-8439-035cfe7727b4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerReference = value;
        }

        @objid ("043efa69-c510-4463-a64e-d16893e39ed9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedNoteTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
