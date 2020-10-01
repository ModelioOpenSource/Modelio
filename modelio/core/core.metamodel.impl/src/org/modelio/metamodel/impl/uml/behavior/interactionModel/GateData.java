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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00459ffc-c4bf-1fd8-97fe-001ec947cd2a")
public class GateData extends MessageEndData {
    @objid ("fe479af8-d99a-4c18-9258-bf5158da688c")
     SmObjectImpl mOwnerUse;

    @objid ("f5b1356f-73fe-4990-a8cc-37697c7bf97b")
     List<SmObjectImpl> mActual = null;

    @objid ("3373912d-1525-4bf5-bdb8-36d946e1030c")
     SmObjectImpl mOwnerInteraction;

    @objid ("a1cc9247-b3bb-40c7-87bb-92010b0ad325")
     SmObjectImpl mOwnerFragment;

    @objid ("aa8cf1f7-1ee2-4254-9669-a0603546a61a")
     SmObjectImpl mFormal;

    @objid ("ef39b1d3-2913-42d2-835c-e3903c4ea687")
    public GateData(GateSmClass smClass) {
        super(smClass);
    }

}
