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
import org.modelio.metamodel.impl.uml.infrastructure.properties.LocalPropertyTableData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
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

@objid ("90df93f3-3d77-459d-8522-3bcaa78554db")
public class LocalPropertyTableSmClass extends PropertyTableSmClass {
    @objid ("e25d2893-6126-43fc-b89f-84f30d9d3c30")
    private SmDependency localAnnotedDep;

    @objid ("1462cca4-6b76-454a-adc2-d54791210bd2")
    public LocalPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2c5dc72a-3f61-4dd8-8318-fdc83f0d42a3")
    @Override
    public String getName() {
        return "LocalPropertyTable";
    }

    @objid ("5a589dfb-5917-41e2-a012-37abcba9ebca")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a8876db9-8756-45f3-be26-006176632e14")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return LocalPropertyTable.class;
    }

    @objid ("0c333bb1-849a-4e0d-8cf6-14f5a83c7378")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("82d5ec2f-80dd-491b-b61e-d32d40f81716")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3d1c1429-b98f-4189-84bb-b749827a8ea8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyTable.MQNAME);
        this.registerFactory(new LocalPropertyTableObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.localAnnotedDep = new LocalAnnotedSmDependency();
        this.localAnnotedDep.init("LocalAnnoted", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.localAnnotedDep);
    }

    @objid ("26d5ddcf-0db4-4533-9076-d4fc19096a32")
    public SmDependency getLocalAnnotedDep() {
        if (this.localAnnotedDep == null) {
        	this.localAnnotedDep = this.getDependencyDef("LocalAnnoted");
        }
        return this.localAnnotedDep;
    }

    @objid ("a5b8f3a2-db5f-4ba7-816d-949f0ccd7159")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("bd28cc78-75ed-47ad-87c8-8daa92ad49ee")
    private static class LocalPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("d6f1ca14-44d5-46ac-b29f-999c3ad65bb7")
        private LocalPropertyTableSmClass smClass;

        @objid ("2b0276c2-8a21-47fc-8457-235fd608535d")
        public LocalPropertyTableObjectFactory(LocalPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4ad9b423-4f68-43ce-854b-ebbf8bed3919")
        @Override
        public ISmObjectData createData() {
            return new LocalPropertyTableData(this.smClass);
        }

        @objid ("ba56d135-b14a-45a3-95d4-1597e68c89ac")
        @Override
        public SmObjectImpl createImpl() {
            return new LocalPropertyTableImpl();
        }

    }

    @objid ("0d5ff448-7ff2-49a0-b5c7-979a9f7c07a1")
    public static class LocalAnnotedSmDependency extends SmSingleDependency {
        @objid ("f6fcb803-9ae1-4eaa-84b5-a6d3b81ae9cc")
        private SmDependency symetricDep;

        @objid ("d07d0c03-0428-4d98-bdf9-2acf8691f95f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LocalPropertyTableData) data).mLocalAnnoted;
        }

        @objid ("2037363e-4891-4aee-a6f1-d80f7025655a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LocalPropertyTableData) data).mLocalAnnoted = value;
        }

        @objid ("fe362d9c-cfa1-4710-819c-a1a814a8ead1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getLocalPropertiesDep();
            }
            return this.symetricDep;
        }

    }

}
