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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.CombinedFragmentData;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00437f1a-c4bf-1fd8-97fe-001ec947cd2a")
public class CombinedFragmentImpl extends InteractionFragmentImpl implements CombinedFragment {
    @objid ("7bc0f808-5584-4598-8c12-303c4e997059")
    @Override
    public InteractionOperator getOperator() {
        return (InteractionOperator) getAttVal(((CombinedFragmentSmClass)getClassOf()).getOperatorAtt());
    }

    @objid ("89c5b5c2-44ab-4ab5-9d15-ca55e5f5cbdd")
    @Override
    public void setOperator(InteractionOperator value) {
        setAttVal(((CombinedFragmentSmClass)getClassOf()).getOperatorAtt(), value);
    }

    @objid ("ab1d4997-4a93-4d25-b3e4-1b9922c7ab55")
    @Override
    public EList<InteractionOperand> getOperand() {
        return new SmList<>(this, ((CombinedFragmentSmClass)getClassOf()).getOperandDep());
    }

    @objid ("86ed96cc-fe59-4d63-b091-451393da6086")
    @Override
    public <T extends InteractionOperand> List<T> getOperand(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InteractionOperand element : getOperand()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("76b2beb0-c90c-4efa-a105-e043ba76f347")
    @Override
    public EList<Gate> getFragmentGate() {
        return new SmList<>(this, ((CombinedFragmentSmClass)getClassOf()).getFragmentGateDep());
    }

    @objid ("91e5e616-d45b-4fdc-b5e6-41becb54ca72")
    @Override
    public <T extends Gate> List<T> getFragmentGate(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Gate element : getFragmentGate()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("27da8d75-e260-4737-b53f-0d04d328d189")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("eff60c12-9e50-442e-b05e-74895b7c00ad")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("665b99f6-1d15-4132-807f-c98250e5d488")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCombinedFragment(this);
    }

}
