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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.informationFlow.DataFlowData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00648264-c4bf-1fd8-97fe-001ec947cd2a")
public class DataFlowImpl extends UmlModelElementImpl implements DataFlow {
    @objid ("92a0f9bf-a768-448a-9a49-2da7629f5cc1")
    @Override
    public NameSpace getDestination() {
        Object obj = getDepVal(((DataFlowSmClass)getClassOf()).getDestinationDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("555c002b-6a37-4dbf-add2-73ca6ac812e5")
    @Override
    public void setDestination(NameSpace value) {
        appendDepVal(((DataFlowSmClass)getClassOf()).getDestinationDep(), (SmObjectImpl)value);
    }

    @objid ("05df5155-bacd-40f2-974a-705661639c67")
    @Override
    public NameSpace getOrigin() {
        Object obj = getDepVal(((DataFlowSmClass)getClassOf()).getOriginDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("65595356-be5e-4196-97ed-4b42e57a8fcf")
    @Override
    public void setOrigin(NameSpace value) {
        appendDepVal(((DataFlowSmClass)getClassOf()).getOriginDep(), (SmObjectImpl)value);
    }

    @objid ("86c7bf9f-75bc-486f-b432-16d238f2ee4d")
    @Override
    public NameSpace getOwner() {
        Object obj = getDepVal(((DataFlowSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("d5fab4b7-cf67-4a3d-97e1-45551e4c7c0d")
    @Override
    public void setOwner(NameSpace value) {
        appendDepVal(((DataFlowSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("2ae6eb68-cd22-4b20-8e34-fc69c7cb2c05")
    @Override
    public Signal getSModel() {
        Object obj = getDepVal(((DataFlowSmClass)getClassOf()).getSModelDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("08ff7de2-6008-4da6-be27-cd5faf3b5a0c")
    @Override
    public void setSModel(Signal value) {
        appendDepVal(((DataFlowSmClass)getClassOf()).getSModelDep(), (SmObjectImpl)value);
    }

    @objid ("309457ec-b765-4abb-a3c6-ac96834dcac3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((DataFlowSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("c6a132c8-97bb-49d9-b853-8134b093fa39")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((DataFlowSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("58e2cf3b-12da-4875-9195-b3c50fc911ba")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitDataFlow(this);
    }

}
