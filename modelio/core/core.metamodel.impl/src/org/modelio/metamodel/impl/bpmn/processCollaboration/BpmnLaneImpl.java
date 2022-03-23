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

package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00749e92-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLaneImpl extends BpmnBaseElementImpl implements BpmnLane {
    @objid ("9fae0260-0f66-4e9c-9a32-e11baf095a1b")
    @Override
    public BpmnLaneSet getChildLaneSet() {
        Object obj = getDepVal(((BpmnLaneSmClass)getClassOf()).getChildLaneSetDep());
        return (obj instanceof BpmnLaneSet)? (BpmnLaneSet)obj : null;
    }

    @objid ("d18e4d2e-4f5b-47a9-9793-00ae1e6bcb64")
    @Override
    public void setChildLaneSet(BpmnLaneSet value) {
        appendDepVal(((BpmnLaneSmClass)getClassOf()).getChildLaneSetDep(), (SmObjectImpl)value);
    }

    @objid ("8e22260f-5574-43a6-90b7-31b3ad78b0d0")
    @Override
    public EList<BpmnFlowElement> getFlowElementRef() {
        return new SmList<>(this, ((BpmnLaneSmClass)getClassOf()).getFlowElementRefDep());
    }

    @objid ("5903a4cc-6753-4bf7-962e-f718902f6272")
    @Override
    public <T extends BpmnFlowElement> List<T> getFlowElementRef(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnFlowElement element : getFlowElementRef()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("fd9019b1-1536-41fb-87e2-8d63d09c0499")
    @Override
    public BpmnLaneSet getLaneSet() {
        Object obj = getDepVal(((BpmnLaneSmClass)getClassOf()).getLaneSetDep());
        return (obj instanceof BpmnLaneSet)? (BpmnLaneSet)obj : null;
    }

    @objid ("e327fcb8-da8b-4355-a9ce-019ad404a8ff")
    @Override
    public void setLaneSet(BpmnLaneSet value) {
        appendDepVal(((BpmnLaneSmClass)getClassOf()).getLaneSetDep(), (SmObjectImpl)value);
    }

    @objid ("2e87706b-bed1-40e6-9992-87be9bcd9559")
    @Override
    public BpmnBaseElement getBpmnPartitionElementRef() {
        Object obj = getDepVal(((BpmnLaneSmClass)getClassOf()).getBpmnPartitionElementRefDep());
        return (obj instanceof BpmnBaseElement)? (BpmnBaseElement)obj : null;
    }

    @objid ("c52839dd-a6a4-4364-b365-dc4419219fd9")
    @Override
    public void setBpmnPartitionElementRef(BpmnBaseElement value) {
        appendDepVal(((BpmnLaneSmClass)getClassOf()).getBpmnPartitionElementRefDep(), (SmObjectImpl)value);
    }

    @objid ("41f5da44-aa0d-4e90-856c-bb82f49ae58d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // LaneSet
        obj = (SmObjectImpl)this.getDepVal(((BpmnLaneSmClass)getClassOf()).getLaneSetDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("c78a41ca-139d-4bdd-b030-0232ccccb2b7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // LaneSet
        dep = ((BpmnLaneSmClass)getClassOf()).getLaneSetDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("bcd16a0b-f7e1-4a6f-a6b2-f1917423d9d9")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnLane(this);
    }

}
