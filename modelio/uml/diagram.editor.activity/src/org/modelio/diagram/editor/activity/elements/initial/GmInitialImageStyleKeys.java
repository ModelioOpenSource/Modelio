/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.activity.elements.initial;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmInitial when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2a9cedd0-55b6-11e2-877f-002564c97630")
public class GmInitialImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d03e4fc9-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInitialStructuredStyleKeys.REPMODE;

    @objid ("d03e4fcb-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInitialStructuredStyleKeys.FONT;

    @objid ("d03e4fcd-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInitialStructuredStyleKeys.TEXTCOLOR;

    @objid ("d03e4fcf-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInitialStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d03e4fd1-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInitialStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("d03e4fd3-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmInitialStructuredStyleKeys.SHOWLABEL;

}
