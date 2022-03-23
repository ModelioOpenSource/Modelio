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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0089574c-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelTreeImpl extends UmlModelElementImpl implements ModelTree {
    @objid ("f6b63c9a-d487-4cfe-bae9-55827aca00d4")
    @Override
    public ModelTree getOwner() {
        Object obj = getDepVal(((ModelTreeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelTree)? (ModelTree)obj : null;
    }

    @objid ("61953c5b-06e2-4dfb-a9ab-ebd87fc6a466")
    @Override
    public void setOwner(ModelTree value) {
        appendDepVal(((ModelTreeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("8e36cf6a-53a6-473b-8f7d-ab35eb6dad6e")
    @Override
    public EList<ModelTree> getOwnedElement() {
        return new SmList<>(this, ((ModelTreeSmClass)getClassOf()).getOwnedElementDep());
    }

    @objid ("6f38ba38-fcb6-4500-a461-d97d25246450")
    @Override
    public <T extends ModelTree> List<T> getOwnedElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModelTree element : getOwnedElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("99f7188e-6109-4808-a7ea-b2d58a0f7c29")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ModelTreeSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("591b1049-5ad4-4772-8c5e-d402fd00d27a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ModelTreeSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e81e4fbc-3fe5-49c6-a8c9-11bcd438d5f6")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitModelTree(this);
    }

}
