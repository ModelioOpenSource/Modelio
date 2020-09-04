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
    @objid ("ec31abb8-aa61-4350-b445-f441362b1d06")
    @Override
    public boolean isUsingAdditions() {
        return (Boolean) getAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt());
    }

    @objid ("673451a1-30f1-4f9a-be7b-ecbd33996f35")
    @Override
    public void setUsingAdditions(boolean value) {
        setAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt(), value);
    }

    @objid ("1e0254bf-44d1-486a-ba33-c221d9088f83")
    @Override
    public EList<Element> getAdded() {
        return new SmList<>(this, ((QueryDefinitionSmClass)getClassOf()).getAddedDep());
    }

    @objid ("fce61cad-2cc0-4bbc-8312-c9771ddc97a7")
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

    @objid ("265000e6-4327-42de-b28c-f0287c9065dc")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("8abb8d15-e03e-46e2-8cbd-8219772dc582")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("37e31b82-9289-4d1b-9ff7-18f23d351ea1")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("89964780-82a4-48ac-8c60-a9dc2f20efba")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("74bc742b-1b9c-4832-942c-d37ac2ff647a")
    @Override
    public MatrixDefinition getOwnerAsLine() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("8f39c4c9-eef7-4752-ba4e-c37ddf304222")
    @Override
    public void setOwnerAsLine(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep(), (SmObjectImpl)value);
    }

    @objid ("d3a02892-d7b9-4386-b2ff-72408477941a")
    @Override
    public MatrixDefinition getOwnerAsCol() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("916ce455-3303-4a17-845f-11ec72e7db00")
    @Override
    public void setOwnerAsCol(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep(), (SmObjectImpl)value);
    }

    @objid ("c016a9c3-c7a8-4f1e-ba54-a329f16078b6")
    @Override
    public MatrixDefinition getOwnerAsDepth() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("0b76ae62-8686-42fd-974f-0ea0d1dad370")
    @Override
    public void setOwnerAsDepth(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep(), (SmObjectImpl)value);
    }

    @objid ("12c83b6d-5630-4f3d-8640-06f8732ac209")
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

    @objid ("2747a427-7414-4222-b842-8adcd672941f")
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

    @objid ("6a950d86-8d7a-4f3e-8802-92acbae72d53")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitQueryDefinition(this);
    }

}
