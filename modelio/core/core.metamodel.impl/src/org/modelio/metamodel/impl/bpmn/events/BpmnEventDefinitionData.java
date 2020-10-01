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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008db116-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnEventDefinitionData extends BpmnBaseElementData {
    @objid ("e7f49812-5ac3-4c3b-ae7f-7ba834ab2db7")
     SmObjectImpl mDefined;

    @objid ("37ceb742-674c-4006-a4e3-4930da7dd83f")
     List<SmObjectImpl> mLoopRef = null;

    @objid ("2c1cea8b-5d4e-468e-9d16-d49f4b684696")
    public BpmnEventDefinitionData(BpmnEventDefinitionSmClass smClass) {
        super(smClass);
    }

}
