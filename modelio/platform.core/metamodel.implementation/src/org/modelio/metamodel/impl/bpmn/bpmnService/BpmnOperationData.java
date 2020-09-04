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
package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000daf70-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnOperationData extends BpmnBaseElementData {
    @objid ("7c5d168b-d128-4dc0-a441-517623a37e49")
     List<SmObjectImpl> mSender = null;

    @objid ("5bef90c5-c2ed-4189-8a2a-76689c9f9042")
     SmObjectImpl mInMessageRef;

    @objid ("f943e37c-dcc8-4feb-8349-06c6cd99233a")
     List<SmObjectImpl> mCaller = null;

    @objid ("3a6ff0ef-914b-4fd6-8c9a-a2f786a08e55")
     SmObjectImpl mOutMessageRef;

    @objid ("897dcbca-c321-4f4a-b3b2-cd2de5c9989a")
     List<SmObjectImpl> mEventDefinition = null;

    @objid ("0e58f2b7-78d0-4a6c-9de1-c2181286f23f")
     SmObjectImpl mBpmnInterfaceRef;

    @objid ("d51743c8-7ea4-40a6-b410-acde251b0864")
     List<SmObjectImpl> mReceiver = null;

    @objid ("81c0f193-ea05-471b-88b3-943a4e0780f3")
    public BpmnOperationData(BpmnOperationSmClass smClass) {
        super(smClass);
    }

}
