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

package org.modelio.diagram.editor.statik.elements.elementimport;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("34cdc3e5-55b7-11e2-877f-002564c97630")
public class GmElementImportStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a547b4d9-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("IMPORT_CONNECTIONROUTER",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a547dbeb-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("IMPORT_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a547dbee-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("IMPORT_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a54802fb-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("IMPORT_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a5482a09-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("IMPORT_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a5482a0c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("IMPORT_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a5485119-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("IMPORT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show name (alias)
     */
    @objid ("a548511c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("IMPORT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Show stereotypes
     */
    @objid ("a548782b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("IMPORT_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a548782e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("IMPORT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a5489f3b-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("IMPORT_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Show information flows.
     */
    @objid ("a548c649-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWFLOWS = createStyleKey("IMPORT_SHOWFLOWS", Boolean.class);

}
