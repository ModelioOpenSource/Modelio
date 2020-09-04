/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnstartevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnStartEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("61bc41c7-55b6-11e2-877f-002564c97630")
public class GmBpmnStartEventImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7235530f-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnStartEventStructuredStyleKeys.REPMODE;

    @objid ("72355311-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnStartEventStructuredStyleKeys.FONT;

    @objid ("72355313-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnStartEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("72355315-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnStartEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("72355317-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnStartEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7236d9a9-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnStartEventStructuredStyleKeys.SHOWLABEL;

}
