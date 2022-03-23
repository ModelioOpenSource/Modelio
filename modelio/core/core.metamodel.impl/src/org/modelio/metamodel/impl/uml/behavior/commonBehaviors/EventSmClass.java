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

package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("05712d4f-4cfb-40cc-af04-edb94f0e9624")
public class EventSmClass extends UmlModelElementSmClass {
    @objid ("b1eca0a8-73e9-40a4-bb1c-44facb0b83a3")
    private SmAttribute expressionAtt;

    @objid ("33179fc7-0c58-4d4c-9f6a-1d4abba39d1a")
    private SmAttribute kindAtt;

    @objid ("0ac7147b-70b5-43dc-83a0-a97e0551b77c")
    private SmDependency triggeredDep;

    @objid ("5d210712-37c3-44ad-a82d-870c0a0a851c")
    private SmDependency modelDep;

    @objid ("30e2f8d7-53bf-4339-b965-3ba29949bead")
    private SmDependency originDep;

    @objid ("e03ad9e9-70c0-477b-8518-58a202f04938")
    private SmDependency calledDep;

    @objid ("a75db21a-14e6-4a36-875b-d13ef7cd030c")
    private SmDependency composedDep;

    @objid ("0325166e-3741-4c6c-a6e3-7d10d7486391")
    public  EventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7e7614bb-f510-47e6-8299-787ee4a3324a")
    @Override
    public String getName() {
        return "Event";
        
    }

    @objid ("74a77ccd-ccf9-466c-9a73-b24c9df5eddd")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ddc118de-59ba-4618-a2aa-f72faab6c423")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Event.class;
        
    }

    @objid ("96a58a24-dd4c-4122-83b1-d7622b099405")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("f71d308b-c1e8-4b1f-b362-d65f7f9bc34d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("55465732-0d7f-4dfc-adad-9d6c1599b57d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new EventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.expressionAtt = new ExpressionSmAttribute();
        this.expressionAtt.init("Expression", this, String.class );
        registerAttribute(this.expressionAtt);
        
        this.kindAtt = new KindSmAttribute();
        this.kindAtt.init("Kind", this, EventType.class );
        registerAttribute(this.kindAtt);
        
        
        // Initialize and register the SmDependency
        this.triggeredDep = new TriggeredSmDependency();
        this.triggeredDep.init("Triggered", this, metamodel.getMClass(Transition.MQNAME), 0, -1 );
        registerDependency(this.triggeredDep);
        
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.modelDep);
        
        this.originDep = new OriginSmDependency();
        this.originDep.init("Origin", this, metamodel.getMClass(State.MQNAME), 0, -1 );
        registerDependency(this.originDep);
        
        this.calledDep = new CalledSmDependency();
        this.calledDep.init("Called", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.calledDep);
        
        this.composedDep = new ComposedSmDependency();
        this.composedDep.init("Composed", this, metamodel.getMClass(Behavior.MQNAME), 1, 1 );
        registerDependency(this.composedDep);
        
        
    }

    @objid ("48540c3c-79e5-49f4-a880-ebc9069051f6")
    public SmAttribute getExpressionAtt() {
        if (this.expressionAtt == null) {
        	this.expressionAtt = this.getAttributeDef("Expression");
        }
        return this.expressionAtt;
    }

    @objid ("6fe23377-35ba-4210-8950-bc23613460cf")
    public SmAttribute getKindAtt() {
        if (this.kindAtt == null) {
        	this.kindAtt = this.getAttributeDef("Kind");
        }
        return this.kindAtt;
    }

    @objid ("d5776880-00ef-4ad0-a321-e35338214657")
    public SmDependency getTriggeredDep() {
        if (this.triggeredDep == null) {
        	this.triggeredDep = this.getDependencyDef("Triggered");
        }
        return this.triggeredDep;
    }

    @objid ("3be67d27-8dc2-4989-bbd9-526ff49a85f0")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("8936294a-f47a-44bb-a42c-988e3359062a")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("7b6a6917-d51d-46ef-bb57-d46af8db2030")
    public SmDependency getCalledDep() {
        if (this.calledDep == null) {
        	this.calledDep = this.getDependencyDef("Called");
        }
        return this.calledDep;
    }

    @objid ("74c2175a-ec52-4192-b121-2503cefc61e6")
    public SmDependency getComposedDep() {
        if (this.composedDep == null) {
        	this.composedDep = this.getDependencyDef("Composed");
        }
        return this.composedDep;
    }

    @objid ("dda9f412-ae8b-4425-ba67-9674f8a6c336")
    private static class EventObjectFactory implements ISmObjectFactory {
        @objid ("c5cbd79b-07f0-4a57-9644-d5363157c12d")
        private EventSmClass smClass;

        @objid ("01703612-2e15-4928-8a66-7af78771332d")
        public  EventObjectFactory(EventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("61027f5f-e7dd-4ab2-8e84-23bb1a2b9be4")
        @Override
        public ISmObjectData createData() {
            return new EventData(this.smClass);
        }

        @objid ("ad9b2c04-771c-4123-9f93-03a2458464c4")
        @Override
        public SmObjectImpl createImpl() {
            return new EventImpl();
        }

    }

    @objid ("287514de-0c70-44f6-b824-44b62b0d0438")
    public static class ExpressionSmAttribute extends SmAttribute {
        @objid ("06aadde8-54e7-4fa7-b203-4c51f02ef531")
        public Object getValue(ISmObjectData data) {
            return ((EventData) data).mExpression;
        }

        @objid ("46882c55-247f-4adb-a132-8b7fb20ea621")
        public void setValue(ISmObjectData data, Object value) {
            ((EventData) data).mExpression = value;
        }

    }

    @objid ("533272f3-678c-47bd-bda5-045c626400e8")
    public static class KindSmAttribute extends SmAttribute {
        @objid ("587ff30f-0348-4b25-aaab-727e831837d4")
        public Object getValue(ISmObjectData data) {
            return ((EventData) data).mKind;
        }

        @objid ("6e3699b2-489a-4fcc-8c4d-ec69bc3a3f03")
        public void setValue(ISmObjectData data, Object value) {
            ((EventData) data).mKind = value;
        }

    }

    @objid ("dcc9b19e-208e-4d79-8b20-f9bb65eb12af")
    public static class TriggeredSmDependency extends SmMultipleDependency {
        @objid ("0201bf00-1861-410e-bf30-42cddc0c3a62")
        private SmDependency symetricDep;

        @objid ("6a17647b-4dd0-4f95-84a3-3541061cfb50")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EventData)data).mTriggered != null)? ((EventData)data).mTriggered:SmMultipleDependency.EMPTY;
        }

        @objid ("ecede0f9-529d-449d-baf4-127f73e2aafb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EventData) data).mTriggered = values;
            
        }

        @objid ("5e8f09f2-5c82-4db2-8986-19ac5b0cf830")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getTriggerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("992e1da2-27ab-414b-98ab-13571789fb18")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("f463224a-e7dc-4e76-a14e-b41ad77bc23f")
        private SmDependency symetricDep;

        @objid ("3b365a01-c7a6-48cc-84e9-33c7777caf26")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EventData) data).mModel;
        }

        @objid ("ae5dc9e3-0610-4afa-ae97-f39d45dba22a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EventData) data).mModel = value;
        }

        @objid ("c364d74a-1144-48ee-a139-0e04598a3810")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getEOccurenceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("719bcb22-ea5d-46db-907b-9feb247b606c")
    public static class OriginSmDependency extends SmMultipleDependency {
        @objid ("f775c0e2-2123-4fe9-9fe1-ff46b9f405e1")
        private SmDependency symetricDep;

        @objid ("c91d9981-9dd5-4e3f-93bb-c0c9cf8f176f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EventData)data).mOrigin != null)? ((EventData)data).mOrigin:SmMultipleDependency.EMPTY;
        }

        @objid ("54477a7f-b24f-41ed-b49e-a5b33fe860df")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EventData) data).mOrigin = values;
            
        }

        @objid ("cfa8ef0c-dbc6-4835-8fa5-e6ac789a8630")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getDefferedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6b6ff990-e161-4fb8-b0fe-a67f54645d53")
    public static class CalledSmDependency extends SmSingleDependency {
        @objid ("0c762912-1702-4dcc-99ec-7d204e043c21")
        private SmDependency symetricDep;

        @objid ("9a894eb7-48cb-451a-9a59-ed97aa68735b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EventData) data).mCalled;
        }

        @objid ("ef2a4405-cb29-4ec4-a1ce-ad2e43a7366d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EventData) data).mCalled = value;
        }

        @objid ("9ee5b279-043b-43f6-9624-d92c7167419d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b3780d04-a999-4e3d-b12b-ef19555757d4")
    public static class ComposedSmDependency extends SmSingleDependency {
        @objid ("daa08bd2-37bc-4139-8492-87771a338ff5")
        private SmDependency symetricDep;

        @objid ("6e11a979-1e8d-4630-baea-6bb4a93d74af")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EventData) data).mComposed;
        }

        @objid ("028b6f9e-03a9-4db1-8a0b-811199895610")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EventData) data).mComposed = value;
        }

        @objid ("05bc90fa-1be2-46b2-8dae-14f161b354d7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getEComponentDep();
            }
            return this.symetricDep;
            
        }

    }

}
