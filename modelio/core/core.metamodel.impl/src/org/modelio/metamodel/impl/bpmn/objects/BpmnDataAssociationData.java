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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0004de90-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataAssociationData extends BpmnBaseElementData {
    @objid ("fbcc8b89-a749-462d-93ec-7b4739ccfb4c")
     Object mAssignment = "";

    @objid ("24c1fe42-51ef-4407-891e-5fe7f7a34f01")
     Object mTransfomation = "";

    @objid ("8c2c10b8-1066-4ba3-8093-4ed9417d3636")
     Object mLanguage = "";

    @objid ("f05dff0f-6bc8-4421-81fe-964f658ee582")
     List<SmObjectImpl> mSourceRef = null;

    @objid ("4ccdf8c5-3e95-47a8-866e-3222f63950d4")
     SmObjectImpl mTargetRef;

    @objid ("d65087c0-9b94-41fd-bb29-e2a35c2537f1")
     SmObjectImpl mEndingActivity;

    @objid ("7b1cbba4-b5a0-4df4-8521-6aeb6d6838a8")
     SmObjectImpl mStartingActivity;

    @objid ("9522d682-87f9-4587-bfc7-34d22b80436d")
     SmObjectImpl mStartingEvent;

    @objid ("300b9ee9-6a6d-415b-89f2-8e3b59633390")
     List<SmObjectImpl> mVisualShortCut = null;

    @objid ("1675b83b-9d1b-404e-b034-d36a72d3f334")
     SmObjectImpl mEndingEvent;

    @objid ("cdec2637-8cd6-4191-affa-4a829b0b6b32")
    public BpmnDataAssociationData(BpmnDataAssociationSmClass smClass) {
        super(smClass);
    }

}
