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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.impl.bpmn.events.BpmnImplicitThrowEventData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008e2808-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnImplicitThrowEventImpl extends BpmnThrowEventImpl implements BpmnImplicitThrowEvent {
    @objid ("31f75202-f07d-48a3-8d85-89dfe86a9d4c")
    @Override
    public BpmnComplexBehaviorDefinition getOwner() {
        Object obj = getDepVal(((BpmnImplicitThrowEventSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof BpmnComplexBehaviorDefinition)? (BpmnComplexBehaviorDefinition)obj : null;
    }

    @objid ("1665203e-f208-4a3b-84fc-4c992f7caffe")
    @Override
    public void setOwner(BpmnComplexBehaviorDefinition value) {
        appendDepVal(((BpmnImplicitThrowEventSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("21662c5f-bb7a-4a20-9d7c-598773927761")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BpmnImplicitThrowEventSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("5d99ee0f-7627-4e1e-9515-efdeaed98bb2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BpmnImplicitThrowEventSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("38ca963a-a0c8-4fcf-939f-93adb02d50fc")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnImplicitThrowEvent(this);
    }

}
