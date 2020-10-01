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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.impl.bpmn.activities.BpmnServiceTaskData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00833fec-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnServiceTaskImpl extends BpmnTaskImpl implements BpmnServiceTask {
    @objid ("13a5d058-1e49-4dce-9567-d2e40a2bdde8")
    @Override
    public String getImplementation() {
        return (String) getAttVal(((BpmnServiceTaskSmClass)getClassOf()).getImplementationAtt());
    }

    @objid ("c5d1d5e7-a627-479c-9f5d-260a0696fb8b")
    @Override
    public void setImplementation(String value) {
        setAttVal(((BpmnServiceTaskSmClass)getClassOf()).getImplementationAtt(), value);
    }

    @objid ("43c56ea2-3388-4fe1-b912-6ba7660d2a21")
    @Override
    public BpmnOperation getOperationRef() {
        Object obj = getDepVal(((BpmnServiceTaskSmClass)getClassOf()).getOperationRefDep());
        return (obj instanceof BpmnOperation)? (BpmnOperation)obj : null;
    }

    @objid ("ebf78d99-2b21-4638-99c5-c32e68de1e3d")
    @Override
    public void setOperationRef(BpmnOperation value) {
        appendDepVal(((BpmnServiceTaskSmClass)getClassOf()).getOperationRefDep(), (SmObjectImpl)value);
    }

    @objid ("f6ab24f1-5505-49ea-a039-ca8de89eaf7d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("968956d5-34b1-4a9e-a58e-574f8d471f90")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("d4b3ca2d-dd81-4c1f-8ab3-b030fd8b4a94")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnServiceTask(this);
    }

}
