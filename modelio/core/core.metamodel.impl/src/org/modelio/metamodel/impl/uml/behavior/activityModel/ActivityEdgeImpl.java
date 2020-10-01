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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002733be-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityEdgeImpl extends UmlModelElementImpl implements ActivityEdge {
    @objid ("b6a88ef3-7bc8-4d8b-8173-3408788a94ea")
    @Override
    public String getGuard() {
        return (String) getAttVal(((ActivityEdgeSmClass)getClassOf()).getGuardAtt());
    }

    @objid ("d982a727-b10d-4a42-a1e5-6c829d11c533")
    @Override
    public void setGuard(String value) {
        setAttVal(((ActivityEdgeSmClass)getClassOf()).getGuardAtt(), value);
    }

    @objid ("b36e5c5e-0a78-4601-ae38-dd823ba28ac3")
    @Override
    public String getWeight() {
        return (String) getAttVal(((ActivityEdgeSmClass)getClassOf()).getWeightAtt());
    }

    @objid ("73a8f841-2d4b-4b2a-8db6-0210f8d0a3f8")
    @Override
    public void setWeight(String value) {
        setAttVal(((ActivityEdgeSmClass)getClassOf()).getWeightAtt(), value);
    }

    @objid ("c5201c62-5269-47ea-a1d5-6c69339c685a")
    @Override
    public ActivityNode getTarget() {
        Object obj = getDepVal(((ActivityEdgeSmClass)getClassOf()).getTargetDep());
        return (obj instanceof ActivityNode)? (ActivityNode)obj : null;
    }

    @objid ("de7f1417-97f1-4dc0-b8fc-5b0d68aeb388")
    @Override
    public void setTarget(ActivityNode value) {
        appendDepVal(((ActivityEdgeSmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("6de9334f-5aaa-4cae-a603-6f3e6fe5e0aa")
    @Override
    public ActivityNode getSource() {
        Object obj = getDepVal(((ActivityEdgeSmClass)getClassOf()).getSourceDep());
        return (obj instanceof ActivityNode)? (ActivityNode)obj : null;
    }

    @objid ("6a35d3b5-ba2c-43a5-8f2b-3902aa6d9723")
    @Override
    public void setSource(ActivityNode value) {
        appendDepVal(((ActivityEdgeSmClass)getClassOf()).getSourceDep(), (SmObjectImpl)value);
    }

    @objid ("934b87e5-0059-4f25-80b4-afbac218dd62")
    @Override
    public InterruptibleActivityRegion getInterrupts() {
        Object obj = getDepVal(((ActivityEdgeSmClass)getClassOf()).getInterruptsDep());
        return (obj instanceof InterruptibleActivityRegion)? (InterruptibleActivityRegion)obj : null;
    }

    @objid ("7d6e09c6-4c44-4afe-8cda-01e4bb9911ec")
    @Override
    public void setInterrupts(InterruptibleActivityRegion value) {
        appendDepVal(((ActivityEdgeSmClass)getClassOf()).getInterruptsDep(), (SmObjectImpl)value);
    }

    @objid ("c7c97521-e4c8-402a-9820-1590d85678a1")
    @Override
    public EList<InformationFlow> getRealizedInformationFlow() {
        return new SmList<>(this, ((ActivityEdgeSmClass)getClassOf()).getRealizedInformationFlowDep());
    }

    @objid ("0950e8f1-b4b6-4e9a-aa51-c34f455117e4")
    @Override
    public <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getRealizedInformationFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("73438117-6a88-4b60-9291-62d21bb7ca3a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Source
        obj = (SmObjectImpl)this.getDepVal(((ActivityEdgeSmClass)getClassOf()).getSourceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("34ed2f32-caad-4c56-a067-12f752e04a13")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Source
        dep = ((ActivityEdgeSmClass)getClassOf()).getSourceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("3eca0943-28bb-4b9b-ad79-279033b2feaa")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityEdge(this);
    }

}
