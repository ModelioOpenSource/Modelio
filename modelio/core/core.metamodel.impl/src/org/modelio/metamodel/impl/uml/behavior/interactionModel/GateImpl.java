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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.GateData;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00456c76-c4bf-1fd8-97fe-001ec947cd2a")
public class GateImpl extends MessageEndImpl implements Gate {
    @objid ("c51baa4b-657c-4c67-acf4-f37871b50c0e")
    @Override
    public InteractionUse getOwnerUse() {
        Object obj = getDepVal(((GateSmClass)getClassOf()).getOwnerUseDep());
        return (obj instanceof InteractionUse)? (InteractionUse)obj : null;
    }

    @objid ("256804f5-35db-4974-a228-670c763f6e3b")
    @Override
    public void setOwnerUse(InteractionUse value) {
        appendDepVal(((GateSmClass)getClassOf()).getOwnerUseDep(), (SmObjectImpl)value);
    }

    @objid ("a857e13a-d1dc-4e38-a30f-b8adbdaf6504")
    @Override
    public EList<Gate> getActual() {
        return new SmList<>(this, ((GateSmClass)getClassOf()).getActualDep());
    }

    @objid ("85e8ba83-176f-4801-8c77-cbdfaa9e9bc5")
    @Override
    public <T extends Gate> List<T> getActual(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Gate element : getActual()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("83d9abfe-34d1-4e22-bc68-03770ec5fd36")
    @Override
    public Interaction getOwnerInteraction() {
        Object obj = getDepVal(((GateSmClass)getClassOf()).getOwnerInteractionDep());
        return (obj instanceof Interaction)? (Interaction)obj : null;
    }

    @objid ("c453acd9-01c9-4732-a44b-28354ebc4e06")
    @Override
    public void setOwnerInteraction(Interaction value) {
        appendDepVal(((GateSmClass)getClassOf()).getOwnerInteractionDep(), (SmObjectImpl)value);
    }

    @objid ("01f4a286-24de-43cc-af2a-b0560360dda4")
    @Override
    public CombinedFragment getOwnerFragment() {
        Object obj = getDepVal(((GateSmClass)getClassOf()).getOwnerFragmentDep());
        return (obj instanceof CombinedFragment)? (CombinedFragment)obj : null;
    }

    @objid ("2e3e0e17-c5f0-4f1a-8afd-a62fec886c07")
    @Override
    public void setOwnerFragment(CombinedFragment value) {
        appendDepVal(((GateSmClass)getClassOf()).getOwnerFragmentDep(), (SmObjectImpl)value);
    }

    @objid ("2de067de-0a9b-4e60-bf6b-923af3168fc4")
    @Override
    public Gate getFormal() {
        Object obj = getDepVal(((GateSmClass)getClassOf()).getFormalDep());
        return (obj instanceof Gate)? (Gate)obj : null;
    }

    @objid ("9eb494d7-3774-4d75-8677-7985b5ee7cb5")
    @Override
    public void setFormal(Gate value) {
        appendDepVal(((GateSmClass)getClassOf()).getFormalDep(), (SmObjectImpl)value);
    }

    @objid ("b0cb8e09-06cc-424f-a468-c2561e2bfab8")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerUse
        obj = (SmObjectImpl)this.getDepVal(((GateSmClass)getClassOf()).getOwnerUseDep());
        if (obj != null)
          return obj;
        // OwnerInteraction
        obj = (SmObjectImpl)this.getDepVal(((GateSmClass)getClassOf()).getOwnerInteractionDep());
        if (obj != null)
          return obj;
        // OwnerFragment
        obj = (SmObjectImpl)this.getDepVal(((GateSmClass)getClassOf()).getOwnerFragmentDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("df363562-0493-46ec-b32c-231fceb1e95e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerUse
        dep = ((GateSmClass)getClassOf()).getOwnerUseDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerInteraction
        dep = ((GateSmClass)getClassOf()).getOwnerInteractionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerFragment
        dep = ((GateSmClass)getClassOf()).getOwnerFragmentDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("dcc87cb7-3594-4929-bf2b-2052fa166b35")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitGate(this);
    }

}
