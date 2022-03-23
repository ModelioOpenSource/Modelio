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

package org.modelio.metamodel.impl.bpmn.flows;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007c30f8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMessageData extends BpmnSharedElementData {
    @objid ("93bebe2c-251b-4ba7-bff9-d99e9c783307")
    List<SmObjectImpl> mOutputMessage = null;

    @objid ("46ec1f77-facf-4aed-a6c6-00e993619062")
    SmObjectImpl mItemRef;

    @objid ("b72d8315-3e09-4b38-8177-8c014da2dcb0")
    List<SmObjectImpl> mEventDefinition = null;

    @objid ("daf22810-e420-4e9d-a3e5-55c63e118b38")
    List<SmObjectImpl> mSender = null;

    @objid ("7f649a37-4121-4894-8fb6-8c1cfd86ec50")
    List<SmObjectImpl> mInputMessage = null;

    @objid ("f17a202c-6434-4f1e-9bd4-862005122978")
    List<SmObjectImpl> mReceiver = null;

    @objid ("653792d6-9c01-4764-b598-c29198ad2102")
    List<SmObjectImpl> mMessageFlow = null;

    @objid ("f5e46620-ccae-40f1-a004-fbc3431ff7a1")
    SmObjectImpl mCollaboration;

    @objid ("2af9f7d9-cf57-430b-b570-cb030b842e85")
    public  BpmnMessageData(BpmnMessageSmClass smClass) {
        super(smClass);
    }

}
