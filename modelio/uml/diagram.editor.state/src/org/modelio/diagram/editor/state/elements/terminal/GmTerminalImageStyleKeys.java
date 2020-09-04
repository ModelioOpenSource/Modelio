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

package org.modelio.diagram.editor.state.elements.terminal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmTerminal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f5a0f9b4-55b6-11e2-877f-002564c97630")
public class GmTerminalImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81b039d1-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("TERMINAL_REPMODE", MetaKey.REPMODE);

    @objid ("81b1c069-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("TERMINAL_FONT", MetaKey.FONT);

    @objid ("81b1c06b-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("TERMINAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81b1c06d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TERMINAL_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("81b1c06f-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("TERMINAL_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("1adb3d4c-5868-4165-bae0-691a47527e7a")
     static final StyleKey SHOWLABEL = createStyleKey("TERMINAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
