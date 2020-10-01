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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * The style provides many properties such has the foreground and background color, the font and some display options.
 * <p>
 * A Style holds a local property map where the property value are fetched from. A Style is also attached to a cascadedStyle that is
 * used as a defaulting mechanism when a property value is not available in the local map.
 */
@objid ("857749ec-1926-11e2-92d2-001ec947c8cc")
public class Style implements IPersistent, IStyle, IStyleChangeListener {
    @objid ("857749f6-1926-11e2-92d2-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("857749f2-1926-11e2-92d2-001ec947c8cc")
    protected IStyle cascadedStyle = null;

    @objid ("857749f3-1926-11e2-92d2-001ec947c8cc")
    protected List<IStyleChangeListener> listeners = new ArrayList<>();

    @objid ("857749ee-1926-11e2-92d2-001ec947c8cc")
    protected Map<StyleKey, Object> properties = new HashMap<>();

    @objid ("1067fa01-084d-4dbd-9d52-2e9a8834f7cc")
    private Map<String, Object> obsoleteProps;

    /**
     * Creates a style associated to an element.
     * 
     * @param cascadedStyle The parent style.
     */
    @objid ("8579ac32-1926-11e2-92d2-001ec947c8cc")
    public Style(IStyle cascadedStyle) {
        assert (cascadedStyle != null);
        this.cascadedStyle = cascadedStyle;
        if (this.cascadedStyle != null) {
            this.cascadedStyle.addListener(this);
        }
    }

    /**
     * Register a style change listener.
     * <p>
     * The listener will be fired each time a property is changed or removed.<br>
     * Registering 2 times a listener will make it fired 2 times.
     * 
     * @param l The style change listener.
     */
    @objid ("8579ac36-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void addListener(IStyleChangeListener l) {
        this.listeners.add(l);
    }

    @objid ("1b471a23-a35c-43c3-bc23-4893b0653e76")
    @Override
    public IStyle getBaseStyle() {
        return getCascadedStyle();
    }

    /**
     * Convenience method to get a boolean property.
     * 
     * @param propertyKey The property key
     * @return The boolean value.
     */
    @objid ("8579ac3b-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean getBoolean(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Boolean) {
            return (Boolean) this.getProperty(propertyKey);
        } else {
            // return false;
            throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a boolean value");
        }
    }

    @objid ("8579ac41-1926-11e2-92d2-001ec947c8cc")
    @Override
    public IStyle getCascadedStyle() {
        return this.cascadedStyle;
    }

    /**
     * Convenience method to get a Color property.
     * 
     * @param propertyKey The property key
     * @return The Color value.
     */
    @objid ("8579ac46-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Color getColor(StyleKey propertyKey) {
        final Object value = this.getProperty(propertyKey);
        if (value == null || value instanceof Color) {
            return (Color) value;
        } else {
            throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a color");
        }
    }

    /**
     * Convenience method to get a Font property.
     * 
     * @param propertyKey The property key
     * @return The Font value.
     */
    @objid ("8579ac4c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Font getFont(StyleKey propertyKey) {
        final Object value = this.getProperty(propertyKey);
        if (value == null || value instanceof Font) {
            return (Font) value;
        } else {
            throw new IllegalArgumentException(propertyKey + "Style property key value '" + value + "' does not match a font");
        }
    }

    /**
     * Convenience method to get an integer property.
     * 
     * @param propertyKey The property key
     * @return The integer value.
     */
    @objid ("8579ac52-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getInteger(StyleKey propertyKey) {
        final Object value = this.getProperty(propertyKey);
        if (value instanceof Integer) {
            return ((Integer) value).intValue();
        } else {
            throw new IllegalArgumentException("Style property key " + propertyKey + " does not match an integer value");
        }
    }

    @objid ("8579ac58-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Set<StyleKey> getLocalKeys() {
        return this.properties.keySet();
    }

    @objid ("857c0eb5-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Get a style property .
     * @param <T>
     * The wanted property value type .
     * 
     * @param key The property key
     * @return The property value
     */
    @objid ("8579ac5f-1926-11e2-92d2-001ec947c8cc")
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProperty(StyleKey key) {
        if (this.properties.containsKey(key)) {
            return (T) this.properties.get(key);
        } else {
            return this.cascadedStyle.getProperty(key);
        }
    }

    /**
     * A normal style is stored in the writer.
     */
    @objid ("8579ac66-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * Tells whether the given property is set locally.
     * 
     * @param propertyKey a style key
     * @return true if a local value is defined for the key, false in the other case.
     */
    @objid ("8579ac6d-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isLocal(StyleKey propertyKey) {
        return this.properties.containsKey(propertyKey);
    }

    /**
     * Normalizing a style consists in removing from its local definitions the values that are currently the same as the value in
     * cascaded style. For Font a special comparison has to be carried out.
     */
    @objid ("857c0eb1-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void normalize() {
        for (StyleKey skey : new ArrayList<>(getLocalKeys())) {
            normalize(skey);
        }
    }

    @objid ("ae4220bb-1413-4353-85ad-ac4a50e99e6f")
    @Override
    public void normalize(StyleKey skey) {
        Object localValue = this.getProperty(skey);
        Object cascadedValue = getCascadedStyle().getProperty(skey);
        
        if (localValue instanceof Font) {
            FontData fd1 = ((Font) localValue).getFontData()[0];
            FontData fd2 = ((Font) cascadedValue).getFontData()[0];
        
            if (fd1.getHeight() == fd2.getHeight() && fd1.getStyle() == fd2.getStyle() && fd1.getName().equals(fd2.getName())) {
                removeProperty(skey);
            }
        
        } else {
            if (localValue.equals(cascadedValue)) {
                removeProperty(skey);
            }
        }
    }

    @objid ("8579ac74-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read the cascaded style
        this.cascadedStyle = (IStyle) in.readProperty("CascadedStyle");
        if (this.cascadedStyle != null) {
            this.cascadedStyle.addListener(this);
        }
        
        // Read the local properties
        final Map<String, Object> allAtts = in.readAllProperties();
        
        this.properties = new HashMap<>(allAtts.size());
        
        for (Entry<String, Object> e : allAtts.entrySet()) {
            final StyleKey stylekey = StyleKey.getInstance(e.getKey());
            if (stylekey != null) {
                this.properties.put(stylekey, e.getValue());
            } else {
                if (this.obsoleteProps == null) {
                    this.obsoleteProps = new HashMap<>();
                }
                this.obsoleteProps.put(e.getKey(), e.getValue());
            }
        
        }
    }

    /**
     * Remove a style change listener.
     * 
     * @param l a style change listener to remove.
     */
    @objid ("8579ac78-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeListener(IStyleChangeListener l) {
        this.listeners.remove(l);
    }

    /**
     * Remove a property value and fires style changes listeners.
     * 
     * @param key The property to remove
     */
    @objid ("8579ac7d-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeProperty(StyleKey key) {
        if (this.properties.remove(key) != null) {
            this.fireListeners(key, null);
        }
    }

    /**
     * Clean all the local values (removing them from the properties map) Does not modify the cascaded style
     */
    @objid ("857c0e8d-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void reset() {
        this.properties.clear();
        this.fireListeners();
    }

    @objid ("16426da8-b39b-47c5-90f8-0cbead63497e")
    @Override
    public void setBaseStyle(IStyle baseStyle) {
        setCascadedStyle(baseStyle);
    }

    /**
     * Set the parent style used to get a property value when it is not defined on this style.
     * 
     * @param style The new parent style.
     */
    @objid ("857c0e91-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setCascadedStyle(IStyle style) {
        // Avoid infinite loops
        if (style == this) {
            return;
        }
        
        // Remove listener from parent
        if (this.cascadedStyle != null) {
            this.cascadedStyle.removeListener(this);
        }
        
        this.cascadedStyle = style;
        
        // Add listener to new parent
        if (this.cascadedStyle != null) {
            this.cascadedStyle.addListener(this);
        }
        
        this.fireListeners();
    }

    /**
     * Change a style property and fires the style listeners.
     * 
     * @param key The property key.
     * @param value The new value.
     */
    @objid ("857c0e96-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setProperty(StyleKey key, Object value) {
        key.validate(value);
        
        if (Objects.equals(value, this.properties.get(key))) {
            // no op, return.
            return;
        }
        
        // Set the property & fire the listeners
        this.properties.put(key, value);
        this.fireListeners(key, value);
    }

    /**
     * Called when a property of the style of the element is modified.
     * <p>
     * The element should then update itself from the style change.
     */
    @objid ("857c0e9c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        this.fireListeners(property, newValue);
    }

    /**
     * Called when a style completely changed .
     * <p>
     * The element should then update itself completely from the style.
     */
    @objid ("857c0ea2-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void styleChanged(IStyle style) {
        // Fire listeners
        this.fireListeners();
    }

    @objid ("857c0ea7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        if (this.cascadedStyle != null) {
            out.writeProperty("CascadedStyle", this.cascadedStyle);
        }
        
        // Write the local properties
        for (Entry<StyleKey, Object> e : this.properties.entrySet()) {
            // StyleKey is not persistent, we write only its name
            out.writeProperty(e.getKey().getId(), e.getValue());
        }
    }

    @objid ("857c0eab-1926-11e2-92d2-001ec947c8cc")
    private void fireListeners(StyleKey key, Object value) {
        // Fire listeners
        for (IStyleChangeListener l : new ArrayList<>(this.listeners)) {
            l.styleChanged(key, value);
        }
    }

    @objid ("857c0eaf-1926-11e2-92d2-001ec947c8cc")
    private void fireListeners() {
        for (IStyleChangeListener l : new ArrayList<>(this.listeners)) {
            l.styleChanged(this);
        }
    }

    /**
     * Get the properties for which no style key was found.
     * <p>
     * The content of this map will be definitively lost on next diagram save.
     * To be used when migrating graphic objects whose style key changed.
     * 
     * @return the obsolete properties, never null.
     */
    @objid ("9bedddb8-f785-40c5-a3ab-5daf5d04d62e")
    public Map<String, Object> getObsoleteProperties() {
        return this.obsoleteProps == null ? Collections.emptyMap() : this.obsoleteProps;
    }

}
