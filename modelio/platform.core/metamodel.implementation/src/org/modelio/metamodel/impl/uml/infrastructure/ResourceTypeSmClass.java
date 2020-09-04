/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("d2e998be-e110-41b3-9044-51e534946026")
    private SmAttribute isHiddenAtt;

    @objid ("168250c5-28fb-421e-8653-74cbf6733345")
    private SmAttribute labelKeyAtt;

    @objid ("f5b3fe6e-b81c-459c-bb6e-a34ad019b9cc")
    private SmAttribute iconAtt;

    @objid ("569f54d2-ab81-44fb-b085-4ccf08c5691b")
    private SmAttribute imageAtt;

    @objid ("84a79169-33be-4e3e-9805-b68cdd6d7f7c")
    private SmDependency ownerStereotypeDep;

    @objid ("3a84ae43-eb01-4fa0-93f6-639a85a6085e")
    private SmDependency ownerReferenceDep;

    @objid ("8c0bffe9-3360-4eba-a229-0e275b3c6c55")
    private SmDependency typedResourceDep;

    @objid ("34ad9de7-271d-49d0-9166-821da871262b")
    public ResourceTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3362e647-78ab-4675-93d0-5ed3ef3f5f8c")
    @Override
    public String getName() {
        return "ResourceType";
    }

    @objid ("42c254d2-0c89-4b12-a88b-65d6deeff6ab")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("0d7f7d95-5ae7-4df8-8014-3af8b6374845")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ResourceType.class;
    }

    @objid ("ae9c8211-4c2e-4bc2-990d-c5286ff4a577")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("586445c1-fba0-40c7-b6f6-c42d7910c6ba")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ad949703-3ae4-4072-a3a0-1d84380281fa")
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

    @objid ("352fe225-3c29-4f3c-b85f-31f580ccd7c0")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("60b70ccb-7c32-41ea-a696-ff6ff6227abf")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("38209db5-752e-442b-b726-b5a3482d1bf0")
    public SmAttribute getIconAtt() {
        if (this.iconAtt == null) {
        	this.iconAtt = this.getAttributeDef("Icon");
        }
        return this.iconAtt;
    }

    @objid ("6701fb9f-7d2d-4bd3-921c-57eedd93637f")
    public SmAttribute getImageAtt() {
        if (this.imageAtt == null) {
        	this.imageAtt = this.getAttributeDef("Image");
        }
        return this.imageAtt;
    }

    @objid ("61eca5cb-f390-4592-a764-a86fad10ab28")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("75db11a0-411b-4faf-b578-8206d558753d")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("f12b52ae-e02e-4094-9925-17fbeb26f0f0")
    public SmDependency getTypedResourceDep() {
        if (this.typedResourceDep == null) {
        	this.typedResourceDep = this.getDependencyDef("TypedResource");
        }
        return this.typedResourceDep;
    }

    @objid ("36555d86-8ca6-4215-b7ba-b6d0c31eb9d8")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("d9aa4bbd-0585-47c9-8f71-5e0dc9f097ea")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIsHidden;
        }

        @objid ("d69d78f4-92b0-4c93-9963-30909098044b")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIsHidden = value;
        }

    }

    @objid ("eab07041-da7e-4004-8562-63daeec768ee")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("0fb72877-f0ce-4003-84d2-b2eb84b74358")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mLabelKey;
        }

        @objid ("dced6834-a394-45ac-a378-c095dd578138")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mLabelKey = value;
        }

    }

    @objid ("7ac93b61-1990-4795-ac8a-af43ce83dc69")
    public static class IconSmAttribute extends SmAttribute {
        @objid ("327790bd-ca96-487b-b24f-1eb52fee02eb")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mIcon;
        }

        @objid ("f27ee6d8-6b92-49f1-a5e7-682e5e4ea1b6")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mIcon = value;
        }

    }

    @objid ("22365074-94f7-4cf1-8157-8ff9dfd1d51e")
    public static class ImageSmAttribute extends SmAttribute {
        @objid ("bc1cc2e9-5996-4602-87da-dd78e2cc6b2e")
        public Object getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mImage;
        }

        @objid ("294e0b33-0dd3-4de7-89bc-6e2ae7b93ad5")
        public void setValue(ISmObjectData data, Object value) {
            ((ResourceTypeData) data).mImage = value;
        }

    }

    @objid ("7ff73313-d2d4-47a4-8d2b-90b34d35f16f")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("265ecd72-70b9-4161-afe7-bded971efbdc")
        private SmDependency symetricDep;

        @objid ("97f7fe30-06cf-4fd6-aee0-90b055220755")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerStereotype;
        }

        @objid ("a0a97753-e318-487c-8e6f-f024cfa29089")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerStereotype = value;
        }

        @objid ("2cad488c-32e2-4f59-a5c6-f172a33179ae")
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
        @objid ("806ffc17-f972-44a4-9d8e-18f6f2b61a8e")
        private SmDependency symetricDep;

        @objid ("30a4ceae-a588-4f45-901d-653a9e34f66e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ResourceTypeData) data).mOwnerReference;
        }

        @objid ("7f881316-e0a0-46f0-b295-90968dd7a4ad")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ResourceTypeData) data).mOwnerReference = value;
        }

        @objid ("7466a542-e9d6-4e0f-8dca-441577502f4a")
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
        @objid ("81f6a7b4-326d-4d20-a2bc-272e49ef5ed8")
        private ResourceTypeSmClass smClass;

        @objid ("9bca9151-341c-419d-9693-9fad44229a12")
        public ResourceTypeObjectFactory(ResourceTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d27f7fd1-9fe1-4563-8302-757287b1ed5e")
        @Override
        public ISmObjectData createData() {
            return new ResourceTypeData(this.smClass);
        }

        @objid ("fb565c52-73ce-41e1-bb94-b1fcc04cf11d")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceTypeImpl();
        }

    }

    @objid ("537f2ae6-0d49-4736-a933-c8be3b6b0286")
    public static class TypedResourceSmDependency extends SmMultipleDependency {
        @objid ("bf1c47de-a6cb-4354-a81f-2916bda117f0")
        private SmDependency symetricDep;

        @objid ("32136ebd-2541-401f-8f87-26fb45f48dd4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ResourceTypeData)data).mTypedResource != null)? ((ResourceTypeData)data).mTypedResource:SmMultipleDependency.EMPTY;
        }

        @objid ("ef794de9-93c8-4bcf-a38c-55372659791f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ResourceTypeData) data).mTypedResource = values;
        }

        @objid ("c6696a1d-96e8-4083-b3a2-5ae98f786d6d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractResourceSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

}
