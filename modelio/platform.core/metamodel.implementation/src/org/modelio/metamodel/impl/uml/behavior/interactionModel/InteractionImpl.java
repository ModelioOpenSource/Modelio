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
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorImpl;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionData;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0046b70c-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionImpl extends BehaviorImpl implements Interaction {
    @objid ("eaa5e7e7-0d6e-4c43-a9e7-eb24eb223e9b")
    @Override
    public EList<Gate> getFormalGate() {
        return new SmList<>(this, ((InteractionSmClass)getClassOf()).getFormalGateDep());
    }

    @objid ("331d6581-2a0f-4f51-9d05-dc29796d9b6c")
    @Override
    public <T extends Gate> List<T> getFormalGate(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Gate element : getFormalGate()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("1a519efa-9947-4542-ac57-4a1c8f20e7d5")
    @Override
    public EList<InteractionFragment> getFragment() {
        return new SmList<>(this, ((InteractionSmClass)getClassOf()).getFragmentDep());
    }

    @objid ("b872ac9b-7fff-49d3-bb0c-88ce32329138")
    @Override
    public <T extends InteractionFragment> List<T> getFragment(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InteractionFragment element : getFragment()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0ec5fbfc-9980-4be4-9994-9e645b97fc47")
    @Override
    public EList<Lifeline> getOwnedLine() {
        return new SmList<>(this, ((InteractionSmClass)getClassOf()).getOwnedLineDep());
    }

    @objid ("278d58e7-b178-4a1a-859a-bfb0c9af49b0")
    @Override
    public <T extends Lifeline> List<T> getOwnedLine(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Lifeline element : getOwnedLine()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("172d2e79-68c7-4333-92e4-1fc498830561")
    @Override
    public EList<InteractionUse> getReferedUse() {
        return new SmList<>(this, ((InteractionSmClass)getClassOf()).getReferedUseDep());
    }

    @objid ("1277d608-1fbb-4f0e-9d16-b013efb49de9")
    @Override
    public <T extends InteractionUse> List<T> getReferedUse(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InteractionUse element : getReferedUse()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("409ad80a-bb06-486d-98cf-96a3f157c831")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("47487f9a-3088-4062-b996-124394f55d84")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("30be15d4-8cc9-4117-bcb7-0b2787199491")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInteraction(this);
    }

}
