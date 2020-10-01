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
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterBindingData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000b59e6-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceParameterBindingImpl extends BpmnBaseElementImpl implements BpmnResourceParameterBinding {
    @objid ("ba47c3c2-c5ea-49a7-8f99-d75af1e10af9")
    @Override
    public String getExpression() {
        return (String) getAttVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getExpressionAtt());
    }

    @objid ("5b5b9a6e-0c3f-4a45-8cb4-b98ec83703ca")
    @Override
    public void setExpression(String value) {
        setAttVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getExpressionAtt(), value);
    }

    @objid ("84041fc4-3cd5-4729-95e3-79d421ef14a9")
    @Override
    public BpmnResourceRole getResourceRole() {
        Object obj = getDepVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getResourceRoleDep());
        return (obj instanceof BpmnResourceRole)? (BpmnResourceRole)obj : null;
    }

    @objid ("51109529-c6df-4847-93ac-539870b374da")
    @Override
    public void setResourceRole(BpmnResourceRole value) {
        appendDepVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getResourceRoleDep(), (SmObjectImpl)value);
    }

    @objid ("bfa50819-3621-4115-890a-4b1937deab41")
    @Override
    public BpmnResourceParameter getParameterRef() {
        Object obj = getDepVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getParameterRefDep());
        return (obj instanceof BpmnResourceParameter)? (BpmnResourceParameter)obj : null;
    }

    @objid ("5313db3f-e5cb-4454-95ba-9db29281aa95")
    @Override
    public void setParameterRef(BpmnResourceParameter value) {
        appendDepVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getParameterRefDep(), (SmObjectImpl)value);
    }

    @objid ("842c955c-5209-40eb-bec0-ab2bf8064bbc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ResourceRole
        obj = (SmObjectImpl)this.getDepVal(((BpmnResourceParameterBindingSmClass)getClassOf()).getResourceRoleDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("3bf8be00-d89c-407d-87dc-028f9434cf3d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ResourceRole
        dep = ((BpmnResourceParameterBindingSmClass)getClassOf()).getResourceRoleDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e77e4ec3-88fa-4fbc-9248-4356c2ee7a2b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnResourceParameterBinding(this);
    }

}
