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

package org.modelio.diagram.editor.activity.elements.callevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("29ca5ba9-55b6-11e2-877f-002564c97630")
public class GmCallEventImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1776c0a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmCallEventStructuredStyleKeys.REPMODE;

    @objid ("d1776c0c-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmCallEventStructuredStyleKeys.FONT;

    @objid ("d1776c0e-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmCallEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("d1776c10-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmCallEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d1776c12-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmCallEventStructuredStyleKeys.SHOWTAGS;

    @objid ("d1776c14-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmCallEventStructuredStyleKeys.AUTOSHOWPINS;

}
