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
package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionData;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("e5225255-1ebe-40cd-bc3d-6cd038eb4046")
public class QueryDefinitionImpl extends ElementImpl implements QueryDefinition {
    @objid ("5b14e0ee-9417-45f9-9287-6b20804883bc")
    @Override
    public boolean isUsingAdditions() {
        return (Boolean) getAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt());
    }

    @objid ("635ef85b-8718-47b6-adec-49cfba4a2302")
    @Override
    public void setUsingAdditions(boolean value) {
        setAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt(), value);
    }

    @objid ("f9aa4ffb-2501-47c8-9bb3-2e294f07982a")
    @Override
    public EList<Element> getAdded() {
        return new SmList<>(this, ((QueryDefinitionSmClass)getClassOf()).getAddedDep());
    }

    @objid ("9440e23e-4b75-459d-8895-eaddf804c406")
    @Override
    public <T extends Element> List<T> getAdded(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Element element : getAdded()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("92c08d78-34a3-41e5-b28c-b47eeafc7fad")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("51276952-83f8-4b52-afb6-3360f45d16c6")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("70514aca-d3b4-449a-af9b-129529b27648")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("041998dc-2241-4a72-840e-3199503ad21e")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("989bb2d1-2d49-4497-9876-5f89289ee13e")
    @Override
    public MatrixDefinition getOwnerAsLine() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("aabe6196-efa4-48b8-b079-e954e1a95d90")
    @Override
    public void setOwnerAsLine(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep(), (SmObjectImpl)value);
    }

    @objid ("d425f928-7179-4d8e-81e6-f8ca4c47e7a3")
    @Override
    public MatrixDefinition getOwnerAsCol() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("06deb6f4-7d38-40eb-970b-fbb9fa47533c")
    @Override
    public void setOwnerAsCol(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep(), (SmObjectImpl)value);
    }

    @objid ("df30af3c-78c5-45cc-a4cf-b339c4b4d6e1")
    @Override
    public MatrixDefinition getOwnerAsDepth() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("0cb1a325-5fac-43e7-ade4-7955f27d24ef")
    @Override
    public void setOwnerAsDepth(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep(), (SmObjectImpl)value);
    }

    @objid ("d1947e0d-2b05-4225-bca0-9238bb100d8c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerAsLine
        obj = (SmObjectImpl)this.getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep());
        if (obj != null)
          return obj;
        // OwnerAsCol
        obj = (SmObjectImpl)this.getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep());
        if (obj != null)
          return obj;
        // OwnerAsDepth
        obj = (SmObjectImpl)this.getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("905ad219-17f9-4467-aea1-027dd4bc36b0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerAsLine
        dep = ((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerAsCol
        dep = ((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerAsDepth
        dep = ((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("6eb6ec8b-7ac7-48d4-b166-c4220a931a98")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitQueryDefinition(this);
    }

}
