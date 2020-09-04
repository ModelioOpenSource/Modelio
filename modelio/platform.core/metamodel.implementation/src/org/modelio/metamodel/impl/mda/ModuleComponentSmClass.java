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
    @objid ("c31cf758-9345-40d5-9faf-dc52e62e7a81")
    private SmAttribute licenseKeyAtt;

    @objid ("a7e9132b-30f1-48fd-b921-cee8acf75325")
    private SmAttribute majVersionAtt;

    @objid ("2047cb34-ff22-48a9-a43b-8b620105847f")
    private SmAttribute minVersionAtt;

    @objid ("0b7bf2b5-85ff-4aec-84cb-479445f55613")
    private SmAttribute minMinVersionAtt;

    @objid ("8c8d05c4-9469-49ba-bef8-49b53ce93de0")
    private SmAttribute minBinVersionCompatibilityAtt;

    @objid ("5257d208-f0bd-42c5-81e0-4aa9461baa3d")
    private SmAttribute javaClassNameAtt;

    @objid ("dbc7b5ea-e443-4cc3-8979-ac4ecc841686")
    private SmDependency definedPropertyTypeDep;

    @objid ("f257957f-a774-4649-81b3-856ecf0c3c87")
    private SmDependency ownedProfileDep;

    @objid ("8b36d174-6a37-400b-8dce-2f2f8f949cc1")
    private SmDependency moduleParameterDep;

    @objid ("94786f1b-4607-41ba-9f48-1d1f0ac8f951")
    private SmDependency dependsOnDep;

    @objid ("07652185-ae9e-4122-ab30-1e721a2f66e6")
    private SmDependency impactedDep;

    @objid ("07b69b07-914a-43fc-9303-89a8772bead1")
    public ModuleComponentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("69cfd42b-6689-407c-ac23-f7a8c4d11c8c")
    @Override
    public String getName() {
        return "ModuleComponent";
    }

    @objid ("f4f20e07-56fc-4d94-960d-cba20ef0c3ec")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("cead512c-7f68-4d2f-96a7-5b1acacc275d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleComponent.class;
    }

    @objid ("670b2471-20fb-461e-8ebd-f23ae9a60d4f")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("701f875b-9741-490b-a197-dcb999099519")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e1c5b811-d960-45e0-8549-f707ff5a0f31")
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

    @objid ("eafaee55-d9ca-481c-a162-411bc00c0b17")
    public SmAttribute getLicenseKeyAtt() {
        if (this.licenseKeyAtt == null) {
        	this.licenseKeyAtt = this.getAttributeDef("LicenseKey");
        }
        return this.licenseKeyAtt;
    }

    @objid ("a1f2cab6-a4c2-47a9-b696-f302d75f8964")
    public SmAttribute getMajVersionAtt() {
        if (this.majVersionAtt == null) {
        	this.majVersionAtt = this.getAttributeDef("MajVersion");
        }
        return this.majVersionAtt;
    }

    @objid ("882a1efb-5c5b-46d4-b085-a178b52bcb27")
    public SmAttribute getMinVersionAtt() {
        if (this.minVersionAtt == null) {
        	this.minVersionAtt = this.getAttributeDef("MinVersion");
        }
        return this.minVersionAtt;
    }

    @objid ("44ffbc5e-0ec1-4236-b80a-670d67ee63b2")
    public SmAttribute getMinMinVersionAtt() {
        if (this.minMinVersionAtt == null) {
        	this.minMinVersionAtt = this.getAttributeDef("MinMinVersion");
        }
        return this.minMinVersionAtt;
    }

    @objid ("67dbc4f0-4a87-461a-81be-1cbccf5843e9")
    public SmAttribute getMinBinVersionCompatibilityAtt() {
        if (this.minBinVersionCompatibilityAtt == null) {
        	this.minBinVersionCompatibilityAtt = this.getAttributeDef("MinBinVersionCompatibility");
        }
        return this.minBinVersionCompatibilityAtt;
    }

    @objid ("f48d5227-aaa9-44b6-95d4-45720791b484")
    public SmAttribute getJavaClassNameAtt() {
        if (this.javaClassNameAtt == null) {
        	this.javaClassNameAtt = this.getAttributeDef("JavaClassName");
        }
        return this.javaClassNameAtt;
    }

    @objid ("a5e2523c-5aa1-40e2-98f4-80232360d06a")
    public SmDependency getDefinedPropertyTypeDep() {
        if (this.definedPropertyTypeDep == null) {
        	this.definedPropertyTypeDep = this.getDependencyDef("DefinedPropertyType");
        }
        return this.definedPropertyTypeDep;
    }

    @objid ("c423678c-0563-4d43-8a1f-accfcc1ffdb3")
    public SmDependency getOwnedProfileDep() {
        if (this.ownedProfileDep == null) {
        	this.ownedProfileDep = this.getDependencyDef("OwnedProfile");
        }
        return this.ownedProfileDep;
    }

    @objid ("f731dbe9-3125-429b-b8de-68bcb4ad0f02")
    public SmDependency getModuleParameterDep() {
        if (this.moduleParameterDep == null) {
        	this.moduleParameterDep = this.getDependencyDef("ModuleParameter");
        }
        return this.moduleParameterDep;
    }

    @objid ("31bd9666-3cc4-407f-ac5c-e2fee2a2be1f")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("033182a2-c12f-4b1a-ab4a-b0bc87f93676")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("b11e0274-946a-4b05-954e-b21c8a1fbc45")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("8ca01b67-f746-4b8f-9a13-9da81377d4b2")
    private static class ModuleComponentObjectFactory implements ISmObjectFactory {
        @objid ("5f84be56-cf39-49ba-bb52-daa68b5f8c48")
        private ModuleComponentSmClass smClass;

        @objid ("5491620f-5074-4cdb-87c1-9062933a6285")
        public ModuleComponentObjectFactory(ModuleComponentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0adca013-ace8-4bbe-9176-9605e8a8d791")
        @Override
        public ISmObjectData createData() {
            return new ModuleComponentData(this.smClass);
        }

        @objid ("c7e1923e-84c8-4f7d-9011-6f67101b0578")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleComponentImpl();
        }

    }

    @objid ("5a3f5244-2139-472e-b313-628f24e24ec6")
    public static class LicenseKeySmAttribute extends SmAttribute {
        @objid ("c0855fe2-dd5b-4cf8-82f1-463b0edf5d6e")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mLicenseKey;
        }

        @objid ("9fcd9029-d0ba-461d-a6b7-58bea2835f91")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mLicenseKey = value;
        }

    }

    @objid ("a8b87c87-9767-4f3a-8249-526c2b0a28ea")
    public static class MajVersionSmAttribute extends SmAttribute {
        @objid ("817e26b2-ed88-4195-be7b-a23ce9c4f4c3")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMajVersion;
        }

        @objid ("676d8686-8454-451c-8ca3-b4a9cebf4c0b")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMajVersion = value;
        }

    }

    @objid ("1239406f-373f-494f-bdcd-6a5e5ccd0e67")
    public static class MinVersionSmAttribute extends SmAttribute {
        @objid ("13ac3e7d-d622-4ead-899e-e2fa9fa26419")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinVersion;
        }

        @objid ("79d9a26c-35d0-4c89-a847-ebfa68ee0064")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinVersion = value;
        }

    }

    @objid ("2681e83c-2c84-4cdb-86c6-7e296a858108")
    public static class MinMinVersionSmAttribute extends SmAttribute {
        @objid ("0f715162-e020-4702-914b-17e2ab9cce25")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinMinVersion;
        }

        @objid ("0083f725-1cad-4b03-bc43-79971903843b")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinMinVersion = value;
        }

    }

    @objid ("61f01130-8fc5-42c5-937b-8179b2fcceda")
    public static class MinBinVersionCompatibilitySmAttribute extends SmAttribute {
        @objid ("d668d13b-34da-4c83-a6d8-26dcf9ecaaec")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mMinBinVersionCompatibility;
        }

        @objid ("8e130bd8-25b2-4f12-a9e3-4cfa262f80cd")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mMinBinVersionCompatibility = value;
        }

    }

    @objid ("a81c5055-b876-4458-8abc-5e7693d3f78d")
    public static class JavaClassNameSmAttribute extends SmAttribute {
        @objid ("8f357406-917a-41f1-908e-5ea3d7d3081f")
        public Object getValue(ISmObjectData data) {
            return ((ModuleComponentData) data).mJavaClassName;
        }

        @objid ("14463066-be4f-4973-a082-42abc6bc65f9")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleComponentData) data).mJavaClassName = value;
        }

    }

    @objid ("51a014b2-dd21-4730-8fb4-122e85ac0cc9")
    public static class DefinedPropertyTypeSmDependency extends SmMultipleDependency {
        @objid ("5f1ee3f3-06be-42d5-a0d6-9032b16bbefb")
        private SmDependency symetricDep;

        @objid ("ebcac33f-b9c8-48fe-9fb1-a04a2bcc0dac")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDefinedPropertyType != null)? ((ModuleComponentData)data).mDefinedPropertyType:SmMultipleDependency.EMPTY;
        }

        @objid ("8320a3e0-599b-49c4-ad83-9aac0afcf481")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDefinedPropertyType = values;
        }

        @objid ("2deaa477-ba1b-4cd3-9100-bad665dfc3a2")
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
        @objid ("2276e54a-b31a-467e-8179-aa7d0aa0362c")
        private SmDependency symetricDep;

        @objid ("7505bb8d-06d5-4446-8588-4f50b21da815")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mOwnedProfile != null)? ((ModuleComponentData)data).mOwnedProfile:SmMultipleDependency.EMPTY;
        }

        @objid ("5091d483-aff0-4073-9d01-9c1e989001a7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mOwnedProfile = values;
        }

        @objid ("ae6d314e-833d-4242-91ae-b7c2625bd38d")
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
        @objid ("bc8cf69e-ca6c-4297-85df-26fc1e1ff2db")
        private SmDependency symetricDep;

        @objid ("7d5182bd-47a0-4530-9b7d-464a039bc238")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mModuleParameter != null)? ((ModuleComponentData)data).mModuleParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("ca424bab-d0f3-4212-871d-aa4e6b1dbcec")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mModuleParameter = values;
        }

        @objid ("8a445e41-6a38-4981-8886-21883fc689e2")
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
        @objid ("36abcadc-3b89-4b79-8ff7-9a5212762fa1")
        private SmDependency symetricDep;

        @objid ("b19c23e9-ff48-4c5b-96ac-50628d74a868")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mDependsOn != null)? ((ModuleComponentData)data).mDependsOn:SmMultipleDependency.EMPTY;
        }

        @objid ("45c573db-5655-4ddd-be7e-5496c077b724")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mDependsOn = values;
        }

        @objid ("1ca89c57-e439-47d0-9e7f-47d59fabbade")
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
        @objid ("6655c1bd-18e6-4957-9eaf-c7b716f22f63")
        private SmDependency symetricDep;

        @objid ("c1a4d11c-4d0e-4ed7-bed2-b625033f9cf3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModuleComponentData)data).mImpacted != null)? ((ModuleComponentData)data).mImpacted:SmMultipleDependency.EMPTY;
        }

        @objid ("16001d45-9988-47b2-aca0-28ca300b8297")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModuleComponentData) data).mImpacted = values;
        }

        @objid ("87a8cf6d-9e3c-454e-aed8-be05f7c77ebf")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDependsOnDep();
            }
            return this.symetricDep;
        }

    }

}
