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

package org.modelio.diagram.editor.state.elements.initialstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmInitialState} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("f5456c33-55b6-11e2-877f-002564c97630")
public class GmInitialStateImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("810ca6cb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInitialStateStructuredStyleKeys.REPMODE;

    @objid ("810ca6cd-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInitialStateStructuredStyleKeys.FONT;

    @objid ("810ca6cf-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInitialStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("810ca6d1-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInitialStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("810ca6d3-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInitialStateStructuredStyleKeys.SHOWTAGS;

    @objid ("0707c503-bae2-4a1d-b7f1-9dee5a9c1eca")
     static final StyleKey SHOWLABEL = GmInitialStateStructuredStyleKeys.SHOWLABEL;

}
