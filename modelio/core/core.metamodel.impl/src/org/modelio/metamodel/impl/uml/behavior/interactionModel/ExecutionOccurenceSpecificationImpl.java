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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionOccurenceSpecificationData;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00447406-c4bf-1fd8-97fe-001ec947cd2a")
public class ExecutionOccurenceSpecificationImpl extends MessageEndImpl implements ExecutionOccurenceSpecification {
    @objid ("87a21097-a7e1-4252-9498-bcb046f64956")
    @Override
    public ExecutionSpecification getFinished() {
        Object obj = getDepVal(((ExecutionOccurenceSpecificationSmClass)getClassOf()).getFinishedDep());
        return (obj instanceof ExecutionSpecification)? (ExecutionSpecification)obj : null;
    }

    @objid ("266ccee9-cf12-4740-947c-486ce0b87fcc")
    @Override
    public void setFinished(ExecutionSpecification value) {
        appendDepVal(((ExecutionOccurenceSpecificationSmClass)getClassOf()).getFinishedDep(), (SmObjectImpl)value);
    }

    @objid ("2d01d77a-953b-4cfd-8218-d89ce6f858e9")
    @Override
    public ExecutionSpecification getStarted() {
        Object obj = getDepVal(((ExecutionOccurenceSpecificationSmClass)getClassOf()).getStartedDep());
        return (obj instanceof ExecutionSpecification)? (ExecutionSpecification)obj : null;
    }

    @objid ("d17903d3-f727-4f68-96eb-501100e775b8")
    @Override
    public void setStarted(ExecutionSpecification value) {
        appendDepVal(((ExecutionOccurenceSpecificationSmClass)getClassOf()).getStartedDep(), (SmObjectImpl)value);
    }

    @objid ("eb4e5274-b265-441e-86ae-f9530ff04e01")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e642628b-412a-47f6-8c29-69779ba3eae2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("21ec706b-2739-46ff-90bd-a2bcf20cc57f")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExecutionOccurenceSpecification(this);
    }

}
