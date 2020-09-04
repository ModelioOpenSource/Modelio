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

package org.modelio.diagram.editor.state.elements.choice;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmChoice when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f4f79a21-55b6-11e2-877f-002564c97630")
public class GmChoiceStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8126976c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CHOICE_REPMODE", MetaKey.REPMODE);

    @objid ("8126976e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CHOICE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("81269770-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CHOICE_FILLMODE", MetaKey.FILLMODE);

    @objid ("81269772-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CHOICE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81269774-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CHOICE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81269776-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CHOICE_FONT", MetaKey.FONT);

    @objid ("81269778-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CHOICE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("8126977a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CHOICE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("8126977c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CHOICE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8126977e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("CHOICE_SHOWLABEL", MetaKey.SHOWLABEL);

}
