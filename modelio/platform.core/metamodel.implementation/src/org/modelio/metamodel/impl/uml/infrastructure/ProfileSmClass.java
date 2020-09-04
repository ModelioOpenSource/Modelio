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
    @objid ("4e0da114-5f87-4594-a834-b982bbc4398b")
    private SmDependency definedStereotypeDep;

    @objid ("9ce172c9-205c-42aa-b762-95b954392af8")
    private SmDependency ownedReferenceDep;

    @objid ("f44b6a0e-d86e-4f11-b50d-64b273b38c99")
    private SmDependency ownerModuleDep;

    @objid ("009eb753-5b03-4081-899e-3715da15ea43")
    private SmDependency definedTypeDep;

    @objid ("0d40c03f-f160-4ca4-b729-f44159df5a2d")
    public ProfileSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("63ea6a66-d62f-4bc1-9661-9b9be0dfb860")
    @Override
    public String getName() {
        return "Profile";
    }

    @objid ("21ed88a7-140f-42fd-9c04-c009b56a9485")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("17a0847c-738a-49f3-bd02-7f320350b1bf")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Profile.class;
    }

    @objid ("1d2c11aa-ce99-4e45-b98e-7625a4edccd6")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("5c3ebf60-4ad3-4a4e-8b54-6d7adc98f9c2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8e3a4e5f-ef0d-4756-bc74-5f18f37af648")
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

    @objid ("5b69d537-65d2-4cca-8b68-9b338f3233c6")
    public SmDependency getDefinedStereotypeDep() {
        if (this.definedStereotypeDep == null) {
        	this.definedStereotypeDep = this.getDependencyDef("DefinedStereotype");
        }
        return this.definedStereotypeDep;
    }

    @objid ("8bba3793-add3-42c4-8f9c-491d8da928c4")
    public SmDependency getOwnedReferenceDep() {
        if (this.ownedReferenceDep == null) {
        	this.ownedReferenceDep = this.getDependencyDef("OwnedReference");
        }
        return this.ownedReferenceDep;
    }

    @objid ("8a7494c5-2735-4a2a-bcef-7a633b946b4f")
    public SmDependency getOwnerModuleDep() {
        if (this.ownerModuleDep == null) {
        	this.ownerModuleDep = this.getDependencyDef("OwnerModule");
        }
        return this.ownerModuleDep;
    }

    @objid ("e2bf93e4-abf1-4ae5-9820-b9c531d2322b")
    public SmDependency getDefinedTypeDep() {
        if (this.definedTypeDep == null) {
        	this.definedTypeDep = this.getDependencyDef("DefinedType");
        }
        return this.definedTypeDep;
    }

    @objid ("af3df1e5-641e-455a-a319-c3d1f2bd659c")
    private static class ProfileObjectFactory implements ISmObjectFactory {
        @objid ("cd400cc9-e7d9-44f9-bdae-5d0de8294725")
        private ProfileSmClass smClass;

        @objid ("38413c24-784b-41c0-a18d-198e5afaca0f")
        public ProfileObjectFactory(ProfileSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1b368660-dad7-48b7-8d69-3081463b317e")
        @Override
        public ISmObjectData createData() {
            return new ProfileData(this.smClass);
        }

        @objid ("1961fd90-b65e-4e0d-94c9-d5947dc7f379")
        @Override
        public SmObjectImpl createImpl() {
            return new ProfileImpl();
        }

    }

    @objid ("a467cebc-cf2a-41b7-a6f4-76a785bbe5c8")
    public static class DefinedStereotypeSmDependency extends SmMultipleDependency {
        @objid ("e3d21d35-8f69-44ac-bf64-3808ad0d7cb3")
        private SmDependency symetricDep;

        @objid ("c0b6c30d-33c0-4bc1-bcc3-6cb9abd51bbe")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedStereotype != null)? ((ProfileData)data).mDefinedStereotype:SmMultipleDependency.EMPTY;
        }

        @objid ("c2b5b1cc-3e15-488c-90eb-4b14dd8b7464")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedStereotype = values;
        }

        @objid ("bd959c74-7df4-4147-83f9-5db4c72d0bcf")
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
        @objid ("2e812e28-ae70-4a37-aeca-94d7ade2f9d1")
        private SmDependency symetricDep;

        @objid ("b6e59c4c-7814-41fa-b75a-2d7b2d3ff24f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ProfileData) data).mOwnerModule;
        }

        @objid ("e61ef21e-c25f-401a-8100-815005faca92")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ProfileData) data).mOwnerModule = value;
        }

        @objid ("df2ea92b-391b-4893-aaf8-8056a79fb68c")
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
        @objid ("29086fdf-488f-4e35-b4ba-2d7ebb7f3c4c")
        private SmDependency symetricDep;

        @objid ("637e3fc8-99c6-492c-b6f0-706a8239cc51")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mOwnedReference != null)? ((ProfileData)data).mOwnedReference:SmMultipleDependency.EMPTY;
        }

        @objid ("2306c00b-da68-4c6c-8cb7-eabbf31f6f22")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mOwnedReference = values;
        }

        @objid ("30de4438-cfa0-4136-98b6-33898a9988e1")
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
        @objid ("202a883d-f80d-4c53-8f13-72e19eafe159")
        private SmDependency symetricDep;

        @objid ("4a2f6661-07db-42b7-9c07-a1a4beca9fa0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProfileData)data).mDefinedType != null)? ((ProfileData)data).mDefinedType:SmMultipleDependency.EMPTY;
        }

        @objid ("abca1c8f-8881-4e56-994b-7a7b4cfbcc1b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProfileData) data).mDefinedType = values;
        }

        @objid ("2c3fd024-01ed-4ce9-9d8c-e019897d4ab3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTypeSmClass)this.getTarget()).getAnalystOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
