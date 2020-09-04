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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0030ab7e-c4bf-1fd8-97fe-001ec947cd2a")
public class ExceptionHandlerImpl extends UmlModelElementImpl implements ExceptionHandler {
    @objid ("f1b7126c-2f60-42bb-819c-69cf40babaae")
    @Override
    public String getGuard() {
        return (String) getAttVal(((ExceptionHandlerSmClass)getClassOf()).getGuardAtt());
    }

    @objid ("24fa05e3-2b87-4b81-99ef-1b6247c595cc")
    @Override
    public void setGuard(String value) {
        setAttVal(((ExceptionHandlerSmClass)getClassOf()).getGuardAtt(), value);
    }

    @objid ("5e8f8a88-8135-4a11-aea3-12d6783bebb6")
    @Override
    public String getWeight() {
        return (String) getAttVal(((ExceptionHandlerSmClass)getClassOf()).getWeightAtt());
    }

    @objid ("a0f0efa6-413c-4718-8295-ecda9c2cb13e")
    @Override
    public void setWeight(String value) {
        setAttVal(((ExceptionHandlerSmClass)getClassOf()).getWeightAtt(), value);
    }

    @objid ("20e873fd-2fba-48f5-a99b-98662ba7a174")
    @Override
    public ActivityAction getProtectedNode() {
        Object obj = getDepVal(((ExceptionHandlerSmClass)getClassOf()).getProtectedNodeDep());
        return (obj instanceof ActivityAction)? (ActivityAction)obj : null;
    }

    @objid ("b49ffe82-b567-43db-a80b-3fb7c7199572")
    @Override
    public void setProtectedNode(ActivityAction value) {
        appendDepVal(((ExceptionHandlerSmClass)getClassOf()).getProtectedNodeDep(), (SmObjectImpl)value);
    }

    @objid ("79c09bc1-0f82-4c64-a545-9969c1c22611")
    @Override
    public InputPin getExceptionInput() {
        Object obj = getDepVal(((ExceptionHandlerSmClass)getClassOf()).getExceptionInputDep());
        return (obj instanceof InputPin)? (InputPin)obj : null;
    }

    @objid ("0917b117-e40a-403f-879b-8c5a4cda1bca")
    @Override
    public void setExceptionInput(InputPin value) {
        appendDepVal(((ExceptionHandlerSmClass)getClassOf()).getExceptionInputDep(), (SmObjectImpl)value);
    }

    @objid ("530fbc39-44b3-4e25-9a05-45acda66a378")
    @Override
    public EList<GeneralClass> getExceptionType() {
        return new SmList<>(this, ((ExceptionHandlerSmClass)getClassOf()).getExceptionTypeDep());
    }

    @objid ("ff0c6cb6-a3a9-44fd-a4e6-5e11a4ada6b0")
    @Override
    public <T extends GeneralClass> List<T> getExceptionType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final GeneralClass element : getExceptionType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("63bb5a4e-9e3e-412a-b600-b2eb796c4b1d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ProtectedNode
        obj = (SmObjectImpl)this.getDepVal(((ExceptionHandlerSmClass)getClassOf()).getProtectedNodeDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ad18c14d-0ece-4849-8566-f5929af00ee6")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ProtectedNode
        dep = ((ExceptionHandlerSmClass)getClassOf()).getProtectedNodeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("fac2d58c-94e8-42e4-a2d9-e09802e660d5")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExceptionHandler(this);
    }

}
