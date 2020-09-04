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
 * This class provides the StyleKey constants for a GmTerminal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("5a1c415b-0ffe-4597-af43-023f8f5e6793")
public class GmTerminalUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("121c4099-1aa0-446e-aa3e-525e884765ef")
    public static final StyleKey REPMODE = createStyleKey("TERMINAL_REPMODE", MetaKey.REPMODE);

    @objid ("bc179dd0-eb5f-480e-82f7-ccf58f390bd7")
    public static final StyleKey FONT = createStyleKey("TERMINAL_FONT", MetaKey.FONT);

    @objid ("59a4d626-c353-4755-ac61-f0e238303e0a")
    public static final StyleKey TEXTCOLOR = createStyleKey("TERMINAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a2a69474-113e-4f15-a896-35091eb09968")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TERMINAL_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("481e23c6-5c0a-4b12-b5c9-3a639ca862ac")
    public static final StyleKey SHOWTAGS = createStyleKey("TERMINAL_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("01f0a910-20d2-4346-bea8-675dcac11c81")
     static final StyleKey SHOWLABEL = createStyleKey("TERMINAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
