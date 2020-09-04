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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00811ea6-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMultiInstanceLoopCharacteristicsImpl extends BpmnLoopCharacteristicsImpl implements BpmnMultiInstanceLoopCharacteristics {
    @objid ("0b31938f-bd7e-4644-8957-3b34cd970e36")
    @Override
    public boolean isIsSequencial() {
        return (Boolean) getAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getIsSequencialAtt());
    }

    @objid ("d559e5a3-8d36-47ee-a998-0fb6022b1d01")
    @Override
    public void setIsSequencial(boolean value) {
        setAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getIsSequencialAtt(), value);
    }

    @objid ("2b0be33b-eaa0-4223-98bb-761c2d9b415d")
    @Override
    public MultiInstanceBehavior getBehavior() {
        return (MultiInstanceBehavior) getAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getBehaviorAtt());
    }

    @objid ("5fff3620-727d-4f86-9e0b-0516370fcb76")
    @Override
    public void setBehavior(MultiInstanceBehavior value) {
        setAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getBehaviorAtt(), value);
    }

    @objid ("fbef7afb-68ec-4243-a7b9-449c87b07c7e")
    @Override
    public String getLoopCardinality() {
        return (String) getAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopCardinalityAtt());
    }

    @objid ("b3d85a2f-ee42-45a7-9eea-6025b564e8fc")
    @Override
    public void setLoopCardinality(String value) {
        setAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopCardinalityAtt(), value);
    }

    @objid ("f1e4d238-f258-43fb-83b0-a0bf2a2d1d00")
    @Override
    public String getCompletionCondition() {
        return (String) getAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getCompletionConditionAtt());
    }

    @objid ("283f4e7c-8b22-442a-88be-cd7802c02986")
    @Override
    public void setCompletionCondition(String value) {
        setAttVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getCompletionConditionAtt(), value);
    }

    @objid ("1b963072-f738-4359-837d-e62bf5066b77")
    @Override
    public BpmnDataInput getLoopDataInput() {
        Object obj = getDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopDataInputDep());
        return (obj instanceof BpmnDataInput)? (BpmnDataInput)obj : null;
    }

    @objid ("f6723945-7331-45b8-bc1f-1521adfcbab1")
    @Override
    public void setLoopDataInput(BpmnDataInput value) {
        appendDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopDataInputDep(), (SmObjectImpl)value);
    }

    @objid ("33a3cb5e-9bdd-45d6-ae98-ca2e36d0975b")
    @Override
    public BpmnDataOutput getLoopDataOutputRef() {
        Object obj = getDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopDataOutputRefDep());
        return (obj instanceof BpmnDataOutput)? (BpmnDataOutput)obj : null;
    }

    @objid ("e7108d36-1d6e-4133-891e-4a1edb0ebafc")
    @Override
    public void setLoopDataOutputRef(BpmnDataOutput value) {
        appendDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getLoopDataOutputRefDep(), (SmObjectImpl)value);
    }

    @objid ("8bb035aa-40b2-4b73-8ec8-daf2180736ac")
    @Override
    public BpmnEventDefinition getCompletionEventRef() {
        Object obj = getDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getCompletionEventRefDep());
        return (obj instanceof BpmnEventDefinition)? (BpmnEventDefinition)obj : null;
    }

    @objid ("cb247c5f-3102-4db1-8212-4a64c16b5443")
    @Override
    public void setCompletionEventRef(BpmnEventDefinition value) {
        appendDepVal(((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getCompletionEventRefDep(), (SmObjectImpl)value);
    }

    @objid ("67d38d0c-8edd-48d0-8620-d60548953b67")
    @Override
    public EList<BpmnComplexBehaviorDefinition> getComplexBehaviorDefinition() {
        return new SmList<>(this, ((BpmnMultiInstanceLoopCharacteristicsSmClass)getClassOf()).getComplexBehaviorDefinitionDep());
    }

    @objid ("62bc0f18-8bf0-4cb3-b393-26b4612dc4ca")
    @Override
    public <T extends BpmnComplexBehaviorDefinition> List<T> getComplexBehaviorDefinition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnComplexBehaviorDefinition element : getComplexBehaviorDefinition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4ec0873a-7579-4148-a56b-d8371feae25b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("a63af474-af36-41b5-a115-71d0a3ebbe15")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("134bab20-8f26-49c6-b6f2-c98c7f14e58c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnMultiInstanceLoopCharacteristics(this);
    }

}
