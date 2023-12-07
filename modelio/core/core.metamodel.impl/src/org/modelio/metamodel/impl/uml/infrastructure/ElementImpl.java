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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
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

    @objid ("7ec8cbfd-0db2-4b76-ad49-fe3368a121ea")
    @Override
    public EList<AbstractDiagram> getDiagramElement() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getDiagramElementDep());
    }

    @objid ("948a2cbe-e572-4ac9-903f-665fa823fe65")
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

    @objid ("8ec0c221-b24b-4ab5-8b57-881dfd422e3a")
    @Override
    public EList<QueryDefinition> getAddedToQuery() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getAddedToQueryDep());
    }

    @objid ("d713078c-982c-4eca-bdeb-bcb08e19874a")
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

    @objid ("4e1907d3-497e-4979-8ba0-a460fa56b0fe")
    @Override
    public EList<ImpactLink> getCausedImpact() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getCausedImpactDep());
    }

    @objid ("71bf8331-56f8-4aed-9158-ddbd0c18875b")
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

    @objid ("e4fd7780-9fe0-4489-8ec8-73a3394aaf12")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return null;
    }

    @objid ("c55c1917-d481-41a4-bb11-3e6aecd329b1")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return null;
    }

    @objid ("0111d2fc-8927-471d-a25d-13e00a142455")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IInfrastructureVisitor)
          return accept((IInfrastructureVisitor)v);
        else
          return null;
    }

    @objid ("414ca81f-ad4f-4585-b444-96fead062977")
    public Object accept(IInfrastructureVisitor v) {
        return v.visitElement(this);
    }

}
