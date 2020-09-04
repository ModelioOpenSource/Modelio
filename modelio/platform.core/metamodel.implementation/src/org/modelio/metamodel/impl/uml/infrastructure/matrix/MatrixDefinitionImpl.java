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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("ce758e96-007e-4b95-9c1f-1964de5db680")
public class MatrixDefinitionImpl extends ModelElementImpl implements MatrixDefinition {
    @objid ("fa500fcc-4679-4300-9533-ec64030bef8e")
    @Override
    public QueryDefinition getLinesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("fb311e2f-383b-4312-bbac-8724db315d2a")
    @Override
    public void setLinesDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("2b26038b-ac7f-4cc0-b5be-25dea51fbce4")
    @Override
    public QueryDefinition getColumnsDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("ca5c8013-aaf7-4d7d-95bc-a01ae7c8c580")
    @Override
    public void setColumnsDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("feb18689-5efe-4897-9dde-1f17e814c098")
    @Override
    public MatrixValueDefinition getValuesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("687ed52f-1364-4538-b91f-9c9e537b6d19")
    @Override
    public void setValuesDefinition(MatrixValueDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("bd709e7f-2911-4cb6-bb14-e3b4dae7eb2e")
    @Override
    public QueryDefinition getDepthDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("d0676c5b-9629-4233-af05-60a49780d82c")
    @Override
    public void setDepthDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("92ec636a-16cb-49c2-a5ea-a169c30616d5")
    @Override
    public ModelElement getOwner() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("716fbcd2-921a-4a77-8800-d3e931eaff3b")
    @Override
    public void setOwner(ModelElement value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("a9d1f5e8-37ee-4350-b92d-f5bc3d3cc78f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("e5cac9b3-f1c7-48a5-a7b9-23a6eee8673f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((MatrixDefinitionSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("f0ee0036-909d-418f-8076-c0fbeef32a8b")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixDefinition(this);
    }

}
