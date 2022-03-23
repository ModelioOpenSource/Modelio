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
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * Dynamic style to be put on a persistent style to override some of its properties.
 * <p>
 * Allows plugin and modules to overwrite user defined style properties.
 * This style must not be persisted : {@link IStyleProvider#getPersistedStyle()} should never return a {@link DynamicStyle}.
 * <p>
 * Most of the setters redirects the call to the cascaded style so that this style is transparent for code
 * expecting a normal {@link IStyle} implementation.
 * <p>
 * To override a style property call {@link #setLocalProperty(StyleKey, Object)} instead of {@link #setProperty(StyleKey, Object)}.
 */
@objid ("8a3a38a6-a61e-44c5-831d-64d32a045945")
public class DynamicStyle extends Style {
    @objid ("0e3cb4ca-419d-4cb2-ad09-9a30e3d7a145")
    public  DynamicStyle(IStyle cascadedStyle) {
        super(cascadedStyle);
    }

    @objid ("040ab7e8-1700-4d0e-9519-467d155868f1")
    @Override
    public IStyle getBaseStyle() {
        return getCascadedStyle().getBaseStyle();
    }

    /**
     * Get all style keys for which a value has been locally set.
     * <p>
     * Overriden style keys are ignored.
     * @return locally defined style keys.
     */
    @objid ("2c2c390d-5770-43db-ba06-f46875c4a29b")
    @Override
    public Set<StyleKey> getLocalKeys() {
        Set<StyleKey> keys =getCascadedStyle().getLocalKeys();
        return keys;
    }

    /**
     * In a dynamic style, every local value is considered dynamic.
     */
    @objid ("74fec813-a751-498b-aeda-86c8f382eeb4")
    @Override
    public boolean isDynamicValue(StyleKey propertyKey) {
        return super.isLocal(propertyKey);
    }

    /**
     * @param propertyKey the key to test.
     * @return true if a local value is defined locally or on the cascaded style for the key
     */
    @objid ("c58e30c8-4bf4-4df2-932e-0dad2efded88")
    @Override
    public boolean isLocal(StyleKey propertyKey) {
        return getCascadedStyle().isLocal(propertyKey);
    }

    /**
     * Normalizing a style consists in normalizing its cascaded style.
     */
    @objid ("46b5d36d-4aa0-4be9-b04e-938b36bfe14b")
    @Override
    public void normalize() {
        this.cascadedStyle.normalize();
    }

    @objid ("e67cc397-3cf9-43b7-9750-2f3cb9967f8f")
    @Override
    public void normalize(StyleKey skey) {
        this.cascadedStyle.normalize(skey);
    }

    /**
     * Clean all the local values on the cascaded persistent style.
     */
    @objid ("41907733-7419-42ac-876e-80c232197c44")
    @Override
    public void reset() {
        getCascadedStyle().reset();
    }

    /**
     * Sets the base style of the cascaded one.
     */
    @objid ("70b1a3e4-e1ea-45b1-a147-efbf65795b74")
    @Override
    public void setBaseStyle(IStyle baseStyle) {
        getCascadedStyle().setBaseStyle(baseStyle);
    }

    /**
     * Override a style property.
     * @param key a style key
     * @param value the forced value.
     */
    @objid ("d5df7032-006a-47f6-ad53-487d9a9e28ec")
    public void setLocalProperty(StyleKey key, Object value) {
        super.setProperty(key, value);
    }

    /**
     * Modifies the cascaded persistent style.
     */
    @objid ("4c895aab-51d2-44f0-ad0c-805169d22b93")
    @Override
    public void setProperty(StyleKey key, Object value) {
        this.cascadedStyle.setProperty(key, value);
    }

    /**
     * Dynamic styles must not be persisted.
     * <p>
     * {@link IStyleProvider#getPersistedStyle()} should never return a {@link DynamicStyle}.
     */
    @objid ("d81ff8c9-bfe3-4e0a-9184-dda4568b4a6f")
    @Override
    public void write(IDiagramWriter out) {
        throw new UnsupportedOperationException("Dynamic styles must not be persisted.");
    }

}
