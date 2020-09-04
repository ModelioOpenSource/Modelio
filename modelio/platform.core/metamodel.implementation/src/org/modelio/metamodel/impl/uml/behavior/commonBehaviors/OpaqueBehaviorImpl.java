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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.OpaqueBehaviorData;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0041b806-c4bf-1fd8-97fe-001ec947cd2a")
public class OpaqueBehaviorImpl extends BehaviorImpl implements OpaqueBehavior {
    @objid ("8e1c6a30-2ed0-459f-b43c-2d0ca905fb7d")
    @Override
    public String getBody() {
        return (String) getAttVal(((OpaqueBehaviorSmClass)getClassOf()).getBodyAtt());
    }

    @objid ("6b0bd6ff-6fd0-45aa-87f3-8da360e30487")
    @Override
    public void setBody(String value) {
        setAttVal(((OpaqueBehaviorSmClass)getClassOf()).getBodyAtt(), value);
    }

    @objid ("be759517-ed2a-4c29-9368-0b3c720e7133")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("a8abd4d2-ea1e-475f-a941-10b477c0d2ed")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("5d959a5f-08d8-419d-8ba6-834f4fb29ee2")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitOpaqueBehavior(this);
    }

}
