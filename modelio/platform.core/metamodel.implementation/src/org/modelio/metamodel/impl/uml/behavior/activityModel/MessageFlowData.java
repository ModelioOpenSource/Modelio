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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00384dac-c4bf-1fd8-97fe-001ec947cd2a")
public class MessageFlowData extends ActivityEdgeData {
    @objid ("21eb7636-07c3-4d0e-8437-5d2a1e5e6c45")
     SmObjectImpl mTargetPartition;

    @objid ("e6d1151c-8987-4900-839f-f4ae0784b6e6")
     SmObjectImpl mSourcePartition;

    @objid ("fb64fb55-fbc4-4d44-8936-a76f1d04c9f9")
    public MessageFlowData(MessageFlowSmClass smClass) {
        super(smClass);
    }

}
