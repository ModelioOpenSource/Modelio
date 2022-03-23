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

package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorSmClass;
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
    @objid ("a2eaf9a3-5d19-4013-96fb-79c02564a108")
    private SmAttribute usingAdditionsAtt;

    @objid ("d228e87a-7ac9-4d25-95a6-21767948bbfb")
    private SmDependency addedDep;

    @objid ("0c61e457-9a90-41bc-9c01-1283bb25f534")
    private SmDependency processorDep;

    @objid ("78e8f7b2-f1f4-42cc-8e5a-7ebd8dd8277a")
    private SmDependency parametersDep;

    @objid ("88c66cb3-1469-4ba6-b9e7-65279bc33257")
    private SmDependency ownerAsLineDep;

    @objid ("b1dafaee-d4b3-4970-918a-67e9c7f3e026")
    private SmDependency ownerAsColDep;

    @objid ("bea44476-1bc4-42e1-9560-3169d9c291d3")
    private SmDependency ownerAsDepthDep;

    @objid ("81e58764-3e65-4fb5-ac4c-933a8760a80c")
    public  QueryDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1d161fb5-1c96-43cf-94b3-6cb5d6edeef7")
    @Override
    public String getName() {
        return "QueryDefinition";
        
    }

    @objid ("587aa69a-838d-4443-bf91-2c28db25d328")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d5846c03-5937-4bdd-9e7e-902b78416bce")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return QueryDefinition.class;
        
    }

    @objid ("d4f4528e-eb0f-4a13-9dde-025549b3c73e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("03304ee0-61bd-4e89-890c-247cc7cfa0bb")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("af9345e1-af95-4880-a9f0-aaa32cf17c13")
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

    @objid ("06f81f2e-4eee-43fd-ab60-e57701ac2e1b")
    public SmAttribute getUsingAdditionsAtt() {
        if (this.usingAdditionsAtt == null) {
        	this.usingAdditionsAtt = this.getAttributeDef("UsingAdditions");
        }
        return this.usingAdditionsAtt;
    }

    @objid ("e776fd6b-1455-4474-94ce-17303878022e")
    public SmDependency getAddedDep() {
        if (this.addedDep == null) {
        	this.addedDep = this.getDependencyDef("Added");
        }
        return this.addedDep;
    }

    @objid ("e9d15188-2dd8-42a0-acc6-eca4d8b49e61")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("1895bee2-7789-4640-b86d-17afe3521fd3")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("11304c8a-3b44-4bd6-9cdb-2d71a30200c0")
    public SmDependency getOwnerAsLineDep() {
        if (this.ownerAsLineDep == null) {
        	this.ownerAsLineDep = this.getDependencyDef("OwnerAsLine");
        }
        return this.ownerAsLineDep;
    }

    @objid ("fbfdc06d-424d-47bc-bf62-ce17f5c4e925")
    public SmDependency getOwnerAsColDep() {
        if (this.ownerAsColDep == null) {
        	this.ownerAsColDep = this.getDependencyDef("OwnerAsCol");
        }
        return this.ownerAsColDep;
    }

    @objid ("a78be006-3b97-49ac-aa62-74f28162e620")
    public SmDependency getOwnerAsDepthDep() {
        if (this.ownerAsDepthDep == null) {
        	this.ownerAsDepthDep = this.getDependencyDef("OwnerAsDepth");
        }
        return this.ownerAsDepthDep;
    }

    @objid ("c35b46ae-13e1-4369-a7ec-c9d632ad78d1")
    private static class QueryDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("154cea55-04e4-421b-a9b3-345be5c11109")
        private QueryDefinitionSmClass smClass;

        @objid ("6223a29d-7b53-4253-bc42-0625032f4e62")
        public  QueryDefinitionObjectFactory(QueryDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("bf93003b-1e87-43ac-9c2e-ab004c82dafd")
        @Override
        public ISmObjectData createData() {
            return new QueryDefinitionData(this.smClass);
        }

        @objid ("d1d1847b-ee96-4d5e-86f5-c0685e56f314")
        @Override
        public SmObjectImpl createImpl() {
            return new QueryDefinitionImpl();
        }

    }

    @objid ("5b4a6943-4c31-439b-91f4-1f352ad662f5")
    public static class UsingAdditionsSmAttribute extends SmAttribute {
        @objid ("c6201418-ac2e-444f-8fc2-79df290ab603")
        public Object getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mUsingAdditions;
        }

        @objid ("e43dd163-a3bd-4979-8c79-37cdf2780326")
        public void setValue(ISmObjectData data, Object value) {
            ((QueryDefinitionData) data).mUsingAdditions = value;
        }

    }

    @objid ("911261b9-5eab-46c8-9359-6505de122fc4")
    public static class AddedSmDependency extends SmMultipleDependency {
        @objid ("44ec2ce7-27f4-4514-b8c6-eeb573e3a860")
        private SmDependency symetricDep;

        @objid ("c18e03c3-17d1-4aaf-8346-30f95a92cd6e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((QueryDefinitionData)data).mAdded != null)? ((QueryDefinitionData)data).mAdded:SmMultipleDependency.EMPTY;
        }

        @objid ("00ec3782-752a-41f2-8b12-46af6c1f001a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((QueryDefinitionData) data).mAdded = values;
            
        }

        @objid ("5c476a67-06d0-41d1-a980-653c94a3ca4f")
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
        @objid ("fc4b81e0-738a-4a36-8122-f1f4cf92a36c")
        private SmDependency symetricDep;

        @objid ("4f57abe2-92ce-4147-93bc-55ea8ba7e05b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mProcessor;
        }

        @objid ("54fd1e98-3d15-433e-9703-16fd1870a87c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mProcessor = value;
        }

        @objid ("7923c3bf-6cff-4fa0-a66c-904767f2f117")
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
        @objid ("6245ce63-0872-4dff-ab84-0ef6f8e8d0f4")
        private SmDependency symetricDep;

        @objid ("5b21d073-c114-45ee-8dae-5023fd12c4de")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mParameters;
        }

        @objid ("ab2aa5c3-3f04-4c92-84c4-91242c642725")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mParameters = value;
        }

        @objid ("d0d91cbf-d309-49d6-8480-1be40b69659e")
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
        @objid ("75d6d6f7-cfae-4d26-bbb2-a553e28bb0ff")
        private SmDependency symetricDep;

        @objid ("1801b6a6-7fab-4cc6-bf5e-2ad9a8013a72")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsLine;
        }

        @objid ("34d812c7-6986-42d0-85db-ae1db5b662ba")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsLine = value;
        }

        @objid ("476c6a61-49e6-4c4b-852d-2565607a1bf6")
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
        @objid ("82e96e14-d439-4a7f-b44e-d685fbca72de")
        private SmDependency symetricDep;

        @objid ("f3ac4ed1-40a6-4a69-9f36-7a1ecdb6f0c1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsCol;
        }

        @objid ("b31b18c3-7c4d-4fad-912d-79c3f518f172")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsCol = value;
        }

        @objid ("c1af76da-6c56-4301-b096-7535a0589a31")
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
        @objid ("83f706b7-267d-40bb-8514-0173a6af40a3")
        private SmDependency symetricDep;

        @objid ("65a0dabc-5ea6-48c0-838d-044eeaa1d5a7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsDepth;
        }

        @objid ("1c67fa4d-2ae4-4c52-88f4-f7dd21607383")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsDepth = value;
        }

        @objid ("306e6ebf-4d9e-4610-bce8-2c92ec3b3535")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getDepthDefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

}
