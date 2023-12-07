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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.EnumeratedPropertyTypeSmClass;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("a382e42b-581e-446d-9e3d-9c08d36d8d98")
public class ModuleParameterSmClass extends ModelElementSmClass {
    @objid ("d5f80a87-79ef-45aa-8e80-dc495ceb0740")
    private SmAttribute groupNameAtt;

    @objid ("1c9743b9-a294-488a-bdc6-9784891092b1")
    private SmAttribute typeAtt;

    @objid ("6d78652f-fce0-4915-84ed-1ba5293cff2c")
    private SmAttribute isUserReadAtt;

    @objid ("40bfd933-b68c-41f1-9719-b734d4fffa22")
    private SmAttribute isUserWriteAtt;

    @objid ("ad517e58-698a-40c0-a70a-0b036b7241cb")
    private SmAttribute isApiReadAtt;

    @objid ("c9c4fbe8-601b-4d2c-9b5a-4427c4518843")
    private SmAttribute isApiWriteAtt;

    @objid ("5ec52bcf-8752-4ab8-8821-1a9e1dca3789")
    private SmAttribute defaultValueAtt;

    @objid ("841b5cd2-6385-4506-acba-7ac1907db85c")
    private SmDependency ownerDep;

    @objid ("b70a2815-2cab-4f3f-b17a-c2cfc49c88b2")
    private SmDependency enumTypeDep;

    @objid ("328ce855-78c2-4bc6-a011-068785a30676")
    public  ModuleParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5a393e27-dcd1-4d94-a87f-d81bd551c622")
    @Override
    public String getName() {
        return "ModuleParameter";
        
    }

    @objid ("58f3e101-0748-427c-8769-e4bd14e3ed55")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("59cecb52-0675-4d78-894e-94dc4b38110a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleParameter.class;
        
    }

    @objid ("2373e49b-72e5-4183-b707-88ed532e8b60")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("9f9ae826-5e80-4324-b505-0efe0db5726a")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("762999b8-e5ee-4b36-9b62-c70d36c6220c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ModuleParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.groupNameAtt = new GroupNameSmAttribute();
        this.groupNameAtt.init("GroupName", this, String.class );
        registerAttribute(this.groupNameAtt);
        
        this.typeAtt = new TypeSmAttribute();
        this.typeAtt.init("Type", this, ModuleParameterType.class );
        registerAttribute(this.typeAtt);
        
        this.isUserReadAtt = new IsUserReadSmAttribute();
        this.isUserReadAtt.init("IsUserRead", this, Boolean.class );
        registerAttribute(this.isUserReadAtt);
        
        this.isUserWriteAtt = new IsUserWriteSmAttribute();
        this.isUserWriteAtt.init("IsUserWrite", this, Boolean.class );
        registerAttribute(this.isUserWriteAtt);
        
        this.isApiReadAtt = new IsApiReadSmAttribute();
        this.isApiReadAtt.init("IsApiRead", this, Boolean.class );
        registerAttribute(this.isApiReadAtt);
        
        this.isApiWriteAtt = new IsApiWriteSmAttribute();
        this.isApiWriteAtt.init("IsApiWrite", this, Boolean.class );
        registerAttribute(this.isApiWriteAtt);
        
        this.defaultValueAtt = new DefaultValueSmAttribute();
        this.defaultValueAtt.init("DefaultValue", this, String.class );
        registerAttribute(this.defaultValueAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ModuleComponent.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.enumTypeDep = new EnumTypeSmDependency();
        this.enumTypeDep.init("EnumType", this, metamodel.getMClass(EnumeratedPropertyType.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.enumTypeDep);
        
        
    }

    @objid ("45c95816-d2ca-44b1-bf22-04521bbc7d27")
    public SmAttribute getGroupNameAtt() {
        if (this.groupNameAtt == null) {
        	this.groupNameAtt = this.getAttributeDef("GroupName");
        }
        return this.groupNameAtt;
    }

    @objid ("566e4326-9468-4c66-8857-e89223a3992f")
    public SmAttribute getTypeAtt() {
        if (this.typeAtt == null) {
        	this.typeAtt = this.getAttributeDef("Type");
        }
        return this.typeAtt;
    }

    @objid ("7f42b6a0-cbd9-4367-8cc2-8d08a421ca2c")
    public SmAttribute getIsUserReadAtt() {
        if (this.isUserReadAtt == null) {
        	this.isUserReadAtt = this.getAttributeDef("IsUserRead");
        }
        return this.isUserReadAtt;
    }

    @objid ("863c194d-df3c-4d8f-b784-c22beb3f8677")
    public SmAttribute getIsUserWriteAtt() {
        if (this.isUserWriteAtt == null) {
        	this.isUserWriteAtt = this.getAttributeDef("IsUserWrite");
        }
        return this.isUserWriteAtt;
    }

    @objid ("54a1fe6b-6133-4fa4-a231-b9fd3129cee0")
    public SmAttribute getIsApiReadAtt() {
        if (this.isApiReadAtt == null) {
        	this.isApiReadAtt = this.getAttributeDef("IsApiRead");
        }
        return this.isApiReadAtt;
    }

    @objid ("5d10cae2-df3a-47d3-a948-d7e780c908b0")
    public SmAttribute getIsApiWriteAtt() {
        if (this.isApiWriteAtt == null) {
        	this.isApiWriteAtt = this.getAttributeDef("IsApiWrite");
        }
        return this.isApiWriteAtt;
    }

    @objid ("9074d8fb-091f-4708-94bd-374e9a8ecbbb")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("780f116a-0bfd-4d06-99e5-40d177efb3bd")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("3a1c9080-c648-4f5f-8809-91e3b2f19c60")
    public SmDependency getEnumTypeDep() {
        if (this.enumTypeDep == null) {
        	this.enumTypeDep = this.getDependencyDef("EnumType");
        }
        return this.enumTypeDep;
    }

    @objid ("77d1f397-47d4-426c-a707-e8996b2c6692")
    private static class ModuleParameterObjectFactory implements ISmObjectFactory {
        @objid ("0534a082-6e91-4f4f-bf78-00a4046e7588")
        private ModuleParameterSmClass smClass;

        @objid ("9e12cd9f-edb4-44ae-b421-77900e94b600")
        public  ModuleParameterObjectFactory(ModuleParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e3393a64-5261-4243-b823-7667c60a0cac")
        @Override
        public ISmObjectData createData() {
            return new ModuleParameterData(this.smClass);
        }

        @objid ("cfa6cdac-0461-417f-998b-306181a09500")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleParameterImpl();
        }

    }

    @objid ("e823d7f5-0a56-4b47-a8b1-ac93c83d35eb")
    public static class GroupNameSmAttribute extends SmAttribute {
        @objid ("2e746d07-c1c7-49f4-8eab-3fe855ced365")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mGroupName;
        }

        @objid ("e66dc2d5-aabd-488b-bd91-50fd21907e75")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mGroupName = value;
        }

    }

    @objid ("741f3a56-0a6d-4594-ab43-0b0fbb3597ef")
    public static class TypeSmAttribute extends SmAttribute {
        @objid ("8b70055b-f65b-4b91-9648-d2cbd8535be9")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mType;
        }

        @objid ("47d8d9e5-7173-4825-8a95-d178b184dfd4")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mType = value;
        }

    }

    @objid ("05bc0644-a2a5-4803-98ab-78adc874fa16")
    public static class IsUserReadSmAttribute extends SmAttribute {
        @objid ("e29675f6-d7bc-4ff7-ab45-a4e2abfe0e03")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserRead;
        }

        @objid ("3e4185a2-650d-4db5-ac0b-47999cbe95e5")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserRead = value;
        }

    }

    @objid ("737751c7-6836-456c-bd45-97fda468d965")
    public static class IsUserWriteSmAttribute extends SmAttribute {
        @objid ("4afe840a-b3eb-4513-9174-7d9e1bd3d04c")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserWrite;
        }

        @objid ("2aa76510-6fb2-4f58-a51f-2213a032cf3c")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserWrite = value;
        }

    }

    @objid ("97a34c69-8835-4896-b20c-c9d6c4c19d24")
    public static class IsApiReadSmAttribute extends SmAttribute {
        @objid ("fd98ba44-8539-4621-8878-c6c6c9a7ee75")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiRead;
        }

        @objid ("98f36180-c1e1-4466-9e01-50545ba26e83")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiRead = value;
        }

    }

    @objid ("e2c26f19-39dd-4079-a626-e11b88adcb12")
    public static class IsApiWriteSmAttribute extends SmAttribute {
        @objid ("2a0a1f3e-ed69-4686-98fc-610e4a4eb7ce")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiWrite;
        }

        @objid ("5dab2684-634b-4648-8676-836aa900143a")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiWrite = value;
        }

    }

    @objid ("584167ce-487b-4cbf-95fa-0ad187324913")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("c8c6c2ca-3b84-4cdc-ad36-a31248c36a04")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mDefaultValue;
        }

        @objid ("5e43bc58-20eb-445e-8191-51d433529305")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mDefaultValue = value;
        }

    }

    @objid ("533ba30c-57bf-494c-8128-b27361a28b6e")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("30aab72f-28a4-4853-9706-a24e1726b2ad")
        private SmDependency symetricDep;

        @objid ("96b46f0e-7fd6-4f82-b6df-5c9a468bf21d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mOwner;
        }

        @objid ("46905156-504a-4d60-b2ef-d3e2cd9a073a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mOwner = value;
        }

        @objid ("26143983-7c23-4ccc-b730-6e28f4556571")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getModuleParameterDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("93cc8c63-c9cf-40eb-8dab-d95355f79dfc")
    public static class EnumTypeSmDependency extends SmSingleDependency {
        @objid ("22204f54-3b8e-4f49-95f9-768d82afc54f")
        private SmDependency symetricDep;

        @objid ("c3ff81e3-17e6-4679-b18a-54c65da387b6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mEnumType;
        }

        @objid ("8270b6cf-ac34-411d-aae1-04e6f7971405")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mEnumType = value;
        }

        @objid ("be6c7b78-edfa-44cb-a4d4-b0f73cf313bb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getOccurenceConfigParamDep();
            }
            return this.symetricDep;
            
        }

    }

}
