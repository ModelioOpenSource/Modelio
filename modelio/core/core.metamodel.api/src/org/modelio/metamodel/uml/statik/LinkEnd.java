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
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * LinkEnd v0.0.9054
 * 
 * 
 * A LinkEnd is the part of a Link that connects to an Instance. It corresponds to an AssociationEnd of an Association. 
 * 
 * In UML 2.0, LinkEnds are implemented as slots. 
 * 
 * A LinkEnd belongs to a Link.
 */
@objid ("000fb1bc-c4bf-1fd8-97fe-001ec947cd2a")
public interface LinkEnd extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e6dd0ee9-d2ae-459e-911e-137b205a96a9")
    public static final String MNAME = "LinkEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("62f5bc44-c159-4a21-873f-c9ba90f5653c")
    public static final String MQNAME = "Standard.LinkEnd";

    /**
     * Get the 'graphical owner' related to this end.
     * The owner is the current source or the opposite end's target according to the navigability.
     */
    @objid ("8b942215-f0a1-454e-9f8a-596315ee40d5")
    Instance getOwner();

    @objid ("006058b0-2963-1080-943a-001ec947cd2a")
    void setTarget(final Instance value, final boolean fixModel);

    @objid ("006059f0-2963-1080-943a-001ec947cd2a")
    void setSource(final Instance value, final boolean fixModel);

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
    @objid ("50d3075c-1fcb-4a03-a648-16729171986f")
    void setNavigable(boolean value);

    @objid ("6d12e031-ad22-449a-9171-6a88d4158b48")
    boolean isNavigable();

    /**
     * Getter for attribute 'LinkEnd.IsOrdered'
     * 
     * Metamodel description:
     * <i>Determines if this LinkEnd is ordered.</i>
     */
    @objid ("fcf73d01-d406-41e9-9490-067237966153")
    boolean isIsOrdered();

    /**
     * Setter for attribute 'LinkEnd.IsOrdered'
     * 
     * Metamodel description:
     * <i>Determines if this LinkEnd is ordered.</i>
     */
    @objid ("85e0d2fc-c5f1-47a6-a7a9-d1d92c110978")
    void setIsOrdered(boolean value);

    /**
     * Getter for attribute 'LinkEnd.IsUnique'
     * 
     * Metamodel description:
     * <i>Determines if this LinkEnd is unique.</i>
     */
    @objid ("ef9777b4-ed2a-4341-bb22-67675cddb70a")
    boolean isIsUnique();

    /**
     * Setter for attribute 'LinkEnd.IsUnique'
     * 
     * Metamodel description:
     * <i>Determines if this LinkEnd is unique.</i>
     */
    @objid ("d7a49a3a-2f15-4f2c-91a0-847c1e869a0c")
    void setIsUnique(boolean value);

    /**
     * Getter for attribute 'LinkEnd.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum value of the Link's multiplicity.</i>
     */
    @objid ("7bba25c1-4310-45fa-b2ce-d6cdc992be70")
    String getMultiplicityMax();

    /**
     * Setter for attribute 'LinkEnd.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum value of the Link's multiplicity.</i>
     */
    @objid ("0ef18ad8-3371-4bb9-933d-7b4e156bea34")
    void setMultiplicityMax(String value);

    /**
     * Getter for attribute 'LinkEnd.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum value of the Link's multiplicity. When placed on a target end, the multiplicity specifies the number of target instances that may be associated with a single source instance across the given Link.</i>
     */
    @objid ("258318a3-c0bd-4ddc-971b-1e72b90fcbb1")
    String getMultiplicityMin();

    /**
     * Setter for attribute 'LinkEnd.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum value of the Link's multiplicity. When placed on a target end, the multiplicity specifies the number of target instances that may be associated with a single source instance across the given Link.</i>
     */
    @objid ("06670c6b-fb3b-41b3-aefd-29ea57042310")
    void setMultiplicityMin(String value);

    /**
     * Getter for relation 'LinkEnd->Link'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("780bebb2-6884-4789-bdef-ca10444ad5fb")
    Link getLink();

    /**
     * Setter for relation 'LinkEnd->Link'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cd72633c-53d3-4556-b5de-b52113e3a225")
    void setLink(Link value);

    /**
     * Getter for relation 'LinkEnd->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("19651663-f981-4f11-802a-d5d7cbd6f88a")
    Instance getTarget();

    /**
     * Setter for relation 'LinkEnd->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8fb70c43-b102-4a64-9424-c7cc07d58fcf")
    void setTarget(Instance value);

    /**
     * Getter for relation 'LinkEnd->OppositeOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("aaf16daa-ed28-496e-bee1-ebf665987a0e")
    LinkEnd getOppositeOwner();

    /**
     * Setter for relation 'LinkEnd->OppositeOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("67ebbbfe-83e1-4802-a6be-2ffada70c737")
    void setOppositeOwner(LinkEnd value);

    /**
     * Getter for relation 'LinkEnd->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d5ab3449-47f3-49a5-9936-6634cda6cb2a")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'LinkEnd->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b9cdbb67-9b00-43b4-aa83-a5150ca375e9")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'LinkEnd->Model'
     * 
     * Metamodel description:
     * <i>The LinkEnd is an occurrence of this AssociationEnd.</i>
     */
    @objid ("661a22c1-2de2-4b65-a955-6b755461b1ac")
    AssociationEnd getModel();

    /**
     * Setter for relation 'LinkEnd->Model'
     * 
     * Metamodel description:
     * <i>The LinkEnd is an occurrence of this AssociationEnd.</i>
     */
    @objid ("1a8cb555-55ca-418e-8378-2f50ba867c6b")
    void setModel(AssociationEnd value);

    /**
     * Getter for relation 'LinkEnd->Consumer'
     * 
     * Metamodel description:
     * <i>Used for Connectors between Ports to designate the RequiredInterface(s) set the LinkEnd is connected to.</i>
     */
    @objid ("8f1c9d42-a8ac-4f52-b3f8-2b5f076b4348")
    RequiredInterface getConsumer();

    /**
     * Setter for relation 'LinkEnd->Consumer'
     * 
     * Metamodel description:
     * <i>Used for Connectors between Ports to designate the RequiredInterface(s) set the LinkEnd is connected to.</i>
     */
    @objid ("443e3927-5e5c-43bb-9d78-44c58df8e6fd")
    void setConsumer(RequiredInterface value);

    /**
     * Getter for relation 'LinkEnd->Opposite'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e10bb26d-c5e7-4f7e-b4cb-8c5551328d52")
    LinkEnd getOpposite();

    /**
     * Setter for relation 'LinkEnd->Opposite'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("322f7781-7f9f-47de-8df7-a12ebfa5784a")
    void setOpposite(LinkEnd value);

    /**
     * Getter for relation 'LinkEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4e37aa68-c0f7-4404-a2cb-e6088f1dda62")
    Instance getSource();

    /**
     * Setter for relation 'LinkEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fddcf86a-595c-4c38-ad3f-c2a660a9497b")
    void setSource(Instance value);

    /**
     * Getter for relation 'LinkEnd->Provider'
     * 
     * Metamodel description:
     * <i>Used for Connectors between Ports to designate the ProvidedInterface(s) set the LinkEnd is connected to.</i>
     */
    @objid ("13e63a4b-fab3-4b16-b7f9-69b4d6187c94")
    ProvidedInterface getProvider();

    /**
     * Setter for relation 'LinkEnd->Provider'
     * 
     * Metamodel description:
     * <i>Used for Connectors between Ports to designate the ProvidedInterface(s) set the LinkEnd is connected to.</i>
     */
    @objid ("a3188449-2f95-4e02-a233-e2e48fa5e5b0")
    void setProvider(ProvidedInterface value);

}
