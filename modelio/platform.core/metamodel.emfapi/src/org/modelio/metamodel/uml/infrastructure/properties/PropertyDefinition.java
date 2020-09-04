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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;

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
    @objid ("54bc82ba-12ec-41e6-92de-cdb89441c692")
    public static final String MNAME = "PropertyDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("adc35fbe-71dc-40b3-bb8f-c8c4bcaa5b48")
    public static final String MQNAME = "Infrastructure.PropertyDefinition";

    /**
     * Convert a value from a string, using the property definition's base type.
     * <p>
     * Used after reading a persisted string value in a property table.
     * </p>
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
    @objid ("ef84f1ac-c8d0-4916-86db-2510346761ad")
    boolean isIsEditable();

    /**
     * Setter for attribute 'PropertyDefinition.IsEditable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0eafaeec-34d5-4b14-8569-c5576c54680a")
    void setIsEditable(boolean value);

    /**
     * Getter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f23035ad-4864-43dc-b229-d716b923d9f8")
    String getDefaultValue();

    /**
     * Setter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f7df8870-77d7-46da-810b-a91719871fe5")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f49c191a-8f9e-4f4f-84e2-9ab77cdc246a")
    PropertyType getType();

    /**
     * Setter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("68622735-cc8c-457c-8c67-37eaf36007d1")
    void setType(PropertyType value);

    /**
     * Getter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("691bb571-4f2e-4b50-ae9d-6d2b0ec8f6ec")
    PropertyTableDefinition getOwner();

    /**
     * Setter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("51df54b7-0c0f-4ed4-ba9e-67973337eb98")
    void setOwner(PropertyTableDefinition value);

}
