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

package org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBoundaryEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("60880787-55b6-11e2-877f-002564c97630")
public class GmBpmnBoundaryEventImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7120d5cf-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnBoundaryEventStructuredStyleKeys.REPMODE;

    @objid ("7120d5d1-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnBoundaryEventStructuredStyleKeys.FONT;

    @objid ("7120d5d3-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnBoundaryEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("7120d5d5-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnBoundaryEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("7120d5d7-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnBoundaryEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7120fcd9-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnBoundaryEventStructuredStyleKeys.SHOWLABEL;

}
