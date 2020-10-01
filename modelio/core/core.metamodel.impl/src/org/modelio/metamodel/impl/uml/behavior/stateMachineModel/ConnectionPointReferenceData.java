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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("004e9526-c4bf-1fd8-97fe-001ec947cd2a")
public class ConnectionPointReferenceData extends StateVertexData {
    @objid ("43db4d8c-e5d1-4c2b-a65f-14ba47913a96")
     SmObjectImpl mExit;

    @objid ("bf0e2050-b518-43f2-8a29-a36e11d414c6")
     SmObjectImpl mEntry;

    @objid ("a484b770-b698-40f4-a2ca-cc8fc32e5cb0")
     SmObjectImpl mOwnerState;

    @objid ("00a25125-5861-4a20-80da-6411ddf2b7e3")
    public ConnectionPointReferenceData(ConnectionPointReferenceSmClass smClass) {
        super(smClass);
    }

}
