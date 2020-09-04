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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00814890-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMultiInstanceLoopCharacteristicsData extends BpmnLoopCharacteristicsData {
    @objid ("50ac6fcc-fa1a-4e9e-a959-f2e48d865a7d")
     Object mIsSequencial = false;

    @objid ("333f01df-7f52-4d3b-9321-472355a17810")
     Object mBehavior = MultiInstanceBehavior.ALLBEHAVIOR;

    @objid ("a5a1f969-de80-4a5d-a365-cdde2f2bb2eb")
     Object mLoopCardinality = "";

    @objid ("6e987b74-35bb-4a5e-b8ff-20f06bffad7a")
     Object mCompletionCondition = "";

    @objid ("dba0358b-eb2d-472d-96b1-0d9d9a7f0b27")
     SmObjectImpl mLoopDataInput;

    @objid ("ba1868df-69f1-4160-8831-202451a4237e")
     SmObjectImpl mLoopDataOutputRef;

    @objid ("5f922501-1830-42b7-9610-0bdf341731f9")
     SmObjectImpl mCompletionEventRef;

    @objid ("b702673a-8d61-4e89-84f6-2128daf8b6e7")
     List<SmObjectImpl> mComplexBehaviorDefinition = null;

    @objid ("6a2dff5f-94a5-43d7-8a68-ce5c945ad261")
    public BpmnMultiInstanceLoopCharacteristicsData(BpmnMultiInstanceLoopCharacteristicsSmClass smClass) {
        super(smClass);
    }

}
