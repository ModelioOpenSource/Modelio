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

package org.modelio.diagram.editor.statik.elements.bpmnprocess;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnProcess when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("342d8c51-55b7-11e2-877f-002564c97630")
public class GmBpmnProcessSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a601b60c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnProcessStructuredStyleKeys.REPMODE;

    @objid ("a601b60e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmBpmnProcessStructuredStyleKeys.FILLCOLOR;

    @objid ("a601b610-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmBpmnProcessStructuredStyleKeys.FILLMODE;

    @objid ("a601b612-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmBpmnProcessStructuredStyleKeys.LINECOLOR;

    @objid ("a601b614-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmBpmnProcessStructuredStyleKeys.LINEWIDTH;

    @objid ("a601b616-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnProcessStructuredStyleKeys.FONT;

    @objid ("a601b618-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnProcessStructuredStyleKeys.TEXTCOLOR;

    @objid ("a601b61a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnProcessStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a601b61c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnProcessStructuredStyleKeys.SHOWTAGS;

}
