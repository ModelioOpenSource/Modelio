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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeData;

@objid ("00015806-c4c0-1fd8-97fe-001ec947cd2a")
public abstract class BpmnGatewayData extends BpmnFlowNodeData {
    @objid ("8c51fd3f-fef1-47df-895c-2c9516c0913c")
     Object mGatewayDirection = BpmnGatewayDirection.UNSPECIFIEDDIRECTION;

    @objid ("2911e96d-2f6f-4f5c-98bc-2c4bc0c5df46")
    public BpmnGatewayData(BpmnGatewaySmClass smClass) {
        super(smClass);
    }

}
