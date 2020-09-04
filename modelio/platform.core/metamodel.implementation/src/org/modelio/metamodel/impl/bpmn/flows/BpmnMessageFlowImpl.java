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
package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007c895e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMessageFlowImpl extends BpmnBaseElementImpl implements BpmnMessageFlow {
    @objid ("abc5221f-9dc4-4043-a2ff-acb429767c0e")
    @Override
    public BpmnMessage getMessageRef() {
        Object obj = getDepVal(((BpmnMessageFlowSmClass)getClassOf()).getMessageRefDep());
        return (obj instanceof BpmnMessage)? (BpmnMessage)obj : null;
    }

    @objid ("65ba7bf5-53a4-4c19-9a81-724e045bc3be")
    @Override
    public void setMessageRef(BpmnMessage value) {
        appendDepVal(((BpmnMessageFlowSmClass)getClassOf()).getMessageRefDep(), (SmObjectImpl)value);
    }

    @objid ("0e9a8785-bf22-414d-bd20-c2b462cdb06a")
    @Override
    public BpmnBaseElement getSourceRef() {
        Object obj = getDepVal(((BpmnMessageFlowSmClass)getClassOf()).getSourceRefDep());
        return (obj instanceof BpmnBaseElement)? (BpmnBaseElement)obj : null;
    }

    @objid ("42e94c33-ebec-445e-adda-0eee12e8eb2d")
    @Override
    public void setSourceRef(BpmnBaseElement value) {
        appendDepVal(((BpmnMessageFlowSmClass)getClassOf()).getSourceRefDep(), (SmObjectImpl)value);
    }

    @objid ("e8aa0c65-0d08-4736-9cdc-a11866758276")
    @Override
    public BpmnBaseElement getTargetRef() {
        Object obj = getDepVal(((BpmnMessageFlowSmClass)getClassOf()).getTargetRefDep());
        return (obj instanceof BpmnBaseElement)? (BpmnBaseElement)obj : null;
    }

    @objid ("c6586728-6b13-4ef3-aae6-90dbbc7cc30f")
    @Override
    public void setTargetRef(BpmnBaseElement value) {
        appendDepVal(((BpmnMessageFlowSmClass)getClassOf()).getTargetRefDep(), (SmObjectImpl)value);
    }

    @objid ("f2952cdd-125a-4ad8-82e0-488c71043c23")
    @Override
    public BpmnCollaboration getCollaboration() {
        Object obj = getDepVal(((BpmnMessageFlowSmClass)getClassOf()).getCollaborationDep());
        return (obj instanceof BpmnCollaboration)? (BpmnCollaboration)obj : null;
    }

    @objid ("65284592-0e5e-4b88-a741-29e14d22a20b")
    @Override
    public void setCollaboration(BpmnCollaboration value) {
        appendDepVal(((BpmnMessageFlowSmClass)getClassOf()).getCollaborationDep(), (SmObjectImpl)value);
    }

    @objid ("bfcbd12a-56af-4711-92d7-c7e2a2438548")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Collaboration
        obj = (SmObjectImpl)this.getDepVal(((BpmnMessageFlowSmClass)getClassOf()).getCollaborationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("dfec7550-f0c8-42da-b91a-df3ca2b9ca33")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Collaboration
        dep = ((BpmnMessageFlowSmClass)getClassOf()).getCollaborationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("d70766f0-1a01-4b25-aebc-2b64fe6fdab0")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnMessageFlow(this);
    }

}
