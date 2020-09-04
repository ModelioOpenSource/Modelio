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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityPartitionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivitySmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ClauseSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.StructuredActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("8d2f590b-0d3a-4c92-8c33-7c5fc66bec82")
public class ActivityNodeSmClass extends UmlModelElementSmClass {
    @objid ("7e8690e0-d8ff-4164-a6d6-5465b0654813")
    private SmDependency ownerDep;

    @objid ("5eec7e8c-a9f3-46b6-973e-0a337c88d51e")
    private SmDependency ownerPartitionDep;

    @objid ("0eed2537-dd7d-4394-a16e-de03ee47c0f3")
    private SmDependency incomingDep;

    @objid ("59532bd9-3eb6-464c-abbd-d4efb7fa890e")
    private SmDependency ownerClauseDep;

    @objid ("295a9244-b22d-48bc-b4c6-a13fa23d3b0a")
    private SmDependency ownerNodeDep;

    @objid ("7a01924f-ca65-40d2-819a-db0cd5966568")
    private SmDependency outgoingDep;

    @objid ("4a2f8de8-b654-43b4-b2b0-891638d474f4")
    public ActivityNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cf8a01a5-3de6-4838-be60-a9c429f3b1bc")
    @Override
    public String getName() {
        return "ActivityNode";
    }

    @objid ("dd205423-33bc-4d99-ac36-3d39efc4e237")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1f4d7d2c-3622-41c2-972a-9cdb71191fe4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityNode.class;
    }

    @objid ("2281f2fd-bbac-4856-9493-f2c1bb78bb3e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("920dcbd2-31cc-4b53-b506-8339f86b9866")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("118ac486-275b-4433-803a-dbb07257a356")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ActivityNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Activity.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.ownerPartitionDep = new OwnerPartitionSmDependency();
        this.ownerPartitionDep.init("OwnerPartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, 1 );
        registerDependency(this.ownerPartitionDep);
        
        this.incomingDep = new IncomingSmDependency();
        this.incomingDep.init("Incoming", this, metamodel.getMClass(ActivityEdge.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.incomingDep);
        
        this.ownerClauseDep = new OwnerClauseSmDependency();
        this.ownerClauseDep.init("OwnerClause", this, metamodel.getMClass(Clause.MQNAME), 0, 1 );
        registerDependency(this.ownerClauseDep);
        
        this.ownerNodeDep = new OwnerNodeSmDependency();
        this.ownerNodeDep.init("OwnerNode", this, metamodel.getMClass(StructuredActivityNode.MQNAME), 0, 1 );
        registerDependency(this.ownerNodeDep);
        
        this.outgoingDep = new OutgoingSmDependency();
        this.outgoingDep.init("Outgoing", this, metamodel.getMClass(ActivityEdge.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.outgoingDep);
    }

    @objid ("3bb3833e-f965-48f0-8077-9d551975f8eb")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("fba053b7-5c6a-41fb-b42f-836611da9ac7")
    public SmDependency getOwnerPartitionDep() {
        if (this.ownerPartitionDep == null) {
        	this.ownerPartitionDep = this.getDependencyDef("OwnerPartition");
        }
        return this.ownerPartitionDep;
    }

    @objid ("15cce8d3-149a-4be5-bb2d-7200ab7d56cc")
    public SmDependency getIncomingDep() {
        if (this.incomingDep == null) {
        	this.incomingDep = this.getDependencyDef("Incoming");
        }
        return this.incomingDep;
    }

    @objid ("98e52a39-8fca-4311-9410-4f17b6431b0b")
    public SmDependency getOwnerClauseDep() {
        if (this.ownerClauseDep == null) {
        	this.ownerClauseDep = this.getDependencyDef("OwnerClause");
        }
        return this.ownerClauseDep;
    }

    @objid ("98eeebef-70f7-4c11-bd98-9e3d9df62018")
    public SmDependency getOwnerNodeDep() {
        if (this.ownerNodeDep == null) {
        	this.ownerNodeDep = this.getDependencyDef("OwnerNode");
        }
        return this.ownerNodeDep;
    }

    @objid ("63cd796a-d1f1-4c7b-9648-b37b974e63dc")
    public SmDependency getOutgoingDep() {
        if (this.outgoingDep == null) {
        	this.outgoingDep = this.getDependencyDef("Outgoing");
        }
        return this.outgoingDep;
    }

    @objid ("c58e5b63-d0e3-4933-a580-226a5cbad281")
    private static class ActivityNodeObjectFactory implements ISmObjectFactory {
        @objid ("efd6954b-75f6-4909-94af-7064dd8ca53b")
        private ActivityNodeSmClass smClass;

        @objid ("55b95737-f030-4f23-9740-51f0290b0126")
        public ActivityNodeObjectFactory(ActivityNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("939391a1-f498-40ea-814e-5838247817ed")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("7d615029-cbb6-4a94-a902-26f65da32bf5")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("011d4468-3ba8-484d-a415-bc611b01ab30")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("8382c658-e53f-4c0c-a119-aa6013719ba4")
        private SmDependency symetricDep;

        @objid ("b414ad17-a80c-47f5-ab29-dff51f478e94")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityNodeData) data).mOwner;
        }

        @objid ("c2d8d14c-c26a-4aae-9264-41dccbb8d1e2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityNodeData) data).mOwner = value;
        }

        @objid ("ead2275f-fa6d-4645-9f41-1bac956607be")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivitySmClass)this.getTarget()).getOwnedNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("315d7b45-87b4-4f21-b415-3dc3485e85e8")
    public static class OwnerPartitionSmDependency extends SmSingleDependency {
        @objid ("47484b0d-7f2a-4907-9dea-e1c34092ecaa")
        private SmDependency symetricDep;

        @objid ("ae57067b-7627-48f0-a084-637977b7500e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityNodeData) data).mOwnerPartition;
        }

        @objid ("54091394-6022-4dcb-9733-0dc927f9c4f3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityNodeData) data).mOwnerPartition = value;
        }

        @objid ("8d479204-92b2-4d8d-86d1-8044b205b51a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getContainedNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("25680ebe-38c4-4407-bc50-a53bf29becdd")
    public static class IncomingSmDependency extends SmMultipleDependency {
        @objid ("0527d69d-eb88-4dcc-bf85-764c504dce4a")
        private SmDependency symetricDep;

        @objid ("b61f3721-3e84-4e38-9f64-b46e2719a8d7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityNodeData)data).mIncoming != null)? ((ActivityNodeData)data).mIncoming:SmMultipleDependency.EMPTY;
        }

        @objid ("f9aa1a14-fcb7-4a58-960e-dc4280105bd0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityNodeData) data).mIncoming = values;
        }

        @objid ("63f637a4-32fd-4a5b-96c3-bd0783208442")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityEdgeSmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("ac0c2517-4ef8-4ae1-9e58-23f5bc7a38b1")
    public static class OwnerClauseSmDependency extends SmSingleDependency {
        @objid ("afc998c0-b39c-4875-b816-bc22d516e27a")
        private SmDependency symetricDep;

        @objid ("9ba61026-bc64-45cf-85eb-48b10ee5880b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityNodeData) data).mOwnerClause;
        }

        @objid ("b60c141c-8e7e-443c-a7d8-2654c081eaf9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityNodeData) data).mOwnerClause = value;
        }

        @objid ("53bd7a01-6797-45a1-9729-2e6219544605")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClauseSmClass)this.getTarget()).getBodyDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bd141321-fa1e-4a7d-b64b-30fde41de35e")
    public static class OwnerNodeSmDependency extends SmSingleDependency {
        @objid ("0ff950ae-03de-4b34-9917-ef01c8d656d7")
        private SmDependency symetricDep;

        @objid ("b073e83f-3b22-47f0-b9ab-68e8cc295762")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ActivityNodeData) data).mOwnerNode;
        }

        @objid ("aed90d94-6c72-4c4d-9930-c13e3d4435bb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ActivityNodeData) data).mOwnerNode = value;
        }

        @objid ("8242fd2c-40df-42a9-af0e-5ff451605969")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StructuredActivityNodeSmClass)this.getTarget()).getBodyDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("59638af5-9a0c-485b-81f8-3c310c71c56e")
    public static class OutgoingSmDependency extends SmMultipleDependency {
        @objid ("35dfe69b-f15b-4d23-8da4-a75aa2c176e7")
        private SmDependency symetricDep;

        @objid ("1ae95e79-b294-48ef-a818-a4ec122526a0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityNodeData)data).mOutgoing != null)? ((ActivityNodeData)data).mOutgoing:SmMultipleDependency.EMPTY;
        }

        @objid ("c5c33e00-2a6a-4bb1-86ab-dafc92e12ebc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityNodeData) data).mOutgoing = values;
        }

        @objid ("699f92ad-b5b2-4afe-b940-e7b671d4f0ec")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityEdgeSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
        }

    }

}
