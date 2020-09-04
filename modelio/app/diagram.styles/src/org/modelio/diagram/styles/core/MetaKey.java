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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Meta key used to represent the same property on different metaclasses.
 * <p>
 * All meta keys are and must be defined here and nowhere else.
 */
@objid ("8555e904-1926-11e2-92d2-001ec947c8cc")
public class MetaKey {
    @objid ("27baf526-1927-11e2-92d2-001ec947c8cc")
    private String key;

    @objid ("27baf52b-1927-11e2-92d2-001ec947c8cc")
    private String label;

    @objid ("27baf530-1927-11e2-92d2-001ec947c8cc")
    private String tooltip;

    /**
     * Enumerates all created style keys.
     */
    @objid ("85584b46-1926-11e2-92d2-001ec947c8cc")
    private static Map<String, MetaKey> instances = new HashMap<>();

    /**
     * Connection router identifier.
     * <p>
     * It is a String used with the connectionRouterRegistry to get a ConnectionRouter.<br>
     * Typed by {@link ConnectionRouterId}.
     */
    @objid ("85584b4b-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey CONNECTIONROUTER = new MetaKey("CONNECTIONROUTER", ConnectionRouterId.class);

    /**
     * Draw or not bridge on vertical line segments that cross an horizontal one. {@link Boolean} type.
     */
    @objid ("85584b4e-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey DRAWLINEBRIDGES = new MetaKey("DRAWLINEBRIDGES", Boolean.class);

    /**
     * Fill color meta key. Typed by {@link Color}.
     */
    @objid ("85584b51-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey FILLCOLOR = new MetaKey("FILLCOLOR", Color.class);

    /**
     * Fill mode meta key. Typed by {@link FillMode}.
     */
    @objid ("85584b54-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey FILLMODE = new MetaKey("FILLMODE", FillMode.class);

    /**
     * Text font meta key. Typed by {@link Font}.
     */
    @objid ("85584b57-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey FONT = new MetaKey("FONT", Font.class);

    /**
     * Line color meta key.
     */
    @objid ("85584b5a-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey LINECOLOR = new MetaKey("LINECOLOR", Color.class);

    /**
     * Line pattern meta key. Typed by {@link LinePattern}.
     */
    @objid ("85584b5d-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey LINEPATTERN = new MetaKey("LINEPATTERN", LinePattern.class);

    /**
     * Line corner radius meta key. Typed by {@link Integer}. 0 means the corners are not rounded.
     */
    @objid ("85584b60-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey LINERADIUS = new MetaKey("LINERADIUS", Integer.class);

    /**
     * Line width meta key. Typed by {@link Integer}.
     */
    @objid ("85584b63-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey LINEWIDTH = new MetaKey("LINEWIDTH", Integer.class);

    /**
     * Representation mode property. Typed by {@link RepresentationMode}.
     */
    @objid ("85584b66-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey REPMODE = new MetaKey("REPMODE", RepresentationMode.class);

    /**
     * Show or not the name satellite label. {@link Boolean} type.
     */
    @objid ("85584b69-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWLABEL = new MetaKey("SHOWLABEL", Boolean.class);

    /**
     * Show name mode meta key. Typed by {@link ShowNameMode}.
     * <p>
     * Values are:
     * <li>{@link ShowNameMode#NONE},
     * <li>{@link ShowNameMode#SIMPLE},
     * <li>{@link ShowNameMode#QUALIFIED},
     * <li>{@link ShowNameMode#FULLQUALIFIED}
     */
    @objid ("85584b6c-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWNAME = new MetaKey("SHOWNAME", ShowNameMode.class);

    /**
     * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
     * <p>
     * Possible values are:
     * <li>{@link ShowStereotypeMode#NONE},
     * <li>{@link ShowStereotypeMode#ICON},
     * <li>{@link ShowStereotypeMode#TEXT},
     * <li>{@link ShowStereotypeMode#TEXTICON}.
     */
    @objid ("85584b6f-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWSTEREOTYPES = new MetaKey("SHOWSTEREOTYPES", ShowStereotypeMode.class);

    /**
     * Show tagged values meta key. {@link Boolean} type.
     */
    @objid ("85584b72-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWTAGS = new MetaKey("SHOWTAGS", Boolean.class);

    /**
     * Text color meta key.
     */
    @objid ("855aada1-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey TEXTCOLOR = new MetaKey("TEXTCOLOR", Color.class);

    /**
     * Features and inner name spaces auto unmask filter. Possible values are:
     * <li>
     * {@link UmaskByVisibilityStragegy#ALL} : show all,
     * <li>{@link UmaskByVisibilityStragegy#ALL_PUBLIC} : show only
     * public,
     * <li>{@link UmaskByVisibilityStragegy#ALL_NON_PRIVATE} : show all but privates,
     * <li>
     * {@link UmaskByVisibilityStragegy#MANUAL} : let the user unmask individually,
     */
    @objid ("855aada4-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey VISIBILITYFILTER = new MetaKey("VISIBILITYFILTER",
	        UmaskByVisibilityStragegy.class);

    /**
     * Show or not the information flows. {@link Boolean} type.
     */
    @objid ("855d0ff9-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWINFORMATIONFLOWS = new MetaKey("SHOWINFORMATIONFLOWS", Boolean.class);

    /**
     * Show visibility meta key. {@link Boolean} type.
     */
    @objid ("855d0ffc-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWVISIBILITY = new MetaKey("SHOWVISIBILITY", Boolean.class);

    /**
     * Show or not the name satellite label. {@link Boolean} type.
     */
    @objid ("855d0fff-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWROLES = new MetaKey("SHOWROLES", Boolean.class);

    /**
     * Show or not the name satellite label. {@link Boolean} type.
     */
    @objid ("855d1002-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey SHOWCARDINALITIES = new MetaKey("SHOWCARDINALITIES", Boolean.class);

    /**
     * Display or not "Create smart link" handle. {@link Boolean} type.
     */
    @objid ("b3394e2a-cd65-4c22-8087-3dc8561af1fa")
    public static final MetaKey SHOWSMARTLINKHANDLE = new MetaKey("SHOWSMARTLINKHANDLE", Boolean.class);

    /**
     * Show automatically or not the pins on the activity nodes. {@link Boolean} type.
     */
    @objid ("855d1005-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey AUTOSHOWPINS = new MetaKey("AUTOSHOWPINS", Boolean.class);

    /**
     * Show automatically or not the missing ports on the classifier nodes. {@link Boolean} type.
     */
    @objid ("855d1008-1926-11e2-92d2-001ec947c8cc")
    public static final MetaKey AUTOSHOWPORTS = new MetaKey("AUTOSHOWPORTS", Boolean.class);

    /**
     * Opacity level.
     * <p>
     * Values may range from 0 to 255. A value of 0 is completely transparent.
     * 
     * @see org.eclipse.draw2d.Graphics#setAlpha(int)
     */
    @objid ("52dd2f54-4d93-45a8-8217-5187fb53bef4")
    public static final MetaKey FILLALPHA = new MetaKey("FILLALPHA", Integer.class);

    /**
     * Hyper link to a model element for drawings. {@link MRef} type.
     */
    @objid ("27077e12-7946-4df7-83e4-b61d177512e0")
    public static final MetaKey HYPERREFLINK = new MetaKey("HYPERREFLINK", MRef.class);

    /**
     * Wrap or not the name label. {@link Boolean} type.
     */
    @objid ("cb84195a-774c-4828-8664-af914a71e942")
    public static final MetaKey WRAPLABEL = new MetaKey("WRAPLABEL", Boolean.class);

    /**
     * Meta key type.
     */
    @objid ("528e0f8e-34c1-4384-b54b-a18645fd0318")
    private Class<?> type;

    /**
     * Private constructor. Don't use outside this class.
     * @param key The meta key name.
     * @param type The meta key type.
     */
    @objid ("855d100f-1926-11e2-92d2-001ec947c8cc")
    MetaKey(String key, Class<?> type) {
        this.key = key;
        this.type = type;
        this.label = DiagramStyles.I18N.getMessage("$MetaKey." + key + ".label");
        this.tooltip = DiagramStyles.I18N.getMessage("$MetaKey." + key + ".tooltip");
        MetaKey.instances.put(this.key, this);
    }

    /**
     * Get the string uniquely identifying the meta key.
     * @return the meta key name.
     */
    @objid ("855d1016-1926-11e2-92d2-001ec947c8cc")
    public String getKey() {
        return this.key;
    }

    /**
     * @return the default label for this Metakey.
     */
    @objid ("855d101b-1926-11e2-92d2-001ec947c8cc")
    public String getLabel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.label;
    }

    /**
     * @return the default tooltip for this Metakey.
     */
    @objid ("855f7254-1926-11e2-92d2-001ec947c8cc")
    public String getTooltip() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tooltip;
    }

    /**
     * Get the meta key type.
     * @return the meta key type.
     */
    @objid ("855f7259-1926-11e2-92d2-001ec947c8cc")
    public Class<?> getType() {
        return this.type;
    }

    @objid ("855f7260-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String toString() {
        return getKey() + ": " + getType().getSimpleName();
    }

    /**
     * Return the MetaKey instance for a given string key.
     * @param id the id string value
     * @return the MetaKey for the given string key or null if none found.
     */
    @objid ("855f7265-1926-11e2-92d2-001ec947c8cc")
    public static MetaKey getInstance(String id) {
        return MetaKey.instances.get(id);
    }

    /**
     * Get all instances of MetaKey
     * @return all created style keys.
     */
    @objid ("855f726b-1926-11e2-92d2-001ec947c8cc")
    public static Collection<MetaKey> getInstances() {
        return MetaKey.instances.values();
    }

    /**
     * Attributes group meta keys.
     * 
     * @author cmarin
     */
    @objid ("8561d4ae-1926-11e2-92d2-001ec947c8cc")
    public static final class AttGroup {
        /**
         * Text font meta key. Typed by {@link Font}.
         */
        @objid ("8561d4b1-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTFONT = new MetaKey("ATTFONT", Font.class);

        /**
         * Text color meta key.
         */
        @objid ("8561d4b4-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTTEXTCOLOR = new MetaKey("ATTTEXTCOLOR", Color.class);

        /**
         * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
         * <p>
         * Possible values are:
         * <li>{@link ShowStereotypeMode#NONE},
         * <li>{@link ShowStereotypeMode#ICON},
         * <li>{@link ShowStereotypeMode#TEXT},
         * <li>{@link ShowStereotypeMode#TEXTICON}.
         */
        @objid ("8561d4b7-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTSHOWSTEREOTYPES = new MetaKey("ATTSHOWSTEREOTYPES",
		        ShowStereotypeMode.class);

        /**
         * Show tagged values meta key. {@link Boolean} type.
         */
        @objid ("8561d4ba-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTSHOWTAGS = new MetaKey("ATTSHOWTAGS", Boolean.class);

        /**
         * Show the attribute group.
         */
        @objid ("8561d4bd-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTSHOWGROUP = new MetaKey("ATTSHOWGROUP", Boolean.class);

        /**
         * Show attributes visibility
         */
        @objid ("85643708-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey ATTSHOWVISIBILITY = new MetaKey("ATTSHOWVISIBILITY", Boolean.class);

        /**
         * Wrap or not the name label. {@link Boolean} type.
         */
        @objid ("12b90d9d-ecb3-43fd-8fe0-623ea3e740fd")
        public static final MetaKey ATTWRAPLABEL = new MetaKey("ATTWRAPLABEL", Boolean.class);

        @objid ("8564370a-1926-11e2-92d2-001ec947c8cc")
        private AttGroup() {
            // just to forbid instantiation
        }

    }

    /**
     * Operation group meta keys.
     * 
     * @author cmarin
     */
    @objid ("8564370c-1926-11e2-92d2-001ec947c8cc")
    public static final class OperationGroup {
        /**
         * Text font meta key. Typed by {@link Font}.
         */
        @objid ("8564370f-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPFONT = new MetaKey("OPFONT", Font.class);

        /**
         * Text color meta key.
         */
        @objid ("85643712-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPTEXTCOLOR = new MetaKey("OPTEXTCOLOR", Color.class);

        /**
         * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
         * <p>
         * Possible values are:
         * <li>{@link ShowStereotypeMode#NONE},
         * <li>{@link ShowStereotypeMode#ICON},
         * <li>{@link ShowStereotypeMode#TEXT},
         * <li>{@link ShowStereotypeMode#TEXTICON}.
         */
        @objid ("85643715-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPSHOWSTEREOTYPES = new MetaKey("OPSHOWSTEREOTYPES",
		        ShowStereotypeMode.class);

        /**
         * Show tagged values meta key. {@link Boolean} type.
         */
        @objid ("85643718-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPSHOWTAGS = new MetaKey("OPSHOWTAGS", Boolean.class);

        /**
         * Show the operations group.
         */
        @objid ("8564371b-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPSHOWGROUP = new MetaKey("OPSHOWGROUP", Boolean.class);

        /**
         * Display the operation signature.
         */
        @objid ("8564371e-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPSHOWSIGNATURE = new MetaKey("OPSHOWSIGNATURE", Boolean.class);

        /**
         * Display the operation visibility (public/private/...)
         */
        @objid ("85643721-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey OPSHOWVISIBILITY = new MetaKey("OPSHOWVISIBILITY", Boolean.class);

        /**
         * Wrap or not the name label. {@link Boolean} type.
         */
        @objid ("c56242f1-e412-43f7-8e38-ef5dd6267e12")
        public static final MetaKey OPWRAPLABEL = new MetaKey("OPWRAPLABEL", Boolean.class);

        @objid ("85643723-1926-11e2-92d2-001ec947c8cc")
        private OperationGroup() {
            // just to forbid instantiation
        }

    }

    /**
     * Internal structure meta keys.
     * 
     * @author cmarin
     */
    @objid ("85643725-1926-11e2-92d2-001ec947c8cc")
    public static final class InternalGroup {
        /**
         * Text font meta key. Typed by {@link Font}.
         */
        @objid ("85643728-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTFONT = new MetaKey("INTFONT", Font.class);

        /**
         * Text color meta key.
         */
        @objid ("8564372b-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTTEXTCOLOR = new MetaKey("INTTEXTCOLOR", Color.class);

        /**
         * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
         * <p>
         * Possible values are:
         * <li>{@link ShowStereotypeMode#NONE},
         * <li>{@link ShowStereotypeMode#ICON},
         * <li>{@link ShowStereotypeMode#TEXT},
         * <li>{@link ShowStereotypeMode#TEXTICON}.
         */
        @objid ("8564372e-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTSHOWSTEREOTYPES = new MetaKey("INTSHOWSTEREOTYPES",
		        ShowStereotypeMode.class);

        /**
         * Show tagged values meta key. {@link Boolean} type.
         */
        @objid ("85643731-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTSHOWTAGS = new MetaKey("INTSHOWTAGS", Boolean.class);

        /**
         * Internal structure viewing mode meta key. {@link InternalsViewMode} type. Possible values are:
         * <li>
         * {@link InternalsViewMode#NONE},
         * <li>{@link InternalsViewMode#LIST},
         * <li>{@link InternalsViewMode#DIAGRAM},
         */
        @objid ("85643734-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTVIEWMODE = new MetaKey("INTVIEWMODE", InternalsViewMode.class);

        /**
         * Auto unmask instances meta key. {@link Boolean} type.
         */
        @objid ("85643737-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INTAUTOUNMASK = new MetaKey("INTAUTOUNMASK", Boolean.class);

        /**
         * Wrap or not the name label. {@link Boolean} type.
         */
        @objid ("9827c771-b971-45ed-b7c1-06a38ac7b532")
        public static final MetaKey INTWRAPLABEL = new MetaKey("INTWRAPLABEL", Boolean.class);

        @objid ("8564373a-1926-11e2-92d2-001ec947c8cc")
        private InternalGroup() {
            // just to forbid instantiation
        }

    }

    /**
     * Inner classes group meta keys.
     * 
     * @author cmarin
     */
    @objid ("8564373c-1926-11e2-92d2-001ec947c8cc")
    public static final class InnerGroup {
        /**
         * Text font meta key. Typed by {@link Font}.
         */
        @objid ("85669963-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERFONT = new MetaKey("INNER_FONT", Font.class);

        /**
         * Text color meta key.
         */
        @objid ("85669966-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERTEXTCOLOR = new MetaKey("INNER_TEXTCOLOR", Color.class);

        /**
         * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
         * <p>
         * Possible values are:
         * <li>{@link ShowStereotypeMode#NONE},
         * <li>{@link ShowStereotypeMode#ICON},
         * <li>{@link ShowStereotypeMode#TEXT},
         * <li>{@link ShowStereotypeMode#TEXTICON}.
         */
        @objid ("85669969-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERSHOWSTEREOTYPES = new MetaKey("INNER_SHOWSTEREOTYPES",
		        ShowStereotypeMode.class);

        /**
         * Show tagged values meta key. {@link Boolean} type.
         */
        @objid ("8566996c-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERSHOWTAGS = new MetaKey("INNER_SHOWTAGS", Boolean.class);

        /**
         * Internal structure viewing mode meta key. {@link InternalsViewMode} type. Possible values are:
         * <li>
         * {@link InternalsViewMode#NONE},
         * <li>{@link InternalsViewMode#LIST},
         * <li>{@link InternalsViewMode#DIAGRAM},
         */
        @objid ("8566996f-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERVIEWMODE = new MetaKey("INNER_VIEWMODE", InternalsViewMode.class);

        /**
         * Inner classes unmask strategy. Possible values are:
         * <li>{@link UmaskByVisibilityStragegy#ALL},
         * <li>
         * {@link UmaskByVisibilityStragegy#ALL_PUBLIC},
         * <li>{@link UmaskByVisibilityStragegy#ALL_NON_PRIVATE},
         * <li>
         * {@link UmaskByVisibilityStragegy#MANUAL},
         */
        @objid ("85669972-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERUNMASKFILTER = new MetaKey("INNERUNMASKFILTER",
		        UmaskByVisibilityStragegy.class);

        /**
         * Show visibility meta key
         */
        @objid ("85669975-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERSHOWVISIBILITY = new MetaKey("SHOWVISIBILITY", Boolean.class);

        /**
         * Show name meta key
         */
        @objid ("85669978-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INNERSHOWNAME = new MetaKey("SHOWNAME", ShowNameMode.class);

        /**
         * Wrap or not the name label. {@link Boolean} type.
         */
        @objid ("1e2c9f95-2a4f-4868-9fd5-2ca6ce3ce48c")
        public static final MetaKey INNERWRAPLABEL = new MetaKey("INNERWRAPLABEL", Boolean.class);

        @objid ("8566997b-1926-11e2-92d2-001ec947c8cc")
        private InnerGroup() {
            // just to forbid instantiation
        }

    }

    /**
     * Information items group meta keys.
     * 
     * @author cmarin
     */
    @objid ("8566997d-1926-11e2-92d2-001ec947c8cc")
    public static final class InformationItemGroup {
        /**
         * Text font meta key. Typed by {@link Font}.
         */
        @objid ("85669980-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INFFONT = new MetaKey("INFFONT", Font.class);

        /**
         * Text color meta key.
         */
        @objid ("85669983-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INFTEXTCOLOR = new MetaKey("INFTEXTCOLOR", Color.class);

        /**
         * Stereotype display mode meta key, typed by {@link ShowStereotypeMode}.
         * <p>
         * Possible values are:
         * <li>{@link ShowStereotypeMode#NONE},
         * <li>{@link ShowStereotypeMode#ICON},
         * <li>{@link ShowStereotypeMode#TEXT},
         * <li>{@link ShowStereotypeMode#TEXTICON}.
         */
        @objid ("85669986-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INFSHOWSTEREOTYPES = new MetaKey("INFSHOWSTEREOTYPES",
		        ShowStereotypeMode.class);

        /**
         * Show tagged values meta key. {@link Boolean} type.
         */
        @objid ("8568fbbd-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INFSHOWTAGS = new MetaKey("INFSHOWTAGS", Boolean.class);

        /**
         * Show the inormation items group.
         */
        @objid ("8568fbc0-1926-11e2-92d2-001ec947c8cc")
        public static final MetaKey INFSHOWGROUP = new MetaKey("INFSHOWGROUP", Boolean.class);

        /**
         * Wrap or not the name label. {@link Boolean} type.
         */
        @objid ("0d249004-ff5f-404d-96e1-4ce0c77526f8")
        public static final MetaKey INFWRAPLABEL = new MetaKey("INFWRAPLABEL", Boolean.class);

        @objid ("8568fbc3-1926-11e2-92d2-001ec947c8cc")
        private InformationItemGroup() {
            // just to forbid instantiation
        }

    }

}
