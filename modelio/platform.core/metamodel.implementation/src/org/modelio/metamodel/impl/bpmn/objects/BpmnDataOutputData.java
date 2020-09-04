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
package org.modelio.metamodel.impl.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00061d64-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataOutputData extends BpmnItemAwareElementData {
    @objid ("8ee3acb8-8586-4741-9246-f1562a11ebea")
     Object mIsCollection = false;

    @objid ("65dcea27-61d8-46ad-84d3-b3b36566a1dd")
     SmObjectImpl mOwnerActivity;

    @objid ("f983c6c0-8528-4fcb-b06c-a6c6fed38927")
     SmObjectImpl mCatched;

    @objid ("634a0a64-37fd-4182-bd5c-86a39a22f899")
     SmObjectImpl mOwnerLoopCharacteristics;

    @objid ("ee6f6712-85e4-4c65-8b30-492b25c739ab")
    public BpmnDataOutputData(BpmnDataOutputSmClass smClass) {
        super(smClass);
    }

}
