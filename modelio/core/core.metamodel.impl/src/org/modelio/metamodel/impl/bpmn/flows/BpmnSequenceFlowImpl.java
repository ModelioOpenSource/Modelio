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

package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007cfdf8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSequenceFlowImpl extends BpmnFlowElementImpl implements BpmnSequenceFlow {
    @objid ("52c6c7f5-d26f-4107-8c54-819500e4c246")
    @Override
    public boolean isIsImmediate() {
        return (Boolean) getAttVal(((BpmnSequenceFlowSmClass)getClassOf()).getIsImmediateAtt());
    }

    @objid ("a89db21a-90d0-4456-aa54-a1a7ef0c1a25")
    @Override
    public void setIsImmediate(boolean value) {
        setAttVal(((BpmnSequenceFlowSmClass)getClassOf()).getIsImmediateAtt(), value);
    }

    @objid ("3cacffae-07ac-45cf-93b9-5ac375e777e9")
    @Override
    public String getConditionExpression() {
        return (String) getAttVal(((BpmnSequenceFlowSmClass)getClassOf()).getConditionExpressionAtt());
    }

    @objid ("4b4c4c29-1fb2-485d-87cd-7259c437f577")
    @Override
    public void setConditionExpression(String value) {
        setAttVal(((BpmnSequenceFlowSmClass)getClassOf()).getConditionExpressionAtt(), value);
    }

    @objid ("20dbf73c-9968-4e91-b195-400629cf59f4")
    @Override
    public BpmnFlowNode getSourceRef() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getSourceRefDep());
        return (obj instanceof BpmnFlowNode)? (BpmnFlowNode)obj : null;
    }

    @objid ("fd6e9eec-5296-47a9-bcc6-3a3a3d76b02c")
    @Override
    public void setSourceRef(BpmnFlowNode value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getSourceRefDep(), (SmObjectImpl)value);
    }

    @objid ("4c6b45ad-e08d-475c-be93-3c5670c1b141")
    @Override
    public BpmnFlowNode getTargetRef() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getTargetRefDep());
        return (obj instanceof BpmnFlowNode)? (BpmnFlowNode)obj : null;
    }

    @objid ("bec15dde-329c-4d9c-a576-c5a7cb46b0fe")
    @Override
    public void setTargetRef(BpmnFlowNode value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getTargetRefDep(), (SmObjectImpl)value);
    }

    @objid ("dcf06fef-11fa-4f0c-8754-05295e537e73")
    @Override
    public BpmnInclusiveGateway getDefaultOfInclusive() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfInclusiveDep());
        return (obj instanceof BpmnInclusiveGateway)? (BpmnInclusiveGateway)obj : null;
    }

    @objid ("02e22cc1-f9bb-436c-aa97-af1ea55d5997")
    @Override
    public void setDefaultOfInclusive(BpmnInclusiveGateway value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfInclusiveDep(), (SmObjectImpl)value);
    }

    @objid ("fec01340-0249-4744-86e0-7423429f266f")
    @Override
    public BpmnActivity getDefaultFrom() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultFromDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("66e7153f-675c-4779-9262-9f149a7ce51b")
    @Override
    public void setDefaultFrom(BpmnActivity value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultFromDep(), (SmObjectImpl)value);
    }

    @objid ("f1210cbb-460a-4b52-9b57-9cd382f85b48")
    @Override
    public BpmnExclusiveGateway getDefaultOfExclusive() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfExclusiveDep());
        return (obj instanceof BpmnExclusiveGateway)? (BpmnExclusiveGateway)obj : null;
    }

    @objid ("818e7d23-64a7-4392-b51e-882f7194e87a")
    @Override
    public void setDefaultOfExclusive(BpmnExclusiveGateway value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfExclusiveDep(), (SmObjectImpl)value);
    }

    @objid ("2073aaf2-bb66-452e-98b4-0757acc47d52")
    @Override
    public EList<BpmnSequenceFlowDataAssociation> getConnector() {
        return new SmList<>(this, ((BpmnSequenceFlowSmClass)getClassOf()).getConnectorDep());
    }

    @objid ("8897d42f-f83e-431f-8437-1ac8173a62c4")
    @Override
    public <T extends BpmnSequenceFlowDataAssociation> List<T> getConnector(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnSequenceFlowDataAssociation element : getConnector()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("9ac3f569-5329-402c-a286-ab8ff584cc2e")
    @Override
    public BpmnComplexGateway getDefaultOfComplex() {
        Object obj = getDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfComplexDep());
        return (obj instanceof BpmnComplexGateway)? (BpmnComplexGateway)obj : null;
    }

    @objid ("5f418c13-ab4d-46ee-8bcd-1c28a24d4f90")
    @Override
    public void setDefaultOfComplex(BpmnComplexGateway value) {
        appendDepVal(((BpmnSequenceFlowSmClass)getClassOf()).getDefaultOfComplexDep(), (SmObjectImpl)value);
    }

    @objid ("6e1a0f9e-7785-44f8-a4d1-cc33dec55365")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3fdcc8cb-8b21-43c6-9875-822d648c2a59")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("66a6f320-8f00-409c-9a9d-5604efaa129d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnSequenceFlow(this);
    }

}
