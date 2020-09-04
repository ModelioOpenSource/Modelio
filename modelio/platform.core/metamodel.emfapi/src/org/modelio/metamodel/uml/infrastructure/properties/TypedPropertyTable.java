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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * TypedPropertyTable v0.0.9054
 * 
 * 
 * Property table whose possible keys are restrained to the table definition.
 */
@objid ("006a7cb4-ec87-1098-b22e-001ec947cd2a")
public interface TypedPropertyTable extends PropertyTable {
    /**
     * The metaclass simple name.
     */
    @objid ("01cfce5e-9fe9-441d-9e51-60b663444459")
    public static final String MNAME = "TypedPropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("0c30b666-003a-43a2-8ab8-e87dd315eb7a")
    public static final String MQNAME = "Infrastructure.TypedPropertyTable";

    /**
     * Get a property value as a String.
     * <p>
     * It is the responsibility of the called to convert it to the appropriate class according to the property's base class.
     * </p>
     * 
     * @param pdef a property.
     * @return its value
     */
    @objid ("054ae888-2834-11e2-bf07-001ec947ccaf")
    String getProperty(PropertyDefinition pdef);

    /**
     * Set a property value.
     * 
     * @param pdef a property.
     * @param value its value.
     */
    @objid ("054ae88c-2834-11e2-bf07-001ec947ccaf")
    void setProperty(PropertyDefinition pdef, String value);

    /**
     * Get a property value, converted to the appropriate class according to the definition's base class.
     * 
     * @param pdef a property.
     * @return its value
     */
    @objid ("ad56a828-c13f-4f8a-bab5-e36b0ff4b0ce")
    Object getPropertyObject(PropertyDefinition pdef);

    /**
     * Set a property value.
     * 
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
     */
    @objid ("f1e997c8-6638-48b3-a67c-94560e5eb3a0")
    PropertyTableDefinition getType();

    /**
     * Setter for relation 'TypedPropertyTable->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("26f8be33-ff8a-4315-b938-c259ca03fe4e")
    void setType(PropertyTableDefinition value);

}
