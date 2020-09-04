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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007fcaf6-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnComplexBehaviorDefinitionData extends BpmnBaseElementData {
    @objid ("99711145-45d2-46a1-8ef9-b64fab81db36")
     Object mCondition = "";

    @objid ("223cc290-229a-4a9d-a8dd-fa272c6c402f")
     SmObjectImpl mOwner;

    @objid ("679dae47-b7a3-49a8-86e4-9ecfd5cc129a")
     SmObjectImpl mEvent;

    @objid ("e152c76e-1006-4e4c-a7b8-481f725c077c")
    public BpmnComplexBehaviorDefinitionData(BpmnComplexBehaviorDefinitionSmClass smClass) {
        super(smClass);
    }

}
