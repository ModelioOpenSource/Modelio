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

package org.modelio.uml.statediagram.editor.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmEntry when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f5149818-55b6-11e2-877f-002564c97630")
public class GmEntrySimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("812e388c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmEntryStructuredStyleKeys.REPMODE;

    @objid ("812e388e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmEntryStructuredStyleKeys.FILLCOLOR;

    @objid ("812e3890-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmEntryStructuredStyleKeys.FILLMODE;

    @objid ("812e3892-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmEntryStructuredStyleKeys.LINECOLOR;

    @objid ("812e3894-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmEntryStructuredStyleKeys.LINEWIDTH;

    @objid ("812e3896-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmEntryStructuredStyleKeys.FONT;

    @objid ("812e3898-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmEntryStructuredStyleKeys.TEXTCOLOR;

    @objid ("812e389a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmEntryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("812e389c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmEntryStructuredStyleKeys.SHOWTAGS;

    @objid ("812e389e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("ENTRY_SHOWLABEL", MetaKey.SHOWLABEL);

}
