/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.state.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f5024879-55b6-11e2-877f-002564c97630")
public class GmConnectionPointStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8152d78b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CONNECTIONPOINT_REPMODE", MetaKey.REPMODE);

    @objid ("8152d78d-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CONNECTIONPOINT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("8152d78f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CONNECTIONPOINT_FILLMODE", MetaKey.FILLMODE);

    @objid ("8152d791-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CONNECTIONPOINT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("8152d793-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CONNECTIONPOINT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8152d795-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CONNECTIONPOINT_FONT", MetaKey.FONT);

    @objid ("8152d797-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CONNECTIONPOINT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("8152d799-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONNECTIONPOINT_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("8152d79b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CONNECTIONPOINT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8152d79d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("CONNECTIONPOINT_SHOWLABEL", MetaKey.SHOWLABEL);

}
