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

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * The style contains many properties such has the foreground and background color, the font and some display options.
 * <p>
 * A property value can be read with the {@link #getProperty(StyleKey)} method or one of the convenience accessors.<br>
 * A property value may be set with the {@link #setProperty(StyleKey, Object)} method.
 * <p>
 * These properties are intended to be displayed and edited in a properties tab.
 * 
 * @author pvlaemyn
 */
@objid ("8551246b-1926-11e2-92d2-001ec947c8cc")
public interface IStyle {
    /**
     * Register a style change listener.
     * <p>
     * The listener will be fired each time a property is changed or removed.<br>
     * Registering 2 times a listener will make it fired 2 times.
     * @param l The style change listener.
     */
    @objid ("855386a8-1926-11e2-92d2-001ec947c8cc")
    void addListener(IStyleChangeListener l);

    /**
     * Get the cascaded style used where property values are looked for when not defined on this style.
     * <p>
     * The base style may be the cascaded style or the cascaded of the cascaded style ...
     * @return The cascaded style.
     */
    @objid ("01735af4-3422-47e4-bf98-2c09deb4ab27")
    IStyle getBaseStyle();

    /**
     * Convenience method to get a boolean property.
     * @param propertyKey The property key
     * @return The boolean value.
     */
    @objid ("85538699-1926-11e2-92d2-001ec947c8cc")
    boolean getBoolean(StyleKey propertyKey);

    /**
     * Get the style where property values are looked for when not defined on this style.
     * @return The cascaded style.
     */
    @objid ("855386b4-1926-11e2-92d2-001ec947c8cc")
    IStyle getCascadedStyle();

    /**
     * Convenience method to get a Color property.
     * @param propertyKey The property key
     * @return The Color value.
     */
    @objid ("85538695-1926-11e2-92d2-001ec947c8cc")
    Color getColor(StyleKey propertyKey);

    /**
     * Convenience method to get a Font property.
     * @param propertyKey The property key
     * @return The Font value.
     */
    @objid ("8553869d-1926-11e2-92d2-001ec947c8cc")
    Font getFont(StyleKey propertyKey);

    /**
     * Convenience method to get an integer property.
     * @param propertyKey The property key
     * @return The integer value.
     */
    @objid ("855386a1-1926-11e2-92d2-001ec947c8cc")
    int getInteger(StyleKey propertyKey);

    /**
     * Get all style keys for which a value has been locally set.
     * @return locally defined style keys.
     */
    @objid ("855386b7-1926-11e2-92d2-001ec947c8cc")
    Set<StyleKey> getLocalKeys();

    /**
     * Get a style property
     * @param <T> The wanted property value type .
     * @param propertyKey The property key
     * @return The property value
     */
    @objid ("8551246d-1926-11e2-92d2-001ec947c8cc")
    <T> T getProperty(StyleKey propertyKey);

    /**
     * @param propertyKey the key to test.
     * @return true if a value is defined dynamically for the key.
     */
    @objid ("dc964689-f4da-42fd-a032-d98843915694")
    default boolean isDynamicValue(StyleKey propertyKey) {
        return false;
    }

    /**
     * @param propertyKey the key to test.
     * @return true if a local value is defined for the key
     */
    @objid ("855386ae-1926-11e2-92d2-001ec947c8cc")
    boolean isLocal(StyleKey propertyKey);

    /**
     * Normalizing a style consists in removing from its local definitions the values that are currently the same as the value in cascaded style.
     */
    @objid ("8555e8eb-1926-11e2-92d2-001ec947c8cc")
    void normalize();

    /**
     * Normalizing a style key consists in removing from thes local definitions when its values is currently the same as the value in cascaded style.
     */
    @objid ("6a41e8f2-bdef-4fad-bb82-8a70229f52e5")
    void normalize(StyleKey skey);

    /**
     * Remove a style change listener.
     * @param l a style change listener to remove.
     */
    @objid ("855386ab-1926-11e2-92d2-001ec947c8cc")
    void removeListener(IStyleChangeListener l);

    /**
     * Remove a property value and fires style changes listeners.
     * @param key The property to remove
     */
    @objid ("85538692-1926-11e2-92d2-001ec947c8cc")
    void removeProperty(StyleKey key);

    /**
     * Clean all the local values (removing them from the properties map).<br>
     * Does not modify the cascaded style
     */
    @objid ("855386b2-1926-11e2-92d2-001ec947c8cc")
    void reset();

    /**
     * Set the style to use to get a property value when it is not defined on this style.
     * <p>
     * Basic implementations will set the baseStyle as cascaded style. Proxy implementations will call the same method on their cascaded style.
     * @param baseStyle The new base style.
     */
    @objid ("fbf305fa-a108-4453-a3d9-79c710e644a8")
    void setBaseStyle(IStyle baseStyle);

    /**
     * Set the parent style used to get a property value when it is not defined on this style.
     * @param style The new parent style.
     */
    @objid ("855386a5-1926-11e2-92d2-001ec947c8cc")
    void setCascadedStyle(IStyle style);

    /**
     * Change a style property and fires the style listeners.
     * @param key The property key.
     * @param value The new value.
     */
    @objid ("85512472-1926-11e2-92d2-001ec947c8cc")
    void setProperty(StyleKey key, Object value);

    /**
     * @return <code>true</code> if this style should be considered as a diagram theme.
     */
    @objid ("ed61f8b5-1abc-43a7-ae42-f0ff38b1343a")
    default boolean isTheme() {
        return getCascadedStyle() != null && getCascadedStyle().isTheme();
    }

    /**
     * Clean the style listeners, cascaded style and handles to avoid leaks.
     */
    @objid ("d311d8aa-6c59-4341-a322-6e94c0ffbf46")
    void dispose();

}
