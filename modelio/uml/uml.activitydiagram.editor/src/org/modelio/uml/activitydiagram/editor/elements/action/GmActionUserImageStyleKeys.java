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

package org.modelio.uml.activitydiagram.editor.elements.action;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmAction when its representation mode is RepresentationMode.IMAGE
 */
@objid ("6ceb91c5-1520-48fe-89c5-504377f49b7a")
public class GmActionUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("de84098d-46ae-4ece-b8ae-b4ee73b4b07c")
     static final StyleKey REPMODE = GmActionStructuredStyleKeys.REPMODE;

    @objid ("9cb0ec2f-6258-4361-a35e-66f4390f88af")
     static final StyleKey FONT = GmActionStructuredStyleKeys.FONT;

    @objid ("c0e59c59-364b-47a3-931b-3b5d7d55d89c")
     static final StyleKey TEXTCOLOR = GmActionStructuredStyleKeys.TEXTCOLOR;

    @objid ("c1256bbc-fcb0-49b8-bce9-68aaadac209b")
     static final StyleKey SHOWSTEREOTYPES = GmActionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("f7f1a389-b914-4ddd-ad4b-d75cbe754c80")
     static final StyleKey SHOWTAGS = GmActionStructuredStyleKeys.SHOWTAGS;

    @objid ("8ef0d5dd-3752-4821-8df9-6a9d6f4fcd2b")
     static final StyleKey AUTOSHOWPINS = GmActionStructuredStyleKeys.AUTOSHOWPINS;

}
