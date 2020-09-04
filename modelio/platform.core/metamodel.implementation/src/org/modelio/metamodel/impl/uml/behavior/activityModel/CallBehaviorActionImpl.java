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
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallBehaviorActionData;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002b2028-c4bf-1fd8-97fe-001ec947cd2a")
public class CallBehaviorActionImpl extends CallActionImpl implements CallBehaviorAction {
    @objid ("998750ba-9d82-4a83-8fd2-fe11d3ae25d0")
    @Override
    public Behavior getCalled() {
        Object obj = getDepVal(((CallBehaviorActionSmClass)getClassOf()).getCalledDep());
        return (obj instanceof Behavior)? (Behavior)obj : null;
    }

    @objid ("1a863485-9823-4cab-a85b-fef798f20ef6")
    @Override
    public void setCalled(Behavior value) {
        appendDepVal(((CallBehaviorActionSmClass)getClassOf()).getCalledDep(), (SmObjectImpl)value);
    }

    @objid ("9beb1b40-5843-45f4-ad4e-d36c62d62640")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("4f9c64aa-685c-48a1-bed3-f4e4cc293a31")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("d8d55603-2cec-48ac-ad35-a55552280e49")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCallBehaviorAction(this);
    }

}
