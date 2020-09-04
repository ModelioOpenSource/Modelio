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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEscalationEventDefinitionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008c1a7c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnEscalationEventDefinitionImpl extends BpmnEventDefinitionImpl implements BpmnEscalationEventDefinition {
    @objid ("9adf8214-aaa6-45d7-b881-aa793a25f902")
    @Override
    public String getEscalationCode() {
        return (String) getAttVal(((BpmnEscalationEventDefinitionSmClass)getClassOf()).getEscalationCodeAtt());
    }

    @objid ("823d9226-c640-4706-9daf-47f5f90336cc")
    @Override
    public void setEscalationCode(String value) {
        setAttVal(((BpmnEscalationEventDefinitionSmClass)getClassOf()).getEscalationCodeAtt(), value);
    }

    @objid ("14cd9f6f-3a0b-4a45-bff1-0dda6c341163")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3433e0e6-49ac-4438-94db-3d56f826e206")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("7e523617-aed2-4677-ace1-0dc5949e7c83")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnEscalationEventDefinition(this);
    }

}
