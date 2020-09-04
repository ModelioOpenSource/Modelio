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
package org.modelio.metamodel.impl.bpmn.flows;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007cb096-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMessageFlowData extends BpmnBaseElementData {
    @objid ("ae47120f-8197-42fa-9093-95135060813a")
     SmObjectImpl mMessageRef;

    @objid ("4021672a-9ded-4d9c-abf4-97091b7cdedf")
     SmObjectImpl mSourceRef;

    @objid ("f7d523d5-2b1d-4f32-a70c-f1006741621e")
     SmObjectImpl mTargetRef;

    @objid ("7a8e4e92-bd25-4427-bf17-05b561923ccd")
     SmObjectImpl mCollaboration;

    @objid ("d26872ed-1440-4aa8-9020-3f0c56b5cda7")
    public BpmnMessageFlowData(BpmnMessageFlowSmClass smClass) {
        super(smClass);
    }

}
