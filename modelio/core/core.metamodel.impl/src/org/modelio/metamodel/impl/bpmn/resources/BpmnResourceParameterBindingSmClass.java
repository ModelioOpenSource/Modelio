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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterBindingData;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleSmClass;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1b609f5e-944b-4853-9119-aa7754ed0e90")
public class BpmnResourceParameterBindingSmClass extends BpmnBaseElementSmClass {
    @objid ("b257800b-0a87-404f-b0cb-e5a081c58f90")
    private SmAttribute expressionAtt;

    @objid ("8ce89645-9a01-41e8-afc3-b3754e1811fb")
    private SmDependency resourceRoleDep;

    @objid ("cf3374af-fcd3-4e4e-a89f-6717ac715af9")
    private SmDependency parameterRefDep;

    @objid ("14d662e3-77a2-4baf-9793-ce49a7f745bd")
    public BpmnResourceParameterBindingSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c0731dfb-c098-4e49-a1ac-1220f9b61b52")
    @Override
    public String getName() {
        return "BpmnResourceParameterBinding";
    }

    @objid ("68e1d287-24df-41eb-8d45-49cc95942c84")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("21070ad1-07c1-4c60-95a1-bdc0bdb32ee7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnResourceParameterBinding.class;
    }

    @objid ("ab5fb6c1-d9cd-4d05-aa1d-fcb2ae2ea6f7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5374aa5d-c8be-4dbb-9fcf-a2bdd9646ebf")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("510cff72-1ca9-4dfb-983a-cef2bbd5975b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnResourceParameterBindingObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.expressionAtt = new ExpressionSmAttribute();
        this.expressionAtt.init("Expression", this, String.class );
        registerAttribute(this.expressionAtt);
        
        
        // Initialize and register the SmDependency
        this.resourceRoleDep = new ResourceRoleSmDependency();
        this.resourceRoleDep.init("ResourceRole", this, metamodel.getMClass(BpmnResourceRole.MQNAME), 1, 1 );
        registerDependency(this.resourceRoleDep);
        
        this.parameterRefDep = new ParameterRefSmDependency();
        this.parameterRefDep.init("ParameterRef", this, metamodel.getMClass(BpmnResourceParameter.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.parameterRefDep);
    }

    @objid ("27478274-bf12-4733-b50a-80be9dcd9482")
    public SmAttribute getExpressionAtt() {
        if (this.expressionAtt == null) {
        	this.expressionAtt = this.getAttributeDef("Expression");
        }
        return this.expressionAtt;
    }

    @objid ("ddac7cec-261b-4341-a155-10afe9f7a1bb")
    public SmDependency getResourceRoleDep() {
        if (this.resourceRoleDep == null) {
        	this.resourceRoleDep = this.getDependencyDef("ResourceRole");
        }
        return this.resourceRoleDep;
    }

    @objid ("441889dc-61f8-439e-8732-69408c09f446")
    public SmDependency getParameterRefDep() {
        if (this.parameterRefDep == null) {
        	this.parameterRefDep = this.getDependencyDef("ParameterRef");
        }
        return this.parameterRefDep;
    }

    @objid ("216d62a9-7a81-4cf8-a192-126970663acf")
    private static class BpmnResourceParameterBindingObjectFactory implements ISmObjectFactory {
        @objid ("afcea21c-ce75-411d-99e9-51668fb900fc")
        private BpmnResourceParameterBindingSmClass smClass;

        @objid ("52fec0cd-9026-4b80-82fb-957df0429084")
        public BpmnResourceParameterBindingObjectFactory(BpmnResourceParameterBindingSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("89e5c7f4-db61-4a3d-be36-80bb08b689e6")
        @Override
        public ISmObjectData createData() {
            return new BpmnResourceParameterBindingData(this.smClass);
        }

        @objid ("2c206f38-ba26-405c-9020-3c89e60eb23e")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnResourceParameterBindingImpl();
        }

    }

    @objid ("c9eb623a-7a0e-4881-8824-86e747038b5c")
    public static class ExpressionSmAttribute extends SmAttribute {
        @objid ("77b90615-9f5d-4c03-8339-39e2a984ec11")
        public Object getValue(ISmObjectData data) {
            return ((BpmnResourceParameterBindingData) data).mExpression;
        }

        @objid ("824f1a81-c57f-44b9-8b7b-0cc72654669c")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnResourceParameterBindingData) data).mExpression = value;
        }

    }

    @objid ("b55042d9-caff-4135-8634-45c56671fd03")
    public static class ResourceRoleSmDependency extends SmSingleDependency {
        @objid ("d184a216-f973-4a22-a6ec-2ab00401f3e3")
        private SmDependency symetricDep;

        @objid ("aeb602c8-2b65-4ba3-9226-d428655db8ae")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceParameterBindingData) data).mResourceRole;
        }

        @objid ("0e06f452-cad3-4465-bb84-7431cabd3d00")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceParameterBindingData) data).mResourceRole = value;
        }

        @objid ("e4b2f05f-cd7b-4b00-bdda-6996f7c94d9b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceRoleSmClass)this.getTarget()).getResourceParameterBindingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e43d9261-c6a0-46e2-9ca5-345e6f193d19")
    public static class ParameterRefSmDependency extends SmSingleDependency {
        @objid ("7c3237df-16bd-4c39-8e3c-366c53073dc7")
        private SmDependency symetricDep;

        @objid ("1c62cfc7-b2bd-4777-84b8-cefb5b1f6612")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnResourceParameterBindingData) data).mParameterRef;
        }

        @objid ("553e6ff3-d069-44a9-9822-c81d2fc8468e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnResourceParameterBindingData) data).mParameterRef = value;
        }

        @objid ("21d2491a-6fd9-47e1-86a3-ef578b0b0e3e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceParameterSmClass)this.getTarget()).getParameterBindingRefsDep();
            }
            return this.symetricDep;
        }

    }

}
