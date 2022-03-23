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
package org.modelio.uml.statikdiagram.editor.elements.templatebinding;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("36daaaf6-55b7-11e2-877f-002564c97630")
public class TemplateBindingStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a7f6812b-55c2-11e2-9337-002564c97630")
    static final StyleKey CONNECTIONROUTER = createStyleKey("TEMPLATEBINDING_CONNECTIONROUTER",
                                                                MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("a7f6812e-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("TEMPLATEBINDING_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("a7f68131-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("TEMPLATEBINDING_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a7f68134-55c2-11e2-9337-002564c97630")
    static final StyleKey LINERADIUS = createStyleKey("TEMPLATEBINDING_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a7f68137-55c2-11e2-9337-002564c97630")
    static final StyleKey DRAWLINEBRIDGES = createStyleKey("TEMPLATEBINDING_DRAWLINEBRIDGES",
                                                               MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font
     */
    @objid ("a7f6813a-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("TEMPLATEBINDING_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("a7f6813d-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("TEMPLATEBINDING_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show name (alias)
     */
    @objid ("a7f68140-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWNAME = createStyleKey("TEMPLATEBINDING_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Show stereotypes
     */
    @objid ("a7f68143-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("TEMPLATEBINDING_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values
     */
    @objid ("a7f68146-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("TEMPLATEBINDING_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("a7f68149-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEPATTERN = createStyleKey("TEMPLATEBINDING_LINEPATTERN", MetaKey.LINEPATTERN);

}
