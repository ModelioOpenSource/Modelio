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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("004999e0-c4bf-1fd8-97fe-001ec947cd2a")
public class MessageData extends UmlModelElementData {
    @objid ("607364ff-ffea-4f21-b58e-6088aa6e88c7")
     Object mArgument = "";

    @objid ("a208202c-7bf5-4f2e-978d-976848d6f8e5")
     Object mKindOfMessage = MessageKind.UNKNOWNKIND;

    @objid ("0af16146-9303-4a1c-88d2-5d8ba082aaa2")
     Object mSortOfMessage = MessageSort.SYNCCALL;

    @objid ("0e6e9528-21af-443f-882d-7e800017c018")
     Object mSequence = "";

    @objid ("6d1aabe2-c525-44a4-9bb0-768277ebb8cb")
     SmObjectImpl mSignalSignature;

    @objid ("8714da2a-d219-4106-b2bf-7f8d2b660321")
     SmObjectImpl mReceiveEvent;

    @objid ("551549cf-439d-48b1-a611-1971136ade47")
     SmObjectImpl mSendEvent;

    @objid ("63b5c983-616f-4a60-b0cc-301d716f4a08")
     SmObjectImpl mInvoked;

    @objid ("0436985f-8dea-44c5-84db-02c4d58938dd")
     List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("ad7241d3-1769-4846-a540-6390ad03005c")
    public MessageData(MessageSmClass smClass) {
        super(smClass);
    }

}
