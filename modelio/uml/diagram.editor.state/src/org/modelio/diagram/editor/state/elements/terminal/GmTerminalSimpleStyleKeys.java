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

package org.modelio.diagram.editor.state.elements.terminal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmTerminal when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f5a406e8-55b6-11e2-877f-002564c97630")
public class GmTerminalSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81b3470c-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("TERMINAL_REPMODE", MetaKey.REPMODE);

    @objid ("81b3470e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("TERMINAL_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81b34710-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("TERMINAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81b34712-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("TERMINAL_FONT", MetaKey.FONT);

    @objid ("81b34714-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("TERMINAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81b34716-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TERMINAL_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("81b34718-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("TERMINAL_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81b3471a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("TERMINAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
