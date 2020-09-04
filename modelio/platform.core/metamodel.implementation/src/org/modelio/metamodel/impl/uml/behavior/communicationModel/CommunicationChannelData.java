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
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("67558b34-b4c9-476e-939c-3da1dcc447df")
public class CommunicationChannelData extends UmlModelElementData {
    @objid ("75254fe4-426f-4dd8-9359-8684dd398abe")
     List<SmObjectImpl> mStartToEndMessage = null;

    @objid ("ec33b896-0413-464d-8d05-8b7d65cd52ee")
     SmObjectImpl mChannel;

    @objid ("c9ca16f8-b1cf-485c-84ca-cf5693a3ca31")
     SmObjectImpl mStart;

    @objid ("a0443d37-6b3d-4a09-a684-9fec4a4d03ff")
     SmObjectImpl mNaryChannel;

    @objid ("187c73ea-ffc2-40bb-ae31-1549836de08c")
     List<SmObjectImpl> mEndToStartMessage = null;

    @objid ("7207c3a1-c820-49b2-a88b-a96d1d7814af")
     SmObjectImpl mEnd;

    @objid ("d75f30e7-0ea7-4daf-b68d-266ad893aee6")
    public CommunicationChannelData(CommunicationChannelSmClass smClass) {
        super(smClass);
    }

}
