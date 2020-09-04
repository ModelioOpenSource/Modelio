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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("002a3320-c4bf-1fd8-97fe-001ec947cd2a")
public class ActivityPartitionData extends ActivityGroupData {
    @objid ("bc358383-46a9-4ffe-8266-a8c6a68ceccd")
     Object mIsDimension = false;

    @objid ("e785a4d8-6063-4818-bdb2-dcfac7aed885")
     Object mIsExternal = false;

    @objid ("391cb274-7ca4-410a-8b4b-0d3ddcb0cad7")
     SmObjectImpl mRepresented;

    @objid ("686227a5-1eeb-440b-9788-22b0d2d49f0b")
     List<SmObjectImpl> mContainedNode = null;

    @objid ("1f2ced54-336e-4bc4-9fa8-96ab55c9ddf7")
     List<SmObjectImpl> mOutgoing = null;

    @objid ("6a376f90-6103-4c53-bdaa-e3e20cf1e217")
     SmObjectImpl mSuperPartition;

    @objid ("2cb9f294-990d-461d-9f02-1b9afaea06a4")
     List<SmObjectImpl> mSubPartition = null;

    @objid ("2cc1dd4a-690e-41f9-985e-c8bf14f4f44e")
     List<SmObjectImpl> mIncoming = null;

    @objid ("12565840-155a-4fc7-91fe-33805eba8cfa")
    public ActivityPartitionData(ActivityPartitionSmClass smClass) {
        super(smClass);
    }

}
