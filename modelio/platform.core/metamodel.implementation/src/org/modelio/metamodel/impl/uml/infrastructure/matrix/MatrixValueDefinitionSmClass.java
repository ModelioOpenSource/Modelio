/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
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

@objid ("85101926-43cd-4c85-8d01-10d896562bcf")
public class MatrixValueDefinitionSmClass extends ElementSmClass {
    @objid ("b2f13c6c-2118-46a2-9c53-7aa5d2053d01")
    private SmDependency processorDep;

    @objid ("006f83f6-5e9b-41c8-bad9-2e8724c66a5f")
    private SmDependency parametersDep;

    @objid ("3c3f50e2-d899-4665-94e0-b61ebcdf5128")
    private SmDependency matrixDep;

    @objid ("1bf5a708-04a6-4bbf-84ed-91d06b41d6f3")
    public MatrixValueDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ca2d2d74-8e28-4def-9424-4c34b2a5756c")
    @Override
    public String getName() {
        return "MatrixValueDefinition";
    }

    @objid ("0e102f2b-6f7c-4eb1-8ae9-48f5ad53b61b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("fad16c6f-0d8f-48a8-a8e4-de628bc4e840")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixValueDefinition.class;
    }

    @objid ("62d18237-c9da-4855-997f-1c64bf5dcb31")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e4d523ac-a0bd-40f4-be92-744107f5d4da")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dd1f3228-f704-4590-88d0-de6a8b6a6378")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new MatrixValueDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.processorDep = new ProcessorSmDependency();
        this.processorDep.init("Processor", this, metamodel.getMClass(ExternProcessor.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.processorDep);
        
        this.parametersDep = new ParametersSmDependency();
        this.parametersDep.init("Parameters", this, metamodel.getMClass(PropertyTable.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parametersDep);
        
        this.matrixDep = new MatrixSmDependency();
        this.matrixDep.init("Matrix", this, metamodel.getMClass(MatrixDefinition.MQNAME), 1, 1 );
        registerDependency(this.matrixDep);
    }

    @objid ("5ddd0fe1-0ae5-4b31-94c0-de5328d0120f")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("d08008fd-449c-4c97-bb42-55887434f35e")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("4b513187-1250-4014-9430-c082ebb9b65a")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("70e19ccc-257b-4e45-8f75-d6ed656fe929")
    private static class MatrixValueDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("2b2d0fea-293d-4bc8-9b41-b7ded181dac0")
        private MatrixValueDefinitionSmClass smClass;

        @objid ("03c12a9f-d934-4802-8e28-284768eedf0d")
        public MatrixValueDefinitionObjectFactory(MatrixValueDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2cac17b9-740d-466f-b8ac-a2fbf264ac41")
        @Override
        public ISmObjectData createData() {
            return new MatrixValueDefinitionData(this.smClass);
        }

        @objid ("05e331ce-5545-4808-ab83-18ab211a6372")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixValueDefinitionImpl();
        }

    }

    @objid ("59aa1528-c526-4b87-97dd-b46952f0799f")
    public static class ProcessorSmDependency extends SmSingleDependency {
        @objid ("455bcd7f-53c0-468d-afec-4d7f33aec659")
        private SmDependency symetricDep;

        @objid ("1c0477ed-8fb9-4109-b034-2e53becc5900")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mProcessor;
        }

        @objid ("010fff1e-cc86-4d3a-baa4-5e05d380b0ea")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mProcessor = value;
        }

        @objid ("07a23f22-ef9f-4010-8ef5-18cc78161c36")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExternProcessorSmClass)this.getTarget()).getOwnerValDefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("07e01b61-d274-4d89-92fb-8a1f731f82d2")
    public static class ParametersSmDependency extends SmSingleDependency {
        @objid ("c94d79c7-75eb-4e84-87d6-289f50586815")
        private SmDependency symetricDep;

        @objid ("4c6c90df-62e1-4d4c-a387-214c0e37929a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mParameters;
        }

        @objid ("bb1be11d-55ab-410e-b767-2cb20ad35f62")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mParameters = value;
        }

        @objid ("80ca97e8-f714-44ec-a1e3-13427c6cfbd1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableSmClass)this.getTarget()).getOwnerValDefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b31054ac-c5f2-4c82-a506-5ed8dd96e1ca")
    public static class MatrixSmDependency extends SmSingleDependency {
        @objid ("30c228dc-02d6-406c-954d-8684173717ee")
        private SmDependency symetricDep;

        @objid ("ff7705c8-c4bb-4d52-8c9f-3c8986a6cfb6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mMatrix;
        }

        @objid ("e1728019-88fe-408c-a164-8e7c32e2244e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mMatrix = value;
        }

        @objid ("f3957273-da51-4a24-b3e8-72cac9e5f6e1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getValuesDefinitionDep();
            }
            return this.symetricDep;
        }

    }

}
