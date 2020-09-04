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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.NaryAssociation;

/**
 * ClassAssociation v0.0.9054
 * 
 * 
 * A ClassAssociation is represented in UML as a Class that plays the role of an Association.
 * 
 * In Modelio, a ClassAssociation belongs to an Association.
 */
@objid ("0002bc78-c4bf-1fd8-97fe-001ec947cd2a")
public interface ClassAssociation extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("be75f4b4-5392-49b2-a27d-f9a6f5610547")
    public static final String MNAME = "ClassAssociation";

    /**
     * The metaclass qualified name.
     */
    @objid ("ff39f74d-7c8b-4847-a9e4-13a0ea06af27")
    public static final String MQNAME = "Standard.ClassAssociation";

    /**
     * Getter for relation 'ClassAssociation->NaryAssociationPart'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3c48401b-bda3-415b-a364-d2d9f4550a56")
    NaryAssociation getNaryAssociationPart();

    /**
     * Setter for relation 'ClassAssociation->NaryAssociationPart'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9420e331-7e07-4b38-a973-3d9c053353d5")
    void setNaryAssociationPart(NaryAssociation value);

    /**
     * Getter for relation 'ClassAssociation->ClassPart'
     * 
     * Metamodel description:
     * <i>Link to the Class that composes the ClassAssociation.</i>
     */
    @objid ("55d3a895-22b3-44d1-b0b8-8d6cccd19274")
    Class getClassPart();

    /**
     * Setter for relation 'ClassAssociation->ClassPart'
     * 
     * Metamodel description:
     * <i>Link to the Class that composes the ClassAssociation.</i>
     */
    @objid ("6aa1ea5e-2627-40cb-99b5-971b4ee0084e")
    void setClassPart(Class value);

    /**
     * Getter for relation 'ClassAssociation->AssociationPart'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("feb18cb2-0978-4297-b2b8-be10a7d0c249")
    Association getAssociationPart();

    /**
     * Setter for relation 'ClassAssociation->AssociationPart'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2a7c62bd-8959-4777-aa81-e734f09079a3")
    void setAssociationPart(Association value);

}
