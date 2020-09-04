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
@objid ("8331b2ea-d6a0-47a8-bdaf-07a0f17bca68")
public class GmBpmnIntermediateCatchEventUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("49f2de44-199b-482b-9d5e-565530e26e9f")
     static final StyleKey REPMODE = GmBpmnIntermediateCatchEventStructuredStyleKeys.REPMODE;

    @objid ("9cc1e18e-233e-4968-88ef-e2437ac73ed5")
     static final StyleKey FONT = GmBpmnIntermediateCatchEventStructuredStyleKeys.FONT;

    @objid ("cf8e6df4-ea55-4673-9792-c02a4380e591")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateCatchEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("937a26d6-da2c-42c8-8ff9-3fd16c8bdfc0")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("c38cecb0-4f4c-46ed-8176-f39d19770ff1")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("cd7d01b5-6036-461c-84da-a204d521f605")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWLABEL;

}
