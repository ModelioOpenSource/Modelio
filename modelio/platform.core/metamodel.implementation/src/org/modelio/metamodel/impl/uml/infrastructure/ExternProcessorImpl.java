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
    @objid ("fbec096f-915d-4cd0-b21f-6f8479b48da4")
    @Override
    public String getClassName() {
        return (String) getAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt());
    }

    @objid ("e2ec8378-4109-430a-a1db-470d19a21fab")
    @Override
    public void setClassName(String value) {
        setAttVal(((ExternProcessorSmClass)getClassOf()).getClassNameAtt(), value);
    }

    @objid ("039f4ac0-3f6f-49a5-979f-05d66ee2273a")
    @Override
    public QueryDefinition getOwnerQuery() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("081ff289-6d45-42b8-b69a-30d1c230ff41")
    @Override
    public void setOwnerQuery(QueryDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerQueryDep(), (SmObjectImpl)value);
    }

    @objid ("596b9a75-8e64-44eb-955d-b65a610c6096")
    @Override
    public MatrixValueDefinition getOwnerValDef() {
        Object obj = getDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("a758423f-fe65-4820-86bc-8063eece6178")
    @Override
    public void setOwnerValDef(MatrixValueDefinition value) {
        appendDepVal(((ExternProcessorSmClass)getClassOf()).getOwnerValDefDep(), (SmObjectImpl)value);
    }

    @objid ("fbeeef70-79e8-482a-9433-a0dd4790aa80")
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

    @objid ("d88654eb-2f38-4759-8eea-65ed1aa998cf")
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

    @objid ("9ae65160-5792-46a8-b6ca-803b2185a626")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitExternProcessor(this);
    }

}
