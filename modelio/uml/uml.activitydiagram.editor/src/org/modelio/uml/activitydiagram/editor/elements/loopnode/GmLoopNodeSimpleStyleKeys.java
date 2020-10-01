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

package org.modelio.uml.activitydiagram.editor.elements.loopnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmLoopnode when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2ac31397-55b6-11e2-877f-002564c97630")
public class GmLoopNodeSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("d128138a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmLoopNodeStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("d128138d-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmLoopNodeStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode: transparent, solid, gradient.
     */
    @objid ("d1299a2b-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmLoopNodeStructuredStyleKeys.FILLMODE;

    /**
     * Outline color.
     */
    @objid ("d1299a2e-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmLoopNodeStructuredStyleKeys.LINECOLOR;

    /**
     * Outline width.
     */
    @objid ("d1299a31-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmLoopNodeStructuredStyleKeys.LINEWIDTH;

    /**
     * Text font.
     */
    @objid ("d1299a34-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmLoopNodeStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("d1299a37-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmLoopNodeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Stereotypes display mode
     */
    @objid ("d1299a3a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmLoopNodeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Show tagged values.
     */
    @objid ("d1299a3d-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmLoopNodeStructuredStyleKeys.SHOWTAGS;

    @objid ("d1299a40-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmLoopNodeStructuredStyleKeys.AUTOSHOWPINS;

}
