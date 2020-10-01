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

package org.modelio.uml.statikdiagram.editor.elements.interaction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInteraction when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("356dfb98-55b7-11e2-877f-002564c97630")
public class GmInteractionSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a6baa5cd-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInteractionStructuredStyleKeys.REPMODE;

    @objid ("a6baa5cf-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmInteractionStructuredStyleKeys.FILLCOLOR;

    @objid ("a6baa5d1-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmInteractionStructuredStyleKeys.FILLMODE;

    @objid ("a6bc2c6a-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmInteractionStructuredStyleKeys.LINECOLOR;

    @objid ("a6bc2c6c-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmInteractionStructuredStyleKeys.LINEWIDTH;

    @objid ("a6bc2c6e-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInteractionStructuredStyleKeys.FONT;

    @objid ("a6bc2c70-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInteractionStructuredStyleKeys.TEXTCOLOR;

    @objid ("a6bc2c72-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInteractionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a6bc2c74-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInteractionStructuredStyleKeys.SHOWTAGS;

}
