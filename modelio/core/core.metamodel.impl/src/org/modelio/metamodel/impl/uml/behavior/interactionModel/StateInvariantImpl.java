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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.StateInvariantData;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("004bb34c-c4bf-1fd8-97fe-001ec947cd2a")
public class StateInvariantImpl extends OccurrenceSpecificationImpl implements StateInvariant {
    @objid ("12f58f59-0bff-4f85-be96-712514c4f547")
    @Override
    public String getBody() {
        return (String) getAttVal(((StateInvariantSmClass)getClassOf()).getBodyAtt());
    }

    @objid ("6a287b68-f3f7-4a49-a99a-bf8a06504345")
    @Override
    public void setBody(String value) {
        setAttVal(((StateInvariantSmClass)getClassOf()).getBodyAtt(), value);
    }

    @objid ("e1607d6a-2b39-488f-9567-161ef67001f7")
    @Override
    public int getEndLineNumber() {
        return (Integer) getAttVal(((StateInvariantSmClass)getClassOf()).getEndLineNumberAtt());
    }

    @objid ("71802fd8-d558-4193-acf2-64c1b0a0f8aa")
    @Override
    public void setEndLineNumber(int value) {
        setAttVal(((StateInvariantSmClass)getClassOf()).getEndLineNumberAtt(), value);
    }

    @objid ("f5e5e3f2-9760-483d-b957-868c06f5eacf")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("b2609c34-17a8-4966-9f49-6a9e72b3c83d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("c8c1ffd2-c63d-43da-bd32-ac3659831f9d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitStateInvariant(this);
    }

}
