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

package org.modelio.diagram.editor.state.elements.junction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmJunction when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f563f095-55b6-11e2-877f-002564c97630")
public class GmJunctionSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("817ccdb9-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("JUNCTION_REPMODE", MetaKey.REPMODE);

    @objid ("817ccdbb-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("JUNCTION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("817ccdbd-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("JUNCTION_FILLMODE", MetaKey.FILLMODE);

    @objid ("817cf4c9-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("JUNCTION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("817cf4cb-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("JUNCTION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("817cf4cd-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("JUNCTION_FONT", MetaKey.FONT);

    @objid ("817d1bda-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("JUNCTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("817d1bdc-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("JUNCTION_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("817d1bde-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("JUNCTION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("817d42ea-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("JUNCTION_SHOWLABEL", MetaKey.SHOWLABEL);

}
