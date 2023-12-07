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
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("2fb6d68e-b51f-429d-a006-2a9a07892168")
public class MatrixValueDefinitionImpl extends ElementImpl implements MatrixValueDefinition {
    @objid ("094e4b06-ba5c-4c7b-a81a-6784e9e3db63")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("e21a894c-eb73-48c1-b40c-0866f3df0783")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("8ededa03-150b-414a-baaf-bf782b3eb6a3")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("7ebd85e3-d78a-43ca-9baa-d5ce7748c7f1")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("4a28e4d5-9bc0-4996-a88b-343754326d17")
    @Override
    public MatrixDefinition getMatrix() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("0cbf7ce4-e10e-4bfe-bf25-b92969aae375")
    @Override
    public void setMatrix(MatrixDefinition value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep(), (SmObjectImpl)value);
    }

    @objid ("de03b75c-a440-4b45-a3bf-676b4f886b3a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Matrix
        obj = (SmObjectImpl)this.getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8f0343b0-d944-4b7a-b5b5-a1cacc22c75c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Matrix
        dep = ((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("1b887913-6510-4f1e-b719-5e8b33178818")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixValueDefinition(this);
    }

}
