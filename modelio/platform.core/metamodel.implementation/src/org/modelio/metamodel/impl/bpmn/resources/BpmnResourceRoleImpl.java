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
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000ab8a6-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceRoleImpl extends BpmnBaseElementImpl implements BpmnResourceRole {
    @objid ("f6451a66-ac9b-4c66-92c4-017d63fbf623")
    @Override
    public BpmnResource getResourceRef() {
        Object obj = getDepVal(((BpmnResourceRoleSmClass)getClassOf()).getResourceRefDep());
        return (obj instanceof BpmnResource)? (BpmnResource)obj : null;
    }

    @objid ("88039849-52c9-4ea6-9afd-fab57dfa87af")
    @Override
    public void setResourceRef(BpmnResource value) {
        appendDepVal(((BpmnResourceRoleSmClass)getClassOf()).getResourceRefDep(), (SmObjectImpl)value);
    }

    @objid ("4264b2f7-bba6-4a32-9740-9ef9eac83aa0")
    @Override
    public BpmnFlowNode getAnnotated() {
        Object obj = getDepVal(((BpmnResourceRoleSmClass)getClassOf()).getAnnotatedDep());
        return (obj instanceof BpmnFlowNode)? (BpmnFlowNode)obj : null;
    }

    @objid ("2c73766f-aaac-4bec-9cdc-cea288fd0452")
    @Override
    public void setAnnotated(BpmnFlowNode value) {
        appendDepVal(((BpmnResourceRoleSmClass)getClassOf()).getAnnotatedDep(), (SmObjectImpl)value);
    }

    @objid ("8ed6a1d4-b1b1-46e1-a98e-1263c5ccf8f6")
    @Override
    public EList<BpmnResourceParameterBinding> getResourceParameterBinding() {
        return new SmList<>(this, ((BpmnResourceRoleSmClass)getClassOf()).getResourceParameterBindingDep());
    }

    @objid ("0d9f9206-32af-497d-b0cc-b505d60fbfac")
    @Override
    public <T extends BpmnResourceParameterBinding> List<T> getResourceParameterBinding(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnResourceParameterBinding element : getResourceParameterBinding()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d795ce32-2350-4889-b0fa-b7adaaed3a1b")
    @Override
    public BpmnProcess getProcess() {
        Object obj = getDepVal(((BpmnResourceRoleSmClass)getClassOf()).getProcessDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("ab53cfa5-6d36-4481-9c54-d7dd1ce008bb")
    @Override
    public void setProcess(BpmnProcess value) {
        appendDepVal(((BpmnResourceRoleSmClass)getClassOf()).getProcessDep(), (SmObjectImpl)value);
    }

    @objid ("6b69f531-9294-4876-bd6e-4dbe08db60c2")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Annotated
        obj = (SmObjectImpl)this.getDepVal(((BpmnResourceRoleSmClass)getClassOf()).getAnnotatedDep());
        if (obj != null)
          return obj;
        // Process
        obj = (SmObjectImpl)this.getDepVal(((BpmnResourceRoleSmClass)getClassOf()).getProcessDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("b0b61709-b661-429c-a5fe-ccb007d9c825")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Annotated
        dep = ((BpmnResourceRoleSmClass)getClassOf()).getAnnotatedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Process
        dep = ((BpmnResourceRoleSmClass)getClassOf()).getProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("0bc18d2c-d376-423a-bda1-91cd25af5f79")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnResourceRole(this);
    }

}
