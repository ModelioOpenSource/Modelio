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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0086b028-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ElementImpl extends SmObjectImpl implements Element {
    @objid ("0046c094-ee6e-1fd9-a292-001ec947cd2a")
    @Override
    public void setName(String name) {
        // do nothing. Element has no name
    }

    @objid ("963131e0-bb91-48a0-b26d-c7e0ffcc6d64")
    @Override
    public EList<AbstractDiagram> getDiagramElement() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getDiagramElementDep());
    }

    @objid ("68f1c61c-1550-4327-acfa-1b8b31440e4e")
    @Override
    public <T extends AbstractDiagram> List<T> getDiagramElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AbstractDiagram element : getDiagramElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("25d2b1b6-a82a-4cda-8e90-9b21022b9fd5")
    @Override
    public EList<QueryDefinition> getAddedToQuery() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getAddedToQueryDep());
    }

    @objid ("78627b50-4d0c-4225-8f33-71c496b4f5ca")
    @Override
    public <T extends QueryDefinition> List<T> getAddedToQuery(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final QueryDefinition element : getAddedToQuery()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0fce0aaf-d818-4d0b-8929-f8f6032952bd")
    @Override
    public EList<ImpactLink> getCausedImpact() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getCausedImpactDep());
    }

    @objid ("9d21a858-17f2-4260-9d8a-4b4de0a3288a")
    @Override
    public <T extends ImpactLink> List<T> getCausedImpact(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ImpactLink element : getCausedImpact()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("43ca1280-50e8-4d58-8dd4-759c6d3ca0a6")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return null;
    }

    @objid ("fc29f270-a942-42a6-ad5d-1875cae9d199")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return null;
    }

    @objid ("4c1e0363-4fad-40c0-bc46-92cf90ea0018")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IInfrastructureVisitor)
          return accept((IInfrastructureVisitor)v);
        else
          return null;
    }

    @objid ("653d78bc-5d14-4ee3-b72d-394f259c44b0")
    public Object accept(IInfrastructureVisitor v) {
        return v.visitElement(this);
    }

}
