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
    @objid ("be702008-7916-4354-bbdc-0222a25f46ec")
    private SmAttribute isHiddenAtt;

    @objid ("38fdc64e-1564-4af7-909f-a95fa0b1a1e9")
    private SmAttribute labelKeyAtt;

    @objid ("69ced834-ed3d-4573-90c7-6aff7e530c1e")
    private SmAttribute mimeTypeAtt;

    @objid ("3199be8f-122d-45e5-8058-8235a49aaf93")
    private SmDependency elementDep;

    @objid ("a4f725a7-eed9-42fe-b643-c0ef88ef23dd")
    private SmDependency ownerStereotypeDep;

    @objid ("4aabcaa8-a30b-464e-95ac-417a8bd4ca9e")
    private SmDependency ownerReferenceDep;

    @objid ("424f05d7-de0c-4cc2-91c1-ac6a0fcf966d")
    public NoteTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("92f5bbd7-e578-47bb-b9ac-788e75dab748")
    @Override
    public String getName() {
        return "NoteType";
    }

    @objid ("2723b83a-2074-4a7f-874c-e30f46b3f703")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("151a8c9d-4d56-444f-85e9-b8cc439ef523")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NoteType.class;
    }

    @objid ("10e428f5-a6f9-4f6f-b58b-a50bdd5e898e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6f608f87-cdbf-4ad6-8a3b-6e9388da1e22")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ceed1298-65da-48b9-98c5-9edcc296a66e")
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

    @objid ("3a1377cc-f5f9-4d9c-8555-6563036e4e09")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("b2176543-668c-4fc4-b73f-26f5162406c6")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("f04e0b3c-0990-41fb-9bcb-9352cd9be5ff")
    public SmAttribute getMimeTypeAtt() {
        if (this.mimeTypeAtt == null) {
        	this.mimeTypeAtt = this.getAttributeDef("MimeType");
        }
        return this.mimeTypeAtt;
    }

    @objid ("0dc6fca5-cdee-4edf-98df-f7899bf06c98")
    public SmDependency getElementDep() {
        if (this.elementDep == null) {
        	this.elementDep = this.getDependencyDef("Element");
        }
        return this.elementDep;
    }

    @objid ("96d13da1-85d0-4cc3-bc00-e0aed405f26a")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("23c7ab84-3033-4448-a994-d1911e2b0a95")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("1c63226f-d265-4504-8721-e7d86bf038f1")
    private static class NoteTypeObjectFactory implements ISmObjectFactory {
        @objid ("7fc12c2e-2f1f-4ce1-a2b8-b9779a3c2f5e")
        private NoteTypeSmClass smClass;

        @objid ("7b88cb4f-fe8e-40a6-89ef-f7721b0683ce")
        public NoteTypeObjectFactory(NoteTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7699eb93-71f2-41c3-b4ea-a2b87f553521")
        @Override
        public ISmObjectData createData() {
            return new NoteTypeData(this.smClass);
        }

        @objid ("5ff97a14-1fc9-4a63-9b22-b63e7846a73b")
        @Override
        public SmObjectImpl createImpl() {
            return new NoteTypeImpl();
        }

    }

    @objid ("6b55654f-85e1-4bd1-8a09-47158337c8f4")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("32c29449-9b9d-4016-b1de-4bac4f86de61")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mIsHidden;
        }

        @objid ("027aa9c1-57ef-437f-a28f-675f19cdc299")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mIsHidden = value;
        }

    }

    @objid ("38cc8079-8fcf-413b-ad0b-a57fde886708")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("ab876dbf-2fd1-480c-9ba0-a1dc3d3cea26")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mLabelKey;
        }

        @objid ("63a9a0fb-7183-4e2a-88b8-a501faa9a73f")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7dff0381-4d43-4649-912b-3e24ee51e21e")
    public static class MimeTypeSmAttribute extends SmAttribute {
        @objid ("b1d0069c-e228-4564-b656-4473c01358ab")
        public Object getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mMimeType;
        }

        @objid ("2f0d015c-96f1-49fd-928a-d22d231f02fd")
        public void setValue(ISmObjectData data, Object value) {
            ((NoteTypeData) data).mMimeType = value;
        }

    }

    @objid ("7618a32e-1219-4439-b06a-663e1f7d2ed9")
    public static class ElementSmDependency extends SmMultipleDependency {
        @objid ("2feeb1c7-7a05-4707-82e9-c4d66ecf1735")
        private SmDependency symetricDep;

        @objid ("1b9f558b-f6bb-4751-b143-5a46c0bffaad")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NoteTypeData)data).mElement != null)? ((NoteTypeData)data).mElement:SmMultipleDependency.EMPTY;
        }

        @objid ("095e9106-3259-467e-9269-2f27f6a5c0bc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NoteTypeData) data).mElement = values;
        }

        @objid ("a89d24b6-cdf9-4059-8f62-93a6cda1f47f")
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
        @objid ("f12807d1-3db7-4bd2-aacb-a1c03b39c126")
        private SmDependency symetricDep;

        @objid ("626a060a-1355-49f7-af87-323c36b9fdb4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerStereotype;
        }

        @objid ("1d01b54f-7b1c-444a-819a-343e30e9a149")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerStereotype = value;
        }

        @objid ("d30fbc77-06ac-40b3-8016-243545ef92d4")
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
        @objid ("1205e79a-c5e2-41c6-ad04-eea931e55ba4")
        private SmDependency symetricDep;

        @objid ("9d1e018f-f0c3-4406-903b-0740d19ad1f8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NoteTypeData) data).mOwnerReference;
        }

        @objid ("4f173b10-b961-4eb9-a60f-221b3cc26d66")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NoteTypeData) data).mOwnerReference = value;
        }

        @objid ("7f6cca44-66ae-4681-a3fc-dced94d54295")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedNoteTypeDep();
            }
            return this.symetricDep;
        }

    }

}
