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
package org.modelio.uml.activitydiagram.editor.elements.activityfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmActivityfinal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("6033dd82-0e6a-4c8c-a448-25eb8e23bf65")
public class GmActivityFinalUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("c2df893f-5683-4d03-a3aa-967e02d9f371")
    public static final StyleKey REPMODE = GmActivityFinalStructuredStyleKeys.REPMODE;

    @objid ("b473e9e8-15ca-4a65-96f0-0dc355e688e5")
    public static final StyleKey FONT = GmActivityFinalStructuredStyleKeys.FONT;

    @objid ("7c43fda9-8d32-48e8-be00-9b836cc19ff9")
    public static final StyleKey TEXTCOLOR = GmActivityFinalStructuredStyleKeys.TEXTCOLOR;

    @objid ("00522d02-696b-4aae-a635-ee7fc3120f4b")
    public static final StyleKey SHOWSTEREOTYPES = GmActivityFinalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("6c4c4dda-42a7-476d-9db1-11e3d44da8e7")
    public static final StyleKey SHOWTAGS = GmActivityFinalStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the element name label.
     */
    @objid ("427ff0fc-1f6c-45fa-9e78-b98035981094")
    public static final StyleKey SHOWLABEL = GmActivityFinalStructuredStyleKeys.SHOWLABEL;

}
