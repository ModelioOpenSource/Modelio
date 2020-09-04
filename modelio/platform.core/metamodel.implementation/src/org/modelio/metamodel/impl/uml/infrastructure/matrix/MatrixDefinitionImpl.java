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
    @objid ("c94557cd-9be6-425b-b2aa-938268eed619")
    @Override
    public QueryDefinition getLinesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("c6742381-32fd-44b4-9343-50a0d750ccea")
    @Override
    public void setLinesDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getLinesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("f3b872b1-6b98-4410-a5ce-a3ec4159cce5")
    @Override
    public QueryDefinition getColumnsDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("67c17024-1d9c-4f65-9e20-572226aa05e0")
    @Override
    public void setColumnsDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getColumnsDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("4331cb69-4062-491b-947b-04fb69421960")
    @Override
    public MatrixValueDefinition getValuesDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("acea115b-cc2e-41f0-a98f-ecf52fd2c17e")
    @Override
    public void setValuesDefinition(MatrixValueDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getValuesDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("7c199746-e77c-4966-82ec-0c3453aaac5a")
    @Override
    public QueryDefinition getDepthDefinition() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("638ea240-7f4a-4c4a-b368-40c76eb65959")
    @Override
    public void setDepthDefinition(QueryDefinition value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getDepthDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("a0e28427-bb37-4f8b-a350-2e75e501b9a3")
    @Override
    public ModelElement getOwner() {
        Object obj = getDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("5d62399d-33ce-4e3f-8d15-dbdfd1b5a8a8")
    @Override
    public void setOwner(ModelElement value) {
        appendDepVal(((MatrixDefinitionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("cc7d1de2-e004-45de-bd08-995112818609")
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

    @objid ("1ef48eee-350e-477d-b694-82444ae33c80")
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

    @objid ("0f894e24-ae65-4200-a3f3-0edb920b9357")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixDefinition(this);
    }

}
