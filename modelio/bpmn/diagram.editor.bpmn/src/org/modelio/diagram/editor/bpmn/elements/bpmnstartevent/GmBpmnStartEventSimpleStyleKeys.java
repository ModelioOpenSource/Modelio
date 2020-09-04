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
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("61bf4f0c-55b6-11e2-877f-002564c97630")
public class GmBpmnStartEventSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7238604d-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnStartEventStructuredStyleKeys.REPMODE;

    @objid ("7238604f-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("STARTEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("72386051-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("STARTEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("72386053-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("STARTEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("72386055-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("STARTEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("72386057-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnStartEventStructuredStyleKeys.FONT;

    @objid ("72386059-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnStartEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("7238605b-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnStartEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("7239e6e9-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnStartEventStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7239e6eb-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnStartEventStructuredStyleKeys.SHOWLABEL;

}
