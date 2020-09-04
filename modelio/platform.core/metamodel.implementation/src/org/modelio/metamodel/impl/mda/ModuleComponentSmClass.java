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
package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mda.ModuleComponentData;
import org.modelio.metamodel.impl.mda.ModuleParameterSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeSmClass;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("d6948b51-f527-415d-ba21-4a4ff4a1b229")
public class ModuleComponentSmClass extends AbstractProjectSmClass {
    @objid ("329224f9-43c7-4845-a0fa-f6850befac48")
    private SmAttribute licenseKeyAtt;

    @objid ("ebd5e951-c2a0-4b6a-9c47-37753249ba61")
    private SmAttribute majVersionAtt;

    @objid ("d56f917a-41db-4f5b-b88d-68138b09e5f4")
    private SmAttribute minVersionAtt;

    @objid ("e1e62e0f-5f9f-4bbb-9d89-bdfec3421aad")
    private SmAttribute minMinVersionAtt;

    @objid ("9dcea7ef-72c0-4646-b723-4ce46dc2fb7f")
    private SmAttribute minBinVersionCompatibilityAtt;

    @objid ("f2cf56ef-88e8-4a69-a3cb-4af9cbbe4518")
    private SmAttribute javaClassNameAtt;

    @objid ("bd2d80c7-cdc5-43c2-a820-75ee6ca511b2")
    private SmDependency definedPropertyTypeDep;

    @objid ("6dde2d0e-5317-4a6a-abff-6acedfdba379")
    private SmDependency ownedProfileDep;

    @objid ("7ffd3895-284c-414f-88f1-477a625a5925")
    private SmDependency moduleParameterDep;

    @objid ("2b023bce-b373-4139-a770-5cf5546a2ac8")
    private SmDependency dependsOnDep;

    @objid ("0d8d038a-d7c7-4ac7-a3a9-57ad9980f54c")
    private SmDependency impactedDep;

    @objid ("c02fadb2-f49b-4bb5-ac19-8d5494689c0d")
    public ModuleComponentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3b89d7b3-222a-49dc-a8d3-789ed4138eba")
    @Override
    public String getName() {
        return "ModuleComponent";
    }

    @objid ("9fc508cf-6d20-416d-8222-2709178b2335")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("14b806f4-f893-4527-b7de-3af6eaa05d26")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleComponent.class;
    }

    @objid ("89e11faf-a6d1-4ab0-a96d-70c9ac1debf3")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("5b3616a8-34e9-4040-93fb-ae24d9702029")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("68e032d5-70ad-4059-a356-e5d3109b8a2e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractProject.MQNAME);
        this.registerFactory(new ModuleComponentObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.licenseKeyAtt = new LicenseKeySmAttribute();
        this.licenseKeyAtt.init("LicenseKey", this, Integer.class );
        registerAttribute(this.licenseKeyAtt);
        
        this.majVersionAtt = new MajVersionSmAttribute();
        this.majVersionAtt.init("MajVersion", this, Integer.class );
        registerAttribute(this.majVersionAtt);
        
        this.minVersionAtt = new MinVersionSmAttribute();
        this.minVersionAtt.init("MinVersion", this, Integer.class );
        registerAttribute(this.minVersionAtt);
        
        this.minMinVersionAtt = new MinMinVersionSmAttribute();
        this.minMinVersionAtt.init("MinMinVersion", this, String.class );
        registerAttribute(this.minMinVersionAtt);
        
        this.minBinVersionCompatibilityAtt = new MinBinVersionCompatibilitySmAttribute();
        this.minBinVersionCompatibilityAtt.init("MinBinVersionCompatibility", this, String.class );
        registerAttribute(this.minBinVersionCompatibilityAtt);
        
        this.javaClassNameAtt = new JavaClassNameSmAttribute();
        this.javaClassNameAtt.init("JavaClassName", this, String.class );
        registerAttribute(this.javaClassNameAtt);
        
        
        // Initialize and register the SmDependency
        this.definedPropertyTypeDep = new DefinedPropertyTypeSmDependency();
        this.definedPropertyTypeDep.init("DefinedPropertyType", this, metamodel.getMClass(PropertyType.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.definedPropertyTypeDep);
        
        this.ownedProfileDep = new OwnedProfileSmDependency();
        this.ownedProfileDep.init("OwnedProfile", this, metamodel.getMClass(Profile.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedProfileDep);
        
        this.moduleParameterDep = new ModuleParameterSmDependency();
        this.moduleParameterDep.init("ModuleParameter", this, metamodel.getMClass(ModuleParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.moduleParameterDep);
        
        this.dependsOnDep = new DependsOnSmDependency();
        this.dependsOnDep.init("DependsOn", this, metamodel.getMClass(ModuleComponent.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.dependsOnDep);
        
        this.impactedDep = new ImpactedSmDependency();
        this.impactedDep.init("Impacted", this, metamodel.getMClass(ModuleComponent.MQNAME), 0, -1 );
        registerDependency(this.impactedDep);
    }

    @objid ("4373b221-b2a2-4fcd-8a8f-6e4a0a556ba2")
    public SmAttribute getLicenseKeyAtt() {
        if (this.licenseKeyAtt == null) {
        	this.licenseKeyAtt = this.getAttributeDef("LicenseKey");
        }
        return this.licenseKeyAtt;
    }

    @objid ("dc78fc5f-3ab0-4b21-a21f-ba1af4e99f7b")
    public SmAttribute getMajVersionAtt() {
        if (this.majVersionAtt == null) {
        	this.majVersionAtt = this.getAttributeDef("MajVersion");
        }
        return this.majVersionAtt;
    }

    @objid ("8655f337-cd2c-4a42-8bd5-806b015413a5")
    public SmAttribute getMinVersionAtt() {
        if (this.minVersionAtt == null) {
        	this.minVersionAtt = this.getAttributeDef("MinVersion");
        }
        return this.minVersionAtt;
    }

    @objid ("06926ba2-31e9-4e59-8866-d8cb05cbae55")
    public SmAttribute getMinMinVersionAtt() {
        if (this.minMinVersionAtt == null) {
        	this.minMinVersionAtt = this.getAttributeDef("MinMinVersion");
        }
        return this.minMinVersionAtt;
    }

    @objid ("17338c96-ed2c-48ba-a5ec-bf93569bdce6")
    public SmAttribute getMinBinVersionCompatibilityAtt() {
        if (this.minBinVersionCompatibilityAtt == null) {
        	this.minBinVersionCompatibilityAtt = this.getAttributeDef("MinBinVersionCompatibility");
        }
        return this.minBinVersionCompatibilityAtt;
    }

    @objid ("a2ad6dd5-a9c9-4ea1-9315-806fd9f58601")
    public SmAttribute getJavaClassNameAtt() {
        if (this.javaClassNameAtt == null) {
        	this.javaClassNameAtt = this.getAttributeDef("JavaClassName");
        }
        return this.javaClassNameAtt;
    }

    @objid ("018c0f1b-6b37-41bc-9153-72dca3dfbae5")
    public SmDependency getDefinedPropertyTypeDep() {
        if (this.definedPropertyTypeDep == null) {
        	this.definedPropertyTypeDep = this.getDependencyDef("DefinedPropertyType");
        }
        return this.definedPropertyTypeDep;
    }

    @objid ("af58cbc4-12db-44f2-a25d-8fd1d076d0fd")
    public SmDependency getOwnedProfileDep() {
        if (this.ownedProfileDep == null) {
        	this.ownedProfileDep = this.getDependencyDef("OwnedProfile");
        }
        return this.ownedProfileDep;
    }

    @objid ("91fb559e-9eec-4180-9f6d-41a55fddfa8f")
    public SmDependency getModuleParameterDep() {
        if (this.moduleParameterDep == null) {
        	this.moduleParameterDep = this.getDependencyDef("ModuleParameter");
        }
        return this.moduleParameterDep;
    }

    @objid ("cdd291ab-e3ca-439f-9666-2ee83d247ffd")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("c1eaa4cb-05d4-42dd-b9ae-7cbf930c2b97")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("71da1a1e-30b2-4523-9b8d-040d75d51069")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("8ca01b67-f746-4b8f-9a13-9da81377d4b2")
    private static class ModuleComponentObjectFactory implements ISmObjectFactory {
        @objid ("76512b49-265c-4925-978b-f855ee21ce0a")
        private ModuleComponentSmClass smClass;

        @objid ("889ff9de-5dbb-44eb-aef6-50d8ddd5eeb4")
        public ModuleComponentObjectFactory(ModuleComponentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9d0425d7-5ad6-4691-9342-8f4f628e5f76")
        @Override
        public ISmObjectData createData() {
            return new ModuleComponentData(this.smClass);
        }

        @objid ("5cecc08d-a776-41c8-96a3-95196c761f28")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleComponentImpl();
        }

    }

    @objid ("5a3f5244-2139-472e-b313-628f24e24ec6")
    public static class LicenseKeySmAttribute extends SmAttribute {
        @objid ("9ee6db6d-6e29-4fe9-aa12-db97860c60ad")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mLicenseKey;
        }

        @objid ("f6e4e555-aa6e-483b-b85f-ed7f4f95adcf")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mLicenseKey = value;
        }

    }

    @objid ("a8b87c87-9767-4f3a-8249-526c2b0a28ea")
    public static class MajVersionSmAttribute extends SmAttribute {
        @objid ("e32acae6-70f6-4b46-bec0-16b71b565f90")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMajVersion;
        }

        @objid ("2df9ef16-bdf0-4ef6-a13a-ed37535104a1")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMajVersion = value;
        }

    }

    @objid ("1239406f-373f-494f-bdcd-6a5e5ccd0e67")
    public static class MinVersionSmAttribute extends SmAttribute {
        @objid ("a257b701-f7a0-4a10-98f8-22dfbc01a5e4")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinVersion;
        }

        @objid ("9d5229e3-7593-4235-b75c-782b98d18fd1")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinVersion = value;
        }

    }

    @objid ("2681e83c-2c84-4cdb-86c6-7e296a858108")
    public static class MinMinVersionSmAttribute extends SmAttribute {
        @objid ("b2744d6e-64fa-4d8d-9c44-488dda37f28a")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinMinVersion;
        }

        @objid ("ced5d411-a3eb-42ae-b435-747166841781")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinMinVersion = value;
        }

    }

    @objid ("61f01130-8fc5-42c5-937b-8179b2fcceda")
    public static class MinBinVersionCompatibilitySmAttribute extends SmAttribute {
        @objid ("d8ea4bcb-b3b9-40c0-a36d-22fcf69d63b1")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinBinVersionCompatibility;
        }

        @objid ("0f6448a9-8ec2-4def-b7c5-11c1bf360c5f")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinBinVersionCompatibility = value;
        }

    }

    @objid ("a81c5055-b876-4458-8abc-5e7693d3f78d")
    public static class JavaClassNameSmAttribute extends SmAttribute {
        @objid ("90583309-8e79-47d8-b655-0ac58624fddd")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mJavaClassName;
        }

        @objid ("f2f0fdfe-9f73-4b3f-a162-89b8fb227c67")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mJavaClassName = value;
        }

    }

    @objid ("51a014b2-dd21-4730-8fb4-122e85ac0cc9")
    public static class DefinedPropertyTypeSmDependency extends SmMultipleDependency {
        @objid ("2efb4f6c-fb1b-471e-8de0-5051e05975a5")
        private SmDependency symetricDep;

        @objid ("dc5c32e6-9665-45e8-8e62-b3b026e66425")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDefinedPropertyType != null)? ((ModuleComponentData)data).mDefinedPropertyType:SmMultipleDependency.EMPTY;
        }

        @objid ("bc687735-6b19-478d-8ce8-2e486c713a5c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDefinedPropertyType = values;
        }

        @objid ("1b78ee9e-4546-4ce1-880b-81101a3f058d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTypeSmClass)this.getTarget()).getModuleOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("48c416aa-74e9-43b9-a236-f5a821e6e3f0")
    public static class OwnedProfileSmDependency extends SmMultipleDependency {
        @objid ("6118ae5f-0897-4dc5-b3ac-01f241a2f148")
        private SmDependency symetricDep;

        @objid ("b76197b2-ce35-4a13-872c-eaa99bfadffe")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mOwnedProfile != null)? ((ModuleComponentData)data).mOwnedProfile:SmMultipleDependency.EMPTY;
        }

        @objid ("e4333b05-8a4e-4e09-8d0d-7f195acdabc7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mOwnedProfile = values;
        }

        @objid ("e0fa3f3a-708b-4f9b-bdee-f498bf8e2e19")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProfileSmClass)this.getTarget()).getOwnerModuleDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("09802bf2-03b5-4cdf-968e-45e57407bd37")
    public static class ModuleParameterSmDependency extends SmMultipleDependency {
        @objid ("29400ef2-deb6-4e17-ad36-c331959e1ddb")
        private SmDependency symetricDep;

        @objid ("5191b17a-c700-4907-8e4f-206427ec3655")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mModuleParameter != null)? ((ModuleComponentData)data).mModuleParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("6bb95efa-cd63-4b1c-b530-1319a8ceaedc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mModuleParameter = values;
        }

        @objid ("f81d4094-f65c-4167-8ae4-1202066e8e08")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleParameterSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("70f7b74c-eb8c-4b1c-8125-9515f2190b72")
    public static class DependsOnSmDependency extends SmMultipleDependency {
        @objid ("2f7f733a-41ae-45f0-a159-adecfa3cd774")
        private SmDependency symetricDep;

        @objid ("4edf854f-cb12-472a-9360-c5b0acb38e68")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDependsOn != null)? ((ModuleComponentData)data).mDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("31b7ead1-ff35-4d07-a0c8-72d95ff971bd")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDependsOn = values;
        }

        @objid ("330f3e62-b9b0-4dd9-ac84-33d02236c9f0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getImpactedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1ec9a1f3-0132-4983-8e1c-51ff5119891b")
    public static class ImpactedSmDependency extends SmMultipleDependency {
        @objid ("88b66e99-c806-4e4a-832f-781ac970fa64")
        private SmDependency symetricDep;

        @objid ("dac17cdb-974c-4f44-b14b-5707e7224eeb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mImpacted != null)? ((ModuleComponentData)data).mImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("25a7e17a-06b5-44dd-87dd-bb8bbb21c75c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mImpacted = values;
        }

        @objid ("84c22800-dd8d-47d6-ad29-bd2a76528730")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDependsOnDep();
            }
            return this.symetricDep;
        }

    }

}
