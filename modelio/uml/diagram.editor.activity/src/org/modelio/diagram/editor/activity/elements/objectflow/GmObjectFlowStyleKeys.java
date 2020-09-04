/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.activity.elements.objectflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmObjectFlow}.
 * 
 * @author cmarin
 */
@objid ("2acab4ac-55b6-11e2-877f-002564c97630")
public class GmObjectFlowStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("d23c90d0-55c0-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("OBJECTFLOW_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("d23c90d3-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("OBJECTFLOW_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("d23e176b-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("OBJECTFLOW_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("d23e176e-55c0-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("OBJECTFLOW_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("d23e1771-55c0-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("OBJECTFLOW_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("d23e1774-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("OBJECTFLOW_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("d23e1777-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("OBJECTFLOW_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("d23e177a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("OBJECTFLOW_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("d23e177d-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("OBJECTFLOW_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the main label with name, tags and stereotypes.
     */
    @objid ("d23e1780-55c0-11e2-9337-002564c97630")
     static final StyleKey NAMEVISIBLE = createStyleKey("OBJECTFLOW_NAMEVISIBLE", MetaKey.SHOWLABEL);

    /**
     * show the guard label.
     */
    @objid ("d23e1783-55c0-11e2-9337-002564c97630")
    public static final StyleKey GUARDVISIBLE = createStyleKey("OBJECTFLOW_GUARDVISIBLE", Boolean.class);

    /**
     * show the weight label.
     */
    @objid ("d23e1786-55c0-11e2-9337-002564c97630")
    public static final StyleKey WEIGHTVISIBLE = createStyleKey("OBJECTFLOW_WEIGHTVISIBLE", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("d23e1789-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("OBJECTFLOW_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("d23e178c-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("OBJECTFLOW_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Information flows
     */
    @objid ("2acc3b4d-55b6-11e2-877f-002564c97630")
    public static class InfoFlows extends ActivityAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("d23e178e-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("OBJECTFLOW_FLOWS_TEXTCOLOR",
                MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("d23e1791-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("OBJECTFLOW_FLOWS_FONT",
                MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("d23e1794-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("OBJECTFLOW_FLOWS_SHOWSTEREOTYPES",
                MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("d23e1797-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("OBJECTFLOW_FLOWS_SHOWTAGS",
                MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
