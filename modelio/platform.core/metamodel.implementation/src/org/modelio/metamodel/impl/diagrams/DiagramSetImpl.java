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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.diagrams.DiagramSetData;
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
    @objid ("6eb1588f-6487-4e49-918b-3d8723380591")
    @Override
    public EList<DiagramSet> getSub() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getSubDep());
    }

    @objid ("f9150837-543d-45f9-bd67-1b31cab8f61b")
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

    @objid ("066d1472-9661-4ebb-87bf-541012d7502a")
    @Override
    public DiagramSet getParent() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getParentDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("eb1f514c-03a6-40fc-a902-5ef087be4048")
    @Override
    public void setParent(DiagramSet value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("fa468b2f-507f-411a-a675-af4d452aa7f0")
    @Override
    public EList<AbstractDiagram> getReferencedDiagram() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getReferencedDiagramDep());
    }

    @objid ("2b90c69c-9f35-4319-a543-bed1d1c42a07")
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

    @objid ("2f89258b-d6f3-4032-a719-3bec7aefbeb2")
    @Override
    public AbstractProject getOwner() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof AbstractProject)? (AbstractProject)obj : null;
    }

    @objid ("4144ffc4-4af9-44d4-bcc1-561fa0debe41")
    @Override
    public void setOwner(AbstractProject value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("c252c9c9-9a9d-45da-bd85-73f4cd396cee")
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

    @objid ("94da7f20-cd4a-41a0-bac8-71cfa858d2ef")
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

    @objid ("a1c05859-2281-4668-b1b8-d6a973b6f8ab")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDiagramSet(this);
    }

}
