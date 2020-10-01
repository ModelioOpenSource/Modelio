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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ClauseData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002cec14-c4bf-1fd8-97fe-001ec947cd2a")
public class ClauseImpl extends UmlModelElementImpl implements Clause {
    @objid ("8ecf9531-8588-41a0-b8cf-46af301007d7")
    @Override
    public String getTest() {
        return (String) getAttVal(((ClauseSmClass)getClassOf()).getTestAtt());
    }

    @objid ("542b5928-0b31-4969-a9d5-b7c98601ace8")
    @Override
    public void setTest(String value) {
        setAttVal(((ClauseSmClass)getClassOf()).getTestAtt(), value);
    }

    @objid ("41a014d1-42fa-4f9a-9b0d-d449ae65ff6e")
    @Override
    public EList<ActivityNode> getBody() {
        return new SmList<>(this, ((ClauseSmClass)getClassOf()).getBodyDep());
    }

    @objid ("67270b8f-cbde-43f8-8c76-7f68d865a55e")
    @Override
    public <T extends ActivityNode> List<T> getBody(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityNode element : getBody()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f1ecde1b-30ee-4595-9979-ee1b81e1fc43")
    @Override
    public ConditionalNode getOwner() {
        Object obj = getDepVal(((ClauseSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ConditionalNode)? (ConditionalNode)obj : null;
    }

    @objid ("1eb48a9a-554c-4dad-8cb8-e77168cd33ac")
    @Override
    public void setOwner(ConditionalNode value) {
        appendDepVal(((ClauseSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("2fffb01e-bae1-49f1-9039-bbe8260a670a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ClauseSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("90cd15e2-52fc-4b10-b7e4-ddad56281b4f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ClauseSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("60670164-5643-49f2-9773-c1762b8b91d8")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitClause(this);
    }

}
