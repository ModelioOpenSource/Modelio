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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventData;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeSmClass;
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

@objid ("b176ad0b-b65b-4dd5-9dad-1af827c191b1")
public class BpmnEventSmClass extends BpmnFlowNodeSmClass {
    @objid ("4161ab47-7113-4a9c-abfe-46e6b382751f")
    private SmDependency eventDefinitionsDep;

    @objid ("d7cf49e3-f1d5-48c8-b7dc-b0b29825e007")
    public BpmnEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("31fd2b67-695c-4275-bca6-953a2daa9da5")
    @Override
    public String getName() {
        return "BpmnEvent";
    }

    @objid ("af23d280-913b-4c65-80f5-4154238bf1df")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("81223ec5-91ac-4f30-974c-aa0fce814111")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEvent.class;
    }

    @objid ("3888c6f4-0c99-485f-8133-d40fa67ec466")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3f2cb934-b40c-4ce5-8585-58e0df0cb7f2")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("ed27cbce-2fd4-4dc1-ab0d-0d4d828ba3bc")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnFlowNode.MQNAME);
        this.registerFactory(new BpmnEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.eventDefinitionsDep = new EventDefinitionsSmDependency();
        this.eventDefinitionsDep.init("EventDefinitions", this, metamodel.getMClass(BpmnEventDefinition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.eventDefinitionsDep);
    }

    @objid ("33bc1322-c411-498a-a51a-ad0edf4fb339")
    public SmDependency getEventDefinitionsDep() {
        if (this.eventDefinitionsDep == null) {
        	this.eventDefinitionsDep = this.getDependencyDef("EventDefinitions");
        }
        return this.eventDefinitionsDep;
    }

    @objid ("0f092c62-798b-483b-b633-71830ca058cb")
    private static class BpmnEventObjectFactory implements ISmObjectFactory {
        @objid ("dfa09baf-36c8-4ea9-b063-a0c4ca31fd3e")
        private BpmnEventSmClass smClass;

        @objid ("64123256-871a-4640-ab78-a6cd621de2c7")
        public BpmnEventObjectFactory(BpmnEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9c7ca479-7994-49c6-91be-9dffd493f1a0")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("5e70b9fa-d5fe-4d0e-85aa-af9cdbde496d")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e88e2a7d-7395-4308-ad8f-ddbf7a513642")
    public static class EventDefinitionsSmDependency extends SmMultipleDependency {
        @objid ("03db9718-71a1-4670-94d6-976375011269")
        private SmDependency symetricDep;

        @objid ("c82acb1a-a58c-430c-99f4-594a524d2866")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnEventData)data).mEventDefinitions != null)? ((BpmnEventData)data).mEventDefinitions:SmMultipleDependency.EMPTY;
        }

        @objid ("ffe6542d-0c24-41ae-a683-13b346f92c7e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnEventData) data).mEventDefinitions = values;
        }

        @objid ("6f8d5ecc-bdab-4abb-b8d1-baed83662a62")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnEventDefinitionSmClass)this.getTarget()).getDefinedDep();
            }
            return this.symetricDep;
        }

    }

}
