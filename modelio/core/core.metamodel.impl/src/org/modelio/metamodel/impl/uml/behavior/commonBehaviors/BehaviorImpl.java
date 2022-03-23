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

package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0040c504-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BehaviorImpl extends UmlModelElementImpl implements Behavior {
    @objid ("0beaf822-a267-487d-83c9-510db6116b8d")
    @Override
    public boolean isIsReentrant() {
        return (Boolean) getAttVal(((BehaviorSmClass)getClassOf()).getIsReentrantAtt());
    }

    @objid ("2fbd6a64-41a1-4fac-bcb8-4a551913fdaa")
    @Override
    public void setIsReentrant(boolean value) {
        setAttVal(((BehaviorSmClass)getClassOf()).getIsReentrantAtt(), value);
    }

    @objid ("d48df1fa-e8f9-4546-a09b-6a2f8e589172")
    @Override
    public NameSpace getOwner() {
        Object obj = getDepVal(((BehaviorSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("f9552f55-1328-4c8e-837c-1b4567b95d99")
    @Override
    public void setOwner(NameSpace value) {
        appendDepVal(((BehaviorSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3c756c2d-7284-40d5-8016-bb4aaddec7f9")
    @Override
    public EList<BehaviorParameter> getParameter() {
        return new SmList<>(this, ((BehaviorSmClass)getClassOf()).getParameterDep());
    }

    @objid ("fb369975-be90-4ab9-b2ec-7791969d3eef")
    @Override
    public <T extends BehaviorParameter> List<T> getParameter(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BehaviorParameter element : getParameter()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("ffca9587-e046-4d0a-b2fb-4133c3efd163")
    @Override
    public Operation getOwnerOperation() {
        Object obj = getDepVal(((BehaviorSmClass)getClassOf()).getOwnerOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("46e25049-f887-45e8-abed-96882440df0c")
    @Override
    public void setOwnerOperation(Operation value) {
        appendDepVal(((BehaviorSmClass)getClassOf()).getOwnerOperationDep(), (SmObjectImpl)value);
    }

    @objid ("b35f1baf-21b9-4719-9874-8e77698eb347")
    @Override
    public EList<Collaboration> getOwnedCollaboration() {
        return new SmList<>(this, ((BehaviorSmClass)getClassOf()).getOwnedCollaborationDep());
    }

    @objid ("bbfe3931-f376-4be5-a204-5fc8ae528797")
    @Override
    public <T extends Collaboration> List<T> getOwnedCollaboration(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Collaboration element : getOwnedCollaboration()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("4bdf1d95-93e1-457b-ba8d-b5b30150496e")
    @Override
    public EList<CallBehaviorAction> getCaller() {
        return new SmList<>(this, ((BehaviorSmClass)getClassOf()).getCallerDep());
    }

    @objid ("f953fca6-7008-4e26-a96f-db73e089749c")
    @Override
    public <T extends CallBehaviorAction> List<T> getCaller(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CallBehaviorAction element : getCaller()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("aff3bb23-201e-4268-a207-9b537e3e953b")
    @Override
    public EList<Event> getEComponent() {
        return new SmList<>(this, ((BehaviorSmClass)getClassOf()).getEComponentDep());
    }

    @objid ("4a4149fe-52a3-4b48-90df-89e719e309ca")
    @Override
    public <T extends Event> List<T> getEComponent(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Event element : getEComponent()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("cb16d660-657d-4d18-bcbd-40cfb81bd55f")
    @Override
    public EList<Transition> getEffectOf() {
        return new SmList<>(this, ((BehaviorSmClass)getClassOf()).getEffectOfDep());
    }

    @objid ("19f857fe-d811-4f69-ab68-64ed37c3ae7a")
    @Override
    public <T extends Transition> List<T> getEffectOf(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getEffectOf()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("8b821d23-e937-4a55-84c9-8b443ff551fc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BehaviorSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        // OwnerOperation
        obj = (SmObjectImpl)this.getDepVal(((BehaviorSmClass)getClassOf()).getOwnerOperationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("30b86ebf-d38b-4ced-8cb4-90f7f8574330")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BehaviorSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerOperation
        dep = ((BehaviorSmClass)getClassOf()).getOwnerOperationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("318683c0-3b1a-4994-a4b6-c08d37b845dd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBehavior(this);
    }

}
