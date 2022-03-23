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
package org.modelio.uml.activitydiagram.editor.elements.inputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("8f32b593-8c6d-4b0a-bede-e33e8977d533")
public class GmInputPinUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("948b9066-3510-4761-89b3-8db094aa68ef")
    static final StyleKey REPMODE = GmInputPinStructuredStyleKeys.REPMODE;

    @objid ("7f8bcef3-8a69-4001-99f6-890ddd8f7c24")
    static final StyleKey FONT = GmInputPinStructuredStyleKeys.FONT;

    @objid ("a74a6aee-29be-47e4-9c53-58621c44a295")
    static final StyleKey TEXTCOLOR = GmInputPinStructuredStyleKeys.TEXTCOLOR;

    @objid ("88202ff5-6134-459f-821c-f1bbbd18f888")
    static final StyleKey SHOWSTEREOTYPES = GmInputPinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("50f67a7d-b675-486d-aaf6-a6d5749134fa")
    static final StyleKey SHOWTAGS = GmInputPinStructuredStyleKeys.SHOWTAGS;

}
