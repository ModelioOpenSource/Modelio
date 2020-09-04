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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ConnectionPointReferenceSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.EntryPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ExitPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.InternalTransitionSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.RegionSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateData;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateMachineSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateVertexSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
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

@objid ("0a274c48-b40b-4380-aa10-8a641668c3b0")
public class StateSmClass extends StateVertexSmClass {
    @objid ("a5339f64-7627-408f-ac63-9950f2188828")
    private SmDependency exitPointDep;

    @objid ("3db1a7a6-ecc7-43fa-b015-d50fef1fea74")
    private SmDependency defferedDep;

    @objid ("b4511e7d-b768-4270-8906-72a133994080")
    private SmDependency internalDep;

    @objid ("a5138667-4605-42cc-ac74-7a39b21bac53")
    private SmDependency entryPointDep;

    @objid ("3e770e92-4d64-422b-a824-a42e2eb5fabf")
    private SmDependency ownedRegionDep;

    @objid ("9004913c-626d-46a6-8dad-a4dc281d7f4e")
    private SmDependency requiredStateOfDep;

    @objid ("53c69e6a-1fc9-4fc3-abd2-0e7c2b7f4513")
    private SmDependency connectionDep;

    @objid ("7a04dd04-1282-4f12-9ed4-a26373869394")
    private SmDependency subMachineDep;

    @objid ("0f518efd-8e12-404e-9d77-ce5c9881492b")
    public StateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("78047d7a-d42e-48c9-a4ac-935c03cab1f2")
    @Override
    public String getName() {
        return "State";
    }

    @objid ("a6c436f5-9f48-4c22-aec8-6e96513af3a6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0232947c-0562-440e-b1d3-eeec92d59c3b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return State.class;
    }

    @objid ("614f33d3-34ae-4c75-aa4c-f95cd04fbac1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a4ed183a-0454-4634-8c8f-08d87229ad1c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ebb03eb5-c6a7-4272-b1e1-45834d9aabe5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StateVertex.MQNAME);
        this.registerFactory(new StateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.exitPointDep = new ExitPointSmDependency();
        this.exitPointDep.init("ExitPoint", this, metamodel.getMClass(ExitPointPseudoState.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.exitPointDep);
        
        this.defferedDep = new DefferedSmDependency();
        this.defferedDep.init("Deffered", this, metamodel.getMClass(Event.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.defferedDep);
        
        this.internalDep = new InternalSmDependency();
        this.internalDep.init("Internal", this, metamodel.getMClass(InternalTransition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.internalDep);
        
        this.entryPointDep = new EntryPointSmDependency();
        this.entryPointDep.init("EntryPoint", this, metamodel.getMClass(EntryPointPseudoState.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.entryPointDep);
        
        this.ownedRegionDep = new OwnedRegionSmDependency();
        this.ownedRegionDep.init("OwnedRegion", this, metamodel.getMClass(Region.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedRegionDep);
        
        this.requiredStateOfDep = new RequiredStateOfSmDependency();
        this.requiredStateOfDep.init("RequiredStateOf", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 );
        registerDependency(this.requiredStateOfDep);
        
        this.connectionDep = new ConnectionSmDependency();
        this.connectionDep.init("Connection", this, metamodel.getMClass(ConnectionPointReference.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.connectionDep);
        
        this.subMachineDep = new SubMachineSmDependency();
        this.subMachineDep.init("SubMachine", this, metamodel.getMClass(StateMachine.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.subMachineDep);
    }

    @objid ("e16e61cb-7af7-4210-90ca-7bbe4dc4db9f")
    public SmDependency getExitPointDep() {
        if (this.exitPointDep == null) {
        	this.exitPointDep = this.getDependencyDef("ExitPoint");
        }
        return this.exitPointDep;
    }

    @objid ("0951aa71-64f9-4dbf-8f39-2499c320af58")
    public SmDependency getDefferedDep() {
        if (this.defferedDep == null) {
        	this.defferedDep = this.getDependencyDef("Deffered");
        }
        return this.defferedDep;
    }

    @objid ("15a3ba23-cfe4-45f4-a632-bd1b5d5c418d")
    public SmDependency getInternalDep() {
        if (this.internalDep == null) {
        	this.internalDep = this.getDependencyDef("Internal");
        }
        return this.internalDep;
    }

    @objid ("03c8b268-6532-429e-95a6-f4a6a684b71c")
    public SmDependency getEntryPointDep() {
        if (this.entryPointDep == null) {
        	this.entryPointDep = this.getDependencyDef("EntryPoint");
        }
        return this.entryPointDep;
    }

    @objid ("8003690b-cbec-4739-9adf-aaa46d372adb")
    public SmDependency getOwnedRegionDep() {
        if (this.ownedRegionDep == null) {
        	this.ownedRegionDep = this.getDependencyDef("OwnedRegion");
        }
        return this.ownedRegionDep;
    }

    @objid ("43633d89-4151-476a-b5fc-39c25d457317")
    public SmDependency getRequiredStateOfDep() {
        if (this.requiredStateOfDep == null) {
        	this.requiredStateOfDep = this.getDependencyDef("RequiredStateOf");
        }
        return this.requiredStateOfDep;
    }

    @objid ("275a5b68-3503-4c55-ae0d-73f16dd74c4a")
    public SmDependency getConnectionDep() {
        if (this.connectionDep == null) {
        	this.connectionDep = this.getDependencyDef("Connection");
        }
        return this.connectionDep;
    }

    @objid ("7cdc77aa-3af9-4e14-b962-2b4baa4ae995")
    public SmDependency getSubMachineDep() {
        if (this.subMachineDep == null) {
        	this.subMachineDep = this.getDependencyDef("SubMachine");
        }
        return this.subMachineDep;
    }

    @objid ("09c173a5-9ba1-47af-9a5d-db77a82751ed")
    private static class StateObjectFactory implements ISmObjectFactory {
        @objid ("f8b4ffe4-bcd9-4918-a2bf-728345e91eba")
        private StateSmClass smClass;

        @objid ("040043c9-3332-4d6e-9a96-539a351e378d")
        public StateObjectFactory(StateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("70ada9aa-82f2-4f21-a804-426aaa6c9447")
        @Override
        public ISmObjectData createData() {
            return new StateData(this.smClass);
        }

        @objid ("166ab5ba-b4dc-412e-89e5-9f0ef9de6d4e")
        @Override
        public SmObjectImpl createImpl() {
            return new StateImpl();
        }

    }

    @objid ("69336a78-c8c1-4663-89d6-5965a131036d")
    public static class ExitPointSmDependency extends SmMultipleDependency {
        @objid ("3c476e8a-a59f-4434-84cf-64d3f008f270")
        private SmDependency symetricDep;

        @objid ("6ebf809b-5a7b-4226-b7d0-5f8530f372c8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mExitPoint != null)? ((StateData)data).mExitPoint:SmMultipleDependency.EMPTY;
        }

        @objid ("fa4a2b5d-8b01-4851-82c6-7be07adf589a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mExitPoint = values;
        }

        @objid ("32c48930-3d05-46fd-9c27-518d1c85e125")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExitPointPseudoStateSmClass)this.getTarget()).getExitOfDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("32a104ce-51c0-4a5d-8c02-bc14bb195e23")
    public static class DefferedSmDependency extends SmMultipleDependency {
        @objid ("31d7796f-29c9-4e73-8227-e70d34d86bbd")
        private SmDependency symetricDep;

        @objid ("0d7ced9b-7f4c-4938-8295-256a66fceae6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mDeffered != null)? ((StateData)data).mDeffered:SmMultipleDependency.EMPTY;
        }

        @objid ("ae2e75d5-1c99-493d-b8d4-adb5f26b652e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mDeffered = values;
        }

        @objid ("d89f2629-3e9b-453c-9356-dcaeea8dc5c0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EventSmClass)this.getTarget()).getOriginDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9b4c56b5-76a8-4166-8801-faab70ebb9a4")
    public static class InternalSmDependency extends SmMultipleDependency {
        @objid ("b59309e9-459a-4cc1-9109-2047aadbd195")
        private SmDependency symetricDep;

        @objid ("04895621-cad2-4c5c-a745-03b74480208c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mInternal != null)? ((StateData)data).mInternal:SmMultipleDependency.EMPTY;
        }

        @objid ("a3c9da7e-b174-4309-a9cc-0c3074a91441")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mInternal = values;
        }

        @objid ("5bdc90e3-502e-4e6d-b0f0-a44978907f78")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InternalTransitionSmClass)this.getTarget()).getSComposedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4eacd3b7-6f46-417b-80ea-0e6b54de9da2")
    public static class EntryPointSmDependency extends SmMultipleDependency {
        @objid ("734a6af5-5cf3-4eea-8040-fd7666127938")
        private SmDependency symetricDep;

        @objid ("58ecb122-b5a0-45ac-ac7b-871d02341152")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mEntryPoint != null)? ((StateData)data).mEntryPoint:SmMultipleDependency.EMPTY;
        }

        @objid ("dade60cb-8909-4fde-8278-55cf8b51defe")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mEntryPoint = values;
        }

        @objid ("6c68ab27-c185-49cc-b092-c4861047e8b1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EntryPointPseudoStateSmClass)this.getTarget()).getEntryOfDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("be860d05-553e-45ce-94a4-db7f75460ef1")
    public static class OwnedRegionSmDependency extends SmMultipleDependency {
        @objid ("18149cd5-0441-4f97-8c0a-68753d92b047")
        private SmDependency symetricDep;

        @objid ("aa59e0b8-00c0-427a-9d2e-05809de08933")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mOwnedRegion != null)? ((StateData)data).mOwnedRegion:SmMultipleDependency.EMPTY;
        }

        @objid ("c5b83470-6057-4aa0-9d03-b77b84e7534e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mOwnedRegion = values;
        }

        @objid ("4ec76474-0704-4c71-aec5-144d0a2807f8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RegionSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("29e4844f-1e65-4af6-b50c-30fe147e3a8d")
    public static class RequiredStateOfSmDependency extends SmMultipleDependency {
        @objid ("99e344cb-741f-4f0e-9c61-42dc5e4c4d81")
        private SmDependency symetricDep;

        @objid ("c08fc696-e5d4-4085-b0e1-95cb15f5c027")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mRequiredStateOf != null)? ((StateData)data).mRequiredStateOf:SmMultipleDependency.EMPTY;
        }

        @objid ("fa8ca1d6-b6aa-424d-8947-1640dfd52851")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mRequiredStateOf = values;
        }

        @objid ("f4c0c9ae-c20e-4bfe-8f52-15eca50a3e98")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getInStateDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a8b94ba3-b077-4589-8243-e24d8f5e7010")
    public static class ConnectionSmDependency extends SmMultipleDependency {
        @objid ("0d73ab61-659a-4807-a739-6e9300f7f348")
        private SmDependency symetricDep;

        @objid ("ef4cf67d-aeb3-4ff8-af8e-c4b9eb8ad515")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateData)data).mConnection != null)? ((StateData)data).mConnection:SmMultipleDependency.EMPTY;
        }

        @objid ("d808d1b8-36c0-4086-aff2-92c97cd8b042")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateData) data).mConnection = values;
        }

        @objid ("3da5b230-fce3-415c-8779-9b5b50f84ea1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConnectionPointReferenceSmClass)this.getTarget()).getOwnerStateDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5263e945-55d3-4d52-9810-884911578057")
    public static class SubMachineSmDependency extends SmSingleDependency {
        @objid ("358bf6c0-bec4-4add-9a25-64db8664e8c0")
        private SmDependency symetricDep;

        @objid ("b59d93ad-7b2a-4a7c-acee-ea7eb71e6568")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StateData) data).mSubMachine;
        }

        @objid ("a45d08af-9239-4fd8-90b4-2701deb00e15")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StateData) data).mSubMachine = value;
        }

        @objid ("9e1a561c-041e-4a01-bae0-eb519d35e576")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateMachineSmClass)this.getTarget()).getSubmachineStateDep();
            }
            return this.symetricDep;
        }

    }

}
