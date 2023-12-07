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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
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

@objid ("2e3cf8ca-2a31-49dc-9304-38b05b812c6c")
public class MatrixDefinitionSmClass extends ModelElementSmClass {
    @objid ("bdb7aa50-b926-4937-af3a-9cba9575bbf9")
    private SmDependency linesDefinitionDep;

    @objid ("f0845670-1b6a-4c97-bf9d-fe6bae0d2544")
    private SmDependency columnsDefinitionDep;

    @objid ("7ef5a3b7-b778-455e-8785-fe70c7e1b65a")
    private SmDependency valuesDefinitionDep;

    @objid ("cf8f89c6-4184-4686-86bf-3eba976a0ca6")
    private SmDependency depthDefinitionDep;

    @objid ("a41c97ed-2104-476a-9d1d-d02131576b2a")
    private SmDependency ownerDep;

    @objid ("00ac1443-90ef-4345-a845-41e4726627ba")
    public  MatrixDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7cf3b9bd-f4f2-4c23-a52d-884e30e4087a")
    @Override
    public String getName() {
        return "MatrixDefinition";
        
    }

    @objid ("533b1666-a76c-4b20-98cc-bfa615b1d940")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6700c6e5-f4b2-4dec-9b98-4e9f27e5e0b0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixDefinition.class;
        
    }

    @objid ("df06e9d6-d501-425a-8e81-48f2fda28c6f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("2db1ca32-6f96-45ae-adf1-e787c8cee190")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("613e4582-9d08-46d3-95b5-dd699907390d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new MatrixDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.linesDefinitionDep = new LinesDefinitionSmDependency();
        this.linesDefinitionDep.init("LinesDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.linesDefinitionDep);
        
        this.columnsDefinitionDep = new ColumnsDefinitionSmDependency();
        this.columnsDefinitionDep.init("ColumnsDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.columnsDefinitionDep);
        
        this.valuesDefinitionDep = new ValuesDefinitionSmDependency();
        this.valuesDefinitionDep.init("ValuesDefinition", this, metamodel.getMClass(MatrixValueDefinition.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.valuesDefinitionDep);
        
        this.depthDefinitionDep = new DepthDefinitionSmDependency();
        this.depthDefinitionDep.init("DepthDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.depthDefinitionDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("b2e1402f-cbc8-4647-935b-9c0d5ed7bb12")
    public SmDependency getLinesDefinitionDep() {
        if (this.linesDefinitionDep == null) {
        	this.linesDefinitionDep = this.getDependencyDef("LinesDefinition");
        }
        return this.linesDefinitionDep;
    }

    @objid ("f2d895c8-75f2-45b7-8f38-3dde048fe508")
    public SmDependency getColumnsDefinitionDep() {
        if (this.columnsDefinitionDep == null) {
        	this.columnsDefinitionDep = this.getDependencyDef("ColumnsDefinition");
        }
        return this.columnsDefinitionDep;
    }

    @objid ("11366354-76cb-485d-be26-71aa63cf768c")
    public SmDependency getValuesDefinitionDep() {
        if (this.valuesDefinitionDep == null) {
        	this.valuesDefinitionDep = this.getDependencyDef("ValuesDefinition");
        }
        return this.valuesDefinitionDep;
    }

    @objid ("f2a9cbb4-fff3-454e-b4c8-9d9ff4da4bc9")
    public SmDependency getDepthDefinitionDep() {
        if (this.depthDefinitionDep == null) {
        	this.depthDefinitionDep = this.getDependencyDef("DepthDefinition");
        }
        return this.depthDefinitionDep;
    }

    @objid ("7b45895a-d78a-4dd6-95da-ad3b7d0fa37d")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("560eaf85-a56b-4d74-8ca7-3df759e15a1d")
    private static class MatrixDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("8303c73b-9d7c-4ec9-ae02-bb67c47d9243")
        private MatrixDefinitionSmClass smClass;

        @objid ("a479c496-fbd1-4e08-8aa7-0e6009710e9f")
        public  MatrixDefinitionObjectFactory(MatrixDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7dc9f938-2790-40da-b5dd-00ba077dbe2a")
        @Override
        public ISmObjectData createData() {
            return new MatrixDefinitionData(this.smClass);
        }

        @objid ("1ed27a3e-0e69-4cde-acea-12b109d8e0f4")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixDefinitionImpl();
        }

    }

    @objid ("2cf12629-f437-4d9d-987f-90caa9f63e28")
    public static class LinesDefinitionSmDependency extends SmSingleDependency {
        @objid ("0c6528f4-f1d6-4539-bc84-27a19ac78d83")
        private SmDependency symetricDep;

        @objid ("4f29d75a-1a4c-4fb1-beb4-7d1d950873f4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mLinesDefinition;
        }

        @objid ("0a28b1ca-c741-48b4-a7f4-50838f81dcc4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mLinesDefinition = value;
        }

        @objid ("cb6fc300-259f-4f16-a210-ff3baf7974bc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsLineDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("80b42027-5eb8-4762-81ff-83e31d0f5fd3")
    public static class ColumnsDefinitionSmDependency extends SmSingleDependency {
        @objid ("dd968b3d-db6b-4d96-b4ee-590323557fad")
        private SmDependency symetricDep;

        @objid ("d7767c97-096f-4012-950a-5bc0ec5dd70a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mColumnsDefinition;
        }

        @objid ("19edf747-3ab7-4fe1-961d-2a8339d6dd53")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mColumnsDefinition = value;
        }

        @objid ("0b3b5d10-83cd-4dfb-8c6a-462239f19e9c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsColDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6d85f4cb-3120-4fdd-8325-15ef402c6989")
    public static class ValuesDefinitionSmDependency extends SmSingleDependency {
        @objid ("b30fc6d7-aa40-4c81-8bfa-3e290c9ead8d")
        private SmDependency symetricDep;

        @objid ("938800a1-57c0-4437-a8c5-4a90b8ac028f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mValuesDefinition;
        }

        @objid ("82a1b8a4-34dc-4ad7-a49e-24683f737365")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mValuesDefinition = value;
        }

        @objid ("88ff5d27-6b29-4b50-8bf6-275265414fad")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getMatrixDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1018623d-5ebf-4d7d-be6a-4588e7b2052f")
    public static class DepthDefinitionSmDependency extends SmSingleDependency {
        @objid ("bcff1867-7eec-40be-9461-f35b7c8539e3")
        private SmDependency symetricDep;

        @objid ("2ae34132-629e-4539-9282-a4fe4cbc2778")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mDepthDefinition;
        }

        @objid ("6983257f-4d6f-4225-974b-453dba1430a4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mDepthDefinition = value;
        }

        @objid ("99d17c53-a41b-4ed8-b9b1-ab2d317a9b0a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsDepthDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("16e1ca50-872c-4aaa-837e-665e33d73c42")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("8eb33a5f-4bc5-4256-b3f1-a6872cdd5446")
        private SmDependency symetricDep;

        @objid ("94961535-387d-4628-b10f-58fc21d6acd0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mOwner;
        }

        @objid ("af85bf58-51be-4e74-b48c-c0e524ebede3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mOwner = value;
        }

        @objid ("836e1a4d-7551-4c43-8e2d-5b418621082e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getMatrixDep();
            }
            return this.symetricDep;
            
        }

    }

}
