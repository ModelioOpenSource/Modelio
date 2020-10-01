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

package org.modelio.uml.activitydiagram.editor.elements.valuepin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmValuePin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("a2cf1432-8049-4969-ba38-df28ba43d065")
public class GmValuePinUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("f0ca4408-44d6-4ae4-9b89-7ab58ead371f")
     static final StyleKey REPMODE = GmValuePinStructuredStyleKeys.REPMODE;

    @objid ("b5971775-22e4-47f8-a1b6-6379cc643532")
     static final StyleKey FONT = GmValuePinStructuredStyleKeys.FONT;

    @objid ("8499705a-7dad-428e-8d77-f5014e07bfdc")
     static final StyleKey TEXTCOLOR = GmValuePinStructuredStyleKeys.TEXTCOLOR;

    @objid ("e3497642-45a8-43aa-975f-6e90f8a88b6c")
     static final StyleKey SHOWSTEREOTYPES = GmValuePinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("bdcb8368-4699-4526-bbc4-ad2db9033abb")
     static final StyleKey SHOWTAGS = GmValuePinStructuredStyleKeys.SHOWTAGS;

}
