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

package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
    @objid ("91e509d8-0d5a-417c-9121-bb66c1c1db6a")
    private SmAttribute licenseKeyAtt;

    @objid ("8aaf94eb-cb56-464d-8f94-bb003657a4f4")
    private SmAttribute majVersionAtt;

    @objid ("0847e0c7-1780-4f43-a4c0-e83c012bca56")
    private SmAttribute minVersionAtt;

    @objid ("f5a692ca-69f1-4664-9eee-cfe07ce66018")
    private SmAttribute minMinVersionAtt;

    @objid ("022ecc71-09be-4738-a6d6-efe3a80b81ec")
    private SmAttribute minBinVersionCompatibilityAtt;

    @objid ("31223e26-a380-471c-9fbf-b27ccf8ae8cf")
    private SmAttribute javaClassNameAtt;

    @objid ("e993b0ec-e080-4fa1-804c-265ee573b88b")
    private SmDependency definedPropertyTypeDep;

    @objid ("6b924595-8695-42f5-b3ff-dd7e63065256")
    private SmDependency ownedProfileDep;

    @objid ("bca1b85b-7fd7-4a67-b8c0-19e2f54424c3")
    private SmDependency moduleParameterDep;

    @objid ("c3aa671f-1e59-4e2d-8760-85097bcdc46b")
    private SmDependency dependsOnDep;

    @objid ("22d43acf-4e94-4bfe-816e-a715fd3ecac8")
    private SmDependency impactedDep;

    @objid ("f2483e97-6a92-4289-a4f8-fb1089cc666c")
    public  ModuleComponentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4542f819-0e09-40db-8b1e-37634e84dacb")
    @Override
    public String getName() {
        return "ModuleComponent";
        
    }

    @objid ("b0af2fcd-fb55-4e5c-a08c-2fa91eb2816f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("88b39aa2-e0a3-4ca4-8681-d6a27ec25409")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleComponent.class;
        
    }

    @objid ("77250680-7c9d-4ceb-91b2-96dbbda00db2")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("9e0ccb97-1942-47d2-8884-aa488f395c9f")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("4ec55a45-effc-4ddc-9856-2af58779ccb3")
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

    @objid ("12661e48-0f08-4791-9f0f-839fb07fc242")
    public SmAttribute getLicenseKeyAtt() {
        if (this.licenseKeyAtt == null) {
        	this.licenseKeyAtt = this.getAttributeDef("LicenseKey");
        }
        return this.licenseKeyAtt;
    }

    @objid ("52d44caf-cae6-488b-b310-fa5ebdad55dd")
    public SmAttribute getMajVersionAtt() {
        if (this.majVersionAtt == null) {
        	this.majVersionAtt = this.getAttributeDef("MajVersion");
        }
        return this.majVersionAtt;
    }

    @objid ("fee8ee26-94d4-41e4-949e-57b79dfa7033")
    public SmAttribute getMinVersionAtt() {
        if (this.minVersionAtt == null) {
        	this.minVersionAtt = this.getAttributeDef("MinVersion");
        }
        return this.minVersionAtt;
    }

    @objid ("148fd0d4-fb25-47ff-b560-d43ae6f619fc")
    public SmAttribute getMinMinVersionAtt() {
        if (this.minMinVersionAtt == null) {
        	this.minMinVersionAtt = this.getAttributeDef("MinMinVersion");
        }
        return this.minMinVersionAtt;
    }

    @objid ("d5e7a533-2720-4ba4-8b64-bfdee849ebcb")
    public SmAttribute getMinBinVersionCompatibilityAtt() {
        if (this.minBinVersionCompatibilityAtt == null) {
        	this.minBinVersionCompatibilityAtt = this.getAttributeDef("MinBinVersionCompatibility");
        }
        return this.minBinVersionCompatibilityAtt;
    }

    @objid ("9180d4f4-63c4-4aad-8770-1445a9e34e62")
    public SmAttribute getJavaClassNameAtt() {
        if (this.javaClassNameAtt == null) {
        	this.javaClassNameAtt = this.getAttributeDef("JavaClassName");
        }
        return this.javaClassNameAtt;
    }

    @objid ("a28befa9-d621-45b2-802f-7e69b44ee21e")
    public SmDependency getDefinedPropertyTypeDep() {
        if (this.definedPropertyTypeDep == null) {
        	this.definedPropertyTypeDep = this.getDependencyDef("DefinedPropertyType");
        }
        return this.definedPropertyTypeDep;
    }

    @objid ("eec67377-1258-4508-95ba-00de9abe4c17")
    public SmDependency getOwnedProfileDep() {
        if (this.ownedProfileDep == null) {
        	this.ownedProfileDep = this.getDependencyDef("OwnedProfile");
        }
        return this.ownedProfileDep;
    }

    @objid ("9e446d3c-ea9f-4c2a-a141-9c3eccc3701f")
    public SmDependency getModuleParameterDep() {
        if (this.moduleParameterDep == null) {
        	this.moduleParameterDep = this.getDependencyDef("ModuleParameter");
        }
        return this.moduleParameterDep;
    }

    @objid ("ccb01783-49ac-4eb2-919f-f9e592fc0d03")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("5fee7d23-5402-481a-aedf-60e357eb0193")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("85817e52-ab59-46b5-990a-c90f504098b4")
    @Override
    public boolean areOrphansAllowed() {
        return true;
        
    }

    @objid ("8ca01b67-f746-4b8f-9a13-9da81377d4b2")
    private static class ModuleComponentObjectFactory implements ISmObjectFactory {
        @objid ("455eb607-e132-49b1-b9aa-f2a136201f05")
        private ModuleComponentSmClass smClass;

        @objid ("496626e0-4863-479e-b791-17884eba1ed1")
        public  ModuleComponentObjectFactory(ModuleComponentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("680a922e-4511-4e1e-8a0c-94fba295c707")
        @Override
        public ISmObjectData createData() {
            return new ModuleComponentData(this.smClass);
        }

        @objid ("cefaba33-ff5f-4da7-9bd7-3b7aa487c9d3")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleComponentImpl();
        }

    }

    @objid ("5a3f5244-2139-472e-b313-628f24e24ec6")
    public static class LicenseKeySmAttribute extends SmAttribute {
        @objid ("2aeffb3f-00e5-41a3-b9e5-d1f8d9c4eef5")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mLicenseKey;
        }

        @objid ("9c855951-8def-49bc-88a6-ee69687c2c60")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mLicenseKey = value;
        }

    }

    @objid ("a8b87c87-9767-4f3a-8249-526c2b0a28ea")
    public static class MajVersionSmAttribute extends SmAttribute {
        @objid ("ae98f39a-ac99-4607-b788-038bceb8e334")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMajVersion;
        }

        @objid ("486247d8-87e6-45d4-83cb-d490a6836dfc")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMajVersion = value;
        }

    }

    @objid ("1239406f-373f-494f-bdcd-6a5e5ccd0e67")
    public static class MinVersionSmAttribute extends SmAttribute {
        @objid ("9087360b-3ada-41e2-9bf9-d881ba07f8ca")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinVersion;
        }

        @objid ("21472f6d-68f8-4a89-9de1-84779aeffa0b")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinVersion = value;
        }

    }

    @objid ("2681e83c-2c84-4cdb-86c6-7e296a858108")
    public static class MinMinVersionSmAttribute extends SmAttribute {
        @objid ("b5adf10e-ccad-4ead-adae-f6fb4322ea7a")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinMinVersion;
        }

        @objid ("e9107896-5d81-4f31-896d-f46a837b8a76")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinMinVersion = value;
        }

    }

    @objid ("61f01130-8fc5-42c5-937b-8179b2fcceda")
    public static class MinBinVersionCompatibilitySmAttribute extends SmAttribute {
        @objid ("9997e7d8-363f-4769-9035-985612c506cd")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinBinVersionCompatibility;
        }

        @objid ("f9647df9-ac5e-46ec-bbc3-fedf823e5dce")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinBinVersionCompatibility = value;
        }

    }

    @objid ("a81c5055-b876-4458-8abc-5e7693d3f78d")
    public static class JavaClassNameSmAttribute extends SmAttribute {
        @objid ("ef134620-48d1-4655-9ab8-0991c92f9354")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mJavaClassName;
        }

        @objid ("ec09b80a-d2af-4690-8b30-232c33fdadab")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mJavaClassName = value;
        }

    }

    @objid ("51a014b2-dd21-4730-8fb4-122e85ac0cc9")
    public static class DefinedPropertyTypeSmDependency extends SmMultipleDependency {
        @objid ("f299e095-d7c4-422a-a187-638d48a09482")
        private SmDependency symetricDep;

        @objid ("ade73fbd-307e-454d-96b4-f3025e033886")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDefinedPropertyType != null)? ((ModuleComponentData)data).mDefinedPropertyType:SmMultipleDependency.EMPTY;
        }

        @objid ("e9f1ccf1-966f-447c-a7b2-b7e463f4d6fe")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDefinedPropertyType = values;
            
        }

        @objid ("d777997f-7a32-4396-b4e3-1967bab83f64")
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
        @objid ("54939036-2297-48a0-ba01-962c972922bb")
        private SmDependency symetricDep;

        @objid ("f4c83f33-9e08-476f-8163-5dee3cfc3737")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mOwnedProfile != null)? ((ModuleComponentData)data).mOwnedProfile:SmMultipleDependency.EMPTY;
        }

        @objid ("e43953de-843e-4122-b955-31903c24ba6f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mOwnedProfile = values;
            
        }

        @objid ("badcf6ef-c46c-4176-8b03-5cd06493575a")
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
        @objid ("2bd431c6-ad09-4627-8243-7bc5a9ec208a")
        private SmDependency symetricDep;

        @objid ("782addb2-2426-4c6b-a538-c6d353f60b0e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mModuleParameter != null)? ((ModuleComponentData)data).mModuleParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("ee592056-4b34-46bd-8285-14d66a5c1ddb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mModuleParameter = values;
            
        }

        @objid ("016d730c-9b8e-4a55-ab11-e8ffbf689ca1")
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
        @objid ("9f0904bf-72e1-48ba-b93b-94c45e2a6904")
        private SmDependency symetricDep;

        @objid ("1501bbe7-6f30-4ee6-8e43-204565eaf05a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDependsOn != null)? ((ModuleComponentData)data).mDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("7d5f3062-123b-47fe-8706-87917d640b7c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDependsOn = values;
            
        }

        @objid ("54fea73d-e1db-49a4-8633-d6bddf7645f6")
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
        @objid ("d2c2e006-768e-4a0e-9137-af22ba686338")
        private SmDependency symetricDep;

        @objid ("4c1fd941-e366-470e-bc12-963d9694d841")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mImpacted != null)? ((ModuleComponentData)data).mImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("4f5e10e7-fb46-450a-8e6b-4e52e0ea8bce")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mImpacted = values;
            
        }

        @objid ("e874e1e2-9f8f-41b5-958a-9b938844f1ac")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDependsOnDep();
            }
            return this.symetricDep;
            
        }

    }

}
