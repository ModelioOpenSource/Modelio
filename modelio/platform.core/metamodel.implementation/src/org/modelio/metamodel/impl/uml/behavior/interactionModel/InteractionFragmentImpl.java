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
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00473c54-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class InteractionFragmentImpl extends UmlModelElementImpl implements InteractionFragment {
    @objid ("8e1600f9-e8a2-4ac9-bf64-059e2eda8a6e")
    @Override
    public int getLineNumber() {
        return (Integer) getAttVal(((InteractionFragmentSmClass)getClassOf()).getLineNumberAtt());
    }

    @objid ("9a1cf0ab-1128-4bac-8c3b-c51589f0d73c")
    @Override
    public void setLineNumber(int value) {
        setAttVal(((InteractionFragmentSmClass)getClassOf()).getLineNumberAtt(), value);
    }

    @objid ("775e1794-c2c2-4c7f-8cc5-718ac4083620")
    @Override
    public InteractionOperand getEnclosingOperand() {
        Object obj = getDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingOperandDep());
        return (obj instanceof InteractionOperand)? (InteractionOperand)obj : null;
    }

    @objid ("f98f6c78-a9ce-443d-963a-ddf3189db45e")
    @Override
    public void setEnclosingOperand(InteractionOperand value) {
        appendDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingOperandDep(), (SmObjectImpl)value);
    }

    @objid ("d4a12932-ebef-4289-a94c-e4bce4f7dcad")
    @Override
    public Interaction getEnclosingInteraction() {
        Object obj = getDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingInteractionDep());
        return (obj instanceof Interaction)? (Interaction)obj : null;
    }

    @objid ("7f8925d4-a28c-47dd-87e7-ed6cf2e5e5c4")
    @Override
    public void setEnclosingInteraction(Interaction value) {
        appendDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingInteractionDep(), (SmObjectImpl)value);
    }

    @objid ("4ca1a84c-3127-41cb-9bad-a467aaec89b9")
    @Override
    public EList<Lifeline> getCovered() {
        return new SmList<>(this, ((InteractionFragmentSmClass)getClassOf()).getCoveredDep());
    }

    @objid ("5c125070-91ab-43bf-b8ee-640b89fa1fe7")
    @Override
    public <T extends Lifeline> List<T> getCovered(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Lifeline element : getCovered()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("a28c3117-b548-4faf-8560-03b8532b6abc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // EnclosingOperand
        obj = (SmObjectImpl)this.getDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingOperandDep());
        if (obj != null)
          return obj;
        // EnclosingInteraction
        obj = (SmObjectImpl)this.getDepVal(((InteractionFragmentSmClass)getClassOf()).getEnclosingInteractionDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("53c818fe-6e1a-4faf-b15d-2491e5d863fb")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // EnclosingOperand
        dep = ((InteractionFragmentSmClass)getClassOf()).getEnclosingOperandDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // EnclosingInteraction
        dep = ((InteractionFragmentSmClass)getClassOf()).getEnclosingInteractionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("3ed4df47-46e8-4065-ad13-3d936c0a97dc")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInteractionFragment(this);
    }

}
