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
package org.modelio.uml.statikdiagram.editor.elements.collab.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("34616da4-55b7-11e2-877f-002564c97630")
class CollaborationSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a56268c9-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = CollaborationStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("a5628fd9-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = CollaborationStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode.
     */
    @objid ("a5628fdc-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = CollaborationStructuredStyleKeys.FILLMODE;

    /**
     * Line color.
     */
    @objid ("a562b6e9-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = CollaborationStructuredStyleKeys.LINECOLOR;

    /**
     * Line width.
     */
    @objid ("a562b6ec-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = CollaborationStructuredStyleKeys.LINEWIDTH;

    /**
     * Text font.
     */
    @objid ("a562ddf9-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = CollaborationStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("a562ddfc-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = CollaborationStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("a563050b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = CollaborationStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("a563050e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = CollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("a5632c1b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = CollaborationStructuredStyleKeys.SHOWTAGS;

    /**
     * Display visibility.
     */
    @objid ("a5632c1e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = CollaborationStructuredStyleKeys.SHOWVISIBILITY;

}
