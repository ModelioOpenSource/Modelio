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

package org.modelio.diagram.editor.activity.elements.sendsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2b469b6b-55b6-11e2-877f-002564c97630")
public class GmSendSignalImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d09bb20b-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmSendSignalStructuredStyleKeys.REPMODE;

    @objid ("d09bb20d-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmSendSignalStructuredStyleKeys.FONT;

    @objid ("d09d38aa-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmSendSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("d09d38ac-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmSendSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d09d38ae-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmSendSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("d09d38b0-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmSendSignalStructuredStyleKeys.AUTOSHOWPINS;

}
