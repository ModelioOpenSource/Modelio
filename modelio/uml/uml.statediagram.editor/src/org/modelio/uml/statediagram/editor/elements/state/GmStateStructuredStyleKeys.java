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

package org.modelio.uml.statediagram.editor.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmState when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f58708f2-55b6-11e2-877f-002564c97630")
public class GmStateStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81068c4c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("STATE_REPMODE", MetaKey.REPMODE);

    @objid ("81068c4e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("STATE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("81068c50-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("STATE_FILLMODE", MetaKey.FILLMODE);

    @objid ("81068c52-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("STATE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81068c54-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("STATE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81068c56-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("STATE_FONT", MetaKey.FONT);

    @objid ("81068c58-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("STATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81068c5a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("STATE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("81068c5c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("STATE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81068c5e-55c2-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPOINTS = createStyleKey("STATE_AUTOSHOWPOINTS", Boolean.class);

    @objid ("81068c60-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWINNER = createStyleKey("STATE_SHOWINNER", Boolean.class);

}
