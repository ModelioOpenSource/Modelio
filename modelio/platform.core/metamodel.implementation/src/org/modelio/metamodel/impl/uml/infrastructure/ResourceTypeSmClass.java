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
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeData;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
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
    @objid ("4ad63599-f110-465a-a51a-c8a6f0da946b")
    private SmAttribute isHiddenAtt;

    @objid ("0623e316-8426-471d-a8b5-53e372784733")
    private SmAttribute labelKeyAtt;

    @objid ("b9cbfc88-3bb1-46d6-a491-07f651d7124f")
    private SmAttribute iconAtt;

    @objid ("4e498bce-0a59-4f58-8c51-5b0514818296")
    private SmAttribute imageAtt;

    @objid ("f9adf514-cea3-4ab3-8b6d-60fac09f1cdc")
    private SmDependency ownerStereotypeDep;

    @objid ("9cd71e66-ba4f-405c-8f0d-f2e1163166b8")
    private SmDependency ownerReferenceDep;

    @objid ("542de3b1-be84-4413-b812-34f503881bad")
    private SmDependency typedResourceDep;

    @objid ("6eb42bf9-c51d-4967-b246-5ea07b496ae2")
    public ResourceTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4f3b23e1-60dc-4548-9598-42836bc240ae")
    @Override
    public String getName() {
        return "ResourceType";
    }

    @objid ("473b7e9b-4175-4b03-a8af-ad5745b1f962")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("866a1644-4d4b-4ff9-aa7e-06402288a107")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ResourceType.class;
    }

    @objid ("355e7669-712f-41c8-b116-d5b45c8dfb54")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("d2fb9c62-deee-4c97-a346-cdfae23bae17")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("83186a0d-2a1a-4876-b9b6-5d18a8717928")
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

    @objid ("08f4285d-beb9-43bc-9ec4-cbe1bf7070fa")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("3e22c6df-42d5-45a6-8c30-761dc5e3b497")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("b6302579-17eb-48d1-89d7-d705531544ff")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("7f0565f5-e1b2-415e-a9c5-b65a53dd7d4d")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("9b94ee9f-902a-461d-b8ca-6caad1271f02")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("e686e0dc-d1bb-4d09-bf7d-c199a5ecbee9")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("df21b005-a698-43e1-a594-637027b8b664")
    public SmDependency getTypedResourceDep() {
        if (this.typedResourceDep == null) {
        	this.typedResourceDep = this.getDependencyDef("TypedResource");
        }
        return this.typedResourceDep;
    }

    @objid ("36555d86-8ca6-4215-b7ba-b6d0c31eb9d8")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("aa465960-34a8-4caa-96d3-b1601f1c508b")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIsHidden;
        }

        @objid ("56ae0a69-a72f-44cf-b83c-416a425752db")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIsHidden = value;
        }

    }

    @objid ("eab07041-da7e-4004-8562-63daeec768ee")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("a7c521b2-6cef-4450-a7e4-34b39efd2a84")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mLabelKey;
        }

        @objid ("768b7149-9596-4326-8f9c-d4a6b3389dd2")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7ac93b61-1990-4795-ac8a-af43ce83dc69")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("841539b7-5ac4-4595-a3aa-cf4b373eb032")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIcon;
        }

        @objid ("9e622182-04c6-46c2-bcbb-3f4db6d0a0ad")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIcon = value;
        }

    }

    @objid ("22365074-94f7-4cf1-8157-8ff9dfd1d51e")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("9f789215-e1ef-410f-a4ea-82c73669b7ed")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mImage;
        }

        @objid ("a35a2e0a-838a-460b-b8db-32b3d24be0db")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mImage = value;
        }

    }

    @objid ("7ff73313-d2d4-47a4-8d2b-90b34d35f16f")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("21332a26-b38c-470c-938b-010238f991c5")
        private SmDependency symetricDep;

        @objid ("3ff3587c-7513-48c4-985a-bbee0d7cb737")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerStereotype;
        }

        @objid ("fdf90e93-9a40-4476-aaad-a9c64466c41a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerStereotype = value;
        }

        @objid ("b824b1a0-f918-42c5-9069-6dc49fdfa217")
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
        @objid ("fcd405dd-2da2-4ea9-8353-72a7cceacdc5")
        private SmDependency symetricDep;

        @objid ("51b56e44-7809-41d9-b652-52a0225baf85")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerReference;
        }

        @objid ("d21c6d1f-b8ab-4754-9ae2-bcbf9b317b9d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerReference = value;
        }

        @objid ("54684c4d-2d90-4110-8596-1c2a56088e40")
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
        @objid ("56efde9d-deb5-49f5-b495-ff6c67945c6a")
        private ResourceTypeSmClass smClass;

        @objid ("88ba4cc5-9f94-443f-8d8a-efa4d2037e33")
        public ResourceTypeObjectFactory(ResourceTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e1b7b915-606c-4b4d-8f2e-2497a0b6223d")
        @Override
        public ISmObjectData createData() {
            return new ResourceTypeData(this.smClass);
        }

        @objid ("343c892b-db41-4395-ac73-1c69ad5e1c3a")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceTypeImpl();
        }

    }

    @objid ("537f2ae6-0d49-4736-a933-c8be3b6b0286")
    public static class TypedResourceSmDependency extends SmMultipleDependency {
        @objid ("c606b2e7-3bfa-4efb-8d5c-0f770900c6f0")
        private SmDependency symetricDep;

        @objid ("89d5dce7-a83f-486c-b857-05db081ab80d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ResourceTypeData)data).mTypedResource != null)? ((ResourceTypeData)data).mTypedResource:SmMultipleDependency.EMPTY;
        }

        @objid ("3e55209d-55cd-4d04-8eed-4ceb993cd6c8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ResourceTypeData) data).mTypedResource = values;
        }

        @objid ("eeb082f1-2a06-4ef2-81d3-7f8d923ee413")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

}
