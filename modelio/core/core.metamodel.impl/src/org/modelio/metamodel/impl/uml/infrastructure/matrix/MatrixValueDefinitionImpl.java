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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("aa8f4ded-740a-4fb6-ad5c-2b79e018079f")
    @Override
    public ExternProcessor getProcessor() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep());
        return (obj instanceof ExternProcessor)? (ExternProcessor)obj : null;
    }

    @objid ("1fb3ed63-b4ce-4c5b-bcdc-a9dee3e71372")
    @Override
    public void setProcessor(ExternProcessor value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getProcessorDep(), (SmObjectImpl)value);
    }

    @objid ("852bb08b-fbac-45cc-951b-53d5a5b800c7")
    @Override
    public PropertyTable getParameters() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep());
        return (obj instanceof PropertyTable)? (PropertyTable)obj : null;
    }

    @objid ("c7897394-f23f-4f7c-a2bb-f99b93bc593b")
    @Override
    public void setParameters(PropertyTable value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getParametersDep(), (SmObjectImpl)value);
    }

    @objid ("592939e8-805e-4964-ae8b-44a505af942a")
    @Override
    public MatrixDefinition getMatrix() {
        Object obj = getDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep());
        return (obj instanceof MatrixDefinition)? (MatrixDefinition)obj : null;
    }

    @objid ("acfd4b08-3fc8-4b9b-bd86-bb109448e9e5")
    @Override
    public void setMatrix(MatrixDefinition value) {
        appendDepVal(((MatrixValueDefinitionSmClass)getClassOf()).getMatrixDep(), (SmObjectImpl)value);
    }

    @objid ("e343c43f-568e-471e-a503-a1680cf75358")
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

    @objid ("783dbe56-d478-482d-a64c-7f2c5ccd13ae")
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

    @objid ("c1b1e8bc-c450-4ffd-9450-01037dfe4069")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMatrixValueDefinition(this);
    }

}
