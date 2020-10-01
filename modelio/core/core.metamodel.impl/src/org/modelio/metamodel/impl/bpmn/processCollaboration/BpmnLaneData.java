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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0074d240-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLaneData extends BpmnBaseElementData {
    @objid ("9090cfe4-2d71-41f7-83be-7249bbee9717")
     SmObjectImpl mChildLaneSet;

    @objid ("2046f49e-54cf-4e3c-94e7-3026b8e9c733")
     List<SmObjectImpl> mFlowElementRef = null;

    @objid ("d21980c9-bf4e-4bb4-b2aa-9710620699e5")
     SmObjectImpl mLaneSet;

    @objid ("5fd27481-37b0-40de-8d6e-3415091af179")
     SmObjectImpl mBpmnPartitionElementRef;

    @objid ("5eaa4fbe-2d40-429d-bde8-cd7479f6588c")
    public BpmnLaneData(BpmnLaneSmClass smClass) {
        super(smClass);
    }

}
