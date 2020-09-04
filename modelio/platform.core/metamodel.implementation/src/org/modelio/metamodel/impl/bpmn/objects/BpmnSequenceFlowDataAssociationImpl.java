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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.impl.bpmn.objects.BpmnSequenceFlowDataAssociationData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00054970-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnSequenceFlowDataAssociationImpl extends BpmnBaseElementImpl implements BpmnSequenceFlowDataAssociation {
    @objid ("f7f3a126-6774-415f-9a51-9b237a3cb859")
    @Override
    public BpmnSequenceFlow getConnected() {
        Object obj = getDepVal(((BpmnSequenceFlowDataAssociationSmClass)getClassOf()).getConnectedDep());
        return (obj instanceof BpmnSequenceFlow)? (BpmnSequenceFlow)obj : null;
    }

    @objid ("7d52b4e5-5f02-4b65-9330-0c89f9ea9b68")
    @Override
    public void setConnected(BpmnSequenceFlow value) {
        appendDepVal(((BpmnSequenceFlowDataAssociationSmClass)getClassOf()).getConnectedDep(), (SmObjectImpl)value);
    }

    @objid ("73663af3-7f0c-46e2-8066-9f9228fcc55f")
    @Override
    public EList<BpmnDataAssociation> getDataAssociation() {
        return new SmList<>(this, ((BpmnSequenceFlowDataAssociationSmClass)getClassOf()).getDataAssociationDep());
    }

    @objid ("0975a16a-4b64-42d9-ba1a-95a4bd88d193")
    @Override
    public <T extends BpmnDataAssociation> List<T> getDataAssociation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataAssociation element : getDataAssociation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("01d2930d-8274-42e5-bc7a-451521d17c1b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Connected
        obj = (SmObjectImpl)this.getDepVal(((BpmnSequenceFlowDataAssociationSmClass)getClassOf()).getConnectedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a1575431-10ee-4b44-87ad-76bcb044b4fa")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Connected
        dep = ((BpmnSequenceFlowDataAssociationSmClass)getClassOf()).getConnectedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("02dcd4fd-8de8-4a10-a3c9-43f0f7371dfd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnSequenceFlowDataAssociation(this);
    }

}
