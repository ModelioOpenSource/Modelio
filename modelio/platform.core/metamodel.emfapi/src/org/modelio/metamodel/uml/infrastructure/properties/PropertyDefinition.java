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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * PropertyDefinition v0.0.9054
 * 
 * 
 * <p>Property definition for typed property tables.</p>
 */
@objid ("00640c80-ec87-1098-b22e-001ec947cd2a")
public interface PropertyDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("80e2cf6c-9ebe-4c33-9ebf-d486ad8ea22a")
    public static final String MNAME = "PropertyDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("1c8a725d-8d27-4693-8407-fc1e8b0bef56")
    public static final String MQNAME = "Infrastructure.PropertyDefinition";

    /**
     * Convert a value from a string, using the property definition's base type.
     * <p>
     * Used after reading a persisted string value in a property table.
     * </p>
     * 
     * @param value the initial string value. Might be <code>null</code>.
     * @param object the element this conversion is done for.
     * @return the converted value. Might be <code>null</code>.
     */
    @objid ("b2842bbb-5742-4a70-a49a-1e7e98f81896")
    Object convertToObject(String value, ModelElement object);

    /**
     * Convert an object value to string, according to the property definition's base type.
     * <p>
     * Used before writing a persisted string value in a property table.
     * </p>
     * 
     * @param value a value. Might be <code>null</code>.
     * @param object the element this conversion is done for.
     * @return the value converted to string.
     */
    @objid ("5f6af4f2-6e07-4985-9ded-45be0200594b")
    String convertToString(Object value, ModelElement object);

    /**
     * Getter for attribute 'PropertyDefinition.IsEditable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c5e3460a-07e6-4340-b2da-c1969c043e85")
    boolean isIsEditable();

    /**
     * Setter for attribute 'PropertyDefinition.IsEditable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("10441fe0-82b8-43c0-aea8-7b10f8ccb46f")
    void setIsEditable(boolean value);

    /**
     * Getter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99b20404-59a9-488d-86a3-edca71c518a7")
    String getDefaultValue();

    /**
     * Setter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d916cbb7-d3fd-410c-aa91-a16224eafa49")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ac8497ee-de53-46e2-932c-9c219badeccf")
    PropertyType getType();

    /**
     * Setter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("42d461d2-9238-438d-855d-7892a7371cf6")
    void setType(PropertyType value);

    /**
     * Getter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ab6d9ba3-a60f-4022-9d48-d10382741922")
    PropertyTableDefinition getOwner();

    /**
     * Setter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b9116627-6c41-4416-98e7-480f22aa1b9c")
    void setOwner(PropertyTableDefinition value);

    /**
     * Translates an internal string value, according to the property definition's base type.
     * <p>
     * Used for displaying translated value (on diagrams for example).
     * </p>
     * @param value
     * 
     * @return the translated string.
     */
    @objid ("ba7e86fd-d887-4038-aa44-38eafcb176bb")
    String computeLabel(String value);

}
