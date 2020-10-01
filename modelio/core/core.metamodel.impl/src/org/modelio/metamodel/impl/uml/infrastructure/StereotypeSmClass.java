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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeData;
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
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

@objid ("e7b338a3-8dcc-4bf8-82ff-571a6f874e3d")
public class StereotypeSmClass extends ModelElementSmClass {
    @objid ("c8d6a020-514a-42ce-b7eb-382f3c29d5c1")
    private SmAttribute imageAtt;

    @objid ("34d6523e-4e19-43a7-9798-35adb99bef30")
    private SmAttribute iconAtt;

    @objid ("b12d2aa9-a772-40f2-8121-5b5322d4c2a5")
    private SmAttribute isHiddenAtt;

    @objid ("b8e27347-197f-43e1-879c-7ff02e60d677")
    private SmAttribute isAbstractAtt;

    @objid ("c5bb96fe-05b7-492c-bb68-304e5457b5b5")
    private SmAttribute labelKeyAtt;

    @objid ("de78a984-8fde-47d4-b06c-933112bd711a")
    private SmAttribute baseClassNameAtt;

    @objid ("5e4fbeb1-2ad6-42e3-8a1c-0e12d92dc676")
    private SmDependency definedTableDep;

    @objid ("be6c1f52-7010-4a67-abce-73e78e9a1b56")
    private SmDependency definedResourceTypeDep;

    @objid ("70e99a63-c45f-47a9-8a9b-a880488ac388")
    private SmDependency ownerDep;

    @objid ("8ca7ebcc-411f-4de6-8165-258ed35f17b8")
    private SmDependency parentDep;

    @objid ("d973ed14-de17-484d-827f-6294f502de97")
    private SmDependency definedTagTypeDep;

    @objid ("4544b9f1-4519-4469-805a-e39b228ea6f9")
    private SmDependency childDep;

    @objid ("a8a2ff0e-1154-41a2-bc19-673cfd8e9ebd")
    private SmDependency definedNoteTypeDep;

    @objid ("2af645cc-8f08-4108-bad4-e2eab71125d8")
    private SmDependency extendedElementDep;

    @objid ("a2024b03-285b-4937-9dc5-f693ed8cfacf")
    public StereotypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2621690c-b2e8-4f33-aa7f-95bc746fd67c")
    @Override
    public String getName() {
        return "Stereotype";
    }

    @objid ("ec4c49db-35bc-4d43-9d9a-fafcab06fb9e")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("865f22de-d58b-4e49-a9b9-fba079b38892")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Stereotype.class;
    }

    @objid ("cf4b81ea-9a24-4baa-aba5-6c3a7ee124a3")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("85ea155b-98de-4192-ada2-16c299897c10")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8bbcddfb-2a4e-4b9b-ac49-42165a62d2ca")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new StereotypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.imageAtt = new ImageSmAttribute();
        this.imageAtt.init("Image", this, String.class );
        registerAttribute(this.imageAtt);
        
        this.iconAtt = new IconSmAttribute();
        this.iconAtt.init("Icon", this, String.class );
        registerAttribute(this.iconAtt);
        
        this.isHiddenAtt = new IsHiddenSmAttribute();
        this.isHiddenAtt.init("IsHidden", this, Boolean.class );
        registerAttribute(this.isHiddenAtt);
        
        this.isAbstractAtt = new IsAbstractSmAttribute();
        this.isAbstractAtt.init("IsAbstract", this, Boolean.class );
        registerAttribute(this.isAbstractAtt);
        
        this.labelKeyAtt = new LabelKeySmAttribute();
        this.labelKeyAtt.init("LabelKey", this, String.class );
        registerAttribute(this.labelKeyAtt);
        
        this.baseClassNameAtt = new BaseClassNameSmAttribute();
        this.baseClassNameAtt.init("BaseClassName", this, String.class );
        registerAttribute(this.baseClassNameAtt);
        
        
        // Initialize and register the SmDependency
        this.definedTableDep = new DefinedTableSmDependency();
        this.definedTableDep.init("DefinedTable", this, metamodel.getMClass(PropertyTableDefinition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedTableDep);
        
        this.definedResourceTypeDep = new DefinedResourceTypeSmDependency();
        this.definedResourceTypeDep.init("DefinedResourceType", this, metamodel.getMClass(ResourceType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedResourceTypeDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Profile.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(Stereotype.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.parentDep);
        
        this.definedTagTypeDep = new DefinedTagTypeSmDependency();
        this.definedTagTypeDep.init("DefinedTagType", this, metamodel.getMClass(TagType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedTagTypeDep);
        
        this.childDep = new ChildSmDependency();
        this.childDep.init("Child", this, metamodel.getMClass(Stereotype.MQNAME), 0, -1 );
        registerDependency(this.childDep);
        
        this.definedNoteTypeDep = new DefinedNoteTypeSmDependency();
        this.definedNoteTypeDep.init("DefinedNoteType", this, metamodel.getMClass(NoteType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedNoteTypeDep);
        
        this.extendedElementDep = new ExtendedElementSmDependency();
        this.extendedElementDep.init("ExtendedElement", this, metamodel.getMClass(ModelElement.MQNAME), 0, -1 );
        registerDependency(this.extendedElementDep);
    }

    @objid ("4ba57ab5-0c58-4c24-b759-023a46d6e872")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("0f872726-965f-40a3-a8e7-666104d68bad")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("504de96d-f394-4bdd-97db-7537d11846e6")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("cb25c94d-abc1-4562-b22f-d08b145ba2a6")
    public SmAttribute getIsAbstractAtt() {
        if (this.isAbstractAtt == null) {
        	this.isAbstractAtt = this.getAttributeDef("IsAbstract");
        }
        return this.isAbstractAtt;
    }

    @objid ("bbf60fb7-8692-4e68-8a8a-2e4c84ca8164")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("6a6f3fc0-8b1b-412a-ac1f-cddb5a2151af")
    public SmAttribute getBaseClassNameAtt() {
        if (this.baseClassNameAtt == null) {
        	this.baseClassNameAtt = this.getAttributeDef("BaseClassName");
        }
        return this.baseClassNameAtt;
    }

    @objid ("32504582-831f-43b8-84c4-32f05b3aea3c")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("d3e27591-5219-4d45-a510-e4e5062a4c12")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("fa33c078-2a70-4662-84bd-74b85dc2d7fd")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("86dec8d2-b964-441e-b5d9-136015ad7586")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("fb32d81b-b581-404f-a403-e09334a24fe7")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("ff683d27-54a6-48b0-8fa4-083ab1384306")
    public SmDependency getChildDep() {
        if (this.childDep == null) {
        	this.childDep = this.getDependencyDef("Child");
        }
        return this.childDep;
    }

    @objid ("70492515-0ee3-4278-8193-0caf051307e6")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("94e82eab-f6bb-45df-825c-965e183012e0")
    public SmDependency getExtendedElementDep() {
        if (this.extendedElementDep == null) {
        	this.extendedElementDep = this.getDependencyDef("ExtendedElement");
        }
        return this.extendedElementDep;
    }

    @objid ("c71124df-ce86-49fe-91a2-cfd7baab652d")
    private static class StereotypeObjectFactory implements ISmObjectFactory {
        @objid ("59ab7a1e-2440-4f2c-8727-74a71f267d78")
        private StereotypeSmClass smClass;

        @objid ("0d40fdb7-bd08-4ea2-accd-69e5f0c9e52f")
        public StereotypeObjectFactory(StereotypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("30aee009-02b5-43d3-beb4-f4200e9ff4ed")
        @Override
        public ISmObjectData createData() {
            return new StereotypeData(this.smClass);
        }

        @objid ("65a9315c-2ce2-444c-b23a-6656bb30f085")
        @Override
        public SmObjectImpl createImpl() {
            return new StereotypeImpl();
        }

    }

    @objid ("82ccbcce-9df5-44a2-89d5-9187a62a7543")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("e24d865f-1fba-4e78-9cf2-3314caf16fa9")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mImage;
        }

        @objid ("ddb65bcf-040b-47e3-bd24-a26fd44dcca6")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mImage = value;
        }

    }

    @objid ("0b6831ef-553b-4c86-b7ab-f8a94a212c5a")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("ffd77863-af0d-445b-8a5a-fa9ef6f3522e")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIcon;
        }

        @objid ("d81e66e8-99a7-4ab4-8378-8292186cb005")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIcon = value;
        }

    }

    @objid ("51a21f4a-617a-433b-b189-05fa49c593fb")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("f946b9c9-0a84-41f6-acf7-361f2a2f5075")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsHidden;
        }

        @objid ("4fdd6181-7f42-4c70-92f5-c81ea419251f")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsHidden = value;
        }

    }

    @objid ("b5fdc9a3-862a-4347-9898-8b1898128204")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("7ebf7b4f-c66e-4f1c-84b0-b8cda4d66d8a")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mLabelKey;
        }

        @objid ("4bad98bc-f6da-4616-bd7c-83622f81f259")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mLabelKey = value;
        }

    }

    @objid ("3461abff-ad6a-4e5e-b65f-d797a4532ef6")
    public static class BaseClassNameSmAttribute extends SmAttribute {
        @objid ("0a712b60-7b32-41b4-a36b-933209844fce")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mBaseClassName;
        }

        @objid ("ab49bf5d-fe80-4d37-8d68-f038f44dd1e3")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mBaseClassName = value;
        }

    }

    @objid ("b2dcf6a7-694b-4932-b03a-5e18d9b820ae")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("e07ddbfa-e19a-4cce-b407-578911c6c73b")
        private SmDependency symetricDep;

        @objid ("1f1a8305-4e55-4d0d-968e-ec7ac10b1527")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mDefinedTable;
        }

        @objid ("f08e2450-0655-4d04-a0ea-4f8d3c43ae64")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mDefinedTable = value;
        }

        @objid ("665fd4db-1960-40d6-b569-864d73a69f98")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getOwnerStereotypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ef341f3c-b507-425d-9312-75ed668df01b")
    public static class ExtendedElementSmDependency extends SmMultipleDependency {
        @objid ("3b244397-5e25-41dd-bd6a-04c67ce76d22")
        private SmDependency symetricDep;

        @objid ("e533dfd9-9813-4af3-b29d-c8ec3a2186da")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mExtendedElement != null)? ((StereotypeData)data).mExtendedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("aae6b21c-c9d6-4057-9a11-a5fa2f41936d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mExtendedElement = values;
        }

        @objid ("7def2061-ab33-4d44-9b60-099813cd93e8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getExtensionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ff2a5d00-48e1-45f0-8a71-a6c113b999d8")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("e9e4bd93-b1f2-4e19-84d2-da0ca30babf1")
        private SmDependency symetricDep;

        @objid ("262d22c3-4f54-4da5-96cc-ec5a5440fdce")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mOwner;
        }

        @objid ("b60705dc-c93f-4a28-899b-fcfdc510ace3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mOwner = value;
        }

        @objid ("0f163311-3ee7-4afb-b09c-505451e4e7e3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProfileSmClass)this.getTarget()).getDefinedStereotypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("149b20f0-29c7-40ea-a26a-08fd4ca33f77")
    public static class ParentSmDependency extends SmSingleDependency {
        @objid ("23dc92b2-2f07-4c04-ae21-ea1774c633be")
        private SmDependency symetricDep;

        @objid ("8eeb8541-8af6-4126-a2e9-86a556203c77")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mParent;
        }

        @objid ("c4be3aac-be12-444c-b98c-a1b47c5407cf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mParent = value;
        }

        @objid ("b32f8c12-f235-4a26-852c-84f2fc832d4d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getChildDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("3968dd91-ab32-471c-88ab-a1f0d82c0291")
    public static class DefinedTagTypeSmDependency extends SmMultipleDependency {
        @objid ("85c5ab00-6eb6-4822-9a2b-c17e9ec8699a")
        private SmDependency symetricDep;

        @objid ("5f439335-7d69-4552-b7b4-56f32df4d65c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedTagType != null)? ((StereotypeData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("2bdde0a4-384a-4ff3-ba16-414f7fdf1248")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedTagType = values;
        }

        @objid ("2d1b6677-9c7f-4617-aa55-a7afb75848d6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagTypeSmClass)this.getTarget()).getOwnerStereotypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c048654b-b7b4-4b75-8a35-7fb78c407618")
    public static class ChildSmDependency extends SmMultipleDependency {
        @objid ("3c8ec66a-f777-4bee-b015-8237386b7f46")
        private SmDependency symetricDep;

        @objid ("6d227b62-553f-4d1a-8260-965f75188ab7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mChild != null)? ((StereotypeData)data).mChild:SmMultipleDependency.EMPTY;
        }

        @objid ("aa36b778-f989-46b9-93d8-96eea1b6503b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mChild = values;
        }

        @objid ("5467585e-c282-4a43-8fd9-a63294334ce2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b97fbe03-0db2-4c7d-9639-336692d2c7b3")
    public static class DefinedNoteTypeSmDependency extends SmMultipleDependency {
        @objid ("4e531c7f-5834-4adc-aeef-36f065955517")
        private SmDependency symetricDep;

        @objid ("5f8d6b05-4142-4987-bfb8-fab6fef17510")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedNoteType != null)? ((StereotypeData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("f8763a7c-b420-4909-98c8-ebf5dcf4805e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedNoteType = values;
        }

        @objid ("ff168df1-92ab-4fc2-864a-513de2aaf56d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NoteTypeSmClass)this.getTarget()).getOwnerStereotypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8190d436-5410-4509-a875-f013d18fb4de")
    public static class DefinedResourceTypeSmDependency extends SmMultipleDependency {
        @objid ("4d4b13c2-4b64-462d-8f50-35c6c208ccd0")
        private SmDependency symetricDep;

        @objid ("ae1982cf-db3d-46fa-9bce-31476b6bddba")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedResourceType != null)? ((StereotypeData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("cedbf5ee-c0c9-430e-983e-95ba58fe792c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedResourceType = values;
        }

        @objid ("09271cd5-3d0a-4f4f-b13e-6c2a6d4190d1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ResourceTypeSmClass)this.getTarget()).getOwnerStereotypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9ba61218-e16b-4019-812a-5bba7ddb6b85")
    public static class IsAbstractSmAttribute extends SmAttribute {
        @objid ("413a786e-e63f-418f-9e71-3f87e43ee0ae")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsAbstract;
        }

        @objid ("68b883c6-b792-4cf8-ab51-1c5f4e938621")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsAbstract = value;
        }

    }

}
