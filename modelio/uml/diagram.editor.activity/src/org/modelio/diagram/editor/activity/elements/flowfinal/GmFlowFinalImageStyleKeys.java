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

package org.modelio.diagram.editor.activity.elements.flowfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmFlowfinal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2a784edb-55b6-11e2-877f-002564c97630")
public class GmFlowFinalImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d2010e6e-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmFlowFinalStructuredStyleKeys.REPMODE;

    @objid ("d2010e70-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmFlowFinalStructuredStyleKeys.FONT;

    @objid ("d2010e72-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmFlowFinalStructuredStyleKeys.TEXTCOLOR;

    @objid ("d2010e74-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmFlowFinalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d202950a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmFlowFinalStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("d202950c-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmFlowFinalStructuredStyleKeys.SHOWLABEL;

}
