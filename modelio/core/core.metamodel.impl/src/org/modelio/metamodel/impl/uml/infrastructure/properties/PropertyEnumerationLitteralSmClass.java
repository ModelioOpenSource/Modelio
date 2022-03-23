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
    @objid ("6c2df247-3662-445c-abfe-992d1959613a")
    private SmDependency ownerDep;

    @objid ("e3949bf8-2420-498c-9fdc-5f902ae01392")
    public  PropertyEnumerationLitteralSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ed889f1e-f75a-4ae0-aaee-e352d1e98c1e")
    @Override
    public String getName() {
        return "PropertyEnumerationLitteral";
        
    }

    @objid ("9ab8b4d9-c454-43fd-858e-f10186fb680b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("80aa0191-88de-4870-b2b8-1e571d2b8010")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PropertyEnumerationLitteral.class;
        
    }

    @objid ("c1026bb1-33b8-4f40-af0a-5879cd66bf30")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("1f684b30-bc5b-478f-b88f-2165dd47f734")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("4d28a34e-779a-4e74-9a81-1072d8713b7a")
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

    @objid ("c333513f-b6b5-4050-bbc4-f80444264afc")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("16973606-bfc2-4299-b464-a03207b835b2")
    private static class PropertyEnumerationLitteralObjectFactory implements ISmObjectFactory {
        @objid ("51aa052e-7357-48e2-91a7-c5e50a395be0")
        private PropertyEnumerationLitteralSmClass smClass;

        @objid ("cd862295-2340-4fcd-96a4-c2a4aa60a348")
        public  PropertyEnumerationLitteralObjectFactory(PropertyEnumerationLitteralSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b195e5fc-f5de-45de-9c08-004bbdd3c5cf")
        @Override
        public ISmObjectData createData() {
            return new PropertyEnumerationLitteralData(this.smClass);
        }

        @objid ("c2143c38-008a-4595-a152-b2a020013159")
        @Override
        public SmObjectImpl createImpl() {
            return new PropertyEnumerationLitteralImpl();
        }

    }

    @objid ("39c7e8eb-8efc-4a8f-989b-105df82d442a")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("9ce19fcf-d717-40ee-935e-3c77af11ba65")
        private SmDependency symetricDep;

        @objid ("4f201ed3-0865-44a7-a832-5ae77677f07e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PropertyEnumerationLitteralData) data).mOwner;
        }

        @objid ("530fc7af-1abb-4daf-99ec-9326ef4c1ed8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PropertyEnumerationLitteralData) data).mOwner = value;
        }

        @objid ("fe9d8afc-1e41-4435-8589-61273ee21fd2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumeratedPropertyTypeSmClass)this.getTarget()).getLitteralDep();
            }
            return this.symetricDep;
            
        }

    }

}
