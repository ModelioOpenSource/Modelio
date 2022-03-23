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
 * DynamicPropertyDefinition v1.1.1
 * 
 * 
 * <p>Property definition for typed property tables.<br />
 * Dynamic values are not stored in the property table istself, but rather calculated.</p>
 */
@objid ("a8ec075a-7c60-4d39-9a40-9e889fd66104")
public interface DynamicPropertyDefinition extends PropertyDefinition {
    /**
     * Property table name to be used when registering a property resolver.
     * 
     * @since 3.8
     * @see IDynamicPropertyResolver
     */
    @objid ("518eb7fe-afeb-49ed-88fd-8928bb28f2ad")
    public static final String PROPERTY_RESOLVER_TABLE = "Constraints";

    /**
     * Property key to be used when registering a property resolver.
     * 
     * @since 3.8
     * @see IDynamicPropertyResolver
     */
    @objid ("bac17dce-7e6a-47c4-be9d-06a3c9343d9d")
    public static final String PROPERTY_RESOLVER_KEY = "DynamicPropertyResolver";

    /**
     * The metaclass simple name.
     */
    @objid ("89573920-1fdf-495e-a072-8f378751226e")
    public static final String MNAME = "DynamicPropertyDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("a2a863b2-3739-44e4-9881-7e0d97cc4fde")
    public static final String MQNAME = "Infrastructure.DynamicPropertyDefinition";

    /**
     * Implementers of this interface are in charge of storing/loading property values.
     * <p>
     * Usage :
     * <ul>
     * <li>instanciate a property resolver</li>
     * <li>register it at metamodel level through {@link MMetamodelFragment#addDynamicBehavior(String, Object)}</li>
     * <li>on your DynamicPropertyDefinition instance, call <code>pdef.setProperty({@link DynamicPropertyDefinition#PROPERTY_RESOLVER_TABLE}, {@link DynamicPropertyDefinition#PROPERTY_RESOLVER_KEY}, resolver)</code>
     * </ul>
     * </p>
     * 
     * @since 3.8
     */
    @objid ("f64aa10f-d083-4cd7-bcd2-7dd9d1b81396")
    public interface IDynamicPropertyResolver {
        /**
         * Convert a value from a string, using the property definition's base type.
         * <p>
         * Used after reading a persisted string value in a property table.
         * </p>
         * @param pdef definition of the property being edited.
         * @param value the initial string value. Might be <code>null</code>.
         * @param element the element this conversion is done for.
         * @return the converted value. Might be <code>null</code>.
         */
        @objid ("127c2fe7-026c-43a5-8c27-544c74028bd3")
        Object convertToObject(DynamicPropertyDefinition pdef, String value, ModelElement element);

        /**
         * Convert an object value to string, according to the property definition's base type.
         * <p>
         * Used before writing a persisted string value in a property table.
         * </p>
         * @param pdef definition of the property being edited.
         * @param value a value. Might be <code>null</code>.
         * @param object the element this conversion is done for.
         * @return the value converted to string.
         */
        @objid ("78442a92-fbaa-46f2-994a-1c3830b5f211")
        String convertToString(DynamicPropertyDefinition pdef, Object value, ModelElement object);

    }

}
