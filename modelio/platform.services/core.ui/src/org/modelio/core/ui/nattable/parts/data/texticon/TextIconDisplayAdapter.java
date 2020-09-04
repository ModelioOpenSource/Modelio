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

package org.modelio.core.ui.nattable.parts.data.texticon;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

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
 * <td>org.modelio.metamodel.uml.infrastructure.properties.
 * PropertyEnumerationLitteral</td>
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
 * <td>java.util.Date</td>
 * <td>Date time as long, Long.toString()</td>
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
 */
@objid ("2357d4ba-db54-44f4-a011-64d43e43ca63")
public class TextIconDisplayAdapter {
    /**
     * Convert a String value to an actual object.
     * 
     * @param type value's class type. Might be <code>null</code>, using String
     * as default type.
     * @param value the value to convert. Might be <code>null</code>.
     * @return a converted value. Might be <code>null</code>.
     */
    @objid ("cb3dd7cc-f47e-44da-8e0b-e2603bdfb0cb")
    public static Object convertToObject(Class<?> type, String value) {
        Class<?> realType;
        // If there is no base type, can only return the string
        if (type == null) {
            realType = String.class;
        } else if (Element.class.isAssignableFrom(type)) {
            realType = Element.class;
        } else if (SmAttribute.class.isAssignableFrom(type)) {
            realType = SmAttribute.class;
        } else if (SmDependency.class.isAssignableFrom(type)) {
            realType = SmDependency.class;
        } else {
            realType = type;
        }
        
        // Conversion based on the base type of the property definition
        switch (realType.getSimpleName()) {
        case "Boolean":
            return new Boolean(value);
        case "Enum":
            for (Object l : realType.getEnumConstants()) {
                if (l.toString().equals(value)) {
                    return l;
                }
            }
            return realType.getEnumConstants()[0];
        case "Float":
            if (value == null || value.isEmpty()) {
                return Float.valueOf(0.0f);
            } else {
                return Float.valueOf(value);
            }
        case "Integer":
            if (value == null || value.isEmpty()) {
                return Integer.valueOf(0);
            }
            try {
                return Integer.valueOf(Float.valueOf(value).intValue());
            } catch (@SuppressWarnings("unused") final NumberFormatException e) {
                return Integer.valueOf(Integer.MIN_VALUE);
            }
        case "Element":
            if (value != null) {
                try {
                    return new MRef(value);
                } catch (@SuppressWarnings("unused") final IllegalArgumentException e) {
                    return null;
                }
            }
            return null;
        case "Time":
            if (value == null || value.isEmpty()) {
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings("unused") final NumberFormatException e) {
                return new Date();
            }
        case "Date":
            if (value == null || value.isEmpty()) {
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings("unused") final NumberFormatException e) {
                return new Date();
            }
        case "String":
        default:
            return value;
        
        }
    }

    /**
     * Convert an object to a String representation.
     * 
     * @param type value's class type. Might be <code>null</code>, using
     * <code>value.getClass()</code> as default type.
     * @param value the value to convert. Might be <code>null</code>.
     * @return a converted value.
     */
    @objid ("378b1344-f7a3-44a4-a054-7eb79444b021")
    public static String convertToString(Class<?> type, Object value) {
        // Get rid of trivial case
        if (value == null) {
            return "";
        }
        
        // Get effective type to convert. The reason why we have to compute an
        // effective type is that it might occur that 'value' is not of the kind
        // given by 'type', this situation is probably a mistake from the caller
        // but we do want a solid fail safe conversion.
        
        Class<?> realType;
        
        if (type == null) {
            realType = value.getClass();
        } else if (type.isAssignableFrom(value.getClass())) {
            // Value is of the expected type...
            realType = type;
        } else {
            // We need to find a fallback option
            if (Element.class.isAssignableFrom(value.getClass())) {
                realType = Element.class;
            } else if (SmAttribute.class.isAssignableFrom(value.getClass())) {
                realType = SmAttribute.class;
            } else if (SmDependency.class.isAssignableFrom(value.getClass())) {
                realType = SmDependency.class;
            } else {
                realType = value.getClass();
            }
        }
        
        switch (realType.getSimpleName()) {
        case "Boolean":
            return Boolean.toString((Boolean) value);
        case "Enum":
            return ((Enum<?>) value).name();
        case "Integer":
            return value.toString();
        case "String":
            return value.toString();
        case "Date":
            return Long.toString(((Date) value).getTime());
        case "Element":
            return ((Element) value).getName();
        case "Float":
            return value.toString();
        case "SmAttribute":
            return ((SmAttribute) value).getName();
        case "SmDependency":
            return ((SmDependency) value).getName();
        case "Time":
            return Long.toString(((Date) value).getTime());
        default:
            // Last resort : a model element of any kind
            if (value instanceof Element) {
                return ((Element) value).getName();
            } else {
                return value.toString();
            }
        }
    }

}
