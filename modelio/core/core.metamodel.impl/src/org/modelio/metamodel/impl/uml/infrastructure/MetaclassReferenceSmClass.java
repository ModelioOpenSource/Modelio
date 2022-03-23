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
    @objid ("9482c7ba-fda0-4915-a69c-ac74f6fa9f75")
    private SmAttribute referencedClassNameAtt;

    @objid ("b3d3f6a3-5177-45e5-a0db-f0b86d88f8fc")
    private SmDependency definedTableDep;

    @objid ("a15e3f6b-87a7-4bf3-9f6b-75a3778a4cb1")
    private SmDependency definedNoteTypeDep;

    @objid ("e94ebc06-a637-4155-9232-91823fa5f2b4")
    private SmDependency definedResourceTypeDep;

    @objid ("2c6efe68-f086-403b-9f35-6cc9f70677af")
    private SmDependency ownerProfileDep;

    @objid ("7260f6d7-a234-4975-a345-a1afc995bed7")
    private SmDependency definedTagTypeDep;

    @objid ("e3162768-dd33-4614-a66e-261ed91e9b5e")
    public  MetaclassReferenceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("41eb4fd1-efbb-4113-88ec-c87c1b2efdf8")
    @Override
    public String getName() {
        return "MetaclassReference";
        
    }

    @objid ("9b710970-4616-4ea4-86c3-ca72730ceb62")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("82be1023-c3ae-401d-b628-8324a09c89e2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MetaclassReference.class;
        
    }

    @objid ("d2f33659-efb9-45d9-9ed6-ded7ae017686")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("c62ad532-a228-4bff-ae40-01c83df3ef5a")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b25f87ef-58f3-42a1-83ec-f6fb31322510")
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

    @objid ("7365df90-a5a8-4bdb-b286-222bed82bab7")
    public SmAttribute getReferencedClassNameAtt() {
        if (this.referencedClassNameAtt == null) {
        	this.referencedClassNameAtt = this.getAttributeDef("ReferencedClassName");
        }
        return this.referencedClassNameAtt;
    }

    @objid ("4d288b76-d722-4189-949c-e664782e59c0")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("d65e567e-29e7-4ad3-8246-fbae9eef77b0")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("f2b102b5-1d89-4ac8-8193-5d1d2f7271e4")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("ffd1259d-1b22-435a-bada-a3731399c74a")
    public SmDependency getOwnerProfileDep() {
        if (this.ownerProfileDep == null) {
        	this.ownerProfileDep = this.getDependencyDef("OwnerProfile");
        }
        return this.ownerProfileDep;
    }

    @objid ("5a82c421-ddf2-457d-be7e-03c07e1b170f")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("9cddf795-64df-41e2-b836-d05dfd30d000")
    private static class MetaclassReferenceObjectFactory implements ISmObjectFactory {
        @objid ("4d65000d-fe2d-4db8-aa77-eda131c636f2")
        private MetaclassReferenceSmClass smClass;

        @objid ("6400ae41-a7ed-43b4-9fa9-b4bb705a2bb0")
        public  MetaclassReferenceObjectFactory(MetaclassReferenceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8b2dd1d2-bc2b-45ee-a7d2-0a0d44aec739")
        @Override
        public ISmObjectData createData() {
            return new MetaclassReferenceData(this.smClass);
        }

        @objid ("772c0876-8a81-4945-9ddf-be8f9778a99f")
        @Override
        public SmObjectImpl createImpl() {
            return new MetaclassReferenceImpl();
        }

    }

    @objid ("ae099c4b-ae98-457e-af7d-68e39b6dacbf")
    public static class ReferencedClassNameSmAttribute extends SmAttribute {
        @objid ("66bc2306-8fa3-41b0-8042-1f3ee72b63dd")
        public Object getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mReferencedClassName;
        }

        @objid ("b1bb4d6f-736c-45bb-bae6-4c6e555b48c7")
        public void setValue(ISmObjectData data, Object value) {
            ((MetaclassReferenceData) data).mReferencedClassName = value;
        }

    }

    @objid ("c28a5d23-4440-4895-90af-4958ddf16be9")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("edb382a6-7ebf-4d37-a4c3-c636e966933b")
        private SmDependency symetricDep;

        @objid ("bbb576c8-a57b-4f81-b7d6-15fc0c4ecb6f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mDefinedTable;
        }

        @objid ("d3b05ddd-b842-4e92-a045-b3ce2a189f12")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mDefinedTable = value;
        }

        @objid ("ae65f893-3b70-4c53-b254-c4e6c3fc58c6")
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
        @objid ("2eb032f3-57b6-4a2e-8f4f-136ef51acce8")
        private SmDependency symetricDep;

        @objid ("5829eb16-6abf-4839-b5b2-ee256b286d9f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedNoteType != null)? ((MetaclassReferenceData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("384fc93e-cde3-4368-9b05-c096a8c9d082")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedNoteType = values;
            
        }

        @objid ("946281c0-772f-4a4f-86a2-00521dd7fe70")
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
        @objid ("ff64acab-47ab-4a43-aadd-463b2374f0bf")
        private SmDependency symetricDep;

        @objid ("d8020ae6-817c-44a9-89e7-944f605a6490")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mOwnerProfile;
        }

        @objid ("5751ce49-9b81-43ae-a8e2-a02210739127")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mOwnerProfile = value;
        }

        @objid ("f2194d6f-af9f-4c16-8bd0-f9669ee623bb")
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
        @objid ("be7f64fd-5bd4-49e2-820c-2daf2d588dd6")
        private SmDependency symetricDep;

        @objid ("6f64f0c5-68f1-44fa-b2cb-c4b3b1b99f93")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedTagType != null)? ((MetaclassReferenceData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("f95181b9-ed24-4bba-91af-12f0c61bbbd0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedTagType = values;
            
        }

        @objid ("1c12dac0-a40f-4489-bb06-4c12cfc2dcac")
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
        @objid ("abe738b7-187a-44dc-b029-e2dfe9f6904a")
        private SmDependency symetricDep;

        @objid ("579cc57a-eebd-433a-8c39-a4101712bebd")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedResourceType != null)? ((MetaclassReferenceData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("f92fbcca-40e5-4d4b-9ca7-71e9c6b22813")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedResourceType = values;
            
        }

        @objid ("5718c99b-09fd-407f-91a7-094615127da9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ResourceTypeSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
            
        }

    }

}
