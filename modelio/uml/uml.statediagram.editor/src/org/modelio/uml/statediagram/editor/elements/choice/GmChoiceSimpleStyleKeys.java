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

package org.modelio.uml.statediagram.editor.elements.choice;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link GmChoice} when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("f4f6139b-55b6-11e2-877f-002564c97630")
public class GmChoiceSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81482930-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmChoiceStructuredStyleKeys.REPMODE;

    @objid ("81482932-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmChoiceStructuredStyleKeys.FILLCOLOR;

    @objid ("81482934-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmChoiceStructuredStyleKeys.FILLMODE;

    @objid ("81482936-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmChoiceStructuredStyleKeys.LINECOLOR;

    @objid ("8149afca-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmChoiceStructuredStyleKeys.LINEWIDTH;

    @objid ("8149afcc-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmChoiceStructuredStyleKeys.FONT;

    @objid ("8149afce-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmChoiceStructuredStyleKeys.TEXTCOLOR;

    @objid ("8149afd0-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmChoiceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8149afd2-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmChoiceStructuredStyleKeys.SHOWTAGS;

    @objid ("8149afd4-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmChoiceStructuredStyleKeys.SHOWLABEL;

}
