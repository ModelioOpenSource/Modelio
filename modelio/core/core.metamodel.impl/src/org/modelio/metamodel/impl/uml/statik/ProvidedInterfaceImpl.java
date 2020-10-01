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
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceData;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0019d6a6-c4bf-1fd8-97fe-001ec947cd2a")
public class ProvidedInterfaceImpl extends UmlModelElementImpl implements ProvidedInterface {
    @objid ("c38781ed-b9eb-46b8-bd05-ff6cc333a7f1")
    @Override
    public EList<Interface> getProvidedElement() {
        return new SmList<>(this, ((ProvidedInterfaceSmClass)getClassOf()).getProvidedElementDep());
    }

    @objid ("1d473598-e609-48ea-a6f0-2dbd32057810")
    @Override
    public <T extends Interface> List<T> getProvidedElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Interface element : getProvidedElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("135fbae7-033b-41ca-adc0-9b3c6725df98")
    @Override
    public Port getProviding() {
        Object obj = getDepVal(((ProvidedInterfaceSmClass)getClassOf()).getProvidingDep());
        return (obj instanceof Port)? (Port)obj : null;
    }

    @objid ("e5cd886e-dfe7-41dd-85fb-932b3f264499")
    @Override
    public void setProviding(Port value) {
        appendDepVal(((ProvidedInterfaceSmClass)getClassOf()).getProvidingDep(), (SmObjectImpl)value);
    }

    @objid ("907484d8-ac8f-47cd-ad53-b97fa7962090")
    @Override
    public EList<LinkEnd> getConsumer() {
        return new SmList<>(this, ((ProvidedInterfaceSmClass)getClassOf()).getConsumerDep());
    }

    @objid ("34eb87d3-e046-49ee-92e7-00ebff8c3629")
    @Override
    public <T extends LinkEnd> List<T> getConsumer(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getConsumer()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ef4d5463-cd8a-4f3e-a243-98d9ee64d525")
    @Override
    public EList<NaryLinkEnd> getNaryConsumer() {
        return new SmList<>(this, ((ProvidedInterfaceSmClass)getClassOf()).getNaryConsumerDep());
    }

    @objid ("cb996184-d5c7-493c-a86d-a7d898d064e3")
    @Override
    public <T extends NaryLinkEnd> List<T> getNaryConsumer(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryLinkEnd element : getNaryConsumer()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("97487051-9404-4c9d-91ae-a8c786a8b273")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Providing
        obj = (SmObjectImpl)this.getDepVal(((ProvidedInterfaceSmClass)getClassOf()).getProvidingDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("b30d7f14-4952-42fe-b73a-6da120c0f427")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Providing
        dep = ((ProvidedInterfaceSmClass)getClassOf()).getProvidingDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("48321ad1-929f-4b7c-b68a-a90854da8923")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitProvidedInterface(this);
    }

}
