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

package org.modelio.diagram.editor.activity.elements.valuepin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmValuePin when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2b6e47aa-55b6-11e2-877f-002564c97630")
public class GmValuePinSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d2a2ccac-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmValuePinStructuredStyleKeys.REPMODE;

    @objid ("d2a2ccae-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmValuePinStructuredStyleKeys.FILLCOLOR;

    @objid ("d2a2ccb0-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmValuePinStructuredStyleKeys.FILLMODE;

    @objid ("d2a2ccb2-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmValuePinStructuredStyleKeys.LINECOLOR;

    @objid ("d2a2ccb4-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmValuePinStructuredStyleKeys.LINEWIDTH;

    @objid ("d2a4534a-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmValuePinStructuredStyleKeys.FONT;

    @objid ("d2a4534c-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmValuePinStructuredStyleKeys.TEXTCOLOR;

    @objid ("d2a4534e-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmValuePinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d2a45350-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmValuePinStructuredStyleKeys.SHOWTAGS;

}
