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

package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
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
    @objid ("1a6800ad-549c-422b-a43e-b18765c81d63")
    @Override
    public boolean isUsingAdditions() {
        return (Boolean) getAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt());
    }

    @objid ("030ef797-9dbc-464f-8edd-d1df1493cba3")
    @Override
    public void setUsingAdditions(boolean value) {
        setAttVal(((QueryDefinitionSmClass)getClassOf()).getUsingAdditionsAtt(), value);
    }

    @objid ("9030305c-7c00-483e-b8ef-13e196172407")
    @Override
    public EList<Element> getAdded() {
        return new SmList<>(this, ((QueryDefinitionSmClass)getClassOf()).getAddedDep());
    }

    @objid ("ee9d7b5f-d7a8-44c5-bac8-a58b44512513")
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

    @objid ("e558ca73-a1e7-4d28-9de6-6801c3d7640e")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("c54105d1-24e9-4b89-9c87-41068291aa62")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("7e2a28d5-69ff-403f-9e31-d1be2fc39602")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("0b46db3d-a39c-4b32-9af6-5a223289faa8")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("1911349e-f693-4ccc-9f94-b9c1a10d8364")
    @Override
    public MatrixDefinition getOwnerAsLine() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("82c3d817-2163-4fb9-af33-c6b696af81bf")
    @Override
    public void setOwnerAsLine(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsLineDep(), (SmObjectImpl)value);
    }

    @objid ("8171752d-6767-4adb-a271-f4d875d18e28")
    @Override
    public MatrixDefinition getOwnerAsCol() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("9dcd1768-2f5e-4fb5-960c-dac56d76cac7")
    @Override
    public void setOwnerAsCol(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsColDep(), (SmObjectImpl)value);
    }

    @objid ("8da68910-29c6-4732-844d-0a95f7034b6d")
    @Override
    public MatrixDefinition getOwnerAsDepth() {
        Object obj = getDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("1cec555e-e330-4f2f-b2d3-da4ae28f69e0")
    @Override
    public void setOwnerAsDepth(MatrixDefinition value) {
        appendDepVal(((QueryDefinitionSmClass)getClassOf()).getOwnerAsDepthDep(), (SmObjectImpl)value);
    }

    @objid ("da05b98a-130e-43ad-8da8-39d675f94a95")
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

    @objid ("62bbc09f-e95f-474f-89cd-50b35b74a8ae")
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

    @objid ("0a0a6f4a-5d86-4ff6-a851-73a9ad999630")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitQueryDefinition(this);
    }

}
