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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * PropertyEnumerationLitteral v0.0.9054
 * 
 * 
 * Enumeration literal for properties. A PropertyEnumerationLitteral belongs to an EnumeratedPropertyType. Its Name attribute gives the literal value.
 * 
 * 
 */
@objid ("00726262-ec87-1098-b22e-001ec947cd2a")
public interface PropertyEnumerationLitteral extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("9df01a0e-fc9b-43f2-ae92-6341d0c12eb6")
    public static final String MNAME = "PropertyEnumerationLitteral";

    /**
     * The metaclass qualified name.
     */
    @objid ("b3b5f46f-148b-4fda-ad11-3e3c9d06d8aa")
    public static final String MQNAME = "Infrastructure.PropertyEnumerationLitteral";

    /**
     * Compare 'this' literal with 'v' based on the ordering of the literals in
     * the EnumeratedPropertyType owning 'this' literal.
     * If 'l' does not belong to 'this' owner, the method returns -1 (meaning l < this by convention)
     * @param l @return
     */
    @objid ("3f7c4c9d-6307-4688-9ae7-6d02f373cc0f")
    int compareTo(PropertyEnumerationLitteral l);

    /**
     * Getter for relation 'PropertyEnumerationLitteral->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("68d88fcc-d67d-4092-8cb7-7d6137c15960")
    EnumeratedPropertyType getOwner();

    /**
     * Setter for relation 'PropertyEnumerationLitteral->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9e350390-e86b-4a5c-8834-cc3a501e9387")
    void setOwner(EnumeratedPropertyType value);
}

