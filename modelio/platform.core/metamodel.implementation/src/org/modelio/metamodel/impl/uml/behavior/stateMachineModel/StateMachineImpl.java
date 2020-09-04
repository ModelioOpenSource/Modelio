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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorImpl;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateMachineData;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
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

@objid ("00543116-c4bf-1fd8-97fe-001ec947cd2a")
public class StateMachineImpl extends BehaviorImpl implements StateMachine {
    @objid ("6a88b38b-743a-48ec-a586-9aa2359ff183")
    @Override
    public KindOfStateMachine getKind() {
        return (KindOfStateMachine) getAttVal(((StateMachineSmClass)getClassOf()).getKindAtt());
    }

    @objid ("b4695090-13b2-4f46-b536-cfab600b205c")
    @Override
    public void setKind(KindOfStateMachine value) {
        setAttVal(((StateMachineSmClass)getClassOf()).getKindAtt(), value);
    }

    @objid ("ef49969d-f77c-49a8-9d1c-ef8f7ad47b56")
    @Override
    public Region getTop() {
        Object obj = getDepVal(((StateMachineSmClass)getClassOf()).getTopDep());
        return (obj instanceof Region)? (Region)obj : null;
    }

    @objid ("d5ee23e4-5444-4712-9d4c-2619a27f18eb")
    @Override
    public void setTop(Region value) {
        appendDepVal(((StateMachineSmClass)getClassOf()).getTopDep(), (SmObjectImpl)value);
    }

    @objid ("9087eeb1-ca26-4e29-9b91-610630e1a046")
    @Override
    public EList<State> getSubmachineState() {
        return new SmList<>(this, ((StateMachineSmClass)getClassOf()).getSubmachineStateDep());
    }

    @objid ("4d9f4643-c858-46cd-a5c5-6b0189dfb1d9")
    @Override
    public <T extends State> List<T> getSubmachineState(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final State element : getSubmachineState()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("10c8873e-c663-4645-856d-dccd1d1031c0")
    @Override
    public EList<EntryPointPseudoState> getEntryPoint() {
        return new SmList<>(this, ((StateMachineSmClass)getClassOf()).getEntryPointDep());
    }

    @objid ("73934074-7360-40e9-9697-d4c08283d870")
    @Override
    public <T extends EntryPointPseudoState> List<T> getEntryPoint(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final EntryPointPseudoState element : getEntryPoint()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("1edd9b0c-c3f9-44b9-9370-2221cab3c09e")
    @Override
    public EList<ExitPointPseudoState> getExitPoint() {
        return new SmList<>(this, ((StateMachineSmClass)getClassOf()).getExitPointDep());
    }

    @objid ("3006b4bd-81cc-4603-a07e-135fec458c5b")
    @Override
    public <T extends ExitPointPseudoState> List<T> getExitPoint(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExitPointPseudoState element : getExitPoint()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3286df78-89f3-4e48-9002-f5595fbb980e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("ff69f96c-f928-4ad1-8b98-3fef57cda4b5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2bf5692a-6444-4ceb-a132-a79944fb0103")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitStateMachine(this);
    }

}
