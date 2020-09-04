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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnEndEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2d7a7535-47fd-4ac7-b797-42c316e2a1a3")
public class GmBpmnDataUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("4b0abac0-e332-4db5-891a-f48905ea8a59")
     static final StyleKey REPMODE = GmBpmnDataObjectStyleKeys.REPMODE;

    @objid ("67fe016c-d47b-415d-bee1-2983893b191b")
     static final StyleKey FONT = GmBpmnDataObjectStyleKeys.FONT;

    @objid ("3eaa1d80-238e-49ad-bc0c-a7c6412e1aa5")
     static final StyleKey TEXTCOLOR = GmBpmnDataObjectStyleKeys.TEXTCOLOR;

    @objid ("399045b4-eb9c-4b0a-ac1a-0f2ce623d460")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnDataObjectStyleKeys.SHOWSTEREOTYPES;

    @objid ("f9651d88-6265-4109-9695-ba5509b9679f")
     static final StyleKey SHOWTAGS = GmBpmnDataObjectStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("ef2958f5-1717-400c-a31f-9561c24a9c82")
     static final StyleKey SHOWLABEL = GmBpmnDataObjectStyleKeys.SHOWLABEL;

}
