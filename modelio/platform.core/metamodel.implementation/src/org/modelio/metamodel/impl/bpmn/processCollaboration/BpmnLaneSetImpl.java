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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0075254c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLaneSetImpl extends BpmnBaseElementImpl implements BpmnLaneSet {
    @objid ("cc162c8a-d3db-4a7d-8c55-26a814a99357")
    @Override
    public EList<BpmnLane> getLane() {
        return new SmList<>(this, ((BpmnLaneSetSmClass)getClassOf()).getLaneDep());
    }

    @objid ("a1100123-4143-49d3-9e2e-144e51733b76")
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

    @objid ("7dd1ddea-8e6f-40ce-8e2e-e9416bfd9c37")
    @Override
    public BpmnProcess getProcess() {
        Object obj = getDepVal(((BpmnLaneSetSmClass)getClassOf()).getProcessDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("ad390a8f-2072-4baf-aca4-9981b2bc88ee")
    @Override
    public void setProcess(BpmnProcess value) {
        appendDepVal(((BpmnLaneSetSmClass)getClassOf()).getProcessDep(), (SmObjectImpl)value);
    }

    @objid ("f2374dcf-4594-48a1-83c4-7d97b74e0ef6")
    @Override
    public BpmnLane getParentLane() {
        Object obj = getDepVal(((BpmnLaneSetSmClass)getClassOf()).getParentLaneDep());
        return (obj instanceof BpmnLane)? (BpmnLane)obj : null;
    }

    @objid ("4357a199-078d-4bd1-87c2-3e4dd8d3895c")
    @Override
    public void setParentLane(BpmnLane value) {
        appendDepVal(((BpmnLaneSetSmClass)getClassOf()).getParentLaneDep(), (SmObjectImpl)value);
    }

    @objid ("2c0f34ac-f742-4b68-a5df-aa75abc72b3e")
    @Override
    public BpmnSubProcess getSubProcess() {
        Object obj = getDepVal(((BpmnLaneSetSmClass)getClassOf()).getSubProcessDep());
        return (obj instanceof BpmnSubProcess)? (BpmnSubProcess)obj : null;
    }

    @objid ("029f7c86-b1f6-47d5-92f2-6fea23cf12a7")
    @Override
    public void setSubProcess(BpmnSubProcess value) {
        appendDepVal(((BpmnLaneSetSmClass)getClassOf()).getSubProcessDep(), (SmObjectImpl)value);
    }

    @objid ("5a208363-a969-4fa5-a3a0-b6578f339c25")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Process
        obj = (SmObjectImpl)this.getDepVal(((BpmnLaneSetSmClass)getClassOf()).getProcessDep());
        if (obj != null)
          return obj;
        // ParentLane
        obj = (SmObjectImpl)this.getDepVal(((BpmnLaneSetSmClass)getClassOf()).getParentLaneDep());
        if (obj != null)
          return obj;
        // SubProcess
        obj = (SmObjectImpl)this.getDepVal(((BpmnLaneSetSmClass)getClassOf()).getSubProcessDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a0aab127-1f29-4efd-9aad-00c0cd1767a9")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Process
        dep = ((BpmnLaneSetSmClass)getClassOf()).getProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ParentLane
        dep = ((BpmnLaneSetSmClass)getClassOf()).getParentLaneDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // SubProcess
        dep = ((BpmnLaneSetSmClass)getClassOf()).getSubProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("359d42a8-2a73-45a4-9539-55bb2c165511")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnLaneSet(this);
    }

}
