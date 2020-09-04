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
    @objid ("796bf9a4-2746-433c-89b9-b803a921ac45")
    private SmAttribute imageAtt;

    @objid ("d981343b-f163-49c8-b8c7-8696d4333a86")
    private SmAttribute iconAtt;

    @objid ("30738be0-3d6e-474a-8426-4328fd4a0047")
    private SmAttribute isHiddenAtt;

    @objid ("7879b05c-af24-4ab2-bc7c-475065ca20c3")
    private SmAttribute isAbstractAtt;

    @objid ("540fd65c-2a4c-4252-ae7c-f5b4a7fe5721")
    private SmAttribute labelKeyAtt;

    @objid ("92d7fb66-0a9b-489f-80ae-1b7eb2ad3fdf")
    private SmAttribute baseClassNameAtt;

    @objid ("23c8af70-458a-41f7-8d69-4875a1c1a6ae")
    private SmDependency definedTableDep;

    @objid ("abdec96d-4bf3-4ce5-9b82-40731fb6d6da")
    private SmDependency definedResourceTypeDep;

    @objid ("fb7b94e5-6bfc-4b19-850f-37944d28c8a3")
    private SmDependency ownerDep;

    @objid ("44e96175-e52b-4573-a898-f703cb4b14e2")
    private SmDependency parentDep;

    @objid ("832accff-19bf-49a4-876f-1c49648cde6e")
    private SmDependency definedTagTypeDep;

    @objid ("31a46df9-345b-4320-9cb8-fee7749c0ccd")
    private SmDependency childDep;

    @objid ("9cc4a66d-c899-4ef9-8bef-6b54dce35956")
    private SmDependency definedNoteTypeDep;

    @objid ("bb56befd-62ec-419e-a22e-d8b261cf5225")
    private SmDependency extendedElementDep;

    @objid ("67c08013-5b1c-4d7d-8514-1081720437ef")
    public StereotypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ee5838c3-e83c-4da1-94f7-230480e0b784")
    @Override
    public String getName() {
        return "Stereotype";
    }

    @objid ("1d4e8fd0-12d5-4366-8ece-81301178a1ea")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("9cb33035-55e8-4cef-8d0f-aabe18f02aff")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Stereotype.class;
    }

    @objid ("eeb41df4-9f93-4887-a32e-2c47a9fc8edf")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("6bce6071-2d67-4fff-ba1c-d37717f25b6f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c60eaee0-b53e-41ad-8c49-66740ad90073")
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

    @objid ("356cb226-4693-4758-a695-b40317e57967")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("2ca2e495-2563-43d7-870b-b8e845e8185f")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("0d5906a6-4e81-4d7a-a4e3-ee87240332f5")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("9926d773-c974-411e-a74e-85b7cf024f02")
    public SmAttribute getIsAbstractAtt() {
        if (this.isAbstractAtt == null) {
        	this.isAbstractAtt = this.getAttributeDef("IsAbstract");
        }
        return this.isAbstractAtt;
    }

    @objid ("88cb5cad-c22b-4618-a579-db9b88ccd5c1")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("d4071f58-08fe-4036-a776-0f869ff04ecc")
    public SmAttribute getBaseClassNameAtt() {
        if (this.baseClassNameAtt == null) {
        	this.baseClassNameAtt = this.getAttributeDef("BaseClassName");
        }
        return this.baseClassNameAtt;
    }

    @objid ("2825241f-94a8-43f7-beaf-a0af77ea9fd3")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("5005394b-a985-452c-8a7a-30743d96e19c")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("edf62700-1758-410e-90dd-150888c12be4")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("bd4c16ba-f822-4beb-88aa-ff026ac5bf0a")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("250869d3-9e4d-49a7-8b21-d655bde143a3")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("b8d7f4ec-6e7d-408c-893a-b89a654284e8")
    public SmDependency getChildDep() {
        if (this.childDep == null) {
        	this.childDep = this.getDependencyDef("Child");
        }
        return this.childDep;
    }

    @objid ("229bb48f-1610-4bbf-b52b-04964f3ed819")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("66e9187c-f7bd-4714-b609-83ee624cd4fe")
    public SmDependency getExtendedElementDep() {
        if (this.extendedElementDep == null) {
        	this.extendedElementDep = this.getDependencyDef("ExtendedElement");
        }
        return this.extendedElementDep;
    }

    @objid ("c71124df-ce86-49fe-91a2-cfd7baab652d")
    private static class StereotypeObjectFactory implements ISmObjectFactory {
        @objid ("0d4b2925-1cb3-4ca4-a088-b271ade7b422")
        private StereotypeSmClass smClass;

        @objid ("d73836de-4678-4f3f-95db-1975adefcb53")
        public StereotypeObjectFactory(StereotypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("365ce2cf-eede-42f3-8be0-8708dbf2b4a7")
        @Override
        public ISmObjectData createData() {
            return new StereotypeData(this.smClass);
        }

        @objid ("151407a2-b0ea-4c7e-bba8-c7c284223fbf")
        @Override
        public SmObjectImpl createImpl() {
            return new StereotypeImpl();
        }

    }

    @objid ("82ccbcce-9df5-44a2-89d5-9187a62a7543")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("388c8ec9-90b6-4848-8d0c-f1e7f03232a8")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mImage;
        }

        @objid ("c125755f-a834-46a7-b966-8f32bb5aa85c")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mImage = value;
        }

    }

    @objid ("0b6831ef-553b-4c86-b7ab-f8a94a212c5a")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("dbfd0e8e-4f0b-4317-8524-21e19f99c849")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIcon;
        }

        @objid ("d99342bd-617c-4ca3-84e7-f00fd4bac3f8")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIcon = value;
        }

    }

    @objid ("51a21f4a-617a-433b-b189-05fa49c593fb")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("229d6823-1269-4aeb-a6e8-bd3c12556b49")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsHidden;
        }

        @objid ("c72bd1ac-03ae-4297-ad21-f134c48eb183")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsHidden = value;
        }

    }

    @objid ("b5fdc9a3-862a-4347-9898-8b1898128204")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("482bef9a-7275-4df2-8813-80e5e0cd8755")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mLabelKey;
        }

        @objid ("216b872d-c4e2-4a79-8498-eccd40aac706")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mLabelKey = value;
        }

    }

    @objid ("3461abff-ad6a-4e5e-b65f-d797a4532ef6")
    public static class BaseClassNameSmAttribute extends SmAttribute {
        @objid ("1b3a52ed-e8bf-4703-9b46-0d702592d013")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mBaseClassName;
        }

        @objid ("ad549ca6-45c3-4e6a-b326-bcd146d15e52")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mBaseClassName = value;
        }

    }

    @objid ("b2dcf6a7-694b-4932-b03a-5e18d9b820ae")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("a7cd4037-f9a7-4b6f-b089-de2eaa56d7d0")
        private SmDependency symetricDep;

        @objid ("702a8ad1-d2e7-4738-af3c-3122c7cb0899")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mDefinedTable;
        }

        @objid ("aa2250de-6073-430a-962b-c5f13f1f8a1d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mDefinedTable = value;
        }

        @objid ("91a87e6a-e160-407b-8683-24f6229b8cab")
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
        @objid ("e6bf4096-92ff-42b6-8766-6bf11927a75d")
        private SmDependency symetricDep;

        @objid ("18d81437-3787-4db5-9afd-a9394dcb3b90")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mExtendedElement != null)? ((StereotypeData)data).mExtendedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("b202a6c9-99b6-4f05-bd7d-5b6aa46a7cd1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mExtendedElement = values;
        }

        @objid ("4358374c-963b-493d-853a-a96743d21c56")
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
        @objid ("b0afb0f7-fb0e-473c-bc46-02477a55a504")
        private SmDependency symetricDep;

        @objid ("bb559e92-6d96-4619-afec-3c934b867b7d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mOwner;
        }

        @objid ("41ec3422-45d6-4cd3-bb43-e284ae0a0682")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mOwner = value;
        }

        @objid ("989b4709-59cf-4888-b745-17634b54374a")
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
        @objid ("0a40bc05-9949-4147-8612-661c651c3520")
        private SmDependency symetricDep;

        @objid ("b1eefdae-6c7f-435d-a9ed-d43628662988")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mParent;
        }

        @objid ("419eef6f-6275-4b7d-a88f-abd52ac69500")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mParent = value;
        }

        @objid ("e8f4ccf6-4714-4941-a982-3d2ac3c28e9e")
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
        @objid ("7ee9ab07-96f0-46ea-9018-53e0148d28ec")
        private SmDependency symetricDep;

        @objid ("fe250af7-be1b-4001-9d61-c31c81f23e78")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedTagType != null)? ((StereotypeData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("603ed699-78dc-4b51-971a-5eee3fb9d842")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedTagType = values;
        }

        @objid ("57651a43-ce98-4238-acbd-e27a6be5724b")
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
        @objid ("628a89c1-b4c2-43aa-884a-d970d7b6ff15")
        private SmDependency symetricDep;

        @objid ("3705c91a-f090-4ba4-8fb0-d2baf4745518")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mChild != null)? ((StereotypeData)data).mChild:SmMultipleDependency.EMPTY;
        }

        @objid ("6a952641-c0c0-4808-9d87-7b741010ef2e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mChild = values;
        }

        @objid ("9c01ebb4-3806-452a-ac7d-3a70a4a684a5")
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
        @objid ("364fcaab-abca-4b52-afe8-edcc5637bc13")
        private SmDependency symetricDep;

        @objid ("c28ae540-4278-428a-ab82-562fe073d996")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedNoteType != null)? ((StereotypeData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("8a3334f6-9e9d-498a-9101-cb1a825d2b7c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedNoteType = values;
        }

        @objid ("889bf7a7-589b-4b93-bce5-2682e418d502")
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
        @objid ("2cc0cf91-be18-4795-8e13-786930fd7711")
        private SmDependency symetricDep;

        @objid ("8bfc6bd9-2182-4252-9f34-9957814e7393")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedResourceType != null)? ((StereotypeData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("a8ad748c-c4cb-41a8-90f9-e0596350a2cd")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedResourceType = values;
        }

        @objid ("47f578a4-74c1-4bdb-98a5-32e3d50a6812")
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
        @objid ("9a657ba0-cdd8-436c-9159-f0184b9103b1")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsAbstract;
        }

        @objid ("3bb03a23-7d0c-492a-8611-f01882e7c3f6")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsAbstract = value;
        }

    }

}
