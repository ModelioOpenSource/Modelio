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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0063bfb4-c4bf-1fd8-97fe-001ec947cd2a")
public class InformationFlowData extends UmlModelElementData {
    @objid ("c5ff132e-db18-4bb5-af2e-402230259b99")
     SmObjectImpl mOwner;

    @objid ("b0d2465e-7cca-415f-84d4-667127374a45")
     List<SmObjectImpl> mInformationSource = null;

    @objid ("e50e5b80-4355-4957-9f4f-13d407c66890")
     List<SmObjectImpl> mInformationTarget = null;

    @objid ("6b381fa2-2c37-48dc-9b0b-24b43960cf77")
     List<SmObjectImpl> mRealizingActivityEdge = null;

    @objid ("f1133632-c2e2-412d-be1f-abbc03d1ae9b")
     List<SmObjectImpl> mRealizingCommunicationMessage = null;

    @objid ("14e429b6-3b8d-40a5-a61f-e212abb210af")
     List<SmObjectImpl> mRealizingFeature = null;

    @objid ("29cd59ba-2bb0-45d4-a0e9-d41a1f642589")
     List<SmObjectImpl> mRealizingLink = null;

    @objid ("c4b0d7d1-4dd8-4c42-8457-04ec540bec16")
     List<SmObjectImpl> mRealizingMessage = null;

    @objid ("bd811a03-a56d-486e-8837-058008707d49")
     List<SmObjectImpl> mRealizingNaryLink = null;

    @objid ("013bf024-1a84-463f-8240-ef8eceafe22d")
     List<SmObjectImpl> mConveyed = null;

    @objid ("cb26928f-cc13-4700-a757-63d6331f1337")
     SmObjectImpl mChannel;

    @objid ("fdfe34fc-102e-499f-a2da-4301d52f4c26")
    public InformationFlowData(InformationFlowSmClass smClass) {
        super(smClass);
    }

}
