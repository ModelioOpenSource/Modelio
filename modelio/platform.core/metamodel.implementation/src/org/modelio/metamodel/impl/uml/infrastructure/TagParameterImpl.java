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
import org.modelio.metamodel.impl.uml.infrastructure.TagParameterData;
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
    @objid ("b36867ee-672b-447a-a833-df9432bde828")
    @Override
    public String getValue() {
        return (String) getAttVal(((TagParameterSmClass)getClassOf()).getValueAtt());
    }

    @objid ("1d1a09ee-9022-4075-867f-8d1380273957")
    @Override
    public void setValue(String value) {
        setAttVal(((TagParameterSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("104c974e-6569-4e79-9dbd-8b2e3ddce235")
    @Override
    public TaggedValue getAnnoted() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("661035bd-6099-41c5-8706-c1a68829c559")
    @Override
    public void setAnnoted(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("e3dfd09e-fb47-42d6-a0c6-c943030c404c")
    @Override
    public TaggedValue getQualified() {
        Object obj = getDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep());
        return (obj instanceof TaggedValue)? (TaggedValue)obj : null;
    }

    @objid ("04c08fe2-09a3-4dee-a320-8deb6d8c35e7")
    @Override
    public void setQualified(TaggedValue value) {
        appendDepVal(((TagParameterSmClass)getClassOf()).getQualifiedDep(), (SmObjectImpl)value);
    }

    @objid ("04f5a60c-d0ad-4344-80dd-a54e83109029")
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

    @objid ("aa3ac43e-37b5-41f3-8bdd-0dc278c7217f")
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

    @objid ("e9697e5c-80f5-4eab-a94c-e78558b1f926")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagParameter(this);
    }

}
