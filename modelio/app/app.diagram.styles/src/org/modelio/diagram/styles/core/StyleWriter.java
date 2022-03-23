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
package org.modelio.diagram.styles.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class writes the properties of a NamedStyle to a textual property file.<br>
 * It writes two kinds of properties:
 * <ul>
 * <li>style properties ie known {@link StyleKey StyleKeys} defined in the {@link NamedStyle Style} .</li>
 * <li>admin properties about the cascading scheme</li>
 * </ul>
 */
@objid ("8587fa82-1926-11e2-92d2-001ec947c8cc")
public class StyleWriter {
    @objid ("efd071f7-3524-48a6-a060-d75f2a3e7a44")
    public String saveAsString(NamedStyle style) {
        final Properties properties = generateProperties(style);
        StringWriter sw = new StringWriter();
        try {
            properties.store(sw, "");
        } catch (IOException e) {
            DiagramStyles.LOG.error(e);
        }
        return sw.toString();
    }

    /**
     * Serializes a property value to a string.
     */
    @objid ("8587fa95-1926-11e2-92d2-001ec947c8cc")
    private static String formatValue(StyleKey sKey, Object value) {
        Class<?> type = sKey.getType();
        
        if (type == Color.class) {
            RGB rgb = ((Color) value).getRGB();
            return rgb.red + " " + rgb.green + " " + rgb.blue;
        }
        if (type == Font.class) {
            FontData fd = ((Font) value).getFontData()[0];
            return fd.getName() + ", " + fd.getHeight() + ", " + fd.getStyle();
        }
        if (type == Boolean.class) {
            return ((Boolean) value).toString();
        }
        if (type == Integer.class) {
            return ((Integer) value).toString();
        }
        if (type == String.class) {
            return (String) value;
        }
        if (type == MRef.class) {
            if (value == null) {
                return "";
            } else {
                return ((MRef) value).toString();
            }
        }
        
        if (type.isEnum()) {
            return value.toString();
        }
        
        DiagramStyles.LOG.warning("StyleWriter.formatValue(): missing converter for type '%s'", type.getName());
        return "not supported type " + type;
    }

    @objid ("a6dd9bbe-8e55-4c5a-89da-811fc11c8e57")
    private Properties generateProperties(NamedStyle style) {
        final Properties properties = new Properties();
        
        // Write admin properties
        
        // style basestyle (optional)
        if (!style.getApplicability().isEmpty()) {
            properties.setProperty(NamedStyle.BASESTYLE_ADMINKEY,
                    style.getApplicability().stream().collect(Collectors.joining(",")));
        }
        
        // style basestyle (optional)
        if (style.getCascadedStyle() instanceof NamedStyle) {
            properties.setProperty(NamedStyle.BASESTYLE_ADMINKEY, ((NamedStyle) style.getCascadedStyle()).getName());
        }
        
        // style provider (optional)
        if (style.getProvider() != null && !style.getProvider().isEmpty()) {
            properties.setProperty(NamedStyle.PROVIDER_ADMINKEY, style.getProvider());
        }
        // style name (mandatory)
        properties.setProperty(NamedStyle.STYLENAME_ADMINKEY, style.getName());
        
        // is theme (mandatory)
        properties.setProperty(NamedStyle.THEME_ADMINKEY, Boolean.toString(style.isTheme()));
        
        // Write style definition properties
        for (StyleKey skey : style.getLocalKeys()) {
            String key = skey.getId();
            String value = StyleWriter.formatValue(skey, style.getProperty(skey));
            properties.setProperty(key, value);
        }
        return properties;
    }

    /**
     * @param style the style to save.
     */
    @objid ("b636bc76-53dd-47e0-8f1e-87a5b88781a7")
    public void saveAsFile(NamedStyle style, Path aFile) {
        final Properties properties = generateProperties(style);
        
        // Write the output file
        try {
            Files.createDirectories(aFile.getParent());
            try (OutputStream outputStream = Files.newOutputStream(aFile)) {
                properties.store(outputStream, "");
            }
        } catch (IOException e) {
            DiagramStyles.LOG.error(e);
        }
        
    }

}
