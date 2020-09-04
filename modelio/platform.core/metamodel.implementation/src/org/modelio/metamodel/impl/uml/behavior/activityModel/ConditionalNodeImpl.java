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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ConditionalNodeData;
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

@objid ("002d81b0-c4bf-1fd8-97fe-001ec947cd2a")
public class ConditionalNodeImpl extends StructuredActivityNodeImpl implements ConditionalNode {
    @objid ("59693787-4cab-45a8-8ead-0559629ab14a")
    @Override
    public boolean isIsDeterminate() {
        return (Boolean) getAttVal(((ConditionalNodeSmClass)getClassOf()).getIsDeterminateAtt());
    }

    @objid ("a49a53a3-1857-47bf-bece-7393bb5f252b")
    @Override
    public void setIsDeterminate(boolean value) {
        setAttVal(((ConditionalNodeSmClass)getClassOf()).getIsDeterminateAtt(), value);
    }

    @objid ("329976f8-7cd0-4f06-9bd4-11efb6f0bf30")
    @Override
    public boolean isIsAssured() {
        return (Boolean) getAttVal(((ConditionalNodeSmClass)getClassOf()).getIsAssuredAtt());
    }

    @objid ("9e452f5d-cc51-4c16-a663-ca94ecdbc267")
    @Override
    public void setIsAssured(boolean value) {
        setAttVal(((ConditionalNodeSmClass)getClassOf()).getIsAssuredAtt(), value);
    }

    @objid ("8d3bfc37-dee5-478f-9e0f-6fb52f29eafa")
    @Override
    public EList<Clause> getOwnedClause() {
        return new SmList<>(this, ((ConditionalNodeSmClass)getClassOf()).getOwnedClauseDep());
    }

    @objid ("c67256b1-622b-447b-bdc0-c239c5bda427")
    @Override
    public <T extends Clause> List<T> getOwnedClause(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Clause element : getOwnedClause()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("34aaf6d5-1b17-4739-b0c0-33cd5ff751b5")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e12bc504-98b0-4d38-9c1c-f14dfadf704f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2e30a182-b977-4e7c-a655-d254ba24505d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitConditionalNode(this);
    }

}
