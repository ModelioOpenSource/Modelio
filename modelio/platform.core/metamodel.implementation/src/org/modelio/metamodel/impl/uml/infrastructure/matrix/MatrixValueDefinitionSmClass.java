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
    @objid ("916f0f10-dbc9-4db2-89dc-d7ce47ab4d8b")
    private SmDependency processorDep;

    @objid ("df70dcb1-2b91-4a31-8c9a-34a86d085d82")
    private SmDependency parametersDep;

    @objid ("35e6971c-abd8-401b-8153-03ce48124325")
    private SmDependency matrixDep;

    @objid ("a63139ef-5c78-4989-bcb8-d732e300b6b2")
    public MatrixValueDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fc24eddd-7a34-4e86-a002-d098a06b0596")
    @Override
    public String getName() {
        return "MatrixValueDefinition";
    }

    @objid ("eca4c3a7-df0b-464d-b9ca-a4643dae3bbb")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("645a7adc-035a-4107-b563-a11abbf02ec9")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixValueDefinition.class;
    }

    @objid ("6038ba6f-2e59-434d-9552-3624ebf68ac5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cb9b2a28-e35e-439a-ac94-aeb1a26247e8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("63e84bf7-9222-40cd-bdba-b0e667425dcf")
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

    @objid ("721e0d53-5a4b-4546-b4a1-7b2a245eac94")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("35c45ce9-0949-4df1-86d5-029063e17ae9")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("5c85e375-1277-454f-8595-85bbd7e79993")
    public SmDependency getMatrixDep() {
        if (this.matrixDep == null) {
        	this.matrixDep = this.getDependencyDef("Matrix");
        }
        return this.matrixDep;
    }

    @objid ("70e19ccc-257b-4e45-8f75-d6ed656fe929")
    private static class MatrixValueDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("c2ad812b-87f4-431c-9e34-eb6db68dd189")
        private MatrixValueDefinitionSmClass smClass;

        @objid ("357f1ce8-b694-4407-960c-274529636079")
        public MatrixValueDefinitionObjectFactory(MatrixValueDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2ac9b2a8-a967-403e-b9e9-6bf0b441649d")
        @Override
        public ISmObjectData createData() {
            return new MatrixValueDefinitionData(this.smClass);
        }

        @objid ("ef1dddff-dd1a-44b2-861e-8760a328f0ac")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixValueDefinitionImpl();
        }

    }

    @objid ("59aa1528-c526-4b87-97dd-b46952f0799f")
    public static class ProcessorSmDependency extends SmSingleDependency {
        @objid ("2dcee0c9-acb7-47bc-b352-be20c8b13ddb")
        private SmDependency symetricDep;

        @objid ("107ff720-ba5d-4d95-934a-3d891241ce5f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mProcessor;
        }

        @objid ("fad90e0d-3a92-47f2-8f31-2518a99da70c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mProcessor = value;
        }

        @objid ("47f991a9-59a4-4d8c-bcd9-6c4187e13a9c")
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
        @objid ("47ab9c0e-cd7c-4a64-839a-c33c1b6e6156")
        private SmDependency symetricDep;

        @objid ("65c2d86b-7580-419c-9678-161c988da3a7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mParameters;
        }

        @objid ("201ad0aa-f05b-494b-9081-225d9c54d728")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mParameters = value;
        }

        @objid ("8c7e95de-e3be-45f7-95d7-83f37bc60ee6")
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
        @objid ("8ea3766d-041d-44fb-aa3a-905376198821")
        private SmDependency symetricDep;

        @objid ("657d0ae2-1241-474f-9634-48d65536dde6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixValueDefinitionData) data).mMatrix;
        }

        @objid ("de238c47-05b2-4be9-9944-1758322e8a71")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixValueDefinitionData) data).mMatrix = value;
        }

        @objid ("f7dc08a7-b865-43eb-910c-4de15f2c12b8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getValuesDefinitionDep();
            }
            return this.symetricDep;
        }

    }

}
