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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("6106ad89-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateCatchEventSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("719828ac-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnIntermediateCatchEventStructuredStyleKeys.REPMODE;

    @objid ("719828ae-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INTERMEDIATECATCHEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("719828b0-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("INTERMEDIATECATCHEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("719828b2-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INTERMEDIATECATCHEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("719828b4-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INTERMEDIATECATCHEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("719828b6-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnIntermediateCatchEventStructuredStyleKeys.FONT;

    @objid ("719828b8-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnIntermediateCatchEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("719828ba-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("719828bc-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("719828be-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnIntermediateCatchEventStructuredStyleKeys.SHOWLABEL;

}
