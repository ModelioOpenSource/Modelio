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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;

/**
 * Binding v0.0.9054
 * 
 * 
 * A Binding is used to define the mapping between the Features of the Collaboration and the Features of the Classifier or Operation in the CollaborationUse.
 * 
 * This mapping indicates which connectable element of the Classifier or Operation plays which role(s) in the Collaboration. A connectable element may be bound to multiple roles in the same Collaboration occurrence (that is, it may play multiple roles).  
 * 
 * In Modelio, a Binding binds an occurrence to an element.  The Binding can be realized (Role->RepresentedFeature) :
 * From a BindableInstance to: 
 *    - a BindableInstance
 *    - an Attribute
 *    - a Parameter 
 *    - an AssociationEnd 
 * From a Connector to:
 *    - an Association
 *    - a Link
 * From a ConnectorEnd to:
 *    - an AssociationEnd
 *    - a LinkEnd
 * 
 * This Binding is generally independent of the Model or the Base of the occurrence (that is, it is not necessary to express the type of a role if it is bound to typed elements).
 *          
 * In Modelio, a Binding belongs to a CollaborationUse.
 */
@objid ("00015824-c4bf-1fd8-97fe-001ec947cd2a")
public interface Binding extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a2b3b1fb-6b9d-4101-a612-bc7eedaf1db7")
    public static final String MNAME = "Binding";

    /**
     * The metaclass qualified name.
     */
    @objid ("79918e10-fa2c-4f3e-a537-f7f17cb52b5b")
    public static final String MQNAME = "Standard.Binding";

    /**
     * Getter for relation 'Binding->ConnectorEndRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5290c644-1d27-4d53-ae23-a250154dcec5")
    ConnectorEnd getConnectorEndRole();

    /**
     * Setter for relation 'Binding->ConnectorEndRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0d6fe701-2c49-4af9-a0bb-9ddcc0419d64")
    void setConnectorEndRole(ConnectorEnd value);

    /**
     * Getter for relation 'Binding->ConnectorRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("39a172fe-cdb8-4614-9ec7-f133dbf796a0")
    NaryConnector getConnectorRole();

    /**
     * Setter for relation 'Binding->ConnectorRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a03dce65-2abf-4b43-9076-ad64d90230b8")
    void setConnectorRole(NaryConnector value);

    /**
     * Getter for relation 'Binding->Role'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("092c42f0-dbd8-4342-b31f-28edb402f5b3")
    BindableInstance getRole();

    /**
     * Setter for relation 'Binding->Role'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1e78861a-1278-4fc2-ab5b-71aef2b4bda3")
    void setRole(BindableInstance value);

    /**
     * Getter for relation 'Binding->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("844b1aec-4046-4530-ac75-43002ba9a75c")
    UmlModelElement getRepresentedFeature();

    /**
     * Setter for relation 'Binding->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("808b1817-9e82-4c08-babb-c0a41bd89417")
    void setRepresentedFeature(UmlModelElement value);

    /**
     * Getter for relation 'Binding->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8c9f9096-673e-4c30-8a19-22f345697eea")
    CollaborationUse getOwner();

    /**
     * Setter for relation 'Binding->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f2a84aa5-3faf-4e8a-bfde-983c42a136ce")
    void setOwner(CollaborationUse value);

}
