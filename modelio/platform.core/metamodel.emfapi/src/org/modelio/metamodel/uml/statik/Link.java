/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.LinkEnd;

/**
 * Link v0.0.9054
 * 
 * 
 * A Link is an Instance of an Association. It has a set of LinkEnds that matches the set of AssociationEnds of the Association.  
 * 
 * Links can be used as Connectors between Parts or Ports in UML 2.0, or they can be Links between Instances. 
 * 
 * In Modelio, a Link belongs to no element.  When a Link acts as a Connector between Parts and/or Ports, these Parts or Ports should belong to the same context (be inside the same Collaboration, Classifier, and so on).
 */
@objid ("815acdf3-5af8-48c5-894b-85d62dbe2e9b")
public interface Link extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8b7e70cd-cae2-4939-ac48-7db9d7aac1dd")
    public static final String MNAME = "Link";

    /**
     * The metaclass qualified name.
     */
    @objid ("72480a30-8d94-4218-839b-19cb99da879a")
    public static final String MQNAME = "Standard.Link";

    /**
     * Getter for relation 'Link->Model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("efe68851-8aa5-421b-ad95-641ac110695f")
    Association getModel();

    /**
     * Setter for relation 'Link->Model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3b30d21f-673e-4ab3-9912-ea61c01e99cc")
    void setModel(Association value);

    /**
     * Getter for relation 'Link->LinkEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("32305df9-93b3-4623-bdb9-f9cadaa34e07")
    EList<LinkEnd> getLinkEnd();

    /**
     * Filtered Getter for relation 'Link->LinkEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f8dbc259-5086-4563-9974-dd3239879456")
    <T extends LinkEnd> List<T> getLinkEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Link->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f5acb5e4-1c27-47c9-8150-ec35416559a5")
    CommunicationChannel getSent();

    /**
     * Setter for relation 'Link->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f7d5be72-8cee-4a7d-8f52-c7dbd31456de")
    void setSent(CommunicationChannel value);

}
