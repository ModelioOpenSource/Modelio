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
    @objid ("4a85d657-1683-4161-a49b-2044a1248d11")
    @Override
    public int getUiDataVersion() {
        return (Integer) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt());
    }

    @objid ("78c5e894-15b1-402b-b4d1-512d8644d592")
    @Override
    public void setUiDataVersion(int value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataVersionAtt(), value);
    }

    @objid ("7a95cd16-a8c1-4274-b05c-130499d31198")
    @Override
    public String getUiData() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt());
    }

    @objid ("070b7307-c37d-4b59-9504-88ec59c8750d")
    @Override
    public void setUiData(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getUiDataAtt(), value);
    }

    @objid ("c173a9f0-af84-4ad9-bc12-576079d0831a")
    @Override
    public String getPreviewData() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getPreviewDataAtt());
    }

    @objid ("ab33394f-af49-44f1-98e2-c7e780ff4332")
    @Override
    public void setPreviewData(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getPreviewDataAtt(), value);
    }

    @objid ("4ca8c75f-8471-4c07-9dda-1357fa931d70")
    @Override
    public String getJsStructure() {
        return (String) getAttVal(((AbstractDiagramSmClass)getClassOf()).getJsStructureAtt());
    }

    @objid ("3315cb65-d09a-426b-8a01-433d21e2f209")
    @Override
    public void setJsStructure(String value) {
        setAttVal(((AbstractDiagramSmClass)getClassOf()).getJsStructureAtt(), value);
    }

    @objid ("3c748595-6481-43bc-9d62-bf5203205f27")
    @Override
    public EList<Element> getRepresented() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getRepresentedDep());
    }

    @objid ("934d3f45-a1e8-4440-b516-c3e59fdbacfb")
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

    @objid ("535ed430-7a82-4806-b442-73120b962bb8")
    @Override
    public EList<DiagramSet> getReferencingSet() {
        return new SmList<>(this, ((AbstractDiagramSmClass)getClassOf()).getReferencingSetDep());
    }

    @objid ("6dee7516-b3b4-4ee3-a18f-1d08f6b79d3d")
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

    @objid ("5b5eab2d-1ca1-4fb8-b264-dbdd56872b91")
    @Override
    public ModelElement getOrigin() {
        Object obj = getDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("7a8c4865-ea8b-48ba-ac1d-2a9f7081a350")
    @Override
    public void setOrigin(ModelElement value) {
        appendDepVal(((AbstractDiagramSmClass)getClassOf()).getOriginDep(), (SmObjectImpl)value);
    }

    @objid ("a7785a0c-8a08-4a13-bc21-94784d432e4e")
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

    @objid ("deb07a1e-ae53-45d2-ac8e-300c4ec2986a")
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

    @objid ("0865f805-0301-43f6-8f88-6881b2a1c496")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractDiagram(this);
    }

}
