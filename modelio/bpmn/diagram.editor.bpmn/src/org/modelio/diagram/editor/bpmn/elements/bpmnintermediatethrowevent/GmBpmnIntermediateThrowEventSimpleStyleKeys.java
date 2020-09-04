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
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("61115bfc-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateThrowEventSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71a2d70b-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnIntermediateThrowEventStructuredStyleKeys.REPMODE;

    @objid ("71a2d70d-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INTERMEDIATTHROWEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("71a2d70f-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("INTERMEDIATTHROWEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("71a2d711-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INTERMEDIATTHROWEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("71a2d713-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INTERMEDIATTHROWEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("71a2d715-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnIntermediateThrowEventStructuredStyleKeys.FONT;

    @objid ("71a2d717-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateThrowEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("71a2d719-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("71a2d71b-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("71a2d71d-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateThrowEventStructuredStyleKeys.SHOWLABEL;

}
