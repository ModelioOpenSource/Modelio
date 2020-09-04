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

package org.modelio.diagram.editor.statik.elements.naryconnector;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for {@link GmNConnectorNode} and {@link GmNConnectorEndLink}.
 */
@objid ("35dd5f27-55b7-11e2-877f-002564c97630")
public class NConnectorStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a592526a-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("NCONNECTOR_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    @objid ("a592526c-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("NCONNECTOR_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a592526e-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("NCONNECTOR_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a5925270-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("NCONNECTOR_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a5925272-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("NCONNECTOR_LINERADIUS", MetaKey.LINERADIUS);

    @objid ("a5925274-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("NCONNECTOR_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    @objid ("a5925276-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("NCONNECTOR_FONT", MetaKey.FONT);

    @objid ("a5925278-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("NCONNECTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a592527a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("NCONNECTOR_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("a592527c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("NCONNECTOR_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a592527e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("NCONNECTOR_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a5925280-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAVIGABILITY = createStyleKey("NCONNECTOR_SHOWNAVIGABILITY", Boolean.class);

    @objid ("a5925282-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("NCONNECTOR_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("a5925284-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("NCONNECTOR_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    @objid ("a5925286-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("NCONNECTOR_REPRES_MODE", MetaKey.REPMODE);

    @objid ("a5925288-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("NCONNECTOR_FILLMODE", MetaKey.FILLMODE);

    /**
     * Show or hide the roles.
     */
    @objid ("a592528a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWROLES = createStyleKey("NCONNECTOR_SHOWROLES", MetaKey.SHOWROLES);

    /**
     * Show or hide the cardinalities.
     */
    @objid ("a592528d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWCARD = createStyleKey("NCONNECTOR_SHOWCARD", MetaKey.SHOWCARDINALITIES);

    /**
     * Information flows
     */
    @objid ("35dee5d9-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a5925292-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("NCONNECTOR_FLOWS_TEXTCOLOR",
                                                             MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a5925295-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("NCONNECTOR_FLOWS_FONT",
                                                        MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a5925298-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("NCONNECTOR_FLOWS_SHOWSTEREOTYPES",
                                                                   MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a593d90b-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("NCONNECTOR_FLOWS_SHOWTAGS",
                                                            MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
