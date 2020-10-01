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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.GateSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionData;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionUseSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1b1f068e-525b-4f7a-9473-ef168b604540")
public class InteractionSmClass extends BehaviorSmClass {
    @objid ("989128bb-2572-484d-8ee1-9bee779ae6e0")
    private SmDependency formalGateDep;

    @objid ("ceb6d745-1a19-4401-bb6c-fa2aa770edb5")
    private SmDependency fragmentDep;

    @objid ("afe8f285-803a-497e-a40f-e27901f66872")
    private SmDependency ownedLineDep;

    @objid ("c3afdad9-66f1-412a-bf11-307a42d3e0d0")
    private SmDependency referedUseDep;

    @objid ("b66254aa-5051-4441-bbee-bf7db689391f")
    public InteractionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8aa5e007-5535-4d69-b387-5929c1fb380e")
    @Override
    public String getName() {
        return "Interaction";
    }

    @objid ("22012ae8-20bb-4fa7-83bb-59ade7a39e71")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1fbeccb5-0fa4-4831-b433-0f9e9e801d77")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Interaction.class;
    }

    @objid ("5275254d-7860-45c1-97d4-7ce1ee1f298c")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("98ceab73-6d73-4b25-9861-250a55a3d734")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("9d83cb89-ddaf-4288-ba0c-b995fbe3c581")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new InteractionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.formalGateDep = new FormalGateSmDependency();
        this.formalGateDep.init("FormalGate", this, metamodel.getMClass(Gate.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.formalGateDep);
        
        this.fragmentDep = new FragmentSmDependency();
        this.fragmentDep.init("Fragment", this, metamodel.getMClass(InteractionFragment.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.fragmentDep);
        
        this.ownedLineDep = new OwnedLineSmDependency();
        this.ownedLineDep.init("OwnedLine", this, metamodel.getMClass(Lifeline.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedLineDep);
        
        this.referedUseDep = new ReferedUseSmDependency();
        this.referedUseDep.init("ReferedUse", this, metamodel.getMClass(InteractionUse.MQNAME), 0, -1 );
        registerDependency(this.referedUseDep);
    }

    @objid ("bb6189fd-606c-4ad4-a4da-7153954be8b4")
    public SmDependency getFormalGateDep() {
        if (this.formalGateDep == null) {
        	this.formalGateDep = this.getDependencyDef("FormalGate");
        }
        return this.formalGateDep;
    }

    @objid ("554a6f4d-3f10-47a6-a6d6-7b9173b18825")
    public SmDependency getFragmentDep() {
        if (this.fragmentDep == null) {
        	this.fragmentDep = this.getDependencyDef("Fragment");
        }
        return this.fragmentDep;
    }

    @objid ("b1352494-0073-4c0c-bf08-830d00dece3f")
    public SmDependency getOwnedLineDep() {
        if (this.ownedLineDep == null) {
        	this.ownedLineDep = this.getDependencyDef("OwnedLine");
        }
        return this.ownedLineDep;
    }

    @objid ("37c0d2c0-5458-4caa-ac3f-bdd0c6d4c8f6")
    public SmDependency getReferedUseDep() {
        if (this.referedUseDep == null) {
        	this.referedUseDep = this.getDependencyDef("ReferedUse");
        }
        return this.referedUseDep;
    }

    @objid ("9b2b5ab7-c549-4f7b-9019-297665a5779b")
    private static class InteractionObjectFactory implements ISmObjectFactory {
        @objid ("b49cfd67-54cf-4936-a8aa-cb0c65438583")
        private InteractionSmClass smClass;

        @objid ("8acfcfb4-c7db-49ca-baf2-337a7cc16fe0")
        public InteractionObjectFactory(InteractionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f2e35cf0-3806-4286-a92e-065669ff9e46")
        @Override
        public ISmObjectData createData() {
            return new InteractionData(this.smClass);
        }

        @objid ("465ddd88-f9c2-4099-8367-8532d9e1c26e")
        @Override
        public SmObjectImpl createImpl() {
            return new InteractionImpl();
        }

    }

    @objid ("a8410462-4edd-4ef7-8f5a-02f6e7514255")
    public static class FormalGateSmDependency extends SmMultipleDependency {
        @objid ("aa829409-f6df-4310-8b99-36de630ac43e")
        private SmDependency symetricDep;

        @objid ("594a29f2-e3cf-4ca3-a56e-f293eb2a71e0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionData)data).mFormalGate != null)? ((InteractionData)data).mFormalGate:SmMultipleDependency.EMPTY;
        }

        @objid ("6240ca0a-88b0-4e79-aa0c-a9957ff3df29")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionData) data).mFormalGate = values;
        }

        @objid ("cd887624-d4c7-4ae6-acfe-b67a974ac4b4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GateSmClass)this.getTarget()).getOwnerInteractionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6080babd-9847-43f9-8c35-fb1ca46234a3")
    public static class FragmentSmDependency extends SmMultipleDependency {
        @objid ("356f8b39-9fb6-4503-99d0-1ca40eb328d4")
        private SmDependency symetricDep;

        @objid ("fbe40c34-d95b-4d96-8bf1-b02fdedd65bc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionData)data).mFragment != null)? ((InteractionData)data).mFragment:SmMultipleDependency.EMPTY;
        }

        @objid ("fc9f33d7-f460-4495-85f1-5e7b76d78414")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionData) data).mFragment = values;
        }

        @objid ("d3e6cb28-1ef8-4ddb-8da9-a3eec85d06d9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionFragmentSmClass)this.getTarget()).getEnclosingInteractionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c0389a7c-b00a-43fd-bf0f-b6bc24249dfa")
    public static class OwnedLineSmDependency extends SmMultipleDependency {
        @objid ("cabfa07a-de69-4fd2-87b0-99c47e13df0e")
        private SmDependency symetricDep;

        @objid ("2245be72-03fa-4c42-8dab-0932797bbe63")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionData)data).mOwnedLine != null)? ((InteractionData)data).mOwnedLine:SmMultipleDependency.EMPTY;
        }

        @objid ("e028aae0-781c-492f-a616-f3abfebf103d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionData) data).mOwnedLine = values;
        }

        @objid ("5d0b528c-884e-4a83-b603-68236f885c4a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LifelineSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0c845625-3e90-4f59-af96-6827d23be178")
    public static class ReferedUseSmDependency extends SmMultipleDependency {
        @objid ("af3351b8-848f-422a-b7bd-4e653f57c665")
        private SmDependency symetricDep;

        @objid ("325662f0-2f72-4536-b600-29112f0f445b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InteractionData)data).mReferedUse != null)? ((InteractionData)data).mReferedUse:SmMultipleDependency.EMPTY;
        }

        @objid ("93cabff7-deca-4833-9a55-d9cb6bce13af")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InteractionData) data).mReferedUse = values;
        }

        @objid ("60bc079d-d661-4ab8-9c10-0e7c7418c402")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionUseSmClass)this.getTarget()).getRefersToDep();
            }
            return this.symetricDep;
        }

    }

}
