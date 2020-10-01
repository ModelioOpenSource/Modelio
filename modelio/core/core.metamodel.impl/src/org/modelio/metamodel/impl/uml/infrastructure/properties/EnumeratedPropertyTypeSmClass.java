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
import org.modelio.metamodel.impl.mda.ModuleParameterSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.EnumeratedPropertyTypeData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyEnumerationLitteralSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeSmClass;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("505b6382-7d7f-4d20-85a0-d4366a65deb6")
public class EnumeratedPropertyTypeSmClass extends PropertyTypeSmClass {
    @objid ("fd0593a7-a10f-4783-83a8-c453351d8e6e")
    private SmDependency litteralDep;

    @objid ("c073d359-fa15-41f6-b78a-5a73b414bd5f")
    private SmDependency occurenceConfigParamDep;

    @objid ("0c888a7b-cf9d-4b47-9991-1f3516afb468")
    public EnumeratedPropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("83af6af2-6c19-4a98-9360-e983f53c6cfa")
    @Override
    public String getName() {
        return "EnumeratedPropertyType";
    }

    @objid ("49c7aa25-937a-4067-b9dc-d4f979600a83")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7f30b94a-744d-4d18-bd19-badeac140380")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return EnumeratedPropertyType.class;
    }

    @objid ("ce27ccd6-aa7e-48f2-a97e-34b2fc844f0e")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("c80aa755-736d-4cf0-b05a-52482cf41090")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("64570944-e7cd-463e-8af4-9e8f4d018898")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyType.MQNAME);
        this.registerFactory(new EnumeratedPropertyTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.litteralDep = new LitteralSmDependency();
        this.litteralDep.init("Litteral", this, metamodel.getMClass(PropertyEnumerationLitteral.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.litteralDep);
        
        this.occurenceConfigParamDep = new OccurenceConfigParamSmDependency();
        this.occurenceConfigParamDep.init("OccurenceConfigParam", this, metamodel.getMClass(ModuleParameter.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.occurenceConfigParamDep);
    }

    @objid ("04464ca3-0ad1-4888-9b89-519e01af0130")
    public SmDependency getLitteralDep() {
        if (this.litteralDep == null) {
        	this.litteralDep = this.getDependencyDef("Litteral");
        }
        return this.litteralDep;
    }

    @objid ("4ab71e6b-7b68-493e-a765-b0b668ded603")
    public SmDependency getOccurenceConfigParamDep() {
        if (this.occurenceConfigParamDep == null) {
        	this.occurenceConfigParamDep = this.getDependencyDef("OccurenceConfigParam");
        }
        return this.occurenceConfigParamDep;
    }

    @objid ("54966067-f9b7-48fd-8f15-663cfcfd5e32")
    private static class EnumeratedPropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("18b09122-8b27-4ca4-a4b7-51d568a8f1b2")
        private EnumeratedPropertyTypeSmClass smClass;

        @objid ("4d23608f-a70c-4f8a-9b55-008ef70dc427")
        public EnumeratedPropertyTypeObjectFactory(EnumeratedPropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("25e38cb0-4a0f-442d-9aff-d5bd9bb65c0c")
        @Override
        public ISmObjectData createData() {
            return new EnumeratedPropertyTypeData(this.smClass);
        }

        @objid ("dccf591f-da14-4a26-8c17-becfb575dbc4")
        @Override
        public SmObjectImpl createImpl() {
            return new EnumeratedPropertyTypeImpl();
        }

    }

    @objid ("35fb40a3-22c8-4af2-8671-3f6a020c2e74")
    public static class LitteralSmDependency extends SmMultipleDependency {
        @objid ("83e35fa7-7c9e-4ddb-8c82-afed429b7dd8")
        private SmDependency symetricDep;

        @objid ("81968728-5a6e-4dfa-9e16-16ef4d930c14")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mLitteral != null)? ((EnumeratedPropertyTypeData)data).mLitteral:SmMultipleDependency.EMPTY;
        }

        @objid ("029ac347-546a-4d5c-870d-e7165dbba552")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mLitteral = values;
        }

        @objid ("8359fc37-ee18-4dc0-83fb-eef520164f8e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyEnumerationLitteralSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b4639c50-da58-48be-80d8-0f7f1435c7ae")
    public static class OccurenceConfigParamSmDependency extends SmMultipleDependency {
        @objid ("2a9490d7-2669-42ad-8f46-46f4c515296c")
        private SmDependency symetricDep;

        @objid ("2190128c-27ce-45f7-b75f-4b13350ca9cb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mOccurenceConfigParam != null)? ((EnumeratedPropertyTypeData)data).mOccurenceConfigParam:SmMultipleDependency.EMPTY;
        }

        @objid ("be307568-60d2-4c49-acca-d3b3f7960506")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mOccurenceConfigParam = values;
        }

        @objid ("3d03ac09-2ad7-49af-8268-a30bb8b842bb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleParameterSmClass)this.getTarget()).getEnumTypeDep();
            }
            return this.symetricDep;
        }

    }

}
