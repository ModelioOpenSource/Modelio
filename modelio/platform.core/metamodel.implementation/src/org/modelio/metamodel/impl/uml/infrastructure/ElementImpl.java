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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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

    @objid ("0c688947-c3e3-4375-9906-55f4513c4964")
    @Override
    public EList<AbstractDiagram> getDiagramElement() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getDiagramElementDep());
    }

    @objid ("4200a6dd-3000-4395-a48c-51130e68ec75")
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

    @objid ("876ef5c8-0e47-4102-a3f1-b3769eb1d71e")
    @Override
    public EList<QueryDefinition> getAddedToQuery() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getAddedToQueryDep());
    }

    @objid ("ebc3fb67-5463-42b3-a558-db0b7821dc21")
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

    @objid ("6f3d1667-9735-436a-b3b2-122a1eb51ca4")
    @Override
    public EList<ImpactLink> getCausedImpact() {
        return new SmList<>(this, ((ElementSmClass)getClassOf()).getCausedImpactDep());
    }

    @objid ("e61235b4-c72c-4b4e-9710-ba6cfacb1531")
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

    @objid ("2c0a9b5d-862c-4d40-99a9-929412435a25")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return null;
    }

    @objid ("9cf9eb34-e38f-4ff1-a0b3-b734056e9c87")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return null;
    }

    @objid ("f1a05d6e-2608-4c00-9608-3e0d997921a2")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IInfrastructureVisitor)
          return accept((IInfrastructureVisitor)v);
        else
          return null;
    }

    @objid ("38fb74c9-ecff-44ad-b123-5cd8b01f8d27")
    public Object accept(IInfrastructureVisitor v) {
        return v.visitElement(this);
    }

}
