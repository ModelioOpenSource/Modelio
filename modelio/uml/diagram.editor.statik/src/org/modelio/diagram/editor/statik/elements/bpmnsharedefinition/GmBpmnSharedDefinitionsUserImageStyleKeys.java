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

package org.modelio.diagram.editor.statik.elements.bpmnsharedefinition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;

/**
 * This class provides the StyleKey constants for a {@link BpmnSharedDefinitions} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("889c47b1-457e-43b5-b159-0d2fa51a1613")
public class GmBpmnSharedDefinitionsUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("2a4f69b4-62ab-410b-8f2c-078896382f07")
     static final StyleKey REPMODE = GmBpmnSharedDefinitionsStructuredStyleKeys.REPMODE;

    @objid ("343b0244-b9fb-4ddd-b001-211d6549f644")
     static final StyleKey FONT = GmBpmnSharedDefinitionsStructuredStyleKeys.FONT;

    @objid ("d074b4cb-3e84-4080-be82-466ed41a677b")
     static final StyleKey TEXTCOLOR = GmBpmnSharedDefinitionsStructuredStyleKeys.TEXTCOLOR;

    @objid ("98364836-5d2a-4676-aedc-ad543daccf69")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnSharedDefinitionsStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("7b81e9c8-7c24-449c-b697-1f9ce62c459d")
     static final StyleKey SHOWTAGS = GmBpmnSharedDefinitionsStructuredStyleKeys.SHOWTAGS;

}
