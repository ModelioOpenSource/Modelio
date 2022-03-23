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
package org.modelio.uml.statediagram.editor.elements.deephistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmDeepHistory when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("f509e9d8-55b6-11e2-877f-002564c97630")
public class GmDeepHistorySimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8112c14c-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmDeepHistoryStructuredStyleKeys.REPMODE;

    @objid ("8112c14e-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmDeepHistoryStructuredStyleKeys.FILLCOLOR;

    @objid ("8112c150-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmDeepHistoryStructuredStyleKeys.FILLMODE;

    @objid ("811447ea-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmDeepHistoryStructuredStyleKeys.LINECOLOR;

    @objid ("811447ec-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmDeepHistoryStructuredStyleKeys.LINEWIDTH;

    @objid ("811447ee-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmDeepHistoryStructuredStyleKeys.FONT;

    @objid ("811447f0-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmDeepHistoryStructuredStyleKeys.TEXTCOLOR;

    @objid ("811447f2-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmDeepHistoryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("811447f4-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmDeepHistoryStructuredStyleKeys.SHOWTAGS;

    @objid ("811447f6-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("DEEPHISTORY_SHOWLABEL", MetaKey.SHOWLABEL);

}
