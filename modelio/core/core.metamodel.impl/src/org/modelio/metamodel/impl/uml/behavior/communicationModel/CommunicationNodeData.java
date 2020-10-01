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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("005adeee-c4bf-1fd8-97fe-001ec947cd2a")
public class CommunicationNodeData extends UmlModelElementData {
    @objid ("7c1447b8-62b4-424d-bb10-dfa65af8e271")
     Object mSelector = "";

    @objid ("34034027-5e1a-4c8a-b698-6c65a5881bf9")
     SmObjectImpl mOwner;

    @objid ("107e77c7-7333-48a4-99a7-361b32d9d75a")
     SmObjectImpl mRepresented;

    @objid ("3b29cf6b-648b-429b-a402-2707ac5ed188")
     List<SmObjectImpl> mStarted = null;

    @objid ("ce884d6b-44e3-4451-a401-b974b30c2b2e")
     List<SmObjectImpl> mEnded = null;

    @objid ("f2ec6e2a-2a2d-4abc-a7da-b08f24d2ee72")
    public CommunicationNodeData(CommunicationNodeSmClass smClass) {
        super(smClass);
    }

}
