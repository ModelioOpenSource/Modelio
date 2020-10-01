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

package org.modelio.uml.activitydiagram.editor.elements.objectnode.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmObjectNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("1ab261af-3bba-4bb7-934b-04bc7cddf8d9")
class GmObjectNodeUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("fa4a4c62-ea25-4c60-8a73-548f3651c6eb")
     static final StyleKey REPMODE = GmObjectNodeStructuredStyleKeys.REPMODE;

    @objid ("45857f8e-240e-4c68-ad49-2c7c767389ac")
     static final StyleKey FONT = GmObjectNodeStructuredStyleKeys.FONT;

    @objid ("b8846fdc-9a47-4508-af19-6953b1071674")
     static final StyleKey TEXTCOLOR = GmObjectNodeStructuredStyleKeys.TEXTCOLOR;

    @objid ("e87d0908-a7d8-40db-8ec2-9c28c4e9abe4")
     static final StyleKey SHOWSTEREOTYPES = GmObjectNodeStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("9f527018-b477-42f1-8336-408c756d7385")
     static final StyleKey SHOWTAGS = GmObjectNodeStructuredStyleKeys.SHOWTAGS;

}
