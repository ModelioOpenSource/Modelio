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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
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

@objid ("0053a458-c4bf-1fd8-97fe-001ec947cd2a")
public class StateImpl extends StateVertexImpl implements State {
    @objid ("74232e46-69ed-44fd-846d-31c070a46e49")
    @Override
    public EList<ExitPointPseudoState> getExitPoint() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getExitPointDep());
    }

    @objid ("74d47363-d19c-4a34-a5c6-b11f5301d627")
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

    @objid ("dd2992ca-8118-425c-aa07-ae7171fe187e")
    @Override
    public EList<Event> getDeffered() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getDefferedDep());
    }

    @objid ("00c4a1c6-ea20-434e-8a73-0644b81a9d02")
    @Override
    public <T extends Event> List<T> getDeffered(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Event element : getDeffered()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("e43b9f19-f448-452d-b6bd-c1bc23a6995c")
    @Override
    public EList<InternalTransition> getInternal() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getInternalDep());
    }

    @objid ("ab6a61a3-86c1-4e0d-9dbf-fb0235a532ed")
    @Override
    public <T extends InternalTransition> List<T> getInternal(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InternalTransition element : getInternal()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("1bf5df5a-281b-453b-bb43-96a6e1cbc7d6")
    @Override
    public EList<EntryPointPseudoState> getEntryPoint() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getEntryPointDep());
    }

    @objid ("b4906cc6-bc77-4c9a-bbb6-97aca958a941")
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

    @objid ("383a0726-46f1-4fe2-908c-c1796d4fa752")
    @Override
    public EList<Region> getOwnedRegion() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getOwnedRegionDep());
    }

    @objid ("b7c93c6f-0eb3-4e20-8ab7-79226841b635")
    @Override
    public <T extends Region> List<T> getOwnedRegion(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Region element : getOwnedRegion()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("981d3770-51f5-4fcb-81f9-d3120cd4bece")
    @Override
    public EList<ObjectNode> getRequiredStateOf() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getRequiredStateOfDep());
    }

    @objid ("54507843-ee37-494b-9db1-b1041ef781d3")
    @Override
    public <T extends ObjectNode> List<T> getRequiredStateOf(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ObjectNode element : getRequiredStateOf()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("523a1317-54df-462d-911c-75992f84842b")
    @Override
    public EList<ConnectionPointReference> getConnection() {
        return new SmList<>(this, ((StateSmClass)getClassOf()).getConnectionDep());
    }

    @objid ("64285e6e-a252-4a9b-8c4d-eba77ebfc18f")
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

    @objid ("63071d23-c281-412b-b80f-4b0b48eaaafb")
    @Override
    public StateMachine getSubMachine() {
        Object obj = getDepVal(((StateSmClass)getClassOf()).getSubMachineDep());
        return (obj instanceof StateMachine)? (StateMachine)obj : null;
    }

    @objid ("3ff2a920-2763-4735-8214-2a5668c4fbb1")
    @Override
    public void setSubMachine(StateMachine value) {
        appendDepVal(((StateSmClass)getClassOf()).getSubMachineDep(), (SmObjectImpl)value);
    }

    @objid ("d9b19804-9fca-4067-9bab-08ea676e657e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("bbd74ac7-c9ad-4e56-a3a0-995b4f8918dd")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("ada7d678-9df9-4535-a15b-727300c5173d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitState(this);
    }

}
