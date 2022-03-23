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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00517a98-c4bf-1fd8-97fe-001ec947cd2a")
public class InternalTransitionImpl extends TransitionImpl implements InternalTransition {
    @objid ("2fdad016-5060-4966-9f98-fbc175b31093")
    @Override
    public State getSComposed() {
        Object obj = getDepVal(((InternalTransitionSmClass)getClassOf()).getSComposedDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("49c13c72-6cbc-4b9d-b8ae-834208150d9c")
    @Override
    public void setSComposed(State value) {
        appendDepVal(((InternalTransitionSmClass)getClassOf()).getSComposedDep(), (SmObjectImpl)value);
    }

    @objid ("81948a8e-afcd-4124-8c2a-c6b1117b890d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SComposed
        obj = (SmObjectImpl)this.getDepVal(((InternalTransitionSmClass)getClassOf()).getSComposedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("65a31eb6-b2dc-4ef2-8044-67d5e3050866")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SComposed
        dep = ((InternalTransitionSmClass)getClassOf()).getSComposedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("eeb5e834-2d1d-4d5c-b312-f1265bed8097")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInternalTransition(this);
    }

}
