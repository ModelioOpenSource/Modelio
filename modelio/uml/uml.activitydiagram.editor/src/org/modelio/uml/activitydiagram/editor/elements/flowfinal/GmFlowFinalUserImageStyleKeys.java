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
package org.modelio.uml.activitydiagram.editor.elements.flowfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmFlowfinal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("c7e82c07-686c-4588-8ebb-3070857996b2")
public class GmFlowFinalUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("5bd36ca7-d54b-427d-af18-3ced1c33a4e2")
    static final StyleKey REPMODE = GmFlowFinalStructuredStyleKeys.REPMODE;

    @objid ("12e706e0-3e73-4553-961b-059d28a39519")
    static final StyleKey FONT = GmFlowFinalStructuredStyleKeys.FONT;

    @objid ("b037f55a-ef53-4e8b-b741-734d98be472b")
    static final StyleKey TEXTCOLOR = GmFlowFinalStructuredStyleKeys.TEXTCOLOR;

    @objid ("3faffecf-d0bb-4f2e-bb1c-eaac6d693fe6")
    static final StyleKey SHOWSTEREOTYPES = GmFlowFinalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8822433c-27ef-4318-bc84-c1f8d3e06018")
    static final StyleKey SHOWTAGS = GmFlowFinalStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("bf894fee-5471-4f8d-b5f0-92a544a1e84d")
    static final StyleKey SHOWLABEL = GmFlowFinalStructuredStyleKeys.SHOWLABEL;

}
