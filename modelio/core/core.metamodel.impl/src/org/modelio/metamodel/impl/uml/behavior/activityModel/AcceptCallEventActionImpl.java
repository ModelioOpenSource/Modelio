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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptCallEventActionData;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00244136-c4bf-1fd8-97fe-001ec947cd2a")
public class AcceptCallEventActionImpl extends ActivityActionImpl implements AcceptCallEventAction {
    @objid ("ade43634-48af-4a77-b6f6-c29eba0ccd5b")
    @Override
    public Operation getCalled() {
        Object obj = getDepVal(((AcceptCallEventActionSmClass)getClassOf()).getCalledDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("b3686542-d165-48a3-94e8-9e7dd4320fae")
    @Override
    public void setCalled(Operation value) {
        appendDepVal(((AcceptCallEventActionSmClass)getClassOf()).getCalledDep(), (SmObjectImpl)value);
    }

    @objid ("e48bd0f3-508e-4022-b758-b499e696649e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("71e88cec-fd7b-413b-a748-38c9fcdc7cf6")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("f659a862-eca3-4b1f-a141-da8c88639410")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAcceptCallEventAction(this);
    }

}
