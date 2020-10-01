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

package org.modelio.uml.statediagram.editor.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmState when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f580ee7b-55b6-11e2-877f-002564c97630")
public class GmStateImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81907ccb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmStateStructuredStyleKeys.REPMODE;

    @objid ("8190a3da-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmStateStructuredStyleKeys.FONT;

    @objid ("8190a3dc-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("8190a3de-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8190caea-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmStateStructuredStyleKeys.SHOWTAGS;

    @objid ("8190caec-55c2-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPOINTS = GmStateStructuredStyleKeys.AUTOSHOWPOINTS;

}
