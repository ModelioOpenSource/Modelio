/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0078058c-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnBaseElementImpl extends ModelElementImpl implements BpmnBaseElement {
    @objid ("f969d2b2-246a-4536-87b8-064fceb116e0")
    @Override
    public MObject getBpmnContext() {
        return getBpmnContext(getCompositionOwner());
    }

    @objid ("7549749f-2033-4952-a907-1e2e90107994")
    private MObject getBpmnContext(final MObject elt) {
        if (elt == null) {
            return null;
        } else if (elt instanceof NameSpace || elt instanceof Operation) {
            return elt;
        } else {
            return getBpmnContext(elt.getCompositionOwner());
        }
    }

    @objid ("c8f8d019-fd8b-4a49-a476-269caf7c8745")
    @Override
    public EList<BpmnAssociation> getOutgoingAssoc() {
        return new SmList<>(this, ((BpmnBaseElementSmClass)getClassOf()).getOutgoingAssocDep());
    }

    @objid ("bddee4bf-6eb9-46f3-baf7-b196810348b5")
    @Override
    public <T extends BpmnAssociation> List<T> getOutgoingAssoc(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnAssociation element : getOutgoingAssoc()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("659659e0-4792-403c-b924-0a94331d1f61")
    @Override
    public EList<BpmnAssociation> getIncomingAssoc() {
        return new SmList<>(this, ((BpmnBaseElementSmClass)getClassOf()).getIncomingAssocDep());
    }

    @objid ("4f6160db-75a0-448b-aba5-4e326647f961")
    @Override
    public <T extends BpmnAssociation> List<T> getIncomingAssoc(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnAssociation element : getIncomingAssoc()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f2d4b90b-1f4c-4db6-b3f7-371173127b46")
    @Override
    public EList<BpmnMessageFlow> getIncomingFlow() {
        return new SmList<>(this, ((BpmnBaseElementSmClass)getClassOf()).getIncomingFlowDep());
    }

    @objid ("fe8d0636-1134-4346-b070-ff5a27be5456")
    @Override
    public <T extends BpmnMessageFlow> List<T> getIncomingFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessageFlow element : getIncomingFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("a893b455-09ea-42f9-8ec2-95c292a92348")
    @Override
    public EList<BpmnMessageFlow> getOutgoingFlow() {
        return new SmList<>(this, ((BpmnBaseElementSmClass)getClassOf()).getOutgoingFlowDep());
    }

    @objid ("669584a9-2443-4bc2-bcc2-7ec1fb119a20")
    @Override
    public <T extends BpmnMessageFlow> List<T> getOutgoingFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessageFlow element : getOutgoingFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7f41c329-686f-4445-b28d-dd7c7657f3d0")
    @Override
    public EList<BpmnLane> getPartitionedLaneRefs() {
        return new SmList<>(this, ((BpmnBaseElementSmClass)getClassOf()).getPartitionedLaneRefsDep());
    }

    @objid ("739624ff-e945-4841-9801-b6b2d814296e")
    @Override
    public <T extends BpmnLane> List<T> getPartitionedLaneRefs(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnLane element : getPartitionedLaneRefs()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("75a4137d-033b-4ce1-b9a8-a658dd3548e3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("b7833187-9066-48cd-8a9f-ea63a2bdfadd")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("75911978-84b2-478e-bb50-082b582a1b96")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IModelVisitor)
          return accept((IModelVisitor)v);
        else
          return super.accept(v);
    }

    @objid ("bd0fbbbf-db5c-47dd-9cb1-c0cfc02b1ed6")
    public Object accept(IModelVisitor v) {
        return v.visitBpmnBaseElement(this);
    }

}
