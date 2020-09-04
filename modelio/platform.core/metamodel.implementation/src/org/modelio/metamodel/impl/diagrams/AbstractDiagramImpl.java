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
import org.modelio.metamodel.impl.diagrams.AbstractDiagramData;
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
    @objid ("e2aa2480-5c48-4dd1-a3b7-27219898143a")
    @Override
    public int getUiDataVersion() {
        return (Integer) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt());
    }

    @objid ("e1059589-1234-4ed4-9af8-b145acdc542c")
    @Override
    public void setUiDataVersion(int value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt(), value);
    }

    @objid ("7ab8ee7c-68be-4f34-9222-a7a6bbefe862")
    @Override
    public String getUiData() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt());
    }

    @objid ("8a93cafd-d896-4c27-8c4c-a4ed504defa0")
    @Override
    public void setUiData(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt(), value);
    }

    @objid ("13227bdc-6d8e-4e08-b17c-da6f44832286")
    @Override
    public EList<Element> getRepresented() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getRepresentedDep());
    }

    @objid ("4a8ffc32-86a9-46ab-8357-97ad24a0536e")
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

    @objid ("d75bccc0-3c4d-4237-bf72-d21612995cf8")
    @Override
    public EList<DiagramSet> getReferencingSet() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getReferencingSetDep());
    }

    @objid ("16559544-77c0-4d99-b8aa-40833e3fd042")
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

    @objid ("ffacd708-5cfd-4965-b446-c55922fbb318")
    @Override
    public ModelElement getOrigin() {
        Object obj = getDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("0ea047f2-0639-455c-a696-0a8e511654eb")
    @Override
    public void setOrigin(ModelElement value) {
        appendDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep(), (SmObjectImpl)value);
    }

    @objid ("1715a041-4aba-4bfc-b2f7-0027d92339a2")
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

    @objid ("1fc47cd5-2792-4d8b-aae5-ffdbbac0b36b")
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

    @objid ("aed3cf9f-c1d5-4983-b78c-8412af94b9bb")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractDiagram(this);
    }

}
