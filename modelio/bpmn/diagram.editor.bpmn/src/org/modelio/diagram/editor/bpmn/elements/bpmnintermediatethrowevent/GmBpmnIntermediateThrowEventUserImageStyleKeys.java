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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnIntermediateThrowEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("c58ef36c-0f2a-46b0-ae56-deabb99c664a")
public class GmBpmnIntermediateThrowEventUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("e4bd865c-4db1-4dc0-a8f6-a4f307fa2f4b")
     static final StyleKey REPMODE = GmBpmnIntermediateThrowEventStructuredStyleKeys.REPMODE;

    @objid ("f7b72f2d-716a-4c02-8c3c-255c220a21f9")
     static final StyleKey FONT = GmBpmnIntermediateThrowEventStructuredStyleKeys.FONT;

    @objid ("eba67805-4c1a-4762-851e-f443f8a93998")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateThrowEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("bd74a843-12df-43ff-9523-7944b7674b72")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("6c09a51d-7011-4f31-9c58-d47eedf6ef9b")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("0c325512-c166-4367-8519-f25690631310")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWLABEL;

}
