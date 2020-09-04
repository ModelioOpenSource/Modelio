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

package org.modelio.diagram.editor.statik.elements.collab;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("344f1e33-55b7-11e2-877f-002564c97630")
public class CollaborationSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a60c6469-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = CollaborationStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("a60c646c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = CollaborationStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode.
     */
    @objid ("a60c646f-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = CollaborationStructuredStyleKeys.FILLMODE;

    /**
     * Line color.
     */
    @objid ("a60c6472-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = CollaborationStructuredStyleKeys.LINECOLOR;

    /**
     * Line width.
     */
    @objid ("a60c6475-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = CollaborationStructuredStyleKeys.LINEWIDTH;

    /**
     * Text font.
     */
    @objid ("a60c6478-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = CollaborationStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("a60c647b-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = CollaborationStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("a60c647e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = CollaborationStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("a60c6481-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = CollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("a60c6484-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = CollaborationStructuredStyleKeys.SHOWTAGS;

    /**
     * Display visibility.
     */
    @objid ("a60deb09-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = CollaborationStructuredStyleKeys.SHOWVISIBILITY;

}
