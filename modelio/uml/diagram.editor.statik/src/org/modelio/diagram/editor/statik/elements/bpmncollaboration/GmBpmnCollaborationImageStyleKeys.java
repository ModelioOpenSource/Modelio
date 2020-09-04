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

package org.modelio.diagram.editor.statik.elements.bpmncollaboration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;

/**
 * This class provides the StyleKey constants for a {@link BpmnCollaboration} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("9f94adf5-5240-445f-b157-bc8da9be967c")
public class GmBpmnCollaborationImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("77f2beca-c068-401a-8437-95e15062674f")
     static final StyleKey REPMODE = GmBpmnCollaborationStructuredStyleKeys.REPMODE;

    @objid ("a61cf66e-089b-4725-92fc-b51525ec4b91")
     static final StyleKey FONT = GmBpmnCollaborationStructuredStyleKeys.FONT;

    @objid ("607b5dbf-7947-421d-9e85-5f5e9673c2f0")
     static final StyleKey TEXTCOLOR = GmBpmnCollaborationStructuredStyleKeys.TEXTCOLOR;

    @objid ("212eb6ce-71af-4b49-ac4f-ca4302fb7437")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnCollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("3e7f8f3e-0f34-4288-bf27-5acdd4c9312b")
     static final StyleKey SHOWTAGS = GmBpmnCollaborationStructuredStyleKeys.SHOWTAGS;

}
