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

package org.modelio.diagram.editor.statik.elements.narylink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for {@link GmNLinkNode} and {@link GmNLinkEndLink}.
 */
@objid ("35f2bc00-55b7-11e2-877f-002564c97630")
public class NLinkStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a70d0b8b-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("NINSTANCELINK_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    @objid ("a70d0b8d-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("NINSTANCELINK_REPRES_MODE", MetaKey.REPMODE);

    @objid ("a70d0b8f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("NINSTANCELINK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a70d0b91-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("NINSTANCELINK_FILLMODE", MetaKey.FILLMODE);

    @objid ("a70d0b93-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("NINSTANCELINK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a70d0b95-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("NINSTANCELINK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a70d0b97-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("NINSTANCELINK_LINERADIUS", MetaKey.LINERADIUS);

    @objid ("a70d0b99-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("NINSTANCELINK_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    @objid ("a70e922a-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("NINSTANCELINK_FONT", MetaKey.FONT);

    @objid ("a70e922c-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("NINSTANCELINK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a70e922e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("NINSTANCELINK_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("a70e9230-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("NINSTANCELINK_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a70e9232-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("NINSTANCELINK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a70e9234-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAVIGABILITY = createStyleKey("NINSTANCELINK_SHOWNAVIGABILITY", Boolean.class);

    @objid ("a70e9236-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("NINSTANCELINK_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("a70e9238-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("NINSTANCELINK_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Show or hide the roles.
     */
    @objid ("a70e923a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWROLES = createStyleKey("NINSTANCELINK_SHOWROLES", MetaKey.SHOWROLES);

    /**
     * Show or hide the cardinalities.
     */
    @objid ("a70e923d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWCARD = createStyleKey("NINSTANCELINK_SHOWCARD", MetaKey.SHOWCARDINALITIES);

    /**
     * Information flows
     */
    @objid ("35f442a1-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a70e9242-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("NINSTANCELINK_FLOWS_TEXTCOLOR",
                                                             MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a70e9245-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("NINSTANCELINK_FLOWS_FONT",
                                                        MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a70e9248-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("NINSTANCELINK_FLOWS_SHOWSTEREOTYPES",
                                                                   MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a70e924b-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("NINSTANCELINK_FLOWS_SHOWTAGS",
                                                            MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
