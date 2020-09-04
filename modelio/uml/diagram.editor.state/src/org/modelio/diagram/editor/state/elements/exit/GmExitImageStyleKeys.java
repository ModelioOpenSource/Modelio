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
@objid ("f5192c06-55b6-11e2-877f-002564c97630")
public class GmExitImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8158f221-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmExitStructuredStyleKeys.REPMODE;

    @objid ("8158f223-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmExitStructuredStyleKeys.FONT;

    @objid ("8158f225-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmExitStructuredStyleKeys.TEXTCOLOR;

    @objid ("8158f227-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmExitStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8158f229-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmExitStructuredStyleKeys.SHOWTAGS;

    @objid ("a27d94b8-8b44-4c79-8e75-a8941bc24bf4")
     static final StyleKey SHOWLABEL = GmExitStructuredStyleKeys.SHOWLABEL;

}
