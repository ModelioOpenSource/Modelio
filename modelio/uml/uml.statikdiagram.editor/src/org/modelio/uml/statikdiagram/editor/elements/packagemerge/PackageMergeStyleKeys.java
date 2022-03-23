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
package org.modelio.uml.statikdiagram.editor.elements.packagemerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("3612c6fe-55b7-11e2-877f-002564c97630")
public class PackageMergeStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a72b900b-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("PACKAGEMERGE_CONNECTIONROUTER",
                                                                       MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a72b900e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("PACKAGEMERGE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a72b9011-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("PACKAGEMERGE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a72b9014-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("PACKAGEMERGE_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a72b9017-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("PACKAGEMERGE_DRAWLINEBRIDGES",
                                                                      MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a72b901a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("PACKAGEMERGE_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a72b901d-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("PACKAGEMERGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show name (alias)
     */
    @objid ("a72b9020-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("PACKAGEMERGE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Show stereotypes
     */
    @objid ("a72b9023-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("PACKAGEMERGE_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a72b9026-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("PACKAGEMERGE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a72b9029-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("PACKAGEMERGE_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Show information flows.
     */
    @objid ("a72b902c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWFLOWS = createStyleKey("PACKAGEMERGE_SHOWFLOWS", Boolean.class);

}
