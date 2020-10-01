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
package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007c0b32-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMessageImpl extends BpmnSharedElementImpl implements BpmnMessage {
    @objid ("59f9335d-9a75-4765-bf6d-ec29cad871d9")
    @Override
    public EList<BpmnOperation> getOutputMessage() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getOutputMessageDep());
    }

    @objid ("6d781a4c-5572-4e3b-aaf5-8a05a23b4316")
    @Override
    public <T extends BpmnOperation> List<T> getOutputMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnOperation element : getOutputMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4e21d57b-5ab0-425a-9401-197c8db9053c")
    @Override
    public BpmnItemDefinition getItemRef() {
        Object obj = getDepVal(((BpmnMessageSmClass)getClassOf()).getItemRefDep());
        return (obj instanceof BpmnItemDefinition)? (BpmnItemDefinition)obj : null;
    }

    @objid ("ee43e780-b726-4d91-b21d-988cd3119764")
    @Override
    public void setItemRef(BpmnItemDefinition value) {
        appendDepVal(((BpmnMessageSmClass)getClassOf()).getItemRefDep(), (SmObjectImpl)value);
    }

    @objid ("85fd3d3b-ad58-4a9a-9862-58c09c5fbe19")
    @Override
    public EList<BpmnMessageEventDefinition> getEventDefinition() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getEventDefinitionDep());
    }

    @objid ("59af077f-6d33-41b5-9dd3-fd3471db5a8d")
    @Override
    public <T extends BpmnMessageEventDefinition> List<T> getEventDefinition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessageEventDefinition element : getEventDefinition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8bc567d1-4b24-4af8-88a6-330aa597a4b9")
    @Override
    public EList<BpmnSendTask> getSender() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getSenderDep());
    }

    @objid ("7492b24b-d811-409a-b2f9-eeaf68013137")
    @Override
    public <T extends BpmnSendTask> List<T> getSender(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnSendTask element : getSender()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8a24f0c7-7bce-4ec9-ba8c-30f731bb3e0f")
    @Override
    public EList<BpmnOperation> getInputMessage() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getInputMessageDep());
    }

    @objid ("b8b47a05-7c4c-4191-9c02-005bf938ba03")
    @Override
    public <T extends BpmnOperation> List<T> getInputMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnOperation element : getInputMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3309cb4a-708f-4ae5-b1fd-498facdfbc4c")
    @Override
    public EList<BpmnReceiveTask> getReceiver() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getReceiverDep());
    }

    @objid ("8b28ed26-c24f-4872-953e-53801bd890eb")
    @Override
    public <T extends BpmnReceiveTask> List<T> getReceiver(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnReceiveTask element : getReceiver()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("443c8713-d49e-40f3-8d23-242e2701653b")
    @Override
    public EList<BpmnMessageFlow> getMessageFlow() {
        return new SmList<>(this, ((BpmnMessageSmClass)getClassOf()).getMessageFlowDep());
    }

    @objid ("27c9a88d-a0b3-42ba-a714-e68ba593da0f")
    @Override
    public <T extends BpmnMessageFlow> List<T> getMessageFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessageFlow element : getMessageFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("70450c81-ee17-4d22-a63f-0f047b2dce56")
    @Override
    public BpmnCollaboration getCollaboration() {
        Object obj = getDepVal(((BpmnMessageSmClass)getClassOf()).getCollaborationDep());
        return (obj instanceof BpmnCollaboration)? (BpmnCollaboration)obj : null;
    }

    @objid ("2be92ac5-7379-4446-8f7e-7e01a8a05aa2")
    @Override
    public void setCollaboration(BpmnCollaboration value) {
        appendDepVal(((BpmnMessageSmClass)getClassOf()).getCollaborationDep(), (SmObjectImpl)value);
    }

    @objid ("6b273fb8-8c0c-441d-babe-38596076190d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Collaboration
        obj = (SmObjectImpl)this.getDepVal(((BpmnMessageSmClass)getClassOf()).getCollaborationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("4b716b7a-475f-46ce-b99e-18ba84887e2a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Collaboration
        dep = ((BpmnMessageSmClass)getClassOf()).getCollaborationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("46f8285c-491a-41b6-9295-011ad914c8dd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnMessage(this);
    }

}
