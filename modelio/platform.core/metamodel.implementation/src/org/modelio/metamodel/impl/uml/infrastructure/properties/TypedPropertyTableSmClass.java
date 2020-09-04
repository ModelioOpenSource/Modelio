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
    @objid ("2f4c6b78-7477-4e01-8753-61648945f91d")
    private SmDependency typeDep;

    @objid ("178c4222-2760-4386-b601-e94a4e73f5ea")
    public TypedPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0328782e-f032-4d79-b2c5-bbd24cd1bc19")
    @Override
    public String getName() {
        return "TypedPropertyTable";
    }

    @objid ("4a2b014a-0017-4684-8be4-dac592b957c2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d62539e0-39af-4e08-ab66-9ecb9565eb24")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TypedPropertyTable.class;
    }

    @objid ("49a2580c-fbf0-42a0-b5aa-799f7564c56e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9aa4dc57-817e-4512-a082-29d4f809dfd5")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("1359e9e7-5df5-4085-b510-da3fad4c6cac")
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

    @objid ("8b2ab303-84c6-4bc9-80cc-5cfe9a395f32")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("9a7ca71a-e2e6-4e5e-8bae-40417229a641")
    private static class TypedPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("e3ad43a3-88d8-4096-94fa-beffa3502ba6")
        private TypedPropertyTableSmClass smClass;

        @objid ("4a767241-aa17-46cc-a72f-6438903d0c2a")
        public TypedPropertyTableObjectFactory(TypedPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("65a9a513-a1dd-4a5c-aaf6-3d3858fe3696")
        @Override
        public ISmObjectData createData() {
            return new TypedPropertyTableData(this.smClass);
        }

        @objid ("e4d67b39-da47-40cb-935d-140d582bc932")
        @Override
        public SmObjectImpl createImpl() {
            return new TypedPropertyTableImpl();
        }

    }

    @objid ("516a7476-698a-4b70-a9a3-e57cc2e9b20b")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("b4b183c7-48c4-4ba8-82fe-0873205d010e")
        private SmDependency symetricDep;

        @objid ("19632a18-bbc0-421f-a55e-7d92430d8a38")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TypedPropertyTableData) data).mType;
        }

        @objid ("ec1f7518-66f9-4eb2-a8ad-a817de3c827d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TypedPropertyTableData) data).mType = value;
        }

        @objid ("d0461bd6-2a6a-4cbf-b57c-478d221d101a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableDefinitionSmClass)this.getTarget()).getTypedTableDep();
            }
            return this.symetricDep;
        }

    }

}
