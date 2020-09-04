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

package org.modelio.diagram.editor.state.elements.exit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmExit when its representation mode is RepresentationMode.IMAGE
 */
@objid ("78aa400b-0894-47f4-8969-bd1b80c7afbc")
public class GmExitUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("16e9b050-a199-4b99-b3bd-1df500eb4b62")
     static final StyleKey REPMODE = GmExitStructuredStyleKeys.REPMODE;

    @objid ("335edc68-0fc2-408b-9121-bd0dfc6a664d")
     static final StyleKey FONT = GmExitStructuredStyleKeys.FONT;

    @objid ("32a34786-b9d3-4de6-b394-e00988a3d106")
     static final StyleKey TEXTCOLOR = GmExitStructuredStyleKeys.TEXTCOLOR;

    @objid ("0a1a2e2b-0baa-412c-9cc6-e1d6c8b6d46c")
     static final StyleKey SHOWSTEREOTYPES = GmExitStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("70bd430c-acb8-4714-8d76-2170d15c8c0e")
     static final StyleKey SHOWTAGS = GmExitStructuredStyleKeys.SHOWTAGS;

    @objid ("aab34d3c-1380-4096-b475-3499be980e3a")
     static final StyleKey SHOWLABEL = GmExitStructuredStyleKeys.SHOWLABEL;

}
