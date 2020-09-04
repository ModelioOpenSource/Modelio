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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.RaisedExceptionData;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001ab350-c4bf-1fd8-97fe-001ec947cd2a")
public class RaisedExceptionImpl extends UmlModelElementImpl implements RaisedException {
    @objid ("2eeb891b-21bd-4dc2-8490-c8842abe9099")
    @Override
    public Classifier getThrownType() {
        Object obj = getDepVal(((RaisedExceptionSmClass)getClassOf()).getThrownTypeDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("752c38b8-7d58-4f3d-a0ff-033f5195fd78")
    @Override
    public void setThrownType(Classifier value) {
        appendDepVal(((RaisedExceptionSmClass)getClassOf()).getThrownTypeDep(), (SmObjectImpl)value);
    }

    @objid ("9bb3f9aa-0847-4a62-b66a-11c1a863e682")
    @Override
    public Operation getThrower() {
        Object obj = getDepVal(((RaisedExceptionSmClass)getClassOf()).getThrowerDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("e65d63a3-4574-4d08-a67a-5be4ba1edece")
    @Override
    public void setThrower(Operation value) {
        appendDepVal(((RaisedExceptionSmClass)getClassOf()).getThrowerDep(), (SmObjectImpl)value);
    }

    @objid ("72f008b7-be20-4063-aa24-34cec938499b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Thrower
        obj = (SmObjectImpl)this.getDepVal(((RaisedExceptionSmClass)getClassOf()).getThrowerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("de9ad780-1429-4cb0-95d3-427a510c5fb7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Thrower
        dep = ((RaisedExceptionSmClass)getClassOf()).getThrowerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("1d55b7ec-f84d-4815-a450-fee7498b56b4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitRaisedException(this);
    }

}
