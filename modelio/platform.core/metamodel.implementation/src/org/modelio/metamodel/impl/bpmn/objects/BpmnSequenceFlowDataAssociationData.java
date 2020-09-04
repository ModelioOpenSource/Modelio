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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00057d32-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnSequenceFlowDataAssociationData extends BpmnBaseElementData {
    @objid ("fe78bea7-4240-479e-962b-ac9b6d1d1ff2")
     SmObjectImpl mConnected;

    @objid ("5ec5cf96-6bf5-4587-bcdc-a8521f7adbcc")
     List<SmObjectImpl> mDataAssociation = null;

    @objid ("a695047c-c64f-49c9-8e1c-9f40ad9afad7")
    public BpmnSequenceFlowDataAssociationData(BpmnSequenceFlowDataAssociationSmClass smClass) {
        super(smClass);
    }

}
