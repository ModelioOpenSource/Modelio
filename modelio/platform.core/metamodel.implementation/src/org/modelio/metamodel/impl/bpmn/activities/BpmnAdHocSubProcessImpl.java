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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.AdHocOrdering;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.impl.bpmn.activities.BpmnAdHocSubProcessData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007e160c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnAdHocSubProcessImpl extends BpmnSubProcessImpl implements BpmnAdHocSubProcess {
    @objid ("9d4f6b38-fa04-4091-a581-f94f5d48ca4d")
    @Override
    public AdHocOrdering getOrdering() {
        return (AdHocOrdering) getAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getOrderingAtt());
    }

    @objid ("23006958-7045-46fa-8486-d541d3720642")
    @Override
    public void setOrdering(AdHocOrdering value) {
        setAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getOrderingAtt(), value);
    }

    @objid ("8a69b72a-69dd-4298-87b4-088a5c51b3d5")
    @Override
    public boolean isCancelRemainingInstances() {
        return (Boolean) getAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getCancelRemainingInstancesAtt());
    }

    @objid ("60ed4a96-4aae-4ba7-818d-35771ea06e3c")
    @Override
    public void setCancelRemainingInstances(boolean value) {
        setAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getCancelRemainingInstancesAtt(), value);
    }

    @objid ("6c92f6e3-a2a4-438e-a9f6-449b7cc6a11a")
    @Override
    public String getCompletionCondition() {
        return (String) getAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getCompletionConditionAtt());
    }

    @objid ("105ac63b-e3db-4608-ad7a-9ce898a7a26d")
    @Override
    public void setCompletionCondition(String value) {
        setAttVal(((BpmnAdHocSubProcessSmClass)getClassOf()).getCompletionConditionAtt(), value);
    }

    @objid ("c280384c-effc-4cd8-97c4-7587cd0bfbff")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e29d6a23-3fb4-4202-ba9c-6a39a0042727")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("f2045362-94eb-4427-b8be-cec3e29e61c7")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnAdHocSubProcess(this);
    }

}
