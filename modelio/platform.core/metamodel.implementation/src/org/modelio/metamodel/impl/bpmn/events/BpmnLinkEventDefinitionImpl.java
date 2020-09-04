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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnLinkEventDefinitionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00904a52-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLinkEventDefinitionImpl extends BpmnEventDefinitionImpl implements BpmnLinkEventDefinition {
    @objid ("d7a6d50b-4a45-48d0-9a40-1674569f4ba8")
    @Override
    public EList<BpmnLinkEventDefinition> getSource() {
        return new SmList<>(this, ((BpmnLinkEventDefinitionSmClass)getClassOf()).getSourceDep());
    }

    @objid ("4b0d5f8c-db27-43ed-9368-8ea51f87bc0b")
    @Override
    public <T extends BpmnLinkEventDefinition> List<T> getSource(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnLinkEventDefinition element : getSource()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("a2aa4e3c-81d2-4df8-9ebe-9f4ca787d567")
    @Override
    public BpmnLinkEventDefinition getTarget() {
        Object obj = getDepVal(((BpmnLinkEventDefinitionSmClass)getClassOf()).getTargetDep());
        return (obj instanceof BpmnLinkEventDefinition)? (BpmnLinkEventDefinition)obj : null;
    }

    @objid ("39b08943-d0d9-45dc-8b27-385f526db48e")
    @Override
    public void setTarget(BpmnLinkEventDefinition value) {
        appendDepVal(((BpmnLinkEventDefinitionSmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("13f177fd-76ba-455c-98ec-d62344e222a8")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("94c6efe3-7e19-46a2-bc7e-4ba50814e4e2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("bd9b42e6-1f32-4ddf-b737-0e8f3dcd915f")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnLinkEventDefinition(this);
    }

}
