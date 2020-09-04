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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivityData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007d9466-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnActivityImpl extends BpmnFlowNodeImpl implements BpmnActivity {
    @objid ("b3969967-6c1a-438d-ac83-2ef0e5498ce2")
    @Override
    public boolean isIsForCompensation() {
        return (Boolean) getAttVal(((BpmnActivitySmClass)getClassOf()).getIsForCompensationAtt());
    }

    @objid ("f465f700-85b6-4a3e-9a90-9fc077bd0236")
    @Override
    public void setIsForCompensation(boolean value) {
        setAttVal(((BpmnActivitySmClass)getClassOf()).getIsForCompensationAtt(), value);
    }

    @objid ("a6d9bc68-b1b1-4e8d-b35b-2566c07a5f70")
    @Override
    public int getStartQuantity() {
        return (Integer) getAttVal(((BpmnActivitySmClass)getClassOf()).getStartQuantityAtt());
    }

    @objid ("7a2d3bcc-c1cd-49e2-8486-43347fb97ecf")
    @Override
    public void setStartQuantity(int value) {
        setAttVal(((BpmnActivitySmClass)getClassOf()).getStartQuantityAtt(), value);
    }

    @objid ("82244324-da66-4a80-bed8-f000767bc7a6")
    @Override
    public int getCompletionQuantity() {
        return (Integer) getAttVal(((BpmnActivitySmClass)getClassOf()).getCompletionQuantityAtt());
    }

    @objid ("0a99fab4-9efa-42e5-b93a-c54ad2cd14c7")
    @Override
    public void setCompletionQuantity(int value) {
        setAttVal(((BpmnActivitySmClass)getClassOf()).getCompletionQuantityAtt(), value);
    }

    @objid ("cf706a36-31a3-4ea6-91cd-31a10089e4a8")
    @Override
    public EList<BpmnCompensateEventDefinition> getCompensateEventDefinitions() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getCompensateEventDefinitionsDep());
    }

    @objid ("b3249189-c8b1-4542-b4a4-6ca3bea364f4")
    @Override
    public <T extends BpmnCompensateEventDefinition> List<T> getCompensateEventDefinitions(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnCompensateEventDefinition element : getCompensateEventDefinitions()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7086fc90-f983-4e4b-9116-c55ae59c00fd")
    @Override
    public EList<BpmnDataInput> getInputSpecification() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getInputSpecificationDep());
    }

    @objid ("b3fbdb4a-5ef3-4286-9cc0-4ef4aa9291eb")
    @Override
    public <T extends BpmnDataInput> List<T> getInputSpecification(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataInput element : getInputSpecification()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("2a7449db-2e68-4795-b5ee-b57f97e4d020")
    @Override
    public EList<BpmnDataAssociation> getDataInputAssociation() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getDataInputAssociationDep());
    }

    @objid ("bc65fce3-6bf3-451e-8121-cc10d3cb5039")
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

    @objid ("c39837b5-d57b-4864-bc2d-bb23cb8389ad")
    @Override
    public EList<BpmnDataOutput> getOutputSpecification() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getOutputSpecificationDep());
    }

    @objid ("bb36febc-c02f-485d-a6ca-97d681a3e0ff")
    @Override
    public <T extends BpmnDataOutput> List<T> getOutputSpecification(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnDataOutput element : getOutputSpecification()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d86ec704-2e21-4722-b16f-c5c960f55f72")
    @Override
    public BpmnLoopCharacteristics getLoopCharacteristics() {
        Object obj = getDepVal(((BpmnActivitySmClass)getClassOf()).getLoopCharacteristicsDep());
        return (obj instanceof BpmnLoopCharacteristics)? (BpmnLoopCharacteristics)obj : null;
    }

    @objid ("c9111e1f-c537-4aef-a71c-b09d06a5e595")
    @Override
    public void setLoopCharacteristics(BpmnLoopCharacteristics value) {
        appendDepVal(((BpmnActivitySmClass)getClassOf()).getLoopCharacteristicsDep(), (SmObjectImpl)value);
    }

    @objid ("bb5b7291-050c-4ff2-81f6-5d1a80d9e2bd")
    @Override
    public EList<BpmnBoundaryEvent> getBoundaryEventRef() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getBoundaryEventRefDep());
    }

    @objid ("00eb2ede-2779-41da-8ff0-2162b71c1916")
    @Override
    public <T extends BpmnBoundaryEvent> List<T> getBoundaryEventRef(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnBoundaryEvent element : getBoundaryEventRef()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("43e66569-9c26-4e54-bb47-4bd4c2ccdb6b")
    @Override
    public EList<BpmnDataAssociation> getDataOutputAssociation() {
        return new SmList<>(this, ((BpmnActivitySmClass)getClassOf()).getDataOutputAssociationDep());
    }

    @objid ("2e35b557-b40c-4378-a98f-047e50282ef2")
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

    @objid ("87c3a6be-9275-4a00-afa3-e8d12e231895")
    @Override
    public BpmnSequenceFlow getDefaultFlow() {
        Object obj = getDepVal(((BpmnActivitySmClass)getClassOf()).getDefaultFlowDep());
        return (obj instanceof BpmnSequenceFlow)? (BpmnSequenceFlow)obj : null;
    }

    @objid ("fccc3903-dcb8-4742-a968-237edfc7e2e6")
    @Override
    public void setDefaultFlow(BpmnSequenceFlow value) {
        appendDepVal(((BpmnActivitySmClass)getClassOf()).getDefaultFlowDep(), (SmObjectImpl)value);
    }

    @objid ("3d4c8af6-b10c-4cbe-9eda-87a30749b012")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("694d399a-b4e3-4d7d-9804-8c1300a4fe67")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("a95dfc90-c628-46c6-93e0-a987446f1e05")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnActivity(this);
    }

}
