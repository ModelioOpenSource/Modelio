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

package org.modelio.bpmn.diagram.editor.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnEndEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("60d452dc-55b6-11e2-877f-002564c97630")
public class GmBpmnDataImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7165ce0c-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnDataObjectStyleKeys.REPMODE;

    @objid ("7165ce0e-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnDataObjectStyleKeys.FONT;

    @objid ("7165ce10-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnDataObjectStyleKeys.TEXTCOLOR;

    @objid ("7165ce12-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnDataObjectStyleKeys.SHOWSTEREOTYPES;

    @objid ("7165ce14-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnDataObjectStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7165ce16-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnDataObjectStyleKeys.SHOWLABEL;

}
