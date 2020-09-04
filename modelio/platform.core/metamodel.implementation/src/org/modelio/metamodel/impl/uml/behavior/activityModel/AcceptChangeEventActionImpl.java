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
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptChangeEventActionData;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0024ba8a-c4bf-1fd8-97fe-001ec947cd2a")
public class AcceptChangeEventActionImpl extends ActivityActionImpl implements AcceptChangeEventAction {
    @objid ("ab4b465d-3e54-4ec6-a147-b6b4baaf866c")
    @Override
    public String getChangeExpresion() {
        return (String) getAttVal(((AcceptChangeEventActionSmClass)getClassOf()).getChangeExpresionAtt());
    }

    @objid ("553a4867-39f6-4c36-a4ff-93f3715bf080")
    @Override
    public void setChangeExpresion(String value) {
        setAttVal(((AcceptChangeEventActionSmClass)getClassOf()).getChangeExpresionAtt(), value);
    }

    @objid ("73cebfd7-af6e-4d17-9169-b1f3b76f20b9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("2ec14e5c-3518-4a9d-9b32-0f53f5bb53bc")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("a239bfa7-a239-40e4-8e05-6be7d63fd10b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAcceptChangeEventAction(this);
    }

}
