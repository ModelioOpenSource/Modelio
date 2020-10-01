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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;

/**
 * StructuredActivityNode v0.0.9054
 * 
 * 
 * A structured activity node represents a structured portion of the activity that is not shared with any other structured node, except for nesting. It may have control edges connected to it and pins. 
 * The execution of any embedded actions may not begin until the structured activity node has received its object and control tokens. The availability of output tokens from the structured activity node does not occur until all embedded actions have completed execution.
 * 
 * In order to avoid race conditions or other concurrency-related  problems, it is sometimes necessary to isolate the effects of a group of actions from the effects of actions outside the group. This may be indicated by setting the mustIsolate attribute to true on a structured activity node. In this case, any object used by an action within the node cannot be accessed by any action outside the node until the structured activity node as a whole completes. 
 */
@objid ("003df874-c4bf-1fd8-97fe-001ec947cd2a")
public interface StructuredActivityNode extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("11337dfd-4a5c-4f9f-87f1-275dce527c2f")
    public static final String MNAME = "StructuredActivityNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("b6bea657-6a37-4801-880d-05beb113e6e1")
    public static final String MQNAME = "Standard.StructuredActivityNode";

    /**
     * Getter for attribute 'StructuredActivityNode.MustIsolate'
     * 
     * Metamodel description:
     * <i>If true, then the actions in the node execute in isolation from actions outside the node. 
     * Default value is false.
     * 
     * Because of the concurrent nature of the execution of actions within and across activities, it can be difficult to guarantee 
     * the consistent access and modification of object memory. In order to avoid race conditions or other concurrency-related 
     * problems, it is sometimes necessary to isolate the effects of a group of actions from the effects of actions outside the 
     * true on a structured activity node. If a structured 
     * group. This may be indicated by setting the mustIsolate attribute to 
     * activity node is 'isolated',  then any object used by an action within the node cannot be accessed by any action outside the 
     * node until the structured activity node as a whole completes. Any concurrent actions that would result in accessing such 
     * objects are required to have their execution deferred until the completion of the node.
     * UML Superstructure Specification, v2.1        425
     * 
     * Note : Any required isolation may be achieved using a locking mechanism, or it may simply sequentialize execution to avoid 
     * concurrency conflicts. Isolation is different from the property of 'atomicity',  which is the guarantee that a group of actions 
     * either all complete successfully or have no effect at all. Atomicity generally requires a rollback mechanism to prevent 
     * committing partial results. </i>
     */
    @objid ("3161d5d8-3571-4319-9c1d-399f74af40cc")
    boolean isMustIsolate();

    /**
     * Setter for attribute 'StructuredActivityNode.MustIsolate'
     * 
     * Metamodel description:
     * <i>If true, then the actions in the node execute in isolation from actions outside the node. 
     * Default value is false.
     * 
     * Because of the concurrent nature of the execution of actions within and across activities, it can be difficult to guarantee 
     * the consistent access and modification of object memory. In order to avoid race conditions or other concurrency-related 
     * problems, it is sometimes necessary to isolate the effects of a group of actions from the effects of actions outside the 
     * true on a structured activity node. If a structured 
     * group. This may be indicated by setting the mustIsolate attribute to 
     * activity node is 'isolated',  then any object used by an action within the node cannot be accessed by any action outside the 
     * node until the structured activity node as a whole completes. Any concurrent actions that would result in accessing such 
     * objects are required to have their execution deferred until the completion of the node.
     * UML Superstructure Specification, v2.1        425
     * 
     * Note : Any required isolation may be achieved using a locking mechanism, or it may simply sequentialize execution to avoid 
     * concurrency conflicts. Isolation is different from the property of 'atomicity',  which is the guarantee that a group of actions 
     * either all complete successfully or have no effect at all. Atomicity generally requires a rollback mechanism to prevent 
     * committing partial results. </i>
     */
    @objid ("c56d6b94-0808-4631-b351-2f914cb688d7")
    void setMustIsolate(boolean value);

    /**
     * Getter for relation 'StructuredActivityNode->Body'
     * 
     * Metamodel description:
     * <i>Implementation of the structured activity node.</i>
     */
    @objid ("751de91d-f286-47fd-8988-52624e428bda")
    EList<ActivityNode> getBody();

    /**
     * Filtered Getter for relation 'StructuredActivityNode->Body'
     * 
     * Metamodel description:
     * <i>Implementation of the structured activity node.</i>
     */
    @objid ("91d83445-f61a-4383-82e1-29002931dd90")
    <T extends ActivityNode> List<T> getBody(java.lang.Class<T> filterClass);

}
