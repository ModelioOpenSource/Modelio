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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
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

@objid ("13e4b29b-d374-4a42-88de-bb3e167df301")
public class GateSmClass extends MessageEndSmClass {
    @objid ("9eca8b58-159d-46b7-b9ba-a58af05a12e3")
    private SmDependency ownerUseDep;

    @objid ("162f455f-5f74-4f1c-9d8b-086281d52c7a")
    private SmDependency actualDep;

    @objid ("4bdcacc0-2b4f-4bd3-b487-c0a1e6520966")
    private SmDependency ownerInteractionDep;

    @objid ("3445c182-525e-4e5e-850d-2c5352cac81b")
    private SmDependency ownerFragmentDep;

    @objid ("88055650-6276-4b08-86e3-54edc2dd804f")
    private SmDependency formalDep;

    @objid ("6aa39c8d-1c27-47a1-b544-bbfc13542d24")
    public  GateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("bf247c5c-dfa3-443b-b1d5-f2f9a654f258")
    @Override
    public String getName() {
        return "Gate";
        
    }

    @objid ("354f113b-42f7-4fcc-986f-bc1a7f9eb790")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2fa25b05-f4a4-471d-bb66-e3f7bb4a65b7")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Gate.class;
        
    }

    @objid ("f76d04ae-b083-476f-91e5-5e2471b881e9")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("cc68684b-a1fe-4049-b6ed-686e2faee5f5")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("fc94d5c3-f64e-4c0a-9e85-0db23202c3e1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(MessageEnd.MQNAME);
        this.registerFactory(new GateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerUseDep = new OwnerUseSmDependency();
        this.ownerUseDep.init("OwnerUse", this, metamodel.getMClass(InteractionUse.MQNAME), 0, 1 );
        registerDependency(this.ownerUseDep);
        
        this.actualDep = new ActualSmDependency();
        this.actualDep.init("Actual", this, metamodel.getMClass(Gate.MQNAME), 0, -1 );
        registerDependency(this.actualDep);
        
        this.ownerInteractionDep = new OwnerInteractionSmDependency();
        this.ownerInteractionDep.init("OwnerInteraction", this, metamodel.getMClass(Interaction.MQNAME), 0, 1 );
        registerDependency(this.ownerInteractionDep);
        
        this.ownerFragmentDep = new OwnerFragmentSmDependency();
        this.ownerFragmentDep.init("OwnerFragment", this, metamodel.getMClass(CombinedFragment.MQNAME), 0, 1 );
        registerDependency(this.ownerFragmentDep);
        
        this.formalDep = new FormalSmDependency();
        this.formalDep.init("Formal", this, metamodel.getMClass(Gate.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.formalDep);
        
        
    }

    @objid ("3ff48af0-7a46-4c30-ab82-7b32ef088d21")
    public SmDependency getOwnerUseDep() {
        if (this.ownerUseDep == null) {
        	this.ownerUseDep = this.getDependencyDef("OwnerUse");
        }
        return this.ownerUseDep;
    }

    @objid ("eb2c007f-035a-4941-a3df-3e063d9b0e12")
    public SmDependency getActualDep() {
        if (this.actualDep == null) {
        	this.actualDep = this.getDependencyDef("Actual");
        }
        return this.actualDep;
    }

    @objid ("6214da09-f281-450b-882c-0ed115bf415a")
    public SmDependency getOwnerInteractionDep() {
        if (this.ownerInteractionDep == null) {
        	this.ownerInteractionDep = this.getDependencyDef("OwnerInteraction");
        }
        return this.ownerInteractionDep;
    }

    @objid ("63f089ae-e2a3-478c-a2bc-3afc4fdd6632")
    public SmDependency getOwnerFragmentDep() {
        if (this.ownerFragmentDep == null) {
        	this.ownerFragmentDep = this.getDependencyDef("OwnerFragment");
        }
        return this.ownerFragmentDep;
    }

    @objid ("3f657b52-1d5f-4e83-8174-c9bec2d9c762")
    public SmDependency getFormalDep() {
        if (this.formalDep == null) {
        	this.formalDep = this.getDependencyDef("Formal");
        }
        return this.formalDep;
    }

    @objid ("d9e21d60-fb7e-4e68-9d36-e3924396ca58")
    private static class GateObjectFactory implements ISmObjectFactory {
        @objid ("0db3cf22-9c34-4482-8915-54cbec34f019")
        private GateSmClass smClass;

        @objid ("e9e5f1fc-45dc-4596-ade6-b59361227ffd")
        public  GateObjectFactory(GateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3fa0b77f-405e-4859-82e2-8be08c4d6710")
        @Override
        public ISmObjectData createData() {
            return new GateData(this.smClass);
        }

        @objid ("75dad494-0077-43da-83fc-f75ac905f204")
        @Override
        public SmObjectImpl createImpl() {
            return new GateImpl();
        }

    }

    @objid ("f8c1c3f7-092c-48cf-a5a7-b7b9d6dda132")
    public static class OwnerUseSmDependency extends SmSingleDependency {
        @objid ("c364f64a-353d-40c8-a14f-03f9d4c22d15")
        private SmDependency symetricDep;

        @objid ("48a18724-05b0-4739-8850-ef94f06ee788")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GateData) data).mOwnerUse;
        }

        @objid ("d4ecca52-a048-4e30-9fb9-762e59efe5de")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GateData) data).mOwnerUse = value;
        }

        @objid ("8cabb4f5-0676-44c2-8b0f-ccfbcb426732")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionUseSmClass)this.getTarget()).getActualGateDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("8522dba8-9b2a-4144-a6c3-af2e6af2dc0e")
    public static class ActualSmDependency extends SmMultipleDependency {
        @objid ("1ed524f5-45f9-4643-91e6-0ac9f0c80cb7")
        private SmDependency symetricDep;

        @objid ("18019ed8-76a4-4db8-a364-68ff2ed97a38")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((GateData)data).mActual != null)? ((GateData)data).mActual:SmMultipleDependency.EMPTY;
        }

        @objid ("242b9e4a-7d08-4190-889b-4c9415267bcb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((GateData) data).mActual = values;
            
        }

        @objid ("56edc165-a72f-41c0-aa80-90f2a0ff388e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GateSmClass)this.getTarget()).getFormalDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b6ae6581-1e1d-4c74-b3c3-bf8f7bad18de")
    public static class OwnerInteractionSmDependency extends SmSingleDependency {
        @objid ("c1b62cca-04b2-493e-ae06-9292ce407a5d")
        private SmDependency symetricDep;

        @objid ("a2386498-704c-4068-89ff-c06a78487560")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GateData) data).mOwnerInteraction;
        }

        @objid ("2f7934ff-4564-47be-b626-94b04de2cd74")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GateData) data).mOwnerInteraction = value;
        }

        @objid ("2065983f-3c9d-4bef-9541-4075c81228f3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionSmClass)this.getTarget()).getFormalGateDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("31a12d96-28a4-4c12-a101-67ecf0441025")
    public static class OwnerFragmentSmDependency extends SmSingleDependency {
        @objid ("bcba334a-5adb-4459-9dea-049ba7a70817")
        private SmDependency symetricDep;

        @objid ("2094fbaf-6f1e-4471-8c8c-213986999d3b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GateData) data).mOwnerFragment;
        }

        @objid ("0bf6e844-21d7-4474-a52b-3cb969c78653")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GateData) data).mOwnerFragment = value;
        }

        @objid ("321d9e38-412f-497b-8dd3-cee603ea993e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CombinedFragmentSmClass)this.getTarget()).getFragmentGateDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b25e318b-7977-417a-adf5-695b1496a47a")
    public static class FormalSmDependency extends SmSingleDependency {
        @objid ("3eb99938-b1cd-4940-82ad-02e9cb80451f")
        private SmDependency symetricDep;

        @objid ("94536862-005b-4ca9-9bb1-3195166f13d8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GateData) data).mFormal;
        }

        @objid ("4ad683df-c4fa-4284-9540-401b7abb0397")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GateData) data).mFormal = value;
        }

        @objid ("eda391ab-e2a8-4b31-9ec4-fd96a1fe3212")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GateSmClass)this.getTarget()).getActualDep();
            }
            return this.symetricDep;
            
        }

    }

}
