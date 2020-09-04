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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00836ee0-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnServiceTaskData extends BpmnTaskData {
    @objid ("1fe30af4-698c-4b9a-958c-bb126c3b324d")
     Object mImplementation = "##WebService";

    @objid ("60ec8315-509e-40b3-af50-c05997e0823d")
     SmObjectImpl mOperationRef;

    @objid ("1626bb4c-88d7-4116-8af1-c21b1bc584b0")
    public BpmnServiceTaskData(BpmnServiceTaskSmClass smClass) {
        super(smClass);
    }

}
