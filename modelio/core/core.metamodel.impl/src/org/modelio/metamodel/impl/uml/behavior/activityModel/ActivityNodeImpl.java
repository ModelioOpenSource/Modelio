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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0028d9f8-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityNodeImpl extends UmlModelElementImpl implements ActivityNode {
    @objid ("e1cd6d2f-4881-4f7a-93d0-c1dd4501284f")
    @Override
    public Activity getOwner() {
        Object obj = getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Activity)? (Activity)obj : null;
    }

    @objid ("2ae15d22-d4d5-4058-b3e9-5635be11bad6")
    @Override
    public void setOwner(Activity value) {
        appendDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("8af6b854-a126-4865-9782-ebb9b60f4a7b")
    @Override
    public ActivityPartition getOwnerPartition() {
        Object obj = getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerPartitionDep());
        return (obj instanceof ActivityPartition)? (ActivityPartition)obj : null;
    }

    @objid ("d61e9ff5-adb0-4b26-b8bf-faf8e43d2116")
    @Override
    public void setOwnerPartition(ActivityPartition value) {
        appendDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerPartitionDep(), (SmObjectImpl)value);
    }

    @objid ("e553a750-736e-4fd6-9b0b-724f2cc0a3ec")
    @Override
    public EList<ActivityEdge> getIncoming() {
        return new SmList<>(this, ((ActivityNodeSmClass)getClassOf()).getIncomingDep());
    }

    @objid ("0bd595b3-f44c-47d7-bd29-00b27857c07d")
    @Override
    public <T extends ActivityEdge> List<T> getIncoming(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityEdge element : getIncoming()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("054497f6-b833-4fcc-a83d-a52c1447db30")
    @Override
    public Clause getOwnerClause() {
        Object obj = getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerClauseDep());
        return (obj instanceof Clause)? (Clause)obj : null;
    }

    @objid ("16f16427-fb83-4d81-88e4-9a7d5731dc64")
    @Override
    public void setOwnerClause(Clause value) {
        appendDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerClauseDep(), (SmObjectImpl)value);
    }

    @objid ("a94d6f72-7b2c-49e6-803c-a90a399b0f98")
    @Override
    public StructuredActivityNode getOwnerNode() {
        Object obj = getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerNodeDep());
        return (obj instanceof StructuredActivityNode)? (StructuredActivityNode)obj : null;
    }

    @objid ("eafd18c9-92bf-4cd2-a28a-753df081b718")
    @Override
    public void setOwnerNode(StructuredActivityNode value) {
        appendDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerNodeDep(), (SmObjectImpl)value);
    }

    @objid ("decef091-90ed-40ef-9477-909744b6e2df")
    @Override
    public EList<ActivityEdge> getOutgoing() {
        return new SmList<>(this, ((ActivityNodeSmClass)getClassOf()).getOutgoingDep());
    }

    @objid ("13da713e-c6ba-4336-941b-4e97c85b8bc1")
    @Override
    public <T extends ActivityEdge> List<T> getOutgoing(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityEdge element : getOutgoing()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("b905e51a-730a-4d9b-b435-d4f7a91b8444")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        // OwnerPartition
        obj = (SmObjectImpl)this.getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerPartitionDep());
        if (obj != null)
          return obj;
        // OwnerClause
        obj = (SmObjectImpl)this.getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerClauseDep());
        if (obj != null)
          return obj;
        // OwnerNode
        obj = (SmObjectImpl)this.getDepVal(((ActivityNodeSmClass)getClassOf()).getOwnerNodeDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("49e893ce-9372-4c94-be52-600b8240a62b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ActivityNodeSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerPartition
        dep = ((ActivityNodeSmClass)getClassOf()).getOwnerPartitionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerClause
        dep = ((ActivityNodeSmClass)getClassOf()).getOwnerClauseDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerNode
        dep = ((ActivityNodeSmClass)getClassOf()).getOwnerNodeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("2b177d32-8313-451e-a5e0-c9572666f62d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityNode(this);
    }

}
