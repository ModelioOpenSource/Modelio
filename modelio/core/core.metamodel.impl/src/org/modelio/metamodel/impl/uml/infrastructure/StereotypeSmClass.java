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
    @objid ("fbf465be-1282-4e70-8887-39515d75aeb8")
    private SmAttribute imageAtt;

    @objid ("52cea206-cc96-4607-874a-50e7047572db")
    private SmAttribute iconAtt;

    @objid ("ab841617-d1b2-4ecb-8c4f-4b4b2b9812d5")
    private SmAttribute isHiddenAtt;

    @objid ("47853c00-5bc0-42f2-ae6b-bec59f82b0ba")
    private SmAttribute isAbstractAtt;

    @objid ("df95650b-33d8-458d-bda2-6e756d809b15")
    private SmAttribute labelKeyAtt;

    @objid ("5e6be833-abb6-48c2-9444-41fa6e6eb662")
    private SmAttribute baseClassNameAtt;

    @objid ("3edbcc86-7531-45ab-a397-2e5b8b230c95")
    private SmDependency definedTableDep;

    @objid ("5d580403-14df-4e7d-8200-4ba4d581bc98")
    private SmDependency definedResourceTypeDep;

    @objid ("5375357a-1415-47db-b705-162256348d53")
    private SmDependency ownerDep;

    @objid ("82a67849-5e24-42a3-b3ee-cc1e047a70fb")
    private SmDependency parentDep;

    @objid ("f5364b00-6621-44f5-9ada-f7d55178879d")
    private SmDependency definedTagTypeDep;

    @objid ("a4df0a16-49ae-4e03-b670-7b7cf0d27f80")
    private SmDependency childDep;

    @objid ("8b6c93cc-00f2-4de3-84a1-8814e0f87d80")
    private SmDependency definedNoteTypeDep;

    @objid ("77a553a1-40c5-41a6-b13b-72f883496428")
    private SmDependency extendedElementDep;

    @objid ("3fe14ff2-8120-4325-9097-4ad3e8c9453a")
    public  StereotypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9b2a1332-c821-41d1-a578-6e2464b2968a")
    @Override
    public String getName() {
        return "Stereotype";
        
    }

    @objid ("aa2567e6-913f-4574-bace-2eaf450c75d1")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("6b2c406b-5134-4a75-b86a-9f1b158a8f63")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Stereotype.class;
        
    }

    @objid ("cfafdfec-44c5-4c2c-a170-b09ae83c7b83")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("92a408cf-972f-4944-9977-83528adf8fb0")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("053361cc-29ae-46f1-a3d2-d5ccd0e310af")
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

    @objid ("ea896513-c0dd-43b2-98c9-9105d5370f62")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("79f283af-b22a-4c2b-8449-ec36d3f0e44d")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("5c6af07d-92c4-4f9e-a2ed-310594cdf59b")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("80386d32-eb52-4e7d-8530-d3f2c82d9827")
    public SmAttribute getIsAbstractAtt() {
        if (this.isAbstractAtt == null) {
        	this.isAbstractAtt = this.getAttributeDef("IsAbstract");
        }
        return this.isAbstractAtt;
    }

    @objid ("fa0e7e92-54aa-4b8f-a828-ade040f3d8ed")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("7fe8875a-50ed-4c3c-aab6-ee2b4b61e736")
    public SmAttribute getBaseClassNameAtt() {
        if (this.baseClassNameAtt == null) {
        	this.baseClassNameAtt = this.getAttributeDef("BaseClassName");
        }
        return this.baseClassNameAtt;
    }

    @objid ("25d7d963-1983-4686-a688-ee284933433a")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("aee643ce-13da-4cf6-b8fd-40fff339d8dd")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("b7566b44-902c-40ca-87a2-bf3065a591a3")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("38cc25d8-7e05-425b-8f0e-0dda7779b910")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("c3391c38-b194-4438-b4cb-7b3ced88f553")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("0dd443ea-a4ed-47e8-b120-f0cb2357f93a")
    public SmDependency getChildDep() {
        if (this.childDep == null) {
        	this.childDep = this.getDependencyDef("Child");
        }
        return this.childDep;
    }

    @objid ("81c878e5-4748-4713-8fce-719a80fa3adf")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("bb4c2eef-ea8f-4b34-8d88-00e7b574f501")
    public SmDependency getExtendedElementDep() {
        if (this.extendedElementDep == null) {
        	this.extendedElementDep = this.getDependencyDef("ExtendedElement");
        }
        return this.extendedElementDep;
    }

    @objid ("c71124df-ce86-49fe-91a2-cfd7baab652d")
    private static class StereotypeObjectFactory implements ISmObjectFactory {
        @objid ("9f01deeb-d0cc-4efd-b78e-5284156cb04c")
        private StereotypeSmClass smClass;

        @objid ("be9b4d9e-11a0-4e66-84ca-3ea2960358ad")
        public  StereotypeObjectFactory(StereotypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b657344c-84ad-4467-bd88-d0907c31eb29")
        @Override
        public ISmObjectData createData() {
            return new StereotypeData(this.smClass);
        }

        @objid ("3b0138fa-3fd9-4492-8021-6e4ad7a09ce2")
        @Override
        public SmObjectImpl createImpl() {
            return new StereotypeImpl();
        }

    }

    @objid ("82ccbcce-9df5-44a2-89d5-9187a62a7543")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("1ef1b91e-4fc4-4623-bcd4-b5f20dd98acf")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mImage;
        }

        @objid ("deb3467f-953b-4226-94d1-ff4bb5b28070")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mImage = value;
        }

    }

    @objid ("0b6831ef-553b-4c86-b7ab-f8a94a212c5a")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("6baa0669-a13c-4317-916d-09b66d08a298")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIcon;
        }

        @objid ("9004ca4e-1208-40b0-ac99-a0b166fcf4c5")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIcon = value;
        }

    }

    @objid ("51a21f4a-617a-433b-b189-05fa49c593fb")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("ff70e45f-f5f2-4ab5-8775-e84e63b3efdc")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsHidden;
        }

        @objid ("80c12aa0-2a0e-4b6c-a7ad-40c619e4b67b")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsHidden = value;
        }

    }

    @objid ("b5fdc9a3-862a-4347-9898-8b1898128204")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("a5af8801-7f9a-45b6-b570-d8ff7c27b1e7")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mLabelKey;
        }

        @objid ("f49712ee-7aad-4d0f-a441-a4282feaac56")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mLabelKey = value;
        }

    }

    @objid ("3461abff-ad6a-4e5e-b65f-d797a4532ef6")
    public static class BaseClassNameSmAttribute extends SmAttribute {
        @objid ("92422b98-cf7d-42cc-8683-4414346294e1")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mBaseClassName;
        }

        @objid ("f6f07275-4c5f-4e47-9da0-58c653f1a490")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mBaseClassName = value;
        }

    }

    @objid ("b2dcf6a7-694b-4932-b03a-5e18d9b820ae")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("9e6d4729-4bc0-4aea-9071-aa6cd70f4db8")
        private SmDependency symetricDep;

        @objid ("1b22b5e8-44cb-42e5-8d2d-1377c3bb1d9a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mDefinedTable;
        }

        @objid ("0b6b2a8f-729d-4c9e-8c3c-3966878e4810")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mDefinedTable = value;
        }

        @objid ("101aab6d-f772-4535-b24d-88bc688b44d4")
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
        @objid ("48213d14-7314-48f2-ba94-9b0e4e11e639")
        private SmDependency symetricDep;

        @objid ("336352b3-c7c1-46e7-8348-8455de3b52b8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mExtendedElement != null)? ((StereotypeData)data).mExtendedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("857a8e69-750f-4708-8793-6ff91b54fe4f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mExtendedElement = values;
            
        }

        @objid ("52616999-144a-48ba-bc76-a45fb016ca09")
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
        @objid ("b4eee14d-2fab-4b11-ade0-613f9331c553")
        private SmDependency symetricDep;

        @objid ("23b35360-77d6-4128-a885-41492ed9d590")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mOwner;
        }

        @objid ("6c00332e-3baa-4865-a12b-8d0df398ad5a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mOwner = value;
        }

        @objid ("44aca8d4-0392-4669-953a-e9abeca6ea5c")
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
        @objid ("d9d269f4-5181-4bae-86ef-77664bde9675")
        private SmDependency symetricDep;

        @objid ("ec96fd45-0a9e-42d0-a742-b275122d223f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StereotypeData) data).mParent;
        }

        @objid ("e55ab44d-1915-444e-94c5-38291034e539")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StereotypeData) data).mParent = value;
        }

        @objid ("5ec61fe5-8914-4a4c-9d50-3a51e6d534e9")
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
        @objid ("537d9c69-cdec-456d-9f06-e35ef20b72de")
        private SmDependency symetricDep;

        @objid ("b50611cd-44c3-45c7-8e94-42684e876c06")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedTagType != null)? ((StereotypeData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("43ce80a5-eac6-4c70-832f-8ef9833fd87d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedTagType = values;
            
        }

        @objid ("5254543d-a0b6-4db7-b6a3-64f637ed2f84")
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
        @objid ("219e18e4-19ce-4d72-8039-c66b23423345")
        private SmDependency symetricDep;

        @objid ("b01e402f-e936-40c9-80a1-d9bdb152c2c0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mChild != null)? ((StereotypeData)data).mChild:SmMultipleDependency.EMPTY;
        }

        @objid ("7764e92f-c6cb-45a1-9eef-606454df9b74")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mChild = values;
            
        }

        @objid ("71a28ecf-fef9-4248-9298-0bfb3f95486f")
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
        @objid ("bf0cde65-1a95-48c1-8bbc-f1a75d47ee21")
        private SmDependency symetricDep;

        @objid ("7522f2d1-87d4-4c1c-98fd-f50b761f2fa0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedNoteType != null)? ((StereotypeData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("9a1bb58c-5c66-4c4e-9ab5-c5e6ef7b2aad")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedNoteType = values;
            
        }

        @objid ("469e50ee-4ae1-435f-8cd1-4c22b9b17b1e")
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
        @objid ("cbc41920-97e4-4ec8-9f7b-070c6dfb967d")
        private SmDependency symetricDep;

        @objid ("859b5a35-4aa0-4625-8a01-0a4a91c4bcb5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StereotypeData)data).mDefinedResourceType != null)? ((StereotypeData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("9f7e2516-39a4-4ca5-b175-e44227acce02")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StereotypeData) data).mDefinedResourceType = values;
            
        }

        @objid ("a75c984c-2bb6-4eb5-928d-fd5a2f1155b7")
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
        @objid ("4d5da761-f8d7-4f36-9f7e-00c02ec9b80f")
        public Object getValue(ISmObjectData data) {
            return ((StereotypeData) data).mIsAbstract;
        }

        @objid ("7c3ff3c8-d9f8-4271-b42d-dcfb0ed76e68")
        public void setValue(ISmObjectData data, Object value) {
            ((StereotypeData) data).mIsAbstract = value;
        }

    }

}
