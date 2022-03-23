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

package org.modelio.metamodel.impl.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0089ac74-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnCompensateEventDefinitionData extends BpmnEventDefinitionData {
    @objid ("e035e4c9-c601-4e52-9bae-de96ac93be97")
    Object mWaitForCompletion = "";

    @objid ("153ea57d-15b4-4ee6-8dbe-64fb16daa512")
    SmObjectImpl mActivityRef;

    @objid ("068c1cd4-e721-4f5b-839d-586392fbf788")
    public  BpmnCompensateEventDefinitionData(BpmnCompensateEventDefinitionSmClass smClass) {
        super(smClass);
    }

}
