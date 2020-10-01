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

package org.modelio.uml.statikdiagram.editor.elements.collabuse.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("348178bf-55b7-11e2-877f-002564c97630")
class CollaborationUseSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a631037c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = CollaborationUseStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("a631037f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = CollaborationUseStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode.
     */
    @objid ("a6310382-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = CollaborationUseStructuredStyleKeys.FILLMODE;

    /**
     * Line color.
     */
    @objid ("a6310385-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = CollaborationUseStructuredStyleKeys.LINECOLOR;

    /**
     * Line width.
     */
    @objid ("a6310388-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = CollaborationUseStructuredStyleKeys.LINEWIDTH;

    /**
     * Text font.
     */
    @objid ("a631038b-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = CollaborationUseStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("a631038e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = CollaborationUseStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("a6328a0a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = CollaborationUseStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("a6328a0d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = CollaborationUseStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("a6328a10-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = CollaborationUseStructuredStyleKeys.SHOWTAGS;

}
