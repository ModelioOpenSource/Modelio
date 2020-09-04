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

package org.modelio.diagram.editor.statik.elements.activity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmActivity when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("33de33ef-55b7-11e2-877f-002564c97630")
public class GmActivitySimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5da09ce-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmActivityStructuredStyleKeys.REPMODE;

    @objid ("a5da09d0-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmActivityStructuredStyleKeys.FILLCOLOR;

    @objid ("a5da09d2-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmActivityStructuredStyleKeys.FILLMODE;

    @objid ("a5da09d4-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmActivityStructuredStyleKeys.LINECOLOR;

    @objid ("a5db9069-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmActivityStructuredStyleKeys.LINEWIDTH;

    @objid ("a5db906b-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmActivityStructuredStyleKeys.FONT;

    @objid ("a5db906d-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmActivityStructuredStyleKeys.TEXTCOLOR;

    @objid ("a5db906f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmActivityStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5db9071-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmActivityStructuredStyleKeys.SHOWTAGS;

}
