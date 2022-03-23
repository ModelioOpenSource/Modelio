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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0095d2e2-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnThrowEventImpl extends BpmnEventImpl implements BpmnThrowEvent {
    @objid ("e23f16e8-9c8b-4d58-b10d-2066f2833a06")
    @Override
    public EList<BpmnDataAssociation> getDataInputAssociation() {
        return new SmList<>(this, ((BpmnThrowEventSmClass)getClassOf()).getDataInputAssociationDep());
    }

    @objid ("ea1641c6-66bb-4892-9616-d4e18dee3588")
    @Override
    public <T extends BpmnDataAssociation> List<T> getDataInputAssociation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataAssociation element : getDataInputAssociation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("42ff707a-691e-47de-98cd-9d01bb8c01de")
    @Override
    public BpmnDataInput getDataInput() {
        Object obj = getDepVal(((BpmnThrowEventSmClass)getClassOf()).getDataInputDep());
        return (obj instanceof BpmnDataInput)? (BpmnDataInput)obj : null;
    }

    @objid ("354886d1-7337-4b81-abe9-eeecf8d5381d")
    @Override
    public void setDataInput(BpmnDataInput value) {
        appendDepVal(((BpmnThrowEventSmClass)getClassOf()).getDataInputDep(), (SmObjectImpl)value);
    }

    @objid ("57b6b8e4-2a22-4bec-a4ad-208e93ad3cdc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("2bf39b4a-9d70-496f-adf4-9143bc7b372c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("f4c6497b-e488-4ede-8919-01e7f1218692")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnThrowEvent(this);
    }

}
