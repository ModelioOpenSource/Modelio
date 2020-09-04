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

package org.modelio.diagram.editor.activity.elements.outputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmOutputPin when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2aef53b1-55b6-11e2-877f-002564c97630")
public class GmOutputPinStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d256817c-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("OUTPUTPIN_REPMODE", MetaKey.REPMODE);

    @objid ("d256817e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("OUTPUTPIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d2568180-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("OUTPUTPIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("d2568182-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("OUTPUTPIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d2568184-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("OUTPUTPIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d2568186-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("OUTPUTPIN_FONT", MetaKey.FONT);

    @objid ("d2568188-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("OUTPUTPIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d258080a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("OUTPUTPIN_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d258080c-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("OUTPUTPIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d258080e-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("OUTPUTPIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
