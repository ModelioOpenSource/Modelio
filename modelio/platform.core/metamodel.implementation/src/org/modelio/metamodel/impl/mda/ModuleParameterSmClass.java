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
    @objid ("75e0f315-c74f-435d-938b-c00823880006")
    private SmAttribute groupNameAtt;

    @objid ("792e3116-0431-4d97-bff8-740a7fe91cdd")
    private SmAttribute typeAtt;

    @objid ("22a1a834-a4c0-45a4-9100-29c8e3077628")
    private SmAttribute isUserReadAtt;

    @objid ("110fdb8f-7fee-4a55-a024-5bb379bea845")
    private SmAttribute isUserWriteAtt;

    @objid ("cfe0ed96-a8b1-45d0-a84c-e2c7324be043")
    private SmAttribute isApiReadAtt;

    @objid ("3a6865d4-c657-4400-a084-c9fcd05a79d0")
    private SmAttribute isApiWriteAtt;

    @objid ("a89d6f00-b3f1-4007-ab1d-604ffd61bd66")
    private SmAttribute defaultValueAtt;

    @objid ("8243326e-c0e9-453b-b22a-2d912bcd150c")
    private SmDependency ownerDep;

    @objid ("b9114192-0890-430f-8a6c-f6d8d9c820ce")
    private SmDependency enumTypeDep;

    @objid ("840d4acf-573c-442c-b4b2-45ab8e0717d3")
    public ModuleParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("eb935853-2cce-4946-8f83-297ea71c9eee")
    @Override
    public String getName() {
        return "ModuleParameter";
    }

    @objid ("f60f27dc-8d88-4007-a82f-55434c4ab5b1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("486dfbc3-0f5a-471f-b0c0-7adb2a0aee6c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModuleParameter.class;
    }

    @objid ("45193d48-ee9e-44a8-ae94-bae897fb1f2b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5a8c8be9-d6ca-4aa3-9371-630cf7304535")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3cf8b09b-0ac6-4967-9677-aefefcd42976")
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

    @objid ("39600bb9-5cb8-4fcc-bae0-8e9f7197f6a3")
    public SmAttribute getGroupNameAtt() {
        if (this.groupNameAtt == null) {
        	this.groupNameAtt = this.getAttributeDef("GroupName");
        }
        return this.groupNameAtt;
    }

    @objid ("7a04498a-b164-43c5-bcb3-d2481c8c4f2f")
    public SmAttribute getTypeAtt() {
        if (this.typeAtt == null) {
        	this.typeAtt = this.getAttributeDef("Type");
        }
        return this.typeAtt;
    }

    @objid ("47043a7c-a775-4086-a683-fe16b1f5128f")
    public SmAttribute getIsUserReadAtt() {
        if (this.isUserReadAtt == null) {
        	this.isUserReadAtt = this.getAttributeDef("IsUserRead");
        }
        return this.isUserReadAtt;
    }

    @objid ("0b245400-a268-4ecd-b8ed-2e7c74df07db")
    public SmAttribute getIsUserWriteAtt() {
        if (this.isUserWriteAtt == null) {
        	this.isUserWriteAtt = this.getAttributeDef("IsUserWrite");
        }
        return this.isUserWriteAtt;
    }

    @objid ("57ca6762-8cc6-4f02-b745-0c8199b0151a")
    public SmAttribute getIsApiReadAtt() {
        if (this.isApiReadAtt == null) {
        	this.isApiReadAtt = this.getAttributeDef("IsApiRead");
        }
        return this.isApiReadAtt;
    }

    @objid ("5abc000c-2d62-4059-adbe-5360531b8a9d")
    public SmAttribute getIsApiWriteAtt() {
        if (this.isApiWriteAtt == null) {
        	this.isApiWriteAtt = this.getAttributeDef("IsApiWrite");
        }
        return this.isApiWriteAtt;
    }

    @objid ("b951ad06-0975-4e4f-8419-58f144febddb")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("55cd0de3-d67c-421d-90d7-67f2e9d54895")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("57032f00-f8ae-4846-99e3-2154ec4c3f48")
    public SmDependency getEnumTypeDep() {
        if (this.enumTypeDep == null) {
        	this.enumTypeDep = this.getDependencyDef("EnumType");
        }
        return this.enumTypeDep;
    }

    @objid ("77d1f397-47d4-426c-a707-e8996b2c6692")
    private static class ModuleParameterObjectFactory implements ISmObjectFactory {
        @objid ("4c278277-fd14-42bd-81c3-74dfc795ea30")
        private ModuleParameterSmClass smClass;

        @objid ("47a3e654-ce29-455d-8601-297ee76344ed")
        public ModuleParameterObjectFactory(ModuleParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ae134deb-d80d-492d-8e74-40d3c326f4e3")
        @Override
        public ISmObjectData createData() {
            return new ModuleParameterData(this.smClass);
        }

        @objid ("c1ad7cad-d4d9-402b-8b33-464679c58092")
        @Override
        public SmObjectImpl createImpl() {
            return new ModuleParameterImpl();
        }

    }

    @objid ("e823d7f5-0a56-4b47-a8b1-ac93c83d35eb")
    public static class GroupNameSmAttribute extends SmAttribute {
        @objid ("539073af-5520-4648-9b91-544b4480d208")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mGroupName;
        }

        @objid ("e6f1f649-cefd-425f-9439-8d7891afa125")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mGroupName = value;
        }

    }

    @objid ("741f3a56-0a6d-4594-ab43-0b0fbb3597ef")
    public static class TypeSmAttribute extends SmAttribute {
        @objid ("fa8f936d-3da0-4dd1-a6be-4517ca5b8fe5")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mType;
        }

        @objid ("2d67a505-e5fc-4fcf-9e1e-40ba7f04cf26")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mType = value;
        }

    }

    @objid ("05bc0644-a2a5-4803-98ab-78adc874fa16")
    public static class IsUserReadSmAttribute extends SmAttribute {
        @objid ("0e2e25be-8223-4075-97b6-68d2e34c378a")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserRead;
        }

        @objid ("de04a652-ec55-40ad-a614-cdc0ee6ddfa9")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserRead = value;
        }

    }

    @objid ("737751c7-6836-456c-bd45-97fda468d965")
    public static class IsUserWriteSmAttribute extends SmAttribute {
        @objid ("ac1276bf-fc34-4b49-9c2b-8262a84c872a")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsUserWrite;
        }

        @objid ("bac230fc-7f62-488b-81d8-b78b293cbf02")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsUserWrite = value;
        }

    }

    @objid ("97a34c69-8835-4896-b20c-c9d6c4c19d24")
    public static class IsApiReadSmAttribute extends SmAttribute {
        @objid ("a38fb8c2-6d80-43ab-bd24-0d4d3498fedc")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiRead;
        }

        @objid ("2254b706-f8a8-4a9a-92b4-d5edc0c17f5f")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiRead = value;
        }

    }

    @objid ("e2c26f19-39dd-4079-a626-e11b88adcb12")
    public static class IsApiWriteSmAttribute extends SmAttribute {
        @objid ("7aede161-9750-44ec-8775-383ad6822373")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mIsApiWrite;
        }

        @objid ("9b1e957c-dfaf-42b3-9d90-7c8d8ba0bfb7")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mIsApiWrite = value;
        }

    }

    @objid ("584167ce-487b-4cbf-95fa-0ad187324913")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("65227a0c-4a3f-45c6-a034-00410de27f5c")
        public Object getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mDefaultValue;
        }

        @objid ("5cc0330d-7fd0-45f6-bea1-80c628f33553")
        public void setValue(ISmObjectData data, Object value) {
            ((ModuleParameterData) data).mDefaultValue = value;
        }

    }

    @objid ("533ba30c-57bf-494c-8128-b27361a28b6e")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("471fe009-9c2b-4d4a-92ce-aa115f2502b2")
        private SmDependency symetricDep;

        @objid ("5b719fa8-65fc-4723-b102-3d3915edd77e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mOwner;
        }

        @objid ("33f52e14-6e17-47ea-b28e-4b8fcf7c9ac6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mOwner = value;
        }

        @objid ("6b1a185f-da6f-4576-867a-dc5f72d47b40")
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
        @objid ("6fb4e3d8-242a-4ea6-96c9-402ad15ce0b1")
        private SmDependency symetricDep;

        @objid ("ce6ed3a6-1b20-4c3e-b79f-065893d2af0a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModuleParameterData) data).mEnumType;
        }

        @objid ("9b0aa203-b6a3-45d3-bb68-85b7f3849975")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModuleParameterData) data).mEnumType = value;
        }

        @objid ("14ec69d5-e061-44d6-9cce-cf1ad7d5644c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getOccurenceConfigParamDep();
            }
            return this.symetricDep;
        }

    }

}
