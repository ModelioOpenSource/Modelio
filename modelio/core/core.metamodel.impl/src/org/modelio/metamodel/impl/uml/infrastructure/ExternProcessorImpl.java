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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
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

@objid ("19257ea4-a0e7-4d4a-a390-75b7f65742f4")
public class ExternProcessorImpl extends ModelElementImpl implements ExternProcessor {
    @objid ("f91c3c56-710b-4f50-a922-fdfdff04505d")
    @Override
    public String getClassName() {
        return (String) getAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt());
    }

    @objid ("a6a4b779-0712-495d-821b-9c6dfdd63fce")
    @Override
    public void setClassName(String value) {
        setAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt(), value);
    }

    @objid ("7743bdce-617e-4763-99dd-24187954397a")
    @Override
    public QueryDefinition getOwnerQuery() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("8da2024f-e082-4ab3-9967-fef543b99b12")
    @Override
    public void setOwnerQuery(QueryDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep(), (SmObjectImpl)value);
    }

    @objid ("c3d47918-944f-48e5-a504-f7a0a84f86e8")
    @Override
    public MatrixValueDefinition getOwnerValDef() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("9df5f401-1c01-4147-93f3-6eb09b843b16")
    @Override
    public void setOwnerValDef(MatrixValueDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep(), (SmObjectImpl)value);
    }

    @objid ("934c564e-ac96-4c6d-8ac9-539fe6df8e0f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerQuery
        obj = (SmObjectImpl)this.getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep());
        if (obj != null)
          return obj;
        // OwnerValDef
        obj = (SmObjectImpl)this.getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("5ecf7c36-8f2c-47e7-8360-6897cd808d8f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerQuery
        dep = ((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerValDef
        dep = ((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a7b53e54-8e68-4c87-afc3-81a54314dc10")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitExternProcessor(this);
    }

}
