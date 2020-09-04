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
import org.modelio.metamodel.impl.uml.behavior.activityModel.OpaqueActionData;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("003a3022-c4bf-1fd8-97fe-001ec947cd2a")
public class OpaqueActionImpl extends ActivityActionImpl implements OpaqueAction {
    @objid ("9c207220-6ae5-46d8-b03a-5d349ca16fa4")
    @Override
    public String getBody() {
        return (String) getAttVal(((OpaqueActionSmClass)getClassOf()).getBodyAtt());
    }

    @objid ("e8196e0c-898e-45ca-91b4-a55e7aad437b")
    @Override
    public void setBody(String value) {
        setAttVal(((OpaqueActionSmClass)getClassOf()).getBodyAtt(), value);
    }

    @objid ("46a4a69c-a2da-4eb4-a41d-ab35c6494352")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("f6430a7e-1ae4-40ec-8c9e-545c037af44c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("31b577df-5a9c-4bea-ba3e-4c8ad9a313ed")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitOpaqueAction(this);
    }

}
