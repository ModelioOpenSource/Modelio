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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnConditionalEventDefinitionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008a1e34-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnConditionalEventDefinitionImpl extends BpmnEventDefinitionImpl implements BpmnConditionalEventDefinition {
    @objid ("957b53cd-8f1b-46d1-9933-e7377b2feb6f")
    @Override
    public String getCondition() {
        return (String) getAttVal(((BpmnConditionalEventDefinitionSmClass)getClassOf()).getConditionAtt());
    }

    @objid ("1f4fdb65-5fa6-444d-80f6-e1af260fb715")
    @Override
    public void setCondition(String value) {
        setAttVal(((BpmnConditionalEventDefinitionSmClass)getClassOf()).getConditionAtt(), value);
    }

    @objid ("deb8566f-a659-4c48-ab73-26141f1a0b33")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0abf9ff8-03d7-4951-bee9-30a1787e9dce")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("930fda71-2a6d-4e9f-9838-171126e7b5cd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnConditionalEventDefinition(this);
    }

}
