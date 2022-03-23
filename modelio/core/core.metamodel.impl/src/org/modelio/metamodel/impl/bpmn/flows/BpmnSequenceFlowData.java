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

package org.modelio.metamodel.impl.bpmn.flows;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007d254e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSequenceFlowData extends BpmnFlowElementData {
    @objid ("53137239-0896-4b4b-9c7c-319bfcd8c0c9")
    Object mIsImmediate = false;

    @objid ("f38a6ff7-e45b-4597-acf9-4d849dae7ae9")
    Object mConditionExpression = "";

    @objid ("957d3d7d-3779-4a09-a3d1-ab311b591eb9")
    SmObjectImpl mSourceRef;

    @objid ("b572e59e-f661-449d-891c-372f51371f40")
    SmObjectImpl mTargetRef;

    @objid ("ea830527-0061-4e9f-be90-0066748b8fe5")
    SmObjectImpl mDefaultOfInclusive;

    @objid ("0dbefb73-0983-4c8e-8904-66906e271a3b")
    SmObjectImpl mDefaultFrom;

    @objid ("d2cfbded-e441-4cca-97bc-a1bc3767e3f3")
    SmObjectImpl mDefaultOfExclusive;

    @objid ("e57f3a75-7492-4fc5-b76a-556dbb27bf50")
    List<SmObjectImpl> mConnector = null;

    @objid ("6f9c4aeb-fd44-41e0-ac33-bb150d556d91")
    SmObjectImpl mDefaultOfComplex;

    @objid ("11b7de06-cbfc-4dfb-9219-77d8b5955045")
    public  BpmnSequenceFlowData(BpmnSequenceFlowSmClass smClass) {
        super(smClass);
    }

}
