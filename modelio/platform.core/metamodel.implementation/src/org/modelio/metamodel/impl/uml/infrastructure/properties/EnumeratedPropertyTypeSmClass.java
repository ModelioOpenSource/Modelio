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
    @objid ("19b869bb-ff25-471c-bf78-8bf71411dcc8")
    private SmDependency litteralDep;

    @objid ("a8dacd47-eeb6-4215-a652-72fa2fc2c81e")
    private SmDependency occurenceConfigParamDep;

    @objid ("11c4767c-147d-4979-9026-04d4236e18dc")
    public EnumeratedPropertyTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6a5206a3-4c94-4287-8ad2-1ea72d78f602")
    @Override
    public String getName() {
        return "EnumeratedPropertyType";
    }

    @objid ("5b337557-a8b0-4850-8f22-02e0870d18d1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c19546f2-83a4-462f-9c1b-2f6a97334fad")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return EnumeratedPropertyType.class;
    }

    @objid ("a41b96c3-53df-49e0-8708-408163b77cc6")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("73ade4fe-b6ae-4a0a-89c9-ff3dbf142a6d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f41fc592-38ac-4ec2-8c7b-64fefa71d0b3")
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

    @objid ("46d1f498-24ab-4cc0-ae29-9bfe306c7b9c")
    public SmDependency getLitteralDep() {
        if (this.litteralDep == null) {
        	this.litteralDep = this.getDependencyDef("Litteral");
        }
        return this.litteralDep;
    }

    @objid ("07d820ad-cf87-4ac2-b241-27f867171df4")
    public SmDependency getOccurenceConfigParamDep() {
        if (this.occurenceConfigParamDep == null) {
        	this.occurenceConfigParamDep = this.getDependencyDef("OccurenceConfigParam");
        }
        return this.occurenceConfigParamDep;
    }

    @objid ("54966067-f9b7-48fd-8f15-663cfcfd5e32")
    private static class EnumeratedPropertyTypeObjectFactory implements ISmObjectFactory {
        @objid ("5224c37b-380f-4d2e-a39d-f96270507651")
        private EnumeratedPropertyTypeSmClass smClass;

        @objid ("ad519634-e391-4ef9-9a81-55a7822d8b78")
        public EnumeratedPropertyTypeObjectFactory(EnumeratedPropertyTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e596bd2a-5e3a-48ff-9489-a21ee48f7275")
        @Override
        public ISmObjectData createData() {
            return new EnumeratedPropertyTypeData(this.smClass);
        }

        @objid ("3162f294-da3e-423c-aa4a-217baa1eea63")
        @Override
        public SmObjectImpl createImpl() {
            return new EnumeratedPropertyTypeImpl();
        }

    }

    @objid ("35fb40a3-22c8-4af2-8671-3f6a020c2e74")
    public static class LitteralSmDependency extends SmMultipleDependency {
        @objid ("72eac86f-552b-4dce-8fa9-745e64a209b9")
        private SmDependency symetricDep;

        @objid ("16a9a8a9-8363-4ad8-8d34-806637ef1117")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mLitteral != null)? ((EnumeratedPropertyTypeData)data).mLitteral:SmMultipleDependency.EMPTY;
        }

        @objid ("c05b0fe2-221e-4027-bb89-30377afc8626")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mLitteral = values;
        }

        @objid ("dc700b6e-d062-4279-bb84-71d9bf743346")
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
        @objid ("163cde19-5cfb-4cdf-9680-565c45ca7dd7")
        private SmDependency symetricDep;

        @objid ("9d04e313-b912-4fcd-a41d-443b7a8cc51a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumeratedPropertyTypeData)data).mOccurenceConfigParam != null)? ((EnumeratedPropertyTypeData)data).mOccurenceConfigParam:SmMultipleDependency.EMPTY;
        }

        @objid ("c409b432-14f1-4ecc-8010-afe96e9023c0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumeratedPropertyTypeData) data).mOccurenceConfigParam = values;
        }

        @objid ("3d6da942-595e-4c02-8722-5a00acf4f508")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModuleParameterSmClass)this.getTarget()).getEnumTypeDep();
            }
            return this.symetricDep;
        }

    }

}
