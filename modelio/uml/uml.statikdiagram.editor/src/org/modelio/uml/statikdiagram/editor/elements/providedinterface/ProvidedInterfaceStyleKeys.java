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

package org.modelio.uml.statikdiagram.editor.elements.providedinterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for ProvidedInterface.
 * 
 * @author cmarin
 */
@objid ("3658f7df-55b7-11e2-877f-002564c97630")
public class ProvidedInterfaceStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Lines color.
     */
    @objid ("a76a1fab-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("PROVIDEDINTERFACE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a76a1fae-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("PROVIDEDINTERFACE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a76a1fb1-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("PROVIDEDINTERFACE_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a76a1fb4-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("PROVIDEDINTERFACE_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("a76a1fb7-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("PROVIDEDINTERFACE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a76a1fba-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("PROVIDEDINTERFACE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a76a1fbd-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("PROVIDEDINTERFACE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a76a1fc0-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("PROVIDEDINTERFACE_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a76a1fc3-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("PROVIDEDINTERFACE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Fill color.
     */
    @objid ("a76a1fc6-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("PROVIDEDINTERFACE_FILLCOLOR", MetaKey.FILLCOLOR);

}
