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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("7e599826-c1b6-47d6-9fec-178ce027d0e1")
public class QueryDefinitionSmClass extends ElementSmClass {
    @objid ("26696ea7-3e92-4cb9-adc3-9ac31ddb2580")
    private SmAttribute usingAdditionsAtt;

    @objid ("d899f9cb-c12c-4568-895f-fdbef72cbc7b")
    private SmDependency addedDep;

    @objid ("09599e5c-4abf-45f4-bbc4-3c534d55e592")
    private SmDependency processorDep;

    @objid ("e93474eb-a57f-4162-98a7-77ca6ea0551d")
    private SmDependency parametersDep;

    @objid ("3467a6ab-57bf-4401-b5c6-5bd702f75e26")
    private SmDependency ownerAsLineDep;

    @objid ("01bd0403-ceda-44c7-ae1f-b06ab55ee7e0")
    private SmDependency ownerAsColDep;

    @objid ("34c3857a-643b-4867-b13b-2553f7aab185")
    private SmDependency ownerAsDepthDep;

    @objid ("2aae16e9-e0c0-4829-b1c5-e0c7ee2997e2")
    public QueryDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("71e452e1-b7b9-4701-bdc7-9db887cbd81a")
    @Override
    public String getName() {
        return "QueryDefinition";
    }

    @objid ("1f995e45-47c8-42ef-80a8-dd3ef71cbff6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d5f24c5e-648f-4ab1-9f0e-2090437d89d1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return QueryDefinition.class;
    }

    @objid ("aad1eaba-9fbc-4335-93e5-ae6b74878164")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("b0b765cd-3aa8-4efc-985b-02017b907ecf")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e7d2c1c4-5120-496a-b6a1-52f41a0459f4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new QueryDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.usingAdditionsAtt = new UsingAdditionsSmAttribute();
        this.usingAdditionsAtt.init("UsingAdditions", this, Boolean.class );
        registerAttribute(this.usingAdditionsAtt);
        
        
        // Initialize and register the SmDependency
        this.addedDep = new AddedSmDependency();
        this.addedDep.init("Added", this, metamodel.getMClass(Element.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.addedDep);
        
        this.processorDep = new ProcessorSmDependency();
        this.processorDep.init("Processor", this, metamodel.getMClass(ExternProcessor.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.processorDep);
        
        this.parametersDep = new ParametersSmDependency();
        this.parametersDep.init("Parameters", this, metamodel.getMClass(PropertyTable.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parametersDep);
        
        this.ownerAsLineDep = new OwnerAsLineSmDependency();
        this.ownerAsLineDep.init("OwnerAsLine", this, metamodel.getMClass(MatrixDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerAsLineDep);
        
        this.ownerAsColDep = new OwnerAsColSmDependency();
        this.ownerAsColDep.init("OwnerAsCol", this, metamodel.getMClass(MatrixDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerAsColDep);
        
        this.ownerAsDepthDep = new OwnerAsDepthSmDependency();
        this.ownerAsDepthDep.init("OwnerAsDepth", this, metamodel.getMClass(MatrixDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerAsDepthDep);
    }

    @objid ("e81248d6-4c94-4902-988d-937bddda37b6")
    public SmAttribute getUsingAdditionsAtt() {
        if (this.usingAdditionsAtt == null) {
        	this.usingAdditionsAtt = this.getAttributeDef("UsingAdditions");
        }
        return this.usingAdditionsAtt;
    }

    @objid ("3e108fbd-7941-448e-aa4a-22685b398f9f")
    public SmDependency getAddedDep() {
        if (this.addedDep == null) {
        	this.addedDep = this.getDependencyDef("Added");
        }
        return this.addedDep;
    }

    @objid ("83f5f0b2-511d-41b1-8eb3-c64ea3a2cba8")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("8379be63-37a2-451b-89cb-54bca2b66266")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("10ffe116-0686-4477-ae3f-48bf8bbd4bd0")
    public SmDependency getOwnerAsLineDep() {
        if (this.ownerAsLineDep == null) {
        	this.ownerAsLineDep = this.getDependencyDef("OwnerAsLine");
        }
        return this.ownerAsLineDep;
    }

    @objid ("d16e5b3d-2b90-47c3-946c-db47f2d0cdd3")
    public SmDependency getOwnerAsColDep() {
        if (this.ownerAsColDep == null) {
        	this.ownerAsColDep = this.getDependencyDef("OwnerAsCol");
        }
        return this.ownerAsColDep;
    }

    @objid ("4ec928c5-b4c9-45b9-b196-688770cb7ec2")
    public SmDependency getOwnerAsDepthDep() {
        if (this.ownerAsDepthDep == null) {
        	this.ownerAsDepthDep = this.getDependencyDef("OwnerAsDepth");
        }
        return this.ownerAsDepthDep;
    }

    @objid ("c35b46ae-13e1-4369-a7ec-c9d632ad78d1")
    private static class QueryDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("488d5687-8bf0-4816-9606-3ed9eaf72e04")
        private QueryDefinitionSmClass smClass;

        @objid ("d839fd9d-d27b-4afc-94d6-d65835c65de2")
        public QueryDefinitionObjectFactory(QueryDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b9a32fee-5c65-4b5a-a00e-0b80b8d3d3b2")
        @Override
        public ISmObjectData createData() {
            return new QueryDefinitionData(this.smClass);
        }

        @objid ("1963816a-ef2f-4881-b8c6-dbca699ddc11")
        @Override
        public SmObjectImpl createImpl() {
            return new QueryDefinitionImpl();
        }

    }

    @objid ("5b4a6943-4c31-439b-91f4-1f352ad662f5")
    public static class UsingAdditionsSmAttribute extends SmAttribute {
        @objid ("70b02c7c-20d2-4914-9635-b0574fbb1f31")
        public Object getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mUsingAdditions;
        }

        @objid ("14f664a3-6f63-435e-a939-c95e2e28ebb1")
        public void setValue(ISmObjectData data, Object value) {
            ((QueryDefinitionData) data).mUsingAdditions = value;
        }

    }

    @objid ("911261b9-5eab-46c8-9359-6505de122fc4")
    public static class AddedSmDependency extends SmMultipleDependency {
        @objid ("4a7c9a7e-2c7b-4a4c-90cc-28dbbbfeb96a")
        private SmDependency symetricDep;

        @objid ("4e7ae3c5-d87d-43a1-bfa3-25578e468480")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((QueryDefinitionData)data).mAdded != null)? ((QueryDefinitionData)data).mAdded:SmMultipleDependency.EMPTY;
        }

        @objid ("c4a4f10e-5e47-4506-b717-d4fc6412a350")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((QueryDefinitionData) data).mAdded = values;
        }

        @objid ("f9a52ba1-212a-4c04-993d-de0e83bdd507")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementSmClass)this.getTarget()).getAddedToQueryDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c1a4fac9-7e20-4925-9ef4-c58131b1592c")
    public static class ProcessorSmDependency extends SmSingleDependency {
        @objid ("c1f61ac9-2ca9-4c38-a124-f64f1f177275")
        private SmDependency symetricDep;

        @objid ("cdeff09d-1a5f-4b5b-8aeb-0d0b7db7f3a0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mProcessor;
        }

        @objid ("636b34fe-acdc-42be-8c3f-cba939c81b77")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mProcessor = value;
        }

        @objid ("f68ea704-c8ec-4f38-9a8c-fecbb397171a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExternProcessorSmClass)this.getTarget()).getOwnerQueryDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("972d7073-2a13-4cc4-947e-66b13ff32d51")
    public static class ParametersSmDependency extends SmSingleDependency {
        @objid ("a9611f53-15e2-4f6c-9e63-5dae4c3d2a83")
        private SmDependency symetricDep;

        @objid ("6eeba1bd-022e-4ed7-8ef6-86fce11d9dda")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mParameters;
        }

        @objid ("740657fe-9d09-44f9-be6d-c58004ff9ab1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mParameters = value;
        }

        @objid ("3670a476-fdf1-411f-9983-f0cf63ebe802")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PropertyTableSmClass)this.getTarget()).getOwnerQueryDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6bcb96f3-c4d8-4637-b7f5-29105dd593a7")
    public static class OwnerAsLineSmDependency extends SmSingleDependency {
        @objid ("c1b62c30-240d-4d60-9f25-d1986b3b0fd3")
        private SmDependency symetricDep;

        @objid ("3f15e748-3942-40fa-9645-748895b94b35")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsLine;
        }

        @objid ("48a259e9-5b8e-436c-b0a5-1707fd809709")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsLine = value;
        }

        @objid ("cabc7fb8-bb17-4854-a285-8172dd517352")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getLinesDefinitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b8a8231a-6046-427f-9b81-9d2cb02ba597")
    public static class OwnerAsColSmDependency extends SmSingleDependency {
        @objid ("9c3dec89-6d1b-4a14-8cd3-44efc7a390f6")
        private SmDependency symetricDep;

        @objid ("be42865f-36e7-4c31-befc-74ec4347fe2d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsCol;
        }

        @objid ("6416333f-802a-4cb3-a22a-2a6f48c5ec70")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsCol = value;
        }

        @objid ("72e4aee7-6e6d-45d0-bd3b-dc35cdc8074d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getColumnsDefinitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("30312de8-5b1e-422a-bce2-d26ddcd41dbf")
    public static class OwnerAsDepthSmDependency extends SmSingleDependency {
        @objid ("9ef5ab4a-8121-459d-9782-ae1ed863c979")
        private SmDependency symetricDep;

        @objid ("36e3262d-9a8a-42d6-b1b1-6fc920450926")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsDepth;
        }

        @objid ("5902db0f-e367-421a-892f-4401411b848d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsDepth = value;
        }

        @objid ("3422d6ed-d35f-4187-907f-32e10aa83a7a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getDepthDefinitionDep();
            }
            return this.symetricDep;
        }

    }

}
