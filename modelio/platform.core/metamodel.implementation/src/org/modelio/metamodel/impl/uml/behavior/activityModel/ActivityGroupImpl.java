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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00283d2c-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityGroupImpl extends UmlModelElementImpl implements ActivityGroup {
    @objid ("8d8033ab-8f1c-4a15-ace1-daf80b55c2cf")
    @Override
    public Activity getInActivity() {
        Object obj = getDepVal(((ActivityGroupSmClass)getClassOf()).getInActivityDep());
        return (obj instanceof Activity)? (Activity)obj : null;
    }

    @objid ("1de272bb-2e9e-4201-9d36-b61a72c08e57")
    @Override
    public void setInActivity(Activity value) {
        appendDepVal(((ActivityGroupSmClass)getClassOf()).getInActivityDep(), (SmObjectImpl)value);
    }

    @objid ("0fc98a5e-eed3-4c3e-84d8-57f023821b43")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // InActivity
        obj = (SmObjectImpl)this.getDepVal(((ActivityGroupSmClass)getClassOf()).getInActivityDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("0d379e2d-d177-481a-a3ef-1f01a85ed5fd")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // InActivity
        dep = ((ActivityGroupSmClass)getClassOf()).getInActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("6c912441-5b4c-43ae-9d10-4caf21e724a3")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityGroup(this);
    }

}
