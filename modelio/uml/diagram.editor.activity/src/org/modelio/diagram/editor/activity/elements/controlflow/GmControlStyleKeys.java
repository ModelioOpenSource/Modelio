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

package org.modelio.diagram.editor.activity.elements.controlflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmControlFlow}.
 * 
 * @author cmarin
 */
@objid ("2a21552c-55b6-11e2-877f-002564c97630")
public class GmControlStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("d1b908e9-55c0-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("CONTROLFLOW_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("d1b908ec-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CONTROLFLOW_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("d1b908ef-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CONTROLFLOW_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("d1b908f2-55c0-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("CONTROLFLOW_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("d1b908f5-55c0-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("CONTROLFLOW_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("d1b908f8-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CONTROLFLOW_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("d1b908fb-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CONTROLFLOW_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("d1b908fe-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONTROLFLOW_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("d1b90901-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CONTROLFLOW_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the main label that displays the name, the tags and the stereotypes.
     */
    @objid ("d1b90904-55c0-11e2-9337-002564c97630")
     static final StyleKey NAMEVISIBLE = createStyleKey("CONTROLFLOW_NAMEVISIBLE", MetaKey.SHOWLABEL);

    /**
     * Show the guard label.
     */
    @objid ("d1b90907-55c0-11e2-9337-002564c97630")
    public static final StyleKey GUARDVISIBLE = createStyleKey("CONTROLFLOW_GUARDVISIBLE", Boolean.class);

    /**
     * Show the weight label.
     */
    @objid ("d1b9090a-55c0-11e2-9337-002564c97630")
    public static final StyleKey WEIGHTVISIBLE = createStyleKey("CONTROLFLOW_WEIGHTVISIBLE", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("d1b9090d-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("CONTROLFLOW_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("d1b90910-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("CONTROLFLOW_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Information flows
     */
    @objid ("2a22dbd4-55b6-11e2-877f-002564c97630")
    public static class InfoFlows extends ActivityAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("d1ba8f8a-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("CONTROLFLOW_FLOWS_TEXTCOLOR",
                MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("d1ba8f8d-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("CONTROLFLOW_FLOWS_FONT",
                MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("d1ba8f90-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("CONTROLFLOW_FLOWS_SHOWSTEREOTYPES",
                MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("d1ba8f93-55c0-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("CONTROLFLOW_FLOWS_SHOWTAGS",
                MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
