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

package org.modelio.diagram.editor.bpmn.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("62240454-55b6-11e2-877f-002564c97630")
public class GmBpmnGatewayStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72adde69-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("GATEWAY_REPMODE", MetaKey.REPMODE);

    @objid ("72adde6b-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("GATEWAY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("72adde6d-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("GATEWAY_FONT", MetaKey.FONT);

    @objid ("72adde6f-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("GATEWAY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("72adde71-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("GATEWAY_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("72adde73-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("GATEWAY_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("72adde75-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("GATEWAY_SHOWLABEL", MetaKey.SHOWLABEL);

}
