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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0097a93c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnComplexGatewayData extends BpmnGatewayData {
    @objid ("ab6d839b-56f8-4d1f-9b12-0661bcf52b5f")
    Object mActivationExpression = "";

    @objid ("33a392fc-1afe-447c-a9db-b1f45138a66c")
    SmObjectImpl mDefaultFlow;

    @objid ("d58e8df2-3362-40c3-8688-fa3c829d02cf")
    public  BpmnComplexGatewayData(BpmnComplexGatewaySmClass smClass) {
        super(smClass);
    }

}
