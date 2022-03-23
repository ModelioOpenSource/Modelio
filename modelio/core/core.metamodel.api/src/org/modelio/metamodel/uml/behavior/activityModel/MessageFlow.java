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

package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * MessageFlow v0.0.9054
 * 
 * 
 * A Message Flow is used to show the flow of messages between two entities that are prepared to send and receive them. 
 * 
 * In BPMN, two separate Pools in the Diagram will represent the two entities.
 */
@objid ("0037db42-c4bf-1fd8-97fe-001ec947cd2a")
public interface MessageFlow extends ActivityEdge {
    /**
     * The metaclass simple name.
     */
    @objid ("b0682aa9-5355-4939-a0ac-3ba1a26e2da2")
    public static final String MNAME = "MessageFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("33fb6cf0-98d0-4c6f-aa6a-bfe205ab87c5")
    public static final String MQNAME = "Standard.MessageFlow";

    /**
     * Getter for relation 'MessageFlow->TargetPartition'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("4d437793-469a-4ba8-9ecf-7196fa3974b4")
    ActivityPartition getTargetPartition();

    /**
     * Setter for relation 'MessageFlow->TargetPartition'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("77c20f5d-ff07-455c-b879-f31b40e54f19")
    void setTargetPartition(ActivityPartition value);

    /**
     * Getter for relation 'MessageFlow->SourcePartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3d49eddb-b75c-4f09-bae7-ee3888660687")
    ActivityPartition getSourcePartition();

    /**
     * Setter for relation 'MessageFlow->SourcePartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2caad4f0-28f5-4d7e-af25-7e5c1426225c")
    void setSourcePartition(ActivityPartition value);

}
