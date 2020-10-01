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
package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000d7c30-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnOperationImpl extends BpmnBaseElementImpl implements BpmnOperation {
    @objid ("758ff4ac-6fd5-4f0c-a0a0-bf14f8a55de2")
    @Override
    public EList<BpmnSendTask> getSender() {
        return new SmList<>(this, ((BpmnOperationSmClass)getClassOf()).getSenderDep());
    }

    @objid ("032603c6-e684-4b30-bdfb-9b94580823b6")
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

    @objid ("8a048ff6-d27c-4573-bc26-1e8a132e6307")
    @Override
    public BpmnMessage getInMessageRef() {
        Object obj = getDepVal(((BpmnOperationSmClass)getClassOf()).getInMessageRefDep());
        return (obj instanceof BpmnMessage)? (BpmnMessage)obj : null;
    }

    @objid ("6839941f-ff0c-4765-ab1d-6ceb1d9ed15a")
    @Override
    public void setInMessageRef(BpmnMessage value) {
        appendDepVal(((BpmnOperationSmClass)getClassOf()).getInMessageRefDep(), (SmObjectImpl)value);
    }

    @objid ("4337905e-b6d1-4875-855e-1444c8cbed65")
    @Override
    public EList<BpmnServiceTask> getCaller() {
        return new SmList<>(this, ((BpmnOperationSmClass)getClassOf()).getCallerDep());
    }

    @objid ("f7ff76a2-686e-4705-8187-8c1da7f71025")
    @Override
    public <T extends BpmnServiceTask> List<T> getCaller(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnServiceTask element : getCaller()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("badefa59-8d2d-4075-a896-53d6c7ddbebd")
    @Override
    public BpmnMessage getOutMessageRef() {
        Object obj = getDepVal(((BpmnOperationSmClass)getClassOf()).getOutMessageRefDep());
        return (obj instanceof BpmnMessage)? (BpmnMessage)obj : null;
    }

    @objid ("4e96fa18-f32b-48c2-99c6-3091671b34da")
    @Override
    public void setOutMessageRef(BpmnMessage value) {
        appendDepVal(((BpmnOperationSmClass)getClassOf()).getOutMessageRefDep(), (SmObjectImpl)value);
    }

    @objid ("faa1e9f2-73bc-40ad-9a08-ae0092714497")
    @Override
    public EList<BpmnMessageEventDefinition> getEventDefinition() {
        return new SmList<>(this, ((BpmnOperationSmClass)getClassOf()).getEventDefinitionDep());
    }

    @objid ("29aa1c4c-99a0-4695-a6e6-659a6193b0f2")
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

    @objid ("da1df089-0d16-4d39-8be2-43d3188755f9")
    @Override
    public BpmnInterface getBpmnInterfaceRef() {
        Object obj = getDepVal(((BpmnOperationSmClass)getClassOf()).getBpmnInterfaceRefDep());
        return (obj instanceof BpmnInterface)? (BpmnInterface)obj : null;
    }

    @objid ("738c68f5-ee0e-4bc7-97df-e0e1f81027ca")
    @Override
    public void setBpmnInterfaceRef(BpmnInterface value) {
        appendDepVal(((BpmnOperationSmClass)getClassOf()).getBpmnInterfaceRefDep(), (SmObjectImpl)value);
    }

    @objid ("7bc2b5c8-1251-40b1-b322-9b581dfd557a")
    @Override
    public EList<BpmnReceiveTask> getReceiver() {
        return new SmList<>(this, ((BpmnOperationSmClass)getClassOf()).getReceiverDep());
    }

    @objid ("2eafc8b1-03b4-46e1-86a2-cbcc5fc43bb6")
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

    @objid ("94e1dc67-1229-4fa8-b646-0e29ae70863d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // BpmnInterfaceRef
        obj = (SmObjectImpl)this.getDepVal(((BpmnOperationSmClass)getClassOf()).getBpmnInterfaceRefDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("235903ae-e16e-426e-b736-fef60fb2b6e7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // BpmnInterfaceRef
        dep = ((BpmnOperationSmClass)getClassOf()).getBpmnInterfaceRefDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("345a1a71-e94b-42cf-8a6d-6928a0ec9ffb")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnOperation(this);
    }

}
