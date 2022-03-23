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

package org.modelio.metamodel.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * CommunicationNode v0.0.9054
 * 
 * 
 * <p>A Communication node represents an individual participant in the Communication Interaction. While Parts and StructuralFeatures may have multiplicity greater than 1, Communication nodes represent only one interacting entity.</p><p>If the referenced Instance is multivalued (i.e, has a multiplicity &gt; 1), then the Communication node may have an expression (the &quot;selector&quot;) that specifies which particular part is represented by this Communication node. If the selector is omitted, this means that an arbitrary representative of the multivalued Connectable element is chosen.</p>
 */
@objid ("005a9290-c4bf-1fd8-97fe-001ec947cd2a")
public interface CommunicationNode extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b6bb3a98-c71c-4fbc-ad02-5ea7fca657c2")
    public static final String MNAME = "CommunicationNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("90deee3f-b64c-4c71-a403-89f4e4c2077d")
    public static final String MQNAME = "Standard.CommunicationNode";

    /**
     * Getter for attribute 'CommunicationNode.Selector'
     * 
     * Metamodel description:
     * <i>If the represented element is multivalued, then this specifies the specific individual part within that set.</i>
     */
    @objid ("3164a86f-9d16-428a-a7a7-d2009711cde8")
    String getSelector();

    /**
     * Setter for attribute 'CommunicationNode.Selector'
     * 
     * Metamodel description:
     * <i>If the represented element is multivalued, then this specifies the specific individual part within that set.</i>
     */
    @objid ("bddb5c61-5f91-4e5d-8041-8d55c81fe972")
    void setSelector(String value);

    /**
     * Getter for relation 'CommunicationNode->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ea5ebe1f-a78b-42d2-8e26-ee7efb2f83c1")
    CommunicationInteraction getOwner();

    /**
     * Setter for relation 'CommunicationNode->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("df4a4945-5fec-4fdb-b28f-5e7ccde0c9c8")
    void setOwner(CommunicationInteraction value);

    /**
     * Getter for relation 'CommunicationNode->Represented'
     * 
     * Metamodel description:
     * <i>References the Instance the communication node represents.</i>
     */
    @objid ("a037bb25-d3be-4550-9f91-7de521858a83")
    Instance getRepresented();

    /**
     * Setter for relation 'CommunicationNode->Represented'
     * 
     * Metamodel description:
     * <i>References the Instance the communication node represents.</i>
     */
    @objid ("148476c4-1c00-4fcc-a112-26b741465f51")
    void setRepresented(Instance value);

    /**
     * Getter for relation 'CommunicationNode->Started'
     * 
     * Metamodel description:
     * <i>References communication channels starting from the node.</i>
     */
    @objid ("745027bc-bf1a-4220-bb3b-a053765d38b6")
    EList<CommunicationChannel> getStarted();

    /**
     * Filtered Getter for relation 'CommunicationNode->Started'
     * 
     * Metamodel description:
     * <i>References communication channels starting from the node.</i>
     */
    @objid ("68c1bc33-ea97-4ad0-8beb-8309b34a3c43")
    <T extends CommunicationChannel> List<T> getStarted(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'CommunicationNode->Ended'
     * 
     * Metamodel description:
     * <i>References communication channels ending on the node.</i>
     */
    @objid ("1b84ffa4-13c2-464a-b8ea-1b74a4675fc4")
    EList<CommunicationChannel> getEnded();

    /**
     * Filtered Getter for relation 'CommunicationNode->Ended'
     * 
     * Metamodel description:
     * <i>References communication channels ending on the node.</i>
     */
    @objid ("b0604c18-b798-4ec2-9174-eaa0c8863676")
    <T extends CommunicationChannel> List<T> getEnded(java.lang.Class<T> filterClass);

}
