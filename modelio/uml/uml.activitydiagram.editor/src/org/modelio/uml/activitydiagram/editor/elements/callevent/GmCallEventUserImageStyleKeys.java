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

package org.modelio.uml.activitydiagram.editor.elements.callevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmCallEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("5b0c1ce0-3133-4838-b2db-ce3b23044a18")
public class GmCallEventUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("5ada01da-6f95-481b-a95e-b8a0fd953674")
     static final StyleKey REPMODE = GmCallEventStructuredStyleKeys.REPMODE;

    @objid ("ae70d161-0681-4b72-8a54-d39260153171")
     static final StyleKey FONT = GmCallEventStructuredStyleKeys.FONT;

    @objid ("916c5630-01a8-421e-b2cc-f9bfe47e1509")
     static final StyleKey TEXTCOLOR = GmCallEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("f6f269da-4dfb-4f1c-b631-f8c9cab70e13")
     static final StyleKey SHOWSTEREOTYPES = GmCallEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("85c8f5fd-b427-4a1d-8608-9ba1429fccea")
     static final StyleKey SHOWTAGS = GmCallEventStructuredStyleKeys.SHOWTAGS;

    @objid ("86d64a99-2eff-4357-b710-8f052399a608")
     static final StyleKey AUTOSHOWPINS = GmCallEventStructuredStyleKeys.AUTOSHOWPINS;

}
