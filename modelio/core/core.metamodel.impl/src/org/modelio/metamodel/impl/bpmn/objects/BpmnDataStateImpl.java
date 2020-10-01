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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataStateData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00087bf4-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataStateImpl extends BpmnBaseElementImpl implements BpmnDataState {
    @objid ("dc878963-9af4-4af1-b99b-db4e01b8a07a")
    @Override
    public BpmnItemAwareElement getItem() {
        Object obj = getDepVal(((BpmnDataStateSmClass)getClassOf()).getItemDep());
        return (obj instanceof BpmnItemAwareElement)? (BpmnItemAwareElement)obj : null;
    }

    @objid ("f5e366af-b1dc-4019-ba03-d757e9cdcd38")
    @Override
    public void setItem(BpmnItemAwareElement value) {
        appendDepVal(((BpmnDataStateSmClass)getClassOf()).getItemDep(), (SmObjectImpl)value);
    }

    @objid ("90961a6c-de99-43fd-b27d-27510c95aa7e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Item
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataStateSmClass)getClassOf()).getItemDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("f0beb323-bab7-4cf7-8578-d04f2780b033")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Item
        dep = ((BpmnDataStateSmClass)getClassOf()).getItemDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("390e7360-84af-44de-a88e-37656a32635b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataState(this);
    }

}
