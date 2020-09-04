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
package org.modelio.metamodel.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnDataStore v0.0.9054
 * 
 * 
 * A DataStore provides a mechanism for Activities to retrieve or update stored information that will persist beyond the scope of the Process.
 * 
 * Ownership :
 * 
 * A datastore belongs to a FlowElement container (Process) or a SubProcess
 */
@objid ("0003e1c0-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataStore extends BpmnItemAwareElement {
    /**
     * The metaclass simple name.
     */
    @objid ("ae540fd5-bbc6-4ec5-893e-c31f65ca6886")
    public static final String MNAME = "BpmnDataStore";

    /**
     * The metaclass qualified name.
     */
    @objid ("bc14dc76-8460-43b7-9880-bcaed40d9626")
    public static final String MQNAME = "Standard.BpmnDataStore";

    /**
     * Getter for attribute 'BpmnDataStore.Capacity'
     * 
     * Metamodel description:
     * <i>Defines the capacity of the Data Store. This is not needed if the isUnlimited attribute is set to true.</i>
     */
    @objid ("3b229765-ca47-40ca-b2bd-220c384d2dc3")
    int getCapacity();

    /**
     * Setter for attribute 'BpmnDataStore.Capacity'
     * 
     * Metamodel description:
     * <i>Defines the capacity of the Data Store. This is not needed if the isUnlimited attribute is set to true.</i>
     */
    @objid ("7b12587e-5a33-4857-a22c-7e9747c75102")
    void setCapacity(int value);

    /**
     * Getter for attribute 'BpmnDataStore.IsUnlimited'
     * 
     * Metamodel description:
     * <i>If isUnlimited is set to true, then the capacity of a Data Store is set as unlimited and will override any value of the capacity attribute.</i>
     */
    @objid ("a1aa28d1-4c4b-406d-80b6-9c70f0e138d2")
    boolean isIsUnlimited();

    /**
     * Setter for attribute 'BpmnDataStore.IsUnlimited'
     * 
     * Metamodel description:
     * <i>If isUnlimited is set to true, then the capacity of a Data Store is set as unlimited and will override any value of the capacity attribute.</i>
     */
    @objid ("3c56365a-d48f-447d-a5ed-c75ba03161eb")
    void setIsUnlimited(boolean value);

}
