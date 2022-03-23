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

package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007fa21a-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnComplexBehaviorDefinitionImpl extends BpmnBaseElementImpl implements BpmnComplexBehaviorDefinition {
    @objid ("8b413f96-76a6-4f82-a288-22245a76a9c2")
    @Override
    public String getCondition() {
        return (String) getAttVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getConditionAtt());
    }

    @objid ("7ecc2c99-47b9-4394-998d-c8b4300e02c3")
    @Override
    public void setCondition(String value) {
        setAttVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getConditionAtt(), value);
    }

    @objid ("15c8c7a4-a43b-4aa3-9a03-4a5e3da4907a")
    @Override
    public BpmnMultiInstanceLoopCharacteristics getOwner() {
        Object obj = getDepVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof BpmnMultiInstanceLoopCharacteristics)? (BpmnMultiInstanceLoopCharacteristics)obj : null;
    }

    @objid ("98c3b72f-bc80-4184-92f1-11783c2edd5c")
    @Override
    public void setOwner(BpmnMultiInstanceLoopCharacteristics value) {
        appendDepVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("8e106458-1507-4990-a86c-ad771d14a81f")
    @Override
    public BpmnImplicitThrowEvent getEvent() {
        Object obj = getDepVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getEventDep());
        return (obj instanceof BpmnImplicitThrowEvent)? (BpmnImplicitThrowEvent)obj : null;
    }

    @objid ("da828e4a-ba0a-4022-bc58-124ec88a6dac")
    @Override
    public void setEvent(BpmnImplicitThrowEvent value) {
        appendDepVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getEventDep(), (SmObjectImpl)value);
    }

    @objid ("c7cbbf1d-252b-4a9c-9ac9-263794343924")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("84e91623-7e74-4ceb-88bc-2197912e1fc8")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BpmnComplexBehaviorDefinitionSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("d023a2b5-ef6d-4859-9349-f687a3eb2794")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnComplexBehaviorDefinition(this);
    }

}
