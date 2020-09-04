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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0026b060-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityActionImpl extends ActivityNodeImpl implements ActivityAction {
    @objid ("387e8a45-5b83-449f-9efe-1ddf8aeebeb4")
    @Override
    public boolean isIsMultipleInstance() {
        return (Boolean) getAttVal(((ActivityActionSmClass)getClassOf()).getIsMultipleInstanceAtt());
    }

    @objid ("6e7fc0d9-1fb7-4dcc-9472-3e13b7f0a250")
    @Override
    public void setIsMultipleInstance(boolean value) {
        setAttVal(((ActivityActionSmClass)getClassOf()).getIsMultipleInstanceAtt(), value);
    }

    @objid ("1f00caec-e60b-47b1-aa8a-8a7fb1d07396")
    @Override
    public boolean isIsCompensation() {
        return (Boolean) getAttVal(((ActivityActionSmClass)getClassOf()).getIsCompensationAtt());
    }

    @objid ("c2040eaf-8ede-43ea-b4e3-1d40ca07c6c7")
    @Override
    public void setIsCompensation(boolean value) {
        setAttVal(((ActivityActionSmClass)getClassOf()).getIsCompensationAtt(), value);
    }

    @objid ("63275adc-0903-4284-a62d-8058b5a8c7f1")
    @Override
    public EList<OutputPin> getOutput() {
        return new SmList<>(this, ((ActivityActionSmClass)getClassOf()).getOutputDep());
    }

    @objid ("2002879c-db58-4078-9832-03f2370a1694")
    @Override
    public <T extends OutputPin> List<T> getOutput(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final OutputPin element : getOutput()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8d898b42-ea14-4095-83be-450a4f5dc6f6")
    @Override
    public EList<InputPin> getInput() {
        return new SmList<>(this, ((ActivityActionSmClass)getClassOf()).getInputDep());
    }

    @objid ("d4e6038b-0381-4ac8-8c27-18b96baf7d84")
    @Override
    public <T extends InputPin> List<T> getInput(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InputPin element : getInput()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("095af958-dc47-4d0a-89fb-3805b71e4e70")
    @Override
    public EList<ExceptionHandler> getHandler() {
        return new SmList<>(this, ((ActivityActionSmClass)getClassOf()).getHandlerDep());
    }

    @objid ("11ce9f41-6c9b-47af-8bb7-eefdb9d12a35")
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

    @objid ("e8f7dbf9-53e9-4f35-b3d4-9b668895d39f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("5f93b34f-1284-4f11-ba65-95555cb3ab3c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8f29206e-b170-474a-9297-61a45d42e807")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityAction(this);
    }

}
