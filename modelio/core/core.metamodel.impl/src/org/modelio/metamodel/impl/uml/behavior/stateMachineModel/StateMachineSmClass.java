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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("8b9c51b8-449b-4b09-a6f6-60d9ca787f80")
public class StateMachineSmClass extends BehaviorSmClass {
    @objid ("6a711b21-1877-43a7-91a1-698e7d26e37f")
    private SmAttribute kindAtt;

    @objid ("67fbc0da-bc82-464d-8983-e093fb0740d1")
    private SmDependency topDep;

    @objid ("7cd0b722-0991-446e-9a8b-79c859a40c60")
    private SmDependency submachineStateDep;

    @objid ("5153518e-826b-4373-8bd6-30306e870abf")
    private SmDependency entryPointDep;

    @objid ("135c6159-8d1f-4658-a28c-2d912e68baee")
    private SmDependency exitPointDep;

    @objid ("df66b335-38a3-491c-bb56-2cc0b0f382e7")
    public  StateMachineSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7ca27631-ae17-43b9-b6ee-42a4ebfbb896")
    @Override
    public String getName() {
        return "StateMachine";
        
    }

    @objid ("e2c0d84e-6345-46b2-8a0f-62da2da4c0ae")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("213e0abc-1949-4b3f-afa9-456101e57012")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StateMachine.class;
        
    }

    @objid ("6e8b0546-4707-4bdd-9e38-f0ce75d96a4d")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("a8c9313f-69b5-4e7d-b7dd-ffe92a7846ac")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("6c353ca8-c532-4314-ae70-2c9bd4a4f3e2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new StateMachineObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.kindAtt = new KindSmAttribute();
        this.kindAtt.init("Kind", this, KindOfStateMachine.class );
        registerAttribute(this.kindAtt);
        
        
        // Initialize and register the SmDependency
        this.topDep = new TopSmDependency();
        this.topDep.init("Top", this, metamodel.getMClass(Region.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.topDep);
        
        this.submachineStateDep = new SubmachineStateSmDependency();
        this.submachineStateDep.init("SubmachineState", this, metamodel.getMClass(State.MQNAME), 0, -1 );
        registerDependency(this.submachineStateDep);
        
        this.entryPointDep = new EntryPointSmDependency();
        this.entryPointDep.init("EntryPoint", this, metamodel.getMClass(EntryPointPseudoState.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.entryPointDep);
        
        this.exitPointDep = new ExitPointSmDependency();
        this.exitPointDep.init("ExitPoint", this, metamodel.getMClass(ExitPointPseudoState.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.exitPointDep);
        
        
    }

    @objid ("eea39e0f-d25c-43be-974a-c9e07af3ce20")
    public SmAttribute getKindAtt() {
        if (this.kindAtt == null) {
        	this.kindAtt = this.getAttributeDef("Kind");
        }
        return this.kindAtt;
    }

    @objid ("1dca4037-9297-4260-82dd-f4a4bd3505f4")
    public SmDependency getTopDep() {
        if (this.topDep == null) {
        	this.topDep = this.getDependencyDef("Top");
        }
        return this.topDep;
    }

    @objid ("13d5dbc7-188b-4399-b9f3-128391daef89")
    public SmDependency getSubmachineStateDep() {
        if (this.submachineStateDep == null) {
        	this.submachineStateDep = this.getDependencyDef("SubmachineState");
        }
        return this.submachineStateDep;
    }

    @objid ("b6c409bd-1ec9-41c3-812f-37699ef633f4")
    public SmDependency getEntryPointDep() {
        if (this.entryPointDep == null) {
        	this.entryPointDep = this.getDependencyDef("EntryPoint");
        }
        return this.entryPointDep;
    }

    @objid ("d2ba53be-6bd7-4df5-9d2e-aedf7f0c7462")
    public SmDependency getExitPointDep() {
        if (this.exitPointDep == null) {
        	this.exitPointDep = this.getDependencyDef("ExitPoint");
        }
        return this.exitPointDep;
    }

    @objid ("b154632e-ada2-4175-909d-0fdc0994cba5")
    private static class StateMachineObjectFactory implements ISmObjectFactory {
        @objid ("e0c13c99-f093-4e4a-8518-66ead0f06554")
        private StateMachineSmClass smClass;

        @objid ("95fe647b-c2aa-41d2-830e-9823f21d0480")
        public  StateMachineObjectFactory(StateMachineSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("629a7a4d-6073-4edf-8c69-f676d217233f")
        @Override
        public ISmObjectData createData() {
            return new StateMachineData(this.smClass);
        }

        @objid ("7cda30a3-64b6-4e77-aa73-3a953319e65a")
        @Override
        public SmObjectImpl createImpl() {
            return new StateMachineImpl();
        }

    }

    @objid ("d89777ff-604f-4fb5-9028-5918cfa4d46d")
    public static class KindSmAttribute extends SmAttribute {
        @objid ("68a34667-eac2-43a8-97f2-c68a1acce578")
        public Object getValue(ISmObjectData data) {
            return ((StateMachineData) data).mKind;
        }

        @objid ("c5be6025-04a0-446d-85fd-4ca19963cb2c")
        public void setValue(ISmObjectData data, Object value) {
            ((StateMachineData) data).mKind = value;
        }

    }

    @objid ("1b9c3282-4f80-402c-8d7a-983355585fe2")
    public static class TopSmDependency extends SmSingleDependency {
        @objid ("d052f41f-260b-468a-ab1f-b9f0eb43d90a")
        private SmDependency symetricDep;

        @objid ("f2c5d95f-f09e-4618-b734-cc584520593c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StateMachineData) data).mTop;
        }

        @objid ("6db3020a-92a9-43d8-b75e-edd8fe8e962b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StateMachineData) data).mTop = value;
        }

        @objid ("1bd8687c-047e-4d39-b007-ca65027fad7f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RegionSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c99ee739-10a7-49b6-9cf7-89c135838890")
    public static class SubmachineStateSmDependency extends SmMultipleDependency {
        @objid ("ebaa454b-12ce-4b39-80b0-60ff83456e02")
        private SmDependency symetricDep;

        @objid ("9258fe8e-9c0c-4e16-b05a-74d29052a855")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateMachineData)data).mSubmachineState != null)? ((StateMachineData)data).mSubmachineState:SmMultipleDependency.EMPTY;
        }

        @objid ("48afae47-46a4-44a5-ab28-50553ffad2dc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateMachineData) data).mSubmachineState = values;
            
        }

        @objid ("9afea228-218c-40de-abc5-e04cc42254a4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getSubMachineDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d6e51b6d-c7cf-4c41-8763-18b7e6295a4b")
    public static class EntryPointSmDependency extends SmMultipleDependency {
        @objid ("2640866a-c182-49cd-85ab-d7f8fb047751")
        private SmDependency symetricDep;

        @objid ("e8a53021-49e2-43b7-8d26-d6d40ea13bb2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateMachineData)data).mEntryPoint != null)? ((StateMachineData)data).mEntryPoint:SmMultipleDependency.EMPTY;
        }

        @objid ("60305b3d-1a7f-47e5-94a4-f809a72ad1cb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateMachineData) data).mEntryPoint = values;
            
        }

        @objid ("7e60c491-4b98-4c37-b969-133043f21c04")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EntryPointPseudoStateSmClass)this.getTarget()).getEntryOfMachineDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("93cea68b-f00e-4fca-b143-d31e1939d5f8")
    public static class ExitPointSmDependency extends SmMultipleDependency {
        @objid ("cb5fffb0-f533-4229-9b58-78c1b236688a")
        private SmDependency symetricDep;

        @objid ("77476a66-f55a-4f74-9d00-b2cb28657bc8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateMachineData)data).mExitPoint != null)? ((StateMachineData)data).mExitPoint:SmMultipleDependency.EMPTY;
        }

        @objid ("e78b2e68-caae-4174-9eaa-236e30c0205e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateMachineData) data).mExitPoint = values;
            
        }

        @objid ("1cb8eac8-5a4d-4a94-a46c-3e6b628e6312")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExitPointPseudoStateSmClass)this.getTarget()).getExitOfMachineDep();
            }
            return this.symetricDep;
            
        }

    }

}
