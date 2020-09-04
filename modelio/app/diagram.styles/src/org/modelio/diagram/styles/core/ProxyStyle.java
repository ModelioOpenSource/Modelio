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

package org.modelio.diagram.styles.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * This style has no property and delegates all to its cascading style, writing included. Setting a property on the proxy style actually modifies its cascading style.
 */
@objid ("856b5e3e-1926-11e2-92d2-001ec947c8cc")
public class ProxyStyle implements IPersistent, IStyle, IStyleChangeListener {
    @objid ("856b5e44-1926-11e2-92d2-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("856b5e40-1926-11e2-92d2-001ec947c8cc")
    private IStyle cascadedStyle = null;

    @objid ("856b5e41-1926-11e2-92d2-001ec947c8cc")
    private List<IStyleChangeListener> listeners = new ArrayList<>();

    /**
     * Constructor for deserialization only.
     */
    @objid ("856b5e46-1926-11e2-92d2-001ec947c8cc")
    public ProxyStyle() {
    }

    /**
     * Creates a style associated to an element.
     * @param cascadedStyle The parent style.
     */
    @objid ("856dc070-1926-11e2-92d2-001ec947c8cc")
    public ProxyStyle(IStyle cascadedStyle) {
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
     * @param l The style change listener.
     */
    @objid ("856dc074-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void addListener(IStyleChangeListener l) {
        this.listeners.add(l);
    }

    /**
     * Delegates to the cascaded style.
     */
    @objid ("19363dfe-4edd-4a2b-9a24-7fe672b7d221")
    @Override
    public IStyle getBaseStyle() {
        return getCascadedStyle().getBaseStyle();
    }

    /**
     * Convenience method to get a boolean property.
     * @param propertyKey The property key
     * @return The boolean value.
     */
    @objid ("856dc079-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean getBoolean(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Boolean) {
            return (Boolean) this.getProperty(propertyKey);
        } else {
            throw new IllegalArgumentException(ProxyStyle.getIllegalArgumentMessagePrefix(propertyKey) + "a boolean value");
        }
    }

    /**
     * Get the style where property values are looked for when not defined on this style.
     * @return The cascaded style.
     */
    @objid ("856dc07f-1926-11e2-92d2-001ec947c8cc")
    @Override
    public IStyle getCascadedStyle() {
        return this.cascadedStyle;
    }

    /**
     * Convenience method to get a Color property.
     * @param propertyKey The property key
     * @return The Color value.
     */
    @objid ("856dc085-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Color getColor(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Color) {
            return (Color) this.getProperty(propertyKey);
        } else {
            throw new IllegalArgumentException(ProxyStyle.getIllegalArgumentMessagePrefix(propertyKey) + "a color");
        }
    }

    /**
     * Convenience method to get a Font property.
     * @param propertyKey The property key
     * @return The Font value.
     */
    @objid ("856dc08b-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Font getFont(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Font) {
            return (Font) this.getProperty(propertyKey);
        } else {
            throw new IllegalArgumentException(ProxyStyle.getIllegalArgumentMessagePrefix(propertyKey) + "a font");
        }
    }

    /**
     * Convenience method to get an integer property.
     * @param propertyKey The property key
     * @return The integer value.
     */
    @objid ("856dc091-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getInteger(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Integer) {
            return ((Integer) this.getProperty(propertyKey)).intValue();
        }
        
        throw new IllegalArgumentException(ProxyStyle.getIllegalArgumentMessagePrefix(propertyKey) + "an integer value");
    }

    /**
     * Get all style keys for which a value has been locally set.
     * @return locally defined style keys.
     */
    @objid ("856dc097-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Set<StyleKey> getLocalKeys() {
        return getCascadedStyle().getLocalKeys();
    }

    @objid ("857749e7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return ProxyStyle.MAJOR_VERSION;
    }

    /**
     * Get a style property .
     * @param <T> The wanted property value type .
     * @param key The property key
     * @return The property value
     */
    @objid ("856dc09f-1926-11e2-92d2-001ec947c8cc")
    @Override
    public <T> T getProperty(StyleKey key) {
        if (this.cascadedStyle != null) {
            return this.cascadedStyle.getProperty(key);
        } else {
            return null;
        }
    }

    /**
     * A proxy style is stored in the stream.
     */
    @objid ("856dc0a7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * @param propertyKey the key to test.
     * @return true if a local value is defined for the key
     */
    @objid ("856dc0ae-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isLocal(StyleKey propertyKey) {
        return getCascadedStyle().isLocal(propertyKey);
    }

    /**
     * Normalizing a style consists in removing from its local definitions the values that are currently the same as the value in cascaded style.
     */
    @objid ("856dc0b5-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void normalize() {
        this.cascadedStyle.normalize();
    }

    @objid ("4b85cad2-c37c-4a50-8177-ffc37e9e5f68")
    @Override
    public void normalize(StyleKey skey) {
        this.cascadedStyle.normalize(skey);
    }

    @objid ("856dc0b9-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read the cascaded style
        this.cascadedStyle = (IStyle) in.readProperty("CascadedStyle");
        if (this.cascadedStyle != null) {
            this.cascadedStyle.addListener(this);
        }
    }

    /**
     * Remove a style change listener.
     * @param l a style change listener to remove.
     */
    @objid ("856dc0bd-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeListener(IStyleChangeListener l) {
        this.listeners.remove(l);
    }

    /**
     * Remove a property value and fires style changes listeners.
     * @param key The property to remove
     */
    @objid ("856dc0c2-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeProperty(StyleKey key) {
        getCascadedStyle().removeProperty(key);
    }

    /**
     * Clean all the local values (removing them from the properties map).<br>
     * Does not modify the cascaded style
     */
    @objid ("85728523-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void reset() {
        getCascadedStyle().reset();
    }

    /**
     * Delegates to the cascaded style.
     */
    @objid ("c87c435d-e23c-4210-a8bb-10b5df2d35ba")
    @Override
    public void setBaseStyle(IStyle baseStyle) {
        getCascadedStyle().setBaseStyle(baseStyle);
    }

    /**
     * Set the parent style used to get a property value when it is not defined on this style.
     * @param style The new parent style.
     */
    @objid ("85728527-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setCascadedStyle(IStyle style) {
        if (this.cascadedStyle == style || this == style) {
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
     * @param key The property key.
     * @param value The new value.
     */
    @objid ("8572852c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setProperty(StyleKey key, Object value) {
        getCascadedStyle().setProperty(key, value);
    }

    /**
     * Called when a property of the style of the element is modified.
     * <p>
     * The element should then update itself from the style change.
     * @param property The style property that changed
     * @param newValue The new property value
     */
    @objid ("8574e781-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        this.fireListeners(property, newValue);
    }

    /**
     * Called when a style completely changed .
     * <p>
     * The element should then update itself completely from the style.
     * @param changedStyle The style that changed
     */
    @objid ("8574e787-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void styleChanged(IStyle changedStyle) {
        // Fire listeners
        this.fireListeners();
    }

    @objid ("857749d7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        if (this.cascadedStyle != null) {
            out.writeProperty("CascadedStyle", this.cascadedStyle instanceof DynamicStyle ? this.cascadedStyle.getCascadedStyle() : this.cascadedStyle);
        }
    }

    @objid ("857749db-1926-11e2-92d2-001ec947c8cc")
    private void fireListeners(StyleKey key, Object value) {
        // Fire listeners
        for (IStyleChangeListener l : new ArrayList<>(this.listeners)) {
            l.styleChanged(key, value);
        }
    }

    @objid ("857749df-1926-11e2-92d2-001ec947c8cc")
    private void fireListeners() {
        for (IStyleChangeListener l : new ArrayList<>(this.listeners)) {
            l.styleChanged(this);
        }
    }

    /**
     * Factorization of a string literral to avoid SONAR error.
     * @param propertyKey the property key
     * @return a preformatted prefix for the message used in IllegalArgumentException constructor. Just add the flavor.
     */
    @objid ("857749e1-1926-11e2-92d2-001ec947c8cc")
    private static String getIllegalArgumentMessagePrefix(StyleKey propertyKey) {
        return "Style property key " + propertyKey + " does not match ";
    }

    @objid ("5babb690-782c-428d-8f46-8a8bfa6a9f83")
    @Override
    public String toString() {
        return "ProxyStyle [" + this.cascadedStyle + "]";
    }

}
