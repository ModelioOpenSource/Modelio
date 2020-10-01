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
package org.modelio.metamodel.impl.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0082e34e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSendTaskData extends BpmnTaskData {
    @objid ("cd8f2355-fe34-4719-85db-6dbe43613453")
     Object mImplementation = "##WebService";

    @objid ("5078f83c-d96b-4ff1-be23-5aa79eb30ef7")
     SmObjectImpl mMessageRef;

    @objid ("72e9e55c-857d-46f7-829a-e524c4076302")
     SmObjectImpl mOperationRef;

    @objid ("0f85c5c0-500d-4872-83d6-e95b86a061e9")
    public BpmnSendTaskData(BpmnSendTaskSmClass smClass) {
        super(smClass);
    }

}
