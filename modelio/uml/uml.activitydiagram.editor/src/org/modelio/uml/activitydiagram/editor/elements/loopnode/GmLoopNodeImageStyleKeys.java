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
 * This class provides the StyleKey constants for a GmLoopnode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2abcf8f7-55b6-11e2-877f-002564c97630")
public class GmLoopNodeImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("d22a414b-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmLoopNodeStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("d22a414e-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = GmLoopNodeStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("d22a4151-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmLoopNodeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Show stereotypes.
     */
    @objid ("d22a4154-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmLoopNodeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Show tagged values
     */
    @objid ("d22a4157-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmLoopNodeStructuredStyleKeys.SHOWTAGS;

    @objid ("d22a415a-55c0-11e2-9337-002564c97630")
    static final StyleKey AUTOSHOWPINS = GmLoopNodeStructuredStyleKeys.AUTOSHOWPINS;

}
