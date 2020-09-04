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

package org.modelio.diagram.editor.state.elements.transition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmTransition}.
 * 
 * @author cmarin
 */
@objid ("f5b03bce-55b6-11e2-877f-002564c97630")
public class GmTransitionStyleKeys extends StateAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("81bc6ecd-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("TRANSITION_ROUTINGMODE",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("81bc6ed0-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("TRANSITION_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("81bc6ed3-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("TRANSITION_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("81bc6ed6-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("TRANSITION_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("81bdf56b-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("TRANSITION_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("81bdf56e-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("TRANSITION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("81bdf571-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("TRANSITION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("81bdf574-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TRANSITION_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("81bdf577-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("TRANSITION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81bdf57a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("TRANSITION_SHOWLABEL", MetaKey.SHOWLABEL);

    @objid ("81bdf57c-55c2-11e2-9337-002564c97630")
     static final StyleKey GUARDVISIBLE = createStyleKey("TRANSITION_GUARDVISIBLE", Boolean.class);

    @objid ("81bdf57e-55c2-11e2-9337-002564c97630")
     static final StyleKey POSTCONDITIONVISIBLE = createStyleKey("TRANSITION_POSTCONDITIONVISIBLE",
                                                                Boolean.class);

    /**
     * Line pattern
     */
    @objid ("81bdf580-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("TRANSITION_LINEPATTERN", MetaKey.LINEPATTERN);

}
