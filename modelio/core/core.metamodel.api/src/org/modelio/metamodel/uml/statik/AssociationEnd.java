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

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;

/**
 * AssociationEnd v0.0.9054
 * 
 * 
 * An AssociationEnd is an endpoint of an Association, which connects the Association to a Classifier. Each AssociationEnd is part of one Association. 
 * 
 * When the Association is navigable, this link is considered to be a property of the connected Class. It is thus a Feature in the same way as Attributes or Operations. 
 * 
 * An Association is linked to several Classes via AssociationEnds, which determine roles, multiplicities and navigabilities. The connected Classifier is defined by the usual composition link from Classifier to Feature. 
 * 
 * In Modelio, an AssociationEnd is a Feature of a Class.
 */
@objid ("00974ea6-c4be-1fd8-97fe-001ec947cd2a")
public interface AssociationEnd extends StructuralFeature {
    /**
     * The metaclass simple name.
     */
    @objid ("b94a4d42-ca36-40ab-9bf2-ba33295f97fe")
    public static final String MNAME = "AssociationEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("371c890f-bbbc-4537-8730-5041038ca2fd")
    public static final String MQNAME = "Standard.AssociationEnd";

    /**
     * Get the 'graphical owner' related to this end.
     * The owner is the current source or the opposite end's target according to the navigability.
     */
    @objid ("1bfd0899-45a2-49c8-b8dc-083141e68be2")
    Classifier getOwner();

    /**
     * Sets both ends sources and targets according to the given navigability.
     * <ul>
     * <li>THISSIDE: only current source and target must be filled.</li>
     * <li>OHERSIDE: only opposite source and target must be filled.</li>
     * <li>BOTHSIDES: current source must be equals to opposite target as well as current target and opposite source.</li>
     * <li>NONE: both sources must be filled, but no target</li>
     * </ul>
     * @param value whether or not to synchronize the other end and both source/target values. This end will be made navigable whatever the current navigability is.
     */
    @objid ("00788250-28da-1080-943a-001ec947cd2a")
    void setNavigable(boolean value);

    /**
     * Sets the value of the 'target' dependency.
     * @param fixModel whether or not to synchronize the other end and both source/target values. This end will be made navigable whatever the current navigability is.
     */
    @objid ("00308234-28fb-1080-943a-001ec947cd2a")
    void setTarget(Classifier value, boolean fixModel);

    /**
     * Sets the value of the 'source' dependency.
     * @param fixModel whether or not to synchronize the other end and both source/target values. This end will be made navigable whatever the current navigability is.
     */
    @objid ("000bdc7c-291a-1080-943a-001ec947cd2a")
    void setSource(Classifier value, boolean fixModel);

    @objid ("44b82fff-5b39-4aaa-ac0d-ddb26b837ecd")
    boolean isNavigable();

    /**
     * Getter for attribute 'AssociationEnd.Aggregation'
     * 
     * Metamodel description:
     * <i>This attribute is used to distinguish between normal Associations (KindIsAssociation), shared aggregation (KindIsAggregation) and strong aggregations (KindIsComposition)</i>
     */
    @objid ("87741b8d-1e3d-4aa5-8e96-8666083cf5d9")
    AggregationKind getAggregation();

    /**
     * Setter for attribute 'AssociationEnd.Aggregation'
     * 
     * Metamodel description:
     * <i>This attribute is used to distinguish between normal Associations (KindIsAssociation), shared aggregation (KindIsAggregation) and strong aggregations (KindIsComposition)</i>
     */
    @objid ("40217ab0-dab1-40f4-9e4d-baa1ebdb156b")
    void setAggregation(AggregationKind value);

    /**
     * Getter for attribute 'AssociationEnd.IsChangeable'
     * 
     * Metamodel description:
     * <i>When placed on a target end, specifies whether an instance of the Association may be modified from the source end.</i>
     */
    @objid ("c08974e7-9a1e-4663-b3af-9deafd6dcf44")
    boolean isIsChangeable();

    /**
     * Setter for attribute 'AssociationEnd.IsChangeable'
     * 
     * Metamodel description:
     * <i>When placed on a target end, specifies whether an instance of the Association may be modified from the source end.</i>
     */
    @objid ("847010d2-c7d6-47b5-81bb-db745f935350")
    void setIsChangeable(boolean value);

    /**
     * Getter for relation 'AssociationEnd->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6813f71e-350e-41f9-a529-487cdca630ad")
    Classifier getTarget();

    /**
     * Setter for relation 'AssociationEnd->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("20c02c9e-3dc8-48c0-85e9-de0f7a64895a")
    void setTarget(Classifier value);

    /**
     * Getter for relation 'AssociationEnd->OppositeOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("43c827e2-35f1-4817-a11a-82686ba5f8dd")
    AssociationEnd getOppositeOwner();

    /**
     * Setter for relation 'AssociationEnd->OppositeOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2663416c-3111-4114-aada-1cbda5c55380")
    void setOppositeOwner(AssociationEnd value);

    /**
     * Getter for relation 'AssociationEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7047f72d-7ff3-489c-a675-b75b57c2da97")
    Classifier getSource();

    /**
     * Setter for relation 'AssociationEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c4b8f790-eb89-4a89-952a-fddeaedf1481")
    void setSource(Classifier value);

    /**
     * Getter for relation 'AssociationEnd->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a337ff12-42d6-497e-bb04-75062a4fc946")
    EList<LinkEnd> getOccurence();

    /**
     * Filtered Getter for relation 'AssociationEnd->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ac5b1c1a-16f9-49f5-83ff-944e50077fb4")
    <T extends LinkEnd> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AssociationEnd->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("30bf9d82-cede-484c-9198-a1537e7f6ed4")
    EList<InformationFlow> getSent();

    /**
     * Filtered Getter for relation 'AssociationEnd->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e36b3678-15a2-460e-9881-f76e3fea2dc6")
    <T extends InformationFlow> List<T> getSent(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AssociationEnd->Qualifier'
     * 
     * Metamodel description:
     * <i>Defines a Qualifier on the AssociationEnd. </i>
     */
    @objid ("50df1c3c-404c-4a9a-85f9-8d294689e65b")
    EList<Attribute> getQualifier();

    /**
     * Filtered Getter for relation 'AssociationEnd->Qualifier'
     * 
     * Metamodel description:
     * <i>Defines a Qualifier on the AssociationEnd. </i>
     */
    @objid ("9bbf9cdc-b75c-4714-a637-be5010f27ed9")
    <T extends Attribute> List<T> getQualifier(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AssociationEnd->Opposite'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("afc3e08a-638a-4290-a142-ab7892638663")
    AssociationEnd getOpposite();

    /**
     * Setter for relation 'AssociationEnd->Opposite'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d77aa88e-9717-46ad-bd9d-805fa39aa962")
    void setOpposite(AssociationEnd value);

    /**
     * Getter for relation 'AssociationEnd->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1bfede1d-c1d7-4abd-ad36-83584578fe7c")
    EList<ObjectNode> getRepresentingObjectNode();

    /**
     * Filtered Getter for relation 'AssociationEnd->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e6be6ac9-94f3-45cd-a58b-794947bedf80")
    <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AssociationEnd->Association'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("358308e4-f83d-4a62-8bb4-5c3c6adc3c3c")
    Association getAssociation();

    /**
     * Setter for relation 'AssociationEnd->Association'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6aaae33f-2390-46eb-9b76-4b4c14beec9b")
    void setAssociation(Association value);

}
