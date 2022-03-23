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
package org.modelio.uml.statikdiagram.editor.elements.realization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmInterfaceRealization}.
 * 
 * @author fpoyer
 */
@objid ("36683a37-55b7-11e2-877f-002564c97630")
public class GmInterfaceRealizationStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a77654ad-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("INTERFACEREALIZATION_CONNECTIONROUTER",
                                                                       MetaKey.CONNECTIONROUTER);

    /**
     * Fill color.
     */
    @objid ("a77654b0-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("INTERFACEREALIZATION_FILLCOLOR",
                                                                MetaKey.FILLCOLOR);

    /**
     * Line color.
     */
    @objid ("a77654b3-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("INTERFACEREALIZATION_LINECOLOR",
                                                                MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a77654b6-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("INTERFACEREALIZATION_LINEWIDTH",
                                                                MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a77654b9-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("INTERFACEREALIZATION_LINERADIUS",
                                                                 MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a77654bc-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("INTERFACEREALIZATION_DRAWLINEBRIDGES",
                                                                      MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("a77654bf-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("INTERFACEREALIZATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a777db4b-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("INTERFACEREALIZATION_TEXTCOLOR",
                                                                MetaKey.TEXTCOLOR);

    /**
     * Stereotype mode: none, icon, text, text+icon
     */
    @objid ("a777db4e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERFACEREALIZATION_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tags: {@link Boolean}.
     */
    @objid ("a777db51-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("INTERFACEREALIZATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern.
     */
    @objid ("a777db54-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("INTERFACEREALIZATION_LINEPATTERN",
                                                                  MetaKey.LINEPATTERN);

}
