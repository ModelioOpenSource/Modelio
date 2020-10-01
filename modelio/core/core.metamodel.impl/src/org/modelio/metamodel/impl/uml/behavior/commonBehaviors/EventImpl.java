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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0042b3fa-c4bf-1fd8-97fe-001ec947cd2a")
public class EventImpl extends UmlModelElementImpl implements Event {
    @objid ("8b69a138-dd6f-4c85-900c-32ede14a047f")
    @Override
    public String getExpression() {
        return (String) getAttVal(((EventSmClass)getClassOf()).getExpressionAtt());
    }

    @objid ("a6f923d2-e556-407f-8fe3-bbfb8b8af990")
    @Override
    public void setExpression(String value) {
        setAttVal(((EventSmClass)getClassOf()).getExpressionAtt(), value);
    }

    @objid ("5c010912-62c7-4596-a4e0-8012e325ca41")
    @Override
    public EventType getKind() {
        return (EventType) getAttVal(((EventSmClass)getClassOf()).getKindAtt());
    }

    @objid ("23b9a33e-f78a-4e03-9272-46f8eb6727df")
    @Override
    public void setKind(EventType value) {
        setAttVal(((EventSmClass)getClassOf()).getKindAtt(), value);
    }

    @objid ("96482703-5355-4842-949d-ee83bd3bae36")
    @Override
    public EList<Transition> getTriggered() {
        return new SmList<>(this, ((EventSmClass)getClassOf()).getTriggeredDep());
    }

    @objid ("f464570e-fade-49a7-83d2-225030f35512")
    @Override
    public <T extends Transition> List<T> getTriggered(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getTriggered()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5dc4d5d6-b70a-4301-8867-cedb9bd3d42c")
    @Override
    public Signal getModel() {
        Object obj = getDepVal(((EventSmClass)getClassOf()).getModelDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("413966bc-9b35-4fce-ba00-d928bf98d096")
    @Override
    public void setModel(Signal value) {
        appendDepVal(((EventSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("5a268444-f751-4b75-94f2-eb8ca462c3e2")
    @Override
    public EList<State> getOrigin() {
        return new SmList<>(this, ((EventSmClass)getClassOf()).getOriginDep());
    }

    @objid ("0bddc0c9-0b3b-474b-8817-a0ee4a33b15f")
    @Override
    public <T extends State> List<T> getOrigin(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final State element : getOrigin()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3f6e7eaf-a165-4c29-9c0b-01dafbb344c2")
    @Override
    public Operation getCalled() {
        Object obj = getDepVal(((EventSmClass)getClassOf()).getCalledDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("0aadbb35-9a4b-4dc7-a390-16787112ca92")
    @Override
    public void setCalled(Operation value) {
        appendDepVal(((EventSmClass)getClassOf()).getCalledDep(), (SmObjectImpl)value);
    }

    @objid ("52a9627c-adc8-457a-93df-e8497d23bc63")
    @Override
    public Behavior getComposed() {
        Object obj = getDepVal(((EventSmClass)getClassOf()).getComposedDep());
        return (obj instanceof Behavior)? (Behavior)obj : null;
    }

    @objid ("5857794e-6cea-44a8-952e-c3c61f155a47")
    @Override
    public void setComposed(Behavior value) {
        appendDepVal(((EventSmClass)getClassOf()).getComposedDep(), (SmObjectImpl)value);
    }

    @objid ("5c716bf9-25f2-4882-ab39-7cba66949920")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Composed
        obj = (SmObjectImpl)this.getDepVal(((EventSmClass)getClassOf()).getComposedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("4aa27281-f4f5-4909-9868-88caf3456d9b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Composed
        dep = ((EventSmClass)getClassOf()).getComposedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a5ad5c99-f808-4309-a59d-f6cd1bcc1618")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitEvent(this);
    }

}
