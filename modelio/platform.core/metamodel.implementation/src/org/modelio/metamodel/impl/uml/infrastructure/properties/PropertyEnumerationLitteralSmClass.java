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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.EnumeratedPropertyTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyEnumerationLitteralData;
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
    @objid ("6f7388ce-ece5-402a-bc9a-6af3a8a067fa")
    private SmDependency ownerDep;

    @objid ("7909c071-42ac-40d5-bf16-c0b0ec82abee")
    public PropertyEnumerationLitteralSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("507f925c-2b01-4758-9fd4-6f8b9e0c017a")
    @Override
    public String getName() {
        return "PropertyEnumerationLitteral";
    }

    @objid ("ae197f6d-4188-4acd-ba87-b397be77672d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("830e2969-844d-4d08-a601-971c640c976a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyEnumerationLitteral.class;
    }

    @objid ("bc19515d-f07a-4a9a-9581-8fff6c2d8561")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f5c93362-adc6-4146-9a20-160dd4c0be9c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0cbe8379-9b56-451e-92f7-9337be467bd0")
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

    @objid ("9b8e6859-0e2c-4d8e-a1ed-dd7cccceed3e")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("16973606-bfc2-4299-b464-a03207b835b2")
    private static class PropertyEnumerationLitteralObjectFactory implements ISmObjectFactory {
        @objid ("981673cd-b215-4c50-9e76-c667005b1ec1")
        private PropertyEnumerationLitteralSmClass smClass;

        @objid ("16b03d29-fc64-4442-b8a2-11aba1aca2cc")
        public PropertyEnumerationLitteralObjectFactory(PropertyEnumerationLitteralSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ae68e970-37f1-4738-9485-604ef3773452")
        @Override
        public ISmObjectData createData() {
            return new PropertyEnumerationLitteralData(this.smClass);
        }

        @objid ("5a5e97e5-fad8-4d0e-adba-ccf4af294090")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyEnumerationLitteralImpl();
        }

    }

    @objid ("39c7e8eb-8efc-4a8f-989b-105df82d442a")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("d52bdb12-2d30-4f5a-a8b2-a53359f1d1a8")
        private SmDependency symetricDep;

        @objid ("6002a449-c68d-4971-8acb-2c3b093bc7dc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyEnumerationLitteralData) data).mOwner;
        }

        @objid ("9fa3e3c8-b186-4eb0-b02e-7b2c938509a4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyEnumerationLitteralData) data).mOwner = value;
        }

        @objid ("1a3fec0d-1a1e-4f68-aff5-26c9bde88d54")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getLitteralDep();
            }
            return this.symetricDep;
        }

    }

}
