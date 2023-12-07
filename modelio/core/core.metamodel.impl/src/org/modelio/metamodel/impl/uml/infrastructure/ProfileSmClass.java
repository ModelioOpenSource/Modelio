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
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeSmClass;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("8062fd8e-8258-4289-a68a-5b061a57688c")
public class ProfileSmClass extends ModelElementSmClass {
    @objid ("e36ab679-2798-4a29-a4eb-244ae8913150")
    private SmDependency definedStereotypeDep;

    @objid ("b625bf5e-41ea-4460-854f-6dec029c5156")
    private SmDependency ownedReferenceDep;

    @objid ("59a1b241-bf82-4c0a-ae06-311332b99860")
    private SmDependency ownerModuleDep;

    @objid ("a4c45fd7-705c-46b1-8808-2e949dd24d5c")
    private SmDependency definedTypeDep;

    @objid ("cde8af46-934a-4828-83a0-aeeef04b1db9")
    public  ProfileSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b0ef21b9-5503-4402-be11-84fea870cbe6")
    @Override
    public String getName() {
        return "Profile";
        
    }

    @objid ("f2a444ff-37c1-49c0-9a86-01269813ea34")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("cc93f096-abef-4669-b061-f31b3f5d8c65")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Profile.class;
        
    }

    @objid ("fbad7e12-e769-4257-b341-9bc9b1178386")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("926f9889-e2ff-41b5-989d-6ab5516d5a54")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("0576dad4-83bc-493c-b144-b4ec89fb3b66")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ProfileObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.definedStereotypeDep = new DefinedStereotypeSmDependency();
        this.definedStereotypeDep.init("DefinedStereotype", this, metamodel.getMClass(Stereotype.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedStereotypeDep);
        
        this.ownedReferenceDep = new OwnedReferenceSmDependency();
        this.ownedReferenceDep.init("OwnedReference", this, metamodel.getMClass(MetaclassReference.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedReferenceDep);
        
        this.ownerModuleDep = new OwnerModuleSmDependency();
        this.ownerModuleDep.init("OwnerModule", this, metamodel.getMClass(ModuleComponent.MQNAME), 0, 1 );
        registerDependency(this.ownerModuleDep);
        
        this.definedTypeDep = new DefinedTypeSmDependency();
        this.definedTypeDep.init("DefinedType", this, metamodel.getMClass(PropertyType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedTypeDep);
        
        
    }

    @objid ("df15543c-ae5d-44f5-abc2-050a632b05ca")
    public SmDependency getDefinedStereotypeDep() {
        if (this.definedStereotypeDep == null) {
        	this.definedStereotypeDep = this.getDependencyDef("DefinedStereotype");
        }
        return this.definedStereotypeDep;
    }

    @objid ("6eb7b48d-e69f-4244-bebe-b1f4ad6f281d")
    public SmDependency getOwnedReferenceDep() {
        if (this.ownedReferenceDep == null) {
        	this.ownedReferenceDep = this.getDependencyDef("OwnedReference");
        }
        return this.ownedReferenceDep;
    }

    @objid ("1f80997d-40ba-4a6b-88bc-3523245ef924")
    public SmDependency getOwnerModuleDep() {
        if (this.ownerModuleDep == null) {
        	this.ownerModuleDep = this.getDependencyDef("OwnerModule");
        }
        return this.ownerModuleDep;
    }

    @objid ("716c75a2-e75b-4917-8d8a-1c5c30fb5082")
    public SmDependency getDefinedTypeDep() {
        if (this.definedTypeDep == null) {
        	this.definedTypeDep = this.getDependencyDef("DefinedType");
        }
        return this.definedTypeDep;
    }

    @objid ("af3df1e5-641e-455a-a319-c3d1f2bd659c")
    private static class ProfileObjectFactory implements ISmObjectFactory {
        @objid ("9abbc938-1581-4d4b-bf4e-510bd5195400")
        private ProfileSmClass smClass;

        @objid ("ecb335b9-440c-4e16-aa1b-7257b126e175")
        public  ProfileObjectFactory(ProfileSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("70a9b0c6-469f-4a69-a88e-042cba210ab8")
        @Override
        public ISmObjectData createData() {
            return new ProfileData(this.smClass);
        }

        @objid ("b5e1b51b-1d84-43d9-a642-4bd089e54350")
        @Override
        public SmObjectImpl createImpl() {
            return new ProfileImpl();
        }

    }

    @objid ("a467cebc-cf2a-41b7-a6f4-76a785bbe5c8")
    public static class DefinedStereotypeSmDependency extends SmMultipleDependency {
        @objid ("ec6d4b93-718c-4f40-9a45-27f97bb7110a")
        private SmDependency symetricDep;

        @objid ("63cda987-8c4c-4a84-9226-63cb23e4c6be")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedStereotype != null)? ((ProfileData)data).mDefinedStereotype:SmMultipleDependency.EMPTY;
        }

        @objid ("01423ca8-c60c-4356-9c14-c8db51a64ebc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedStereotype = values;
            
        }

        @objid ("8054f9f8-4a7f-419e-a96a-37334efb66b4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("7a8bbfd9-67b5-4ccc-8066-76c786848730")
    public static class OwnerModuleSmDependency extends SmSingleDependency {
        @objid ("20a4ec65-74c0-4492-9d57-ad9c5562bf18")
        private SmDependency symetricDep;

        @objid ("3b8f531d-6e76-43ac-8eda-a4aa373381dd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ProfileData) data).mOwnerModule;
        }

        @objid ("b3dc3bd8-4303-4d24-b4aa-41353a59944c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ProfileData) data).mOwnerModule = value;
        }

        @objid ("16f7aa77-7447-423f-a714-cab06862df53")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getOwnedProfileDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6365bf06-7c47-43e7-a78b-459e9ac9df94")
    public static class OwnedReferenceSmDependency extends SmMultipleDependency {
        @objid ("e6681540-80cd-44e2-916c-7620dd99463b")
        private SmDependency symetricDep;

        @objid ("f1046628-d7a2-4046-a6b4-bef90ee9a8c5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mOwnedReference != null)? ((ProfileData)data).mOwnedReference:SmMultipleDependency.EMPTY;
        }

        @objid ("0f156a4a-857d-494c-94f0-5324720d7be2")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mOwnedReference = values;
            
        }

        @objid ("d8712faf-ec46-41f7-aa75-3ac3a4390559")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getOwnerProfileDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("2ac6e53e-46f2-4a13-b15a-3271cc0aac6b")
    public static class DefinedTypeSmDependency extends SmMultipleDependency {
        @objid ("feed021d-e7b6-456a-a0d5-bed91ad0dcc4")
        private SmDependency symetricDep;

        @objid ("9d192686-4437-49f9-8bae-8ce5991cdc58")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedType != null)? ((ProfileData)data).mDefinedType:SmMultipleDependency.EMPTY;
        }

        @objid ("55787ed6-33a2-4ac9-86fd-dd61e0b498a8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedType = values;
            
        }

        @objid ("ad926ea0-9786-4c1b-87b4-edbd5b9ca1e8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTypeSmClass)this.getTarget()).getAnalystOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}
