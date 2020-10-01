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

package org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnIntermediateThrowEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("610e4eb4-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateThrowEventImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("719fc9cd-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnIntermediateThrowEventStructuredStyleKeys.REPMODE;

    @objid ("719fc9cf-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnIntermediateThrowEventStructuredStyleKeys.FONT;

    @objid ("719fc9d1-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateThrowEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("719fc9d3-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("719fc9d5-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("719fc9d7-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWLABEL;

}
