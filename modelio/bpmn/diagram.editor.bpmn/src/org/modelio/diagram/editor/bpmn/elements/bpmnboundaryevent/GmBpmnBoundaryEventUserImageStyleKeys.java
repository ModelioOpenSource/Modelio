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

package org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBoundaryEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("0da9baec-fe76-46bd-8da6-0126962e0ed0")
public class GmBpmnBoundaryEventUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("f1c85051-40db-4ea4-a075-511a7c253b96")
     static final StyleKey REPMODE = GmBpmnBoundaryEventStructuredStyleKeys.REPMODE;

    @objid ("07e40ddc-1d4e-411d-8499-d9be9f77acd7")
     static final StyleKey FONT = GmBpmnBoundaryEventStructuredStyleKeys.FONT;

    @objid ("2272c148-0d65-4690-af8a-6307e3a9ff78")
     static final StyleKey TEXTCOLOR = GmBpmnBoundaryEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("24c0078e-14e5-499e-8227-ed6a5b8d7dee")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnBoundaryEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("285202de-85e8-4668-8c76-5701de3a0482")
     static final StyleKey SHOWTAGS = GmBpmnBoundaryEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("f6421d01-9076-4cc5-a3fd-3580bdac88e8")
     static final StyleKey SHOWLABEL = GmBpmnBoundaryEventStructuredStyleKeys.SHOWLABEL;

}
