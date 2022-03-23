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

package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008790d8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnBoundaryEventImpl extends BpmnCatchEventImpl implements BpmnBoundaryEvent {
    @objid ("0018e718-5bc0-43f7-a994-58b75c92e651")
    @Override
    public boolean isCancelActivity() {
        return (Boolean) getAttVal(((BpmnBoundaryEventSmClass)getClassOf()).getCancelActivityAtt());
    }

    @objid ("6f6d221b-5032-4e77-ab78-c5507372efc9")
    @Override
    public void setCancelActivity(boolean value) {
        setAttVal(((BpmnBoundaryEventSmClass)getClassOf()).getCancelActivityAtt(), value);
    }

    @objid ("928eafd2-9d50-4e8d-a3d7-eccd6ea72809")
    @Override
    public BpmnActivity getAttachedToRef() {
        Object obj = getDepVal(((BpmnBoundaryEventSmClass)getClassOf()).getAttachedToRefDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("204e0b0e-faf2-4cf2-95e2-19a78fd635ae")
    @Override
    public void setAttachedToRef(BpmnActivity value) {
        appendDepVal(((BpmnBoundaryEventSmClass)getClassOf()).getAttachedToRefDep(), (SmObjectImpl)value);
    }

    @objid ("83b97acf-b55c-4692-b5b7-86a5880638ef")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3817f6a8-15ce-499e-90eb-890021c815ff")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("33bb6e3a-b932-4146-9494-641ffdaf4053")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnBoundaryEvent(this);
    }

}
