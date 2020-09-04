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

package org.modelio.diagram.editor.activity.elements.exceptionhandler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmExceptionHandler}.
 * 
 * @author cmarin
 */
@objid ("2a4f1bf0-55b6-11e2-877f-002564c97630")
public class GmExceptionHandlerStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("d1e57019-55c0-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("EXCEPTIONHANDLER_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("d1e5701c-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("EXCEPTIONHANDLER_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("d1e5701f-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("EXCEPTIONHANDLER_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("d1e57022-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("EXCEPTIONHANDLER_LINERADIUS",
            MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("d1e57025-55c0-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("EXCEPTIONHANDLER_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("d1e57028-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("EXCEPTIONHANDLER_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("d1e5702b-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("EXCEPTIONHANDLER_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("d1e5702e-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXCEPTIONHANDLER_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("d1e57031-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("EXCEPTIONHANDLER_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show label
     */
    @objid ("d1e57034-55c0-11e2-9337-002564c97630")
    public static final StyleKey NAMEVISIBLE = createStyleKey("EXCEPTIONHANDLER_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Line pattern
     */
    @objid ("d1e57037-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("EXCEPTIONHANDLER_LINEPATTERN",
            MetaKey.LINEPATTERN);

}
