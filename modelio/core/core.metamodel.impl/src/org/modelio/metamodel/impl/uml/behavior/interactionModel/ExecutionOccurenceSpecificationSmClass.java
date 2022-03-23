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
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
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

@objid ("06fe4006-f9b7-4e49-920f-8f0fd75a1fdd")
public class ExecutionOccurenceSpecificationSmClass extends MessageEndSmClass {
    @objid ("9a2a8f91-3a45-4ba7-b0a8-2ae8532b268f")
    private SmDependency finishedDep;

    @objid ("3611f823-3965-4e0d-a9f4-7494234df3b3")
    private SmDependency startedDep;

    @objid ("8a472bb6-0bc1-41f1-8964-4bfb5e563da3")
    public  ExecutionOccurenceSpecificationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d527f2e8-3a77-465c-bf89-c97cdbe5c688")
    @Override
    public String getName() {
        return "ExecutionOccurenceSpecification";
        
    }

    @objid ("8a29416e-fe57-4770-a1fc-43a83fe9ee04")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("03537d1a-110b-4c84-9309-46e392876251")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExecutionOccurenceSpecification.class;
        
    }

    @objid ("ee62e7cf-8421-4b35-9f44-3f9fd37d2eb2")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("70f67d9f-6ee6-42ab-bf72-85206191378a")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("523d9952-e6f9-4657-90ab-bcbf195c8b4a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(MessageEnd.MQNAME);
        this.registerFactory(new ExecutionOccurenceSpecificationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.finishedDep = new FinishedSmDependency();
        this.finishedDep.init("Finished", this, metamodel.getMClass(ExecutionSpecification.MQNAME), 0, 1 , SmDirective.SMCDTODELETE);
        registerDependency(this.finishedDep);
        
        this.startedDep = new StartedSmDependency();
        this.startedDep.init("Started", this, metamodel.getMClass(ExecutionSpecification.MQNAME), 0, 1 , SmDirective.SMCDPARTOF, SmDirective.SMCDTODELETE);
        registerDependency(this.startedDep);
        
        
    }

    @objid ("e28e2242-f862-4486-93f9-a441e5f6124d")
    public SmDependency getFinishedDep() {
        if (this.finishedDep == null) {
        	this.finishedDep = this.getDependencyDef("Finished");
        }
        return this.finishedDep;
    }

    @objid ("1b0c8729-8564-4111-8f1e-fd00da54cf25")
    public SmDependency getStartedDep() {
        if (this.startedDep == null) {
        	this.startedDep = this.getDependencyDef("Started");
        }
        return this.startedDep;
    }

    @objid ("bd19f5ca-bbfa-49cd-88b0-47e4637f0e64")
    private static class ExecutionOccurenceSpecificationObjectFactory implements ISmObjectFactory {
        @objid ("bcabd319-ca1b-442e-82d8-a2b128222ece")
        private ExecutionOccurenceSpecificationSmClass smClass;

        @objid ("861968e7-4f95-42ac-8c65-5f56007941b2")
        public  ExecutionOccurenceSpecificationObjectFactory(ExecutionOccurenceSpecificationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4988532a-f2ba-4940-b470-0d8003e71908")
        @Override
        public ISmObjectData createData() {
            return new ExecutionOccurenceSpecificationData(this.smClass);
        }

        @objid ("8aa59e5c-bcc4-495a-b4d0-fc5da2c70e69")
        @Override
        public SmObjectImpl createImpl() {
            return new ExecutionOccurenceSpecificationImpl();
        }

    }

    @objid ("450a7ac6-3101-4492-a4ad-1ba8f5b1aed8")
    public static class FinishedSmDependency extends SmSingleDependency {
        @objid ("d0506dc4-0072-437f-b902-380ee3784fc6")
        private SmDependency symetricDep;

        @objid ("51847251-2f94-48bd-a6a6-631e7b3301ca")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExecutionOccurenceSpecificationData) data).mFinished;
        }

        @objid ("60c6cca6-efd7-4c77-a5e7-3d152c2efc36")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExecutionOccurenceSpecificationData) data).mFinished = value;
        }

        @objid ("25a271cf-a45c-4fa1-9f5b-7c12a5a1d01f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExecutionSpecificationSmClass)this.getTarget()).getFinishDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("7cd06869-bc74-4774-86a3-b92aa8908a95")
    public static class StartedSmDependency extends SmSingleDependency {
        @objid ("41cf1fd6-1c6e-471e-9ced-7c559325ba8c")
        private SmDependency symetricDep;

        @objid ("cf0b0413-01f0-4337-afc9-9eab7c8d3624")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExecutionOccurenceSpecificationData) data).mStarted;
        }

        @objid ("f09c4911-676f-448b-826d-be1e841bfd33")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExecutionOccurenceSpecificationData) data).mStarted = value;
        }

        @objid ("5587230b-ce79-48d5-b8ab-f198c3522883")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExecutionSpecificationSmClass)this.getTarget()).getStartDep();
            }
            return this.symetricDep;
            
        }

    }

}
