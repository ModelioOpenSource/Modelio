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
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileData;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
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
    @objid ("7b671948-0f4c-4347-8418-8a508a66a614")
    private SmDependency definedStereotypeDep;

    @objid ("bb26aa6a-9e60-47a3-883a-e4503b2136d6")
    private SmDependency ownedReferenceDep;

    @objid ("5a6ab6c1-77e5-4af7-b967-f451b252c4b9")
    private SmDependency ownerModuleDep;

    @objid ("b9e644e3-40ee-43f7-b736-444e0c995b54")
    private SmDependency definedTypeDep;

    @objid ("59a5a404-8f40-4da4-bd7a-636e1889488c")
    public ProfileSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d25fbb4f-cb04-4e30-9fd6-2a33fb4aabcc")
    @Override
    public String getName() {
        return "Profile";
    }

    @objid ("c4828215-a2fb-4c98-aa7d-9792ae566ac8")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("2b2d5b98-1225-4447-9810-edf690bc4dd6")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Profile.class;
    }

    @objid ("f6fa5236-6287-4ed9-9810-cd99208e172e")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("ee0161f2-84d4-4db7-8b23-95058d8ebdf9")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("5c0b704b-f7d2-4c2c-9506-f6cd54ea4efa")
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

    @objid ("efd5e55b-99a1-4906-b08d-017678d339e6")
    public SmDependency getDefinedStereotypeDep() {
        if (this.definedStereotypeDep == null) {
        	this.definedStereotypeDep = this.getDependencyDef("DefinedStereotype");
        }
        return this.definedStereotypeDep;
    }

    @objid ("4c8bff8a-4802-4377-9358-b765f6edf1a5")
    public SmDependency getOwnedReferenceDep() {
        if (this.ownedReferenceDep == null) {
        	this.ownedReferenceDep = this.getDependencyDef("OwnedReference");
        }
        return this.ownedReferenceDep;
    }

    @objid ("81691551-8464-4ea2-9091-e518e7e9578f")
    public SmDependency getOwnerModuleDep() {
        if (this.ownerModuleDep == null) {
        	this.ownerModuleDep = this.getDependencyDef("OwnerModule");
        }
        return this.ownerModuleDep;
    }

    @objid ("c94e9d86-b1ff-4886-b4f7-826b27e341f9")
    public SmDependency getDefinedTypeDep() {
        if (this.definedTypeDep == null) {
        	this.definedTypeDep = this.getDependencyDef("DefinedType");
        }
        return this.definedTypeDep;
    }

    @objid ("af3df1e5-641e-455a-a319-c3d1f2bd659c")
    private static class ProfileObjectFactory implements ISmObjectFactory {
        @objid ("94020794-2d2b-4939-951e-c26dc52237cb")
        private ProfileSmClass smClass;

        @objid ("6d814b84-e3d3-4475-8dd7-c22b8b2ad969")
        public ProfileObjectFactory(ProfileSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("011e0b86-0b73-4240-92f1-d1bd381f98ef")
        @Override
        public ISmObjectData createData() {
            return new ProfileData(this.smClass);
        }

        @objid ("897b0e43-d376-48f3-95c5-f6dba7258bb9")
        @Override
        public SmObjectImpl createImpl() {
            return new ProfileImpl();
        }

    }

    @objid ("a467cebc-cf2a-41b7-a6f4-76a785bbe5c8")
    public static class DefinedStereotypeSmDependency extends SmMultipleDependency {
        @objid ("eb592396-430a-4eb8-bb45-6ed6ccad7619")
        private SmDependency symetricDep;

        @objid ("598119cc-cb07-4bfc-afd1-2d7332870b89")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedStereotype != null)? ((ProfileData)data).mDefinedStereotype:SmMultipleDependency.EMPTY;
        }

        @objid ("dc95a27b-5e4f-4c71-80c7-86edda83ec0b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedStereotype = values;
        }

        @objid ("d842b672-ce03-4be5-843c-7ccda21e5fc4")
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
        @objid ("e779a7d5-c3be-4fbd-8fa2-26ebd6802121")
        private SmDependency symetricDep;

        @objid ("d3727f49-c49d-451e-aca7-f8ae38efa254")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ProfileData) data).mOwnerModule;
        }

        @objid ("fb948e86-8b05-498d-892a-9b010411d507")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ProfileData) data).mOwnerModule = value;
        }

        @objid ("6753f663-737b-4695-a07f-157a83fee370")
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
        @objid ("7947f03e-ff32-4a6c-a5be-95e6811a7349")
        private SmDependency symetricDep;

        @objid ("a39651da-bd77-44df-88f6-1a114383d415")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mOwnedReference != null)? ((ProfileData)data).mOwnedReference:SmMultipleDependency.EMPTY;
        }

        @objid ("c6fde493-793a-4044-ab64-96ec8e64967f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mOwnedReference = values;
        }

        @objid ("f6da9eca-da5a-4b18-867f-bf653e61ab75")
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
        @objid ("fcf15a5e-40fd-4754-ac90-a09365411922")
        private SmDependency symetricDep;

        @objid ("5d68de6a-052a-402e-aade-7854b601951e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedType != null)? ((ProfileData)data).mDefinedType:SmMultipleDependency.EMPTY;
        }

        @objid ("7f30b9a0-b114-4670-b013-a7db4014ca18")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedType = values;
        }

        @objid ("9a5fe914-44f5-4927-a671-382c469cebdc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTypeSmClass)this.getTarget()).getAnalystOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
