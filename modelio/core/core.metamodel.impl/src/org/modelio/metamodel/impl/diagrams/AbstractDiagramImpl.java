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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00675638-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class AbstractDiagramImpl extends ModelElementImpl implements AbstractDiagram {
    @objid ("8ae2528c-105f-40cb-bf7d-6773be793521")
    @Override
    public int getUiDataVersion() {
        return (Integer) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt());
    }

    @objid ("4d07594d-062c-44a6-b44d-3895df31db86")
    @Override
    public void setUiDataVersion(int value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt(), value);
    }

    @objid ("8f9cc3bc-ab5a-4f7f-9501-56e73edfe946")
    @Override
    public String getUiData() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt());
    }

    @objid ("a22edb86-907a-4944-bffd-f0511bd14a5e")
    @Override
    public void setUiData(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt(), value);
    }

    @objid ("de4e0dce-f801-4579-b33e-eddfd7a29791")
    @Override
    public String getPreviewData() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getPreviewDataAtt());
    }

    @objid ("7f945032-4615-4fd0-9e38-69f41f079476")
    @Override
    public void setPreviewData(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getPreviewDataAtt(), value);
    }

    @objid ("da80dc5d-b3e8-4057-a7bc-9dd13e61612a")
    @Override
    public EList<Element> getRepresented() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getRepresentedDep());
    }

    @objid ("4328bb5e-dd4d-412f-801f-df3d4b53851f")
    @Override
    public <T extends Element> List<T> getRepresented(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Element element : getRepresented()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("5be10f9b-a743-4360-9d18-ab213150a20e")
    @Override
    public EList<DiagramSet> getReferencingSet() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getReferencingSetDep());
    }

    @objid ("f09c5722-b552-4cef-8d1c-e3cdeac750cf")
    @Override
    public <T extends DiagramSet> List<T> getReferencingSet(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DiagramSet element : getReferencingSet()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("6381db55-6466-4455-9e4d-446abbdcace1")
    @Override
    public ModelElement getOrigin() {
        Object obj = getDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("148c41af-07f9-4b3c-955a-a931952844b2")
    @Override
    public void setOrigin(ModelElement value) {
        appendDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep(), (SmObjectImpl)value);
    }

    @objid ("d6deac0b-9a50-42a2-929b-d2049603dbf9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Origin
        obj = (SmObjectImpl)this.getDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a7b495d4-cadd-423a-b9de-41f265de96a5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Origin
        dep = ((AbstractDiagramSmClass)getClassOf()).getOriginDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("e13e059d-8790-4410-ae6b-b4c3fc6172f4")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractDiagram(this);
    }

}
