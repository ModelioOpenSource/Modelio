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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("f5240d35-ec20-4f76-8d77-d7b38d9f315f")
    private SmAttribute baseTypeAtt;

    @objid ("b09be98f-b922-48d8-818b-1c36d836de38")
    private SmDependency moduleOwnerDep;

    @objid ("0206faf3-ca6b-418b-8bac-3858431bef75")
    private SmDependency typedDep;

    @objid ("5133661d-4276-46d2-8dda-bc0c5f95bb9a")
    private SmDependency analystOwnerDep;

    @objid ("8f547a95-35b9-4783-bfe0-15c160ed37fc")
    public PropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3fd92005-13f6-4e0d-874a-11e05c81b11e")
    @Override
    public String getName() {
        return "PropertyType";
    }

    @objid ("374409bf-37d7-483e-9ee7-86bcd3dd8fb2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c3b0c6b7-282d-48c3-8297-9de5c9f17c51")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyType.class;
    }

    @objid ("a6d745d3-895a-4d56-b9be-1ef44a02e245")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("7faa42cc-d0ed-4a32-b834-177f168b6a51")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("fa87cd72-52e4-4bce-a2b1-ee89a1fb51a8")
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

    @objid ("e2eb6773-e8ae-49ec-a0cd-c7a6b4fce628")
    public SmAttribute getBaseTypeAtt() {
        if (this.baseTypeAtt == null) {
        	this.baseTypeAtt = this.getAttributeDef("BaseType");
        }
        return this.baseTypeAtt;
    }

    @objid ("8e3286e3-4853-437c-b2af-420378acb630")
    public SmDependency getModuleOwnerDep() {
        if (this.moduleOwnerDep == null) {
        	this.moduleOwnerDep = this.getDependencyDef("ModuleOwner");
        }
        return this.moduleOwnerDep;
    }

    @objid ("6b7e5afb-3a2c-498c-bb67-50837f65149d")
    public SmDependency getTypedDep() {
        if (this.typedDep == null) {
        	this.typedDep = this.getDependencyDef("Typed");
        }
        return this.typedDep;
    }

    @objid ("249fc76e-dc6a-4815-b988-63080c207dd0")
    public SmDependency getAnalystOwnerDep() {
        if (this.analystOwnerDep == null) {
        	this.analystOwnerDep = this.getDependencyDef("AnalystOwner");
        }
        return this.analystOwnerDep;
    }

    @objid ("6262643b-38d1-4311-b68c-9a45d3cd2f7f")
    private static class PropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("68320ae3-c50a-42df-aab5-1cc8047c655f")
        private PropertyTypeSmClass smClass;

        @objid ("8e592f07-9a25-44db-8b4c-0590707709d8")
        public PropertyTypeObjectFactory(PropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2335817c-6399-415a-9834-56bad6365102")
        @Override
        public ISmObjectData createData() {
            return new PropertyTypeData(this.smClass);
        }

        @objid ("99b63a6d-78a2-454c-8eae-eb200c4ae1e2")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyTypeImpl();
        }

    }

    @objid ("3ff1e077-53d6-4672-8149-d5701beaea1e")
    public static class BaseTypeSmAttribute extends SmAttribute {
        @objid ("7fd45602-4a05-40bb-a617-6c97313c894a")
        public Object getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mBaseType;
        }

        @objid ("24ffda57-2e84-4bc7-af44-f2311a1994b2")
        public void setValue(ISmObjectData data, Object value) {
            ((PropertyTypeData) data).mBaseType = value;
        }

    }

    @objid ("1a1ae5f5-194e-424e-9c98-0cd6ea53367a")
    public static class AnalystOwnerSmDependency extends SmSingleDependency {
        @objid ("f265f049-825f-4db0-8316-30b8e29d4b9f")
        private SmDependency symetricDep;

        @objid ("52013dc0-0a4f-48cb-9b53-7eab37ecc47a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mAnalystOwner;
        }

        @objid ("cd1068b4-4df2-4b9a-b639-4ff392e96dde")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mAnalystOwner = value;
        }

        @objid ("77b0c3fe-65d9-46f6-b9aa-edb0d21ac686")
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
        @objid ("999d5681-9516-4f63-a571-a87f819074db")
        private SmDependency symetricDep;

        @objid ("0fccccec-ed6b-444e-a0ba-e4ac20ffd399")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PropertyTypeData)data).mTyped != null)? ((PropertyTypeData)data).mTyped:SmMultipleDependency.EMPTY;
        }

        @objid ("3c2ddd0c-b97f-4169-ac51-88cf1f320e54")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PropertyTypeData) data).mTyped = values;
        }

        @objid ("be7da4f4-1f46-4dda-8d36-806d2be4f606")
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
        @objid ("435c2991-f72a-4752-8253-afbb4b8c8d24")
        private SmDependency symetricDep;

        @objid ("a8b11896-6758-42f2-bd88-37473f7a8ce6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyTypeData) data).mModuleOwner;
        }

        @objid ("cbebeb87-f535-49bf-95e1-c7d70401a754")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyTypeData) data).mModuleOwner = value;
        }

        @objid ("de6f2683-8c3d-4eca-8a69-51f939c7827d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleComponentSmClass)this.getTarget()).getDefinedPropertyTypeDep();
            }
            return this.symetricDep;
        }

    }

}
