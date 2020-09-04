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
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00072ee8-c4c0-1fd8-97fe-001ec947cd2a")
public abstract class BpmnItemAwareElementImpl extends BpmnFlowElementImpl implements BpmnItemAwareElement {
    @objid ("1bd7ef81-4ea9-485f-8623-da6d31c1fbe9")
    @Override
    public EList<BpmnDataAssociation> getTargetOfDataAssociation() {
        return new SmList<>(this, ((BpmnItemAwareElementSmClass)getClassOf()).getTargetOfDataAssociationDep());
    }

    @objid ("b6f48dc6-bfee-4d14-af24-b7fcfd3e85a2")
    @Override
    public <T extends BpmnDataAssociation> List<T> getTargetOfDataAssociation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataAssociation element : getTargetOfDataAssociation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8b1513aa-2e85-4814-a0f5-8da3cdf7b153")
    @Override
    public BpmnItemDefinition getItemSubjectRef() {
        Object obj = getDepVal(((BpmnItemAwareElementSmClass)getClassOf()).getItemSubjectRefDep());
        return (obj instanceof BpmnItemDefinition)? (BpmnItemDefinition)obj : null;
    }

    @objid ("6ae7ec39-f59c-4464-8ec1-513367a2d23b")
    @Override
    public void setItemSubjectRef(BpmnItemDefinition value) {
        appendDepVal(((BpmnItemAwareElementSmClass)getClassOf()).getItemSubjectRefDep(), (SmObjectImpl)value);
    }

    @objid ("fb9bab10-474f-47d4-ad86-bb9a34f02875")
    @Override
    public BpmnDataState getDataState() {
        Object obj = getDepVal(((BpmnItemAwareElementSmClass)getClassOf()).getDataStateDep());
        return (obj instanceof BpmnDataState)? (BpmnDataState)obj : null;
    }

    @objid ("d44c8e3f-0a53-4784-b84b-5de4f93bd3e5")
    @Override
    public void setDataState(BpmnDataState value) {
        appendDepVal(((BpmnItemAwareElementSmClass)getClassOf()).getDataStateDep(), (SmObjectImpl)value);
    }

    @objid ("0017395d-f031-4644-98a4-44a99ebbe6ad")
    @Override
    public EList<BpmnDataAssociation> getSourceOfDataAssociation() {
        return new SmList<>(this, ((BpmnItemAwareElementSmClass)getClassOf()).getSourceOfDataAssociationDep());
    }

    @objid ("22fb9d1e-9183-4d78-a4ed-475b061fc8f1")
    @Override
    public <T extends BpmnDataAssociation> List<T> getSourceOfDataAssociation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataAssociation element : getSourceOfDataAssociation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6c48a3b9-a72f-4291-aef3-37e07d39d64b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("f078dde3-f201-4088-b890-7311d6a10f06")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("d4b0a3a3-2f6a-4390-9755-211b35920dd9")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnItemAwareElement(this);
    }

}
