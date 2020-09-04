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

package org.modelio.diagram.editor.activity.elements.structuredactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2b545708-55b6-11e2-877f-002564c97630")
public class GmStructuredActivityImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d2907d2a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmStructuredActivityStructuredStyleKeys.REPMODE;

    @objid ("d2907d2c-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmStructuredActivityStructuredStyleKeys.FONT;

    @objid ("d2907d2e-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmStructuredActivityStructuredStyleKeys.TEXTCOLOR;

    @objid ("d2907d30-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmStructuredActivityStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d2907d32-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmStructuredActivityStructuredStyleKeys.SHOWTAGS;

    @objid ("d2907d34-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmStructuredActivityStructuredStyleKeys.AUTOSHOWPINS;

}
