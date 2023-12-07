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
import org.modelio.metamodel.impl.mda.ModuleParameterSmClass;
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
    @objid ("ff49a43a-dfa5-452c-b586-3e328d3cec02")
    private SmDependency litteralDep;

    @objid ("c166ab18-6094-4153-a82a-fa14a8620d53")
    private SmDependency occurenceConfigParamDep;

    @objid ("3897a2f0-0656-45cd-a39b-81d933dca210")
    public  EnumeratedPropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8e8bfd9b-349d-4fc8-9b84-e0097ff4accf")
    @Override
    public String getName() {
        return "EnumeratedPropertyType";
        
    }

    @objid ("ae98a07c-4305-4f5b-8570-47046001cc60")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("5f780339-fe7d-49d8-8d67-73a0c0b678e9")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return EnumeratedPropertyType.class;
        
    }

    @objid ("c5eaad23-3d04-4df6-b6f9-668cd99e9c18")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("667c6c85-bc45-4796-83cc-16df9bd20884")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7ba101e5-2e7c-4a9c-9939-05cea30bc399")
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

    @objid ("a7857d03-3867-4980-b19d-983ff6a777ca")
    public SmDependency getLitteralDep() {
        if (this.litteralDep == null) {
        	this.litteralDep = this.getDependencyDef("Litteral");
        }
        return this.litteralDep;
    }

    @objid ("01175fbc-a51b-4f2c-a0e4-5d4f4b6e32e3")
    public SmDependency getOccurenceConfigParamDep() {
        if (this.occurenceConfigParamDep == null) {
        	this.occurenceConfigParamDep = this.getDependencyDef("OccurenceConfigParam");
        }
        return this.occurenceConfigParamDep;
    }

    @objid ("54966067-f9b7-48fd-8f15-663cfcfd5e32")
    private static class EnumeratedPropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("5addc755-082d-4f5a-b935-03e2e536fc32")
        private EnumeratedPropertyTypeSmClass smClass;

        @objid ("81de9bff-b062-43e4-ac96-4ec877a7a825")
        public  EnumeratedPropertyTypeObjectFactory(EnumeratedPropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("70a30151-788f-4e4e-b954-dd0b343ba0f9")
        @Override
        public ISmObjectData createData() {
            return new EnumeratedPropertyTypeData(this.smClass);
        }

        @objid ("51f29492-2a73-4a22-baf2-efc84c8e0d09")
        @Override
        public SmObjectImpl createImpl() {
            return new EnumeratedPropertyTypeImpl();
        }

    }

    @objid ("35fb40a3-22c8-4af2-8671-3f6a020c2e74")
    public static class LitteralSmDependency extends SmMultipleDependency {
        @objid ("7a643247-c8c1-4746-85ee-e66af4a34ce4")
        private SmDependency symetricDep;

        @objid ("d16b7251-edeb-438f-b950-3232243d7b3a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mLitteral != null)? ((EnumeratedPropertyTypeData)data).mLitteral:SmMultipleDependency.EMPTY;
        }

        @objid ("ad90e0bf-497f-4ffa-9129-da8b79f0b8f5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mLitteral = values;
            
        }

        @objid ("67b619a4-7ac9-4811-b756-1eea09d852c3")
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
        @objid ("f50d4dd8-fb4a-4c59-9581-8152327768fb")
        private SmDependency symetricDep;

        @objid ("bde6890a-59ec-425a-9dbd-8075bac2498a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mOccurenceConfigParam != null)? ((EnumeratedPropertyTypeData)data).mOccurenceConfigParam:SmMultipleDependency.EMPTY;
        }

        @objid ("44597164-1084-4400-980d-53c6f6364555")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mOccurenceConfigParam = values;
            
        }

        @objid ("b2f23cb2-6acb-42ef-8454-9dcd5d90c641")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleParameterSmClass)this.getTarget()).getEnumTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
