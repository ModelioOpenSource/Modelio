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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * A named style is a special style that is global to the project (or more).
 * <p>
 * This style can be referenced and used in many diagrams.<br/>
 * The named style name is used as identifier to find it among shared
 * styles.
 * 
 * @author phv
 */
@objid ("8568fbc5-1926-11e2-92d2-001ec947c8cc")
public class NamedStyle extends Style {
    @objid ("671c42ed-05a1-4d7c-be56-4d5d353b0526")
    public static final String APPLICABILITY_ADMINKEY = "application";

    @objid ("27710c41-1927-11e2-92d2-001ec947c8cc")
    public static final String BASESTYLE_ADMINKEY = "basestyle";

    @objid ("856b5e1b-1926-11e2-92d2-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("ebbb69aa-b991-495e-b1c8-2eceb7ceba9b")
    public static final String PROVIDER_ADMINKEY = "provider";

    @objid ("27710c3c-1927-11e2-92d2-001ec947c8cc")
    public static final String STYLENAME_ADMINKEY = "stylename";

    @objid ("07543c8f-3462-4f94-90a7-48569ec27a8a")
    public static final String THEME_ADMINKEY = "theme";

    /**
     * The style applicability condition. This optional value helps the ui to propose only relevant styles to the end user depending on an element's type.
     * Value must be a comma separated list of full qualified metaclass names. (eg: Standard.Class, Standard.Component)
     * 
     * A missing key or a null or empty value matches anything.
     */
    @mdl.prop
    @objid ("63e5e109-938e-422a-8cbb-07d9990bf64f")
    private Set<String> applicability = new HashSet<>(2);

    @mdl.propgetter
    public Set<String> getApplicability() {
        // Automatically generated method. Please do not modify this code.
        return this.applicability;
    }

    /**
     * The style name is used as a unique mandatory identifier for the style. It is also used as a label in the gui when listing styles.
     */
    @mdl.prop
    @objid ("27710c46-1927-11e2-92d2-001ec947c8cc")
    private String name;

    @mdl.propgetter
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    @mdl.propsetter
    public void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    /**
     * The style provider is an optional value indicating who provided the style definition.
     */
    @mdl.prop
    @objid ("bebaf5d6-c89d-4783-9fb8-c544954445a8")
    private String provider;

    @mdl.propgetter
    public String getProvider() {
        // Automatically generated method. Please do not modify this code.
        return this.provider;
    }

    @mdl.propsetter
    public void setProvider(String value) {
        // Automatically generated method. Please do not modify this code.
        this.provider = value;
    }

    /**
     * Whether or not this named style should be considered as a theme.
     */
    @objid ("511cd9f1-b96a-4870-b7c6-e3e520430f86")
    private boolean isTheme;

    /**
     * Pattern to be matched when naming a style.
     */
    @objid ("6e741beb-5be2-4981-85f0-655c63a72d11")
    public static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_ &]+");

    /**
     * Creates a named style.
     * @param name The named style name.
     * @param cascadedStyle The parent style.
     */
    @objid ("856b5e1d-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle(String name, IStyle cascadedStyle) {
        super(cascadedStyle);
        this.name = name;
        this.properties = new HashMap<>();
    }

    @objid ("856b5e22-1926-11e2-92d2-001ec947c8cc")
    public NamedStyle(String name, Map<StyleKey, Object> styleProperties, IStyle cascadedStyle) {
        super(cascadedStyle);
        this.properties = styleProperties;
        this.name = name;
    }

    @objid ("856b5e39-1926-11e2-92d2-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return NamedStyle.MAJOR_VERSION;
    }

    /**
     * A named style is not stored with the diagram.
     */
    @objid ("856b5e2a-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        // TODO: return false in the case we want to save the style
        // somewhere else than in a diagram.
        return true;
    }

    /**
     * @return <code>true</code> if the given name is valid for a style.
     */
    @objid ("e127ca4d-c72f-4a28-813a-1657108a2f36")
    public static boolean isValidName(String name) {
        return NamedStyle.NAME_PATTERN.matcher(name).matches();
    }

    @objid ("856b5e31-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        super.read(in);
    }

    @objid ("1190fba8-bbf6-400f-9f58-270f452fffd9")
    public void setApplicability(Set<String> applicability) {
        this.applicability = applicability;
    }

    @objid ("6830a8ef-f074-4360-b8f7-67605f65aa8a")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("NamedStyle [");
        
        String n = getName();
        if (n != null && !n.isEmpty()) {
            s.append("name='");
            s.append(n);
            s.append("', ");
        }
        
        s.append("isTheme='");
        s.append(this.isTheme);
        s.append("', ");
        
        s.append("provider=");
        s.append(this.provider);
        s.append("]");
        return s.toString();
    }

    @objid ("856b5e35-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        if (isExternal(out)) {
            // The call to super is not correct here as a named style
            // is indeed an external reference and should not be explicitely serialized.
            //
            // Instead write the name of the style as external reference
            out.writeExtRef(this, "", this.name);
        } else {
            super.write(out);
        }
    }

    @objid ("be877fe5-676c-48dc-89f8-27471be87ae8")
    @Override
    public boolean isTheme() {
        return this.isTheme;
    }

    @objid ("b9e6dbe1-3266-4132-a24a-fb1d17e4dcc2")
    public void setTheme(boolean isTheme) {
        this.isTheme = isTheme;
    }

}
