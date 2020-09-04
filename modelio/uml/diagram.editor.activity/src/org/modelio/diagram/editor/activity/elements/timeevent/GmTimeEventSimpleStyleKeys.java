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

package org.modelio.diagram.editor.activity.elements.timeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmTimeEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2b63993f-55b6-11e2-877f-002564c97630")
public class GmTimeEventSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d29fbf69-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmTimeEventStructuredStyleKeys.REPMODE;

    @objid ("d29fbf6b-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmTimeEventStructuredStyleKeys.FILLCOLOR;

    @objid ("d29fbf6d-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmTimeEventStructuredStyleKeys.FILLMODE;

    @objid ("d29fbf6f-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmTimeEventStructuredStyleKeys.LINECOLOR;

    @objid ("d29fbf71-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmTimeEventStructuredStyleKeys.LINEWIDTH;

    @objid ("d29fbf73-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmTimeEventStructuredStyleKeys.FONT;

    @objid ("d29fbf75-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmTimeEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("d29fbf77-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmTimeEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d29fbf79-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmTimeEventStructuredStyleKeys.SHOWTAGS;

    @objid ("d29fbf7b-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmTimeEventStructuredStyleKeys.AUTOSHOWPINS;

}
