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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0055e47a-c4bf-1fd8-97fe-001ec947cd2a")
public class TransitionImpl extends UmlModelElementImpl implements Transition {
    @objid ("948c1b3e-3d54-4caa-bc4a-67e3af56d267")
    @Override
    public String getEffect() {
        return (String) getAttVal(((TransitionSmClass)getClassOf()).getEffectAtt());
    }

    @objid ("bcf1b1bc-fc2a-4800-8d5a-203022f91562")
    @Override
    public void setEffect(String value) {
        setAttVal(((TransitionSmClass)getClassOf()).getEffectAtt(), value);
    }

    @objid ("06223854-1bd1-4e7d-8fc3-badf26e5cc42")
    @Override
    public String getReceivedEvents() {
        return (String) getAttVal(((TransitionSmClass)getClassOf()).getReceivedEventsAtt());
    }

    @objid ("18917c27-5497-47ba-8eb6-a7e6943ab530")
    @Override
    public void setReceivedEvents(String value) {
        setAttVal(((TransitionSmClass)getClassOf()).getReceivedEventsAtt(), value);
    }

    @objid ("9010b6b8-d995-4514-b984-681969dd300d")
    @Override
    public String getSentEvents() {
        return (String) getAttVal(((TransitionSmClass)getClassOf()).getSentEventsAtt());
    }

    @objid ("495f4987-512c-4924-9ff3-6d75b519a23c")
    @Override
    public void setSentEvents(String value) {
        setAttVal(((TransitionSmClass)getClassOf()).getSentEventsAtt(), value);
    }

    @objid ("48ff9ad5-4bc4-4eec-865b-c504379adeb8")
    @Override
    public String getGuard() {
        return (String) getAttVal(((TransitionSmClass)getClassOf()).getGuardAtt());
    }

    @objid ("e0d818dc-2cb2-4260-a715-b7e63f5ea27c")
    @Override
    public void setGuard(String value) {
        setAttVal(((TransitionSmClass)getClassOf()).getGuardAtt(), value);
    }

    @objid ("6d4eb0a1-2888-49b8-bee3-6b072c79e5ae")
    @Override
    public String getPostCondition() {
        return (String) getAttVal(((TransitionSmClass)getClassOf()).getPostConditionAtt());
    }

    @objid ("3c84c6cf-b357-4170-8626-dc75ff281178")
    @Override
    public void setPostCondition(String value) {
        setAttVal(((TransitionSmClass)getClassOf()).getPostConditionAtt(), value);
    }

    @objid ("d6c09ccd-5eab-4663-ba06-e2b1fe6b736c")
    @Override
    public Operation getProcessed() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getProcessedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("a1aeb7bb-a0cb-4335-b900-e97a9c0a3430")
    @Override
    public void setProcessed(Operation value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getProcessedDep(), (SmObjectImpl)value);
    }

    @objid ("b9f9a698-ac9b-4bd9-af3f-00d36ce0d413")
    @Override
    public Event getTrigger() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getTriggerDep());
        return (obj instanceof Event)? (Event)obj : null;
    }

    @objid ("c85f4036-c9c8-4500-9f98-b77f75198642")
    @Override
    public void setTrigger(Event value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getTriggerDep(), (SmObjectImpl)value);
    }

    @objid ("9d92ae06-9ed1-4983-b27c-95ec3d17bf95")
    @Override
    public Behavior getBehaviorEffect() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getBehaviorEffectDep());
        return (obj instanceof Behavior)? (Behavior)obj : null;
    }

    @objid ("2c6cee53-75b4-450a-84e1-b302ff8cea38")
    @Override
    public void setBehaviorEffect(Behavior value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getBehaviorEffectDep(), (SmObjectImpl)value);
    }

    @objid ("90c4e233-edfa-4cd3-9000-5e49d8eb4640")
    @Override
    public StateVertex getTarget() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getTargetDep());
        return (obj instanceof StateVertex)? (StateVertex)obj : null;
    }

    @objid ("3bd81d06-0a6f-4775-938a-f1d55abacd28")
    @Override
    public void setTarget(StateVertex value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("f50e0789-5303-4508-b8ca-2bd740598fa0")
    @Override
    public StateVertex getSource() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getSourceDep());
        return (obj instanceof StateVertex)? (StateVertex)obj : null;
    }

    @objid ("41a4f152-98c4-4d59-807f-2835131ebc27")
    @Override
    public void setSource(StateVertex value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getSourceDep(), (SmObjectImpl)value);
    }

    @objid ("d4a007a9-dfab-4d80-b082-1087008cf8c7")
    @Override
    public Signal getEffects() {
        Object obj = getDepVal(((TransitionSmClass)getClassOf()).getEffectsDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("8bb15981-934e-4fc3-8290-31c27a41116e")
    @Override
    public void setEffects(Signal value) {
        appendDepVal(((TransitionSmClass)getClassOf()).getEffectsDep(), (SmObjectImpl)value);
    }

    @objid ("f530a36e-86a5-4a5c-b67a-869e32330083")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Source
        obj = (SmObjectImpl)this.getDepVal(((TransitionSmClass)getClassOf()).getSourceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("f47ec64b-d14f-49ee-868b-2c11bd98dfaa")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Source
        dep = ((TransitionSmClass)getClassOf()).getSourceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("6afceae8-a3cb-4a61-92db-a0557b63e178")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitTransition(this);
    }

}
