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
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnExclusiveGatewayData;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewaySmClass;
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

@objid ("74ebbbc4-d063-450d-bcd0-a747eb364dff")
public class BpmnExclusiveGatewaySmClass extends BpmnGatewaySmClass {
    @objid ("3ad399f2-2aa7-4d0d-880e-24ad59383af4")
    private SmDependency defaultFlowDep;

    @objid ("f4eab8ba-9ec9-4d2b-80c8-a27589c98e87")
    public BpmnExclusiveGatewaySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e4944579-c7b5-4ddd-bc79-0201dd0e0ab3")
    @Override
    public String getName() {
        return "BpmnExclusiveGateway";
    }

    @objid ("5ce648a4-d056-4036-a8db-b710af807a61")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("34771590-6c36-4632-95c9-24d4b5f53e94")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnExclusiveGateway.class;
    }

    @objid ("744d00b6-8a38-4353-9063-0d7ece29efa7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7fd46f03-287f-4d7c-b7ed-a2b3d62d7032")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("bf6404b6-d858-4671-8cad-6254606ecd4e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnGateway.MQNAME);
        this.registerFactory(new BpmnExclusiveGatewayObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.defaultFlowDep = new DefaultFlowSmDependency();
        this.defaultFlowDep.init("DefaultFlow", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.defaultFlowDep);
    }

    @objid ("19174c75-2d0d-4957-bfb3-ca5dc975ab25")
    public SmDependency getDefaultFlowDep() {
        if (this.defaultFlowDep == null) {
        	this.defaultFlowDep = this.getDependencyDef("DefaultFlow");
        }
        return this.defaultFlowDep;
    }

    @objid ("e6a587e3-8edf-4642-8015-e2f247f7992f")
    private static class BpmnExclusiveGatewayObjectFactory implements ISmObjectFactory {
        @objid ("864bc20c-f44d-4a2a-ba8f-ca37887ebdd3")
        private BpmnExclusiveGatewaySmClass smClass;

        @objid ("82154a68-1705-477e-8134-4b844ca33fb8")
        public BpmnExclusiveGatewayObjectFactory(BpmnExclusiveGatewaySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("70a6364e-34aa-4fab-801e-c082b5bb70b1")
        @Override
        public ISmObjectData createData() {
            return new BpmnExclusiveGatewayData(this.smClass);
        }

        @objid ("dbd4b612-4fd5-4392-a093-8cca47733a01")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnExclusiveGatewayImpl();
        }

    }

    @objid ("c5cd96c6-3d50-4b4e-9b6b-acfe846d724b")
    public static class DefaultFlowSmDependency extends SmSingleDependency {
        @objid ("018f47cb-050b-4e12-842e-3db3190c7385")
        private SmDependency symetricDep;

        @objid ("b551f79b-7bbf-480b-b6e8-86a95de6f182")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnExclusiveGatewayData) data).mDefaultFlow;
        }

        @objid ("366b532d-d05c-4a70-9201-82f3874a553e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnExclusiveGatewayData) data).mDefaultFlow = value;
        }

        @objid ("5d27b2e4-f50a-45a3-8723-2cb16cd10ea1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getDefaultOfExclusiveDep();
            }
            return this.symetricDep;
        }

    }

}
