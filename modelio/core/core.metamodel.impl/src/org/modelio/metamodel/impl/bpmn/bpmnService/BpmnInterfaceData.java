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

package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000e4fac-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnInterfaceData extends BpmnSharedElementData {
    @objid ("b366ae17-08a1-4762-aeeb-dd6326ecc766")
    List<SmObjectImpl> mOperation = null;

    @objid ("5d38fc16-1705-4dff-9d75-f4ea4f9ac26f")
    List<SmObjectImpl> mParticipantRef = null;

    @objid ("76c5d403-5d38-42a6-a2cb-e027b38d3b11")
    public  BpmnInterfaceData(BpmnInterfaceSmClass smClass) {
        super(smClass);
    }

}
