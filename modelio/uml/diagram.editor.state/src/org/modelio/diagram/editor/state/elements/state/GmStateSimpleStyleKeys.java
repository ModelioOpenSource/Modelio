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

package org.modelio.diagram.editor.state.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmState when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f5858272-55b6-11e2-877f-002564c97630")
public class GmStateSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8195aceb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmStateStructuredStyleKeys.REPMODE;

    @objid ("8195d3fa-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmStateStructuredStyleKeys.FILLCOLOR;

    @objid ("8195d3fc-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmStateStructuredStyleKeys.FILLMODE;

    @objid ("8195d3fe-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmStateStructuredStyleKeys.LINECOLOR;

    @objid ("8195fb0a-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmStateStructuredStyleKeys.LINEWIDTH;

    @objid ("8195fb0c-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmStateStructuredStyleKeys.FONT;

    @objid ("8195fb0e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("8196221a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8196221c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmStateStructuredStyleKeys.SHOWTAGS;

    @objid ("8196221e-55c2-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPOINTS = GmStateStructuredStyleKeys.AUTOSHOWPOINTS;

}
