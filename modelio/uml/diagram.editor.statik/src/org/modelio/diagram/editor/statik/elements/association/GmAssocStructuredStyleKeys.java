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

package org.modelio.diagram.editor.statik.elements.association;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for {@link GmAssociation}.
 */
@objid ("33ed760b-55b7-11e2-877f-002564c97630")
public class GmAssocStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a58002f2-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("ASSOC_CONNECTIONROUTER", MetaKey.CONNECTIONROUTER);

    @objid ("a58002f4-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("ASSOC_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a58002f6-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("ASSOC_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a58002f8-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("ASSOC_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a58002fa-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("ASSOC_LINERADIUS", MetaKey.LINERADIUS);

    @objid ("a58002fc-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("ASSOC_DRAWLINEBRIDGES", MetaKey.DRAWLINEBRIDGES);

    @objid ("a58002fe-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ASSOC_FONT", MetaKey.FONT);

    @objid ("a581898a-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ASSOC_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a581898c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ASSOC_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("a581898e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ASSOC_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a5818990-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = createStyleKey("ASSOC_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Show or hide the roles.
     */
    @objid ("a5818992-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWROLES = createStyleKey("ASSOC_SHOWROLES", Boolean.class);

    @objid ("a5818995-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = createStyleKey("ASSOC_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    /**
     * Show or hide the navigability arrows.
     */
    @objid ("a5818997-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAVIGABILITY = createStyleKey("ASSOC_SHOWNAVIGABILITY", Boolean.class);

    /**
     * Show or hide the cardinalities.
     */
    @objid ("a581899a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWCARD = createStyleKey("ASSOC_SHOWCARD", Boolean.class);

    @objid ("a581899d-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("ASSOC_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("a581899f-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWFLOWS = createStyleKey("ASSOC_SHOWFLOWS", MetaKey.SHOWINFORMATIONFLOWS);

    /**
     * Information flows
     */
    @objid ("33eefcaa-55b7-11e2-877f-002564c97630")
    public static class InfoFlows extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a58189a3-55c2-11e2-9337-002564c97630")
        public static final StyleKey FLOWTEXTCOLOR = createStyleKey("ASSOC_FLOWS_TEXTCOLOR", MetaKey.InformationItemGroup.INFTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a58189a6-55c2-11e2-9337-002564c97630")
        public static final StyleKey FLOWFONT = createStyleKey("ASSOC_FLOWS_FONT", MetaKey.InformationItemGroup.INFFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a58189a9-55c2-11e2-9337-002564c97630")
        public static final StyleKey FLOWSHOWSTEREOTYPES = createStyleKey("ASSOC_FLOWS_SHOWSTEREOTYPES", MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a58189ac-55c2-11e2-9337-002564c97630")
        public static final StyleKey FLOWSHOWTAGS = createStyleKey("ASSOC_FLOWS_SHOWTAGS", MetaKey.InformationItemGroup.INFSHOWTAGS);

    }

}
