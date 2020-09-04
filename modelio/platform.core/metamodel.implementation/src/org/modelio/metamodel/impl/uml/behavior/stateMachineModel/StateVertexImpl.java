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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateVertexData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0054c0ea-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class StateVertexImpl extends UmlModelElementImpl implements StateVertex {
    @objid ("83e4b8ea-de32-4212-92cc-2cb662a328eb")
    @Override
    public EList<Transition> getOutGoing() {
        return new SmList<>(this, ((StateVertexSmClass)getClassOf()).getOutGoingDep());
    }

    @objid ("b5f191e7-22e8-4943-a41a-3c50e5c77175")
    @Override
    public <T extends Transition> List<T> getOutGoing(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getOutGoing()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("920f2746-67fe-4563-b699-8cb42525e600")
    @Override
    public EList<Transition> getIncoming() {
        return new SmList<>(this, ((StateVertexSmClass)getClassOf()).getIncomingDep());
    }

    @objid ("1c09d651-26f6-40f9-aa4f-42788eeccc59")
    @Override
    public <T extends Transition> List<T> getIncoming(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getIncoming()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("dcf3e4fd-4b24-436f-9aa8-f0b26a6766b6")
    @Override
    public Region getParent() {
        Object obj = getDepVal(((StateVertexSmClass)getClassOf()).getParentDep());
        return (obj instanceof Region)? (Region)obj : null;
    }

    @objid ("23356494-4f3e-4341-b4e0-d3ac9e0035e5")
    @Override
    public void setParent(Region value) {
        appendDepVal(((StateVertexSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("989a3990-6cab-49c6-b4c0-040c864309fd")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Parent
        obj = (SmObjectImpl)this.getDepVal(((StateVertexSmClass)getClassOf()).getParentDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("43de63e3-ada5-40b4-9517-a4dcf5ca6a00")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Parent
        dep = ((StateVertexSmClass)getClassOf()).getParentDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("46bdce0e-54fb-49ef-a345-237b0d9c2bf2")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitStateVertex(this);
    }

}
