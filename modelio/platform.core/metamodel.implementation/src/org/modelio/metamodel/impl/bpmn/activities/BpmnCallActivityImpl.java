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
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnCallActivityData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007f281c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnCallActivityImpl extends BpmnActivityImpl implements BpmnCallActivity {
    @objid ("11221fe2-d7e2-426f-a85f-b3b24d3bafb8")
    @Override
    public BpmnTask getCalledGlobalTask() {
        Object obj = getDepVal(((BpmnCallActivitySmClass)getClassOf()).getCalledGlobalTaskDep());
        return (obj instanceof BpmnTask)? (BpmnTask)obj : null;
    }

    @objid ("ef19429a-a58d-403e-a5fb-dd412b700182")
    @Override
    public void setCalledGlobalTask(BpmnTask value) {
        appendDepVal(((BpmnCallActivitySmClass)getClassOf()).getCalledGlobalTaskDep(), (SmObjectImpl)value);
    }

    @objid ("dd33f641-8fe3-4564-a933-dc852ea7ed9f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c9abd846-0ba0-4e83-850d-09a9e0aa5707")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("135213aa-bb53-4217-8cef-61ff7afa8477")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnCallActivity(this);
    }

}
