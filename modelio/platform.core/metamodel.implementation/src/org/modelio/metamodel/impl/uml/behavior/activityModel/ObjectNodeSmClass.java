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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeData;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorParameterSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("60b71769-0305-4555-b642-3fd3eccd55e4")
public class ObjectNodeSmClass extends ActivityNodeSmClass {
    @objid ("9a069911-893f-4f26-a62f-0b63ddb0e5d2")
    private SmAttribute isControlTypeAtt;

    @objid ("dff0e6c9-99e8-4f6d-a7b7-4174b73c1d8f")
    private SmAttribute orderingAtt;

    @objid ("521cec02-201f-4ec9-9237-bdade5400594")
    private SmAttribute selectionBehaviorAtt;

    @objid ("c19c18cc-f312-47cc-ae98-e19e8bccd355")
    private SmAttribute upperBoundAtt;

    @objid ("9c9d96c1-71d3-471b-aacb-afb9dd742560")
    private SmDependency representedDep;

    @objid ("1e0c38d9-04ec-4903-8844-9bf5325ef9d5")
    private SmDependency representedRealParameterDep;

    @objid ("2d02818a-01a3-4575-9081-96c5be99e828")
    private SmDependency typeDep;

    @objid ("5bbe1551-d2cb-414c-a1a8-529d8d54b288")
    private SmDependency representedRoleDep;

    @objid ("f93eb0ff-fcb7-4dec-9084-e48cfc049198")
    private SmDependency representedAttributeDep;

    @objid ("7da8c612-adf1-48d1-9212-41d51674f0cb")
    private SmDependency inStateDep;

    @objid ("80fd151c-a349-4735-939b-f0d0f8a782dc")
    public ObjectNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("85756cda-e4d1-4c29-a873-eb0ed7b9ce04")
    @Override
    public String getName() {
        return "ObjectNode";
    }

    @objid ("be993ccd-d354-4637-8894-524cfeb19a78")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c5417a5c-15e1-492c-bfda-1ea7ad129c53")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ObjectNode.class;
    }

    @objid ("4f7dc840-1596-4a1d-8e03-cc390af3bbec")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9135bb35-8e7b-45f2-a3aa-68382326dd8b")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("9b8be859-371e-4d92-a7ce-e6c999c06e2f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityNode.MQNAME);
        this.registerFactory(new ObjectNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isControlTypeAtt = new IsControlTypeSmAttribute();
        this.isControlTypeAtt.init("IsControlType", this, Boolean.class );
        registerAttribute(this.isControlTypeAtt);
        
        this.orderingAtt = new OrderingSmAttribute();
        this.orderingAtt.init("Ordering", this, ObjectNodeOrderingKind.class );
        registerAttribute(this.orderingAtt);
        
        this.selectionBehaviorAtt = new SelectionBehaviorSmAttribute();
        this.selectionBehaviorAtt.init("SelectionBehavior", this, String.class );
        registerAttribute(this.selectionBehaviorAtt);
        
        this.upperBoundAtt = new UpperBoundSmAttribute();
        this.upperBoundAtt.init("UpperBound", this, String.class );
        registerAttribute(this.upperBoundAtt);
        
        
        // Initialize and register the SmDependency
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Instance.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedDep);
        
        this.representedRealParameterDep = new RepresentedRealParameterSmDependency();
        this.representedRealParameterDep.init("RepresentedRealParameter", this, metamodel.getMClass(BehaviorParameter.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedRealParameterDep);
        
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(GeneralClass.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.representedRoleDep = new RepresentedRoleSmDependency();
        this.representedRoleDep.init("RepresentedRole", this, metamodel.getMClass(AssociationEnd.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedRoleDep);
        
        this.representedAttributeDep = new RepresentedAttributeSmDependency();
        this.representedAttributeDep.init("RepresentedAttribute", this, metamodel.getMClass(Attribute.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedAttributeDep);
        
        this.inStateDep = new InStateSmDependency();
        this.inStateDep.init("InState", this, metamodel.getMClass(State.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.inStateDep);
    }

    @objid ("cb4e81ac-489f-4350-91b6-34ad429d8690")
    public SmAttribute getIsControlTypeAtt() {
        if (this.isControlTypeAtt == null) {
        	this.isControlTypeAtt = this.getAttributeDef("IsControlType");
        }
        return this.isControlTypeAtt;
    }

    @objid ("2dd32dfa-5383-444d-9cd3-ebcc5d05a498")
    public SmAttribute getOrderingAtt() {
        if (this.orderingAtt == null) {
        	this.orderingAtt = this.getAttributeDef("Ordering");
        }
        return this.orderingAtt;
    }

    @objid ("0d091aa5-24e8-4f57-82eb-9962bebdbc75")
    public SmAttribute getSelectionBehaviorAtt() {
        if (this.selectionBehaviorAtt == null) {
        	this.selectionBehaviorAtt = this.getAttributeDef("SelectionBehavior");
        }
        return this.selectionBehaviorAtt;
    }

    @objid ("41ff8cde-0c33-4306-8526-fa309a4fec9f")
    public SmAttribute getUpperBoundAtt() {
        if (this.upperBoundAtt == null) {
        	this.upperBoundAtt = this.getAttributeDef("UpperBound");
        }
        return this.upperBoundAtt;
    }

    @objid ("6fda2de1-83f3-48fb-9db9-94e5fba3ae77")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("13afaf86-dce9-4666-b676-8a647fcb3fee")
    public SmDependency getRepresentedRealParameterDep() {
        if (this.representedRealParameterDep == null) {
        	this.representedRealParameterDep = this.getDependencyDef("RepresentedRealParameter");
        }
        return this.representedRealParameterDep;
    }

    @objid ("01008922-3988-4121-a748-fe795252d469")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("90092bb8-d222-4c4c-9190-548aa2366453")
    public SmDependency getRepresentedRoleDep() {
        if (this.representedRoleDep == null) {
        	this.representedRoleDep = this.getDependencyDef("RepresentedRole");
        }
        return this.representedRoleDep;
    }

    @objid ("f10e032f-835a-4d35-88d7-2f798a78555e")
    public SmDependency getRepresentedAttributeDep() {
        if (this.representedAttributeDep == null) {
        	this.representedAttributeDep = this.getDependencyDef("RepresentedAttribute");
        }
        return this.representedAttributeDep;
    }

    @objid ("96e7cb63-6c95-4621-b4e1-34b198981ff2")
    public SmDependency getInStateDep() {
        if (this.inStateDep == null) {
        	this.inStateDep = this.getDependencyDef("InState");
        }
        return this.inStateDep;
    }

    @objid ("8d702db0-816c-4aaa-ab3e-6fd472d71dac")
    private static class ObjectNodeObjectFactory implements ISmObjectFactory {
        @objid ("a9f7482d-1dc1-473e-87c2-11768ddc60bf")
        private ObjectNodeSmClass smClass;

        @objid ("a04a7dbc-61e0-45be-b551-8f41675e7349")
        public ObjectNodeObjectFactory(ObjectNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("649ce72d-ddac-4028-99f5-a4d43d123967")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("25e692f3-0d3b-4e38-bd30-6a2ff4599512")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("c1c2ceac-ade9-4745-a65b-be2cb3948436")
    public static class IsControlTypeSmAttribute extends SmAttribute {
        @objid ("19de5de8-c74f-4861-a651-39c5f0ffdf44")
        public Object getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mIsControlType;
        }

        @objid ("c05fae05-c428-4a61-8a9e-5ddcf365d343")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectNodeData) data).mIsControlType = value;
        }

    }

    @objid ("ef835d23-f5ff-426f-9d5c-e655989df510")
    public static class OrderingSmAttribute extends SmAttribute {
        @objid ("eb6ace98-b5af-4865-a96b-d656a985d5ef")
        public Object getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mOrdering;
        }

        @objid ("c17ccc2a-2fac-4076-941e-1f05ea84c7dc")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectNodeData) data).mOrdering = value;
        }

    }

    @objid ("b65e9545-3654-4c03-96d0-f02afdf6d742")
    public static class SelectionBehaviorSmAttribute extends SmAttribute {
        @objid ("db4f3681-43c0-4ff2-9f27-21bac20eba6d")
        public Object getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mSelectionBehavior;
        }

        @objid ("555d01c0-0eb9-4dbb-bfbe-1136c693cb81")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectNodeData) data).mSelectionBehavior = value;
        }

    }

    @objid ("c75ddb12-8275-458e-bdf2-dc60a48862cc")
    public static class UpperBoundSmAttribute extends SmAttribute {
        @objid ("3106ad23-d30f-430f-b093-5e198cf060fb")
        public Object getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mUpperBound;
        }

        @objid ("c9477b13-4a7a-4550-a643-3d4d97e65f1b")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectNodeData) data).mUpperBound = value;
        }

    }

    @objid ("75a21422-376f-4e30-9f2e-fe27274eb332")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("54db5322-9b78-463a-b93a-a64fa5db4f8f")
        private SmDependency symetricDep;

        @objid ("74da6f97-e63e-4b02-96e2-f58b61b6e3e2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mRepresented;
        }

        @objid ("b4efce2d-9148-470e-a493-afc4677e322f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mRepresented = value;
        }

        @objid ("37453f3d-8e06-4560-9cc0-90bcbaea5c86")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getRepresentingObjectNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c9739744-7fc6-4022-a005-398fda16305c")
    public static class RepresentedRealParameterSmDependency extends SmSingleDependency {
        @objid ("0d7b544e-3ab3-413c-aa92-efebaa62267b")
        private SmDependency symetricDep;

        @objid ("59900c3d-81cd-4674-b664-7c188eda39d4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mRepresentedRealParameter;
        }

        @objid ("4e2fabd8-e381-455a-85de-a773dc5a9b7b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mRepresentedRealParameter = value;
        }

        @objid ("f7622758-0852-47a0-9c48-4185f22faa46")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorParameterSmClass)this.getTarget()).getRepresentingObjectNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c5a029a8-71e1-4322-afa3-549a91c26977")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("46cea9a2-32ba-4791-9ecd-a1afcc19045e")
        private SmDependency symetricDep;

        @objid ("7ec1a8e8-82b5-423c-88f5-3f75d719e78d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mType;
        }

        @objid ("05e3ec69-75d7-4233-9393-44760c201820")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mType = value;
        }

        @objid ("3a30f85a-8103-4fd6-9328-222f305f2929")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralClassSmClass)this.getTarget()).getOccurenceObjectNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("bad2b412-4720-4f4f-a88c-69d0368edacd")
    public static class RepresentedRoleSmDependency extends SmSingleDependency {
        @objid ("4bcb85df-4e27-46f3-a750-90e74df3ba73")
        private SmDependency symetricDep;

        @objid ("a6e159fb-31b3-45a7-a55b-d2063a8b950d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mRepresentedRole;
        }

        @objid ("2a727608-45a1-4f45-b5d0-099bc09479e9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mRepresentedRole = value;
        }

        @objid ("161a966a-1ea4-450e-99cc-090db2700a63")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getRepresentingObjectNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("22ca8cf9-82a9-4cf3-8e68-d2c67e3e6d63")
    public static class RepresentedAttributeSmDependency extends SmSingleDependency {
        @objid ("4dd34a01-0381-4318-b858-86f68b176773")
        private SmDependency symetricDep;

        @objid ("ef287a8a-dae9-4098-9898-3ef3b8b7b6c7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mRepresentedAttribute;
        }

        @objid ("08824c86-105c-4f70-a171-65375ff1878d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mRepresentedAttribute = value;
        }

        @objid ("2b203703-d157-4863-bc96-3235bf247c66")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeSmClass)this.getTarget()).getRepresentingObjectNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e92027ed-2556-43eb-b758-74fc84a22c5f")
    public static class InStateSmDependency extends SmSingleDependency {
        @objid ("55b49032-a0bd-4652-9431-aa1cfd0e08de")
        private SmDependency symetricDep;

        @objid ("59825959-2d9a-4dfb-b47a-ffdd98d3824b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ObjectNodeData) data).mInState;
        }

        @objid ("a099dfb8-8252-4ca9-8e39-0fe9d279d933")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ObjectNodeData) data).mInState = value;
        }

        @objid ("013ac87a-9ea3-4758-8f1b-7d34bbc74d24")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getRequiredStateOfDep();
            }
            return this.symetricDep;
        }

    }

}
