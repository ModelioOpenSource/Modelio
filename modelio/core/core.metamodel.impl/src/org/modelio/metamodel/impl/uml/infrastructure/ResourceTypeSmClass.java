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
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
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

@objid ("a04bbc92-8e0b-4e19-be5a-d9bf89e06abf")
public class ResourceTypeSmClass extends ModelElementSmClass {
    @objid ("4d91edf1-8190-4337-82f9-d9e93ea03e9e")
    private SmAttribute isHiddenAtt;

    @objid ("b82ee824-b170-4783-bc8f-087a1077e05e")
    private SmAttribute labelKeyAtt;

    @objid ("f6b09ccd-06d3-422c-9a9f-f6e2c76ec12c")
    private SmAttribute iconAtt;

    @objid ("a7c46584-a727-4093-882f-0f8fd8e963b9")
    private SmAttribute imageAtt;

    @objid ("47b3a612-dd19-42c8-a038-2145c7bf360c")
    private SmDependency ownerStereotypeDep;

    @objid ("e427d399-b020-4e97-9bc0-4c4c6768132c")
    private SmDependency ownerReferenceDep;

    @objid ("00f3278b-8b44-4f8f-86b3-9872c28a3d55")
    private SmDependency typedResourceDep;

    @objid ("52329694-6691-4363-ae58-f848f0c1cd4c")
    public  ResourceTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4aa1aadd-28ae-444a-a3d8-5738dd8ea125")
    @Override
    public String getName() {
        return "ResourceType";
        
    }

    @objid ("d7a9e46f-7faa-4fc3-a8d8-1d6b569dcedb")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("9ab34066-1ceb-41fc-b97b-3ec350fb2d93")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ResourceType.class;
        
    }

    @objid ("6ab0b8e9-fc25-4a3d-bad8-9fa249d2b057")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("8aa0a7d3-3943-40fc-ab64-4ab38811d645")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("1cf464e5-bf7a-4687-b48b-3cde60e8b780")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ResourceTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isHiddenAtt = new IsHiddenSmAttribute();
        this.isHiddenAtt.init("IsHidden", this, Boolean.class );
        registerAttribute(this.isHiddenAtt);
        
        this.labelKeyAtt = new LabelKeySmAttribute();
        this.labelKeyAtt.init("LabelKey", this, String.class );
        registerAttribute(this.labelKeyAtt);
        
        this.iconAtt = new IconSmAttribute();
        this.iconAtt.init("Icon", this, String.class );
        registerAttribute(this.iconAtt);
        
        this.imageAtt = new ImageSmAttribute();
        this.imageAtt.init("Image", this, String.class );
        registerAttribute(this.imageAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerStereotypeDep = new OwnerStereotypeSmDependency();
        this.ownerStereotypeDep.init("OwnerStereotype", this, metamodel.getMClass(Stereotype.MQNAME), 0, 1 );
        registerDependency(this.ownerStereotypeDep);
        
        this.ownerReferenceDep = new OwnerReferenceSmDependency();
        this.ownerReferenceDep.init("OwnerReference", this, metamodel.getMClass(MetaclassReference.MQNAME), 0, 1 );
        registerDependency(this.ownerReferenceDep);
        
        this.typedResourceDep = new TypedResourceSmDependency();
        this.typedResourceDep.init("TypedResource", this, metamodel.getMClass(AbstractResource.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.typedResourceDep);
        
        
    }

    @objid ("a6d87bb0-86ee-4429-a4e1-497fc0d32bd4")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("1a17cb81-0edb-4052-9170-3283b161fa8e")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("0d135c87-6d5c-4df9-a820-1aa02cddfaf5")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("3f118b90-6b6a-47fd-aad0-b8f7c079293a")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("d7e2b5fa-f0df-4ce5-97ec-86a2d20d8615")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("877e25a4-5a34-4eff-956c-a26ff72d0dfa")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("5dafb59e-6b97-472a-b3fa-b4c70b120729")
    public SmDependency getTypedResourceDep() {
        if (this.typedResourceDep == null) {
        	this.typedResourceDep = this.getDependencyDef("TypedResource");
        }
        return this.typedResourceDep;
    }

    @objid ("36555d86-8ca6-4215-b7ba-b6d0c31eb9d8")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("2411d2c2-f7e2-4eba-bf81-4bad235888a1")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIsHidden;
        }

        @objid ("4cc28dfe-62e7-47c1-8c70-a4cfb9f2a77a")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIsHidden = value;
        }

    }

    @objid ("eab07041-da7e-4004-8562-63daeec768ee")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("5a3274d3-c481-4961-b8ed-5f8850cdd39e")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mLabelKey;
        }

        @objid ("72b1123c-97ce-415b-b03b-9f41245c2133")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7ac93b61-1990-4795-ac8a-af43ce83dc69")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("57467765-a3af-4d0e-9491-d21a9a1e162e")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIcon;
        }

        @objid ("67e1495a-1982-4b1a-8d29-4d52d31c8a91")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIcon = value;
        }

    }

    @objid ("22365074-94f7-4cf1-8157-8ff9dfd1d51e")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("fa034cbf-6967-4f35-8a7e-fb217d540a2e")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mImage;
        }

        @objid ("f5e9f3dd-1d08-4fae-b75c-cf44567cd891")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mImage = value;
        }

    }

    @objid ("7ff73313-d2d4-47a4-8d2b-90b34d35f16f")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("ae681910-a727-46a5-a1a3-d23b02a25b94")
        private SmDependency symetricDep;

        @objid ("a20df5b9-436e-481f-b7c3-e281d0d47d8d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerStereotype;
        }

        @objid ("f12ae0ff-ff14-42f3-9b4c-a962bf139127")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerStereotype = value;
        }

        @objid ("e2623ad4-691e-4407-b8c9-c8c8b6b99728")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getDefinedResourceTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("68a0b4c9-afa4-4e9e-b507-27bddc252390")
    public static class OwnerReferenceSmDependency extends SmSingleDependency {
        @objid ("4c5288d3-6b6c-44de-b6d3-b85538a6139b")
        private SmDependency symetricDep;

        @objid ("bb5387d0-eb51-4a8d-b9fa-9c9e429353c9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerReference;
        }

        @objid ("9d163d1b-6a34-40b2-9e54-7e8906b45ffb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerReference = value;
        }

        @objid ("fd1ced54-8220-4ef6-853d-fa87340636bd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedResourceTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("93ee99bc-5ca3-4664-a88a-553c805d5723")
    private static class ResourceTypeObjectFactory implements ISmObjectFactory {
        @objid ("0bc97304-fccd-42ae-aa5f-d0aafb793a23")
        private ResourceTypeSmClass smClass;

        @objid ("734885c3-2520-4f28-bbea-a6d198d96a69")
        public  ResourceTypeObjectFactory(ResourceTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("82fd415d-9b71-4067-8034-c4c62a6bfc21")
        @Override
        public ISmObjectData createData() {
            return new ResourceTypeData(this.smClass);
        }

        @objid ("5e630fa1-4af7-4720-b91d-b5a493e3298b")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceTypeImpl();
        }

    }

    @objid ("537f2ae6-0d49-4736-a933-c8be3b6b0286")
    public static class TypedResourceSmDependency extends SmMultipleDependency {
        @objid ("10fd6fea-c694-4149-abde-3b57f56b2f79")
        private SmDependency symetricDep;

        @objid ("14911087-3d13-40a1-b48a-fd02aaf09ea6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ResourceTypeData)data).mTypedResource != null)? ((ResourceTypeData)data).mTypedResource:SmMultipleDependency.EMPTY;
        }

        @objid ("e02d08b8-172b-4e97-bca3-ec0e8f36f4ad")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ResourceTypeData) data).mTypedResource = values;
            
        }

        @objid ("13dfcebe-5b0a-4bba-96c8-b223a1b6c42b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
