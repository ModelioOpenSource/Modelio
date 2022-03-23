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
 * ActivityPartition v0.0.9054
 * 
 * 
 * Partitions divide the nodes and edges to constrain and show a view of the contained nodes. Partitions can share contents. They often correspond to organizational units in a business model. They may be used to allocate characteristics or resources among the nodes of an activity.
 * 
 * Partitions do not affect the token flow of the model. They constrain and provide a view on the behaviors invoked in activities. Constraints vary according to the type of element that the partition represents. The following constraints are normative:
 * 
 * 1) Classifier
 * Behaviors of invocations contained by the partition are the responsibility of instances of the classifier represented by the partition. This means the context of invoked behaviors is the classifier. Invoked procedures containing a call to an operation or sending a signal must target objects at runtime that are instances of the classifier.
 * 
 * 2) Instance
 * This imposes the same constraints as classifier, but restricted to a particular instance of the classifier.
 * 
 * 3) Part
 * Behaviors of invocations contained by the partition are the responsibility of instances playing the part represented by the partition. This imposes the constraints for classifiers above according to the type of the part. In addition, invoked procedures containing a call to an operation or sending a signal must target objects at runtime that play the part at the time the message is sent. Just as partitions in the same dimension and nesting must be represented by parts of the same classifier's internal structure, all the runtime target objects of operation and signal passing invoked by the same execution of the activity must play parts of the same instance of the structured classifier. In particular, if an activity is executed in the context of a particular object at runtime, the parts of that object will be used as targets. If a part has more than one object playing it at runtime, the invocations are treated as if they were multiple, that is, the calls are sent in parallel, and the invocation does not complete until all the operations return.
 * 
 * 4) Attribute and Value
 * A partition may be represented by an attribute and its subpartitions by values of that attribute. Behaviors of invocations contained by the subpartition have this attribute and the value represented by the subpartition. For example, a partition may represent the location at which a behavior is carried out, and the subpartitions would represent specific values for that attribute, such as Chicago. The location attribute could be on the process class associated with an activity, or added in a profile to extend behaviors with these attributes.
 */
@objid ("0029d8f8-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityPartition extends ActivityGroup {
    /**
     * The metaclass simple name.
     */
    @objid ("f680c7db-9d0b-4560-8ec2-7c7452f36e7a")
    public static final String MNAME = "ActivityPartition";

    /**
     * The metaclass qualified name.
     */
    @objid ("3dab00e7-c077-4863-adae-08d5198a6743")
    public static final String MQNAME = "Standard.ActivityPartition";

    /**
     * Getter for attribute 'ActivityPartition.IsDimension'
     * 
     * Metamodel description:
     * <i>Indicates whether the partition groups other partitions along a dimension.</i>
     */
    @objid ("68dad451-f92f-4274-8fde-e7d52fd3ade2")
    boolean isIsDimension();

    /**
     * Setter for attribute 'ActivityPartition.IsDimension'
     * 
     * Metamodel description:
     * <i>Indicates whether the partition groups other partitions along a dimension.</i>
     */
    @objid ("35c1f434-aa17-4504-ba98-67eb5c5b4725")
    void setIsDimension(boolean value);

    /**
     * Getter for attribute 'ActivityPartition.IsExternal'
     * 
     * Metamodel description:
     * <i>Indicates whether the partition represents an entity to which the partitioning structure does not apply.</i>
     */
    @objid ("67caaeb0-995e-4b8f-95c2-6eae9d846b2f")
    boolean isIsExternal();

    /**
     * Setter for attribute 'ActivityPartition.IsExternal'
     * 
     * Metamodel description:
     * <i>Indicates whether the partition represents an entity to which the partitioning structure does not apply.</i>
     */
    @objid ("741b2335-dcbc-48cb-a014-0e236e32d595")
    void setIsExternal(boolean value);

    /**
     * Getter for relation 'ActivityPartition->Represented'
     * 
     * Metamodel description:
     * <i>An element constraining behaviors invoked by nodes in the partition. A partition should represent a classifier, an attribute, its value a parameter or an instance.</i>
     */
    @objid ("ccf30f53-ebe4-4a49-b85f-8e2beb67446e")
    UmlModelElement getRepresented();

    /**
     * Setter for relation 'ActivityPartition->Represented'
     * 
     * Metamodel description:
     * <i>An element constraining behaviors invoked by nodes in the partition. A partition should represent a classifier, an attribute, its value a parameter or an instance.</i>
     */
    @objid ("356e66f6-8153-484f-8dfb-188395b28750")
    void setRepresented(UmlModelElement value);

    /**
     * Getter for relation 'ActivityPartition->ContainedNode'
     * 
     * Metamodel description:
     * <i>Nodes immediately contained in the partition.</i>
     */
    @objid ("c6218c1a-68b7-4dbc-be99-d3c88ddef542")
    EList<ActivityNode> getContainedNode();

    /**
     * Filtered Getter for relation 'ActivityPartition->ContainedNode'
     * 
     * Metamodel description:
     * <i>Nodes immediately contained in the partition.</i>
     */
    @objid ("12a44b14-c0c5-4fb1-872c-569948006641")
    <T extends ActivityNode> List<T> getContainedNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityPartition->Outgoing'
     * 
     * Metamodel description:
     * <i>Outgoing BPMN messages.</i>
     */
    @objid ("fc34208c-08df-44b4-836f-8d234c0c4219")
    EList<MessageFlow> getOutgoing();

    /**
     * Filtered Getter for relation 'ActivityPartition->Outgoing'
     * 
     * Metamodel description:
     * <i>Outgoing BPMN messages.</i>
     */
    @objid ("cc543b6b-241e-4242-8ab8-de56f215ea0b")
    <T extends MessageFlow> List<T> getOutgoing(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityPartition->SuperPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("caaab23f-9938-4df1-989f-9dd274ac818d")
    ActivityPartition getSuperPartition();

    /**
     * Setter for relation 'ActivityPartition->SuperPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e8b4e312-fb80-402c-9d0f-e6c0798690f2")
    void setSuperPartition(ActivityPartition value);

    /**
     * Getter for relation 'ActivityPartition->SubPartition'
     * 
     * Metamodel description:
     * <i>Partitions immediately contained in the partition.</i>
     */
    @objid ("76e25389-dc66-4b6a-be13-4143c59d1ac0")
    EList<ActivityPartition> getSubPartition();

    /**
     * Filtered Getter for relation 'ActivityPartition->SubPartition'
     * 
     * Metamodel description:
     * <i>Partitions immediately contained in the partition.</i>
     */
    @objid ("ed54ccbe-fdb4-4349-9835-f6150cd24c7c")
    <T extends ActivityPartition> List<T> getSubPartition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityPartition->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1ae55ed6-30f4-4246-877d-c3e7277a9e58")
    EList<MessageFlow> getIncoming();

    /**
     * Filtered Getter for relation 'ActivityPartition->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("86a74b2a-fe1a-457c-8d00-a2fb29c58c32")
    <T extends MessageFlow> List<T> getIncoming(java.lang.Class<T> filterClass);

}
