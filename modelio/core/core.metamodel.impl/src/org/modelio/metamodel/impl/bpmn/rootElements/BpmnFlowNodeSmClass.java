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

package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleSmClass;
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

@objid ("099437a9-c4b0-48dd-8511-54c9ea9b442e")
public class BpmnFlowNodeSmClass extends BpmnFlowElementSmClass {
    @objid ("d217a7cc-d201-4c79-8da1-43278d816fef")
    private SmDependency outgoingDep;

    @objid ("d1463ea2-d026-426d-bff4-282c322e272a")
    private SmDependency resourceDep;

    @objid ("e4ce15ab-4215-4796-b1d5-890c57e10267")
    private SmDependency incomingDep;

    @objid ("0fa75122-9103-4f7b-8653-9fb75173dca7")
    public  BpmnFlowNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ff5a0a8d-865d-4d81-bdf3-9973acc92c2d")
    @Override
    public String getName() {
        return "BpmnFlowNode";
        
    }

    @objid ("72df2d02-074a-45ad-801c-64168414e5b7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d58a7fef-5caf-4f3b-a81b-1eb7f6094f25")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnFlowNode.class;
        
    }

    @objid ("81d1d7f8-5d8c-4a7a-8f70-71ad3cb8cfeb")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("b35424c0-d4ff-4794-977c-651285ea1df1")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("5d96c582-0a0b-40be-9329-9a564f23b7f1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnFlowElement.MQNAME);
        this.registerFactory(new BpmnFlowNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.outgoingDep = new OutgoingSmDependency();
        this.outgoingDep.init("Outgoing", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.outgoingDep);
        
        this.resourceDep = new ResourceSmDependency();
        this.resourceDep.init("Resource", this, metamodel.getMClass(BpmnResourceRole.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.resourceDep);
        
        this.incomingDep = new IncomingSmDependency();
        this.incomingDep.init("Incoming", this, metamodel.getMClass(BpmnSequenceFlow.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.incomingDep);
        
        
    }

    @objid ("3ebfd166-01c8-4fc3-a300-959d5a5f3dd7")
    public SmDependency getOutgoingDep() {
        if (this.outgoingDep == null) {
        	this.outgoingDep = this.getDependencyDef("Outgoing");
        }
        return this.outgoingDep;
    }

    @objid ("307080d9-b2c4-48e8-8712-da23056172e0")
    public SmDependency getResourceDep() {
        if (this.resourceDep == null) {
        	this.resourceDep = this.getDependencyDef("Resource");
        }
        return this.resourceDep;
    }

    @objid ("6043a415-c687-4053-af96-31042d143a11")
    public SmDependency getIncomingDep() {
        if (this.incomingDep == null) {
        	this.incomingDep = this.getDependencyDef("Incoming");
        }
        return this.incomingDep;
    }

    @objid ("a9d53b0e-f2a9-44f7-bcfc-bb5a7a92af15")
    private static class BpmnFlowNodeObjectFactory implements ISmObjectFactory {
        @objid ("e76eb9c1-c26c-4429-ad4d-0759aea19a0e")
        private BpmnFlowNodeSmClass smClass;

        @objid ("a438b4f5-f554-4477-b23a-5181cbbdff8f")
        public  BpmnFlowNodeObjectFactory(BpmnFlowNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2c3e350e-cec1-4aa6-9c39-819efbe742bf")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("35476471-718a-4f78-ad7f-51fc4fe3e1af")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("0c939c36-4ac9-4718-ac28-932b475c79e4")
    public static class OutgoingSmDependency extends SmMultipleDependency {
        @objid ("b81cf295-ba15-48c3-b36e-cf8972fd6be7")
        private SmDependency symetricDep;

        @objid ("66b0111e-1338-471f-a7b1-8cf29592066b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnFlowNodeData)data).mOutgoing != null)? ((BpmnFlowNodeData)data).mOutgoing:SmMultipleDependency.EMPTY;
        }

        @objid ("182b51a5-5a99-47a0-a0f8-2b1dd0fef18a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnFlowNodeData) data).mOutgoing = values;
            
        }

        @objid ("c40a1ba3-b8a9-4112-a625-18a279572828")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getSourceRefDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("23080d3b-a187-4d65-bb86-082e7d7b0ada")
    public static class ResourceSmDependency extends SmMultipleDependency {
        @objid ("4def17c0-fb67-464a-8645-d803eb5076d5")
        private SmDependency symetricDep;

        @objid ("dda765ee-8f39-40bd-a6c4-f39dabbb7e7b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnFlowNodeData)data).mResource != null)? ((BpmnFlowNodeData)data).mResource:SmMultipleDependency.EMPTY;
        }

        @objid ("2fd90e57-082f-4bfb-a595-5a085b06c20c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnFlowNodeData) data).mResource = values;
            
        }

        @objid ("0085b57b-04d7-4471-b342-733a2ae5da7d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceRoleSmClass)this.getTarget()).getAnnotatedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("e1f7770f-0139-46f8-9bf8-944cb24621ac")
    public static class IncomingSmDependency extends SmMultipleDependency {
        @objid ("ce55f97f-e52b-459b-a773-b43e41e2caa4")
        private SmDependency symetricDep;

        @objid ("73ffae86-15f9-461d-b071-880bc5f3f31c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnFlowNodeData)data).mIncoming != null)? ((BpmnFlowNodeData)data).mIncoming:SmMultipleDependency.EMPTY;
        }

        @objid ("3fd57bce-473b-4244-b8cb-f3fba8cdda48")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnFlowNodeData) data).mIncoming = values;
            
        }

        @objid ("dce68c8c-9e6a-4bf1-9337-db0e184278f9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSequenceFlowSmClass)this.getTarget()).getTargetRefDep();
            }
            return this.symetricDep;
            
        }

    }

}
