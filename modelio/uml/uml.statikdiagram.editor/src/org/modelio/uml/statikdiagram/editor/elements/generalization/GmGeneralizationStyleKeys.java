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

package org.modelio.uml.statikdiagram.editor.elements.generalization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmGeneralization}.
 * 
 * @author cmarin
 */
@objid ("34edcf14-55b7-11e2-877f-002564c97630")
public class GmGeneralizationStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a66b4d4a-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("GENZ_CONNECTIONROUTER",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Fill color.
     */
    @objid ("a66b4d4d-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("GENZ_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Line color.
     */
    @objid ("a66b4d50-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("GENZ_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a66b4d53-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("GENZ_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a66b4d56-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("GENZ_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a66b4d59-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("GENZ_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("a66b4d5c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("GENZ_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a66b4d5f-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("GENZ_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype mode: none, icon, text, text+icon
     */
    @objid ("a66b4d62-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("GENZ_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tags: {@link Boolean}.
     */
    @objid ("a66cd3e9-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("GENZ_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern.
     */
    @objid ("a66cd3ec-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("GENZ_LINEPATTERN", MetaKey.LINEPATTERN);

}
