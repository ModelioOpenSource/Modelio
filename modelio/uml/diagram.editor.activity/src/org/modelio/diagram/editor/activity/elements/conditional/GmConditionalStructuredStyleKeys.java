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

package org.modelio.diagram.editor.activity.elements.conditional;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmConditional when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a1b3abd-55b6-11e2-877f-002564c97630")
public class GmConditionalStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1268ce9-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CONDITIONAL_REPMODE", MetaKey.REPMODE);

    @objid ("d1268ceb-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CONDITIONAL_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d1268ced-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CONDITIONAL_FILLMODE", MetaKey.FILLMODE);

    @objid ("d1268cef-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CONDITIONAL_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d1268cf1-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CONDITIONAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d1268cf3-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CONDITIONAL_FONT", MetaKey.FONT);

    @objid ("d1268cf5-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CONDITIONAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d1268cf7-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONDITIONAL_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d1268cf9-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CONDITIONAL_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d1268cfb-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("CONDITIONAL_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
