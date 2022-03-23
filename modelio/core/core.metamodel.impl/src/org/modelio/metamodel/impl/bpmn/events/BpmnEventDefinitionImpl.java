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
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008d76e2-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnEventDefinitionImpl extends BpmnBaseElementImpl implements BpmnEventDefinition {
    @objid ("479c66af-454c-4645-a763-7ac9fed5f612")
    @Override
    public BpmnEvent getDefined() {
        Object obj = getDepVal(((BpmnEventDefinitionSmClass)getClassOf()).getDefinedDep());
        return (obj instanceof BpmnEvent)? (BpmnEvent)obj : null;
    }

    @objid ("bb96d196-e7f1-4b6f-b199-4faa7b23d3e8")
    @Override
    public void setDefined(BpmnEvent value) {
        appendDepVal(((BpmnEventDefinitionSmClass)getClassOf()).getDefinedDep(), (SmObjectImpl)value);
    }

    @objid ("90373e51-59c2-4e95-97cc-6e6a3508fbbe")
    @Override
    public EList<BpmnMultiInstanceLoopCharacteristics> getLoopRef() {
        return new SmList<>(this, ((BpmnEventDefinitionSmClass)getClassOf()).getLoopRefDep());
    }

    @objid ("e923b89c-2743-4d24-b9b0-438581ba91f7")
    @Override
    public <T extends BpmnMultiInstanceLoopCharacteristics> List<T> getLoopRef(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMultiInstanceLoopCharacteristics element : getLoopRef()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("2b79544c-6cef-4a0e-b6f1-ea76574d7923")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Defined
        obj = (SmObjectImpl)this.getDepVal(((BpmnEventDefinitionSmClass)getClassOf()).getDefinedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("57a78df0-c1ed-43f2-9080-d4baca2e41d2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Defined
        dep = ((BpmnEventDefinitionSmClass)getClassOf()).getDefinedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("2b2c289a-fd07-48ff-9ed1-0827c810b1ca")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnEventDefinition(this);
    }

}
