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

package org.modelio.diagram.api.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.modelio.diagram.api.plugin.DiagramApi;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.ui.CoreColorRegistry;
import org.modelio.ui.CoreFontRegistry;

/**
 * Helper class for converting various StyleKey types to and from strings.
 * Supported types include:
 * <ul>
 * <li><code>boolean</code></li>
 * <li><code>int</code></li>
 * <li><code>Enum</code></li>
 * <li><code>org.eclipse.swt.graphics.Color</code></li>
 * <li><code>org.eclipse.swt.graphics.Font</code></li>
 * </ul>
 * <p>
 * All conversions are made using {@link StringConverter}.
 * <p>
 * All methods declared on this class are static. This
 * class cannot be instantiated.
 * @see StringConverter
 */
@objid ("3665adf2-a604-4688-99ed-6d2b625dea08")
public class StyleKeyTypeConverter {
    @objid ("7ef59774-cc7e-4cf0-83ed-8ee6b4545ba1")
    private StyleKeyTypeConverter() {
        // Nothing to do
    }

    /**
     * Convert a value to a String.
     * @param key a style key
     * @param value its value
     * @return its value converted to string
     */
    @objid ("024df590-605c-485b-9d75-afdba3f338db")
    public static String convertToString(final StyleKey key, final Object value) {
        // Enum values, try to convert the type
        if (key.getType().isEnum()) {
            return value.toString();
        }
            
        // Boolean type
        if (key.getType().isAssignableFrom(Boolean.class)) {
            return ((Boolean) value).toString();
        }
            
        // Integer type
        if (key.getType().isAssignableFrom(Integer.class)) {
            return ((Integer) value).toString();
        }
            
        // Font type
        if (key.getType().isAssignableFrom(Font.class)) {
            return StringConverter.asString(((Font) value).getFontData()[0]);
        }
            
        // Color type
        if (key.getType().isAssignableFrom(Color.class)) {
            return StringConverter.asString(((Color) value).getRGB());
        }
            
        // String type
        if (key.getType().isAssignableFrom(String.class)) {
            return (String) value;
        }
        
        // Unknown type
        DiagramApi.LOG.warning("org.modelio.api", "Missing converted for : " + key.getId());
        return null;
    }

    /**
     * Convert stringValue to the type of the StyleKey.
     * @param key a style key
     * @param stringValue a style key value as a string
     * @return the same value converted to the style key type
     */
    @objid ("5cbff0d8-ed61-406f-93b6-62abccd9ff19")
    public static Object convertFromString(final StyleKey key, final String stringValue) {
        // Enum values, try to convert the type
        if (key.getType().isEnum()) {
            for (Object e : key.getType().getEnumConstants()) {
                if (stringValue.equals(e.toString())) {
                    return e;
                }
            }
        }
            
        // Boolean type
        if (key.getType().isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(stringValue);
        }
            
        // Integer type
        if (key.getType().isAssignableFrom(Integer.class)) {
            return Integer.valueOf(stringValue);
        }
            
        // Font type
        if (key.getType().isAssignableFrom(Font.class)) {
            return CoreFontRegistry.getFont(StringConverter.asFontData(stringValue));
        }
            
        // Color type
        if (key.getType().isAssignableFrom(Color.class)) {
            RGB rgb = StringConverter.asRGB(stringValue);
            return CoreColorRegistry.getColor(rgb);
        }
            
        // String type
        if (key.getType().isAssignableFrom(String.class)) {
            return stringValue;
        }
            
        // Unknown type
        DiagramApi.LOG.warning("org.modelio.api", "Missing converted for : " + key.getId());
        return null;
    }

}
