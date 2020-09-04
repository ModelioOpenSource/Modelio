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
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceData;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c1f59956-870a-44c7-bfe4-146d808cbdbe")
public class BpmnResourceSmClass extends BpmnSharedElementSmClass {
    @objid ("8e4c7419-31d0-43d3-a0d0-f2d1831da7c6")
    private SmDependency resourceroleRefsDep;

    @objid ("2e10c271-757c-435f-aed5-ce94cf9056a5")
    private SmDependency parameterDep;

    @objid ("97ff7eeb-52e1-4e06-8b72-ab94c476afbb")
    public BpmnResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4a64c228-04a2-4871-8641-fce247dfd6d3")
    @Override
    public String getName() {
        return "BpmnResource";
    }

    @objid ("0749d2d4-b9ce-4b70-b648-204da4a2a437")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("230a97a3-4f59-42a1-8bb5-72b1eb032d17")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnResource.class;
    }

    @objid ("16bdc9ec-3370-4861-8465-404e3e24aa10")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("d3d0dbba-625e-4160-8bfc-2486f976f5af")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c3c2ca11-88c1-47f8-868e-4e73f26fe742")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSharedElement.MQNAME);
        this.registerFactory(new BpmnResourceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.resourceroleRefsDep = new ResourceroleRefsSmDependency();
        this.resourceroleRefsDep.init("ResourceroleRefs", this, metamodel.getMClass(BpmnResourceRole.MQNAME), 0, -1 );
        registerDependency(this.resourceroleRefsDep);
        
        this.parameterDep = new ParameterSmDependency();
        this.parameterDep.init("Parameter", this, metamodel.getMClass(BpmnResourceParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parameterDep);
    }

    @objid ("4d0db960-0315-437c-9a11-bd3c0496112f")
    public SmDependency getResourceroleRefsDep() {
        if (this.resourceroleRefsDep == null) {
        	this.resourceroleRefsDep = this.getDependencyDef("ResourceroleRefs");
        }
        return this.resourceroleRefsDep;
    }

    @objid ("d99d8246-5e7c-4af5-ab2b-d843599b0628")
    public SmDependency getParameterDep() {
        if (this.parameterDep == null) {
        	this.parameterDep = this.getDependencyDef("Parameter");
        }
        return this.parameterDep;
    }

    @objid ("eb39271a-cc23-4083-9253-c22071448d50")
    private static class BpmnResourceObjectFactory implements ISmObjectFactory {
        @objid ("6ae83ae7-9775-4856-88d8-a4528e831960")
        private BpmnResourceSmClass smClass;

        @objid ("1b92e459-0194-4e4c-9463-ee354f4c016c")
        public BpmnResourceObjectFactory(BpmnResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("857d183b-fc2f-45d3-8234-d21dbd00ed3b")
        @Override
        public ISmObjectData createData() {
            return new BpmnResourceData(this.smClass);
        }

        @objid ("ede8fe79-e99e-4662-8187-e3aebe0b907d")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnResourceImpl();
        }

    }

    @objid ("cbb529de-8aa9-420c-960d-e27e161207ad")
    public static class ResourceroleRefsSmDependency extends SmMultipleDependency {
        @objid ("d4fcfa5d-7b67-414e-8f0d-0ffa4470f835")
        private SmDependency symetricDep;

        @objid ("bedbbff4-46d2-46a4-967f-5adfe553b62c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnResourceData)data).mResourceroleRefs != null)? ((BpmnResourceData)data).mResourceroleRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("3d6beaf3-8320-48c7-92f9-62658ee34b54")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnResourceData) data).mResourceroleRefs = values;
        }

        @objid ("26978cea-3414-49bd-af28-676a7b253efd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceRoleSmClass)this.getTarget()).getResourceRefDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("423189b3-1d27-427e-9115-710f00724fd3")
    public static class ParameterSmDependency extends SmMultipleDependency {
        @objid ("253508b8-1457-4951-94d7-3895699da3a8")
        private SmDependency symetricDep;

        @objid ("d9633106-9664-4d70-a004-e9bd9f215288")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnResourceData)data).mParameter != null)? ((BpmnResourceData)data).mParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("865ae6cb-9fc4-4b36-9ca5-a610a94da614")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnResourceData) data).mParameter = values;
        }

        @objid ("7ae17772-4105-45db-8be8-9b7790157217")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceParameterSmClass)this.getTarget()).getResourceDep();
            }
            return this.symetricDep;
        }

    }

}
