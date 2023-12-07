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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
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

@objid ("864b6926-ae8d-4a3b-a96e-053b5bb7f2e3")
public class TypedPropertyTableSmClass extends PropertyTableSmClass {
    @objid ("ac2af07e-78c4-4911-bb0e-145601617f4a")
    private SmDependency typeDep;

    @objid ("4e36b137-b0d4-484a-a543-871b56c28448")
    public  TypedPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cf534e5e-be77-4dff-889b-735cd46165e8")
    @Override
    public String getName() {
        return "TypedPropertyTable";
        
    }

    @objid ("4947598b-832f-4820-9c49-8af5c38368e1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("77be7da2-4bcb-4748-b41f-27068617acfb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TypedPropertyTable.class;
        
    }

    @objid ("8192725a-e47d-4696-8b34-95879b582681")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("a5edd7b7-2a2d-4c74-8d78-59a5ef6b4e39")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("9e4df250-d434-42be-8b9d-239603bbbe5c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyTable.MQNAME);
        this.registerFactory(new TypedPropertyTableObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(PropertyTableDefinition.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        
    }

    @objid ("65868714-b035-4ace-beda-f7a08f803277")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("9a7ca71a-e2e6-4e5e-8bae-40417229a641")
    private static class TypedPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("e5a2bc43-8e33-460a-bd5d-f9929b06ebb0")
        private TypedPropertyTableSmClass smClass;

        @objid ("6027065e-7655-45c7-84a6-cdbafe053e33")
        public  TypedPropertyTableObjectFactory(TypedPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("330739f4-912f-4906-b4ad-cf3cecd7b208")
        @Override
        public ISmObjectData createData() {
            return new TypedPropertyTableData(this.smClass);
        }

        @objid ("b1932d77-8597-4b6b-af46-53d49fe9ec75")
        @Override
        public SmObjectImpl createImpl() {
            return new TypedPropertyTableImpl();
        }

    }

    @objid ("516a7476-698a-4b70-a9a3-e57cc2e9b20b")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("cc4f4512-72e0-4372-bf5b-7c49fde37901")
        private SmDependency symetricDep;

        @objid ("2d060c64-3688-4ebd-a408-3e669f78dad0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TypedPropertyTableData) data).mType;
        }

        @objid ("7e33e5d9-c3cf-4066-b7ed-80b391989bbc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TypedPropertyTableData) data).mType = value;
        }

        @objid ("5416dabd-39c8-4caf-bfae-8edf8f6b3758")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getTypedTableDep();
            }
            return this.symetricDep;
            
        }

    }

}
