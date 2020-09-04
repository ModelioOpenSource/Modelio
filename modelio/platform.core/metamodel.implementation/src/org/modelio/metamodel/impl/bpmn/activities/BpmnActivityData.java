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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007dc792-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnActivityData extends BpmnFlowNodeData {
    @objid ("c2fc1594-fce4-45cb-93d3-b0a2099768be")
     Object mIsForCompensation = false;

    @objid ("4ad02e93-d9dd-41c0-b797-f5d824bdacb0")
     Object mStartQuantity = 1;

    @objid ("64820610-d020-44c7-aae4-1b478024c500")
     Object mCompletionQuantity = 1;

    @objid ("baefc526-82c0-4504-a4b4-c1ec4e193440")
     List<SmObjectImpl> mCompensateEventDefinitions = null;

    @objid ("3c31f673-e054-4160-8913-594bd02846b6")
     List<SmObjectImpl> mInputSpecification = null;

    @objid ("6764fa5f-f884-4c54-8e06-66eebb4b7db1")
     List<SmObjectImpl> mDataInputAssociation = null;

    @objid ("049436e9-de52-4aab-ac76-62e78a8f8a84")
     List<SmObjectImpl> mOutputSpecification = null;

    @objid ("650bda57-00e9-4fbc-b860-7d4bda903cc1")
     SmObjectImpl mLoopCharacteristics;

    @objid ("f857e515-5edc-44bf-b475-d3136e323213")
     List<SmObjectImpl> mBoundaryEventRef = null;

    @objid ("e8fb15ca-f3bb-486e-a209-db9869e7ba33")
     List<SmObjectImpl> mDataOutputAssociation = null;

    @objid ("0081d4ba-7375-4afb-93cd-18492f03052e")
     SmObjectImpl mDefaultFlow;

    @objid ("7a867d99-ebfb-4ce8-80c1-0041ad607e54")
    public BpmnActivityData(BpmnActivitySmClass smClass) {
        super(smClass);
    }

}
