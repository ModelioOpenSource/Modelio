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

package org.modelio.uml.statikdiagram.editor.elements.collabuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("346f2950-55b7-11e2-877f-002564c97630")
public class CollaborationUseSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a621c13b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = CollaborationUseStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("a621c13e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = CollaborationUseStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode.
     */
    @objid ("a621c141-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = CollaborationUseStructuredStyleKeys.FILLMODE;

    /**
     * Line color.
     */
    @objid ("a621c144-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = CollaborationUseStructuredStyleKeys.LINECOLOR;

    /**
     * Line width.
     */
    @objid ("a621c147-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = CollaborationUseStructuredStyleKeys.LINEWIDTH;

    /**
     * Text font.
     */
    @objid ("a621c14a-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = CollaborationUseStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("a621c14d-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = CollaborationUseStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("a621c150-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = CollaborationUseStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("a621c153-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = CollaborationUseStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("a621c156-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = CollaborationUseStructuredStyleKeys.SHOWTAGS;

}
