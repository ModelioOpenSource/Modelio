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

@objid ("0044efbc-c4bf-1fd8-97fe-001ec947cd2a")
public class ExecutionSpecificationImpl extends InteractionFragmentImpl implements ExecutionSpecification {
    @objid ("08fcb782-80af-4f07-af92-dba635bf9391")
    @Override
    public ExecutionOccurenceSpecification getFinish() {
        Object obj = getDepVal(((ExecutionSpecificationSmClass)getClassOf()).getFinishDep());
        return (obj instanceof ExecutionOccurenceSpecification)? (ExecutionOccurenceSpecification)obj : null;
    }

    @objid ("b5406800-3438-4de1-9226-98f663e38ff0")
    @Override
    public void setFinish(ExecutionOccurenceSpecification value) {
        appendDepVal(((ExecutionSpecificationSmClass)getClassOf()).getFinishDep(), (SmObjectImpl)value);
    }

    @objid ("4f939beb-e095-480f-80ce-125c45ee24c7")
    @Override
    public ExecutionOccurenceSpecification getStart() {
        Object obj = getDepVal(((ExecutionSpecificationSmClass)getClassOf()).getStartDep());
        return (obj instanceof ExecutionOccurenceSpecification)? (ExecutionOccurenceSpecification)obj : null;
    }

    @objid ("cd2e84ae-3d13-441a-8268-ec956bb8e9e8")
    @Override
    public void setStart(ExecutionOccurenceSpecification value) {
        appendDepVal(((ExecutionSpecificationSmClass)getClassOf()).getStartDep(), (SmObjectImpl)value);
    }

    @objid ("132fbd36-d5a0-485d-9f67-8f5173d2ee82")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("b44320f5-b7f1-4315-af49-31b79d6aa820")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8993746a-9387-468a-b648-68173d74ad91")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExecutionSpecification(this);
    }

}
