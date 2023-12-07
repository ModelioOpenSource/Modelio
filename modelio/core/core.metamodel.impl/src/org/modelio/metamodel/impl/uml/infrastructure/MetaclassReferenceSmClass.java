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
    @objid ("96df12eb-6955-4933-82b0-6f037b70ccc5")
    private SmAttribute referencedClassNameAtt;

    @objid ("09227bb0-151f-4d26-8f20-b8def937b562")
    private SmDependency definedTableDep;

    @objid ("d9fae8c7-dbb8-4d10-9dd4-0815fa001878")
    private SmDependency definedNoteTypeDep;

    @objid ("359087b0-6b9a-410c-99a3-99ad912f47d2")
    private SmDependency definedResourceTypeDep;

    @objid ("e54c1668-1da7-4411-9bd2-aadb9c45ab1b")
    private SmDependency ownerProfileDep;

    @objid ("7da0229d-7500-46b4-91d3-7b0cf3725d99")
    private SmDependency definedTagTypeDep;

    @objid ("85825926-4334-429e-8645-d160c27e61d4")
    public  MetaclassReferenceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("69f3a538-dfc5-4fde-adce-4f7e609c0c61")
    @Override
    public String getName() {
        return "MetaclassReference";
        
    }

    @objid ("0a568ebf-c7fc-4633-860e-a54a0d9fa215")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("a64b9225-d5d4-4bbd-97fa-3eefb71a5dd0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MetaclassReference.class;
        
    }

    @objid ("85c24540-e137-41b7-aa3a-a15d8a1df7c4")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("be6a5329-2bd6-4234-98e6-382304d311c8")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("166026b2-be4b-4692-9fba-786eb31af8cc")
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

    @objid ("6431068f-ca53-496b-b213-eed498c9ead3")
    public SmAttribute getReferencedClassNameAtt() {
        if (this.referencedClassNameAtt == null) {
        	this.referencedClassNameAtt = this.getAttributeDef("ReferencedClassName");
        }
        return this.referencedClassNameAtt;
    }

    @objid ("8225ce8d-02ea-4766-8a18-a00f21b9478b")
    public SmDependency getDefinedTableDep() {
        if (this.definedTableDep == null) {
        	this.definedTableDep = this.getDependencyDef("DefinedTable");
        }
        return this.definedTableDep;
    }

    @objid ("18f0dd70-4974-40b9-8a4a-00b2bf031c20")
    public SmDependency getDefinedNoteTypeDep() {
        if (this.definedNoteTypeDep == null) {
        	this.definedNoteTypeDep = this.getDependencyDef("DefinedNoteType");
        }
        return this.definedNoteTypeDep;
    }

    @objid ("5c6d063d-26f0-4473-9fec-ec5ff9f0dfa5")
    public SmDependency getDefinedResourceTypeDep() {
        if (this.definedResourceTypeDep == null) {
        	this.definedResourceTypeDep = this.getDependencyDef("DefinedResourceType");
        }
        return this.definedResourceTypeDep;
    }

    @objid ("67763b0c-9179-4a89-8ff1-0eedf72f4480")
    public SmDependency getOwnerProfileDep() {
        if (this.ownerProfileDep == null) {
        	this.ownerProfileDep = this.getDependencyDef("OwnerProfile");
        }
        return this.ownerProfileDep;
    }

    @objid ("bf87137a-7844-44ba-a189-28883e308dec")
    public SmDependency getDefinedTagTypeDep() {
        if (this.definedTagTypeDep == null) {
        	this.definedTagTypeDep = this.getDependencyDef("DefinedTagType");
        }
        return this.definedTagTypeDep;
    }

    @objid ("9cddf795-64df-41e2-b836-d05dfd30d000")
    private static class MetaclassReferenceObjectFactory implements ISmObjectFactory {
        @objid ("399a4249-5f6e-42ae-86a8-8c7043949190")
        private MetaclassReferenceSmClass smClass;

        @objid ("ac55bd67-bea4-408e-b49f-14ca8f0c7330")
        public  MetaclassReferenceObjectFactory(MetaclassReferenceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("98820eb9-4af2-45a7-b703-aeb09fc982ee")
        @Override
        public ISmObjectData createData() {
            return new MetaclassReferenceData(this.smClass);
        }

        @objid ("fc468768-56c6-45de-86db-a79cb8f954b5")
        @Override
        public SmObjectImpl createImpl() {
            return new MetaclassReferenceImpl();
        }

    }

    @objid ("ae099c4b-ae98-457e-af7d-68e39b6dacbf")
    public static class ReferencedClassNameSmAttribute extends SmAttribute {
        @objid ("17eddad4-1c76-47b2-9eeb-42c9f8d331b7")
        public Object getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mReferencedClassName;
        }

        @objid ("cd2517e2-826c-47b8-aa6f-d664297f887f")
        public void setValue(ISmObjectData data, Object value) {
            ((MetaclassReferenceData) data).mReferencedClassName = value;
        }

    }

    @objid ("c28a5d23-4440-4895-90af-4958ddf16be9")
    public static class DefinedTableSmDependency extends SmSingleDependency {
        @objid ("0deebe51-a353-46f7-a484-bfcdf3debe4e")
        private SmDependency symetricDep;

        @objid ("c4cf6361-778a-44b2-a33c-18c81802658f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mDefinedTable;
        }

        @objid ("8f147a75-8619-4606-bec7-03e978319a04")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mDefinedTable = value;
        }

        @objid ("e3906847-c98e-4703-ba1f-8b1e20b52224")
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
        @objid ("b107d16e-6ab0-48c3-9d9b-661312e03f37")
        private SmDependency symetricDep;

        @objid ("077ba744-7634-4818-abdc-2e92b2ed54be")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedNoteType != null)? ((MetaclassReferenceData)data).mDefinedNoteType:SmMultipleDependency.EMPTY;
        }

        @objid ("6d55a7b2-ab97-421c-add2-1d11cc293de4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedNoteType = values;
            
        }

        @objid ("355595d4-33e1-4825-8184-a1533a26a7eb")
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
        @objid ("f31a88a0-f635-4f44-8b98-d8cfcb5f6c7f")
        private SmDependency symetricDep;

        @objid ("ef1bec4e-e635-4f0f-8005-39d90ba4e575")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MetaclassReferenceData) data).mOwnerProfile;
        }

        @objid ("bfd47cee-4af5-40b3-8929-a2545955c423")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MetaclassReferenceData) data).mOwnerProfile = value;
        }

        @objid ("ceb4f1c8-5153-40d2-b83b-bf5430476ec7")
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
        @objid ("ba592cf2-a69e-417d-aaf2-41bd9f345164")
        private SmDependency symetricDep;

        @objid ("fb0f07c9-7eca-4428-a6b6-3f6bd83fb784")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedTagType != null)? ((MetaclassReferenceData)data).mDefinedTagType:SmMultipleDependency.EMPTY;
        }

        @objid ("44980e68-ffb8-48c1-9b63-f9e4cd71860c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedTagType = values;
            
        }

        @objid ("6b4b574a-0737-4f69-afd2-ec8990c54d6b")
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
        @objid ("60120141-fada-4db5-8311-5f47f22453f8")
        private SmDependency symetricDep;

        @objid ("523a08f2-55c2-424f-887c-30f6f28627c7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((MetaclassReferenceData)data).mDefinedResourceType != null)? ((MetaclassReferenceData)data).mDefinedResourceType:SmMultipleDependency.EMPTY;
        }

        @objid ("d37b0a18-05ba-400f-b31a-6272604c87d1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((MetaclassReferenceData) data).mDefinedResourceType = values;
            
        }

        @objid ("3edf6e19-24a2-4de5-8ae1-5c28b03b6a42")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ResourceTypeSmClass)this.getTarget()).getOwnerReferenceDep();
            }
            return this.symetricDep;
            
        }

    }

}
