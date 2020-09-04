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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallOperationActionData;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.statik.Operation;
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

@objid ("173bd08a-e974-4c88-8f4b-06b784f080b2")
public class CallOperationActionSmClass extends CallActionSmClass {
    @objid ("79b9266f-832f-4b5c-bb0c-ece8f0e4bb09")
    private SmDependency calledDep;

    @objid ("06a007a8-5152-452a-905a-f9d0092c123a")
    public CallOperationActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("12cd9a11-0fb7-4397-8394-28fdb380feca")
    @Override
    public String getName() {
        return "CallOperationAction";
    }

    @objid ("4b0f629b-3955-4f25-b458-f493dadc4d6d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1f3c5f93-bab1-446a-ab93-86a6a4c5ce26")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CallOperationAction.class;
    }

    @objid ("f807aad4-36bb-4f15-9dd6-6a9b2cd51873")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6222ce74-17ed-492e-9edd-ff33691cd819")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("aab09e3d-728a-4f4f-9a67-853dcf0e9a7d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(CallAction.MQNAME);
        this.registerFactory(new CallOperationActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.calledDep = new CalledSmDependency();
        this.calledDep.init("Called", this, metamodel.getMClass(Operation.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.calledDep);
    }

    @objid ("2fb23f82-c525-4664-b6ae-c4084f55c0e9")
    public SmDependency getCalledDep() {
        if (this.calledDep == null) {
        	this.calledDep = this.getDependencyDef("Called");
        }
        return this.calledDep;
    }

    @objid ("eb16de6c-34a6-493f-b939-aaafef78e9f8")
    private static class CallOperationActionObjectFactory implements ISmObjectFactory {
        @objid ("06fc4898-1f33-40f6-b3e1-636735cf87d5")
        private CallOperationActionSmClass smClass;

        @objid ("71be897e-c944-480d-aa4e-af4d24370a35")
        public CallOperationActionObjectFactory(CallOperationActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0e071ad5-9ec6-4d3c-8808-26de4c4bda53")
        @Override
        public ISmObjectData createData() {
            return new CallOperationActionData(this.smClass);
        }

        @objid ("48d3a50e-3418-45ab-9556-7c890d254153")
        @Override
        public SmObjectImpl createImpl() {
            return new CallOperationActionImpl();
        }

    }

    @objid ("058f71fd-ad1c-412e-ad7f-cc1438245f20")
    public static class CalledSmDependency extends SmSingleDependency {
        @objid ("9a87279d-b462-4090-8e1a-29c16e0e382d")
        private SmDependency symetricDep;

        @objid ("eff2bdfa-2605-4b8a-937f-8665c5557c86")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CallOperationActionData) data).mCalled;
        }

        @objid ("8844cee8-88d6-4eaa-a927-31422bc06a09")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CallOperationActionData) data).mCalled = value;
        }

        @objid ("578cbd21-2a3f-4880-89a5-e560a3e96835")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getCallingActionDep();
            }
            return this.symetricDep;
        }

    }

}
