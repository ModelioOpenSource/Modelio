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

package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00845b16-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSubProcessImpl extends BpmnActivityImpl implements BpmnSubProcess {
    @objid ("f0aa1fc8-d41a-4afd-a5c2-54984ab9f00d")
    @Override
    public EList<BpmnArtifact> getArtifact() {
        return new SmList<>(this, ((BpmnSubProcessSmClass)getClassOf()).getArtifactDep());
    }

    @objid ("a28c4475-00f6-468f-82f5-61f6a23d3112")
    @Override
    public <T extends BpmnArtifact> List<T> getArtifact(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnArtifact element : getArtifact()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("5116bfa4-2fb4-4cbd-8552-a02a79b778ef")
    @Override
    public EList<BpmnFlowElement> getFlowElement() {
        return new SmList<>(this, ((BpmnSubProcessSmClass)getClassOf()).getFlowElementDep());
    }

    @objid ("05a70c63-b76d-4de8-a897-7cf569ad27c6")
    @Override
    public <T extends BpmnFlowElement> List<T> getFlowElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnFlowElement element : getFlowElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("59ca1f4e-b7c6-40ab-95a0-284461a99cfc")
    @Override
    public BpmnLaneSet getLaneSet() {
        Object obj = getDepVal(((BpmnSubProcessSmClass)getClassOf()).getLaneSetDep());
        return (obj instanceof BpmnLaneSet)? (BpmnLaneSet)obj : null;
    }

    @objid ("0ad8f51d-cf50-4a4e-ad6c-3092eec9e066")
    @Override
    public void setLaneSet(BpmnLaneSet value) {
        appendDepVal(((BpmnSubProcessSmClass)getClassOf()).getLaneSetDep(), (SmObjectImpl)value);
    }

    @objid ("d9cdd8e6-0a0c-48b7-b7cd-bd31f5568a87")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3b77c78a-a73f-4820-8050-69b13247f3e9")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("6f5a900f-ed44-403d-bdda-929d1eed932d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnSubProcess(this);
    }

}
