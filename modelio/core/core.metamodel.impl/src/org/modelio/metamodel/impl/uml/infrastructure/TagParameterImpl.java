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
    @objid ("98b3b974-8556-4f41-9d7d-e457a2f66ef8")
    @Override
    public String getValue() {
        return (String) getAttVal(((TagParameterSmClass)getClassOf()).getValueAtt());
    }

    @objid ("0c4302cc-9734-4426-9d6b-daa4d32e0d64")
    @Override
    public void setValue(String value) {
        setAttVal(((TagParameterSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("e625e877-87b7-4c44-a1bd-85285457ab28")
    @Override
    public TaggedValue getAnnoted() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("1a7fb9b7-9248-4d2b-afe9-6653fd03e08e")
    @Override
    public void setAnnoted(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("f200e9ce-1fbc-4105-b5c7-15f1cbf3cfae")
    @Override
    public TaggedValue getQualified() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("bfea1d6d-fb90-4625-b2e6-386daf75300f")
    @Override
    public void setQualified(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep(), (SmObjectImpl)value);
    }

    @objid ("93479cd7-a32b-45f3-b009-1f8d162f51c5")
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

    @objid ("5187332e-d7d2-46fb-9ab2-23479cf4ce98")
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

    @objid ("7756ab2a-6439-4225-805b-428a7fd30af5")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagParameter(this);
    }

}
