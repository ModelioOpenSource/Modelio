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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007a05d0-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnFlowNodeData extends BpmnFlowElementData {
    @objid ("c3901735-efde-4245-82de-ff023b7de6a7")
     List<SmObjectImpl> mOutgoing = null;

    @objid ("24791bd0-316c-4383-b8a5-80a59fde2591")
     List<SmObjectImpl> mResource = null;

    @objid ("14b21c50-20bc-42a8-b973-8e5f4f3e9585")
     List<SmObjectImpl> mIncoming = null;

    @objid ("9060642b-c931-4342-b0e5-9469b1f3fffb")
    public BpmnFlowNodeData(BpmnFlowNodeSmClass smClass) {
        super(smClass);
    }

}
