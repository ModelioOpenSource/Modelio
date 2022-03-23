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
package org.modelio.uml.activitydiagram.editor.elements.valuepin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmValuePin when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2b6fce2e-55b6-11e2-877f-002564c97630")
public class GmValuePinStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d2a45352-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("VALUEPIN_REPMODE", MetaKey.REPMODE);

    @objid ("d2a45354-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("VALUEPIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d2a45356-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("VALUEPIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("d2a45358-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("VALUEPIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d2a4535a-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("VALUEPIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d2a4535c-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("VALUEPIN_FONT", MetaKey.FONT);

    @objid ("d2a4535e-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("VALUEPIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d2a45360-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("VALUEPIN_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d2a45362-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("VALUEPIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d2a45364-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("VALUEPIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
