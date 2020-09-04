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

package org.modelio.diagram.editor.activity.elements.acceptsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmAcceptsignal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2977f5f0-55b6-11e2-877f-002564c97630")
public class GmAcceptSignalImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d0e1e2ca-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmAcceptSignalStructuredStyleKeys.REPMODE;

    @objid ("d0e1e2cc-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmAcceptSignalStructuredStyleKeys.FONT;

    @objid ("d0e1e2ce-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmAcceptSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("d0e1e2d0-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmAcceptSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d0e1e2d2-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmAcceptSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("d0e1e2d4-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmAcceptSignalStructuredStyleKeys.AUTOSHOWPINS;

}
