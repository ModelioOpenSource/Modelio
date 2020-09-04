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
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.impl.bpmn.events.BpmnStartEventData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00928268-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnStartEventImpl extends BpmnCatchEventImpl implements BpmnStartEvent {
    @objid ("292ad01d-548a-4a22-8490-3864482ac7ed")
    @Override
    public boolean isIsInterrupting() {
        return (Boolean) getAttVal(((BpmnStartEventSmClass)getClassOf()).getIsInterruptingAtt());
    }

    @objid ("d300051e-85f1-484e-8400-246ca689c413")
    @Override
    public void setIsInterrupting(boolean value) {
        setAttVal(((BpmnStartEventSmClass)getClassOf()).getIsInterruptingAtt(), value);
    }

    @objid ("52326812-baed-46e9-b128-5019d6e922da")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("7f78ef97-ef4a-4906-892b-3cfceaf0dc1e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("0e314622-a518-4043-9c5c-ff28dcc7d32c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnStartEvent(this);
    }

}
