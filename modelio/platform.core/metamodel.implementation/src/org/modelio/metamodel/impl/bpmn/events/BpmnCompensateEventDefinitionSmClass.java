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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCompensateEventDefinitionData;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
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

@objid ("dae9bc16-62d9-4710-8180-d149139425f7")
public class BpmnCompensateEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("13528dea-cb84-419c-b4da-dcdb58a13d5e")
    private SmAttribute waitForCompletionAtt;

    @objid ("1b4496e6-43db-46a7-86f0-a0b6d3f59504")
    private SmDependency activityRefDep;

    @objid ("751ed04e-66e0-4efd-894a-b3391b4e607d")
    public BpmnCompensateEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4dac53d0-8b83-4262-829e-3697b52a80a8")
    @Override
    public String getName() {
        return "BpmnCompensateEventDefinition";
    }

    @objid ("f20fd1ff-1d46-4609-b2e1-26421f2e297c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("81a01494-60e7-41f0-8721-9663702d955f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCompensateEventDefinition.class;
    }

    @objid ("220696fb-7e51-4ee9-8ae3-27af88240b5d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e5de3a47-3f27-493b-9f88-61d22b5b771f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e376e359-12a8-439c-9fa3-8bd16f36233a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnCompensateEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.waitForCompletionAtt = new WaitForCompletionSmAttribute();
        this.waitForCompletionAtt.init("WaitForCompletion", this, String.class );
        registerAttribute(this.waitForCompletionAtt);
        
        
        // Initialize and register the SmDependency
        this.activityRefDep = new ActivityRefSmDependency();
        this.activityRefDep.init("ActivityRef", this, metamodel.getMClass(BpmnActivity.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.activityRefDep);
    }

    @objid ("855cecf5-4b61-4ba2-b25a-618c149e2046")
    public SmAttribute getWaitForCompletionAtt() {
        if (this.waitForCompletionAtt == null) {
        	this.waitForCompletionAtt = this.getAttributeDef("WaitForCompletion");
        }
        return this.waitForCompletionAtt;
    }

    @objid ("5919f7f3-a865-4f28-b9fc-b9d38f2884ee")
    public SmDependency getActivityRefDep() {
        if (this.activityRefDep == null) {
        	this.activityRefDep = this.getDependencyDef("ActivityRef");
        }
        return this.activityRefDep;
    }

    @objid ("3b31e472-bf20-4dff-bfc3-72aec4c5ad4a")
    private static class BpmnCompensateEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("3c926997-0cc4-493a-a95f-4aae8d7aa396")
        private BpmnCompensateEventDefinitionSmClass smClass;

        @objid ("dd258ef3-2a0a-4145-9b9e-c62bbef9df73")
        public BpmnCompensateEventDefinitionObjectFactory(BpmnCompensateEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4cbc21b7-d18a-43ed-a01e-8f086d0e24ce")
        @Override
        public ISmObjectData createData() {
            return new BpmnCompensateEventDefinitionData(this.smClass);
        }

        @objid ("c824725d-a8c8-455f-b239-d48f9a69ea30")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnCompensateEventDefinitionImpl();
        }

    }

    @objid ("c82c2808-d596-4185-89f9-a5c7ef08a1c2")
    public static class WaitForCompletionSmAttribute extends SmAttribute {
        @objid ("d88f0565-05ba-4812-a253-6c9449d9818a")
        public Object getValue(ISmObjectData data) {
            return ((BpmnCompensateEventDefinitionData) data).mWaitForCompletion;
        }

        @objid ("52bf7025-bb16-47cf-949e-58a8854145e4")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnCompensateEventDefinitionData) data).mWaitForCompletion = value;
        }

    }

    @objid ("6fccd917-6aa9-4fdc-940c-bb582422afcd")
    public static class ActivityRefSmDependency extends SmSingleDependency {
        @objid ("c8d43e9b-4415-4f6b-8131-a872b25c9768")
        private SmDependency symetricDep;

        @objid ("ea019329-dd13-4cbc-8d7e-a07d2ef9afff")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnCompensateEventDefinitionData) data).mActivityRef;
        }

        @objid ("cf9885b7-0223-44ca-a6bb-d358a800e17a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnCompensateEventDefinitionData) data).mActivityRef = value;
        }

        @objid ("e482085e-143d-4e92-9f19-c63f6ad65dd4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnActivitySmClass)this.getTarget()).getCompensateEventDefinitionsDep();
            }
            return this.symetricDep;
        }

    }

}
