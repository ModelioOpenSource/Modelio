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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ConnectionPointReferenceData;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.EntryPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ExitPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateVertexSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
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

@objid ("954c673f-50e5-43b4-9c35-0fb3cdbcfd12")
public class ConnectionPointReferenceSmClass extends StateVertexSmClass {
    @objid ("8fd4976f-d1e1-4992-ab79-2c89a5b613d9")
    private SmDependency exitDep;

    @objid ("d238e332-72cb-476e-958d-ee60c45b9584")
    private SmDependency entryDep;

    @objid ("d35e368c-8652-494b-9e8d-905242e5e91f")
    private SmDependency ownerStateDep;

    @objid ("3f85a370-6535-46df-8b1e-95fdbc4eff03")
    public ConnectionPointReferenceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a556292e-4a6e-4683-bedf-e56fab95a4ad")
    @Override
    public String getName() {
        return "ConnectionPointReference";
    }

    @objid ("083acfe1-644f-4b80-8e00-8f1fb1437079")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1ca40c54-4fb6-4282-ac70-c0b341b75644")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ConnectionPointReference.class;
    }

    @objid ("6a89bff2-d2ed-4154-872c-336f0eb2fc5c")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9fd0370b-1f45-497e-9d38-be5e040061dc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("37870b6f-f881-45b7-b225-b3b88416b832")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StateVertex.MQNAME);
        this.registerFactory(new ConnectionPointReferenceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.exitDep = new ExitSmDependency();
        this.exitDep.init("Exit", this, metamodel.getMClass(ExitPointPseudoState.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.exitDep);
        
        this.entryDep = new EntrySmDependency();
        this.entryDep.init("Entry", this, metamodel.getMClass(EntryPointPseudoState.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.entryDep);
        
        this.ownerStateDep = new OwnerStateSmDependency();
        this.ownerStateDep.init("OwnerState", this, metamodel.getMClass(State.MQNAME), 0, 1 );
        registerDependency(this.ownerStateDep);
    }

    @objid ("87949f38-41cc-48f8-85f3-3cf578117739")
    public SmDependency getExitDep() {
        if (this.exitDep == null) {
        	this.exitDep = this.getDependencyDef("Exit");
        }
        return this.exitDep;
    }

    @objid ("e91657f2-00b7-41be-acb1-2ed94649f562")
    public SmDependency getEntryDep() {
        if (this.entryDep == null) {
        	this.entryDep = this.getDependencyDef("Entry");
        }
        return this.entryDep;
    }

    @objid ("63d58f2f-12c2-47ee-98ab-6c5b3da1ebe5")
    public SmDependency getOwnerStateDep() {
        if (this.ownerStateDep == null) {
        	this.ownerStateDep = this.getDependencyDef("OwnerState");
        }
        return this.ownerStateDep;
    }

    @objid ("e184f120-7fea-488a-92bb-586b34e05e69")
    private static class ConnectionPointReferenceObjectFactory implements ISmObjectFactory {
        @objid ("76ce945b-86ec-47af-9e3e-c0c060265c1c")
        private ConnectionPointReferenceSmClass smClass;

        @objid ("a4d6831a-5a90-494a-92e6-a73bbdd085ac")
        public ConnectionPointReferenceObjectFactory(ConnectionPointReferenceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("61cf7002-b289-4089-8ed5-6b0708c03af8")
        @Override
        public ISmObjectData createData() {
            return new ConnectionPointReferenceData(this.smClass);
        }

        @objid ("25733355-98a2-47c7-b4c8-f7ba9461a228")
        @Override
        public SmObjectImpl createImpl() {
            return new ConnectionPointReferenceImpl();
        }

    }

    @objid ("fcb7430b-15ed-49f3-ab2f-d73e6ce96c83")
    public static class ExitSmDependency extends SmSingleDependency {
        @objid ("32812f8b-4183-4243-bece-23a0789d2385")
        private SmDependency symetricDep;

        @objid ("b77d8160-c191-45cb-94d1-98b4ef5245e2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ConnectionPointReferenceData) data).mExit;
        }

        @objid ("b4d49c5a-6a94-44d3-891c-38b0bdf9f78d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ConnectionPointReferenceData) data).mExit = value;
        }

        @objid ("81e517c3-6225-4d7a-800a-cd4225fa2301")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExitPointPseudoStateSmClass)this.getTarget()).getConnectionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1ee19205-11cf-4ae4-a58e-972106fa3c46")
    public static class EntrySmDependency extends SmSingleDependency {
        @objid ("aebe7103-0903-42f5-a45b-684bdd70a1b6")
        private SmDependency symetricDep;

        @objid ("fcdc4cb5-6857-4169-9e06-4010d53b3f6b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ConnectionPointReferenceData) data).mEntry;
        }

        @objid ("b3421cab-5f2f-417b-8953-f1bf26ba4744")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ConnectionPointReferenceData) data).mEntry = value;
        }

        @objid ("94aed1df-9b81-4f5d-9149-9c86b1365a37")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EntryPointPseudoStateSmClass)this.getTarget()).getConnectionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1440f33f-c555-402b-98ed-95eba2ac9cdb")
    public static class OwnerStateSmDependency extends SmSingleDependency {
        @objid ("ac12db20-4c59-45ac-bdc3-64cd04969214")
        private SmDependency symetricDep;

        @objid ("d549c101-4ce0-42e1-99fb-f2459da2e04d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ConnectionPointReferenceData) data).mOwnerState;
        }

        @objid ("0438a7f4-840c-4567-87bf-f3071bd266a1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ConnectionPointReferenceData) data).mOwnerState = value;
        }

        @objid ("ff57f2c4-e3f9-4d32-8df3-dfd697ab864d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getConnectionDep();
            }
            return this.symetricDep;
        }

    }

}
