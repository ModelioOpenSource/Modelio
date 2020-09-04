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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Link;

/**
 * Association v0.0.9054
 * 
 * 
 * An Association describes discrete connections among objects or other instances in a system.  An Association is often established between two Classes (binary associations), but can be established between several Classes (n-ary associations). 
 * 
 * An Association can be related to a ClassAssociation that may, for example, provide Attributes and Operations.  The connections to the associated Classes are specified through the AssociationEnd metaclass.  The AssociationEnd metaclass will provide the properties of an Association, such as cardinalities, navigability, and so on. Aggregation is a specific case of an Association. 
 * 
 * In Modelio, an Association physically belongs to no other elements. It has a specific way of behaving during transfer and copy/paste operations, depending on whether the connected Classes are transferred in conjunction or not.
 */
@objid ("28f7c9fd-bee9-46f0-8969-5c46572cec86")
public interface Association extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("4d4a1952-743d-4b6a-99ec-d728d14e5b62")
    public static final String MNAME = "Association";

    /**
     * The metaclass qualified name.
     */
    @objid ("f5cd6dcd-267e-48be-b939-614e044e5575")
    public static final String MQNAME = "Standard.Association";

    /**
     * Getter for relation 'Association->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bfed74c4-9c91-4751-97cf-c0ae6533be22")
    EList<Link> getOccurence();

    /**
     * Filtered Getter for relation 'Association->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2a60d613-2bb8-4177-9b0c-de253462a3fc")
    <T extends Link> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Association->End'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bd69461f-6a73-40d7-8315-e523b1010d24")
    EList<AssociationEnd> getEnd();

    /**
     * Filtered Getter for relation 'Association->End'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("63638baa-4047-40dd-a47c-caa27f557c45")
    <T extends AssociationEnd> List<T> getEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Association->LinkToClass'
     * 
     * Metamodel description:
     * <i>Specifies a ClassAssociation that may be related to the Association.</i>
     */
    @objid ("bec781f9-55e4-404c-8ec8-ab15243067ee")
    ClassAssociation getLinkToClass();

    /**
     * Setter for relation 'Association->LinkToClass'
     * 
     * Metamodel description:
     * <i>Specifies a ClassAssociation that may be related to the Association.</i>
     */
    @objid ("d1b75fcc-4b8e-49e3-9f65-203409b0c579")
    void setLinkToClass(ClassAssociation value);

}
