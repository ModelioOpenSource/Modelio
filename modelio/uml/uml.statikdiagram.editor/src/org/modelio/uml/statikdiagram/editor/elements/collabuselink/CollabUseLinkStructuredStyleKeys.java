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

package org.modelio.uml.statikdiagram.editor.elements.collabuselink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * {@link GmCollabUseLink} style keys.
 * 
 * @author cmarin
 */
@objid ("348aa066-55b7-11e2-877f-002564c97630")
public class CollabUseLinkStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a635974d-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("COLLABORATIONUSELINK_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a6359750-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("COLLABORATIONUSELINK_LINECOLOR",
                                                            MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a6371deb-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("COLLABORATIONUSELINK_LINEWIDTH",
                                                            MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a6371dee-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("COLLABORATIONUSELINK_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a6371df1-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("COLLABORATIONUSELINK_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a6371df4-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("COLLABORATIONUSELINK_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a6371df7-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATIONUSELINK_TEXTCOLOR",
                                                            MetaKey.TEXTCOLOR);

    /**
     * Show stereotypes
     */
    @objid ("a6371dfa-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATIONUSELINK_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a6371dfd-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("COLLABORATIONUSELINK_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a6371e00-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("COLLABORATIONUSELINK_LINEPATTERN",
                                                              MetaKey.LINEPATTERN);

    /**
     * Style keys for collaboration use bindings shown as a label group.
     * 
     * @author cmarin
     */
    @objid ("348c271c-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class BindingsGroup extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a6371e05-55c2-11e2-9337-002564c97630")
         static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATIONUSELINK_INTERNAL_TEXTCOLOR",
                                                         MetaKey.AttGroup.ATTTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6371e08-55c2-11e2-9337-002564c97630")
         static final StyleKey FONT = createStyleKey("COLLABORATIONUSELINK_INTERNAL_FONT",
                                                    MetaKey.AttGroup.ATTFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a6371e0b-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATIONUSELINK_INTERNAL_SHOWSTEREOTYPES",
                                                               MetaKey.AttGroup.ATTSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a6371e0e-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWTAGS = createStyleKey("COLLABORATIONUSELINK_INTERNAL_SHOWTAGS",
                                                        MetaKey.AttGroup.ATTSHOWTAGS);

        /**
         * Show collaboration use bindings as labels : true / false.
         */
        @objid ("a6371e11-55c2-11e2-9337-002564c97630")
         static final StyleKey INTERNALSVIEWMODE = createStyleKey("COLLABORATIONUSELINK_INTERNALSVIEWMODE",
                                                                 MetaKey.AttGroup.ATTSHOWGROUP);

    }

}
