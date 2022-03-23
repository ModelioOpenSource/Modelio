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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
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

@objid ("004feeee-c4bf-1fd8-97fe-001ec947cd2a")
public class ExitPointPseudoStateImpl extends AbstractPseudoStateImpl implements ExitPointPseudoState {
    @objid ("855f245d-189b-4214-ad14-b3fb4bfb2112")
    @Override
    public State getExitOf() {
        Object obj = getDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("7adb3345-2b33-4eb8-85b2-f5da3b14383e")
    @Override
    public void setExitOf(State value) {
        appendDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfDep(), (SmObjectImpl)value);
    }

    @objid ("6e14bdc8-1079-4d27-85dd-8391b3e4a909")
    @Override
    public EList<ConnectionPointReference> getConnection() {
        return new SmList<>(this, ((ExitPointPseudoStateSmClass)getClassOf()).getConnectionDep());
    }

    @objid ("fc9d88f2-e7ac-4191-8bad-23e75b9282ab")
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

    @objid ("1e323827-22a7-4bc8-b529-e5f4873b0971")
    @Override
    public StateMachine getExitOfMachine() {
        Object obj = getDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfMachineDep());
        return (obj instanceof StateMachine)? (StateMachine)obj : null;
    }

    @objid ("7e3e8a23-c99c-4e64-b367-e983f7c02ab6")
    @Override
    public void setExitOfMachine(StateMachine value) {
        appendDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfMachineDep(), (SmObjectImpl)value);
    }

    @objid ("80a3380a-ce3d-4bd0-b5ff-f769273d5b8a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ExitOf
        obj = (SmObjectImpl)this.getDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfDep());
        if (obj != null)
          return obj;
        // ExitOfMachine
        obj = (SmObjectImpl)this.getDepVal(((ExitPointPseudoStateSmClass)getClassOf()).getExitOfMachineDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("590f3d2c-ce65-4dd0-a138-2eaaf46662d8")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ExitOf
        dep = ((ExitPointPseudoStateSmClass)getClassOf()).getExitOfDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ExitOfMachine
        dep = ((ExitPointPseudoStateSmClass)getClassOf()).getExitOfMachineDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e15fcce9-78a9-43ea-9789-92f95b15b84a")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExitPointPseudoState(this);
    }

}
