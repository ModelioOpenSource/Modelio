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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("004a25fe-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class MessageEndData extends OccurrenceSpecificationData {
    @objid ("1d0d0448-05b3-4426-9e68-2e1641cbdb3f")
    SmObjectImpl mReceivedMessage;

    @objid ("c357220a-3ca6-4ef9-a231-a4f4c7d77ca0")
    SmObjectImpl mSentMessage;

    @objid ("bb89ed53-5a41-455a-9131-eec1c7056916")
    public  MessageEndData(MessageEndSmClass smClass) {
        super(smClass);
    }

}
