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
package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnInterfaceData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000e1c26-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnInterfaceImpl extends BpmnSharedElementImpl implements BpmnInterface {
    @objid ("cec07e20-6b74-4257-a88c-bf67db7ef07b")
    @Override
    public EList<BpmnOperation> getOperation() {
        return new SmList<>(this, ((BpmnInterfaceSmClass)getClassOf()).getOperationDep());
    }

    @objid ("30c964e7-b6b9-4fc9-9a71-7427e5680e10")
    @Override
    public <T extends BpmnOperation> List<T> getOperation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnOperation element : getOperation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e5cd875e-509d-4dbb-aecc-14bba831e187")
    @Override
    public EList<BpmnParticipant> getParticipantRef() {
        return new SmList<>(this, ((BpmnInterfaceSmClass)getClassOf()).getParticipantRefDep());
    }

    @objid ("6cc821e9-e526-45ca-b13e-8e03c05d7702")
    @Override
    public <T extends BpmnParticipant> List<T> getParticipantRef(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnParticipant element : getParticipantRef()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("04330af4-9d35-4b71-b635-5ef7cded2b82")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("49d23dc1-45d6-4baa-8329-11117a5f99ba")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("cb84868b-a2c0-41af-a3e7-2511b39d694c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnInterface(this);
    }

}
