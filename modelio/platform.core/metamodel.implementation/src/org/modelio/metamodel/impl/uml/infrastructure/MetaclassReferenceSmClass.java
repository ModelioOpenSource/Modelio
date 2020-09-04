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
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceData;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
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

@objid ("c431f62a-cc67-4a1e-b558-d0d2481187a3")
public class MetaclassReferenceSmClass extends ElementSmClass {
    @objid ("09c63b60-0e84-4b85-ab1e-72c5444baca6")
    private SmAttribute referencedClassNameAtt;

    @objid ("2c4ffee4-fa39-47dc-a1e3-7831b687c0af")
    private SmDependency definedTableDep;

    @objid ("978a104e-a589-45b4-9c1e-0c943ac33aeb")
    private SmDependency definedNoteTypeDep;

    @objid ("4af6ba24-adf4-410a-a00a-fd0f16d11e2d")
    private SmDependency definedResourceTypeDep;

    @objid ("e0825c75-fb76-453d-ba3b-1fe60fcb43d5")
    private SmDependency ownerProfileDep;

    @objid ("5b238c24-ab31-4e50-93c7-72de54be7910")
    private SmDependency definedTagTypeDep;

    @objid ("d42189d7-8524-49ce-84cd-1fd40a9e3194")
    public MetaclassReferenceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ce4eb183-e5d1-46ab-806c-e0a48e26c67f")
    @Override
    public String getName() {
        return "MetaclassReference";
    }

    @objid ("c92c1b5d-cf66-4c36-96f7-4a9507d8da81")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("dd569f9b-7604-46e1-b516-5f937732c282")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MetaclassReference.class;
    }

    @objid ("c40d68a4-17f2-482c-a2ca-1fb654f56417")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("52703462-d479-400e-9cf0-5366026a2ddc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f650b57d-389d-4186-9b93-2846e8134ec9")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new MetaclassReferenceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.referencedClassNameAtt = new ReferencedClassNameSmAttribute();
        this.referencedClassNameAtt.init("ReferencedClassName", this, String.class );
        registerAttribute(this.referencedClassNameAtt);
        
        
        // Initialize and register the SmDependency
        this.definedTableDep = new DefinedTableSmDependency();
        this.definedTableDep.init("DefinedTable", this, metamodel.getMClass(PropertyTableDefinition.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedTableDep);
        
        this.definedNoteTypeDep = new DefinedNoteTypeSmDependency();
        this.definedNoteTypeDep.init("DefinedNoteType", this, metamodel.getMClass(NoteType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedNoteTypeDep);
        
        this.definedResourceTypeDep = new DefinedResourceTypeSmDependency();
        this.definedResourceTypeDep.init("DefinedResourceType", this, metamodel.getMClass(ResourceType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedResourceTypeDep);
        
        this.ownerProfileDep = new OwnerProfileSmDependency();
        this.ownerProfileDep.init("OwnerProfile", this, metamodel.getMClass(Profile.MQNAME), 0, 1 );
        registerDependency(this.ownerProfileDep);
        
        this.definedTagTypeDep = new DefinedTagTypeSmDependency();
        this.definedTagTypeDep.init("DefinedTagType", this, metamodel.getMClass(TagType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedTagTypeDep);
    }

    @objid ("137193d2-864b-4f74-ad9c-ea1cba11c3b0")
    public SmAttribute getReferencedClassNameAtt() {
        if (this.referencedClassNameAtt == null) {
        	this.referencedClassNameAtt = this.getAttributeDef("ReferencedClassName");
        }
        return this.referencedClassNameAtt;
    }

    @objid ("390f6a4c-f430-4bad-9256-a5c94482a5a0")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("0ee6b5a6-84e5-4c00-bff1-6e1b9d3aaf4b")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("1bb8f26d-9e45-4a88-8f02-098f9c1e7576")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("856bbdca-cda2-4bdb-a085-7408a94298ac")
    public SmDependency getOwnerProfileDep() {
        if (this.ownerProfileDep == null) {
        	this.ownerProfileDep = this.getDependencyDef("OwnerProfile");
        }
        return this.ownerProfileDep;
    }

    @objid ("30e13388-68a0-4d2b-9839-5ba650624e67")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("9cddf795-64df-41e2-b836-d05dfd30d000")
    private static class MetaclassReferenceObjectFactory implements ISmObjectFactory {
        @objid ("02ed4a0e-0431-40d0-83c6-69d21142c4f5")
        private MetaclassReferenceSmClass smClass;

        @objid ("1b777592-6bfa-401c-a325-40eca58ea13a")
        public MetaclassReferenceObjectFactory(MetaclassReferenceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f190ea69-f487-4a69-8497-ac5845881943")
        @Override
        public ISmObjectData createData() {
            return new MetaclassReferenceData(this.smClass);
        }

        @objid ("b9dadac4-b156-4afd-a17d-2c8d4eda6464")
        @Override
        public SmObjectImpl createImpl() {
            return new MetaclassReferenceImpl();
        }

    }

    @objid ("ae099c4b-ae98-457e-af7d-68e39b6dacbf")
    public static class ReferencedClassNameSmAttribute extends SmAttribute {
        @objid ("485ad662-2c16-4d5a-ac8f-3fec3ffbf298")
        public Object getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mReferencedClassName;
        }

        @objid ("a370473f-9219-4c12-a481-eac60708fd7a")
        public void setValue(ISmObjectData data, Object value) {
            ((MetaclassReferenceData) data).mReferencedClassName = value;
        }

    }

    @objid ("c28a5d23-4440-4895-90af-4958ddf16be9")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("e8b5fd14-a71e-4481-9e07-b5a925adc13a")
        private SmDependency symetricDep;

        @objid ("15776638-596a-462d-9f4a-32240f289473")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mDefinedTable;
        }

        @objid ("620d344e-00f9-464a-920e-a32417c9d774")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mDefinedTable = value;
        }

        @objid ("26300911-1471-4fde-a576-cf08b3a15181")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7378ff6f-d073-4c68-a05b-7ccbd6940e62")
    public static class DefinedNoteTypeSmDependency extends SmMultipleDependency {
        @objid ("639402af-a6d4-4ddc-81ab-af05db88e40e")
        private SmDependency symetricDep;

        @objid ("60caeb0f-5a8a-4cd0-877b-9402dae35861")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedNoteType != null)? ((MetaclassReferenceData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("21cd8cb4-3016-4098-96eb-924150892500")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedNoteType = values;
        }

        @objid ("2c7ebe37-ba1b-48f6-97c8-f3e4fea31116")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NoteTypeSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c98111b8-390b-4fe2-a22b-51a172942d4b")
    public static class OwnerProfileSmDependency extends SmSingleDependency {
        @objid ("fa106606-5bac-48d9-acb9-3f45047b6ecb")
        private SmDependency symetricDep;

        @objid ("45418bba-8197-40de-bbf3-4068415d02f0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mOwnerProfile;
        }

        @objid ("de8a7823-35da-44ce-9ae1-c4c4a987578e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mOwnerProfile = value;
        }

        @objid ("44f078ad-1533-45dd-a909-482b5a4235f5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProfileSmClass)this.getTarget()).getOwnedReferenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9b5b27d4-7388-47af-84a8-5f973457c042")
    public static class DefinedTagTypeSmDependency extends SmMultipleDependency {
        @objid ("0f863d86-9044-4d57-82fe-86aff3ece7e7")
        private SmDependency symetricDep;

        @objid ("7d01bb24-e363-4cc9-a4df-71e6cf33ec11")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedTagType != null)? ((MetaclassReferenceData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("8b6aa6a1-c5e4-4fbf-80b3-b6ba8ae1e8e8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedTagType = values;
        }

        @objid ("55b3b00c-7d32-473a-bacc-0c7e924551e8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagTypeSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("189a154f-c91a-45f5-9add-011b22612fb1")
    public static class DefinedResourceTypeSmDependency extends SmMultipleDependency {
        @objid ("a8074aac-8a06-4a9f-a977-7b4ade50abfb")
        private SmDependency symetricDep;

        @objid ("ac212326-74a9-4c02-be7f-b032abade931")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedResourceType != null)? ((MetaclassReferenceData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("902099fe-0ffa-4ec0-80cc-60ad3c8b1a49")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedResourceType = values;
        }

        @objid ("c22410ee-1513-4831-8cab-676a2bfd7705")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ResourceTypeSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
        }

    }

}
