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
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000765a2-c4c0-1fd8-97fe-001ec947cd2a")
public abstract class BpmnItemAwareElementData extends BpmnFlowElementData {
    @objid ("8fc3d9a0-eb2f-440f-b22c-285a3e76cc67")
     List<SmObjectImpl> mTargetOfDataAssociation = null;

    @objid ("73952009-0fe3-4b03-bd46-ece363f657ea")
     SmObjectImpl mItemSubjectRef;

    @objid ("b03588b6-3176-4f86-acad-436a050864c2")
     SmObjectImpl mDataState;

    @objid ("5465c730-5e10-4d65-9cdc-698820212fb8")
     List<SmObjectImpl> mSourceOfDataAssociation = null;

    @objid ("aaab986e-9bdb-47c0-8c10-4da479761730")
    public BpmnItemAwareElementData(BpmnItemAwareElementSmClass smClass) {
        super(smClass);
    }

}
