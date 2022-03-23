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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorImpl;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00263040-c4bf-1fd8-97fe-001ec947cd2a")
public class ActivityImpl extends BehaviorImpl implements Activity {
    @objid ("83aadfc7-fdff-4212-897b-f264573723f8")
    @Override
    public boolean isIsSingleExecution() {
        return (Boolean) getAttVal(((ActivitySmClass)getClassOf()).getIsSingleExecutionAtt());
    }

    @objid ("21eba336-bef5-4943-bfcd-d06d154a1bc8")
    @Override
    public void setIsSingleExecution(boolean value) {
        setAttVal(((ActivitySmClass)getClassOf()).getIsSingleExecutionAtt(), value);
    }

    @objid ("a29cfe85-202a-4645-8b40-08f1a7a8e711")
    @Override
    public boolean isIsReadOnly() {
        return (Boolean) getAttVal(((ActivitySmClass)getClassOf()).getIsReadOnlyAtt());
    }

    @objid ("6398f33f-1e8b-4aa3-a598-938e00430c90")
    @Override
    public void setIsReadOnly(boolean value) {
        setAttVal(((ActivitySmClass)getClassOf()).getIsReadOnlyAtt(), value);
    }

    @objid ("cad474b1-7dfb-4de0-ba68-cb126bc740c5")
    @Override
    public EList<ActivityGroup> getOwnedGroup() {
        return new SmList<>(this, ((ActivitySmClass)getClassOf()).getOwnedGroupDep());
    }

    @objid ("c0f04372-9652-4b9b-8112-9c85d32afc0e")
    @Override
    public <T extends ActivityGroup> List<T> getOwnedGroup(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityGroup element : getOwnedGroup()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("9d5f4cff-3e76-405e-b25a-daec9144f344")
    @Override
    public EList<ActivityNode> getOwnedNode() {
        return new SmList<>(this, ((ActivitySmClass)getClassOf()).getOwnedNodeDep());
    }

    @objid ("6c3bf5a0-40a8-4afe-9006-15e06a151109")
    @Override
    public <T extends ActivityNode> List<T> getOwnedNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityNode element : getOwnedNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("f865726e-6a50-41df-800a-1668e6f2fab3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("f3c39efb-8b93-4433-abba-09f16bfbac80")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("1b77fc7c-53c7-4213-aa77-cf9642883610")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivity(this);
    }

}
