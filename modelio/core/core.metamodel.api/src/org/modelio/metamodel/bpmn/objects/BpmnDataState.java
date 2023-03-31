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

package org.modelio.metamodel.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnDataState v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("00084846-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataState extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3270decd-0ddc-4048-8d1e-b8ce695bbe5c")
    public static final String MNAME = "BpmnDataState";

    /**
     * The metaclass qualified name.
     */
    @objid ("408eaaf7-4955-4538-819d-646e2eea4403")
    public static final String MQNAME = "Standard.BpmnDataState";

    /**
     * Getter for relation 'BpmnDataState->Item'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a9f36242-f1d0-42ad-8e79-fa3e79a6c882")
    BpmnItemAwareElement getItem();

    /**
     * Setter for relation 'BpmnDataState->Item'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9e06f521-b3b0-4f98-a3a8-abedf46c2c4e")
    void setItem(BpmnItemAwareElement value);
}

