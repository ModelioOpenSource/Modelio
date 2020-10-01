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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.GeneralClassData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0042606c-c4bf-1fd8-97fe-001ec947cd2a")
public class SignalData extends GeneralClassData {
    @objid ("ee113fb4-92c0-4163-ae2d-90dcaf0280d4")
     Object mIsEvent = false;

    @objid ("accf73ae-718d-43f9-866a-02f5cf6bd61a")
     Object mIsException = false;

    @objid ("8e1a0746-db71-4ab6-8749-8626d53147d5")
     List<SmObjectImpl> mSender = null;

    @objid ("d1a24b78-c9dc-4792-a050-57083ac88e0c")
     List<SmObjectImpl> mUsage = null;

    @objid ("920bb889-5988-4b58-96ce-0d6cbc11e8fd")
     List<SmObjectImpl> mSends = null;

    @objid ("b8829aeb-69ea-424c-87b4-6b8181a3d6bf")
     SmObjectImpl mPBase;

    @objid ("f7df43dd-f272-4f24-aeb7-929b745b3741")
     SmObjectImpl mOBase;

    @objid ("2ae61296-090c-47d3-a323-60d9c33ae95a")
     List<SmObjectImpl> mCommunicationUsage = null;

    @objid ("30e326fc-fed1-4c49-8e2e-cc192271a173")
     List<SmObjectImpl> mDOccurence = null;

    @objid ("21c18dfd-b2b2-4db3-9989-6da48e1beed3")
     List<SmObjectImpl> mEOccurence = null;

    @objid ("ebc8a0cd-f053-463e-9741-9bce0d316040")
     SmObjectImpl mBase;

    @objid ("b7abfc4c-f8be-4214-8547-1a05da422f20")
     List<SmObjectImpl> mReceiver = null;

    @objid ("fd7adc4d-7930-42f7-8e1a-64508faafd8f")
    public SignalData(SignalSmClass smClass) {
        super(smClass);
    }

}
