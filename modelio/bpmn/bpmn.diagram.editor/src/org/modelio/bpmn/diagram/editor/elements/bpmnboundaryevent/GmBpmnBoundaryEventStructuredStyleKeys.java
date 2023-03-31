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
package org.modelio.bpmn.diagram.editor.elements.bpmnboundaryevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBoundaryEvent when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("608b14fa-55b6-11e2-877f-002564c97630")
public class GmBpmnBoundaryEventStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7122f8ab-55c1-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("BOUNDARYEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("7122f8ad-55c1-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("BOUNDARYEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("71231fba-55c1-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("BOUNDARYEVENT_FONT", MetaKey.FONT);

    @objid ("71231fbc-55c1-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("BOUNDARYEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("712346ca-55c1-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("BOUNDARYEVENT_SHOWSTEREOTYPES",
                    MetaKey.SHOWSTEREOTYPES);

    @objid ("712346cc-55c1-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("BOUNDARYEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("712346ce-55c1-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("BOUNDARYEVENT_SHOWLABEL", MetaKey.SHOWLABEL);

}
