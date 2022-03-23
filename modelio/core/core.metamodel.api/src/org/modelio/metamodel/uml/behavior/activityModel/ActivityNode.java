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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * ActivityNode v0.0.9054
 * 
 * 
 * The execution of an action represents some transformation or processing in the modeled
 * system, be it a computer system or otherwise.
 * 
 * An action may have sets of incoming and outgoing activity edges that specify control flow and data flow to and from other nodes. An action will not begin execution until all of its input conditions are satisfied. The completion of the execution of an action may enable the execution of a set  of successor nodes and actions that take their inputs from the outputs of the action.
 * 
 * Action can have pre- and postconditions (using constraints).
 */
@objid ("0028adde-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityNode extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("9ad7acd5-3651-4d82-a598-0ff7d7b4dd20")
    public static final String MNAME = "ActivityNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("9a3c2448-62e7-4895-ad08-acf07322d81e")
    public static final String MQNAME = "Standard.ActivityNode";

    /**
     * Getter for relation 'ActivityNode->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c9003b1e-b6f7-49e4-80f0-ba8e86651fde")
    Activity getOwner();

    /**
     * Setter for relation 'ActivityNode->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("66cc5d5b-c2fe-4cb4-b896-619d93c1691f")
    void setOwner(Activity value);

    /**
     * Getter for relation 'ActivityNode->OwnerPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bc456e3e-8c92-4bf5-82d8-705568c510ca")
    ActivityPartition getOwnerPartition();

    /**
     * Setter for relation 'ActivityNode->OwnerPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d8ad5af4-f0cf-4dad-87aa-0c382274ef02")
    void setOwnerPartition(ActivityPartition value);

    /**
     * Getter for relation 'ActivityNode->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8a6cddaa-d99d-44aa-82c7-391d09389347")
    EList<ActivityEdge> getIncoming();

    /**
     * Filtered Getter for relation 'ActivityNode->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("265327a3-56b9-47fb-a2cf-d8e91ffb33ec")
    <T extends ActivityEdge> List<T> getIncoming(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityNode->OwnerClause'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3d953f08-df43-452f-b1af-dfb3c4743b58")
    Clause getOwnerClause();

    /**
     * Setter for relation 'ActivityNode->OwnerClause'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("98b8d4bf-24f2-4bfc-9c2d-c103cf3caeab")
    void setOwnerClause(Clause value);

    /**
     * Getter for relation 'ActivityNode->OwnerNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62e15cac-9409-4672-ab43-7c51f9c5541e")
    StructuredActivityNode getOwnerNode();

    /**
     * Setter for relation 'ActivityNode->OwnerNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2e7b77fa-7a5e-4c70-83fe-378727f508f7")
    void setOwnerNode(StructuredActivityNode value);

    /**
     * Getter for relation 'ActivityNode->Outgoing'
     * 
     * Metamodel description:
     * <i>Edges that have the node as source.</i>
     */
    @objid ("1d1ba5e7-0d9f-4fea-9689-07c08ecaf2b4")
    EList<ActivityEdge> getOutgoing();

    /**
     * Filtered Getter for relation 'ActivityNode->Outgoing'
     * 
     * Metamodel description:
     * <i>Edges that have the node as source.</i>
     */
    @objid ("069488c2-7dc2-49df-9676-e79f11c75c6e")
    <T extends ActivityEdge> List<T> getOutgoing(java.lang.Class<T> filterClass);

}
