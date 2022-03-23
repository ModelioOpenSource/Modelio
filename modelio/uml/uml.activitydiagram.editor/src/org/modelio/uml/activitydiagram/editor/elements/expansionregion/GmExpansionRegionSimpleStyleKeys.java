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
package org.modelio.uml.activitydiagram.editor.elements.expansionregion;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2a65ff67-55b6-11e2-877f-002564c97630")
public class GmExpansionRegionSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d08c6fc9-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("EXPANSIONREGION_REPMODE", MetaKey.REPMODE);

    @objid ("d08c6fcb-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("EXPANSIONREGION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d08c6fcd-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("EXPANSIONREGION_FILLMODE", MetaKey.FILLMODE);

    @objid ("d08c6fcf-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("EXPANSIONREGION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d08c6fd1-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("EXPANSIONREGION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d08c6fd3-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("EXPANSIONREGION_FONT", MetaKey.FONT);

    @objid ("d08c6fd5-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("EXPANSIONREGION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d08c6fd7-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXPANSIONREGION_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d08c6fd9-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("EXPANSIONREGION_SHOWTAGS", MetaKey.SHOWTAGS);

}
