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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ConnectionPointReferenceData;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("004e6c5e-c4bf-1fd8-97fe-001ec947cd2a")
public class ConnectionPointReferenceImpl extends StateVertexImpl implements ConnectionPointReference {
    @objid ("bda1e522-95f3-41bd-b5fd-ed4de912571c")
    @Override
    public ExitPointPseudoState getExit() {
        Object obj = getDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getExitDep());
        return (obj instanceof ExitPointPseudoState)? (ExitPointPseudoState)obj : null;
    }

    @objid ("afbea99e-70c7-40c2-aff1-7f5bd9c17546")
    @Override
    public void setExit(ExitPointPseudoState value) {
        appendDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getExitDep(), (SmObjectImpl)value);
    }

    @objid ("41b8821a-58cd-43a8-be12-df2479878b92")
    @Override
    public EntryPointPseudoState getEntry() {
        Object obj = getDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getEntryDep());
        return (obj instanceof EntryPointPseudoState)? (EntryPointPseudoState)obj : null;
    }

    @objid ("54b0d907-f1c0-4fc9-9d15-61de459bda7a")
    @Override
    public void setEntry(EntryPointPseudoState value) {
        appendDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getEntryDep(), (SmObjectImpl)value);
    }

    @objid ("b7c19cff-e48d-47c0-ae87-e07180c4008e")
    @Override
    public State getOwnerState() {
        Object obj = getDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getOwnerStateDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("5c582fb4-ed6d-413c-8220-cc57e1c2f035")
    @Override
    public void setOwnerState(State value) {
        appendDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getOwnerStateDep(), (SmObjectImpl)value);
    }

    @objid ("1dee4ce2-1aa3-49ef-9bad-163a6e923ef5")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerState
        obj = (SmObjectImpl)this.getDepVal(((ConnectionPointReferenceSmClass)getClassOf()).getOwnerStateDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("dcd3bd3e-63a2-4795-82eb-e84fdfcd0b87")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerState
        dep = ((ConnectionPointReferenceSmClass)getClassOf()).getOwnerStateDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a0c5ade8-e50f-4795-9781-f9ebde1d2c69")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitConnectionPointReference(this);
    }

}
