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

package org.modelio.uml.statikdiagram.editor.elements.instancelink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * {@link GmInstanceLink} style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("3561c6a0-55b7-11e2-877f-002564c97630")
public class InstanceLinkStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a6b17e0f-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("INSTANCELINK_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    /**
     * Diamond fill color
     */
    @objid ("a6b17e12-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INSTANCELINK_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Line color
     */
    @objid ("a6b17e15-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INSTANCELINK_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a6b17e18-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INSTANCELINK_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a6b304a9-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("INSTANCELINK_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a6b304ac-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("INSTANCELINK_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Labels font
     */
    @objid ("a6b304af-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INSTANCELINK_FONT", MetaKey.FONT);

    /**
     * Labels color.
     */
    @objid ("a6b304b2-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INSTANCELINK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode. Is it really useful ?
     */
    @objid ("a6b304b5-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("INSTANCELINK_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode, typed by {@link ShowStereotypeMode}.
     */
    @objid ("a6b304b8-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INSTANCELINK_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tags
     */
    @objid ("a6b304bb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INSTANCELINK_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display navigability.
     */
    @objid ("a6b304be-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAVIGABILITY = createStyleKey("INSTANCELINK_SHOWNAVIGABILITY", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("a6b304c1-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("INSTANCELINK_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Display information flows.
     */
    @objid ("a6b304c4-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("INSTANCELINK_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    @objid ("a6b304c7-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWROLES = createStyleKey("INSTANCELINK_SHOWROLES", MetaKey.SHOWROLES);

    @objid ("a6b304c9-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWCARD = createStyleKey("INSTANCELINK_SHOWCARD", MetaKey.SHOWCARDINALITIES);

    /**
     * Information flows
     */
    @objid ("35634d48-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a6b304cd-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("INSTANCELINK_FLOWS_TEXTCOLOR",
                                                             MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6b304d0-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("INSTANCELINK_FLOWS_FONT",
                                                        MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a6b304d3-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("INSTANCELINK_FLOWS_SHOWSTEREOTYPES",
                                                                   MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a6b304d6-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("INSTANCELINK_FLOWS_SHOWTAGS",
                                                            MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
