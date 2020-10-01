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
package org.modelio.metamodel.impl.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0006c016-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataInputData extends BpmnItemAwareElementData {
    @objid ("3f1ea9d8-a2df-43a3-bf95-edbbb4454be5")
     Object mIsCollection = false;

    @objid ("2765fcb0-beea-481b-b48d-13cf9d5d844e")
     SmObjectImpl mOwnerLoopCharacteristics;

    @objid ("3ff63ade-6ea8-42f3-a145-6d43fef239b9")
     SmObjectImpl mOwnerActivity;

    @objid ("48adbb56-15b8-4075-b54e-7c765587a37b")
     SmObjectImpl mOwnerThrowEvent;

    @objid ("72464398-1289-4dce-8552-b26f42ecac6a")
    public BpmnDataInputData(BpmnDataInputSmClass smClass) {
        super(smClass);
    }

}
