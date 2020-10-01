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

package org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmBpmnSharedDefinitions when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("eee31a8e-b31b-43de-be5c-454294e87216")
public class GmBpmnSharedDefinitionsStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("d3a922ef-666d-4330-8bdc-5b937278af34")
     static final StyleKey REPMODE = createStyleKey("BPMNSHAREDDEFINITIONS_REPMODE", MetaKey.REPMODE);

    @objid ("4f0c568c-75e1-48a5-b570-7a78067a0db2")
     static final StyleKey FILLCOLOR = createStyleKey("BPMNSHAREDDEFINITIONS_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("ba8ad0a7-064a-4dbf-83f1-4011fbc729be")
     static final StyleKey FILLMODE = createStyleKey("BPMNSHAREDDEFINITIONS_FILLMODE", MetaKey.FILLMODE);

    @objid ("3e775eec-7be9-486c-99a9-436a147ef74b")
     static final StyleKey LINECOLOR = createStyleKey("BPMNSHAREDDEFINITIONS_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("cad4becd-7b7a-4eca-9dc6-c78dbca86068")
     static final StyleKey LINEWIDTH = createStyleKey("BPMNSHAREDDEFINITIONS_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("ad84497b-02f2-47d5-a087-bb23c0a523b1")
     static final StyleKey FONT = createStyleKey("BPMNSHAREDDEFINITIONS_FONT", MetaKey.FONT);

    @objid ("d28bb9fc-ed91-4545-9ce0-cd7c0f71127b")
     static final StyleKey TEXTCOLOR = createStyleKey("BPMNSHAREDDEFINITIONS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("668fb63a-7037-4a9f-8fd7-9d4210415be0")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNSHAREDDEFINITIONS_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("e9a0ac55-e199-491f-b3b8-1194b2733c86")
     static final StyleKey SHOWTAGS = createStyleKey("BPMNSHAREDDEFINITIONS_SHOWTAGS", MetaKey.SHOWTAGS);

}
