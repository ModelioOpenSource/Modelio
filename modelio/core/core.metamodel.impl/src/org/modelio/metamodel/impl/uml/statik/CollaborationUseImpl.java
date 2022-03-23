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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0004a754-c4bf-1fd8-97fe-001ec947cd2a")
public class CollaborationUseImpl extends UmlModelElementImpl implements CollaborationUse {
    @objid ("1338f7c8-10fb-4bc4-aca7-b0b95e44829d")
    @Override
    public Collaboration getType() {
        Object obj = getDepVal(((CollaborationUseSmClass)getClassOf()).getTypeDep());
        return (obj instanceof Collaboration)? (Collaboration)obj : null;
    }

    @objid ("aabc465b-e02e-48dc-b5e8-f483852ac4b4")
    @Override
    public void setType(Collaboration value) {
        appendDepVal(((CollaborationUseSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("db8f27b4-299a-497c-8da0-3de5fee62e03")
    @Override
    public NameSpace getNRepresented() {
        Object obj = getDepVal(((CollaborationUseSmClass)getClassOf()).getNRepresentedDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("3b298c0d-6f32-46bd-b421-7ea5da4253f5")
    @Override
    public void setNRepresented(NameSpace value) {
        appendDepVal(((CollaborationUseSmClass)getClassOf()).getNRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("65853f24-452f-46fb-94cb-3df6b7db4a20")
    @Override
    public Operation getORepresented() {
        Object obj = getDepVal(((CollaborationUseSmClass)getClassOf()).getORepresentedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("cf23d55d-9033-4ed5-92a5-8ea17354d917")
    @Override
    public void setORepresented(Operation value) {
        appendDepVal(((CollaborationUseSmClass)getClassOf()).getORepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("50257c43-1d85-4ab2-ae0e-69a658a1d79a")
    @Override
    public EList<Binding> getRoleBinding() {
        return new SmList<>(this, ((CollaborationUseSmClass)getClassOf()).getRoleBindingDep());
    }

    @objid ("c1ece42a-64fc-404b-bfaf-05be7119b350")
    @Override
    public <T extends Binding> List<T> getRoleBinding(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Binding element : getRoleBinding()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("d79bf93d-3b0f-43e4-9157-233789c93282")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // NRepresented
        obj = (SmObjectImpl)this.getDepVal(((CollaborationUseSmClass)getClassOf()).getNRepresentedDep());
        if (obj != null)
          return obj;
        // ORepresented
        obj = (SmObjectImpl)this.getDepVal(((CollaborationUseSmClass)getClassOf()).getORepresentedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("7680ae3b-b33f-412a-82ba-a4cdc28f80ca")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // NRepresented
        dep = ((CollaborationUseSmClass)getClassOf()).getNRepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ORepresented
        dep = ((CollaborationUseSmClass)getClassOf()).getORepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("1b7a51d2-8e3f-499f-a50c-7ee9090b8bfd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCollaborationUse(this);
    }

}
