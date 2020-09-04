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

package org.modelio.diagram.editor.bpmn.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.IMAGE
 */
@objid ("62227da7-55b6-11e2-877f-002564c97630")
public class GmBpmnGatewayImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72ac57d2-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnGatewayStructuredStyleKeys.REPMODE;

    @objid ("72ac57d4-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnGatewayStructuredStyleKeys.FONT;

    @objid ("72ac57d6-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnGatewayStructuredStyleKeys.TEXTCOLOR;

    @objid ("72ac57d8-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnGatewayStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("72ac57da-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnGatewayStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("72ac57dc-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmBpmnGatewayStructuredStyleKeys.SHOWLABEL;

}
