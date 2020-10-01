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

package org.modelio.uml.activitydiagram.editor.elements.inputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2aadb6b7-55b6-11e2-877f-002564c97630")
public class GmInputPinSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d22426ca-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInputPinStructuredStyleKeys.REPMODE;

    @objid ("d22426cc-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmInputPinStructuredStyleKeys.FILLCOLOR;

    @objid ("d22426ce-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmInputPinStructuredStyleKeys.FILLMODE;

    @objid ("d22426d0-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmInputPinStructuredStyleKeys.LINECOLOR;

    @objid ("d22426d2-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmInputPinStructuredStyleKeys.LINEWIDTH;

    @objid ("d22426d4-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInputPinStructuredStyleKeys.FONT;

    @objid ("d22426d6-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInputPinStructuredStyleKeys.TEXTCOLOR;

    @objid ("d22426d8-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInputPinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d22426da-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInputPinStructuredStyleKeys.SHOWTAGS;

}
