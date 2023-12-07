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

package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorSmClass;
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
    @objid ("e5709052-f612-42b8-9c6b-a2c12ff300f5")
    private SmDependency processorDep;

    @objid ("0b5e07d2-4672-4784-8d9e-eefbfecf79ef")
    private SmDependency parametersDep;

    @objid ("f03c14df-fdc5-46f2-b825-fea103ad3816")
    private SmDependency matrixDep;

    @objid ("5241b9dc-12d9-4c82-aaf9-01b608eeba28")
    public  MatrixValueDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d3435da7-955f-4773-815c-ff705198ca3f")
    @Override
    public String getName() {
        return "MatrixValueDefinition";
        
    }

    @objid ("514c7a25-5609-4570-9def-69e7947fd931")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("85252478-ad6c-41bc-9a96-de6585d74162")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixValueDefinition.class;
        
    }

    @objid ("84df8210-4085-422e-89a4-04a534bfb267")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("4ffb8a4f-c9fa-4992-9e8c-dde8cf94a32b")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7d82e6d0-3fe4-4581-979a-433b0385ec7e")
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

    @objid ("80fdb57f-5f4b-445e-b79e-a58456014225")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("cb33493f-a0d3-4112-94d1-0ec5909ff4ec")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("6084025d-11a7-452f-a486-c28aaff3608f")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("70e19ccc-257b-4e45-8f75-d6ed656fe929")
    private static class MatrixValueDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("29eb4028-de54-4b61-8c59-17df2b56f101")
        private MatrixValueDefinitionSmClass smClass;

        @objid ("1cc32edb-27ac-4ef0-8aea-e1b23b58834d")
        public  MatrixValueDefinitionObjectFactory(MatrixValueDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ed49eda5-1361-4b36-847f-c176f2d57ac2")
        @Override
        public ISmObjectData createData() {
            return new MatrixValueDefinitionData(this.smClass);
        }

        @objid ("f674c6ad-38a5-441f-84e6-e514b48c2993")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixValueDefinitionImpl();
        }

    }

    @objid ("59aa1528-c526-4b87-97dd-b46952f0799f")
    public static class ProcessorSmDependency extends SmSingleDependency {
        @objid ("4f05476a-67c3-4264-b632-b44c5c3e6e41")
        private SmDependency symetricDep;

        @objid ("2621b9dd-0ecd-4609-b249-6f6c39df8804")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mProcessor;
        }

        @objid ("e10c9e5c-7881-44be-bebc-51118b61ea05")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mProcessor = value;
        }

        @objid ("e66fc4cc-ffaa-4b78-a3f5-c2864e110ca0")
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
        @objid ("abde13ac-732d-4a19-a4d3-48f209054834")
        private SmDependency symetricDep;

        @objid ("4dbb52a4-8200-41c8-a9d8-84b11f815a74")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mParameters;
        }

        @objid ("32f409ea-4d0e-4222-806c-48b5177f3bbb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mParameters = value;
        }

        @objid ("d99b6f46-d3b4-4f8b-87ff-a96ba0eb2087")
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
        @objid ("e506d7f9-ab7a-4854-9fc1-07e6b66f00c6")
        private SmDependency symetricDep;

        @objid ("88e72c0d-6dd9-4032-805b-f637e636e62c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mMatrix;
        }

        @objid ("8b6b338e-c20d-42ea-b46c-6de9ba55e799")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mMatrix = value;
        }

        @objid ("7ad4f127-4aa5-4f95-b184-c62c73f3ae84")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getValuesDefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

}
