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

package org.modelio.diagram.editor.activity.elements.callbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallBehavior when its representation mode is RepresentationMode.IMAGE
 */
@objid ("29bc9ff2-55b6-11e2-877f-002564c97630")
public class GmCallBehaviorImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d16e444c-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmCallBehaviorStructuredStyleKeys.REPMODE;

    @objid ("d16e444e-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmCallBehaviorStructuredStyleKeys.FONT;

    @objid ("d16e4450-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmCallBehaviorStructuredStyleKeys.TEXTCOLOR;

    @objid ("d16e4452-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmCallBehaviorStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d16e4454-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmCallBehaviorStructuredStyleKeys.SHOWTAGS;

    @objid ("d16e4456-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmCallBehaviorStructuredStyleKeys.AUTOSHOWPINS;

}
