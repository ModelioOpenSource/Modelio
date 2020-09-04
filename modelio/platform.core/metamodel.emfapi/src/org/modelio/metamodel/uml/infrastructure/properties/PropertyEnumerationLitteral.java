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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;

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
    @objid ("b1c98b82-48e1-4d6d-9a6e-0d34df69f707")
    public static final String MNAME = "PropertyEnumerationLitteral";

    /**
     * The metaclass qualified name.
     */
    @objid ("de542762-e4cd-40ac-aaab-a557ee6f66df")
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
    @objid ("8292f0dc-6183-4d4f-a54f-3b88eceece10")
    EnumeratedPropertyType getOwner();

    /**
     * Setter for relation 'PropertyEnumerationLitteral->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("354c461f-ff50-4f96-8bd9-b7d527f401c4")
    void setOwner(EnumeratedPropertyType value);

}
