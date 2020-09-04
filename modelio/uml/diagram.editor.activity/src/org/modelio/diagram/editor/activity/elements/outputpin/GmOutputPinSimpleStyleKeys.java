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
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmOutputPin when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2aef5389-55b6-11e2-877f-002564c97630")
public class GmOutputPinSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d256816a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmOutputPinStructuredStyleKeys.REPMODE;

    @objid ("d256816c-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmOutputPinStructuredStyleKeys.FILLCOLOR;

    @objid ("d256816e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmOutputPinStructuredStyleKeys.FILLMODE;

    @objid ("d2568170-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmOutputPinStructuredStyleKeys.LINECOLOR;

    @objid ("d2568172-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmOutputPinStructuredStyleKeys.LINEWIDTH;

    @objid ("d2568174-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmOutputPinStructuredStyleKeys.FONT;

    @objid ("d2568176-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmOutputPinStructuredStyleKeys.TEXTCOLOR;

    @objid ("d2568178-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmOutputPinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d256817a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmOutputPinStructuredStyleKeys.SHOWTAGS;

}
