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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
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
    @objid ("abc44940-3e85-4eae-9713-810435e870e3")
    @Override
    public QueryDefinition getLinesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("b0a0797a-a84b-4c0e-a7ea-6498803b8c1e")
    @Override
    public void setLinesDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("778b3125-9e3a-4989-b4da-f2429a052849")
    @Override
    public QueryDefinition getColumnsDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("1e1b612b-c785-46ab-b785-89eadf80f70a")
    @Override
    public void setColumnsDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("3a08ef39-2b93-4a74-97d4-e23be468a5fc")
    @Override
    public MatrixValueDefinition getValuesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("7f7097ea-05dd-4478-a742-dd1b1e4a9d15")
    @Override
    public void setValuesDefinition(MatrixValueDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("a3328c83-6de2-4099-a1ba-1cfdc01a35dc")
    @Override
    public QueryDefinition getDepthDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("b12834f0-8545-46a4-935a-661731ffdf31")
    @Override
    public void setDepthDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("8abf092a-6883-4686-a2f9-9b15ba137f5a")
    @Override
    public ModelElement getOwner() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("2293729a-897e-4ec9-af9d-e303f7dcaae9")
    @Override
    public void setOwner(ModelElement value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("5ed17503-3659-43fa-9e60-3d8e7dc114b5")
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

    @objid ("7a770980-3e19-4ad9-9859-d3f1177f19fd")
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

    @objid ("f7668baa-4c88-49be-9e6f-c87ed5b5ec1b")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixDefinition(this);
    }

}
