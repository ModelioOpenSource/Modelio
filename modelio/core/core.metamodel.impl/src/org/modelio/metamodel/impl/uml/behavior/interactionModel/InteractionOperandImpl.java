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
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0047c124-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionOperandImpl extends InteractionFragmentImpl implements InteractionOperand {
    @objid ("28a8c264-92f4-4b9b-aa5d-da2d21b195a7")
    @Override
    public String getGuard() {
        return (String) getAttVal(((InteractionOperandSmClass)getClassOf()).getGuardAtt());
    }

    @objid ("d5aee9ea-4928-45f3-9dda-ab34569e04a4")
    @Override
    public void setGuard(String value) {
        setAttVal(((InteractionOperandSmClass)getClassOf()).getGuardAtt(), value);
    }

    @objid ("b153fb0a-101e-4487-b0b0-aae6f2002b47")
    @Override
    public int getEndLineNumber() {
        return (Integer) getAttVal(((InteractionOperandSmClass)getClassOf()).getEndLineNumberAtt());
    }

    @objid ("0642218d-cbbd-4316-ba03-b07dc76f3a11")
    @Override
    public void setEndLineNumber(int value) {
        setAttVal(((InteractionOperandSmClass)getClassOf()).getEndLineNumberAtt(), value);
    }

    @objid ("cb7e9707-0e98-4cfb-a953-a451f3f6bb51")
    @Override
    public EList<InteractionFragment> getFragment() {
        return new SmList<>(this, ((InteractionOperandSmClass)getClassOf()).getFragmentDep());
    }

    @objid ("3827d03c-c474-46bf-bb8d-7c9e287baa28")
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

    @objid ("1fb0eaf9-080c-4a69-8329-8e95e6626f34")
    @Override
    public CombinedFragment getOwnerFragment() {
        Object obj = getDepVal(((InteractionOperandSmClass)getClassOf()).getOwnerFragmentDep());
        return (obj instanceof CombinedFragment)? (CombinedFragment)obj : null;
    }

    @objid ("01728d62-47c1-42fb-8052-f9cd616cf708")
    @Override
    public void setOwnerFragment(CombinedFragment value) {
        appendDepVal(((InteractionOperandSmClass)getClassOf()).getOwnerFragmentDep(), (SmObjectImpl)value);
    }

    @objid ("7f09a3cc-e1e0-4189-b10b-4ef3de92babf")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerFragment
        obj = (SmObjectImpl)this.getDepVal(((InteractionOperandSmClass)getClassOf()).getOwnerFragmentDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("735c78d7-7964-4a1e-9639-8b60e244c140")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerFragment
        dep = ((InteractionOperandSmClass)getClassOf()).getOwnerFragmentDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("9171bbf4-14b6-4f8c-a374-f63693e85822")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInteractionOperand(this);
    }

}
