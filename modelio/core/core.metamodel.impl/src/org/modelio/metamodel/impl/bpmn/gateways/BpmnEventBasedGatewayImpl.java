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

package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGatewayType;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("009813cc-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnEventBasedGatewayImpl extends BpmnGatewayImpl implements BpmnEventBasedGateway {
    @objid ("4512c96d-51cd-4c73-bd52-bc73e94a1d2c")
    @Override
    public boolean isInstanciate() {
        return (Boolean) getAttVal(((BpmnEventBasedGatewaySmClass)getClassOf()).getInstanciateAtt());
    }

    @objid ("9f665f02-5b32-4471-ac58-3a3430c8d5e0")
    @Override
    public void setInstanciate(boolean value) {
        setAttVal(((BpmnEventBasedGatewaySmClass)getClassOf()).getInstanciateAtt(), value);
    }

    @objid ("c2a3b587-cdcc-428d-86fd-05e5fec563a0")
    @Override
    public BpmnEventBasedGatewayType getEventGatewayType() {
        return (BpmnEventBasedGatewayType) getAttVal(((BpmnEventBasedGatewaySmClass)getClassOf()).getEventGatewayTypeAtt());
    }

    @objid ("1082640f-0011-46f2-8a23-b2e56e15a673")
    @Override
    public void setEventGatewayType(BpmnEventBasedGatewayType value) {
        setAttVal(((BpmnEventBasedGatewaySmClass)getClassOf()).getEventGatewayTypeAtt(), value);
    }

    @objid ("b64b9819-399c-437b-ae36-d760f80f4bd9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("83a9332d-6054-489e-a8bf-e91969b90a66")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("76a14202-17a2-44a4-9520-76147309bc81")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnEventBasedGateway(this);
    }

}
