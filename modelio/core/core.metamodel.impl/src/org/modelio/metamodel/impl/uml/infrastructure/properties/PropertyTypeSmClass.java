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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("3959d3e8-7a71-49d7-90a5-715e119033e0")
public class PropertyTypeSmClass extends ModelElementSmClass {
    @objid ("d8f03120-078b-49e2-b826-d0ab66657a8d")
    private SmAttribute baseTypeAtt;

    @objid ("de0427bc-4963-4dfd-bdaa-d31934c2aa22")
    private SmDependency moduleOwnerDep;

    @objid ("19299f53-c3b2-4db5-b9bc-d1dceba62967")
    private SmDependency typedDep;

    @objid ("eb53d489-e682-4eab-8237-cea16957d81b")
    private SmDependency analystOwnerDep;

    @objid ("db657132-8c36-45c3-a0e5-df16252e2859")
    public  PropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6c07fb06-263e-4658-bceb-51297c7552ea")
    @Override
    public String getName() {
        return "PropertyType";
        
    }

    @objid ("adcf05c7-ab79-430a-9d05-d518c87dab39")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3d9c0859-9534-4ef9-8dd9-ef49dc507646")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyType.class;
        
    }

    @objid ("8a72d5f1-6957-48a1-a096-0b8f2615b595")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("0c837378-0892-4664-a62c-3029f478f130")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7cc24d34-cb80-4550-9d0b-20f03406f360")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new PropertyTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.baseTypeAtt = new BaseTypeSmAttribute();
        this.baseTypeAtt.init("BaseType", this, PropertyBaseType.class );
        registerAttribute(this.baseTypeAtt);
        
        
        // Initialize and register the SmDependency
        this.moduleOwnerDep = new ModuleOwnerSmDependency();
        this.moduleOwnerDep.init("ModuleOwner", this, metamodel.getMClass(ModuleComponent.MQNAME), 1, 1 );
        registerDependency(this.moduleOwnerDep);
        
        this.typedDep = new TypedSmDependency();
        this.typedDep.init("Typed", this, metamodel.getMClass(PropertyDefinition.MQNAME), 0, -1 );
        registerDependency(this.typedDep);
        
        this.analystOwnerDep = new AnalystOwnerSmDependency();
        this.analystOwnerDep.init("AnalystOwner", this, metamodel.getMClass(Profile.MQNAME), 1, 1 );
        registerDependency(this.analystOwnerDep);
        
        
    }

    @objid ("fbc56524-7747-4ee4-b3d7-7bea37419c61")
    public SmAttribute getBaseTypeAtt() {
        if (this.baseTypeAtt == null) {
        	this.baseTypeAtt = this.getAttributeDef("BaseType");
        }
        return this.baseTypeAtt;
    }

    @objid ("02971409-6315-4e69-81ca-adca782d1597")
    public SmDependency getModuleOwnerDep() {
        if (this.moduleOwnerDep == null) {
        	this.moduleOwnerDep = this.getDependencyDef("ModuleOwner");
        }
        return this.moduleOwnerDep;
    }

    @objid ("cf7f678b-e046-490f-8163-a6c368070368")
    public SmDependency getTypedDep() {
        if (this.typedDep == null) {
        	this.typedDep = this.getDependencyDef("Typed");
        }
        return this.typedDep;
    }

    @objid ("b2ac87c6-7131-467c-8485-a32a621108c3")
    public SmDependency getAnalystOwnerDep() {
        if (this.analystOwnerDep == null) {
        	this.analystOwnerDep = this.getDependencyDef("AnalystOwner");
        }
        return this.analystOwnerDep;
    }

    @objid ("6262643b-38d1-4311-b68c-9a45d3cd2f7f")
    private static class PropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("d9b49bbd-f23b-46cf-a96f-3ae75e4a46d6")
        private PropertyTypeSmClass smClass;

        @objid ("aab4c9f2-8ba1-45da-8be4-b457c538d34a")
        public  PropertyTypeObjectFactory(PropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d10b9b98-0359-4ea5-80f0-3dad4ad3b273")
        @Override
        public ISmObjectData createData() {
            return new PropertyTypeData(this.smClass);
        }

        @objid ("41bb8806-7009-4841-87fb-585e907b2af1")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTypeImpl();
        }

    }

    @objid ("3ff1e077-53d6-4672-8149-d5701beaea1e")
    public static class BaseTypeSmAttribute extends SmAttribute {
        @objid ("35108049-dfb9-46cb-970b-7f2873bf1a16")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mBaseType;
        }

        @objid ("da959bde-d51f-4bce-a458-2e401a410628")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTypeData) data).mBaseType = value;
        }

    }

    @objid ("1a1ae5f5-194e-424e-9c98-0cd6ea53367a")
    public static class AnalystOwnerSmDependency extends SmSingleDependency {
        @objid ("2fe5ab56-be18-44b4-b4db-40737c1fe40b")
        private SmDependency symetricDep;

        @objid ("528d4764-d6ad-4671-b040-5cb47d8517f9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mAnalystOwner;
        }

        @objid ("1f91d62c-5b45-42f7-ad1b-82ed6e2e8c05")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mAnalystOwner = value;
        }

        @objid ("03a1a5e0-5720-4dd3-ab6e-8a111a3e8ee0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProfileSmClass)this.getTarget()).getDefinedTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("7ed25def-7fc2-4620-a4ea-5a58092f17dc")
    public static class TypedSmDependency extends SmMultipleDependency {
        @objid ("498ebda0-58d7-4092-9295-40ad33d82dde")
        private SmDependency symetricDep;

        @objid ("088383da-cf94-4d11-926b-ec0efce6feaa")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTypeData)data).mTyped != null)? ((PropertyTypeData)data).mTyped:SmMultipleDependency.EMPTY;
        }

        @objid ("0dac3846-176a-4e4a-916d-3e4983d26272")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTypeData) data).mTyped = values;
            
        }

        @objid ("071758cc-8daf-404c-a1bc-29804fdfa762")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyDefinitionSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("922d6358-a0c5-4a68-8c3b-e27ea4129b29")
    public static class ModuleOwnerSmDependency extends SmSingleDependency {
        @objid ("59d6f797-0512-4a5f-b7c8-66b4456ac9ae")
        private SmDependency symetricDep;

        @objid ("4256a60a-81bb-4b08-a300-b5c6616de318")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mModuleOwner;
        }

        @objid ("017d19bd-03e2-46e9-bfa4-c1710701c4c8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mModuleOwner = value;
        }

        @objid ("a5cb3304-7c7e-4635-bae0-06233fefb4a9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDefinedPropertyTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
