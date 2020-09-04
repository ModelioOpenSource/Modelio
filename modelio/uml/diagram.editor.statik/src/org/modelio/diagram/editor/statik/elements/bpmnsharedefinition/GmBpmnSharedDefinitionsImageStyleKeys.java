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

package org.modelio.diagram.editor.statik.elements.bpmnsharedefinition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;

/**
 * This class provides the StyleKey constants for a {@link BpmnSharedDefinitions} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("ae621875-d20c-429a-aea4-6fdc53564d41")
public class GmBpmnSharedDefinitionsImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("ab86a9e7-b077-4639-bd0e-ca864f533140")
     static final StyleKey REPMODE = GmBpmnSharedDefinitionsStructuredStyleKeys.REPMODE;

    @objid ("5150e426-1546-4f24-b1ec-8e446477c0d3")
     static final StyleKey FONT = GmBpmnSharedDefinitionsStructuredStyleKeys.FONT;

    @objid ("f5214755-1799-46e0-969e-a0540677d788")
     static final StyleKey TEXTCOLOR = GmBpmnSharedDefinitionsStructuredStyleKeys.TEXTCOLOR;

    @objid ("65053e4f-8ada-4064-96f4-c93be13ba5c6")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnSharedDefinitionsStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d8e6a312-3af9-4240-b492-a1479bd3883b")
     static final StyleKey SHOWTAGS = GmBpmnSharedDefinitionsStructuredStyleKeys.SHOWTAGS;

}
