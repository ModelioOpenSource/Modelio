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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnIntermediateCatchEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("6103a040-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateCatchEventImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71951b70-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnIntermediateCatchEventStructuredStyleKeys.REPMODE;

    @objid ("71951b72-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnIntermediateCatchEventStructuredStyleKeys.FONT;

    @objid ("71951b74-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateCatchEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("71951b76-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("7196a20a-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7196a20c-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWLABEL;

}
