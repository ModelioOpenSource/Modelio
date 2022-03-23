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

package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b6cefc17-8520-4a81-aa9d-d15c52ef0901")
public class BpmnInclusiveGatewaySmClass extends BpmnGatewaySmClass {
    @objid ("c2b84f95-3151-4f1a-a953-8f8dfb649dfc")
    private SmDependency defaultFlowDep;

    @objid ("b7355304-77f7-4254-8f96-019786b18d17")
    public  BpmnInclusiveGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9cfc5042-9d03-4d72-bc0c-1a8f9b528b23")
    @Override
    public String getName() {
        return "BpmnInclusiveGateway";
        
    }

    @objid ("ecae389c-b42a-4768-9af4-0420572b03f3")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("fc8247b7-52ce-4486-88d5-c8f500aabfac")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnInclusiveGateway.class;
        
    }

    @objid ("865c7865-c735-4e53-926d-cf67cdd65c91")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("182518d9-a892-4711-9557-7b047b340691")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("2628f81b-33d3-468a-bda1-e0605d8c83ae")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnGateway.MQNAME);
        this.registerFactory(new BpmnInclusiveGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.defaultFlowDep = new DefaultFlowSmDependency();
        this.defaultFlowDep.init("DefaultFlow", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.defaultFlowDep);
        
        
    }

    @objid ("7983e0b4-c933-4b9f-a260-ba34b4b87f92")
    public SmDependency getDefaultFlowDep() {
        if (this.defaultFlowDep == null) {
        	this.defaultFlowDep = this.getDependencyDef("DefaultFlow");
        }
        return this.defaultFlowDep;
    }

    @objid ("276012d4-e8de-4225-9182-6a5e36a988bf")
    private static class BpmnInclusiveGatewayObjectFactory implements ISmObjectFactory {
        @objid ("f5939318-9f80-4e46-a3ac-12809f97458a")
        private BpmnInclusiveGatewaySmClass smClass;

        @objid ("34632f1d-0ac0-4e74-ab71-efe56fc9b1ad")
        public  BpmnInclusiveGatewayObjectFactory(BpmnInclusiveGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("725aa72c-d314-4f18-b7fd-19b070a8d168")
        @Override
        public ISmObjectData createData() {
            return new BpmnInclusiveGatewayData(this.smClass);
        }

        @objid ("c93a7c5d-bfa8-4698-ace0-1b2cb554c149")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnInclusiveGatewayImpl();
        }

    }

    @objid ("e56de04b-e042-4e43-a50a-c9216bec3754")
    public static class DefaultFlowSmDependency extends SmSingleDependency {
        @objid ("aee24c7d-b3d1-4a6e-9812-f0650d4a9bfc")
        private SmDependency symetricDep;

        @objid ("511ecf9f-a6d4-48b9-9dec-f49cd65da5a2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnInclusiveGatewayData) data).mDefaultFlow;
        }

        @objid ("cc43a0a4-1ae4-4440-9f14-ba5dfab0dc2f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnInclusiveGatewayData) data).mDefaultFlow = value;
        }

        @objid ("f2e7dd99-c686-473c-a2f7-9354e7c355c2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getDefaultOfInclusiveDep();
            }
            return this.symetricDep;
            
        }

    }

}
