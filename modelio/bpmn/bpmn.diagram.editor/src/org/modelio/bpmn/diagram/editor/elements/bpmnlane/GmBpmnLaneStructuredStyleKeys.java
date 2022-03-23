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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnLane} when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("611c0a56-55b6-11e2-877f-002564c97630")
public class GmBpmnLaneStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71abfece-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("71abfed0-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FILLMODE", MetaKey.FILLMODE);

    @objid ("71abfed2-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("71abfed4-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("71abfed6-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FONT", MetaKey.FONT);

    @objid ("71abfed8-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("71abfeda-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("71abfedc-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_SHOWTAGS", MetaKey.SHOWTAGS);

}
