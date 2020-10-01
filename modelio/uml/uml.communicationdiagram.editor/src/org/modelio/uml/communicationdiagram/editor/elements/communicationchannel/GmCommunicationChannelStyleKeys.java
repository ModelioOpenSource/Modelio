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

package org.modelio.uml.communicationdiagram.editor.elements.communicationchannel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.communicationdiagram.editor.style.CommunicationAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmCommunicationChannel}.
 */
@objid ("7a2382d3-55b6-11e2-877f-002564c97630")
public class GmCommunicationChannelStyleKeys extends CommunicationAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("9cc1a88b-55c1-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("COMMUNICATIONCHANNEL_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("9cc1cf9b-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("COMMUNICATIONCHANNEL_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("9cc1cf9e-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("COMMUNICATIONCHANNEL_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("9cc1f6ab-55c1-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("COMMUNICATIONCHANNEL_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("9cc1f6ae-55c1-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("COMMUNICATIONCHANNEL_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("9cc21dbb-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("COMMUNICATIONCHANNEL_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("9cc244c9-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("COMMUNICATIONCHANNEL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("9cc244cc-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMMUNICATIONCHANNEL_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("9cc26bdb-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("COMMUNICATIONCHANNEL_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("9cc292ea-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("COMMUNICATIONCHANNEL_LINEPATTERN",
            MetaKey.LINEPATTERN);

    @objid ("9cc292ed-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("COMMUNICATIONCHANNEL_SHOWFLOWS",
            MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Information flows
     */
    @objid ("7a250960-55b6-11e2-877f-002564c97630")
    public static class InfoFlows extends CommunicationAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("9cc2e10b-55c1-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("COMMUNICATIONCHANNEL_FLOWS_TEXTCOLOR",
                MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("9cc2e10e-55c1-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("COMMUNICATIONCHANNEL_FLOWS_FONT",
                MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("9cc3081b-55c1-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("COMMUNICATIONCHANNEL_FLOWS_SHOWSTEREOTYPES",
                MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("9cc3081e-55c1-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("COMMUNICATIONCHANNEL_FLOWS_SHOWTAGS",
                MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
