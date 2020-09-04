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

package org.modelio.diagram.editor.bpmn.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.IMAGE
 */
@objid ("5525f768-b5ee-453d-83d1-d8760904e4b7")
public class GmBpmnGatewayUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("1bc11155-99cc-470e-9958-08146208ff72")
     static final StyleKey REPMODE = GmBpmnGatewayStructuredStyleKeys.REPMODE;

    @objid ("4ef26402-e9e7-4ee4-a9ec-50960a7a1841")
     static final StyleKey FONT = GmBpmnGatewayStructuredStyleKeys.FONT;

    @objid ("600c9e5f-50fa-4699-b828-4b635ed64b4c")
     static final StyleKey TEXTCOLOR = GmBpmnGatewayStructuredStyleKeys.TEXTCOLOR;

    @objid ("277fecd7-a008-4e16-ab8b-52f5ec9cf69d")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnGatewayStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("29c444e3-0983-4ebe-a475-d5130a905ada")
     static final StyleKey SHOWTAGS = GmBpmnGatewayStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("a8798df5-e043-4d80-87b5-daec0ad3f04b")
     static final StyleKey SHOWLABEL = GmBpmnGatewayStructuredStyleKeys.SHOWLABEL;

}
