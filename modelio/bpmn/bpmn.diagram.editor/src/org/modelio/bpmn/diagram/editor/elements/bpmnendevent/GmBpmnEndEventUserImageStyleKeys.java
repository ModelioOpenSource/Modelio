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
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnEndEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("c599d98a-8270-40b6-bc3d-c6e7cefaec22")
public class GmBpmnEndEventUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("f771720d-f065-43d3-b4a9-5a5c61109df0")
    static final StyleKey REPMODE = GmBpmnEndEventStructuredStyleKeys.REPMODE;

    @objid ("82b58eba-8ebf-4a5f-bcfb-8e160f1d8c45")
    static final StyleKey FONT = GmBpmnEndEventStructuredStyleKeys.FONT;

    @objid ("d657df2c-75fe-4a57-9123-c2c0167bba8e")
    static final StyleKey TEXTCOLOR = GmBpmnEndEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("7029050a-1050-49f7-91c8-fcaeb8e10895")
    static final StyleKey SHOWSTEREOTYPES = GmBpmnEndEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("749bcfe9-8fd3-46ba-9491-7aeee345e33b")
    static final StyleKey SHOWTAGS = GmBpmnEndEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("3d20fd2b-f085-4800-b0e7-cbf552aee351")
    static final StyleKey SHOWLABEL = GmBpmnEndEventStructuredStyleKeys.SHOWLABEL;

}
