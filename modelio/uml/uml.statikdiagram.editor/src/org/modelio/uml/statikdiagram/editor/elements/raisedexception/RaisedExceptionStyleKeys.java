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

package org.modelio.uml.statikdiagram.editor.elements.raisedexception;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("36621fb1-55b7-11e2-877f-002564c97630")
public class RaisedExceptionStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a7703a2b-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("RAISEDEXCEPTION_CONNECTIONROUTER",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a7703a2e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("RAISEDEXCEPTION_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a7703a31-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("RAISEDEXCEPTION_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a7703a34-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("RAISEDEXCEPTION_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a7703a37-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("RAISEDEXCEPTION_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a7703a3a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("RAISEDEXCEPTION_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a7703a3d-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("RAISEDEXCEPTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show name (alias)
     */
    @objid ("a7703a40-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("RAISEDEXCEPTION_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Show stereotypes
     */
    @objid ("a7703a43-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("RAISEDEXCEPTION_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a7703a46-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("RAISEDEXCEPTION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a7703a49-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("RAISEDEXCEPTION_LINEPATTERN",
                                                              MetaKey.LINEPATTERN);

    /**
     * Show information flows.
     */
    @objid ("a7703a4c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWFLOWS = createStyleKey("RAISEDEXCEPTION_SHOWFLOWS", Boolean.class);

}
