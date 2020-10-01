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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0078aed8-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnArtifactImpl extends BpmnBaseElementImpl implements BpmnArtifact {
    @objid ("bd9bce8a-8f74-477a-875e-676661ef2dfe")
    @Override
    public BpmnSubProcess getSubProcess() {
        Object obj = getDepVal(((BpmnArtifactSmClass)getClassOf()).getSubProcessDep());
        return (obj instanceof BpmnSubProcess)? (BpmnSubProcess)obj : null;
    }

    @objid ("6f8f0e5c-ce43-4c3c-91ac-4b2d05f80a4d")
    @Override
    public void setSubProcess(BpmnSubProcess value) {
        appendDepVal(((BpmnArtifactSmClass)getClassOf()).getSubProcessDep(), (SmObjectImpl)value);
    }

    @objid ("6b17d944-7385-475b-9087-5625e54e355e")
    @Override
    public BpmnCollaboration getCollaboration() {
        Object obj = getDepVal(((BpmnArtifactSmClass)getClassOf()).getCollaborationDep());
        return (obj instanceof BpmnCollaboration)? (BpmnCollaboration)obj : null;
    }

    @objid ("b28cb91a-9e32-4ba0-895e-f173077edbe0")
    @Override
    public void setCollaboration(BpmnCollaboration value) {
        appendDepVal(((BpmnArtifactSmClass)getClassOf()).getCollaborationDep(), (SmObjectImpl)value);
    }

    @objid ("a1860d17-4ad6-4ccc-8374-b4ab6c8c3cf9")
    @Override
    public BpmnProcess getProcess() {
        Object obj = getDepVal(((BpmnArtifactSmClass)getClassOf()).getProcessDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("2fc8652a-0fdb-422a-bd91-48fae1b48e4f")
    @Override
    public void setProcess(BpmnProcess value) {
        appendDepVal(((BpmnArtifactSmClass)getClassOf()).getProcessDep(), (SmObjectImpl)value);
    }

    @objid ("f8e2e84c-aa98-446a-986b-f1aeb93956f4")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SubProcess
        obj = (SmObjectImpl)this.getDepVal(((BpmnArtifactSmClass)getClassOf()).getSubProcessDep());
        if (obj != null)
          return obj;
        // Collaboration
        obj = (SmObjectImpl)this.getDepVal(((BpmnArtifactSmClass)getClassOf()).getCollaborationDep());
        if (obj != null)
          return obj;
        // Process
        obj = (SmObjectImpl)this.getDepVal(((BpmnArtifactSmClass)getClassOf()).getProcessDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("0716fb7d-de95-413f-9dc8-15c9ce8722ce")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SubProcess
        dep = ((BpmnArtifactSmClass)getClassOf()).getSubProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Collaboration
        dep = ((BpmnArtifactSmClass)getClassOf()).getCollaborationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Process
        dep = ((BpmnArtifactSmClass)getClassOf()).getProcessDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a43a026a-d1b5-4f01-b7b5-1fe2c855b2a4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnArtifact(this);
    }

}
