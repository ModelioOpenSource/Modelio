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

package org.modelio.diagram.editor.bpmn.elements.bpmnendevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("60e51ba8-55b6-11e2-877f-002564c97630")
public class GmBpmnEndEventSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71781d8b-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnEndEventStructuredStyleKeys.REPMODE;

    @objid ("71781d8d-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ENDEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("71781d8f-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ENDEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("71781d91-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ENDEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("71781d93-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ENDEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("71781d95-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnEndEventStructuredStyleKeys.FONT;

    @objid ("71781d97-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnEndEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("71781d99-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnEndEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("71781d9b-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ENDEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the label.
     */
    @objid ("71781d9d-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnEndEventStructuredStyleKeys.SHOWLABEL;

}
