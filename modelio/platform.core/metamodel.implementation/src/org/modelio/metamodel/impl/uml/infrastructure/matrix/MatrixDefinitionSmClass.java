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
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
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
    @objid ("728ad72f-9fba-4028-943c-623c2f445401")
    private SmDependency linesDefinitionDep;

    @objid ("20200bd5-5115-4bc2-86d3-754c879cc974")
    private SmDependency columnsDefinitionDep;

    @objid ("60a6a594-e2fb-4594-a483-5d393f073702")
    private SmDependency valuesDefinitionDep;

    @objid ("cd49da72-cae6-4096-bd2e-6ac09c2535a1")
    private SmDependency depthDefinitionDep;

    @objid ("327f53d5-75f5-4bee-ab9e-fa26fcac7a70")
    private SmDependency ownerDep;

    @objid ("97b8162d-2d23-4883-afd5-8c7c9dda6f10")
    public MatrixDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("74b7f681-fe76-4652-8d66-48df5b7acbad")
    @Override
    public String getName() {
        return "MatrixDefinition";
    }

    @objid ("e1bb707d-54df-4654-a74e-220d904328d1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8e69e90c-d3be-44f8-ae1b-3558bb5ad516")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixDefinition.class;
    }

    @objid ("cfc40d19-c98d-4fe3-8e84-f877467298f2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e7472d93-fb77-487d-ae33-fd8f70f80b5b")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("17ba643b-2f41-48c8-a061-82d39c21f84a")
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

    @objid ("e379f5b4-1278-42d2-b67e-213e7bd036e0")
    public SmDependency getLinesDefinitionDep() {
        if (this.linesDefinitionDep == null) {
        	this.linesDefinitionDep = this.getDependencyDef("LinesDefinition");
        }
        return this.linesDefinitionDep;
    }

    @objid ("71157c0d-e943-4bd5-9713-eec6cba39e40")
    public SmDependency getColumnsDefinitionDep() {
        if (this.columnsDefinitionDep == null) {
        	this.columnsDefinitionDep = this.getDependencyDef("ColumnsDefinition");
        }
        return this.columnsDefinitionDep;
    }

    @objid ("abf015fb-55f4-4024-bc60-e908a75c5f89")
    public SmDependency getValuesDefinitionDep() {
        if (this.valuesDefinitionDep == null) {
        	this.valuesDefinitionDep = this.getDependencyDef("ValuesDefinition");
        }
        return this.valuesDefinitionDep;
    }

    @objid ("f6af2e0e-0786-42f4-9004-e61e1fdb53d2")
    public SmDependency getDepthDefinitionDep() {
        if (this.depthDefinitionDep == null) {
        	this.depthDefinitionDep = this.getDependencyDef("DepthDefinition");
        }
        return this.depthDefinitionDep;
    }

    @objid ("3a6d15dc-86bf-487f-a381-8beba9b03491")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("560eaf85-a56b-4d74-8ca7-3df759e15a1d")
    private static class MatrixDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("f57c82bc-6edb-4706-b63c-5435ba5abdc2")
        private MatrixDefinitionSmClass smClass;

        @objid ("7f1bfd72-64b3-4fe4-8694-047e33b65198")
        public MatrixDefinitionObjectFactory(MatrixDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("361c4925-754b-44e2-abc2-59c6ec44de4f")
        @Override
        public ISmObjectData createData() {
            return new MatrixDefinitionData(this.smClass);
        }

        @objid ("b8e44463-8660-43a5-9967-92ce0a4b94f2")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixDefinitionImpl();
        }

    }

    @objid ("2cf12629-f437-4d9d-987f-90caa9f63e28")
    public static class LinesDefinitionSmDependency extends SmSingleDependency {
        @objid ("bc843f4e-081f-417a-b9f0-24339113a2ad")
        private SmDependency symetricDep;

        @objid ("4aae2ee0-642c-4405-8074-662c10b01fb9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mLinesDefinition;
        }

        @objid ("0d3547b6-4d9d-47d2-9e96-2190efe128ac")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mLinesDefinition = value;
        }

        @objid ("4612bdda-22e7-45b5-9216-d45a5cbea8b5")
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
        @objid ("64c41348-9ae5-4b14-838b-36885e694378")
        private SmDependency symetricDep;

        @objid ("3852238e-29e2-4033-98c7-3bd712415054")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mColumnsDefinition;
        }

        @objid ("762c154d-c40d-4f8c-b91c-e304f2b8fe0b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mColumnsDefinition = value;
        }

        @objid ("2d2af772-1b46-4478-b2dd-44029e8471b1")
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
        @objid ("1eab3bf1-77a4-4f75-aac7-1967c388faa0")
        private SmDependency symetricDep;

        @objid ("d5ccd4aa-08b0-41aa-b44e-a4cdafccab4e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mValuesDefinition;
        }

        @objid ("afecf16b-a66a-4e6a-88c9-431b10ec1ddb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mValuesDefinition = value;
        }

        @objid ("2b2e2f07-5b9b-470e-a306-e5c61aa4df63")
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
        @objid ("9b032532-be12-4328-bb6f-6b5c6f35b3b4")
        private SmDependency symetricDep;

        @objid ("9b854aba-59af-4abf-a242-181a5f267111")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mDepthDefinition;
        }

        @objid ("5423d716-41fe-4d8b-b9a0-1ed7a3f7911b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mDepthDefinition = value;
        }

        @objid ("cfe41fa3-46c6-48aa-ba1d-04e725fd192d")
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
        @objid ("c7c65845-d922-49c1-acff-82704532f481")
        private SmDependency symetricDep;

        @objid ("c4b9f6af-f036-44b5-92b1-fc51a480c9eb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mOwner;
        }

        @objid ("9a80bf5f-8aff-45fd-b2f7-71aeb7901a0f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mOwner = value;
        }

        @objid ("49ff14ce-3dfa-40c5-a5fe-6ca0cbcdf7ea")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getMatrixDep();
            }
            return this.symetricDep;
        }

    }

}
