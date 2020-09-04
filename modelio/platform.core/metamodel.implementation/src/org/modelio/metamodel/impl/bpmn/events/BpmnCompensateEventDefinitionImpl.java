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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnCompensateEventDefinitionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00897772-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnCompensateEventDefinitionImpl extends BpmnEventDefinitionImpl implements BpmnCompensateEventDefinition {
    @objid ("0c66d043-8eb8-4e3d-a479-eab1a09ed6aa")
    @Override
    public String getWaitForCompletion() {
        return (String) getAttVal(((BpmnCompensateEventDefinitionSmClass)getClassOf()).getWaitForCompletionAtt());
    }

    @objid ("ec20bef8-af9c-46d1-be01-eebd8bf07214")
    @Override
    public void setWaitForCompletion(String value) {
        setAttVal(((BpmnCompensateEventDefinitionSmClass)getClassOf()).getWaitForCompletionAtt(), value);
    }

    @objid ("8a38498c-d451-4f7d-8289-343d32787a83")
    @Override
    public BpmnActivity getActivityRef() {
        Object obj = getDepVal(((BpmnCompensateEventDefinitionSmClass)getClassOf()).getActivityRefDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("101f7071-d793-450b-940a-a583833b9631")
    @Override
    public void setActivityRef(BpmnActivity value) {
        appendDepVal(((BpmnCompensateEventDefinitionSmClass)getClassOf()).getActivityRefDep(), (SmObjectImpl)value);
    }

    @objid ("c25d5f90-de31-45ea-b8d9-c0f716b1a6a7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("18b0f26a-97fa-4601-91ac-1de1113a81d0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("722b2ca0-ebb2-4b46-a303-0e686248206e")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnCompensateEventDefinition(this);
    }

}
