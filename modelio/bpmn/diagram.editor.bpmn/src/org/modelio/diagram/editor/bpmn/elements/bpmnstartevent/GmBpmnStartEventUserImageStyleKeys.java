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

package org.modelio.diagram.editor.bpmn.elements.bpmnstartevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnStartEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("ac2c119c-7d2a-4348-b895-9a49dcc016ef")
public class GmBpmnStartEventUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("05daf5bd-5e9e-4e1f-bc0a-4d89f483459a")
     static final StyleKey REPMODE = GmBpmnStartEventStructuredStyleKeys.REPMODE;

    @objid ("aefa78b2-1a3a-41f4-9683-6a66060c2910")
     static final StyleKey FONT = GmBpmnStartEventStructuredStyleKeys.FONT;

    @objid ("9b900da1-31b2-4981-b68f-7f6f2b9dd027")
     static final StyleKey TEXTCOLOR = GmBpmnStartEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("2ae50bb9-b6bd-48aa-ab33-4dbff6c799fb")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnStartEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("572d72fc-f1af-4090-ab25-6379d7652dc1")
     static final StyleKey SHOWTAGS = GmBpmnStartEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("3f91895b-d280-4fd1-a01c-12b00c6d7213")
     static final StyleKey SHOWLABEL = GmBpmnStartEventStructuredStyleKeys.SHOWLABEL;

}
