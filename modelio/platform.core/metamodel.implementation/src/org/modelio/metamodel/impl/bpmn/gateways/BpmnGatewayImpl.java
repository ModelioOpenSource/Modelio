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
package org.modelio.metamodel.impl.bpmn.gateways;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewayData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00012282-c4c0-1fd8-97fe-001ec947cd2a")
public abstract class BpmnGatewayImpl extends BpmnFlowNodeImpl implements BpmnGateway {
    @objid ("e53232a3-c977-4ffe-bb05-61a0d42d74fb")
    @Override
    public BpmnGatewayDirection getGatewayDirection() {
        return (BpmnGatewayDirection) getAttVal(((BpmnGatewaySmClass)getClassOf()).getGatewayDirectionAtt());
    }

    @objid ("7d25734d-3af9-4158-ba17-5913ef00d173")
    @Override
    public void setGatewayDirection(BpmnGatewayDirection value) {
        setAttVal(((BpmnGatewaySmClass)getClassOf()).getGatewayDirectionAtt(), value);
    }

    @objid ("673b4c99-a351-4e13-abed-d302d5e37fcf")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("d7d93a3b-645b-4d58-9f69-cc59706a0164")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("0fb3d5b1-ec1b-4f1f-a0f9-5bbba856ffa6")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnGateway(this);
    }

}
