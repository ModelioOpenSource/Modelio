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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * Activity v0.0.9054
 * 
 * 
 * An activity specifies the coordination of executions of subordinate behaviors, using a control and data flow model. The subordinate behaviors coordinated by these models may be initiated because other behaviors in the model finish executing, because objects and data become available, or because events occur outside the flow. The flow of execution is modeled as activity nodes connected by activity edges. A node can be the execution of a subordinate behavior, such as an arithmetic computation, a call to an operation, or manipulation of object contents. Activity nodes also include flow-of-control constructs, such as synchronization, decision, and concurrency control. 
 * 
 * Activities may form invocation hierarchies invoking other activities, ultimately resolving to individual actions. 
 * 
 * In an object-oriented model, activities are usually invoked indirectly as methods bound to operations that are directly invoked. Activities may describe procedural computation. In this context, they are the methods corresponding to operations on classes. 
 * 
 * Activities may be applied to organizational modeling for business process engineering and workflow. In this context, events often originate inside the system, such as the finishing of a task, but also outside the system, such as a customer call. Activities can also be used for information system modeling to specify system level processes.
 * 
 * In Modelio, an activity can be owned by a NameSpace or an operation. It can also be owned by a structured activity node, but in this case, the activity is "hidden" (visually merged with the ActivityNode).
 */
@objid ("0026094e-c4bf-1fd8-97fe-001ec947cd2a")
public interface Activity extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("561adf8a-cbe5-4716-985a-f2f1f1048bed")
    public static final String MNAME = "Activity";

    /**
     * The metaclass qualified name.
     */
    @objid ("c425d717-7c19-4949-86e5-7c2424e0b98f")
    public static final String MQNAME = "Standard.Activity";

    /**
     * Getter for attribute 'Activity.IsSingleExecution'
     * 
     * Metamodel description:
     * <i>If true, all invocations of the activity are handled by the same execution.</i>
     */
    @objid ("c8904af7-f968-4550-be8e-19d895c8bd4c")
    boolean isIsSingleExecution();

    /**
     * Setter for attribute 'Activity.IsSingleExecution'
     * 
     * Metamodel description:
     * <i>If true, all invocations of the activity are handled by the same execution.</i>
     */
    @objid ("6150afc2-71ab-44ef-a6c6-bc087002212a")
    void setIsSingleExecution(boolean value);

    /**
     * Getter for attribute 'Activity.IsReadOnly'
     * 
     * Metamodel description:
     * <i>If true, this activity must not make any changes to variables outside the activity or to objects. This is an assertion, not an executable property.</i>
     */
    @objid ("17c90e61-dd19-41b1-b885-1281297ff5bd")
    boolean isIsReadOnly();

    /**
     * Setter for attribute 'Activity.IsReadOnly'
     * 
     * Metamodel description:
     * <i>If true, this activity must not make any changes to variables outside the activity or to objects. This is an assertion, not an executable property.</i>
     */
    @objid ("2677a247-0774-4599-b30f-8e73290c176e")
    void setIsReadOnly(boolean value);

    /**
     * Getter for relation 'Activity->OwnedGroup'
     * 
     * Metamodel description:
     * <i>Top-level groups in the activity. </i>
     */
    @objid ("765f3a2a-fb9b-4367-b17e-02f79765ecf4")
    EList<ActivityGroup> getOwnedGroup();

    /**
     * Filtered Getter for relation 'Activity->OwnedGroup'
     * 
     * Metamodel description:
     * <i>Top-level groups in the activity. </i>
     */
    @objid ("755b9da6-b43e-4f72-8ead-0f79b4953d25")
    <T extends ActivityGroup> List<T> getOwnedGroup(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Activity->OwnedNode'
     * 
     * Metamodel description:
     * <i>Nodes coordinated by the activity.</i>
     */
    @objid ("8296fbb5-347b-4e5b-a178-8f9f43baaafa")
    EList<ActivityNode> getOwnedNode();

    /**
     * Filtered Getter for relation 'Activity->OwnedNode'
     * 
     * Metamodel description:
     * <i>Nodes coordinated by the activity.</i>
     */
    @objid ("fe954622-1e7c-41fe-87c4-a6f2f92616ce")
    <T extends ActivityNode> List<T> getOwnedNode(java.lang.Class<T> filterClass);

}
