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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnComplexGatewayData;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewaySmClass;
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

@objid ("17ab5785-d5f9-4c2d-992a-883ea789b18b")
public class BpmnComplexGatewaySmClass extends BpmnGatewaySmClass {
    @objid ("49d97ab5-53ff-4bce-9e64-b7b924e09d92")
    private SmAttribute activationExpressionAtt;

    @objid ("48402463-f99d-46d3-af1e-3c6874ee7d14")
    private SmDependency defaultFlowDep;

    @objid ("44816c16-5507-4d94-b83a-e20b2bb58cc2")
    public BpmnComplexGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f8accdb1-8bbc-45b7-8707-af9a6032b4cb")
    @Override
    public String getName() {
        return "BpmnComplexGateway";
    }

    @objid ("1ebc6f3a-82ff-4422-a00b-a0330c1bd8ea")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("afa8b4c1-3c32-4c0b-b6a8-d12c13aaaa3c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnComplexGateway.class;
    }

    @objid ("c4ef2c93-6f10-40d9-ad40-90201844701e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0935e1fd-7616-4431-aeb9-a730fe6cffdf")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8f89cce0-bc53-4140-8fe9-c82ee4353ed8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnGateway.MQNAME);
        this.registerFactory(new BpmnComplexGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.activationExpressionAtt = new ActivationExpressionSmAttribute();
        this.activationExpressionAtt.init("ActivationExpression", this, String.class );
        registerAttribute(this.activationExpressionAtt);
        
        
        // Initialize and register the SmDependency
        this.defaultFlowDep = new DefaultFlowSmDependency();
        this.defaultFlowDep.init("DefaultFlow", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.defaultFlowDep);
    }

    @objid ("45ccf678-ea46-4c1b-86e4-30783b25de36")
    public SmAttribute getActivationExpressionAtt() {
        if (this.activationExpressionAtt == null) {
        	this.activationExpressionAtt = this.getAttributeDef("ActivationExpression");
        }
        return this.activationExpressionAtt;
    }

    @objid ("195251b9-5615-4468-b6d0-4c422b69f6bb")
    public SmDependency getDefaultFlowDep() {
        if (this.defaultFlowDep == null) {
        	this.defaultFlowDep = this.getDependencyDef("DefaultFlow");
        }
        return this.defaultFlowDep;
    }

    @objid ("13f57164-1a35-4ad0-8e85-bb9e5cf60411")
    private static class BpmnComplexGatewayObjectFactory implements ISmObjectFactory {
        @objid ("11517692-ee75-45af-891e-218ea0e26802")
        private BpmnComplexGatewaySmClass smClass;

        @objid ("2f036690-af8d-477c-8896-8b1d65dafb4f")
        public BpmnComplexGatewayObjectFactory(BpmnComplexGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1eddba90-7f9b-4a97-875a-d4e93ad53cc6")
        @Override
        public ISmObjectData createData() {
            return new BpmnComplexGatewayData(this.smClass);
        }

        @objid ("139245b4-4be8-4de4-87ab-436cf2e9b042")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnComplexGatewayImpl();
        }

    }

    @objid ("f074a2a1-aa33-4f91-b924-992724171b8f")
    public static class ActivationExpressionSmAttribute extends SmAttribute {
        @objid ("cbc3440c-e543-4d69-b9d4-3060ec4e02ab")
        public Object getValue(ISmObjectData data) {
            return ((BpmnComplexGatewayData) data).mActivationExpression;
        }

        @objid ("d4de7ccd-897d-4650-8fc2-14a7960004d0")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnComplexGatewayData) data).mActivationExpression = value;
        }

    }

    @objid ("d67bb597-b6ad-4969-8c19-6a7e23d60bb7")
    public static class DefaultFlowSmDependency extends SmSingleDependency {
        @objid ("829a4e74-b96d-4fb7-b941-3ef12bad2973")
        private SmDependency symetricDep;

        @objid ("f0938d96-86b9-480e-8fcd-4dc8cb7a4bec")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnComplexGatewayData) data).mDefaultFlow;
        }

        @objid ("93dded66-f2d1-46f1-86fa-3c07883591ed")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnComplexGatewayData) data).mDefaultFlow = value;
        }

        @objid ("edc1e501-bb4a-4f00-b411-f1865f0a34ef")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getDefaultOfComplexDep();
            }
            return this.symetricDep;
        }

    }

}
