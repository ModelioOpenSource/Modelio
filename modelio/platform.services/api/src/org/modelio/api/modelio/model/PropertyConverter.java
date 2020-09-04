/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Provides conversion facilities for property values.
 * <table border='1'>
 * <th>Enum Type</th>
 * <th>Java Object type</th>
 * <th>Storage string</th>
 * <tr align='left'>
 * <td>BOOLEAN</td>
 * <td>java.lang.Boolean</td>
 * <td>Boolean.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>ENUMERATE</td>
 * <td>org.modelio.metamodel.uml.infrastructure.properties. PropertyEnumerationLitteral</td>
 * <td>The property literal name</td>
 * </tr>
 * <tr align='left'>
 * <td>FLOAT</td>
 * <td>java.lang.Float</td>
 * <td>Float.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>UNSIGNED</td>
 * <td>java.lang.Integer</td>
 * <td>Integer.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>INTEGER</td>
 * <td>java.lang.Integer</td>
 * <td>Integer.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>ELEMENT</td>
 * <td>MRef</td>
 * <td>MRef.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>RICHTEXT</td>
 * <td>?</td>
 * <td>?</td>
 * </tr>
 * <tr align='left'>
 * <td>TIME</td>
 * <td>java.sql.Time</td>
 * <td>Time as long, long.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>DATE</td>
 * <td>java.util.Date</td>
 * <td>Date as long, long.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>STRING</td>
 * <td>java.lang.String</td>
 * <td>the string itself</td>
 * </tr>
 * <tr align='left'>
 * <td>TEXT</td>
 * <td>java.lang.String</td>
 * <td>the string itself</td>
 * </tr>
 * </table>
 * 
 * @since 3.4
 */
@objid ("f883ec22-6bd8-45d0-9c06-88fbfc64a101")
public class PropertyConverter {
    /**
     * @param pType the PropertyType, cannot be null
     * @param value the value to be converted.
     */
    @objid ("c1f2524c-e87d-4b55-943f-2b5d1f978197")
    public static Object convertToObject(PropertyType pType, String value, ModelElement modelElement) {
        // If there is no base type, can only return the string
        if (pType.getBaseType() == null) {
            return value;
        }
        
        // Conversion based on the base type of the property definition
        switch (pType.getBaseType()) {
        case BOOLEAN:
            return new Boolean(value);
        case ENUMERATE:
            final EnumeratedPropertyType type = (EnumeratedPropertyType) pType;
            for (final PropertyEnumerationLitteral v : type.getLitteral()) {
                if (v.getName().equals(value)) {
                    return v;
                }
            }
            return null;
        case FLOAT:
            if (value == null || value.isEmpty()) {
                return Float.valueOf("0.0");
            } else {
                return Float.valueOf(value);
            }
        case UNSIGNED:
        case INTEGER:
            if (value == null || value.isEmpty()) {
                return Integer.valueOf(0);
            }
            try {
                return Integer.valueOf(Float.valueOf(value).intValue());
            } catch (final NumberFormatException e) {
                return Integer.valueOf(Integer.MIN_VALUE);
            }
        case ELEMENT:
            if (value != null) {
                try {
                    return new MRef(value);
                } catch (final IllegalArgumentException e) {
                    return null;
                }
            }
            return null;
        case RICHTEXT:
            if (value != null) {
                try {
                    return new MRef(value);
                } catch (final IllegalArgumentException e) {
                    // Ignore invalid MREf
                }
            }
            // Return the edited element for RichNote creation...
            return new MRef(modelElement);
        case TIME:
            if (value == null || value.isEmpty()) {
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (final NumberFormatException e) {
                return new Date();
            }
        case DATE:
            if (value == null || value.isEmpty()) {
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (final NumberFormatException e) {
                return new Date();
            }
        case STRING:
        case TEXT:
        default:
            return value;
        
        }
    }

    @objid ("498cc454-6b6b-48e4-8cf4-64a1cedd1c80")
    public static String convertToString(PropertyType pType, Object value) {
        if (value == null) {
            return "";
        }
        
        switch (pType.getBaseType()) {
        case BOOLEAN:
            return Boolean.toString((Boolean) value);
        case ENUMERATE:
            return ((PropertyEnumerationLitteral) value).getName();
        case INTEGER:
            return value.toString();
        case STRING:
            return value.toString();
        case DATE:
            return String.valueOf(((Date) value).getTime());
        case ELEMENT:
            return value.toString();
        case FLOAT:
            return value.toString();
        case RICHTEXT:
            return value.toString();
        case TEXT:
            return value.toString();
        case TIME:
            return String.valueOf(((Date) value).getTime());
        case UNSIGNED:
            return value.toString();
        default:
            return "?" + value.getClass().getSimpleName() + "?";
        }
    }

    @objid ("f75fcdca-81ea-4e9c-a5da-0e872e42d139")
    public static Object convertToObject(PropertyDefinition pdef, String value, ModelElement modelElement) {
        return PropertyConverter.convertToObject(pdef.getType(), value, modelElement);
    }

    @objid ("2ed4ca47-462c-40b7-bef5-eb1915f7b5da")
    public static String convertToString(PropertyDefinition pdef, Object value) {
        return PropertyConverter.convertToString(pdef.getType(), value);
    }

}
