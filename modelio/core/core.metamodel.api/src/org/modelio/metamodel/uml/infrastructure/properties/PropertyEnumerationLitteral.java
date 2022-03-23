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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * PropertyEnumerationLitteral v0.0.9054
 * 
 * 
 * Enumeration literal for properties. A PropertyEnumerationLitteral belongs to an EnumeratedPropertyType. Its Name attribute gives the literal value.
 */
@objid ("00726262-ec87-1098-b22e-001ec947cd2a")
public interface PropertyEnumerationLitteral extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a25c63ff-d184-41c9-aab6-a29cca85f42d")
    public static final String MNAME = "PropertyEnumerationLitteral";

    /**
     * The metaclass qualified name.
     */
    @objid ("0200e80e-4f6c-4663-bb20-6b3c4ecca88a")
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
     */
    @objid ("00280604-7678-440b-9ac7-958206b7b120")
    EnumeratedPropertyType getOwner();

    /**
     * Setter for relation 'PropertyEnumerationLitteral->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("43155574-367a-4713-bae7-0d8841d954df")
    void setOwner(EnumeratedPropertyType value);

}
