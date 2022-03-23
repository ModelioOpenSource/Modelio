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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("004f6fc8-c4bf-1fd8-97fe-001ec947cd2a")
public class EntryPointPseudoStateImpl extends AbstractPseudoStateImpl implements EntryPointPseudoState {
    @objid ("9de0246a-92cf-436c-a89c-bd596c6eef8e")
    @Override
    public State getEntryOf() {
        Object obj = getDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("883b1d6e-9164-4efd-90e9-3f2799733f6e")
    @Override
    public void setEntryOf(State value) {
        appendDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfDep(), (SmObjectImpl)value);
    }

    @objid ("a85bce6f-2a1b-49a5-82be-7477c878e512")
    @Override
    public EList<ConnectionPointReference> getConnection() {
        return new SmList<>(this, ((EntryPointPseudoStateSmClass)getClassOf()).getConnectionDep());
    }

    @objid ("27253f49-8d4a-4ff8-b79f-63fcf05a961e")
    @Override
    public <T extends ConnectionPointReference> List<T> getConnection(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ConnectionPointReference element : getConnection()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("41438339-fdfe-457a-b3d4-917955102076")
    @Override
    public StateMachine getEntryOfMachine() {
        Object obj = getDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfMachineDep());
        return (obj instanceof StateMachine)? (StateMachine)obj : null;
    }

    @objid ("b0651f72-1f03-467c-9a90-2f9f4e62d214")
    @Override
    public void setEntryOfMachine(StateMachine value) {
        appendDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfMachineDep(), (SmObjectImpl)value);
    }

    @objid ("81ef2d1f-3d30-4097-93f6-2c5e6666323d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // EntryOf
        obj = (SmObjectImpl)this.getDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfDep());
        if (obj != null)
          return obj;
        // EntryOfMachine
        obj = (SmObjectImpl)this.getDepVal(((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfMachineDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8edc287d-17ea-4400-bf09-b335e58cf7df")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // EntryOf
        dep = ((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // EntryOfMachine
        dep = ((EntryPointPseudoStateSmClass)getClassOf()).getEntryOfMachineDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5f12fc92-c26f-494a-a694-baaaffc73f31")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitEntryPointPseudoState(this);
    }

}
