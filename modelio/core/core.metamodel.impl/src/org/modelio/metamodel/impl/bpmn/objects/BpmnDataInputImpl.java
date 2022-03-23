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

package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00068a7e-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataInputImpl extends BpmnItemAwareElementImpl implements BpmnDataInput {
    @objid ("549d06c5-8d46-4514-994a-5ce6bed98aa4")
    @Override
    public boolean isIsCollection() {
        return (Boolean) getAttVal(((BpmnDataInputSmClass)getClassOf()).getIsCollectionAtt());
    }

    @objid ("37c1cd19-328d-4de5-94e8-88c870275c64")
    @Override
    public void setIsCollection(boolean value) {
        setAttVal(((BpmnDataInputSmClass)getClassOf()).getIsCollectionAtt(), value);
    }

    @objid ("86f58473-03db-42f9-95bd-9ddfd26f9977")
    @Override
    public BpmnMultiInstanceLoopCharacteristics getOwnerLoopCharacteristics() {
        Object obj = getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep());
        return (obj instanceof BpmnMultiInstanceLoopCharacteristics)? (BpmnMultiInstanceLoopCharacteristics)obj : null;
    }

    @objid ("59fd871e-4b29-4a39-bb93-7e2729dc089f")
    @Override
    public void setOwnerLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics value) {
        appendDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep(), (SmObjectImpl)value);
    }

    @objid ("5ba37450-0163-4bb6-ac7f-e8fd9f4577cf")
    @Override
    public BpmnActivity getOwnerActivity() {
        Object obj = getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerActivityDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("55780157-0b8c-4e35-a9d5-fc879df63f08")
    @Override
    public void setOwnerActivity(BpmnActivity value) {
        appendDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerActivityDep(), (SmObjectImpl)value);
    }

    @objid ("5372fd5d-cd1b-48ca-925e-1d8f02aab1e1")
    @Override
    public BpmnThrowEvent getOwnerThrowEvent() {
        Object obj = getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerThrowEventDep());
        return (obj instanceof BpmnThrowEvent)? (BpmnThrowEvent)obj : null;
    }

    @objid ("32486de5-3e0d-43ca-8259-706d34ff8e1b")
    @Override
    public void setOwnerThrowEvent(BpmnThrowEvent value) {
        appendDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerThrowEventDep(), (SmObjectImpl)value);
    }

    @objid ("e5557029-bc90-4e40-87e0-f61a49b00c46")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerLoopCharacteristics
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep());
        if (obj != null)
          return obj;
        // OwnerActivity
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerActivityDep());
        if (obj != null)
          return obj;
        // OwnerThrowEvent
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataInputSmClass)getClassOf()).getOwnerThrowEventDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("cec356bf-c47a-4c56-84b5-e0465cf79852")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerLoopCharacteristics
        dep = ((BpmnDataInputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerActivity
        dep = ((BpmnDataInputSmClass)getClassOf()).getOwnerActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerThrowEvent
        dep = ((BpmnDataInputSmClass)getClassOf()).getOwnerThrowEventDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("8e032e7a-c34c-4a99-a440-2ce9f96d84dc")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataInput(this);
    }

}
