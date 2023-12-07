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
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008ec7d6-c4be-1fd8-97fe-001ec947cd2a")
public class TagParameterImpl extends ElementImpl implements TagParameter {
    @objid ("52b82864-964c-47bc-8505-c8ef3bb3d0f6")
    @Override
    public String getValue() {
        return (String) getAttVal(((TagParameterSmClass)getClassOf()).getValueAtt());
    }

    @objid ("82d634c2-6f92-4db2-8ffc-b3484f2e4556")
    @Override
    public void setValue(String value) {
        setAttVal(((TagParameterSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("cb53b136-95a8-47f5-aeb5-5ce472171a7b")
    @Override
    public TaggedValue getAnnoted() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("020a04c3-3ec4-4204-9709-8e54439748bd")
    @Override
    public void setAnnoted(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("ca08fd21-f024-4ca1-8013-57f5429c6bae")
    @Override
    public TaggedValue getQualified() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("6244c5d3-bd4a-462c-87da-b57fc4def1c0")
    @Override
    public void setQualified(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep(), (SmObjectImpl)value);
    }

    @objid ("3ddbf680-e243-4c75-be95-3fe993183ff3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Annoted
        obj = (SmObjectImpl)this.getDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep());
        if (obj != null)
          return obj;
        // Qualified
        obj = (SmObjectImpl)this.getDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("2239f7f9-ca73-450f-80b3-171015288091")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Annoted
        dep = ((TagParameterSmClass)getClassOf()).getAnnotedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Qualified
        dep = ((TagParameterSmClass)getClassOf()).getQualifiedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("832dde11-2535-4e6e-8624-60fce3b0b813")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagParameter(this);
    }

}
