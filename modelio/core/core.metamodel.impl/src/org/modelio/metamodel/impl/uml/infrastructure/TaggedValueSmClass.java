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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("ca86e242-965e-4c2a-b06e-2c12ee7543f8")
public class TaggedValueSmClass extends ModelElementSmClass {
    @objid ("a7c3e275-3f4d-4407-bb79-1fe65e172574")
    private SmDependency actualDep;

    @objid ("cad43a1d-c003-4073-969a-48e0463bdb25")
    private SmDependency qualifierDep;

    @objid ("e8eda24e-96ad-46ba-bc7e-2840d01d6ae9")
    private SmDependency definitionDep;

    @objid ("6d360293-d918-400c-8377-c8fee78e4b25")
    private SmDependency annotedDep;

    @objid ("bff13ac9-3913-4f04-b297-cc3671c3e656")
    public  TaggedValueSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8c6c1ecc-4511-4785-a53d-76ed039786ab")
    @Override
    public String getName() {
        return "TaggedValue";
        
    }

    @objid ("8813bf56-f82d-4380-b1c1-caf70ec7c604")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("71e732b8-2658-4fab-895d-d1ba4613d648")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TaggedValue.class;
        
    }

    @objid ("f43a1f0a-bf7e-4741-bc67-ac9d3e3fee55")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("87f89b61-0cd1-4cb3-958a-7699a8c69cfc")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("c68b7229-45c0-43c0-8361-2f5107317216")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new TaggedValueObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.actualDep = new ActualSmDependency();
        this.actualDep.init("Actual", this, metamodel.getMClass(TagParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.actualDep);
        
        this.qualifierDep = new QualifierSmDependency();
        this.qualifierDep.init("Qualifier", this, metamodel.getMClass(TagParameter.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.qualifierDep);
        
        this.definitionDep = new DefinitionSmDependency();
        this.definitionDep.init("Definition", this, metamodel.getMClass(TagType.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.definitionDep);
        
        this.annotedDep = new AnnotedSmDependency();
        this.annotedDep.init("Annoted", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 );
        registerDependency(this.annotedDep);
        
        
    }

    @objid ("4f83f205-afb0-4d25-89af-542664438048")
    public SmDependency getActualDep() {
        if (this.actualDep == null) {
        	this.actualDep = this.getDependencyDef("Actual");
        }
        return this.actualDep;
    }

    @objid ("9a18d086-a09f-4438-9546-e05638302808")
    public SmDependency getQualifierDep() {
        if (this.qualifierDep == null) {
        	this.qualifierDep = this.getDependencyDef("Qualifier");
        }
        return this.qualifierDep;
    }

    @objid ("8ed48053-4ead-4b44-9821-204ea9d14e23")
    public SmDependency getDefinitionDep() {
        if (this.definitionDep == null) {
        	this.definitionDep = this.getDependencyDef("Definition");
        }
        return this.definitionDep;
    }

    @objid ("61ef0ca4-8a51-4bff-bdf7-526a1d0d45a6")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("e1a9d137-513a-48a2-89f4-0a7147e327e5")
    private static class TaggedValueObjectFactory implements ISmObjectFactory {
        @objid ("8b100960-6b09-4538-8f69-116a0ea6553c")
        private TaggedValueSmClass smClass;

        @objid ("1d5244bd-54bd-460a-8067-beef32cd50aa")
        public  TaggedValueObjectFactory(TaggedValueSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("db63c4cb-edd1-4c89-814a-a1710fd2ab67")
        @Override
        public ISmObjectData createData() {
            return new TaggedValueData(this.smClass);
        }

        @objid ("93c5e675-f3e8-4af2-9fd7-c96673fb273d")
        @Override
        public SmObjectImpl createImpl() {
            return new TaggedValueImpl();
        }

    }

    @objid ("6d16912f-38e7-47a4-828b-9be58b4acf04")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("9c0ca105-fa87-4831-883a-034136bd85e6")
        private SmDependency symetricDep;

        @objid ("360e9031-6c17-4fb8-82a5-d7e056c8abe3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mAnnoted;
        }

        @objid ("808bc7ef-f88f-488f-9233-c25d0958fda8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mAnnoted = value;
        }

        @objid ("a3269f2d-4fb7-4174-b0e4-83499c29faee")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getTagDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("06b8778e-28f9-446c-8214-a2ca481d375c")
    public static class ActualSmDependency extends SmMultipleDependency {
        @objid ("381092e8-3015-4edb-bf61-e7c51c7e7687")
        private SmDependency symetricDep;

        @objid ("75e657c4-e80e-49ab-a775-797ab0526f4a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TaggedValueData)data).mActual != null)? ((TaggedValueData)data).mActual:SmMultipleDependency.EMPTY;
        }

        @objid ("eb989244-02dd-4aa9-a466-4e39071bc12a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TaggedValueData) data).mActual = values;
            
        }

        @objid ("d33a937b-cb89-4255-9b35-618035ee70ee")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagParameterSmClass)this.getTarget()).getAnnotedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d07f8927-3f48-40c8-ab90-a874ea788489")
    public static class QualifierSmDependency extends SmSingleDependency {
        @objid ("db0eb104-9193-4dd5-a15d-236eedec8934")
        private SmDependency symetricDep;

        @objid ("4ee4c027-d491-4a89-a300-2a614c04c8bb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mQualifier;
        }

        @objid ("2a420ae3-8de6-4779-bd56-e0b9db2fc151")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mQualifier = value;
        }

        @objid ("fa75eda1-3065-4d85-b69f-7e5b191e2cca")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagParameterSmClass)this.getTarget()).getQualifiedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a99b11c4-7b6a-4998-b18b-235e88a7d675")
    public static class DefinitionSmDependency extends SmSingleDependency {
        @objid ("f47971da-994c-444c-a555-2a479e4e4306")
        private SmDependency symetricDep;

        @objid ("ab56d3da-b471-4c84-851e-eae0d79e9b5c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mDefinition;
        }

        @objid ("df12b804-84f7-4348-8a14-d9b495c7987a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mDefinition = value;
        }

        @objid ("3c8c55ea-1e91-4836-839e-14179687bd6c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagTypeSmClass)this.getTarget()).getTagOccurenceDep();
            }
            return this.symetricDep;
            
        }

    }

}
