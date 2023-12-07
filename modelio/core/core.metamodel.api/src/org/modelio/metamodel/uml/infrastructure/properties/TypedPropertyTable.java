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

/**
 * TypedPropertyTable v0.0.9054
 * 
 * 
 * Property table whose possible keys are restrained to the table definition.
 * 
 * 
 */
@objid ("006a7cb4-ec87-1098-b22e-001ec947cd2a")
public interface TypedPropertyTable extends PropertyTable {
    /**
     * The metaclass simple name.
     */
    @objid ("dd44329d-33d7-4b19-bd16-dcd8520f5337")
    public static final String MNAME = "TypedPropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("5b2366c2-48e6-4c06-87f1-7adbc6ba906e")
    public static final String MQNAME = "Infrastructure.TypedPropertyTable";

    /**
     * Get a property value as a String.
     * <p>
     * It is the responsibility of the called to convert it to the appropriate class according to the property's base class.
     * </p>
     * @param pdef a property.
     * @return its value
     */
    @objid ("054ae888-2834-11e2-bf07-001ec947ccaf")
    String getProperty(PropertyDefinition pdef);

    /**
     * Set a property value.
     * @param pdef a property.
     * @param value its value.
     */
    @objid ("054ae88c-2834-11e2-bf07-001ec947ccaf")
    void setProperty(PropertyDefinition pdef, String value);

    /**
     * Get a property value, converted to the appropriate class according to the definition's base class.
     * @param pdef a property.
     * @return its value
     */
    @objid ("ad56a828-c13f-4f8a-bab5-e36b0ff4b0ce")
    Object getPropertyObject(PropertyDefinition pdef);

    /**
     * Set a property value.
     * @param pdef a property.
     * @param value its value.
     */
    @objid ("098844d4-c33b-4a47-aa0b-740357a1f4b7")
    void setPropertyObject(PropertyDefinition pdef, Object value);

    /**
     * Getter for relation 'TypedPropertyTable->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("56adfc3b-92a7-46dc-bfdb-58e32ed17a1b")
    PropertyTableDefinition getType();

    /**
     * Setter for relation 'TypedPropertyTable->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e52363ce-3d82-40d5-9568-c4af94dabcb3")
    void setType(PropertyTableDefinition value);
}

