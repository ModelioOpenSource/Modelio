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
package org.modelio.uml.statediagram.editor.elements.exit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmExit when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("f520cd0a-55b6-11e2-877f-002564c97630")
public class GmExitStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("810505ab-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("EXIT_REPMODE", MetaKey.REPMODE);

    @objid ("810505ad-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("EXIT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("810505af-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("EXIT_FILLMODE", MetaKey.FILLMODE);

    @objid ("810505b1-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("EXIT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("810505b3-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("EXIT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("810505b5-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("EXIT_FONT", MetaKey.FONT);

    @objid ("810505b7-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("EXIT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("810505b9-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXIT_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("810505bb-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("EXIT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("810505bd-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("EXIT_SHOWLABEL", MetaKey.SHOWLABEL);

}
