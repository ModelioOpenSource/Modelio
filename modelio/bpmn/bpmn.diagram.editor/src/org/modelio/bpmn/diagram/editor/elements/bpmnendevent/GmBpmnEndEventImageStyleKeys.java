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

package org.modelio.bpmn.diagram.editor.elements.bpmnendevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnEndEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("60e08801-55b6-11e2-877f-002564c97630")
public class GmBpmnEndEventImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71707c6d-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnEndEventStructuredStyleKeys.REPMODE;

    @objid ("71707c6f-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnEndEventStructuredStyleKeys.FONT;

    @objid ("71707c71-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnEndEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("71707c73-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnEndEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("71707c75-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnEndEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("71707c77-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnEndEventStructuredStyleKeys.SHOWLABEL;

}
