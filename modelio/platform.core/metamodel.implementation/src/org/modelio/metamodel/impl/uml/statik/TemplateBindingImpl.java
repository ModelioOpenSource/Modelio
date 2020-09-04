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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.TemplateBindingData;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001c7c58-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateBindingImpl extends UmlModelElementImpl implements TemplateBinding {
    @objid ("b96a9371-f29c-4b13-8a73-4273bc3cfb6f")
    @Override
    public EList<TemplateParameterSubstitution> getParameterSubstitution() {
        return new SmList<>(this, ((TemplateBindingSmClass)getClassOf()).getParameterSubstitutionDep());
    }

    @objid ("bd6a1da0-e93f-4193-83cd-efd9575a5dea")
    @Override
    public <T extends TemplateParameterSubstitution> List<T> getParameterSubstitution(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameterSubstitution element : getParameterSubstitution()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b64690c3-745c-4a2c-b4dc-f2dc9bafcdb9")
    @Override
    public Operation getBoundOperation() {
        Object obj = getDepVal(((TemplateBindingSmClass)getClassOf()).getBoundOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("7a99d2a4-e9e8-4aad-905b-b87fd5149fae")
    @Override
    public void setBoundOperation(Operation value) {
        appendDepVal(((TemplateBindingSmClass)getClassOf()).getBoundOperationDep(), (SmObjectImpl)value);
    }

    @objid ("28e65e20-cccf-4688-a013-4920666255dd")
    @Override
    public Operation getInstanciatedTemplateOperation() {
        Object obj = getDepVal(((TemplateBindingSmClass)getClassOf()).getInstanciatedTemplateOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("a3f00604-9527-4ded-b1a1-a43b32c74a47")
    @Override
    public void setInstanciatedTemplateOperation(Operation value) {
        appendDepVal(((TemplateBindingSmClass)getClassOf()).getInstanciatedTemplateOperationDep(), (SmObjectImpl)value);
    }

    @objid ("e8c4db6a-78bc-4ca0-afbc-2beaf3ce2ab7")
    @Override
    public NameSpace getInstanciatedTemplate() {
        Object obj = getDepVal(((TemplateBindingSmClass)getClassOf()).getInstanciatedTemplateDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("fa4547e6-17e0-4ef7-995f-56c28efe96c1")
    @Override
    public void setInstanciatedTemplate(NameSpace value) {
        appendDepVal(((TemplateBindingSmClass)getClassOf()).getInstanciatedTemplateDep(), (SmObjectImpl)value);
    }

    @objid ("6533a059-7080-4f5e-91ee-48a28e481e97")
    @Override
    public NameSpace getBoundElement() {
        Object obj = getDepVal(((TemplateBindingSmClass)getClassOf()).getBoundElementDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("14607d9c-8b58-415b-8ce2-f268583e8c07")
    @Override
    public void setBoundElement(NameSpace value) {
        appendDepVal(((TemplateBindingSmClass)getClassOf()).getBoundElementDep(), (SmObjectImpl)value);
    }

    @objid ("fd30f259-b77a-4746-af24-ccb495776fa7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // BoundOperation
        obj = (SmObjectImpl)this.getDepVal(((TemplateBindingSmClass)getClassOf()).getBoundOperationDep());
        if (obj != null)
          return obj;
        // BoundElement
        obj = (SmObjectImpl)this.getDepVal(((TemplateBindingSmClass)getClassOf()).getBoundElementDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("6a76fcfe-f4d7-4efd-be1c-b71d2267143c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // BoundOperation
        dep = ((TemplateBindingSmClass)getClassOf()).getBoundOperationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // BoundElement
        dep = ((TemplateBindingSmClass)getClassOf()).getBoundElementDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5d064fab-f296-4f87-9bd8-2201610531e1")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitTemplateBinding(this);
    }

}
