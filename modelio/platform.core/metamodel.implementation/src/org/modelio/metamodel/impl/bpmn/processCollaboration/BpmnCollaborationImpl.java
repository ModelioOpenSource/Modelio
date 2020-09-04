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
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationData;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0073fd98-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnCollaborationImpl extends BehaviorImpl implements BpmnCollaboration {
    @objid ("26a6a9fe-be1a-4d30-8477-83d1de41c49c")
    @Override
    public boolean isIsClosed() {
        return (Boolean) getAttVal(((BpmnCollaborationSmClass)getClassOf()).getIsClosedAtt());
    }

    @objid ("d7e5f25b-8542-4137-a26c-489dbfbe50e2")
    @Override
    public void setIsClosed(boolean value) {
        setAttVal(((BpmnCollaborationSmClass)getClassOf()).getIsClosedAtt(), value);
    }

    @objid ("ceec8acf-79ae-4436-a1ff-f3acd3862120")
    @Override
    public EList<BpmnArtifact> getArtifact() {
        return new SmList<>(this, ((BpmnCollaborationSmClass)getClassOf()).getArtifactDep());
    }

    @objid ("489f7644-8d88-4c22-97ea-54a8e1fd32e3")
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

    @objid ("7f950d96-8249-41cd-99c2-b4701be36d5d")
    @Override
    public EList<BpmnMessageFlow> getMessageFlow() {
        return new SmList<>(this, ((BpmnCollaborationSmClass)getClassOf()).getMessageFlowDep());
    }

    @objid ("4e94484b-b60c-4fd3-9f60-096243018ec7")
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

    @objid ("d4cab63b-053a-4959-b3ee-c0dcaefe5cba")
    @Override
    public EList<BpmnParticipant> getParticipants() {
        return new SmList<>(this, ((BpmnCollaborationSmClass)getClassOf()).getParticipantsDep());
    }

    @objid ("ba1830c7-3e33-439c-a69f-68b14e95dbd3")
    @Override
    public <T extends BpmnParticipant> List<T> getParticipants(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnParticipant element : getParticipants()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0cf0b556-92ca-4051-8981-a9b64e72911a")
    @Override
    public EList<BpmnMessage> getMessages() {
        return new SmList<>(this, ((BpmnCollaborationSmClass)getClassOf()).getMessagesDep());
    }

    @objid ("0e750677-2734-468b-9e4d-d9574bdba1db")
    @Override
    public <T extends BpmnMessage> List<T> getMessages(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessage element : getMessages()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ff7ced11-607d-4ecf-b901-bb1b77850f10")
    @Override
    public BpmnProcess getDefinedProcess() {
        Object obj = getDepVal(((BpmnCollaborationSmClass)getClassOf()).getDefinedProcessDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("a35f5d05-c609-49ff-bb12-c862110c757d")
    @Override
    public void setDefinedProcess(BpmnProcess value) {
        appendDepVal(((BpmnCollaborationSmClass)getClassOf()).getDefinedProcessDep(), (SmObjectImpl)value);
    }

    @objid ("adfa2462-30ee-4a2f-ba97-b9e9bae1d7f2")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // DefinedProcess
        obj = (SmObjectImpl)this.getDepVal(((BpmnCollaborationSmClass)getClassOf()).getDefinedProcessDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("6aca0158-a168-40ef-b4b0-dfa2b75cc9d6")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // DefinedProcess
        dep = ((BpmnCollaborationSmClass)getClassOf()).getDefinedProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("4429d7aa-a7fb-44ac-8fd4-27bd68e1f9e9")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnCollaboration(this);
    }

}
