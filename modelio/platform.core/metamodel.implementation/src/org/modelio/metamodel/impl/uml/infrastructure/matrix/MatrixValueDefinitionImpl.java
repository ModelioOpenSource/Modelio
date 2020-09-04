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
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionData;
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
    @objid ("d5019e0b-cc95-4eeb-b3bb-46db79c25dab")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("74e41c8b-2748-4efd-b255-24879886eb43")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("9a2d206f-3460-4eed-a970-3f697db07958")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("244db63a-694a-4d18-a6de-b9d532ea036f")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("c28c8a90-05d0-443c-8764-fec04dd569e9")
    @Override
    public MatrixDefinition getMatrix() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("92d6966e-d01a-41da-be86-3e95ada6b4b4")
    @Override
    public void setMatrix(MatrixDefinition value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep(), (SmObjectImpl)value);
    }

    @objid ("d183a414-6ab0-4aea-a5c1-d539e951777f")
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

    @objid ("5bfea7aa-8e1b-4b2b-bfd9-f9bab429293e")
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

    @objid ("dae8c13c-b4f9-49a8-92a0-e7ab4f820a9e")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixValueDefinition(this);
    }

}
