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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorData;
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
    @objid ("94cebe7a-7c87-43e5-ba02-3c5cdaaffd24")
    @Override
    public String getClassName() {
        return (String) getAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt());
    }

    @objid ("365bfe7c-2d72-4bf2-a2db-19b8d0c5e71a")
    @Override
    public void setClassName(String value) {
        setAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt(), value);
    }

    @objid ("02349cef-58bb-40cd-8b91-755a7109fb34")
    @Override
    public QueryDefinition getOwnerQuery() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("17364ef0-fa62-4d78-b931-414905fa5de4")
    @Override
    public void setOwnerQuery(QueryDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep(), (SmObjectImpl)value);
    }

    @objid ("094df6d0-7344-4755-93ff-37226cd710f4")
    @Override
    public MatrixValueDefinition getOwnerValDef() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("c03941f1-82b6-4c61-9ad3-55aafd9bdb2b")
    @Override
    public void setOwnerValDef(MatrixValueDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep(), (SmObjectImpl)value);
    }

    @objid ("1f5c8447-4925-4ee3-9e62-a34cf672a6f5")
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

    @objid ("8424735e-abd5-4f44-a07b-855d93e133a7")
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

    @objid ("9a204509-c347-4b89-8064-d49a1a79d86c")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitExternProcessor(this);
    }

}
