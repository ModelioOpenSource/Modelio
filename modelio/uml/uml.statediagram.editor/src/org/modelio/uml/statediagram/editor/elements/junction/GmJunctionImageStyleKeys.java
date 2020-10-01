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

package org.modelio.uml.statediagram.editor.elements.junction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmJunction when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f560e35c-55b6-11e2-877f-002564c97630")
public class GmJunctionImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8179e789-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("JUNCTION_REPMODE", MetaKey.REPMODE);

    @objid ("8179e78b-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("JUNCTION_FONT", MetaKey.FONT);

    @objid ("8179e78d-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("JUNCTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("817a0e9a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("JUNCTION_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("817a0e9c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("JUNCTION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("683f8701-d58a-4cd6-9abb-56ef8b6a00b5")
     static final StyleKey SHOWLABEL = createStyleKey("JUNCTION_SHOWLABEL", MetaKey.SHOWLABEL);

}
