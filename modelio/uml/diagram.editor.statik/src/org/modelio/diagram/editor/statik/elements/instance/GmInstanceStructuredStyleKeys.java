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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("353eae2a-55b7-11e2-877f-002564c97630")
public final class GmInstanceStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a68fec5d-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("INSTANCE_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a68fec60-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("INSTANCE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a68fec63-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("INSTANCE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a68fec66-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("INSTANCE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a69172e9-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("INSTANCE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a69172ec-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("INSTANCE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a69172ef-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("INSTANCE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a69172f2-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("INSTANCE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a69172f5-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("INSTANCE_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a69172f8-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("INSTANCE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Internal structure visualization mode: labels, diagram or none.
     */
    @objid ("a69172fb-55c2-11e2-9337-002564c97630")
    public static final StyleKey INTERNALSVIEWMODE = createStyleKey("INSTANCE_INTERNALSVIEWMODE",
                                                                    MetaKey.InnerGroup.INNERVIEWMODE);

    /**
     * Display attributes.
     */
    @objid ("a69172fe-55c2-11e2-9337-002564c97630")
    public static final StyleKey SLOTGROUPVISIBLE = createStyleKey("INSTANCE_SLOT_GROUPVISIBLE",
                                                                   Boolean.class);

    /**
     * Slots style keys.
     * 
     * @author cmarin
     */
    @objid ("354034cc-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static final class Slot extends StaticAbstractStyleKeyProvider {
        /**
         * Slots text color.
         */
        @objid ("a6917301-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("INSTANCE_SLOT_TEXTCOLOR",
                                                                MetaKey.AttGroup.ATTTEXTCOLOR);

        /**
         * Slots font.
         */
        @objid ("a6917304-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("INSTANCE_SLOT_FONT", MetaKey.AttGroup.ATTFONT);

        /**
         * Slots stereotype display mode.
         */
        @objid ("a6917307-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("INSTANCE_SLOT_SHOWSTEREOTYPES",
                                                                      MetaKey.AttGroup.ATTSHOWSTEREOTYPES);

        /**
         * Display slots tagged values.
         */
        @objid ("a691730a-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("INSTANCE_SLOT_SHOWTAGS",
                                                               MetaKey.AttGroup.ATTSHOWTAGS);

    }

    /**
     * Style keys for all internal structure group members.
     * <p>
     * INTERNALSVIEWMODE and INTERNALS are used for internal structure zone too.
     * 
     * @author cmarin
     */
    @objid ("354034e6-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends StaticAbstractStyleKeyProvider {
        /**
         * Auto unmask inner instances.
         */
        @objid ("a691730d-55c2-11e2-9337-002564c97630")
        public static final StyleKey AUTOUNMASK = createStyleKey("INSTANCE_INTERNAL_AUTOUNMASK",
                                                                 MetaKey.InternalGroup.INTAUTOUNMASK);

        /**
         * Text color.
         */
        @objid ("a6917310-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("INSTANCE_INTERNAL_TEXTCOLOR",
                                                                MetaKey.InternalGroup.INTTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6917313-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("INSTANCE_INTERNAL_FONT",
                                                           MetaKey.InternalGroup.INTFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a692f98b-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("INSTANCE_INTERNAL_SHOWSTEREOTYPES",
                                                                      MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a692f98e-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("INSTANCE_INTERNAL_SHOWTAGS",
                                                               MetaKey.InternalGroup.INTSHOWTAGS);

    }

}
