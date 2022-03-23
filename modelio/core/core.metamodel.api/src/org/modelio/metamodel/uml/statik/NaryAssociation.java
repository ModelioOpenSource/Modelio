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
 * NaryAssociation v0.0.9054
 * 
 * 
 * null
 */
@objid ("0021601a-c4bf-1fd8-97fe-001ec947cd2a")
public interface NaryAssociation extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("7c696438-4513-4a9a-9a22-2bdeb14538a0")
    public static final String MNAME = "NaryAssociation";

    /**
     * The metaclass qualified name.
     */
    @objid ("dca6c8d2-fdf3-4882-97a6-907309082791")
    public static final String MQNAME = "Standard.NaryAssociation";

    /**
     * Getter for relation 'NaryAssociation->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("60ef25b9-3341-4243-a409-69012ee7b228")
    EList<NaryLink> getOccurence();

    /**
     * Filtered Getter for relation 'NaryAssociation->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a099024b-2bd1-44c0-bb83-1589f31abb8d")
    <T extends NaryLink> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NaryAssociation->NaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e79b8613-dce8-4352-9ae1-7f1deecb0306")
    EList<NaryAssociationEnd> getNaryEnd();

    /**
     * Filtered Getter for relation 'NaryAssociation->NaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6beafd6b-b4eb-41c2-a9ac-3e09abe1885c")
    <T extends NaryAssociationEnd> List<T> getNaryEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NaryAssociation->LinkToClass'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0360046a-c5cd-4601-bbf6-93146a668dc6")
    ClassAssociation getLinkToClass();

    /**
     * Setter for relation 'NaryAssociation->LinkToClass'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("169b4849-4ab0-48c3-af98-e44bbafecaeb")
    void setLinkToClass(ClassAssociation value);

}
