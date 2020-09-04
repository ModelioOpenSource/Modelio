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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnCallActivityData;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskSmClass;
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

@objid ("47a2e0da-e146-4568-9ea0-eafa8cc8b0e8")
public class BpmnCallActivitySmClass extends BpmnActivitySmClass {
    @objid ("9246af7a-ce8c-4bea-9a2d-35ba4aca696c")
    private SmDependency calledGlobalTaskDep;

    @objid ("2dfa0006-834e-41ba-8c29-fe593712a365")
    public BpmnCallActivitySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("29835881-b9ba-4f34-8b06-a3b4d24b69b8")
    @Override
    public String getName() {
        return "BpmnCallActivity";
    }

    @objid ("bd0ad82d-8f9c-48df-bd4f-1e3a38dd4fb2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d3f64439-18bb-47e1-8952-0e92954c2b05")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCallActivity.class;
    }

    @objid ("b3ef31f4-c4d1-4842-baf0-6a39b1f04fc1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4612ce71-79a2-431b-9021-a67aecfcc662")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("0921a29c-0798-49b4-9137-e511ae92ad72")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnActivity.MQNAME);
        this.registerFactory(new BpmnCallActivityObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.calledGlobalTaskDep = new CalledGlobalTaskSmDependency();
        this.calledGlobalTaskDep.init("CalledGlobalTask", this, metamodel.getMClass(BpmnTask.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.calledGlobalTaskDep);
    }

    @objid ("22706371-05f0-4817-b72d-e782b74e18e0")
    public SmDependency getCalledGlobalTaskDep() {
        if (this.calledGlobalTaskDep == null) {
        	this.calledGlobalTaskDep = this.getDependencyDef("CalledGlobalTask");
        }
        return this.calledGlobalTaskDep;
    }

    @objid ("fdaa7d04-a33f-4335-b5b2-f06df9179141")
    private static class BpmnCallActivityObjectFactory implements ISmObjectFactory {
        @objid ("79fa3932-889d-47a7-af83-56d6c4837258")
        private BpmnCallActivitySmClass smClass;

        @objid ("0f8afa14-23c9-4287-a9e8-6bf79b1da303")
        public BpmnCallActivityObjectFactory(BpmnCallActivitySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f17fcad9-cbe7-478a-b244-65d6e85863a3")
        @Override
        public ISmObjectData createData() {
            return new BpmnCallActivityData(this.smClass);
        }

        @objid ("f0e43f37-8e95-4f74-ad22-e817b53cd84c")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnCallActivityImpl();
        }

    }

    @objid ("56b1900d-8af1-4b43-8b81-f6f5ac940de3")
    public static class CalledGlobalTaskSmDependency extends SmSingleDependency {
        @objid ("d126e90a-0db7-4e74-a1cb-72cfad1cb7c6")
        private SmDependency symetricDep;

        @objid ("fb56aa76-a7d7-41b4-863f-7a2d3ff74dde")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnCallActivityData) data).mCalledGlobalTask;
        }

        @objid ("4069a2a2-a5d3-4a1e-a98c-58e2afbc1348")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnCallActivityData) data).mCalledGlobalTask = value;
        }

        @objid ("c0cea00c-23cb-4806-827c-31ab908b7a99")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnTaskSmClass)this.getTarget()).getCallerDep();
            }
            return this.symetricDep;
        }

    }

}
