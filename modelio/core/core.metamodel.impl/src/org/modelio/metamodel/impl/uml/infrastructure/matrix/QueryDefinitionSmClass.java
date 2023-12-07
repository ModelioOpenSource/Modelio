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
    @objid ("c7c25ec8-7beb-41f1-8ab8-0068e5ef437e")
    private SmAttribute usingAdditionsAtt;

    @objid ("d10363ca-003a-4c88-a5e8-d5be1a207081")
    private SmDependency addedDep;

    @objid ("236fdcd1-8ded-42da-8516-8fdb0e5f73a5")
    private SmDependency processorDep;

    @objid ("cd036ac8-519f-4030-8c8b-c374c6ddd46c")
    private SmDependency parametersDep;

    @objid ("ea83a0b6-9c12-448c-bcc4-72c7fe265a0c")
    private SmDependency ownerAsLineDep;

    @objid ("7dd45864-f555-4f91-b75f-eb4cff054b1a")
    private SmDependency ownerAsColDep;

    @objid ("ee49123a-bc87-47a6-9cd8-cbbdb0534150")
    private SmDependency ownerAsDepthDep;

    @objid ("18cec89f-9ef1-4e7a-9ca1-ae5a1460c78f")
    public  QueryDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6975d7e9-5050-47ae-b417-13d099b87d96")
    @Override
    public String getName() {
        return "QueryDefinition";
        
    }

    @objid ("ff7a552a-beb8-4a9a-bb1a-120f1e10251a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3c509667-9e4c-4340-82d6-1503a153b9c3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return QueryDefinition.class;
        
    }

    @objid ("4f792b10-c715-42c6-b0af-97eede3f2687")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("0c70c1e7-93a2-420d-bbba-a1bdf34eb7bd")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("f40a1617-1abb-420a-87e6-f5735d37393c")
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

    @objid ("e972bea3-3118-4dba-968b-40e1f935be5b")
    public SmAttribute getUsingAdditionsAtt() {
        if (this.usingAdditionsAtt == null) {
        	this.usingAdditionsAtt = this.getAttributeDef("UsingAdditions");
        }
        return this.usingAdditionsAtt;
    }

    @objid ("fbbc2668-9b11-495b-a8e7-a7bde59b73c3")
    public SmDependency getAddedDep() {
        if (this.addedDep == null) {
        	this.addedDep = this.getDependencyDef("Added");
        }
        return this.addedDep;
    }

    @objid ("14a6d74f-8ffb-4a8f-8ad4-2d463623739f")
    public SmDependency getProcessorDep() {
        if (this.processorDep == null) {
        	this.processorDep = this.getDependencyDef("Processor");
        }
        return this.processorDep;
    }

    @objid ("1e99cabc-8d8e-4435-88e8-bf0e38835fa0")
    public SmDependency getParametersDep() {
        if (this.parametersDep == null) {
        	this.parametersDep = this.getDependencyDef("Parameters");
        }
        return this.parametersDep;
    }

    @objid ("bd96706c-b406-4802-a190-d8afea833244")
    public SmDependency getOwnerAsLineDep() {
        if (this.ownerAsLineDep == null) {
        	this.ownerAsLineDep = this.getDependencyDef("OwnerAsLine");
        }
        return this.ownerAsLineDep;
    }

    @objid ("42ac0f6d-c1db-4e7f-adbc-7b010004abdc")
    public SmDependency getOwnerAsColDep() {
        if (this.ownerAsColDep == null) {
        	this.ownerAsColDep = this.getDependencyDef("OwnerAsCol");
        }
        return this.ownerAsColDep;
    }

    @objid ("b204bfd7-92b7-494c-a736-91dba00402da")
    public SmDependency getOwnerAsDepthDep() {
        if (this.ownerAsDepthDep == null) {
        	this.ownerAsDepthDep = this.getDependencyDef("OwnerAsDepth");
        }
        return this.ownerAsDepthDep;
    }

    @objid ("c35b46ae-13e1-4369-a7ec-c9d632ad78d1")
    private static class QueryDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("f6c4b0a0-ec6e-44ee-b1de-6f0ee7b1aff1")
        private QueryDefinitionSmClass smClass;

        @objid ("bbf2d78b-03fd-4078-9b61-6c67a6f0757f")
        public  QueryDefinitionObjectFactory(QueryDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("18a28dde-4145-49f5-9ad1-21f88dce9721")
        @Override
        public ISmObjectData createData() {
            return new QueryDefinitionData(this.smClass);
        }

        @objid ("daa1681f-db43-4017-b9c4-7e106ac0bb4f")
        @Override
        public SmObjectImpl createImpl() {
            return new QueryDefinitionImpl();
        }

    }

    @objid ("5b4a6943-4c31-439b-91f4-1f352ad662f5")
    public static class UsingAdditionsSmAttribute extends SmAttribute {
        @objid ("f6bb39c6-f45d-41e6-9151-814b073b39e3")
        public Object getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mUsingAdditions;
        }

        @objid ("a6f3dc2a-769c-437e-aad8-a79405216f92")
        public void setValue(ISmObjectData data, Object value) {
            ((QueryDefinitionData) data).mUsingAdditions = value;
        }

    }

    @objid ("911261b9-5eab-46c8-9359-6505de122fc4")
    public static class AddedSmDependency extends SmMultipleDependency {
        @objid ("8ca3ff83-d9ac-4178-b490-3d710e83af35")
        private SmDependency symetricDep;

        @objid ("2258c4c9-285b-43f5-8499-1ff9f9bb4580")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((QueryDefinitionData)data).mAdded != null)? ((QueryDefinitionData)data).mAdded:SmMultipleDependency.EMPTY;
        }

        @objid ("addf70cb-3735-4a68-bd69-23c1aa6a8919")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((QueryDefinitionData) data).mAdded = values;
            
        }

        @objid ("c42cf496-3a58-474e-a269-b4764e5e4c63")
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
        @objid ("6054b368-d37c-4c13-b0e8-6ca2c0bb8f9a")
        private SmDependency symetricDep;

        @objid ("076a5b9a-e6c0-4bfd-9901-845516d76f46")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mProcessor;
        }

        @objid ("07ae6dcc-8e00-4637-82c0-32402e422448")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mProcessor = value;
        }

        @objid ("ab235ba9-83b3-49e8-b59a-e65a3ed048d2")
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
        @objid ("1992a10d-0e82-4c80-8135-b059c733ddf2")
        private SmDependency symetricDep;

        @objid ("3b4b87f6-25cc-4df6-9ed2-f406412d56cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mParameters;
        }

        @objid ("ff557eed-0b20-417b-b646-1ac48917dd5a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mParameters = value;
        }

        @objid ("b97e4427-d91a-4727-9b18-2b0d85174682")
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
        @objid ("d1618eac-e5ac-41ad-87bb-1da6da01bc89")
        private SmDependency symetricDep;

        @objid ("748e8d2f-1845-4fe3-a750-1889f1d39a63")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsLine;
        }

        @objid ("f16ecd75-34bc-4b1a-abb5-4d015c797a92")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsLine = value;
        }

        @objid ("b26578d0-22ee-4292-b06b-a69754bc8239")
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
        @objid ("ed1cd9c1-18dc-438a-86c8-117c6f2167d8")
        private SmDependency symetricDep;

        @objid ("3124a614-fff5-4654-94ec-efbdea3f7962")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsCol;
        }

        @objid ("38d40972-3cde-410a-91b3-306f136e8c27")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsCol = value;
        }

        @objid ("9e627ed3-4a2d-4a44-b64b-e53d661b91c4")
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
        @objid ("38af1fb0-3fd4-4456-bb92-3bc9561f754a")
        private SmDependency symetricDep;

        @objid ("3a55d16a-e7be-4715-867c-5fdf533fba46")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((QueryDefinitionData) data).mOwnerAsDepth;
        }

        @objid ("4a931146-777c-46d7-81e2-29c62d86cd14")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((QueryDefinitionData) data).mOwnerAsDepth = value;
        }

        @objid ("15fec080-347b-432e-9be2-53af5c5955e0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixDefinitionSmClass)this.getTarget()).getDepthDefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

}
