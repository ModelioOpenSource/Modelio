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
package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnLoopCharacteristics v0.0.9054
 * 
 * 
 * null
 */
@objid ("007ff738-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnLoopCharacteristics extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("47696992-1fa3-404d-a8b2-402dabbc2ee3")
    public static final String MNAME = "BpmnLoopCharacteristics";

    /**
     * The metaclass qualified name.
     */
    @objid ("a65de6da-de69-4ab5-ae4b-5b6969d178b2")
    public static final String MQNAME = "Standard.BpmnLoopCharacteristics";

    /**
     * Getter for relation 'BpmnLoopCharacteristics->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ac35e628-d60e-4162-a1b8-9e6e2c36e800")
    BpmnActivity getOwnerActivity();

    /**
     * Setter for relation 'BpmnLoopCharacteristics->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c120d0ed-3e58-4730-a6a2-a4a89e5b1258")
    void setOwnerActivity(BpmnActivity value);

}
