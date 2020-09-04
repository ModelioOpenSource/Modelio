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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeData;
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
    @objid ("4cf9a62d-6808-46ef-9773-b5736f36df8b")
    private SmAttribute baseTypeAtt;

    @objid ("6d963bf2-4216-4d5b-aba5-39d11d660188")
    private SmDependency moduleOwnerDep;

    @objid ("0a38d665-9eeb-4095-8aa4-ee510e247234")
    private SmDependency typedDep;

    @objid ("fddd2bab-e467-416c-aede-6e6ba2593084")
    private SmDependency analystOwnerDep;

    @objid ("f873709f-91f7-48e0-81e0-89fda298bcd5")
    public PropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c04b321c-433a-44db-9b45-e8dfd588233c")
    @Override
    public String getName() {
        return "PropertyType";
    }

    @objid ("a892bdd0-f772-4d99-aa7f-56e5e9c9ec9a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b01e75bf-23d5-4003-a196-6d89fe4f0b15")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyType.class;
    }

    @objid ("b0cb9160-042c-4d5e-8132-ce561cf030d9")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("def98431-2fe3-43de-8993-b7ef784fe370")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ae5b94a7-a269-43c2-8a10-6d266d99c574")
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

    @objid ("15a5e475-f417-472b-a153-9aef7d6b1f9a")
    public SmAttribute getBaseTypeAtt() {
        if (this.baseTypeAtt == null) {
        	this.baseTypeAtt = this.getAttributeDef("BaseType");
        }
        return this.baseTypeAtt;
    }

    @objid ("de3539a0-ad3e-48fd-984e-8baa4b8fc777")
    public SmDependency getModuleOwnerDep() {
        if (this.moduleOwnerDep == null) {
        	this.moduleOwnerDep = this.getDependencyDef("ModuleOwner");
        }
        return this.moduleOwnerDep;
    }

    @objid ("8b5702a4-7756-4f3e-9438-c43d755ebe3f")
    public SmDependency getTypedDep() {
        if (this.typedDep == null) {
        	this.typedDep = this.getDependencyDef("Typed");
        }
        return this.typedDep;
    }

    @objid ("7fcbb026-93d5-4ae5-9492-34a9cd01cb87")
    public SmDependency getAnalystOwnerDep() {
        if (this.analystOwnerDep == null) {
        	this.analystOwnerDep = this.getDependencyDef("AnalystOwner");
        }
        return this.analystOwnerDep;
    }

    @objid ("6262643b-38d1-4311-b68c-9a45d3cd2f7f")
    private static class PropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("81df5793-f068-49ce-a6cf-914812ac0bb8")
        private PropertyTypeSmClass smClass;

        @objid ("f90a310a-5e38-407b-a14e-cba2d012ab28")
        public PropertyTypeObjectFactory(PropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("56662eba-aa5a-4c11-9348-f45e9a6a5af1")
        @Override
        public ISmObjectData createData() {
            return new PropertyTypeData(this.smClass);
        }

        @objid ("9982aebc-c21a-46cd-af09-9334745d229a")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTypeImpl();
        }

    }

    @objid ("3ff1e077-53d6-4672-8149-d5701beaea1e")
    public static class BaseTypeSmAttribute extends SmAttribute {
        @objid ("566d76db-73cd-4840-a14a-c6f93300b28a")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mBaseType;
        }

        @objid ("d62c6e4b-3e1d-4062-8bf2-5015f4200cfc")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTypeData) data).mBaseType = value;
        }

    }

    @objid ("1a1ae5f5-194e-424e-9c98-0cd6ea53367a")
    public static class AnalystOwnerSmDependency extends SmSingleDependency {
        @objid ("cd9bae68-414d-4694-88b0-e5a8c4487baf")
        private SmDependency symetricDep;

        @objid ("99003f08-3dfd-42d9-a33e-0845bb712c68")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mAnalystOwner;
        }

        @objid ("ed3fab1e-49ca-4caf-a18a-89f3e5d0a112")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mAnalystOwner = value;
        }

        @objid ("dbc9f35b-3770-4f4d-863b-87dee1add00e")
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
        @objid ("ca42607b-885b-4ebb-8b8c-f0c39ccbb234")
        private SmDependency symetricDep;

        @objid ("0012acfc-5f27-4c9b-b473-c586a7f80f7c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTypeData)data).mTyped != null)? ((PropertyTypeData)data).mTyped:SmMultipleDependency.EMPTY;
        }

        @objid ("cc9b1d2d-4413-4d8b-b91d-201f154a18ec")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTypeData) data).mTyped = values;
        }

        @objid ("8ddc694f-50b7-44cc-ab80-f4e0aefe7fc9")
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
        @objid ("66bf673a-41a9-40bb-a3b6-e4a16eb8a676")
        private SmDependency symetricDep;

        @objid ("3c419baf-43e8-469f-9e57-0939ec0edb29")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mModuleOwner;
        }

        @objid ("4cfb38a7-3426-437e-97e9-e66aebb3c685")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mModuleOwner = value;
        }

        @objid ("5461c029-413e-439e-86f4-c2caa7e1b6f3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDefinedPropertyTypeDep();
            }
            return this.symetricDep;
        }

    }

}
