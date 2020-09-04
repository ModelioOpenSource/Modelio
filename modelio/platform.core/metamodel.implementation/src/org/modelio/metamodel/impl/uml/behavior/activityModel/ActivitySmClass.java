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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("0cf56662-cbd9-4437-9a92-9d3cc49f704a")
public class ActivitySmClass extends BehaviorSmClass {
    @objid ("ff1c9ea5-b876-470e-845d-c4c231128b86")
    private SmAttribute isSingleExecutionAtt;

    @objid ("7f12d2e0-af49-43ea-bcd0-19e74f978fd2")
    private SmAttribute isReadOnlyAtt;

    @objid ("6e21cfee-4aa7-4ea9-be7f-3ad6407dd090")
    private SmDependency ownedGroupDep;

    @objid ("33003b9b-83e4-44d8-a5ef-1de3a6a8ac3d")
    private SmDependency ownedNodeDep;

    @objid ("703b7380-4154-48c9-bdcb-8b82a5ca156e")
    public ActivitySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3cbd6b9e-87d5-41a5-8ff1-fb787fbf2c2a")
    @Override
    public String getName() {
        return "Activity";
    }

    @objid ("838a461a-baa4-4263-9776-ea22dba6b578")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9acd3719-2c79-4a65-9eb1-ef3aea1f6be0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Activity.class;
    }

    @objid ("05c3ca45-0dc2-41d8-9b9b-d66a2f346bfa")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("37121e9e-bc6e-489d-a9e7-71704e97b174")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ed7aa951-4f9b-4613-80be-609d604b7e87")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new ActivityObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isSingleExecutionAtt = new IsSingleExecutionSmAttribute();
        this.isSingleExecutionAtt.init("IsSingleExecution", this, Boolean.class );
        registerAttribute(this.isSingleExecutionAtt);
        
        this.isReadOnlyAtt = new IsReadOnlySmAttribute();
        this.isReadOnlyAtt.init("IsReadOnly", this, Boolean.class );
        registerAttribute(this.isReadOnlyAtt);
        
        
        // Initialize and register the SmDependency
        this.ownedGroupDep = new OwnedGroupSmDependency();
        this.ownedGroupDep.init("OwnedGroup", this, metamodel.getMClass(ActivityGroup.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedGroupDep);
        
        this.ownedNodeDep = new OwnedNodeSmDependency();
        this.ownedNodeDep.init("OwnedNode", this, metamodel.getMClass(ActivityNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedNodeDep);
    }

    @objid ("3a07bd67-127d-498d-ad78-78b9c9f60cb1")
    public SmAttribute getIsSingleExecutionAtt() {
        if (this.isSingleExecutionAtt == null) {
        	this.isSingleExecutionAtt = this.getAttributeDef("IsSingleExecution");
        }
        return this.isSingleExecutionAtt;
    }

    @objid ("c799e098-1cf8-4fbd-86f7-086f3d491186")
    public SmAttribute getIsReadOnlyAtt() {
        if (this.isReadOnlyAtt == null) {
        	this.isReadOnlyAtt = this.getAttributeDef("IsReadOnly");
        }
        return this.isReadOnlyAtt;
    }

    @objid ("5d8ac876-2199-4c06-833f-2d6ee02521c9")
    public SmDependency getOwnedGroupDep() {
        if (this.ownedGroupDep == null) {
        	this.ownedGroupDep = this.getDependencyDef("OwnedGroup");
        }
        return this.ownedGroupDep;
    }

    @objid ("381e4e0c-af04-4880-a87d-355b7c426c57")
    public SmDependency getOwnedNodeDep() {
        if (this.ownedNodeDep == null) {
        	this.ownedNodeDep = this.getDependencyDef("OwnedNode");
        }
        return this.ownedNodeDep;
    }

    @objid ("e28eb2b1-04af-464e-be5c-5b2950a14f3a")
    private static class ActivityObjectFactory implements ISmObjectFactory {
        @objid ("7846f0c6-dab6-45ab-9eed-02bf31c82a4f")
        private ActivitySmClass smClass;

        @objid ("664cee80-38bf-401f-b64f-1ef556e31238")
        public ActivityObjectFactory(ActivitySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("89945232-2b10-4e19-89de-9b33be4aedb8")
        @Override
        public ISmObjectData createData() {
            return new ActivityData(this.smClass);
        }

        @objid ("77ceedb1-0829-4e13-b4f5-bc3e0dad7349")
        @Override
        public SmObjectImpl createImpl() {
            return new ActivityImpl();
        }

    }

    @objid ("568bc5b9-f4f2-4db5-9368-e6bbd8d910cf")
    public static class IsSingleExecutionSmAttribute extends SmAttribute {
        @objid ("5dc27404-3c60-429a-b4c0-8aa7ae1fbb65")
        public Object getValue(ISmObjectData data) {
            return ((ActivityData) data).mIsSingleExecution;
        }

        @objid ("d5e81f2c-289b-452c-959d-2a8c73c7c9a3")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityData) data).mIsSingleExecution = value;
        }

    }

    @objid ("48dc53c8-d2e5-4fc8-bbf2-6f396f936d3c")
    public static class IsReadOnlySmAttribute extends SmAttribute {
        @objid ("c0206608-9771-40ab-aecb-6837fbd6cffa")
        public Object getValue(ISmObjectData data) {
            return ((ActivityData) data).mIsReadOnly;
        }

        @objid ("a8f8ff8b-c230-4043-9bef-eb7f7f3a3985")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityData) data).mIsReadOnly = value;
        }

    }

    @objid ("335b7190-22b4-493a-b96e-05b9574782c5")
    public static class OwnedGroupSmDependency extends SmMultipleDependency {
        @objid ("3ab521a4-2245-4894-aad7-8dba8924da10")
        private SmDependency symetricDep;

        @objid ("717a9d88-f8da-40f7-930b-15a41e31aa73")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityData)data).mOwnedGroup != null)? ((ActivityData)data).mOwnedGroup:SmMultipleDependency.EMPTY;
        }

        @objid ("675a63c3-3847-48ca-88fa-60634dca3c3d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityData) data).mOwnedGroup = values;
        }

        @objid ("d96267ff-2d81-4a8e-acaa-c123895cf919")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityGroupSmClass)this.getTarget()).getInActivityDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("403b7a2f-2812-4e66-8d26-86db91fb65db")
    public static class OwnedNodeSmDependency extends SmMultipleDependency {
        @objid ("330093a4-b914-45c0-b34f-640a1582d27f")
        private SmDependency symetricDep;

        @objid ("4948946d-b4a9-42fa-b9c1-62cad259727d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityData)data).mOwnedNode != null)? ((ActivityData)data).mOwnedNode:SmMultipleDependency.EMPTY;
        }

        @objid ("a33677ea-3d10-43db-a04e-4d7c92fcbbc5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityData) data).mOwnedNode = values;
        }

        @objid ("bf6b93ed-8dcb-4330-ad84-de5189e130d1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
