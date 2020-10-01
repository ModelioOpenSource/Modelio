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

package org.modelio.uml.statikdiagram.editor.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style keys for {@link GmNAssocNode} and {@link GmNAssocEndLink}.
 */
@objid ("35cfa379-55b7-11e2-877f-002564c97630")
public class NAssocStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a6f9356b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("NASSOC_REPRES_MODE", MetaKey.REPMODE);

    @objid ("a6f9356d-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("NASSOC_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    @objid ("a6f9356f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("NASSOC_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a6f93571-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("NASSOC_FILLMODE", MetaKey.FILLMODE);

    @objid ("a6f93573-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("NASSOC_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a6f93575-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("NASSOC_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a6f93577-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("NASSOC_LINERADIUS", MetaKey.LINERADIUS);

    @objid ("a6f93579-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("NASSOC_DRAWLINEBRIDGES", MetaKey.DRAWLINEBRIDGES);

    @objid ("a6f9357b-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("NASSOC_FONT", MetaKey.FONT);

    @objid ("a6f9357d-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("NASSOC_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a6f9357f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("NASSOC_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("a6f93581-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("NASSOC_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a6f93583-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("NASSOC_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Show or hide the roles.
     */
    @objid ("a6f93585-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWROLES = createStyleKey("NASSOC_SHOWROLES", MetaKey.SHOWROLES);

    /**
     * Show or hide the navigability arrows.
     */
    @objid ("a6f93588-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAVIGABILITY = createStyleKey("NASSOC_SHOWNAVIGABILITY", Boolean.class);

    /**
     * Show or hide the cardinalities.
     */
    @objid ("a6f9358b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWCARD = createStyleKey("NASSOC_SHOWCARD", MetaKey.SHOWCARDINALITIES);

    @objid ("a6fabc09-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEPATTERN = createStyleKey("NASSOC_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("a6fabc0b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWFLOWS = createStyleKey("NASSOC_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Information flows
     */
    @objid ("35d12a29-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a6fabc0f-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWTEXTCOLOR = createStyleKey("NASSOC_FLOWS_TEXTCOLOR",
                                                             MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6fabc12-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWFONT = createStyleKey("NASSOC_FLOWS_FONT",
                                                        MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a6fabc15-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("NASSOC_FLOWS_SHOWSTEREOTYPES",
                                                                   MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a6fabc18-55c2-11e2-9337-002564c97630")
         static final StyleKey FLOWSHOWTAGS = createStyleKey("NASSOC_FLOWS_SHOWTAGS",
                                                            MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
