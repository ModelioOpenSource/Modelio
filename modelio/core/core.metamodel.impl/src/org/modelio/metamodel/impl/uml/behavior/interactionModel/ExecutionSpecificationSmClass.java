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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionOccurenceSpecificationSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionSpecificationData;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
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

@objid ("c7dca441-9d72-4635-ad32-c686fc16d434")
public class ExecutionSpecificationSmClass extends InteractionFragmentSmClass {
    @objid ("83440d6c-f50f-4ecc-b878-07081d86784b")
    private SmDependency finishDep;

    @objid ("c374f9e4-d187-4de9-b1f9-e3c0520574cb")
    private SmDependency startDep;

    @objid ("d6caeb72-9941-4f54-ab7c-45515f7c53d5")
    public ExecutionSpecificationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("72f07119-ea77-414a-851c-81bd84891106")
    @Override
    public String getName() {
        return "ExecutionSpecification";
    }

    @objid ("fc4bca9f-3ba2-4011-84ad-dce47c85c6cd")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a557670e-27d3-42fe-8e89-28c3072fa693")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExecutionSpecification.class;
    }

    @objid ("3a993be4-7149-4c94-b477-512266407884")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4617e50b-4245-49ce-8df1-a70fa2c0833d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("72f7a88d-fb61-49f1-b7a8-ded656d4a8fd")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionFragment.MQNAME);
        this.registerFactory(new ExecutionSpecificationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.finishDep = new FinishSmDependency();
        this.finishDep.init("Finish", this, metamodel.getMClass(ExecutionOccurenceSpecification.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.finishDep);
        
        this.startDep = new StartSmDependency();
        this.startDep.init("Start", this, metamodel.getMClass(ExecutionOccurenceSpecification.MQNAME), 1, 1 );
        registerDependency(this.startDep);
    }

    @objid ("30069734-8276-4208-9684-82bb9c67b0e4")
    public SmDependency getFinishDep() {
        if (this.finishDep == null) {
        	this.finishDep = this.getDependencyDef("Finish");
        }
        return this.finishDep;
    }

    @objid ("8349cfa3-9b17-4e17-b30a-3321f9b27664")
    public SmDependency getStartDep() {
        if (this.startDep == null) {
        	this.startDep = this.getDependencyDef("Start");
        }
        return this.startDep;
    }

    @objid ("0bef207a-3a94-4188-9f6a-4e2f02022edc")
    private static class ExecutionSpecificationObjectFactory implements ISmObjectFactory {
        @objid ("4c86a146-0e64-470a-8d83-c828f9b6d0e1")
        private ExecutionSpecificationSmClass smClass;

        @objid ("91d6ec6e-a799-4fff-9fb3-7bc12ecd9fed")
        public ExecutionSpecificationObjectFactory(ExecutionSpecificationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e9488afa-a193-4be5-b5bb-79e7cdc143fb")
        @Override
        public ISmObjectData createData() {
            return new ExecutionSpecificationData(this.smClass);
        }

        @objid ("7321386f-76ab-40de-9b0f-82fc544050d5")
        @Override
        public SmObjectImpl createImpl() {
            return new ExecutionSpecificationImpl();
        }

    }

    @objid ("b04a97f6-056e-4d0a-914c-c0e067947fb4")
    public static class FinishSmDependency extends SmSingleDependency {
        @objid ("cb2b0bf0-ea3d-4fdc-bc2f-72edfeff5e18")
        private SmDependency symetricDep;

        @objid ("dd275c19-886e-4def-9241-3870c8b85def")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExecutionSpecificationData) data).mFinish;
        }

        @objid ("93690bb2-be41-48ef-9ec2-ba06df8d01e2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExecutionSpecificationData) data).mFinish = value;
        }

        @objid ("d23ab13a-857d-4f17-8918-b8b5ac99aba1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExecutionOccurenceSpecificationSmClass)this.getTarget()).getFinishedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("819a5304-77f4-4377-abab-a132aacc1b34")
    public static class StartSmDependency extends SmSingleDependency {
        @objid ("769ae43e-cd36-4e66-804c-e1502d8b2179")
        private SmDependency symetricDep;

        @objid ("de39e5bd-15cb-46f5-8fca-7a1d70b1fd3e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExecutionSpecificationData) data).mStart;
        }

        @objid ("04b3e054-6fb9-45c5-9482-f3b18e96200e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExecutionSpecificationData) data).mStart = value;
        }

        @objid ("b721301a-6f35-4c99-8355-a3151af52cc3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExecutionOccurenceSpecificationSmClass)this.getTarget()).getStartedDep();
            }
            return this.symetricDep;
        }

    }

}
