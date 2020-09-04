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

package org.modelio.diagram.editor.state.elements.initialstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmInitialstate when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f549ffee-55b6-11e2-877f-002564c97630")
public class GmInitialStateStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("810e2d6c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("INITIALSTATE_REPMODE", MetaKey.REPMODE);

    @objid ("810e2d6e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INITIALSTATE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("810e2d70-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("INITIALSTATE_FILLMODE", MetaKey.FILLMODE);

    @objid ("810e2d72-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INITIALSTATE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("810e2d74-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INITIALSTATE_FONT", MetaKey.FONT);

    @objid ("810e2d76-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INITIALSTATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("810e2d78-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INITIALSTATE_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("810e2d7a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INITIALSTATE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     */
    @objid ("810e2d7c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("INITIALSTATE_SHOWLABEL", MetaKey.SHOWLABEL);

}
