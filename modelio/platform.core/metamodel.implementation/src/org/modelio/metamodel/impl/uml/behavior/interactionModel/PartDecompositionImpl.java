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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.PartDecompositionData;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("004b1612-c4bf-1fd8-97fe-001ec947cd2a")
public class PartDecompositionImpl extends InteractionUseImpl implements PartDecomposition {
    @objid ("87fbab8c-e3ce-4996-a75c-48d907e5e279")
    @Override
    public Lifeline getDecomposed() {
        Object obj = getDepVal(((PartDecompositionSmClass)getClassOf()).getDecomposedDep());
        return (obj instanceof Lifeline)? (Lifeline)obj : null;
    }

    @objid ("79d8f021-063e-4171-830a-92a536ca2223")
    @Override
    public void setDecomposed(Lifeline value) {
        appendDepVal(((PartDecompositionSmClass)getClassOf()).getDecomposedDep(), (SmObjectImpl)value);
    }

    @objid ("e416ea71-cc95-4902-aee7-a80bae16b337")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Decomposed
        obj = (SmObjectImpl)this.getDepVal(((PartDecompositionSmClass)getClassOf()).getDecomposedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("109a1a61-6b3c-46cd-a10a-1b453fcc9bbf")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Decomposed
        dep = ((PartDecompositionSmClass)getClassOf()).getDecomposedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("22b12af4-618c-4c4b-a377-500c6928cbac")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPartDecomposition(this);
    }

}
