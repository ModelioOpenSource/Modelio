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
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * PropertyDefinition v0.0.9054
 * 
 * 
 * <p>Property definition for typed property tables.</p>
 * 
 * 
 * 
 */
@objid ("00640c80-ec87-1098-b22e-001ec947cd2a")
public interface PropertyDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("f48f52f5-37d2-4e6d-a63c-f51099c7c2a4")
    public static final String MNAME = "PropertyDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("565b5dce-1e4a-4e2d-860f-d2aada52250c")
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
     * Translates an internal string value, according to the property definition's base type.
     * <p>
     * Used for displaying translated value (on diagrams for example).
     * </p>
     * @param value
     * @return the translated string.
     */
    @objid ("ba7e86fd-d887-4038-aa44-38eafcb176bb")
    String computeLabel(String value);

    @objid ("f8b75391-f4ac-4dd6-9e85-797b2ea71aee")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'PropertyDefinition.IsEditable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e45d1dea-945a-4243-8e60-67466395fbfa")
    boolean isIsEditable();

    /**
     * Setter for attribute 'PropertyDefinition.IsEditable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("68fffc33-f236-4681-b2ca-c837ebf10822")
    void setIsEditable(boolean value);

    /**
     * Getter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c48df2ae-eb29-4018-a9c0-51e30d3fc0d6")
    String getDefaultValue();

    /**
     * Setter for attribute 'PropertyDefinition.DefaultValue'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("45469ec3-9a70-4be3-8739-8964d39efe31")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1c24e831-1b5d-4776-8e64-4097d596a82f")
    PropertyType getType();

    /**
     * Setter for relation 'PropertyDefinition->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("85ce366a-21e7-4ea2-b011-0e6c84ec47fe")
    void setType(PropertyType value);

    /**
     * Getter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6e289114-63bd-4c3b-9f9f-2d08c201f249")
    PropertyTableDefinition getOwner();

    /**
     * Setter for relation 'PropertyDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d803768b-e00b-4abf-9ed9-74a56c918373")
    void setOwner(PropertyTableDefinition value);
}

