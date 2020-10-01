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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a style property key.
 * <p>
 * {@link StyleKey} are composed of a key and a {@link MetaKey meta-key}. <br>
 * The key is used to associate the <code>StyleKey</code> to a particular metaclass. The meta-key describes the 'nature' of the
 * property, it might be the no-meta-key <i>null</i> value.<br>
 * <p>
 * {@link MetaKey} are used to compare and match <code>StyleKey</code> instances in order to decide that they match even though their
 * metaclass keys differs. The <i>null</i> value matches with absolutely no metakey including itself.
 * <p>
 * Meta-keys defines 'concepts' for properties. For example the {@link MetaKey#FILLCOLOR} represents the concept of the
 * background color of a 'box-like' graphic. Therefore, when cloning properties between let say a Class and a Package,
 * it is possible to copy the CLAZZ_FILLCOLOR property of the class to the PACKAGE_FILLCOLOR property of the package
 * because both these properties are based on the same {@link MetaKey#FILLCOLOR} metakey.<br>
 * The important rule when defining new <code>StyleKey</code> instances, is that the pair (key, metakey) is strictly unique.
 * <p>
 * All style properties for a graphic model class are enumerated as static attributes in
 * a dedicated style key class extending the {@link AbstractStyleKeyProvider} class.
 */
@objid ("857c0eba-1926-11e2-92d2-001ec947c8cc")
public class StyleKey {
    /**
     * Unique identifier for the style key.
     */
    @mdl.prop
    @objid ("27a31d9c-1927-11e2-92d2-001ec947c8cc")
    private String id;

    @mdl.propgetter
    public String getId() {
        // Automatically generated method. Please do not modify this code.
        return this.id;
    }

    /**
     * Label displayed in the GUI.
     */
    @mdl.prop
    @objid ("27a7e24f-1927-11e2-92d2-001ec947c8cc")
    private String label;

    @mdl.propgetter
    public String getLabel() {
        // Automatically generated method. Please do not modify this code.
        return this.label;
    }

    /**
     * Tooltip displayable by the GUI.
     */
    @mdl.prop
    @objid ("27a7e257-1927-11e2-92d2-001ec947c8cc")
    private String tooltip;

    @mdl.propgetter
    public String getTooltip() {
        // Automatically generated method. Please do not modify this code.
        return this.tooltip;
    }

    /**
     * Category that can be used to group style keys.
     */
    @mdl.prop
    @objid ("27aca706-1927-11e2-92d2-001ec947c8cc")
    private String category;

    @mdl.propgetter
    public String getCategory() {
        // Automatically generated method. Please do not modify this code.
        return this.category;
    }

    /**
     * Meta-keys defines 'concepts' for properties. For example the MetaKey.FILLCOLOR represents the concept of the
     * background color of a 'box-like' graphic. Therefore, when cloning properties between let say a Class and a
     * Package, it is possible to copy the CLAZZ_FILLCOLOR property of the class to the PACKAGE_FILLCOLOR property of
     * the package because both these properties are based on the same MetaKey.FILLCOLOR metakey.
     */
    @objid ("857c0ed0-1926-11e2-92d2-001ec947c8cc")
    private MetaKey metakey;

    /**
     * Enumerates all created style keys.
     */
    @objid ("857c0ed2-1926-11e2-92d2-001ec947c8cc")
    private static Map<String, StyleKey> instances = new HashMap<>();

    /**
     * Type of the style key.
     */
    @mdl.prop
    @objid ("de20fe3a-cbf5-4713-81d9-7463a386b6de")
    private Class<?> type;

    @mdl.propgetter
    public Class<?> getType() {
        // Automatically generated method. Please do not modify this code.
        return this.type;
    }

    /**
     * Return the StyleKey instance for a given string key.
     * 
     * @param key the key string value
     * @return the StyleKey for the given string key or null if none found.
     */
    @objid ("8580d350-1926-11e2-92d2-001ec947c8cc")
    public static StyleKey getInstance(String key) {
        return instances.get(key);
    }

    /**
     * Get all instances of StyleKey.
     * 
     * @return all created style keys.
     */
    @objid ("8580d356-1926-11e2-92d2-001ec947c8cc")
    public static Collection<StyleKey> getInstances() {
        return instances.values();
    }

    /**
     * Creates a style key.
     * 
     * @param id The style key id.
     * @param type The type of the style key.
     * @param label the key I18n label
     * @param tooltip the key I18n tooltip
     * @param category the key I18n category
     */
    @objid ("8580d35d-1926-11e2-92d2-001ec947c8cc")
    public StyleKey(String id, Class<?> type, String label, String tooltip, String category) {
        this.id = id;
        this.metakey = null;
        this.type = type;
        this.label = label;
        this.tooltip = tooltip;
        this.category = category;
        instances.put(this.id, this);
    }

    /**
     * Creates a StyleKey based on a MetaKey.
     * 
     * @param id The style id
     * @param metakey the base meta key.
     * @param label the key I18n label
     * @param tooltip the key I18n tooltip
     * @param category the key I18n category
     */
    @objid ("8583359f-1926-11e2-92d2-001ec947c8cc")
    public StyleKey(String id, MetaKey metakey, String label, String tooltip, String category) {
        this.id = id;
        this.metakey = metakey;
        this.type = metakey.getType();
        this.label = label;
        this.tooltip = tooltip;
        this.category = category;
        instances.put(this.id, this);
    }

    @objid ("858335a7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        StyleKey other = (StyleKey) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Get the meta key if any.
     * 
     * @return the meta key or null if the style key is not based on a meta key.
     */
    @objid ("858335ad-1926-11e2-92d2-001ec947c8cc")
    public MetaKey getMetakey() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.metakey;
    }

    @objid ("858335b2-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    /**
     * Compare this style key with the given one for matching.
     * <p>
     * The style keys match if they are the same or have the same meta key.
     * 
     * @param k The style key to compare with.
     * @return true if the style keys are the same or have the same meta key.
     */
    @objid ("858335b7-1926-11e2-92d2-001ec947c8cc")
    public boolean match(StyleKey k) {
        return (equals(k)) || (this.metakey != null && (this.metakey.equals(k.metakey)));
    }

    @objid ("858335bd-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String toString() {
        return this.id;
    }

    /**
     * Checks the given value is valid for this style key.
     * <p>
     * Throws an exception explaining the problem if the value is not valid.
     * The exception message may be directly displayed in the GUI so must be human readable.
     * 
     * @param value the style key value to check
     * @throws java.lang.IllegalArgumentException if the value is not valid. The message tells why.
     */
    @objid ("abef35ab-465b-4112-86b7-ff622bb2b57c")
    public void validate(Object value) throws IllegalArgumentException {
        // Accepts null only for MRef
        if (value == null && getType() == MRef.class) {
            return;
        }
        
        // Check the value type matches the style key type
        if (!getType().isInstance(value)) {
            String msg = DiagramStyles.I18N.getMessage("StyleKey.InvalidValue", String.valueOf(value), this.id, getType().getSimpleName());
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Fill mode type.
     */
    @objid ("858335c2-1926-11e2-92d2-001ec947c8cc")
    public enum FillMode {
        /**
         * The figure must be transparent.
         */
        TRANSPARENT,
        /**
         * The figure is filled with a solid color.
         */
        SOLID,
        /**
         * The figure is filled with a gradient.
         */
        GRADIENT;
    }

    /**
     * Name display mode.
     */
    @objid ("858597f7-1926-11e2-92d2-001ec947c8cc")
    public enum ShowNameMode {
        /**
         * Don't display the name.
         */
        NONE,
        /**
         * Simple mode should display just the name.
         */
        SIMPLE,
        /**
         * Qualified mode should display the name of the element with its parent name.
         */
        QUALIFIED,
        /**
         * Qualified mode should display the name of the element with its absolute path from the root package.
         */
        FULLQUALIFIED;
    }

    /**
     * Stereotype display mode.
     */
    @objid ("85859801-1926-11e2-92d2-001ec947c8cc")
    public enum ShowStereotypeMode {
        /**
         * Don't display stereotypes at all.
         */
        NONE,
        /**
         * Display a small icon for each stereotype.
         */
        ICON,
        /**
         * Display a <<A stereotype>> label for each stereotype.
         */
        TEXT,
        /**
         * Display a <<A stereotype>> label and a small icon for each stereotype.
         */
        TEXTICON;
    }

    /**
     * Controls which namespaces and features are automatically displayed on a namespace.
     */
    @objid ("8585980b-1926-11e2-92d2-001ec947c8cc")
    public enum UmaskByVisibilityStragegy {
        /**
         * Display all namespaces or features.
         */
        ALL,
        /**
         * Display only public namespaces or features.
         */
        ALL_PUBLIC,
        /**
         * Display only all public, protected and package private features and namespaces.
         */
        ALL_NON_PRIVATE,
        /**
         * No automatic display, the user manually unmask the namespaces or features.
         */
        MANUAL;
    }

    /**
     * Internal structure display mode.
     */
    @objid ("85859815-1926-11e2-92d2-001ec947c8cc")
    public enum InternalsViewMode {
        /**
         * Don't display internal structure.
         */
        NONE,
        /**
         * Display the internal structure as a list of labels.
         */
        LIST,
        /**
         * Display the internal structure as an embedded diagram.
         */
        DIAGRAM;
    }

    /**
     * MObject representation mode.
     */
    @objid ("8585981d-1926-11e2-92d2-001ec947c8cc")
    public enum RepresentationMode {
        /**
         * Simple mode should represent an object in its simplified mode: an interface as a circle, an actor as a
         * person, ... Other elements should usually be represented as a rectangle with the element name inside. No sub
         * element should be represented.
         */
        SIMPLE,
        /**
         * Nodes in structured mode should display the structure of the represented element: classes should display
         * their attributes, operations, internal structure, ...
         */
        STRUCTURED,
        /**
         * Stereotype image mode. Nodes should display a big image representing the first stereotype that defines a
         * diagram image. A default image is defined for elements where no stereotype defines a diagram image.
         */
        IMAGE,
        /**
         * User defined image mode. Nodes should display a big image representing the image defined in the {userDiagramImage} tagged value. A default image is defined for elements where there is no user diagram image.
         */
        USER_IMAGE;
    }

    /**
     * Line pattern.
     * 
     * @see org.eclipse.swt.SWT
     */
    @objid ("85859825-1926-11e2-92d2-001ec947c8cc")
    public enum LinePattern {
        /**
         * Line drawing style for solid lines
         * 
         * @see org.eclipse.swt.SWT#LINE_SOLID
         */
        LINE_SOLID,
        /**
         * Line drawing style for dashed lines
         */
        LINE_DASH,
        /**
         * Line drawing style for dotted lines
         */
        LINE_DOT,
        /**
         * Line drawing style for alternating dash-dot lines
         */
        LINE_DASHDOT,
        /**
         * Line drawing style for dash-dot-dot lines
         */
        LINE_DASHDOTDOT;

        /**
         * Convert the enumerate to its {@link org.eclipse.swt.SWT} matching line style constant.
         * 
         * @return the SWT matching line style constant.
         */
        @objid ("85859831-1926-11e2-92d2-001ec947c8cc")
        public int toSWTConstant() {
            return ordinal() + 1;
        }

        /**
         * Convert the given {@link org.eclipse.swt.SWT} constant to a LinePattern
         * 
         * @param i an SWT line style constant
         * @return The matching line pattern.
         */
        @objid ("85859836-1926-11e2-92d2-001ec947c8cc")
        public static LinePattern fromSWTConstant(int i) {
            return LinePattern.values()[i - 1];
        }

    }

    /**
     * Available connection routers.
     */
    @objid ("8585983c-1926-11e2-92d2-001ec947c8cc")
    public enum ConnectionRouterId {
        /**
         * Direct from source to destination.
         */
        DIRECT,
        /**
         * link with bend point. Uses {@link org.eclipse.draw2d.BendpointConnectionRouter}
         */
        BENDPOINT,
        /**
         * Orthogonal link. Uses {@link org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter
         * OrthogonalRouter} router.
         */
        ORTHOGONAL;
    }

}
