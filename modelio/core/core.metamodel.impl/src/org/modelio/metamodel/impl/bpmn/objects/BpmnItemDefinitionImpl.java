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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnItemKind;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemDefinitionData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0007d5be-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnItemDefinitionImpl extends BpmnSharedElementImpl implements BpmnItemDefinition {
    @objid ("ba61a7d3-603e-43e4-9959-be684779c6f5")
    @Override
    public BpmnItemKind getItemKind() {
        return (BpmnItemKind) getAttVal(((BpmnItemDefinitionSmClass)getClassOf()).getItemKindAtt());
    }

    @objid ("4aac8c79-21a7-42db-b98b-588a31959ffb")
    @Override
    public void setItemKind(BpmnItemKind value) {
        setAttVal(((BpmnItemDefinitionSmClass)getClassOf()).getItemKindAtt(), value);
    }

    @objid ("d8e9297b-97be-48fd-a659-ba302943401b")
    @Override
    public boolean isIsCollection() {
        return (Boolean) getAttVal(((BpmnItemDefinitionSmClass)getClassOf()).getIsCollectionAtt());
    }

    @objid ("db587b76-854d-45aa-9a80-c8ff7fd0fd58")
    @Override
    public void setIsCollection(boolean value) {
        setAttVal(((BpmnItemDefinitionSmClass)getClassOf()).getIsCollectionAtt(), value);
    }

    @objid ("f9fc3f44-45d2-41f0-ad64-03461f509239")
    @Override
    public EList<BpmnMessage> getTypedMessage() {
        return new SmList<>(this, ((BpmnItemDefinitionSmClass)getClassOf()).getTypedMessageDep());
    }

    @objid ("5e58edc9-0b93-4737-b59e-1859f924c950")
    @Override
    public <T extends BpmnMessage> List<T> getTypedMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnMessage element : getTypedMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0eb12a21-777a-478c-9077-7df6a41eee1f")
    @Override
    public EList<BpmnItemAwareElement> getTypedItem() {
        return new SmList<>(this, ((BpmnItemDefinitionSmClass)getClassOf()).getTypedItemDep());
    }

    @objid ("3cd157b3-7ed8-4ed8-b1bd-8558a900599e")
    @Override
    public <T extends BpmnItemAwareElement> List<T> getTypedItem(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnItemAwareElement element : getTypedItem()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("09288759-e3c3-4ab3-91d9-8e5a469c3f5e")
    @Override
    public EList<BpmnResourceParameter> getTypedResourceParameter() {
        return new SmList<>(this, ((BpmnItemDefinitionSmClass)getClassOf()).getTypedResourceParameterDep());
    }

    @objid ("6bdcdb4b-cba6-4b1f-a463-f772a315cc74")
    @Override
    public <T extends BpmnResourceParameter> List<T> getTypedResourceParameter(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnResourceParameter element : getTypedResourceParameter()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("11a1b092-56bc-40b3-9956-b4adbadae203")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3f96a79e-7d37-4ec2-a5b5-89c94e4eea8b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("73beaf34-12fc-4d30-a5f2-f729270ee894")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnItemDefinition(this);
    }

}
