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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;

/**
 * BpmnDataOutput v0.0.9054
 * 
 * 
 * Activities and Processes often required data in order to execute. In addition they may produce data during or as a result of execution. Data that is produced is captured using Data Outputs
 * 
 * A DataOutput belong either to a process or to an activity.
 */
@objid ("0005b81a-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataOutput extends BpmnItemAwareElement {
    /**
     * The metaclass simple name.
     */
    @objid ("0c5871b3-3e50-4354-81a3-e7ad5428e989")
    public static final String MNAME = "BpmnDataOutput";

    /**
     * The metaclass qualified name.
     */
    @objid ("863c77b3-fcd9-41af-b71d-38ce7eb81858")
    public static final String MQNAME = "Standard.BpmnDataOutput";

    /**
     * Getter for attribute 'BpmnDataOutput.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("66f38417-2ce9-44a0-983f-60dab30e86d0")
    boolean isIsCollection();

    /**
     * Setter for attribute 'BpmnDataOutput.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d4232db7-2263-4ea9-99f4-7c84362f794a")
    void setIsCollection(boolean value);

    /**
     * Getter for relation 'BpmnDataOutput->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7fd15da4-5d9b-4e66-b1c4-4f9385d2c43b")
    BpmnActivity getOwnerActivity();

    /**
     * Setter for relation 'BpmnDataOutput->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d28dd805-59e4-4826-961d-9008da5bce14")
    void setOwnerActivity(BpmnActivity value);

    /**
     * Getter for relation 'BpmnDataOutput->Catched'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5cef40b7-d01e-4e27-ae6c-cfe82663d16f")
    BpmnCatchEvent getCatched();

    /**
     * Setter for relation 'BpmnDataOutput->Catched'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a485b34a-eae6-4461-afe9-37a6d826dae0")
    void setCatched(BpmnCatchEvent value);

    /**
     * Getter for relation 'BpmnDataOutput->OwnerLoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9af6d1d8-3249-4686-917e-60020b5a663f")
    BpmnMultiInstanceLoopCharacteristics getOwnerLoopCharacteristics();

    /**
     * Setter for relation 'BpmnDataOutput->OwnerLoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ed178778-72fa-42b1-9ac0-35e3cae5629d")
    void setOwnerLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics value);

}
