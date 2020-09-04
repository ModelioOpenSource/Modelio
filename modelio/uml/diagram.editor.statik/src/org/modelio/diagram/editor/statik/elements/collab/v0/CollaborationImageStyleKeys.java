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

package org.modelio.diagram.editor.statik.elements.collab.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.IMAGE
 */
@objid ("34616d7a-55b7-11e2-877f-002564c97630")
class CollaborationImageStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a61712c9-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = CollaborationStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("a61712cc-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = CollaborationStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("a61712cf-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = CollaborationStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("a61712d2-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = CollaborationStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("a61712d5-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = CollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("a61712d8-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = CollaborationStructuredStyleKeys.SHOWTAGS;

    /**
     * Display visibility.
     */
    @objid ("a61712db-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = CollaborationStructuredStyleKeys.SHOWVISIBILITY;

}
