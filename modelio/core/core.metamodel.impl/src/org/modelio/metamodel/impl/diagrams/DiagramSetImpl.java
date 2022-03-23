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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006e3d9a-c4bf-1fd8-97fe-001ec947cd2a")
public class DiagramSetImpl extends ModelElementImpl implements DiagramSet {
    @objid ("cff91786-c571-418c-8b3d-b50b25f938fb")
    @Override
    public EList<DiagramSet> getSub() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getSubDep());
    }

    @objid ("9c5b7559-e85a-4b5e-aa53-22f2840b2a95")
    @Override
    public <T extends DiagramSet> List<T> getSub(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DiagramSet element : getSub()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("5abfd184-b45c-406e-b461-705b4df1bd9d")
    @Override
    public DiagramSet getParent() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getParentDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("29368247-ae5b-4900-ac1d-4ec0912e80a5")
    @Override
    public void setParent(DiagramSet value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("8b08ab21-dd20-49d1-8678-d7f91ac82e71")
    @Override
    public EList<AbstractDiagram> getReferencedDiagram() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getReferencedDiagramDep());
    }

    @objid ("7146574a-41cb-4aab-ad03-e12e39993d80")
    @Override
    public <T extends AbstractDiagram> List<T> getReferencedDiagram(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AbstractDiagram element : getReferencedDiagram()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("4419e2eb-ad7d-43a3-920c-ff972ce42df2")
    @Override
    public AbstractProject getOwner() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof AbstractProject)? (AbstractProject)obj : null;
    }

    @objid ("4eff5ddd-a375-4ef4-a10c-236fe0498b20")
    @Override
    public void setOwner(AbstractProject value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("01b80d55-737d-41c6-a8d6-0d504568fc8f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Parent
        obj = (SmObjectImpl)this.getDepVal(((DiagramSetSmClass)getClassOf()).getParentDep());
        if (obj != null)
          return obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a801c390-c3bd-4499-bee5-d5d154af29e0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Parent
        dep = ((DiagramSetSmClass)getClassOf()).getParentDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Owner
        dep = ((DiagramSetSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e0a7c825-f144-45f7-ad83-53852bc058ea")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDiagramSet(this);
    }

}
