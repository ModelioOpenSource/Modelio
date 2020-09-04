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

package org.modelio.diagram.editor.state.elements.deephistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmDeepHistory when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("f506dc99-55b6-11e2-877f-002564c97630")
public class GmDeepHistoryImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8155e4cf-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmDeepHistoryStructuredStyleKeys.REPMODE;

    @objid ("8155e4d1-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmDeepHistoryStructuredStyleKeys.FONT;

    @objid ("8155e4d3-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmDeepHistoryStructuredStyleKeys.TEXTCOLOR;

    @objid ("8155e4d5-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmDeepHistoryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8155e4d7-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmDeepHistoryStructuredStyleKeys.SHOWTAGS;

}
