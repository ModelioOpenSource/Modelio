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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InputPinData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00353996-c4bf-1fd8-97fe-001ec947cd2a")
public class InputPinImpl extends PinImpl implements InputPin {
    @objid ("0bc47ca2-f538-452a-86f7-45e40d1b7249")
    @Override
    public boolean isIsSelf() {
        return (Boolean) getAttVal(((InputPinSmClass)getClassOf()).getIsSelfAtt());
    }

    @objid ("571110a7-458d-4d10-8494-2cee1228215b")
    @Override
    public void setIsSelf(boolean value) {
        setAttVal(((InputPinSmClass)getClassOf()).getIsSelfAtt(), value);
    }

    @objid ("84e4702c-ead6-4444-a8a8-cbc0cc6fa288")
    @Override
    public EList<ExceptionHandler> getHandler() {
        return new SmList<>(this, ((InputPinSmClass)getClassOf()).getHandlerDep());
    }

    @objid ("13e83a9f-6adc-4082-9bb9-b025c6029ec5")
    @Override
    public <T extends ExceptionHandler> List<T> getHandler(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExceptionHandler element : getHandler()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("01da1ba4-af63-4e10-8443-8e77baddf61e")
    @Override
    public ActivityAction getInputing() {
        Object obj = getDepVal(((InputPinSmClass)getClassOf()).getInputingDep());
        return (obj instanceof ActivityAction)? (ActivityAction)obj : null;
    }

    @objid ("2dffd139-dddd-4fd4-af31-ba4a7192643c")
    @Override
    public void setInputing(ActivityAction value) {
        appendDepVal(((InputPinSmClass)getClassOf()).getInputingDep(), (SmObjectImpl)value);
    }

    @objid ("9e3376ab-401c-4f9e-9703-9ed18e58290e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Inputing
        obj = (SmObjectImpl)this.getDepVal(((InputPinSmClass)getClassOf()).getInputingDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("98998f46-a682-499a-93a8-75dcf4832a79")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Inputing
        dep = ((InputPinSmClass)getClassOf()).getInputingDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("291f4ed2-0799-4b5a-bbcb-863a8cab24a6")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInputPin(this);
    }

}
