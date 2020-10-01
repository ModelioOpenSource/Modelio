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
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0079db46-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnFlowNodeImpl extends BpmnFlowElementImpl implements BpmnFlowNode {
    @objid ("c799b940-1200-45e7-a13d-a6ac00dd554f")
    @Override
    public EList<BpmnSequenceFlow> getOutgoing() {
        return new SmList<>(this, ((BpmnFlowNodeSmClass)getClassOf()).getOutgoingDep());
    }

    @objid ("ffff158d-aa11-4194-9971-90dfc1dcc2e7")
    @Override
    public <T extends BpmnSequenceFlow> List<T> getOutgoing(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnSequenceFlow element : getOutgoing()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5456b411-f301-491d-91be-0429b0c5a2c9")
    @Override
    public EList<BpmnResourceRole> getResource() {
        return new SmList<>(this, ((BpmnFlowNodeSmClass)getClassOf()).getResourceDep());
    }

    @objid ("4e13bb09-fa4a-4a52-a28d-c1f20d9e36fc")
    @Override
    public <T extends BpmnResourceRole> List<T> getResource(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnResourceRole element : getResource()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ab309742-cb94-436a-b948-42e4202324fe")
    @Override
    public EList<BpmnSequenceFlow> getIncoming() {
        return new SmList<>(this, ((BpmnFlowNodeSmClass)getClassOf()).getIncomingDep());
    }

    @objid ("6945ee2d-9a55-4499-b2c9-b15b0e09cc63")
    @Override
    public <T extends BpmnSequenceFlow> List<T> getIncoming(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnSequenceFlow element : getIncoming()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("27d03faf-0d77-4170-89a5-a83af12f4513")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0490073e-915a-4835-8612-55cb32920ba0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("d5c69188-7a8e-407c-aa45-aa5e12466415")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnFlowNode(this);
    }

}
