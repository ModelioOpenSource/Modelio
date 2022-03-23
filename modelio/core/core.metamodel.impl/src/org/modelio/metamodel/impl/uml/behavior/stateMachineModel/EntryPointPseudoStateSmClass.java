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
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
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

@objid ("235804a1-d997-4750-84a6-c6994e26fecd")
public class EntryPointPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("511c0c34-fffb-4ea8-b853-75d6be1722c4")
    private SmDependency entryOfDep;

    @objid ("7a27977a-344a-4d7b-8ae3-1ac2ce3d4c2a")
    private SmDependency connectionDep;

    @objid ("622a8182-8d76-47e8-a6e3-999e2b21f6ab")
    private SmDependency entryOfMachineDep;

    @objid ("46eb5515-26f5-4911-9717-759e585ed888")
    public  EntryPointPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ad525d28-35bf-4fd1-8226-1855a57a83c9")
    @Override
    public String getName() {
        return "EntryPointPseudoState";
        
    }

    @objid ("39a56162-4e09-4371-988e-b72fb418c18b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("945bd1d3-aea0-4e94-a218-57205c9032fb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return EntryPointPseudoState.class;
        
    }

    @objid ("7bedfd24-60a2-44cd-9a37-bcc13dc2c695")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("b1f9e435-9577-4ca0-80ca-a3c61c91b43e")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("df8a0880-e9f6-439e-b449-ba6aee58b0c7")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new EntryPointPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.entryOfDep = new EntryOfSmDependency();
        this.entryOfDep.init("EntryOf", this, metamodel.getMClass(State.MQNAME), 0, 1 );
        registerDependency(this.entryOfDep);
        
        this.connectionDep = new ConnectionSmDependency();
        this.connectionDep.init("Connection", this, metamodel.getMClass(ConnectionPointReference.MQNAME), 0, -1 );
        registerDependency(this.connectionDep);
        
        this.entryOfMachineDep = new EntryOfMachineSmDependency();
        this.entryOfMachineDep.init("EntryOfMachine", this, metamodel.getMClass(StateMachine.MQNAME), 0, 1 );
        registerDependency(this.entryOfMachineDep);
        
        
    }

    @objid ("09babede-aefe-4ced-a792-7c37fb529279")
    public SmDependency getEntryOfDep() {
        if (this.entryOfDep == null) {
        	this.entryOfDep = this.getDependencyDef("EntryOf");
        }
        return this.entryOfDep;
    }

    @objid ("1b5a7abc-2586-46cb-8cc4-e6b183c3d1a4")
    public SmDependency getConnectionDep() {
        if (this.connectionDep == null) {
        	this.connectionDep = this.getDependencyDef("Connection");
        }
        return this.connectionDep;
    }

    @objid ("7dc7faaa-e57b-4776-8ffd-327dcc3260a3")
    public SmDependency getEntryOfMachineDep() {
        if (this.entryOfMachineDep == null) {
        	this.entryOfMachineDep = this.getDependencyDef("EntryOfMachine");
        }
        return this.entryOfMachineDep;
    }

    @objid ("aaa4d9f6-88f4-49b1-99e8-797b228be6ae")
    private static class EntryPointPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("8c0a406b-14a7-4ccc-9cf1-fc7b834b17d6")
        private EntryPointPseudoStateSmClass smClass;

        @objid ("e306b219-29d1-464e-b0c0-37de238ea708")
        public  EntryPointPseudoStateObjectFactory(EntryPointPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8f629c23-f98d-4438-814a-07f8fc236685")
        @Override
        public ISmObjectData createData() {
            return new EntryPointPseudoStateData(this.smClass);
        }

        @objid ("b12e1a9e-8e43-4f15-b00f-52b2171b0b09")
        @Override
        public SmObjectImpl createImpl() {
            return new EntryPointPseudoStateImpl();
        }

    }

    @objid ("2aa915e0-20b5-4fb1-882a-9da48c19516c")
    public static class EntryOfSmDependency extends SmSingleDependency {
        @objid ("e589e73a-4a68-4d6e-9ed5-cf5fcc5980bd")
        private SmDependency symetricDep;

        @objid ("120495af-d559-4aac-8b13-caf98dd20401")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EntryPointPseudoStateData) data).mEntryOf;
        }

        @objid ("f59c00fb-ed28-4267-9e30-9319aa5938a0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EntryPointPseudoStateData) data).mEntryOf = value;
        }

        @objid ("19d85a98-2198-4079-979c-6aaa3b284932")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getEntryPointDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("929d1f14-ab57-4caa-aae5-b466508ac805")
    public static class ConnectionSmDependency extends SmMultipleDependency {
        @objid ("cc50cc1b-4afb-4d02-b800-de2818ef069e")
        private SmDependency symetricDep;

        @objid ("9ca5df33-472b-4abc-8590-48f703d69c3d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EntryPointPseudoStateData)data).mConnection != null)? ((EntryPointPseudoStateData)data).mConnection:SmMultipleDependency.EMPTY;
        }

        @objid ("0f05cda8-8b8f-4843-befb-bab2aae12726")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EntryPointPseudoStateData) data).mConnection = values;
            
        }

        @objid ("909b5d20-9923-4047-b939-669656412faa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConnectionPointReferenceSmClass)this.getTarget()).getEntryDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("fbe21536-9c9f-4964-8a78-79e544fce2ae")
    public static class EntryOfMachineSmDependency extends SmSingleDependency {
        @objid ("bc924f27-a73b-4097-8a9b-598757a58fdb")
        private SmDependency symetricDep;

        @objid ("8900e78a-b445-48ff-9622-1729d4ba4b2e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EntryPointPseudoStateData) data).mEntryOfMachine;
        }

        @objid ("821493b0-2ece-4dbd-bfbd-7b99192c5ad4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EntryPointPseudoStateData) data).mEntryOfMachine = value;
        }

        @objid ("b1547ad3-d4d0-43dd-ad80-cd2382990d01")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateMachineSmClass)this.getTarget()).getEntryPointDep();
            }
            return this.symetricDep;
            
        }

    }

}
