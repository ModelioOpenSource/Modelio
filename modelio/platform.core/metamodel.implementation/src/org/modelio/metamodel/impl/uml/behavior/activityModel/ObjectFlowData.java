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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlowEffectKind;

@objid ("0038ffd6-c4bf-1fd8-97fe-001ec947cd2a")
public class ObjectFlowData extends ActivityEdgeData {
    @objid ("e6c3d68d-37b0-4754-ac58-e578a915f0b5")
     Object mTransformationBehavior = "";

    @objid ("78cb0e10-69cb-4f21-b42e-a81b4e439a34")
     Object mSelectionBehavior = "";

    @objid ("66424c2a-cc23-44dd-ad9a-11861d15a6c3")
     Object mIsMultiCast = false;

    @objid ("c2cee41f-34b6-4e3a-8bb8-e906903fc62e")
     Object mIsMultiReceive = false;

    @objid ("dac68db7-527b-4d0e-baea-405f4196d299")
     Object mEffect = ObjectFlowEffectKind.READFLOW;

    @objid ("e24a24d0-a1c5-47d9-8833-e16cc6105883")
    public ObjectFlowData(ObjectFlowSmClass smClass) {
        super(smClass);
    }

}
