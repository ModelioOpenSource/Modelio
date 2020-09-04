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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0088d56a-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnCatchEventImpl extends BpmnEventImpl implements BpmnCatchEvent {
    @objid ("bd1ed38f-0a22-40c9-bf35-4ec471d57194")
    @Override
    public boolean isParallelMultiple() {
        return (Boolean) getAttVal(((BpmnCatchEventSmClass)getClassOf()).getParallelMultipleAtt());
    }

    @objid ("f71ae32d-4776-46fb-b6c1-b9929b7dc0ae")
    @Override
    public void setParallelMultiple(boolean value) {
        setAttVal(((BpmnCatchEventSmClass)getClassOf()).getParallelMultipleAtt(), value);
    }

    @objid ("031ae53d-ae0f-44d8-86c3-7f443569fc46")
    @Override
    public EList<BpmnDataAssociation> getDataOutputAssociation() {
        return new SmList<>(this, ((BpmnCatchEventSmClass)getClassOf()).getDataOutputAssociationDep());
    }

    @objid ("65df6bce-ad7d-4357-b4bf-a2738ccf2d58")
    @Override
    public <T extends BpmnDataAssociation> List<T> getDataOutputAssociation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataAssociation element : getDataOutputAssociation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8a2dbfd6-59b7-497c-b6c8-83a2500e327a")
    @Override
    public BpmnDataOutput getDataOutput() {
        Object obj = getDepVal(((BpmnCatchEventSmClass)getClassOf()).getDataOutputDep());
        return (obj instanceof BpmnDataOutput)? (BpmnDataOutput)obj : null;
    }

    @objid ("8667bcf5-fac0-417d-8f88-8db324e50507")
    @Override
    public void setDataOutput(BpmnDataOutput value) {
        appendDepVal(((BpmnCatchEventSmClass)getClassOf()).getDataOutputDep(), (SmObjectImpl)value);
    }

    @objid ("7c001ca0-813e-4c82-8304-015005e31fdb")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c7772cb3-e951-47c4-a796-77c9423922b7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("524c36fa-be81-45eb-998e-a34517ccde0e")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnCatchEvent(this);
    }

}
