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
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;

/**
 * Instance v0.0.9054
 * 
 * 
 * In Modelio, Instances represent both the UML 2.0 Instance specification, and the UML 2.0 Part within structured Classifiers. The distinction is made through the definition context.  If the context is a namespacing context (such as Package or Class), then it corresponds to the UML 2.0 instance specification. If the context of the instance is an instantiation context, such as typically in the case of Collaborations, then it corresponds to Parts. Parts and Ports are handled as instances in Modelio, whereas UML 2.0 handles them as properties.  
 * 
 * An Instance is connected to zero or several Classifiers, which declare its structure and behavior. It has a set of attribute values (Slot in UML, and AttributeLink in Modelio) and is connected to a set of Links, where both sets match the definitions of its Classifiers/NameSpaces (if there are any). The two sets implement the current state of the Instance.  
 * 
 * In Modelio, an Instance belongs to its NameSpace (Class, Package, Collaboration, and so on), or to another Instance (Cluster association).  
 * 
 * There exist three important cases of configuration for instances in Modelio:  
 * 1 - The Instance belongs to a Package. This corresponds to the UML 2.0 notion of InstanceSpecification.  
 * 2 - The Instance belongs to a Collaboration (it is a Part, and its links are Connectors). 
 * 3 - The Instance belongs to a Classifier. In this case, it is a Part or a Port of the Classifier.  
 * 
 * (UML 2.0 semantics): When an Instance of the containing Classifier is created, a set of Instances corresponding to its properties may be created either immediately or at some later time, depending on the multiplicity. These Instances are Instances of the Classifier typing the property. 
 * 
 * A Part specifies that a set of Instances may exist.  This set of Instances is a subset of the total set of Instances specified by the Classifier typing the Part. A Part of a Classifier declares that an Instance of this Classifier may contain a set of Instances by composition. All such Instances are destroyed when the container Classifier Instance is destroyed.  
 * 
 * In Modelio, an Instance belongs to another Instance (embedded or clustered Instances) or belongs to a NameSpace (Package, Class, Collaboration).
 */
@objid ("000d46d4-c4bf-1fd8-97fe-001ec947cd2a")
public interface Instance extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("fd86be64-b5cd-43dc-96f8-9464a6b8d782")
    public static final String MNAME = "Instance";

    /**
     * The metaclass qualified name.
     */
    @objid ("1bfdd096-3547-43fd-8523-a0104911e1b0")
    public static final String MQNAME = "Standard.Instance";

    /**
     * Getter for attribute 'Instance.IsConstant'
     * 
     * Metamodel description:
     * <i>Determines whether it is a constant.</i>
     */
    @objid ("cbf4bfdf-e849-4a67-857f-8ae959fb0ef8")
    boolean isIsConstant();

    /**
     * Setter for attribute 'Instance.IsConstant'
     * 
     * Metamodel description:
     * <i>Determines whether it is a constant.</i>
     */
    @objid ("865dad67-4da7-46e5-8686-0483cdac1a23")
    void setIsConstant(boolean value);

    /**
     * Getter for attribute 'Instance.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum number of Instances that can exist. In the case where the Instance belongs to a Collaboration (typically in the case of internalStructure) this represents the number of instances at the time of creation of the container NameSpace or Classifier.</i>
     */
    @objid ("78aeb23d-c9bf-44bd-8b9f-609402209229")
    String getMultiplicityMin();

    /**
     * Setter for attribute 'Instance.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum number of Instances that can exist. In the case where the Instance belongs to a Collaboration (typically in the case of internalStructure) this represents the number of instances at the time of creation of the container NameSpace or Classifier.</i>
     */
    @objid ("2022624f-4242-4c24-88e0-ab4608df0e0d")
    void setMultiplicityMin(String value);

    /**
     * Getter for attribute 'Instance.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum number of Instances that can exist in the context of the owner NameSpace.</i>
     */
    @objid ("1d5b8244-5218-4ba3-bf32-37fde38c25a5")
    String getMultiplicityMax();

    /**
     * Setter for attribute 'Instance.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum number of Instances that can exist in the context of the owner NameSpace.</i>
     */
    @objid ("3011c509-a62d-4429-88bd-4a004f16adc7")
    void setMultiplicityMax(String value);

    /**
     * Getter for attribute 'Instance.Value'
     * 
     * Metamodel description:
     * <i>Current value of the Instance. This can be an expression, used instead of the set of values of the AttributeLinks of the instance.</i>
     */
    @objid ("36f5af1c-d5f4-49a8-8563-88aa949b81b9")
    String getValue();

    /**
     * Setter for attribute 'Instance.Value'
     * 
     * Metamodel description:
     * <i>Current value of the Instance. This can be an expression, used instead of the set of values of the AttributeLinks of the instance.</i>
     */
    @objid ("4614f4df-34b3-49b2-87e7-445af037a2b3")
    void setValue(String value);

    /**
     * Getter for relation 'Instance->RepresentedCommunicationNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0d6b12eb-f25c-4a1c-a293-00697c2caf30")
    EList<CommunicationNode> getRepresentedCommunicationNode();

    /**
     * Filtered Getter for relation 'Instance->RepresentedCommunicationNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c1ff2079-c3ba-4862-b5f8-c245bb4f7d37")
    <T extends CommunicationNode> List<T> getRepresentedCommunicationNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->OwnedEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5203e26f-9a7c-40ac-94c8-3eaa7884fee8")
    EList<LinkEnd> getOwnedEnd();

    /**
     * Filtered Getter for relation 'Instance->OwnedEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7e85deb2-3ed6-4880-8f2a-3cdfa52d4cb8")
    <T extends LinkEnd> List<T> getOwnedEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->Base'
     * 
     * Metamodel description:
     * <i>Defines the Classifier as the model of the Instance or Role.</i>
     */
    @objid ("8cfbd12a-548a-497a-8087-411eee12d5e5")
    NameSpace getBase();

    /**
     * Setter for relation 'Instance->Base'
     * 
     * Metamodel description:
     * <i>Defines the Classifier as the model of the Instance or Role.</i>
     */
    @objid ("56ec2646-0933-42b9-9d71-a4dc1067b0f7")
    void setBase(NameSpace value);

    /**
     * Getter for relation 'Instance->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c2cf428c-dc9b-44f5-be8f-a3693832c34e")
    EList<ObjectNode> getRepresentingObjectNode();

    /**
     * Filtered Getter for relation 'Instance->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a3bd373d-1f9b-4b05-a1ee-65603ef94e72")
    <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cba82375-ef0c-408f-8e6b-308688db9577")
    NameSpace getOwner();

    /**
     * Setter for relation 'Instance->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7cf9487f-a9c6-43f7-8ba0-9b8c8d445762")
    void setOwner(NameSpace value);

    /**
     * Getter for relation 'Instance->OwnedNaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4bdad97a-b277-490a-bfa0-547ef3ac634d")
    EList<NaryLinkEnd> getOwnedNaryEnd();

    /**
     * Filtered Getter for relation 'Instance->OwnedNaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4a4d73d2-775f-4dd5-8283-9f0be638c84b")
    <T extends NaryLinkEnd> List<T> getOwnedNaryEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->RepresentedLifeLine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8b9b38eb-607b-40e6-9fc3-5c2cf0d6cce6")
    EList<Lifeline> getRepresentedLifeLine();

    /**
     * Filtered Getter for relation 'Instance->RepresentedLifeLine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("acd2c7f9-a10e-4e34-ba24-bd5c33b4c864")
    <T extends Lifeline> List<T> getRepresentedLifeLine(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->Slot'
     * 
     * Metamodel description:
     * <i>Occurrences of attributes for the current object (Instance or Role).</i>
     */
    @objid ("14e8bfc8-75ae-4b9b-b970-2fa7eebb5b2b")
    EList<AttributeLink> getSlot();

    /**
     * Filtered Getter for relation 'Instance->Slot'
     * 
     * Metamodel description:
     * <i>Occurrences of attributes for the current object (Instance or Role).</i>
     */
    @objid ("af9bb602-6c0d-49e4-b145-e4a87adfff41")
    <T extends AttributeLink> List<T> getSlot(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->Part'
     * 
     * Metamodel description:
     * <i>Instances can be embedded. This can represent cluster. In Modelio, this is used to represent ComponentInstance supported by NodeInstances, or Objects in ComponentInstances or NodeInstances.</i>
     */
    @objid ("9de4b527-60bd-4139-9490-57a7be953616")
    EList<BindableInstance> getPart();

    /**
     * Filtered Getter for relation 'Instance->Part'
     * 
     * Metamodel description:
     * <i>Instances can be embedded. This can represent cluster. In Modelio, this is used to represent ComponentInstance supported by NodeInstances, or Objects in ComponentInstances or NodeInstances.</i>
     */
    @objid ("878347c2-1d7c-423e-b51b-a77c4424e127")
    <T extends BindableInstance> List<T> getPart(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Instance->TargetingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9c23efa7-3497-4f1d-ad96-959ee385ceb5")
    EList<LinkEnd> getTargetingEnd();

    /**
     * Filtered Getter for relation 'Instance->TargetingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3a94e95a-92b4-44cf-b91f-64b7e28032c5")
    <T extends LinkEnd> List<T> getTargetingEnd(java.lang.Class<T> filterClass);

}
