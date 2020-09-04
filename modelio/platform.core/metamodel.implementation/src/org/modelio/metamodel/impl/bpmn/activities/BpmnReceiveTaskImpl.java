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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.impl.bpmn.activities.BpmnReceiveTaskData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0081a92a-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnReceiveTaskImpl extends BpmnTaskImpl implements BpmnReceiveTask {
    @objid ("e344fca1-6949-4bab-9a12-5937116ec7e2")
    @Override
    public String getImplementation() {
        return (String) getAttVal(((BpmnReceiveTaskSmClass)getClassOf()).getImplementationAtt());
    }

    @objid ("71c683ec-7f67-4d25-86fb-34f925c0ec6a")
    @Override
    public void setImplementation(String value) {
        setAttVal(((BpmnReceiveTaskSmClass)getClassOf()).getImplementationAtt(), value);
    }

    @objid ("6a741c25-f396-4c8f-9b35-52fd96296a16")
    @Override
    public boolean isInstanciate() {
        return (Boolean) getAttVal(((BpmnReceiveTaskSmClass)getClassOf()).getInstanciateAtt());
    }

    @objid ("7662b0a6-b706-4966-80b7-6fe30573c3bb")
    @Override
    public void setInstanciate(boolean value) {
        setAttVal(((BpmnReceiveTaskSmClass)getClassOf()).getInstanciateAtt(), value);
    }

    @objid ("28c4ed83-39a9-4e3d-a959-f8e0a1921feb")
    @Override
    public BpmnMessage getMessageRef() {
        Object obj = getDepVal(((BpmnReceiveTaskSmClass)getClassOf()).getMessageRefDep());
        return (obj instanceof BpmnMessage)? (BpmnMessage)obj : null;
    }

    @objid ("899fbabe-c912-4457-85d9-b7e84780f190")
    @Override
    public void setMessageRef(BpmnMessage value) {
        appendDepVal(((BpmnReceiveTaskSmClass)getClassOf()).getMessageRefDep(), (SmObjectImpl)value);
    }

    @objid ("355454c5-23d2-4676-be72-1651b0924728")
    @Override
    public BpmnOperation getOperationRef() {
        Object obj = getDepVal(((BpmnReceiveTaskSmClass)getClassOf()).getOperationRefDep());
        return (obj instanceof BpmnOperation)? (BpmnOperation)obj : null;
    }

    @objid ("9266333a-61d5-4296-a09d-98920f25c389")
    @Override
    public void setOperationRef(BpmnOperation value) {
        appendDepVal(((BpmnReceiveTaskSmClass)getClassOf()).getOperationRefDep(), (SmObjectImpl)value);
    }

    @objid ("5118871f-c299-438e-8e76-6cd73e4cb41b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0a04a0c7-9231-4930-9bb3-eaf4b8381629")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("dfdde394-2dcc-4787-83f3-cb94f1cc8ac6")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnReceiveTask(this);
    }

}
