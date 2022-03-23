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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0057164c-c4bf-1fd8-97fe-001ec947cd2a")
public class RegionImpl extends UmlModelElementImpl implements Region {
    @objid ("370896b0-9ea0-46fa-9d2a-a4446240e783")
    @Override
    public State getParent() {
        Object obj = getDepVal(((RegionSmClass)getClassOf()).getParentDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("267133ef-cde1-47a5-b03e-d6d0ad816514")
    @Override
    public void setParent(State value) {
        appendDepVal(((RegionSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("e02d1ad3-3661-4a8b-82ab-aada94e47979")
    @Override
    public StateMachine getRepresented() {
        Object obj = getDepVal(((RegionSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof StateMachine)? (StateMachine)obj : null;
    }

    @objid ("f591cb4a-9a5b-425d-b23c-b840dfbfe662")
    @Override
    public void setRepresented(StateMachine value) {
        appendDepVal(((RegionSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("5ec8020b-56f7-49dc-a913-13552a5bd2c4")
    @Override
    public EList<StateVertex> getSub() {
        return new SmList<>(this, ((RegionSmClass)getClassOf()).getSubDep());
    }

    @objid ("7651e29e-f9a4-44ab-9e44-6221e3670d9b")
    @Override
    public <T extends StateVertex> List<T> getSub(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final StateVertex element : getSub()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("1ea4a4bb-cdb1-4e1b-b0b1-168b863ab4d9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Parent
        obj = (SmObjectImpl)this.getDepVal(((RegionSmClass)getClassOf()).getParentDep());
        if (obj != null)
          return obj;
        // Represented
        obj = (SmObjectImpl)this.getDepVal(((RegionSmClass)getClassOf()).getRepresentedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8767ff9f-53fa-4767-be0f-793dde82ae6e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Parent
        dep = ((RegionSmClass)getClassOf()).getParentDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Represented
        dep = ((RegionSmClass)getClassOf()).getRepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e9562af7-bef7-4c6c-a45b-8cac39b6e752")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitRegion(this);
    }

}
