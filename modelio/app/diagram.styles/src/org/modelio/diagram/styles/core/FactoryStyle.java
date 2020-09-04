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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Factory settings for style property values. This class must define a VALID value for each StyleKey.
 * <p>
 * <b>Important note about Fonts and Colors:</b><br>
 * Fonts and Colors of the factory style must not be allocated from the DiagramElement plugin font and color factories, as these
 * factories have a lifecycle bound to the session duration (they are cleaned up, disposing their ressources on session close).
 * FactoryStyle is statically initialised and its lifecycle is that of the application itself. It does not dispose its graphics
 * ressources (not needed).
 * </p>
 */
@objid ("85479aee-1926-11e2-92d2-001ec947c8cc")
public class FactoryStyle implements IStyle, IPersistent {
    @objid ("8549fd2c-1926-11e2-92d2-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("85479af0-1926-11e2-92d2-001ec947c8cc")
    private static final FactoryStyle INSTANCE = new FactoryStyle();

    @objid ("8549fd2b-1926-11e2-92d2-001ec947c8cc")
    private final FactoryStyleDefaults factoryStyleDefaults;

    @objid ("85479af1-1926-11e2-92d2-001ec947c8cc")
    private final Map<StyleKey, Object> properties;

    @objid ("20334efb-c619-4eae-881f-dc01481c9f7d")
    private final Collection<AbstractStyleKeyProvider> styleKeyProviders = new ArrayList<>();

    /**
     * Register a style change listener.
     * <p>
     * The listener will be fired each time a property is changed or removed.<br>
     * Registering 2 times a listener will make it fired 2 times.
     * @param l The style change listener.
     */
    @objid ("8549fd35-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void addListener(IStyleChangeListener l) {
        // Nothing to do yet.
    }

    /**
     * Modelio plugin implementing diagrams can declare their {@link AbstractStyleKeyProvider} classes to the FactoryStyle so that
     * their {@link StyleKey} instances are known by the FactoryStyle.
     * @param styleProvider the style key provider to register.
     * @throws java.lang.IllegalArgumentException if the style provider instantiation failed. A nested exception is then provided.
     */
    @objid ("8549fd3a-1926-11e2-92d2-001ec947c8cc")
    public void declareProvider(Class<? extends AbstractStyleKeyProvider> styleProvider) throws IllegalArgumentException {
        // Create a dumb instance.
        // This will force class loading and static members initialisation
        try {
            declareProvider(styleProvider.newInstance());
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Modelio plugin implementing diagrams can declare their {@link AbstractStyleKeyProvider} instances to the FactoryStyle so that
     * their {@link StyleKey} instances are known by the FactoryStyle.
     * @param styleProvider the style key provider to register.
     * @throws java.lang.IllegalArgumentException if the style provider instantiation failed. A nested exception is then provided.
     */
    @objid ("e5d1eeb1-e81e-428f-b47e-d030c2dc3cc8")
    public void declareProvider(AbstractStyleKeyProvider styleProvider) throws IllegalArgumentException {
        // Fake using instance.
        // This will force class loading and members initialization
        styleProvider.getStyleKeys();
        // record the provider
        this.styleKeyProviders.add(styleProvider);
    }

    @objid ("1812dab0-4ff3-49b5-b3af-170fe33ffa64")
    @Override
    public IStyle getBaseStyle() {
        return this;
    }

    /**
     * Convenience method to get a boolean property.
     * @param propertyKey The property key
     * @return The boolean value.
     */
    @objid ("8549fd40-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean getBoolean(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Boolean) {
            return ((Boolean) this.getProperty(propertyKey)).booleanValue();
        }
        // return false;
        throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a boolean value");
    }

    @objid ("8549fd46-1926-11e2-92d2-001ec947c8cc")
    @Override
    public IStyle getCascadedStyle() {
        return this;
    }

    /**
     * Convenience method to get a Color property.
     * @param propertyKey The property key
     * @return The Color value.
     */
    @objid ("8549fd4b-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Color getColor(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Color) {
            return (Color) this.getProperty(propertyKey);
        }
        throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a color");
    }

    /**
     * Convenience method to get a Font property.
     * @param propertyKey The property key
     * @return The Font value.
     */
    @objid ("8549fd51-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Font getFont(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Font) {
            return (Font) this.getProperty(propertyKey);
        }
        throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a font");
    }

    /**
     * Get the factory style instance.
     * @return the factory style instance.
     */
    @objid ("8549fd2e-1926-11e2-92d2-001ec947c8cc")
    public static FactoryStyle getInstance() {
        return FactoryStyle.INSTANCE;
    }

    /**
     * Convenience method to get an integer property.
     * @param propertyKey The property key
     * @return The integer value.
     */
    @objid ("8549fd57-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getInteger(StyleKey propertyKey) {
        if (this.getProperty(propertyKey) instanceof Integer) {
            return ((Integer) this.getProperty(propertyKey)).intValue();
        }
        
        throw new IllegalArgumentException("Style property key " + propertyKey + " does not match a integer value");
    }

    @objid ("8549fd5d-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Set<StyleKey> getLocalKeys() {
        return this.properties.keySet();
    }

    @objid ("854ec20a-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return FactoryStyle.MAJOR_VERSION;
    }

    /**
     * Get a style property
     * @param propertyKey The property key
     * @return The property value
     */
    @objid ("8549fd64-1926-11e2-92d2-001ec947c8cc")
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProperty(StyleKey propertyKey) {
        // first look up in the local properties
        Object value = this.properties.get(propertyKey);
        
        // if not found, try the defaults provider
        if (value == null) {
            value = FactoryStyleDefaults.getDefaultValue(propertyKey);
        }
        
        assert (value != null || propertyKey.getType() == MRef.class) : "No factory setting for property " + propertyKey;
        return (T) value;
    }

    /**
     * @return all registered style key providers.
     */
    @objid ("0a5354f9-fe3d-47a7-9f70-f0f24a856159")
    public Collection<AbstractStyleKeyProvider> getStyleKeyProviders() {
        return this.styleKeyProviders;
    }

    /**
     * Inject the values defined in 'properties' into the FactoryStyle settings overriding existing values when required.
     * @param defaultValues the values to inject in the FactoryStyle
     */
    @objid ("854c5f84-1926-11e2-92d2-001ec947c8cc")
    public void injectDefaultValues(Map<StyleKey, Object> defaultValues) {
        for (Entry<StyleKey, Object> entry : defaultValues.entrySet()) {
            StyleKey sKey = entry.getKey();
            Object value = entry.getValue();
            assert (value != null);
            this.properties.put(sKey, value);
        }
    }

    /**
     * The factory style is always external.
     */
    @objid ("854c5f8b-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return true;
    }

    /**
     * @param propertyKey the key for which to search a local value.
     * @return true if a local value is defined for the key.
     */
    @objid ("854ec1df-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isLocal(StyleKey propertyKey) {
        return this.properties.containsKey(propertyKey);
    }

    @objid ("854ec207-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void normalize() {
        // nothing to do
    }

    @objid ("b7df0492-2542-41f3-b21c-1e9af92af9c3")
    @Override
    public void normalize(StyleKey skey) {
        // nothing to do
    }

    @objid ("854ec1e6-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Should throw an UnsupportedOperationException but do nothing
        // to not break existing projects.
    }

    /**
     * Remove a style change listener.
     * @param l a style change listener to remove.
     */
    @objid ("854ec1ea-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeListener(IStyleChangeListener l) {
        // Nothing to do yet.
    }

    /**
     * Remove a property value and fires style changes listeners.
     * @param key The property to remove
     */
    @objid ("854ec1ef-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void removeProperty(StyleKey key) {
        if (this.properties != null) {
            this.properties.remove(key);
        }
    }

    /**
     * Clean all the local values (removing them from the properties map) Does not modify the cascaded style
     */
    @objid ("854ec1f4-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void reset() {
        // OP operation on factory settings.
        throw new IllegalArgumentException("FactorySettings cannot be resetted");
    }

    @objid ("607bbaf1-040d-4878-a904-40ed639f09b3")
    @Override
    public void setBaseStyle(IStyle baseStyle) {
        throw new UnsupportedOperationException("Illegal attempt to set factory parent style");
    }

    /**
     * Set the parent style used to get a property value when it is not defined on this style.
     * @param style The new parent style.
     */
    @objid ("854ec1f8-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setCascadedStyle(IStyle style) {
        throw new UnsupportedOperationException("Illegal attempt to set factory parent style");
    }

    /**
     * Change a style property and fires the style listeners.
     * @param key The property key.
     * @param value The new value.
     */
    @objid ("854ec1fd-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void setProperty(StyleKey key, Object value) {
        throw new UnsupportedOperationException("Illegal attempt to set factory setting for property " + key + " = "+value);
    }

    @objid ("854ec203-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        // Write the external reference
        out.writeExtRef(this, "", "factory");
    }

    @objid ("8549fd33-1926-11e2-92d2-001ec947c8cc")
    private FactoryStyle() {
        // setup the ultimate default provider
        this.factoryStyleDefaults = new FactoryStyleDefaults();
        this.properties = new HashMap<>();
    }

    @objid ("f15352ac-135e-4912-96a0-8ae56741ac8a")
    @Override
    public String toString() {
        return "FactoryStyle []";
    }

}
