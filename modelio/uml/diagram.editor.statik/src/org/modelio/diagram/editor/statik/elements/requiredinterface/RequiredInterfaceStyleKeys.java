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

package org.modelio.diagram.editor.statik.elements.requiredinterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for RequiredInterface.
 * 
 * @author cmarin
 */
@objid ("36822aa6-55b7-11e2-877f-002564c97630")
public class RequiredInterfaceStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Fill mode.
     */
    @objid ("a791cbeb-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("REQUIREDINTERFACE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a791cbee-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("REQUIREDINTERFACE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a791cbf1-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("REQUIREDINTERFACE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a791cbf4-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("REQUIREDINTERFACE_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a791cbf7-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("REQUIREDINTERFACE_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("a791cbfa-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("REQUIREDINTERFACE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a791cbfd-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("REQUIREDINTERFACE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a791cc00-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("REQUIREDINTERFACE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a791cc03-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("REQUIREDINTERFACE_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a791cc06-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("REQUIREDINTERFACE_SHOWTAGS", MetaKey.SHOWTAGS);

}
