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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("72567f2c-7474-4d71-b8d7-a1e8576d3aa1")
public class ExitPointPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("c78affb5-b616-4491-b2af-96b1e313005b")
    private SmDependency exitOfDep;

    @objid ("d80c0bf9-a006-4ad7-86c7-2ef2c653883e")
    private SmDependency connectionDep;

    @objid ("651bd7ba-bfe7-4bc9-9a80-ccf3137de826")
    private SmDependency exitOfMachineDep;

    @objid ("bb2ea859-2bab-4c70-83b5-8886059fc716")
    public  ExitPointPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a9c0798e-49dd-43dc-9f20-7660ce2052c4")
    @Override
    public String getName() {
        return "ExitPointPseudoState";
        
    }

    @objid ("459caf5c-57b7-4eb3-9335-ea1b5f21b735")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("70b0c470-8140-4f77-a623-8c57701401c2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExitPointPseudoState.class;
        
    }

    @objid ("a07ea9df-a554-4911-9088-676fe8d9bd38")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("e3d91402-dcd5-4e92-8079-e6f6da0dd012")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("f8ad0098-2e5e-4320-9e15-56d33e3db980")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new ExitPointPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.exitOfDep = new ExitOfSmDependency();
        this.exitOfDep.init("ExitOf", this, metamodel.getMClass(State.MQNAME), 0, 1 );
        registerDependency(this.exitOfDep);
        
        this.connectionDep = new ConnectionSmDependency();
        this.connectionDep.init("Connection", this, metamodel.getMClass(ConnectionPointReference.MQNAME), 0, -1 );
        registerDependency(this.connectionDep);
        
        this.exitOfMachineDep = new ExitOfMachineSmDependency();
        this.exitOfMachineDep.init("ExitOfMachine", this, metamodel.getMClass(StateMachine.MQNAME), 0, 1 );
        registerDependency(this.exitOfMachineDep);
        
        
    }

    @objid ("ddd422ef-fa81-46ec-adea-d3453a940c0a")
    public SmDependency getExitOfDep() {
        if (this.exitOfDep == null) {
        	this.exitOfDep = this.getDependencyDef("ExitOf");
        }
        return this.exitOfDep;
    }

    @objid ("f8be380e-ec88-42fc-88d6-7bc109071c71")
    public SmDependency getConnectionDep() {
        if (this.connectionDep == null) {
        	this.connectionDep = this.getDependencyDef("Connection");
        }
        return this.connectionDep;
    }

    @objid ("540dae57-b764-4951-8154-cde2f65136f1")
    public SmDependency getExitOfMachineDep() {
        if (this.exitOfMachineDep == null) {
        	this.exitOfMachineDep = this.getDependencyDef("ExitOfMachine");
        }
        return this.exitOfMachineDep;
    }

    @objid ("4b7d4a34-cc76-4246-9479-6e9499360581")
    private static class ExitPointPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("7c6d36dc-6a5c-4f38-82ae-5e7d770a4fe2")
        private ExitPointPseudoStateSmClass smClass;

        @objid ("68fd82aa-a91c-472e-8008-7705eec5eaaf")
        public  ExitPointPseudoStateObjectFactory(ExitPointPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e451634c-660b-4ae2-a403-17f8ca506d28")
        @Override
        public ISmObjectData createData() {
            return new ExitPointPseudoStateData(this.smClass);
        }

        @objid ("96a5ab33-1134-45d0-b05f-76b79626817b")
        @Override
        public SmObjectImpl createImpl() {
            return new ExitPointPseudoStateImpl();
        }

    }

    @objid ("e37a9cb6-0803-4ba9-bcd0-c602ded50ab2")
    public static class ExitOfSmDependency extends SmSingleDependency {
        @objid ("c55af009-a9cb-4b41-b7ec-013f41471c02")
        private SmDependency symetricDep;

        @objid ("347dbb74-db74-4fdd-8b8f-d76e4254004a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExitPointPseudoStateData) data).mExitOf;
        }

        @objid ("d041ef72-260a-451c-a65a-a8c361faeb85")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExitPointPseudoStateData) data).mExitOf = value;
        }

        @objid ("f465d462-b6ae-4915-934d-10140377a4cb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getExitPointDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("97ecd351-1154-431b-848f-04df724da19d")
    public static class ConnectionSmDependency extends SmMultipleDependency {
        @objid ("b25112ff-fed0-4590-8c24-aef0408cb2a8")
        private SmDependency symetricDep;

        @objid ("ad1db40c-4110-47fa-b88c-80a7f8db856c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ExitPointPseudoStateData)data).mConnection != null)? ((ExitPointPseudoStateData)data).mConnection:SmMultipleDependency.EMPTY;
        }

        @objid ("fc31b349-e7db-400d-8528-13140b90a648")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ExitPointPseudoStateData) data).mConnection = values;
            
        }

        @objid ("15f1e28f-40e9-43fc-a4e9-5aaf791b8575")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConnectionPointReferenceSmClass)this.getTarget()).getExitDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("59571b81-ec24-442d-b2bc-d879695633d7")
    public static class ExitOfMachineSmDependency extends SmSingleDependency {
        @objid ("282d973c-8fd0-4662-b8c3-94cd1b4b1caf")
        private SmDependency symetricDep;

        @objid ("119d18c4-841f-4c31-911c-0f5645d57804")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExitPointPseudoStateData) data).mExitOfMachine;
        }

        @objid ("8f208486-a65e-4ad2-bb4e-245556c23223")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExitPointPseudoStateData) data).mExitOfMachine = value;
        }

        @objid ("d36ebf65-e218-486f-8f0c-ae53f7659c21")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateMachineSmClass)this.getTarget()).getExitPointDep();
            }
            return this.symetricDep;
            
        }

    }

}
