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

package org.modelio.diagram.editor.statik.elements.informationflowlink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmInformationFlowLink style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("35094678-55b7-11e2-877f-002564c97630")
public class InformationFlowLinkStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a675fbae-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("INFOFLOWLINK_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a675fbb1-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("INFOFLOWLINK_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a675fbb4-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INFOFLOWLINK_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a675fbb7-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("INFOFLOWLINK_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a675fbba-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("INFOFLOWLINK_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a675fbbd-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INFOFLOWLINK_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a675fbc0-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INFOFLOWLINK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show stereotypes
     */
    @objid ("a677824a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INFOFLOWLINK_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a677824d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INFOFLOWLINK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("b4924614-0367-4b62-a464-01c49d7a1cba")
     static final StyleKey SHOWLABEL = createStyleKey("INFOFLOWLINK_SHOWLABEL", MetaKey.SHOWLABEL);

    @objid ("19a39804-7179-4103-b8e1-398c8b8eedb5")
     static final StyleKey SHOWFLOWARROW = createStyleKey("INFOFLOWLINK_SHOWFLOWARROW", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("a6778250-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("INFOFLOWLINK_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Style keys for collaboration use bindings shown as a label group.
     * 
     * @author cmarin
     */
    @objid ("350accfc-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Items extends StaticAbstractStyleKeyProvider {
        /**
         * Show conveyed items: true / false.
         */
        @objid ("a6778255-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWGROUP = createStyleKey("INFOFLOWLINK_ITEMS_SHOWGROUP",
                                                         MetaKey.InformationItemGroup.INFSHOWGROUP);

        /**
         * Text color.
         */
        @objid ("a6778258-55c2-11e2-9337-002564c97630")
         static final StyleKey TEXTCOLOR = createStyleKey("INFOFLOWLINK_ITEMS_TEXTCOLOR",
                                                         MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a677825b-55c2-11e2-9337-002564c97630")
         static final StyleKey FONT = createStyleKey("INFOFLOWLINK_ITEMS_FONT",
                                                    MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a677825e-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWSTEREOTYPES = createStyleKey("INFOFLOWLINK_ITEMS_SHOWSTEREOTYPES",
                                                               MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a6778261-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWTAGS = createStyleKey("INFOFLOWLINK_ITEMS_SHOWTAGS",
                                                        MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
