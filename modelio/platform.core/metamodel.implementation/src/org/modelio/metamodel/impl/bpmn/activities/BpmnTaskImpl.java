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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0084e734-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnTaskImpl extends BpmnActivityImpl implements BpmnTask {
    @objid ("6ccadc8e-fc1a-4fc6-b443-79884445baf1")
    @Override
    public boolean isIsGlobal() {
        return (Boolean) getAttVal(((BpmnTaskSmClass)getClassOf()).getIsGlobalAtt());
    }

    @objid ("a2ba72d3-f11f-4230-a4d5-1ead51acfecc")
    @Override
    public void setIsGlobal(boolean value) {
        setAttVal(((BpmnTaskSmClass)getClassOf()).getIsGlobalAtt(), value);
    }

    @objid ("aae558f6-d2ea-48de-9d13-87c57ebf47b7")
    @Override
    public EList<BpmnCallActivity> getCaller() {
        return new SmList<>(this, ((BpmnTaskSmClass)getClassOf()).getCallerDep());
    }

    @objid ("cce3582e-c5ac-4152-a1ed-b7e22540f5ca")
    @Override
    public <T extends BpmnCallActivity> List<T> getCaller(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnCallActivity element : getCaller()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5c36b9ce-37b8-4c8a-b416-c79c8792e983")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0b4c374b-e102-4d4b-a13c-2737781c9c5b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("a3728a6d-7002-41e5-8960-d127912e3fd2")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnTask(this);
    }

}
