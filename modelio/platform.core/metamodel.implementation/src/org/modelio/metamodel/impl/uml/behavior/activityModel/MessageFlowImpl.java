/* 
 * Copyright 2013-2018 Modeliosoft
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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.MessageFlowData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00381364-c4bf-1fd8-97fe-001ec947cd2a")
public class MessageFlowImpl extends ActivityEdgeImpl implements MessageFlow {
    @objid ("1bf5d615-41d9-422a-a898-119511f796ea")
    @Override
    public ActivityPartition getTargetPartition() {
        Object obj = getDepVal(((MessageFlowSmClass)getClassOf()).getTargetPartitionDep());
        return (obj instanceof ActivityPartition)? (ActivityPartition)obj : null;
    }

    @objid ("c2fb144b-3f38-41c8-b730-9d11fedcd337")
    @Override
    public void setTargetPartition(ActivityPartition value) {
        appendDepVal(((MessageFlowSmClass)getClassOf()).getTargetPartitionDep(), (SmObjectImpl)value);
    }

    @objid ("7e2f2a53-46ad-4894-b7f7-b0a293309979")
    @Override
    public ActivityPartition getSourcePartition() {
        Object obj = getDepVal(((MessageFlowSmClass)getClassOf()).getSourcePartitionDep());
        return (obj instanceof ActivityPartition)? (ActivityPartition)obj : null;
    }

    @objid ("3c35aef3-12a6-4f4d-9567-f49f1756159c")
    @Override
    public void setSourcePartition(ActivityPartition value) {
        appendDepVal(((MessageFlowSmClass)getClassOf()).getSourcePartitionDep(), (SmObjectImpl)value);
    }

    @objid ("bfd09b56-36e4-4104-b730-c65e188af9a4")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SourcePartition
        obj = (SmObjectImpl)this.getDepVal(((MessageFlowSmClass)getClassOf()).getSourcePartitionDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ce7825d4-3acd-4aff-83b9-55b220279fb8")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SourcePartition
        dep = ((MessageFlowSmClass)getClassOf()).getSourcePartitionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("b4187beb-b9dc-4da3-819b-c7c1f524a284")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitMessageFlow(this);
    }

}
