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

package org.modelio.diagram.editor.statik.elements.packageimport;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("36099f55-55b7-11e2-877f-002564c97630")
public class PackageImportStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a726fc2b-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("PACKAGEIMPORT_CONNECTIONROUTER",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a726fc2e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("PACKAGEIMPORT_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a726fc31-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("PACKAGEIMPORT_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a726fc34-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("PACKAGEIMPORT_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a726fc37-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("PACKAGEIMPORT_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a726fc3a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("PACKAGEIMPORT_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a726fc3d-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("PACKAGEIMPORT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show name (alias)
     */
    @objid ("a726fc40-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("PACKAGEIMPORT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Show stereotypes
     */
    @objid ("a726fc43-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("PACKAGEIMPORT_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a726fc46-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("PACKAGEIMPORT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a726fc49-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("PACKAGEIMPORT_LINEPATTERN",
                                                              MetaKey.LINEPATTERN);

    /**
     * Show information flows.
     */
    @objid ("a726fc4c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWFLOWS = createStyleKey("PACKAGEIMPORT_SHOWFLOWS", Boolean.class);

}
