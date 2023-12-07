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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("248ccc62-bd28-427d-bfd3-d3abff00fd40")
public class PropertyEnumerationLitteralSmClass extends ModelElementSmClass {
    @objid ("460d19b3-8842-43af-a0ae-263992433020")
    private SmDependency ownerDep;

    @objid ("88131402-dc35-48d1-ada2-201e747b1a6a")
    public  PropertyEnumerationLitteralSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("90e0233c-6c81-423a-97f6-7be5ae92b79e")
    @Override
    public String getName() {
        return "PropertyEnumerationLitteral";
        
    }

    @objid ("25122f06-873b-419b-b9a7-e2b95c48c7e2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bd2b436d-8b13-4b9c-857d-813feef64326")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyEnumerationLitteral.class;
        
    }

    @objid ("28dd1a66-459d-4ffa-bf33-951bea86c9b3")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("245a9dd9-a37d-4d1f-ab75-1d4ad34f4648")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("86e86229-5384-4ebb-a9fa-37c11c9f5243")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new PropertyEnumerationLitteralObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(EnumeratedPropertyType.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("b81abefe-0733-432a-8d99-5d02edb328ce")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("16973606-bfc2-4299-b464-a03207b835b2")
    private static class PropertyEnumerationLitteralObjectFactory implements ISmObjectFactory {
        @objid ("42fa3376-1057-4ed5-941b-e70e8088afd3")
        private PropertyEnumerationLitteralSmClass smClass;

        @objid ("fb974564-747c-4411-8d15-c41cd4ea4bed")
        public  PropertyEnumerationLitteralObjectFactory(PropertyEnumerationLitteralSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0453767a-cfea-48b8-928f-0c6b004f2668")
        @Override
        public ISmObjectData createData() {
            return new PropertyEnumerationLitteralData(this.smClass);
        }

        @objid ("34c7479c-801f-4c34-a5a2-dc2c774dff40")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyEnumerationLitteralImpl();
        }

    }

    @objid ("39c7e8eb-8efc-4a8f-989b-105df82d442a")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("039d6cec-f5f3-4d18-a3cc-97661460a22d")
        private SmDependency symetricDep;

        @objid ("8ed3e43d-be2e-4dce-b790-8bffff448e40")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyEnumerationLitteralData) data).mOwner;
        }

        @objid ("338b8c10-a6d9-4224-a7ba-8f289f840374")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyEnumerationLitteralData) data).mOwner = value;
        }

        @objid ("21d19c6a-4831-4dd9-b281-593c19e092a6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getLitteralDep();
            }
            return this.symetricDep;
            
        }

    }

}
