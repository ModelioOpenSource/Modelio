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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * CollaborationUse v0.0.9054
 * 
 * 
 * (UML 2.0) A CollaborationUse (or collaboration occurrence) represents one particular use of a Collaboration to explain the relationships between the properties of a Classifier. A CollaborationUse indicates a set of roles and Connectors that cooperate within the Classifier according to a given Collaboration, indicated by the type of the collaboration occurrence. 
 * 
 * There may be multiple uses of a given Collaboration within a Classifier, each involving a different set of roles and Connectors. A given role or Connector may be involved in multiple occurrences of the same or different Collaborations. Associated Bindings map Features of the Collaboration type to Features in the Classifier. These Bindings indicate which role in the Classifier plays which role in the Collaboration.
 * 
 * In Modelio, a CollaborationUse can belong to a NameSpace (Classifier or Collaboration) or to an Operation.
 * 
 * 
 */
@objid ("00047d6a-c4bf-1fd8-97fe-001ec947cd2a")
public interface CollaborationUse extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("93b65637-44d4-430d-adcc-5c84d496356b")
    public static final String MNAME = "CollaborationUse";

    /**
     * The metaclass qualified name.
     */
    @objid ("72eb51e4-92ab-4d28-b0a9-e3984a86033c")
    public static final String MQNAME = "Standard.CollaborationUse";

    /**
     * Getter for relation 'CollaborationUse->Type'
     * 
     * Metamodel description:
     * <i>Defines the Collaboration whose CollaborationUse is an occurrence.</i>
     * 
     */
    @objid ("4cb753fe-3de2-4c7a-81a5-d43ee589e148")
    Collaboration getType();

    /**
     * Setter for relation 'CollaborationUse->Type'
     * 
     * Metamodel description:
     * <i>Defines the Collaboration whose CollaborationUse is an occurrence.</i>
     * 
     */
    @objid ("0e41ddd0-ecf5-4baf-9705-ef1b9e84be11")
    void setType(Collaboration value);

    /**
     * Getter for relation 'CollaborationUse->NRepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6a1029b3-0044-4c10-b04d-8bb9bf2d4fa9")
    NameSpace getNRepresented();

    /**
     * Setter for relation 'CollaborationUse->NRepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7c182a5b-cfed-46b1-83b6-1183e96f92bf")
    void setNRepresented(NameSpace value);

    /**
     * Getter for relation 'CollaborationUse->ORepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("47beb715-9cb9-423d-a88a-ca63d6c1e533")
    Operation getORepresented();

    /**
     * Setter for relation 'CollaborationUse->ORepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6cc730e7-6e2c-44e5-8312-e65f46c40923")
    void setORepresented(Operation value);

    /**
     * Getter for relation 'CollaborationUse->RoleBinding'
     * 
     * Metamodel description:
     * <i>The CollaborationUse owns the Binding links that bind elements of the Type Collaboration to elements accessible from the owner NameSpace of the CollaborationUse.</i>
     * 
     */
    @objid ("a0d3b078-3f19-4116-ba7d-11608f54b374")
    EList<Binding> getRoleBinding();

    /**
     * Filtered Getter for relation 'CollaborationUse->RoleBinding'
     * 
     * Metamodel description:
     * <i>The CollaborationUse owns the Binding links that bind elements of the Type Collaboration to elements accessible from the owner NameSpace of the CollaborationUse.</i>
     * 
     */
    @objid ("708ef73d-434a-43e2-bf84-611aa33e7ff3")
    <T extends Binding> List<T> getRoleBinding(java.lang.Class<T> filterClass);
}

