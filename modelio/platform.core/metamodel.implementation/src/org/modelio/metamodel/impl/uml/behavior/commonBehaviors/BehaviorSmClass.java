/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallBehaviorActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorParameterSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
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

@objid ("263559ae-b792-4606-bc85-3bc49331fac4")
public class BehaviorSmClass extends UmlModelElementSmClass {
    @objid ("fe9d0247-e334-4a86-bef6-9b265231357a")
    private SmAttribute isReentrantAtt;

    @objid ("42a3662b-1b5b-476a-ae48-19c39bb77ef6")
    private SmDependency ownerDep;

    @objid ("0b01ab0d-7933-4e69-a8a4-80f916a20cae")
    private SmDependency parameterDep;

    @objid ("aadbc6a0-b141-44ef-ab3d-5c27c0f34d0d")
    private SmDependency ownerOperationDep;

    @objid ("88333cf7-eb6b-4da5-9e30-61d0b6fb490a")
    private SmDependency ownedCollaborationDep;

    @objid ("49aee30d-eb87-40d0-b5d7-664e138a6723")
    private SmDependency callerDep;

    @objid ("00438226-0384-49ec-84fc-b7914f51a3cd")
    private SmDependency eComponentDep;

    @objid ("b5fb8d20-f834-4bee-bc2b-2e2e3380e624")
    private SmDependency effectOfDep;

    @objid ("8dd8f75a-65f6-476f-8903-296af69e5932")
    public BehaviorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ba65ba59-70b6-4c97-bef2-88edbd3c25e5")
    @Override
    public String getName() {
        return "Behavior";
    }

    @objid ("2e39be2b-69b3-4d7e-8dff-8e7aca4de639")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9205c4ef-085c-461b-beb4-38b0fd770cfe")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Behavior.class;
    }

    @objid ("a93cde29-7ccc-4666-a495-50e6b007c79f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f7f3a9a4-4bcd-40b0-af55-a1efa99b5f7b")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("a8a6a597-9aa3-41db-a13a-35fbb87f0513")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new BehaviorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isReentrantAtt = new IsReentrantSmAttribute();
        this.isReentrantAtt.init("IsReentrant", this, Boolean.class );
        registerAttribute(this.isReentrantAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.parameterDep = new ParameterSmDependency();
        this.parameterDep.init("Parameter", this, metamodel.getMClass(BehaviorParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.parameterDep);
        
        this.ownerOperationDep = new OwnerOperationSmDependency();
        this.ownerOperationDep.init("OwnerOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.ownerOperationDep);
        
        this.ownedCollaborationDep = new OwnedCollaborationSmDependency();
        this.ownedCollaborationDep.init("OwnedCollaboration", this, metamodel.getMClass(Collaboration.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedCollaborationDep);
        
        this.callerDep = new CallerSmDependency();
        this.callerDep.init("Caller", this, metamodel.getMClass(CallBehaviorAction.MQNAME), 0, -1 );
        registerDependency(this.callerDep);
        
        this.eComponentDep = new EComponentSmDependency();
        this.eComponentDep.init("EComponent", this, metamodel.getMClass(Event.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.eComponentDep);
        
        this.effectOfDep = new EffectOfSmDependency();
        this.effectOfDep.init("EffectOf", this, metamodel.getMClass(Transition.MQNAME), 0, -1 );
        registerDependency(this.effectOfDep);
    }

    @objid ("81d5efe7-92c7-45f3-8a23-3f302499fe0d")
    public SmAttribute getIsReentrantAtt() {
        if (this.isReentrantAtt == null) {
        	this.isReentrantAtt = this.getAttributeDef("IsReentrant");
        }
        return this.isReentrantAtt;
    }

    @objid ("623f86f4-f8f2-490a-b499-651de09e7642")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("7fd64f1a-fe0e-4880-ab66-b9fa53b61ba3")
    public SmDependency getParameterDep() {
        if (this.parameterDep == null) {
        	this.parameterDep = this.getDependencyDef("Parameter");
        }
        return this.parameterDep;
    }

    @objid ("ef6ed789-cf24-4b33-a666-d48447f3e58f")
    public SmDependency getOwnerOperationDep() {
        if (this.ownerOperationDep == null) {
        	this.ownerOperationDep = this.getDependencyDef("OwnerOperation");
        }
        return this.ownerOperationDep;
    }

    @objid ("b9690fb4-c0cb-4e98-94a9-0b2bc02bb420")
    public SmDependency getOwnedCollaborationDep() {
        if (this.ownedCollaborationDep == null) {
        	this.ownedCollaborationDep = this.getDependencyDef("OwnedCollaboration");
        }
        return this.ownedCollaborationDep;
    }

    @objid ("0e3873dc-5f25-44c0-aea0-018c2db386c4")
    public SmDependency getCallerDep() {
        if (this.callerDep == null) {
        	this.callerDep = this.getDependencyDef("Caller");
        }
        return this.callerDep;
    }

    @objid ("6347e333-d1e0-4ed5-ace0-ebd888b9b0b9")
    public SmDependency getEComponentDep() {
        if (this.eComponentDep == null) {
        	this.eComponentDep = this.getDependencyDef("EComponent");
        }
        return this.eComponentDep;
    }

    @objid ("56961aea-e3d8-4963-9a81-f1ba0d24380a")
    public SmDependency getEffectOfDep() {
        if (this.effectOfDep == null) {
        	this.effectOfDep = this.getDependencyDef("EffectOf");
        }
        return this.effectOfDep;
    }

    @objid ("a904cfd4-9f8d-43c8-aebe-c37f5f7e8ab8")
    private static class BehaviorObjectFactory implements ISmObjectFactory {
        @objid ("f86c366f-1ba9-4609-ba35-5cde62bc3c21")
        private BehaviorSmClass smClass;

        @objid ("9724a944-e384-43e6-b623-b99de241fbe2")
        public BehaviorObjectFactory(BehaviorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8b9641e2-0fe2-4e86-a7c6-9c808f601e61")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("2ff7f9d9-d5cc-4455-bbb4-732eb422e243")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("27531df8-2475-4c87-bf97-1c4f5236ac27")
    public static class IsReentrantSmAttribute extends SmAttribute {
        @objid ("f4ad4839-a044-48b3-bfa5-efcf980f253e")
        public Object getValue(ISmObjectData data) {
            return ((BehaviorData) data).mIsReentrant;
        }

        @objid ("d856e803-69eb-450e-b16f-96f1c6b73c58")
        public void setValue(ISmObjectData data, Object value) {
            ((BehaviorData) data).mIsReentrant = value;
        }

    }

    @objid ("ac1d0444-b5cd-4d44-8010-ff5541ed274e")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("a377a2bc-1db2-46fe-8d8a-ae3e1eaa5b05")
        private SmDependency symetricDep;

        @objid ("f4f2cdff-b863-4af1-86cf-992a8a22bd24")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BehaviorData) data).mOwner;
        }

        @objid ("b36dac94-aad0-40c2-8d7e-4ff4b5f8c1c1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BehaviorData) data).mOwner = value;
        }

        @objid ("3413be44-d864-40db-8e49-0f8a35d4a956")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedBehaviorDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a91e9eaa-2798-4694-a33d-cff4cde37c4a")
    public static class ParameterSmDependency extends SmMultipleDependency {
        @objid ("2d47cbb4-aa67-40ad-8c91-5adce037d1d6")
        private SmDependency symetricDep;

        @objid ("9168b962-8cbe-40d1-9f12-04951d733f11")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorData)data).mParameter != null)? ((BehaviorData)data).mParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("114c18f5-0375-43c1-b21f-996cba3c8b8a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorData) data).mParameter = values;
        }

        @objid ("d3a22d5a-11ab-454a-a125-86bf53c38a35")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorParameterSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2f9465e7-2701-41e4-b1a7-fcc8b7c43fa4")
    public static class OwnerOperationSmDependency extends SmSingleDependency {
        @objid ("d574e481-edbf-4818-9a2b-f0a237f13228")
        private SmDependency symetricDep;

        @objid ("30290508-e611-4c68-9fc7-ad22e2e2ab85")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BehaviorData) data).mOwnerOperation;
        }

        @objid ("3d7455f1-1a80-48dc-9823-876c62e2bb54")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BehaviorData) data).mOwnerOperation = value;
        }

        @objid ("a72daf15-05b9-44a9-8fce-53f1a53cafbb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOwnedBehaviorDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("522b5a55-072b-4ba2-a9cb-72331a7aecdb")
    public static class OwnedCollaborationSmDependency extends SmMultipleDependency {
        @objid ("c5f58e50-ff80-4b73-be8a-0da97ece81ac")
        private SmDependency symetricDep;

        @objid ("97505e4b-c901-48b5-aa35-a9cc51bc18db")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorData)data).mOwnedCollaboration != null)? ((BehaviorData)data).mOwnedCollaboration:SmMultipleDependency.EMPTY;
        }

        @objid ("6059a0b1-2d0d-45ba-b1f2-3cdc923eebea")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorData) data).mOwnedCollaboration = values;
        }

        @objid ("069f42e2-abb6-4bff-bc0c-f223396b75d9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationSmClass)this.getTarget()).getBRepresentedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9fbed98a-4910-4fb5-9478-d48e7fbc8954")
    public static class CallerSmDependency extends SmMultipleDependency {
        @objid ("8bcc86c1-34f4-4119-a395-3e7c0b2ddbaa")
        private SmDependency symetricDep;

        @objid ("fcb7b8cc-16fe-4c51-a8d9-66ed0cee3dd1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorData)data).mCaller != null)? ((BehaviorData)data).mCaller:SmMultipleDependency.EMPTY;
        }

        @objid ("82e73ff4-7e6d-4a84-a0fc-57d3b102bff4")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorData) data).mCaller = values;
        }

        @objid ("dc85e338-76d7-4599-80ff-558c1de64764")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CallBehaviorActionSmClass)this.getTarget()).getCalledDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("91f067a3-654a-426b-8463-ab91e6e4d30c")
    public static class EComponentSmDependency extends SmMultipleDependency {
        @objid ("2ed8c6e1-f066-4f71-b943-d0131eb00f9a")
        private SmDependency symetricDep;

        @objid ("50fe56a8-9494-43e7-8928-e814e1336e8b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorData)data).mEComponent != null)? ((BehaviorData)data).mEComponent:SmMultipleDependency.EMPTY;
        }

        @objid ("c90028ed-c249-4750-838d-85a6914ce240")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorData) data).mEComponent = values;
        }

        @objid ("48f4a520-2d35-4568-a529-c2f2636a30fc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EventSmClass)this.getTarget()).getComposedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7893f36d-c3bf-4e52-8ac5-1d379b9bdc1b")
    public static class EffectOfSmDependency extends SmMultipleDependency {
        @objid ("e56c5046-2250-47bf-aadb-bf509a76b16e")
        private SmDependency symetricDep;

        @objid ("a28c532f-1589-4585-a0cd-c9266e2911f7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BehaviorData)data).mEffectOf != null)? ((BehaviorData)data).mEffectOf:SmMultipleDependency.EMPTY;
        }

        @objid ("adde751b-2e2d-4d8a-a79d-34958750f164")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BehaviorData) data).mEffectOf = values;
        }

        @objid ("2ef1fbf8-9146-4195-8edb-2f66f60c59d8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getBehaviorEffectDep();
            }
            return this.symetricDep;
        }

    }

}
