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

package org.modelio.diagram.editor.activity.elements.calloperation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCalloperation when its representation mode is RepresentationMode.IMAGE
 */
@objid ("29d8173a-55b6-11e2-877f-002564c97630")
public class GmCallOperationImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d189bb8b-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmCallOperationStructuredStyleKeys.REPMODE;

    @objid ("d189bb8d-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmCallOperationStructuredStyleKeys.FONT;

    @objid ("d189bb8f-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmCallOperationStructuredStyleKeys.TEXTCOLOR;

    @objid ("d189bb91-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmCallOperationStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d189bb93-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmCallOperationStructuredStyleKeys.SHOWTAGS;

    @objid ("d189bb95-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmCallOperationStructuredStyleKeys.AUTOSHOWPINS;

}
