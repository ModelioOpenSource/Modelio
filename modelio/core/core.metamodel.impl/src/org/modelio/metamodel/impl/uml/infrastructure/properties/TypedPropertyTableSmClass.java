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
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.TypedPropertyTableData;
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
    @objid ("44caa03e-0af3-446f-b23a-0963d038de51")
    private SmDependency typeDep;

    @objid ("239c80f9-46bc-471a-ba6d-5876a5877a0a")
    public TypedPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e5d2a7c0-e55f-4bc4-a3a4-278b1a68e9ef")
    @Override
    public String getName() {
        return "TypedPropertyTable";
    }

    @objid ("d7ff9a06-f003-462b-a499-5267c67fe793")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7c213e6d-a5e2-44d6-a3a8-939d70540361")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TypedPropertyTable.class;
    }

    @objid ("f825da4c-76e0-4100-b3f7-75f82470565d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("331fb22c-0e30-4944-8386-a2b65cd2e43f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8c9ff23a-0b0b-45eb-8c5e-e2d5b7f8a2e2")
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

    @objid ("4320b742-fc1f-4061-a8da-0f3e643e6ca6")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("9a7ca71a-e2e6-4e5e-8bae-40417229a641")
    private static class TypedPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("d23b5d03-6c0b-47a5-a807-1b180b018182")
        private TypedPropertyTableSmClass smClass;

        @objid ("3f4228d9-cbd8-4d06-8750-1156528c35cf")
        public TypedPropertyTableObjectFactory(TypedPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("da2d5590-c185-4ba4-8d2c-02e95344ef0c")
        @Override
        public ISmObjectData createData() {
            return new TypedPropertyTableData(this.smClass);
        }

        @objid ("d5c20854-3e47-4edb-8442-78ef6cf5c7b7")
        @Override
        public SmObjectImpl createImpl() {
            return new TypedPropertyTableImpl();
        }

    }

    @objid ("516a7476-698a-4b70-a9a3-e57cc2e9b20b")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("f83742b7-f640-4d23-9df1-97f2437686c2")
        private SmDependency symetricDep;

        @objid ("112e4977-f505-4b57-9080-292a8684f2fb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TypedPropertyTableData) data).mType;
        }

        @objid ("89fb20fa-36f7-40ea-9c42-1cfb8f8c729a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TypedPropertyTableData) data).mType = value;
        }

        @objid ("dcba6579-50c2-450f-b713-66d437c1f42c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getTypedTableDep();
            }
            return this.symetricDep;
        }

    }

}
