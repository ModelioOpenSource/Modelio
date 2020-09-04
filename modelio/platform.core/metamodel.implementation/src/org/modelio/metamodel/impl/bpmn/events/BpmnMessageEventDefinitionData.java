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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00913f52-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnMessageEventDefinitionData extends BpmnEventDefinitionData {
    @objid ("2f0e5c6b-7d2c-4222-9be5-f2587e356fe5")
     SmObjectImpl mMessageRef;

    @objid ("8df56c24-e44c-4021-bc42-3ce278c4d460")
     List<SmObjectImpl> mOperationRef = null;

    @objid ("6b14653d-b296-4ace-a883-b2ec0099fb63")
    public BpmnMessageEventDefinitionData(BpmnMessageEventDefinitionSmClass smClass) {
        super(smClass);
    }

}
