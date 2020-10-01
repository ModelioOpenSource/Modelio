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

package org.modelio.bpmn.diagram.editor.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("62227dc4-55b6-11e2-877f-002564c97630")
public class GmBpmnGatewaySimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72ac57df-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnGatewayStructuredStyleKeys.REPMODE;

    @objid ("72ac57e1-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmBpmnGatewayStructuredStyleKeys.FILLCOLOR;

    @objid ("72ac57e3-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("GATEWAY_FILLMODE", MetaKey.FILLMODE);

    @objid ("72ac57e5-55c1-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("GATEWAY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("72ac57e7-55c1-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("GATEWAY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("72ac57e9-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnGatewayStructuredStyleKeys.FONT;

    @objid ("72ac57eb-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnGatewayStructuredStyleKeys.TEXTCOLOR;

    @objid ("72ac57ed-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnGatewayStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("72ac57ef-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("GATEWAY_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the label.
     */
    @objid ("72ac57f1-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnGatewayStructuredStyleKeys.SHOWLABEL;

}
