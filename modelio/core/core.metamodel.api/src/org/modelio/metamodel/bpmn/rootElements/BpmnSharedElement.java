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

package org.modelio.metamodel.bpmn.rootElements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnSharedElement v2.2.0
 * 
 * 
 * null
 */
@objid ("03349104-b866-40e2-8265-dfd9e1482a0a")
public interface BpmnSharedElement extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("5fd0315a-24dc-496b-b22e-edde1e5ebe3a")
    public static final String MNAME = "BpmnSharedElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("7bed5f82-ff19-4935-8f81-2e807f4dfc02")
    public static final String MQNAME = "Standard.BpmnSharedElement";

    /**
     * Getter for relation 'BpmnSharedElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6275c9aa-9617-4b6f-a0a5-8299530d06a8")
    BpmnSharedDefinitions getOwner();

    /**
     * Setter for relation 'BpmnSharedElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("74195891-d0b9-4ed7-8358-72826bfc15f3")
    void setOwner(BpmnSharedDefinitions value);

}
