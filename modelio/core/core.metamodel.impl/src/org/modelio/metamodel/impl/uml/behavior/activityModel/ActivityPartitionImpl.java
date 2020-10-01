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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityPartitionData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002a04ae-c4bf-1fd8-97fe-001ec947cd2a")
public class ActivityPartitionImpl extends ActivityGroupImpl implements ActivityPartition {
    @objid ("a0acbb97-12c1-48cb-967d-2276b1b821a4")
    @Override
    public boolean isIsDimension() {
        return (Boolean) getAttVal(((ActivityPartitionSmClass)getClassOf()).getIsDimensionAtt());
    }

    @objid ("f9327809-67fe-4c03-8d84-3381d1774b33")
    @Override
    public void setIsDimension(boolean value) {
        setAttVal(((ActivityPartitionSmClass)getClassOf()).getIsDimensionAtt(), value);
    }

    @objid ("859c20a4-8e7c-4c35-894d-805c74681258")
    @Override
    public boolean isIsExternal() {
        return (Boolean) getAttVal(((ActivityPartitionSmClass)getClassOf()).getIsExternalAtt());
    }

    @objid ("ab6ce7ea-943f-41d2-acec-df751f78584a")
    @Override
    public void setIsExternal(boolean value) {
        setAttVal(((ActivityPartitionSmClass)getClassOf()).getIsExternalAtt(), value);
    }

    @objid ("f4cb58fb-26aa-4eaf-ac50-a5e54b17cd6a")
    @Override
    public UmlModelElement getRepresented() {
        Object obj = getDepVal(((ActivityPartitionSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("7e886bea-642d-4af9-915d-597329205fc5")
    @Override
    public void setRepresented(UmlModelElement value) {
        appendDepVal(((ActivityPartitionSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("ba4ac53a-ee57-4287-bfe6-841b01c1227d")
    @Override
    public EList<ActivityNode> getContainedNode() {
        return new SmList<>(this, ((ActivityPartitionSmClass)getClassOf()).getContainedNodeDep());
    }

    @objid ("ea4af6ac-7fb6-4d7c-a000-098e876ab227")
    @Override
    public <T extends ActivityNode> List<T> getContainedNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityNode element : getContainedNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("79d90f2b-5ae5-4654-8666-2b8ce9cd83eb")
    @Override
    public EList<MessageFlow> getOutgoing() {
        return new SmList<>(this, ((ActivityPartitionSmClass)getClassOf()).getOutgoingDep());
    }

    @objid ("f729739e-9576-4aeb-adf6-18d9f25d157c")
    @Override
    public <T extends MessageFlow> List<T> getOutgoing(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final MessageFlow element : getOutgoing()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("afc18a61-f817-4fd7-8753-b45ba5b6a1e0")
    @Override
    public ActivityPartition getSuperPartition() {
        Object obj = getDepVal(((ActivityPartitionSmClass)getClassOf()).getSuperPartitionDep());
        return (obj instanceof ActivityPartition)? (ActivityPartition)obj : null;
    }

    @objid ("67e93d53-8f9d-4bb0-9c6c-744dfd2488aa")
    @Override
    public void setSuperPartition(ActivityPartition value) {
        appendDepVal(((ActivityPartitionSmClass)getClassOf()).getSuperPartitionDep(), (SmObjectImpl)value);
    }

    @objid ("fe04837f-dc05-4c06-91e6-63a38b119897")
    @Override
    public EList<ActivityPartition> getSubPartition() {
        return new SmList<>(this, ((ActivityPartitionSmClass)getClassOf()).getSubPartitionDep());
    }

    @objid ("f42a606c-ed6c-4b65-bb46-8254cc8e181e")
    @Override
    public <T extends ActivityPartition> List<T> getSubPartition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityPartition element : getSubPartition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e7938d10-d3df-4c98-81db-30bede640494")
    @Override
    public EList<MessageFlow> getIncoming() {
        return new SmList<>(this, ((ActivityPartitionSmClass)getClassOf()).getIncomingDep());
    }

    @objid ("d8d666df-9a75-4952-96cc-2130c39f62f9")
    @Override
    public <T extends MessageFlow> List<T> getIncoming(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final MessageFlow element : getIncoming()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ad8619a0-4ee9-4254-933e-d2ec50aa9e90")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SuperPartition
        obj = (SmObjectImpl)this.getDepVal(((ActivityPartitionSmClass)getClassOf()).getSuperPartitionDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("9e3c3545-44b8-4c48-88e5-607a9c46457f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SuperPartition
        dep = ((ActivityPartitionSmClass)getClassOf()).getSuperPartitionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("bd54deb2-a62a-4c10-bb4c-6fa602264b0b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityPartition(this);
    }

}
