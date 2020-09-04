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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionUseData;
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

@objid ("00485742-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionUseImpl extends InteractionFragmentImpl implements InteractionUse {
    @objid ("b1240c9a-468b-45d8-9624-3fbf982dac5f")
    @Override
    public int getEndLineNumber() {
        return (Integer) getAttVal(((InteractionUseSmClass)getClassOf()).getEndLineNumberAtt());
    }

    @objid ("c37acfc7-4c33-49ba-afe6-2905591cf106")
    @Override
    public void setEndLineNumber(int value) {
        setAttVal(((InteractionUseSmClass)getClassOf()).getEndLineNumberAtt(), value);
    }

    @objid ("50c76bb4-6bde-4f69-9394-d90effb61a86")
    @Override
    public EList<Gate> getActualGate() {
        return new SmList<>(this, ((InteractionUseSmClass)getClassOf()).getActualGateDep());
    }

    @objid ("a43117ed-6bf3-49bb-ba28-84739b7ba15b")
    @Override
    public <T extends Gate> List<T> getActualGate(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Gate element : getActualGate()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4f0f20f3-dd5e-4c7c-b11a-6614da6bd387")
    @Override
    public Interaction getRefersTo() {
        Object obj = getDepVal(((InteractionUseSmClass)getClassOf()).getRefersToDep());
        return (obj instanceof Interaction)? (Interaction)obj : null;
    }

    @objid ("65dc28d9-9d06-4db0-b4d4-5493e55a597d")
    @Override
    public void setRefersTo(Interaction value) {
        appendDepVal(((InteractionUseSmClass)getClassOf()).getRefersToDep(), (SmObjectImpl)value);
    }

    @objid ("9868a0db-1b19-46b1-a5bb-8c6ab11cee5c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("9106e80c-8106-406c-8e4b-f3232a2a3327")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("edbf76c0-9b42-48e5-ab6e-fd13cbe5903b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInteractionUse(this);
    }

}
