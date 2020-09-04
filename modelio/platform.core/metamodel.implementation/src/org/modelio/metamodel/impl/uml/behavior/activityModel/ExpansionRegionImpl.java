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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionRegionData;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionKind;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0031ed18-c4bf-1fd8-97fe-001ec947cd2a")
public class ExpansionRegionImpl extends StructuredActivityNodeImpl implements ExpansionRegion {
    @objid ("adce1979-4274-4fa1-9c8a-df8e3a2c5f2f")
    @Override
    public ExpansionKind getMode() {
        return (ExpansionKind) getAttVal(((ExpansionRegionSmClass)getClassOf()).getModeAtt());
    }

    @objid ("4e2d260d-a0a1-4016-9d1d-0b8b9376ee8e")
    @Override
    public void setMode(ExpansionKind value) {
        setAttVal(((ExpansionRegionSmClass)getClassOf()).getModeAtt(), value);
    }

    @objid ("aed93f07-68fb-4a41-add5-560a088fc93d")
    @Override
    public EList<ExpansionNode> getOutputElement() {
        return new SmList<>(this, ((ExpansionRegionSmClass)getClassOf()).getOutputElementDep());
    }

    @objid ("f28dca1f-a2c5-4857-8cbf-c422b1715b0f")
    @Override
    public <T extends ExpansionNode> List<T> getOutputElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExpansionNode element : getOutputElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("c55d7eb3-079e-487a-a661-16acac9dafe0")
    @Override
    public EList<ExpansionNode> getInputElement() {
        return new SmList<>(this, ((ExpansionRegionSmClass)getClassOf()).getInputElementDep());
    }

    @objid ("df259962-86be-4ca1-b0a3-f47e65231c84")
    @Override
    public <T extends ExpansionNode> List<T> getInputElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExpansionNode element : getInputElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("56ba2ae9-7637-4534-bfbe-581abce10429")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e209bcb6-b4f0-4a8d-9efa-ed65dd9cb960")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("19192645-5ffe-4418-b0cc-e3f055dff2d4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExpansionRegion(this);
    }

}
