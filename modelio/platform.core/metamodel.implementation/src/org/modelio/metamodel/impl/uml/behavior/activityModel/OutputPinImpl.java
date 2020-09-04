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
import org.modelio.metamodel.impl.uml.behavior.activityModel.OutputPinData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("003ae756-c4bf-1fd8-97fe-001ec947cd2a")
public class OutputPinImpl extends PinImpl implements OutputPin {
    @objid ("bdc86ba3-d8bc-4427-9cab-3059e8961171")
    @Override
    public ActivityAction getOutputing() {
        Object obj = getDepVal(((OutputPinSmClass)getClassOf()).getOutputingDep());
        return (obj instanceof ActivityAction)? (ActivityAction)obj : null;
    }

    @objid ("d20a19b5-ed18-41a8-98b3-a1eea6b48595")
    @Override
    public void setOutputing(ActivityAction value) {
        appendDepVal(((OutputPinSmClass)getClassOf()).getOutputingDep(), (SmObjectImpl)value);
    }

    @objid ("d5f5881f-b193-466d-8679-ea071791eef3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Outputing
        obj = (SmObjectImpl)this.getDepVal(((OutputPinSmClass)getClassOf()).getOutputingDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("f1e47e25-4ab0-4c10-898d-d12743fccf62")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Outputing
        dep = ((OutputPinSmClass)getClassOf()).getOutputingDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("6eb2338c-9c14-40f7-aa06-c8d85f1feaec")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitOutputPin(this);
    }

}
