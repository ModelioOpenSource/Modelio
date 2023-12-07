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
    @objid ("358ab473-3c41-4735-8988-4e42f3509907")
    @Override
    public EList<DiagramSet> getSub() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getSubDep());
    }

    @objid ("fbe10009-81f8-4fd0-a211-1562c6f8b1da")
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

    @objid ("7d98f8c0-ef44-4e2a-9dc8-dce1b90bef6c")
    @Override
    public DiagramSet getParent() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getParentDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("8a7eb361-0d5d-4f20-9d54-0cf359402e30")
    @Override
    public void setParent(DiagramSet value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("21839514-e3f5-4df4-82ed-1893779e66af")
    @Override
    public EList<AbstractDiagram> getReferencedDiagram() {
        return new SmList<>(this, ((DiagramSetSmClass)getClassOf()).getReferencedDiagramDep());
    }

    @objid ("1ba250fc-5f59-410e-a5ab-0d5099848279")
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

    @objid ("8a7111b7-86c8-4f4a-951f-b3fec71a3c39")
    @Override
    public AbstractProject getOwner() {
        Object obj = getDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof AbstractProject)? (AbstractProject)obj : null;
    }

    @objid ("14d4c02a-2527-4ba5-a3a6-9ebd167565b1")
    @Override
    public void setOwner(AbstractProject value) {
        appendDepVal(((DiagramSetSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("d8c5dc1d-134d-421a-a958-1b7a12309668")
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

    @objid ("e031053f-b762-4509-bcc1-68c0f98b9836")
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

    @objid ("a3fa99a2-4e3d-403f-86de-2517aed98199")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDiagramSet(this);
    }

}
