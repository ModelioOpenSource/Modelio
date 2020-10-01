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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0079514e-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnFlowElementImpl extends BpmnBaseElementImpl implements BpmnFlowElement {
    @objid ("62f0a084-b544-44cb-81bb-d12a869ee651")
    @Override
    public boolean isTriggeredByEvent() {
        return (Boolean) getAttVal(((BpmnFlowElementSmClass)getClassOf()).getTriggeredByEventAtt());
    }

    @objid ("d5dac6c1-f34e-45e6-8743-2f26a3645dcf")
    @Override
    public void setTriggeredByEvent(boolean value) {
        setAttVal(((BpmnFlowElementSmClass)getClassOf()).getTriggeredByEventAtt(), value);
    }

    @objid ("2bd2608f-f6eb-47cc-9bbb-3b7622c314d3")
    @Override
    public EList<BpmnGroup> getGroups() {
        return new SmList<>(this, ((BpmnFlowElementSmClass)getClassOf()).getGroupsDep());
    }

    @objid ("fdc14afe-496e-42d6-9cda-ce0ad2259bca")
    @Override
    public <T extends BpmnGroup> List<T> getGroups(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnGroup element : getGroups()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7aa73851-9c4a-4288-8e2e-937e646772e1")
    @Override
    public BpmnSubProcess getSubProcess() {
        Object obj = getDepVal(((BpmnFlowElementSmClass)getClassOf()).getSubProcessDep());
        return (obj instanceof BpmnSubProcess)? (BpmnSubProcess)obj : null;
    }

    @objid ("dd3ccd37-503b-409d-8837-c761da13ed84")
    @Override
    public void setSubProcess(BpmnSubProcess value) {
        appendDepVal(((BpmnFlowElementSmClass)getClassOf()).getSubProcessDep(), (SmObjectImpl)value);
    }

    @objid ("e1fdac92-85a5-4b4d-bea2-6760bed0e2ec")
    @Override
    public EList<BpmnLane> getLane() {
        return new SmList<>(this, ((BpmnFlowElementSmClass)getClassOf()).getLaneDep());
    }

    @objid ("6e848f3a-9497-4cfa-8a41-db293d897c89")
    @Override
    public <T extends BpmnLane> List<T> getLane(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnLane element : getLane()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("88a225a5-9b79-4af6-b630-c1051348e8fe")
    @Override
    public BpmnProcess getContainer() {
        Object obj = getDepVal(((BpmnFlowElementSmClass)getClassOf()).getContainerDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("a9521bec-f62d-4a40-846c-63097bcd968f")
    @Override
    public void setContainer(BpmnProcess value) {
        appendDepVal(((BpmnFlowElementSmClass)getClassOf()).getContainerDep(), (SmObjectImpl)value);
    }

    @objid ("fb5e516a-7a66-4225-99c6-7105b0df059c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SubProcess
        obj = (SmObjectImpl)this.getDepVal(((BpmnFlowElementSmClass)getClassOf()).getSubProcessDep());
        if (obj != null)
          return obj;
        // Container
        obj = (SmObjectImpl)this.getDepVal(((BpmnFlowElementSmClass)getClassOf()).getContainerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("c008f33a-57af-480c-9e57-3580fda4bc21")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SubProcess
        dep = ((BpmnFlowElementSmClass)getClassOf()).getSubProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Container
        dep = ((BpmnFlowElementSmClass)getClassOf()).getContainerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("d84e16a6-68fa-4349-8dc7-612330f237ce")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnFlowElement(this);
    }

}
