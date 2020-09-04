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

package org.modelio.diagram.editor.statik.elements.connector;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * {@link GmConnectorLink} style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("34b0c5fa-55b7-11e2-877f-002564c97630")
public class ConnectorLinkStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a652e34b-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("CONNECTOR_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    /**
     * Diamond fill color
     */
    @objid ("a652e34e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CONNECTOR_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Line color
     */
    @objid ("a652e351-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CONNECTOR_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a652e354-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CONNECTOR_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a652e357-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("CONNECTOR_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a652e35a-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("CONNECTOR_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Labels font
     */
    @objid ("a652e35d-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CONNECTOR_FONT", MetaKey.FONT);

    /**
     * Labels color.
     */
    @objid ("a652e360-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CONNECTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode. Is it really useful ?
     */
    @objid ("a652e363-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("CONNECTOR_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode, typed by {@link ShowStereotypeMode}.
     */
    @objid ("a652e366-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONNECTOR_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tags
     */
    @objid ("a652e369-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CONNECTOR_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display navigability.
     */
    @objid ("a652e36c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAVIGABILITY = createStyleKey("CONNECTOR_SHOWNAVIGABILITY", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("a652e36f-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("CONNECTOR_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Display information flows.
     */
    @objid ("a652e372-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("CONNECTOR_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    @objid ("a652e375-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWROLES = createStyleKey("CONNECTOR_SHOWROLES", MetaKey.SHOWROLES);

    @objid ("a652e377-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWCARD = createStyleKey("CONNECTOR_SHOWCARD", MetaKey.SHOWCARDINALITIES);

    /**
     * Information flows
     */
    @objid ("34b24cc6-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a652e37b-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("CONNECTOR_FLOWS_TEXTCOLOR",
                                                             MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a65469eb-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("CONNECTOR_FLOWS_FONT",
                                                        MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a65469ee-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("CONNECTOR_FLOWS_SHOWSTEREOTYPES",
                                                                   MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a65469f1-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("CONNECTOR_FLOWS_SHOWTAGS",
                                                            MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
