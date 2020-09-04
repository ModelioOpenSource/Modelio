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

package org.modelio.diagram.editor.activity.elements.structuredactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2b58eaec-55b6-11e2-877f-002564c97630")
public class GmStructuredActivityStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d29b2b9e-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("STRUCTUREDACTIVITY_REPMODE", MetaKey.REPMODE);

    @objid ("d29b2ba0-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("STRUCTUREDACTIVITY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d29cb22a-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("STRUCTUREDACTIVITY_FILLMODE", MetaKey.FILLMODE);

    @objid ("d29cb22c-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("STRUCTUREDACTIVITY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d29cb22e-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("STRUCTUREDACTIVITY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d29cb230-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("STRUCTUREDACTIVITY_FONT", MetaKey.FONT);

    @objid ("d29cb232-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("STRUCTUREDACTIVITY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d29cb234-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("STRUCTUREDACTIVITY_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d29cb236-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("STRUCTUREDACTIVITY_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d29cb238-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("STRUCTUREDACTIVITY_AUTOSHOWPINS",
            MetaKey.AUTOSHOWPINS);

}
