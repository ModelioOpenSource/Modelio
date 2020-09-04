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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.RequiredInterfaceData;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001b9b4e-c4bf-1fd8-97fe-001ec947cd2a")
public class RequiredInterfaceImpl extends UmlModelElementImpl implements RequiredInterface {
    @objid ("571e733f-2799-4f40-b589-bf3912835145")
    @Override
    public EList<Interface> getRequiredElement() {
        return new SmList<>(this, ((RequiredInterfaceSmClass)getClassOf()).getRequiredElementDep());
    }

    @objid ("12e31d45-9e7b-4400-8031-5d1a66e7bbb4")
    @Override
    public <T extends Interface> List<T> getRequiredElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Interface element : getRequiredElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("582929cd-c303-4b2c-a825-a13431c9dc8a")
    @Override
    public EList<LinkEnd> getProvider() {
        return new SmList<>(this, ((RequiredInterfaceSmClass)getClassOf()).getProviderDep());
    }

    @objid ("3fdca6b0-2a9e-4aef-bdc2-be9ad705aaa9")
    @Override
    public <T extends LinkEnd> List<T> getProvider(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getProvider()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d0eeaf28-df31-4066-b007-a5f4c4aad396")
    @Override
    public Port getRequiring() {
        Object obj = getDepVal(((RequiredInterfaceSmClass)getClassOf()).getRequiringDep());
        return (obj instanceof Port)? (Port)obj : null;
    }

    @objid ("80d3c5f4-5a8d-41f3-aada-9879874e09ff")
    @Override
    public void setRequiring(Port value) {
        appendDepVal(((RequiredInterfaceSmClass)getClassOf()).getRequiringDep(), (SmObjectImpl)value);
    }

    @objid ("0ebd0d6b-58f4-4a80-9e41-06159a1034c9")
    @Override
    public EList<NaryLinkEnd> getNaryProvider() {
        return new SmList<>(this, ((RequiredInterfaceSmClass)getClassOf()).getNaryProviderDep());
    }

    @objid ("0a2ae108-a446-476a-9b7f-79dac0a8b0ec")
    @Override
    public <T extends NaryLinkEnd> List<T> getNaryProvider(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryLinkEnd element : getNaryProvider()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7566a2dd-8ec7-4e62-b8dd-6dc5b6811402")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Requiring
        obj = (SmObjectImpl)this.getDepVal(((RequiredInterfaceSmClass)getClassOf()).getRequiringDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("af91c709-6fcb-49c3-9756-418c94fb7517")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Requiring
        dep = ((RequiredInterfaceSmClass)getClassOf()).getRequiringDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a2e7e47b-55f6-4663-b654-9588c9e9ea05")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitRequiredInterface(this);
    }

}
