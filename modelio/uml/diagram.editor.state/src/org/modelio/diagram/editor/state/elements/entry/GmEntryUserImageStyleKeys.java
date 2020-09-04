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

package org.modelio.diagram.editor.state.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmEntry when its representation mode is RepresentationMode.IMAGE
 */
@objid ("5608d1a7-de9e-408e-957a-a18b3986cb2f")
public class GmEntryUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8ea4aebc-8d1a-46d5-8c79-e11abefb0bcd")
     static final StyleKey REPMODE = GmEntryStructuredStyleKeys.REPMODE;

    @objid ("bfcf12d7-816f-4bc3-869b-68d42ff92530")
     static final StyleKey FONT = GmEntryStructuredStyleKeys.FONT;

    @objid ("1f697474-8bc4-465b-886e-dd6c5bfd72c9")
     static final StyleKey TEXTCOLOR = GmEntryStructuredStyleKeys.TEXTCOLOR;

    @objid ("4d828a3b-0e5e-43b9-8d3c-d40c6d0d7646")
     static final StyleKey SHOWSTEREOTYPES = GmEntryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("4319654c-f30d-4658-8209-f48d7ac6919e")
     static final StyleKey SHOWTAGS = GmEntryStructuredStyleKeys.SHOWTAGS;

    @objid ("eff543f7-72a6-4f48-bda2-39508b6d8fc7")
     static final StyleKey SHOWLABEL = GmEntryStructuredStyleKeys.SHOWLABEL;

}
