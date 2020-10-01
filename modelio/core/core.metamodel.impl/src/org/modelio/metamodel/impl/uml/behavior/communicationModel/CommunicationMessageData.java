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
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("005b5928-c4bf-1fd8-97fe-001ec947cd2a")
public class CommunicationMessageData extends UmlModelElementData {
    @objid ("1098d850-eb3d-4c08-b7d0-896275e509b9")
     Object mArgument = "";

    @objid ("f3374e0c-2374-4f9d-a410-8cd0a0e2a598")
     Object mSequence = "";

    @objid ("c39f87e0-cf6a-420e-9d73-cf2a89ae1f17")
     Object mSortOfMessage = MessageSort.SYNCCALL;

    @objid ("f51303f8-67ac-4b9c-841f-2c5553ac29ad")
     List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("a58dcc7c-06fe-4b41-b387-6b3270862606")
     SmObjectImpl mChannel;

    @objid ("33ac7221-cfcc-486e-8c59-23764b4e8fe2")
     SmObjectImpl mInvertedChannel;

    @objid ("f30594de-d075-4cd8-b243-1bd02c69969d")
     SmObjectImpl mInvoked;

    @objid ("c328931b-e1c7-46da-adf3-559a5944d9d6")
     SmObjectImpl mSignalSignature;

    @objid ("63696536-5a26-47c2-a6e7-cf172cbd132f")
    public CommunicationMessageData(CommunicationMessageSmClass smClass) {
        super(smClass);
    }

}
