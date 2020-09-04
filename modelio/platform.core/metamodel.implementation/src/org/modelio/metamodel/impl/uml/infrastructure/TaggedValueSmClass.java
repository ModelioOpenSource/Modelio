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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagParameterSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueData;
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
    @objid ("62fb00f1-8039-4a47-945c-c3ead688303b")
    private SmDependency actualDep;

    @objid ("e06aa53b-570e-47b2-a927-201917797e25")
    private SmDependency qualifierDep;

    @objid ("09069e3b-f0dd-4b6a-bab6-b4f6a0569a86")
    private SmDependency definitionDep;

    @objid ("b356f4d3-9046-4fbf-ae27-849f9ceb33aa")
    private SmDependency annotedDep;

    @objid ("ff77d1af-2887-4a9d-8e24-cc89168c7fcd")
    public TaggedValueSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6ed68056-fc6a-4be3-be70-e512681d8f9a")
    @Override
    public String getName() {
        return "TaggedValue";
    }

    @objid ("9a61e86b-f295-404b-a65e-56a0010525d3")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a2b85695-55a6-41bd-95d7-a0f956d25126")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TaggedValue.class;
    }

    @objid ("b62f9d8d-9182-470a-9a68-6cdfd6cf8487")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a0755e94-f609-4b71-b693-9e8756887dbe")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("546b10fd-6463-4814-8b51-4368b22fdab0")
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

    @objid ("416a5d8e-5357-41b5-be70-9e2f7afecbf3")
    public SmDependency getActualDep() {
        if (this.actualDep == null) {
        	this.actualDep = this.getDependencyDef("Actual");
        }
        return this.actualDep;
    }

    @objid ("eaebcd31-9e79-469c-9ef0-08e7c5271222")
    public SmDependency getQualifierDep() {
        if (this.qualifierDep == null) {
        	this.qualifierDep = this.getDependencyDef("Qualifier");
        }
        return this.qualifierDep;
    }

    @objid ("1e9858b4-9521-4878-b887-2d8f3b074f8f")
    public SmDependency getDefinitionDep() {
        if (this.definitionDep == null) {
        	this.definitionDep = this.getDependencyDef("Definition");
        }
        return this.definitionDep;
    }

    @objid ("2bf7e691-8861-4ba0-b807-768860883cc8")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("e1a9d137-513a-48a2-89f4-0a7147e327e5")
    private static class TaggedValueObjectFactory implements ISmObjectFactory {
        @objid ("9e6fc176-1b5d-4a47-a620-3bd5dc0b0db3")
        private TaggedValueSmClass smClass;

        @objid ("bb2baf48-720b-42b3-a086-10486804c62e")
        public TaggedValueObjectFactory(TaggedValueSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("526f7b48-c25b-4905-b6b2-6f6b81f4e093")
        @Override
        public ISmObjectData createData() {
            return new TaggedValueData(this.smClass);
        }

        @objid ("8b9f54b9-9daf-4162-9205-b89dcaea8249")
        @Override
        public SmObjectImpl createImpl() {
            return new TaggedValueImpl();
        }

    }

    @objid ("6d16912f-38e7-47a4-828b-9be58b4acf04")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("04df32d4-48ef-4aeb-bfc3-141f71ebcc3d")
        private SmDependency symetricDep;

        @objid ("862568d1-6ec0-4ce8-b57a-48388ab1a399")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mAnnoted;
        }

        @objid ("b9ab021b-6c57-4c62-98ff-8851a7125aaf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mAnnoted = value;
        }

        @objid ("57edb7c0-24b6-4b39-b30a-1e757993aa30")
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
        @objid ("37fe6dd9-faa5-439b-a587-cf8aeb23116b")
        private SmDependency symetricDep;

        @objid ("247b5edc-01e2-4117-86c7-f7f76609376c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TaggedValueData)data).mActual != null)? ((TaggedValueData)data).mActual:SmMultipleDependency.EMPTY;
        }

        @objid ("9a718ab7-9c70-4d76-b5f8-268840493495")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TaggedValueData) data).mActual = values;
        }

        @objid ("585f555a-bbd0-416c-acff-1e4bf39d8678")
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
        @objid ("590bf095-b180-4140-be19-d88677c0a16f")
        private SmDependency symetricDep;

        @objid ("f73dd023-074f-428a-8e44-2ba0f4ef553e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mQualifier;
        }

        @objid ("ba1f5033-0b55-41fa-bf83-a5356e81945f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mQualifier = value;
        }

        @objid ("aceb541e-df4e-4b59-821a-1d1282ef24cf")
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
        @objid ("6f718a92-f9f9-4f4d-95d8-9233c9640da8")
        private SmDependency symetricDep;

        @objid ("50a26fe9-32f7-4562-a980-37c56934f028")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mDefinition;
        }

        @objid ("322a336b-339f-417a-8a8d-99e9379b82a1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mDefinition = value;
        }

        @objid ("8c0fb3fe-76b2-4f6a-8f10-0e281d82bed2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagTypeSmClass)this.getTarget()).getTagOccurenceDep();
            }
            return this.symetricDep;
        }

    }

}
