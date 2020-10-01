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

@objid ("0081d3c8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnReceiveTaskData extends BpmnTaskData {
    @objid ("367248a2-8006-4386-8985-86b04817213f")
     Object mImplementation = "##WebService";

    @objid ("f68bbba8-f6c1-4921-be3b-e0f9c4edd69d")
     Object mInstanciate = false;

    @objid ("56f7d87c-0ccb-4c46-b5e1-92a26f57e090")
     SmObjectImpl mMessageRef;

    @objid ("fb146afc-4ec3-43b6-bc3c-c49130c06d07")
     SmObjectImpl mOperationRef;

    @objid ("3db1978a-be9e-48c3-8051-3f05fa1cfa17")
    public BpmnReceiveTaskData(BpmnReceiveTaskSmClass smClass) {
        super(smClass);
    }

}
