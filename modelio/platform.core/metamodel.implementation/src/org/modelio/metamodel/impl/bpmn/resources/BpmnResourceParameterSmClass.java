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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterBindingSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterData;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
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

@objid ("5a6847cb-a94a-4380-b40b-1c6bd8001307")
public class BpmnResourceParameterSmClass extends BpmnBaseElementSmClass {
    @objid ("2fda4c56-22ec-4ea5-a889-0cbb7c0818d2")
    private SmAttribute isRequiredAtt;

    @objid ("a592baa5-ba26-4470-859c-f8b8268801e0")
    private SmDependency resourceDep;

    @objid ("fa2d87c1-5c0a-4742-b8cc-e67809595bf2")
    private SmDependency typeDep;

    @objid ("ffab0108-6595-4e55-a8b1-c8edf2f7e68c")
    private SmDependency parameterBindingRefsDep;

    @objid ("3e2d27c7-42b9-4fe8-a170-6963e978365f")
    public BpmnResourceParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("70f7fca7-62ff-4df0-b091-e4a05e7f8379")
    @Override
    public String getName() {
        return "BpmnResourceParameter";
    }

    @objid ("66e5b042-10eb-4486-80cd-0d72cb707cea")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0e98507f-0adc-4d9a-a37e-d96e6a33e245")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnResourceParameter.class;
    }

    @objid ("d5b5f784-3d0a-4d5f-a538-ac86b09d1eb3")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4dcd7ef2-510f-4f2e-aab4-6a0f78a52639")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("02535a69-979a-492a-ad93-3be2977d95a6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnResourceParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isRequiredAtt = new IsRequiredSmAttribute();
        this.isRequiredAtt.init("IsRequired", this, Boolean.class );
        registerAttribute(this.isRequiredAtt);
        
        
        // Initialize and register the SmDependency
        this.resourceDep = new ResourceSmDependency();
        this.resourceDep.init("Resource", this, metamodel.getMClass(BpmnResource.MQNAME), 1, 1 );
        registerDependency(this.resourceDep);
        
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(BpmnItemDefinition.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.parameterBindingRefsDep = new ParameterBindingRefsSmDependency();
        this.parameterBindingRefsDep.init("ParameterBindingRefs", this, metamodel.getMClass(BpmnResourceParameterBinding.MQNAME), 0, -1 );
        registerDependency(this.parameterBindingRefsDep);
    }

    @objid ("9f75aea3-eaf5-45d0-b384-04f28e6fcd54")
    public SmAttribute getIsRequiredAtt() {
        if (this.isRequiredAtt == null) {
        	this.isRequiredAtt = this.getAttributeDef("IsRequired");
        }
        return this.isRequiredAtt;
    }

    @objid ("bd951e8a-b778-4056-a83f-79efe63f1d05")
    public SmDependency getResourceDep() {
        if (this.resourceDep == null) {
        	this.resourceDep = this.getDependencyDef("Resource");
        }
        return this.resourceDep;
    }

    @objid ("dd6951c6-8ef2-4e6c-9313-34ab9fd625ac")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("1621e862-d9ba-49f1-b6fb-5027098f3e84")
    public SmDependency getParameterBindingRefsDep() {
        if (this.parameterBindingRefsDep == null) {
        	this.parameterBindingRefsDep = this.getDependencyDef("ParameterBindingRefs");
        }
        return this.parameterBindingRefsDep;
    }

    @objid ("fd2e4392-3eb8-4918-b347-ff8e674b3066")
    private static class BpmnResourceParameterObjectFactory implements ISmObjectFactory {
        @objid ("0b8af42f-7561-4b26-b94f-4d939d35443d")
        private BpmnResourceParameterSmClass smClass;

        @objid ("4d4b2eff-0f33-4e03-a15a-d063a44dd0bc")
        public BpmnResourceParameterObjectFactory(BpmnResourceParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0cd4dde6-a750-4455-8639-0ab9aaca0a25")
        @Override
        public ISmObjectData createData() {
            return new BpmnResourceParameterData(this.smClass);
        }

        @objid ("e5c69d2e-4f76-43ab-bfd5-88aeb85144ed")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnResourceParameterImpl();
        }

    }

    @objid ("d34da7f2-beed-4b00-b135-97bf8ce930d8")
    public static class IsRequiredSmAttribute extends SmAttribute {
        @objid ("75571679-01bf-4386-ad59-1a494924c779")
        public Object getValue(ISmObjectData data) {
            return ((BpmnResourceParameterData) data).mIsRequired;
        }

        @objid ("acb622b8-2aa5-4b49-b25e-0d5408595535")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnResourceParameterData) data).mIsRequired = value;
        }

    }

    @objid ("6c839190-49c1-4992-b384-a98004a26245")
    public static class ResourceSmDependency extends SmSingleDependency {
        @objid ("d66dc81b-f59b-4b29-8dec-2fdfe9aa505d")
        private SmDependency symetricDep;

        @objid ("b11d3e34-ddcc-4256-8456-effd557fcbc3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceParameterData) data).mResource;
        }

        @objid ("85942be4-18c2-4c71-962a-36fff3353251")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceParameterData) data).mResource = value;
        }

        @objid ("8a7ad566-9e5f-4818-9244-9dd608eb473f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceSmClass)this.getTarget()).getParameterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("db898e57-b3f6-47c1-a65e-5a27906c6b6c")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("c296a76b-ea2e-4828-826c-f85e2e853839")
        private SmDependency symetricDep;

        @objid ("99983881-6f0f-4d33-aae6-94ac4ca3798e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceParameterData) data).mType;
        }

        @objid ("8970c654-5f10-4bb7-bf69-1bf99038829d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceParameterData) data).mType = value;
        }

        @objid ("fb18e7af-bd3c-47e4-aa53-68f8cee215ac")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemDefinitionSmClass)this.getTarget()).getTypedResourceParameterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c29f324b-ea03-4794-a792-a4e42d0fd264")
    public static class ParameterBindingRefsSmDependency extends SmMultipleDependency {
        @objid ("77faea32-d773-4085-b77d-47875fcb6e6a")
        private SmDependency symetricDep;

        @objid ("46572066-4bdc-40b1-a1d6-0341b3e7ce51")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnResourceParameterData)data).mParameterBindingRefs != null)? ((BpmnResourceParameterData)data).mParameterBindingRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("2ce3d930-a8fd-442d-a31d-d3ff4533478f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnResourceParameterData) data).mParameterBindingRefs = values;
        }

        @objid ("8bbd5673-aac5-4bae-88cd-b20e3b17a21f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceParameterBindingSmClass)this.getTarget()).getParameterRefDep();
            }
            return this.symetricDep;
        }

    }

}
