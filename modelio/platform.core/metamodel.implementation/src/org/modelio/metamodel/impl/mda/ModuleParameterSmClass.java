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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.mda.ModuleParameterData;
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
    @objid ("5d07f76a-34c5-4127-9998-68cc48f85d45")
    private SmAttribute groupNameAtt;

    @objid ("1e1e7b99-e61d-4634-8608-5b00776c975d")
    private SmAttribute typeAtt;

    @objid ("95982939-c5df-4651-a401-80177db9d78b")
    private SmAttribute isUserReadAtt;

    @objid ("efb01754-c1a5-401c-b979-4a271af731ae")
    private SmAttribute isUserWriteAtt;

    @objid ("2e651b54-38f7-4f21-876b-0e0ee2fac503")
    private SmAttribute isApiReadAtt;

    @objid ("7fe295d0-3c7b-431a-8de4-0247b26e750b")
    private SmAttribute isApiWriteAtt;

    @objid ("525b9c01-8621-468f-ac33-423462828aa0")
    private SmAttribute defaultValueAtt;

    @objid ("82329f0d-56c3-475e-876b-2a4b23f55789")
    private SmDependency ownerDep;

    @objid ("96cf1eb5-dbd5-427d-a670-0462a896dd30")
    private SmDependency enumTypeDep;

    @objid ("5992748a-c9a8-49a9-ae55-2fc757916450")
    public ModuleParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("12aed95c-1cd4-46e2-84d1-1eb4e516fb4b")
    @Override
    public String getName() {
        return "ModuleParameter";
    }

    @objid ("94d962c2-476d-4425-b343-4ac9f7f2d49a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5a8086a1-1102-46f7-a0e8-1bb8925ba346")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleParameter.class;
    }

    @objid ("f8f1fbf8-0bee-4055-92ec-e71571608bbd")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("479238a9-8d7f-481d-ab85-90f2e7826b94")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8b100dfc-6ba6-4176-9508-d81f635e98e9")
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

    @objid ("570d773e-8be0-4f59-8ab3-12ac8d83408d")
    public SmAttribute getGroupNameAtt() {
        if (this.groupNameAtt == null) {
        	this.groupNameAtt = this.getAttributeDef("GroupName");
        }
        return this.groupNameAtt;
    }

    @objid ("b44b0703-91fe-46af-b624-7a354696e7f5")
    public SmAttribute getTypeAtt() {
        if (this.typeAtt == null) {
        	this.typeAtt = this.getAttributeDef("Type");
        }
        return this.typeAtt;
    }

    @objid ("50029d84-6600-4c7b-a8b3-edc61c2606b0")
    public SmAttribute getIsUserReadAtt() {
        if (this.isUserReadAtt == null) {
        	this.isUserReadAtt = this.getAttributeDef("IsUserRead");
        }
        return this.isUserReadAtt;
    }

    @objid ("4a0c6b4d-0b75-47ac-9f39-c8ecdee5a75e")
    public SmAttribute getIsUserWriteAtt() {
        if (this.isUserWriteAtt == null) {
        	this.isUserWriteAtt = this.getAttributeDef("IsUserWrite");
        }
        return this.isUserWriteAtt;
    }

    @objid ("180241f3-4c8f-406c-a555-f3147b5f8277")
    public SmAttribute getIsApiReadAtt() {
        if (this.isApiReadAtt == null) {
        	this.isApiReadAtt = this.getAttributeDef("IsApiRead");
        }
        return this.isApiReadAtt;
    }

    @objid ("cffae76b-1d88-487c-bdac-30980484485e")
    public SmAttribute getIsApiWriteAtt() {
        if (this.isApiWriteAtt == null) {
        	this.isApiWriteAtt = this.getAttributeDef("IsApiWrite");
        }
        return this.isApiWriteAtt;
    }

    @objid ("623df36f-1167-40ec-a1e0-429ea7b60a92")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("f2d59f7a-cc8b-414a-91da-be042d69d912")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("53fc39e5-1501-4230-8165-58af415b3562")
    public SmDependency getEnumTypeDep() {
        if (this.enumTypeDep == null) {
        	this.enumTypeDep = this.getDependencyDef("EnumType");
        }
        return this.enumTypeDep;
    }

    @objid ("77d1f397-47d4-426c-a707-e8996b2c6692")
    private static class ModuleParameterObjectFactory implements ISmObjectFactory {
        @objid ("1268ae3b-2d9e-4ab6-b4d1-171dba9c43ac")
        private ModuleParameterSmClass smClass;

        @objid ("4f699ce3-6228-41d4-a95d-7dad3581b540")
        public ModuleParameterObjectFactory(ModuleParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6fdc3ade-80cd-471b-b546-63ad0d30e966")
        @Override
        public ISmObjectData createData() {
            return new ModuleParameterData(this.smClass);
        }

        @objid ("4276a182-0259-4b24-8db7-f2c88d5817a5")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleParameterImpl();
        }

    }

    @objid ("e823d7f5-0a56-4b47-a8b1-ac93c83d35eb")
    public static class GroupNameSmAttribute extends SmAttribute {
        @objid ("c9d2617a-52b5-4701-beae-70adac0cdc00")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mGroupName;
        }

        @objid ("1fac94fd-27c2-436c-acca-c1dcda26bbba")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mGroupName = value;
        }

    }

    @objid ("741f3a56-0a6d-4594-ab43-0b0fbb3597ef")
    public static class TypeSmAttribute extends SmAttribute {
        @objid ("1f07f803-dbc1-45dd-85a4-b08dc27dc2eb")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mType;
        }

        @objid ("94b977a4-5346-4db4-9c7f-4b54de79d344")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mType = value;
        }

    }

    @objid ("05bc0644-a2a5-4803-98ab-78adc874fa16")
    public static class IsUserReadSmAttribute extends SmAttribute {
        @objid ("e7526eba-a33d-40d0-b1c9-891f9ebf1bf3")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserRead;
        }

        @objid ("4e9def84-476f-462d-8112-7c39e1260089")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserRead = value;
        }

    }

    @objid ("737751c7-6836-456c-bd45-97fda468d965")
    public static class IsUserWriteSmAttribute extends SmAttribute {
        @objid ("e3736a4d-9a90-4edb-a32a-13181895c872")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserWrite;
        }

        @objid ("bdcf549d-1626-4356-bd6a-03013416c747")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserWrite = value;
        }

    }

    @objid ("97a34c69-8835-4896-b20c-c9d6c4c19d24")
    public static class IsApiReadSmAttribute extends SmAttribute {
        @objid ("590c455c-7eec-4cc6-81b8-632772427e34")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiRead;
        }

        @objid ("1146b203-0c5e-4534-97fe-015db03ad582")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiRead = value;
        }

    }

    @objid ("e2c26f19-39dd-4079-a626-e11b88adcb12")
    public static class IsApiWriteSmAttribute extends SmAttribute {
        @objid ("e5f2619a-a76d-44f8-a37d-4ee048604bab")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiWrite;
        }

        @objid ("aada8076-a982-4540-a67b-c898793b5105")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiWrite = value;
        }

    }

    @objid ("584167ce-487b-4cbf-95fa-0ad187324913")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("03fed848-2205-48ea-887f-f9de891a7ad9")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mDefaultValue;
        }

        @objid ("bd2a3675-0c84-4473-82a9-69b7d8ee1803")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mDefaultValue = value;
        }

    }

    @objid ("533ba30c-57bf-494c-8128-b27361a28b6e")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("9c33957c-3cc4-4a5e-bbfb-713aad67bb4d")
        private SmDependency symetricDep;

        @objid ("136f292f-0418-4894-bdc1-9b2d9e82d95f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mOwner;
        }

        @objid ("86b013fb-757c-492f-891f-8e7829c9d308")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mOwner = value;
        }

        @objid ("17ef3d4a-b191-4fc7-b9b5-9f41544368fd")
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
        @objid ("fa8ca3b8-8e1e-4114-8841-36e6645a856f")
        private SmDependency symetricDep;

        @objid ("7c61fe64-4bbd-48d3-b9ee-afab97945a93")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mEnumType;
        }

        @objid ("f259b5f9-2980-40e8-95da-30662e667663")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mEnumType = value;
        }

        @objid ("52032c25-7045-4584-a0f5-eaadcddec0eb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getOccurenceConfigParamDep();
            }
            return this.symetricDep;
        }

    }

}
