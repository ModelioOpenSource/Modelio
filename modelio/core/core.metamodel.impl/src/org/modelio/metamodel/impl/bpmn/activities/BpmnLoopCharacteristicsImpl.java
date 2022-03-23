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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00801fec-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnLoopCharacteristicsImpl extends BpmnBaseElementImpl implements BpmnLoopCharacteristics {
    @objid ("aa17f838-9a68-4ebb-b349-34c4b9ec52b0")
    @Override
    public BpmnActivity getOwnerActivity() {
        Object obj = getDepVal(((BpmnLoopCharacteristicsSmClass)getClassOf()).getOwnerActivityDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("b2f53081-9861-4030-8ea2-5818662724db")
    @Override
    public void setOwnerActivity(BpmnActivity value) {
        appendDepVal(((BpmnLoopCharacteristicsSmClass)getClassOf()).getOwnerActivityDep(), (SmObjectImpl)value);
    }

    @objid ("a2174faf-7369-48ce-9f2e-8c95f31331ff")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerActivity
        obj = (SmObjectImpl)this.getDepVal(((BpmnLoopCharacteristicsSmClass)getClassOf()).getOwnerActivityDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("51d383b9-b2cd-4aa0-a29a-000732027d87")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerActivity
        dep = ((BpmnLoopCharacteristicsSmClass)getClassOf()).getOwnerActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("2be5a152-8be9-4eb9-a445-482fc07aec4f")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnLoopCharacteristics(this);
    }

}
