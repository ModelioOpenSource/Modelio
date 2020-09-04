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
package org.modelio.metamodel.impl.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0096dd72-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnTimerEventDefinitionData extends BpmnEventDefinitionData {
    @objid ("96914890-b71b-4865-82c3-39d75af1a7ee")
     Object mTimeCycle = "";

    @objid ("7d944770-d5dd-486d-9eee-5e772e5fc482")
     Object mTimeDate = "";

    @objid ("328c9946-2620-4baf-b367-ef2d2b32eca0")
     Object mTimeDuration = "";

    @objid ("813f8f60-c85a-43a8-a4cf-075bed7b78e2")
    public BpmnTimerEventDefinitionData(BpmnTimerEventDefinitionSmClass smClass) {
        super(smClass);
    }

}
