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
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcessType;
import org.modelio.metamodel.bpmn.processCollaboration.OptionalBoolean;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessData;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0076549e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnProcessImpl extends BehaviorImpl implements BpmnProcess {
    @objid ("fb8801ca-f630-4510-975d-3ba94b74a66c")
    @Override
    public BpmnProcessType getProcessType() {
        return (BpmnProcessType) getAttVal(((BpmnProcessSmClass)getClassOf()).getProcessTypeAtt());
    }

    @objid ("50bd2a18-83ad-4b72-a9e1-058de066a7b0")
    @Override
    public void setProcessType(BpmnProcessType value) {
        setAttVal(((BpmnProcessSmClass)getClassOf()).getProcessTypeAtt(), value);
    }

    @objid ("34c1069d-2285-462d-8786-f2d7c1d51d7c")
    @Override
    public boolean isIsClosed() {
        return (Boolean) getAttVal(((BpmnProcessSmClass)getClassOf()).getIsClosedAtt());
    }

    @objid ("5d5f57d8-b91e-4d10-88b7-42ede25d470a")
    @Override
    public void setIsClosed(boolean value) {
        setAttVal(((BpmnProcessSmClass)getClassOf()).getIsClosedAtt(), value);
    }

    @objid ("cc49dd7c-a51e-42c8-82e6-78d4377caada")
    @Override
    public OptionalBoolean getIsExecutable() {
        return (OptionalBoolean) getAttVal(((BpmnProcessSmClass)getClassOf()).getIsExecutableAtt());
    }

    @objid ("8c6e05ca-189e-486c-bdc6-c4453dca194d")
    @Override
    public void setIsExecutable(OptionalBoolean value) {
        setAttVal(((BpmnProcessSmClass)getClassOf()).getIsExecutableAtt(), value);
    }

    @objid ("8fe0a871-3bd9-4964-a4dc-a9a540fecbc0")
    @Override
    public EList<BpmnProcess> getSupports() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getSupportsDep());
    }

    @objid ("c431cfdc-88a0-4b87-bded-5f2f39dfa0d7")
    @Override
    public <T extends BpmnProcess> List<T> getSupports(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnProcess element : getSupports()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f330ea97-9ccd-4281-b2d1-8053833a1129")
    @Override
    public EList<BpmnArtifact> getArtifact() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getArtifactDep());
    }

    @objid ("9963377e-96e1-44c8-a6aa-995e9ebc405b")
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

    @objid ("cfddc3f8-476c-4dc1-86da-d9bce6aa4ba5")
    @Override
    public BpmnLaneSet getLaneSet() {
        Object obj = getDepVal(((BpmnProcessSmClass)getClassOf()).getLaneSetDep());
        return (obj instanceof BpmnLaneSet)? (BpmnLaneSet)obj : null;
    }

    @objid ("6d4d8cae-2362-41e8-8093-dbda99d0df7d")
    @Override
    public void setLaneSet(BpmnLaneSet value) {
        appendDepVal(((BpmnProcessSmClass)getClassOf()).getLaneSetDep(), (SmObjectImpl)value);
    }

    @objid ("5824cd54-759b-4eab-b7c7-662017d91646")
    @Override
    public EList<BpmnProcess> getSupported() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getSupportedDep());
    }

    @objid ("dd47e45d-f818-4d97-be48-0c4baecb04d9")
    @Override
    public <T extends BpmnProcess> List<T> getSupported(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnProcess element : getSupported()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5ad1b3e1-6197-443c-97ed-dd7efd961a45")
    @Override
    public EList<BpmnParticipant> getParticipant() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getParticipantDep());
    }

    @objid ("fbbcadbe-5873-44e6-bddd-51569d7fd6bc")
    @Override
    public <T extends BpmnParticipant> List<T> getParticipant(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnParticipant element : getParticipant()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("688b424b-6d7f-4c7a-8ea5-1d9542f9dc5f")
    @Override
    public EList<BpmnFlowElement> getFlowElement() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getFlowElementDep());
    }

    @objid ("e98ffaaa-b071-4895-9940-ccbc9eef3d73")
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

    @objid ("0b136821-e1e7-4417-8eb9-7a2d54f20a91")
    @Override
    public EList<BpmnResourceRole> getResource() {
        return new SmList<>(this, ((BpmnProcessSmClass)getClassOf()).getResourceDep());
    }

    @objid ("47621a56-970a-4125-ac88-75fb885d37fb")
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

    @objid ("a4b076a8-a164-48da-a72a-5ce7d06abefc")
    @Override
    public BpmnCollaboration getDefinitionalCollaboration() {
        Object obj = getDepVal(((BpmnProcessSmClass)getClassOf()).getDefinitionalCollaborationDep());
        return (obj instanceof BpmnCollaboration)? (BpmnCollaboration)obj : null;
    }

    @objid ("7202223f-a947-45a6-8104-8bc75af45937")
    @Override
    public void setDefinitionalCollaboration(BpmnCollaboration value) {
        appendDepVal(((BpmnProcessSmClass)getClassOf()).getDefinitionalCollaborationDep(), (SmObjectImpl)value);
    }

    @objid ("7a3290b8-20bd-4a06-8d5f-02ae3cd3af0c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("1fb66c53-14dc-4e78-8e07-d2c5b1d9f5ce")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("0f79fc57-1668-4741-b1a0-0b11ff353a51")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnProcess(this);
    }

}
