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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorParameterData;
import org.modelio.metamodel.impl.uml.statik.ParameterImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00413f3e-c4bf-1fd8-97fe-001ec947cd2a")
public class BehaviorParameterImpl extends ParameterImpl implements BehaviorParameter {
    @objid ("aec8018b-6f32-4fcd-8c41-c9e3e263bf30")
    @Override
    public EList<ObjectNode> getRepresentingObjectNode() {
        return new SmList<>(this, ((BehaviorParameterSmClass)getClassOf()).getRepresentingObjectNodeDep());
    }

    @objid ("cfcb78d2-235e-4984-b4cb-e922e6b3907b")
    @Override
    public <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ObjectNode element : getRepresentingObjectNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("bf9b5a43-03f8-41d1-b9a4-d1066e96b2b6")
    @Override
    public Behavior getOwner() {
        Object obj = getDepVal(((BehaviorParameterSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Behavior)? (Behavior)obj : null;
    }

    @objid ("bd4186df-68ec-4c5b-9a39-1926262c2930")
    @Override
    public void setOwner(Behavior value) {
        appendDepVal(((BehaviorParameterSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("0ab6d23f-236a-4924-b8e4-3e65d3b4f361")
    @Override
    public Parameter getMapped() {
        Object obj = getDepVal(((BehaviorParameterSmClass)getClassOf()).getMappedDep());
        return (obj instanceof Parameter)? (Parameter)obj : null;
    }

    @objid ("85365283-8350-40b9-88e2-7048adf5d0e5")
    @Override
    public void setMapped(Parameter value) {
        appendDepVal(((BehaviorParameterSmClass)getClassOf()).getMappedDep(), (SmObjectImpl)value);
    }

    @objid ("7c358d5f-9b22-47ac-869c-d51d9ad6ae36")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BehaviorParameterSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("49a9b040-aab3-40c9-a489-ba31ea182ef0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BehaviorParameterSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("1bc988a4-f34b-4b40-8a67-1e31534ef840")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBehaviorParameter(this);
    }

}
