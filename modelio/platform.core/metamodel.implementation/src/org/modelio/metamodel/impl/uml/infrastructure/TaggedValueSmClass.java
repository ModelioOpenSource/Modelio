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
    @objid ("ba7b4373-a0d2-406f-bc28-3665e905c001")
    private SmDependency actualDep;

    @objid ("297bace4-cc40-493c-a148-c07dbfdebd1a")
    private SmDependency qualifierDep;

    @objid ("708d2b1f-c7fd-4cc6-b645-f3510df1eab6")
    private SmDependency definitionDep;

    @objid ("8ea5a849-ade2-426d-ab1c-ce0cb63e6722")
    private SmDependency annotedDep;

    @objid ("00ccd356-b0a3-4a07-b1fd-fba18578ca08")
    public TaggedValueSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b3293dcd-3ad5-4bd8-a34d-e26929e3af66")
    @Override
    public String getName() {
        return "TaggedValue";
    }

    @objid ("01159109-db8e-4e24-aa09-ea3070555dbd")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1fdfb18b-15f7-4f87-bf24-2fedb256bf27")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TaggedValue.class;
    }

    @objid ("6d6d770a-1634-4185-8928-74e84a8f9633")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e809dcb7-35ae-4c43-a596-4eee1ea0b7a7")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6aef3595-6842-4311-9149-a50f79a4dfec")
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

    @objid ("2a07e55e-beec-4d2d-b8fc-b5d26c3cfe10")
    public SmDependency getActualDep() {
        if (this.actualDep == null) {
        	this.actualDep = this.getDependencyDef("Actual");
        }
        return this.actualDep;
    }

    @objid ("3c6392fa-ba47-4090-bb12-66e67daa02d5")
    public SmDependency getQualifierDep() {
        if (this.qualifierDep == null) {
        	this.qualifierDep = this.getDependencyDef("Qualifier");
        }
        return this.qualifierDep;
    }

    @objid ("50c368dc-a658-4195-8080-17ead219b8dc")
    public SmDependency getDefinitionDep() {
        if (this.definitionDep == null) {
        	this.definitionDep = this.getDependencyDef("Definition");
        }
        return this.definitionDep;
    }

    @objid ("f69f881f-de0b-444d-8018-a535c2e4b93e")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("e1a9d137-513a-48a2-89f4-0a7147e327e5")
    private static class TaggedValueObjectFactory implements ISmObjectFactory {
        @objid ("66ee1230-b55d-472b-8290-9b482e6d4008")
        private TaggedValueSmClass smClass;

        @objid ("63471103-309c-4492-809c-ddce4c8813e2")
        public TaggedValueObjectFactory(TaggedValueSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d1e2f39d-4053-4f9c-bd83-b4a56f32dfbc")
        @Override
        public ISmObjectData createData() {
            return new TaggedValueData(this.smClass);
        }

        @objid ("c7470e07-6aee-4307-931b-116fe197f66b")
        @Override
        public SmObjectImpl createImpl() {
            return new TaggedValueImpl();
        }

    }

    @objid ("6d16912f-38e7-47a4-828b-9be58b4acf04")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("34dc45fb-572b-47e3-8d23-6a82fe0640d9")
        private SmDependency symetricDep;

        @objid ("8939b4ff-515f-43cc-a0ef-c1f3ad7f85b7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mAnnoted;
        }

        @objid ("420913d0-481f-4ed1-a5de-84f80edc0980")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mAnnoted = value;
        }

        @objid ("b17941eb-8867-4ded-a19e-8a556ef2e34b")
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
        @objid ("b8fdd308-5e1d-4894-9a50-f60ef1fd56dd")
        private SmDependency symetricDep;

        @objid ("3947ba8d-bc16-415f-9325-d64b54e1cd9a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TaggedValueData)data).mActual != null)? ((TaggedValueData)data).mActual:SmMultipleDependency.EMPTY;
        }

        @objid ("b4777487-d868-44a6-9b55-43129311c681")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TaggedValueData) data).mActual = values;
        }

        @objid ("93e0e48c-6f2a-4116-b298-b88fbaa75d22")
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
        @objid ("3f6c1cb6-af5f-476d-a4f4-4142c0fb1ddf")
        private SmDependency symetricDep;

        @objid ("1a62132c-75d4-43fe-8b9a-62b88acc3517")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mQualifier;
        }

        @objid ("90aa147d-12dd-41e0-a1c3-5c3b356849e7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mQualifier = value;
        }

        @objid ("7e98049e-dff2-4891-b870-65594344ba32")
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
        @objid ("994c4a47-2a97-4283-9bf0-cc5ee27e90f2")
        private SmDependency symetricDep;

        @objid ("dbc67a92-e7b9-407a-8477-6971c2c96016")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TaggedValueData) data).mDefinition;
        }

        @objid ("b4ae70dd-cf2f-4e59-b7ce-68a03fd49945")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TaggedValueData) data).mDefinition = value;
        }

        @objid ("d88acdde-7fdc-4995-83d9-19754e2d661d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TagTypeSmClass)this.getTarget()).getTagOccurenceDep();
            }
            return this.symetricDep;
        }

    }

}
