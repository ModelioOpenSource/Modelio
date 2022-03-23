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

package org.modelio.metamodel.uml.informationFlow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.StructuralFeature;

/**
 * InformationFlow v0.0.9054
 * 
 * 
 * <p>An InformationFlow specifies that one or more information items circulates from its sources to its targets. Information flows require some kind of &quot;information channel&quot;&nbsp;for transmitting information items from the source to the destination.</p><p>An information channel is represented in various ways depending on the nature of its sources and targets. It may be represented by connectors, links, associations, or even dependencies. For example, if the source and destination are parts in some composite structure such as a collaboration, then the information channel is likely to be represented by a connector between them. Or, if the source and target are objects (which are a kind of InstanceSpecification), they may be represented by a link that joins the two, and so on.</p><p>The sources and targets of the information flow can only be one of the following kind: Actor, Node, UseCase, Artifact, Class, Component, Port, Attribute, AssociationEnd, Interface, Package, ActivityNode, ActivityPartition and Instance.</p>
 */
@objid ("0063764e-c4bf-1fd8-97fe-001ec947cd2a")
public interface InformationFlow extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("27efd311-6771-4315-afbe-9bf2c1aad4b6")
    public static final String MNAME = "InformationFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("297009c0-5eb3-480b-a030-99dc8c9c182e")
    public static final String MQNAME = "Standard.InformationFlow";

    /**
     * Getter for relation 'InformationFlow->Owner'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("42de8812-075f-4205-b2cb-4dea09c0be73")
    NameSpace getOwner();

    /**
     * Setter for relation 'InformationFlow->Owner'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("2b050454-03c6-4472-882e-268fcf73c1d8")
    void setOwner(NameSpace value);

    /**
     * Getter for relation 'InformationFlow->InformationSource'
     * 
     * Metamodel description:
     * <i>Defines from which source the conveyed information items are initiated.</i>
     */
    @objid ("46c236b1-87de-4ec1-a11d-996281cd5d47")
    EList<UmlModelElement> getInformationSource();

    /**
     * Filtered Getter for relation 'InformationFlow->InformationSource'
     * 
     * Metamodel description:
     * <i>Defines from which source the conveyed information items are initiated.</i>
     */
    @objid ("9f64d5b2-81b5-4b03-901b-e0f5449ff06e")
    <T extends UmlModelElement> List<T> getInformationSource(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->InformationTarget'
     * 
     * Metamodel description:
     * <i>Defines to which target the conveyed information items are directed.</i>
     */
    @objid ("ea09302d-b354-4dd8-a293-867490ace91f")
    EList<UmlModelElement> getInformationTarget();

    /**
     * Filtered Getter for relation 'InformationFlow->InformationTarget'
     * 
     * Metamodel description:
     * <i>Defines to which target the conveyed information items are directed.</i>
     */
    @objid ("40e26d59-9e61-4047-8584-e08d28c6fe2b")
    <T extends UmlModelElement> List<T> getInformationTarget(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingActivityEdge'
     * 
     * Metamodel description:
     * <i>Determines which ActivityEdges will realize the specified flow.</i>
     */
    @objid ("004bb017-99a5-4122-869c-844aad00a93f")
    EList<ActivityEdge> getRealizingActivityEdge();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingActivityEdge'
     * 
     * Metamodel description:
     * <i>Determines which ActivityEdges will realize the specified flow.</i>
     */
    @objid ("a9b0a2c3-8965-4db2-b637-71d70da9bea3")
    <T extends ActivityEdge> List<T> getRealizingActivityEdge(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingCommunicationMessage'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d5b6b6d1-29d2-4abb-8361-d89feee8ef65")
    EList<CommunicationMessage> getRealizingCommunicationMessage();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingCommunicationMessage'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("325020af-1e64-49ce-82cc-39548d3b42c3")
    <T extends CommunicationMessage> List<T> getRealizingCommunicationMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("79574d48-30ca-43a6-a248-53d05eaec58e")
    EList<StructuralFeature> getRealizingFeature();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("64bdb180-80e3-4591-b02f-175b424c42c3")
    <T extends StructuralFeature> List<T> getRealizingFeature(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6ae7fdc9-1fa6-4d56-9a56-15e20a900aac")
    EList<LinkEnd> getRealizingLink();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3fd7052a-380c-4804-8076-426172255d5f")
    <T extends LinkEnd> List<T> getRealizingLink(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7fd907e2-1475-4ec4-bb9a-28e299175e41")
    EList<Message> getRealizingMessage();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fbfd7095-d761-499f-a575-34854b0a8c31")
    <T extends Message> List<T> getRealizingMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->RealizingNaryLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("625e7658-60ce-4dae-a327-528873715219")
    EList<NaryLink> getRealizingNaryLink();

    /**
     * Filtered Getter for relation 'InformationFlow->RealizingNaryLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8bce6135-780b-4f45-9420-ea95962b2fbc")
    <T extends NaryLink> List<T> getRealizingNaryLink(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->Conveyed'
     * 
     * Metamodel description:
     * <i>Specifies the information items that may circulate on this information flow.</i>
     */
    @objid ("9331c75b-cfcf-43f3-935c-5a17f6dac306")
    EList<Classifier> getConveyed();

    /**
     * Filtered Getter for relation 'InformationFlow->Conveyed'
     * 
     * Metamodel description:
     * <i>Specifies the information items that may circulate on this information flow.</i>
     */
    @objid ("835839d9-2923-4d6d-b4d7-78986de3c497")
    <T extends Classifier> List<T> getConveyed(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'InformationFlow->Channel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d7116a6d-21e3-4a04-abf4-6c240ece3ffb")
    AssociationEnd getChannel();

    /**
     * Setter for relation 'InformationFlow->Channel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b850d077-13b4-4118-b164-3321a193c163")
    void setChannel(AssociationEnd value);

}
