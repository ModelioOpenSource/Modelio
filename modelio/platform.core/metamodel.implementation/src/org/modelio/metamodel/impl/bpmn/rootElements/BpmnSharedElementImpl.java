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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("108579fe-cb43-4b9b-a43a-e968798023a6")
public class BpmnSharedElementImpl extends BpmnBaseElementImpl implements BpmnSharedElement {
    @objid ("06321ad2-0f68-456c-a7dd-50a9d552c7c1")
    @Override
    public BpmnSharedDefinitions getOwner() {
        Object obj = getDepVal(((BpmnSharedElementSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof BpmnSharedDefinitions)? (BpmnSharedDefinitions)obj : null;
    }

    @objid ("0c052371-d16f-4b7f-a587-5b73bcf02867")
    @Override
    public void setOwner(BpmnSharedDefinitions value) {
        appendDepVal(((BpmnSharedElementSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("02093528-256f-4748-9c78-4c111764e307")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BpmnSharedElementSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8612085c-9c3b-4425-8469-9432ab012837")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BpmnSharedElementSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("f90e4594-5ba9-4c72-88c1-4ec99c1d6048")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnSharedElement(this);
    }

}
