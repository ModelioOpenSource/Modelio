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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSendTaskData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0082b734-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSendTaskImpl extends BpmnTaskImpl implements BpmnSendTask {
    @objid ("b44cdf69-45ce-49e7-865e-def00af966e9")
    @Override
    public String getImplementation() {
        return (String) getAttVal(((BpmnSendTaskSmClass)getClassOf()).getImplementationAtt());
    }

    @objid ("ca56f169-919c-442d-8596-5bf9d8f66bc5")
    @Override
    public void setImplementation(String value) {
        setAttVal(((BpmnSendTaskSmClass)getClassOf()).getImplementationAtt(), value);
    }

    @objid ("ce364edb-5b64-4428-b695-0ce31c19fe9f")
    @Override
    public BpmnMessage getMessageRef() {
        Object obj = getDepVal(((BpmnSendTaskSmClass)getClassOf()).getMessageRefDep());
        return (obj instanceof BpmnMessage)? (BpmnMessage)obj : null;
    }

    @objid ("3d053230-9d24-4f1d-b3a5-f7a18cafa2cb")
    @Override
    public void setMessageRef(BpmnMessage value) {
        appendDepVal(((BpmnSendTaskSmClass)getClassOf()).getMessageRefDep(), (SmObjectImpl)value);
    }

    @objid ("97fb6889-9d55-4cf6-9d19-5802c2e3337e")
    @Override
    public BpmnOperation getOperationRef() {
        Object obj = getDepVal(((BpmnSendTaskSmClass)getClassOf()).getOperationRefDep());
        return (obj instanceof BpmnOperation)? (BpmnOperation)obj : null;
    }

    @objid ("919e8433-aeea-46f0-9280-ae4b3a257e7c")
    @Override
    public void setOperationRef(BpmnOperation value) {
        appendDepVal(((BpmnSendTaskSmClass)getClassOf()).getOperationRefDep(), (SmObjectImpl)value);
    }

    @objid ("51ff1d08-11c9-46ea-bb07-9e90478a4622")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("a472f381-b3c0-4f6d-aea8-96afae457d9f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2925f475-4474-436a-baf3-2b8f80c542fd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnSendTask(this);
    }

}
